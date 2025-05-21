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
				<form action="updateProcess.mvc" name="updateForm" id="updateForm"
					class="row border-primary g-3" method="post"
					enctype="multipart/form-data">		
					<input type="hidden" name="no" value="${board.no}">	
					<input type="hidden" name="pageNum" value="${pageNum}">
					<c:if test="${searchOption}">
						<input type="hidden" name="type" value="${type}">
						<input type="hidden" name="keyword" value="${keyword}">						
					</c:if>					
					<div class="col-4 offset-2">
				    <label for="writer" class="form-label">글쓴이</label>
				    <input type="text" class="form-control" id="writer" name="writer"
				    	placeholder="작성자를 입력해 주세요" value="${board.writer}">
				  </div>
					<div class="col-4">
				    <label for="pass" class="form-label">비밀번호</label>
				    <input type="text" class="form-control" id="pass" name="pass">
				  </div>	
				 	<div class="col-8 offset-2">
				    <label for="title" class="form-label">제 목</label>
				    <input type="text" class="form-control" id="title" name="title"
				    	value="${board.title}">
				  </div>
				  <div class="col-8 offset-2">
				    <label for="content" class="form-label">내 용</label>
				    <textarea class="form-control" id="content" name="content" 
				    rows="10">${board.content}</textarea>
				  </div>
				 	<div class="col-8 offset-2">				   
				    <input type="file" class="form-control" id="file1" name="file1">
				  </div>				  
					<div class="col-8 offset-2 text-center mt-5">
						<input type="submit" class="btn btn-primary" value="수정하기">
						&nbsp;&nbsp;
						<!-- 검색 리스트에서 넘어온 경우 다시 검색 리스트로 보내야 함 -->						
						<c:if test="${searchOption}">
							<input type="button" class="btn btn-warning" value="목록보기"
								onclick="location.href='freeList.mvc?pageNum=${pageNum}&type=${type}&keyword=${keyword}'">
						</c:if>	
						<!-- 일반 리스트에서 넘어온 경우 다시 일반 리스트로 보내야함 -->
						<c:if test="${not searchOption}">
							<input type="button" class="btn btn-warning" value="목록보기"
								onclick="location.href='freeList.mvc?pageNum=${pageNum}'">
						</c:if>	
					</div>
				</form>	
			</div>
		</div>


