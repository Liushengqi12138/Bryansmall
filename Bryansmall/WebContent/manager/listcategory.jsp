<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示分类列表的页面</title>
</head>
<body style="text-align: center">
	<br />
	<br />
	<table border="1px" cellpadding="0" cellspacing="0" width="100%" style="text-align: center">
		<caption>
			<h2>商品分类信息</h2>
		</caption>
		<tr>
			<td>分类名称</td>
			<td>分类描述</td>
			<td>操作</td>
		</tr>

		<c:forEach var="category" items="${categories}">
			<tr>
				<td>${category.name }</td>
				<td>${category.description }</td>
				<td>
				<a href="${pageContext.request.contextPath }/manager/CategoryServlet?method=change&id=${category.id }">修改</a> 
				<a href="${pageContext.request.contextPath }/manager/CategoryServlet?method=dele&id=${category.id }">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>