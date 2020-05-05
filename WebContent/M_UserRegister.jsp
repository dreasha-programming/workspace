<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String userId = (String) request.getAttribute("userId");
	String userName = (String) request.getAttribute("userName");
	String password = (String) request.getAttribute("password");
	String mailAddress = (String) request.getAttribute("mailAddress");
	String point = (String) request.getAttribute("point");
	String InsUpdKbn = (String) request.getAttribute("InsUpdKbn");
%>
<head>
<meta charset="Shift_JIS">
<title>M_User Register</title>
</head>
<body>
<p><%=InsUpdKbn %> process complete!</p>
<br>
	<table class="table_M_User">
	  <tr><th></th><th>Maintenance Item</th></tr>
	  <tr><td>ID</td><td><%=userId%></td></tr>
	  <tr><td>Name</td><td><%=userName%></td></tr>
	  <tr><td>Password</td><td><%=password%></td></tr>
	  <tr><td>MailAddress</td><td><%=mailAddress%></td></tr>
	  <tr><td>Point</td><td><%=point%></td></tr>
	</table>
	<button onclick="location.href='./MasterMaintenance.jsp'">Back</button>
</body>
</html>