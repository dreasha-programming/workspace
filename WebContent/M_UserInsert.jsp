<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String latestId = (String) request.getAttribute("latestId");
	String Id = (String) request.getAttribute("Id");
%>
<html>
<head>
<meta charset="Shift_JIS">
<title>M_User Insert</title>
<style>
table,th,td {
  width: 200px;
  border: 1px;
}
</style>
</head>
<body>
<form action="<%=request.getContextPath()%>/M_UserRegister" method="post">
	<table class="table_M_User">
	  <tr><th></th><th>Maintenance Item</th></tr>
	  <tr><td>ID</td><td><%=latestId%></td></tr>
	  <tr><td>Name</td><td><input type="text" name="userName" value=""></td></tr>
	  <tr><td>Password</td><td><input type="text" name="password" value=""></td></tr>
	  <tr><td>MailAddress</td><td><input type="text" name="mailAddress" value=""></td></tr>
	  <tr><td>Point</td><td><input type="text" name="point" value=""></td></tr>
	  <tr><td>Kengen</td><td><input type="text" name="kengen" value=""></td></tr>
	</table>
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="userId" value="<%=latestId%>" />
	<input type="submit" name="InsUpdKbn" value="Insert">&nbsp;&nbsp;&nbsp;
</form>
<br>
<form action="<%=request.getContextPath()%>/MasterMaintenance" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>