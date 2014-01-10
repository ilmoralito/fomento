package org.fomento

class RenounceController {
	def partnerService
	def feeService

	static defaultAction = "renounce"
	static allowedMethods = [renounce:"GET"]

    def renounce(Integer partnerId) {
    	def partner = partnerService.getPartner(partnerId)

    	if (!partner) {
    		response.sendError 404
    	}

    	//Recuperacion de informacion
    	def data = feeService.totalFeesByPartner(partner)
    	def dividendResults = data.dividendResults

    	print dividendResults

    }

}
