<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- content -->			
<div class="row my-5" id="global-content">

	<div style="visibility: hidden;">
		<p id="pageNum">${currentPage}</p>
		<p id="searchTypePara">${type}</p>
		<p id="searchKeywordPara">${keyword}</p>
	</div>
	
	<div class="col">		
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">자유게시판</h2>			
			</div>			
		</div>		
		<form name="searchForm" id="searchForm" action="${pageContext.request.contextPath}/free/freeList" method="post"
			class="row my-3 text-center justify-content-center">
			<div class="col-auto">
				<select name="type" class="form-select">				 
					<option value="title">제목</option>
					<option value="writer">작성자</option>
					<option value="content">내용</option>
				</select>
			</div>
			<div class="col-4">
				<input type="text" name="keyword" class="form-control" id="keyword">
			</div>
			<div class="col-auto">
				<input type="submit" class="btn btn-primary" value="검색">
			</div>
		</form>
		
		<%-- 검색 요청인 경우  --%>
		<c:if test="${searchOption}">
			<div class="row">
				<div class="col text-center">
					"${ keyword }" 검색 결과
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/free/freeList" class="btn btn-outline-success">리스트</a>
				</div>
				<div class="col text-end">
					<a href="writeForm?pageNum=${currentPage}&type=${type}&keyword=${keyword}" class="btn btn-outline-success">글쓰기</a>
				</div>
			</div>				
		</c:if>
		
		<%-- 검색 요청이 아닌 경우  --%>
		<c:if test="${not searchOption}">
			<div class="row">
				<div class="col text-end">
					<a href="writeForm?pageNum=${currentPage}&type=${type}&keyword=${keyword}" class="btn btn-outline-success">글쓰기</a>
				</div>
			</div>
		</c:if>
		
		<div class="row my-3">
			<div class="col">
				<table class="table table-hover">
					<thead class="table-success">
						<tr>
							<th>no</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					
					<tbody class="text-secondary">
					<%-- 검색 요청이면서 게시글이 있는 경우 --%>
						<c:if test="${searchOption and not empty bList}">
							<c:forEach var="board" items="${ bList }">
								<tr class="freeDetail" data-board-no="${board.no}" style="cursor: pointer;">
									<td>${board.no}</td> 
									<td>${board.title}</td>
									<td>${board.writer}</td>
									<td>${board.regDate}</td>
									<td>${board.readCount}</td>
								</tr>
							</c:forEach>
						</c:if>
						
						<%-- 일반 게시 글 리스트 요청이면서 게시 글이 있는 경우 --%>
						<c:if test="${not searchOption and not empty bList}">
							<c:forEach var="board" items="${ bList }">
								<tr class="freeDetail" data-board-no="${board.no}" style="cursor: pointer;">
									<td>${board.no}</td> 
									<td>${board.title}</td>
									<td>${board.writer}</td>
									<td>${board.regDate}</td>
									<td>${board.readCount}</td>
								</tr>
							</c:forEach>
						</c:if>
						
						<!-- 검색 게시 글 리스트 이면서 게시글이 없는 경우 -->
						<c:if test="${searchOption and empty bList}">
							<tr>
								<td colspan="5" class="text-center">
									"${keyword}"가 포함된 게시 글이 존재하지 않음
								</td>
							</tr>
						</c:if>
					
						<!-- 일반 게시 글 리스트 이면서 게시글이 없는 경우 -->
						<c:if test="${not searchOption and empty bList}">
							<tr>
								<td colspan="5" class="text-center">게시 글이 존재하지 않음</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 검색 요청이면서 검색된 리스트가 존재할 경우 페이지네이션 -->
		<c:if test="${ searchOption and not empty bList }">
			<div class="row">
				<div class="col">
					<nav aria-label="Page navigation example">
					  <ul class="pagination justify-content-center">
					    <c:if test="${startPage > pageGroup}">
					    <li class="page-item">
					    	<a class="page-link" 
					    		href="${pageContext.request.contextPath}/free/freeList?pageNum=${startPage - pageGroup}&type=${type}&keyword=${keyword}">Pre</a>
					    </li>
					    </c:if>
					    <c:forEach var="i" begin="${startPage}" end="${endPage}">
					    	<%-- 현재 페이지 - 링크x active --%>
					    	<c:if test="${i == currentPage}">
					    	<li class="page-item">
					    		<span class="page-link active">${i}</span>
					    	</li>
					    	</c:if>
					    	<c:if test="${i != currentPage}">
					    	<li class="page-item">
					    		<a class="page-link" href="${pageContext.request.contextPath}/free/freeList?pageNum=${i}&type=${type}&keyword=${keyword}">${i}</a>
					    	</li>
					    	</c:if>
					    </c:forEach>
					    <c:if test="${endPage < pageCount}">						    
					    <li class="page-item">
					    	<a class="page-link" 
					    		href="${pageContext.request.contextPath}/free/freeList?pageNum=${startPage + pageGroup}&type=${type}&keyword=${keyword}">Next</a>
					    </li>
					    </c:if>
					  </ul>
					</nav>
				</div>
			</div>
		</c:if>
		
		<!-- 일반 요청이면서 검색된 리스트가 존재할 경우 페이지네이션 -->
		<c:if test="${ not searchOption and not empty bList }">
			<div class="row">
				<div class="col">
					<nav aria-label="Page navigation example">
					  <ul class="pagination justify-content-center">
					    <c:if test="${startPage > pageGroup}">
					    <li class="page-item">
					    	<a class="page-link" 
					    		href="${pageContext.request.contextPath}/free/freeList?pageNum=${startPage - pageGroup}">Pre</a>
					    </li>
					    </c:if>
					    <c:forEach var="i" begin="${startPage}" end="${endPage}">
					    	<%-- 현재 페이지 - 링크x active --%>
					    	<c:if test="${i == currentPage}">
					    	<li class="page-item">
					    		<span class="page-link active">${i}</span>
					    	</li>
					    	</c:if>
					    	<c:if test="${i != currentPage}">
					    	<li class="page-item">
					    		<a class="page-link" href="${pageContext.request.contextPath}/free/freeList?pageNum=${i}">${i}</a>
					    	</li>
					    	</c:if>
					    </c:forEach>
					    <c:if test="${endPage < pageCount}">						    
					    <li class="page-item">
					    	<a class="page-link" 
					    		href="${pageContext.request.contextPath}/free/freeList?pageNum=${startPage + pageGroup}">Next</a>
					    </li>
					    </c:if>
					  </ul>
					</nav>
				</div>
			</div>
		</c:if>
	</div>
</div>	
<script src="${pageContext.request.contextPath}/js/free.js"></script>