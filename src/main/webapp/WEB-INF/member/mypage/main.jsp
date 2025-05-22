<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet" />

<div class="container admin-main-container"> 
    <div class="row my-5 admin-page-section">
        <div class="col">
            <h2 class="admin-section-title">사용자 페이지</h2>
            <p class="admin-dashboard-info">현재 세션 ID : ${sessionScope.id}</p>

            <div class="row g-4"> 
                <div class="col-md-6 col-lg-4">
                    <a href="${pageContext.request.contextPath}/member/mypage/reservation" class="admin-dashboard-card-link">
                        <div class="card-icon"><i class="bi bi-calendar-check-fill"></i></div>
                        <h5 class="card-link-title">예약 내역 보기</h5>
                    </a>
                </div>
                <div class="col-md-6 col-lg-4">
                    <a href="${pageContext.request.contextPath}/member/mypage/diaryList" class="admin-dashboard-card-link">
                        <div class="card-icon"><i class="bi bi-journal-richtext"></i></div>
                        <h5 class="card-link-title">다이어리 보기</h5>
                    </a>
                </div>
                <div class="col-md-6 col-lg-4">
                    <a href="${pageContext.request.contextPath}/member/mypage/boardList" class="admin-dashboard-card-link">
                        <div class="card-icon"><i class="bi bi-card-list"></i></div>
                        <h5 class="card-link-title">작성 게시글 보기</h5>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>