<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
	<tr>
		<td>글번호</td>
		<td><input type =text id = num name =num value ="${gd.num}"></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type =text id = num name =num value ="${gd.name}"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><input type =text id = num name =num value ="${gd.content}"></td>
	</tr>
	<tr>
		<td>평가</td>
		<td><input type =text id = num name =num value ="${gd.grade}"></td>
	</tr>
	<tr>
		<td>날 짜</td>
		<td><input type =text id = num name =num value ="${gd.created}"></td>
	</tr>
	<tr>
		<td>IP 주소</td>
		<td><input type =text id = num name =num value ="${gd.ipaddr}"></td>
	</tr>
	<tr>
	<td colspan =2 align = center>
	<c:if test="${sessionScope.mdto.name==gd.name }">
	<input type = button value="수정" onclick ="updateBtn()">
	<input type = button value="삭제" onclick ="deleteBtn(${gd.num})">
	</c:if>
	</td>
	</tr>
</table>
