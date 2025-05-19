<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginform.css" />		
			<div class="row my-5" id="global-content">
				<div class="col align-content-center" style="min-height: 600px;">
					<form class="my-5" id="loginForm" action="login" method="post">
							<h2 class="fw-bold">로그인</h2>
								<div id="login">
										<input type="text" id="userId" name="id" class="input-style" placeholder="아이디를 입력해주세요." />
										<input type="password" id="userPass" name="password" class="input-style" placeholder="비밀번호를 입력해주세요."/>
								</div>
									<input type="submit" value="로그인" id="btnLogin" class="login-style"/>
								<div id="searchBtn">
									<p>
										<span>비밀번호 찾기</span> | <span>아이디 찾기</span> | <span>회원가입</span>
									</p>
								</div>
					</form>
				</div>
			</div>