<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: #f8f9fa;
}

:root {
	--main-theme-color: #C5E1D4;
	--main-theme-darker-text: #2c5c49;
	--main-theme-border-color: #b0d1c0;
	--main-theme-light-bg: #eef3f1;
	--text-muted-custom: #5a6670;
}

.btn-custom-theme {
	background-color: var(--main-theme-color);
	border-color: var(--main-theme-border-color);
	color: var(--main-theme-darker-text);
	font-weight: 500;
}

.btn-custom-theme:hover, .btn-custom-theme:focus {
	background-color: #b3d6c6;
	border-color: #a0c3b3;
	color: var(--main-theme-darker-text);
}

.qna-header-block {
	background-color: var(--main-theme-light-bg);
	border-left: 5px solid var(--main-theme-color);
}

.qna-detail-title {
	color: var(--main-theme-darker-text);
	font-weight: 700;
	font-size: 1.75rem;
}

.qna-meta-info {
	font-size: 0.9rem;
	color: var(--text-muted-custom);
}

.qna-meta-info strong {
	color: #343a40;
}

.qna-meta-info .bi {
	margin-right: 0.25rem;
	color: var(--main-theme-color);
}

.status-badge {
	padding: 0.4em 0.7em;
	font-size: 0.85em;
	font-weight: 500;
	vertical-align: middle;
}

.status-answered {
	background-color: var(--main-theme-color) !important;
	color: var(--main-theme-darker-text) !important;
}

.status-waiting {
	background-color: #ffc107 !important;
	color: #333 !important;
}

.content-card {
	background-color: #fff;
	border: 1px solid #dee2e6;
	box-shadow: 0 0.125rem 0.375rem rgba(0, 0, 0, 0.06);
}

.content-card .card-header {
	background-color: #f8f9fa;
	color: var(--main-theme-darker-text);
	font-weight: 600;
	border-bottom: 1px solid #e9ecef;
}

.content-card .card-body p, .content-card .card-body .form-label {
	/* 폼 레이블에도 동일한 스타일 적용 */
	white-space: pre-wrap;
	line-height: 1.8;
	color: #495057;
	margin-bottom: 0.5rem; /* 레이블 하단 마진 */
}

.content-card .card-body p {
	margin-bottom: 0;
}

.answer-card .card-header { /* 기존 답변 표시용 */
	background-color: var(--main-theme-light-bg);
}

.answer-card { /* 기존 답변 표시용 */
	border-top: 3px solid var(--main-theme-color);
}

.answer-meta-info { /* 기존 답변 표시용 */
	font-size: 0.85rem;
	color: var(--text-muted-custom);
}

.answer-meta-info .bi { /* 기존 답변 표시용 */
	margin-right: 0.25rem;
}

.answer-form-card .card-header { /* 답변 작성 폼용 카드 헤더 */
	background-color: var(--main-theme-light-bg);
}

.answer-form-card .card-header h5 {
	margin-bottom: 0; /* h5 기본 마진 제거 */
	font-size: 1.15rem; /* 질문/답변 내용 헤더보다 약간 작게 */
}

.action-buttons .btn {
	margin-right: 0.5rem;
	font-weight: 500;
}

.action-buttons .btn-sm {
	padding: 0.3rem 0.6rem;
	font-size: 0.875rem;
}
</style>
</head>
<body>

	<div class="container mt-5 mb-5">

    <c:if test="${not empty qnaBoard}">
        <div class="qna-header-block p-4 mb-4 rounded">
            <h1 class="qna-detail-title mb-3">${qnaBoard.title}</h1>
            <div class="qna-meta-info">
                <span class="me-3"><i class="bi bi-person"></i> 작성자: <strong>${qnaBoard.writer}</strong></span>
                <span class="me-3"><i class="bi bi-calendar3"></i> 작성일:
                    <fmt:formatDate value="${qnaBoard.regDate}" pattern="yyyy-MM-dd HH:mm"/>
                </span>
                <span><i class="bi bi-hourglass-split"></i> 상태: 
                    <c:choose>
                        <c:when test="${qnaBoard.status == '답변완료'}">
                            <span class="badge rounded-pill status-badge status-answered">답변완료</span>
                        </c:when>
                        <c:when test="${qnaBoard.status == '답변대기'}">
                            <span class="badge rounded-pill status-badge status-waiting">답변대기</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge rounded-pill status-badge">${qnaBoard.status}</span>
                        </c:otherwise>
                    </c:choose>
                </span>
            </div>
        </div>

        <div class="card content-card mb-4">
            <div class="card-header">
                <i class="bi bi-patch-question-fill me-1" style="color: var(--main-theme-darker-text);"></i> 
                <strong>질문 내용</strong>
            </div>
            <div class="card-body">
                <p>${qnaBoard.content}</p>
            </div>
        </div>

        <%-- 답변이 이미 있는 경우, 답변 내용을 표시 --%>
        <c:if test="${not empty qnaAnswer}">
            <div class="card content-card answer-card mb-4">
                <div class="card-header">
                    <i class="bi bi-chat-left-dots-fill me-1" style="color: var(--main-theme-darker-text);"></i>
                    <strong>답변 내용</strong>
                </div>
                <div class="card-body">
                    <div class="answer-meta-info mb-3 text-end">
                        <span class="me-3"><i class="bi bi-person-check"></i> 답변자: <strong>${qnaAnswer.answerWriter}</strong></span>
                        <span><i class="bi bi-calendar-check"></i> 답변일: 
                            <fmt:formatDate value="${qnaAnswer.answerRegDate}" pattern="yyyy-MM-dd HH:mm"/>
                        </span>
                    </div>
                    <p>${qnaAnswer.answerContent}</p>
                </div>
            </div>
        </c:if>

        <c:if test="${empty qnaAnswer && isAdmin == true}">
            <div class="card content-card answer-form-card mb-4">
                <div class="card-header">
                    <h5>
                        <i class="bi bi-pencil-fill me-2" style="color: var(--main-theme-darker-text);"></i>답변 작성하기
                    </h5>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/support/qnaAnswerWriteResult" method="post">
                        <input type="hidden" name="qnaNo" value="${qnaBoard.no}">
                        <input type="hidden" name="answerWriter" value="${sessionScope.id}"> <%-- 또는 sessionScope.adminId 등 실제 관리자 ID 세션 속성명 --%>
                        
                        <div class="mb-3">
                            <label for="answerText" class="form-label fw-medium"><strong>답변 내용:</strong></label>
                            <textarea class="form-control form-control-lg" id="answerText" name="answerContent" rows="10" placeholder="문의에 대한 답변을 상세히 입력해주세요." required></textarea>
                        </div>
                        <div class="text-end">
                            <button type="submit" class="btn btn-custom-theme px-4">
                                <i class="bi bi-check2-circle"></i> 답변 등록
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>

        <div class="row mt-5 pt-4 border-top">
            <div class="col-md-auto me-auto action-buttons mb-2 mb-md-0">
                <c:if test="${sessionScope.id == qnaBoard.writer || isAdmin == false}">
                    <a href="${pageContext.request.contextPath}/support/qnaUpdateForm?no=${qnaBoard.no}" class="btn btn-outline-secondary btn-sm">
                        <i class="bi bi-pencil-square"></i> 질문 수정
                    </a>
                    <a href="${pageContext.request.contextPath}/support/qnaDelete?no=${qnaBoard.no}" class="btn btn-outline-danger btn-sm" onclick="return confirm('정말로 이 문의글을 삭제하시겠습니까?');">
                        <i class="bi bi-trash3"></i> 질문 삭제
                    </a>
                </c:if>
                
                <c:if test="${isAdmin == true}">
                    
                    <a href="${pageContext.request.contextPath}/support/qnaAnswerDelete?answerNo=${qnaAnswer.answerNo}&qnaNo=${qnaBoard.no}" class="btn btn-outline-warning btn-sm" onclick="return confirm('정말로 이 답변을 삭제하시겠습니까?');">
                        <i class="bi bi-reply-all"></i> 답변 삭제
                    </a>
                </c:if>
            </div>
            <div class="col-md-auto action-buttons">
                <a href="${pageContext.request.contextPath}/support/qnaList" class="btn btn-custom-theme">
                    <i class="bi bi-list-task"></i> 목록으로
                </a>
            </div>
        </div>
    </c:if>

   
</div>