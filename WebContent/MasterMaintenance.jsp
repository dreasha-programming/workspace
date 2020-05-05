<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<title>Master Maintenance</title>
</head>
<body>
<form method="POST" action="<%=request.getContextPath()%>/M_UserInsert" id="link1">
<a href="about:blank" onclick="document.getElementById('link1').submit();return false;">M_User Insert</a>
</form>
<br>
<br>
<form method="POST" action="<%=request.getContextPath()%>/M_UserUpdate" id="link2">
<a href="about:blank" onclick="document.getElementById('link2').submit();return false;">M_User Update</a>
</form>
</body>
</html>