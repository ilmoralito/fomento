package org.fomento

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class DeductionController {

	static defaultAction = "list"
	static allowedMethods = [
        list:["GET", "POST"]
    ]

    def list(Partner partner, Integer period) {
        if (request.method == "POST") {

        }

        BigDecimal total = 0

        /*
        if (deductions) {
            //get recent totalAfterDeduction value
        } else {
            //get DP for partner in thsi period
            total = 1560
        }
        */

        [partner:partner, period:period, total:total]
    }
}
