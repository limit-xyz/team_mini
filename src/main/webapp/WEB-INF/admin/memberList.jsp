<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content -->
<div class="row">
	<div class="col">
		<table class="table table-bordered table-hover my-3">
		
			<thead>
				<tr class="table-dark text-center">
					<th style="width: 8%;">NO</th>
					<th style="width: 20%;">이름</th>
					<th style="width: 25%;">가입일</th>
					<th style="width: 25%;">차단 해제일</th>
					<th style="width: 22%;">명령</th>
				</tr>		
			</thead>
			
			<tbody class="text-secondary" id="tableBody">
				<c:if test="${ not empty memberList }">
					<c:forEach var="member" items="${memberList}" varStatus="status">
						<tr>
							<td class="text-center">${ member.no }</td>
							
							<td>${ member.name }</td>
							
							<td class="text-end">
								<fmt:formatDate value="${member.regDate}" pattern="yyyy/MM/dd" />
							</td>
							
							<td class="text-end">
								<c:if test="${not member.ignore}">
									<p>-</p>
								</c:if>
								
								<c:if test="${member.ignore}">
									<fmt:formatDate value="${member.ignoreDate}" pattern="yyyy/MM/dd HH:mm:SS" />
								</c:if>
							</td>
							
							<td class="text-center">
								<button type="button" class="ignoreMember btn btn-warning" data-no="${member.no}"
									data-bs-toggle="modal" data-bs-target="#staticBackdrop">차단</button>
								<button type="button" class="releaseMember btn btn-success" data-no="${member.no}">차단해제</button>
    							<button type="button" class="deleteMember btn btn-danger" data-no="${member.no}">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>							

				<c:if test="${ empty memberList }">
					<tr>
						<td colspan="5" class="text-center">멤버가 존재하지 않음</td>
					</tr>
				</c:if>
				
			</tbody>
		</table>
		
		<div class="row">
			<div class="col text-center">
				<a href="main">돌아가기</a>
			</div>
		</div>
		
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

			<form id="ignoreMemberForm">
				<div class="modal-body">
					<div class="mb-3">
						<h5><span id="ignoreNo"></span>번 유저를 차단합니다.</h5>
						<label for="ignoreDate" class="form-label">얼마나 차단하시겠습니까?</label>
						<div class="input-group" style="width: 120px;">
							<input type="number" class="form-control" id="ignoreDate" name="ignoreDate">
							<span class="input-group-text">일</span>				
						</div>
						
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
<!-- Content End -->