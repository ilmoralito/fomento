package org.fomento

class ReportController {

	def feeService

	static defaultAction = "dividends"
	static allowedMethods = [
		dividends:["GET", "POST"],
		applyDividends:"POST"
	]

    def dividends(DividendsCommand cmd) {
    	if (request.method == "POST") {
    		if (cmd.hasErrors()) {
    			return [cmd:cmd]
    		}

    		def tas = 0
	    	def partners = Partner.findAllByStatus(true)

	    	partners.each { partner ->
	    		tas = tas + feeService.partnerTotalCapitalization(partner, cmd.period)
	    	}

	    	return [partners:partners, tas:tas, up:cmd.up, period:cmd.period]
    	}
    }

    def applyDividends(ApplyDividendsCommand cmd) {
    	//get all partners with status set to true
    	if (cmd.hasErrors()) {
    		return [cmd:cmd]
    	}

    	def partners = Partner.findAllByStatus(true)
    	def dividendsCount =  Dividend.countByPeriod(cmd.period)

		if (!dividendsCount) {
			partners.each { partner ->
				//aps Total del aporte personal del Socio en el periodo
				def aps = feeService.partnerTotalCapitalization(partner, cmd.period)
				//fps Factor porcentual socio, FPS = TAS/APS
				def fps = cmd.tas/aps
				//dd Distribución de  dividendos a pagar al socio, DD = Utilidad del periodo * FPS
				def dd = cmd.up * fps
				//dp DD * 0.1
				def dp = dd * 0.1

				//add dividend to each partner in period
				partner.addToDividends(new Dividend(dividend:dp, period:cmd.period))
			}
		} else {
			//ask user if want to procede
			print "no"
		}
    	//add to each partner in partners Dividens to Pay (DP)
    	/*
    	def tmpPartners = []
        tmpPartners.addAll partners

        tmpPartners.each { partner ->
            user.removeFromClassrooms(classroom)
        }

        if (partners) {
        	partners.each { partner ->
        		print partner
        	}
        }
        */

        redirect action:"dividends", params:[period:cmd.period]
    }

}

class DividendsCommand {
	BigDecimal up
	Integer period

	static constraints = {
		up blank:false, min:1.0
		period min:2013
	}

}

class ApplyDividendsCommand {
	BigDecimal tas
	BigDecimal up
	Integer period

	static constraints = {
		tas blank:false, min:1.0
		up blank:false, min:1.0
		period blank:false, min:2013
	}
}
