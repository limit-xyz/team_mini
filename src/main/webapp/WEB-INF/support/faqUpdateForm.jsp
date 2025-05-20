<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- content -->			
		<div class="row my-5" id="global-content">
			<div class="col">
				<div class="row my-5 text-center">
					<div class="col">
						<h2 class="fs-3 fw-bold">FAQ 글수정</h2> 
					</div>
				</div>
				<form action="${pageContext.request.contextPath}/support/faqUpdateResult" name="UpdateForm" id="UpdateForm"
					class="row border-primary g-3" method="post">
					<input type="hidden" name="faqNo" value="${faq.faqNo}"> 

				 	<div class="col-8 offset-2">
				    <label for="title" class="form-label">제 목</label>
				    <input type="text" class="form-control" id="title" name="title" value="${faq.faqTitle}">
				  </div>
				  <div class="col-8 offset-2">
				    <label for="content" class="form-label">내 용</label>
				    <textarea class="form-control" id="content" name="content" rows="10">${faq.faqContent}</textarea>
				  </div>
					
					<div class="col-8 offset-2 text-center mt-5">
						<input type="submit" class="btn btn-primary mx-2" value="수정하기"> 
						<input type="button" class="btn btn-primary mx-2" value="목록보기"
							onclick="location.href='${pageContext.request.contextPath}/support/faq'">
					</div>
				</form>
			</div>
		</div>