package org.fomento

import org.hibernate.transform.AliasToEntityMapResultTransformer

class ReportService {

	DividendService dividendService
	FeeService feeService

    def fp(Partner partner, Integer period, String flag) {
        def result = dividendService.feePeriodData(period)
        def tssResults = tss(period)
        def tss = (flag == "socio") ? tssResults.totalPartnerCapital : tssResults.totalFactoryCapital
        def total = totalResultsByPartnerOrFactory(partner, flag)

        BigDecimal fp = total / tss
        return fp
    }

    def dd(BigDecimal up, BigDecimal pd, BigDecimal fp) {
        BigDecimal uc = up * pd
        BigDecimal dd = uc * fp

        return dd
    }

    def totalResultsByPartnerOrFactory(Partner partner, String flag) {
        def totalCuotas = totalC(partner, flag)
        //----------------------------------------------

        def capitalizationTotal = (flag == "socio") ? tCap(partner) : 0
        def totalDividendFactory = (flag == "empresa") ? tDividendFactory(partner) : 0
        //----------------------------------------------
        BigDecimal saldoIni = 0
        def saldoI = saldoInicial(partner, flag, saldoIni)
        BigDecimal total

        if (flag=="socio") {
            total = saldoI + totalCuotas + capitalizationTotal
        }else{
            total = saldoI + totalCuotas + totalDividendFactory
        }

        total
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
        def capital, divBruto, divNeto, capTotal = 0
        String porcentaje

        def dividends = Dividend.findAllByPartner(partner)

        if (dividends) {
            dividends.each { dividendInstance ->
                def criteria = Capitalization.createCriteria()

                capital = criteria.get{
                    dividend {
                        eq "id", dividendInstance.id
                    }
                }

                capital = (capital) ?: 100

                porcentaje = capital.toString()
                divBruto = dividendInstance.partnerDividend
                divNeto = divBruto-(divBruto * 0.1)
                capTotal = capTotal + ((divNeto*porcentaje.toInteger())/100)
            }
        } else {
            capTotal = 0
        }

        return capTotal
    }

    def tDividendFactory(Partner partner){
        def dFactory = Dividend.createCriteria()
        def dividendFactory = dFactory.get {
            eq "partner", partner
            projections{
                sum("factoryDividend")
            }
        }
        def result = (dividendFactory) ? (dividendFactory - (dividendFactory * 0.1)) : 0
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

    //Total saldo socios
    def tss(Integer periodToNotInclude) {
        //total affiliation
        def criteria = Affiliation.createCriteria()
        def affiliationTotal = criteria.get {
            partner {
                eq "status", true
            }

            projections {
                sum "capitalization", "saldoInicialSocio"
                sum "factoryCapital", "saldoInicialEmpresa"
            }

            resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        }

        //total fees
        def feeCriteria = Fee.createCriteria()
        def feeTotal = feeCriteria.get {
            partner {
                eq "status", true
            }

            projections {
                sum "fee", "fee"
                sum "factoryFee", "factoryFee"
            }

            resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        }

        //list all dividends
        def dividends = Dividend.findAllByPeriodNotEqual(periodToNotInclude)

        def totalCapitalization = 0.0
        def totalFactoryDividend = 0.0

        dividends.each { dividend ->
            def capitalization = (dividend?.capitalization?.percentage) ? dividend.capitalization.percentage.toString().toInteger()  : 100
            def dividendNeto = dividend.partnerDividend - (dividend.partnerDividend * 0.1)

            totalCapitalization = totalCapitalization + (dividendNeto * (capitalization / 100))
            totalFactoryDividend = totalFactoryDividend + (dividend.factoryDividend - (dividend.factoryDividend * 0.1))
        }
        def totalPartnerCapital = affiliationTotal.saldoInicialSocio + feeTotal.fee + totalCapitalization
        def totalFactoryCapital = affiliationTotal.saldoInicialEmpresa + feeTotal.factoryFee + totalFactoryDividend
        [totalPartnerCapital:totalPartnerCapital, totalFactoryCapital:totalFactoryCapital]
    }
}
