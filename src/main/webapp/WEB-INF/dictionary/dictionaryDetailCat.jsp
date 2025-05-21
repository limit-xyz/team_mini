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
							    <img src="filePath.ajax?fileName=${cat.image}" 
							         class="img-fluid rounded d-block mx-auto"
							         style="width: 100%; height: 100%; object-fit: cover;">
							  </div>
							</td>		
						</tr>
						
						<tr>
							<th class="col-2">종류</th>
							<td class="col-4">고양이</td>	
							
							<th class="col-2">분류</th>
							<td class="col-4">${ cat.breedType }</td>	
						</tr>
									
						<tr>
							<th>이름</th>
							<td>${ cat.nameKor }</td>	
							
							<th>영문명</th>
							<td>${ cat.nameEng }</td>
						</tr>
						
						<tr>	
							<th>출신지</th>
							<td>${ cat.origin }</td>
							
							<th>설명</th>
							<td>${ cat.history }</td>	
						</tr>
																		
						<tr>	
							<th>키</th>
							<td>${ cat.height }</td>
							
							<th>체중</th>
							<td>${ cat.weight }</td>	
						</tr>
							
						<tr>	
							<th>털</th>
							<td>${ cat.coat }</td>
							
							<th>색상</th>
							<td>${ cat.color }</td>	
						</tr>
													
						<tr>	
							<th>수명</th>
							<td>${ cat.lifespan }</td>
							
							<th>흔히 발병하는 건강 이슈</th>
							<td>${ cat.healthIssues }</td>	
						</tr>
						
						<tr>	
							<th>지능</th>
							<td>${ cat.intelligence }</td>
							
							<th>독립성</th>
							<td>${ cat.independence }</td>	
						</tr>
						
						<tr>	
							<th>성격</th>
							<td>${ cat.personality }</td>
							
							<th>애정도</th>
							<td>${ cat.affectionLevel }</td>	
						</tr>
						
						<tr>	
							<th>추천 환경</th>
							<td>${ cat.environment }</td>
							
							<th>활동량</th>
							<td>${ cat.activityLevel }</td>	
						</tr>
						
						<tr>	
							<th>울음소리 빈도/크기</th>
							<td>${ cat.vocality }</td>
							
							<th>미용</th>
							<td>${ cat.grooming }</td>	
						</tr>
						
						<tr>	
							<th>털빠짐 정도</th>
							<td>${ cat.sheddingLevel }</td>
							
							<th>알러지 저자극성 여부</th>
							<td>${ cat.hypoallergenic }</td>	
						</tr>
						
						<tr>	
							<th>아이들과의 친화력</th>
							<td>${ cat.compatibilityWithKids }</td>
							
							<th>초보자 추천 여부</th>
							<td>${ cat.beginnerFriendly }</td>	
						</tr>
						


					</tbody>
				</table>
			</div>
		</div>

		<!-- 상세보기 버튼 영역 -->
		<div class="row my-3">
			<div class="col text-center">				
				<input class="btn btn-warning" type="button" id="dictionaryUpdate" value="수정하기" data-dictionary-no="${dictionary.no}" />
				<input class="btn btn-danger"  type="button" id="dictionaryDelete" value="삭제하기" data-dictionary-no="${dictionary.no}" />				
				<input class="btn btn-primary" type="button" value="목록보기" 
				onclick="location.href='dictionaryList?pageNum=${pageNum}&searchDictionaryType=${searchDictionaryType}&searchDictionaryKeyword=${searchDictionaryKeyword}'"/>
			</div>		
		</div>
	</div>	
</div>
<script src="${pageContext.request.contextPath}/js/dictionary.js"></script>