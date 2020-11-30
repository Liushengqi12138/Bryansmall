<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bryansmall 后台管理页面</title>
</head>
<frameset rows="18%,*">
	<frame src="${pageContext.request.contextPath }/manager/head.jsp" name="head" />
	<frameset cols="15%,*">
		<frame src="${pageContext.request.contextPath }/manager/left.jsp" name="left" />
		<frame src="" name="right" />
	</frameset>
</frameset>
</html>