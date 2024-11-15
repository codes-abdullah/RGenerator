<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>
<!DOCTYPE html>
<html dir='<u:language-direction language="${lang}"/>'>
<head>
<meta charset="UTF-8">
<title><fmt:message key="${R.strings.ACCOUNT_HELP}" /></title>
</head>
<body>
	
	<c:if test="${not empty requestScope[R.keys.WARNING_MESSAGES]}">
		<c:forEach items="${requestScope[R.keys.WARNING_MESSAGES]}" var="msg">
			<font color="red"> <fmt:message key='${msg}' />
			</font>
		</c:forEach>
	</c:if>
	<form action='<f:path context="${R.servlets.ACCOUNT_HELP}"/>' method="post">
		<table>
			<tr>
				<td><fmt:message key="${R.strings.EMAIL}" /></td>
				<td><input type="email" name="${R.html_names.EMAIL}"
					value="${requestScope[R.keys.USER].email}"></td>
			</tr>
			<tr>
				<td><input type="submit"
					value="<fmt:message key="${R.strings.RESET}"/>"></td>
			</tr>
		</table>
	</form>
</body>
</html>