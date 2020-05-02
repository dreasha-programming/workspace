<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String remainPoint = (String) request.getAttribute("Point");
	String getPoint = (String) request.getAttribute("GetPoint");
	String UserName = (String) request.getAttribute("UserName");
	String Id = (String) request.getAttribute("Id");
%>
<html>
<head>
<meta charset="Shift_JIS">
<title>Point get Page</title>
</head>
<body>
<%
if(getPoint.equals("0")){
%>
<%=UserName%> have no points to get.
<form action="<%=request.getContextPath()%>/MainMenu" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="submit" value="Back"></input>
</form>
<%
} else {
%>
<%=UserName%> can get <%=getPoint%> points.
Do you get points?
	<form action="<%=request.getContextPath()%>/PointResult"
		method="post">
		<input type="hidden" name="Id" value="<%=Id%>" />
		<input type="hidden" name="remainPoint" value="<%=remainPoint%>" />
		<input type="hidden" name="txtPoint" value="<%=getPoint%>" />
		<input type="hidden" name="UketoriFlg" value="1" />
		<input type="submit" name= "btnPutPoint" value="get"/>
	</form>
<%
}
%>
</body>
</html>