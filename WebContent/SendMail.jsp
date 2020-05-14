<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<%
	String Id = (String) request.getAttribute("Id");
	String errorFlg = (String) request.getAttribute("errorFlg");
%>
<head>
<meta charset="Shift_JIS">
<title>Send Mail</title>
</head>
<body>
<% if (errorFlg.equals("0")){ %>
Your message has been sent.
<%} else {%>
An unexpected error has occurred.
<%} %>
<br>
<form action="<%=request.getContextPath()%>/MainMenu" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>