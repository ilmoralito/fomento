$(".chkall").click(function(event){
     if($(this).prop("checked")) {
	 	$("input[type='checkbox']:not(:checked)").prop("checked", "checked");
	 }else{
		$("input[type='checkbox']:checked").prop("checked", "");
	 }
});