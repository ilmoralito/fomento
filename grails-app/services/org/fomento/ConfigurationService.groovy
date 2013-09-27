package org.fomento

class ConfigurationService {

    def loadFactoryFee() {
    	def fees = Configuration.list().fee

    	return fees.min()
    }
}
