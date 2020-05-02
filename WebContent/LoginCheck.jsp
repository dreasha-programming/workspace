<!DOCTYPE html>
<html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String errorNum = (String) request.getAttribute("errorNum");
	String errorMessage = "";
	if (errorNum.equals("1")){
		errorMessage="Id is wrong.(DataType Error)";
	} else if (errorNum.equals("2")){
		errorMessage="Id is wrong.(No id in database)";
	} else if (errorNum.equals("3")){
		errorMessage="Password is wrong.";
	}
%>
<head>
<meta charset="UTF-8">
<title>Login Check</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/index.jsp">
<%=errorMessage%>
<br>
	<input type="submit" value="Back"></input>
</form>
</body>
</html>