package org.fomento

import static java.util.Calendar.*

class FomentoTagsTagLib {

	def feeService

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
				out << g.link(controller:"deduction", action:"deductions", params:[year:period]) {period}
			} else {
				out << g.link(controller:'fee', action:'list', params:[id:params?.id, year:period], class:"btn ${(period == 2014 && period != 2013) ? 'btn-info' : 'btn-link'}") {period}
			}
		}
	}

	def currentYear = { attrs ->
		out << new Date()[YEAR]
	}

	def aps = { attrs, body ->
		def partner = attrs.partner
		def period = attrs.period

		out << feeService.partnerTotalCapitalization(partner, period)
	}

	def fps = { attrs ->
		def partner = attrs.partner
		def period = attrs.period
		BigDecimal aps = fomento.aps(partner:partner, period:period).toDouble()
		def tas = attrs.tas

		out << tas / aps
	}
}
