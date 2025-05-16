<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- content -->
<div class="row my-5" id="global-content">
	<div class="col">
	
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">게시글 수정하기</h2>
			</div>
		</div>
		
		<form name="diaryUpdateForm" action="diaryUpdateProcess?pageNum=${pageNum}" id="diaryUpdateForm" class="row g-3 border-primary" 
			method="post" enctype="multipart/form-data">

			<input type="hidden" name="no" value="${diary.no}">
			<input type="hidden" name="memberId" value="${sessionScope.id}">
			<input type="hidden" name="pageNum" value="${pageNum}">
			
			<div class="col-8 offset-md-2">
			    <label for="title" class="form-label">제 목</label>
			    <input type="text" class="form-control" name="title"  id="title"  value="${diary.title}">
	  		</div>
	  		
	  		<div class="col-8 offset-md-2">
			    <label for="petName" class="form-label">누구의 다이어리를 작성하시나요?</label>
			    <input type="text" class="form-control" name="petName"  id="petName" value="${diary.petName}"
			    	placeholder="반려동물의 이름을 입력해 주세요">
	  		</div>
	  		
			<div class="col-8 offset-md-2">
			    <label for="content" class="form-label">내 용</label>
			    <textarea class="form-control" name="content" id="content" rows="10">
			    	${diary.content}
			    </textarea>
	  		</div>
	  		
	  		<div class="col-8 offset-md-2">
			    <label for="photo" class="form-label">사 진 (정사각형 모양에 특화되어있음)</label>
			    <input type="file" class="form-control" name="photo"  id="photo" accept="image/*" >
	  		</div>	
	  				  			
	  		<div class="col-8 offset-md-2 text-center mt-5">
			   <input type="submit" value="수정하기" class="btn btn-primary"/>
					&nbsp;&nbsp;
				<input type="button" value="목록보기" onclick="location.href='diaryList?pageNum=${pageNum}'" class="btn btn-primary"/>
	  		</div>	
	  		
		</form>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/diary.js"></script>

    