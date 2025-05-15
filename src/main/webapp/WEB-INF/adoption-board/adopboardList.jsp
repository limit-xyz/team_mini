<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  content -->
<div class="row my-5" id="global-content">
	<div class="col">
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">게시 글 리스트</h2>
			</div>
		</div>
		<form name="searchForm" id="searchForm" action="adoptionList.mvc"
			class="row my-3 text-center justify-content-center">
			<div class="col-auto">
				<select name="type" class="form-select">
					<option value="title">제목</option>
					<option value="adoption_type">상태</option>
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




		<!--  검색 요청 -->
		<c:if test="${searchOption }">
			<div class="row">
				<div class="col text-conter">"${keyword }" 검색 결과</div>
			</div>
			<div class="row">
				<div class="col">
					<a href="adoptboardList.mvc" class="btn btn-outline-success">리스트</a>
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
							<th>상태</th>
							<th>제목</th>
							<th>작성자</th>
							<th>입양/분양</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody class="text-secondary">

						<%-- 검색 요청이면서 게시글이 있는 경우 --%>
						<c:if test="${searchOption and not empty blist}">
							<c:forEach var="adopboard" items="${ blist }">
								<tr>
									<td>${adopboard.postId}</td>
									<td>${adopboard.approvalStatus}</td>
									<td class="text-secondary"><a
										href="adoptionDetailList.mvc?no=${adopboard.postId}
							&pageNum=${currentPage}&type=${searchColumn}&keyword=${keyword}
							&approvalStatus=${approvalStatus}"
										class="text-secondary text-decoration-none">${adopboard.title}</a>
									</td>
									<td>${adopboard.userId}</td>
									<td>${adopboard.adoptionType}</td>
									<td>${adopboard.createdAt}</td>
								</tr>
							</c:forEach>
						</c:if>

						<!-- 검색 게시 글 리스트 이면서 게시글이 없는 경우 -->
						<c:if test="${searchOption and empty blist}">
							<tr>
								<td colspan="6" class="text-center">"${keyword}"가 포함된 게시 글이
									존재하지 않음</td>
							</tr>
						</c:if>

						<!-- 일반 게시글 리스트 요청이면서 게시글이 있는 경우  -->
						<c:if test="${not searchOption and not empty blist }">
							<c:forEach var="adopboard" items="${blist }">
								<tr>
									<td>${adopboard.postId}</td>
									<td>${adopboard.approvalStatus}</td>
									<td class="text-secondary"><a
										href="adoptionDetail.mvc?postId=${adopboard.postId}&pageNum=${currentPage}"
										class="text-secondary text-decoration-none">${adopboard.title}</td>
									<td>${adopboard.userId}</td>
									<td>${adopboard.adoptionType}</td>
									<td>${adopboard.createdAt}</td>
								</tr>
							</c:forEach>
						</c:if>



						<!-- 일반 게시 글 리스트 이면서 게시글이 없는 경우 -->
						<c:if test="${not searchOption and empty blist}">
							<tr>
								<td colspan="6" class="text-center">게시 글이 존재하지 않음</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>

		<!-- 검색 요청이면서 검색된 리스트가 존재할 경우 페이지네이션 -->
		<c:if test="${ searchOption and not empty blist }">
			<div class="row">
				<div class="col">
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<c:if test="${startPage > pageGroup}">
								<li class="page-item"><a class="page-link"
									href="adoptionList.mvc?pageNum=${startPage - pageGroup}&type=${searchColumn}&keyword=${keyword}">Pre</a>
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
										href="adoptionList.mvc?pageNum=${i}&type=${searchColumn}&keyword=${keyword}">${i}</a>
									</li>
								</c:if>
							</c:forEach>
							<c:if test="${endPage < pageCount}">
								<li class="page-item"><a class="page-link"
									href="adoptionList.mvc?pageNum=${startPage + pageGroup}&type=${searchColumn}&keyword=${keyword}">Next</a>
								</li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</c:if>

		<!-- 일반 요청이면서 검색된 리스트가 존재할 경우 페이지네이션 -->
		<c:if test="${ not searchOption and not empty blist }">
			<div class="row">
				<div class="col">
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<c:if test="${startPage > pageGroup}">
								<li class="page-item"><a class="page-link"
									href="adoptionList.mvc?pageNum=${startPage - pageGroup}">Pre</a></li>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<%-- 현재 페이지 - 링크x active --%>
								<c:if test="${i == currentPage}">
									<li class="page-item"><span class="page-link active">${i}</span>
									</li>
								</c:if>
								<c:if test="${i != currentPage}">
									<li class="page-item"><a class="page-link"
										href="adoptionList.mvc?pageNum=${i}">${i}</a></li>
								</c:if>
							</c:forEach>
							<c:if test="${endPage < pageCount}">
								<li class="page-item"><a class="page-link"
									href="adoptionList.mvc?pageNum=${startPage + pageGroup}">Next</a>
								</li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</c:if>
	</div>
</div>



