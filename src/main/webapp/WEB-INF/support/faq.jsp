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
      <div class="row my-5">
        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <li class="page-item disabled">
              <a class="page-link">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#">Next</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>