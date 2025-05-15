/* 기존의 jspbbs 테이블을 삭제하고 추천, 땡큐를 저장할 컬럼을 추가하여 다시 생성한다.
 **/
 -- 게시글 번호, 제목, 글쓴이, 내용, 날짜, 조회수, 비밀번호, 파일정보, 추천, 땡큐
-- no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank
drop table adoption_post;
CREATE TABLE adoption_post(
  post_id	  NUMBER PRIMARY KEY,
  user_id	 VARCHAR2(50 CHAR) NOT NULL,
  title 	 VARCHAR2(50 CHAR) NOT NULL,
  content  VARCHAR2(1000 CHAR) NOT NULL,
  adoption_type	VARCHAR2(10) NOT NULL, --'입양' 또는 '분양'
  region 	 VARCHAR2(100),
  animal_type_main VARCHAR2(50),	--'강아지' 또는 '고양이'
  animal_type_detail VARCHAR2(50), --'대형견'/'소형견' or '단모종'/장모종'
  image_path VARCHAR2(300),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  views_count  NUMBER DEFAULT 0,
  approval_status VARCHAR2(20 CHAR)   --<- 입양 /분양 상태를 입양 완료/ 분양완료/ 진행중 대기 상태 등으로 표기할 예정
);

/* jspbbs에서 사용할 시퀀스
 * 기존의 시퀀스를 삭제하고 다시 생성한다.
 **/
DROP SEQUENCE adoption_seq;

CREATE SEQUENCE adoption_seq;



-- 페이징 처리와 댓글 처리를 위해서 아래 SQL 쿼리를 COMMIT까지 10번 실행 한다. 
-- 주의 : 199번과 200번 게시글을 댓글(reply) 테이블에서 참조하고 있음

INSERT INTO adoption_post ( 
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'user_dog01', '말티즈 강아지 입양 보내요', '작고 귀여운 말티즈, 건강하고 활발해요.', '입양', '서울 마포구', '강아지', '소형견', 'images/maltese1.jpg', CURRENT_TIMESTAMP, 5, '진행중');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'user_cat02', '러시안블루 고양이 분양', '순하고 조용한 러시안블루입니다.', '분양', '서울 송파구', '고양이', '단모종', 'images/russianblue.jpg', CURRENT_TIMESTAMP, 9, '분양 완료');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'parrot_lover', '알록달록한 앵무새 입양', '말을 따라하는 똑똑한 앵무새예요.', '입양', '서울 종로구', '새', '앵무새', 'images/parrot2.jpg', CURRENT_TIMESTAMP, 11, '진행중');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'fish_fan', '열대어와 어항 분양', '다양한 열대어 포함된 어항입니다.', '분양', '서울 강서구', '어류', '4자 어항류', 'images/fish_tank.jpg', CURRENT_TIMESTAMP, 7, '진행중');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'rabbit_mom', '토끼 입양 보내요', '아기 토끼 2마리 함께 입양 보냅니다.', '입양', '서울 은평구', '포유류', '토끼', 'images/rabbit2.jpg', CURRENT_TIMESTAMP, 4, '진행중');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'snake_user', '옥수수뱀 분양합니다', '온순한 파충류이며 관리 잘 되어 있습니다.', '분양', '서울 중랑구', '파충류', '뱀', 'images/cornsnake.jpg', CURRENT_TIMESTAMP, 6, '분양 완료');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'mix_dog01', '믹스견 입양 원해요', '중형 믹스견으로 사람을 좋아해요.', '입양', '서울 강북구', '강아지', '중형견', 'images/mixdog.jpg', CURRENT_TIMESTAMP, 3, '진행중');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'cat_lady33', '아기 고양이 분양', '2개월 된 아기 고양이입니다.', '분양', '서울 동대문구', '고양이', '단모종', 'images/kitten.jpg', CURRENT_TIMESTAMP, 10, '진행중');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'hedgie_home', '고슴도치 입양 보내요', '순한 성격의 고슴도치입니다.', '입양', '서울 서대문구', '포유류', '고슴도치', 'images/hedgehog2.jpg', CURRENT_TIMESTAMP, 2, '입양 완료');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'labrador_dad', '래브라도 리트리버 분양', '아이들과 잘 지냅니다.', '분양', '서울 용산구', '강아지', '대형견', 'images/labrador2.jpg', CURRENT_TIMESTAMP, 8, '분양 완료');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'canary_fan', '노란 카나리아 입양 보낼게요', '노래 잘 부르는 새입니다.', '입양', '서울 중구', '새', '카나리아', 'images/canary2.jpg', CURRENT_TIMESTAMP, 3, '진행중');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'aqua_love', '금붕어 분양', '아이들이 키우던 금붕어입니다.', '분양', '서울 강남구', '어류', '2자 어항류', 'images/goldfish2.jpg', CURRENT_TIMESTAMP, 9, '분양 완료');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'cat_baby', '장모종 고양이 입양', '털 빠짐 많지만 애교 많은 아이예요.', '입양', '서울 도봉구', '고양이', '장모종', 'images/longfurcat.jpg', CURRENT_TIMESTAMP, 6, '진행중');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'ferret_love', '페럿 분양합니다', '훈련이 잘 되어 있습니다.', '분양', '서울 관악구', '포유류', '페럿', 'images/ferret.jpg', CURRENT_TIMESTAMP, 4, '진행중');

INSERT INTO adoption_post (
  post_id, user_id, title, content, adoption_type, region, animal_type_main,
  animal_type_detail, image_path, created_at, views_count, approval_status
) VALUES
(adoption_seq.NEXTVAL, 'shiba_dog', '시바견 입양 원해요', '활발하고 똑똑한 시바견입니다.', '입양', '서울 성북구', '강아지', '중형견', 'images/shiba.jpg', CURRENT_TIMESTAMP, 7, '진행중');

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
