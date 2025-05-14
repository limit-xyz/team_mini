$(function() {

	// 멤버 삭제 메소드
	$(document).on("click", ".deleteMember", function() {
		var userId = $(this).data('userId');

		var isDelete = confirm("유저 " + userId + " 를 삭제하시겠습니까?\n이 명령은 되돌릴 수 없습니다.")

		if (isDelete) {
			data = {
				"id": userId
			}

			$.ajax({
				"url": "memberDelete.ajax",
				"data": data,
				"type": "post",
				"dataType": "json",
				"success": function(regData) {
					// 삭제 후, 받아온 데이터로 새로고침

					$tbody = $("#tableBody");
					$tbody.empty();

					$.each(regData, function(i, member) {

						let regDate = dateToString(new Date(member.regDate), false);
						let birthDate = dateToString(new Date(member.birthDate), false);
						let banDate = dateToString(new Date(member.banDate), true);

						var result =
							"<tr>"
							+ `	<td class="text-center">${member.id}</td>`
							+ `	<td class="text-center">${member.name}</td>`
							+ `	<td class="text-center">${member.gender}</td>`
							+ `	<td class="text-center">${member.mobile}</td>`
							+ `	<td class="text-center">${member.address}</td>`
							+ `	<td class='text-end'>${regDate}</td>`
							+ `	<td class='text-end'>${birthDate}</td>`
							+ " <td class='banDate text-end'>"
							+ ((member.ban) ? `<p>${banDate}</p>` : "<p>-</p>")
							+ " </td>"

							+ `	<td class='userRole' data-user-id='${member.id}'>`
							+ `		<select class='userRoleSelect form-select' ${member.role == 'admin' ? 'disabled' : ''}>`
							+ `			<option value='admin' ${member.role == 'admin' ? 'selected' : 'hidden'}>ADMIN</option>`
							+ `			<option value='beautician' ${member.role == 'beautician' ? 'selected' : ''}>BEAUTICIAN</option>`
							+ `			<option value='doctor' ${member.role == 'doctor' ? 'selected' : ''}>DOCTOR</option>`
							+ `			<option value='expert' ${member.role == 'expert' ? 'selected' : ''}>EXPERT</option>`
							+ `			<option value='seller' ${member.role == 'seller' ? 'selected' : ''}>SELLER</option>`
							+ `			<option value='user' ${member.role == 'user' ? 'selected' : ''}>USER</option>`
							+ "		</select>"
							+ "	</td>"

							+ " <td class='text-center'>";

						if (member.role != "admin") {
							result +=
								`<button type="button" class="banMember btn btn-warning me-2" data-user-id="${member.id}"`
								+ `data-bs-toggle="modal" data-bs-target="#staticBackdrop">임시차단</button>`
								+ `<button type="button" class="releaseMember btn btn-success me-2" data-user-id="${member.id}">차단해제</button>`
								+ `<button type="button" class="deleteMember btn btn-danger" data-user-id="${member.id}">유저삭제</button>`;
						}


						result += " </td>";
						result += `<td class="banReason d-none">${member.banReason}</td>`;
						result += "</tr>";

						$tbody.append(result);
					});
				},
				"error": function() {
					console.log("error")
				}
			});
		}

		return false;
	});



	// 멤버 역할 변경 메소드
	$(document).on("change", ".userRoleSelect", function() {
		var $this = $(this);
		var role = $(this).val();
		var userId = $(this).parent().data("userId");

		var isRoleChange = confirm(userId + " 의 역할을 " + $(this).val().toUpperCase() + " 로 바꾸시겠습니까?");

		if (isRoleChange) {

			data = {
				"id": userId,
				"role": role,
				"change": true
			}

			$.ajax({
				"url": "memberRole.ajax",
				"data": data,
				"type": "post",
				"dataType": "json",
				"success": function() {
					alert("변경 완료");
				},
				"error": function() {
					console.log("error")
				}
			});
		}

		else {
			data = {
				"id": userId,
				"role": "",
				"change": false
			}

			$.ajax({
				"url": "memberRole.ajax",
				"data": data,
				"type": "post",
				"dataType": "json",
				"success": function(resData) {
					$this.val(resData);
				},
				"error": function() {
					console.log("error")
				}
			});
		}


		return false;
	});


	// 멤버 차단 클릭 메소드, 모달 창의 내용을 초기화하고 띄움
	$(document).on("click", ".banMember", function() {
		$("#banDate").val("");
		$("#banReason").val("");

		var id = $(this).data('userId');
		$("#banUserId").text(id);
	});



	// 멤버 차단 메소드, 모달 창에서 submit 되었을 때 작동
	$("#banMemberForm").on("submit", function() {

		var userId = $("#banUserId").text();
		var banDate = $("#banDate").val();
		var banReason = $("#banReason").val();


		if (banDate < 1) {
			alert("1 이상의 숫자를 입력해주세요.")
			$("#banDate").focus();
			return false;
		}

		if (banReason.length < 1) {
			alert("차단 사유를 입력해주세요.")
			$("#banReason").focus();
			return false;
		}

		data = {
			"id": userId,
			"date": banDate,
			"reason": banReason
		}

		$.ajax({
			"url": "memberBan.ajax",
			"data": data,
			"type": "post",
			"dataType": "json",
			"success": function(regData) {

				// 현재 this 가 모달창이라, 위치 찾기
				var $button = $("#tableBody").find(`button[data-user-id="${userId}"]`);
				var $banDate = $button.parent().parent().find($(".banDate"));
				var $banReason = $button.parent().parent().find($(".banReason"));

				// 가져온 데이터로 새로고침
				$banDate.empty();
				let date = new Date(regData.banDate);
				let strDate = dateToString(date, true);
				var result = `<p>${strDate}</p>`
				$banDate.append(result);

				$banReason.text(regData.banReason);

				// 모달창 닫기
				var modal = bootstrap.Modal.getInstance($('#staticBackdrop')[0]);
				modal.hide();
			},
			"error": function() {
				console.log("error")
			}
		});

		return false;
	});



	// 멤버 차단해제 메소드
	$(document).on("click", ".releaseMember", function() {
		var $isBan = $(this).parent().parent().find($(".banDate"));

		if ($isBan.children().text() == "-") {
			alert("차단되지 않은 사용자입니다.");
			return false;
		}

		var userId = $(this).data('userId');
		var banReason = $(this).parent().parent().find($(".banReason")).text();
		var isRelease = confirm(userId + " 의 차단을 해제하시겠습니까?\n\n[차단 사유] \n" + banReason);

		if (isRelease) {

			data = {
				"id": userId
			}

			$.ajax({
				"url": "memberRelease.ajax",
				"data": data,
				"type": "post",
				"dataType": "text",
				"success": function() {
					// 차단 해제하고 새로고침
					$isBan.empty();
					var result = `<p>-</p>`
					$isBan.append(result);
				},
				"error": function() {
					console.log("error")
				}
			});
		}

		return false;
	});
});


function dateModifier(str) {
	if (str < 10)
		str = "0" + str;
	return str;
};

function dateToString(date, isHour) {
	if (isHour) {
		return date.getFullYear() + "/" + dateModifier(date.getMonth() + 1) + "/" + dateModifier(date.getDate()) + " " +
			dateModifier(date.getHours()) + ":" + dateModifier(date.getMinutes()) + ":" + dateModifier(date.getSeconds());
	}

	else {
		return date.getFullYear() + "/" + dateModifier(date.getMonth() + 1) + "/" + dateModifier(date.getDate());
	}

}



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
		
		url = "idCheck.member?id=" + id;
		
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


