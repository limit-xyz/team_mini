$(function() {

	// 다이어리 상세보기
	$(document).on("click", ".dictionaryDetail", function() {
		var animalId = $(this).data("animalId");
		var type = $(this).data("animalType");
		var pageNum = $("#pageNum").text();
		var searchDictionaryType = $("#searchDictionaryTypePara").text();
		var searchDictionaryKeyword = $("#searchDictionaryKeywordPara").text();

		location.href = "dictionaryDetail?animalId=" + animalId + "&type=" + type + "&pageNum=" + pageNum +
			"&searchDictionaryType=" + searchDictionaryType + "&searchDictionaryKeyword=" + searchDictionaryKeyword;
	});

	// 검색창 유효성 검사
	$("#searchDictionaryForm").on("submit", function() {
		
		var searchKeyword = $("#searchDictionaryKeyword").val();

		if (searchKeyword.length < 1) {
			alert("검색어를 입력해주세요");
			$("#searchDictionaryKeyword").focus();
			return false;
		}
	});

	// 다이어리 수정 폼 요청
	$(document).on("click", "#dictionaryUpdate", function() {
		var animalId = $(this).data("animalId");
		var type = $(this).data("animalType");
		var pageNum = $("#pageNum").text();
		var searchDictionaryType = $("#searchDictionaryTypePara").text();
		var searchDictionaryKeyword = $("#searchDictionaryKeywordPara").text();

		location.href = "dictionaryUpdateForm?animalId=" + animalId + "&type=" + type + "&pageNum=" + pageNum +
			"&searchDictionaryType=" + searchDictionaryType + "&searchDictionaryKeyword=" + searchDictionaryKeyword;
	});


	// 다이어리 삭제
	$(document).on("click", "#dictionaryDelete", function() {
		var animalId = $(this).data("animalId");
		var type = $(this).data("animalType");
		var pageNum = $("#pageNum").text();
		var searchDictionaryType = $("#searchDictionaryTypePara").text();
		var searchDictionaryKeyword = $("#searchDictionaryKeywordPara").text();

		location.href = "dictionaryDelete?animalId=" + animalId + "&type=" + type + "&pageNum=" + pageNum +
			"&searchDictionaryType=" + searchDictionaryType + "&searchDictionaryKeyword=" + searchDictionaryKeyword;
	});
});