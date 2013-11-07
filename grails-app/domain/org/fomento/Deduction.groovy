package org.fomento

class Deduction implements Serializable {

    BigDecimal totalBeforeDeduction
    BigDecimal totalAfterDeduction
    BigDecimal percentage
    Integer period
    String reason

	Date dateCreated
	Date lastUpdated

    static constraints = {
        totalBeforeDeduction min:0.0
        totalAfterDeduction min:0.0
        percentage min:0.0, max:1.0
        period blank:false, min:2013
        reason nullable:true, maxSize:50000
    }

    static namedQueries = {

    }

    static belongsTo = [dividend:Dividend]

    static mapping = {
        version false
    }

    String toString() {
        period
    }

}
