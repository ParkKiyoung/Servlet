<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String uploadPath = request.getRealPath("/upload");
	//컴퓨터 내에서의 실제 물리적 주소안의 경로를 가져옴

	int size = 10 * 1024 * 1024;//파일 크기 정의 최대 10mb
	String name = "";
	String subject = "";
	String filename1 = "";
	String filename2 = "";
	String origfilename1 = "";
	String origfilename2 = "";

	try {
		MultipartRequest multi = new MultipartRequest(request, 
														uploadPath, 
														size, 
														"UTF-8",
														new DefaultFileRenamePolicy());
		//파일 업로드에서는 리퀘스트를 바로 쓸수 없다. multipartRequest가 항사 필요하고 
		//인자값으로 리퀘스트가 들어간다. 
		//인자값은 (리퀘스트객체, 그다음 컴퓨터에 저장할실제경로, 파일 크기, 인코딩 타입, 중복시 이름재정의 옵션)

		name = multi.getParameter("name");
		//그래서 multi를 통해 이름과 제목을 가져온다. 
		subject = multi.getParameter("subject");

		Enumeration files = multi.getFileNames();
		//파일이 복수 이기 때문에 사용하는 객체 이다. 
		//for문이라고 생각해도 좋다.
		String file1 = (String) files.nextElement();
		//첫번째 파일을 가져오고
		filename1 = multi.getFilesystemName(file1);
		//거기서 저장될 파일명(중복시 숫자가 더 붙음)
		origfilename1 = multi.getOriginalFileName(file1);
		//파일의 실제이름을 가져옴

		String file2 = (String) files.nextElement();
		filename2 = multi.getFilesystemName(file2);
		origfilename2 = multi.getOriginalFileName(file2);
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<html>
<body>
	<form name="filecheck" action="fileCheck.jsp" method="post">
		<input type="hidden" name=name value="<%=name%>"> <input
			type="hidden" name=subject value="<%=subject%>"> <input
			type="hidden" name=filename1 value="<%=filename1%>"> <input
			type="hidden" name=filename2 value="<%=filename2%>"> <input
			type="hidden" name=origfilename1 value="<%=origfilename1%>"> <input
			type="hidden" name=origfilename2 value="<%=origfilename2%>">

	</form>
	<a href="#" onclick="javascript : filecheck.submit()">업로드 확인 및 다운로드 페이지로 이동</a>
</body>
</html>
