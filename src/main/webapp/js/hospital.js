/**
 * 
 */
$(function() {
	
	let lat = $("#lat").val();
	let lng = $("#lng").val();
	let dataSelect = $("#dataSelect").val();
	
	const mapContainer = document.getElementById('map');
	const mapOption = {
	    center: new kakao.maps.LatLng(lat, lng), // 서울 중심 좌표
	    level: 7
	};
	const map = new kakao.maps.Map(mapContainer, mapOption);
	let markers = [];
	const listEl = document.getElementById('placesList');
	const paginationEl = document.getElementById('pagination');

	function displayPlaces(places) {
	    clearMarkers();
	    listEl.innerHTML = '';

	    const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
	    const bounds = new kakao.maps.LatLngBounds();
	    const geocoder = new kakao.maps.services.Geocoder();

	    const promises = places.map((place, i) => {
	        return new Promise((resolve, reject) => {
	            geocoder.addressSearch(place.address, (result, status) => {
	                if (status === kakao.maps.services.Status.OK) {
	                    const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	                    const marker = new kakao.maps.Marker({
	                        map,
	                        position: coords
	                    });
	                    markers.push(marker);
	                    bounds.extend(coords);

	                    const listItem = document.createElement('li');
	                    listItem.innerHTML = `
	                        <strong>${place.place_name}</strong><br>
	                        ${place.address}<br>
	                        ${place.phone}<br>
	                        <a href="${place.place_url}" target="_blank">동물병원 정보</a><br><br>
	                    `;
	                    listEl.appendChild(listItem);

	                    listItem.onclick = () => map.panTo(coords);

	                    // ✅ 마커와 리스트 항목에 인포윈도우 이벤트 추가
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
	                    reject(`Geocode failed for: ${place.address}`);
	                }
	            });
	        });
	    });

	    Promise.all(promises)
	        .then(() => {
	            map.setBounds(bounds);
	        })
	        .catch((err) => {
	            console.error("Geocoding error:", err);
	        });

	    // ✅ displayInfowindow 함수는 바깥에 있어야 합니다
	    function displayInfowindow(marker, title) {
	        const content = `<div style="padding:5px;z-index:1;">${title}</div>`;
	        infowindow.setContent(content);
	        infowindow.open(map, marker);
	    }
	}


	function clearMarkers() {
	    markers.forEach(marker => marker.setMap(null));
	    markers = [];
	}

	// 페이지네이션
	function displayPagination(data, itemsPerPage = 10) {
	    const totalPages = Math.ceil(data.length / itemsPerPage);
	    paginationEl.innerHTML = '';

	    for (let i = 1; i <= totalPages; i++) {
	        const btn = document.createElement('button');
	        btn.innerText = i;
	        btn.onclick = () => {
	            const start = (i - 1) * itemsPerPage;
	            const end = start + itemsPerPage;
	            displayPlaces(data.slice(start, end));
	        };
	        paginationEl.appendChild(btn);
	    }

	    // 처음 1페이지 표시
	    displayPlaces(data.slice(0, itemsPerPage));
	}

	
	$("#locationConfirm").on("click", function() {
		let lat = $("#lat").val();
		let lng = $("#lng").val();
		let dataSelect = $("#dataSelect").val();
		// console.log(dataSelect);
		let data = "lat=" + lat + "&lng=" + lng +"&dataSelect=" + dataSelect;
		
		// 지도 중심 좌표 변경
		const newCenter = new kakao.maps.LatLng(lat, lng);
		map.setCenter(newCenter);
		
		
		$.ajax({
			url:"locationConfirm.ajax",
			data:data,
			"dataType":"json",
			success:function(resData) {
				console.log(resData);
				displayPagination(resData, 10);
			},
			error: function(xhr) {
				console.log("error : ",xhr);
			}	
		})
	});
	
});