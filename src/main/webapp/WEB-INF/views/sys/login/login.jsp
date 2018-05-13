<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<script src="core/jquery-1.8.3.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript" src="core/load.js"></script>
<script src="sys/login/login.js" type="text/javascript" charset="UTF-8"></script>
<link rel="stylesheet" type="text/css" href="sys/login/login.css">
</head>
<body>
	<div id="sky"></div>
	<div id="head"></div>
	<section>
	<div id="middle">
		<form action="index" method="post" check="doCheckLogin" onsubmit="return submitform();">
			<ul style="text-align: center;">
				<li style="font-size: 48px">九元建材管理系统</li>

				<li><input class="name" name="username" id= "userAccount" placeholder="请输入帐号"></li>
				<li><input type="password" name="password" class="password" id="password"
					placeholder="请输入密码"></li>
				<li id="verifyCode" style="display: block;">
					<input id="valid" name="code" placeholder="请输入验证码" maxlength="4"> 
					<span id="validcode"> 
						<img id="verifyCodePic" id="img_captcha" title="点击更换" src="/defaultCaptcha" onclick="getVerify(this);">
					</span> 
					<span id="changImg"> 换一张 </span>
				</li>
				<li><button id="login">立即登录</button></li>
				<li><span id="err" style="display: inline-block;"></span></li>
			</ul>
		</form>
	</div>
	</section>
	<div id="footer">

		<a href="http://wpa.qq.com/msgrd?v=3&uin=659282955&site=qq&menu=yes"
			target="_blank">关于我们@qq659282955 &nbsp &nbsp|&nbsp &nbsp</a> <a>Copyright
			© 2018 AdiZheng 保留所有权利 粤ICP备120号</a>
	</div>
	<div id="cloud"></div>





</body>
</html>