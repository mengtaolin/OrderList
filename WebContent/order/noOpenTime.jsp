<%@page import="com.zhizhang.CheckUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>志展点餐系统</title>
</head>
<body>
还未到点餐时间，请您等候。
中午点餐时间为每日：<%=CheckUtil.orderTime() %>
</body>
</html>