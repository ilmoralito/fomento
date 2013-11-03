package org.fomento

import grails.plugins.springsecurity.Secured

@Secured(["ROLE_ADMIN","ROLE_USER"])
class ReportController {

	def feeService
	def dividendService

	static defaultAction = "dividends"
	static allowedMethods = [
		dividends:["GET", "POST"],
		applyDividends:"POST",
		overwriteDividends:["GET", "POST"],
		list:"GET",
		show:"GET"
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

    //list
    def list() {
    	def dividends = Dividend.byPeriod().list()

    	[dividends:dividends]
    }

    //show
    def show(Integer period) {
    	def dividends = Dividend.findAllByPeriod(period)

    	if (!dividends) {
    		redirect action:"list"
    	}

    	[dividends:dividends]
    }

    //create
    def applyDividends(ApplyDividendsCommand cmd) {
    	//get all partners with status set to true
    	if (cmd.hasErrors()) {
    		return [cmd:cmd]
    	}

    	def partners = Partner.findAllByStatus(true)
    	def dividendsCount =  Dividend.countByPeriod(cmd.period)

		if (!dividendsCount) {
			partners.each { partner ->
				def dp = dividendService.getPeriodUtility(partner, cmd.tas, cmd.up, cmd.period)

				//add dividend to each partner in period
				partner.addToDividends(new Dividend(dividend:dp.dp, period:cmd.period))
			}
		} else {
			//ask user if want to procede
			redirect action:"overwriteDividends", params:[tas:cmd.tas, up:cmd.up, period:cmd.period]
			return false
		}

        redirect action:"list"
    }

    //update
    @Secured("ROLE_ADMIN")
    def overwriteDividends() {
    	if (request.method == "POST") {
    		def partners = Partner.findAllByStatus(true)

    		partners.each { partner ->
    			def dividend = Dividend.findByPartnerAndPeriod(partner, params.period)
    			def dp = dividendService.getPeriodUtility(partner, params.double("tas"), params.double("up"), params.int("period"))

    			if (dividend) {
    				dividend.properties["dividend"] = dp.dp

    				if (!dividend.save()) {
    					dividend.errors.allErrors.each {
    						print it
    					}
    				}
    			}
    		}

    		redirect action:"list"
    		return false
    	}
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
