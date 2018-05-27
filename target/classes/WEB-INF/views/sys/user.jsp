<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<%@include file="/WEB-INF/views/base/base.jsp"%>
<script type="text/javascript" src= "${path}/sys/user.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="dee-toolbar-2">
			<div class="dee-toolbar-button">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a> 
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
			</div>
			<div class="dee-toolbar-search">
				<label>起始时间：</label><input class="easyui-datebox"
					style="width: 100px"> <label>结束时间：</label><input
					class="easyui-datebox" style="width: 100px"> <label>用户组：</label>
				<select class="easyui-combobox" panelHeight="auto"
					style="width: 100px">
					<option value="0">选择用户组</option>
					<option value="1">黄钻</option>
					<option value="2">红钻</option>
					<option value="3">蓝钻</option>
				</select> <label>关键词：</label><input class="dee-text" style="width: 100px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
			</div>
		</div>
		<!-- End of toolbar -->
		<table id="dee-datagrid-2" class="easyui-datagrid"
			toolbar="#dee-toolbar-2"></table>
	</div>
	<!-- Begin of easyui-dialog -->
	<div id="dee-dialog-2" class="easyui-dialog"
		data-options="closed:true,iconCls:'icon-save'"
		style="width: 400px; padding: 10px;">
		<form id="dee-form-2" method="post">
			<table>
				<tr>
					<td width="60" height="10" align="right">帐 号:</td>
					<td><input type="text" id="userAccount" name="userAccount" class="dee-text easyui-validatebox" data-options="validType:['unique','englishOrNum'],required:true" /></td>
				</tr>
				<tr>
					<td align="right">姓 名:</td>
					<td><input type="text" id="userName" name="userName" class="dee-text easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td align="right">密 码:</td>
					<td><input type="password" id="password" name="password" class="dee-text easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td align="right">电 话:</td>
					<td><input type="text" id="phone" name="phone" class="dee-text" data-options="validType:'tel'" /></td>
				</tr>
				<tr>
					<td align="right">邮 箱:</td>
					<td><input type="text" id="email" name="email" class="dee-text easyui-validatebox"  data-options="validType:'email'"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>