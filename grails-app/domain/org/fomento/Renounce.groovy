package org.fomento

class Renounce {
    String fullName
    String identificationCard
    BigDecimal residuePartner = 0.0
    BigDecimal residueFactory

	Date dateCreated
	Date lastUpdated

    static constraints = {
        fullName blank:false
        identificationCard blank:false, unique:true, matches: "^\\d{3}-?(\\d{6})-?\\d{4}[a-zA-Z]\$"
        residuePartner blank:false
        residueFactory blank:false
    }

    static hasMany = [histories:History]
    static belongsTo = [partner:Partner]

    static mapping = {
        version false
    }

    String toString() {
        fullName
    }

}
