package org.fomento

class DividendService {

	def feeService

	def feePeriodData(Integer period) {
		BigDecimal tas = feeService.ta(period, "fee")
		BigDecimal tae = feeService.ta(period, "factoryFee")

		BigDecimal tap = tas + tae

		if (tap==0) {
			[tas:0, tae:0, tap:0, pds:0, pde:0]
		}else{
			BigDecimal pds = tas / tap
			BigDecimal pde = tae / tap
			[tas:tas, tae:tae, tap:tap, pds:pds, pde:pde]
		}
	}

	def total(Partner partner) {
		def dividends = Dividend.findAllByPartner(partner)
		def total = 0

		dividends.each { dividend ->
			if (dividend?.capitalization?.percentage) {
				def percentage = dividend.capitalization.percentage / 100
				def periodDividend = dividend.partnerDividend * percentage

				total = total + periodDividend
			} else {
				total = total + dividend.partnerDividend
			}
		}

		total
	}

	def searchRenouncePeriod(Integer period){
		def criteria = History.createCriteria()
            def totalRenounceByPeriod = criteria.get{
                eq("period", period)
                projections {
                    sum ('partnerDivNeto')
                }
            }
            return (totalRenounceByPeriod) ?: 0
	}

}
