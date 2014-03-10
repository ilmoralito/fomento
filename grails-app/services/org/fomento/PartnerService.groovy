package org.fomento

class PartnerService {
	FeeService feeService

	def getPartner(Integer id) {
    	def partner = Partner.get(id)
    	partner
    }

    def partnerRenounce(Partner partner){
    	def results = feeService.totalFeesByPartner(partner)

    	def partnerData1 = results.partnerData1
    	def partnerData2 = results.partnerData2
    	def partnerFees = results.fees

    	//-------Save renoune data-------
    	def renounce = new Renounce(
    		residuePartner: partner?.affiliation?.capitalization,
    		residueFactory:  partner?.affiliation?.factoryCapital,
    		enrollmentDate: partner?.affiliation?.enrollmentDate,
    		partner:partner
    	).save()

    	if (!renounce.save()) {
            renounce.errors.allErrors.each {
                print it
            }
        }
    	//--------Save History----------
    	def i = 0, capital, fCapital
    	partnerData1.each{ pd ->
    		if (i==0) {
    			capital = partner?.affiliation?.capitalization + partnerData2[i].totalPartnerFee + pd.capitalization
    			fCapital = partner?.affiliation?.factoryCapital + partnerData2[i].totalFactoryFee + pd.factoryDividend
    		}else{
    			capital = capital + partnerData2[i].totalPartnerFee + pd.capitalization
    			fCapital = fCapital + partnerData2[i].totalFactoryFee + pd.factoryDividend
    		}

            def porcent = (pd.porcentaje)?pd.porcentaje.toString().toInteger():100

    		def partnerHistory = new History(
    			period: pd.period,
    			numberFees: partnerData2[i].numberFees,
    			totalPartnerFee: partnerData2[i].totalPartnerFee,
			    partnerDividend: pd.partnerDividend,
                partnerDivNeto: pd.partnerDivNeto,
			    capitalization: pd.capitalization,
			    withdraw: pd.withdraw,
			    capital: capital,
			    totalfactoryFee: partnerData2[i].totalFactoryFee,
			    factoryDividend:pd.factoryDividend,
			    factoryCapital: fCapital,
			    up: pd.up,
			    percentage: porcent,
			    renounce: renounce
    		)

    		if (!partnerHistory.save()) {
                partnerHistory.errors.allErrors.each {
                    print it
                }
            }

			def fHistory = partnerFees.findAll{it.period == pd.period}

			fHistory.each { fh ->
				def feeHistory = new FeeHistory(
					fee: fh.fee,
					numberFee: fh.count,
					history: partnerHistory
				)

				if (!feeHistory.save()) {
	                feeHistory.errors.allErrors.each {
	                	print it
	                }
            	}
			}
   			i++
    	}
    	def updateData = updateDataAfterRenounce(partner)
    }

    def updateDataAfterRenounce(Partner partner){
    	// Partner's Balance to zero
    	def affiliation = Affiliation.get(partner.affiliation.id)
    	affiliation.capitalization = 0.0
    	affiliation.factoryCapital = 0.0

    	// Delete partner's Fee
    	Fee.executeUpdate("delete Fee f where f.partner =" + partner.id)

    	// Delete partner's Dividend
    	Dividend.executeUpdate("delete Dividend d where d.partner =" + partner.id)

    	// change partner's status
    	partner.status = false
    }

    def checkIfPartnerHasFeesOrdividends(Partner partner) {
        def dontHasFeesOrDividends = true

        if (!partner.fees.size() && !partner.dividends.size()) {
            dontHasFeesOrDividends = false
        }

        dontHasFeesOrDividends
    }

}
