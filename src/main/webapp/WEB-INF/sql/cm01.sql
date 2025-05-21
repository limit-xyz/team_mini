CREATE TABLE cm01 (
NO NUMBER,         
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

CREATE SEQUENCE cm01_SEQ
START WITH 101
INCREMENT BY 1
NOCACHE;

SELECT * FROM CM01;


INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (1, '우리 집 강아지의 하루 #1', '문예진', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (1)', SYSDATE, 87, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (2, '고양이와 함께한 주말 #1', '배지호', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (1)', SYSDATE, 56, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (3, '반려견과의 첫 만남 #1', '심유나', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (1)', SYSDATE, 15, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (4, '고슴도치 키우기 도전기 #1', '노지민', '고슴도치를 키우면서 배우는 점이 많아요. (1)', SYSDATE, 99, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (5, '앵무새가 말을 해요 #1', '서하람', '앵무새가 사람 말을 따라 해서 놀랐어요. (1)', SYSDATE, 15, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (6, '강아지 간식 만들기 #1', '임다인', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (1)', SYSDATE, 23, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (7, '고양이 화장실 추천 #1', '백지훈', '고양이 화장실 선택은 정말 중요한 문제더라고요. (1)', SYSDATE, 1, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (8, '반려동물 보험 알아보기 #1', '강도현', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (1)', SYSDATE, 1, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (9, '햄스터의 첫 탈출 #1', '강도현', '햄스터가 집을 탈출해서 한참 찾았어요! (1)', SYSDATE, 41, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (10, '거북이 산책 후기 #1', '정예린', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (1)', SYSDATE, 45, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (11, '우리 집 강아지의 하루 #2', '심유나', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (2)', SYSDATE, 98, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (12, '고양이와 함께한 주말 #2', '임다인', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (2)', SYSDATE, 58, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (13, '반려견과의 첫 만남 #2', '노지민', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (2)', SYSDATE, 78, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (14, '고슴도치 키우기 도전기 #2', '윤도윤', '고슴도치를 키우면서 배우는 점이 많아요. (2)', SYSDATE, 23, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (15, '앵무새가 말을 해요 #2', '한수연', '앵무새가 사람 말을 따라 해서 놀랐어요. (2)', SYSDATE, 2, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (16, '강아지 간식 만들기 #2', '오세윤', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (2)', SYSDATE, 64, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (17, '고양이 화장실 추천 #2', '김서연', '고양이 화장실 선택은 정말 중요한 문제더라고요. (2)', SYSDATE, 57, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (18, '반려동물 보험 알아보기 #2', '서하람', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (2)', SYSDATE, 94, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (19, '햄스터의 첫 탈출 #2', '강도현', '햄스터가 집을 탈출해서 한참 찾았어요! (2)', SYSDATE, 80, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (20, '거북이 산책 후기 #2', '윤도윤', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (2)', SYSDATE, 14, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (21, '우리 집 강아지의 하루 #3', '최민재', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (3)', SYSDATE, 19, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (22, '고양이와 함께한 주말 #3', '심유나', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (3)', SYSDATE, 1, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (23, '반려견과의 첫 만남 #3', '강도현', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (3)', SYSDATE, 97, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (24, '고슴도치 키우기 도전기 #3', '서하람', '고슴도치를 키우면서 배우는 점이 많아요. (3)', SYSDATE, 71, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (25, '앵무새가 말을 해요 #3', '임다인', '앵무새가 사람 말을 따라 해서 놀랐어요. (3)', SYSDATE, 29, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (26, '강아지 간식 만들기 #3', '정예린', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (3)', SYSDATE, 29, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (27, '고양이 화장실 추천 #3', '정예린', '고양이 화장실 선택은 정말 중요한 문제더라고요. (3)', SYSDATE, 44, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (28, '반려동물 보험 알아보기 #3', '신하람', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (3)', SYSDATE, 2, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (29, '햄스터의 첫 탈출 #3', '오세윤', '햄스터가 집을 탈출해서 한참 찾았어요! (3)', SYSDATE, 94, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (30, '거북이 산책 후기 #3', '한수연', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (3)', SYSDATE, 43, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (31, '우리 집 강아지의 하루 #4', '심유나', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (4)', SYSDATE, 28, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (32, '고양이와 함께한 주말 #4', '이준호', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (4)', SYSDATE, 42, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (33, '반려견과의 첫 만남 #4', '문예진', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (4)', SYSDATE, 99, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (34, '고슴도치 키우기 도전기 #4', '임다인', '고슴도치를 키우면서 배우는 점이 많아요. (4)', SYSDATE, 74, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (35, '앵무새가 말을 해요 #4', '임다인', '앵무새가 사람 말을 따라 해서 놀랐어요. (4)', SYSDATE, 49, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (36, '강아지 간식 만들기 #4', '박하늘', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (4)', SYSDATE, 49, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (37, '고양이 화장실 추천 #4', '박하늘', '고양이 화장실 선택은 정말 중요한 문제더라고요. (4)', SYSDATE, 29, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (38, '반려동물 보험 알아보기 #4', '문예진', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (4)', SYSDATE, 2, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (39, '햄스터의 첫 탈출 #4', '김서연', '햄스터가 집을 탈출해서 한참 찾았어요! (4)', SYSDATE, 81, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (40, '거북이 산책 후기 #4', '신하람', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (4)', SYSDATE, 17, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (41, '우리 집 강아지의 하루 #5', '서하람', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (5)', SYSDATE, 85, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (42, '고양이와 함께한 주말 #5', '배지호', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (5)', SYSDATE, 85, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (43, '반려견과의 첫 만남 #5', '신하람', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (5)', SYSDATE, 67, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (44, '고슴도치 키우기 도전기 #5', '한수연', '고슴도치를 키우면서 배우는 점이 많아요. (5)', SYSDATE, 7, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (45, '앵무새가 말을 해요 #5', '조아라', '앵무새가 사람 말을 따라 해서 놀랐어요. (5)', SYSDATE, 49, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (46, '강아지 간식 만들기 #5', '강도현', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (5)', SYSDATE, 80, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (47, '고양이 화장실 추천 #5', '김서연', '고양이 화장실 선택은 정말 중요한 문제더라고요. (5)', SYSDATE, 27, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (48, '반려동물 보험 알아보기 #5', '윤도윤', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (5)', SYSDATE, 9, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (49, '햄스터의 첫 탈출 #5', '백지훈', '햄스터가 집을 탈출해서 한참 찾았어요! (5)', SYSDATE, 99, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (50, '거북이 산책 후기 #5', '서하람', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (5)', SYSDATE, 6, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (51, '우리 집 강아지의 하루 #6', '서하람', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (6)', SYSDATE, 51, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (52, '고양이와 함께한 주말 #6', '최민재', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (6)', SYSDATE, 84, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (53, '반려견과의 첫 만남 #6', '심유나', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (6)', SYSDATE, 83, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (54, '고슴도치 키우기 도전기 #6', '강도현', '고슴도치를 키우면서 배우는 점이 많아요. (6)', SYSDATE, 97, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (55, '앵무새가 말을 해요 #6', '정예린', '앵무새가 사람 말을 따라 해서 놀랐어요. (6)', SYSDATE, 0, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (56, '강아지 간식 만들기 #6', '정예린', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (6)', SYSDATE, 49, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (57, '고양이 화장실 추천 #6', '김서연', '고양이 화장실 선택은 정말 중요한 문제더라고요. (6)', SYSDATE, 57, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (58, '반려동물 보험 알아보기 #6', '심유나', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (6)', SYSDATE, 36, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (59, '햄스터의 첫 탈출 #6', '신하람', '햄스터가 집을 탈출해서 한참 찾았어요! (6)', SYSDATE, 93, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (60, '거북이 산책 후기 #6', '윤도윤', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (6)', SYSDATE, 4, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (61, '우리 집 강아지의 하루 #7', '이준호', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (7)', SYSDATE, 48, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (62, '고양이와 함께한 주말 #7', '노지민', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (7)', SYSDATE, 45, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (63, '반려견과의 첫 만남 #7', '한수연', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (7)', SYSDATE, 77, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (64, '고슴도치 키우기 도전기 #7', '문예진', '고슴도치를 키우면서 배우는 점이 많아요. (7)', SYSDATE, 42, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (65, '앵무새가 말을 해요 #7', '한수연', '앵무새가 사람 말을 따라 해서 놀랐어요. (7)', SYSDATE, 17, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (66, '강아지 간식 만들기 #7', '백지훈', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (7)', SYSDATE, 68, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (67, '고양이 화장실 추천 #7', '백지훈', '고양이 화장실 선택은 정말 중요한 문제더라고요. (7)', SYSDATE, 77, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (68, '반려동물 보험 알아보기 #7', '강도현', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (7)', SYSDATE, 57, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (69, '햄스터의 첫 탈출 #7', '백지훈', '햄스터가 집을 탈출해서 한참 찾았어요! (7)', SYSDATE, 40, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (70, '거북이 산책 후기 #7', '심유나', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (7)', SYSDATE, 77, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (71, '우리 집 강아지의 하루 #8', '김서연', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (8)', SYSDATE, 20, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (72, '고양이와 함께한 주말 #8', '강도현', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (8)', SYSDATE, 27, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (73, '반려견과의 첫 만남 #8', '이준호', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (8)', SYSDATE, 47, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (74, '고슴도치 키우기 도전기 #8', '심유나', '고슴도치를 키우면서 배우는 점이 많아요. (8)', SYSDATE, 75, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (75, '앵무새가 말을 해요 #8', '정예린', '앵무새가 사람 말을 따라 해서 놀랐어요. (8)', SYSDATE, 52, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (76, '강아지 간식 만들기 #8', '백지훈', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (8)', SYSDATE, 33, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (77, '고양이 화장실 추천 #8', '장지우', '고양이 화장실 선택은 정말 중요한 문제더라고요. (8)', SYSDATE, 51, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (78, '반려동물 보험 알아보기 #8', '배지호', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (8)', SYSDATE, 9, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (79, '햄스터의 첫 탈출 #8', '윤도윤', '햄스터가 집을 탈출해서 한참 찾았어요! (8)', SYSDATE, 6, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (80, '거북이 산책 후기 #8', '구현우', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (8)', SYSDATE, 19, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (81, '우리 집 강아지의 하루 #9', '서하람', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (9)', SYSDATE, 2, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (82, '고양이와 함께한 주말 #9', '정예린', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (9)', SYSDATE, 23, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (83, '반려견과의 첫 만남 #9', '구현우', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (9)', SYSDATE, 51, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (84, '고슴도치 키우기 도전기 #9', '정예린', '고슴도치를 키우면서 배우는 점이 많아요. (9)', SYSDATE, 91, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (85, '앵무새가 말을 해요 #9', '배지호', '앵무새가 사람 말을 따라 해서 놀랐어요. (9)', SYSDATE, 61, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (86, '강아지 간식 만들기 #9', '임다인', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (9)', SYSDATE, 36, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (87, '고양이 화장실 추천 #9', '배지호', '고양이 화장실 선택은 정말 중요한 문제더라고요. (9)', SYSDATE, 70, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (88, '반려동물 보험 알아보기 #9', '노지민', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (9)', SYSDATE, 24, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (89, '햄스터의 첫 탈출 #9', '윤도윤', '햄스터가 집을 탈출해서 한참 찾았어요! (9)', SYSDATE, 93, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (90, '거북이 산책 후기 #9', '오세윤', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (9)', SYSDATE, 46, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (91, '우리 집 강아지의 하루 #10', '서하람', '정말 행복했던 하루였어요. 강아지가 웃는 것 같았어요. (10)', SYSDATE, 33, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (92, '고양이와 함께한 주말 #10', '김서연', '고양이와 함께 집에서 뒹굴거리며 보낸 주말이 최고였어요. (10)', SYSDATE, 44, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (93, '반려견과의 첫 만남 #10', '강도현', '처음 만났을 땐 낯설었지만 금방 친해졌어요. (10)', SYSDATE, 73, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (94, '고슴도치 키우기 도전기 #10', '이준호', '고슴도치를 키우면서 배우는 점이 많아요. (10)', SYSDATE, 62, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (95, '앵무새가 말을 해요 #10', '서하람', '앵무새가 사람 말을 따라 해서 놀랐어요. (10)', SYSDATE, 44, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (96, '강아지 간식 만들기 #10', '임다인', '직접 만든 강아지 간식을 너무 잘 먹어서 뿌듯했어요. (10)', SYSDATE, 80, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (97, '고양이 화장실 추천 #10', '배지호', '고양이 화장실 선택은 정말 중요한 문제더라고요. (10)', SYSDATE, 42, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (98, '반려동물 보험 알아보기 #10', '한수연', '반려동물 보험이 생각보다 다양해서 고민이 되네요. (10)', SYSDATE, 79, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (99, '햄스터의 첫 탈출 #10', '김서연', '햄스터가 집을 탈출해서 한참 찾았어요! (10)', SYSDATE, 74, '1234', '', 0, 0);

INSERT INTO cm01(no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank) 
VALUES (100, '거북이 산책 후기 #10', '심유나', '거북이도 산책을 좋아한다는 걸 알게 됐어요. (10)', SYSDATE, 55, '1234', '', 0, 0);