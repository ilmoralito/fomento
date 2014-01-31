package org.fomento
import org.hibernate.transform.AliasToEntityMapResultTransformer
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
					def period = (params?.dateCreated) ? new Date().parse("yyyy-MM-dd", params.dateCreated)[YEAR] : new Date()[YEAR]
					def count = Fee.countByPartnerAndPeriod(partner, period)

					if (count == 12) {
						break
					}

					if (partner) {
						def date = (!params?.dateCreated) ? new Date() : new Date().parse("yyyy-MM-dd", params?.dateCreated)

						if (typeOfPayment == "Catorcena") {
							if (partner?.fees) {
								def portion = partner.affiliation.portion
								def lastFee = Fee.findAllByPartner(partner).last()

								if (portion == lastFee.fee) {
									lastFee.fee = partner.affiliation.fee
									lastFee.lastUpdated = new Date()
									lastFee.save()
									break
								}
							}
						}

						partner.addToFees(
							fee:(typeOfPayment == "Catorcena" && partner.affiliation.portion) ? partner.affiliation.portion : partner.affiliation.fee,
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

		if (params?.typeOfPayment == "Catorcena" && params?.filter) {
			def criteria = Partner.createCriteria()
			def partners = criteria.list() {
				eq "status", true

				affiliation {
					eq "typeOfPayment", "Catorcena"
					if (params?.filter == "divididos") {
						isNotNull "portion"
					} else {
						isNull "portion"
					}
				}
			}

			return [partners:partners]
		} else {
			return [partners:Partner.byTypeOfPayment(typeOfPayment).byStatus(true).list(params)]
		}
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

	@Secured(['ROLE_ADMIN'])
	def show(Long id) {
		def fee = Fee.get(id)

		if (!fee) {
			response.sendError 404
		}

		[fee:fee]
	}

	@Secured(['ROLE_ADMIN'])
	def update(Long id) {
		def fee = Fee.get(id)

		if (!fee) {
			response.sendError 404
		}
		if (params.dateCreated.empty) {
			params.dateCreated = new Date().parse("yyyy-MM-dd", params?.dateCre)
		}else{
			params.dateCreated = new Date().parse("yyyy-MM-dd", params?.dateCreated)
		}
		
		fee.properties["fee", "factoryFee", "dateCreated"] = params

		if (!fee.save()) {
			render view:"show", model:[fee:fee, id:id, partner:params?.partner, period:params?.period]
			return false
		}

		flash.message = "Aztualizacion confirmada"

		redirect action:"show", params:[id:id, partner:params?.partner, period:params?.period]
	}

	def elist(){
		if (request.method == "POST" || params.flag!=null) {
			def partnerTotal, max, offset
			max = params.max
        	offset = params.offset
			[partners:Partner.list(max:max, offset:offset, sort:"fullName"),peri:params.peri, partnerTotal:Partner.count(), all:params.all]
		}
		
	}

}

