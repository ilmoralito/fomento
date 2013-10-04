package org.fomento

class FeeService {

    def calcTotal(List fees, Partner partner) {
    	def total = 0

    	if (fees) {
    		fees.each {
    			total = total + it.total
    		}
    	}

    	return total + partner.affiliation.capitalization
    }
}
