<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/faq.css" />
    
    
 <div class="container">
    <div id="title">문의 게시판</div>
      <div class="row">
        <div class="col">
            <div id="tab-container">
              <div class="pill-button-group">
                <button class="pill-button"><a href="${pageContext.request.contextPath}/support/faq">FAQ</a></button>
                <button class="pill-button active"><a href="${pageContext.request.contextPath}/support/qnaList">문의 게시판</a></button>
                <button class="pill-button"><a href="${pageContext.request.contextPath}/support/reportList">신고 게시판</a></button>
              </div>
            </div>
        </div>
      </div>
      
    <div class="qna-content-area">
        <div class="row">
            <div class="col">
                <div class="table-responsive">
                    <table class="table table-hover text-center">
                        <thead>
                            <tr>
                                <th scope="col" style="width: 10%;">번호</th>
                                <th scope="col" style="width: 15%;">답변상황</th>
                                <th scope="col" style="width: 40%;" class="text-start">제목</th>
                                <th scope="col" style="width: 15%;">작성자</th>
                                <th scope="col" style="width: 20%;">작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>5</td>
                                <td><span class="badge rounded-pill status-badge status-answered">답변완료</span></td>
                                <td class="text-start"><a href="#" class="qna-title-link">개인 정보 변경은 어떻게 하나요?</a></td>
                                <td>김민준</td>
                                <td>2025-05-20</td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td><span class="badge rounded-pill status-badge status-waiting">답변대기</span></td>
                                <td class="text-start"><a href="#" class="qna-title-link">이벤트 참여 관련 문의입니다. 제 아이디가 누락된 것 같아요.</a></td>
                                <td>이수현</td>
                                <td>2025-05-19</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td><span class="badge rounded-pill status-badge status-answered">답변완료</span></td>
                                <td class="text-start"><a href="#" class="qna-title-link">모바일 앱에서 로그인이 안됩니다.</a></td>
                                <td>박서윤</td>
                                <td>2025-05-18</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td><span class="badge rounded-pill status-badge status-answered">답변완료</span></td>
                                <td class="text-start"><a href="#" class="qna-title-link">결제 오류가 발생했는데 확인 부탁드립니다.</a></td>
                                <td>최지우</td>
                                <td>2025-05-17</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td><span class="badge rounded-pill status-badge status-waiting">답변대기</span></td>
                                <td class="text-start"><a href="#" class="qna-title-link">사이트 이용 가이드라인이 있나요?</a></td>
                                <td>정하은</td>
                                <td>2025-05-16</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
       <div class="row mt-2">
      	<div class="col text-end">
						<a href="${pageContext.request.contextPath}/support/faqWriteForm" class="btn btn-outline-success">문의하기</a>
					</div>
      </div>
          
        <div class="row mt-4"> 
            <div class="col">
                <nav aria-label="Page navigation example">
                  <ul class="pagination justify-content-center my-3"> 
                    <li class="page-item disabled"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                  </ul>
                </nav>
            </div>
        </div>
        </div> 
        </div> 
        
        