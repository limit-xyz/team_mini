<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- content -->
<div class="row my-5" id="global-content">
	<div class="col">
	
		<div style="visibility: hidden;">
			<p id="pageNum">${pageNum}</p>
			<p id="searchDictionaryTypePara">${searchDictionaryType}</p>
			<p id="searchDictionaryKeywordPara">${searchDictionaryKeyword}</p>
		</div>
	
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">펫과사전 상세보기</h2>
			</div>
		</div>

		<!-- 상세보기 영역  --> 
		<div class="row my-3">
			<div class="col">
				<table class="table table-bordered" >
					<tbody>
						<tr>
							<td colspan="12">
							  <div style="width: 100%; max-width: 500px; aspect-ratio: 1 / 1;" class="mx-auto">
								<img src="${pageContext.request.contextPath}/dictionary/filePath.ajax?fileName=${dog.image}" 
							       class="img-fluid rounded d-block mx-auto"
							       style="width: 100%; height: 100%; object-fit: cover;">
							  </div>
							</td>		
						</tr>
						
						<tr>
							<th class="col-2">종류</th>
							<td class="col-4">개</td>	
							
							<th class="col-2">크기 분류</th>
							<td class="col-4">${ dog.dogSize }</td>	
						</tr>
									
						<tr>
							<th>이름</th>
							<td>${ dog.nameKor }</td>	
							
							<th>영문명</th>
							<td>${ dog.nameEng }</td>
						</tr>
						
						<tr>	
							<th>출신지</th>
							<td>${ dog.origin }</td>
							
							<th>설명</th>
							<td>${ dog.history }</td>	
						</tr>
																		
						<tr>	
							<th>키</th>
							<td>${ dog.height }</td>
							
							<th>체중</th>
							<td>${ dog.weight }</td>	
						</tr>
							
						<tr>	
							<th>털</th>
							<td>${ dog.coat }</td>
							
							<th>색상</th>
							<td>${ dog.color }</td>	
						</tr>
													
						<tr>	
							<th>수명</th>
							<td>${ dog.lifespan }</td>
							
							<th>흔히 발병하는 건강 이슈</th>
							<td>${ dog.healthIssues }</td>	
						</tr>
						
						<tr>	
							<th>지능</th>
							<td>${ dog.intelligence }</td>
							
							<th>훈련 난이도</th>
							<td>${ dog.trainingDifficulty }</td>	
						</tr>
						
						
						<tr>	
							<th>추천 환경</th>
							<td>${ dog.environment }</td>
							
							<th>운동량</th>
							<td>${ dog.exercise }</td>	
						</tr>
						
						<tr>	
							<th>짖음 정도</th>
							<td>${ dog.barkingLevel }</td>
							
							<th>미용</th>
							<td>${ dog.grooming }</td>	
						</tr>
						
						<tr>	
							<th>성격</th>
							<td>${ dog.personality }</td>
							
							<th>초보자 추천 여부</th>
							<td>${ dog.beginnerFriendly }</td>	
						</tr>

					</tbody>
				</table>
			</div>
		</div>


		<!-- 상세보기 버튼 영역 -->
		<div class="row my-3">
			<div class="col text-center">
				<c:if test="${isExpert}">				
					<input class="btn btn-warning" type="button" id="dictionaryUpdate" value="수정하기"
						data-animal-id="${dog.id}" data-animal-type="dog" />
					<input class="btn btn-danger"  type="button" id="dictionaryDelete" value="삭제하기"
						data-animal-id="${dog.id}" data-animal-type="dog"/>				
				</c:if>
				
				<input class="btn btn-primary" type="button" value="목록보기" 
				onclick="location.href='${pageContext.request.contextPath}/dictionary/dictionaryList?pageNum=${pageNum}&searchDictionaryType=${searchDictionaryType}&searchDictionaryKeyword=${searchDictionaryKeyword}'"/>
			</div>		
		</div>
	</div>	
</div>
<script src="${pageContext.request.contextPath}/js/dictionary.js"></script>