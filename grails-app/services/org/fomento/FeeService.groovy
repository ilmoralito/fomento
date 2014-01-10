package org.fomento

import static java.util.Calendar.*
import org.hibernate.transform.AliasToEntityMapResultTransformer

class FeeService implements Serializable {

    def totalFeesByPeriod(Partner partner, Integer period, String property) {
        def criteria = Fee.createCriteria()
        def result = criteria.get {
            eq "partner", partner
            eq "period", period

            projections {
                sum property
            }
        }

        return (result) ?: 0
    }

    def ta(Integer period, String property) {
        def criteria = Fee.createCriteria()
        def ta = criteria.get {
            eq "period", period

            projections {
                sum property
            }
        }

        ta
    }

    def totalFeesByPartner(Partner partner) {
        def criteria = Fee.createCriteria()
        def results = criteria.list {
            eq "partner", partner

            projections {
                groupProperty "period"
                property "period", "period"
                count "fee", "numberFees"
                sum "fee", "totalPartnerFee"
                sum "factoryFee", "totalFactoryFee"
            }

            resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        }

        def crt = Fee.createCriteria()
        def fees = crt.list {
            eq "partner", partner

            projections {
                groupProperty "fee"
                groupProperty "period"
                property "period", "period"
                property "fee", "fee"
                count "fee", "count"
            }

            resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        }

        def dividend = Dividend.createCriteria()
        def dividendResults = dividend.list {
            eq "partner", partner

            projections {
                groupProperty "period"
                property "period", "period"
                property "up", "up"
                property "partnerDividend", "partnerDividend"
                property "factoryDividend", "factoryDividend"
                property "capitalization", "capitalization"
            }

            resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        }

        dividendResults.collect { d ->
            d.partnerDividend = d.partnerDividend - (d.partnerDividend * 0.1)
            d.factoryDividend = d.factoryDividend - (d.factoryDividend * 0.1)
        }

        dividendResults.each { d ->
            def var = (d.capitalization) ? d.capitalization.toString().toInteger() : 0

            d.capital = d.partnerDividend * ( var / 100)
            d.withdraw = d.partnerDividend - d.capital
        }

        [results:results, fees:fees, dividendResults:dividendResults]
    }
}
