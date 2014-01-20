package org.fomento

class FeeHistory {

    Integer period
    BigDecimal fee
    Integer numberFee
   
    static constraints = {
        period blank:false
        fee blank:false
        numberFee blank:false
    }

    static mapping = {
        version false
    }

    static belongsTo = [history:History]
}
