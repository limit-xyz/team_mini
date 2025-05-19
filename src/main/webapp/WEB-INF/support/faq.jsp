<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/faq.css" />
    <div class="container">
      <div id="title">FAQ</div>
      <div class="row">
        <div id="tab-container">
          <div class="pill-button-group">
            <button class="pill-button active">FAQ</button>
            <button class="pill-button">1대1 문의</button>
            <button class="pill-button">신고 게시판</button>
          </div>
        </div>
      </div>

      <div class="row mt-5">
        
					<form action="#">
					<div class="input-group mb-3">
						<input name="keyword" type="text" class="form-control" placeholder="입력해주세요">
						<input class="btn btn-outline-secondary" type="submit" id="button-addon2" value="검색">
					</div>
					</form>
      </div>

<div class="row">
  <div class="col">
    <div class="accordion" id="accordionExample">
      <c:forEach var="faq" items="${faqList }">
        <div class="accordion-item">
          <h2 class="accordion-header" id="heading${faq.faqNo}">
<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
        data-bs-target="#collapse${faq.faqNo}" aria-expanded="false"
        aria-controls="collapse${faq.faqNo}">

  <span>${faq.faqTitle }</span>
  <span class="flex-grow-1"></span>
  <c:if test="${isAdmin == true }">
    <%-- <a> 태그를 <span>으로 변경하고, data-href 속성에 URL 저장 --%>
    <span data-href="${pageContext.request.contextPath}/support/faqUpdateForm?no=${faq.faqNo}"
          class="btn btn-sm btn-outline-secondary me-2 edit-faq-action z-3" role="button" tabindex="0">수정</span>
    <span data-href="${pageContext.request.contextPath}/support/faqDelete?no=${faq.faqNo}"
          class="btn btn-sm btn-outline-danger me-2 delete-faq-action z-3" role="button" tabindex="0">삭제</span>
  </c:if>
</button>
          </h2>
          <div id="collapse${faq.faqNo}" class="accordion-collapse collapse"
            aria-labelledby="heading${faq.faqNo}" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              ${faq.faqContent }
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
      
      <c:if test="${isAdmin == true }">
      <div class="row mt-2">
      	<div class="col text-end">
						<a href="${pageContext.request.contextPath}/support/faqWriteForm" class="btn btn-outline-success">글쓰기</a>
					</div>
      </div>
      </c:if>
      <!-- 검색 요청이면서 검색된 리스트가 존재할 경우 페이지네이션 -->
				<c:if test="${ searchOption and not empty faqList }">
				<div class="row">
					<div class="col">
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center my-5">
						    <c:if test="${startPage > pageGroup}">
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="${pageContext.request.contextPath}/support/faq?pageNum=${startPage - pageGroup}&keyword=${keyword}">Pre</a>
						    </li>
						    </c:if>
						    <c:forEach var="i" begin="${startPage}" end="${endPage}">
						    	<%-- 현재 페이지 - 링크x active --%>
						    	<c:if test="${i == currentPage}">
						    	<li class="page-item">
						    		<span class="page-link active">${i}</span>
						    	</li>
						    	</c:if>
						    	<c:if test="${i != currentPage}">
						    	<li class="page-item">
						    		<a class="page-link" href="${pageContext.request.contextPath}/support/faq?pageNum=${i}&type=${type}&keyword=${keyword}">${i}</a>
						    	</li>
						    	</c:if>
						    </c:forEach>
						    <c:if test="${endPage < pageCount}">						    
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="${pageContext.request.contextPath}/support/faq?pageNum=${startPage + pageGroup}&type=${type}&keyword=${keyword}">Next</a>
						    </li>
						    </c:if>
						  </ul>
						</nav>
					</div>
				</div>
				</c:if>
				
				<!-- 일반 요청이면서 검색된 리스트가 존재할 경우 페이지네이션 -->
				<c:if test="${ not searchOption and not empty faqList }">
				<div class="row">
					<div class="col">
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center my-5">
						    <c:if test="${startPage > pageGroup}">
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="${pageContext.request.contextPath}/support/faq?pageNum=${startPage - pageGroup}">Pre</a>
						    </li>
						    </c:if>
						    <c:forEach var="i" begin="${startPage}" end="${endPage}">
						    	<%-- 현재 페이지 - 링크x active --%>
						    	<c:if test="${i == currentPage}">
						    	<li class="page-item">
						    		<span class="page-link active">${i}</span>
						    	</li>
						    	</c:if>
						    	<c:if test="${i != currentPage}">
						    	<li class="page-item">
						    		<a class="page-link" href="${pageContext.request.contextPath}/support/faq?pageNum=${i}">${i}</a>
						    	</li>
						    	</c:if>
						    </c:forEach>
						    <c:if test="${endPage < pageCount}">						    
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="${pageContext.request.contextPath}/support/faq?pageNum=${startPage + pageGroup}">Next</a>
						    </li>
						    </c:if>
						  </ul>
						</nav>
					</div>
				</div>
				</c:if>

    </div>
    
    
    <script>
    document.addEventListener('DOMContentLoaded', function () {
    	  // 모든 수정 버튼(span)에 이벤트 리스너 추가
    	  document.querySelectorAll('.edit-faq-action').forEach(function(spanElement) {
    	    spanElement.addEventListener('click', function(event) {
    	      // 1. 이벤트 버블링 중단 (매우 중요!)
    	      event.stopPropagation();

    	      const url = this.dataset.href; // data-href 속성에서 URL 가져오기
    	      if (url) {
    	        // alert('수정: ' + url); // 테스트용 얼럿
    	        console.log('수정 span 클릭됨 - URL로 이동 예정: ' + url);
    	        window.location.href = url; // JavaScript로 페이지 이동
    	      } else {
    	        console.error('수정 URL을 찾을 수 없습니다.');
    	      }
    	    });
    	    // Enter 키로도 버튼이 동작하도록 설정 (접근성 향상)
    	    spanElement.addEventListener('keypress', function(event) {
    	        if (event.key === 'Enter') {
    	            event.preventDefault(); // 기본 동작 방지
    	            event.stopPropagation();
    	            this.click(); // 클릭 이벤트 강제 실행
    	        }
    	    });
    	  });

    	  // 모든 삭제 버튼(span)에 이벤트 리스너 추가
    	  document.querySelectorAll('.delete-faq-action').forEach(function(spanElement) {
    	    spanElement.addEventListener('click', function(event) {
    	      // 1. 이벤트 버블링 중단 (매우 중요!)
    	      event.stopPropagation();

    	      const url = this.dataset.href; // data-href 속성에서 URL 가져오기
    	      if (confirm('정말로 이 항목을 삭제하시겠습니까?')) {
    	        if (url) {
    	          // alert('삭제: ' + url); // 테스트용 얼럿
    	          console.log('삭제 확인됨 - URL로 이동 예정: ' + url);
    	          window.location.href = url; // JavaScript로 페이지 이동
    	        } else {
    	          console.error('삭제 URL을 찾을 수 없습니다.');
    	        }
    	      } else {
    	        console.log('삭제가 취소되었습니다.');
    	        // span이므로 event.preventDefault()는 필요하지 않습니다.
    	      }
    	    });
    	    // Enter 키로도 버튼이 동작하도록 설정 (접근성 향상)
    	    spanElement.addEventListener('keypress', function(event) {
    	        if (event.key === 'Enter') {
    	            event.preventDefault();
    	            event.stopPropagation();
    	            this.click();
    	        }
    	    });
    	  });
    	});
    </script>
    