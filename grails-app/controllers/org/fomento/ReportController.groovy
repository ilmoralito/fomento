package org.fomento

class ReportController {

	def feeService

	static defaultAction = "dividends"
	static allowedMethods = [
		dividends:["GET", "POST"],
		applyDividends:"POST"
	]

    def dividends(DividendsCommand cmd, Integer period) {
    	if (request.method == "POST") {
    		if (cmd.hasErrors()) {
    			return [cmd:cmd]
    		}

    		def tas = 0
    		//TODO:only actives partners
	    	def partners = Partner.list()

	    	partners.each { partner ->
	    		tas = tas + feeService.partnerTotalCapitalization(partner, period)
	    	}

	    	return [partners:partners, tas:tas, up:(params.double("up")) ?: 10, period:period]
    	}
    }

    def applyDividends() {
    	/*
    	//get all partners with status set to true
    	def partners = Partner.findAllByStatus(true)

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
    }

}

class DividendsCommand {
	BigDecimal up

	static constraints = {
		up blank:false, min:1.0
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
