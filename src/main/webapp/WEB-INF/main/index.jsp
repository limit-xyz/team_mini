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
			<a href="#" class="card-link">
				<div class="outer-container">
					<div class="circle-image">
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkuIadwwXvaQ3aaa6rnbRYCyg9_5nwQWksGQ&s">
					</div>
					<div class="circle-caption">
		 				신규 동물병원 보기
					</div>
				</div>
			</a>
			<a href="#" class="card-link">
				<div class="outer-container">
					<div class="circle-image">
						<img src="https://cdn.mkhealth.co.kr/news/photo/202106/53700_55215_4051.jpg">
					</div>
					<div class="circle-caption">
		 				신규 미용실 보기
					</div>
				</div>
			</a>
			<a href="#" class="card-link">	
				<div class="outer-container">
					<div class="circle-image">
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKd6WgOp3mnB0Kaw8WdOS6-bNJjPqCbNW30Q&s">
					</div>
					<div class="circle-caption">
		 				신규 펫과사전 보기
					</div>
				</div>
			</a>
			<a href="#" class="card-link">
				<div class="outer-container">
					<div class="circle-image">
						<img src="data:image/jpeg;base64,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">
					</div>
					<div class="circle-caption">
		 				신규 상품정보	보기
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