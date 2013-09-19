import org.fomento.*
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
        //roles
        def adminRole = (Role.findByAuthority("ROLE_ADMIN")) ?: new Role(authority:"ROLE_ADMIN").save()
        def userRole = (Role.findByAuthority("ROLE_USER")) ?: new Role(authority:"ROLE_USER").save()

    	switch(GrailsUtil.environment) {
    		case "development":
                //users
                def testAdminUser = new User(username:"me", enabled:true, password:"password").save()
                def testUserUser = new User(username:"ich", enabled:true, password:"password").save()

                UserRole.create testAdminUser, adminRole, true
                UserRole.create testUserUser, userRole, true

                //test
                assert User.count() == 2
                assert Role.count() == 2
    		break
    		case "production":
    			def prodAdminUser = (User.findByUsername("me")) ?: new User(username:"me", enabled:true, password:"123").save()

    			UserRole.create prodAdminUser, adminRole, true
    		break
    	}
    }
    def destroy = {
    }
}
