var percentage = $("#percentage"),
	value = $("#value"),
	totalBeforeDecution = $("#totalBeforeDecution"),
	totalAfterDeduction = $("#totalAfterDeduction");

percentage.on("change", function(){
	//update range step
	value.html(this.value)

	//update total after deduction
	/*
	var value = parseFloat(pt.html()), //actual partner total
		rang = parseInt(range.val()) / 100, //calc %
		res = value - (value * rang); //

	tAd.val(res);
	ptad.html(res.toFixed(2));
	*/
	
	
});

