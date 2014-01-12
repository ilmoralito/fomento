import org.fomento.*
import grails.util.GrailsUtil
import static java.util.Calendar.*

class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        //roles
        def adminRole = Role.findByAuthority("ROLE_ADMIN") ?: new Role(authority:"ROLE_ADMIN").save()
        def userRole = Role.findByAuthority("ROLE_USER") ?: new Role(authority:"ROLE_USER").save()

    	switch(GrailsUtil.environment) {
    		case "development":
                //configuraion
                def partnersFees = grailsApplication.config.org.fomento.fees
                def factoryFee = grailsApplication.config.org.fomento.fees.min()

                //users
                def admin = User.findByUsername("me@gmail.com") ?: new User(username:"me@gmail.com", fullName:"Julian Perez", password:"123", enabled:true).save()
                def user = User.findByUsername("ich@gmail.com") ?: new User(username:"ich@gmail.com", fullName:"Jose Perez", password:"123", enabled:true).save()

                if (!admin.authorities.contains(adminRole)) {
                    UserRole.create admin, adminRole, true
                }

                if (!user.authorities.contains(userRole)) {
                    UserRole.create user, userRole, true
                }

                //juanPerez
                //affiliation
                def a1 = new Affiliation(
                    fee:partnersFees[0],
                    typeOfPayment:"Catorcena",
                    factoryFee:factoryFee,
                    enrollmentDate:new Date() - 250,
                    capitalization:2500.00,
                    factoryCapital:2500.00
                )

                a1.save()

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
                        print it
                    }
                }

                //johnDoe
                //affiliation
                def a2 = new Affiliation(
                    fee:partnersFees[1],
                    typeOfPayment:"Catorcena",
                    factoryFee:factoryFee,
                    enrollmentDate:new Date() - 300,
                    capitalization:5000.00,
                    factoryCapital:5000.00
                )

                a2.save()

                //partners
                def johnDoe = new Partner(
                    fullName:"John Doe",
                    numberOfEmployee:126,
                    identificationCard:"291-290180-0001P",
                    department:"Administracion",
                    salary:16000,
                    affiliation:a2
                )

                if (!johnDoe.save()) {
                    johnDoe.errors.allErrors.each {
                        print it
                    }
                }

                //fulano
                //affiliation
                def a3 = new Affiliation(
                    fee:partnersFees[2],
                    portion:null,
                    typeOfPayment:"Bono",
                    factoryFee:factoryFee,
                    enrollmentDate:new Date() - 120,
                    capitalization:5800.00,
                    factoryCapital:10000.00
                )

                a3.save()

                //partners
                def fulano = new Partner(
                    fullName:"Fulano",
                    numberOfEmployee:127,
                    identificationCard:"291-290171-0001P",
                    department:"Molino",
                    salary:16000,
                    affiliation:a3
                )

                if (!fulano.save()) {
                    fulano.errors.allErrors.each {
                        print it
                    }
                }

                //Create fees
                def partners = Partner.list()

                partners.each { partner ->
                    for(period in 2011..2012) {
                        for(month in 0..11) {
                            def date = new Date()

                            date[YEAR] = period
                            date[MONTH] = month
                            date[DATE] = 1

                            def fee = new Fee(
                                fee:partner.affiliation.fee,
                                factoryFee:factoryFee,
                                period:period,
                                dateCreated:date,
                                lastUpdated:date,
                                partner:partner
                            ).save()
                        }
                    }
                }
            break
    		case "production":
    			def prodAdmin = User.findByUsername("me@gmail.com") ?: new User(username:"me@gmail.com", enabled:true, password:"123").save()

                if (!prodAdmin.authorities.contains(adminRole)) {
                    UserRole.create prodAdmin, adminRole, true
                }
    		break
    	}
    }
    def destroy = {
    }
}
