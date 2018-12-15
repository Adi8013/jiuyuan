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
			icon : 2,
			closeBtn : 0
		});
		return;
	}
	ckbs.each(function() {
		$(this).parent().parent().remove();
	});
}

$(function() {
	init();
	var td = $("td[attr = '1']");
	/* tdAttr.css("background-color","yellow"); */
	if (salesize < 9) {
		td.attr("rowspan", "7");
	} else {
		td.attr("rowspan", salesize - 2);
	}

	$("#addDetail").click(function() {
						// * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
						var $tr = $("#tablelist tr").eq(0);
						detailNum++;
						var trStr = '<tr style="height: 27px;" index="'
								+ detailNum
								+ '">'
								+ '<td><input type="checkbox" name="cbk"/></td>'
								+ '<td><input class="detail"/></td>'
								+ '<td><input class="detail"/></td>'
								+ '<td><input class="detail"/></td>'
								+ '<td><input class="detail"/></td>'
								+ '<td><input class="detail"/></td>'
								+ '<td><input class="detail"/></td>'
								+ '<td style="border-right: 1px solid black;"><input class="detail"/></td>'
								+ '</tr>';
						$tr.after(trStr);
					});
	$("#delDetail").click(function() {
		delTr('cbk');
	});

	//返回
	$("#return").click(function() {
		top.layer.close(topIndex);   //关闭弹窗
	});
	
})