package org.fomento

class Dividend {

    BigDecimal dividend
    Integer period

	Date dateCreated
	Date lastUpdated

    static constraints = {
        dividend blank:false, min:0.0
        period blank:false, min:2013
    }

    static namedQueries = {

    }

    static belongsTo = [partner:Partner]

    static mapping = {
        version false
    }

    String toString() {
        "$period: $dividend"
    }

}
