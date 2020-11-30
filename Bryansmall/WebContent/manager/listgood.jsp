<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示后台商品列表的页面</title>
</head>
<body style="text-align: center">
	<br />
	<br />
	<table border="1px" cellpadding="0" cellspacing="0" width="100%" style="text-align: center">
		<caption>
			<h2>商品信息</h2>
		</caption>
		<tr>
			<td>商品</td>
			<td>品牌</td>
			<td>售价</td>
			<td>描述</td>
			<td>图片</td>
			<td>操作</td>
		</tr>

		<c:forEach var="good" items="${list}">
			<tr>
				<td>${good.name }</td>
				<td>${good.brand }</td>
				<td>${good.price }</td>
				<td>${good.description }</td>
				<td><a
					href="${pageContext.request.contextPath }/images/${good.image }">查看图片</a></td>
				<td>
				<a href="${pageContext.request.contextPath }/manager/GoodServlet?method=change&id=${good.id }">修改</a> 
				<a href="${pageContext.request.contextPath }/manager/GoodServlet?method=dele&id=${good.id }"> 删除 </a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
</body>
</html>