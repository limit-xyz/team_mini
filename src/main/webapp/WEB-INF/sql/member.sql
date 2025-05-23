DROP TABLE member CASCADE CONSTRAINTS;

CREATE TABLE member (
    id            VARCHAR2(50) PRIMARY KEY,
    name          VARCHAR2(50) NOT NULL,
    password      VARCHAR2(50) NOT NULL,
    gender        VARCHAR2(10),
    mobile        VARCHAR2(20),
    zipcode		  VARCHAR2(200),
    address1      VARCHAR2(200),
    address2      VARCHAR2(200),
    email         VARCHAR2(100),
    reg_date      DATE DEFAULT SYSDATE NOT NULL,
    introduction  VARCHAR2(300),
    birth_date    DATE,
    ban_date      DATE DEFAULT SYSDATE-1 NOT NULL,
    ban_reason    VARCHAR2(200),
    role          VARCHAR2(20) DEFAULT 'user' NOT NULL
);


-- 관리자 계정
INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date, role)
VALUES ('midas', '관리자', '1234', '남', '010-0000-0000', '00000 ', '서울특별시 종로구', '청운동 100호', 'admin@example.com', '최고 관리자입니다.', TO_DATE('1980-12-12', 'YYYY-MM-DD'), 'admin');


-- 일반 회원 10명
INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user01', '홍길동', '1234', '남', '010-1234-5678', '12345 ', '서울특별시 강남구', '역삼동 101호', 'hong01@example.com', '안녕하세요, 홍길동입니다.', TO_DATE('1990-01-01', 'YYYY-MM-DD'));

INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user02', '김영희', '1234', '여', '010-2345-6789', '54321 ', '부산광역시 해운대구', '좌동 202호', 'kim02@example.com', '영희에요~ 반갑습니다!', TO_DATE('1992-02-14', 'YYYY-MM-DD'));

INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user03', '이철수', '1234', '남', '010-3456-7890', '67890 ', '대구광역시 수성구', '범어동 303호', 'lee03@example.com', '철수입니다.', TO_DATE('1988-03-03', 'YYYY-MM-DD'));

INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user04', '박지민', '1234', '여', '010-4567-8901', '11111 ', '인천광역시 연수구', '송도동 404호', 'park04@example.com', '지민입니다~!', TO_DATE('1995-04-20', 'YYYY-MM-DD'));

INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user05', '최유리', '1234', '여', '010-5678-9012', '22222 ', '광주광역시 북구', '두암동 505호', 'choi05@example.com', '유리입니다 :)', TO_DATE('1993-05-15', 'YYYY-MM-DD'));

INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user06', '정우성', '1234', '남', '010-6789-0123', '33333 ', '대전광역시 서구', '둔산동 606호', 'jung06@example.com', '정우성입니다.', TO_DATE('1985-06-10', 'YYYY-MM-DD'));

INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user07', '한가인', '1234', '여', '010-7890-1234', '44444 ', '울산광역시 남구', '삼산동 707호', 'han07@example.com', '가인입니다^^', TO_DATE('1984-07-07', 'YYYY-MM-DD'));

INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user08', '서강준', '1234', '남', '010-8901-2345', '55555 ', '경기도 성남시', '분당동 808호', 'seo08@example.com', '서강준이에요.', TO_DATE('1991-08-08', 'YYYY-MM-DD'));

INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user09', '이지은', '1234', '여', '010-9012-3456', '66666 ', '경기도 고양시', '일산동 909호', 'lee09@example.com', '이지은입니다.', TO_DATE('1993-09-18', 'YYYY-MM-DD'));

INSERT INTO member (id, name, password, gender, mobile, zipcode, address1, address2, email, introduction, birth_date)
VALUES ('user10', '장동건', '1234', '남', '010-0123-4567', '77777 ', '강원도 춘천시', '석사동 1010호', 'jang10@example.com', '장동건입니다.', TO_DATE('1972-10-01', 'YYYY-MM-DD'));

commit;

SELECT * FROM member;



-- 아래는 페이징 테스트용 쓰레기값 넣기
CREATE SEQUENCE m_s1;
CREATE SEQUENCE m_s2;
CREATE SEQUENCE m_s3;

INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
INSERT INTO member (id, name, password) VALUES (m_s1.NEXTVAL, m_s2.NEXTVAL, m_s3.NEXTVAL);
COMMIT;