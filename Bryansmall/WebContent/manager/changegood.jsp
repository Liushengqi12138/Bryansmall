<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品页面</title>
</head>
<body>
	<br/>
	<br/>
	<form
		action="${pageContext.request.contextPath }/manager/GoodServlet?method=changec"
		method="post" enctype="multipart/form-data">
		<table width="500px">
			<tr>
				<td>商品</td>
				<td><input type="text" name="name" style="width: 200px"></td>
			<tr>
			<tr>
				<td>品牌</td>
				<td><input type="text" name="brand" style="width: 200px"></td>
			</tr>
			<tr>
				<td>售价</td>
				<td><input type="text" name="price" style="width: 200px"></td>
			</tr>
			<tr>
				<td>图片</td>
				<td><input type="file" name="image" style="width: 200px"></td>
			</tr>
			<tr>
				<td>描述</td>
				<td><textarea rows="4" cols="40" name="description"></textarea></td>
			</tr>
			<tr>
				<td>所属分类</td>
				<td>
					<select name="category_id">
						<c:forEach var="category" items="${categories}">
							<option value="${category.id }">${category.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="修改商品"></td>
			</tr>
		</table>
	</form>
</body>
</html>