$(function() {
	// 게시글 클릭 이벤트
	$(document).on("click", ".boardDetail", function() {
		var no = $(this).data("boardNo");
		var type = $(this).data("boardType");
		
		location.href = "searchDetail?no=" + no + "&type=" + type;
	});
});