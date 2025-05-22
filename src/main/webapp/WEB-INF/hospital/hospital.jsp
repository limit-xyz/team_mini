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
		width: 250px;
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
	
	#menu_wrap .option {text-align: center;}
	
	#menu_wrap .option p {margin: 10px 0;}
	
	#menu_wrap button {margin-left: 5px;}
	
	#placesList li {list-style: none;}
	
	#placesList .item {
		position: relative;
		border-bottom: 1px solid #888;
		overflow: hidden;
		cursor: pointer;
		min-height: 65px;
	}
	
	#placesList .item span {
		display: block;
		margin-top: 4px;
	}
	
	#placesList .item h5, #placesList .item .info {
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
	}
	
	#placesList .item .info {padding: 10px 0 10px 55px;}
	
	#placesList .info .gray {color: #8a8a8a;}
	
	#placesList .info .jibun {
		padding-left: 26px;
		background:
			url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
			no-repeat;
	}
	
	#placesList .info .tel {color: #009900;}
	
	#placesList .item .markerbg {
		float: left;
		position: absolute;
		width: 36px;
		height: 37px;
		margin: 10px 0 0 10px;
			url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
			no-repeat;
	}
	
	#placesList .item .marker_1 {background-position: 0 -10px;}
	#placesList .item .marker_2 {background-position: 0 -56px;}
	#placesList .item .marker_3 {background-position: 0 -102px}
	#placesList .item .marker_4 {background-position: 0 -148px;}
	#placesList .item .marker_5 {background-position: 0 -194px;}
	#placesList .item .marker_6 {background-position: 0 -240px;}
	#placesList .item .marker_7 {background-position: 0 -286px;}
	#placesList .item .marker_8 {background-position: 0 -332px;}
	#placesList .item .marker_9 {background-position: 0 -378px;}
	#placesList .item .marker_10 {background-position: 0 -423px;}
	#placesList .item .marker_11 {background-position: 0 -470px;}
	#placesList .item .marker_12 {background-position: 0 -516px;}
	#placesList .item .marker_13 {background-position: 0 -562px;}
	#placesList .item .marker_14 {background-position: 0 -608px;}
	#placesList .item .marker_15 {background-position: 0 -654px;}
	
	#pagination {
		margin: 10px auto;
		text-align: center;
	}
	
	#pagination a {
		display: inline-block;
		margin-right: 10px;
	}
	
	#pagination .on {
		font-weight: bold;
		cursor: default;
		color: #777;
	}

	
</style>
<body>
	<div class="container">
		<div class="col-md-10 offset-md-1">
			<div class="row my-3">
				<h2>병원/미용실 정보</h2>
				<div>
					<select id="dataSelect">
						<option value="hospital" selected>반려동물 병원</option>
						<option value="beauty">반려동물 미용실</option>
					</select>
					<input type="button" id="findLocation" value="현재 위치를 선택"> 
					<button id="findLocationByAddress">직접 위치 설정하기</button>
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
								<div id="pagination"></div>
								<div id="pageNum"></div>
								<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${jsKey}&libraries=services"></script>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div>
						이름으로 검색 : <input type="text" id="searchOption">
						<input type="button" id="locationConfirm" value="지도에 위치 표시">
						<input type="button" id="nearbyLocationConfirm" value="가까운 곳만 지도에 위치표시">
				</div>
			</div>
		</div>
	</div>


<!-- 
	https://yeon-ju-k.tistory.com/183  api 키 숨기기
	https://xianeml.tistory.com/75
	https://apis.map.kakao.com/web/sample/moveMap/
-->






