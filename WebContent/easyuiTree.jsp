<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<%@ include file="/WEB-INF/BWJKSystem/common/common_css.jsp"%>
	<%@ include file="/WEB-INF/BWJKSystem/common/common_js.jsp"%>
</head>
<body>
<script type="text/javascript">
    
   $(function(){ 
     InitTreeData();
   });
   
    function InitTreeData(){
      $('#tree').tree({
          url:"<%=request.getContentType()%>/menu/findList.action",
          checkbox:true,
          onClick:function(node){
            alert(node.text);
          },
          onContextMenu:function(e,node){  
                        e.preventDefault();  
                        $('#tree').tree('select', node.target);  
                        $('#mm').menu('show', {  
                            left: e.pageX,  
                            top: e.pageY  
                        });  
                    }  
        });
     }

</script>


   <ul id="tree">
    </ul>
    <div id="mm" class="easyui-menu" style="width: 120px;">
        <div onclick="append()" iconcls="icon-add">
            添加节点</div>
        <div onclick="remove()" iconcls="icon-remove">
            删除节点</div>
        
        <div onclick="update()" iconcls="icon-edit">修改节点</div>
    </div>
</body>
</html>