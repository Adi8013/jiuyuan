<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<%@include file="/WEB-INF/views/base/base.jsp"%>
<script type="text/javascript">
	var salesize = "${saleDetails.size()}";
</script>
<script type="text/javascript" src= "${path}/module/salemanager/veiwSale.js"></script>
<style type="text/css">
.View-Title1 {
	font-size: 17px;
}

.View-value1 {
	font-size: 12px;
}

#tableList {
	width: 100%;
	/* border:1px solid black; */
}

#tableList tr td {
	border: 1px solid black;
	border-bottom: none;
	border-right: none;
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
					<%--<tr ><td style="position: fixed; margin-left: 870px; margin-top: -78px;"><button onclick="exportExcel();">导出Excel</button></td></tr>--%>
					<tr >
						<td style="position: fixed; margin-left: 870px; margin-top: -78px;">
							<a href="/sale/exportExcel/${sale.pk}/${sale.saleNo}" download="jiuyuan-${sale.saleNo}">导出Excel</a>
						</td>
					</tr>
					<tr>
						<td class="View-Title1">收货单位：</td>
						<td class="View-value1"><span>${sale.receiver}</span></td>
						<td class="View-Title1">No.</td>
						<td class="View-value1"
							style="font-size: 16px; padding-left: 0px;"><span id="saleNo">${sale.saleNo}</span></td>
						<%--<td class="View-value1"--%>
							<%--style="font-size: 16px;"><button>导出Excel</button></td>--%>
					</tr>
					<tr>
						<td class="View-Title1">地 址：</td>
						<td class="View-value1"><span>${sale.address}</span></td>
						<td class="View-Title1" colspan="2"
							style="text-align: left; padding-left: 88px;">${sale.createDate}</td>
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
							<td style="border-right: 1px solid black;">专营</td>
						</tr>
						<c:forEach items="${saleDetails}" var="sales" varStatus="status">
							<tr>
								<td>${sales.productName}</td>
								<td>${sales.size}</td>
								<td>${sales.unit}</td>
								<td>${sales.quantity}</td>
								<td>${sales.perPrice}</td>
								<td>${sales.quantity*sales.perPrice}</td>
								<td>${sales.remark}</td>
								<c:if test="${status.count == 1}">
									<td width="121px" attr = "${status.count}" style="border-right: 1px solid black;" >
										九元牌高端高温琉璃瓦、仿古瓦、青灰瓦、各式花窗、多种规格大青砖、收工青砖片、等园林建筑产品，欢迎来电咨询
									</td>
								</c:if>
								<c:if test="${saleDetails.size() >= 9}">
									<c:if test="${saleDetails.size()-1 == status.count}">
										<td width="121px" rowspan="2" attr="${status.count + saleDetails.size()}" style="border-right: 1px solid black;">
											商品请当面验收非质量问题恕不退换
										</td>
									</c:if>
								</c:if>
								<c:if test="${saleDetails.size() == 8}">
									<c:if test="${saleDetails.size() == status.count}">
										<td width="121px" rowspan="2" attr="${status.count + saleDetails.size()}" style="border-right: 1px solid black;">
											商品请当面验收非质量问题恕不退换
										</td>
									</c:if>
								</c:if>
								<%-- <td attr="i.count" rowspan="<c:if test='${9 > saleDetails.size() && i.count == 1}'>10</c:if>"></td> --%>
							</tr>
							<%-- <c:if test="${i.count < saleDetails.size() }">
							</c:if> --%>
						</c:forEach>
						<c:forEach begin="${saleDetails.size() + 1}" end="9" step="1" varStatus="status">
							<c:if test="${saleDetails.size() < 9}">
								<c:if test="${status.count + saleDetails.size() != 8}">
									<tr style="height: 27px;">
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<!-- <td style="border-right: 1px solid black;"></td> -->
									</tr>
								</c:if>
								<c:if test="${status.count + saleDetails.size() == 8}">
									<tr style="height: 27px;">
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td ></td>
										<td width="121px" rowspan="2" attr="${status.count + saleDetails.size()}" style="border-right: 1px solid black;">
											商品请当面验收非质量问题恕不退换
										</td>
									</tr>
								</c:if>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="4">合计</td>
							<td></td>
							<td>${sale.totalPrice}</td>
							<td colspan="2" style="text-align: left; border-right: 1px solid black;" >制单员：${sale.operator}</td>
						</tr>
						<tr>
							<td colspan="6" style="text-align: left; border-bottom: 1px solid black;">大写人民币：${sale.rmbCH}</td>
							<td colspan="2" style="text-align: left; border-bottom: 1px solid black; border-right: 1px solid black;">客户签收：</td>
						</tr>
					</table>
				</div>
				<!-- <table>
					<tr>
						<td>厂址：</td>
						<td class="View-value1"><span>佛山市三水芦苞工业区</span></td>
						<td>服务热线：</td>
						<td class="View-value1"><span>400-850-6773</span></td>
					</tr>
					<tr>
						<td >深圳办：</td>
						<td class="View-value1"><span>深圳市龙华新区民治街道骏景华庭B113-114号</span></td>
						<td>服务热线：</td>
						<td class="View-value1"><span>0755-81756662</span></td>
					</tr>
				</table> -->
			</div>
		</div>
	</div>
</body>
</html>

