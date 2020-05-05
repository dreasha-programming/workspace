<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<title>M_User Update</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/M_UserUpdate2"
		method="post">
		<p>ID:<input type="text" name="userId">
		<input type="submit" name= "btnSearch" value="Search"/>
		</p>
	</form>
<button onclick="location.href='./MasterMaintenance.jsp'">Back</button>
</body>
</html>