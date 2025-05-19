<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Content -->
<div class="row">
	<div class="col">
	
		<div style="visibility: hidden;">
			<p id="pageNum">${currentPage}</p>
			<p id="isBanSort">${isBanSort}</p>
			<p id="searchId">${searchId}</p>
		</div>
	
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">멤버 리스트</h2>
			</div>
		</div>  
	
		<table class="table table-bordered table-hover my-3">
		
			<thead>
				<tr class="table-dark text-center">
					<th>아이디</th>
					<th>이름</th>
					<th>성별</th>
					<th>전화번호</th>
					<th colspan=3>주소</th>
					<th>가입일</th>
					<th>생일</th>
					<th onclick="location.href=`member?banSort=1`">차단 해제일</th>
					<th>역할</th>
					<th>작업</th>
				</tr>		
			</thead>
			
			<tbody class="text-secondary" id="tableBody">
				<c:if test="${ not empty memberList }">
					<c:forEach var="member" items="${memberList}" varStatus="status">
						<tr>
							<td class="text-center">${ member.id }</td>
							
							<td class="text-center">${ member.name }</td>
							
							<td class="text-center">${ member.gender }</td>
							
							<td class="text-center">${ member.mobile }</td>
							
							<td class="text-center">${ member.zipcode }</td>
							
							<td class="text-center">${ member.address1 }</td>
							
							<td class="text-center">${ member.address2 }</td>
							
							<td class="text-end">
								<fmt:formatDate value="${member.regDate}" pattern="yyyy/MM/dd" />
							</td>
							
							<td class="text-end">
								<fmt:formatDate value="${member.birthDate}" pattern="yyyy/MM/dd" />
							</td>
							
							<td class="banDate text-end">
								<c:if test="${not member.ban}">
									<p>-</p>
								</c:if>
								
								<c:if test="${member.ban}">
									<fmt:formatDate value="${member.banDate}" pattern="yyyy/MM/dd HH:mm:ss" />
								</c:if>
							</td>
							
							<td class="userRole" data-user-id="${member.id}">
									<select class="userRoleSelect form-select" ${member.role == 'admin' ? 'disabled' : ''}>
											<option value='admin' ${member.role == 'admin' ? 'selected' : 'hidden'}>ADMIN</option>
											<option value='beautician' ${member.role == 'beautician' ? 'selected' : ''}>BEAUTICIAN</option>
											<option value='doctor' ${member.role == 'doctor' ? 'selected' : ''}>DOCTOR</option>
											<option value='expert' ${member.role == 'expert' ? 'selected' : ''}>EXPERT</option>
											<option value='seller' ${member.role == 'seller' ? 'selected' : ''}>SELLER</option>
											<option value='user' ${member.role == 'user' ? 'selected' : ''}>USER</option>
									</select>
							</td>
							
							<td class="text-center">
								<c:if test='${member.role != "admin"}'>
									<button type="button" class="banMember btn btn-warning me-2" data-user-id="${member.id}"
										data-bs-toggle="modal" data-bs-target="#staticBackdrop">임시차단</button>
									<button type="button" class="releaseMember btn btn-success me-2" data-user-id="${member.id}">차단해제</button>
	    							<button type="button" class="deleteMember btn btn-danger" data-user-id="${member.id}">유저삭제</button>
    							</c:if>
							</td>
							
							<td class="banReason d-none">${member.banReason}</td>
						</tr>
					</c:forEach>
				</c:if>							

				<c:if test="${ empty memberList }">
					<tr>
						<c:if test="${searchOption == '1'}">
							<td colspan="12" class="text-center">검색 결과가 존재하지 않음</td>
						</c:if>
						
						<c:if test="${not searchOption == '1'}">
							<td colspan="12" class="text-center">멤버가 존재하지 않음</td>
						</c:if>
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


<div class="row">
	<div class="col">
		<nav aria-label="Page navigation">
		  <ul class="pagination justify-content-center">
		  
		  	<c:if test="${ startPage > PAGE_GROUP }">
			    <li class="page-item">
			      <a class="page-link" href="member?pageNum=${ startPage - PAGE_GROUP }&searchMemberId=${searchId}">Prev</a>
			    </li>
		    </c:if>
		   	
		    <c:forEach var="i" begin="${startPage}" end="${endPage}">
		    	<c:if test="${i == currentPage }">
			    	<li class="page-item active" aria-current="page">
			    		<span class="page-link">${i}</span>
			    	</li>
		    	</c:if>
		    	
		    	<c:if test="${i != currentPage }">
			    	<li class="page-item">
			    		<a class="page-link" href="member?pageNum=${ i }&searchMemberId=${searchId}">${i}</a>
			    	</li>
			    </c:if>					    
		    </c:forEach>							    

			<c:if test="${ endPage < pageCount }">
			    <li class="page-item">
			      <a class="page-link" href="member?pageNum=${ startPage + PAGE_GROUP }&searchMemberId=${searchId}">Next</a>
			    </li>
		  	</c:if>
		  	
		  </ul>
		</nav>
	</div>
</div>

					    	
<div class="row my-1">
	<div class="col">
		<form name="searchMemberForm" id="searchMemberForm"  action="member"
			class="row justify-content-center my-2 ">
			
			<div class="col-1">
				<label class="form-label"  for="searchMemberId">아이디 검색</label>
			</div>
			
			<div class="col-4">
				<input type="text" name="searchMemberId" class="form-control" id="searchMemberId" value="${searchId}"/>
			</div>
			
			<div class="col-auto">
				<input type="submit" value="검 색" class="btn btn-primary"/>
			</div>
		</form>			
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
<script src="${pageContext.request.contextPath}/js/member_admin.js"></script>
<!-- Content End -->