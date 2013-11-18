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

}
