官网:http://layer.layui.com/
官方api:http://layer.layui.com/api.html#layer.config

说明：为了适合系统使用，已经对原有默认样式进行了部分修改。

/////////////// 常用说明  ////////////////////
场景1：退出系统，有两个按钮，确定和取消
其中有几个属性需要特殊说明:
icon:用于设置问号，感叹号等文字前的图标。其中：1：√,2:感叹号,3:问号,4:锁,5:哭脸,6√,7:感叹号
btn:按钮，可有多个，第一个按钮的回调函数为yes(需手动关闭),第二个为cancel,第三个为btn3:，以此类推。
area:数组，宽和高,注意使用数字，官网的字符串格式在firefox下有问题
top.layer.open({
	title:'提示',
	icon: 3,
	area:[250,150],
	btn:['确定', '取消'],
	content:'你确定要退出系统吗？',
	shift:6,
	closeBtn :2,
	yes: function(index){
  	Ajax.service(
		'idUserLogin', 
		'logout',  
	    [_usercode],
	    function(data){
	    	window.location.href="/gdczsam/index.jsp";
	    }
	    );        
	    //一般设定yes回调，必须进行手工关闭
	    top.layer.close(index);
    }
});


场景2：列表页面弹出编辑或查看页面
说明:由于直接在列表页面调用layer.open的方法会造成弹出窗口只能在列表页面拖动，这里更改为在列表页面写好设置set，交由mainframe.jsp中的方法弹出.
注意：1、需要指定弹出窗宽和高，不要使用自动。会有bug。
	 2、若需要打开多个窗口，只需要加多个设置shade: false,
关闭弹出窗口方法：
var index = top.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
top.layer.close(index); //再执行关闭 
	 
a、单个窗口，有遮罩层
top.layer.open({
			type:2,
			title:'账号关联',
			shift:1,
			closeBtn :2,
			area:[800,600],
			content:'/jiuyuan/sys/user.jsp?userid='+
		    		userid+"&userConnection="+userConnection
});
 
b、查看页面，可弹出多个窗口，无遮罩层.ie8下有个bug，多个窗口时，无法点击标题栏就将窗口置顶
top.layer.open({
			type:2,
			title:'账号关联',
			shift:1,
			closeBtn :2,
			area:[800,600],
			shade:false,
			zIndex:layer.zIndex, 
    		success:function(layero){
        		top.layer.setTop(layero); 
    		},
			content:'/user.jsp?userid='+
		    		userid+"&userConnection="+userConnection
});


 