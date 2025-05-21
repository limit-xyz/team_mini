<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="post" class="com.miniproject.dao.AdoptionWriteDto" scope="request" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>

<style>
/* write 폼과 동일한 CSS 복사 */
.lightform {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
}
.lightform div {
    margin-bottom: 15px;
}
.lightform h2, h3 {
    text-align: center;
}
.lightform form {
    background-color: #fff;
    padding: 30px;
    margin: 20px auto;
    width: 60%;
    max-width: 800px;
    border-radius: 12px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}
.lightform label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
}
.lightform input[type="text"], select, textarea {
    width: calc(100% - 20px);
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ddd;
    border-radius: 6px;
    font-size: 1.1em;
    box-sizing: border-box;
}
.lightform textarea {
    resize: vertical;
    min-height: 100px;
}
.lightform input[type="file"] {
    margin-bottom: 15px;
}
.button-container {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
}
.lightform input[type="submit"], #cancel-button {
    background-color: #4caf50;
    color: white;
    padding: 12px 25px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    width: 48%;
    box-sizing: border-box;
    font-size: 1.1em;
    transition: background-color 0.3s ease;
}
.lightform input[type="submit"]:hover {
    background-color: #45a049;
}
#cancel-button {
    background-color: #f44336;
}
#cancel-button:hover {
    background-color: #d32f2f;
}
#error-message {
    color: red;
    margin-bottom: 15px;
}
#userId {
    background-color: #e9ecef;
    cursor: not-allowed;
    border: 1px solid #ddd;
}
</style>

<div class="lightform">
<!-- content -->			
		
						<h2>작성 게시글 수정</h2>
					</div>
				</div>
				<form action="${pageContext.request.contextPath}/adoption/AdoptionUpdateService" 
				 method="post" enctype="multipart/form-data">		
					
					<input type = "hidden" name="postId" value="${post.postId}" />
					<input type = "hidden" name="userId" value="${post.userId}" />
				<div>
					<label for="userId">작성자 ID:</label>
					<input type="text" id="userId" value="${post.userId}" readonly>
				</div>
				
				<div>
					<label for="adoptionType">입양/분양 선택 : </label>
					<select name="adoptionType" id="adoptionType" required>
						<option value="입양" ${post.adoptionType == '입양' ? 'selected' : ''}> 입양</option>
						<option value="분양" ${post.adoptionType == '분양' ? 'selected' : ''}> 입양</option>
					</select>
				</div>
				
				<div>
					<lable for="approvalStatus">진행 상태 : </lable>
						<select name="approvalStatus" required>
							<option value="대기중"${post.approvalStatus == '대기중' ? 'selected' : ''}>대기중</option>
							<option value="진행중"${post.approvalStatus == '진행중' ? 'selected' : ''}>진행중</option>
							<option value="분양 완료"${post.approvalStatus == '분양 완료' ? 'selected' : ''}>분양 완료</option>
							<option value="입양 완료"${post.approvalStatus == '입양 완료' ? 'selected' : ''}>입양 완료</option>
						</select>
				</div>
				
				<div>
					<label for="animalTypeMain">동물 종류 : </label>
						<select name="animalTypeMain" id="animalTypeMain" onchange="updateDetailOption()" required>
							<option value="강아지" ${post.animalTypeMain == '강아지' ? 'selected' : ''}>강아지</option>
							<option value="고양이" ${post.animalTypeMain == '고양이' ? 'selected' : ''}>고양이</option>
							<option value="포유류" ${post.animalTypeMain == '포유류' ? 'selected' : ''}>포유류</option>
							<option value="새" ${post.animalTypeMain == '새' ? 'selected' : ''}>새</option>
							<option value="파충류" ${post.animalTypeMain == '파충류' ? 'selected' : ''}>파충류</option>
							<option value="어류" ${post.animalTypeMain == '어류' ? 'selected' : ''}>어류</option>
						</select>
				</div>
				
	    	<div>
            <label for="animalTypeDetail">세부 유형 : </label>
            <select name="animalTypeDetail" id="animalTypeDetail" required>
                <option value="${post.animalTypeDetail}">${post.animalTypeDetail}</option>
            </select>
        </div>
				
			    <div>
            <label for="region">지역 : </label>
            <input type="text" name="region" value="${post.region}" />
        </div>
				
							
				<div>
            <label for="title">제목 : </label>
            <input type="text" name="title" id="title" value="${post.title}" required />
        </div>
				
				  <div>
            <label for="content">내용 : </label>
            <textarea name="content" id="content" rows="6" required>${fn:trim(post.content)}</textarea>
        </div>
			
			 <div>
            <label for="imagePath">이미지 업로드 : </label>
            <c:if test="${not empty post.imagePath}">
                기존 파일: <a href="${pageContext.request.contextPath}/AdoptionDownload?fileName=${post.imagePath}">${post.imagePath}</a><br/>
            </c:if>
            <input type="file" name="imagePath" id="imagePath" />
        </div>
			
        <div class="button-container">
            <input type="submit" value="수정 완료" />
            <button type="button" id="cancel-button">취소</button>
        </div>
    </form>
</div>

<script>
const detailOption = {
    "강아지": ["대형견","중형견", "소형견"],
    "고양이": ["단모종", "장모종", "소형묘"],
    "포유류": ["토끼", "햄스터", "고슴도치", "쥐 / 랫","기타"],
    "새": ["앵무새","카나리아","잉꼬","사랑새","기타"],
    "파충류":["도마뱀","거북이","이구아나","뱀"],
    "어류":["1자 어항류","2자 어항류", "3자 어항류", "4자 어항류", "5자 혹은 6자 대형어항류", "갑각류", "조개류", "달팽이류"]
};

function updateDetailOption() {
    const main = document.getElementById("animalTypeMain");
    const detail = document.getElementById("animalTypeDetail");

    detail.innerHTML = "";

    if (main && detailOption[main.value]) {
        detailOption[main.value].forEach(function(option){
            const opt = document.createElement("option");
            opt.value = option;
            opt.text = option;
            if(option === '${post.animalTypeDetail}') {
                opt.selected = true;
            }
            detail.appendChild(opt);
        });
    } else {
        const opt = document.createElement("option");
        opt.value = "";
        opt.text = "동물의 종류를 선택해주세요";
        detail.appendChild(opt);
    }
}

window.onload = updateDetailOption;

document.getElementById("cancel-button").addEventListener("click", function() {
    if (confirm("수정을 취소하시겠습니까?")) {
        location.href = "${pageContext.request.contextPath}/adoption/AdoptionDetail?postId=${post.postId}";
    }
});
</script>
