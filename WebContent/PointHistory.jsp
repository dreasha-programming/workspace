<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<%
	String[] arrFromUserName = (String[]) request.getAttribute("arrFromUserName");
	String[] arrToUserName = (String[]) request.getAttribute("arrToUserName");
	String[] arrComment = (String[]) request.getAttribute("arrComment");
	String[] arrPoint = (String[]) request.getAttribute("arrPoint");
	String[] arrUketoriFlg = (String[]) request.getAttribute("arrUketoriFlg");
	String[] arrUpdateDate = (String[]) request.getAttribute("arrUpdateDate");
	int maxArraySize = Integer.parseInt((String) request.getAttribute("maxArraySize"));
	String Id = (String) request.getAttribute("Id");
%>
<head>
<meta charset="Shift_JIS">
<title>Point History</title>
<style>
table th,tr,td {
  width: 200px;
  border-collapse: collapse;
}
</style>
</head>
<body>
<table class="table" border="1" style="border-collapse: collapse">
  <tr><th>From</th><th>To</th><th>Comment</th><th>Point</th><th>GetStatus</th><th>UpdateDate</th></tr>
<%
for(int i=0;i<maxArraySize;i++){
%>
<tr>
<td><%= arrFromUserName[i] %></td>
<td><%= arrToUserName[i] %></td>
<td><%= arrComment[i] %></td>
<td><%= arrPoint[i] %></td>
<td><%= arrUketoriFlg[i] %></td>
<td><%= arrUpdateDate[i] %></td>
</tr>
<% }%>
</table>
<form action="<%=request.getContextPath()%>/MainMenu" method="post">
	<input type="hidden" name="Id" value="<%=Id%>" />
	<input type="hidden" name="backFlg" value="1" />
	<input type="submit" value="Back"></input>
</form>
</body>
</html>