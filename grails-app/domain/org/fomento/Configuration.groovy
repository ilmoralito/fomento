package org.fomento

class Configuration {

    BigDecimal fee

	Date dateCreated
	Date lastUpdated

    static constraints = {
        fee blank:false, unique:true, min:200.00
    }

    static mapping = {
        version false
    }

    String toString() {
    	fee
    }

}
