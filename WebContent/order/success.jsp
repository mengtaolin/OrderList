<%@page import="com.zhizhang.dao.selectOrder.SelectOrderInfo"%>
<%@page import="com.zhizhang.dao.selectOrder.OrderPriceInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>志展订餐系统</title>
</head>
<body>
<%
	SelectOrderInfo info = (SelectOrderInfo)request.getAttribute("selectOrderInfo");
%>
<div><b><%=info.getDepartment() %></b>&nbsp&nbsp&nbsp<b><%=info.getEmployee()%></b>&nbsp&nbsp&nbsp定了<br/><%
	for(OrderPriceInfo priceInfo : info.getOrderPriceInfo()){
		out.print(priceInfo.getCompName() + "的" + priceInfo.getOrderName() + " : " + priceInfo.getPrice() + "元 &nbsp&nbsp&nbsp <br/>");
	}
	out.print("<h2>总价：" + info.getTotalPrice() + "元</h2>");
%></div>
<div id="endtime"></div>
<script type="text/javascript">
var i = 10;
var key = setInterval(remainTime,1000);
function remainTime(){
    if(i==0){
    	clearInterval(key)
        location.href='/OrderList/initData';
    } 
    i--;
    document.getElementById('endtime').innerHTML="跳转ing....." + i;  
}
</script>
</body>
</html>