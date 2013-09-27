package org.fomento
import grails.plugins.springsecurity.Secured
import org.springframework.web.context.request.RequestContextHolder as RCH

class UserController{
	def springSecurityService

	static defaultAction = "actionName"
	static allowedMethods = [
	"profile":["GET", "POST"]]

    def actionName() {

    }

    def create(){
    	[userInstance:new User(params)]
    }

    def save(){
    	def userInstance = new User(params)
    	if (!userInstance.save(flush:true)) {
    		flash.message=message("errores men")
    		render(view:"create", model:[userInstance:userInstance])
    		return false
    	}

    	redirect(action:"list")
    }

    def list(){
    	[userInstance:User.list()]
    }

    def profile(changepasswordCommand cmd){
    	def user = springSecurityService.currentUser
    	session.us = user.password
    	if (request.post) {
    		switch(params.flag) {
    			case 'generaldata':
    				user.properties['username']=params
    				if (!params.fullname.isEmpty()){ user.properties['fullname']=params	}
    				if (user.save(flush:true)) {
    					def varmen=message(code:'org.fomento.mensuccess')
    					render(view:"profile", model:[userInstance:user, activegeneral:"active", activepassword:"", men1:"ok", varmen:varmen])
    				}else{
    					render(view:"profile", model:[userInstance:user, activegeneral:"active", activepassword:"", er1:"ok"])
    				}
    			break
    			case 'passwordchange':
    				if (cmd.hasErrors()) {
                        render(view:"profile", model:[userInstance:session.user, cmd:cmd, activepassword:"active", activegeneral:"", er2:"ok"])
                        return false
                    }

                    def userInstance = cmd.updatePassword()
		            	
                    if (userInstance.save(flush:true)) {
                        session.user = userInstance
                        flash.message= message(code:'ni.com.cookbook.passwordchanged')
                        render(view:"profile", model:[userInstance:userInstance,activepassword:"active", activegeneral:"", men2:"ok"])
                    }else{
                        flash.message="errors"
                    }   
					break  
    		}
    	}else{
    		[userInstance:user,activegeneral:"active"]
    	}
    }

}


class changepasswordCommand {
	
	String currentpassword
    String password
    String confirmpassword

    static constraints = {
    	importFrom User
    	def session = RCH.requestAttributes.session

            currentpassword blank:false, validator:{currentpass, user ->
            	currentpass == session?.us
            }

        confirmpassword blank:false, validator: {confirmpass, user ->
            confirmpass == user.password
        }
    
    } 

    

    User updatePassword() {
        def session = RCH.requestAttributes.session
        def user = User.findByUsername(session?.us?.username)

        if (user) {
            user.properties["password"] = password
        }

        user
    }
}
