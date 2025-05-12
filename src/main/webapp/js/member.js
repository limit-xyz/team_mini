// DOM이 준비되면 실행될 콜백 함수 
$(function() {	
	
	$("#btnPassCheck").on("click", function() {
		// id, pass을 읽어와서 서버로 요청을 보낼 때 같이 보낸다.
		let userId = $("#userId").val();
		let oldPass = $("#oldPass").val();
		if($.trim(oldPass).length <= 0) {
			alert("기존 비밀번호를 입력해 주세요");
			return false;
		}
		
		let data = "id=" + userId + "&pass=" + oldPass;
		
		$.ajax({
			"url": "passCheck.ajax",
			"data": data,
			"type": "get",
			"dataType": "json",
			"success": function(resData) {
				// resData => {"result": "10"}
				console.log(resData);
				if(resData.result == -1) {
					alert(userId + "는 존재하지 않는 아이디");
				} else if(resData.result == 0) {
					alert("비밀번호가 틀립니다.");
					$("#oldPass").val("");
					$("#oldPass").focus();
					
				} else if(resData.result == 1) {
					alert("비밀번호가 확인되었습니다.");
					$("#btnPassCheck").prop("disabled", true);
					$("#oldPass").attr("readonly", true);
					$("#pass1").focus();
				}
			},
			"error": function() {
				console.log("error");
			}
		});
		
	});
	
	
	$("#joinForm").on("submit", function() {
		return joinFormCheck(true);
	});
	
	$("#memberUpdateForm").on("submit", function() {
		return joinFormCheck(false);
	});
	
	$("#selectDomain").on("change", function() {
		let str = $(this).val();
		if(str == "직접입력") {
			$("#emailDomain").val("");
			$("#emailDomain").attr("readonly", false);
			
		} else if(str == "네이버") {
			$("#emailDomain").val("naver.com");	
			$("#emailDomain").attr("readonly", true);
			
		}	else if(str == "다음") {
			$("#emailDomain").val("daum.net");	
			$("#emailDomain").attr("readonly", true);
						
		}	else if(str == "한메일") {
			$("#emailDomain").val("hanmail.net");	
			$("#emailDomain").attr("readonly", true);			
		} else if(str == "구글") {
			$("#emailDomain").val("gmail.com");	
			$("#emailDomain").attr("readonly", true);					
		}
		
		
	});
		
	$("#btnIdCheckClose").on("click", function() {				
		let id = $(this).attr("data-id-value");		
		opener.document.joinForm.id.value = id;
		opener.document.joinForm.isIdCheck.value = true;
		window.close();
	});	
	
	// 새 창으로 띄운 아이디 입력 폼 유효성 검사
	$("#idCheckForm").on("submit", function() {
		let id = $("#checkId").val();
		
		if(id.length < 5) {
			alert("아이디를 5자 이상 입력해 주세요");
			return false;
		}
	});
	
	$("#btnOverlapId").on("click", function() {
		
		let id = $("#id").val();
		if(id.length < 5) {
			alert("아이디를 5자 이상 입력해 주세요");
			return false;
		}
		
		url = "overlapIdCheck.mvc?id=" + id;
		
		// 새 창을 띄워서 서버로 요청을 보낸다.
		window.open(url, "idCheck", "toolbar=no, scrollbars=no, resizeable=no, " 
			+"status=no, menubar=no, width=500, height=400");
		
	});
		
	$("#userId").on("keyup", function() {
		// 영문, 숫자만 입력되도록 abDkㅇ - 정규표현식
		// aBDC123ㅇDFDSFㅇ,new RegExp()  "dsfdsf"  
		let regExp = /[^a-zA-Z0-9]/gi
		if(regExp.test($(this).val())) {
			alert("영문 대소문자, 숫자만 입력 가능");
			$(this).val($(this).val().replace(regExp, ""));
		}
	});
	
	$("#pass1").on("keyup", inputCharReplace);
	$("#pass2").on("keyup", inputCharReplace);
	$("#emailId").on("keyup", inputCharReplace);
	$("#emailDomain").on("keyup", inputEmailDomainReplace);
	
	$("#btnZipcode").click(findZipcode);
		
	$("#loginForm").submit(function() {
		var id = $("#userId").val();
		var pass = $("#userPass").val();
		
		if(id.length <= 0) {
			alert("아이디가 입력되지 않았습니다.\n아이디를 입력해주세요");
			$("#userId").focus();
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호가 입력되지 않았습니다.\n비밀번호를 입력해주세요");
			$("#userPass").focus();
			return false;
		}
	});
}); // end $(function() {	})


// 우편번호 찾기 버튼이 클릭되면 실행할 함수
function findZipcode() { // 카카오 우편번호 API를 사용
	new daum.Postcode({
	    oncomplete: function(data) {
	        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	        // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	        var addr = ''; // 주소 변수
	        var extraAddr = ''; // 참고항목 변수
						        
					addr = data.roadAddress;

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
              extraAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if(data.buildingName !== '' && data.apartment === 'Y'){
              extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if(extraAddr !== ''){
              extraAddr = ' (' + extraAddr + ')';
          }
					
					addr += extraAddr;			
					$("#zipcode").val(data.zonecode);
					$("#address1").val(addr);      
					$("#address2").focus();
	    }
	}).open();
	
}

function joinFormCheck(isJoinForm) {
	let name = $("#name").val();
	let id = $("#id").val();
	let pass1 = $("#pass1").val();
	let pass2 = $("#pass2").val();
	let zipcode = $("#zipcode").val();
	let address1 = $("#address1").val();
	let emailId = $("#emailId").val();
	let emailDomain = $("#emailDomain").val();
	let mobile2 = $("#mobile2").val();
	let mobile3 = $("#mobile3").val();
	
	if(name == "") {
		alert("이름을 입력해 주세요");
		return false;
	}
	
	if(id.length <= 0) {
		alert("아이디를 입력해 주세요");
		return false;
	}
	
	if(isJoinForm) {
		let isIdCheck = $("#isIdCheck").val();
		if(isIdCheck == 'false') {
			alert("아이디 중복확인을 해 주세요");
			return false;
		}
	}
	
	if(pass1.length <= 0) {
		alert("비밀번호를 입력해 주세요");
		return false;
	}
	
	if(pass2.length <= 0) {
		alert("비밀번호 확인을 입력해 주세요");
		return false;
	}
	
	if(zipcode.length <= 0) {
		alert("우번번호를 입력해 주세요");
		return false;
	}
	
	if(address1.length <= 0) {
		alert("주소를 입력해 주세요");
		return false;
	}
	
	if(emailId.length <= 0) {
		alert("이메일 아이디를 입력해 주세요");
		return false;
	}

	if(emailDomain.length <= 0) {
		alert("이메일 도메인을 입력해 주세요");
		return false;
	}	
	
	if(mobile2.length <= 0 || mobile3.length <= 0) {
		alert("휴대폰 번호를 입력해 주세요");
		return false;
	}		
	
}




function inputCharReplace() {
	let regExp = /[^a-zA-Z0-9]/gi
	if(regExp.test($(this).val())) {
		alert("영문 대소문자, 숫자만 입력 가능");
		$(this).val($(this).val().replace(regExp, ""));
	}
}

function inputEmailDomainReplace() {	
	let regExp = /[^a-z0-9\.]/gi	
	if(regExp.test($(this).val())) {
		alert("이메일 도메인은 영문 소문자, 숫자, 점(.)만 입력 가능");
		$(this).val($(this).val().replace(regExp, ""));
	}
}



