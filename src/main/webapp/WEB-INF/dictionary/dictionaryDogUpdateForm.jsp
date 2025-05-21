<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- content -->
<div class="row my-5" id="global-content">
	<div class="col">
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">펫과사전 수정</h2>
			</div>
		</div>  
		<form action="dictionaryUpdateProcess?pageNum=${pageNum}&type=dog" id="dictionaryUpdateForm"  class="row g-3 border-primary"
			method="post" enctype="multipart/form-data">
			
			<input type="hidden" name="animalId" value="${dog.id}">
			
	  		<div class="col-4 offset-md-2">
			    <label for="type" class="form-label">종류</label>
			    <input type="text" class="form-control" name="type"  id="type" value="dog" readonly >
	  		</div>
	  		
	  		<div class="col-4">
			    <label for="dogSize" class="form-label">크기 분류</label><br>	
			    <input type="radio" name="dogSize" value="대형" ${dog.dogSize == '대형' ? 'checked' : ''}>대형
			    <input type="radio" name="dogSize" value="중형" ${dog.dogSize == '중형' ? 'checked' : ''}>중형
			    <input type="radio" name="dogSize" value="소형" ${dog.dogSize == '소형' ? 'checked' : ''}>소형
	  		</div>
	  		
	  		<div class="col-4 offset-md-2">
			    <label for="nameKor" class="form-label">이름</label>
			    <input type="text" class="form-control" name="nameKor"  id="nameKor" value="${dog.nameKor}">
	  		</div>
	  		
	  		<div class="col-4">
			    <label for="nameEng" class="form-label">영문명</label>
			    <input type="text" class="form-control" name="nameEng"  id="nameEng"  value="${dog.nameEng}">
	  		</div>
	  		
	  		<div class="col-4 offset-md-2">
			    <label for="origin" class="form-label">출신지</label>
			    <input type="text" class="form-control" name="origin"  id="origin"  value="${dog.origin}">
	  		</div>
	  		
	  		<div class="col-4">
			    <label for="history" class="form-label">설명</label>
			    <input type="text" class="form-control" name="history"  id="history" value="${dog.history}">
	  		</div>
	  		
	  		<div class="col-4 offset-md-2">
			    <label for="height" class="form-label">키</label>
			    <input type="text" class="form-control" name="height"  id="height" value="${dog.height}">
	  		</div>
	  		
	  		<div class="col-4">
			    <label for="weight" class="form-label">체중</label>
			    <input type="text" class="form-control" name="weight"  id="weight" value="${dog.weight}">
	  		</div>
	  		
	  		<div class="col-4 offset-md-2">
			    <label for="coat" class="form-label">털</label>
			    <input type="text" class="form-control" name="coat"  id="coat" value="${dog.coat}">
	  		</div>
	  		
	  		<div class="col-4">
			    <label for="color" class="form-label">색상</label>
			    <input type="text" class="form-control" name="color"  id="color" value="${dog.color}">
	  		</div>
	  		
	  		<div class="col-4 offset-md-2">
			    <label for="lifespan" class="form-label">수명</label>
			    <input type="text" class="form-control" name="lifespan"  id="lifespan" value="${dog.lifespan}">
	  		</div>
	  		
	  		<div class="col-4">
			    <label for="healthIssues" class="form-label">흔히 발병하는 건강 이슈</label>
			    <input type="text" class="form-control" name="healthIssues"  id="healthIssues" value="${dog.healthIssues}">
	  		</div>
	  		
	  		<div class="col-4 offset-md-2">
			    <label for="intelligence" class="form-label">지능</label><br>	
			    <input type="radio" name="intelligence" value="높음" ${dog.intelligence == '높음' ? 'checked' : ''}>높음
			    <input type="radio" name="intelligence" value="중간" ${dog.intelligence == '중간' ? 'checked' : ''}>중간
			    <input type="radio" name="intelligence" value="낮음" ${dog.intelligence == '낮음' ? 'checked' : ''}>낮음
	  		</div>
	  		
	  		<div class="col-4">
			    <label for="trainingDifficulty" class="form-label">훈련 난이도</label><br>	
			    <input type="radio" name="trainingDifficulty" value="쉬움" ${dog.trainingDifficulty == '쉬움' ? 'checked' : ''}>쉬움
			    <input type="radio" name="trainingDifficulty" value="중간" ${dog.trainingDifficulty == '중간' ? 'checked' : ''}>중간
			    <input type="radio" name="trainingDifficulty" value="어려움" ${dog.trainingDifficulty == '어려움' ? 'checked' : ''}>어려움
	  		</div>
	  		
	  		<div class="col-4 offset-md-2">
			    <label for="environment" class="form-label">추천 환경</label>
			    <input type="text" class="form-control" name="environment"  id="environment" value="${dog.environment}">
	  		</div>
	  		
	  		<div class="col-4">
			    <label for="exercise" class="form-label">운동량</label>
			    <input type="text" class="form-control" name="exercise"  id="exercise" value="${dog.exercise}">
	  		</div>

 			<div class="col-4 offset-md-2">
			    <label for="barkingLevel" class="form-label">짖음 정도</label><br>	
			    <input type="radio" name="barkingLevel" value="높음" ${dog.barkingLevel == '높음' ? 'checked' : ''}>높음
			    <input type="radio" name="barkingLevel" value="중간" ${dog.barkingLevel == '중간' ? 'checked' : ''}>중간
			    <input type="radio" name="barkingLevel" value="낮음" ${dog.barkingLevel == '낮음' ? 'checked' : ''}>낮음
	  		</div>
	  		
	  		<div class="col-4">
			    <label for="grooming" class="form-label">미용</label>
			    <input type="text" class="form-control" name="grooming"  id="grooming" value="${dog.grooming}">
	  		</div>	
	  		
	  		<div class="col-4 offset-md-2">
			    <label for="personality" class="form-label">성격</label>
			    <input type="text" class="form-control" name="personality"  id="personality" value="${dog.personality}" >
	  		</div>

 			<div class="col-4">
			    <label for="beginnerFriendly" class="form-label">초보자 추천 여부</label><br>	
			    <input type="radio" name="beginnerFriendly" value="Y" ${dog.beginnerFriendly == 'Y' ? 'checked' : ''}>Y
			    <input type="radio" name="beginnerFriendly" value="N" ${dog.beginnerFriendly == 'N' ? 'checked' : ''}>N
	  		</div>
		
	  		<div class="col-8 offset-md-2">
			    <label for="photo" class="form-label">사 진 (정사각형 모양에 특화되어있음)</label>
			    <input type="file" class="form-control" name="photo"  id="photo" accept="image/*" >
	  		</div>	
	  				  			
	  		<div class="col-8 offset-md-2 text-center mt-5">
			   <input type="submit" value="수정하기" class="btn btn-primary"/>
					&nbsp;&nbsp;
				<input type="button" value="목록보기" class="btn btn-primary"
				onclick="location.href='dictionaryList?pageNum=${pageNum}&searchDictionaryType=${searchDictionaryType}&searchDictionaryKeyword=${searchDictionaryKeyword}'"/>
	  		</div>	
	  		
		</form>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/dictionary.js"></script>

    