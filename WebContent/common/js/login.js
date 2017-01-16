$(function(){

	//<!-- 登录界面 -->
	$('#dd').dialog({    
		title: '百望云平台监控登录系统',    
		width: 400,    
		height: 200,  
		closed: false,    //定义是否可以关闭窗口。
		cache: false,    
		modal: true   
	});    

	//<!-- 验证用户名与密码 -->
	$('#manager').validatebox({    
		required: true,    
		validType: ['manager','length[3,6]'],
		missingMessage:"请输入用户名",
		invalidMessage:"用户名长度为3-6位"  ,
		deltaX:10
	});

	$('#password').validatebox({    
		required: true,    
		validType: ['manager','length[6,9]'],
		missingMessage:"请输入用户密码",
		invalidMessage:"用户密码长度为6-9位"
	});

	
});