$(function() {

	// 다이어리 삭제
	$(document).on("click", "#diaryDelete", function() {
		var no = $(this).data("diaryNo");
		location.href = "diaryDelete?no=" + no;
	});

	// 다이어리 수정
	$(document).on("click", "#diaryUpdate", function() {
		var no = $(this).data("diaryNo");
		location.href = "diaryUpdateForm?no=" + no;
	});

	// 다이어리 상세보기
	$(document).on("click", ".diaryDetail", function() {
		var no = $(this).data("diaryNo");
		location.href = "diaryDetail?no=" + no;
	});
});