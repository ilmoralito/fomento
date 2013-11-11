package org.fomento

class DividendService {

	def feeService

	def feePeriodData(partners, Integer period) {
		BigDecimal tas = feeService.ta(period, "fee")
		BigDecimal tae = feeService.ta(period, "factoryFee")

		BigDecimal tap = tas + tae
		BigDecimal pds = tas / tap
		BigDecimal pde = tae / tap

		[tas:tas, tae:tae, tap:tap, pds:pds, pde:pde]
	}

	def getPeriodUtility(Partner partner, BigDecimal tas, BigDecimal up, Integer period) {
		def aps = feeService.totalFeesByPeriod(partner, period, "fee")
		def ape = feeService.totalFeesByPeriod(partner, period, "factoryFee")

		def tap = aps + ape
		def pds = aps / tap
		def pde = ape / tap

		//partner
		def partnerDD = up * pds
		def partnerDP = partnerDD

		//factory
		def factoryDD = up * pde
		def factoryDP = factoryDD

		[partnerDP:partnerDP, factoryDP:factoryDP]
    }

}
