DROP TABLE QNA_BOARD CASCADE CONSTRAINTS;
CREATE TABLE QNA_BOARD (
    NO NUMBER PRIMARY KEY,          -- 글번호 (기본키)
    STATUS VARCHAR2(50) NOT NULL,   -- 상태 (예: 답변대기, 답변완료 등)
    WRITER VARCHAR2(100) NOT NULL,  -- 작성자
    TITLE VARCHAR2(255) NOT NULL,   -- 제목
    CONTENT CLOB NOT NULL,          -- 내용 (긴 글을 위해 CLOB 타입 사용)
    REG_DATE DATE DEFAULT SYSDATE   -- 작성일 (기본값: 현재 날짜)
);

DROP SEQUENCE QNA_BOARD_SEQ;
CREATE SEQUENCE QNA_BOARD_SEQ;


-- user01 데이터 (10건)
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user01', 'user01 문의 1: 비밀번호 변경 문의', '안녕하세요. user01입니다. 비밀번호 변경은 어떻게 하나요?', SYSDATE - (1/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user01', 'user01 문의 2: 서비스 오류 문의', 'user01입니다. 서비스 접속 시 오류가 발생합니다. 확인 부탁드립니다.', SYSDATE - (2/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user01', 'user01 문의 3: 환불 규정 문의', '환불 규정에 대해 자세히 알고 싶습니다. user01입니다.', SYSDATE - (3/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user01', 'user01 문의 4: 기능 개선 제안', 'user01입니다. 새로운 기능 추가를 제안합니다. (내용)...', SYSDATE - (4/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user01', 'user01 문의 5: 개인정보 수정 요청', 'user01입니다. 가입 시 입력한 개인정보를 수정하고 싶습니다.', SYSDATE - (5/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user01', 'user01 문의 6: 이벤트 참여 방법 문의', '진행 중인 이벤트 참여 방법에 대해 문의드립니다. user01입니다.', SYSDATE - (6/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user01', 'user01 문의 7: 배송 조회 문의', 'user01입니다. 주문한 상품의 배송 현황을 알고 싶습니다.', SYSDATE - (7/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user01', 'user01 문의 8: 이용약관 문의', 'user01입니다. 서비스 이용약관 내용 중 궁금한 점이 있습니다.', SYSDATE - (8/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user01', 'user01 문의 9: 제휴 문의', '안녕하세요. user01입니다. 제휴 관련하여 문의드립니다.', SYSDATE - (9/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user01', 'user01 문의 10: 기타 문의사항', 'user01입니다. 기타 문의사항입니다. (내용)...', SYSDATE - (10/40));

-- user02 데이터 (10건)
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user02', 'user02 문의 1: 아이디 찾기 문의', 'user02입니다. 아이디를 잊어버렸습니다. 어떻게 찾을 수 있나요?', SYSDATE - (11/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user02', 'user02 문의 2: 결제 오류 문의', 'user02입니다. 결제 시도 중 오류가 발생했습니다.', SYSDATE - (12/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user02', 'user02 문의 3: 서비스 이용 방법 문의', 'user02입니다. 특정 서비스 이용 방법에 대해 알려주세요.', SYSDATE - (13/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user02', 'user02 문의 4: 계정 탈퇴 문의', 'user02입니다. 계정 탈퇴 절차를 알고 싶습니다.', SYSDATE - (14/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user02', 'user02 문의 5: 포인트 적립 문의', 'user02입니다. 포인트 적립 기준에 대해 문의합니다.', SYSDATE - (15/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user02', 'user02 문의 6: 광고 문의', 'user02입니다. 광고 게재 관련하여 문의드립니다.', SYSDATE - (16/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user02', 'user02 문의 7: 신고 접수', 'user02입니다. 불량 사용자 신고합니다. (내용)...', SYSDATE - (17/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user02', 'user02 문의 8: 앱 사용 불편사항', 'user02입니다. 모바일 앱 사용 중 불편한 점이 있습니다.', SYSDATE - (18/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user02', 'user02 문의 9: 기술 지원 요청', 'user02입니다. 기술적인 지원이 필요합니다.', SYSDATE - (19/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user02', 'user02 문의 10: 고객센터 연결 문의', 'user02입니다. 고객센터와 통화하고 싶습니다.', SYSDATE - (20/40));

-- user03 데이터 (10건)
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user03', 'user03 문의 1: 상품 정보 문의', 'user03입니다. 특정 상품에 대한 상세 정보를 알고 싶습니다.', SYSDATE - (21/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user03', 'user03 문의 2: 쿠폰 사용 문의', 'user03입니다. 발급받은 쿠폰 사용 방법을 모르겠습니다.', SYSDATE - (22/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user03', 'user03 문의 3: 회원 등급 문의', 'user03입니다. 회원 등급별 혜택이 궁금합니다.', SYSDATE - (23/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user03', 'user03 문의 4: 시스템 점검 시간 문의', 'user03입니다. 시스템 정기 점검 시간이 언제인가요?', SYSDATE - (24/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user03', 'user03 문의 5: 파일 업로드 오류', 'user03입니다. 문의글 작성 시 파일 업로드가 되지 않습니다.', SYSDATE - (25/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user03', 'user03 문의 6: 개인정보보호정책 문의', 'user03입니다. 개인정보보호정책에 대해 문의합니다.', SYSDATE - (26/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user03', 'user03 문의 7: 추천인 제도 문의', 'user03입니다. 추천인 제도에 대해 궁금합니다.', SYSDATE - (27/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user03', 'user03 문의 8: 모바일 최적화 문의', 'user03입니다. 사이트가 모바일에서 잘 보이지 않습니다.', SYSDATE - (28/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user03', 'user03 문의 9: 해외 로그인 차단 문의', 'user03입니다. 해외 로그인 차단 기능이 있나요?', SYSDATE - (29/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user03', 'user03 문의 10: 데이터 백업 문의', 'user03입니다. 제 문의 데이터는 안전하게 백업되나요?', SYSDATE - (30/40));

-- user04 데이터 (10건)
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user04', 'user04 문의 1: 서비스 가입 절차 문의', 'user04입니다. 서비스 가입 절차를 알려주세요.', SYSDATE - (31/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user04', 'user04 문의 2: 콘텐츠 저작권 문의', 'user04입니다. 게시된 콘텐츠의 저작권에 대해 문의합니다.', SYSDATE - (32/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user04', 'user04 문의 3: 알림 설정 문의', 'user04입니다. 답변 알림 설정을 어떻게 변경하나요?', SYSDATE - (33/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user04', 'user04 문의 4: 자주 묻는 질문 (FAQ) 오류 제보', 'user04입니다. FAQ 내용 중 오류가 있어 제보합니다.', SYSDATE - (34/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user04', 'user04 문의 5: API 연동 문의', 'user04입니다. 외부 API 연동 가능 여부 및 방법 문의합니다.', SYSDATE - (35/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user04', 'user04 문의 6: 장애 발생 시 대처 방안 문의', 'user04입니다. 서비스 장애 발생 시 어떻게 대처해야 하나요?', SYSDATE - (36/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user04', 'user04 문의 7: 마케팅 수신 동의 변경', 'user04입니다. 마케팅 정보 수신 동의를 변경하고 싶습니다.', SYSDATE - (37/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user04', 'user04 문의 8: 고객 지원 시간 문의', 'user04입니다. 고객 지원 운영 시간이 궁금합니다.', SYSDATE - (38/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변대기', 'user04', 'user04 문의 9: 서비스 만족도 조사 참여', 'user04입니다. 서비스 만족도 조사에 참여하고 싶습니다.', SYSDATE - (39/40));
INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE)
VALUES (QNA_BOARD_SEQ.NEXTVAL, '답변완료', 'user04', 'user04 문의 10: 개선 요청사항 전달', 'user04입니다. 서비스 사용 중 느낀 개선점에 대해 전달합니다.', SYSDATE - (40/40));

COMMIT;



SELECT * From qna_board;

DROP TABLE QNA_ANSWER;
CREATE TABLE QNA_ANSWER (
    ANSWER_NO NUMBER PRIMARY KEY,               -- 답변 고유 번호 (기본키)
    NO NUMBER NOT NULL,                         -- 원본 질문 글번호 (QNA_BOARD 테이블의 NO 참조 - 외래키)
    ANSWER_CONTENT CLOB NOT NULL,               -- 답변 내용
    ANSWER_WRITER VARCHAR2(100) NOT NULL,       -- 답변 작성자 (예: 관리자 ID)
    ANSWER_REG_DATE DATE DEFAULT SYSDATE,       -- 답변 등록일
    CONSTRAINT FK_QNA_ANSWER_ORIGINAL_NO FOREIGN KEY (NO) -- 외래키 제약조건: QNA_ANSWER.NO가 QNA_BOARD.NO를 참조
        REFERENCES QNA_BOARD (NO) ON DELETE CASCADE -- 원본 질문 삭제 시 관련 답변도 삭제
);

DROP SEQUENCE QNA_ANSWER_SEQ;
CREATE SEQUENCE QNA_ANSWER_SEQ;