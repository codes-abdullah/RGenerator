<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="/header/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error occurred</title>
</head>
<body>
	<c:choose>
		<c:when test="${pageContext.errorData.statusCode==500}">
			<font color="red"><c:out
					value="Message: ${pageContext.errorData.throwable.message}" /> </font>
			<br>
			<font color="red"><c:out
					value="Caused by: ${pageContext.errorData.throwable.cause.message}" /><br>
			</font>
			<font color="red"><c:out
					value="Internal error detected: ${pageContext.errorData.throwable}"></c:out><br>
				<a href="index.jsp">Go Home</a> <br> <c:forEach
					items="${pageContext.errorData.throwable.stackTrace}" var="item">
					<c:out value="${item}" />
					<br>
				</c:forEach> </font>
			<br>
			<font color="red"><c:out
					value="Cause: ${pageContext.errorData.throwable.cause}"></c:out><br>
				<c:forEach
					items="${pageContext.errorData.throwable.cause.stackTrace}"
					var="item">
					<c:out value="${item}" />
					<br>
				</c:forEach> </font>
		</c:when>
		<c:when test="${pageContext.errorData.statusCode==404}">
			<c:out
				value="Requested page is not available: ${pageContext.errorData.requestURI}" />
			<br>
			<c:out value="Message: ${pageContext.errorData.throwable.message }"></c:out>
			<br>
			<a href="index.jsp">Go Home</a>
		</c:when>
		<c:otherwise>
			<c:out value="nothing to show here"></c:out>
		</c:otherwise>
	</c:choose>
</body>
</html>
