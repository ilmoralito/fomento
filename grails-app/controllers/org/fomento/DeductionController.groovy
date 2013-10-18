package org.fomento

class DeductionController {

	def feeService

	static defaultAction = "deductions"
	static allowedMethods = []

    def deductionsFlow = {
        init {
            action {
                def partner = Partner.findById(params.int("partner"))

                [partner:partner, period:params?.period, total:feeService.partnerTotalCapitalization(partner)]
            }

            on("success").to "list"
            on(Exception).to "done"
        }

        create {
            on("confirm") {
                def deduction = new Deduction(
                    totalBeforeDeduction:params?.totalBeforeDeduction,
                    totalAfterDeduction:params?.totalAfterDeduction,
                    period:flow.period,
                    percentage:params.int("percentage") / 100,
                    partner:flow.partner
                )

                if (!deduction.save()) {
                    deduction.errors.allErrors.each {
                        print it
                    }
                }

                flow.partner.refresh()
            }.to "list"

            on("cancel").to "done"
            on("list").to "list"
            on("create").to "create"
        }

        list {

            on("list").to "list"
            on("create").to "create"
        }

        done {
            redirect controller:"fee", action:"list", params:[id:flow.partner.id]
        }
    }

}
