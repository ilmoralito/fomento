package org.fomento

class Fee implements Serializable {

    BigDecimal fee
    BigDecimal factoryFee
    BigDecimal total
    Date paymentDate

	Date dateCreated
	Date lastUpdated

    static constraints = {
        fee min:0.00, blank:false
        paymentDate blank:false
    }

    static namedQueries = {
        byYear { int year ->
            def now = new Date().parse("yyyy", year)

            eq "paymentDate", now
        }
    }

    static belongsTo = [partner:Partner]

    static mapping = {
        version false
    }

    String toString() {
        fee
    }

}
