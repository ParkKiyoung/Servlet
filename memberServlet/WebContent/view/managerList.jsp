<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align=center>관리자 ${sessionScope.id}님의 페이지</h1>
	<table align=center border=1>
		<tr>
		<td colspan=10 align =right>
		<input type = button value="로그아웃" onclick="location.href='logout.do'">
		</td>
		</tr>
		<tr>
			<td>회원번호</td>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>성별</td>
			<td>나이</td>
			<td>전화번호</td>
			<td>이메일</td>
			<td>주소</td>
			<td>회원등급</td>
		</tr>
		<c:forEach items="${arr }" var="i">
			<tr>
				<td>${i.num }</td>
				<td><a href = "ManagerView.do?num=${i.num }">${i.id }</a></td>
				<td>${i.passwd }</td>
				<td>${i.name }</td>
				<td>${i.gender }</td>
				<td>${i.age }</td>
				<td>${i.tel }</td>
				<td>${i.email }</td>
				<td>${i.addr }</td>
				<td><c:if test="${i.admin eq '1'}">일반회원</c:if>
				<c:if test="${i.admin eq '2'}">관리자</c:if></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>