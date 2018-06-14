<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<%@include file="/WEB-INF/views/base/base.jsp"%>
<script type="text/javascript" src= "${path}/module/salemanager/veiwSale.js"></script>
</head>
<body class="view_body" style="background-color:white;" >
		<div id="tabs" class="easyui-tabs  clearfloat" >
			<div title="基本信息" id="EditPanel">
    			<div class="EditPanel">
    				<div class="editItem">
						<div class="editlogo"></div>
						<div class="editTitle" style="width: 70px;">总信息部分</div>
						<hr class="editline" />
					</div>
    				<table cellspacing="1" cellpadding="0" style="border-spacing: 8px;">
    					<c:forEach items="${saleDetails}" var="saleDetails" varStatus="i">
    						<tr>
							<td class="View-Title1">${saleDetails.saleNo}</td><td class="View-value1"><span id="id_projectCode"></span></td>
							<td class="View-Title1">${saleDetails.productName}</td><td class="View-value1"><span id="id_projectName"></span></td>
						</tr>
    					</c:forEach>
						<tr>
							<td class="View-Title1">项目类型</td><td class="View-value1"><span id="id_projectType"></span></td>
							<td class="View-Title1">项目负责人</td><td class="View-value1"><span id="id_projectCharge"></span></td>
						</tr>
						<tr>
							<td class="View-Title1">项目金额</td><td class="View-value1"><span id="id_projectAmount"></span></td>
							<td class="View-Title1">签订时间</td><td class="View-value1"><span id="id_signDate"></span></td>
						</tr>
    				</table>
    			</div>
    			<div class="EditPanel">
    				<div class="editItem">
						<div class="editlogo"></div>
						<div class="editTitle" style="width: 70px;">明细信息部分</div>
						<hr class="editline" />
					</div>
					<div class="biao">
						<table id="tableList"></table>
					</div>
    			</div>
    		</div>	
		</div>
 	</body>
</html>