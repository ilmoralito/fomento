package org.fomento

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'])
class ConfigurationController {

	static defaultAction = "actionName"
	static allowedMethods = []

    def actionName() {

    }

}
