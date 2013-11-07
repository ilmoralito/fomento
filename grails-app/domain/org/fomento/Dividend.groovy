package org.fomento

import org.hibernate.transform.AliasToEntityMapResultTransformer

class Dividend {

    BigDecimal partnerDividend
    BigDecimal factoryDividend

    BigDecimal partnerTAS
    BigDecimal factoryTAS
    BigDecimal tap
    BigDecimal fps
    BigDecimal fpe

    BigDecimal periodUP
    Integer period

	Date dateCreated
	Date lastUpdated

    static constraints = {
        partnerDividend blank:false, min:0.0
        factoryDividend blank:false, min:0.0
        partnerTAS blank:false, min:0.0
        factoryTAS blank:false, min:0.0
        tap blank:false, min:0.0
        fps blank:false, min:0.0
        fpe blank:false, min:0.0
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
    static hasMany = [deductions:Deduction]

    static mapping = {
        version false
        deductions sort: 'dateCreated', order: 'desc'
    }

    String toString() {
        period
    }

}
