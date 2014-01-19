package org.fomento

import static java.util.Calendar.*

class FomentoTagsTagLib {

	def feeService
	def dividendService
	ReportService reportService

	static namespace = "fomento"

	def periods = {
		def currentPeriod = new Date()[YEAR]

		for(period in 2012 ..currentPeriod) {
			out << g.link(controller:'partner', action:'report', params:[id:params?.id, period:period], class:"btn ${(period == params.int('period')) ? 'btn-info' : 'btn-link'}") {period}
		}
	}

	def currentYear = {
		out << new Date()[YEAR]
	}

	def fp = { attrs ->
		Partner partner = attrs.partner
		Integer period = attrs.int("period")
		String flag = attrs.flag

		BigDecimal fp = reportService.fp(partner, period, flag)

		out << fp
	}

	def dd = { attrs ->
		BigDecimal up = attrs.double("up")
		BigDecimal pd = attrs.double("pd")
		BigDecimal fp = attrs.double("fp")

		BigDecimal dd = reportService.dd(up, pd, fp)

		out << dd
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
		out << totalCuotas
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

		out << totalAporte
	}

	def partnerSaldo = { attrs ->
		Partner partner = attrs.partner
		String flag = attrs.flag

		def total = reportService.totalResultsByPartnerOrFactory(partner, flag)

     	out << g.formatNumber(number:total, type:"number", maxFractionDigits:"2")
	}
}
