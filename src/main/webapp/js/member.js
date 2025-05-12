$(function() {

	// 멤버 삭제 메소드
	$(".deleteMember").on("click", function() {
		var no = $(this).data('no');

		var isDelete = confirm(no + "번 사용자를 삭제하시겠습니까?\n이 명령은 되돌릴 수 없습니다.")

		if (isDelete) {
			alert("삭제 완료");
		}
	});


	// 멤버 차단 메소드
	$(".ignoreMember").on("click", function() {
		var no = $(this).data('no');
		$("#ignoreDate").val("");
		$("#ignoreNo").text(no);
	});


	// 멤버 차단해제 메소드
	$(".releaseMember").on("click", function() {
		var no = $(this).data('no');
		var isRelease = confirm(no + "번 사용자의 차단을 해제하시겠습니까?");

		if (isRelease) {

		}
	});
});