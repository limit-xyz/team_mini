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
drop table adoption_reply;
CREATE TABLE adoption_reply (
    reply_id NUMBER PRIMARY KEY,
    post_id NUMBER NOT NULL,
    user_id VARCHAR2(50 CHAR) NOT NULL,
    content VARCHAR2(500 CHAR) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_secret VARCHAR2(1) DEFAULT 'N', -- 비밀 댓글 여부 ('Y' 또는 'N')
    FOREIGN KEY (post_id) REFERENCES adoption_post(post_id) ON DELETE CASCADE
);

DROP SEQUENCE adoption_reply_seq;
CREATE SEQUENCE adoption_reply_seq;

-- 댓글 테이블의 시퀀스
DROP SEQUENCE reply_seq;
CREATE SEQUENCE reply_seq
      MAXVALUE 9999999
      INCREMENT BY 1      
      NOCACHE
      NOORDER
      NOCYCLE
SELECT
    ap.post_id,
    ap.title,
    COUNT(ar.reply_id) AS comment_count
FROM
    adoption_post ap
LEFT JOIN
    adoption_reply ar ON ap.post_id = ar.post_id
GROUP BY
    ap.post_id, ap.title
ORDER BY
    ap.post_id DESC;
      

-- 댓글 데이터 추가하기 - 1번만 실행 한다. --
-- 1번 게시글 ('말티즈 강아지 입양 보내요') 댓글
INSERT INTO adoption_reply (reply_id, post_id, user_id, content, created_at, is_secret)
VALUES (adoption_reply_seq.NEXTVAL, 1, 'happy_dogmom', '저희 집 강아지랑 친구가 될 수 있을까요? 성격이 순한가요?', CURRENT_TIMESTAMP, 'N');

INSERT INTO adoption_reply (reply_id, post_id, user_id, content, created_at, is_secret)
VALUES (adoption_reply_seq.NEXTVAL, 1, 'love_maltese', '말티즈 정말 좋아하는데, 혹시 입양 조건이 있나요?', CURRENT_TIMESTAMP, 'N');

INSERT INTO adoption_reply (reply_id, post_id, user_id, content, created_at, is_secret)
VALUES (adoption_reply_seq.NEXTVAL, 1, 'user_dog01', '네, 순하고 사람을 잘 따릅니다. 입양 조건은 책임감 있게 키워주실 분입니다.', CURRENT_TIMESTAMP, 'N');

INSERT INTO adoption_reply (reply_id, post_id, user_id, content, created_at, is_secret)
VALUES (adoption_reply_seq.NEXTVAL, 1, 'interested', '비밀 댓글 문의드립니다.', CURRENT_TIMESTAMP, 'Y');

-- 2번 게시글 ('러시안블루 고양이 분양') 댓글
INSERT INTO adoption_reply (reply_id, post_id, user_id, content, created_at, is_secret)
VALUES (adoption_reply_seq.NEXTVAL, 2, 'blue_lover', '러시안블루 너무 예뻐요! 분양 지역이 어디인가요?', CURRENT_TIMESTAMP, 'N');

INSERT INTO adoption_reply (reply_id, post_id, user_id, content, created_at, is_secret)
VALUES (adoption_reply_seq.NEXTVAL, 2, 'cat_adopter', '고양이 키우고 싶은데, 초보자도 괜찮을까요?', CURRENT_TIMESTAMP, 'N');

-- 7번 게시글 ('믹스견 입양 원해요') 댓글
INSERT INTO adoption_reply (reply_id, post_id, user_id, content, created_at, is_secret)
VALUES (adoption_reply_seq.NEXTVAL, 7, 'find_mydog', '믹스견 종류가 어떻게 되나요? 사진 더 볼 수 있을까요?', CURRENT_TIMESTAMP, 'N');

COMMIT;

SELECT * FROM adoption_reply;


