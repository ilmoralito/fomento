package org.fomento
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class PartnerController {

	static defaultAction = "list"
	static allowedMethods = [
		"list":"GET",
		"create":["GET","POST"]
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

}
