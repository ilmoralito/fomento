package org.fomento

class History {

    Integer period
    Integer numberFees

    BigDecimal totalPartnerFee
    BigDecimal partnerDividend
    BigDecimal partnerDivNeto
    BigDecimal capitalization
    BigDecimal withdraw
    BigDecimal capital

    BigDecimal totalfactoryFee
    BigDecimal factoryDividend
    BigDecimal factoryCapital

    BigDecimal up
    BigDecimal ir
    BigDecimal backup
    Integer percentage

    static constraints = {
        period blank:false
        numberFees blank:false

        totalPartnerFee blank:false, min:0.0
        partnerDividend blank:false, min:0.0
        capitalization blank:false, min: 0.0
        capital blank:false, min: 0.0

        totalfactoryFee blank:false, min:0.0
        factoryDividend blank:false, min:0.0
        factoryCapital blank:false, min:0.0
        ir blank:false
        backup blank:false
    }

    static belongsTo = [renounce:Renounce]
    static hasMany = [feeHistory:FeeHistory]

    static mapping = {
        version false
    }

    String toString() {
        period
    }

}
