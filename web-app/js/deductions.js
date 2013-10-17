var amount = $("#amount"),
	range = $("#percentage"),
	pt = $("span#total"),
	ptad = $("span#total_after_deduction"),
	tAd = $("#totalAfterDeduction");


range.on("change", function(){
	//update range step
	amount.html(this.value)

	//update total after deduction
	var value = parseFloat(pt.html()), //actual partner total
		rang = parseInt(range.val()) / 100, //calc %
		res = value - (value * rang); //

	tAd.val(res);
	ptad.html(res);
});

