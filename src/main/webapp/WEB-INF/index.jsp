<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>JSP MVC 게시판</title>	
	<link href="bootstrap/bootstrap.min.css" rel="stylesheet" >
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.12.1/font/bootstrap-icons.min.css">	
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/global.css" />
	<link rel="stylesheet" type="text/css" href="css/member.css" />		
    <style>      	
    </style>
	<script src="js/jquery-3.7.1.min.js"></script>
	<script src="js/formCheck.js"></script>
	<script src="js/member.js"></script>
</head>
<body>
<%@ include file="pages/header.jsp" %>
	<div class="container">
	<%--
	"/WEB-INF/index.jsp?body=" + "board/boardList" + ".jsp"  
	--%>
		
		<%--
			<jsp:include page="board/boardList.jsp" />  
			<jsp:include page="error/errorpage.jsp" />
			request.getParameter("body");
		--%>
		<jsp:include page="${ param.body }" />
		
	</div>
	<%@ include file="pages/footer.jsp" %>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>		
</body>
</html>
