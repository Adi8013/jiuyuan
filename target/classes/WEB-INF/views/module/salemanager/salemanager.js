$(function() {
	$('#dee-datagrid-2').datagrid({
		url : '/sale/salelist',
		loadFilter : pagerFilter,
		rownumbers : true,
		singleSelect : false,
		pageSize : 20,
		pagination : true,
		multiSort : true,
		fitColumns : true,
		fit : true,
		columns : [ [ 
			{checkbox : true}, 
			{field : 'saleNo',title : '销售单号',width : 100}, 
			{field : 'productName',title : '产品名称',width : 100}, 
			{field : 'size',title : '型号规格',width : 100}, 
			{field : 'unit',title : '单位',width : 100,align : 'right'}, 
			{field : 'quantity',title : '数量',width : 100}, 
			{field : 'perPrice',title : '单价',width : 100} 
		] ]
	});
});

/**
 * Name 添加记录
 */
function add() {
	$('#dee-form-2').form('submit', {
		url : '/user/addone',
		onSubmit : function() {
			var isValid = $(this).form('validate');
			if (!isValid) {
				$.messager.alert('操作提示', '请按要求填写内容', 'warnning');
			}
			return isValid; // 返回false终止表单提交
		},
		success : function(data) {
			var data = eval('(' + data + ')'); // change the JSON string to javascript object
			if (data.msg == "success") {
				$.messager.alert('操作提示', '添加用户成功！', 'info');
				$('#dee-dialog-2').dialog('close');
				reload();
			} else {
				$.messager.alert('操作提示', data.msg, 'error');
				reload();
			}
		}
	});
}

/**
 * Name 修改记录
 */
function edit() {
	$('#dee-form-2').form('submit', {
		url : '/user/modifyone',
		success : function(data) {
			if (data == "success") {
				$.messager.alert('信息提示', '提交成功！', 'info');
				$('#dee-dialog-2').dialog('close');
				reload();
			} else {
				$.messager.alert('信息提示', '提交失败！', 'info');
				reload();
			}
		}
	});
}

/**
 * Name 删除记录
 */
function remove() {
	$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
		if (result) {
			var items = $('#dee-datagrid-2').datagrid('getSelections');// 选择多条记录
			if (items.length == 0) {
				$.messager.alert('信息提示', '请至少选择一条数据！', 'error');
				return;
			}
			var ids = [];
			$(items).each(function() {
				ids.push(this.pk);
			});
			$.ajax({
				url : '/user/delete',
				type:'POST',
				data:{"pks":ids},
				success : function(data) {
					if (data) {
						$.messager.alert('信息提示', '删除成功！', 'info');
						reload();
					} else {
						$.messager.alert('信息提示', '删除失败，请联系管理员！', 'error');
						reload();
					}
				}
			});
		}
	});
}

/**
 * Name 打开添加窗口
 */
function openAdd() {
	$('#userAccount').attr('disabled',false);
	$('#userAccount').validatebox('reduce');
	$('#dee-form-2').form('clear');
	$('#dee-dialog-2').dialog({
		closed : false,
		modal : true,
		title : "添加信息",
		buttons : [ {
			text : '确定',
			iconCls : 'icon-ok',
			handler : add
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('.tooltip').hide();
				$('#dee-dialog-2').dialog('close');
			}
		} ]
	});
}

/**
 * Name 打开修改窗口
 */
function openEdit() {
	$('#userAccount').validatebox('remove');
	$('#dee-form-2').form('clear');
	var item = $('#dee-datagrid-2').datagrid('getSelected');// 多选也是选到一条记录
	if (item == null) {
		$.messager.alert('信息提示','请选择一条数据进行修改！','error');
		return;
	}
	//alert(item.productid);return;
	// 禁用userAccount
	//$('#userAccount').attr('disabled',true);
	
	//$('#userAccount').removeClass('easyui-validatebox');
	$.ajax({
		url : '/user/findone',
		data : {"pk" : item.pk},
		dataType : "json",
		success : function(data) {
			if (data == null) {
				$.messager.alert('信息提示','参数有误，请联系管理员！','error');
			} else {
				console.log(data);
				var pkHtml = '<input type="hidden" id="pk" name="pk"/>';
				//绑定值
				$('#userAccount').attr('disabled',true);
				$('#dee-form-2').prepend(pkHtml).form('load', data)
				//$('#userAccount').removeAttr('data-options');
			}
		}
	});
	$('#dee-dialog-2').dialog({
		closed : false,
		modal : true,
		title : "修改信息",
		buttons : [ {
			text : '确定',
			iconCls : 'icon-ok',
			handler : edit
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#dee-dialog-2').dialog('close');
			}
		} ]
	});
}

/**
 * 刷新datagrid
 */
function reload() {
	$('#dee-datagrid-2').datagrid('reload');//刷新
}
