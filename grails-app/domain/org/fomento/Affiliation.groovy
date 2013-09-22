package org.fomento

class Affiliation {

    BigDecimal fee
    String typeOfPayment
    BigDecimal factoryFee
    Date enrollmentDate

	Date dateCreated
	Date lastUpdated

    static constraints = {
        fee blank:false, min:1.00
        typeOfPayment blank:false, inList:["Catorcena", "Fin de mes", "Bono"], maxSize:255
        factoryFee blank:false, min:200.00
        enrollmentDate blank:false, validator: {enrollmentDate ->
            def today = new Date()

            enrollmentDate <= today
        }
    }

    static namedQueries = {

    }

    static belongsTo = [partner:Partner]

    static mapping = {
        version:false
    }

    String toString() {
        fee
    }

}
