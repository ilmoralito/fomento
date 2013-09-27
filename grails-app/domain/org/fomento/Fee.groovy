package org.fomento

class Fee {

    BigDecimal fee
    Date paymentDate

	Date dateCreated
	Date lastUpdated

    static constraints = {
        fee min:0.00, blank:false
        paymentDate blank:false
    }

    static namedQueries = {

    }

    static belongsTo = [partner:Partner]

    static mapping = {
        version false
    }

    String toString() {
        fee
    }

}
