<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="post" class="com.miniproject.dao.AdoptionWriteDto" scope="request" />

<form action="AdoptionUpdateService.mvc" method="post">
<input type = "hidden" name="postId" value="${post.postId}" />
제목 : <input type="text" name="title" value="${post.title }" />
내용 : <textarea type="text" name="content">${post.content } </textarea><br/>
지역 : <input type="text" name ="region" value=${post.region " /}<br/>
<input type ="submit" value="수정 완료"/>

</form>