<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content -->
<div class="row">
	<div class="col">
	
		<div style="visibility: hidden;">
			<p id="pageNum">${pageNum}</p>
			<p id="searchDiaryTypePara">${searchDiaryType}</p>
			<p id="searchDiaryKeywordPara">${searchDiaryKeyword}</p>
		</div>
	
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">나의 다이어리 리스트</h2>
			</div>
		</div>  
		
		<div class="row my-3">
			<div class="col text-end">
				<a class="btn btn-outline-success"
				href="diaryWriteForm?pageNum=${pageNum}&searchDiaryType=${searchDiaryType}&searchDiaryKeyword=${searchDiaryKeyword}">글쓰기</a>
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
						<c:if test="${searchDiaryOption == '1'}">
							<td colspan="12" class="text-center">검색 결과가 존재하지 않음</td>
						</c:if>
						
						<c:if test="${not searchDiaryOption == '1'}">
							<td colspan="12" class="text-center">다이어리가 존재하지 않음</td>
						</c:if>
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
			      <a class="page-link"
			      href="diaryList?pageNum=${ startPage - PAGE_GROUP }&searchDiaryType=${searchDiaryType}&searchDiaryKeyword=${searchDiaryKeyword}">Prev</a>
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
			    		<a class="page-link" href="diaryList?pageNum=${i}&searchDiaryType=${searchDiaryType}&searchDiaryKeyword=${searchDiaryKeyword}">${i}</a>
			    	</li>
			    </c:if>					    
		    </c:forEach>							    

			<c:if test="${ endPage < pageCount }">
			    <li class="page-item">
			      <a class="page-link"
			      href="diaryList?pageNum=${startPage + PAGE_GROUP}&searchDiaryType=${searchDiaryType}&searchDiaryKeyword=${searchDiaryKeyword}">Next</a>
			    </li>
		  	</c:if>
		  </ul>
		</nav>
	</div>
</div>

<div class="row my-1">
	<div class="col">
		<form name="searchDiaryForm" id="searchDiaryForm"  action="diaryList"
			class="row justify-content-center my-2 ">
			<div class="col-auto">
				<select name="searchDiaryType" class="form-select">
					<option value="title" ${searchDiaryType == 'title' ? "selected" : ""}>제목</option>
					<option value="content" ${searchDiaryType == 'content' ? "selected" : ""}>내용</option>
					<option value="pet_name" ${searchDiaryType == 'pet_name' ? "selected" : ""}>반려동물명</option>
				</select>
			</div>
			<div class="col-4">
				<input type="text" name="searchDiaryKeyword" class="form-control" id="searchDiaryKeyword" value="${searchDiaryKeyword}"/>
			</div>
			<div class="col-auto">
				<input type="submit" value="검 색" class="btn btn-primary"/>
			</div>
		</form>	
	</div>
</div>

<script src="${pageContext.request.contextPath}/js/diary.js"></script>
<!-- Content End -->