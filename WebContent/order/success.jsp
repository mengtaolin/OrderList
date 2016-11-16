<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
订餐成功
<script type="text/javascript">
var i = 10;  
function remainTime(){  
    if(i==0){  
        location.href='orderList.jsp';  
    } 
    i--;
    document.getElementById('endtime').innerHTML="跳转ing....." + i;  
    setTimeout("remainTime()",1000);  
}  
remainTime();  
</script>
</body>
</html>