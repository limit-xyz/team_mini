<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dictionaryList.css">

<div class="container mt-4 mb-5">
	
	<div style="visibility: hidden; height:0; overflow:hidden;">
		<p id="pageNum">${pageNum}</p>
		<p id="searchDictionaryTypePara">${searchDictionaryType}</p>
		<p id="searchDictionaryKeyword">${searchDictionaryKeyword}</p>
	</div>

	<div class="row text-center my-4">
		<div class="col">
			<h2 class="page-main-title">펫과사전</h2>
            <hr class="short-hr">
		</div>
	</div>  
	
	<c:if test="${isExpert}">
		<div class="row my-3 justify-content-end">
			<div class="col-auto">
				<form action="${pageContext.request.contextPath}/dictionary/dictionaryWriteForm" method="get" class="d-flex align-items-center gap-2">
					<input type="hidden" name="pageNum" value="${pageNum}">
					<input type="hidden" name="searchDictionaryType" value="${searchDictionaryType}">
					<input type="hidden" name="searchDictionaryKeyword" value="${searchDictionaryKeyword}">
					<select name="type" class="form-select form-select-sm" style="min-width: 100px;">
						<option value="dog" selected>강아지</option>
						<option value="cat">고양이</option>
					</select>
					<button type="submit" class="btn btn-custom-theme btn-sm">
                        <i class="bi bi-pencil-square me-1"></i>글쓰기
                    </button>
				</form>
			</div>
		</div>
	</c:if>
	
 
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4 my-3">
        <c:if test="${not empty dictionaryList}">
            <c:forEach var="dictionary" items="${dictionaryList}" varStatus="status">
                <div class="col">
                    <div class="card h-100 dictionaryDetail" 
                         style="cursor: pointer;"
                         data-animal-type="${dictionary.type}"  
                         data-animal-id="${dictionary.id}">

                        <div class="pet-card-image-placeholder d-flex align-items-center justify-content-center" style="min-height: 180px; background-color: #f8f9fa; border-bottom: 1px solid #dee2e6;">
                            <img src="filePath.ajax?fileName=${dictionary.image}" 
							         class="img-fluid rounded d-block mx-auto"
							         style="width: 100%; height: 300px; object-fit: cover;">
                        </div>

                        <div class="card-body p-3 d-flex flex-column">
                            <div>
                                <h5 class="card-title pet-card-title">${dictionary.nameKor}</h5>
                                <h6 class="card-subtitle mb-2 text-muted pet-card-subtitle">${dictionary.nameEng}</h6>
                            </div>
                            <div class="pet-card-info mt-2">
                                <p class="mb-1"><small><strong>종류:</strong> ${dictionary.type}</small></p>
                                <p class="mb-1"><small><strong>출신지:</strong> ${dictionary.origin}</small></p>
                                <p class="mb-1"><small><strong>평균 신장:</strong> ${dictionary.height}</small></p>
                                <p class="mb-0"><small><strong>평균 체중:</strong> ${dictionary.weight}</small></p>
                            </div>
                            <div class="text-end pt-2 mt-auto"> 
                                 <span class="badge pet-card-detail-badge"><small>자세히 보기 <i class="bi bi-arrow-right-short"></i></small></span>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <c:if test="${empty dictionaryList}">
        <div class="row my-5">
            <div class="col text-center py-5">
                <c:if test="${searchDictionaryOption == '1'}">
                    <p class="lead text-muted">"${searchDictionaryKeyword}"에 대한 검색 결과가 존재하지 않습니다.</p>
                </c:if>
                <c:if test="${not searchDictionaryOption == '1'}">
                    <p class="lead text-muted">등록된 펫 정보가 없습니다.</p>
                </c:if>
            </div>
        </div>
    </c:if>
	
    <div class="row mt-4">
        <div class="col">
            <nav aria-label="Page navigation">
              <ul class="pagination justify-content-center" >
                <c:if test="${ startPage > PAGE_GROUP }">
                    <li class="page-item">
                      <a class="page-link"
                      href="dictionaryList?pageNum=${ startPage - PAGE_GROUP }&searchDictionaryType=${searchDictionaryType}&searchDictionaryKeyword=${searchDictionaryKeyword}">Prev</a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                    <c:if test="${i == pageNum }">
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">${i}</span>
                        </li>
                    </c:if>
                    <c:if test="${i != pageNum }">
                        <li class="page-item">
                            <a class="page-link"
                            href="dictionaryList?pageNum=${i}&searchDictionaryType=${searchDictionaryType}&searchDictionaryKeyword=${searchDictionaryKeyword}">${i}</a>
                        </li>
                    </c:if>					    
                </c:forEach>							    
                <c:if test="${ endPage < pageCount }">
                    <li class="page-item">
                      <a class="page-link"
                      href="dictionaryList?pageNum=${startPage + PAGE_GROUP}&searchDictionaryType=${searchDictionaryType}&searchDictionaryKeyword=${searchDictionaryKeyword}">Next</a>
                    </li>
                </c:if>
              </ul>
            </nav>
        </div>
    </div>

    <div class="row my-4 justify-content-center">
        <div class="col-md-8 col-lg-7">
            <form name="searchDictionaryForm" id="searchDictionaryForm"  action="dictionaryList" method="get" 
                class="row g-2 align-items-center">                      
                <div class="col">
                    <select name="searchDictionaryType" class="form-select">
                        <option value="name_ko" ${searchDictionaryType == 'name_ko' ? "selected" : ""}>이름(한글)</option>
                        <option value="name_en" ${searchDictionaryType == 'name_en' ? "selected" : ""}>이름(영문)</option>
                        <option value="type" ${searchDictionaryType == 'type' ? "selected" : ""}>종류</option>
                    </select>
                </div>
                <div class="col-6 col-sm">
                    <input type="text" name="searchDictionaryKeyword" class="form-control" id="searchDictionaryKeyword" value="${searchDictionaryKeyword}" placeholder="검색어 입력"/>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-custom-theme"><i class="bi bi-search"></i> 검 색</button>
                </div>
            </form>	
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/dictionary.js"></script>



