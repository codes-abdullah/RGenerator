<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>
<!DOCTYPE html>
<html dir='<u:language-direction language="${lang}"/>'>
<head>
<meta charset="UTF-8">
<title><fmt:message key="${R.strings.ACCOUNT_HELP}" /></title>
<link rel="stylesheet" href='<f:path context="${R.css.ALL_MIN}"/>'>
<script type="text/javascript" src='<f:path context="${R.js.CAPTCHA}"/>'></script>
<link rel="stylesheet" href='<f:path context="${R.css.LOGIN}"/>'>
</head>
<body>
	<div class="${R.html_classes.CONTAINER}">
		<div class="${R.html_classes.CONTAINER_CENTER}">
			<h1>
				<fmt:message key="${R.strings.ACCOUNT_HELP}" />
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
			<div
				class="${R.html_classes.ACCOUNT_HELP_EMAIL_WILL_BE_SENT_CONTAINER}">
				<label>
					<fmt:message key="${R.strings.ACCOUNT_HELP_EMAIL_WILL_BE_SENT}" />
				</label>
			</div>
			<form action='<f:path context="${R.servlets.ACCOUNT_HELP}"/>'
				method="post">
				<div class="${R.html_classes.INPUT_CONTAINER}">
					<div class="${R.html_classes.INPUT_FIELD_CONTAINER}">
						<input dir="ltr" type="email" name="${R.html_names.EMAIL}"
							id="${R.html_ids.EMAIL}" value="" required="required">
						<span></span>
						<label>
							<fmt:message key="${R.strings.EMAIL}" />
						</label>
					</div>
				</div>
				<div class="${R.html_names.CAPTCHA_CONTAINER}" dir="ltr">
					<div class="${R.html_classes.ICON_CONTAINER}">
						<span>
							<i class="fas fa-refresh"
								onclick="${R.js_functions.GENERATE_CAPTCHA}"></i>
						</span>
					</div>
					<div class="${R.html_classes.OUTPUT_FIELD_CONTAINER}" dir="ltr">
						<input type="text" name="${R.html_names.CAPTCHA}"
							id="${R.html_ids.CAPTCHA}" class="${R.html_classes.CAPTCHA}"
							tabindex="-1" readonly="readonly">
						<label>CAPTCHA</label>
					</div>
				</div>
				<div class="${R.html_classes.INPUT_CONTAINER}">
					<div class="${R.html_classes.INPUT_FIELD_CONTAINER}">
						<input dir="ltr" type="text" id="${R.html_ids.CAPTCHA_INPUT}"
							name="${R.html_names.CAPTCHA_INPUT}" required="required">
						<span></span>
						<label>
							<fmt:message key="${R.strings.ENTER_CAPTCHA}" />
						</label>
					</div>
				</div>
				<input type="submit" name="${R.html_names.SUBMIT}"
					value='<fmt:message key="${R.strings.SUBMIT}"/>'>
				<div class="${R.html_classes.LOGIN_AND_REGISTER}">
					<fmt:message key="${R.strings.NOT_MEMBER}" />
					<a href='<f:path context="${R.jsps.REGISTER}"/>'>
						<fmt:message key="${R.strings.REGISTER}" />
					</a>
				</div>
				<div class="${R.html_classes.LOGIN_AND_REGISTER}">
					<fmt:message key="${R.strings.HAVE_ACCOUNT}" />
					<a href='<f:path context="${R.jsps.LOGIN}"/>'>
						<fmt:message key="${R.strings.LOGIN}" />
					</a>
				</div>
			</form>
		</div>
	</div>
	<div></div>
</body>
</html>