<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%-- page 지시자를 사용해 이 페이지가 에러를 처리하는 페이지임을 지정한다. --%>
<%@ page isErrorPage="true" %>

<%-- 현재 페이지가 정상적으로 처리되었다는 응답 상태 코드 설정 --%>
<% response.setStatus(200); %>
<div class="row my-5" id="global-content">
	<div class="col">    
		<div id="errorpage" class="my-5">
			에러페이지
			<map name="errorpage">
				<area shape="rect" coords="336, 136, 436, 159" 
					href="index.mvc" alt="홈으로 이동" />
			</map>
		</div>
	</div>
</div>