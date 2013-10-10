package org.fomento

class FeeController {

    def feeService

	static defaultAction = "list"
	static allowedMethods = [
		"list":"GET"
	]

    def list(Integer id, String year) {
    	def partner = Partner.get(params?.id)
        def y = (params.int("year")) ?: 2013

    	if (!partner) {
    		response.sendError 404
    	}

        def fees = Fee.byPeriod(y).findAllByPartner(partner,[sort:"paymentDate", order:"desc"])

    	[
            fees:fees,
            partner:partner,
            total:feeService.calcTotal(fees, partner),
            totalFactoryFee:feeService.calcFactoryTotalFeesByPartner(fees)
        ]
    }

}
