<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet" />

<div class="container mt-4 mb-5">
    <div class="row my-4">
        <div class="col">                    
            <div class="row text-center">
                <div class="col">                
                    <h2 class="admin-page-title">멤버 리스트</h2>
                    <hr class="admin-short-hr"> 
                </div>
            </div>  
        
            <div style="visibility: hidden; height: 0; overflow:hidden;">
                <p id="pageNum">${currentPage}</p>
                <p id="isBanSort">${isBanSort}</p>
                <p id="searchId">${searchId}</p>
            </div>
        
            <div class="table-responsive">
                <table class="table table-bordered table-hover my-4 admin-member-list-table"> 
                    <thead>
                        <tr>
                            <th class="th-id">아이디</th>
                            <th class="th-name">이름</th>
                            <th class="th-gender">성별</th>
                            <th class="th-phone">전화번호</th>
                            <th class="th-zipcode">우편번호</th>
                            <th class="th-address1">주소1</th>
                            <th class="th-address2">주소2</th>
                            <th class="th-regdate">가입일</th>
                            <th class="th-birthdate">생일</th>
                            <th class="th-bandate clickable-header" onclick="location.href='member?banSort=1'">차단 해제일 <i class="bi bi-arrow-down-up ms-1"></i></th>
                            <th class="th-role">역할</th>
                            <th class="th-actions">작업</th>
                        </tr>        
                    </thead>
                    
                    <tbody id="tableBody">
                        <c:if test="${ not empty memberList }">
                            <c:forEach var="member" items="${memberList}" varStatus="status">
                                <tr>
                                    <td class="td-id">${ member.id }</td>
                                    <td class="td-name">${ member.name }</td>
                                    <td class="td-gender">${ member.gender }</td>
                                    <td class="td-phone">${ member.mobile }</td>
                                    <td class="td-zipcode">${ member.zipcode }</td>
                                    <td class="td-address1">${ member.address1 }</td>
                                    <td class="td-address2">${ member.address2 }</td>
                                    <td class="td-regdate">
                                        <fmt:formatDate value="${member.regDate}" pattern="yyyy/MM/dd" />
                                    </td>
                                    <td class="td-birthdate">
                                        <fmt:formatDate value="${member.birthDate}" pattern="yyyy/MM/dd" />
                                    </td>
                                    <td class="td-bandate banDate">
                                        <c:if test="${not member.ban}">
                                            <span>-</span>
                                        </c:if>
                                        <c:if test="${member.ban}">
                                            <fmt:formatDate value="${member.banDate}" pattern="yyyy/MM/dd HH:mm" />
                                        </c:if>
                                    </td>
                                    <td class="td-role userRole" data-user-id="${member.id}">
                                        <select class="userRoleSelect form-select form-select-sm" ${member.role == 'admin' ? 'disabled' : ''}>
                                            <option value='admin' ${member.role == 'admin' ? 'selected' : 'hidden'}>ADMIN</option>
                                            <option value='beautician' ${member.role == 'beautician' ? 'selected' : ''}>BEAUTICIAN</option>
                                            <option value='doctor' ${member.role == 'doctor' ? 'selected' : ''}>DOCTOR</option>
                                            <option value='expert' ${member.role == 'expert' ? 'selected' : ''}>EXPERT</option>
                                            <option value='seller' ${member.role == 'seller' ? 'selected' : ''}>SELLER</option>
                                            <option value='user' ${member.role == 'user' ? 'selected' : ''}>USER</option>
                                        </select>
                                    </td>
                                    <td class="td-actions">
                                        <c:if test='${member.role != "admin"}'>
                                            <div class="btn-group btn-group-sm" role="group" aria-label="Member Actions">
                                                <button type="button" class="banMember btn btn-warning" data-user-id="${member.id}"
                                                    data-bs-toggle="modal" data-bs-target="#staticBackdrop"><i class="bi bi-slash-circle"></i> 임시차단</button>
                                                <button type="button" class="releaseMember btn btn-success" data-user-id="${member.id}"><i class="bi bi-check-circle"></i> 차단해제</button>
                                                <button type="button" class="deleteMember btn btn-danger" data-user-id="${member.id}"><i class="bi bi-trash3"></i> 유저삭제</button>
                                            </div>
                                        </c:if>
                                    </td>
                                    <td class="banReason" style="visibility: hidden;">${member.banReason}</td>
                                </tr>
                            </c:forEach>
                        </c:if>                            
                        <c:if test="${ empty memberList }">
                            <tr><td colspan="12" class="text-center py-4">...</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
            
            <div class="row mb-4">
                <div class="col text-center">
                    <a href="${pageContext.request.contextPath}/main/main" class="btn btn-outline-secondary"><i class="bi bi-arrow-left-circle"></i> 메인으로 돌아가기</a>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <nav aria-label="Page navigation">
                      <ul class="pagination justify-content-center">
                      </ul>
                    </nav>
                </div>
            </div>
                    
            <div class="row my-4 justify-content-center">
                <div class="col-md-8 col-lg-6">
                    <form name="searchMemberForm" id="adminSearchMemberForm"  action="member" class="row g-2 align-items-center">                  
                        <label class="col-auto col-form-label"  for="searchMemberId">아이디 검색:</label>
                        <div class="col">
                            <input type="text" name="searchMemberId" class="form-control" id="searchMemberId" value="${searchId}" placeholder="검색할 아이디 입력"/>
                        </div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-admin-theme"><i class="bi bi-search"></i> 검 색</button> 
                        </div>
                    </form>            
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade admin-modal" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true"> {/* 클래스 추가 */}
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header"> 
                    <h1 class="modal-title fs-5" id="modalLabel">회원 임시 차단</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal"><i class="bi bi-x-circle"></i> 취소</button>
                        <button type="submit" class="btn btn-admin-theme"><i class="bi bi-check-circle"></i> 확인</button> 
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/member_admin.js"></script>