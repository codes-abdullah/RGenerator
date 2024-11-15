<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" uri="/WEB-INF/utils.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/file-utils.tld"%>
<c:set var="lang"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale.language}" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="${R.i18ns.STRINGS}" />
<!DOCTYPE html>
<html dir='<u:language-direction language="${lang}"/>'>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src='<f:path context="${R.js.RJS }"/>'></script>
<script type="text/javascript" src='<f:path context="${R.js.UTILS }"/>'></script>
<title><fmt:message key="${R.strings.EDIT_USERS}" /></title>
</head>
<body>
	Editing
	<form>
		<select name="${R.html_names.LANG}" onchange="submit()">
			<c:forEach items="${R.arrays.SUPPORTED_LANGUAGES}" var="l">
				<option value="${l}" ${lang==l ? 'selected' : ''}>
					<fmt:message key="${l}"></fmt:message>
				</option>
			</c:forEach>
		</select>
		<input name="${R.html_names.ID}" type="hidden" value="${param.id}">
		<input name="${R.html_names.USERNAME}" type="hidden"
			value="${param.username}">
		<input name="${R.html_names.EMAIL}" type="hidden"
			value="${param.email}">
		<input name="${R.html_names.PASSWORD}" type="hidden"
			value="${param.password}">
		<input name="${R.html_names.ADMIN}" type="hidden"
			${param.admin ? 'checked' : ''}>
	</form>
	<form>
		<table border="1" width="100%">
			<tr>
				<td>
					<fmt:message>${R.strings.ID}</fmt:message>
				<td>
					<fmt:message>${R.strings.USERNAME}</fmt:message>
				<td>
					<fmt:message>${R.strings.EMAIL}</fmt:message>
				<td>
					<fmt:message>${R.strings.PASSWORD}</fmt:message>
				<td>
					<fmt:message>${R.strings.ADMIN}</fmt:message>
				<td>
					<fmt:message>${R.strings.SUBMIT}</fmt:message>
			</tr>
			<tr>
				<td>
					<input name="${R.html_names.ID}" type="hidden" value="${param.id}">
					${param.id}
				</td>
				<td>
					<input name="${R.html_names.USERNAME}" type="text"
						value="${param.username}">
				</td>
				<td>
					<input name="${R.html_names.EMAIL}" type="text"
						value="${param.email}">
				</td>
				<td>
					<input name="${R.html_names.PASSWORD}" type="text"
						value="${param.password}">
				</td>
				<td>
					<input name="${R.html_names.ADMIN}" type="checkbox"
						${param.admin ? 'checked' : ''}>
				</td>
				<td>
					<input type="submit"
						value='<fmt:message key="${R.strings.SUBMIT}"/>'>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>