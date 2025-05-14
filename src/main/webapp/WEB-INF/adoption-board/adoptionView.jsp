<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miniproject.dao.AdoptionWriteDto" %>
<jsp:useBean id="post" class="com.miniproject.dao.AdoptionWriteDto" scope="request"/>

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
