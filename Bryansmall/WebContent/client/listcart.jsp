<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车列表页面</title>
<style type="text/css">
	td {
		margin: 0px;
		padding: 0px;
		text-align: center;
	}
</style>
<script type="text/javascript">
	function deleteitem(id) {
		var b = window.confirm("您确认删除吗？");
		if (b) {
			//window代表当前浏览器窗口，location代表当前浏览器窗口的地址栏
			window.location.href = "${pageContext.request.contextPath }/DeleteItemServlet?id=" + id;
		}
	}
	
	function clearcart() {
		var b = window.confirm("您确认清空吗？");
		if (b) {
			window.location.href = "${pageContext.request.contextPath }/ClearCartServlet";
		}
	}
	
	function changeQuantity(input, id, oldValue) {
		var quantity = input.value;//得到要修改的数量
		
		//检查用户输入的数量是不是一个正整数
		if (quantity < 0 || quantity != parseInt(quantity)) {
			alert("请输入一个正整数");
			input.value = oldValue;
			return;
		}
		
		var b = window.confirm("您确认把书的数量修改为" + quantity + "吗？");
		if (b) {
			window.location.href = "${pageContext.request.contextPath }/ChangeQuantityServlet?id=" + id + "&quantity=" + quantity;
		}
	}
</script>
</head>
<body style="text-align: center;">
	<%@include file="/client/head.jsp"%>
	<br />
	<br />
	<table border="1px" cellpadding="0" cellspacing="0" width="90%" align="center">
		<caption>
			<h2>购物车页面</h2>
		</caption>
		<tr>
			<td>商品</td>
			<td>品牌</td>
			<td>售价</td>
			<td>数量</td>
			<td>小计</td>
			<td>操作</td>
		</tr>
		<c:forEach var="entry" items="${cart.map}">
			<tr>
				<td>${entry.value.good.name }</td>
				<td>${entry.value.good.brand }</td>
				<td>${entry.value.good.price }</td>
				<td>
				<input type="text" name="quantity" value="${entry.value.quantity }" style="width: 25px" onchange="changeQuantity(this, ${entry.key }, ${entry.value.quantity })" /> 
				</td>
				<td>${entry.value.price }元</td>
				<td>
				<a href="javascript:void(0)" onclick="deleteitem(${entry.key})">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2">合计</td>
			<td colspan="2">${cart.price }元</td>
			<td colspan="2">
					<a href="javascript:void(0)" onclick="clearcart()">清空购物车</a>
			</td>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath }/client/OrderServlet">生成订单</a>
</body>
</html>