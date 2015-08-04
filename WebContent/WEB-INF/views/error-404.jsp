<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />

<t:narrow_layout title="404!">
	<div class="text-center">
		<h1>Oops!</h1>
		<p>Está página não existe!</p>
	</div>
</t:narrow_layout>