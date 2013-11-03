package org.fomento

import org.hibernate.transform.AliasToEntityMapResultTransformer

class Dividend {

    BigDecimal dividend
    Integer period
    BigDecimal periodUP
    BigDecimal periodTAS

	Date dateCreated
	Date lastUpdated

    static constraints = {
        dividend blank:false, min:0.0
        period blank:false, min:2013
    }

    static namedQueries = {
        byPeriod {
            projections {
                 groupProperty "period", "period"
            }

            resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        }
    }

    static belongsTo = [partner:Partner]

    static mapping = {
        version false
    }

    String toString() {
        "$period: $dividend"
    }

}
