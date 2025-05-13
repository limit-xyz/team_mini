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
							+ ` <td class="text-center">${member.role.toUpperCase()}</td>`
							+ " <td class='text-center'>";

						if (member.role != "admin") {
							result +=
								`<button type="button" class="banMember btn btn-warning me-2" data-user-id="${member.id}"`
								+ `data-bs-toggle="modal" data-bs-target="#staticBackdrop">임시차단</button>`
								+ `<button type="button" class="releaseMember btn btn-success me-2" data-user-id="${member.id}">차단해제</button>`
								+ `<button type="button" class="deleteMember btn btn-danger" data-user-id="${member.id}">유저삭제</button>`;
						}


						result += " </td>";
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
				
				console.log(regData);

				// 현재 this 가 모달창이라, 위치 찾기
				var $button = $("#tableBody").find(`button[data-user-id="${userId}"]`);
				var $banDate = $button.parent().parent().find($(".banDate"));

				// 가져온 데이터로 새로고침
				$banDate.empty();
				let date = new Date(regData.banDate);
				let strDate = dateToString(date, true);
				var result = `<p>${strDate}</p>`
				$banDate.append(result);

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
		var isRelease = confirm(userId + " 의 차단을 해제하시겠습니까?");

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