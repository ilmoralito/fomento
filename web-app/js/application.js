$('#enrollmentDate').datepicker({
	format:"yyyy-mm-dd"
}).on("changeDate", function(e){
	$("#enrollmentDate").datepicker("hide");
});

$('#from').datepicker({
	format:"yyyy-mm-dd"
}).on("changeDate", function(e){
	$("#from").datepicker("hide");
});

$('#to').datepicker({
	format:"yyyy-mm-dd"
}).on("changeDate", function(e){
	$("#to").datepicker("hide");
});
