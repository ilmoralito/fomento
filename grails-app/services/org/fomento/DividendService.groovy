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

}
