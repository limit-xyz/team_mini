<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- content -->			
<div class="row my-5" id="global-content">
	<div class="col">
	
		<div style="visibility: hidden;">
			<p id="no">${board.no}</p>
			<p id="pageNum">${pageNum}</p>
			<p id="searchType">${type}</p>
			<p id="searchKeyword">${keyword}</p>
		</div>
	
		<div class="row text-center">
			<div class="col">				
				<h2 class="fs-3 fw-bold">게시 글 상세보기</h2>
			</div>
		</div>	
		
		<div class="row">
			<div class="col">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th class="table-secondary">제 목</th>
							<td id="freeTitle" colspan="3">${board.title}</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${board.writer}</td>
							<th>작성일</th>
							<td><fmt:formatDate value="${board.regDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<th>파 일</th>
							<td>
								<c:if test="${empty board.file1}">
									첨부파일 없음
								</c:if>								
								<c:if test="${not empty board.file1}">
									<a href="${pageContext.request.contextPath}/free/fileDownload?fileName=${board.file1}">파일 다운로드</a>								
								</c:if>
							</td>
							<th>조회수</th>
							<td>${board.readCount}</td>
						</tr>

						<tr>
							<td colspan="4">
								<pre>${board.content}</pre>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>	
		
		<div class="row my-3">
			<div class="col text-center">
				<c:if test="${board.writer == sessionScope.id}">
					<input type="button" class="btn btn-primary" id="detailUpdate" value="수정하기" data-board-no="${board.no}">
				</c:if>
				
				<c:if test="${(board.writer == sessionScope.id) || sessionScope.isAdmin}">
					<input type="button" class="btn btn-danger ms-2 me-2" id="detailDelete" value="삭제하기" data-board-no="${board.no}">
				</c:if>				
				

				<c:if test="${not empty boardType}">
					<input type="button" class="btn btn-warning" value="목록보기"
						onclick="location.href='${pageContext.request.contextPath}/member/mypage/freeList?pageNum=${pageNum}&searchBoardType=${type}&searchBoardKeyword=${keyword}'">
				</c:if>
				
				<c:if test="${empty boardType}">
					<input type="button" class="btn btn-warning" value="목록보기"
					onclick="location.href='${pageContext.request.contextPath}/free/freeList?pageNum=${pageNum}&type=${type}&keyword=${keyword}'">
				</c:if>
			</div>
		</div>
		
		<!-- 추천/땡큐 영역 -->
		<div class="row my-5">
			<div class="col border p-3">
				<div id="recommend" class="text-end">
					<span id="commend" class="btnCommend text-primary" style="cursor: pointer;">
						<img src="${pageContext.request.contextPath}/images/recommend.png" alt="추천하기"> 추천
						<span class="recommend">(${board.recommend})</span>
					</span> | 
					<span id="thank" class="btnCommend text-primary" style="cursor: pointer;">
						<img src="${pageContext.request.contextPath}/images/smile.png" alt="땡큐"> 땡큐
						<span class="recommend">(${board.thank})</span>
					</span> | 
					<span id="replyWrite" class="text-primary" style="cursor: pointer;">
						<i class="bi bi-file-text-fill" style="color: cornflowerblue;"></i>
						댓글쓰기
					</span>
				</div>
			</div>				
		</div>
		
		<!-- 댓글 헤더 영역 -->
		<div class="row" id="replyTitle">
			<div class="col p-2 text-center bg-dark text-white">
				<h3 class="fs-4">이 글에 대한 댓글 리스트</h3>
			</div>				
		</div>
		
		<!-- 댓글 리스트 영역 -->
		<!-- 댓글이 존재하는 경우 -->
		<c:if test="${ not empty replyList }">
		<div class="row">
			<div class="col" id="replyList">
				<c:forEach var="reply" items="${replyList}">
				<div class="replyRow row border border-top-0">
					<div class="col">
						<div class="row bg-light p-2">
							<div class="col-4">
								<span>${ reply.replyWriter }</span>
							</div>
							<div class="col-8 text-end p-2">
								<span class="me-3">
									<fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd HH:mm:dd" />
								</span>
								<button class="modifyReply btn btn-outline-success btn-sm"
									data-no="${reply.no}"><i class="bi bi-file-text"> 수정</i></button>									
								<button class="deleteReply btn btn-outline-warning btn-sm"
									data-no="${reply.no}"><i class="bi bi-trash"> 삭제</i></button>
								<button class="reportReply btn btn-outline-danger btn-sm" data-reply-no="${reply.no}">
									<i class="bi bi-telephone-outbound"> 신고</i></button>
							</div>
						</div>
						<div class="row">
							<div class="replyContent col p-3">
								<pre data-reply-no="${reply.no}">${reply.replyContent}</pre>
						</div>
						</div>							
					</div>
				</div>
				</c:forEach>						
			</div>				
		</div>		
		</c:if>
		
		<!-- 댓글이 존재하지 않는 경우 -->
		<c:if test="${ empty replyList }">
		<div class="row my-5">
			<div class="col border p-5 text-center">
				<div>이 게시글에 대한 댓글이 존재하지 않음</div>
			</div>				
		</div>
		</c:if>	
		
		<!-- 댓글 쓰기 폼 -->
		<div class="row my-3 d-none" id="replyForm">
			<div class="col">						
				<form name="replyWriteForm" id="replyWriteForm">
					<input type="hidden" name="boardNo" value="${board.no}" >
					<input type="hidden" name="replyWriter" value="${sessionScope.id}" >
					<div class="row bg-light border my-3 p-3">
						<div class="col">
							<div class="row">
								<div class="col text-center">
									<span>악의적인 댓글은 예고 없이 삭제될 수 있으며 아무튼 잘 쓰자.</span>
								</div>
							</div>
							<div class="row my-3">
								<div class="col-10">
									<textarea name="replyContent" id="replyContent" 
										class="form-control" rows="4"></textarea>
								</div>
								<div class="col-2">
									<input type="submit" value="댓글쓰기" 
										class="btn btn-primary w-100 h-100"
										id="replyWriteButton">
								</div>
							</div>									
						</div>
					</div>
				</form>
			</div>				
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/free.js"></script>
<script src="${pageContext.request.contextPath}/js/free_reply.js"></script>