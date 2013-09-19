package org.fomento

class PartnerController {

	static defaultAction = "list"
	static allowedMethods = [
		"list":"GET"
	]

    def list() {
    	[partners:Partner.list()]
    }

}
