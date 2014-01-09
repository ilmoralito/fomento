package org.fomento

class History {

    Integer period
    Integer numberFees

    BigDecimal partnerFee
    BigDecimal totalPartnerFee
    BigDecimal partnerDividend
    BigDecimal capitalization
    BigDecimal capital

    BigDecimal factoryFee
    BigDecimal totalfactoryFee
    BigDecimal factoryDividend

	Date dateCreated

    static constraints = {
        period blank:false
        numberFees blank:false

        partnerFee blank:false, min:0.0
        totalPartnerFee blank:false, min:0.0
        partnerDividend blank:false, min:0.0
        capitalization blank:false, min: 0.0
        capital blank:false, min: 0.0

        factoryFee blank:false, min:0.0
        totalfactoryFee blank:false, min:0.0
        factoryDividend blank:false, min:0.0
    }

    static belongsTo = [renounce:Renounce]

    static mapping = {
        version false
    }

    String toString() {
        period
    }

}
