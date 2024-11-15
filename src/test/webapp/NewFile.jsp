<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href=""></link>
<body>
	<fmt:message var="TITLE" key="${R.strings.APPNAME}"/>
	<c:set var="ICON"/>
	<c:set var="CONTENT">
		<a href="www.goog.com">Home</a>
		<a href="#">About</a>
		<a href="#">Shop</a>
	</c:set>
	<%@ include file="/widgets/dropdown-menu/dropdown-menu.jsp"%>
</body>
</html>