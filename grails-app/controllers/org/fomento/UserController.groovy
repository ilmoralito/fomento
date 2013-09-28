package org.fomento
import org.springframework.web.context.request.RequestContextHolder as RCH

class UserController{
	def springSecurityService

	static defaultAction = "actionName"
	static allowedMethods = [
	"profile":["GET", "POST"]]

    def actionName() {

    }

    def profile(changepasswordCommand cmd){
    	def user = springSecurityService.currentUser
    	session.us = user
    	
    	if (request.post) {
    		switch(params.flag) {
    			case 'generaldata':
    				user.properties['username']=params
    				if (!params.fullname.isEmpty()){ user.properties['fullname']=params	}
    				if (user.save(flush:true)) {
    					def varmen=message(code:'org.fomento.mensuccess')
    					render(view:"profile", model:[userInstance:user, activegeneral:"active", activepassword:"", men:"ok", varmen:varmen])
    				}else{
    					render(view:"profile", model:[userInstance:user, activegeneral:"active", activepassword:"", er:"ok"])
    				}
    			break
    			case 'passwordchange':
    				if (cmd.hasErrors()) {
                        render(view:"profile", model:[userInstance:session.user, cmd:cmd, activepassword:"active", activegeneral:"", er:"ok"])
                        return false
                    }

                    def userInstance = cmd.updatePassword()
		            	
                    if (userInstance.save(flush:true)) {
                        session.user = userInstance
                        flash.message= message(code:'ni.com.cookbook.passwordchanged')
                        render(view:"profile", model:[userInstance:userInstance,activepassword:"active", activegeneral:""])
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
            currentpassword blank:false, validator:{currentpass ->
            	currentpass== session?.us?.password
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
