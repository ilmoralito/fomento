package org.fomento

import grails.plugins.springsecurity.Secured
import org.compass.core.engine.SearchEngineQueryParseException

@Secured(["ROLE_ADMIN","ROLE_USER"])
class PartnerController {

    def configurationService
    def searchableService

	static defaultAction = "list"
	static allowedMethods = [
		"list":["GET","POST"],
		"create":["GET","POST"],
        "delete":["GET","POST"],
        "show":"GET",
        "update":"POST",
        "changeStatus":["GET","POST"]
	]

    def list() {
        if (request.method == "GET") {
    	   return [partners:Partner.list()]
        } else {
            //search by enrollment date property in Affiliation domain class
            if (params?.from && params?.to) {
                return [partners:Partner.fromTo(parseDate(params.from), parseDate(params.to)).list()]
            }

            //filter by partner status
            if (params?.status) {
                return [partners:Partner.findAllByStatus((params.status == "Activo") ? true : false)]
            }

            //search by patern domain class properties
            if (!params?.query?.trim()) {
                return [:]
            } else {
                try {
                    return [partners: searchableService.search(params.query, params).results]
                } catch(SearchEngineQueryParseException ex) {
                    return [parseException: true]
                }
            }
        }
    }

    def create() {
    	if (request.method == "POST") {
            def ed = (!params?.affiliation?.enrollmentDate) ? new Date() : new Date().parse("yyyy-MM-dd", params?.affiliation?.enrollmentDate)

            def affiliation = new Affiliation(
                fee: params?.affiliation?.fee,
                typeOfPayment: params?.affiliation?.typeOfPayment,
                factoryFee: configurationService.loadFactoryFee(),
                enrollmentDate: ed,
                capitalization: (params?.affiliation?.capitalization) ?: 0
            )

            def partner = new Partner(
                fullName: params?.fullName,
                numberOfEmployee: params?.numberOfEmployee,
                identificationCard: params?.identificationCard,
                department: params?.department,
                salary: params?.salary,
                affiliation: affiliation
            )

    		if (!partner.save()) {
    			return [partner:partner]
    		}

    		flash.message = "Socio creado exitosamente"
    	} else {
    		[partner:new Partner(params)]
    	}
    }

    @Secured("ROLE_ADMIN")
    def delete(Integer id) {
        def partner = Partner.get(id)

        if (!partner) {
            response.sendError 404
        }

        if (request.method == "GET") {
            return [partner:partner]
        } else {
            partner.delete()

            flash.message = "Socio eliminado correctamente"
            redirect action:"list"
        }
    }

    def show(Integer id) {
        def partner = Partner.get(id)

        if (!partner) {
            response.sendError 404
        }

        [partner:partner]
    }

    @Secured("ROLE_ADMIN")
    def update(Integer id) {
        def partner = Partner.get(id)

        if (!partner) {
            response.sendError 404
        }

        partner.fullName = params?.fullName
        partner.numberOfEmployee = params.int("numberOfEmployee")
        partner.identificationCard = params?.identificationCard
        partner.department = params?.department
        partner.salary = params.double("salary")
        partner.affiliation.fee = params.double("affiliation.fee")
        partner.affiliation.typeOfPayment = params?.affiliation?.typeOfPayment
        partner.affiliation.factoryFee = configurationService.loadFactoryFee()
        partner.affiliation.enrollmentDate = parseDate(params?.affiliation?.enrollmentDate)
        partner.affiliation.capitalization = (params.double("affiliation.capitalization")) ?: 0

        if (!partner.save()) {
            render view:"show", model:[partner:partner, id:id]
            return false
        }

        flash.message = "Registro actualizado correctamente"
        redirect action:"show", params:[id:id]
    }

    @Secured("ROLE_ADMIN")
    def changeStatus(Integer id) {
        def partner = Partner.get(id)

        if (!partner) {
            response.sendError 404
        }

        if (request.method == "POST") {
            partner.status = (partner.status) ? false : true

            if (!partner.save()) {
                return [partner:partner]
            }
        }

        [partner:partner]
    }

    private parseDate(date) {
        return (!date) ? new Date() : new Date().parse("yyyy-MM-dd", date)
    }

}
