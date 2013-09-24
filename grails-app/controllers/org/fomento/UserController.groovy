package org.fomento

class UserController{
	
	def springSecurityService

	static defaultAction = "actionName"
	static allowedMethods = [
	"profile":["GET", "POST"]]

    def actionName() {

    }

    def profile(){
    	if (request.POST) {
    		
    	}else{
    		def user = springSecurityService.currentUser
    		[userInstance:user,activepersonal:"active"]
    	}
    }

}
