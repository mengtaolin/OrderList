<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>志展点餐系统</title>
</head>
<body>
<div>
	<div>
		每日点餐内容
	</div>
	<div><label for="meeting">点餐日期：</label><input id="meeting" type="date" value="<%=request.getAttribute("time")%>"/></div>
	<div>
		<%
			
		%>
	</div>
</div>
</body>
</html>