<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${path}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${path}/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
</head>
<body>
	<table id = "dg" class="easyui-datagrid" style="width: 400px; height: 250px">
		
	</table>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#dg').datagrid({    
    		url:'/user/userlist',    
    		columns:[[    
       			 {field:'userName',title:'姓名111',width:100},    
        		{field:'password',title:'密码',width:100},    
       			 {field:'phone',title:'电话',width:100,align:'right'}    
   			 ]]    
		});  
	});
</script>
</body>
</html>