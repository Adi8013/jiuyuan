$(document).ready(function() {
	$("#username").focus();
	$("#changImg").click(function() {
		var $img = $(this).prev().children()[0];
		$img.src ="/defaultCaptcha?"+Math.random();
	});
	$('#login').click(function(e) {
		submitform();
	});
});

function getVerify(obj) {
	obj.src ="/defaultCaptcha?"+Math.random();
}

function refreshCaptcha() {
	var date = new Date();
	var captcha = document.getElementById("img_captcha");
	captcha.src = "captchaCode?t=" + date.getTime();
}


//回车登录
$(document).keydown(function(e){
	if(e.keyCode == 13) {
		submitform();
	}
});

function submitform(){
	$("#err").text("");
	if($("#username").val() == ""){
		$("#err").text("请输入用户名！");
		return false;
	}
	if($("#password").val() == ""){
		$("#err").text("请输入密码！");
		return false;
	}
	if($("#captcha").val() == ""){
		$("#err").text("请输入验证码！");
		return false;
	}
	
	
	var actionurl = $('form').attr('action');
	var checkurl = $('form').attr('check');
	var username = "username="+$("#username").val();
	var password = "password="+$("#password").val();
	var captcha = "captcha="+$("#captcha").val();
	var formData = username+"&"+password+"&"+captcha;
	
	loading('登陆中..', 1);
	setTimeout(function () { 
		unloading();
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			url : checkurl,// 请求的action路径
			data : formData,
			error : function() {// 请求失败处理函数
			},
			success : function(d) {
				if (d.success) {
					window.location.href = actionurl;
				}else{
					$("#err").text(d.msg);
				}
			}
		});
    }, 1000);
	
}

//加载信息
function loading(name, overlay) {
	$('section').append('<div id="overlay"></div><div id="preloader">' + name + '</div>');
	if (overlay == 1) {
		$('#overlay').css('opacity', 0.1).fadeIn(function() {
			$('#preloader').fadeIn();
		});
		return false;
	}
	$('#preloader').fadeIn();
}

function unloading() {
	$('#preloader').fadeOut('fast', function() {
		$('#overlay').fadeOut();
	});
}
