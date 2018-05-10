<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%> 
<%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="core/easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="core/easyui-1.4.1/themes/icon.css">
<script type="text/javascript" src="core/jquery-1.8.3.js"></script>
<script type="text/javascript" src="core/easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="core/easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="core/easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<!--  
<script type="text/javascript" src="js/system/forbiddenutil.js"></script>
-->
