package org.fomento

class FeeService {

    def calcTotal(fees, Partner partner) {
    	def total = 0

    	if (fees) {
    		fees.each { fee ->
    			total = total + fee.fee
    		}
    	}

    	return total + partner.affiliation.capitalization
    }

    def calcFactoryTotalFeesByPartner(fees) {
    	def total = 0

    	if (fees) {
    		fees.each { fee ->
    			total = total + fee.factoryFee
    		}
    	}

    	return total
    }

    def partnerTotalCapitalization(partner) {
        //all this must past formula validation
        //sum capitalization + sum all fees by current user - (sum deductions)

        def partnerFees = calcTotal(partner.fees, partner)
        def factoryFees = calcFactoryTotalFeesByPartner(partner.fees)
        def total = partnerFees + factoryFees

        total
    }
}
