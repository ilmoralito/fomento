package org.fomento

import static java.util.Calendar.*

class FeeService implements Serializable {

    //total fees by partner fee and by factory fee
    def totalFeesByPeriod(Partner partner, Integer period, String property) {
        def criteria = Fee.createCriteria()
        def result = criteria.get {
            eq "partner", partner
            eq "period", period

            projections {
                sum property
            }
        }

        result
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
