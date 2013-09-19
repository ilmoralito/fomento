package org.fomento
import grails.plugins.springsecurity.Secured

class PartnerController {

	static defaultAction = "list"
	static allowedMethods = [
		"list":"GET"
	]

	@Secured(['ROLE_ADMIN'])
    def list() {
    	[partners:Partner.list()]
    }

}
