<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
    <!-- content -->
    <div>
    <h2> 입양/분양 게시글 작성</h2>
    </div>
    
    <!-- 폼 데이터를 전송 -->
    <h3>입양/분양 게시글 작성</h3>
    <form action="AdoptionWriterServlet" method="post" enctype="multipart/form-data">
    <div>
		<label>입양/분양 선택 : </label>
	    <select name="type" required>
	    	<option value="입양">입양</option>
	    	<option value="분양">분양</option>
	    </select>	    
    </div><br><br>
    <div>
    <label>동물 종류 : </label>
	    <select name="animalTypeMain" id="animalTypeMain" onchange="updateDetailOption()" required>
	    	<option value="강아지">강아지</option>
	    	<option value="고양이">고양이</option>
	    	<option value="포유류">포유류</option>
	    	<option value="새">새</option>
	    	<option value="파충류">파충류</option>
	    	<option value="어류">어류</option>
	    </select>	    
    </div><br><br>
    
    <label>세부 유형 : </label>
    	<select name="animalTypeDetail" id="animalTypeDetail" required>
    	<option value="">동물의 종류를 선택해주세요</option>
    	</select><br><br>
    
    <label>지역 : </label>
    <input type="text" name="region" required><br><br>
    
    <label>제목 : </label>
    <input type="text" name="title" required><br><br>
    
    <label>내용 : </label>
    <textarea name="content" required>내용을 입력해주세요</textarea><br><br>
    
    <label>이미지 업로드 : </label>
    <input type="file" name="image"><br><br>
    
    <input type ="submit" value="등록">
    
    </form>
    
    <script>
    	const detailOption = {
    			"강아지": ["대형견","중형견", "소형견"],
    			"고양이": ["단모종", "장모종", "소형묘"],
    			"포유류": ["토끼", "햄스터", "고슴도치", "쥐 / 랫","기타"],
    			"새": ["앵무새","카나리아","잉꼬","사랑새","기타"],
    			"파충류":["도마뱀","거북이","이구아나","뱀"],
    			"어류":["1자 어항류","2자 어항류", "3자 어항류", "4자 어항류", "5자 혹은 6자 대형어항류", "갑각류", "조개류", "달팽이류"]
    	}
    
    function updateDetailOptions(){
    		const main = document.getElementById("animalTypeMain")
    		const detail = document.getElementById("animalTypeDetail")
    		
    		detail.innerHTML ="";
    		
    		if(main && detailOptions[main]){
    			detailOptions[main].forEach(function(option)
    				const opt = document.createElement("option");
    				opt.value = option;
    				opt.text = option;
    				detail.appendChild(opt);
    					
    		
    			});
    		} else{
    			const opt = document.createElement("option");
    			opt.text = "동물의 종류를 선택해주세요"
    			detail.appendChild(opt);
    		}    		
    	}
   </script>
  </html>