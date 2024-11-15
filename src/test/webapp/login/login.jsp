<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>
<!DOCTYPE html>
<html dir='<u:language-direction language="${lang}"/>'>
<head>
<meta charset="UTF-8">
<title><fmt:message key="${R.strings.LOGIN}" /></title>
<link rel="stylesheet" href='<f:path context="${R.css.ALL_MIN}"/>'>
<link rel="stylesheet" href='<f:path context="${R.css.LOGIN}"/>'>
<script type="text/javascript" src='<f:path context="${R.js.TOOLTIP}"/>'></script>
</head>
<body>

	<div class="${R.html_classes.CONTAINER}">
		<div class="${R.html_classes.CONTAINER_CENTER}">
			<h1>
				<fmt:message key="${R.strings.LOGIN}" />
			</h1>
			<div class="${R.html_classes.WARNING_MESSAGES_CONTAINER}">
				<c:if test="${not empty requestScope[R.keys.WARNING_MESSAGES]}">
					<ul>
						<c:forEach items="${requestScope[R.keys.WARNING_MESSAGES]}"
							var="msg">
							<li>
								<fmt:message key="${msg}" />
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<form action='<f:path context="${R.servlets.LOGIN}"/>' method="post">
				<div class="${R.html_classes.INPUT_CONTAINER}">
					<div class="${R.html_classes.INPUT_FIELD_CONTAINER}">
						<input dir="ltr" type="text" name="${R.html_names.USERNAME}"
							id="${R.html_ids.USERNAME}"
							value="${requestScope[R.keys.USER].username}" required="required">
						<span></span>
						<label>
							<fmt:message key="${R.strings.USERNAME}" />
						</label>
					</div>
				</div>
				<div class="${R.html_classes.INPUT_CONTAINER}">
					<div class="${R.html_classes.INPUT_FIELD_CONTAINER}">
						<input dir="ltr" type="password" name="${R.html_names.PASSWORD}"
							id="${R.html_ids.PASSWORD}"
							value="" required="required">
						<span></span>
						<label>
							<fmt:message key="${R.strings.PASSWORD}" />
						</label>
					</div>
					<div class="${R.html_classes.ICONS_CONTAINER}">
						<div class="${R.html_classes.ICON_CONTAINER}">
							<span>
								<i class="fas fa-eye"
									onclick="${R.js_functions.TOGGLE_PASSWORD_VISIBILITY}"></i>
							</span>
						</div>
					</div>
				</div>
				<div>
					<input type="checkbox" name="${R.html_names.REMEMBER_ME}" id="checkbox-id"
						class="${R.html_classes.REMEMBER_ME}">
					<label for="checkbox-id">
						<fmt:message key="${R.strings.REMEMBER_ME}" />
					</label>
				</div>
				<input type="submit" name="${R.html_names.SUBMIT}"
					value='<fmt:message key="${R.strings.LOGIN}"/>'>
				<div class="${R.html_classes.LOGIN_AND_REGISTER}">
					<fmt:message key="${R.strings.NOT_MEMBER}" />
					<a href='<f:path context="${R.jsps.REGISTER}"/>'>
						<fmt:message key="${R.strings.REGISTER}" />
					</a>
				</div>
				
				<div class="${R.html_classes.LOGIN_AND_REGISTER}">					
					<a href='<f:path context="${R.jsps.ACCOUNT_HELP}"/>'>
						<fmt:message key="${R.strings.NEED_ACCOUNT_HELP}" />
					</a>
				</div>
			</form>
		</div>
	</div>
	<div></div>
</body>
</html>