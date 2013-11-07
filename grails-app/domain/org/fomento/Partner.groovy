package org.fomento

import static java.util.Calendar.*

class Partner implements Serializable {

    static searchable = true

    String fullName
    Integer numberOfEmployee
    String identificationCard
    String department
    BigDecimal salary
    Boolean status = true
    Affiliation affiliation

	Date dateCreated
	Date lastUpdated

    static constraints = {
        fullName blank:false
        numberOfEmployee blank:false, unique:true, min:1
        identificationCard blank:false, unique:true, matches: "^\\d{3}-?(\\d{6})-?\\d{4}[a-zA-Z]\$"
        department blank:false, maxSize:255
        salary blank:false, min:1000.0
    }

    static namedQueries = {
        byStatus { status ->
            eq "status", status
        }

        fromTo { from, to ->
            affiliation {
                ge "enrollmentDate", from.clearTime()
                le "enrollmentDate", to.clearTime()
            }
        }

        byTypeOfPayment { typeOfPayment ->
            affiliation {
                eq "typeOfPayment", typeOfPayment
            }
        }

        report { int period ->
            def from = new Date()

            from[YEAR] = period
            from[MONTH] = JANUARY
            from[DATE] = 1

            def to = new Date()

            to[YEAR] = period
            to[MONTH] = DECEMBER
            to[DATE] = 31

            fees {
                ge "paymentDate", from.clearTime()
                le "paymentDate", to.clearTime()
            }
        }
    }

    static hasMany = [fees:Fee, dividends:Dividend]

    static mapping = {
        version false
        status defaultValue: true
        sort dateCreated: "desc"
        dividends sort: 'period', order: 'desc'
    }

    String toString() {
        fullName
    }

}
