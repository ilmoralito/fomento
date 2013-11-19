package org.fomento

class Fee implements Serializable {

    BigDecimal fee
    BigDecimal factoryFee
    Integer period

	Date dateCreated
	Date lastUpdated

    static constraints = {
        fee min:0.00, blank:false
        factoryFee min:0.00, blank:false
        period min:2013, blank:false
    }

    static belongsTo = [partner:Partner]

    static mapping = {
        version false
    }

    String toString() {
        fee
    }

}
