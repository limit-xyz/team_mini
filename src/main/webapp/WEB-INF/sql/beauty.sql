DROP TABLE animal_beauty;
CREATE TABLE animal_beauty (
  id NUMBER PRIMARY KEY,
  place_name VARCHAR2(100 CHAR) NOT NULL,
  address VARCHAR2(200 CHAR) NOT NULL,
  phone VARCHAR2(20 CHAR) unique,
  longitude NUMBER(15,10),
  latitude NUMBER(15,10),
  place_url VARCHAR2(300 CHAR)
);

DROP SEQUENCE animal_beauty_seq;
CREATE SEQUENCE animal_beauty_seq;

INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '애니살롱 본점', '서울 강남구 역삼동 687-1', '02-542-1555', 127.04036023599129, 37.50781272030771, 'http://place.map.kakao.com/944340882');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '디오네', '서울 서초구 서초동 1486-14', '02-6245-1234', 127.008558941463, 37.4829749430531, 'http://place.map.kakao.com/2086985574');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '우유 우리동네강아지유치원미용실 호텔', '서울 서초구 반포동 701-48', '070-4006-7634', 127.017899699389, 37.5089472182308, 'http://place.map.kakao.com/70441801');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '슈몽', '서울 강남구 역삼동 698-6', '02-6415-0202', 127.04375111769035, 37.506049147286994, 'http://place.map.kakao.com/11418449');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '24시 가히애견유치원호텔 광진건대점', '서울 광진구 화양동 467-1', '070-8019-3944', 127.078466149037, 37.5454175888105, 'http://place.map.kakao.com/1901504251');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '청담고양이', '서울 강남구 논현동 149-1', '010-9563-3007', 127.027417863414, 37.5104149309628, 'http://place.map.kakao.com/1485680648');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '안녕강아지와고양이', '서울 송파구 오금동 25-5', '02-400-0080', 127.131000710207, 37.5064639446508, 'http://place.map.kakao.com/22094703');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '펌킨펫하우스 청담', '서울 강남구 논현동 273-13', '02-6204-5960', 127.03938901141477, 37.51056741942688, 'http://place.map.kakao.com/72073323');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, 'MM애견공방', '서울 송파구 잠실동 234-8', '02-418-5559', 127.08526775631081, 37.50536964214978, 'http://place.map.kakao.com/1618762036');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '우쭈쭈 그루밍 합정점', '서울 마포구 서교동 444-6', '02-3144-0851', 126.912411008033, 37.5531813837428, 'http://place.map.kakao.com/422731068');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '엘리자 바스 강남본점', '서울 강남구 논현동 77-11', '02-511-0518', 127.032447151204, 37.5170837890247, 'http://place.map.kakao.com/1994924919');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '달이네애견미용실', '서울 강남구 논현동 125-17', '02-394-3213', 127.025485392208, 37.5118821988719, 'http://place.map.kakao.com/1848492107');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '멍스타일', '서울 강동구 길동 331-13', '02-477-0173', 127.145612229449, 37.5432473448809, 'http://place.map.kakao.com/201487172');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '릴리줄리살롱', '서울 강남구 삼성동 26-34', '02-549-1128', 127.048705553033, 37.514105792631, 'http://place.map.kakao.com/955776953');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '다온', '서울 금천구 독산동 969-28', '010-3030-5216', 126.90085580792098, 37.47579045691324, 'http://place.map.kakao.com/1611998079');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '니니애견미용실', '서울 중랑구 묵동 233-100', '02-977-9981', 127.07546989612803, 37.61625024940116, 'http://place.map.kakao.com/1309619533');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '애니살롱 1호점', '서울 강남구 삼성동 103-16', '010-3579-1550', 127.063955218082, 37.5168213529579, 'http://place.map.kakao.com/1956983295');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '봄동살롱 - 고양이미용실', '서울 성북구 종암동 3-368', '010-9415-9881', 127.03923334601484, 37.59772098740066, 'http://place.map.kakao.com/1233812606');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '로얄캣', '서울 강남구 논현동 176-9', '010-5282-1203', 127.0313071925, 37.5095453957562, 'http://place.map.kakao.com/1289945003');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '마이뷰티독', '서울 강서구 화곡동 354-63', '010-6363-7907', 126.839297364887, 37.5327401395987, 'http://place.map.kakao.com/1973840900');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '꽃냥이네', '서울 은평구 대조동 206-10', '070-4115-0079', 126.92157678994344, 37.61455446577359, 'http://place.map.kakao.com/1485663199');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '투퍼피', '서울 관악구 신림동 1429-16', '010-7592-8183', 126.928063268393, 37.4886567349796, 'http://place.map.kakao.com/681706352');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '파파펫살롱', '서울 중랑구 묵동 248-21', '010-9984-9461', 127.075190416393, 37.6052844909324, 'http://place.map.kakao.com/1303591104');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '컴포터블', '서울 동작구 상도1동 319-3', '010-6472-6651', 126.953397095807, 37.5015033850359, 'http://place.map.kakao.com/270177811');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '아이뽀라', '서울 용산구 신창동 77-75', '010-7929-0561', 126.955421336615, 37.5359044682191, 'http://place.map.kakao.com/1193171965');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '앙꼬네강아지미용실', '서울 성동구 행당동 232-1', '02-2295-4560', 127.03666699566, 37.5599845791999, 'http://place.map.kakao.com/1356140428');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '어글리독', '서울 관악구 신림동 507-29', NULL, 126.91816228274506, 37.482765807839996, 'http://place.map.kakao.com/908118034');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '개이쁘다', '서울 강동구 암사동 473-15', '02-427-5888', 127.13385518544752, 37.55196309796629, 'http://place.map.kakao.com/1525388684');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '디자인독', '서울 강남구 논현동 190-17', '010-3365-3444', 127.030821396181, 37.50813455009, 'http://place.map.kakao.com/718000733');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '드로잉독', '서울 성북구 삼선동4가 95', '010-6312-6855', 127.011390474911, 37.589827963652, 'http://place.map.kakao.com/1282080846');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '작고소중해', '서울 중랑구 면목동 177-158', '010-4195-0223', 127.07732136763042, 37.586302832169885, 'http://place.map.kakao.com/243924907');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '따뜻하개', '서울 관악구 신림동 1631-11', '010-5700-1102', 126.92899187223, 37.4788047695389, 'http://place.map.kakao.com/2077030924');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '그루미송', '서울 서대문구 북가좌동 329-41', '010-5698-3272', 126.91217947414901, 37.58267349620626, 'http://place.map.kakao.com/422186242');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '보미펫살롱', '서울 성북구 보문동1가 143-2', '010-7196-4420', 127.019274628768, 37.5864824548832, 'http://place.map.kakao.com/1403147245');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '강아지미용실', '서울 중랑구 묵동 238-33', NULL, 127.07564720235312, 37.613500319433015, 'http://place.map.kakao.com/200130077');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '스마일마이펫', '서울 송파구 문정동 32', '010-6447-7303', 127.12529853150583, 37.488361712981835, 'http://place.map.kakao.com/1404513095');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '리즈강아지미용실', '서울 강북구 미아동 87-86', '02-989-0575', 127.03373909291, 37.6153235213997, 'http://place.map.kakao.com/1113543132');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '단델리온견살롱', '서울 성북구 장위동 75-462', '010-6695-3072', 127.051582910656, 37.6156152978408, 'http://place.map.kakao.com/1169207255');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '올어바웃도그', '서울 송파구 석촌동 16-5', '010-9251-6700', 127.098915614341, 37.5030713502542, 'http://place.map.kakao.com/54087628');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '쉬리펫살롱', '서울 강서구 염창동 183', '02-6013-1967', 126.8737036403508, 37.551765096514174, 'http://place.map.kakao.com/1483531920');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '노랑퍼피 애견미용실', '서울 강동구 천호동 84-7', '010-3395-1066', 127.13718928838696, 37.54341330367037, 'http://place.map.kakao.com/97408088');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '포근 펫 살롱', '서울 금천구 독산동 1043-12', '010-8010-6861', 126.90301577555529, 37.46390972444227, 'http://place.map.kakao.com/1414541228');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '도그히얼', '서울 영등포구 문래동6가 11-1', '010-6774-6444', 126.88793274237938, 37.5211724854758, 'http://place.map.kakao.com/1517374855');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '창 너머 고양이', '서울 서초구 서초동 1628-38', '0503-7150-1168', 127.019120572872, 37.4886960094851, 'http://place.map.kakao.com/369747845');
INSERT INTO animal_beauty (id, place_name, address, phone, longitude, latitude, place_url) VALUES (animal_beauty_seq.NEXTVAL, '원스독', '서울 강북구 미아동 813', '010-8241-5952', 127.021845756924, 37.6132324248698, 'http://place.map.kakao.com/74658927');

commit;

select * from animal_beauty;