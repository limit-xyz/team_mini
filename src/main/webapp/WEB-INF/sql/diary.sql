DROP TABLE diary;
DROP SEQUENCE diary_seq;

CREATE TABLE diary (
    diary_no    NUMBER PRIMARY KEY,
    member_id   VARCHAR2(50) NOT NULL,
    pet_name    VARCHAR2(50) NOT NULL,
    title       VARCHAR2(100) NOT NULL,
    content     VARCHAR2(400) NOT NULL,
    reg_date    DATE DEFAULT SYSDATE NOT NULL,
    photo       VARCHAR2(200),
    
    CONSTRAINT fk_diary_member 
        FOREIGN KEY (member_id)
        REFERENCES member(id)
        ON DELETE CASCADE
);

CREATE SEQUENCE diary_seq;

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '첫 산책', '콩이와 첫 산책을 다녀왔다. 아주 신났음.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '목욕 데이', '초코가 목욕을 했는데 물을 너무 싫어함.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '간식 실험', '새 간식을 줘봤는데 너무 좋아한다.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '미용실 다녀옴', '털을 깎고 나니 한층 산뜻해짐.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '병원 방문', '정기검진 받았는데 건강함!', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '친구 만남', '공원에서 다른 강아지들과 잘 놀았다.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '비 오는 날 산책', '비를 싫어해서 집 앞만 살짝 걸었다.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '훈련 성공!', '기다려 훈련에 성공해서 뿌듯.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '생일파티', '콩이 생일이라 간식파티 열어줌.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '동물 카페 탐방', '강아지 동반 카페 다녀옴.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '새 장난감', '삑삑이 장난감을 엄청 좋아함.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '운동장 놀기', '달리기 엄청 잘함!', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '건강검진 결과', '모든 수치 정상이었음.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '장난감 부심', '새 장난감을 다른 강아지에게 자랑함.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '여름 산책', '덥지만 아침 일찍 산책 나감.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '산책 후 발 씻기', '발 닦는 걸 싫어하지만 참음.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '강아지 모임 참석', '다른 보호자들과 이야기 많이 나눔.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '간식 주기', '좋아하는 간식을 하루 한 번 줌.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user02', '콩이', '셀프 사진 찍기', '핸드폰 카메라 보고 포즈 취함.', NULL);

INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo)
VALUES (diary_seq.NEXTVAL, 'user03', '초코', '애견 용품 쇼핑', '새 옷이 마음에 드는 듯.', NULL);

COMMIT;

SELECT * FROM diary;


-- 페이징 처리용 더미값 집어넣기
CREATE SEQUENCE d_s1;
CREATE SEQUENCE d_s2;
CREATE SEQUENCE d_s3;

INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
INSERT INTO diary (diary_no, member_id, pet_name, title, content) VALUES (diary_seq.NEXTVAL, 'user02', d_s1.NEXTVAL, d_s2.NEXTVAL, d_s3.NEXTVAL);
COMMIT;