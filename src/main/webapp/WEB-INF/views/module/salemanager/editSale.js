// 记录明细的下标
var detailNum = 0
var topIndex = top.layer.index;

function init() {
    // 自动填充制单员（为当前系统默认登录人）
    $("#operator").text("制单员： " + top.strUserName);
}

function delTr(ckb) {
    // 获取选中的复选框，然后循环遍历删除
    var ckbs = $("input[name=" + ckb + "]:checked");
    if (ckbs.size() == 0) {
        top.layer.alert('请选择要删除的行！', {
            icon: 2,
            closeBtn: 0
        });
        return;
    }
    ckbs.each(function () {
        var $tr = $(this).parent().parent();
        // console.log($("#sumPrice").text());
        // console.log($tr.find('input[name="totalPrice"]').eq(0));
        // 重新计算合计
        var newSumPrice =  $("#sumPrice").text() - $tr.find('input[name="totalPrice"]').eq(0).val();
        console.log("newSumPrice" + newSumPrice);
        $("#sumPrice").text(newSumPrice.toFixed(2));
        $("#CN").text(numToCN(newSumPrice));
        $tr.remove();
    });

}

function detailNumToName(num) {
    switch (num) {
        case 1:
            return "产品名称";
        case 2:
            return "型号规格";
        case 3:
            return "单位";
        case 4:
            return "单价";
        case 5:
            return "数量";
    }
}

/**
 * 获取明细
 * @returns {*}
 */
function getData() {
    var k;

    var array = [];
    var inputVal;
    var $dataRows = $('#tableList tr[attr="detail"]');
    if ($dataRows.length == 0) {
        top.layer.alert('最少要添加一条明细数据', {
            icon: 2
        });
        return false;
    }
    var receiver = $("#id_receiver").val();
    var address = $("#id_address").val();

    if (isEmpty(receiver) || isEmpty(address)) {
        top.layer.alert('收货单位或地址不能为空', {
            icon: 2
        });
        return false;
    }
    var result = "";
    $dataRows.each(function (index) {
        var st = [];
        var chil = $(this).children('td');
        var tdCount = chil.length;
        for (k = 1; k < tdCount; k++) {
            var _input = chil.eq(k).children('input');
            inputVal = _input.val();
            if ((inputVal == null || inputVal == "") && k != (tdCount-1)) {
                result = "第" + (index + 1) + "行" + detailNumToName(k) + "不能为空";
                return false;
            }
            st.push(inputVal);
        }
        var dataJson = {};
        /*dataJson = {
            'productName': st[0],
            'size': st[1],
            'unit': st[2],
            'perPrice': st[3],
            'quantity': st[4],
            'remark': st[6]
        };*/
        dataJson.productName = st[0];
        dataJson.size = st[1];
        dataJson.unit = st[2];
        dataJson.perPrice = st[3];
        dataJson.quantity = st[4];
        dataJson.remark = st[6];
        dataJson.receiver = receiver;
        dataJson.address = address;
        array.push(dataJson);
    });
    if ( result != "") {
        top.layer.alert(result, {
            icon: 2
        });
        return false
    }
    // console.log(array);
    return array;
}

/**
 * 每行的金额（单价*数量）
 * @param dom 对象 obj
 * @returns {boolean}
 */
function totalPrice(obj) {
    var num = obj.value.replace(/\s*/g, ""); // 去除所有空格
    if (num.split(".").length > 2 ) { // 小数点验证
        top.layer.alert('请输入大于0的数字', {
            icon: 2
        })
        $(obj).val("");
        return false;
    }
    var reg = /^\d+(\.\d+)?$/; // 注意：故意限制了 0321 这种格式，如不需要，直接reg=/^\d+$/;
    if ((!reg.test(num) || num <= 0) && num.lastIndexOf(".") == -1 && !(num =="")) {
        top.layer.alert('请输入大于0的数字', {
            icon: 2
        });
        $(obj).val("");
        return false;
    }
    if (obj.name == "perPrice") {
        var quantity = $(obj).parent().next().children('input').val();
        $(obj).parent().next().next().children('input').val((num * quantity).toFixed(2));
    } else {
        var perPrice = $(obj).parent().prev().children('input').val();
        $(obj).parent().next().children('input').val((num * perPrice).toFixed(2));
    }
    var sumPrice = 0;
    // 所有行金额总和，并填充到合计上
    $('input[name="totalPrice"]').each(function () {
        sumPrice += parseFloat($(this).val());
    });
    //console.log("sumPrice:" + sumPrice);
    $("#sumPrice").text(sumPrice.toFixed(2));

    // 将数字金额变成中文大写金额
    $("#CN").text(numToCN(sumPrice))
}

$(function () {
    init();
    var td = $("td[attr = '1']");
    /* tdAttr.css("background-color","yellow"); */
    if (salesize < 9) {
        td.attr("rowspan", "7");
    } else {
        td.attr("rowspan", salesize - 2);
    }

    $("#addDetail").click(function () {
        // * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
        var $tr = $("#tablelist tr").eq(0);
        detailNum++;
        var trStr = '<tr attr="detail" style="height: 27px;" index="'
            + detailNum
            + '">'
            + '<td><input type="checkbox" name="cbk"/></td>'
            + '<td style="height: 26px"><input class="detail" name="productName"/></td>'
            + '<td style="height: 26px"><input class="detail" name="size"/></td>'
            + '<td style="height: 26px"><input class="detail" name="unit"/></td>'
            + '<td style="height: 26px"><input class="detail" name="perPrice"  oninput="totalPrice(this);" onblur=""/></td>'
            + '<td style="height: 26px"><input class="detail" name="quantity" oninput="totalPrice(this);"/></td>'
            + '<td style="height: 26px"><input class="detail" name="totalPrice" disabled = "disabled" value="0.00"/></td>'
            + '<td style="border-right: 1px solid black;"><input class="detail" name="remark"/></td>'
            + '</tr>';
        $tr.after(trStr);
    });

    $("#delDetail").click(function () {
        delTr('cbk');
    });

    // 保存
    $("#bt_save").click(function () {
        var detail = getData();
        if (!detail) {
            return;
        }
        // console.log(JSON.stringify(detail));
        var pratent_index = parent.layer.getFrameIndex(window.name); //获取父窗口索引
        // console.log(pratent_index)
        top.layer.open({
            title: "信息",
            icon: 3,
            area: ['300px', '150px'],
            btn: ['确定', '取消'],
            content: "确定保存销售单吗？",
            shift: 1,
            closeBtn: 0,
            yes: function (index) {
                // console.log(index)
                top.layer.close(index);
                $("body").addLoading({
                    msg: "正在保存销售单，请稍后...."
                });
                $.ajax({
                    type: 'POST',
                    url: "/sale/addSale",
                    data: JSON.stringify(detail),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data) {
                        $("body").removeLoading();
                        parent.layer.close(pratent_index);
                        if (data.status == 200) {
                            top.layer.alert(data.msg,{
                                icon:1,
                                closeBtn:0
                            });
                        } else {
                            top.layer.alert(data.msg,{
                                icon:5,
                                closeBtn:0
                            });
                        }

                    }
                });
            },
            cancel: function (index) {

            }
        });
    });

    //返回
    $("#return").click(function () {
        top.layer.close(topIndex);   //关闭弹窗
    });

})