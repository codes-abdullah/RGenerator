<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons.jsp"%>
<!DOCTYPE html>
<html>
<body>
	<fmt:message key="${R.strings.WELCOME_BACK}" />
	<font color="blue">${sessionScope[R.keys.USER].username}</font>
</body>
</html>