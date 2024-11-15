<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div>
		<a href='<f:path context="${R.jsps.INDEX}"/>'>LOGO</a>
		<a href='<f:path context="${R.jsps.INDEX}"/>'>
			<fmt:message key="${R.strings.INDEX}" />
		</a>
		<a href='<f:path context="${R.jsps.CONTACT_US}"/>'>
			<fmt:message key="${R.strings.CONTACT_US}" />
		</a>
		<a href='<f:path context="${R.jsps.ABOUT_US}"/>'>
			<fmt:message key="${R.strings.ABOUT_US}" />
		</a>
		<c:choose>
			<c:when test="${empty sessionScope[R.keys.USER]}">
				<a href='<f:path context="${R.jsps.LOGIN}"/>'>
					<fmt:message key="${R.strings.LOGIN}" />
				</a>
				<a href='<f:path context="${R.jsps.REGISTER}"/>'>
					<fmt:message key="${R.strings.REGISTER}" />
				</a>
			</c:when>
			<c:otherwise>
				<fmt:message key="${R.strings.WELCOME_BACK}" /> ${sessionScope[R.keys.USER].username }
			</c:otherwise>
		</c:choose>
		<form>
			<select name="${R.html_names.LANG}" onchange="submit()">
				<c:forEach items="${R.arrays.SUPPORTED_LANGUAGES}" var="l">
					<option value="${l}" ${lang==l ? 'selected' : ''}>
						<fmt:message key="${l}"></fmt:message>
					</option>
				</c:forEach>
			</select>
		</form>
	</div>
</body>
</html>