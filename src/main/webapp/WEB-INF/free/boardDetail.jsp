<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${pageContext.request.contextPath}/js/reply.js"></script>

		<!-- content -->			
		<div class="row my-5" id="global-content">
			<div class="col">
				<form name="checkForm" id="checkForm">
					<input type="hidden" name="no" value="${board.no}" id="no">
					<input type="hidden" name="pass" id="rPass">
					<input type="hidden" name="pageNum" value="${pageNum}">
					<c:if test="${searchOption}">
						<input type="hidden" name="type" value="${type}">
						<input type="hidden" name="keyword" value="${keyword}">						
					</c:if>
				</form>
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
									<td colspan="3">${board.title}</td>
								</tr>
								<tr>
									<th>작성자</th>
									<td>${board.writer}</td>
									<th>작성일</th>
									<td><fmt:formatDate value="${board.regDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
								<tr>
									<th>비밀번호</th>
									<td><input class="form-control" type="password" name="pass" id="pass" ></td>
									<th>조회수</th>
									<td>${board.readCount}</td>
								</tr>
								<tr>
									<th>파 일</th>
									<td colspan="3">
										<c:if test="${empty board.file1}">
											첨부파일 없음
										</c:if>								
										<c:if test="${not empty board.file1}">
											<a href="${pageContext.request.contextPath}/free/fileDownload?fileName=${board.file1}">파일 다운로드</a>								
										</c:if>
									</td>
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
						<input type="button" class="btn btn-primary" id="detailUpdate" value="수정하기">				
						<input type="button" class="btn btn-danger ms-2 me-2" id="detailDelete" value="삭제하기">				
						<!-- 검색 리스트에서 넘어온 경우 다시 검색 리스트로 보내야 함 -->						
						<c:if test="${searchOption}">
							<c:if test="${not empty boardType}">
								<input type="button" class="btn btn-warning" value="목록보기"
									onclick="location.href='${pageContext.request.contextPath}/member/mypage/boardList?pageNum=${pageNum}&searchBoardType=${type}&searchBoardKeyword=${keyword}'">
							</c:if>
							
							<c:if test="${empty boardType}">
								<input type="button" class="btn btn-warning" value="목록보기"
								onclick="location.href='${pageContext.request.contextPath}/free/boardList?pageNum=${pageNum}&type=${type}&keyword=${keyword}'">
							</c:if>

						</c:if>	
						<!-- 일반 리스트에서 넘어온 경우 다시 일반 리스트로 보내야함 -->
						<c:if test="${not searchOption}">
							<c:if test="${not empty boardType}">
								<input type="button" class="btn btn-warning" value="목록보기"
									onclick="location.href='${pageContext.request.contextPath}/member/mypage/boardList?pageNum=${pageNum}&searchBoardType=${type}&searchBoardKeyword=${keyword}'">
							</c:if>
							
							<c:if test="${empty boardType}">				
								<input type="button" class="btn btn-warning" value="목록보기"
									onclick="location.href='${pageContext.request.contextPath}/free/boardList?pageNum=${pageNum}'">
							</c:if>
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
										<button class="btn btn-outline-danger btn-sm"
											onclick="reportReply('${reply.no}')">
											<i class="bi bi-telephone-outbound"> 신고</i></button>
									</div>
								</div>
								<div class="row">
									<div class="col p-3">
										<pre>${reply.replyContent }</pre>
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
						<form name="replyWriteForm" id="replyWriteForm" action="#">
							<input type="hidden" name="bbsNo" value="${board.no}" >
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

