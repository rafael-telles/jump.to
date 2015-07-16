<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="main.Global" />
<t:narrow_layout>
	<c:if test="${empty user && link.userId == user.id}">
		<c:redirect url="/login" />
	</c:if>

	<div class="container">
		<p>${link.longUrl}</p>
		<p>Clicks: ${link.clicks}</p>
	</div>
</t:narrow_layout>