package org.fomento
import grails.plugins.springsecurity.Secured
import org.springframework.web.context.request.RequestContextHolder as RCH

class UserController{
	def springSecurityService

	static defaultAction = "profile"
	static allowedMethods = [
	"profile":["GET", "POST"],
	"delete":["GET", "POST"]]


    def create(){
    	[userInstance:new User(params)]
    }

    def delete(){
    	def userInstance = User.get(params.id)
    	if (!userInstance) {
            response.sendError 404
        }
        if (request.post) {
        	userInstance.delete()
        	flash.message="El usuario ha sido borrado"
        	redirect(action:"list")
        }else{
        	[userInstance:userInstance]
        }
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

    def updateGeneralData(){
    	def user = springSecurityService.currentUser
    	user.properties['username','fullName']=params
    	if (user.save(flush:true)) {
    		def mess=message(code:'org.fomento.mensuccess')
    		render(view:"profile", model:[userInstance:user, activegeneral:"active", activepassword:"", men1:"ok", mess:mess])
    	}else{
    		render(view:"profile", model:[userInstance:user, activegeneral:"active", activepassword:"", er1:"ok"])
    	}
    }

    def updatePassword(changepasswordCommand cmd){
    	def user = springSecurityService.currentUser
    	session.us = user.password
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
    }

    def profile(){
    	def user = springSecurityService.currentUser
    	[userInstance:user,activegeneral:"active"]
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
