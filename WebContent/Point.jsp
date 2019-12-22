<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String remainPoint = (String) request.getAttribute("Point");
	String UserName = (String) request.getAttribute("UserName");
	String Id = (String) request.getAttribute("Id");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Point put page</title>
</head>
<body>
<%=UserName%> have <%=remainPoint%> points.
<br />
How many points do you give?
	<form action="<%=request.getContextPath()%>/PointResult"
		method="post">
		<input type="hidden" name="UserName" value="<%=UserName%>" />
		<input type="hidden" name="Id" value="<%=Id%>" />
		<input type="hidden" name="remainPoint" value="<%=remainPoint%>" />
		To：<input type="text" name= "txtTo"/>
		<br />
		point：<input type="text" name="txtPoint">
		<input type="submit" name= "btnPutPoint" value="put"/>
	</form>
</body>
</html>