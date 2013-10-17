package org.fomento

class FeeService implements Serializable {

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
        def totalDeductions = 0

        partner?.deductions?.each { deduction ->
            totalDeductions = totalDeductions + (deduction.totalBeforeDeduction - deduction.totalAfterDeduction)
        }

        def total = (partnerFees + factoryFees) - totalDeductions

        total
    }
}
