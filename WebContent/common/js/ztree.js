$(document).ready(function(){ 
	alert("11");
		loadTree();
	}); 

var setting = {
		async: {
			enable: false
		},
		check: {
			enable: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		view: {
			expandSpeed: ""
		},
		callback: {
			//onClick: onClick,//单击事件
			onDblClick:onClick//双击事件
		}
};

function loadTree(){
	$.ajax({  
	    url : "<%=request.getContextPath()%>/menu/findList.action",
	    type : "post",  
	    dataType : "json",  
	    success : function(data) {  
	        $.fn.zTree.init($("#treeDemo"), setting, data);//初始化树节点时，添加同步获取的数据  
	        var zTree = $.fn.zTree.getZTreeObj("treeDemo");//获取zTree对象  
	        var node = zTree.getNodeByParam("id", 1, null);//获取要展开的节点，这里以节点“1”为例  
	        zTree.selectNode(node); //选中节点  
	    }  
	});  
}
 
function onClick(event, treeId, treeNode, clickFlag) {
	 
}