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
      <div class="row">
        <div class="col serch_input_wrap">
          <input id="serch_input" type="text" placeholder=" 검색어를 입력해주세요" />
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="accordion" id="accordionExample">
          	<c:forEach var="faq" items="${faqList }">
            <div class="accordion-item">
              <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" 
                      data-bs-target="#collapse${faq.faqNo}" aria-expanded="false" 
                      aria-controls="collapse${faq.faqNo}">${faq.faqTitle }</button>
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