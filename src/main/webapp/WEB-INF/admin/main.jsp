<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet" />

<div class="container admin-main-container"> 
    <div class="row my-5 admin-page-section">
        <div class="col">
            <h2 class="admin-section-title">관리자 페이지</h2>
            <c:if test="${isAdmin == true}">
                <p class="admin-dashboard-info">어드민 계정으로 접속 중입니다.</p>
            </c:if>
            
            <div class="row g-4"> 
                <div class="col-md-6 col-lg-4">
                    <a href="member" class="admin-dashboard-card-link"> 
                        <div class="card-icon"><i class="bi bi-people-fill"></i></div>
                        <h5 class="card-link-title">멤버 관리</h5>
                    </a>
                </div>

                <div class="col-md-6 col-lg-4">
                    <a href="${pageContext.request.contextPath}/support/qnaList" class="admin-dashboard-card-link"> 
                        <div class="card-icon"><i class="bi bi-chat-left-text-fill"></i></div>
                        <h5 class="card-link-title">문의 관리</h5>
                    </a>
                </div>
            </div>
        </div>
    </div>    
</div>