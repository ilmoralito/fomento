package org.fomento

class FeeController {

    def feeService

	static defaultAction = "list"
	static allowedMethods = [
		"list":"GET"
	]

    def list() {
    	def partner = Partner.get(params?.id)

    	if (!partner) {
    		response.sendError 404
    	}

    	[fees:Fee.findAllByPartner(partner), total:feeService.calcTotal(partner.fees)]
    }

}
