package org.fomento

import grails.plugins.springsecurity.Secured
import static java.util.Calendar.*

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class FeeController {

	static defaultAction = "create"
	static allowedMethods = [
		create:["GET", "POST"],
		list:["GET", "POST"],
		show:"GET",
		update:["GET", "POST"]
	]

	def create(String typeOfPayment) {
		if (request.method == "POST") {
			def p = params.list("partners")

			if (p) {
				for(id in p) {
					def partner = Partner.get(id)

					//check if current partner has reach 12 fees by period
					//if this condition is true then abort adding a new fee
					def period = Calendar.instance.get(Calendar.YEAR)
					def query = Fee.countByPartnerAndPeriod(partner, period)

					if (query == 12) {
						break
					}

					if (partner) {
						def date = (!params?.dateCreated) ? new Date() : new Date().parse("yyyy-MM-dd", params?.dateCreated)

						if (typeOfPayment == "Catorcena") {
							def range = partner.affiliation.range
							def lastFee = Fee.findAllByPartner(partner).last()

							if (range == lastFee.fee) {
								lastFee.fee = partner.affiliation.fee
								lastFee.lastUpdated = new Date()
								lastFee.save()
								break
							}
						}

						partner.addToFees(
							fee:(typeOfPayment == "Catorcena" && partner.affiliation.range) ? partner.affiliation.range : partner.affiliation.fee,
							factoryFee:partner.affiliation.factoryFee,
							period:period,
							dateCreated:date,
							lastUpdated:date
						)

						partner.save()
					}
				}

				flash.message = "${p.size()} cuotas aplicadas"
			} else {
				flash.message = "Debes seleccionar socios para poder continuar"
			}
		}

		[partners:Partner.byTypeOfPayment(typeOfPayment).byStatus(true).list(params)]
	}

	def list() {
		if (request.method == "POST") {
			def criteria = Fee.createCriteria()
			def partners = criteria.list {
				eq "period", params.int("period")
				projections {
					distinct "partner"
				}
			}

			return [partners:partners]
		}
	}

	def show(Long id) {
		def fee = Fee.get(id)

		if (!fee) {
			response.sendError 404
		}

		[fee:fee]
	}

	def update(Long id) {
		def fee = Fee.get(id)

		if (!fee) {
			response.sendError 404
		}
		
		params.dateCreated = new Date().parse("yyyy-MM-dd", params?.dateCreated)
		fee.properties["fee", "factoryFee", "dateCreated"] = params
		
		if (!fee.save()) {
			render view:"show", model:[fee:fee, id:id, partner:params?.partner, period:params?.period]
			return false
		}

		flash.message = "Aztualizacion confirmada"

		redirect action:"show", params:[id:id, partner:params?.partner, period:params?.period]
	}

}
