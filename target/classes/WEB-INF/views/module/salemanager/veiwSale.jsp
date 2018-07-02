<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<%@include file="/WEB-INF/views/base/base.jsp"%>
<script type="text/javascript"
	src="${path}/module/salemanager/veiwSale.js"></script>
<style type="text/css">
.View-Title1{
	font-size:17px;
}
.View-value1{
	font-size:12px;
}
#tableList{
	width:100%;
	/* border:1px solid black; */ 
}
#tableList tr td{
	border:1px solid black; 
}
</style>
</head>
<body class="view_body" style="background-color: white;">
	<div id="tabs" class="easyui-tabs  clearfloat">
		<div title="基本信息" id="EditPanel">
			<div class="EditPanel">
				<div class="editItem">
					<div class="editlogo"></div>
					<div class="editTitle" style="width: 70px;">基本信息</div>
					<hr class="editline" />
				</div>
				<table cellspacing="1" cellpadding="0" style="border-spacing: 8px;">
					<tr>
						<td class="View-Title1">收货单位：</td>
						<td class="View-value1"><span>${sale.receiver}</span></td>
						<td class="View-Title1">No.</td>
						<td class="View-value1" style="font-size:16px;padding-left:0px;"><span>${sale.saleNo}</span></td>
					</tr>
					<tr>
						<td class="View-Title1">地 址：</td>
						<td class="View-value1"><span>${sale.address}</span></td>
						<td class="View-Title1" colspan="2" style="text-align:left;padding-left:88px;">${sale.createDate}</td>
					</tr>
				</table>
			</div>
			<div class="EditPanel">
				<div class="editItem">
					<div class="editlogo"></div>
					<div class="editTitle" style="width: 70px;">明细</div>
					<hr class="editline" />
				</div>
				<div class="biao">
					<table id="tableList" cellspacing="0" cellpadding="0">
						<tr>
							<td>产品名称</td>
							<td>型号规格</td>
							<td>单位</td>
							<td>数量</td>
							<td>单价</td>
							<td>金额</td>
							<td>备注</td>
							<td>专营</td>
						</tr>
						<c:forEach items="${saleDetails}" var="sales" varStatus="i">
						${saleDetails.size()}
							<tr>
								<td attr="${fn:length(items)}">${sales.productName}</td>
								<td>${sales.size}</td>
								<td>${sales.unit}</td>
								<td>${sales.quantity}</td>
								<td>${sales.perPrice}</td>
								<td>${sales.quantity*sales.perPrice}</td>
								<td>${sales.remark}</td>
								<td></td>
							</tr>
							<%-- <c:if test="${i.count < saleDetails.size() }">
							</c:if> --%>
						</c:forEach>
						<tr>
							<td colspan="4">合计</td>
							<td></td>
							<td>${sale.totalPrice}</td>
							<td colspan="2" style="text-align: left;">制单员：${sale.operator}</td>
						</tr>
						<tr>
							<td colspan="6" style="text-align: left;">大写人民币：${sale.rmbCH}</td>
							<td colspan="2" style="text-align: left;">客户签收：</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>