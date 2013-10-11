package org.fomento

import static java.util.Calendar.*

class FeeController {

    def feeService

	static defaultAction = "list"
	static allowedMethods = [
		"list":"GET"
	]

    def list(Integer id, String year) {
    	def partner = Partner.get(params?.id)
        def now = new Date()
        def currentYear = now[YEAR]
        def period = (params.int("year")) ?: currentYear

    	if (!partner) {
    		response.sendError 404
    	}

        def fees = Fee.byPeriod(period).findAllByPartner(partner,[sort:"paymentDate", order:"desc"])

    	[
            fees:fees,
            period:period,
            partner:partner,
            total:feeService.calcTotal(fees, partner),
            totalFactoryFee:feeService.calcFactoryTotalFeesByPartner(fees)
        ]
    }

}
