<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons.jsp" %>
<%@ include file="/header/header.jsp"%>
<!DOCTYPE html>
<html dir="<u:language-direction language="${lang}"/>">
<head>
<title><fmt:message key="${R.strings.APPNAME}" /></title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope[R.keys.USER]}">
			<table>
				<tr>
					<td><fmt:message key="${R.strings.WELCOME_BACK }" />
						${sessionScope[R.keys.USER]}</td>
				</tr>
				<tr>
					<td><a href='<f:path context="${R.jsps.LOGOUT}"/>'> <fmt:message
								key="${R.strings.LOGOUT}" />
					</a></td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<fmt:message key="${R.strings.WELCOME}" />
		</c:otherwise>
	</c:choose>
</body>
</html>