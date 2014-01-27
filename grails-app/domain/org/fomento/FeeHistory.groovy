package org.fomento

class FeeHistory {

    BigDecimal fee
    Integer numberFee

    static constraints = {
        fee blank:false
        numberFee blank:false
    }

    static mapping = {
        version false
    }

    static belongsTo = [history:History]
}
