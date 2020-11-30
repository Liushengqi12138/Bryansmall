<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Bryansmall</h1>
<br/>
<br/>

<div>
	<div style="margin-left: 43%; float: left">
		<a href="${pageContext.request.contextPath }/index.jsp">首页</a> 
		<a href="${pageContext.request.contextPath }/client/listcart.jsp">查看购物车</a>
		<a href="${pageContext.request.contextPath }/client/ListOrderServlet?method=getAll&name=${user.username }">查看自己的订单</a>
		<a href="${pageContext.request.contextPath }/client/GotomanagerServlet">后台管理</a>
	</div>
	<br/>
	<div style="float: right;">
		<c:if test="${user == null }">
			<h2>欢迎光临，记得先登录后购物哦↓</h2>
			<form action="/client/LoginServlet" method="post">
				用户名：<input type="text" name="username" style="width: 50px">
				密码：<input type="password" name="password" style="width: 50px">
				<input type="submit" value="登录"> 
				<input type="button" value="注册" onclick="javascript:window.location.href='/Bryansmall/client/register.jsp'">
			</form>
		</c:if>
		<c:if test="${user != null }">
			<form action="/client/LogoutServlet" method="post">
				欢迎您，${user.username }
				<input type="submit" value="注销"> 
			</form>
		</c:if>
	</div>
	<div style="clear: both"></div>
</div>
<hr>
