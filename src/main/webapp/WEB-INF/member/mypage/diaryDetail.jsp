<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- content -->
<div class="row my-5" id="global-content">
	<div class="col">
	
		<div style="visibility: hidden;">
			<p id="pageNum">${pageNum}</p>
		</div>
	
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">다이어리 상세보기</h2>
			</div>
		</div>
		
		<!-- 상세보기 영역  --> 
		<div class="row my-3">
			<div class="col">
				<table class="table table-bordered" >
					<tbody>					
						<tr>
							<th class="col-2">제 목</th>
							<td colspan="3">${ diary.title }</td>		
						</tr>
						
						<tr>
							<th class="col-2">작성자</th>
							<td class="col-4">${ diary.memberId }</td>
							
							<th class="col-2">작성일</th>
							<td>
								<fmt:formatDate value="${ diary.regDate }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>		
						</tr>
						
						<tr>
							<th class="col-2">반려동물명</th>
							<td colspan="3">${ diary.petName }</td>
						</tr>

						<tr>
							<th class="col-2">사  진</th>
							<td colspan="3">
							<c:if test="${ empty diary.photo }">
								없음
							</c:if>
							<c:if test="${ not empty diary.photo }">
								<div style="width: 100%; max-width: 300px; aspect-ratio: 1 / 1;">
									<img src="filePath.ajax?fileName=${diary.photo}"
										class="img-fluid rounded" style="width: 100%; height: 100%; object-fit: cover;">
								</div>
							</c:if>
							</td>		
						</tr>
						<tr>		
							<td colspan="6">
								<pre>${ diary.content }</pre>
							</td>
						</tr>	
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- 상세보기 버튼 영역 -->
		<div class="row my-3">
			<div class="col text-center">				
				<input class="btn btn-warning" type="button" id="diaryUpdate" value="수정하기" data-diary-no="${diary.no}" />
				<input class="btn btn-danger"  type="button" id="diaryDelete" value="삭제하기" data-diary-no="${diary.no}" />				
				<input class="btn btn-primary" type="button" value="목록보기"  onclick="location.href='diaryList?pageNum=${pageNum}'"/>
			</div>		
		</div>
	</div>	
</div>
<script src="${pageContext.request.contextPath}/js/diary.js"></script>