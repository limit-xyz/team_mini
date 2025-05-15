$(function() {

	// 다이어리 상세보기
	$(document).on("click", ".diaryDetail", function() {
		var no = $(this).data("diaryNo");
		location.href = "diaryDetail?no=" + no;
	});

	// 다이어리 쓰기 폼 유효성 검사
	$("#diaryWriteForm").on("submit", function() {
		if ($("#title").val().length < 1) {
			alert("제목을 입력해주세요.");
			$("#title").focus();
			return false;
		}

		if ($("#petName").val().length < 1) {
			alert("작성하려는 다이어리의 반려동물 이름을 입력해주세요.");
			$("#petName").focus();
			return false;
		}

		if ($("#content").val().length < 1) {
			alert("다이어리 내용을 입력해주세요.");
			$("#content").focus();
			return false;
		}
	});

	// 다이어리 수정 폼 요청
	$(document).on("click", "#diaryUpdate", function() {
		var no = $(this).data("diaryNo");
		location.href = "diaryUpdateForm?no=" + no;
	});


	// 다이어리 수정 폼 유효성 검사
	$("#diaryUpdateForm").on("submit", function() {
		if ($("#title").val().length < 1) {
			alert("제목을 입력해주세요.");
			$("#title").focus();
			return false;
		}

		if ($("#petName").val().length < 1) {
			alert("작성하려는 다이어리의 반려동물 이름을 입력해주세요.");
			$("#petName").focus();
			return false;
		}

		if ($("#content").val().length < 1) {
			alert("다이어리 내용을 입력해주세요.");
			$("#content").focus();
			return false;
		}
	});


	// 다이어리 삭제
	$(document).on("click", "#diaryDelete", function() {
		var no = $(this).data("diaryNo");
		location.href = "diaryDelete?no=" + no;
	});
});