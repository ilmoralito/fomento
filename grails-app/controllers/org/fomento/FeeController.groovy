package org.fomento

import grails.plugins.springsecurity.Secured
import static java.util.Calendar.*

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class FeeController {

	static defaultAction = "create"
	static allowedMethods = [
		create:["GET", "POST"],
		list:"GET"
	]

	def create(String typeOfPayment) {
		if (request.method == "POST") {
			def p = params.list("partners")

			def today = new Date()
			def period = today[YEAR]

			if (p) {
				p.each { id ->
					def partner = Partner.get(id)

					partner.addToFees(
						fee:partner.affiliation.fee,
						factoryFee:partner.affiliation.factoryFee,
						period:period
					)

					partner.save()
				}

				flash.message = "${p.size()} cuotas aplicadas"
			} else {
				flash.message = "Debes seleccionar socios para poder continuar"
			}
		}

		[partners:Partner.byTypeOfPayment(typeOfPayment).byStatus(true).list(params)]
	}

	def list() {
		def fees = Fee.list()

		[fees:fees]
	}

}
