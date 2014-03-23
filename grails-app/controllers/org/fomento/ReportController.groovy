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
		list:"GET",
		show:"GET",
        delete:["GET", "POST"],
        capitalize:["GET", "POST"],
        printReport:"GET"
	]

    def beforeInterceptor = [action:this.&negativeUtility, only:"applyDividends"]

    def dividends(DividendsCommand cmd) {
    	if (request.method == "POST") {
    		if (cmd.hasErrors()) {
    			return [cmd:cmd]
    		}

            def searchRenounce = dividendService.searchRenouncePeriod(cmd.period)

            if (searchRenounce > 0) {
                cmd.up = cmd.up - searchRenounce
            }

	    	def partners = Partner.findAllByStatus(true, [sort:"fullName"])
            def result = dividendService.feePeriodData(cmd.period)

	    	return [
                partners:partners,
                tas:result.tas,
                tae:result.tae,
                tap:result.tap,
                pds:result.pds,
                pde:result.pde,
                up:cmd.up - (cmd.up * 0.4),
                ir:cmd.up * 0.3,
                backup:cmd.up * 0.1,
                period:cmd.period
            ]
    	}
    }

    def list() {
        def criteria = Dividend.createCriteria()
        def dividends = criteria.list {
            projections {
                distinct "period"
            }
        }

      	[dividends:dividends]
    }

    def show(Integer period) {
    	def dividends = Dividend.findAllByPeriod(period, [sort:"partner.fullName"])

    	if (!dividends) {
    		redirect action:"list"
    	}

    	[
            dividends:dividends,
            up:dividends.first().up,
            ir:dividends.first().ir,
            backup:dividends.first().backup,
            tas:dividends.first().tas,
            tae:dividends.first().tae,
            tap:dividends.first().tap,
            pds:dividends.first().pds,
            pde:dividends.first().pde,
        ]
    }

    def applyDividends(ApplyDividendsCommand cmd) {
    	if (cmd.hasErrors()) {
    		return [cmd:cmd]
    	}

        def dividendsCountInPeriod = Dividend.countByPeriod(cmd.period)

        if (dividendsCountInPeriod) {
            flash.message = "Ya existen dividendos en este periodo. Accion cancelada. Eliminar dividendo para poder recrearlo"
            redirect action:"dividends"
            return false
        }

        def partners = Partner.findAllByStatus(true)
		partners.each { partner ->
            def fps = reportService.fp(partner, cmd.period, "socio")
            def fpe = reportService.fp(partner, cmd.period, "empresa")
            BigDecimal partnerDD = reportService.dd(cmd.up, cmd.pds, fps)
            BigDecimal factoryDD = reportService.dd(cmd.up, cmd.pde, fpe)

            def dividend = new Dividend (
                partnerDividend:partnerDD,
                factoryDividend:factoryDD,
                fps:fps,
                fpe:fpe,
                tas:cmd.tas,
                tae:cmd.tae,
                tap:cmd.tap,
                pds:cmd.pds,
                pde:cmd.pde,
                up:cmd.up,
                ir:cmd.ir,
                backup:cmd.backup,
                period:cmd.period,
                partner:partner
            )

            if (!dividend.save()) {
                dividend.errors.allErrors.each {
                    print it
                }
            }
		}

        redirect action:"list"
    }

    private negativeUtility(){
        def Float up = params.up.toFloat()
        if (up < 0) {
             flash.message = "No pueden guardarse dividendos con valores negativos, revise la utilidad del periodo"
            redirect(action:"list")
            return false
        }
    }

    private errorRemoving(period){
        Integer peri = params.int("period")
        def dividend = Dividend.findByPeriodGreaterThan(peri)
        if (dividend) {
            flash.message = "Error al intentar elimimar el dividendo, solo puede eliminar el último dividendo registrado"
            redirect(action:"list")
            return false
        }
    }

    @Secured("ROLE_ADMIN")
    def delete() {
        def checkRemove = errorRemoving(params.period)
        def query = Dividend.where {
            period == params.int("period")
        }
        if (request.method == "GET") {
            return [period:params.period]
        }else{
            int total = query.deleteAll()
            flash.message = "$total dividendos eliminados"
            redirect action:"list"
        }
    }

    def capitalize(Integer id) {
        def dividend = Dividend.get(id)
        if (!dividend) {
            redirect action:"list"
        }

        def capitalization

        if (request.method == "POST") {
            if (params.capitalizationId) {
                //update
                capitalization = Capitalization.get(params.capitalizationId)
                capitalization.percentage = params.int("percentage")
            } else {
                //create
                capitalization = new Capitalization(percentage:params.percentage, dividend:dividend)
            }

            if (!capitalization.save()) {
                capitalization.errors.allErrors.each {
                    print it
                }
            }
        }

        [dividend:dividend, id:id]
    }

    def printReport(){
        def partner = Partner.get(params.id)
        boolean cap = true
        def flag = "socio"
        def cuotasSocio = reportService.totalC(partner, flag)

        flag = "empresa"
        def cuotasEmpresa = reportService.totalC(partner, flag)

        def totalCapitalizaciones = reportService.tCap(partner)

        flag = "capital"
        def periodCapital = reportService.periodCap(partner, params.int("period"), cap, flag)

        if (periodCapital==false) {
            redirect(action:"capitalize", params:[id:params.iddiv])
            flash.message = "No se ha asignado ninguna capitalización al dividendo del periodo " + params.period +  ", debe hacerlo para imprimir el reporte!!"
            return false
        }else{
            flag = "retiro"
            def retiro = reportService.periodCap(partner, params.int("period"), cap, flag)

            flag = "socio"
            BigDecimal saldoIni = 0
            def saldoIniSocio = reportService.saldoInicial(partner, flag, saldoIni)

            flag = "empresa"
            def saldoIniEmpresa = reportService.saldoInicial(partner, flag, saldoIni)

            def saldoTotalSocio = cuotasSocio + totalCapitalizaciones + saldoIniSocio

            def saldoTotalEmpresa = cuotasEmpresa + saldoIniEmpresa
            def fecha = new Date().format("yyyy-MM-dd")
            [socio:saldoTotalSocio, empresa:saldoTotalEmpresa, capitalizacion:periodCapital, retiro:retiro, partner:partner, period:params.period, position:params.int("position"), fecha:fecha]
        }
    }
}

class DividendsCommand {
	BigDecimal up
	Integer period

	static constraints = {
		up blank:false
		period min:2010
	}
}

class ApplyDividendsCommand {
    BigDecimal fps
    BigDecimal fpe
	BigDecimal tas
    BigDecimal tae
    BigDecimal tap
    BigDecimal pds
    BigDecimal pde
    BigDecimal up
    BigDecimal ir
	BigDecimal backup
    Integer period

	static constraints = {
		importFrom Dividend
	}
}
