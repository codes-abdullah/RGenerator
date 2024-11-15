<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" uri="/WEB-INF/utils.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/file-utils.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="lang"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale.language}"
	scope="session"></c:set>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="${R.i18ns.STRINGS}" scope="session" />
<%-- <script type="text/javascript" src='<f:path context="${R.js.RJS}"/>'></script> --%>
<%-- <script type="text/javascript" src='<f:path context="${R.js.UTILS}"/>'></script> --%>
<%-- <link  rel="stylesheet" href='<f:path context="${R.css.STYLE }"/>' > --%>
</head>
</html>