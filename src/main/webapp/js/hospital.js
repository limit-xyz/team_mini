/**
 * 
 */

$(function() {
	
	var lat = 37.566826;
	var lng = 126.9786567;
	let markers = [];
	let currentInfowindow = null;
	const listEl = document.getElementById('placesList');
	const paginationEl = document.getElementById('pagination');
	const pageNum = document.getElementById('pageNum');
	const params = new URLSearchParams(window.location.search);

	// 카카오 맵 생성 관련
	var mapContainer = document.getElementById('map');
	var mapOption = {
	    center: new kakao.maps.LatLng(lat, lng), // 서울 중심 좌표
	    level: 7
	};
	var map = new kakao.maps.Map(mapContainer, mapOption);
	
	
	// ip로 내 위치 찾기
	$("#findLocation").on("click", function() {
		$.getJSON('https://ipapi.co/json/', function(data) {
      lat = data.latitude;
      lng = data.longitude;
      var ip = data.ip;
			
			var newCenter = new kakao.maps.LatLng(lat, lng);
			map.setCenter(newCenter);
			
			// 기존 마커 제거
			clearMarkers();

			// 현재 위치에 마커 생성
			var marker = new kakao.maps.Marker({
			  position: newCenter,
			  map: map,
			  title: "현재 위치"
			});

			markers.push(marker); // 배열에 추가해두면 나중에 clearMarkers()로 제거 가능

			// 인포윈도우 간단 표시 (선택)
			if (currentInfowindow) {
			  currentInfowindow.close(); // 기존 인포윈도우 닫기
			}

			currentInfowindow = new kakao.maps.InfoWindow({
			  content: '<div style="padding:5px;">현재 위치</div>'
			});
			currentInfowindow.open(map, marker);
			
    }).fail(function() {
			alert("위치 정보를 불러오지 못했습니다.")
		});
		
	});
	
	// 도로명 주소로 위도, 경도 찾기
	$('#findLocationByAddress').click(function () {
		new daum.Postcode({
			oncomplete: function(data) {
				addr = data.roadAddress;
			  if (!addr) {
		      alert("주소를 입력하세요.");
		      return;
				}
			
			  $.ajax({
		      url: 'https://dapi.kakao.com/v2/local/search/address.json',
		      type: 'GET',
		      data: { query: addr },
		      headers: {
		          //'Authorization': 'KakaoAK ' + kakaoApiKey
							'Authorization': 'KakaoAK ' + restKey
		      },
		      success: function (res) {
		        if (res.documents.length > 0) {
		          var result = res.documents[0];
							console.log("result.x :", result.x, ", result.y : ", result.y)
							lat = result.y, lng = result.x;

							var newCenter = new kakao.maps.LatLng(lat, lng);
							map.setCenter(newCenter);
							
							// 기존 마커 제거
							clearMarkers();

							// 현재 위치에 마커 생성
							var marker = new kakao.maps.Marker({
							  position: newCenter,
							  map: map,
							  title: "현재 위치"
							});

							markers.push(marker); // 배열에 추가해두면 나중에 clearMarkers()로 제거 가능

							if (currentInfowindow) {
							  currentInfowindow.close(); // 기존 인포윈도우 닫기
							}

							currentInfowindow = new kakao.maps.InfoWindow({
							  content: '<div style="padding:5px;">현재 위치</div>'
							});
							currentInfowindow.open(map, marker);

							
							
		        } else {
							alert("좌표를 찾을 수 없습니다.")
		        }
		      },
		      error: function (err) {
			      console.error(err);
			      $('#result').html("<p>API 요청 중 오류가 발생했습니다.</p>");
		      }
				});
			}
		}).open();
		
  });
	
	// 지도에 마커 고정 및 위치 정보 출력창
	function displayPlaces(places) {
	  clearMarkers();
	  listEl.innerHTML = '';
	  let dataSelect = $("#dataSelect").val();

	  var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
	  var bounds = new kakao.maps.LatLngBounds();
	  var geocoder = new kakao.maps.services.Geocoder();

	  var promises = places.map((place, i) => {
	    return new Promise((resolve) => {
	      if (!place.address || place.address.trim() === "") {
	        console.warn("유효하지 않은 주소:", place);
	        resolve(); // 주소가 없으면 패스
	        return;
	      }

	      geocoder.addressSearch(place.address, (result, status) => {
	        if (status === kakao.maps.services.Status.OK && result.length > 0) {
	          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	          console.log("마커 생성 위치:", coords.toString());

	          var marker = new kakao.maps.Marker({ map, position: coords });
	          markers.push(marker);
	          bounds.extend(coords);

	          var listItem = document.createElement('li');
	          listItem.innerHTML = `
	            <span><strong>${place.place_name}</strong></span><br>
	            <span>${place.address}</span><br>
	            <span>전화번호 : ${place.phone}</span><br>
	            <span><a href="${place.place_url}" target="_blank">
	              ${dataSelect === "hospital" ? "동물병원 정보" : "미용실 정보"}
	            </a></span><br>
	          `;

	          listItem.onclick = () => map.panTo(coords);
	          listEl.appendChild(listItem);

	          // 마커 및 리스트에 인포윈도우 바인딩
	          (function(marker, title, itemEl) {
	            kakao.maps.event.addListener(marker, 'mouseover', function () {
	              displayInfowindow(marker, title);
	            });
	            kakao.maps.event.addListener(marker, 'mouseout', function () {
	              infowindow.close();
	            });
	            itemEl.onmouseover = function () {
	              displayInfowindow(marker, title);
	            };
	            itemEl.onmouseout = function () {
	              infowindow.close();
	            };
	          })(marker, place.place_name, listItem);

	          resolve();
	        } else {
	          console.warn("주소 검색 실패:", place.address);
	          resolve();
	        }
	      });
	    });
	  });

	  Promise.all(promises).then(() => {
	    if (!bounds.isEmpty()) {
	      map.setBounds(bounds);
	    } else {
	      console.warn("지도에 표시할 유효한 마커가 없습니다.");
	    }
	  }).catch((err) => {
	    console.error("Geocoding error:", err);
	  });

	  function displayInfowindow(marker, title) {
	    var content = `<div style="padding:5px;z-index:1;">${title}</div>`;
	    infowindow.setContent(content);
	    infowindow.open(map, marker);
	  }
	}
	

	// 마커 지우기
	function clearMarkers() {
	    markers.forEach(marker => marker.setMap(null));
	    markers = [];
	}

	// 페이지네이션
	function displayPagination(data, itemsPerPage = 10) {
    var totalPages = Math.ceil(data.length / itemsPerPage);
    pageNum.innerHTML = '';
		
    for (let i = 1; i <= totalPages; i++) {
      var btn = document.createElement('button');
      btn.innerText = i;
      btn.onclick = () => {
          var start = (i - 1) * itemsPerPage;
          var end = start + itemsPerPage;
          displayPlaces(data.slice(start, end));
      };
      pageNum.appendChild(btn);
    }

    // 처음 1페이지 표시
    displayPlaces(data.slice(0, itemsPerPage));
	}

	// "지도에 위치 표시" 버튼 클릭 시 DB에서 데이터 가져와서 ajax로 뿌리는 동작
	$("#locationConfirm").on("click", function() {
	
		let dataSelect = $("#dataSelect").val();
		let searchOption = $("#searchOption").val();
		let data = "lat=" + lat + "&lng=" + lng +"&dataSelect=" + dataSelect 
			+"&searchOption=" + searchOption +"&nearby=0";
		
		// 지도 중심 좌표 변경
		var newCenter = new kakao.maps.LatLng(lat, lng);
		map.setCenter(newCenter);
		console.log(lat, lng);
		console.log(data);
		const requestUrl = contextPath + "/ajax/locationConfirm.ajax"
		console.log("requestUrl : ", requestUrl);
		
		$.ajax({
			url: requestUrl,
			data:data,
			"dataType":"json",
			success:function(resData) {
				
				console.log(resData);
				listEl.innerHTML = '';
				
				if(resData.length > 0) {
					displayPagination(resData, 10);
				} else {
					var listItem = document.createElement('li');
					listItem.innerHTML = `<strong>일치하는 검색 결과가 없습니다.</strong><br>`;
					listEl.appendChild(listItem);
				}
			},
			error: function(xhr) {
				console.log("error : ",xhr);
			}	
		});	
	});
	
	
	// "가까운 곳만 지도에 위치표시" 버튼 클릭 시 DB에서 데이터 가져와서 ajax로 뿌리는 동작
	$("#nearbyLocationConfirm").on("click", function() {

		let dataSelect = $("#dataSelect").val();
		let searchOption = $("#searchOption").val();
		let data = "lat=" + lat + "&lng=" + lng +"&dataSelect=" + dataSelect +"&searchOption=" + searchOption
			+"&nearby=1";

		// 지도 중심 좌표 변경
		var newCenter = new kakao.maps.LatLng(lat, lng);
		map.setCenter(newCenter);
		console.log(lat, lng);
		console.log(data);
		const requestUrl = contextPath + "/ajax/locationConfirm.ajax"
		console.log("requestUrl : ", requestUrl);

		$.ajax({
			url: requestUrl,
			data:data,
			"dataType":"json",
			success:function(resData) {
				
				console.log(resData);
				listEl.innerHTML = '';
				
				if(resData.length > 0) {
					displayPagination(resData, 10);
				} else {
					var listItem = document.createElement('li');
					listItem.innerHTML = `<strong>일치하는 검색 결과가 없습니다.</strong><br>`;
					listEl.appendChild(listItem);
				}
			},
			error: function(xhr) {
				console.log("error : ",xhr);
			}	
		})
	});
	
});


