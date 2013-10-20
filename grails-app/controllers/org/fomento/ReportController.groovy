package org.fomento

class ReportController {

	def feeService

	static defaultAction = "dividends"
	static allowedMethods = [
		dividends:["GET", "POST"]
	]

    def dividends(DividendsCommand cmd, Integer period) {
    	if (request.method == "POST") {
    		if (cmd.hasErrors()) {
    			return [cmd:cmd]
    		}

    		def tas = 0
	    	def partners = Partner.list()

	    	partners.each { partner ->
	    		tas = tas + feeService.partnerTotalCapitalization(partner, period)
	    	}

	    	return [partners:partners, tas:tas, up:(params.double("up")) ?: 10, period:period]
    	}
    }

}

class DividendsCommand {
	BigDecimal up

	static constraints = {
		up blank:false, min:1.0
	}
}
