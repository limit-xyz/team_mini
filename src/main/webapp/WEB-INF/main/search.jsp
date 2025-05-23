<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- content -->			
<div class="row my-5" id="global-content">
	
	<div class="col">		
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">"${keyword}" 검색 결과</h2>			
			</div>			
		</div>		
		
		<div class="row my-3">
			<div class="col">
				<table class="table table-hover">
					<thead class="table-success">
						<tr>
							<th>게시판</th>
							<th>제목</th>
							<th>내용</th>
							<th>작성자</th>
						</tr>
					</thead>
					
					<tbody class="text-secondary">
					<%-- 검색 요청이면서 게시글이 있는 경우 --%>
						<c:if test="${not empty searchList}">
							<c:forEach var="board" items="${ searchList }">
								<tr class="boardDetail" data-board-no="${board.no}" data-board-type="${board.type}" style="cursor: pointer;">
									<td>
										<c:choose>
										    <c:when test="${board.type == 'free'}">
										        자유게시판
										    </c:when>
										    <c:when test="${board.type == 'adoption'}">
										        입양/분양 게시판
										    </c:when>
										    <c:when test="${board.type == 'dog'}">
										        펫과사전
										    </c:when>
										    <c:when test="${board.type == 'cat'}">
										        펫과사전
										    </c:when>
										    <c:when test="${board.type == 'faq'}">
										        FAQ
										    </c:when>
										    <c:when test="${board.type == 'qna'}">
										        문의 게시판
										    </c:when>
										    <c:otherwise>
										        기타
										    </c:otherwise>
										</c:choose>
									</td>
									
									<td>${board.title}</td>
									
									<td>${board.content}</td>
									
									<td>${board.writer}</td>
								</tr>
							</c:forEach>
						</c:if>

						<c:if test="${empty searchList}">
							<tr>
								<td colspan="5" class="text-center">
									"${keyword}"가 포함된 게시 글이 존재하지 않음
								</td>
							</tr>
						</c:if>
				
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/search.js"></script>
