DROP TABLE animal_hospital;

CREATE TABLE animal_hospital (
  id NUMBER PRIMARY KEY,
  place_name VARCHAR2(100 CHAR) NOT NULL,
  address VARCHAR2(200 CHAR) NOT NULL,
  phone VARCHAR2(20 CHAR) unique,
  longitude NUMBER(15,10),
  latitude NUMBER(15,10),
  place_url VARCHAR2(300 CHAR)
);

DROP SEQUENCE animal_hospital_seq;

CREATE SEQUENCE animal_hospital_seq;

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '예은동물의료센터', '서울 강남구 논현동 87-4', '02-529-5575', 127.0312109264, 37.5146712343, 'http://place.map.kakao.com/17733573');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, 'VIP동물의료센터 청담점', '서울 강남구 청담동 46', '02-511-7522', 127.0490596749, 37.5194188636, 'http://place.map.kakao.com/356547106');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, 'N동물의료센터 노원점', '서울 노원구 상계동 318-2', '02-919-0075', 127.0681039026, 37.6531149422, 'http://place.map.kakao.com/20646966');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '로얄동물메디컬센터 본원', '서울 중랑구 면목동 146-75', '02-494-7582', 127.0802641192, 37.5875090989, 'http://place.map.kakao.com/312550076');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '청담눈초롱안과동물병원', '서울 강남구 청담동 125-12', '02-512-7566', 127.0526269512, 37.5251576157, 'http://place.map.kakao.com/1217873242');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '24시우리들동물메디컬센터', '서울 양천구 신정동 982-4', '02-2642-7588', 126.8604706103, 37.5221865960, 'http://place.map.kakao.com/16087655');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, 'OK동물병원', '서울 강남구 역삼동 766-20', '02-569-7582', 127.0498175517, 37.4955617388, 'http://place.map.kakao.com/713282068');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '24시샤인동물메디컬센터', '서울 송파구 방이동 108-7', '02-2088-7775', 127.1115620434, 37.5108294466, 'http://place.map.kakao.com/1941364028');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '웨스턴동물의료센터', '서울 마포구 노고산동 40-56', '02-701-7580', 126.9380830664, 37.5551960496, 'http://place.map.kakao.com/15727904');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '드림동물병원', '서울 강남구 역삼동 771', '02-508-7583', 127.0422170000, 37.4971261439, 'http://place.map.kakao.com/12255315');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '24시 로운동물의료센터', '서울 양천구 목동 807-1', '02-6953-7576', 126.8642366630, 37.5308225786, 'http://place.map.kakao.com/1601217278');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '24시 애니 동물병원', '서울 성북구 보문동5가 3-1', '02-926-8275', 127.0198027555, 37.5841713203, 'http://place.map.kakao.com/1639433227');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '24시월드펫동물메디컬센터', '서울 양천구 신월동 602-1', '02-2698-7582', 126.8453755003, 37.5205306779, 'http://place.map.kakao.com/1509590395');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '24시수동물메디컬센터', '서울 영등포구 양평동1가 28-3', '02-2676-7582', 126.8911923879, 37.5223071924, 'http://place.map.kakao.com/20248355');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '닥터펫동물의료센터', '서울 강남구 삼성동 35-3', '02-3443-8275', 127.0435012838, 37.5120931789, 'http://place.map.kakao.com/14569826');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '서울대학교 수의과대학 동물병원', '서울 관악구 신림동 산 56-1', '02-880-8661', 126.9531781599, 37.4681506717, 'http://place.map.kakao.com/17807930');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '24시지구촌 동물메디컬센터', '서울 구로구 구로동 512-2', '02-869-7582',126.8858613973, 37.4982048065, 'http://place.map.kakao.com/10775432');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '24시 스마트동물메디컬센터', '서울 은평구 응암동 91-5', '02-387-7582', 126.9181271391, 37.5997831031, 'http://place.map.kakao.com/19244935');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '서울동물심장병원', '서울 강남구 역삼동 689-2', '02-6203-7588', 127.0408651901, 37.5066953006, 'http://place.map.kakao.com/883892852');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '에코동물병원', '서울 송파구 가락동 137-3', '02-443-2222', 127.1281090996, 37.4927610189, 'http://place.map.kakao.com/12384571');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, 'VIP동물의료센터 성북점', '서울 성북구 동소문동6가 3-1', '02-953-0075', 127.0134108654, 37.5923325097, 'http://place.map.kakao.com/27559633');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, 'VIP동물의료센터 동대문점', '서울 동대문구 장안동 376-3', '02-2215-7522', 127.0675516968, 37.5663472990, 'http://place.map.kakao.com/27532150');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, 'N동물의료센터 강북점', '서울 강북구 미아동 138-19', '02-984-0075', 127.0296627905, 37.6183519368, 'http://place.map.kakao.com/25448952');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '웰케어동물병원', '서울 마포구 서교동 447-2', '02-337-7575', 126.9190919932, 37.5568451015, 'http://place.map.kakao.com/8430046');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '압구정동물의료센터', '서울 강남구 신사동 617', '02-547-7588', 127.0325177928, 37.5286850789, 'http://place.map.kakao.com/1855155802');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '우리동물메디컬센터', '서울 금천구 독산동 950-3', '02-853-7582', 126.9047273958, 37.4797310522, 'http://place.map.kakao.com/22454588');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '스마트동물병원 신사본점', '서울 강남구 신사동 587-13', '02-549-0275', 127.0299996536, 37.5207073699, 'http://place.map.kakao.com/24568431');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '둘리동물병원', '서울 도봉구 창동 661-64', '02-993-0075',127.0358625046, 37.6500838274, 'http://place.map.kakao.com/331615749');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '그레이스 고양이병원', '서울 강남구 청담동 88-38', '02-3442-5554', 127.0408917842, 37.5243450527, 'http://place.map.kakao.com/16680392');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '반려동물의료센터 다울', '서울 서초구 방배동 915-10', '02-3473-0911', 126.9946126630, 37.4807397272, 'http://place.map.kakao.com/14854774');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '송파마리동물병원', '서울 송파구 방이동 115-4', '02-415-7555', 127.1149366963, 37.5090755601, 'http://place.map.kakao.com/1268211718');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '에이블동물병원', '서울 서초구 양재동 4-14', '02-2058-1882', 127.0416697089, 37.4809190109, 'http://place.map.kakao.com/19476837');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '페토피아동물병원', '서울 송파구 방이동 124-8', '02-414-0075', 127.1166623276, 37.5081404280, 'http://place.map.kakao.com/16054794');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '헬릭스동물메디컬센터', '서울 서초구 반포동 19-1', '02-2135-9119', 127.0022800354, 37.5048886236, 'http://place.map.kakao.com/916295968');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '금천24시K동물의료센터', '서울 금천구 시흥동 999-1', '02-808-2475', 126.8996011973, 37.4561401504, 'http://place.map.kakao.com/270921004');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '남서울동물병원', '서울 서초구 방배동 809-1', '02-582-2186', 126.9916367338, 37.4924481552, 'http://place.map.kakao.com/1600999750');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '라온펫동물병원', '서울 송파구 송파동 122-7', '02-555-2272', 127.1139065545, 37.5040156148, 'http://place.map.kakao.com/1435846881');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '골드퍼피동물병원 공릉점', '서울 노원구 공릉동 596-5', '0507-1360-5956', 127.0749453104, 37.6205978499, 'http://place.map.kakao.com/1967064193');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '나음동물의료센터', '서울 강서구 화곡동 1026-12', '02-2690-9990', 126.8380431886, 37.5445252989, 'http://place.map.kakao.com/1287004186');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '로얄동물메디컬센터 강동', '서울 강동구 길동 236-7', '02-457-0075', 127.1414003571, 37.5341271406, 'http://place.map.kakao.com/276576667');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '봉천현대동물병원', '서울 관악구 봉천동 1612-1', '02-878-9978', 126.9557036191, 37.4794584207, 'http://place.map.kakao.com/1488283964');


INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '퍼피즌동물병원', '서울 강남구 삼성동 34-3', '02-541-2651', 127.0429850285, 37.5132223261, 'http://place.map.kakao.com/14624117');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '24시마곡M동물의료센터', '서울 강서구 방화동 246-64', '02-2662-7515', 126.8162648270, 37.5685842591, 'http://place.map.kakao.com/357483361');

INSERT INTO animal_hospital (id, place_name, address, phone, longitude, latitude, place_url) 
VALUES (animal_hospital_seq.NEXTVAL, '잠실베스트동물메디컬센터', '서울 송파구 석촌동 2-9', '02-2203-7975', 127.0973992140, 37.5049611171, 'http://place.map.kakao.com/24576378');



COMMIT;

select * from animal_hospital;
