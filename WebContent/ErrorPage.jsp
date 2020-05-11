<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String errorItem = (String) request.getAttribute("errorItem");
	String errorMessage = (String) request.getAttribute("errorMessage");
	String InsUpdKbn = (String) request.getAttribute("InsUpdKbn");
	String userId = (String) request.getAttribute("userId");
	String Id = (String) request.getAttribute("Id");
%>
<html>
<head>
<meta charset="Shift_JIS">
<title>Error Page</title>
</head>
<body>
<%=errorMessage %>
<br>
<%if (InsUpdKbn.equals("Insert")){%>
<form action="<%=request.getContextPath()%>/M_UserInsert" method="post">
<input type="hidden" name="userId" value="<%=userId%>">
<input type="hidden" name="Id" value="<%=Id%>" />
<input type="submit" name="InsUpdKbn" value="Back">
</form>
<%} else {%>
<form action="<%=request.getContextPath()%>/M_UserUpdate" method="post">
<input type="hidden" name="userId" value="<%=userId%>">
<input type="hidden" name="Id" value="<%=Id%>" />
<input type="submit" name="InsUpdKbn" value="Back">
</form>
<%} %>
</body>
</html>