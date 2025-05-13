  <!-- 헤더  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <div class="headerWrap bg-success p-2 text-dark bg-opacity-25">
      <div class="container ">
        <div class="row text-end">
          <div class="col" style="font-size:14px;">
            <a href="loginForm.member" class="" >로그인</a>
            <a href="memberJoinForm.member" class="ms-3">회원가입</a>
            <!-- 첫페이지 qna -->
            <a href="qna.qna" class="ms-3">고객센터</a>
          </div>
        </div>
        <nav class="navbar navbar-expand-lg ">
          <div class="container-fluid">
            <a class="navbar-brand h-40" href="#"><img src="images/logo.png" alt=""></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
              <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll fs-6" style="--bs-scroll-height: 100px;">
                <li class="nav-item">
                  <a class="nav-link active" href="#">미용 예약</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">병원 예약</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">펫과사전</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">상품 정보</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">커뮤니티</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">입양/분양</a>
                </li>
              </ul>
              <form class="d-flex " role="search">
                <input class="form-control me-2 rounded-pill" type="search" placeholder="검색어를 입력해주세요." aria-label="Search">
                <button class="btn btn-outline-success" type="submit"><i class="bi bi-search"></i></button>
              </form>
            </div>
          </div>
        </nav>
        </div>
      </div>