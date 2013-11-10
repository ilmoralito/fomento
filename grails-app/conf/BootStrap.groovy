import org.fomento.*
import grails.util.GrailsUtil

class BootStrap {

    def configurationService

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
                    fee:400,
                    typeOfPayment:"Catorcena",
                    factoryFee: configurationService.loadFactoryFee(),
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

                //fees
                def fee1 = new Fee(
                    fee:juanPerez.affiliation.fee,
                    factoryFee:configurationService.loadFactoryFee(),
                    total:juanPerez.affiliation.fee + configurationService.loadFactoryFee(),
                    paymentDate:new Date() - 62,
                    period:2013
                )

                def fee2 = new Fee(
                    fee:juanPerez.affiliation.fee,
                    factoryFee:configurationService.loadFactoryFee(),
                    total:juanPerez.affiliation.fee + configurationService.loadFactoryFee(),
                    paymentDate:new Date() - 30,
                    period:2013
                )

                juanPerez.addToFees(fee1)
                juanPerez.addToFees(fee2)

                //johnDoe
                //affiliation
                def a2 = new Affiliation(
                    fee:300,
                    typeOfPayment:"Catorcena",
                    factoryFee: configurationService.loadFactoryFee(),
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

                //fees
                def fee3 = new Fee(
                    fee:johnDoe.affiliation.fee,
                    factoryFee:configurationService.loadFactoryFee(),
                    total:johnDoe.affiliation.fee + configurationService.loadFactoryFee(),
                    paymentDate:new Date() - 62,
                    period:2013
                )

                def fee4 = new Fee(
                    fee:johnDoe.affiliation.fee,
                    factoryFee:configurationService.loadFactoryFee(),
                    total:johnDoe.affiliation.fee + configurationService.loadFactoryFee(),
                    paymentDate:new Date() - 30,
                    period:2013
                )

                johnDoe.addToFees(fee3)
                johnDoe.addToFees(fee4)

                //fulano
                //affiliation
                def a3 = new Affiliation(
                    fee:300,
                    typeOfPayment:"Bono",
                    factoryFee: configurationService.loadFactoryFee(),
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

                //fees
                def fee5 = new Fee(
                    fee:fulano.affiliation.fee,
                    factoryFee:configurationService.loadFactoryFee(),
                    total:fulano.affiliation.fee + configurationService.loadFactoryFee(),
                    paymentDate:new Date() - 62,
                    period:2013
                )

                def fee6 = new Fee(
                    fee:fulano.affiliation.fee,
                    factoryFee:configurationService.loadFactoryFee(),
                    total:fulano.affiliation.fee + configurationService.loadFactoryFee(),
                    paymentDate:new Date() - 30,
                    period:2013
                )

                def fee7 = new Fee(
                    fee:fulano.affiliation.fee,
                    factoryFee:configurationService.loadFactoryFee(),
                    total:fulano.affiliation.fee + configurationService.loadFactoryFee(),
                    paymentDate:new Date() - 10,
                    period:2013
                )

                fulano.addToFees(fee5)
                fulano.addToFees(fee6)
                fulano.addToFees(fee7)
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
