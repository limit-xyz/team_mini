<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<style>
  .outer-container {
    width: 150px;
    height: 150px;
    border: 2px solid #aaa;
    border-radius: 10px;
    padding: 10px;
    box-sizing: border-box;
    background-color: #fdfdfd;
  }

  .circle-image {
    width: 90px;
    height: 90px;
    border-radius: 50%;
    overflow: hidden;
    border: 2px solid #ccc;
  }

  .circle-image img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: left top; 
    display: block;
  }

  .circle-caption {
    text-align: center;
    margin-top: 10px;
    font-size: 14px;
    font-weight: bold;
  }
  
   .row-container {
    display: flex;            
    gap: 30px;                
    justify-content: center;  
    flex-wrap: nowrap;        
  }
  
  #topLine {
  	margin : 25px;
  }
  
  a.card-link {
    text-decoration: none; /* 밑줄 제거 */
    color: inherit;         /* 기본 텍스트 색상 유지 */
  }
  
</style>
<div class="row">
	<div class="col" id="topLine">
		<div class="row-container">
			<a href="http://localhost:8080/team_mini/hospital/*" class="card-link">
				<div class="outer-container">
					<div class="circle-image">
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkuIadwwXvaQ3aaa6rnbRYCyg9_5nwQWksGQ&s">
					</div>
					<div class="circle-caption">
		 				신규 동물병원 보기
					</div>
				</div>
			</a>
			<a href="http://localhost:8080/team_mini/hospital/*" class="card-link">
				<div class="outer-container">
					<div class="circle-image">
						<img src="https://cdn.mkhealth.co.kr/news/photo/202106/53700_55215_4051.jpg">
					</div>
					<div class="circle-caption">
		 				신규 미용실 보기
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/dictionary/dictionaryList" class="card-link">	
				<div class="outer-container">
					<div class="circle-image">
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKd6WgOp3mnB0Kaw8WdOS6-bNJjPqCbNW30Q&s">
					</div>
					<div class="circle-caption">
		 				신규 펫과사전 보기
					</div>
				</div>
			</a>

		</div>
	</div>
</div>
<div class="row" id="mainHeight">
	<div class="col-md-4 offset-md-1">
		<div class="row my-4">
			<div>
				<span class="text-success fs-3 fw-bold" id="allInOne">올인원</span> 
				<span class="text fs-4 fw-bold">반려동물 <br>케어 시스템</span>
			</div>
		</div>
		<div class="row my-4">
			<div class="ms-2 mt-5">
				<p class="fst-italic fw-bold">소중한 가족을 위한 맞춤 케어</p>  
				<p class="fst-italic fw-bold">반려동물의 건강과 행복, 저희가 함께 지켜드립니다</p>
			</div>
		</div>
	</div>
	<div class="col-6">
		<img src="https://image.dongascience.com/Photo/2023/07/2a8a3655edb9a187db216dc4adc13b7e.jpg">
	</div>
</div>