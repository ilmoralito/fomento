package org.fomento
import grails.plugins.springsecurity.Secured
import org.springframework.web.context.request.RequestContextHolder as RCH

@Secured(['ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'])
class UserController{
	def springSecurityService

	static defaultAction = "list"
	static allowedMethods = [
       list:"GET",
       create:"GET",
       save:"POST",
	   profile:["GET", "POST"],
	   delete:["GET", "POST"],
       edit:"GET",
       update:"POST"
    ]

    def list(){
        [userInstance:User.list()]
    }

    def create(){
    	[userInstance:new User(params)]
    }

    def save(){
        def userInstance = new User(username:params?.username, fullName:params?.fullName, password:params?.password)

        if (!userInstance.save(flush:true)) {
            render(view:"create", model:[userInstance:userInstance])
            return false
        }

        def role = Role.findByAuthority(params?.authority)
        UserRole.create userInstance, role, true

        flash.message = "Usuario $userInstance.fullName creado correctamente"
        redirect(action:"list")
    }

    def edit(Integer id){
        def userInstance = User.get(id)

        if (!userInstance) {
            response.sendError 404
        }

        [userInstance:userInstance]
    }

    def update(Integer id){
        def userInstance = User.get(id)

        if (!userInstance) {
            response.sendError 404
        }

        if (params?.password) {
            userInstance.properties["username", "fullName", "password"] = params
        } else {
            userInstance.properties["username", "fullName"] = params
        }

        if (userInstance.save()) {
            UserRole.removeAll userInstance
            def role = Role.findByAuthority((params?.authority) ?: 'ROLE_USER')
            UserRole.create userInstance, role, true
        }

        redirect action:"edit", params:[id:id]
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Integer id){
        def userInstance = User.get(id)

        if (!userInstance) {
            response.sendError 404
        }

        if (request.post) {
            UserRole.removeAll userInstance
            userInstance.delete()

            flash.message="Usuario borrado"
            redirect(action:"list")
            return
        }

        [userInstance:userInstance]
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

    def enabledaccount(){
        def mess
        if (!params.id) {
           redirect action:"list"
        } else {
            def userInstance = User.get(params.id)

            if (params.enabled == 'on') {
                userInstance.properties['enabled'] = true
                mess = message(code:'org.fomento.menEnableAccount')
                render(view: "edit", model:[userInstance:userInstance, men:"ok", mess:mess, userRole: params.userRole])
            } else if (params.disable == 'on') {
                mess = message(code:'org.fomento.menDisableAccount')
                userInstance.properties['enabled'] = false
                render(view: "edit", model:[userInstance:userInstance, men:"ok", mess:mess, userRole: params.userRole])
            } else{
                render(view: "edit", model:[userInstance:userInstance, userRole: params.userRole])
            }
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
