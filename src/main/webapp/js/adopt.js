$(function() {

	$contextPath = $("#contextPath").text();
	$pageNum = $("#pageNum").text();

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
		var replyId = $(this).data("no");
		var postId = $("#postId").val();
		location.href = $contextPath + '/adoption/AdoptionReplyUpdateForm?replyId=' + replyId + '&postId=' + postId;
	});

	function reportReply(replyId) {
		if (confirm("댓글을 신고하시겠습니까?")) {
			location.href = '${pageContext.request.contextPath}/adoption/AdoptionReplyReport?replyId=' + replyId;
		}
	}
});