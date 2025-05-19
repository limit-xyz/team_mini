<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- content -->			
		<div class="row my-5" id="global-content">
			<div class="col">					
				<div class="row my-5 text-center">
					<div class="col">				
						<h2 class="fs-3 fw-bold">FAQ 글쓰기</h2>
					</div>
				</div>
				<!-- 폼의 데이터를 전송할 때 application/x-www-urlencoded -->
				<form action="${pageContext.request.contextPath}/support/faqWriteResult" name="writeForm" id="writeForm"
					class="row border-primary g-3" method="post">			
				 	<div class="col-8 offset-2">
				    <label for="title" class="form-label">제 목</label>
				    <input type="text" class="form-control" id="title" name="title">
				  </div>				  
				  <div class="col-8 offset-2">
				    <label for="content" class="form-label">내 용</label>
				    <textarea class="form-control" id="content" name="content" rows="10"></textarea>
				  </div>

					<div class="col-8 offset-2 text-center mt-5">
						<input type="submit" class="btn btn-primary" value="등록하기">
						&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="목록보기"
							onclick="location.href='${pageContext.request.contextPath}/support/faq'">
					</div>
				</form>		
			</div>
		</div>