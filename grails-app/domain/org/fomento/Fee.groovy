package org.fomento

import static java.util.Calendar.*

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
        byPeriod { int year ->
            def from = new Date()
            from[YEAR] = year
            from[MONTH] = JANUARY
            from[DATE] = 1

            def to = new Date()
            to[YEAR] = year
            to[MONTH] = DECEMBER
            to[DATE] = 31

            ge "paymentDate", from.clearTime()
            le "paymentDate", to.clearTime()
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
