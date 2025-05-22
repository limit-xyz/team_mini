<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content -->
<div class="row">
	<div class="col">
	
		<div style="visibility: hidden;">
			<p id="pageNum">${pageNum}</p>
			<p id="searchBoardTypePara">${searchBoardType}</p>
			<p id="searchBoardKeywordPara">${searchBoardKeyword}</p>
		</div>
	
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">나의 작성글 리스트</h2>
			</div>
		</div>  
	
		<table class="table table-bordered table-hover my-3">
			<thead>
				<tr class="table-dark text-center">
					<th>번호</th>
					<th>게시판</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>		
			</thead>
			
			
			<tbody class="text-secondary" id="tableBody">
				<c:if test="${ not empty boardList }">
					<c:forEach var="board" items="${boardList}" varStatus="status">
						<tr class="myBoardDetail" data-board-no="${board.no}" data-board-type="${board.type}" style="cursor: pointer;">
							<td class="text-center">${ ((pageNum-1)*10) + status.index + 1 }</td>
							
							<td class="text-center">${ board.type }</td>
							
							<td>${ board.title }</td>
							
							<td class="text-center">${ board.writer }</td>
							
							<td class="text-end">
								<fmt:formatDate value="${board.regDate}" pattern="yyyy/MM/dd HH:mm:ss" />
							</td>
							
							<td class="text-center">${ board.readCount }</td>
						</tr>
					</c:forEach>
				</c:if>							

				<c:if test="${ empty boardList }">
					<tr>
						<c:if test="${searchBoardOption == '1'}">
							<td colspan="12" class="text-center">검색 결과가 존재하지 않음</td>
						</c:if>
						
						<c:if test="${not searchBoardOption == '1'}">
							<td colspan="12" class="text-center">작성한 게시글이 존재하지 않음</td>
						</c:if>
					</tr>
				</c:if>
				
			</tbody>
			
		</table>
		
		<div class="row">
			<div class="col text-center">
				<a href="${pageContext.request.contextPath}/member/mypage/main">돌아가기</a>
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
			      href="diaryList?pageNum=${ startPage - PAGE_GROUP }&searchBoardType=${searchBoardType}&searchBoardKeyword=${searchBoardKeyword}">Prev</a>
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
			    		<a class="page-link" href="diaryList?pageNum=${i}&searchBoardType=${searchBoardType}&searchBoardKeyword=${searchBoardKeyword}">${i}</a>
			    	</li>
			    </c:if>					    
		    </c:forEach>							    

			<c:if test="${ endPage < pageCount }">
			    <li class="page-item">
			      <a class="page-link"
			      href="diaryList?pageNum=${startPage + PAGE_GROUP}&searchBoardType=${searchBoardType}&searchBoardKeyword=${searchBoardKeyword}">Next</a>
			    </li>
		  	</c:if>
		  </ul>
		</nav>
	</div>
</div>

<div class="row my-1">
	<div class="col">
		<form name="searchBoardForm" id="searchBoardForm"  action="boardList"
			class="row justify-content-center my-2 ">
			<div class="col-auto">
				<select name="searchBoardType" class="form-select">
					<option value="title" ${searchBoardType == 'title' ? "selected" : ""}>제목</option>
					<option value="content" ${searchBoardType == 'content' ? "selected" : ""}>내용</option>
				</select>
			</div>
			<div class="col-4">
				<input type="text" name="searchBoardKeyword" class="form-control" id="searchBoardKeyword" value="${searchBoardKeyword}"/>
			</div>
			<div class="col-auto">
				<input type="submit" value="검 색" class="btn btn-primary"/>
			</div>
		</form>	
	</div>
</div>

<script src="${pageContext.request.contextPath}/js/mypage.js"></script>
<!-- Content End -->