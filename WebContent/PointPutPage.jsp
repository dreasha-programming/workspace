<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String remainPoint = (String) request.getAttribute("remainPoint");
	String UserName = (String) request.getAttribute("UserName");
	String Id = (String) request.getAttribute("Id");
	String[] userList = (String[]) request.getAttribute("userList");
%>
<html>
<head>
<meta charset="Shift_JIS">
<title>Point Put Page</title>
</head>
<body>
<%=UserName%> have <%=remainPoint%> points.
<br>
How many points do you put?
<br>
<br>
<form action="<%=request.getContextPath()%>/PointResult"
		method="post">
		<input type="hidden" name="UserName" value="<%=UserName%>" />
		<input type="hidden" name="Id" value="<%=Id%>" />
		<input type="hidden" name="remainPoint" value="<%=remainPoint%>" />
		<input type="hidden" name="UketoriFlg" value="0" />
		<p>To:</p>
		<input type="text" name="txtTo">
		<p>Purpose:</p>
		<input type="text" name="txtPurpose">
		<p>Points:</p>
		<input type="text" name="txtPutPoint">
		<input type="submit" name= "btnPutPoint" value="put"/>
	</form>
</body>
</html>