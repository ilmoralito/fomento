package org.fomento

class DividendService {

	def feeService

	def feePeriodData(partners, Integer period) {
		BigDecimal tas = 0
		BigDecimal tae = 0

		partners.each { partner ->
			def totalPartnerFeesByPeriod = feeService.totalFeesByPeriod(partner, period, "fee")
			def totalFactoryFeesByPeriod = feeService.totalFeesByPeriod(partner, period, "factoryFee")

			tas = tas + totalPartnerFeesByPeriod
			tae = tae + totalFactoryFeesByPeriod
		}

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

    def dd(BigDecimal up, BigDecimal pd, BigDecimal fp) {
    	BigDecimal uc = up * pd
    	BigDecimal dd = uc * fp


    	return up
    }
}
