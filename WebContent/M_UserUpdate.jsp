<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<title>M_User Update</title>
<%
	String Id = (String) request.getAttribute("Id");
 %>
</head>
<body>
	<form action="<%=request.getContextPath()%>/M_UserUpdate2"
		method="post">
		<p>ID:<input type="text" name="userId">
		<input type="hidden" name="Id" value="<%=Id%>" />
		<input type="submit" name= "btnSearch" value="Search"/>
		</p>
	</form>
<br>
<form action="<%=request.getContextPath()%>/MasterMaintenance" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>