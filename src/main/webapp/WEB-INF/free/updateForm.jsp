<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!-- content -->			
<div class="row my-5" id="global-content">
	<div class="col">					
		<div class="row my-5 text-center">
			<div class="col">				
				<h2 class="fs-3 fw-bold">게시 글 수정하기</h2>
			</div>
		</div>
		<form action="updateProcess" name="updateForm" id="updateForm"
			class="row border-primary g-3" method="post"
			enctype="multipart/form-data">		
			<input type="hidden" name="no" value="${board.no}">	
			<input type="hidden" name="pageNum" value="${pageNum}">	
			<input type="hidden" name="type" value="${type}">	
			<input type="hidden" name="keyword" value="${keyword}">	
						
			<div class="col-8 offset-2">
			    <label for="writer" class="form-label">글쓴이</label>
			    <input type="text" class="form-control" id="writer" name="writer" value="${sessionScope.id}" readonly>
		    </div>
		    
		 	<div class="col-8 offset-2">
			    <label for="title" class="form-label">제 목</label>
			    <input type="text" class="form-control" id="title" name="title" value="${board.title}">
		 	</div>			
		 		  
			<div class="col-8 offset-2">
				<label for="content" class="form-label">내 용</label>
				<textarea class="form-control" id="content" name="content" rows="10">${board.content}</textarea>
			</div>
			
		 	<div class="col-8 offset-2">				   
		   		<input type="file" class="form-control" id="file1" name="file1">
		  	</div>
	  
			<div class="col-8 offset-2 text-center mt-5">
				<input type="submit" class="btn btn-primary" value="수정하기">
				&nbsp;&nbsp;
				<input type="button" class="btn btn-primary" value="목록보기"
					onclick="location.href='freeList?pageNum=${pageNum}&type=${type}&keyword=${keyword}'">
			</div>
		</form>	
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/free.js"></script>


