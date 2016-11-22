<%@page import="com.zhizhang.dao.selectOrder.DayOrderInfo"%>
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
	<hr>
	<div>
		<%
			int type = (Integer)request.getAttribute("everydayType");
			if(type == 3){
				DayOrderInfo dayInfo = (DayOrderInfo)request.getAttribute("dayInfo");
				
			}
			else if(type == 1 || type == 2 || type == 0){
				out.print("没有订餐数据");
			}
		%>
	</div>
</div>
</body>
</html>