<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<%@include file="/WEB-INF/views/base/base.jsp"%>
</head>
<body>
	<table id = "dg" class="easyui-datagrid" style="width: 400px; height: 250px">
		
	</table>
	<script type="text/javascript">
		$('#dg').datagrid({    
    		url:'/user/userlist',    
    		columns:[[    
       			 {field:'userName',title:'姓名111',width:100},    
        		{field:'password',title:'密码',width:100},    
       			 {field:'phone',title:'电话',width:100,align:'right'}    
   			 ]]    
		});  


</script>
</body>
</html>