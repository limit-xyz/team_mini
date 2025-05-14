$(function() {
	
	// 다이어리 상세보기
	$(document).on("click", ".diaryDetail", function() {
		alert("클릭");
	});
	
	$(document).on("hover", ".diaryDetail", function() {
		 $(this).css('cursor', 'pointer');
	});
});