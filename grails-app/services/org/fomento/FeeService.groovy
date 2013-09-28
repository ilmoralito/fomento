package org.fomento

class FeeService {

    def calcTotal(fees) {
    	def total = 0

    	if (fees) {
    		fees.each {
    			total = total + it.total
    		}
    	}

    	return total
    }
}
