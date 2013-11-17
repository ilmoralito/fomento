package org.fomento

class Capitalization {

    Integer percentage

	Date dateCreated
	Date lastUpdated

    static constraints = {
        percentage blank:false, min:0, max:100
    }

    static belongsTo = [dividend:Dividend]

    static mapping = {
        version false
    }

    def beforeInsert() {
        percentage = 100 - percentage
    }

    def beforeUpdate() {
        percentage = 100 - percentage
    }

    String toString() {
        percentage
    }

}
