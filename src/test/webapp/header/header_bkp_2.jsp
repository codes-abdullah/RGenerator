<f:css path="${R.css.HEADER}" />

<header>
	<div class="${R.html_classes.HEADER_CONTAINER}">
		<a href='<f:path context="${R.jsps.INDEX}"/>'>
			<img class="logo" alt='<fmt:message key="${R.strings.INDEX}"/>'
				src='<f:path context="${R.imgs.LOGO}"/>'>
		</a>
		<div class="${R.html_classes.DROPDOWN_MENUS_GROUP}">			
			<c:set var="ICON">
				<i class="${R.html_classes.DROPDOWN_MENU_ICON} fas fa-info-circle"></i>
			</c:set>
			<u:set var="TITLE" value="${R.strings.APPNAME}" />
			<c:set var="CONTENT">
				<a href="#">${R.strings.INDEX }</a>
				<a href="#">${R.strings.INSTALL }</a>
			</c:set>
			<%@ include file="/widgets/dropdown-menus/dropdown-menu.jsp"%>
			<c:set var="ICON">
				<i class="${R.html_classes.DROPDOWN_MENU_ICON} fas fa-eye"></i>
			</c:set>
			<u:set var="TITLE" value="${R.strings.ABOUT_US}" />
			<c:set var="CONTENT">
				<a href="#">${R.strings.EDIT}</a>
				<a href="#">${R.strings.ADMIN_EMAIL }</a>
			</c:set>
			<%@ include file="/widgets/dropdown-menus/dropdown-menu.jsp"%>
		</div>
		<div class="${R.html_classes.BLANK_SPACE}"></div>
		<div class="${R.html_classes.SEARCH_BAR_CONTAINER }">			
			<c:set var="TITLE">
				<i class="fas fa-search"></i>
			</c:set>
			<f:path context="${R.jsps.INDEX}" var="LINK" />
			<%@ include file="/widgets/search-bars/search-bar.jsp"%>
		</div>
		<div class="${R.html_classes.BUTTONS_GROUP}">
			<u:set var="TITLE" value="${R.strings.LOGIN}" />
			<f:path context="${R.jsps.LOGIN}" var="LINK" />
			<%@ include file="/widgets/buttons/button.jsp"%>
			<f:path context="${R.jsps.REGISTER}" var="LINK" />
			<u:set var="TITLE" value="${R.strings.REGISTER}" />
			<%@ include file="/widgets/buttons/button.jsp"%>
		</div>
	</div>
	<div class="${R.html_classes.HEADER_SEPARATOR}"></div>
</header>
