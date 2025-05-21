<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content -->
<div class="row">
	<div class="col">
	
		<div style="visibility: hidden;">
			<p id="pageNum">${pageNum}</p>
			<p id="searchDictionaryTypePara">${searchDictionaryType}</p>
			<p id="searchDictionaryKeyword">${searchDictionaryKeyword}</p>
		</div>
	
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">펫과사전</h2>
			</div>
		</div>  
		
		<c:if test="${isExpert}">
			<div class="row my-3">
				<div class="col text-end">
					<a class="btn btn-outline-success"
					href="dictionaryWriteForm?pageNum=${pageNum}&searchDictionaryType=${searchDictionaryType}&searchDictionaryKeyword=${searchDictionaryKeyword}">글쓰기</a>
				</div>
			</div>
		</c:if>
	
		<table class="table table-bordered table-hover my-3">
		
			<thead>
				<tr class="table-dark text-center">
					<th>번호</th>
					<th>종류</th>
					<th>이름</th>
					<th>영문명</th>
					<th>출신지</th>
					<th>키</th>
					<th>무게</th>
				</tr>		
			</thead>
			
			
			<tbody class="text-secondary" id="tableBody">
				<c:if test="${ not empty dictionaryList }">
					<c:forEach var="dictionary" items="${dictionaryList}" varStatus="status">
						<tr class="dictionaryDetail text-center" style="cursor: pointer;"
						 data-animal-type="${dictionary.type}"  data-animal-id="${dictionary.id}">
							<td>${ ((pageNum-1)*10) + status.index + 1 }</td>
							<td>${ dictionary.type }</td>
							<td>${ dictionary.nameKor }</td>
							<td>${ dictionary.nameEng }</td>
							<td>${ dictionary.origin }</td>
							<td>${ dictionary.height }</td>
							<td>${ dictionary.weight }</td>		
						</tr>
					</c:forEach>
				</c:if>							

				<c:if test="${ empty dictionaryList }">
					<tr>
						<c:if test="${searchDictionaryOption == '1'}">
							<td colspan="12" class="text-center">검색 결과가 존재하지 않음</td>
						</c:if>
						
						<c:if test="${not searchDictionaryOption == '1'}">
							<td colspan="12" class="text-center">다이어리가 존재하지 않음</td>
						</c:if>
					</tr>
				</c:if>
			</tbody>
		</table>		
	</div>
</div>


<div class="row">
	<div class="col">
		<nav aria-label="Page navigation">
		  <ul class="pagination justify-content-center">
		  
		  	<c:if test="${ startPage > PAGE_GROUP }">
			    <li class="page-item">
			      <a class="page-link"
			      href="dictionaryList?pageNum=${ startPage - PAGE_GROUP }&searchDiaryType=${searchDiaryType}&searchDiaryKeyword=${searchDiaryKeyword}">Prev</a>
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
			    		<a class="page-link"
			    		href="dictionaryList?pageNum=${i}&searchDictionaryType=${searchDictionaryType}&searchDictionaryKeyword=${searchDictionaryKeyword}">${i}</a>
			    	</li>
			    </c:if>					    
		    </c:forEach>							    

			<c:if test="${ endPage < pageCount }">
			    <li class="page-item">
			      <a class="page-link"
			      href="dictionaryList?pageNum=${startPage + PAGE_GROUP}&searchDictionaryType=${searchDictionaryType}&searchDictionaryKeyword=${searchDictionaryKeyword}">Next</a>
			    </li>
		  	</c:if>
		  </ul>
		</nav>
	</div>
</div>

<div class="row my-1">
	<div class="col">
		<form name="searchDirectoryForm" id="searchDirectoryForm"  action="dictionaryList"
			class="row justify-content-center my-2 ">
			<div class="col-auto">
				<select name="searchDictionaryType" class="form-select">
					<option value="name_ko" ${searchDictionaryType == 'name_ko' ? "selected" : ""}>이름</option>
					<option value="name_en" ${searchDictionaryType == 'name_en' ? "selected" : ""}>영문명</option>
					<option value="type" ${searchDictionaryType == 'type' ? "selected" : ""}>종류</option>
				</select>
			</div>
			<div class="col-4">
				<input type="text" name="searchDictionaryKeyword" class="form-control" id="searchDictionaryKeyword" value="${searchDictionaryKeyword}"/>
			</div>
			<div class="col-auto">
				<input type="submit" value="검 색" class="btn btn-primary"/>
			</div>
		</form>	
	</div>
</div>


<script src="${pageContext.request.contextPath}/js/dictionary.js"></script>
<!-- Content End -->