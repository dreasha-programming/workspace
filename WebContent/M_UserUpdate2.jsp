<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String userId = (String) request.getAttribute("userId");
	String userName = (String) request.getAttribute("userName");
	String password = (String) request.getAttribute("password");
	String mailAddress = (String) request.getAttribute("mailAddress");
	String point = (String) request.getAttribute("point");
	String kengen = (String) request.getAttribute("kengen");
%>
<html>
<head>
<meta charset="Shift_JIS">
<title>M_User Update</title>
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
	  <tr><td>ID</td><td><%=userId%></td></tr>
	  <tr><td>Name</td><td><input type="text" name="userName" value="<%=userName%>"></td></tr>
	  <tr><td>Password</td><td><input type="text" name="password" value="<%=password%>"></td></tr>
	  <tr><td>MailAddress</td><td><input type="text" name="mailAddress" value="<%=mailAddress%>"></td></tr>
	  <tr><td>Point</td><td><input type="text" name="point" value="<%=point%>"></td></tr>
	  <tr><td>Kengen</td><td><input type="text" name="point" value="<%=kengen%>"></td></tr>
	</table>
	<input type="hidden" name="userId" value="<%=userId%>">
	<input type="submit" name="InsUpdKbn" value="Update">&nbsp;&nbsp;&nbsp;
	<input type="submit" name="InsUpdKbn" value="Delete">&nbsp;&nbsp;&nbsp;
</form>
<button onclick="location.href='./MasterMaintenance.jsp'">Back</button>
</body>
</html>