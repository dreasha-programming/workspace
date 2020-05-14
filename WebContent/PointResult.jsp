<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String calcPoint = (String) request.getAttribute("calcPoint");
	String UserName = (String) request.getAttribute("UserName");
	String Id = (String) request.getAttribute("Id");
	String UketoriFlg = (String) request.getAttribute("UketoriFlg");
	String txtPutPoint = (String) request.getAttribute("txtPutPoint");
	String toUserId = (String) request.getAttribute("toUserId");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
<%=UserName%> have <%=calcPoint%> points.
<% if (UketoriFlg.equals("0")){ %>
<form action="<%=request.getContextPath()%>/SendMail" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="txtPutPoint" value=<%=txtPutPoint %> />
	<input type="hidden" name="toUserId" value=<%=toUserId %> />
	<input type="submit" value="Send Mail"></input>
</form>
<%} %>
<form action="<%=request.getContextPath()%>/MainMenu" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>