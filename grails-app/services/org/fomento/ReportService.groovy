package org.fomento

class ReportService {

	DividendService dividendService
	FeeService feeService

    def fp(Partner partner, Integer period, String fee, String capital) {
        def result = dividendService.feePeriodData(partner, period)

        BigDecimal ta = (capital == "capitalization") ? result.tas : result.tae
        BigDecimal ap = feeService.totalFeesByPeriod(partner, period, fee)
        BigDecimal se

        if (capital == "capitalization") {
            se = partner?.affiliation?.capitalization
        } else {
            se = partner?.affiliation?.factoryCapital
        }

        def criteria = Affiliation.createCriteria()
        BigDecimal ts = criteria.get {
            projections {
                sum capital
            }
        }

        BigDecimal fp = (ap + se) / (ta + ts)

        print "---------------1"
        println fp
        return fp
    }

    def dd(BigDecimal up, BigDecimal pd, BigDecimal fp) {
        BigDecimal uc = up * pd
        BigDecimal dd = uc * fp

        print "---------------2"
        println fp

        return dd
    }

}
