<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单明细页面</title>
</head>
<body>
	<table border="1px" cellpadding="0" cellspacing="0" width="100%" style="text-align: center">
		<h3>订单明细</h3>
		<tr>
			<td>商品</td>
			<td>售价</td>
			<td>数量</td>
			<td>货款</td>
		</tr>
		<c:forEach var="orderitem" items="${order.orderitems }">
			<tr>
				<td>${orderitem.good.name }</td>
				<td>${orderitem.good.price }</td>
				<td>${orderitem.quantity }</td>
				<td>${orderitem.price }元</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2">总计应收货款</td>
			<td colspan="2">${order.price }元</td>
		</tr>
	</table>
	<br />
	<br />
	<table border="1px" cellpadding="0" cellspacing="0" width="90%">
		<h3>收货人详细地址</h3>
		<tr>
			<td>用户</td>
			<td>电话</td>
			<td>手机</td>
			<td>地址</td>
			<td>邮箱</td>
		</tr>
		<tr>
			<td>${order.user.username }</td>
			<td>${order.user.phone }</td>
			<td>${order.user.cellphone }</td>
			<td>${order.user.address }</td>
			<td>${order.user.email }</td>
		</tr>
	</table>
	<br />
	<a href="${pageContext.request.contextPath }/manager/OrderServlet1?method=update&id=${order.id }">确认发货</a>
</body>
</html>