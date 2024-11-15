<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/header/header.jsp"%>
<!DOCTYPE html>
<html dir='<u:language-direction language="${lang}"/>'>
<head>
<script type="text/javascript" src='<f:path context="${R.js.RJS}" />'></script>
<script type="text/javascript"
	src='<f:path context="${R.js.INSTALL}" />'></script>
<meta charset="UTF-8">
<title><fmt:message key="${R.strings.INSTALL_DATABASE}"/></title>
</head>
<body>
	<form action='<f:path context="${R.servlets.INSTALL}" />' method="post">
		<fieldset>
			<legend>
				<fmt:message key="${R.strings.INSTALL_DATABASE}" />
			</legend>
			<table>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_DRIVER }" />
					</td>
					<td>
						<input dir="ltr" type="text" name="${R.html_names.DB_DRIVER}"
							onkeyup="buildUrl()" value="${R.consts.DEFAULT_DB_DRIVER}">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_DIALECT }" />
					</td>
					<td>
						<input dir="ltr" type="text" name="${R.html_names.DB_DIALECT}"
							onkeyup="buildUrl()" value="${R.consts.DEFAULT_DB_DIALECT}">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_SCHEMA}" />
					</td>
					<td>
						<input dir="ltr" type="text" name="${R.html_names.DB_SCHEMA}"
							value="${R.consts.DEFAULT_DB_SCHEMA}" onkeyup="buildUrl()">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_ARGS}" />
						:
					</td>
					<td>
						<input dir="ltr" type="text" name="${R.html_names.DB_ARGS}"
							value="${R.consts.DEFAULT_DB_ARGS}" onkeyup="buildUrl()">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_HOST}" />
						:
					</td>
					<td>
						<input dir="ltr" type="text" name="${R.html_names.DB_HOST}"
							value="${R.consts.DEFAULT_DB_HOST}" onkeyup="buildUrl()"
							onkeyup="foo()">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_PORT}" />
						:
					</td>
					<td>
						<input dir="ltr" type="text" name="${R.html_names.DB_PORT}"
							value="${R.consts.DEFAULT_DB_PORT}" onkeyup="buildUrl()">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_NAME}" />
						:
					</td>
					<td>
						<input dir="ltr" type="text" name="${R.html_names.DB_NAME}"
							value="mydb" onkeyup="buildUrl()">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_USERNAME}" />
						:
					</td>
					<td>
						<input dir="ltr" type="text" name="${R.html_names.DB_USERNAME}"
							onkeyup="buildUrl()" value="${R.consts.DEFAULT_DB_USERNAME}">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_PASSWORD}" />
						:
					</td>
					<td>
						<input dir="ltr" type="password"
							name="${R.html_names.DB_PASSWORD}" onkeyup="buildUrl()"
							value="${R.consts.DEFAULT_DB_PASSWORD}">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.DB_URL}" />
						:
					</td>
					<td>
						<a dir="ltr" id="${R.html_ids.DB_URL}"></a>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>
				<fmt:message key="${R.strings.ADMIN_REGISTER}" />
			</legend>
			<table>
				<tr>
					<td>
						<fmt:message key="${R.strings.ADMIN_USERNAME}" />
					</td>
					<td>
						<input dir="ltr" type="text" name="${R.html_names.USERNAME}"
							value="abod">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.ADMIN_EMAIL}">
						</fmt:message>
					</td>
					<td>
						<input dir="ltr" type="email" name="${R.html_names.EMAIL}"
							value="abod@gmail.com">
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="${R.strings.ADMIN_PASSWORD}">
						</fmt:message>
					</td>
					<td>
						<input dir="ltr" type="password" name="${R.html_names.PASSWORD}"
							value="1986">
					</td>
				</tr>
			</table>
		</fieldset>
		<br>
		<input type="submit"
			value='<fmt:message key="${R.strings.INSTALL}"/> ' />
	</form>
	<div id="error"></div>
</body>
</html>