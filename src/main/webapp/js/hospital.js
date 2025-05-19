/**
 * 
 */

$(function() {
	
	var lat = 37.566826;
	var lng = 126.9786567;
	let markers = [];
	const listEl = document.getElementById('placesList');
	const paginationEl = document.getElementById('pagination');
	const pageNum = document.getElementById('pageNum');
	const kakaoApiKey = "f2e0d03c4537669dacbf2261b5ffd1e8";

	// 카카오 맵 생성 관련
	const mapContainer = document.getElementById('map');
	const mapOption = {
	    center: new kakao.maps.LatLng(lat, lng), // 서울 중심 좌표
	    level: 7
	};
	const map = new kakao.maps.Map(mapContainer, mapOption);
	
	
	// ip로 내 위치 찾기
	$("#findLocation").on("click", function() {
		$.getJSON('https://ipapi.co/json/', function(data) {
      lat = data.latitude;
      lng = data.longitude;
      var ip = data.ip;
			$("#lat").val(lat);
			$("#lng").val(lng);
      
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
		          'Authorization': 'KakaoAK ' + kakaoApiKey
		      },
		      success: function (res) {
		        if (res.documents.length > 0) {
		          const result = res.documents[0];
							console.log("result.x :", result.x, ", result.y : ", result.y)
							lat = result.y, lng = result.x;
		          $("#lng").val(lng);
		          $("#lat").val(lat);
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
		
    const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    const bounds = new kakao.maps.LatLngBounds();
    const geocoder = new kakao.maps.services.Geocoder();
    const promises = places.map((place, i) => {
    	return new Promise((resolve) => {
      	geocoder.addressSearch(place.address, (result, status) => {
        	if (status === kakao.maps.services.Status.OK) {
        	
						const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            const marker = new kakao.maps.Marker({ map, position: coords });
            markers.push(marker);
            bounds.extend(coords);

            const listItem = document.createElement('li');
            listItem.innerHTML = `
              <strong>${place.place_name}</strong><br>
              ${place.address}<br>
              ${place.phone}<br>
							
              <a href="${place.place_url}" target="_blank">
								${dataSelect =="hospital" ? "동물병원 정보" : "미용실 정보"} 
							</a><br><br>
            `;
						listEl.style.borderRadius = "8px";
						listEl.style.border = "1px solid black";
						listEl.style.padding = "15px";
            listEl.appendChild(listItem);
            listItem.onclick = () => map.panTo(coords);

            // 마커와 리스트 항목에 인포윈도우 이벤트 추가
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
        	}
      	});
      });
    });
    Promise.all(promises).then(() => {
     	map.setBounds(bounds);
    })
    .catch((err) => {
      console.error("Geocoding error:", err);
    });

    function displayInfowindow(marker, title) {
      const content = `<div style="padding:5px;z-index:1;">${title}</div>`;
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
    const totalPages = Math.ceil(data.length / itemsPerPage);
    pageNum.innerHTML = '';
		
    for (let i = 1; i <= totalPages; i++) {
      const btn = document.createElement('button');
      btn.innerText = i;
      btn.onclick = () => {
          const start = (i - 1) * itemsPerPage;
          const end = start + itemsPerPage;
          displayPlaces(data.slice(start, end));
      };
      pageNum.appendChild(btn);
    }

    // 처음 1페이지 표시
    displayPlaces(data.slice(0, itemsPerPage));
	}

	// "검색" 버튼 클릭 시 DB에서 데이터 가져와서 ajax로 뿌리는 동작
	$("#locationConfirm").on("click", function() {
		lat = $("#lat").val();
		lng = $("#lng").val();
		let dataSelect = $("#dataSelect").val();
		let searchOption = $("#searchOption").val();
		let data = "lat=" + lat + "&lng=" + lng +"&dataSelect=" + dataSelect 
			+"&searchOption=" + searchOption;
		
		// 지도 중심 좌표 변경
		const newCenter = new kakao.maps.LatLng(lat, lng);
		map.setCenter(newCenter);

		$.ajax({
			url:"locationConfirm.ajax",
			data:data,
			"dataType":"json",
			success:function(resData) {
				
				console.log(resData);
				listEl.innerHTML = '';
				
				if(resData.length > 0) {
					displayPagination(resData, 10);
				} else {
					const listItem = document.createElement('li');
					listItem.innerHTML = `<strong>일치하는 검색 결과가 없습니다.</strong><br>`;
					listEl.appendChild(listItem);
				}
			},
			error: function(xhr) {
				console.log("error : ",xhr);
			}	
		});	
	});
});


