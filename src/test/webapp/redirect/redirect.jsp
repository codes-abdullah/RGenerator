<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons.jsp"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html dir='<u:language-direction language="${lang}"/>'>
<head>
<meta charset="UTF-8">
<c:set var="url"
	value="${not empty requestScope[R.keys.URL] ? requestScope[R.keys.URL] : R.jsps.INDEX}"
	scope="request" />
<meta http-equiv="refresh"
	content='${requestScope[R.keys.REDIRECT_AFTER]}, url=<f:path context="${url}"/>'>
<title><fmt:message key="${requestScope[R.keys.TITLE]}" /></title>
</head>
<body>
	<c:if test="${not empty requestScope[R.keys.MESSAGE]}">
		<font color="blue">
			<fmt:message key="${requestScope[R.keys.MESSAGE]}" />
		</font>
		<br>
	</c:if>
	<c:if test="${not empty requestScope[R.keys.WARNING_MESSAGE]}">
		<font color="red">
			<fmt:message key="${requestScope[R.keys.WARNING_MESSAGE]}" />
		</font>
		<br>
	</c:if>
	<c:if test="${not empty requestScope[R.keys.WARNING_MESSAGES]}">
		<c:forEach items="${requestScope[R.keys.WARNING_MESSAGES]}" var="msg">
			<font color="red">
				<fmt:message key="${msg}" />
			</font>
			<br>
		</c:forEach>
	</c:if>
	<c:if test="${not empty requestScope[R.keys.INCLUDE_PAGE]}">
		<jsp:include page="${requestScope[R.keys.INCLUDE_PAGE]}" />
		<br>
	</c:if>
	<c:if test="${not empty requestScope[R.keys.URL]}">
		<br>
		<a href='<f:path context="${requestScope[R.keys.URL]}"/>'>
			<fmt:message key="${R.strings.CLICK_TO_REDIRECT}" />
		</a>
	</c:if>
</body>
</html>