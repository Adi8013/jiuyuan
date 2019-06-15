$(function () {
    $('.panel-tool-close').click(function () {
        console.log(this);
    });
    $('#dee-datagrid-2').datagrid({
        url: '/user/userlist',
        loadFilter: pagerFilter,
        rownumbers: true,
        singleSelect: false,
        pageSize: 20,
        pagination: true,
        multiSort: true,
        fitColumns: true,
        fit: true,
        columns: [[
            {checkbox: true},
            {field: 'userAccount', title: '登录帐号', width: 100},
            {field: 'userName', title: '姓名', width: 100},
            {field: 'password', title: '密码', width: 100},
            {field: 'phone', title: '电话', width: 100, align: 'right'},
            {field: 'email', title: '邮箱', width: 100},
            {field: 'registerDate', title: '注册日期', width: 100}
        ]]
    });
});

function searchUser() {
    var username = $('#search_username').val();
    // console.log(JSON.stringify(condition));
    $.ajax({
        type: 'POST',
        url: "/user/search",
        data: {"username": username},
        dataType: "json",
        success: function (data) {
            $('#dee-datagrid-2').datagrid('loadData',data);
        }
    });
}
/**
 * Name 添加记录
 */
function add() {
    if (!checkRequired()) {
        top.layer.alert("帐号，姓名，密码不能为空！",{
            icon:5,
            closeBtn:0
        });
        return;
    }
    $('#dee-form-2').form('submit', {
        url: '/user/addone',
        onSubmit: function () {
            var isValid = $(this).form('validate');
            if (!isValid) {
                //$.messager.alert('操作提示', '请按要求填写内容', 'warnning');
                top.layer.alert('请按要求填写内容', {
                    icon: 2,
                    closeBtn: 0
                });
            }
            return isValid; // 返回false终止表单提交
        },
        success: function (data) {
            var data = eval('(' + data + ')'); // change the JSON string to javascript object
            if (data.msg == "success") {
                //$.messager.alert('操作提示', '添加用户成功！', 'info');
                top.layer.alert('添加用户成功！', {
                    icon: 1,
                    closeBtn: 0
                });
                $('#dee-dialog-2').dialog('close');
                reload();
            } else {
                //$.messager.alert('操作提示', data.msg, 'error');
                top.layer.alert(data.msg, {
                    icon: 5,
                    closeBtn: 0
                });
                reload();
            }
        }
    });
}

function checkRequired() {
    var uc = $('#userAccount').val();
    var un = $('#userName').val();
    var pwd = $('#password').val();

    if (isEmpty(uc) || isEmpty(un) || isEmpty(pwd)) {
        return false;
    }
    return true;
}

/**
 * Name 修改记录
 */
function edit() {
    if (!checkRequired()) {
        top.layer.alert("帐号，姓名，密码不能为空！",{
            icon:5,
            closeBtn:0
        });
        return;
    }
    $('#dee-form-2').form('submit', {
        url: '/user/modifyone',
        success: function (data) {
            if (data == "success") {
                //$.messager.alert('信息提示', '提交成功！', 'info');
                top.layer.alert('修改成功', {
                    icon: 1,
                    closeBtn: 0
                });
                $('#dee-dialog-2').dialog('close');
                reload();
            } else {
                //$.messager.alert('信息提示', '提交失败！', 'info');
                top.layer.alert('修改失败', {
                    icon: 5,
                    closeBtn: 0
                });
                reload();
            }
            // 移除隐藏pk域
            //$('#pk').remove();
        }
    });
}

/**
 * Name 删除记录
 */
function remove() {
    var items = $('#dee-datagrid-2').datagrid('getSelections');// 选择多条记录
    var flag = false;
    $(items).each(function () {
        if (this.userAccount == 'super') {
            top.layer.alert('超级管理员super帐号无法删除', {
                icon: 5,
                closeBtn: 0
            });
            flag = true;
        }
    });
    if (flag) {
        return;
    }
    $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
        if (result) {
            var items = $('#dee-datagrid-2').datagrid('getSelections');// 选择多条记录

            if (items.length == 0) {
                //$.messager.alert('信息提示', '请至少选择一条数据！', 'error');
                top.layer.alert('请至少选择一条数据！', {
                    icon: 5,
                    closeBtn: 0
                });
                return;
            }
            var ids = [];
            $(items).each(function () {
                ids.push(this.pk);
            });
            $.ajax({
                url: '/user/delete',
                type: 'POST',
                data: {"pks": ids},
                success: function (data) {
                    if (data) {
                        //$.messager.alert('信息提示', '删除成功！', 'info');
                        top.layer.alert('删除成功！', {
                            icon: 1,
                            closeBtn: 0
                        });
                        reload();
                    } else {
                        //$.messager.alert('信息提示', '删除失败，请联系管理员！', 'error');
                        top.layer.alert('删除失败，请联系管理员！', {
                            icon: 5,
                            closeBtn: 0
                        });
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
    $('#userAccount').attr('disabled', false);
    $('#userName').attr('disabled', false);
    $('#userAccount').validatebox('reduce');
    $('#dee-form-2').form('clear');
    $('#dee-dialog-2').dialog({
        closed: false,
        modal: true,
        title: "添加信息",
        onClose: function () {
            //关闭事件：解决弹出窗口关闭后，验证消息还显示在最上面
            //$('.validatebox-tip').remove(); // 并不可行
            $('.tooltip').hide();
        },
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: add
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('.tooltip').hide();
                $('#dee-dialog-2').dialog('close');
            }
        }]
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
        //$.messager.alert('信息提示','请选择一条数据进行修改！','error');
        top.layer.alert('请选择一条数据进行修改！', {
            icon: 5,
            closeBtn: 0
        });
        return;
    } else if (item.userAccount == 'super') {
        top.layer.alert('超级管理员super帐号无法修改！！', {
            icon: 5,
            closeBtn: 0
        });
        return
    }

    $('#userAccount').validatebox('remove');
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
                //var pkHtml = '<input type="hidden" id="pk" name="pk"/>';
                //绑定值
                $('#userAccount').attr('disabled', true);
                if (data.userAccount == 'super') {
                    $('#userName').attr('disabled', true);
                } else {
                    $('#userName').attr('disabled', false);
                }
                //$('#dee-form-2').prepend(pkHtml).form('load', data)
                $('#dee-form-2').form('load', data);
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

