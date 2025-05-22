<%--
	회원 가입시 아이디 중복검사 요청에 대한 처리 결과를 출력할 View 페이지
	이 페이지는 새창으로 실행되고 중복 아이디 체크를 할 수 있는 폼을 제공한다.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/member.js"></script>
<title>중복 아이디 체크</title>
</head>
<body>
	<div class="row my-5" id="global-content">
		<c:choose>
			<c:when test="${ overlap }" >
			<div class="col">
				<div class="row text-center">
					<div class="col">
						<h2 class="fs-3 fw-bold">사용할 수 없는 아이디</h2>
					</div>
				</div>
				<div class="row my-3 text-center">
					<div class="col">
						입력하신 ${id}는 이미 사용 중인 아이디 입니다.
					</div>
				</div>
				<div class="row my-3">
					<div class="col  text-center">
						다른 아이디를 선택해 주세요
					</div>
				</div>				
				<form action="idCheck" name="idCheckForm" 
						method="post" id="idCheckForm" class="row mt-5">			
					<div class="col-10 offset-1">
						<div class="input-group">
							<span class="input-group-text">* 아이디 : </span>								
							<input type="text" class="form-control" name="id" id="checkId">
							<input type="submit" class="btn btn-primary" value="중복확인">
						</div>						
					</div>
				</form>		
			</div>
			</c:when>	
			<c:otherwise>
				<div class="col">
					<div class="row text-center">
						<div class="col">
							<h2 class="fs-3 fw-bold">사용할 수 있는 아이디</h2>
						</div>
					</div>
					<div class="row my-3 text-center">
						<div class="col">
							입력하신 ${id}는 사용할 수 있는 아이디 입니다.
						</div>
					</div>
					<div class="row mt-5">
						<div class="col  text-center">
							<input type="button" value="${ id }을(를) 아이디로 사용하기" 
									id="btnIdCheckClose" data-id-value="${ id }" class="btn btn-primary"/>
						</div>
					</div>	
				</div>	
					<script>
					/*  
						// 새창으로 띄운 아이디 중복 확인 창에서 "아이디 사용 버튼" 이벤트 처리
						let btn  = document.querySelector("#btnIdCheckClose");
					
						btn.addEventListener("click", function() {
								console.log("vanila this : " + this);
							});
					
						btn.addEventListener("click", (e) => {
							console.log("vanila arrow this : " + this + ", " + e.target);
						});	
						*/
					</script>		
			</c:otherwise>
		</c:choose>		
	</div>

</body>
</html>

