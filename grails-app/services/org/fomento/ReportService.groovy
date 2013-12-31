package org.fomento

class ReportService {

	DividendService dividendService
	FeeService feeService

    def fp(Partner partner, Integer period, String fee, String capital) {
        def result = dividendService.feePeriodData(partner, period)

        BigDecimal ta = (capital == "capitalization") ? result.tas : result.tae
        BigDecimal ap = feeService.totalFeesByPeriod(partner, period, fee)
        BigDecimal ss

        if (capital == "capitalization") {
            ss = partner?.affiliation?.capitalization

            //ss = sis + dividendService.total(partner)
        } else {
            ss = partner?.affiliation?.factoryCapital
        }

        def criteria = Affiliation.createCriteria()
        BigDecimal ts = criteria.get {
            projections {
                sum capital
            }
        }

        BigDecimal fp = (ap + ss) / (ta + ts)

        return fp
    }

    def dd(BigDecimal up, BigDecimal pd, BigDecimal fp) {
        BigDecimal uc = up * pd
        BigDecimal dd = uc * fp

        return dd
    }
    // total de cuotas del socio
    def totalC(Partner partner, String flag){
        def cuota = Fee.findAllByPartner(partner)
        def tcuota
        if (cuota) {
            def criteria = Fee.createCriteria()
            def totalCuotas = criteria.get{
                eq("partner", partner)
                projections {
                    if (flag=="socio") {
                        sum ('fee') 
                    }else{
                        sum("factoryFee")
                    }
                }
            }
            tcuota = totalCuotas
        }else{
            tcuota = 0
        }
        return tcuota
    }
    // Total de capitalizaciones
    def tCap(Partner partner){
        def capital, idDiv, divBruto, divNeto, capTotal = 0
        String porcentaje

        def div = Dividend.findAllByPartner(partner)
            if (div) {
                div.each{
                    idDiv = it.id
                    def criteria = Capitalization.createCriteria()
                    capital = criteria.get{
                        dividend{
                            eq "id", idDiv
                        }
                    }
                    if (capital) {
                        porcentaje = capital.toString() //convertir a string el dato optenido de la consulta
                        divBruto = it.partnerDividend
                        divNeto = divBruto-(divBruto * 0.1)
                        capTotal = capTotal + ((divNeto*porcentaje.toInteger())/100)
                    }else{
                        capTotal = capTotal   
                    }
                }//end each
            }else{
                capTotal = 0
            }
        return capTotal
    }

    //Calcular Saldo Inicial
    def saldoInicial(Partner partner, String flag, BigDecimal saldoIni){
        def salIni = Affiliation.findById(partner.id)
        if (flag == "socio") {
            saldoIni = salIni.capitalization
        }else{
            saldoIni = salIni.factoryCapital
        }
        return saldoIni
    }

    //Capitalizacion del periodo
    def periodCap(Partner partner, Integer period, boolean cap, String flag){
        def div = Dividend.findByPartnerAndPeriod(partner, period)
        
        def criteria = Capitalization.createCriteria()
        def capital = criteria.get{
            dividend{
                eq "id", div.id
            }
        }
        if (capital) {
            String porcentaje = capital
            def capi, retiro
            def divNeto = (div.partnerDividend - (div.partnerDividend*0.1)) 
            capi = (divNeto * porcentaje.toInteger())/100
            retiro = divNeto - capi
            switch(flag) {
                  case "capital":
                      return capi
                  break
                  case "retiro":
                      return retiro
                  break
            }  
        }else{
            return cap = false 
        }
        
    }
}
