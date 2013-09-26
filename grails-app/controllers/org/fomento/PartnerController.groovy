package org.fomento
import grails.plugins.springsecurity.Secured

@Secured(["ROLE_ADMIN","ROLE_USER"])
class PartnerController {

	static defaultAction = "list"
	static allowedMethods = [
		"list":"GET",
		"create":["GET","POST"],
        "delete":["GET","POST"],
        "show":"GET"
	]

    def list() {
    	[partners:Partner.list()]
    }

    def create() {
    	if (request.method == "POST") {
            //affiliation
            def ed = (!params?.affiliation?.enrollmentDate) ? new Date() : new Date().parse("yyyy-MM-dd", params?.affiliation?.enrollmentDate)

            def affiliation = new Affiliation(
                fee: params?.affiliation?.fee,
                typeOfPayment: params?.affiliation?.typeOfPayment,
                factoryFee: params?.affiliation?.factoryFee,
                enrollmentDate: ed
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

        partner.properties = params

        if (!partner.save()) {
            render view:"show", model:[partner:partner, id:id]
            return false
        }

        flash.message = "Registro actualizado correctamente"
        redirect action:"show", params:[id:id]
    }

}
