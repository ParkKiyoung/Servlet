<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form action="login" method="post">
		ID : <input type="text" name="userid" id="userid"><br>
		PWD : <input type="password" name="pwd" id="pwd"><br> 
		<input type="submit" value="로그인"> <input type="reset" value="취소">
	</form>
	<br>
	<br> ${msg}
</body>
</html>