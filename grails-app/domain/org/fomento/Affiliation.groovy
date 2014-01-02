package org.fomento

class Affiliation implements Serializable {

    BigDecimal fee
    BigDecimal portion
    String typeOfPayment
    BigDecimal factoryFee
    Date enrollmentDate
    BigDecimal capitalization = 0.0
    BigDecimal factoryCapital

	Date dateCreated
	Date lastUpdated

    static constraints = {
        fee blank:false, min:1.0
        portion nullable:true, min:1.0, validator: { val, obj -> val < obj.fee }
        typeOfPayment blank:false, inList: ["Catorcena", "Fin de mes", "Bono"], maxSize:100
        factoryFee blank:false, min:200.0
        enrollmentDate blank:false, validator: { enrollmentDate ->
            def today = new Date()

            enrollmentDate <= today
        }
        capitalization blank:false, min:0.0
        factoryCapital blank:false, min:0.0
    }

    static belongsTo = [partner:Partner]

    static mapping = {
        version false
    }

    String toString() { fee }

}
