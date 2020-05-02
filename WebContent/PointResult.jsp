<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String calcPoint = (String) request.getAttribute("calcPoint");
	String UserName = (String) request.getAttribute("UserName");
	String Id = (String) request.getAttribute("Id");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
<%=UserName%> have <%=calcPoint%> points.
<form action="<%=request.getContextPath()%>/MainMenu" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>