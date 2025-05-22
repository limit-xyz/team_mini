DROP TABLE cm01 CASCADE CONSTRAINTS;
CREATE TABLE cm01 (
NO NUMBER PRIMARY KEY,          
TITLE VARCHAR(1000),
WRITER VARCHAR(1000),  
CONTENT VARCHAR(1000),
REG_DATE TIMESTAMP,
READ_COUNT NUMBER,        
PASS VARCHAR(1000), 
FILE1 VARCHAR(1000),  
RECOMMEND NUMBER,           
THANK NUMBER                     
);

DROP SEQUENCE cm01_seq;
CREATE SEQUENCE cm01_seq;

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '우리 집 강아지의 하루', 'user02', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요.', SYSDATE, 87, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '고양이와 함께한 주말', 'user02', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요.', SYSDATE, 56, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '반려견과의 첫 만남', 'user04', '처음 만났을 땐 낯설었지만 금방 친해졌어요.', SYSDATE, 15, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '고슴도치 키우기 도전기', 'user05', '고슴도치를 키우면서 배우는 점이 많아요.', SYSDATE, 99, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '앵무새가 말을 해요', '서하람', '앵무새가 사람 말을 따라 해서 놀랐어요.', SYSDATE, 15, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '강아지 간식 만들기', '임다인', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요.', SYSDATE, 23, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '고양이 화장실 추천', '백지훈', '고양이 화장실 선택은 정말 중요한 문제더라고요.', SYSDATE, 1, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '반려동물 보험 알아보기', '강도현', '반려동물 보험이 생각보다 다양해서 고민이 되네요.', SYSDATE, 1, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '햄스터의 첫 탈출', '강도현', '햄스터가 집을 탈출해서 한참 찾았어요!', SYSDATE, 41, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (cm01_seq.NEXTVAL, '거북이 산책 후기', '정예린', '거북이도 산책을 좋아한다는 걸 알게 됐어요.', SYSDATE, 45, '1234', '', 0, 0);

COMMIT;

DROP TABLE cm01_reply;
CREATE TABLE cm01_reply(
	no NUMBER PRIMARY KEY,
	board_no NUMBER NOT NULL,
	reply_content VARCHAR2(500 CHAR),
	reply_writer VARCHAR2(20 CHAR) NOT NULL,
	reg_date TIMESTAMP NOT NULL,	
	CONSTRAINT reply_fk FOREIGN KEY(board_no) REFERENCES cm01(no) ON DELETE CASCADE
);

DROP SEQUENCE reply_seq;
CREATE SEQUENCE reply_seq;

COMMIT;