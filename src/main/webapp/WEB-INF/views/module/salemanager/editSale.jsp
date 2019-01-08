<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<%--var saleDetails = ${saleDetails};--%>
	<%--var sale = "${sale}";--%>
</script>
<script type="text/javascript" src= "${path}/module/salemanager/editSale.js"></script>
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
#delDetail,#addDetail:hover{
	cursor:pointer;
}
.detail {
	height: 25px;
	border: 0px;
	padding: 0px;
	margin: 0px;
}

.tddetail {
	width: 131px;
	height: 26px;
	border: 0px;
	padding: 0px;
	margin: 0px;
}
</style>
</head>
<body class="view_body" style="background-color: white;">
	<div id="tabs" class="easyui-tabs  clearfloat">
		<div title="基本信息" id="EditPanel">
			<div class="EditPanel">
				<div class="editTips">(以下用<span class="notnullTip" style="margin-left:3px;">*</span>标记为必填信息)</div>
				<div class="editItem">
					<div class="editlogo"></div>
					<div class="editTitle" style="width: 70px;">基本信息</div>
					<hr class="editline" />
				</div>
				<table cellspacing="1" cellpadding="0" style="border-spacing: 8px;">
					<tr>
						<td class="View-Title1">收货单位<span class="notnullTip">*</span></td>
						<td class="Edit-Input1"><span><input id="id_receiver" value="${sale.receiver}"/></span></td>
						<td class="View-Title1">No.</td>
						<td class="View-value1"
							style="font-size: 16px; padding-left: 0px;">
							<c:if test="${sale != null}"><span id = 'saleNo'>${sale.saleNo}</span><span><input type="hidden" value="${sale.pk}" id='salePK'></span></c:if>
							<c:if test="${sale == null}"><span>系统自动生成</span></c:if>
						</td>
					</tr>
					<tr>
						<td class="View-Title1">地 址<span class="notnullTip">*</span></td>
						<td class="Edit-Input1"><span><input id="id_address" value="${sale.address}"/></span></td>
					</tr>
				</table>
			</div>
			<div>
				<div class="editItem">
					<div class="editlogo"></div>
					<div class="editTitle" style="width: 70px;">
						明细
						<!-- <a href="#" iconCls="icon-add" onclick="addDetail()" style="width: 70px; color: #72D872;">添加</a>
						<a href="#" iconCls="icon-remove" onclick="remove()" style="width: 70px; color: red;">删除</a>  -->
						<span id="addDetail">
							<span class="easyui-linkbutton" iconCls="icon-add" style="width: 70px; color: #72D872;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<span style="width: 70px; color: #72D872;">添加</span>
						</span>
						<span id="delDetail">
							<span class="easyui-linkbutton" iconCls="icon-remove" style="width: 70px; color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<span style="width: 70px; color: red;" >删除</span>
						</span>
					</div>
					<hr class="editline" />
				</div>
				<div class="biao">
					<table id="tableList" cellspacing="0" cellpadding="0" >
						<tr>
							<td width="27px"></td>
							<td>产品名称</td>
							<td>型号规格</td>
							<td>单位</td>
							<td>单价</td>
							<td>数量</td>
							<td>金额</td>
							<td style="border-right: 1px solid black;">备注</td>
						</tr>
						<c:if test="${saleDetails != null}">
							<c:forEach var="sales" items="${saleDetails}" varStatus="status">
								<tr attr="detail" style="height: 27px;">
									<td><input type="checkbox" name="cbk"/></td>
									<td style="height: 26px"><input class="detail" name="productName" value="${sales.productName}"/></td>
									<td style="height: 26px"><input class="detail" name="size"  value="${sales.size}"/></td>
									<td style="height: 26px"><input class="detail" name="unit"  value="${sales.unit}"/></td>
									<td style="height: 26px"><input class="detail" name="perPrice"  value="${sales.perPrice}" oninput="totalPrice(this);" onblur=""/></td>
									<td style="height: 26px"><input class="detail" name="quantity"  value="${sales.quantity}" oninput="totalPrice(this);"/></td>
									<td style="height: 26px"><input class="detail" name="totalPrice" disabled = "disabled" value="${sales.quantity * sales.perPrice}"/></td>
									<td style="border-right: 1px solid black;"><input class="detail" name="remark" value="${sales.remark}"/></td>
								</tr>
							</c:forEach>
						</c:if>

						<tr>
							<td colspan="5" >合计</td>
							<td></td>
							<td id="sumPrice">${sale.totalPrice}</td>
							<td id="operator" style="text-align: left; border-right: 1px solid black;" ></td><!-- 制单员 -->
						</tr>
						<tr>
							<td colspan="7" style="text-align: left; border-bottom: 1px solid black;" >大写人民币： <span id="CN">${sale.rmbCH}</span></td><!-- 大写人民币： -->
							<td style="text-align: left; border-bottom: 1px solid black; border-right: 1px solid black;">客户签收：</td>
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
	<div class="Editinput">
			<input type="button" id="bt_save" class="bt_ensure" value="保存"></input>
			<input id="return" type="button" class="bt_cancel" value="返回"></input>
		</div>
</body>
</html>

