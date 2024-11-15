<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>
<!DOCTYPE html>
<html dir='<u:language-direction language="${lang}"/>'>
<head>
<meta charset="UTF-8">
<title><fmt:message key="${R.strings.REGISTER}" /></title>
<link rel="stylesheet" href='<f:path context="${R.css.ALL_MIN}"/>'>
<link rel="stylesheet" href='<f:path context="${R.css.REGISTER}"/>'>
<script type="text/javascript"
	src='<f:path context="${R.js.CAPTCHA}"/>'></script>
<script type="text/javascript" src='<f:path context="${R.js.TOOLTIP}"/>'></script>
</head>
<body>
	<div class="${R.html_classes.CONTAINER}">
		<div class="${R.html_classes.CONTAINER_CENTER}">
			<h1>
				<fmt:message key="${R.strings.REGISTER}" />
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
			<form action='<f:path context="${R.servlets.REGISTER}"/>'
				method="post">
				<div class="${R.html_classes.INPUT_CONTAINER}">
					<div class="${R.html_classes.INPUT_FIELD_CONTAINER}">
						<input pattern="${R.consts.USERNAME_REGEX_PATTERN}" dir="ltr"
							type="text" name="${R.html_names.USERNAME}"
							id="${R.html_ids.USERNAME}"
							value="${requestScope[R.keys.USER].username}" required="required">
						<span></span>
						<label>
							<fmt:message key="${R.strings.USERNAME}" />
						</label>
					</div>
					<div class="${R.html_classes.ICONS_CONTAINER}">
						<div class="${R.html_classes.ICON_CONTAINER }">
							<div
								onmouseenter="${R.js_functions.ON_TOOLTIP_CONTAINER_MOUSE_ENTER}">
								<i class="fas fa-info-circle"></i>
								<div class="${R.html_classes.TOOLTIP}">
									<fmt:message key="${R.strings.USERNAME_RULES}" var="rules" />
									<ul>
										<c:forEach var="rule" items="${fn:split(rules, '|')}">
											<li>${rule}</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="${R.html_classes.INPUT_CONTAINER}">
					<div class="${R.html_classes.INPUT_FIELD_CONTAINER}">
						<input pattern="${R.consts.EMAIL_REGEX_PATTERN}" dir="ltr"
							type="email" name="${R.html_names.EMAIL}"
							value="${requestScope[R.keys.USER].email}"
							id="${R.html_ids.EMAIL}" required="required">
						<span></span>
						<label>
							<fmt:message key="${R.strings.EMAIL}" />
						</label>
					</div>
					<div class="${R.html_classes.ICONS_CONTAINER}">
						<div class="${R.html_classes.ICON_CONTAINER }">
							<div
								onmouseenter="${R.js_functions.ON_TOOLTIP_CONTAINER_MOUSE_ENTER}">
								<i class="fas fa-info-circle"></i>
								<div class="${R.html_classes.TOOLTIP}">
									<fmt:message key="${R.strings.EMAIL_RULES}" var="rules" />
									<ul>
										<c:forEach var="rule" items="${fn:split(rules, '|')}">
											<li>${rule}</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="${R.html_classes.INPUT_CONTAINER}">
					<div class="${R.html_classes.INPUT_FIELD_CONTAINER}">
						<input pattern="${R.consts.PASSWORD_REGEX_PATTERN}" dir="ltr"
							type="password" name="${R.html_names.PASSWORD}"
							id="${R.html_ids.PASSWORD}"
							value="${requestScope[R.keys.USER].password}" required="required">
						<span></span>
						<label>
							<fmt:message key="${R.strings.PASSWORD}" />
						</label>
					</div>
					<div class="${R.html_classes.ICONS_CONTAINER}">
						<div class="${R.html_classes.ICON_CONTAINER }">
							<div
								onmouseenter="${R.js_functions.ON_TOOLTIP_CONTAINER_MOUSE_ENTER}">
								<i class="fas fa-info-circle"></i>
								<div class="${R.html_classes.TOOLTIP}">
									<fmt:message key="${R.strings.PASSWORD_RULES}" var="rules" />
									<ul>
										<c:forEach var="rule" items="${fn:split(rules, '|')}">
											<li>${rule}</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
						<div class="${R.html_classes.ICON_CONTAINER}">
							<span>
								<i class="fas fa-eye"
									onclick="${R.js_functions.TOGGLE_PASSWORD_VISIBILITY}"></i>
							</span>
						</div>
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
					value='<fmt:message key="${R.strings.REGISTER}"/>'>
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