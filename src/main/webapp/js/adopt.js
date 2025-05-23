$(function() {

	$contextPath = $("#contextPath").text();
	$pageNum = $("#pageNum").text();
	$userId = $("#userId").text();

	$(document).on("click", "#detailUpdate", function() {
		var postId = $("#postId").val();
		location.href = $contextPath + "/adoption/AdoptionUpdateForm?postId=" + postId;
	});

	$(document).on("click", "#detailDelete", function() {
		var postId = $("#postId").val();
		location.href = $contextPath + "/adoption/AdoptionDelete?postId=" + postId;
	});

	$(document).on("click", "#guestReply", function() {
		alert("댓글을 작성하려면 로그인해주세요.");
		window.location.href = contextPath + "/member/loginForm";
	});

	$(document).on("click", ".modifyReply", function() {
		var replyId = $(this).data("no");
		var postId = $("#postId").val();
		location.href = $contextPath + '/adoption/AdoptionReplyUpdateForm?replyId=' + replyId + '&postId=' + postId;
	});

	$(document).on("click", ".deleteReply", function() {
		var replyId = $(this).data("no");
		var postId = $("#postId").val();
		location.href = $contextPath + '/adoption/AdoptionReplyDelete?replyId=' + replyId + '&postId=' + postId + "&pageNum=" + $pageNum;
	});

	$(document).on("click", ".reportReply", function() {
		
		if ($userId == null || $userId == ""){
			alert("로그인이 필요합니다.");
			return false;
		}
		
		if (confirm("댓글을 신고하시겠습니까?")) {
			alert("관리자에게 신고되었습니다.");
			var postId = $("#postId").val();
			var title = $("#boardTitle").text();
			var replyContent = $(this).data("replyContent");
			
			var url = $contextPath + '/adoption/AdoptionReplyReport?userId=' + $userId + '&title=' + title + '&replyContent=' + replyContent;
			url += '&postId=' + postId + '&pageNum=' + $pageNum;
			location.href = url;
		}
	});
});