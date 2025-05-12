<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>테스트</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.12.1/font/bootstrap-icons.min.css">
<style>
</style>
<script src="js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="pages/header.jsp"%>
		<jsp:include page="${ param.body }" />
		<%@ include file="pages/footer.jsp"%>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>