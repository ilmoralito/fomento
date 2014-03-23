package org.fomento
import static java.util.Calendar.*

class RenounceController {
	def partnerService
	def feeService
    def dividendService
    def reportService

	static defaultAction = "renounce"
	static allowedMethods = [
        renounce:["GET", "POST"],
        confirmRenounce:["GET", "POST"],
        show:"GET"
    ]

    def renounce(Integer partnerId) {
    	def partner = partnerService.getPartner(partnerId)
        def period =  new Date()[YEAR]

    	if (!partner) {
    		response.sendError 404
    	}

        def countFeesInCurrentPeriod = Fee.findAllByPartnerAndPeriod(partner, period)
        def hasDividendsInCurrentPeriod = Dividend.findByPartnerAndPeriod(partner, period)

        //set flag variable to true if partner does not have dividends but has fees in current period
        def flag = (!hasDividendsInCurrentPeriod && countFeesInCurrentPeriod) ? true : false

        def data = feeService.totalFeesByPartner(partner)

        def dividends = partner.dividends
        if (!dividends) {
            println data.partnerData2.size()
            def per
            if (data.partnerData2.size()>1) {
                flash.message = "Hay errores en el sistema, no pueden haber cuotas de distintos periodos sin haber generado dividendos con las cuotas mas antiguas!!"
                redirect(controller:"partner", action:"list")
                return false
            }else if (data.partnerData2.size()<1) {
                flash.message = "Hay errores en el sistema, No hay cuotas registradas para poder llevar a cabo el proceso de renuncia!!"
                redirect(controller:"partner", action:"list")
                return false
            }else{
                data.partnerData2.each{
                    per = it.period
                }
                period = per
            }
        }

        [partnerData1:data.partnerData1, partnerData2:data.partnerData2, feesData:data.fees, partner:partner, flag:flag, period:period, dividends:dividends]
    }

    def confirmRenounce(){
        def partner = partnerService.getPartner(params.int("partnerId"))

        if (request.method == "POST") {
            //Calcular y guardar dividendo proporcional
            BigDecimal up = params.up.toBigDecimal()
            Integer period = params.int("period")

            def searchRenounce = dividendService.searchRenouncePeriod(period)

            if (searchRenounce > 0) {
                up = up - searchRenounce
            }

            def fps = reportService.fp(partner, period, "socio")
            def fpe = reportService.fp(partner, period, "empresa")
            def periodData = dividendService.feePeriodData(period)
            renounceProcess(period, fps, fpe, periodData, up, partner)

            //---------Guardar Datos del socio en Tablas de renuncia--------
            def renounce = partnerService.partnerRenounce(partner)
            def renounceId = partner.renouncies.id
            redirect(action:"show", params:[renounceId:renounceId])

        }else{

            def renounce = partnerService.partnerRenounce(partner)
            def renounceId = partner.renouncies.id
            redirect(action:"show", params:[renounceId:renounceId])
        }


    }

    private renounceProcess(period, fps, fpe, pdat, up, Partner partner){
        BigDecimal irPeriod = up * 0.3
        BigDecimal backup = up * 0.1
        up = up - (up * 0.4)

        BigDecimal partnerDD = fps.toBigDecimal() * (up.toBigDecimal() * pdat.pds.toBigDecimal())
        BigDecimal factoryDD = fpe.toBigDecimal() * (up.toBigDecimal() * pdat.pde.toBigDecimal())
        println up.toBigDecimal()
        println fps.toBigDecimal()
        println pdat.pde
            def dividend = new Dividend (
                partnerDividend:partnerDD,
                factoryDividend:factoryDD,
                fps:fps,
                fpe:fpe,
                tas:pdat.tas.toBigDecimal(),
                tae:pdat.tae.toBigDecimal(),
                tap:pdat.tap.toBigDecimal(),
                pds:pdat.pds.toBigDecimal(),
                pde:pdat.pde.toBigDecimal(),
                up:up.toBigDecimal(),
                ir:irPeriod,
                backup:backup,
                period:period.toInteger(),
                partner:partner
            )

            if (!dividend.save()) {
                dividend.errors.allErrors.each {
                    print it
                }
            }
    }

    def show(Integer renounceId) {
        def renounce = Renounce.get(renounceId)

        if (!renounce) {
            response.sendError 404
        }

        [renounce:renounce]
    }

}
