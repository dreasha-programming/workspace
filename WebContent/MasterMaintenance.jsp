<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<%
	String Id = request.getParameter("Id");
%>
<html>
<head>
<meta charset="Shift_JIS">
<title>Master Maintenance</title>
</head>
<body>
<form method="POST" action="<%=request.getContextPath()%>/M_UserInsert" id="link1">
<a href="about:blank" onclick="document.getElementById('link1').submit();return false;">M_User Insert</a>
</form>
<br>
<br>
<form method="POST" action="<%=request.getContextPath()%>/M_UserUpdate" id="link2">
<a href="about:blank" onclick="document.getElementById('link2').submit();return false;">M_User Update</a>
</form>
<br>
<br>
<form action="<%=request.getContextPath()%>/MainMenu" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>