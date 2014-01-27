package org.fomento

class Renounce {
    BigDecimal residuePartner = 0.0
    BigDecimal residueFactory
    Date enrollmentDate

	Date dateCreated

    static constraints = {
        residuePartner blank:false
        residueFactory blank:false
    }

    static hasMany = [histories:History]
    static belongsTo = [partner:Partner]

    static mapping = {
        version false
    }
}
