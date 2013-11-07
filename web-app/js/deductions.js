var percentage = $("#percentage"),
	value = $("#value"),
	totalBeforeDeduction = $("#totalBeforeDeduction").val(),
	totalAfterDeduction = $("#totalAfterDeduction");

percentage.on("change", function(){
	//update range step
	value.html(this.value)

	//update total after deduction
	var per = this.value / 100
	var result = parseFloat(totalBeforeDeduction) - parseFloat(totalBeforeDeduction) * per

	totalAfterDeduction.val(result.toFixed(2))
});

