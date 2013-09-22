package org.fomento
import grails.plugins.springsecurity.Secured

@Secured(["ROLE_ADMIN","ROLE_USER"])
class PartnerController {

	static defaultAction = "list"
	static allowedMethods = [
		"list":"GET",
		"create":["GET","POST"],
        "delete":["GET","POST"]
	]

    def list() {
    	[partners:Partner.list()]
    }

    def create() {
    	if (request.method == "POST") {
    		def partner = new Partner(params)

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

}
