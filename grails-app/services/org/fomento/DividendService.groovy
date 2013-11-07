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

		def tap = partnerAPS + factoryAPS
		def fps = (partnerAPS / tap) * 100
		def fpe = (factoryAPS / tap) * 100

		//partner
		//def partnerFPS = partnerAPS / tas
		def partnerDD = (up * fps) / 100
		def partnerDP = partnerDD

		//factory
		//def factoryFPS = factoryAPS / tas
		def factoryDD = (up * fpe) / 100
		def factoryDP = factoryDD

		[partnerDP:partnerDP, factoryDP:factoryDP]
    }
}
