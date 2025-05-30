DROP TABLE cat;
DROP SEQUENCE cat_seq;

CREATE TABLE cat (
    id NUMBER PRIMARY KEY,
    name_ko VARCHAR2(100) NOT NULL,                      -- 품종 이름 (한글)
    name_en VARCHAR2(100),                               -- 품종 이름 (영문)
    origin VARCHAR2(100),                                -- 원산지
    history VARCHAR2(1000),                              -- 유래/역사
    lifespan VARCHAR2(20),                               -- 수명 (예: '12~15년')
    personality VARCHAR2(100),                           -- 성격 요약
    weight VARCHAR2(50),                                 -- 몸무게 (예: '3~6kg')
    height VARCHAR2(50),                                 -- 키 (예: '23~25cm')
    coat VARCHAR2(500),                                  -- 털 길이/특성
    color VARCHAR2(200),                                 -- 대표 색상
    vocality VARCHAR2(10) CHECK (vocality IN ('낮음', '중간', '높음')), -- 울음소리 빈도/크기
    intelligence VARCHAR2(10) CHECK (intelligence IN ('낮음', '중간', '높음')), -- 지능
    affection_level VARCHAR2(10) CHECK (affection_level IN ('낮음', '중간', '높음')), -- 애정도
    independence VARCHAR2(10) CHECK (independence IN ('낮음', '중간', '높음')),       -- 독립성
    activity_level VARCHAR2(10) CHECK (activity_level IN ('낮음', '중간', '높음')),   -- 활동량
    grooming VARCHAR2(500),                              -- 손질/미용 주기
    health_issues VARCHAR2(1000),                        -- 건강 이슈
    shedding_level VARCHAR2(10) CHECK (shedding_level IN ('낮음', '중간', '높음')),  -- 털 빠짐 정도
    hypoallergenic CHAR(1) CHECK (hypoallergenic IN ('Y', 'N')),                     -- 저자극성 여부
    compatibility_with_kids VARCHAR2(10) CHECK (compatibility_with_kids IN ('낮음', '중간', '높음')), -- 아이들과의 친화력
    beginner_friendly CHAR(1) CHECK (beginner_friendly IN ('Y', 'N')),              -- 초보자 적합 여부
    environment VARCHAR2(500),                             -- 추천 반려 환경
    breed_type VARCHAR2(20) CHECK (breed_type IN ('장묘종', '단묘종', '소형묘')),       -- 묘종 구분
    image VARCHAR2(300)                                    -- 대표 이미지 경로
);
CREATE SEQUENCE cat_seq;






INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '아메리칸 쇼트헤어', 'American Shorthair', '미국',
    '영국에서 미국으로 이주한 고양이에서 유래. 튼튼하고 건강한 품종.',
    '15~20년', '온순하고 사교적임', '3~6kg', '23~25cm',
    '짧고 빽빽한 털', '실버탭비, 브라운, 블랙 등 다양',
    '중간', '중간', '중간', '중간', '중간',
    '주 1회 빗질 권장', '심각한 유전 질환 적음',
    '중간', 'N', '높음', 'Y',
    '가정집, 아이 있는 환경 모두 적합',
    '단묘종', 'american_shorthair.png'
);

-- 러시안 블루
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '러시안 블루', 'Russian Blue', '러시아',
    '러시아 북부에서 유래. 차분하고 우아한 고양이로 알려짐.',
    '15~20년', '조용하고 충성심 강함', '3~5kg', '23~25cm',
    '짧고 부드러운 이중모', '푸른빛 회색(블루 그레이)',
    '낮음', '높음', '중간', '높음', '중간',
    '주 1회 빗질 정도면 충분', '비교적 건강한 편',
    '낮음', 'Y', '중간', 'Y',
    '조용한 가정 환경에 적합', '단묘종', 'russian_blue.png'
);

-- 페르시안
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '페르시안', 'Persian', '이란(페르시아)',
    '수백 년의 역사를 가진 대표적인 장모종. 고급스러움의 상징.',
    '12~17년', '차분하고 느긋함', '3~7kg', '24~26cm',
    '길고 풍성한 털', '화이트, 블루, 크림 등 다양한 색상',
    '중간', '중간', '중간', '중간', '낮음',
    '매일 빗질 필수', '눈물흘림, 호흡기 문제, 다낭성 신장질환',
    '높음', 'N', '중간', 'N',
    '조용한 실내 환경 적합', '장묘종', 'persian.png'
);

-- 메인쿤
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '메인쿤', 'Maine Coon', '미국',
    '북미 지역에서 자연 발생한 대형 고양이. "젠틀 자이언트"로 불림.',
    '12~15년', '사교적이고 온화함', '6~9kg', '25~40cm',
    '두껍고 방수성 있는 이중모', '브라운, 레드, 블랙, 화이트 등 다양',
    '중간', '높음', '높음', '중간', '중간',
    '주 2~3회 이상 정기적 빗질', '고관절이형성증, 심근증',
    '높음', 'N', '높음', 'Y',
    '넓은 공간이 있는 집, 어린이와도 잘 어울림', '장묘종', 'maine_coon.png'
);

-- 코니시 렉스
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '코니시 렉스', 'Cornish Rex', '영국',
    '1950년대 영국에서 돌연변이로 태어난 곱슬털 고양이.',
    '11~15년', '활동적이고 호기심 많음', '2.5~4kg', '20~23cm',
    '짧고 곱슬거리는 벨벳 같은 털', '화이트, 블랙, 크림 등 다양',
    '높음', '높음', '높음', '중간', '높음',
    '주기적인 목욕 및 부드러운 빗질', '피부 민감, 체온 관리 유의',
    '낮음', 'Y', '높음', 'Y',
    '따뜻한 실내 환경 적합', '소형묘', 'cornish_rex.png'
);

-- 브리티시 쇼트헤어
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '브리티시 쇼트헤어', 'British Shorthair', '영국',
    '고대 로마 고양이의 후손으로 알려짐. 안정적인 성격으로 유명.',
    '12~17년', '침착하고 독립적', '4~8kg', '24~28cm',
    '짧고 밀도 높은 털', '블루, 크림, 실버, 골드',
    '낮음', '중간', '중간', '높음', '낮음',
    '주 1~2회 빗질 권장', '비만, 심장질환',
    '중간', 'N', '중간', 'Y',
    '조용하고 넓은 공간 선호', '단묘종', 'british_shorthair.png'
);

-- 벵갈
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '벵갈', 'Bengal', '미국',
    '야생 표범고양이와의 교배로 탄생. 강렬한 외모와 에너지.',
    '12~16년', '활발하고 지능 높음', '4~7kg', '25~30cm',
    '짧고 매끄러운 털', '스팟 무늬, 마블 무늬',
    '높음', '높음', '중간', '중간', '높음',
    '주 1회 빗질', '심장질환(   HCM) 가능성',
    '중간', 'N', '중간', 'N',
    '운동량 많아 활동 공간 필요', '단묘종', 'bengal.png'
);

-- 이집션 마우
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '이집션 마우', 'Egyptian Mau', '이집트',
    '고대 이집트로부터 유래한 것으로 알려짐. 천연 반점무늬 보유.',
    '13~16년', '조용하고 민첩함', '3~5kg', '23~26cm',
    '짧고 반짝이는 털', '실버, 브론즈, 스모크',
    '낮음', '중간', '중간', '중간', '높음',
    '주 1회 빗질', '민감한 성향, 유전질환 낮음',
    '낮음', 'N', '중간', 'Y',
    '조용한 가정 적합', '단묘종', 'egyptian_mau.png'
);

-- 오리엔탈 쇼트헤어
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '오리엔탈 쇼트헤어', 'Oriental Shorthair', '태국/미국',
    '샴 고양이에서 파생된 품종으로 다양한 색상과 활발한 성격이 특징.',
    '12~15년', '사교적이고 수다스러움', '3~5kg', '22~25cm',
    '짧고 밀착된 털', '모든 색상과 무늬 가능',
    '높음', '높음', '높음', '중간', '높음',
    '주 1회 빗질', '심장병 등 유전성 질환 주의',
    '중간', 'N', '중간', 'N',
    '사람과의 교류 많은 가정에 적합', '단묘종', 'oriental_shorthair.png'
);

-- 터키시 앙고라
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '터키시 앙고라', 'Turkish Angora', '터키',
    '오래된 자연 품종 중 하나로, 우아하고 똑똑함.',
    '12~18년', '지적이고 에너지 넘침', '3~5kg', '24~28cm',
    '부드럽고 실키한 중장모', '화이트, 블랙, 블루 등 다양',
    '중간', '높음', '높음', '중간', '높음',
    '주 2회 빗질', '청각 문제 (백색+청안)', 
    '중간', 'N', '높음', 'Y',
    '활동 공간이 있는 실내', '장묘종', 'turkish_angora.png'
);

-- 라가머핀
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '라가머핀', 'Ragamuffin', '미국',
    '랙돌에서 파생된 품종으로 더욱 부드럽고 애교 많음.',
    '12~16년', '온순하고 애정 많음', '5~9kg', '24~30cm',
    '부드럽고 풍성한 장모', '화이트, 크림, 블루 등',
    '낮음', '중간', '높음', '중간', '낮음',
    '주 2~3회 빗질', '심장질환, 다발성 낭종',
    '높음', 'N', '높음', 'Y',
    '아이, 노인과 잘 지냄', '장묘종', 'ragamuffin.png'
);

-- 히말라얀
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '히말라얀', 'Himalayan', '미국/영국',
    '페르시안과 샴의 교배로 탄생. 샴의 색과 페르시안의 외모.',
    '9~15년', '온순하고 얌전함', '4~6kg', '24~28cm',
    '길고 부드러운 털', '포인트 컬러 (크림+다크 포인트)',
    '중간', '중간', '중간', '중간', '낮음',
    '매일 빗질 권장', '호흡기 문제, 다낭성 신장질환',
    '높음', 'N', '중간', 'N',
    '조용한 실내, 스트레스 적은 환경', '장묘종', 'himalayan.png'
);

-- 노르웨이 숲고양이
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '노르웨이 숲고양이', 'Norwegian Forest Cat', '노르웨이',
    '추운 기후에서 진화된 자연 품종. 두꺼운 이중모를 가짐.',
    '14~16년', '독립적이며 조용함', '4~9kg', '26~30cm',
    '길고 방수성 있는 털', '화이트, 브라운, 블랙 등 다양',
    '낮음', '중간', '중간', '높음', '중간',
    '주 2~3회 빗질', '심장병(HCM), 고관절이형성',
    '높음', 'N', '중간', 'Y',
    '실내외 겸용 가능, 넓은 공간 선호', '장묘종', 'norwegian_forest_cat.png'
);

-- 싱가푸라
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '싱가푸라', 'Singapura', '싱가포르',
    '가장 작은 고양이 품종 중 하나로, 활발하고 호기심 많음.',
    '12~15년', '장난꾸러기이면서 애정 많음', '2~3kg', '20~22cm',
    '짧고 부드러운 털', '세피아 아구티',
    '중간', '중간', '높음', '중간', '높음',
    '주 1회 빗질', '유전성 빈혈, 비뇨기계 질환',
    '낮음', 'N', '중간', 'Y',
    '소형 아파트 등 협소한 공간도 적합', '소형묘', 'singapura.png'
);

-- 먼치킨
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '먼치킨', 'Munchkin', '미국',
    '짧은 다리를 가진 유전적 돌연변이 고양이로 활발하고 사교적.',
    '12~15년', '사교적이고 장난기 많음', '3~4kg', '17~20cm',
    '짧거나 중간 길이', '화이트, 블랙, 회색 등',
    '중간', '중간', '높음', '중간', '중간',
    '주 1회 빗질', '관절 및 척추 문제 가능성',
    '중간', 'N', '높음', 'Y',
    '실내 생활 적합, 장시간 점프 적음', '소형묘', 'munchkin.png'
);

-- 데본 렉스
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '데본 렉스', 'Devon Rex', '영국',
    '특유의 곱슬털과 큰 귀, 개와 같은 성격으로 인기 많음.',
    '9~15년', '활발하고 호기심 강함', '2~4kg', '20~23cm',
    '부드러운 곱슬털', '모든 색상',
    '높음', '높음', '높음', '중간', '높음',
    '털 빠짐 거의 없음', '피부질환 주의',
    '낮음', 'Y', '높음', 'Y',
    '에너지 높은 가정, 함께 놀아주는 환경', '소형묘', 'devon_rex.png'
);

-- 재패니즈 밥테일
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '재패니즈 밥테일', 'Japanese Bobtail', '일본',
    '수백 년 전부터 일본에서 길러진 짧은 꼬리 고양이.',
    '11~15년', '밝고 명랑함', '2.5~4kg', '20~25cm',
    '짧거나 중간 털 (단/장모 모두 있음)', '삼색, 흰색, 검정 등',
    '중간', '높음', '높음', '중간', '높음',
    '주 1~2회 빗질', '유전적 질환 적음',
    '중간', 'N', '높음', 'Y',
    '아이들과 잘 어울림, 활동적인 환경', '소형묘', 'japanese_bobtail.png'
);

-- 스핑크스
INSERT INTO cat VALUES (
    cat_seq.NEXTVAL, '스핑크스', 'Sphynx', '캐나다',
    '1970년대 무모 돌연변이 고양이로부터 발전된 품종.',
    '9~14년', '사람 친화적이고 외향적', '3~6kg', '20~25cm',
    '털 없음 또는 미세한 솜털', '피부색상 다양 (분홍, 회색 등)',
    '높음', '높음', '높음', '낮음', '높음',
    '주기적인 목욕 필요', '피부건조, 체온조절 문제',
    '낮음', 'N', '높음', 'Y',
    '실내 적합, 따뜻한 환경 필수', '소형묘', 'sphynx.png'
);

COMMIT;

SELECT * FROM cat ORDER BY breed_type;