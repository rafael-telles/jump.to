<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="global" class="main.Global" />

<t:narrow_layout>
	<c:if test="${not empty user}">
		<c:redirect url="/dashboard"/>
	</c:if>

	<div class="jumbotron">
		<h1>Bem-vindo!</h1>
		<p class="lead">${global.siteName} é um simples encurtador de URLs.</p>
		<a class="btn btn-success btn-lg" href="/login">Login</a>
	</div>
</t:narrow_layout>