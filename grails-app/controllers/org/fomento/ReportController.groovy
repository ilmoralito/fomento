package org.fomento

import grails.plugins.springsecurity.Secured

@Secured(["ROLE_ADMIN","ROLE_USER"])
class ReportController {

	def feeService
	def dividendService
    def reportService

	static defaultAction = "dividends"
	static allowedMethods = [
		dividends:["GET", "POST"],
		applyDividends:"POST",
		overwriteDividends:["GET", "POST"],
		list:"GET",
		show:"GET",
        delete:"GET"
	]

    def dividends(DividendsCommand cmd) {
    	if (request.method == "POST") {
    		if (cmd.hasErrors()) {
    			return [cmd:cmd]
    		}

	    	def partners = Partner.findAllByStatus(true)
            def result = dividendService.feePeriodData(partners, cmd.period)

	    	return [
                partners:partners,
                tas:result.tas,
                tae:result.tae,
                tap:result.tap,
                pds:result.pds,
                pde:result.pde,
                up:cmd.up,
                period:cmd.period
            ]
    	}
    }

    def list() {
    	def dividends = Dividend.byPeriod().list()

    	[dividends:dividends]
    }

    def show(Integer period) {
    	def dividends = Dividend.findAllByPeriod(period)

    	if (!dividends) {
    		redirect action:"list"
    	}

    	[
            dividends:dividends,
            up:dividends.first().up,
            tas:dividends.first().tas,
            tae:dividends.first().tae,
            tap:dividends.first().tap,
            pds:dividends.first().pds,
            pde:dividends.first().pde,
        ]
    }

    def applyDividends(ApplyDividendsCommand cmd) {
    	if (cmd.hasErrors()) {
            cmd.errors.allErrors.each {
                print it
            }
    		return [cmd:cmd]
    	}

    	def partners = Partner.findAllByStatus(true)
    	def dividendsCount =  Dividend.countByPeriod(cmd.period)

    	if (!dividendsCount) {
			partners.each { partner ->
                def fps = reportService.fp(partner, cmd.period, "fee", "capitalization")
                def fpe = reportService.fp(partner, cmd.period, "factoryFee", "factoryCapital")

                def partnerDD = reportService.dd(cmd.up, cmd.pds, fps)
                def factoryDD = reportService.dd(cmd.up, cmd.pds, fpe)

                def dividend = new Dividend (
                    partnerDividend:partnerDD,
                    factoryDividend:factoryDD,
                    tas:cmd.tas,
                    tae:cmd.tae,
                    tap:cmd.tap,
                    pds:cmd.pds,
                    pde:cmd.pde,
                    up:cmd.up,
                    period:cmd.period,
                    partner:partner
                )

                if (!dividend.save()) {
                    dividend.errors.allErrors.each {
                        print it
                    }
                }

    			//partner.addToDividends(dividend)
			}
		} else {
			redirect action:"overwriteDividends", params:[tas:cmd.tas, tae:cmd.tae, tap:cmd.tap, pds:cmd.pds, pde:cmd.pde, up:cmd.up, period:cmd.period]
			return false
		}

        redirect action:"list"
    }

    @Secured("ROLE_ADMIN")
    def overwriteDividends() {
    	if (request.method == "POST") {
    		def partners = Partner.findAllByStatus(true)
            def pds = params.double("pds")
            def pde = params.double("pde")
            def up = params.double("up")
            def period = params.int("period")

            partners.each { partner ->
                def dividend = Dividend.findByPartnerAndPeriod(partner, period)
                def fps = reportService.fp(partner, period, "fee", "capitalization")
                def fpe = reportService.fp(partner, period, "factoryFee", "factoryCapital")

                def partnerDD = reportService.dd(up, pds, fps)
                def factoryDD = reportService.dd(up, pds, fpe)

    			if (dividend) {
    				dividend.partnerDividend = partnerDD
                    dividend.factoryDividend = factoryDD
                    dividend.tas = params.double("tas")
                    dividend.tae = params.double("tae")
                    dividend.tap = params.double("tap")
                    dividend.pds = params.double("pds")
                    dividend.pde = params.double("pde")
                    dividend.up = up

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

    //delete
    @Secured("ROLE_ADMIN")
    def delete(Integer period) {
        def query = Dividend.where {
            period == period
        }

        int total = query.deleteAll()

        flash.message = "$total dividendos eliminados"

        redirect action:"list"
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
    BigDecimal tae
    BigDecimal tap
    BigDecimal pds
    BigDecimal pde
	BigDecimal up
    Integer period

	static constraints = {
		tas blank:false, min:1.0
        tae blank:false, min:1.0
        tap blank:false, min:1.0
        pds blank:false, min:0.0
        pde blank:false, min:0.0
		up blank:false, min:1.0
		period blank:false, min:2013
	}
}
