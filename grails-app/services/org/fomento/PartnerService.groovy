package org.fomento

class PartnerService {

    def getPartner(Integer id) {
    	def partner = Partner.get(id)

    	partner
    }
}
