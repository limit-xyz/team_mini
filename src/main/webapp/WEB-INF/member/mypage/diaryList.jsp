<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content -->
<div class="row">
	<div class="col">
	
		<div style="visibility: hidden;">
			<p id="pageNum">${pageNum}</p>
		</div>
	
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">나의 다이어리 리스트</h2>
			</div>
		</div>  
		
		<div class="row my-3">
			<div class="col text-end">
				<a href="diaryWriteForm?pageNum=${pageNum}" class="btn btn-outline-success">글쓰기</a>
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
							<td class="text-center">${ ((pageNum-1)*10) + status.index + 1 }</td>
							
							<td class="text-center">${ diary.memberId }</td>
							
							<td class="text-center">${ diary.petName }</td>
							
							<td>${ diary.title }</td>
							
							<td class="text-end">
								<fmt:formatDate value="${diary.regDate}" pattern="yyyy/MM/dd HH:mm:ss" />
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


<div class="row">
	<div class="col">
		<nav aria-label="Page navigation">
		  <ul class="pagination justify-content-center">
		  
		  	<c:if test="${ startPage > PAGE_GROUP }">
			    <li class="page-item">
			      <a class="page-link" href="diaryList?pageNum=${ startPage - PAGE_GROUP }">Prev</a>
			    </li>
		    </c:if>
		   	
		    <c:forEach var="i" begin="${startPage}" end="${endPage}">
		    	<c:if test="${i == pageNum }">
			    	<li class="page-item active" aria-current="page">
			    		<span class="page-link">${i}</span>
			    	</li>
		    	</c:if>
		    	
		    	<c:if test="${i != pageNum }">
			    	<li class="page-item">
			    		<a class="page-link" href="diaryList?pageNum=${ i }">${i}</a>
			    	</li>
			    </c:if>					    
		    </c:forEach>							    

			<c:if test="${ endPage < pageCount }">
			    <li class="page-item">
			      <a class="page-link" href="diaryList?pageNum=${ startPage + PAGE_GROUP }">Next</a>
			    </li>
		  	</c:if>
		  </ul>
		</nav>
	</div>
</div>



<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header bg-primary bg-gradient text-white">
				<h1 class="modal-title fs-5 fw-bold" id="modalLabel">회원 차단</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>

			<form id="banMemberForm">
				<div class="modal-body">
					<div class="mb-3">
						<h5><span id="banUserId"></span> 를 차단합니다.</h5>
						<label for="banDate" class="form-label">얼마나 차단하시겠습니까?</label>
						<div class="input-group" style="width: 120px;">
							<input type="number" class="form-control" id="banDate" name="banDate">
							<span class="input-group-text">일</span>				
						</div>
						
						<label for="banReason" class="form-label">차단 사유</label>
						<textarea class="form-control" name="banReason" id="banReason" rows="3"></textarea>						
					</div>
				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary ">확인</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
				</div>
			</form>
			
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath}/js/diary.js"></script>
<!-- Content End -->