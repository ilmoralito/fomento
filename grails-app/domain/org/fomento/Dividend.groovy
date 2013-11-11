package org.fomento

import org.hibernate.transform.AliasToEntityMapResultTransformer

class Dividend {

    BigDecimal partnerDividend
    BigDecimal factoryDividend

    BigDecimal tas
    BigDecimal tae
    BigDecimal tap
    BigDecimal pds
    BigDecimal pde

    BigDecimal up
    Integer period

	Date dateCreated
	Date lastUpdated

    Deduction deduction

    static constraints = {
        partnerDividend blank:false, min:0.0
        factoryDividend blank:false, min:0.0
        tas blank:false, min:0.0
        tae blank:false, min:0.0
        tap blank:false, min:0.0
        pds blank:false, min:0.0
        pde blank:false, min:0.0
        period blank:false, min:2013
        up blank:false, min:1.0
        deduction nullable:true
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
        period
    }

}
