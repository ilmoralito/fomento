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

        if (request.method == "POST") {
            def sPartner = fomento.partnerSaldo(partner:partner, flag:"socio")
            def sPartners = dividendService.feePeriodData(params.int("period"))
            print sPartner
            print sPartners
        }else{
            def data = feeService.totalFeesByPartner(partner)
            [partnerData1:data.partnerData1, partnerData2:data.partnerData2, feesData:data.fees, partner:partner]
        }
    }

    def confirmRenounce(){
        def partner = partnerService.getPartner(params.int("partnerId"))
        print partner
    }

}
