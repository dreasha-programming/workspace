<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<%
	// Servletのデータ受け取り
	String[] arrPoint = (String[]) request.getAttribute("arrPoint");
	String[] arrName = (String[]) request.getAttribute("arrName");
	String[] arrDiff = (String[]) request.getAttribute("arrDiff");
	String Id = (String) request.getAttribute("Id");
	int arrSize = Integer.parseInt((String) request.getAttribute("arrSize"));
    int i,j = 0;
%>
<head>
<meta charset="Shift_JIS">
<title>Point Graph</title>
</head>
<body>
<table>
<% for (i = 0; i < arrSize; i++) {%>
    <tr>
        <td><%=arrName[i] %>:</td>
        <%for (j=0;j<Integer.parseInt(arrPoint[i]);j++){ %>
        <td style="background-color: blue; font-size: 20px; width: 5px;" >&nbsp;</td>
        <%} %>
        <%for (j=0;j<Integer.parseInt(arrDiff[i]);j++){ %>
        <td style="background-color: white; font-size: 20px; width: 5px;" >&nbsp;</td>
        <%} %>
        <td>(<%=arrPoint[i] %>)</td>
    </tr>
<%} %>
</table>
<br>
<form action="<%=request.getContextPath()%>/MainMenu" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>