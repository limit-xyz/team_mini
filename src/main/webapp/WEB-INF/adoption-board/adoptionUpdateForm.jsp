<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="post" class="com.miniproject.dao.AdoptionWriteDto" scope="request" />

<form action="AdoptionUpdateService.mvc" method="post">
<input type = "hidden" name="postId" value="${post.postId}" />
제목 : <input type="text" name="title" value="${post.title }" />
내용 : <textarea type="text" name="content">${post.content } </textarea><br/>
지역 : <input type="text" name ="region" value="${post.region}"/><br/>
<input type ="submit" value="수정 완료"/>
searchColumn
</form>

<!-- content -->			
		<div class="row my-5" id="global-content">
			<div class="col">					
				<div class="row my-5 text-center">
					<div class="col">				
						<h2 class="fs-3 fw-bold">게시 글 수정하기</h2>
					</div>
				</div>
				<form action="AdoptionUpdateService.mvc" name="adopUpdateForm" id="adopUpdateForm"
					class="row border-primary g-3" method="post"
					enctype="multipart/form-data">		
					<input type = "hidden" name="postId" value="${post.postId}" />
					<input type="hidden" name="pageNum" value="${pageNum}">
					<c:if test="${searchOption}">
						<input type="hidden" name="type" value="${type}">
						<input type="hidden" name="keyword" value="${keyword}">						
					</c:if>					
					<div class="col-4 offset-2">
				    <label for="writer" class="form-label">글쓴이</label>
				    <input type="text" class="form-control" id="writer" name="writer"
				    	placeholder="작성자를 입력해 주세요" value="${adopboard.userId}">
				  </div>
				 	<div class="col-8 offset-2">
				    <label for="title" class="form-label">제 목</label>
				    <input type="text" class="form-control" id="title" name="title"
				    	value="${adopboard.title}">
				  </div>
				  <div class="col-8 offset-2">
				    <label for="content" class="form-label">내 용</label>
				    <textarea class="form-control" id="content" name="content" 
				    rows="10">${adopboard.content}</textarea>
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
								onclick="location.href='adopboardList.mvc?pageNum=${pageNum}&type=${searchColumn}&keyword=${keyword}'">
						</c:if>	
						<!-- 일반 리스트에서 넘어온 경우 다시 일반 리스트로 보내야함 -->
						<c:if test="${not searchOption}">
							<input type="button" class="btn btn-warning" value="목록보기"
								onclick="location.href='adopboardList.mvc?pageNum=${pageNum}'">
						</c:if>	
					</div>
				</form>
				
						
			</div>
		</div>
	
