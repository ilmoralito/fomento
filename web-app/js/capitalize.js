var percentage = $("#percentage"),
	range = $("#range"),
	capital = $("#capital").html(),
	capitalized = $("#capitalized"),
	nocapitalized = $("#nocapitalized");

percentage.on("input change",function(){
	var per = 100 - this.value
	range.html(per);

	capitalized.html(capital * (per / 100))

	nocapitalized.html(capital - (capital * (per / 100)))
});

//on load
var per = 100 - percentage.val()
range.html(per);

capitalized.html(capital * (per / 100))

nocapitalized.html(capital - (capital * (per / 100)))
