package org.fomento
import grails.plugins.springsecurity.Secured
import org.springframework.web.context.request.RequestContextHolder as RCH

@Secured(['ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'])
class UserController{
	def springSecurityService

	static defaultAction = "list"
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

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
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

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def updatePassword(changepasswordCommand cmd){
    	def user = springSecurityService.currentUser
        def pass = springSecurityService.encodePassword(params.currentpassword)
        session.user = user
        
        if (pass!=user.password) {
            def mess = message(code:'org.fomento.errorCurrentPassword')
            render(view:"profile",
            model:[userInstance:session.user, activepassword:"active", activegeneral:"", er2:"ok", mess:mess])
            return false
        }else{
            if (cmd.hasErrors()) {
                render(view:"profile", model:[userInstance:session.user, cmd:cmd, activepassword:"active", activegeneral:"", er2:"ok"])
                return false
            }

            def userInstance = cmd.updatePassword()
                            
            if (userInstance.save(flush:true)) {
                session.user = userInstance
                def mess= message(code:'ni.com.cookbook.passwordchanged')
                render(view:"profile", model:[userInstance:userInstance,activepassword:"active", activegeneral:"", men2:"ok", mess:mess])
            }else{
                flash.message="Errores"
            }  
        }
    	 
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def profile(){
    	def user = springSecurityService.currentUser
    	[userInstance:user,activegeneral:"active"]
    }

    def edit(){
        def userRole
        def userInstance = User.get(params.id)
        def role = UserRole.get(userInstance.id, 1)
        if (!role) {
             role = UserRole.get(userInstance.id, 2)
             if (!role) {
                userRole = "NO_ROLE"
             }else{
                userRole = "ROLE_USER"
             }
         }else{
                userRole = "ROLE_ADMIN" 
         }
        [userInstance:userInstance, userRole:userRole]
    }

    def update(){
        def userInstance = User.get(params.id)
        if (!params.password.isEmpty()) {
            userInstance.properties['password']=params
        }
        userInstance.properties['username','fullName']= params
        if (userInstance.save(flush:true)) {
            def mess=message(code:'org.fomento.mensuccess')
           render(view: "edit", model:[userInstance:userInstance, men:"ok", mess:mess, userRole:params.userRole])
        }else{
           render(view: "edit", model:[userInstance:userInstance, er:"ok", userRole:params.userRole])
        }
    }

    def enabledaccount(){
        def mess
        def userInstance = User.get(params.id)
        if (params.enabled=='on') {
            userInstance.properties['enabled']=true
            mess=message(code:'org.fomento.menEnableAccount')
            render(view: "edit", model:[userInstance:userInstance, men:"ok", mess:mess, userRole: params.userRole])
        }else if (params.disable=='on') {
            mess=message(code:'org.fomento.menDisableAccount')
            userInstance.properties['enabled']=false
            render(view: "edit", model:[userInstance:userInstance, men:"ok", mess:mess, userRole: params.userRole])
        }else{
            render(view: "edit", model:[userInstance:userInstance, userRole: params.userRole])
        }
    }

    def assignrole(){ 
       def userInstance = User.get(params.id)
       def role, newRole, mess
       if (params.role=="admin") {
           role = Role.get(1)
           newRole = UserRole.create(userInstance, role, true)
           mess = message(code:'org.fomento.assignedAdminRole')
           render(view:"edit", model:[userInstance:userInstance, men:"ok", mess:mess, userRole:"ROLE_ADMIN"])
       }else{
           role = Role.get(2)
           newRole = UserRole.create(userInstance, role, true)
           mess = message(code:'org.fomento.assignedUserRole')
           render(view:"edit", model:[userInstance:userInstance, men:"ok", mess:mess, userRole:"ROLE_USER"])
        }
       
    }

    def changerole(){
        def userInstance = User.get(params.id)
        def role, role2, mess
       if (params.roleadmin=='on') {
            role = Role.get(2)
            role2 = Role.get(1)
            def removeUser = UserRole.findByUserAndRole(userInstance, role)
            if (!removeUser) {
                render(view: "edit", model:[userInstance:userInstance, userRole: params.userRole]) 
            }else{
                removeUser.delete(flush:true)
                def newRole = UserRole.create(userInstance, role2, true)
                mess=message(code:'org.fomento.menrolchange')
                render(view: "edit", model:[userInstance:userInstance, men:"ok", mess:mess, userRole: "ROLE_ADMIN"])
            }
       }else if(params.roleuser=="on"){
            role = Role.get(1)
            role2 = Role.get(2)
            def removeUser = UserRole.findByUserAndRole(userInstance, role)  
            if (!removeUser) {
                render(view: "edit", model:[userInstance:userInstance, userRole: params.userRole]) 
            }else{
                removeUser.delete(flush:true) 
                def newRole = UserRole.create(userInstance, role2, true)
                mess=message(code:'org.fomento.menrolchange')
                render(view: "edit", model:[userInstance:userInstance, men:"ok", mess:mess, userRole: "ROLE_USER"])  
            }
       }else{
            render(view: "edit", model:[userInstance:userInstance, userRole: params.userRole]) 
       }
    }

}


class changepasswordCommand {
	
	String password
    String confirmpassword

    static constraints = {
    	importFrom User
    	
        confirmpassword blank:false, validator: {confirmpass, user ->
            confirmpass == user.password
        }
    
    } 

    User updatePassword() {
        def session = RCH.requestAttributes.session
        def user = User.findByUsername(session?.user?.username)

        if (user) {
            user.properties["password"] = password
        }

        user
    }
}
