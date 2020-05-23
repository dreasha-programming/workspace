<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<%
	String Id = (String) request.getAttribute("Id");
	String filePath = (String) request.getAttribute("filePath");
%>

<head>
<meta charset="Shift_JIS">
<title>AccessLog Check</title>
</head>
<body>
<img src="<%=filePath%>">
<br>
<form action="<%=request.getContextPath()%>/MasterMaintenance" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>