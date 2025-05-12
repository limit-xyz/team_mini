<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content -->
<div class="row">
	<div class="col">
		<table class="table table-hover my-3">
		
			<thead>
				<tr class="table-dark">
					<th>NO</th>
					<th>이름</th>
				</tr>		
			</thead>
			
			<tbody class="text-secondary">
				<c:if test="${ not empty memberList }">
					<c:forEach var="member" items="${memberList}" varStatus="status">
					<tr>
						<td>${ member.no }</td>
						<td>${ member.name }</td>
					</tr>
					</c:forEach>
				</c:if>							
	
				<c:if test="${ empty memberList }">
					<tr>
						<td colspan="2" class="text-center">멤버가 존재하지 않음</td>
					</tr>
				</c:if>
				
			</tbody>
		</table>
	</div>
</div>
<!-- Content End -->