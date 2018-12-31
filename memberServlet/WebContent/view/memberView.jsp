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
	<h1 align=center>${md.name }님의 회원정보</h1>
	<form action="operate.do" method="post">
		<!-- 업데이트는 post로  -->
		<table boder=1 align=center>
			<tr>
				<td>회원번호</td>
				<td><input type=text id=num name=num value="${md.num}"
					readonly></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type=text id=id name=id value="${md.id}" readonly></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type=password id=passwd name=passwd
					value="${md.passwd}"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type=text id=name name=name value="${md.name}"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type=text id=tel name=tel value="${md.tel}"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><input type=radio id=gender name = gender value="male" 
				<c:if test="${md.gender eq 'male'}">checked="checked"</c:if>>남자
					<input type=radio id=gender name = gender value="female"
					<c:if test="${md.gender eq 'female'}">checked="checked"</c:if>>여자
				</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input type=text id=age name=age value="${md.age}"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type=text id=email name=email value="${md.email}"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type=text id=addr name=addr value="${md.addr}"></td>
			</tr>
			<tr>
				<td>회원등급</td>
				<td><input type=radio id=admin name=admin value="1"
					<c:if test="${md.admin eq '1'}">checked="checked"</c:if>>일반회원
					<input type=radio id=admin name=admin value="2"
					<c:if test="${md.admin eq '2'}">checked="checked"</c:if>>관리자
				</td>
			</tr>
			<tr>
				<td colspan = 2 align = center><input type=submit value="수정"> <input type=button
					value="삭제" onclick="deleteBtn(${md.num})"></td>
			</tr>
		</table>

	</form>

</body>
</html>