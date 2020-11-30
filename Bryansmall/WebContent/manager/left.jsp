<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>后台管理页面左侧导航页面</title>
    
    <style type="text/css">
      .dc { 
      		display: none; 
      		margin-left: 10px;
      	  }
	</style>
	
	<script language="javascript">
	      function test(e) {
	    	  	var div = document.getElementById(e);
	            div.style.display = div.style.display == 'block' ? 'none' : 'block' ;       
	      }
	</script>
</head>
<body>
 <ul>
    	<li>
    		<a href="#" onclick="test('div1')">分类管理
    			<div class="dc" id="div1">
	    			<a href="${pageContext.request.contextPath}/manager/addcategory.jsp"  target="right">添加分类</a><br/>
	    			<a href="${pageContext.request.contextPath}/manager/CategoryServlet?method=getAll"  target="right">查看分类</a><br/>
	    		</div>
    		</a>
    	</li>
    	
    	<br/><br/>
    	
    	<li>
    		<a href="#" onclick="test('div2')">商品管理
    			<div class="dc" id="div2">
    				<a href="${pageContext.request.contextPath}/manager/GoodServlet?method=forAddUI"  target="right">添加商品</a><br/>
    				<a href="${pageContext.request.contextPath}/manager/GoodServlet?method=list"  target="right">查看商品</a>
	    		</div>
    		</a>
    	</li>
    	
    	<br/><br/>
    	
    	<li>
    		<a href="#" onclick="test('div3')">订单管理
	    		<div class="dc" id="div3">
	    			<a href="${pageContext.request.contextPath }/manager/OrderServlet1?method=getAll&status=false"  target="right">待处理订单</a><br/>
	    			<a href="${pageContext.request.contextPath }/manager/OrderServlet1?method=getAll&status=true"  target="right">已发货订单</a><br/>
	    		</div>
    		</a>
    	</li>
    	
	    		
    	
    	<li>
    		<a href="${pageContext.request.contextPath }/index.jsp" target="_blank">返回首页
    		<div class="dc" id="div4">
    		</div>
    		</a>
    	</li>
    	
    </ul>
</body>
</html>