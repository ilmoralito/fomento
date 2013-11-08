package org.fomento

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class DeductionController {

	static defaultAction = "list"
	static allowedMethods = [
        list:["GET", "POST"]
    ]

    def list(Integer id) {
        if (request.method == "POST") {
            def deduction = new Deduction(params)

            if (!deduction.save()) {
                deduction.errors.allErrors.each {
                    print it
                }
            }
        }

        def dividend = Dividend.get(id)

        [dividend:dividend]
    }
}
