<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!--  content -->
<div class="row my-5" id="global-content">
	<div class="col">
		<div class="row text-center mb-4">
			<div class="col">
				<h2 class="fs-3 fw-bold">입양 게시판</h2>
			</div>
		</div>
		<form name="searchForm" id="searchForm" action="adoptionList.mvc"
			class="row mb-3 text-center justify-content-center">
			<div class="col-auto">
				<select name="type" class="form-select">
					<option value="title" ${type == 'title' ? 'selected' : '' }>제목</option>
					<option value="adoption_type" ${type == 'adoption_type' ? 'selected' : '' }>상태</option>
					<option value="writer" ${type == 'writer' ? 'selected' : '' }>작성자</option>
					<option value="content" ${type == 'content' ? 'selected' : '' }>내용</option>
				</select>
			</div>
			
			<!--  검색 요청 -->
			<div class="col-4">
				<input type="text" name="keyword" class="form-control form-control-sm" 
				id="keyword" value="${keyword }">
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary btn-sm" value="검색">검색</button>
			</div>
		</form>
		<div class="row mb-3">
			<div class="col text-end">
			<c:choose>
						<c:when test="${sessionScope.userId !=null }">
						<a href="AdoptionWriteForm.mvc" class="btn btn-success btn-sm">글쓰기</a>
					</c:when>
					<c:otherwise>
						<a href="#" class="btn btn-secondarybtn-sm" onclick="alertLogin()">글쓰기</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="row">
			<div class="col">
				<table class="table table-hover table-boardered">
					<thead class="table-dark">
						<tr>
							<th class = "text-center" style='width: 8%;'>no</th>
							<th class = "text-center" style='width: 10%;'>상태</th>
							<th>제목</th>
							<th class = "text-center" style='width: 12%;'>작성자</th>
							<th class = "text-center" style='width: 12%;'>입양/분양</th>
							<th class = "text-center" style='width: 12%;'>작성일</th>
							<th class = "text-center" style='width: 12%;'>조회수</th>
							
						</tr>
					</thead>
					<tbody class="text-secondary">

					<c:choose>
						<c:when test="${not empty blist}">
							<c:forEach var="adopboard" items="${ blist }">
								<tr>
									<td class="text-center">${adopboard.postId}</td>
									<td class="text-center">${adopboard.approvalStatus}</td>
									<td class="text-secondary">
									<a href="AdoptionDetail.mvc?postId=${adopboard.postId}
							&pageNum=${currentPage}&type=${type}&keyword=${keyword}"
										class="text-secondary text-decoration-none">${adopboard.title}</a>
									</td>
									<td class="text-center">${adopboard.userId}</td>
									<td class="text-center">${adopboard.adoptionType}</td>
									<td class="text-center"><fmt:formatDate value="${adopboard.createdAt}" pattern="yyyy/MM/dd HH:mm" /></td>
									
									<td class="text-center">${adopboard.viewsCount }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" class="text-center">
								<c:if test="${not empty keyword }">"${keyword}" 검색 결과가 없습니다.</c:if>
								<c:if test="${empty keyword }">등록된 게시글이 없습니다.</c:if>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>

	
			<c:if test="${not empty blist }">
				<div class ='row'>
					<div class ="col">
						<nav aria-label='page navigation'>
							<ul class='pagination justify-content-center'>
								<c:if test="${startPage > pageGroup }">
									<li class = "page-item">
									 <a class = "page-link"
									 	href = "AdoptionList.mvc?pageNum=${startPage - pageGroup}
									 	&type=${searchColumn}&keyword=${keyword}"> 이전</a>
										</li>
								</c:if>
								<c:forEach var='i' begin="${startPage}" end="${endPage }">
									<li class ="page-item ${i == currentPage ? 'active' : '' }">
										<a class ="page-link"
									href ="AdoptionList.mvc?pageNum=${i}&type=${type }&keyword=${keyword }">${i}</a>
									</li>
									</c:forEach>
								<c:if test="${endPage < pageCount}">
									<li class="page-item">
										<a class="page-link"
								   		href="AdoptionList.mvc?pageNum=${startPage + pageGroup}&type=${type}&keyword=${keyword}">다음</a>
									</li>
									</c:if>
							</ul>
				</nav>
			</div>
		</div>
	</c:if>
</div>

<script>
		function alertLogin(){
			alert("로그인 후 글쓰기가 가능합니다.");
			window.location.href="member/loginForm";
		}
</script>
