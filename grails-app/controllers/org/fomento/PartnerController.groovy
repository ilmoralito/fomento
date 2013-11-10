package org.fomento

import grails.plugins.springsecurity.Secured
import org.compass.core.engine.SearchEngineQueryParseException
import static java.util.Calendar.*

@Secured(["ROLE_ADMIN","ROLE_USER"])
class PartnerController {

    def configurationService
    def searchableService
    def feeService

	static defaultAction = "list"
	static allowedMethods = [
		"list":["GET","POST"],
		"create":["GET","POST"],
        "delete":["GET","POST"],
        "show":"GET",
        "update":"POST",
        "changeStatus":["GET","POST"],
        "partnerToApplyFees":["GET","POST"]
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
                def status = params.boolean("status")

                return [partners:Partner.byStatus(status).list()]
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
                fee: params.double("affiliation.fee"),
                typeOfPayment: params?.affiliation?.typeOfPayment,
                factoryFee: configurationService.loadFactoryFee(),
                enrollmentDate: ed,
                capitalization: params?.affiliation?.capitalization,
                factoryCapital:params?.affiliation?.factoryCapital
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
        partner.affiliation.capitalization = params.double("affiliation.capitalization")
        partner.affiliation.factoryCapital = params.double("affiliation.factoryCapital")

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

    def partnerToApplyFees(String typeOfPayment) {
        if (request.method == "POST") {
            def partners = params.list("partners")

            if (partners) {
                //check if new fees date are not in partner fees paymentDate
                //TODO:check later if thsi is posible to do inside Partner domain class in beforeInsert event!
                def today = new Date()
                def year = today[YEAR]
                def month = today[MONTH]

                partners.each { n ->
                    def partner = Partner.findByNumberOfEmployee(n)

                    //check if current partner do not have a fee in this month and year
                    //if this condition is false then apply new fee else jump to the next partner in list
                    def flag = false

                    partner.fees.each {
                        if (it.paymentDate[YEAR] == year && it.paymentDate[MONTH] == month) {
                            flag = true
                        }
                    }

                    if (!flag) {
                        partner.addToFees(
                            fee:partner.affiliation.fee,
                            factoryFee:configurationService.loadFactoryFee(),
                            total:partner.affiliation.fee + configurationService.loadFactoryFee(),
                            paymentDate:new Date()
                        )

                        partner.save()
                    }
                }
            } else {
                flash.message = "Debes seleccionar socios para poder continuar"
            }
        }

        [partners:Partner.byTypeOfPayment(typeOfPayment).byStatus(true).list(params)]
    }

    def report(Integer id, Integer period) {
        def partner = Partner.get(id)

        if (!partner) {
            redirect action:"list"
            return false
        }

        //get fees
        def fees = Fee.findAllByPartnerAndPeriod(partner, period)

        //dividend
        def dividend = Dividend.findByPartnerAndPeriod(partner, period)

        [
            partner:partner,
            fees:fees,
            totalPartnerFee:feeService.totalFeesByPeriod(partner, period, "fee"),
            totalFactoryFee:feeService.totalFeesByPeriod(partner, period, "factoryFee"),
            tas:dividend?.partnerDividend,
            tae:dividend?.factoryDividend
        ]
    }

    private parseDate(date) {
        return (!date) ? new Date() : new Date().parse("yyyy-MM-dd", date)
    }

}
