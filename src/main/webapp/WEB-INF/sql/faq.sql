CREATE TABLE FAQ (
    faq_no      NUMBER          CONSTRAINT PK_FAQ_NO PRIMARY KEY, -- 번호 (프라이머리 키)
    faq_title   VARCHAR2(300)   NOT NULL,                       -- 제목
    faq_content CLOB            NOT NULL,                       -- 내용 (긴 텍스트를 위해 CLOB 사용)
    faq_author  VARCHAR2(100)   NULL                            -- 작성자 (NULL 허용 가능)
);

CREATE SEQUENCE SEQ_FAQ_NO
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE
    NOCACHE;

-- 테이블 및 시퀀스 생성 확인을 위한 주석
-- DESC FAQ;
-- SELECT * FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_FAQ_NO';

SELECT * FROM FAQ;


-- 샘플 데이터 삽입 예시

-- 일반 문의
INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '펫케어 서비스는 무엇인가요?', '펫케어는 반려동물 양육에 필요한 미용/병원 예약, 상품 정보, 커뮤니티, 입양/분양, 고객 지원 등을 통합적으로 제공하는 종합 정보 서비스입니다.', 'admin');

-- 회원 관련
INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '회원가입은 어떻게 하나요?', '홈페이지 상단의 ''회원가입'' 버튼을 클릭하여 안내에 따라 정보를 입력하시면 쉽게 가입할 수 있습니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '아이디 또는 비밀번호를 잊어버렸어요.', '로그인 페이지의 ''아이디/비밀번호 찾기'' 기능을 이용해 주세요. 가입 시 입력한 이메일 정보가 필요합니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '개인 정보는 어디서 수정할 수 있나요?', '로그인 후 ''마이페이지'' > ''개인 정보 수정'' 메뉴에서 연락처, 비밀번호 등의 정보를 변경할 수 있습니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '회원 탈퇴는 어떻게 하나요?', '''마이페이지'' > ''회원 탈퇴'' 메뉴를 통해 진행하실 수 있습니다. 탈퇴 시 작성한 게시글 및 댓글 등의 정보 처리 방침을 확인해 주세요.', 'admin');

-- 미용/병원 예약
INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '미용실 예약은 어떻게 하나요?', '''미용 서비스'' 메뉴에서 원하시는 미용실을 검색하고, 상세 페이지 내 ''온라인 예약'' 버튼을 통해 예약 절차를 진행할 수 있습니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '동물병원 예약도 가능한가요?', '네, ''병원 서비스'' 메뉴에서 지역별 동물병원을 검색하고, 일부 병원의 경우 온라인 예약 기능을 제공하고 있습니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '예약한 내역을 확인하고 싶어요.', '로그인 후 ''마이페이지'' > ''예약 내역 확인''에서 미용 및 병원 예약 현황과 상세 정보를 보실 수 있습니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '미용실이나 병원 리뷰는 어떻게 작성하나요?', '해당 미용실 또는 병원을 이용하신 후, 마이페이지의 예약 내역 또는 각 업체 상세 정보 페이지 하단에서 리뷰를 작성하실 수 있습니다.', 'admin');

-- 마이페이지 기능
INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '반려동물 다이어리는 무엇인가요?', '반려동물의 일상, 건강 상태, 예방 접종일, 특별한 순간 등을 사진과 함께 기록하고 관리할 수 있는 마이페이지의 기능입니다.', 'admin');

-- 정보 서비스
INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '상품 정보는 어떤 것들이 있나요?', '사료, 간식, 장난감, 의류, 위생용품 등 다양한 반려동물 관련 상품 정보를 제공하며, 이는 외부 제휴 또는 크롤링 데이터를 기반으로 합니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '품종별 정보를 알고 싶어요.', '''반려동물 정보'' 메뉴에서 다양한 견종 및 묘종의 특징, 성격, 관리 방법, 주의 질환 등의 유용한 정보를 확인하실 수 있습니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '전문가 Q&A는 어떻게 이용하나요?', '''반려동물 정보'' > ''전문가 Q&A'' 게시판에 반려동물 관련 궁금한 점을 질문하시면, 제휴된 전문가 또는 운영팀이 답변을 드립니다.', 'admin');

-- 커뮤니티
INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '커뮤니티 게시판에 글을 쓰고 싶어요.', '회원가입 후 ''커뮤니티'' > ''자유 게시판'' 등 각 주제별 게시판에서 자유롭게 글을 작성하고 다른 사용자들과 정보를 공유할 수 있습니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '불량 게시글이나 사용자를 신고하고 싶어요.', '각 게시글 또는 사용자 프로필 하단/옆의 ''신고하기'' 버튼을 통해 부적절한 콘텐츠나 사용자를 신고할 수 있습니다. 신고 시 구체적인 사유를 선택하거나 입력해 주세요.', 'admin');

-- 입양/분양
INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '입양/분양 게시판 이용 방법이 궁금해요.', '''입양/분양'' 메뉴에서 현재 가족을 찾고 있는 아이들의 정보를 확인하거나, 책임감 있는 입양/분양을 위해 직접 글을 작성할 수 있습니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '비밀 댓글 기능은 무엇인가요?', '입양/분양 게시판 등에서 개인 정보 보호를 위해 글 작성자와 댓글 작성자, 그리고 관리자만 내용을 확인할 수 있는 댓글 기능입니다.', 'admin');

-- 고객센터
INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '1:1 문의는 어떻게 하나요?', '로그인 후 ''고객센터'' > ''1:1 문의하기'' 메뉴에서 문의 유형(예: 계정, 서비스 오류, 건의사항 등)을 선택하고 내용을 작성하여 제출해 주시면 됩니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '신고 처리 결과는 어디서 볼 수 있나요?', '''고객센터'' 내 ''신고 관련 처리/안내 게시판''에서 주요 신고 유형에 대한 처리 기준이나 공지사항을 확인할 수 있습니다. 개별 신고 건에 대한 상세 결과는 1:1 문의 답변 등으로 안내될 수 있습니다.', 'admin');

INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author)
VALUES (SEQ_FAQ_NO.NEXTVAL, '서비스 이용 중 오류가 발생했어요.', '''고객센터'' > ''1:1 문의''를 통해 오류가 발생한 화면의 스크린샷, 오류 메시지, 발생 상황 등을 자세히 알려주시면 신속하게 확인 후 개선하도록 하겠습니다.', 'admin');

COMMIT;

-- 모든 FAQ 조회 (최신순)
-- SELECT faq_no, faq_title, faq_author, TO_CHAR(SYSDATE, 'YYYY-MM-DD') AS created_date FROM FAQ ORDER BY faq_no DESC;
-- 특정 FAQ 내용 조회
-- SELECT * FROM FAQ WHERE faq_no = 1;

select * from faq;
