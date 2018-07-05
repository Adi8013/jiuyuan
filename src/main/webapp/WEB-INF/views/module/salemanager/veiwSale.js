
function view2() {
	alert(111);
}

$(function() {
	var td = $("td[attr = '1']");
	/*tdAttr.css("background-color","yellow");*/
	if (salesize < 9) {
		td.attr("rowspan","7");
	} else {
		td.attr("rowspan",salesize - 2);
	}
})