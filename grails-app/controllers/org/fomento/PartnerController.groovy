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
    		
    	} else {
    		[partner:new Partner(params)]
    	}
    }

}
