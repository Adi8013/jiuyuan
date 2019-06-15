/*function exportExcel() {
	var title = "九元古建瓦砖销售单";
	var uri = 'data:text/xls;charset=utf-8,\ufeff,' + encodeURIComponent(title);
	// 通过创建a标签实现
	var link = document.createElement('a')
	link.href = uri;
	// 对下载文件命名
	link.download = "jiuyuan-" + $('#saleNo').text()+".xls";
	document.body.appendChild(link);
	link.click();
	document.body.removeChild(link);

}*/
$(function() {
	var td = $("td[attr = '1']");
	/*tdAttr.css("background-color","yellow");*/
	if (salesize < 9) {
		td.attr("rowspan","7");
	} else {
		td.attr("rowspan",salesize - 2);
	}
})