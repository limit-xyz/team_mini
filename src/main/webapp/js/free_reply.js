$(function() { // DOM이 준비되면
	
	// 댓글 수정 폼이 전송될 때 - 이벤트 위임 방식으로 리스너 적용
	$(document).on("click",".deleteReply", function(e) {
		console.log("deleteReply")
		let no=$(this).attr("data-no")
		let boardNo = $("#replyForm input[name=boardNo]").val();
		
		let params = "replyNo=" + no +"&boardNo=" + boardNo; 
		console.log(params);
		
		let writer=$(this).parent().prev().find("span").text();
		
		// 현재 폼을 백업 - 댓글 쓰고 날아가기 전에  
		let $replyForm = $("#replyForm").slideUp(300)
		
		let result = confirm(writer + "님의 작성한" + no +"번 댓글을 삭제할까요?")
		
		if(result){
		
		$.ajax({
			url: "replyDelete.ajax",
			data: params,
			dataType: "json",
			type: "post",
			"success": function(resData) {
				// resData = jQuery 응답 데이터를 처리해서 넣어주는 데이터
				// resData => 댓글 리스트 = [{no:20, boardNo: 200, replyTitle: "" ,...}, {}, ...]
				console.log(resData);
				//$.each(Array, function(index, value) {});
				//$(".replyRow").each(function(index, value) {});
				
				// --- 여기를 추가하세요 ---
				       console.log("서버로부터 받은 데이터 (resData):");
				       console.log(JSON.stringify(resData, null, 2)); // JSON 형태로 예쁘게 출력
				       // --- 여기까지 ---
				$("#replyList").empty();
				$.each(resData, function(i, v) {
					// console.log(i, v);			
					// 작성일 데이터 처리 - May 8, 2025, 3:40:37 PM => 2025-05-08 15:40:08
					let date = new Date(v.regDate);
					let strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
						? "0" + (date.getMonth() + 1) : date.getMonth() + 1) + "-"
						+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
						+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":" 
						+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":" 
						+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds()); 
							
					// 하나의 댓글 요소를 출력					
					let result =
									'<div class="replyRow row border border-top-0">'
								+	'	<div class="col">'
								+	'		<div class="row bg-light p-2">'
								+ '			<div class="col-4">'
								+ `				<span>${v.replyWriter}</span>`
								+ '			</div>'
								+ '			<div class="col-8 text-end p-2">'
								+ '				<span class="me-3">'
								+ `					${strDate}`
								+ '				</span>'
								+ '				<button class="modifyReply btn btn-outline-success btn-sm"'
								+ '					data-no="' + v.no + '"><i class="bi bi-file-text"> 수정</i></button>'									
								+ '				<button class="deleteReply btn btn-outline-warning btn-sm"'
								+ `					data-no="${v.no}"><i class="bi bi-trash"> 삭제</i></button>`
								+ '				<button class="btn btn-outline-danger btn-sm"'
								+ `					onclick="reportReply('${v.no}')">`
								+ '					<i class="bi bi-telephone-outbound"> 신고</i></button>'
								+ '			</div>'
								+ '			</div>'
								+ '		<div class="row">'
								+ '		<div class="col p-3">'
								+ '			<pre>' + v.replyContent + '</pre>'
								+ '		</div>'
								+ '	</div>'							
								+ '</div>'
							+ '</div>'
							
						$("#replyList").append(result);						
					
				}); // end $.each(resData, function(i, v){});
				

				$("#replyContent").val("");
				$("#global-content > div.col").append($replyForm)
				
			}, // end "success": function(resData)
			error: function(xhr) {
				console.log("error : ", xhr)
			} 
		});
		}
		//아니오 누르면 아무동작도 안함
		
	}); // end $("#replyUpdateForm").on("submit", function(e) {});
	
	
	// 댓글 수정 폼이 전송될 때 - 이벤트 위임 방식으로 리스너 적용
	$(document).on("submit","#replyUpdateForm", function(e) {
	
		if($("#replyContent").val().length < 5) {
			alert("댓글은 5자 이상 입력...");
		} 
		// 데이터를 준비해서 서버로 보내야 함	
		let params = $(this).serialize() + "&replyNo=" + $(this).attr("data-no"); 
		console.log(params);
		
		// 현재 폼을 백업 - 댓글 쓰고 날아가기 전에  
		let $replyForm = $("#replyForm").slideUp(300)
		
		setTimeout(function(){
		$.ajax({
			url: "replyUpdate.ajax",
			data: params,
			dataType: "json",
			type: "post",
			"success": function(resData) {
				// resData = jQuery 응답 데이터를 처리해서 넣어주는 데이터
				// resData => 댓글 리스트 = [{no:20, boardNo: 200, replyTitle: "" ,...}, {}, ...]
				console.log(resData);
				//$.each(Array, function(index, value) {});
				//$(".replyRow").each(function(index, value) {});
				$("#replyList").empty();
				$.each(resData, function(i, v) {
					// console.log(i, v);			
					// 작성일 데이터 처리 - May 8, 2025, 3:40:37 PM => 2025-05-08 15:40:08
					let date = new Date(v.regDate);
					let strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
						? "0" + (date.getMonth() + 1) : date.getMonth() + 1) + "-"
						+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
						+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":" 
						+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":" 
						+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds()); 
							
					// 하나의 댓글 요소를 출력					
					let result =
									'<div class="replyRow row border border-top-0">'
								+	'	<div class="col">'
								+	'		<div class="row bg-light p-2">'
								+ '			<div class="col-4">'
								+ `				<span>${v.replyWriter}</span>`
								+ '			</div>'
								+ '			<div class="col-8 text-end p-2">'
								+ '				<span class="me-3">'
								+ `					${strDate}`
								+ '				</span>'
								+ '				<button class="modifyReply btn btn-outline-success btn-sm"'
								+ '					data-no="' + v.no + '"><i class="bi bi-file-text"> 수정</i></button>'									
								+ '				<button class="deleteReply btn btn-outline-warning btn-sm"'
								+ `					data-no="${v.no}"><i class="bi bi-trash"> 삭제</i></button>`
								+ '				<button class="btn btn-outline-danger btn-sm"'
								+ `					onclick="reportReply('${v.no}')">`
								+ '					<i class="bi bi-telephone-outbound"> 신고</i></button>'
								+ '			</div>'
								+ '			</div>'
								+ '		<div class="row">'
								+ '		<div class="col p-3">'
								+ '			<pre>' + v.replyContent + '</pre>'
								+ '		</div>'
								+ '	</div>'							
								+ '</div>'
							+ '</div>'
							
						$("#replyList").append(result);						
					
				}); // end $.each(resData, function(i, v){});
				

				$("#replyContent").val("");
				$("#global-content > div.col").append($replyForm)
				
			}, // end "success": function(resData)
			error: function(xhr) {
				console.log("error : ", xhr)
			} 
		});
		},300);
		return false;			
		
	}); // end $("#replyUpdateForm").on("submit", function(e) {});
	
	// 댓글 쓰기 폼이 전송될 때
	$(document).on("submit","#replyWriteForm", function(e) {
		if($("#replyContent").val().length < 5) {
			alert("댓글은 5자 이상 입력...");
			return false;
		} 

		let params = $(this).serialize(); 
		console.log(params);
		
		$.ajax({
			url: "replyWrite.ajax",
			data: params,
			dataType: "json",
			type: "post",
			"success": function(resData) {
				$("#replyList").empty();
				$.each(resData, function(i, v) {
					let date = new Date(v.regDate);
					let strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
						? "0" + (date.getMonth() + 1) : date.getMonth() + 1) + "-"
						+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
						+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":" 
						+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":" 
						+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds()); 
							
					// 하나의 댓글 요소를 출력					
					let result =
									'<div class="replyRow row border border-top-0">'
								+	'	<div class="col">'
								+	'		<div class="row bg-light p-2">'
								+ '			<div class="col-4">'
								+ `				<span>${v.replyWriter}</span>`
								+ '			</div>'
								+ '			<div class="col-8 text-end p-2">'
								+ '				<span class="me-3">'
								+ `					${strDate}`
								+ '				</span>'
								+ '				<button class="modifyReply btn btn-outline-success btn-sm"'
								+ '					data-no="' + v.no + '"><i class="bi bi-file-text"> 수정</i></button>'									
								+ '				<button class="deleteReply btn btn-outline-warning btn-sm"'
								+ `					data-no="${v.no}"><i class="bi bi-trash"> 삭제</i></button>`
								+ '				<button class="btn btn-outline-danger btn-sm"'
								+ `					onclick="reportReply('${v.no}')">`
								+ '					<i class="bi bi-telephone-outbound"> 신고</i></button>'
								+ '			</div>'
								+ '			</div>'
								+ '		<div class="row">'
								+ '		<div class="col p-3">'
								+ '			<pre>' + v.replyContent + '</pre>'
								+ '		</div>'
								+ '	</div>'							
								+ '</div>'
							+ '</div>'
							
						$("#replyList").append(result);						
					
				}); // end $.each(resData, function(i, v){});
				
				//$("#replyForm").slideUp(300);
				//$("#replyContent").val("");
				$("#replyForm").slideUp(300)
					.add("#replyContent").val("");
				
			}, // end "success": function(resData)
			error: function(xhr) {
				console.log("error : ", xhr)
				alert("로그인이 필요합니다.");
			} 
		});
		
		return false;			
		
	}); // end $("#replyWriteForm").on("submit", function(e) {});
	
	// 수정 버튼 클릭 이벤트 처리	
	$("#replyList").on("click", ".modifyReply", function(e) {
		$replyRow = $(this).parents(".replyRow");
		
		// 폼이 보이는지 체크 - display  <div style="display: block"></div>
		if($("#replyForm").is(":visible")) { // 보이는 상태
			
			let $next = $replyRow.next();
			if($next.is("#replyForm")) {
				return;				
			}
			
			$("#replyForm").slideUp(300);
			
			setTimeout(function() {
				$("#replyForm").insertAfter($replyRow).slideDown(300);
			}, 300);
			
		} else {
			// 현재 폼이 안보일때
			$("#replyForm").insertAfter($replyRow)
				.css("display", "none").removeClass("d-none").slideDown(300);
		}	
		
		$("#replyForm").find("form")
			.attr({"id": "replyUpdateForm", "data-no": $(this).attr("data-no")});
		$("#replyWriteButton").val("댓글수정");	
		
		let reply = $(this).parent().parent().next().find("pre").text();
		$("#replyContent").val(reply);	
		
	});	
	
	// 댓글 쓰기 클릭 이벤트 처리
	$("#replyWrite").on("click", function() {		
		
		if($("#replyForm").is(":visible")) { // 보이는 상태
			
			let $prev = $("#replyTitle").prev();
			
			if($prev.is("#replyForm")) {
				return;
			}
			
			$("#replyForm").slideUp(300);
			
			setTimeout(function() {
				$("#replyForm").insertBefore("#replyTitle").slideDown(300);
			}, 300);
			
		} else {
			// 현재 폼이 안보일때
			$("#replyForm").insertBefore("#replyTitle")
					.css("display", "none").removeClass("d-none").slideDown(300);
		}		
		
		$("#replyForm").find("form")
			.attr("id", "replyWriteForm").removeAttr("data-no");			
		$("#replyWriteButton").val("댓글쓰기");		
		$("#replyContent").val("");
		
	});	
	
	$(".btnCommend").click(function(e) {
		
		let com = $(this).attr("id");
		console.log("com : " + com);
		
		$.ajax({
			url: "recommend.ajax",
			type: "post",
			data: {recommend: com, no: $("#no").text()},
			dataType: "json",
			success: function(resData) {
				let msg = com == 'commend' ? "추천이" : "땡큐가";
				$("#commend > .recommend").text("(" + resData.recommend + ")");				
				$("#thank > .recommend").text("(" + resData.thank + ")");
				alert(msg  + " 반영되었습니다.");
			},
			error: function(xhr, status, error) {
				console.log("error : ", xhr, " - ", 
						xhr.statusText, " - ", status, " - ", error);
			}
		});
		
	});
	

});

function reportReply(replyNo) {
	alert(replyNo + "번 댓글을 신고할까요?");
}