<%@page import="com.zhizhang.dao.OrderCompanyInfo"%>
<%@page import="com.zhizhang.dao.AllOrderCompanyInfo"%>
<%@page import="com.zhizhang.dao.DepartmentInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.zhizhang.dao.CompanyDataInfo"%>
<%@page import="com.zhizhang.dao.OrderListInfo" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>志展点餐系统</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
		function onChange(e){
			var element = document.getElementById("employee");
			var select = document.getElementById("department")

			$.post("/OrderList/initData",{selectId:select.selectedOptions[0].value},function(result){
				var department = JSON.parse(result);
				var list = department.children;
				var len = list.length;
				element.innerHTML = "";
				for(var i= 0;i < len;i ++)
				{
					var child = list[i]
					element.innerHTML += "<option label='" + child.name + "' value='" + child.id + "'>" + child.name+ "</option>";
				}
				element.selectedIndex = 0;
			});
		}
		
		function radioClick(e){
			var tmp = e.getAttribut("name")
			var list = $(".tmp")
			for(var node in list){
				node.selected = false;
			}
			e.selected = true;
		}
	</script>
<body>
	<form action="/OrderList/doOrder" method="GET">
	<hr>
	姓名：<select name="department" id="department" onchange="onChange(this)">
	<option value="请选择">请选择</option>
	<%
		CompanyDataInfo data = (CompanyDataInfo) pageContext.getServletContext().getAttribute("data");
		List<DepartmentInfo> list = data.departments;
		int len = list.size();
		for(int i = 0;i < len; i++){
			DepartmentInfo depart = (DepartmentInfo)list.get(i);
			%>
			<option label="<%=depart.getName() %>" value="<%=depart.getId()%>"><%=depart.getName() %></option>
			<%
		}
	%>
	</select>
	
	<select name="employee" id="employee">
	</select>
	<br/>
	<hr/>
	<%
	AllOrderCompanyInfo info = (AllOrderCompanyInfo) pageContext.getServletContext().getAttribute("allOrderInfo");
	List<OrderCompanyInfo> orderCompList = info.getAllInfo();
	for(OrderCompanyInfo compInfo : orderCompList)
	{
		%>
		<u><div>店名：<%=compInfo.getName()%>&nbsp&nbsp&nbsp口号：<%=compInfo.getCatchword() %></div></u>
		<tr>
			<%
			List<OrderListInfo> orderList = compInfo.getListInfo();
			for(OrderListInfo listInfo : orderList)
			{
				%>
				<td>
					<input type="checkbox" value="<%=listInfo.getName()%>" id="<%=listInfo.getId()%>">
				</td>
				<td>
					<%=listInfo.getName() %>
				</td>
				<td id="radioTd">
					<%
					List<Float> priceList = listInfo.getPriceList();
					int priceLen = priceList.size();
					for(int j = 0;j < priceLen;j ++){
					%>
						<input type="radio" id="price<%=j%>" name="<%=listInfo.getId()%>" onclick="radioClick(this)">
						<%=priceList.get(j) %>元
					<%
					}
					%>
				</td>
				<br/>
				<%
			}
			%>
		</tr>
		<br/>
		<hr/>
		<%
	}
	%>
	<div id="selectDepartment"></div>
	<div id="selectOrder"></div>
	<br/>
	<input type="submit" value="提交">
	</form>
	
</body>
</html>