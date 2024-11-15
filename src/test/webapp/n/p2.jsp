<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="/WEB-INF/utils.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/file-utils.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<f:css path="${R.css.XSTYLE}" />
</head>
<body>
	<jsp:include page="./xbutton.jsp" />
	<u:css-block path="${R.css.XSTYLE}">
		<u:css-class name="${R.html_classes.BUTTON}" />
		<jsp:include page="./xbutton.jsp" />
	</u:css-block>
	<jsp:include page="./xbutton.jsp" />
</body>
</html>