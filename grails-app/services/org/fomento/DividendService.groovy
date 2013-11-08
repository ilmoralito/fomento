package org.fomento

class DividendService {

	def feeService

	def calcTAS(def partners, Integer period) {
		def partnerTAS = 0
		def factoryTAS = 0

		partners.each { partner ->
			def totalPartnerFeesByPeriod = feeService.totalFeesByPeriod(partner, period, "fee")
			def totalFactoryFeesByPartnerInPeriod = feeService.totalFeesByPeriod(partner, period, "factoryFee")

			partnerTAS = partnerTAS + totalPartnerFeesByPeriod
			factoryTAS = factoryTAS + totalFactoryFeesByPartnerInPeriod
		}

		[partnerTAS:partnerTAS, factoryTAS:factoryTAS]
	}

    def getPeriodUtility(Partner partner, BigDecimal tas, BigDecimal up, Integer period) {
		def aps = feeService.totalFeesByPeriod(partner, period, "fee")
		def ape = feeService.totalFeesByPeriod(partner, period, "factoryFee")

		def tap = aps + ape
		def pds = aps / tap
		def pde = ape / tap

		//partner
		//def partnerFPS = aps / tas
		def partnerDD = up * pds
		def partnerDP = partnerDD

		//factory
		//def factoryFPS = ape / tas
		def factoryDD = up * pde
		def factoryDP = factoryDD

		[partnerDP:partnerDP, factoryDP:factoryDP]
    }
}
