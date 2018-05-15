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
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="dee-toolbar-2">
			<div class="dee-toolbar-button">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					onclick="openAdd()" plain="true">添加</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()"
					plain="true">修改</a> <a href="#" class="easyui-linkbutton"
					iconCls="icon-remove" onclick="remove()" plain="true">删除</a> <a
					href="#" class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="cancel()" plain="true">取消</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()"
					plain="true">刷新</a>
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
					<td width="60" align="right">帐 号:</td>
					<td><input type="text" name="userAccount" class="dee-text easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td align="right">姓 名:</td>
					<td><input type="text" name="userName" class="dee-text easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td align="right">密 码:</td>
					<td><input type="password" name="password" class="dee-text easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td align="right">电 话:</td>
					<td><input type="text" name="phone" class="dee-text" /></td>
				</tr>
				<tr>
					<td align="right">邮 箱:</td>
					<td><input type="text" name="email" class="dee-text easyui-validatebox"  data-options="validType:'email'"/></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- End of easyui-dialog -->
	<script type="text/javascript">
	/**
	* Name 添加记录
	*/
	function add(){
		$('#dee-form-2').form('submit', {
			url:'/user/addone',
			onSubmit: function(){    
				var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
			    },  
			success:function(data){
				if(data == "success"){
					$.messager.alert('操作提示','添加用户成功！','info');
					$('#dee-dialog-2').dialog('close');
					reload();
				}
				else
				{
					$.messager.alert('操作提示','添加用户失败！','error');
					reload();
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#dee-form-2').form('submit', {
			url:'',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#dee-dialog-2').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var items = $('#dee-datagrid-2').datagrid('getSelections');
				var ids = [];
				$(items).each(function(){
					ids.push(this.productid);	
				});
				//alert(ids);return;
				$.ajax({
					url:'',
					data:'',
					success:function(data){
						if(data){
							$.messager.alert('信息提示','删除成功！','info');		
						}
						else
						{
							$.messager.alert('信息提示','删除失败！','info');		
						}
					}	
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		$('#dee-form-2').form('clear');
		$('#dee-dialog-2').dialog({
			closed: false,
			modal:true,
            title: "添加信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            },{
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#dee-dialog-2').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		$('#dee-form-2').form('clear');
		var item = $('#dee-datagrid-2').datagrid('getSelected');
		//alert(item.productid);return;
		$.ajax({
			url:'',
			data:'',
			success:function(data){
				if(data){
					$('#dee-dialog-2').dialog('close');	
				}
				else{
					//绑定值
					$('#dee-form-2').form('load', data)
				}
			}	
		});
		$('#dee-dialog-2').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#dee-dialog-2').dialog('close');                    
                }
            }]
        });
	}	
	
	/**
	* 刷新datagrid
	*/
	 function reload(){
        $('#dee-datagrid-2').datagrid('reload');//刷新
    }
	
	/**
	* Name 分页过滤器
	*/
	function pagerFilter(data){            
		if (typeof data.length == 'number' && typeof data.splice == 'function'){// is array                
			data = {                   
				total: data.length,                   
				rows: data               
			}            
		}        
		var dg = $(this);         
		var opts = dg.datagrid('options');          
		var pager = dg.datagrid('getPager');          
		pager.pagination({                
			onSelectPage:function(pageNum, pageSize){                 
				opts.pageNumber = pageNum;                   
				opts.pageSize = pageSize;                
				pager.pagination('refresh',{pageNumber:pageNum,pageSize:pageSize});                  
				dg.datagrid('loadData',data);                
			}          
		});           
		if (!data.originalRows){               
			data.originalRows = (data.rows);       
		}         
		var start = (opts.pageNumber-1)*parseInt(opts.pageSize);          
		var end = start + parseInt(opts.pageSize);        
		data.rows = (data.originalRows.slice(start, end));         
		return data;       
	}
	
	/**
	* Name 载入数据
	*/
	$('#dee-datagrid-2').datagrid({
		url:'/user/userlist',
		loadFilter:pagerFilter,		
		rownumbers:true,
		singleSelect:false,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		fit:true,
		columns:[[ 
			 	{checkbox:true},
			 	{field:'userAccount',title:'登录帐号',width:100},    
  			 	{field:'userName',title:'姓名',width:100},    
   			 	{field:'password',title:'密码',width:100},    
  			 	{field:'phone',title:'电话',width:100,align:'right'},
   			 	{field:'email',title:'邮箱',width:100},
   				{field:'registerDate',title:'注册日期',width:100}
			 ]]  
	});
</script>
</body>
</html>