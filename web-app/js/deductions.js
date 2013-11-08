var percentage = $("#percentage"),
	value = $("#value"),
	totalBeforeDeduction = $("#totalBeforeDeduction").val(),
	noCapitalization = $("#noCapitalization");

percentage.on("change", function(){
	//update range step
	value.html(this.value)

	//update total after deduction
	var per = this.value / 100
	var result = parseFloat(totalBeforeDeduction) - parseFloat(totalBeforeDeduction) * per

	noCapitalization.val(result.toFixed(2))
});

