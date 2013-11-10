package org.fomento

class ReportService {

	DividendService dividendService
	FeeService feeService

    def fps(Partner partner, Integer period) {
    	def result = dividendService.feePeriodData(partner, period)

    	BigDecimal tap = result.tap
    	BigDecimal aps = feeService.totalFeesByPeriod(partner, period, "fee")
    	BigDecimal ss = partner?.affiliation?.capitalization

    	def criteria = Affiliation.createCriteria()
        def tss = criteria.get {
            projections {
                sum "capitalization"
            }
        }

		BigDecimal fps = (aps + ss) / (tap + tss)

		return fps
    }

    def fpe(Partner partner, Integer period) {
    	def result = dividendService.feePeriodData(partner, period)

    	BigDecimal tap = result.tap
    	BigDecimal ape = feeService.totalFeesByPeriod(partner, period, "factoryFee")
    	BigDecimal se = partner?.affiliation?.factoryCapital

    	def criteria = Affiliation.createCriteria()
        def tse = criteria.get {
            projections {
                sum "factoryCapital"
            }
        }

		BigDecimal fpe = (ape + se) / (tap + tse)

		return fpe
    }

    def dd(BigDecimal up, BigDecimal pd, BigDecimal fp) {
        BigDecimal uc = up * pd
        BigDecimal dd = uc * fp

        return dd
    }

}
