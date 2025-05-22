<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/faq.css" />


<div class="container my-5" id="qna-update-page-container"> 
    
    <div class="row">
        <div class="col text-center">
            <h2 id="title">문의글 수정</h2> 
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-10 col-lg-10">
            <c:if test="${not empty qnaBoard}">
                <div class="content-card"> 
                    <div class="card-body p-4 p-md-5">
                        <form action="${pageContext.request.contextPath}/support/qnaUpdateResult" name="updateForm" id="updateForm" method="post">
                            <input type="hidden" name="no" value="${qnaBoard.no}">
                            
                            <div class="mb-3">
                                <label for="qnaUpdateTitle" class="form-label form-section-label">제 목</label>
                                <input type="text" class="form-control" id="qnaUpdateTitle" name="title" value="<c:out value='${qnaBoard.title}'/>" placeholder="제목을 입력해주세요." required>
                            </div>
                              
                            <div class="mb-4">
                                <label for="qnaUpdateContent" class="form-label form-section-label">내 용</label>
                                <textarea class="form-control" id="qnaUpdateContent" name="content" rows="12" placeholder="내용을 입력해주세요." required><c:out value='${qnaBoard.content}'/></textarea>
                            </div>

                            <c:if test="${sessionScope.isAdmin == true}">
                                <div class="mb-4">
                                    <label for="qnaUpdateStatus" class="form-label form-section-label">상 태</label>
                                    <select class="form-select" id="qnaUpdateStatus" name="status">
                                        <option value="답변대기" ${qnaBoard.status == '답변대기' ? 'selected' : ''}>답변대기</option>
                                        <option value="답변완료" ${qnaBoard.status == '답변완료' ? 'selected' : ''}>답변완료</option>
                                        <%-- 다른 상태 값이 있다면 여기에 <option> 추가 --%>
                                    </select>
                                </div>
                            </c:if>

                            <div class="mt-5 d-flex justify-content-center gap-2">
                                <button type="submit" class="btn btn-custom-theme px-4"
                                onclick="location.href='${pageContext.request.contextPath}/support/qnaUpdateResult?no=${qnaBoard.no}'">
                                    <i class="bi bi-check-lg me-1"></i> 수정
                                </button>
                                <button type="button" class="btn btn-outline-secondary px-4"
                                    onclick="location.href='${pageContext.request.contextPath}/support/qnaDetail?no=${qnaBoard.no}'">
                                    <i class="bi bi-x-lg me-1"></i> 취소
                                </button>
                            </div>
                        </form>        
                    </div>
                </div>
            </c:if>
            <c:if test="${empty qnaBoard}">
                <div class="alert alert-warning text-center" role="alert">
                    수정할 문의글 정보를 찾을 수 없거나 수정 권한이 없습니다.
                </div>
                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/support/qnaList" class="btn btn-secondary">목록으로 돌아가기</a>
                </div>
            </c:if>
        </div>
    </div>
</div>