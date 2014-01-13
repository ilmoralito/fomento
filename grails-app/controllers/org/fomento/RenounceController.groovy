package org.fomento

class RenounceController {
	def partnerService
	def feeService
    def dividendService

	static defaultAction = "renounce"
	static allowedMethods = [renounce:["GET", "POST"]]

    def renounce(Integer partnerId) {
    	def partner = partnerService.getPartner(partnerId)

    	if (!partner) {
    		response.sendError 404
    	}

    	//Recuperacion de informacion
    	def data = feeService.totalFeesByPartner(partner)
    	def dividendResults = data.dividendResults

    	if (request.method == "POST") {
            def sPartner = fomento.partnerSaldo(partner:partner, flag:"socio")
            def sPartners = dividendService.feePeriodData(params.int("period"))

            print sPartners
        }

    }

}
