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
    	def i = 0, capital
    	partnerData1.each{ pd ->
    		if (i==0) {
    			capital = partner?.affiliation?.capitalization + partnerData2[i].totalPartnerFee + pd.capitalization
    		}else{
    			capital = capital + partnerData2[i].totalPartnerFee + pd.capitalization
    		}


    		def partnerHistory = new History(
    			period: pd.period,
    			numberFees: partnerData2[i].numberFees,
    			totalPartnerFee: partnerData2[i].totalPartnerFee,
			    partnerDividend: pd.partnerDividend,
			    capitalization: pd.capitalization,
			    withdraw: pd.withdraw,
			    capital: capital,
			    totalfactoryFee: partnerData2[i].totalFactoryFee,
			    factoryDividend:pd.factoryDividend,
			    up: pd.up,
			    percentage: pd.porcentaje.toString().toInteger(),
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
					period: fh.period,
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
    }


}
