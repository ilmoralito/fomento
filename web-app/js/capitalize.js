var percentage = $("#percentage"),
	range = $("#range"),
	capital = $("#capital").html(),
	capitalized = $("#capitalized"),
	nocapitalized = $("#nocapitalized");

percentage.on("input change",function(){
	var per = 100 - this.value
	range.html(per);

	capitalized.html(get_total_to_capitalize(capital, per).toFixed(3))

	nocapitalized.html(get_total_to_no_capitalize(capital, per).toFixed(3))
});

//on load
var per = 100 - percentage.val()
range.html(per);

capitalized.html(get_total_to_capitalize(capital, per).toFixed(3))

nocapitalized.html(get_total_to_no_capitalize(capital, per).toFixed(3))

function get_total_to_capitalize(capital, percentage){
	return capital * (percentage / 100)
}

function get_total_to_no_capitalize(capital, percentage){
	var to_capitalize = get_total_to_capitalize(capital, percentage)

	return capital - to_capitalize
}