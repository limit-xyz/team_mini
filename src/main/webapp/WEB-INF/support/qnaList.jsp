<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/faq.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div id="title">문의 게시판</div>
	<div class="row">
		<div class="col">
			<div id="tab-container">
				<div class="pill-button-group">
					<button class="pill-button">
						<a href="${pageContext.request.contextPath}/support/faq">FAQ</a>
					</button>
					<button class="pill-button active">
						<a href="${pageContext.request.contextPath}/support/qnaList">문의
							게시판</a>
					</button>
					<button class="pill-button">
						<a href="${pageContext.request.contextPath}/support/reportList">신고
							게시판</a>
					</button>
				</div>
			</div>
		</div>
	</div>

	<div class="qna-content-area">
		<div class="row">
			<div class="col">
				<div class="table-responsive">
					<table class="table table-hover text-center">
						<thead>
							<tr>
								<th scope="col" style="width: 10%;">번호</th>
								<th scope="col" style="width: 15%;">답변상황</th>
								<th scope="col" style="width: 40%;" class="text-start">제목</th>
								<th scope="col" style="width: 15%;">작성자</th>
								<th scope="col" style="width: 20%;">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="qna" items="${qnaList}">
								<tr>

									<td>${qna.no }</td>
									<td><c:choose>
											<c:when test="${qna.status == '답변완료'}">
												<span
													class="badge rounded-pill status-badge status-answered">답변완료</span>
											</c:when>
											<c:when test="${qna.status == '답변대기'}">
												<span class="badge rounded-pill status-badge status-waiting">답변대기</span>
											</c:when>
										</c:choose></td>
									<td class="text-start"><a href="${pageContext.request.contextPath}/support/qnaDetail" class="qna-title-link">${qna.title }</a></td>
									<td>${qna.writer }</td>
									<td>${qna.regDate }</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="row mt-2">
			<div class="col text-end">
				<a href="${pageContext.request.contextPath}/support/faqWriteForm"
					class="btn btn-outline-success">문의하기</a>
			</div>
		</div>

		<!-- 일반 요청이면서 검색된 리스트가 존재할 경우 페이지네이션 -->
		<c:if test="${not empty qnaList }">
			<div class="row">
				<div class="col">
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center my-3">
							<c:if test="${startPage > pageGroup}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/support/qnaList?pageNum=${startPage - pageGroup}">Pre</a>
								</li>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<%-- 현재 페이지 - 링크x active --%>
								<c:if test="${i == currentPage}">
									<li class="page-item"><span class="page-link active">${i}</span>
									</li>
								</c:if>
								<c:if test="${i != currentPage}">
									<li class="page-item"><a class="page-link"
										href="${pageContext.request.contextPath}/support/qnaList?pageNum=${i}">${i}</a>
									</li>
								</c:if>
							</c:forEach>
							<c:if test="${endPage < pageCount}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/support/qnaList?pageNum=${startPage + pageGroup}">Next</a>
								</li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</c:if>
	</div>
</div>

