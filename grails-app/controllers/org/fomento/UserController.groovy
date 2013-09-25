package org.fomento

class UserController{
	
	def springSecurityService

	static defaultAction = "actionName"
	static allowedMethods = [
	"profile":["GET", "POST"]]

    def actionName() {

    }

    def profile(){
    	def user = springSecurityService.currentUser
    	if (request.post) {
    		switch(params.flag) {
    			case 'generaldata':
    				user.properties['username']=params
    				user.properties['fullname']=params
    				if (user.save(flush:true)) {
    					def varmen=message(code:'org.fomento.mensuccess')
    					render(view:"profile", model:[userInstance:user, activegeneral:"active", activepassword:"", men:"ok", varmen:varmen])
    				}else{
    					render(view:"profile", model:[userInstance:user, activegeneral:"active", activepassword:"", er:"ok"])
    				}
    			break
    			case 'passwordchange':
    				
    			break
    		}
    	}else{
    		[userInstance:user,activegeneral:"active"]
    	}
    }

}
