<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#send").click(function(){
		var name = $("#name").val();
		var content = $("#content").val();
		var grade = $("input:radio[name=grade]:checked").val();
		var postString = "name="+name+"&content="+content+"&grade="+grade;
		$.ajax({
			type :"post",
			url :"create",
			data : postString,
			success : function(data){
			$("#result").html(data)
			},
			beforeSend:showRequest
		})
	});//send
	
	$("#btnSearch").click(function(){
		getSearch(1,$("#field").val(),$("#word").val());
	});
	getData(1);
	
});
function getSearch(pageNum,field,word){//검색부분
	$("#result").load("search",{"pageNum":pageNum,"field":field,"word":word},function(data){
		$("#result").html(data);
	})
};
function getData(pageNum){//일반 리스트 출력
	$("#result").load("list",{"pageNum":pageNum},function(data){
		$("#result").html(data);
	});
}
function showRequest(){
	if(frm.name.value==""){
		alert("이름 입력");
		$("#name").focus();
		return false
	}
	if(frm.content.value==""){
		alert("내용 입력");
		$("#content").focus();
		return false
	}
	if($("input:radio[name=grade]:checked").length==0){
		//선택하면 length는 1이됨
		alert("점수 선택");
		return false
	}
	return true;
}
//글자수 체크하는 함수
function textCount(obj,target){
	var len = obj.value.length;//입력글자수
	if(obj.size<len){
		obj.value="";
		alert("글자수 초과");
		return;
	}
	$("#"+target).text(len);//target영역에 글자수 출력
}
//상세보기
function fview(num){
	$.get("view",{"num":num},function(data){
		$("#view").html(data);
	});
	
}
function deleteBtn(num){
	if(confirm("정말 삭제하시겠습니까?")){
	location.href = "deleteAction?num="+num;
	};
}

</script>
</head>
<body>
<div align = "center">
<c:if test="${sessionScope.mdto==null }">
<a href = "login.jsp">로그인</a>
</c:if>
<c:if test="${sessionScope.mdto!=null }">
${sessionScope.mdto.name }(${sessionScope.mdto.id})님 환영합니다.
<a href = "logout">로그아웃</a>
</c:if>
</div>
	<form id = frm>
		<table border =1 align = center>
			<tr>
			<td>글쓴이</td>
				<td><input type="text" id=name size=20 name=name onkeyup="textCount(this,'nameCount')" value = "${sessionScope.mdto.name}">
				20글자 이내(<span id="nameCount" style="color: red;">0</span>)</td>
			</tr>
			<tr>
			<td>내용</td>
				<td align=center><input type="text" size=70 id=content	name=content onkeyup="textCount(this,'contentCount')">
				 70글자 이내 
				 (<span id="contentCount" style="color: red;">0</span>)</td>
			</tr>
			<tr>
			<td align = center>
			평가			
			</td>
			<td>
			<input type ="radio" name ="grade" value ="excellent">아주잘함(excellent)
			<input type ="radio" name ="grade" value ="good">잘함(good)
			<input type ="radio" name ="grade" value ="normal">보통(normal)
			<input type ="radio" name ="grade" value ="fail">노럭(fail)
			</td>
			</tr>
			<tr>
			<td colspan = 2>
			<input type = button value = "입력" id = "send">
			</tr>
		</table>
	</form>
	<br><br><br>
	<!-- 방명록 -->
	<div id = "result" align = "center"></div>
	<br><br><br>
	<!-- 상세보기 -->
	<div id = "view" align = "center"></div>
	<!-- 검색부분 -->
	
	<div align = center>
	<form action="" name = "search" id = search>
	<select name = "field" id = "field">
	<option value = "name">글쓴이</option>
	<option value = "content">내용</option>
	</select>
	<input type = "text" name = "word" id = "word">
	<input type = "button" value= "찾기" id = "btnSearch">
	</form>
	</div>
	

</body>
</html>