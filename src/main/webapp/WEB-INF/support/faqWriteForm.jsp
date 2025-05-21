<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- content -->			
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/faq.css" />

<div class="container" id="faq-write-page-container"> 
    
    <div class="row mb-4">
        <div class="col text-center">
            <h2 id="title">FAQ 글쓰기</h2>
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-10 col-lg-10"> 
            <div class="content-card"> 
                <div class="card-body p-4 p-md-5"> 
                    <form action="${pageContext.request.contextPath}/support/faqWriteResult" name="writeForm" id="writeForm" method="post">
                        
                        <div class="mb-4">
                            <label for="title" class="form-label fw-medium">제 목</label>
                            <input type="text" class="form-control form-page-title" name="title" placeholder="제목을 입력해주세요." required>
                        </div>
                          
                        <div class="mb-4">
                            <label for="content" class="form-label fw-medium">내 용</label>
                            <textarea class="form-control" id="content" name="content" rows="12" placeholder="내용을 입력해주세요." required></textarea>
                        </div>

                        <div class="mt-5 d-flex justify-content-center gap-2">
                            <button type="submit" class="btn btn-custom-theme px-4">
                                <i class="bi bi-check-lg me-1"></i>등록하기
                            </button>
                            <button type="button" class="btn btn-outline-secondary px-4"
                                onclick="location.href='${pageContext.request.contextPath}/support/faq'">
                                <i class="bi bi-list-ul me-1"></i>목록보기
                            </button>
                        </div>
                    </form>        
                </div>
            </div>
        </div>
    </div>
</div>