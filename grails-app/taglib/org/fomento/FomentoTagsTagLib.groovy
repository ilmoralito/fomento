package org.fomento

import static java.util.Calendar.*

class FomentoTagsTagLib {

	def feeService
	def dividendService
	ReportService reportService

	static namespace = "fomento"

	def partnerStatus = { attrs ->
		if (attrs.status) {
			out << "Activo"
		} else {
			out << "Desabilitado"
		}
	}

	def periods = { attrs ->
		def ctrl = attrs.ctrl
		def now = new Date()
		def currentYear = now[YEAR]

		def periods = (2013..currentYear).toArray()

		for(period in periods) {
			if (ctrl == "deductions") {
				out << g.link(controller:"deduction", action:"deductions", params:[year:period], class:"btn ${(period == 2014 && period != 2013) ? 'btn-info' : 'btn-link'}") {period}
			} else if (ctrl == "report") {
				out << g.link(controller:"report", params:[period:period], class:"btn ${(period == 2014 && period != 2013) ? 'btn-info' : 'btn-link'}") {period}
			} else {
				out << g.link(controller:'partner', action:'report', params:[id:params.int("id"), period:period], class:"btn ${(period == 2014 && period != 2013) ? 'btn-info' : 'btn-link'}") {period}
			}
		}
	}

	def currentYear = { attrs ->
		out << new Date()[YEAR]
	}

	def fp = { attrs ->
		Partner partner = attrs.partner
		Integer period = attrs.int("period")
		String fee = attrs.fee
		String capital = attrs.capital

		BigDecimal fp = reportService.fp(partner, period, fee, capital)

		out << fp
	}

	def dd = { attrs ->
		BigDecimal up = attrs.double("up")
		BigDecimal pd = attrs.double("pd")
		BigDecimal fp = attrs.double("fp")

		BigDecimal dd = reportService.dd(up, pd, fp)

		out << g.formatNumber(number:dd, type:"number", maxFractionDigits:"2")
	}

	def feeDetail = { attrs ->
		Partner partner = attrs.partner
		Integer period = attrs.int("period")
		Integer month = attrs.int("month")

		Date from = new Date() - 21
		Date to = new Date() + 9
		def query = Fee.where {
			partner == partner
		}

		out << "27, S:400 E:205.5"
	}

	def partnerFee = { attrs ->
		Date fecha1 = new Date()
		def dialast, diafirst, k
		
		Partner parN = attrs.parN
		String flag = attrs.flag
		Integer mth = attrs.int("mth")
		Integer peri = attrs.int("peri")

		
	   	
	   	switch(mth) {
	   		case 0: fecha1.set(month:0) 
	   			dialast = 31-fecha1.date
	   		break;
	   		case 1:
	   			fecha1.set(month:1)
	   			if (fecha1.date==29) {
	   				dialast = 29-fecha1.date
	   			}else{
	   				dialast = 28-fecha1.date
	   			}
	   			
	   			break;
	   		case 2:
	   			fecha1.set(month:2)
	   			dialast = 31-fecha1.date
	   		break;
	   		case 3:
	   			fecha1.set(month:3)
	   			dialast = 30-fecha1.date
	   		break;
	   		case 4:
	   			fecha1.set(month:4)
	   			dialast = 31-fecha1.date
	   		break;
	   		case 5:
	   			fecha1.set(month:5)
	   			dialast = 30-fecha1.date
	   		break;
	   		case 6:
	   			fecha1.set(month:6)
	   			dialast = 31-fecha1.date
	   		break;
	   		case 7:
	   			fecha1.set(month:7)
	   			dialast = 31-fecha1.date
	   		break;
	   		case 8:
	   			fecha1.set(month:8)
	   			dialast = 30-fecha1.date
	   		break;
	   		case 9:
	   			fecha1.set(month:9)
	   			dialast = 31-fecha1.date
	   		break;
	   		case 10:
	   			fecha1.set(month:10)
	   			dialast = 30-fecha1.date
	   		break;
	   		case 11:
	   			fecha1.set(month:11)
	   			dialast = 31-fecha1.date
	   		break;
	   	}
	   		

		
		fecha1.set(year:peri)
        diafirst = fecha1.date + 1
               

		def cuota = Fee.findAllByPartnerAndDateCreatedBetween(parN,fecha1-diafirst,fecha1+dialast)
		
		cuota.each{
			if (flag=="soc") {
				k = it.fee
			}else{
				k = it.factoryFee
			}
			
		}

		out << k
	}

	def parTotal = { attrs ->
		Partner parN = attrs.parN
		String flag = attrs.flag
		Integer peri = attrs.int("peri")
		
		def criteria = Fee.createCriteria()
		def totalCuotas = criteria.get{
			eq("period", peri)
			eq("partner", parN)
			projections {
				if (flag=="soc") {
					sum ('fee')	
				}else{
					sum("factoryFee")
				}
            }
        } 
		out<< totalCuotas
	}

	def aporteTotal = { attrs ->
		String flag = attrs.flag
		Integer peri = attrs.int("peri")

		def criteria = Fee.createCriteria()
		def totalAporte = criteria.get{
			eq("period", peri)
			projections{
				if(flag=="soc"){
					sum("fee")
				}else{
					sum("factoryFee")
				}
			}
		}


		out<< totalAporte
	}

	def partnerSaldo = { attrs ->
		Partner partner = attrs.partner
		String flag = attrs.flag
		
		def totalCuotas = reportService.totalC(partner, flag)
	    //----------------------------------------------
        def capitalizationTotal
        if (flag=="socio") {
        	capitalizationTotal = reportService.tCap(partner)
        }
       	//----------------------------------------------
       	BigDecimal saldoIni = 0
       	def saldoI = reportService.saldoInicial(partner, flag, saldoIni) 
        BigDecimal total
       
        if (flag=="socio") {
        	total = saldoI + totalCuotas + capitalizationTotal
        }else{
        	total = saldoI + totalCuotas
        }
        
     	out<< g.formatNumber(number:total, type:"number", maxFractionDigits:"2")
	}

}
