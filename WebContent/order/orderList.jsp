<%@page import="com.zhizhang.CheckUtil"%>
<%@page import="java.util.Date"%>
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
		
		function radioClick(obj){
			var checked = obj.checked;
			var tmp = obj.name.split("_");
			var str = "." + tmp[1];
			var list = $(str)
			var len = list.length;
			for(var i= 0;i< len;i ++){
				var node = list[i];
				node.checked = false;
			}
			obj.checked = checked;
		}
		
		function check(){
			
			var element = $("#department");
			if(element[0].value!="请选择"){
				var obj = $("input[type=checkbox]");
				var len = obj.length;
				var str = "您点了【";
				var count = 0;
				for(var i=0;i < len;i++){
					if(obj[i].checked == true){
						str += obj[i].getAttribute("compName") + "  " + obj[i].getAttribute("orderName");
						count ++;
					}
				}
				if(count == 0){
					str = "您还未选择点餐内容";
					return false
				}
				else{
					str += "】\n是否确定?";
					var close = confirm(str);
			        if ( close) {
			            return true;
			        }
			        else
			        {
			            return false;
			        }
					return true;
				}
			}
			else {
				alert("请选择点餐人员")
				return false;
			}
		}
		
		function test(){
			var obj = $("input[type=checkbox]");
			var len = obj.length;
			var str = "您点了（";
			var count = 0;
			for(var i=0;i < len;i++){
				if(obj[i].checked == true){
					str += obj[i].getAttribute("compName") + "  " + obj[i].getAttribute("orderName");
					count ++;
				}
			}
			if(count == 0){
				str = "您还未选择点餐内容";
			}
			else{
				str += "\n是否确定?"
			}
			alert(str);
		}
	</script>
<body>
	<form action="/OrderList/doOrder" method="GET" onsubmit="return check()">
	<hr>
	&nbsp&nbsp&nbsp姓名：<select name="department" id="department" onchange="onChange(this)">
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
	&nbsp&nbsp&nbsp点餐进行中<%
		Date date = new Date();
		out.print("时间为 : " + CheckUtil.orderTime());
	%>
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
				&nbsp&nbsp&nbsp<td>
					<%=listInfo.getName() %>
				</td>
				<td id="radioTd">
					<%
					List<Float> priceList = listInfo.getPriceList();
					int priceLen = priceList.size();
					for(int j = 0;j < priceLen;j ++){
					%>
						<input type="checkbox" id="price<%=j%>" class="<%=listInfo.getId()%>" 
						name="<%=compInfo.getId() %>_<%=listInfo.getId()%>_<%=priceList.get(j) %>"
						compName="<%=compInfo.getName() %>" 
						orderName="<%=listInfo.getName() %>"
						onclick="radioClick(this)">
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
	<input type="submit" value="提交">&nbsp&nbsp<input type="button" onclick="test()" value="测试">
	</form>
	<hr/>
	&nbsp&nbsp&nbsp<a href="checkEveryDayOrder">查看点餐内容</a>
	<hr/>
	<br/>
</body>
</html>