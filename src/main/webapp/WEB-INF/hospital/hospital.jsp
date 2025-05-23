<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.InputStream" %>

<%
    Properties prop = new Properties();
    InputStream input = application.getResourceAsStream("/WEB-INF/resources/appkey.properties"); // 경로 중요!
    
    String jsKey = null;
    String restKey = null;
    
    if (input != null) {
        prop.load(input);
         jsKey = prop.getProperty("kakao.javascriptApiKey");
         restKey = prop.getProperty("kakao.restfulApiKey");
    } 
    
    request.setAttribute("jsKey", jsKey);
    request.setAttribute("restKey", restKey);
%>
<script>
	const restKey = "${restKey}";
	const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/js/hospital.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<style>
	.map_wrap, .map_wrap * {
		margin: 0;
		padding: 0;
		font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
		font-size: 12px;
	}
	
	.map_wrap a, .map_wrap a:hover, .map_wrap a:active {
		color: #000;
		text-decoration: none;
	}
	
	.map_wrap {
		position: relative;
		width: 100%;
		height: 500px;
	}
	
	#menu_wrap {
		position: absolute;
		top: 0;
		left: 0;
		bottom: 0;
		width: 340px;
		margin: 10px 0 30px 10px;
		padding: 5px;
		overflow-y: auto;
		background: rgba(255, 255, 255, 0.7);
		z-index: 1;
		font-size: 12px;
		border-radius: 10px;
	}
	
	.bg_white {
		background: #fff;
	}
	
	#menu_wrap hr {
		display: block;
		height: 1px;
		border: 0;
		margin: 3px 0;
	}
	
	#menu_wrap button {margin-left: 5px;}
	
	#placesList li {
		list-style: none;
		position: relative;
		border-bottom: 1px solid #888;
		overflow: hidden;
		cursor: pointer;
		min-height: 65px;
	}

	#placesList .item .info {padding: 10px 0 10px 55px;}
	
	#placesList .info .jibun {
		padding-left: 26px;
		background:
			url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
			no-repeat;
	}
	
	#placesList .info .tel {color: #009900;}
	
	.container1 {
  	min-height: 300px;
  	margin-top: 40px;
  	margin-bottom: 50px;
  	display: flex;
  	flex-direction: column;
  	justify-content: center;
  }
	
  h1 {
    color: #406400; /* 진한 녹색 (dark green) */
    font-weight: bold; /* 글자 굵게 */
  }
  
</style>
<body>
	<div class="container1">
		<div class="col-md-10 offset-md-1">
			<div class="row">
				<h1>병원/미용실 정보</h2>
				<div class="content my-3" class="me-5">
					<select id="dataSelect">
						<option value="hospital" selected>반려동물 병원</option>
						<option value="beauty">반려동물 미용실</option>
					</select>
					<input type="button" class="rounded-pill bg-white ms-5" id="findLocation" value="현재 위치를 선택">
					<input type="button" class="rounded-pill bg-white ms-3" id="findLocationByAddress" value="직접 위치 설정하기">
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<div id="map" style="width: 700px; height: 500px;"></div>
				</div>
				<div class="col">
					<div class="map_wrap" id="map_wrap">
					
						<div id="map" style="width: 100%; height: 100%; position: relative;"></div>
						<div id="menu_border">
							<div id="menu_wrap" class="bg_white">
								<hr>
								<ul id="placesList"></ul>
								<div id="pageNum"></div>
								<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${jsKey}&libraries=services"></script>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row my-4">
				<div>
						<input type="text" class="rounded-pill bg-white" id="searchOption" placeholder="이름으로 검색">
						<input type="button" class="mx-2 rounded-pill bg-white" id="locationConfirm" value="지도에 위치 표시">
						<input type="button" class="rounded-pill bg-white" id="nearbyLocationConfirm" value="가까운 곳 지도에 표시">
				</div>
			</div>
		</div>
	</div>


<!-- 
	https://yeon-ju-k.tistory.com/183  api 키 숨기기
	https://xianeml.tistory.com/75
	https://apis.map.kakao.com/web/sample/moveMap/
-->





<style>
	/* 기본 테마 변수 */
	:root {
		--main-theme-color: #C5E1D4;
		--main-theme-darker-text: #2c5c49;
		--main-theme-border-color: #b0d1c0;
		--main-theme-light-bg: #eef3f1;    
		--text-muted-custom: #5a6670;
		--card-default-shadow: 0 0.125rem 0.25rem rgba(0,0,0,0.075);
		--container-bg: #fff; /* 컨텐츠 영역 배경색 */
	}

	/* 기존 사용자 CSS 유지 및 일부 수정 */
	body {
		font-family: 'Noto Sans KR', sans-serif, 'Malgun Gothic', dotum, '돋움';
		font-size: 14px;
		background-color: #f0f2f5; /* 페이지 전체 배경색 약간 변경 */
		color: #333;
		margin:0; 
		padding:0;
	}

	/* 사용자 정의 .container1 대신 부트스트랩 .container 사용 권장, 
	   여기서는 .container1 에 스타일 적용 */
	.container1 {
		min-height: 300px;
		margin-top: 2rem;  /* 부트스트랩 my-4 또는 my-5 와 유사 */
		margin-bottom: 2rem;
		/* display: flex 관련은 내부 요소 배치에 따라 제거하거나 유지 */
	}
	
	/* 페이지 제목 h1 (기존 h1 스타일 우선 적용 후, 필요시 class로 세부 조정) */
	.container1 h1 { /* h1에 직접 적용되도록 */
		color: var(--main-theme-darker-text); 
		font-weight: 700; /* bold보다 구체적인 값 */
		font-size: 1.8rem;  /* 페이지 제목 크기 */
		text-align: center;
		margin-bottom: 1.5rem; /* 하단 여백 */
		padding-bottom: 0.5rem;
		border-bottom: 2px solid var(--main-theme-color);
		display: inline-block; /* border-bottom이 텍스트 길이에 맞게 */
	}
	/* h1을 중앙 정렬하기 위해 부모 div에 text-center 추가 필요 */
	/* 예: <div class="row text-center"><div class="col"><h1>...</h1></div></div> */


	/* 상단 컨트롤 영역 (기존 .content 클래스 활용 또는 새 클래스 부여) */
	.top-controls-bar { /* 예시 클래스명, HTML의 <div class="content my-3 me-5">에 추가 */
		display: flex;
		align-items: center;
		gap: 0.75rem;
		padding: 0.75rem 1rem;
		background-color: var(--main-theme-light-bg);
		border-radius: 0.375rem;
		margin-bottom: 1.5rem !important;
	}
	.top-controls-bar select,
	.top-controls-bar input[type="button"] {
		font-size: 0.875rem; /* 14px */
		padding: 0.375rem 0.75rem; /* 부트스트랩 sm 버튼/폼 크기와 유사 */
		border-radius: 50px; /* 기존 rounded-pill 유지 */
		border: 1px solid var(--main-theme-border-color);
		background-color: var(--container-bg);
		color: var(--main-theme-darker-text);
		cursor:pointer;
	}
	.top-controls-bar input[type="button"]:hover {
		background-color: var(--main-theme-color);
	}


	/* 지도 영역 스타일 */
	#map { /* 유일한 지도 div */
		width: 100%; /* 부모 col 너비에 맞춤 */
		height: 500px; /* 사용자가 지정한 높이 */
		border: 1px solid var(--main-theme-border-color);
		border-radius: 0.375rem;
	}

	/* map_wrap은 #menu_wrap의 부모로서 position:relative 역할만 하도록 */
	.map_wrap {
		position: relative;
		width: 100%;
		height: 500px; /* #map과 동일한 높이 */
	}
	
	/* 검색 결과 목록 (#menu_wrap) - "상자 디자인" 적용 및 개선 */
	#menu_wrap {
		position: absolute; 
		width: 330px; /* 기존 340px에서 약간 줄임 */
		left:4px;
		padding: 15px; /* 내부 패딩 증가 */
		overflow-y: auto;
		background: var(--container-bg); /* 불투명 흰색 배경 */
		border: 1px solid var(--main-theme-border-color); /* 테마 테두리 */
		border-radius: 0.375rem; /* 일관된 둥근 모서리 */
		box-shadow: var(--card-default-shadow); 
		z-index: 10; 
		font-size: 13px; /* 내부 기본 폰트 크기 */
	}
	
	#menu_wrap hr {
		display: block; height: 1px; border: 0; 
		border-top: 1px solid #e9ecef; /* 연한 구분선 */
		margin: 10px 0; 
	}
	
	/* #menu_wrap button {margin-left: 5px;} /* 필요시 유지 또는 버튼 클래스로 제어 */
	
	#placesList { padding: 0; margin: 0; }
	#placesList li {
		list-style: none;
		position: relative;
		border-bottom: 1px solid #f0f0f0;
		overflow: hidden;
		cursor: pointer;
		padding: 10px 5px;
	}
	#placesList li:last-child { border-bottom: none; }
	#placesList li:hover { background-color: var(--main-theme-light-bg); }

    /* Kakao 제공 마커 스타일은 유지 */
	#placesList .item .markerbg { float:left; position:absolute; width:36px; height:37px; margin:5px 0 0 5px; background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number.png) no-repeat;}
    /* marker_1 ~ marker_15 ... */
	
	#placesList .item h5 { /* 장소 이름 */
		font-size: 0.9rem; /* 14.4px */
		font-weight: 600;
		color: var(--main-theme-darker-text);
		margin: 0 0 3px 45px; /* 마커 공간 확보 */
		text-overflow: ellipsis; overflow: hidden; white-space: nowrap;
	}
	#placesList .item .info {
		padding: 0 0 0 45px; /* 마커 공간 확보 */
		font-size: 0.8rem; /* 12.8px */
		line-height: 1.4;
		color: var(--text-muted-custom);
	}
	#placesList .item .info span { display: block; margin-top: 2px; }
	#placesList .info .jibun {
		padding-left: 18px; 
		background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat left center;
		background-size: 12px 12px;
	}
	#placesList .info .tel { color: var(--main-theme-darker-text); font-weight: 500; }
	
	/* 결과 목록 페이지네이션 */
	#pageNum {
        margin:15px 0 5px; text-align: center;
    }
    #pageNum a {
        display: inline-block; margin: 0 2px; padding: 5px 8px; 
        border:1px solid var(--main-theme-border-color);
        border-radius: 0.25rem; font-size: 0.85rem;
        color: var(--main-theme-darker-text); text-decoration: none;
        background-color: var(--container-bg);
    }
    #pageNum a:hover { background-color: var(--main-theme-light-bg); }
    #pageNum a.on {
        background: var(--main-theme-color); font-weight: bold;
        color: var(--main-theme-darker-text); border-color: var(--main-theme-color);
    }

	/* 하단 검색 컨트롤 */
	.bottom-controls-section { /* HTML에서 <div class="row my-4">에 이 클래스 추가 */
		display: flex;
		align-items: center;
		gap: 0.5rem;
		justify-content: center;
		margin-top: 1.5rem !important;
		padding: 1rem;
		background-color: var(--main-theme-light-bg);
		border-radius: 0.375rem;
	}
	.bottom-controls-section input[type="text"],
	.bottom-controls-section input[type="button"] {
		font-size: 0.9rem;
		padding: 0.375rem 0.75rem;
		border-radius: 50px;
		border: 1px solid var(--main-theme-border-color);
		background-color: var(--container-bg);
	}
    .bottom-controls-section input[type="button"] {
        color: var(--main-theme-darker-text);
        cursor: pointer;
    }
	.bottom-controls-section input[type="button"]:hover {
		background-color: var(--main-theme-color);
	}

</style>



