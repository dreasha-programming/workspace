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
	String kengen = (String) request.getAttribute("kengen");
	String InsUpdKbn = (String) request.getAttribute("InsUpdKbn");
	String Id = (String) request.getAttribute("Id");
%>
<head>
<meta charset="Shift_JIS">
<title>M_User Register</title>
<style>
table th,td {
  width: 100px;
  border: 1px;
}
</style>
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
	  <tr><td>Kengen</td><td><%=kengen%></td></tr>
	</table>
<br>
<form action="<%=request.getContextPath()%>/MasterMaintenance" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>