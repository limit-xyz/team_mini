<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet" />

<div class="container">
    <div class="row my-5 page-section">
        <div class="col">
            <h2 class="section-title">관리자 페이지</h2>
            <c:if test="${isAdmin == true}"> <%-- sessionScope.isAdmin 또는 requestScope.isAdmin 등 실제 전달된 변수명 사용 --%>
                <p class="admin-info">어드민 계정으로 접속 중입니다.</p>
            </c:if>
            
            <div class="row g-4"> 
                <div class="col-md-6 col-lg-4">
                    <a href="member" class="dashboard-card-link"> 
                        <div class="card-icon"><i class="bi bi-people-fill"></i></div>
                        <h5 class="card-link-title">멤버 관리</h5>
                    </a>
                </div>

                <div class="col-md-6 col-lg-4">
                    <a href="inquiry" class="dashboard-card-link"> 
                        <div class="card-icon"><i class="bi bi-chat-left-text-fill"></i></div>
                        <h5 class="card-link-title">문의 관리</h5>
                    </a>
                </div>
            </div>
        </div>
    </div>    

    <div class="row my-5 page-section">
        <div class="col">
            <h2 class="section-title">사용자 페이지</h2>
            <p class="user-info">현재 세션 ID : ${sessionScope.id}</p>

            <div class="row g-4"> 
                <div class="col-md-6 col-lg-4">
                    <a href="${pageContext.request.contextPath}/member/mypage/reservation" class="dashboard-card-link">
                        <div class="card-icon"><i class="bi bi-calendar-check-fill"></i></div>
                        <h5 class="card-link-title">예약 내역 보기</h5>
                    </a>
                </div>
                <div class="col-md-6 col-lg-4">
                    <a href="${pageContext.request.contextPath}/member/mypage/diaryList" class="dashboard-card-link">
                        <div class="card-icon"><i class="bi bi-journal-richtext"></i></div>
                        <h5 class="card-link-title">다이어리 보기</h5>
                    </a>
                </div>
                <div class="col-md-6 col-lg-4">
                    <a href="${pageContext.request.contextPath}/member/mypage/boards" class="dashboard-card-link">
                        <div class="card-icon"><i class="bi bi-card-list"></i></div>
                        <h5 class="card-link-title">작성 게시글 보기</h5>
                    </a>
                </div>
               
            </div>
        </div>
    </div>
</div>