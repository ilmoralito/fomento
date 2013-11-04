package org.fomento

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class FeeController {

    def feeService

	static defaultAction = "someAction"
	static allowedMethods = [
		"someAction":"GET"
	]

}
