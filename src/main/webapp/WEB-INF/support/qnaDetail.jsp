<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: #f8f9fa;
}

:root {
	--main-theme-color: #C5E1D4;
	--main-theme-darker-text: #2c5c49;
	--main-theme-border-color: #b0d1c0;
	--main-theme-light-bg: #eef3f1;
	--text-muted-custom: #5a6670;
}

.btn-custom-theme {
	background-color: var(--main-theme-color);
	border-color: var(--main-theme-border-color);
	color: var(--main-theme-darker-text);
	font-weight: 500;
}

.btn-custom-theme:hover, .btn-custom-theme:focus {
	background-color: #b3d6c6;
	border-color: #a0c3b3;
	color: var(--main-theme-darker-text);
}

.qna-header-block {
	background-color: var(--main-theme-light-bg);
	border-left: 5px solid var(--main-theme-color);
}

.qna-detail-title {
	color: var(--main-theme-darker-text);
	font-weight: 700;
	font-size: 1.75rem;
}

.qna-meta-info {
	font-size: 0.9rem;
	color: var(--text-muted-custom);
}

.qna-meta-info strong {
	color: #343a40;
}

.qna-meta-info .bi {
	margin-right: 0.25rem;
	color: var(--main-theme-color);
}

.status-badge {
	padding: 0.4em 0.7em;
	font-size: 0.85em;
	font-weight: 500;
	vertical-align: middle;
}

.status-answered {
	background-color: var(--main-theme-color) !important;
	color: var(--main-theme-darker-text) !important;
}

.status-waiting {
	background-color: #ffc107 !important;
	color: #333 !important;
}

.content-card {
	background-color: #fff;
	border: 1px solid #dee2e6;
	box-shadow: 0 0.125rem 0.375rem rgba(0, 0, 0, 0.06);
}

.content-card .card-header {
	background-color: #f8f9fa;
	color: var(--main-theme-darker-text);
	font-weight: 600;
	border-bottom: 1px solid #e9ecef;
}

.content-card .card-body p, .content-card .card-body .form-label {
	/* 폼 레이블에도 동일한 스타일 적용 */
	white-space: pre-wrap;
	line-height: 1.8;
	color: #495057;
	margin-bottom: 0.5rem; /* 레이블 하단 마진 */
}

.content-card .card-body p {
	margin-bottom: 0;
}

.answer-card .card-header { /* 기존 답변 표시용 */
	background-color: var(--main-theme-light-bg);
}

.answer-card { /* 기존 답변 표시용 */
	border-top: 3px solid var(--main-theme-color);
}

.answer-meta-info { /* 기존 답변 표시용 */
	font-size: 0.85rem;
	color: var(--text-muted-custom);
}

.answer-meta-info .bi { /* 기존 답변 표시용 */
	margin-right: 0.25rem;
}

.answer-form-card .card-header { /* 답변 작성 폼용 카드 헤더 */
	background-color: var(--main-theme-light-bg);
}

.answer-form-card .card-header h5 {
	margin-bottom: 0; /* h5 기본 마진 제거 */
	font-size: 1.15rem; /* 질문/답변 내용 헤더보다 약간 작게 */
}

.action-buttons .btn {
	margin-right: 0.5rem;
	font-weight: 500;
}

.action-buttons .btn-sm {
	padding: 0.3rem 0.6rem;
	font-size: 0.875rem;
}
</style>
</head>
<body>

	<div class="container mt-5 mb-5">

		<div class="qna-header-block p-4 mb-4 rounded">
			<h1 class="qna-detail-title mb-3">서비스 이용 중 오류 발생 문의 (답변 대기 상태
				예시)</h1>
			<div class="qna-meta-info">
				<span class="me-3"><i class="bi bi-person"></i> 작성자: <strong>user02</strong></span>
				<span class="me-3"><i class="bi bi-calendar3"></i> 작성일:
					2025-05-20</span> <span><i class="bi bi-hourglass-split"></i> 상태: <span
					class="badge rounded-pill status-badge status-waiting">답변대기</span></span>
			</div>
		</div>

		<div class="card content-card mb-4">
			<div class="card-header">
				<i class="bi bi-patch-question-fill me-1"
					style="color: var(--main-theme-darker-text);"></i> <strong>질문
					내용</strong>
			</div>
			<div class="card-body">
				<p>
					안녕하세요. 금일 오전 10시경부터 OOO 서비스의 특정 기능 사용 시 간헐적으로 '요청 처리 실패' 메시지가
					나타납니다. <br> 몇 번 재시도하면 성공하기도 하는데, 원인 파악 및 조치 부탁드립니다. <br>
					감사합니다.
				</p>
			</div>
		</div>

		<div class="card content-card answer-form-card mb-4">
			<div class="card-header">
				<h5>
					<i class="bi bi-pencil-fill me-2"
						style="color: var(--main-theme-darker-text);"></i>답변 작성하기
				</h5>
			</div>
			<div class="card-body">
				<form action="실제_답변_처리_URL" method="post">
					<div class="mb-3">
						<label for="answerText" class="form-label"><strong>답변
								내용:</strong></label>
						<textarea class="form-control" id="answerText"
							name="answerContent" rows="10"
							placeholder="문의에 대한 답변을 상세히 입력해주세요." required></textarea>
					</div>
					<div class="text-end">
						<button type="submit" class="btn btn-custom-theme px-4">
							<i class="bi bi-check2-circle"></i> 답변 등록
						</button>
					</div>
				</form>
			</div>
		</div>

		<div class="row mt-5 pt-4 border-top">
			<div class="col-md-auto me-auto action-buttons mb-2 mb-md-0">
				<button type="button" class="btn btn-outline-secondary btn-sm">
					<i class="bi bi-pencil-square"></i> 질문 수정
				</button>
				<button type="button" class="btn btn-outline-danger btn-sm">
					<i class="bi bi-trash3"></i> 질문 삭제
				</button>

			</div>
			<div class="col-md-auto action-buttons">
				<a href="#" class="btn btn-custom-theme"><i
					class="bi bi-list-task"></i> 목록으로</a>
			</div>
		</div>

	</div>