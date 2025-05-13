DROP TABLE member;

CREATE TABLE member (
    id            VARCHAR2(50) PRIMARY KEY,
    name          VARCHAR2(50) NOT NULL,
    password      VARCHAR2(50) NOT NULL,
    gender        VARCHAR2(10),
    mobile        VARCHAR2(20),
    address       VARCHAR2(200),
    email         VARCHAR2(100),
    reg_date      DATE DEFAULT SYSDATE NOT NULL,
    introduction  VARCHAR2(300),
    birth_date    DATE,
    ban_date      DATE DEFAULT SYSDATE-1 NOT NULL,
    ban_reason    VARCHAR2(200),
    role          VARCHAR2(20) DEFAULT 'user' NOT NULL
);

-- 관리자 계정
INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('midas', '관리자', '1234', 'M', '010-1111-2222', '서울시 강남구', 'midas@example.com', SYSDATE, '관리자 계정입니다.', TO_DATE('1980-01-01', 'YYYY-MM-DD'), 'admin');

-- 일반 사용자 계정
INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('user01', '홍길동', '1234', 'M', '010-1234-5678', '서울시 종로구', 'hong@example.com', SYSDATE, '안녕하세요. 홍길동입니다.', TO_DATE('1990-05-10', 'YYYY-MM-DD'), 'user');

INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('user02', '김영희', '1234', 'F', '010-2345-6789', '부산시 해운대구', 'kimyh@example.com', SYSDATE, '반갑습니다. 김영희예요.', TO_DATE('1992-08-15', 'YYYY-MM-DD'), 'user');

INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('user03', '이철수', '1234', 'M', '010-3456-7890', '대구시 수성구', 'lee@example.com', SYSDATE, '이철수입니다.', TO_DATE('1988-03-22', 'YYYY-MM-DD'), 'user');

INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('user04', '박지민', '1234', 'F', '010-4567-8901', '인천시 남동구', 'parkjm@example.com', SYSDATE, '반가워요~', TO_DATE('1995-12-01', 'YYYY-MM-DD'), 'user');

INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('user05', '최수정', '1234', 'F', '010-5678-9012', '광주시 북구', 'choisj@example.com', SYSDATE, '잘 부탁드려요.', TO_DATE('1993-07-19', 'YYYY-MM-DD'), 'user');

INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('user06', '장도윤', '1234', 'M', '010-6789-0123', '울산시 남구', 'jangdy@example.com', SYSDATE, '장도윤입니다.', TO_DATE('1991-09-03', 'YYYY-MM-DD'), 'user');

INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('user07', '윤하늘', '1234', 'F', '010-7890-1234', '경기도 수원시', 'yunhn@example.com', SYSDATE, '하늘처럼 밝게!', TO_DATE('1994-11-30', 'YYYY-MM-DD'), 'user');

INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('user08', '백승훈', '1234', 'M', '010-8901-2345', '강원도 춘천시', 'baeksh@example.com', SYSDATE, '백승훈입니다.', TO_DATE('1989-06-14', 'YYYY-MM-DD'), 'user');

INSERT INTO member (id, name, password, gender, mobile, address, email, reg_date, introduction, birth_date, role)
VALUES ('user09', '오지은', '1234', 'F', '010-9012-3456', '제주시 애월읍', 'ohje@example.com', SYSDATE, '제주도 살아요~', TO_DATE('1996-10-27', 'YYYY-MM-DD'), 'user');

commit;

SELECT * FROM member;