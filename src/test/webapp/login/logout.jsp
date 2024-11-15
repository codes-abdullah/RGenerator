<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html>
<c:set var="lang"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale.language}"
	scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="${R.i18ns.STRINGS}" />
<html dir='<u:language-direction language="${lang}"/>'>
<head>
<meta charset="UTF-8">
<title><fmt:message key="${R.strings.LOGIN}" /></title>
</head>
<body>
	<p>
		<fmt:message key="${R.strings.LOGOUT}" />
	</p>	
	<fmt:message key="${R.strings.CONFIRM_LOGOUT}" />
	<a href='<f:path context="${R.servlets.LOGOUT}"/>'>
		<fmt:message key="${R.strings.LOGOUT}" />
	</a>
	---------
	<a href='<f:path context="${R.jsps.INDEX}"/>'>
		<fmt:message key="${R.strings.CANCEL}" />
	</a>
</body>
</html>