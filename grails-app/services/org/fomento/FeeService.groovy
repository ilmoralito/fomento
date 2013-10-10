package org.fomento

class FeeService {

    def calcTotal(Partner partner) {
    	def total = 0

    	if (partner.fees) {
    		partner.fees.each { fee ->
    			total = total + fee.fee
    		}
    	}

    	return total + partner.affiliation.capitalization
    }

    def calcFactoryTotalFeesByPartner(Partner partner) {
    	def total = 0

    	if (partner.fees) {
    		partner.fees.each { fee ->
    			total = total + fee.factoryFee
    		}
    	}

    	return total
    }
}
