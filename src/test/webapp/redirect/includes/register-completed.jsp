<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons.jsp"%>
<!DOCTYPE html>
<html>
<body>

	<fmt:message key="${R.strings.REGISTER_COMPLETED}" /> 
	<font color="blue">${requestScope[R.keys.USER].email}</font>
</body>
</html>