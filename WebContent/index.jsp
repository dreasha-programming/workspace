<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/MainMenu" method="post">
	<p>UserId:<input type="text" name="Id"></p>
	<p>Password:<input type="password" name="Password"></input>
	<input type="submit" value="Login"></input>
</form>
</body>
</html>