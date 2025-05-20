<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/faq.css" />
<div class="container">
	<div id="title">문의 게시판</div>
      <div class="row">
        <div id="tab-container">
          <div class="pill-button-group">
            <button class="pill-button "><a href="${pageContext.request.contextPath}/support/faq">FAQ</a></button>
            <button class="pill-button active"><a href="${pageContext.request.contextPath}/support/qnaList">문의 게시판</a></button>
            <button class="pill-button"><a href="${pageContext.request.contextPath}/support/reportList">신고 게시판</a></button>
          </div>
        </div>
      </div>
</div>