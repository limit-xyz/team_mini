<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miniproject.dao.AdoptionWriteDto" %>
<jsp:useBean id="post" class="com.miniproject.dao.AdoptionWriteDto" scope="request"/>

<div class="row my-5" id="global-content">
	<div class="col">
		<form name ="checkForm" id="checkForm">
			<input type="hidden" name="postId" value="${adopboard.PostId}" id="postId">
			<input type="hidden" name="userId" value="${adopboard.UserId}" id="userId">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<c:if test="${searchOption}">
				<input type="hidden" name="searchColumn" value="${searchColumn}">
				<input type="hidden" name="keyword" value="${keyword}">
				<input type="hidden" name="approvalStatus" value="${approval_status}">
				
			</c:if>				
		</form>
		<div class ="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">게시 글 상세보기</h2>
			</div>
		</div>
		
		</div>
</div>



<h3>${post.title }</h3>
<p>작성자 : ${post.userId }</p>
<p>내용 : ${post.content }</p>
<p>타입 : ${post.adoptionType }</p>
<p>종류 : ${post.animalTypeMain}(${post.animalTypeDetail} })</p>
<p>지역 : ${post.region }</p>
<p>작성일 : ${post.createdAt }</p>
<c:if test="${post.imagePath !=null; }">
<img src="${pageContext.request.contextPath}/uploads/${post.imagePath}" width ="300"/>
</c:if>
<a href="AdoptionDeletService.mvc?PostId=${post.postId} }">삭제<a>
<a href="adoptionUpdateForm.jsp?postId=${post.postId }">수정</a>
