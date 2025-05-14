<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="row my-5">
	<div class="col">
		<h2 class="mb-5">관리자 페이지</h2>
		
		<div class="row">
			<div class="col">
				<a href="member" class="p-3" style="border: 1px solid black; background-color:#EEE;">멤버 관리</a>
			</div>

			<div class="col">
				<a href="inquiry"  class="p-3" style="border: 1px solid black; background-color:#EEE;">문의 관리</a>
			</div>
		</div>
	</div>
</div>	

<div class="row my-5">
	<div class="col">
		<h2 class="mb-5">사용자 페이지</h2>
		<div class="row">
			<div class="col">
				<a href="${pageContext.request.contextPath}/member/mypage/reservation"  class="p-3" style="border: 1px solid black; background-color:#EEE;">예약 내역 보기</a>
			</div>
				<div class="col">
				<a href="${pageContext.request.contextPath}/member/mypage/diaryList"  class="p-3" style="border: 1px solid black; background-color:#EEE;">다이어리 보기</a>
			</div>
				<div class="col">
				<a href="${pageContext.request.contextPath}/member/mypage/boards"  class="p-3" style="border: 1px solid black; background-color:#EEE;">작성 게시글 보기</a>
			</div>
		</div>
	</div>
</div>