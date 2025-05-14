/* 기존의 jspbbs 테이블을 삭제하고 추천, 땡큐를 저장할 컬럼을 추가하여 다시 생성한다.
 **/
 -- 게시글 번호, 제목, 글쓴이, 내용, 날짜, 조회수, 비밀번호, 파일정보, 추천, 땡큐
-- no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank

CREATE TABLE adoption_post(
  post_id	 INT AUTO_INCREMENT PRIMARY KEY,
  user_id	 VARCHAR2(50 CHAR) NOT NULL,
  title 	 VARCHAR2(50 CHAR) NOT NULL,
  content  VARCHAR2(1000 CHAR) NOT NULL,
  adoption_type		 VARCHAR2(10) NOT NULL, --'입양' 또는 '분양'
  region 	 VARCHAR2(100),
  animal_type_main VARCHAR(50),	--'강아지' 또는 '고양이'
  animal_type_detail VARCHAR(50), --'대형견'/'소형견' or '단모종'/장모종'
  image_path VARCHAR@(300),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  views  INT DEFAULT 0
  --approval_status  <- 입양 /분양 상태를 입양 완료/ 분양완료/ 진행중 대기 상태 등으로 표기할 예정
);

/* jspbbs에서 사용할 시퀀스
 * 기존의 시퀀스를 삭제하고 다시 생성한다.
 **/
CREATE SEQUENCE adoption_seq;


-- 페이징 처리와 댓글 처리를 위해서 아래 SQL 쿼리를 COMMIT까지 10번 실행 한다. 
-- 주의 : 199번과 200번 게시글을 댓글(reply) 테이블에서 참조하고 있음
INSERT INTO adoption_post (user_id, title, content, type, region, animal_type_main, animal_type_detail, image_path, created_at, views)
VALUES 
('love_bunny', '사랑스러운 강아지 입양합니다', '건강한 강아지를 입양할 분을 찾습니다. 관심 있으시면 연락 주세요.', '입양', '서울시 강남구', '강아지', '소형견', '/images/dog1.jpg', '2025-05-12 10:30:00', 120),
('cat_lovers', '고양이 분양합니다', '귀여운 고양이가 새로운 집을 찾고 있습니다. 입양을 원하시면 연락 주세요.', '분양', '서울시 서초구', '고양이', '단모종', '/images/cat1.jpg', '2025-05-11 14:00:00', 45),
('dog_lover_88', '다정한 고양이 입양해 주세요', '이 고양이는 아주 사람을 좋아합니다. 입양을 원하시면 연락주세요.', '입양', '서울시 마포구', '고양이', '장모종', '/images/cat2.jpg', '2025-05-10 18:45:00', 58),
('happy_pet_owner', '튼튼한 강아지 분양합니다', '튼튼하고 건강한 강아지를 분양합니다. 소형견을 원하시는 분 연락 주세요.', '분양', '경기도 성남시', '강아지', '소형견', '/images/dog2.jpg', '2025-05-09 08:20:00', 62),
('kitten_fanatic', '기다리고 있는 입양 고양이', '따뜻한 집을 기다리고 있는 귀여운 고양이입니다. 관심 있으신 분들은 연락 주세요.', '입양', '서울시 용산구', '고양이', '단모종', '/images/cat3.jpg', '2025-05-08 11:30:00', 30),
('paw_hero', '귀여운 강아지 입양합니다', '이 강아지는 정말 착하고 사람을 좋아해요. 입양할 분을 기다립니다.', '입양', '서울시 송파구', '강아지', '중형견', '/images/dog3.jpg', '2025-05-07 15:00:00', 85),
('pet_paradise', '모든 종류의 고양이 입양 중', '우리는 다양한 고양이를 입양 중입니다. 원하는 고양이를 찾아보세요.', '입양', '경기도 고양시', '고양이', '다양한 품종', '/images/cat4.jpg', '2025-05-06 17:00:00', 110),
('four_legged_friend', '강아지 분양합니다', '건강한 강아지를 분양합니다. 관심 있으시면 바로 연락 주세요!', '분양', '인천시 부평구', '강아지', '소형견', '/images/dog4.jpg', '2025-05-05 09:40:00', 95),
('pets_adore', '귀여운 고양이 입양해 주세요', '이 고양이는 굉장히 활발하고 귀여워요. 따뜻한 집을 찾아요.', '입양', '서울시 동대문구', '고양이', '단모종', '/images/cat5.jpg', '2025-05-04 13:20:00', 76),
('lovely_pet', '분양할 귀여운 강아지', '사랑스러운 강아지가 새로운 집을 찾고 있습니다. 연락 주세요!', '분양', '경기도 화성시', '강아지', '중형견', '/images/dog5.jpg', '2025-05-03 11:10:00', 42),
('meow_pals', '입양할 고양이들', '저희 집에는 여러 고양이가 있습니다. 입양을 원하시면 연락 주세요.', '입양', '서울시 강동구', '고양이', '소형묘', '/images/cat6.jpg', '2025-05-02 14:50:00', 60),
('doggy_lover', '튼튼한 강아지 분양합니다', '귀엽고 건강한 강아지를 찾고 있습니다. 관심 있으면 연락 주세요!', '분양', '서울시 영등포구', '강아지', '대형견', '/images/dog6.jpg', '2025-05-01 10:00:00', 108),
('kitten_queen', '사랑스러운 고양이 입양 중', '사람을 매우 좋아하는 고양이입니다. 따뜻한 집을 기다리고 있어요.', '입양', '서울시 강서구', '고양이', '단모종', '/images/cat7.jpg', '2025-04-30 12:40:00', 50),
('paws_and_claws', '강아지와 고양이 입양합니다', '강아지와 고양이를 동시에 입양하실 분을 찾습니다. 관심 있으면 연락 주세요.', '입양', '부산시 해운대구', '강아지', '소형견', '/images/dog7.jpg', '2025-04-29 16:30:00', 38),
('tail_waggers', '분양할 강아지들', '튼튼하고 건강한 강아지를 분양합니다. 집을 찾고 있어요.', '분양', '서울시 노원구', '강아지', '대형견', '/images/dog8.jpg', '2025-04-28 11:15:00', 120);
COMMIT;
SELECT * FROM adoption_post;








/* 먼저 HR 스키마에 reply 테이블과 reply 테이블에서 사용할 SEQUENCE를 생성한다.
 * 아래의 댓글 데이터를 reply 테이블에 추가한다.
 **/
DROP TABLE reply;
CREATE TABLE reply(
	no NUMBER(7) PRIMARY KEY,
	bbs_no NUMBER(7) NOT NULL,
	reply_content VARCHAR2(500 CHAR),
	reply_writer VARCHAR2(20 CHAR) NOT NULL,
	reg_date TIMESTAMP NOT NULL,	
	CONSTRAINT reply_fk FOREIGN KEY(bbs_no) REFERENCES jspbbs(no)
);

-- 댓글 테이블의 시퀀스
DROP SEQUENCE reply_seq;
CREATE SEQUENCE reply_seq
      MAXVALUE 9999999
      INCREMENT BY 1      
      NOCACHE
      NOORDER
      NOCYCLE;


-- 댓글 데이터 추가하기 - 1번만 실행 한다. --
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '항상 감사합니다.' || CHR(13) || CHR(10) || ' 땡큐!~', 'midas', '2023-05-08 13:44:32');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '저두 감사드립니다.', 'servlet', '2023-05-09 05:24:57');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '저두요~ ^_^', 'midas', '2023-05-09 09:19:23');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 199, '나두 관심이 많은뎅~', 'servlet', '2023-05-09 11:54:45');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 199, '헤헷~ 감사 합니다.', 'admin', '2023-05-09 12:16:51');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 199, '아~ 다들 이 사이트가 잘되길 바라는 군요', 'midas', '2023-05-09 13:34:11');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 199, '관리자님께서 워낙 신경을 쓰시니...' || CHR(13) || CHR(10) || '잘될 겁니다. ', 'servlet', '2023-05-09 14:03:37');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '저두 사이트 발전에 관심이 많습니다.' || CHR(13) || CHR(10) || '감사합니다.' , 'midas', '2023-05-09 14:39:29');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '와~ 대단하네요 우리 사이트~' || CHR(13) || CHR(10) || 'ㅋㅋㅋ~', 'servlet', '2023-05-09 14:41:18');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '우리 관리자님이 워낙 열심이시라~' || CHR(13) || CHR(10) || '그리고 회원님들도 열성으로 사이트에 충성 접속하시니...'|| CHR(13) || CHR(10) ||' 않될 수가 있나요...^_^', 'admin', '2023-05-09 14:52:48');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '네 맞아요~', 'admin', '2023-05-10 15:42:12');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '그렇죠 그렇고 말구요', 'servlet', '2023-05-11 15:44:57');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '항상 감사합니다.', 'midas', '2023-05-15 16:19:23');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '땡큐!~', 'servlet', '2023-05-16 17:31:45');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 199, '저두요~ 땡큐!~', 'servlet', '2023-05-19 18:16:51');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 199, '모두들 열성이네요~' || CHR(13) || CHR(10) || '헤헤~' || CHR(13) || CHR(10) || '땡큐!~', 'admin', '2023-05-20 10:34:11');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 199, '땡큐~ 여기 붙어라~ ^_^', 'midas', '2023-05-20 11:33:27');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 199, '땡큐~ 붙었어요~', 'admin', '2023-05-20 13:19:59');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '당연 회원이면 관심 가져야죠~', 'servlet', '2023-06-10 13:41:17');
INSERT INTO reply(no, bbs_no, reply_content, reply_writer, reg_date) VALUES(reply_seq.NEXTVAL, 200, '동감 합니다.' || CHR(13) || CHR(10) ||' 땡큐!~', 'midas', '2023-06-12 14:52:48');

commit;
SELECT * FROM reply;




