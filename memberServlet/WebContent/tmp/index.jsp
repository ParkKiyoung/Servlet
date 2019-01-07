<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String pagefile = request.getParameter("page");
	if (pagefile == null) {
		pagefile = "newitem";
	}
%>
</head>
<body>
	<table border = 1>
		<tr>
			<td height="43" colspan=3 align=left><jsp:include page="top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td width="15%" align=right valign = top><jsp:include page="left.jsp"></jsp:include>
			</td>
			<td colspan = 2 align=center><jsp:include page='<%=pagefile+".jsp" %>'></jsp:include>
			</td>
		</tr>
	</table>

</body>
</html>