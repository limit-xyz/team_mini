/**
 * 
 */
$(function() {
	$("#locationConfirm").on("click", function() {
		
		let lat = $("#lat").val();
		let lng = $("#lng").val();
		let data = "lat=" + lat + "&lng=" + lng;
		
		$.ajax({
			url:"locationConfirm.ajax",
			data:data,
			"dataType":"json",
			success:function(resData) {
				console.log(resData);
				
				
				
				
			},
			error: function(xhr) {
				console.log("error : ",xhr);
			}	
		})
		
	});
	
});