package org.fomento

import static java.util.Calendar.*

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
}
