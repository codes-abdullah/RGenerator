<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="f" uri="../WEB-INF/file-utils.tld"%>
<%@ taglib prefix="u" uri="../WEB-INF/utils.tld"%>
<c:set var="lang"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale.language}"></c:set>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="${R.i18ns.STRINGS}" />
<!DOCTYPE html>
<html dir='<u:language-direction language="${lang}"/>'>
<head>
<script type="text/javascript" src='<f:path context="${R.js.RJS}"/>'></script>
<script type="text/javascript" src='<f:path context="${R.js.UTILS}"/>'></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<select name="${R.html_names.LANG}" onchange="submit()">
			<c:forEach var="l" items="${R.arrays.SUPPORTED_LANGUAGES}">
				<option value="${l}" ${lang==l?'selected' : '' }><fmt:message
						key="${l}" />
				</option>
			</c:forEach>
		</select>
	</form>

	<%-- <jsp:useBean id="dbd" class="codes.abdullah.web.admin.DatabaseDetails" /> --%>
	${dbd.fillForDerbyTest()}
	<%-- ${dbd.testOpenConnection()}   --%>



	<f:write var="dbd" path="${R.paths.DBD}" />
	<f:read as="serializable" var="dbd" path="${R.paths.DBD}" />
	<f:read as="sql" var="select_all_users"
		path="${R.sqls.USERS_SELECT_ALL}" type="resource" />
	<sql:setDataSource var="db" driver="${dbd.driver}" url="${dbd.url}"
		user="${dbd.username}" password="${dbd.password}" />
	<sql:query dataSource="${db}" var="rs">
		${select_all_users}	
	</sql:query>
	<c:set var="rows"
		value="${not empty param.rows ? param.rows : R.consts.DEFAULT_DISPLAY_ROWS_PER_PAGE}" />
	<c:set var="last_page_rows" value="${Math.floor(rs.rowCount % rows)}" />
	<c:set var="total_pages"
		value="${Math.floor(rs.rowCount / rows) + (last_page_rows==0 ? 0:1)}" />
	<fmt:formatNumber var="total_pages" minFractionDigits="0"
		maxFractionDigits="0" value="${total_pages}" />
	<c:set var="page"
		value="${Math.min(total_pages, Math.max(param.page, 1))}" />
	<form action='<f:path context="${R.jsps.SHOW_ALL_USERS}" />'>
		<fmt:message key="${R.strings.DISPLAY_ROWS_PER_PAGE}" />
		<input id="${R.html_names.DISPLAY_ROWS_PER_PAGE}"
			name="${R.html_names.DISPLAY_ROWS_PER_PAGE}" type="text"
			value='${rows}'>
		<input type="submit" value="refresh">
		<input type="hidden" name="page" value="${page}" />
	</form>

	<c:set var="end" value="${page * rows}" />
	<c:set var="begin" value="${end - rows}" />
	<table border="1" width="100%">
		<tr>
			<td>
				<fmt:message>${R.strings.ID}</fmt:message>
			</td>
			<td>
				<fmt:message>${R.strings.USERNAME}</fmt:message>
			</td>
			<td>
				<fmt:message>${R.strings.EMAIL}</fmt:message>
			</td>
			<td>
				<fmt:message>${R.strings.PASSWORD}</fmt:message>
			</td>
			<td>
				<fmt:message>${R.strings.ADMIN}</fmt:message>
			</td>
			<td>
				<fmt:message>${R.strings.EDIT}</fmt:message>
			</td>
		</tr>



		<c:forEach items="${rs.rows}" var="row" begin="${begin}"
			end="${end-1}">
			<tr id="${row.id}">
				<td id="${R.html_ids.ID}">${row[R.html_ids.ID]}</td>
				<td id="${R.html_ids.USERNAME}">${row.username}</td>
				<td id="${R.html_ids.EMAIL}">${row.email}</td>
				<td id="${R.html_ids.PASSWORD}">${row.password }</td>
				<td id="${R.html_ids.ADMIN}">${row.admin}</td>
				<td>
					<input type="button" onclick="editUserByRowId(${row.id})"
						value='<fmt:message>${R.strings.EDIT}</fmt:message>'>
				</td>
			</tr>
		</c:forEach>
	</table>
	<f:path var="pagination_href"
		context="${R.jsps.SHOW_ALL_USERS}?page=%d&rows=${rows}" />
	<u:pagination href="${pagination_href}" value="%03d"
		currentPage="${page}" totalPages="${total_pages}" totalLinks="7"
		first="[<<]" previous="[<]" next="[>]" last="[>>]" />
</body>
</html>