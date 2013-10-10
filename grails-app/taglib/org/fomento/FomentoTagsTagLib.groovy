package org.fomento

import static java.util.Calendar.*

class FomentoTagsTagLib {
	static namespace = "fomento"

	def partnerStatus = { attrs ->
		if (attrs.status) {
			out << "Activo"
		} else {
			out << "Desabilitado"
		}
	}

	def periods = { attrs ->
		def now = new Date()
		def currentYear = now[YEAR]

		def periods = (2013..currentYear).toArray()

		for(period in periods) {
			out << g.link(controller:'fee', action:'list', params:[id:params?.id, year:period], class:"btn btn-link") {period}
		}
	}
}
