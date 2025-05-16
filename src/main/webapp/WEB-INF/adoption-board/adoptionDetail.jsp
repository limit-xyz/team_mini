<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miniproject.dao.AdoptionWriteDto" %>
<jsp:useBean id="post" class="com.miniproject.dao.AdoptionWriteDto" scope="request"/>


<!-- content -->
<div class="row my-5" id="global-content">
	<div class="col">
		<form name ="checkForm" id="checkForm">
			<input type="hidden" name="postId" value="${adopboard.PostId}" id="postId">
			<input type="hidden" name="userId" value="${adopboard.UserId}" id="userId">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<c:if test="${searchOption}">
				<input type="hidden" name="searchColumn" value="${searchColumn}">
				<input type="hidden" name="keyword" value="${keyword}">
				<input type="hidden" name="approvalStatus" value="${approvalStatus}">
			</c:if>				
		</form>
		<div class ="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">게시 글 상세보기</h2>
			</div>
		</div>
		
			<div class="row">
				<div class="col">
				<table class ="table table-bordered">
				<tbody>
					<tr>
						<th class="table-secondary"> 제 목</th>
						<td colspan = "3">${adopboard.title }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${adopboard.userId }</td>
						<th>작성일</th>
						<td><fmt:formatDate value="${adopboard.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
					<tr>
						<th>조회수</th> 
						<td>${adopboard.viewsCount }</td>
					</tr>
					<tr>
					<th>파 일</th>
					<td colspan="3">
					<c:if test="${empty adopboard.imagePath }">
					첨부파일 없음
					</c:if>
					<c:if test"${not empty imagePath}">
					<a href="imagePathDownload.mvc?fileName=${adopboard.imagePath}">파일 다운로드</a>
					</c:if>
				</td>
				</tr>
				<tr>
				<td colspan="4">
				<pre>${adopboard.content}</pre>
				</td>
				</tr>
				</tbody>
				</table>
				</div>
			</div>
		<div class="roe my-3"> 
			<div class="col text-center">
				<input type="button" class="btn btn-primary" id="detailUpdate" value="수정하기">
				<input type="button" class="btn btn-danger ms-2 me-2" id="detailDelete" value="삭제하기">
				<!--  검색 리스트에서 넘어온 경우 다시 보내기 -->
				<c:if test="${searchOption }">
				<input type="button" class="btn btn-warning" value="목록보기"
				onclick="location.href='adoptionList.mvc?pageNum=${pageNum }'">
				</c:if>
				</div>
			</div>
			
		<!--  댓글 리스트 영역  -->
 				<!--  댓글이 존재 하는 경우  -->
 				<c:if test="${ not empty adopreplyList }">
				<div class="row">
					<div class ="col" id="replyList">
						<c:forEach var="reply" items="${adopreplyList}">
							<div class="replyRow row border border-top-0">
								<div class="col">
									<div class ="row bg-light p-2">
										<div class="col-4">
										<span>${reply.replyWriter}</span>
										</div>
										<div class="col-8 text-end p=2">
										<span class="me-3">
										<fmt:formatDate value="${adopreply.regDate }" pattern="yyyy-MM-dd : HH:mm:ss" />
										</span>
										<button class="modifyReply btn btn-outline-success btn-sm" data-no='${adopreply.no }'><i class="bi bi-file-text"> 수정</i></button>
										<button class="deleteReply btn btn-outline-warning btn-sm" data-no='${adopreply.no }'><i class="bi bi-trash-fill"> 삭제</i></button>
										<button class="btn btn-outline-danger btn-sm"
											onclick ="reportReply('${adopreply.no}')"><i class="bi bi-exclamation-triangle-fill"> 신고</i></button>
										</div>
									</div>
									<div class ="row">
										<div class ="col p-3">
											<pre>${adopreply.replyContent }</pre>
										</div>
									</div>
								</div>
							</div>
							
							</c:forEach>
					</div>
				</div>
				</c:if>
				<!--  댓글이 존재 하지 않을 경우   -->
				<c:if test="${ empty adopreplyList }">
				<div class="row my-5">
					<div class ="col border p-5 text-center">
						 이 게시글에 대한 댓글이 존재 하지 않음
					</div>
				</div>
				</c:if>
										
										
				<!--  댓글 쓰기 폼 -->
				<div class="row my-5 d-none" id="replyForm">
					<div class ="col">
						 <form name ="replyWriteForm" id ="replyWriteForm">
						 	<input type="hidden" name = "bbsNo" value="${adopboard.no }">
						 	<input type="hidden" name = "replyWriter" value="${sessionScope.id }">
						 	<div class="row bg-light border my-3 p-3">
						 		<div class="col">
								 	<div class="row">
								 		<div class="col text-center">
											<span>악의적인 댓글은 예고없이 삭제처리 될수 있습니다.</span>
									 </div>
									</div>
						 		<div class="row my-3">
								 	<div class="col-10">
								 	<textarea name="replyContent" id="replyContent" class="form-control" rows="4"></textarea>
									</div>
								 	<div class="col-2">
								 	<input type="submit" value="댓글쓰기" 
								 		class="btn btn-primary w-100 h-100" id="replyWriterButton">
									</div>
						 		</div>
						 	</div>
						 </form>
					</div>
				</div>
		
		
		</div>
</div>


