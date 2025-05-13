<%-- 회원가입 폼 요청 처리 결과를 출력할 View 페이지 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	새로운 5자리 우편번호로 회원 주소를 입력 받기 위해 daum.net에서
	제공하는 우편번호 찾기 API를 사용하였다.
	참고 사이트 : http://postcode.map.daum.net/guide
-->    
<script src=
"https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

 

<div class="row my-5" id="global-content">
	<div class="col">
		<div class="row my-3 text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">회원 정보 입력</h2>
			</div>
		</div>
		<form action="memberJoinResult.member" name="joinForm" method="post" id="joinForm">
			<%-- 
				회원 아이디 중복 검사를 했는지의 정보를 hidden 필드로 저장 
			--%>
			<input type="hidden" name="isIdCheck" id="isIdCheck" value="false"/>
			<div class="row mt-5 mb-3">
				<div class="col-8 offset-2">				
					<label for="name" class="form-label">* 이 름 : </label>
					<input type="text" class="form-control" name="name" id="name">
				</div>				
			</div>
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label for="id" class="form-label">* 아이디 : </label>
					<div class="row">
						<div class="col-6">
							<input type="text" class="form-control" name="id" id="id">
						</div>
						<div class="col-4">
							<input type="button" class="btn btn-warning" id="btnOverlapId" value="중복확인">
						</div>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label for="pass1" class="form-label">* 비밀번호 : </label>
					<input type="password" class="form-control" name="pass1" id="pass1">					
				</div>
			</div>
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label for="pass2" class="form-label">* 비밀번호 확인 : </label>
					<input type="password" class="form-control" name="pass2" id="pass2">
				</div>
			</div>
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label for="zipcode" class="form-label">* 우편번호 : </label>
					<div class="row">
						<div class="col-4">
							<input type="text" class="form-control" name="zipcode" id="zipcode" maxlength="5" readonly>
						</div>
						<div class="col-4">
							<input type="button" class="btn btn-warning" id="btnZipcode" value="우편번호 찾기">
						</div>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label for="address1" class="form-label">* 자택주소 : </label>
					<input type="text" class="form-control" name="address1" id="address1"  readonly>
				</div>	
			</div>
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label for="address2" class="form-label">상세주소 : </label>				
					<input type="text" class="form-control" name="address2" id="address2">
				</div>
			</div>
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label for="emailId" class="form-label">* 이 메 일 : </label>
					<div class="row">
						<div class="col-md-4">
							<input type="text" class="form-control" name="emailId" id="emailId">
						</div> @ 
						<div class="col-md-4">
							<input type="text" class="form-control" name="emailDomain" id="emailDomain">
						</div>
						<div class="col-md-3">
							<select class="form-select" name="selectDomain" id="selectDomain">
								<option>직접입력</option>
								<option selected>네이버</option>
								<option>다음</option>
								<option>한메일</option>
								<option>구글</option>
							</select>
						</div>
					</div>
				</div>	
			</div>
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label for="mobile2" class="form-label">* 휴 대 폰 : </label>
					<div class="row">
						<div class="col-md-3">
							<select class="form-select" name="mobile1" id="mobile1">
								<option>010</option>
								<option>011</option>
								<option>016</option>
								<option>017</option>
								<option>018</option>
								<option>019</option>
							</select>
						</div>-
						<div class="col-md-4">
							<input type="text" class="form-control" name="mobile2" id="mobile2" maxlength="4">
						</div>-
						<div class="col-md-4">
							<input type="text" class="form-control" name="mobile3" id="mobile3" maxlength="4">
						</div>
					</div>
				</div>
			</div>			
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label class="form-label">메일 수신여부 : </label>					
					<div class="row">
						<div class="col-md-3">
							<div class="form-check">
								<input type="radio" class="form-check-input" name="emailGet" id="emailOk" value="true">
								<label class="form-check-label" for="emailOk">수신함</label>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-check">
								<input type="radio" class="form-check-input" name="emailGet" id="emailNo" value="false">
								<label class="form-check-label" for="emailNo">수신안함</label>
							</div>
						</div>	
					</div>					
				</div>	
			</div>
			<div class="row my-3">
				<div class="col-8 offset-2">
					<label for="phone2" class="form-label">자택전화 : </label>
					<div class="row">
						<div class="col-md-3">
							<select class="form-select" name="phone1" id="phone1">
								<option>02</option>
								<option>031</option>
								<option>032</option>
								<option>033</option>
								<option>041</option>
								<option>042</option>
								<option>043</option>
								<option>044</option>
								<option>051</option>
								<option>052</option>
								<option>053</option>
								<option>054</option>
								<option>055</option>
								<option>061</option>
								<option>062</option>
								<option>063</option>
								<option>064</option>
								<option>010</option>
								<option>011</option>
								<option>016</option>
								<option>017</option>
								<option>018</option>
								<option>019</option>
							</select>
						</div> - 
						<div class="col-md-4">
							<input type="text" class="form-control" name="phone2" id="phone2" maxlength="4">
						</div> - 
						<div class="col-md-4">
							<input type="text" class="form-control" name="phone3" id="phone3" maxlength="4">
						</div>
					</div>
				</div>	
			</div>			
			<div class="row mb-3 mt-5">				
				<div class="col-8 offset-2">
					<input type="submit" value="가입하기" class="btn btn-primary">
				</div>
			</div>
		</form>
	</div>
</div>


