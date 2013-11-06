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
		def partnerAPS = feeService.totalFeesByPeriod(partner, period, "fee")
		def factoryAPS = feeService.totalFeesByPeriod(partner, period, "factoryFee")

		//partner
		def partnerFPS = partnerAPS / tas
		def partnerDD = up * partnerFPS
		def partnerDP = partnerDD - (partnerDD * 0.1)

		//factory
		def factoryFPS = factoryAPS / tas
		def factoryDD = up * factoryFPS
		def factoryDP = factoryDD - (factoryDD * 0.1)

		[partnerDP:partnerDP, factoryDP:factoryDP]
    }
}
