<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
if(${log}==3){
	alert("비밀번호가 다릅니다.");
}else{
	if(confirm("회원이 아닙니다. 회원가입 하시겠습니까?")){
		location.href="join.jsp";
	};
}
</script>

</head>
<body>
	<form action="login.do" method="post">
		<table>
			<tr>
				<td colspan=2>로그인</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" id=id name=id></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type=password id=passwd name=passwd></td>
			</tr>
			<tr>
				
				<td><input type=submit value="로그인"></td>
				<td><input type=button value="회원가입" onclick="location.href='join.jsp'"></td>
			</tr>

		</table>
	</form>

</body>
</html>