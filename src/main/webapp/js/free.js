$(function() {

	// 게시글 상세보기
	$(document).on("click", ".freeDetail", function() {
		var no = $(this).data("boardNo");
		var pageNum = $("#pageNum").text();
		var searchType = $("#searchTypePara").text();
		var searchKeyword = $("#searchKeywordPara").text();

		location.href = "freeDetail?no=" + no + "&pageNum=" + pageNum +
			"&type=" + searchType + "&keyword=" + searchKeyword;
	});

	// 검색창 유효성 검사
	$("#searchForm").on("submit", function() {
		var keyword = $("#keyword").val();

		if (keyword.length < 1) {
			alert("검색어를 입력해주세요");
			$("#keyword").focus();
			return false;
		}
	});

	// 게시글 쓰기 폼 유효성 검사
	$("#writeForm").on("submit", function() {
		if ($("#title").val().length < 1) {
			alert("제목을 입력해주세요.");
			$("#title").focus();
			return false;
		}

		if ($("#content").val().length < 1) {
			alert("내용을 입력해주세요.");
			$("#content").focus();
			return false;
		}
	});

	// 게시글 수정 폼 요청
	$(document).on("click", "#detailUpdate", function() {

		var no = $(this).data("boardNo");
		var pageNum = $("#pageNum").text();
		var type = $("#searchType").text();
		var keyword = $("#searchKeyword").text();

		location.href = "updateForm?no=" + no + "&pageNum=" + pageNum + "&type=" + type + "&keyword=" + keyword;
	});


	// 게시글 수정 폼 유효성 검사
	$("#updateForm").on("submit", function() {
		if ($("#title").val().length < 1) {
			alert("제목을 입력해주세요.");
			$("#title").focus();
			return false;
		}

		if ($("#content").val().length < 1) {
			alert("내용을 입력해주세요.");
			$("#content").focus();
			return false;
		}
	});


	// 게시글 삭제
	$(document).on("click", "#detailDelete", function() {
		var no = $(this).data("boardNo");
		var pageNum = $("#pageNum").text();
		var type = $("#searchType").text();
		var keyword = $("#searchKeyword").text();

		location.href = "deleteProcess?no=" + no + "&pageNum=" + pageNum + "&type=" + type + "&keyword=" + keyword;
	});
});