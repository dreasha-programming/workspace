<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String remainPoint = (String) request.getAttribute("Point");
	String UserName = (String) request.getAttribute("UserName");
	String Id = (String) request.getAttribute("Id");
%>
<head>
<meta charset="Shift_JIS">
<title>MainMenu</title>
</head>
<body>
Login User : <%=UserName%>
<br>
<br>
<form method="POST" action="<%=request.getContextPath()%>/PointGetPage" id="link1">
<!--  <a href="<%=request.getContextPath()%>/PointGetPage">Point Get Page</a> -->
<input name="Id" value="<%=Id%>" type="hidden" />
<a href="about:blank" onclick="document.getElementById('link1').submit();return false;">Point Get Page</a>
</form>
<br>
<br>
<form method="POST" action="<%=request.getContextPath()%>/PointPutPage" id="link2">
<input name="Id" value="<%=Id%>" type="hidden" />
<a href="about:blank" onclick="document.getElementById('link2').submit();return false;">Point Put Page</a>
</form>
<!--<a href="<%=request.getContextPath()%>/PointF">Point Put Page</a>-->

</body>
</html>