package org.fomento

class Deduction implements Serializable {

    def feeService

    BigDecimal deduction
    String reason

	Date dateCreated
	Date lastUpdated

    static constraints = {
        deduction min:0.0
        reason nullable:true, maxSize:50000
    }

    static namedQueries = {

    }

    static belongsTo = [partner:Partner]

    static mapping = {
        version false
    }

    String toString() {
        deduction
    }

}
