<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${count gt 0 }">
	<div align=center>총게시물 수 : ${count}</div>
	<table border solid>
		<tr>
			<th>no</th>
			<th>이 름</th>
			<th>내 용</th>
			<th>평 가</th>
			<th>날 짜</th>
			<th>IP 주소</th>
		</tr>
		<c:forEach items="${lists}" var="i">
			<tr>
				<td>${i.num}</td>
				<td><a href="javascript:fview(${i.num})">${i.name}</a></td>
				<!-- 자바스크립트라고 알려주기 위함으로 앞에 javascript라고 씀 -->
				<td size=30>${i.content}</td>
				<td>${i.grade}</td>
				<td>${i.created}</td>
				<td>${i.ipaddr}</td>
			</tr>
		</c:forEach>
	</table>
	<div align=center>
		<!-- 이전 -->
		<c:if test="${startPage>blockPage}">
			<a href="javascript:getSearch(${startPage-blockPage},'${field}','${word}')">[이전]</a>
		</c:if>
		<!-- 페이지 출력 -->
		<c:forEach begin="${startPage}" end="${endPage}" var="i">
			<c:if test="${i == currentPage}">[${i}]</c:if>
			<c:if test="${i!=currentPage }">
				<a href="javascript:getSearch(${i},'${field}','${word }')">[${i}]</a>
			</c:if>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${endPage<totPage}">
			<a href="javascript:getSearch(${endPage+1},'${field}','${word }')">[다음]</a>
		</c:if>
	</div>
</c:if>