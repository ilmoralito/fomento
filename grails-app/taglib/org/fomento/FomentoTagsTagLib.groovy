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

		out << g.formatNumber(number:fp, type:"number", maxFractionDigits:2)
	}

	def dp = { attrs ->
		Partner partner = attrs.partner
		BigDecimal tas = attrs.tas
		BigDecimal up = attrs.up
		Integer period = attrs.period
		String flag = attrs.flag

		def result = dividendService.getPeriodUtility(partner, tas, up, period)

		if (attrs.flag == "partner") {
			out << g.formatNumber(number:result.partnerDP, type:"number", maxFractionDigits:2)
		} else {
			out << g.formatNumber(number:result.factoryDP, type:"number", maxFractionDigits:2)
		}
	}

	def dd = { attrs ->
		BigDecimal up = attrs.double("up")
		BigDecimal pd = attrs.double("pd")
		BigDecimal fp = attrs.double("fp")

		BigDecimal dd = reportService.dd(up, pd, fp)

		out << g.formatNumber(number:dd, type:"number", maxFractionDigits:2)
	}

}
