<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<meta charset="UTF-8">
<style>
body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        div {
            margin-bottom: 15px;
        }
        h2, h3 {
            text-align: center;
        }
        form {
            background-color: #fff;
            padding: 30px;
            margin: 20px auto;
            width: 60%;
            max-width: 800px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], select, textarea {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 1.1em;
            box-sizing: border-box;
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        input[type="file"] {
            margin-bottom: 15px;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        input[type="submit"], #cancel-button {
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
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        #cancel-button {
            background-color: #f44336;
        }
        #cancel-button:hover {
            background-color: #d32f2f;
        }
        .error-message {
            color: red;
            margin-bottom: 15px;
        }
        small {
            display: block;
            font-size: 0.8em;
            color: #777;
            margin-top: 5px;
        }
        #userId {
            background-color: #e9ecef; 
            cursor: not-allowed; 
            border: 1px solid #ddd;
        }
       
        #imagePreview {
            max-width: 100%;
            height: auto;
            margin-top: 10px;
            border-radius: 6px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
</style>

<body>
    <!-- content -->
    <div>
    <h2> 입양/분양 게시글 작성</h2>
    </div>
    
    <!-- 폼 데이터를 전송 -->
    <h3> 입양 / 분양 게시글 쓰기</h3>
    <form action="${pageContext.request.contextPath}/AdoptionWrite.mvc" method="post" enctype="multipart/form-data">
    <div>
     <label for="userId">작성자 ID : <input type="text" name="userId" id="userId" value="${sessionScope.id}" readonly></label><br/>
    </div>
    
    <div>
		<label for="type">입양/분양 선택 : </label>
	    <select name="type" required>
	    	<option value="입양">입양</option>
	    	<option value="분양">분양</option>
	    </select>	    
    </div>
    
    <div>
	    <label for="animalTypeMain">동물 종류 : </label>
		    <select name="animalTypeMain" id="animalTypeMain" onchange="updateDetailOption()" required>
		    	<option value="강아지">강아지</option>
		    	<option value="고양이">고양이</option>
		    	<option value="포유류">포유류</option>
		    	<option value="새">새</option>
		    	<option value="파충류">파충류</option>
		    	<option value="어류">어류</option>
		    </select>	    
    </div>
    
    <div>
    	<label for="animalTypeDetail">세부 유형 : </label>
    		<select name="animalTypeDetail" id="animalTypeDetail" required>
    			<option value="">동물의 종류를 선택해주세요</option>
    		</select>
    </div>
    
   <div>
	    <label for="region">지역 : </label>
	    <input type="text" name="region" > ※ 현재 서비스 지역은 서울지역에 한하여 제공되고 있습니다.<br/>
   </div>
    
   <div>
    		<label for="title">제목 : </label><input type="text" name="title" id="title" required></label>
   </div>
   
   <div>
   		<label for="content">내용 : <textarea name="content" rews="6" cols="50" placeholder="내용을 입력하세요" required></textarea>
   </div>
    
    <div>
	    <label for="imagePath">이미지 업로드 : 
	    <input type="file" name="imagePath"></label><br/>
    </div>
    
    <div class="button-container">
	    <input type ="submit" value="등록" id='submit'>
    	<button type="button" id="cancel-button"> 취소</button>
    </div>
    </form>
    
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
    	
    	 document.getElementById("cancel-button").addEventListener("click", function(){
    	        if(confirm("게시글 등록을 취소하시겠습니까?")){
    	        		location.href = "${pageContext.request.contextPath}/AdoptionList.mvc" ;
    	        	}
    	        });
    	
    	    // 이미지 미리보기 스크립트
        const imageInput = document.getElementById('imagePath');
        const imagePreview = document.getElementById('imagePreview');

        imageInput.addEventListener('change', function() {
            const file = this.files[0];
            const reader = new FileReader();

            reader.onload = function(e) {
                imagePreview.innerHTML = `<img id="imagePreviewImg" src="${e.target.result}" alt="미리보기">`;
            };

            if (file) {
                if (file.type.startsWith('image/')) {
                    reader.readAsDataURL(file);
                } else {
                    imagePreview.innerHTML = '<p>이미지 파일만 선택해주세요.</p>';
                    imageInput.value = ''; // 파일 선택 취소
                }
            } else {
                imagePreview.innerHTML = ''; // 파일 선택 취소 시 미리보기 영역 초기화
            }
        });
        
       
        
   </script>
   </body>
  </html>