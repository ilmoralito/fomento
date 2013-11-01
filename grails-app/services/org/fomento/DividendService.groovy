package org.fomento

class DividendService {

	def feeService

    def getPeriodUtility(def partner, BigDecimal tas, BigDecimal up, Integer period) {
    	//aps Total del aporte personal del Socio en el periodo
		def aps = feeService.partnerTotalCapitalization(partner, period)
		//fps Factor porcentual socio, FPS = TAS/APS
		def fps = tas / aps
		//dd Distribución de  dividendos a pagar al socio, DD = Utilidad del periodo * FPS
		def dd = up * fps
		//dp DD * 0.1
		def dp = dd * 0.1

		[dp:dp]
    }
}
