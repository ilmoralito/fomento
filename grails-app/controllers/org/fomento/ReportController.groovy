package org.fomento

class ReportController {

	def feeService

	static defaultAction = "dividends"
	static allowedMethods = [
		dividends:["GET", "POST"]
	]

    def dividends(Integer period) {
    	/*
    	1 Calcular el TAS (Total aporte de todos los socios en el periodo)
    	*/
    	def tas = 0
    	def partners = Partner.list()

    	partners.each { partner ->
    		tas = tas + feeService.partnerTotalCapitalization(partner, period)
    	}

    	/*
    	esto debe ser calculado en la vista, en un bucle por cada socio realizar el calculo
		2 Calcular APS (Total del aporte personal del Socio en el periodo)
		3 Calcular FPS (Factor porcentual socio)

		FPS = TAS/APS

		4 Calcular DD (Distribución de  dividendos a pagar al socio)

		DD = Utilidad del periodo * FPS

		Nota: la utilidad del periodo es un campo que debe ser administrado porque ese dato
		debe de introducirse antes para calcular la distribución de dividendos a cada socio

		5 Calcular DP (Dividendos a pagar) es el valor neto a devolver al socio a menos que este decida capitalizar.

		DP = DD * 0.1

		debemos generar una especie de reporte imprimible para cada socio o que abarque dos o tres socios.
		*/


    }

}
