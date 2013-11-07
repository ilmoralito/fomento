package org.fomento

import static java.util.Calendar.*

class FomentoTagsTagLib {

	def feeService
	def dividendService

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

	def dp = { attrs ->
		Partner partner = attrs.partner
		BigDecimal tas = attrs.tas
		BigDecimal up = attrs.up
		Integer period = attrs.period
		String flag = attrs.flag

		def result = dividendService.getPeriodUtility(partner, tas, up, period)

		if (attrs.flag == "partner") {
			out << result.partnerDP
		} else {
			out << result.factoryDP
		}
	}

	def tap = { attrs ->
		def tas = attrs.double("tas")
		def tae = attrs.double("tae")

		def tap = tas + tae

		out << g.formatNumber(number:tap, type:"number", maxFractionDigits:2)
	}

	def dd = { attrs ->
		BigDecimal tas = attrs.double("tas")
		BigDecimal tae = attrs.double("tae")
		String flag = attrs.flag
		BigDecimal dd = 0

		BigDecimal tap = tas + tae

		if (flag) {
			dd = (tas / tap) * 100
		} else {
			dd = (tae / tap) * 100
		}

		out << g.formatNumber(number:dd, type:"number", maxFractionDigits:2) + "%"
	}
}
