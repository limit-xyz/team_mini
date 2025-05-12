$(function() {

	// 멤버 삭제 메소드
	$(document).on("click", ".deleteMember", function() {
		var no = $(this).data('no');

		var isDelete = confirm(no + "번 사용자를 삭제하시겠습니까?\n이 명령은 되돌릴 수 없습니다.")

		if (isDelete) {
			data = {
				"no": no
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

						let regDate = dateToString(new Date(member.regDate));
						let ignoreDate = dateToString(new Date(member.ignoreDate));

						var result = "<tr>"
							+ `	<td class="text-center">${member.no}</td>`
							+ `	<td>${member.name}</td>`
							+ "	<td class='text-end'>"
							+ `		<p>${regDate}</p>`
							+ "	</td>"
							+ "	<td class='text-end'>"
							+ ((member.ignore) ? `<p>${ignoreDate}</p>` : "<p>-</p>")
							+ "	</td>"
							+ "	<td class='text-center'>"
							+ `		<button type='button' class='ignoreMember btn btn-warning' data-no="${member.no}"
										data-bs-toggle='modal' data-bs-target='#staticBackdrop'>차단</button>`
							+ `		<button type='button' class='releaseMember btn btn-success' data-no="${member.no}">차단해제</button>`
							+ `		<button type='button' class='deleteMember btn btn-danger'' data-no="${member.no}">삭제</button>`
							+ "	</td>"
							+ "</tr>"

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



	// 멤버 차단 클릭 메소드, 모달 창을 띄움
	$(document).on("click", ".ignoreMember", function() {
		var no = $(this).data('no');
		$("#ignoreDate").val("");
		$("#ignoreNo").text(no);
	});



	// 멤버 차단 메소드, 모달 창에서 submit 되었을 때 작동
	$("#ignoreMemberForm").on("submit", function() {

		var no = $("#ignoreNo").text();
		var ignoreDate = $("#ignoreDate").val();

		if (ignoreDate < 1) {
			alert("1 이상의 숫자를 입력해주세요.")
			$("#ignoreDate").focus();
			return false;
		}

		data = {
			"no": no,
			"date": ignoreDate
		}

		$.ajax({
			"url": "memberIgnore.ajax",
			"data": data,
			"type": "post",
			"dataType": "json",
			"success": function(regData) {

				// 현재 this 가 모달창이라, 위치 찾기
				var $button = $("#tableBody").find(`button[data-no="${no}"]`);
				var $ignoreDate = $button.parent().prev();

				// 가져온 데이터로 새로고침
				$ignoreDate.empty();
				let date = new Date(regData.ignoreDate);
				let strDate = dateToString(date);
				var result = `<p>${strDate}</p>`
				$ignoreDate.append(result);

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
		var $isIgnore = $(this).parent().prev();

		if ($isIgnore.children().text() == "-") {
			alert("차단되지 않은 사용자입니다.");
			return false;
		}

		var no = $(this).data('no');
		var isRelease = confirm(no + "번 사용자의 차단을 해제하시겠습니까?");

		if (isRelease) {

			data = {
				"no": no
			}

			$.ajax({
				"url": "memberRelease.ajax",
				"data": data,
				"type": "post",
				"dataType": "text",
				"success": function() {
					// 차단 해제하고 새로고침
					$isIgnore.empty();
					var result = `<p>-</p>`
					$isIgnore.append(result);
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

function dateToString(date) {
	return date.getFullYear() + "/" + dateModifier(date.getMonth() + 1) + "/" + dateModifier(date.getDate()) + " " +
		dateModifier(date.getHours()) + ":" + dateModifier(date.getMinutes()) + ":" + dateModifier(date.getSeconds());
}