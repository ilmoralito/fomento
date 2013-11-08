package org.fomento

class Deduction implements Serializable {

    BigDecimal noCapitalization
    BigDecimal percentage

	Date dateCreated
	Date lastUpdated

    static constraints = {
        noCapitalization min:0.0
        percentage min:0.0, max:1.0
    }

    static belongsTo = [dividend:Dividend]

    static mapping = {
        version false
    }

    String toString() {
        period
    }

}
