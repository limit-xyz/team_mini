<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

		<!-- content -->			
		<div class="row my-5" id="global-content">
			<div class="col">					
				<div class="row my-5 text-center">
					<div class="col">				
						<h2 class="fs-3 fw-bold">게시 글쓰기</h2>
					</div>
				</div>
				<!-- 폼의 데이터를 전송할 때 application/x-www-urlencoded -->
				<form action="writeProcess.mvc" name="writeForm" id="writeForm"
					class="row border-primary g-3" method="post" 
					enctype="multipart/form-data">			
					<div class="col-4 offset-2">
				    <label for="writer" class="form-label">글쓴이</label>
				    <input type="text" class="form-control" id="writer" name="writer"
				    	placeholder="작성자를 입력해 주세요">
				  </div>
					<div class="col-4">
				    <label for="pass" class="form-label">비밀번호</label>
				    <input type="text" class="form-control" id="pass" name="pass">
				  </div>	
				 	<div class="col-8 offset-2">
				    <label for="title" class="form-label">제 목</label>
				    <input type="text" class="form-control" id="title" name="title">
				  </div>				  
				  <div class="col-8 offset-2">
				    <label for="content" class="form-label">내 용</label>
				    <textarea class="form-control" id="content" name="content" rows="10"></textarea>
				  </div>
				 	<div class="col-8 offset-2">				   
				    <input type="file" class="form-control" id="file1" name="file1">
				  </div>				  
					<div class="col-8 offset-2 text-center mt-5">
						<input type="submit" class="btn btn-primary" value="등록하기">
						&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="목록보기"
							onclick="location.href='freeList.mvc'">
					</div>
				</form>		
			</div>
		</div>


