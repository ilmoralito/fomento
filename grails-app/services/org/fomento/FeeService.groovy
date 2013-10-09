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
}
