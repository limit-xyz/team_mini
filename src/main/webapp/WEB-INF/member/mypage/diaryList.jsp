<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content -->
<div class="row">
	<div class="col">
	
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">나의 다이어리 리스트</h2>
			</div>
		</div>  
		
		<div class="row my-3">
			<div class="col text-end">
				<a href="diaryWriteForm" class="btn btn-outline-success">글쓰기</a>
			</div>
		</div>
	
		<table class="table table-bordered table-hover my-3">
		
			<thead>
				<tr class="table-dark text-center">
					<th>번호</th>
					<th>작성자</th>
					<th>반려동물명</th>
					<th>제목</th>
					<th>작성일</th>
				</tr>		
			</thead>
			
			
			<tbody class="text-secondary" id="tableBody">
				<c:if test="${ not empty diaryList }">
					<c:forEach var="diary" items="${diaryList}" varStatus="status">
						<tr class="diaryDetail" data-diary-no="${diary.no}" style="cursor: pointer;">
							<td class="text-center">${ status.index + 1 }</td>
							
							<td class="text-center">${ diary.memberId }</td>
							
							<td class="text-center">${ diary.petName }</td>
							
							<td>${ diary.title }</td>
							
							<td class="text-end">
								<fmt:formatDate value="${diary.regDate}" pattern="yyyy/MM/dd" />
							</td>
							
						</tr>
					</c:forEach>
				</c:if>							

				<c:if test="${ empty diaryList }">
					<tr>
						<td colspan="5" class="text-center">다이어리가 존재하지 않음</td>
					</tr>
				</c:if>
				
			</tbody>
			
		</table>
		
		<div class="row">
			<div class="col text-center">
				<a href="${pageContext.request.contextPath}/admin/main">돌아가기</a>
			</div>
		</div>
		
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/diary.js"></script>
<!-- Content End -->