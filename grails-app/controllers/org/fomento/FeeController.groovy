package org.fomento
import org.hibernate.transform.AliasToEntityMapResultTransformer
import grails.plugins.springsecurity.Secured
import static java.util.Calendar.*

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class FeeController {

	def feeService

	static defaultAction = "create"
	static allowedMethods = [
		create:["GET", "POST"],
		list:["GET", "POST"],
		show:"GET",
		update:["GET", "POST"],
		deleteFees:["GET", "POST"],
		confirmDeleteFees:"POST"
	]

	def create(String typeOfPayment) {
		if (request.method == "POST") {
			def p = params.list("partners")

			if (p) {
				for(id in p) {
					def partner = Partner.get(id)

					def period = (params?.dateCreated) ? new Date().parse("yyyy-MM-dd", params.dateCreated)[YEAR] : new Date()[YEAR]
					def count = Fee.countByPartnerAndPeriod(partner, period)

					//check if current partner has reach 12 fees by period
					//if this condition is true then abort adding a new fee
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

		params.dateCreated = new Date().parse("yyyy-MM-dd", params?.dateCreated)
		params.period = params.dateCreated[YEAR]
		fee.properties["fee", "factoryFee", "dateCreated", "period"] = params

		flash.message = (fee.save()) ? "Actualizacion confirmada" : "A ocurrido un error. Porfavor intentalo otravez"
		redirect action:"show", params:[id:id]
	}

	@Secured(["ROLE_ADMIN"])
	def confirmDeleteFees() {
		def dateCreated = new Date().parse("yyyy-MM-dd", params?.dateCreated).clearTime()
		def typeOfPayments = params.typeOfPayment.tokenize(",")

		def fees = feeService.listFeesToDelete(dateCreated, typeOfPayments)

		fees.each { fee ->
			fee.delete()
		}

		flash.message = "Cuotas eliminadas"
		redirect action:"deleteFees"
	}

	def deleteFees() {
		def criteria = Fee.createCriteria()
		def results = criteria.list {
			projections {
				property "dateCreated"
			}
		}

		def dates = results*.format("yyyy-MM-dd").unique().reverse()

		if (request.method == "POST") {
			List typeOfPayments = params.list("typeOfPayment")

			if (!typeOfPayments) {
				redirect action:"deleteFees"
				return false
			}

			def dateCreated = new Date().parse("yyyy-MM-dd", params?.dateCreated).clearTime()
			def fees = feeService.listFeesToDelete(dateCreated, typeOfPayments)

			return [fees:fees, dates:dates]
		}

		[dates:dates]
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
