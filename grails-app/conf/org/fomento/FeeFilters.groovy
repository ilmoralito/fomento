package org.fomento

class FeeFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
