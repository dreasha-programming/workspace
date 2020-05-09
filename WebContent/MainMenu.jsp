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
	String kengen = (String) request.getAttribute("kengen");
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
<br>
<br>
<form method="POST" action="<%=request.getContextPath()%>/PointGraph" id="link3">
<input name="Id" value="<%=Id%>" type="hidden" />
<a href="about:blank" onclick="document.getElementById('link3').submit();return false;">Point Graph Page</a>
</form>
<br>
<br>
<form method="POST" action="<%=request.getContextPath()%>/PointHistory" id="link4">
<input name="Id" value="<%=Id%>" type="hidden" />
<a href="about:blank" onclick="document.getElementById('link4').submit();return false;">Point History Page</a>
</form>

<% if (kengen.equals("0")){ %>
<br>
<br>
<form method="POST" action="<%=request.getContextPath()%>/./MasterMaintenance" id="link0">
<input name="Id" value="<%=Id%>" type="hidden" />
<a href="about:blank" onclick="document.getElementById('link0').submit();return false;">Master Maintenance</a>
</form>
<%} %>
<br>
<br>
<button onclick="location.href='./index.jsp'">Logout</button>
</body>
</html>