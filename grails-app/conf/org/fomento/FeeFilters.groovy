package org.fomento

class FeeFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                def dayOfMonth = new Date().date

                if (grailsApplication.config.org.fomento.fees.containsValue(dayOfMonth)) {
                    //flag to display alert <li> in main-navbar
                    session.alert = true

                    //get type of payment assigned to this day of month
                    def data = grailsApplication.config.org.fomento.fees.find{key, value ->
                        value == dayOfMonth
                    }

                    session.typeOfPayment = data.key
                } else {
                    //only check for session.alert if it exist the session.typeOfPayment exist too
                    if (session.alert) {
                        session.alert = null
                        session.typeOfPayment = null
                    }
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
