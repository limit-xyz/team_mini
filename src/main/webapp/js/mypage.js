$(function() {

	// 다이어리 상세보기
	$(document).on("click", ".myBoardDetail", function() {
		var no = $(this).data("boardNo");
		var boardtype = $(this).data("boardType");
		var pageNum = $("#pageNum").text();
		var searchBoardType = $("#searchBoardTypePara").text();
		var searchBoardKeyword = $("#searchBoardKeywordPara").text();

		location.href = "myBoardDetail?no=" + no + "&boardType=" + boardtype +"&pageNum=" + pageNum +
			"&type=" + searchBoardType + "&keyword=" + searchBoardKeyword;
	});

	// 검색창 유효성 검사
	$("#searchBoardForm").on("submit", function() {
		var searchKeyword = $("#searchBoardKeyword").val();

		if (searchKeyword.length < 1) {
			alert("검색어를 입력해주세요");
			$("#searchBoardKeyword").focus();
			return false;
		}
	});

});