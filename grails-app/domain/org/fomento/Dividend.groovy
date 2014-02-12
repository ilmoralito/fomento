package org.fomento

import org.hibernate.transform.AliasToEntityMapResultTransformer

class Dividend {

    BigDecimal partnerDividend
    BigDecimal factoryDividend

    BigDecimal fps
    BigDecimal fpe

    BigDecimal tas
    BigDecimal tae
    BigDecimal tap
    BigDecimal pds
    BigDecimal pde

    BigDecimal up
    Integer period

    Capitalization capitalization

	Date dateCreated

    static constraints = {
        partnerDividend blank:false, min:0.0, scale:3
        factoryDividend blank:false, min:0.0, scale:3
        fps nullable:true, min:0.0, scale:3
        fpe nullable:true, min:0.0, scale:3
        tas blank:false, min:0.0, scale:3
        tae blank:false, min:0.0, scale:3
        tap blank:false, min:0.0, scale:3
        pds blank:false, min:0.00, scale:3
        pde blank:false, min:0.00, scale:3
        period blank:false, min:2010
        up blank:false
        capitalization nullable:true
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
