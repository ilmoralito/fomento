package org.fomento

class History {

    Integer period
    Integer numberFees

    BigDecimal totalPartnerFee
    BigDecimal partnerDividend
    BigDecimal capitalization
    BigDecimal capital

    BigDecimal totalfactoryFee
    BigDecimal factoryDividend

    static constraints = {
        period blank:false
        numberFees blank:false

        totalPartnerFee blank:false, min:0.0
        partnerDividend blank:false, min:0.0
        capitalization blank:false, min: 0.0
        capital blank:false, min: 0.0

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
