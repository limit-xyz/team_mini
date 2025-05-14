<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!--  content -->





<!--  검색 요청 -->
<c:if test ="${searchOption }">
<div class ="row">
	<div class ="col text-conter">
		"${keyword }" 검색 결과
	</div>
</div>
	<div class="row">
					<div class="col">
						<a href="adoptionList.mvc" class="btn btn-outline-success">리스트</a>
					</div>
					<div class="col text-end">
						<a href="adoptionWrite.mvc" class="btn btn-outline-success">글쓰기</a>
					</div>
				</div>
</c:if>
		<%-- 검색 요청이 아닌 경우  --%>
				<c:if test="${not searchOption}">
				<div class="row">
					<div class="col text-end">
						<a href="adoptionWrite.mvc" class="btn btn-outline-success">글쓰기</a>
					</div>
				</div>
				</c:if>
				<div class="row my-3">
					<div class="col">
						<table class="table table-hover">
							<thead class="table-dark">
								<tr>
									<th>no</th>
									<th>진행상황</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody class="text-secondary">

	<%-- 검색 요청이면서 게시글이 있는 경우 --%>
		<c:if test="${searchOption and not empty bList}">
				<c:forEach var="adopboard" items="${ bList }">
					<tr>
						<td>${adopboard.no}</td> 
						<td class="text-secondary">
							<a href="adoptionDetailList.mvc?no=${adopboard.postId}
							&pageNum=${currentPage}&type=${searchColumn}&keyword=${keyword}
							&approvalStatus=${approvalStatus}" 
							class="text-secondary text-decoration-none">${adopboard.title}</a>
							</td>
						<td class="text-secondary">${adopboard.userid}</td>
						<td>${adopboard.created_at}</td>
						<td>${adopboard.views}</td>
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
						    		href="boardList.mvc?pageNum=${startPage - pageGroup}&type=${searchColumn}&keyword=${keyword}">Pre</a>
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
   		<a class="page-link" href="boardList.mvc?pageNum=${i}&type=${searchColumn}&keyword=${keyword}">${i}</a>
   	</li>
 		</c:if>
   </c:forEach>
   
			    <c:if test="${endPage < pageCount}">						    
				    <li class="page-item">
				    	<a class="page-link" 
				    		href="boardList.mvc?pageNum=${startPage + pageGroup}&type=${searchColumn}&keyword=${keyword}">Next</a>
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
						    		href="boardList.mvc?pageNum=${startPage - pageGroup}">Pre</a>
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
						    		<a class="page-link" href="boardList.mvc?pageNum=${i}">${i}</a>
						    	</li>
						    	</c:if>
						    </c:forEach>
						    <c:if test="${endPage < pageCount}">						    
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="boardList.mvc?pageNum=${startPage + pageGroup}">Next</a>
						    </li>
						    </c:if>
						  </ul>
						</nav>
					</div>
				</div>
				</c:if>
		</div>
	</div>		
					
					
					

<h2>입양 분양 게시판</h2>
		<table>
			<tr>
				<th>no</th>
				<th>진행상황</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="adopboard" items="${bList}">
				<tr>
					<th>${adopboard.postId }</th>
					<th>${adopboard.approval_status }</th>
					<th>${adopboard.title}</th>
					<th>${adopboard.userid}</th>
					<th>${adopboard.created_at}</th>
					<th>${adopboard.views }조회수</th>
				</tr>
				</c:forEach>
		</table>
</body>
</html> 