package org.fomento

class FomentoTagsTagLib {
	static namespace = "fomento"

	def partnerStatus = {attrs ->
		if (attrs.status) {
			out << "Activo"
		} else {
			out << "Desabilitado"
		}
	}
}
