<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/member.js"></script>
<style>
  .outer-container {
    width: 160px; /* 너비 조정 */
    min-height: 170px; /* 최소 높이 설정으로 내용물에 따른 유연성 + 일관성 */
    border: 1px solid #e0e0e0; /* 부드러운 테두리 색상 */
    border-radius: 12px; /* 좀 더 둥근 모서리 */
    padding: 20px 15px; /* 내부 여백 조정 (상하 20px, 좌우 15px) */
    box-sizing: border-box;
    background-color: #ffffff; /* 깔끔한 흰색 배경 */
    box-shadow: 0 2px 5px rgba(0,0,0,0.08); /* 은은한 초기 그림자 */
    transition: transform 0.25s ease-out, box-shadow 0.25s ease-out, border-color 0.25s ease-out;
    display: flex;
    flex-direction: column;
    align-items: center; /* 내부 요소들 가로 중앙 정렬 */
    justify-content: center; /* 내부 요소들 세로 중앙 정렬 (공간이 있을 경우) */
  }

  .circle-image {
    width: 75px; /* 이미지 크기 조정 */
    height: 75px;
    border-radius: 50%;
    overflow: hidden;
    border: 2px solid #f0f0f0; /* 매우 연한 이미지 테두리 */
    margin-bottom: 15px; /* 이미지와 캡션 사이 간격 */
    flex-shrink: 0; /* 컨테이너 크기 변경 시 이미지 축소 방지 */
  }

  .circle-image img {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 이미지가 원을 꽉 채우도록 */
    object-position: center center; /* 이미지의 중앙이 보이도록 */
    display: block;
  }

  .circle-caption {
    text-align: center;
    font-size: 14px;
    font-weight: 600; /* 약간 두껍게 (semi-bold) */
    color: #333333; /* 가독성 좋은 어두운 회색 */
    line-height: 1.35; /* 줄 간격 (캡션이 두 줄일 경우 대비) */
    transition: color 0.25s ease-out; /* 캡션 색상 변경 시 부드러운 효과 */
  }
  
   .row-container {
    display: flex;            
    gap: 30px;                /* 버튼 사이 간격 유지 */
    justify-content: center;  
    flex-wrap: nowrap;        /* 원래 설정대로 3개 아이템 한 줄에 유지 */
  }
  
  #topLine {
  	margin-top: 30px; /* 상단 여백 증가 */
    margin-bottom: 40px; /* 하단 여백 증가 (아래 콘텐츠와 구분) */
  }
  
  a.card-link {
    text-decoration: none; /* 밑줄 제거 */
    color: inherit;         /* 기본 텍스트 색상 유지 */
  }

  /* Hover Effects */
  a.card-link:hover .outer-container {
    transform: translateY(-4px); /* 위로 살짝 이동 */
    box-shadow: 0 5px 15px rgba(0,0,0,0.12); /* 그림자 강조 */
    border-color: #28a745; /* 테두리 색상 변경 (녹색 계열) */
  }

  a.card-link:hover .circle-caption {
    color: #28a745; /* 캡션 텍스트 색상 변경 (녹색 계열) */
  }
  
</style>

<div class="row">
	<div class="col" id="topLine">
		<div class="row-container">
			<a href="${pageContext.request.contextPath}/hospital/*" class="card-link">
				<div class="outer-container">
					<div class="circle-image">
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkuIadwwXvaQ3aaa6rnbRYCyg9_5nwQWksGQ&s" alt="동물병원 찾기">
					</div>
					<div class="circle-caption">
		 				신규 동물병원 보기
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/hospital/*" class="card-link">
				<div class="outer-container">
					<div class="circle-image">
						<img src="https://cdn.mkhealth.co.kr/news/photo/202106/53700_55215_4051.jpg" alt="미용실 찾기">
					</div>
					<div class="circle-caption">
		 				신규 미용실 보기
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/dictionary/dictionaryList" class="card-link">	
				<div class="outer-container">
					<div class="circle-image">
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKd6WgOp3mnB0Kaw8WdOS6-bNJjPqCbNW30Q&s" alt="펫과사전 보기">
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
		<img src="https://image.dongascience.com/Photo/2023/07/2a8a3655edb9a187db216dc4adc13b7e.jpg" alt="반려동물과 함께하는 모습" style="width: 100%; height: auto; border-radius: 8px;">
	</div>
</div>
