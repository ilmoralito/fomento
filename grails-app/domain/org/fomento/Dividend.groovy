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
    BigDecimal ir
    BigDecimal backup
    Integer period

    Capitalization capitalization

	Date dateCreated
    Date lastUpdated

    static constraints = {
        partnerDividend blank:false, min:0.0, scale:12
        factoryDividend blank:false, min:0.0, scale:12
        fps nullable:true, min:0.0, scale:12
        fpe nullable:true, min:0.0, scale:12
        tas blank:false, min:0.0, scale:12
        tae blank:false, min:0.0, scale:12
        tap blank:false, min:0.0, scale:12
        pds blank:false, min:0.00, scale:12
        pde blank:false, min:0.00, scale:12
        period blank:false, min:2010
        up blank:false
        ir blank:false
        backup blank:false
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
