DROP TABLE inquiry;
DROP SEQUENCE inquiry_seq;

CREATE TABLE inquiry (
    no      NUMBER PRIMARY KEY,
    user_id         VARCHAR2(50) NOT NULL,
    category        VARCHAR2(50) NOT NULL,
    title           VARCHAR2(100) NOT NULL,
    content         VARCHAR2(250) NOT NULL,
    status          VARCHAR2(20) DEFAULT '접수중' NOT NULL,
    reg_date      TIMESTAMP DEFAULT SYSDATE NOT NULL,
    ans_content  VARCHAR2(250),
    ans_date     TIMESTAMP
);

CREATE SEQUENCE inquiry_seq;

INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user01', '회원정보', '비밀번호 변경이 안돼요', '비밀번호 변경 시 오류가 발생합니다.', '접수중', SYSDATE);
INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user02', '결제', '결제 영수증이 필요해요', '영수증을 메일로 받을 수 있나요?', '접수중', SYSDATE);
INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user03', '이벤트', '이벤트 당첨 여부 확인', '지난 이벤트 당첨 결과가 궁금합니다.', '접수중', SYSDATE);
INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user04', '기타', '서비스 이용 문의', '서비스 이용 가능 시간대를 알고 싶습니다.', '접수중', SYSDATE);
INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user05', '회원정보', '회원 탈퇴 문의', '회원 탈퇴는 어떻게 하나요?', '접수중', SYSDATE);
INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user06', '결제', '환불 요청', '결제 후 환불은 어떻게 진행되나요?', '접수중', SYSDATE);
INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user07', '이벤트', '이벤트 참여 방법', '이벤트에 참여하려면 어떻게 하나요?', '접수중', SYSDATE);
INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user08', '기타', '앱 오류 문의', '앱 실행 시 강제 종료됩니다.', '접수중', SYSDATE);
INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user09', '회원정보', '이메일 변경', '회원 정보에 등록된 이메일을 변경하고 싶습니다.', '접수중', SYSDATE);
INSERT INTO inquiry (no, user_id, category, title, content, status, reg_date) VALUES (inquiry_seq.NEXTVAL, 'user10', '결제', '이중 결제 문의', '같은 결제가 두 번 된 것 같습니다.', '접수중', SYSDATE);

COMMIT;

SELECT * FROM inquiry;