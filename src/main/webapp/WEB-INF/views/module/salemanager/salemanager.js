$(function () {
    $('#dee-datagrid-2').datagrid({
        url: '/sale/salelist',
        loadFilter: pagerFilter,
        rownumbers: true,
        singleSelect: false,
        pageSize: 20,
        pagination: true,
        multiSort: false,
        fitColumns: true,
        fit: true,
        columns: [[
            {checkbox: true},
            {
                field: 'option', title: '操作', minwidth: 180, formatter: function (value, row, index) {
                    var oper_container = '';
                    if (row.operator == top.strUserAccount) {
                        oper_container += "<a href='javascript:void(0);' class='modifyFunc' onclick='modify(\"" + row.pk + "\")' params=\"[" + row.pk + "]\" >修改</a>  ";
                        oper_container += "<a href='javascript:void(0);' onclick='remove(\"" + row.pk + "\")' class='deleteFunc' params=\"[" + row.pk + "]\" >删除</a>  ";
                    }
                    oper_container += "<a href='javascript:void(0);' onclick='view(\"" + row.pk + "\")' >查看</a>  ";
                    return oper_container;
                }
            },
            {field: 'saleNo', title: '销售单号', width: 60 },
            {field: 'operator', title: '制单人', width: 40},
            {field: 'receiver', title: '收货单位', width: 100},
            {field: 'address', title: '地址', width: 200},
            {field: 'insertTime', title: '制单时间', width: 100},
            {field: 'updatePerson', title: '最后更新人', width: 100},
            {field: 'lastestUpdate', title: '最后更新时间', width: 100},
        ]]
    });
});

/**
 *
 */
function searchBy() {
    var startTime = $('#start_time').datebox('getValue');
    var endTime = $('#end_time').datebox('getValue');
    var receiver = $('#id_receive').val();
    if (startTime > endTime) {
        top.layer.alert("起始日期（"+ startTime +"）不能大于结束日期（"+ endTime +"）",{
            icon:5,
            closeBtn:0
        });
        return;
    }
    var condition = {};
    condition.startTime = startTime;
    condition.endTime = endTime;
    condition.receiver = receiver;
    // console.log(JSON.stringify(condition));
    $.ajax({
        type: 'POST',
        url: "/sale/search",
        data: JSON.stringify(condition),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            $('#dee-datagrid-2').datagrid('loadData',data);
        }
    });
}
/**
 * Name 查看销售单
 */
function view(pk) {
    top.layer.open({
        type: 2,
        title: "九元古建砖瓦销售单",
        shift: 1,
        closeBtn: 2,
        area: ["1000px", "700px"],
        shade: false,
        zIndex: '2018',
        success: function (layero) {
            top.layer.setTop(layero);
        },
        content: "/sale/saleview?salePk=" + pk,
        end: function () {
            reload();
        }
    });
}


/**
 * Name 打开添加页面
 */
function openAdd() {
    top.layer.open({
        type: 2,
        title: "添加销售单",
        shift: 1,
        closeBtn: 2,
        area: ["1100px", "700px"],
        shade: false,
        zIndex: '2018',
        success: function (layero) {
            top.layer.setTop(layero);
        },
        content: "/sale/editSale?method=" + "add",
        end: function () {
            reload();
        }
    });
}

/**
 * Name 打开添加页面
 */
function modify(pk) {
    top.layer.open({
        type: 2,
        title: "修改销售单",
        shift: 1,
        closeBtn: 2,
        area: ["1100px", "700px"],
        shade: false,
        zIndex: '2018',
        success: function (layero) {
            top.layer.setTop(layero);
        },
        content: "/sale/editSale?method=modify&salePk=" + pk,
        end: function () {
            reload();
        }
    });
}

/**
 * Name 修改记录
 */
function edit() {
    $('#dee-form-2').form('submit', {
        url: '/user/modifyone',
        success: function (data) {
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
    top.layer.confirm('确定要删除选定数据吗？',
        {
            icon: 3,
            title: '信息提示',
            yes: function (index) {
                var items = $('#dee-datagrid-2').datagrid('getSelections');// 选择多条记录
                if (items.length == 0) {
                    top.layer.alert('请至少选择一条数据！', {icon: 5, closeBtn: 0});
                    return;
                }
                var ids = [];
                $(items).each(function () {
                    ids.push(this.pk);
                });
                $.ajax({
                    url: '/sale/deleteSale',
                    type: 'POST',
                    data: {"pks": ids},
                    success: function (data) {
                        console.log("data: " + data);
                        if (data) {
                            top.layer.alert('删除成功！', {icon: 1, closeBtn: 0});
                            reload();
                        } else {
                            top.layer.alert('删除失败，请联系管理员！', {icon: 5, closeBtn: 0});
                            reload();
                        }
                    }
                });
                top.layer.close(index);
                reload();
            },
            cancel: function (index) {
                top.layer.close(index);
                reload();
            }
        });
}

/**
 * Name 打开添加窗口
 */

/*function openAdd() {
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
}*/

/**
 * Name 打开修改窗口
 */
function openEdit() {
    $('#userAccount').validatebox('remove');
    $('#dee-form-2').form('clear');
    var item = $('#dee-datagrid-2').datagrid('getSelected');// 多选也是选到一条记录
    if (item == null) {
        $.messager.alert('信息提示', '请选择一条数据进行修改！', 'error');
        return;
    }
    //alert(item.productid);return;
    // 禁用userAccount
    //$('#userAccount').attr('disabled',true);

    //$('#userAccount').removeClass('easyui-validatebox');
    $.ajax({
        url: '/user/findone',
        data: {"pk": item.pk},
        dataType: "json",
        success: function (data) {
            if (data == null) {
                $.messager.alert('信息提示', '参数有误，请联系管理员！', 'error');
            } else {
                console.log(data);
                var pkHtml = '<input type="hidden" id="pk" name="pk"/>';
                //绑定值
                $('#userAccount').attr('disabled', true);
                $('#dee-form-2').prepend(pkHtml).form('load', data)
                //$('#userAccount').removeAttr('data-options');
            }
        }
    });
    $('#dee-dialog-2').dialog({
        closed: false,
        modal: true,
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
function reload() {
    $('#dee-datagrid-2').datagrid('reload');//刷新
}

