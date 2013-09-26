 import org.fomento.*
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
        //roles
        def adminRole = Role.findByAuthority("ROLE_ADMIN") ?: new Role(authority:"ROLE_ADMIN").save()
        def userRole = Role.findByAuthority("ROLE_USER") ?: new Role(authority:"ROLE_USER").save()

        //configuration setting or getting fee
        if (!Configuration.list()) {
            new Configuration(fee:205.21).save()
            new Configuration(fee:300).save()
            new Configuration(fee:400).save()
        }

    	switch(GrailsUtil.environment) {
    		case "development":
                //users
                def admin = User.findByUsername("me") ?: new User(username:"me", password:"123", enabled:true).save()
                def user = User.findByUsername("ich") ?: new User(username:"ich", password:"123", enabled:true).save()

                if (!admin.authorities.contains(adminRole)) {
                    UserRole.create admin, adminRole, true
                }

                if (!user.authorities.contains(userRole)) {
                    UserRole.create user, userRole, true
                }

                //affiliation
                def a1 = new Affiliation(
                    fee:450,
                    typeOfPayment:"Catorcena",
                    factoryFee:205,
                    enrollmentDate:new Date() - 250
                )

                if (a1.validate()) {
                    a1.errors.allErrors.each {
                        print it.defaultMessage
                    }
                }

                //partners
                def juanPerez = new Partner(
                    fullName:"Juan Perez",
                    numberOfEmployee:125,
                    identificationCard:"291-290180-0001W",
                    department:"Molino",
                    salary:15000,
                    affiliation:a1
                )

                if (!juanPerez.save()) {
                    juanPerez.errors.allErrors.each {
                        print it.defaultMessage
                    }
                }

    		break
    		case "production":
    			def prodAdmin = User.findByUsername("me") ?: new User(username:"me", enabled:true, password:"123").save()

                if (!prodAdmin.authorities.contains(adminRole)) {
                    UserRole.create prodAdmin, adminRole, true
                }
    		break
    	}
    }
    def destroy = {
    }
}
