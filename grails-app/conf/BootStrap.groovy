import org.fomento.*
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
    	switch(GrailsUtil.environment) {
    		case "development":
                //roles
                def adminRole = new Role(authority:"ROLE_ADMIN").save()
                def userRole = new Role(authority:"ROLE_USER").save()

                //users
                def testAdminUser = new User(username:"me", enabled:true, password:"password").save()
                def testUserUser = new User(username:"ich", enabled:true, password:"password").save()

                UserRole.create testAdminUser, adminRole, true
                UserRole.create testUserUser, userRole, true

                //test
                assert User.count() == 2
                assert Role.count() == 2
    		break
    	}
    }
    def destroy = {
    }
}
