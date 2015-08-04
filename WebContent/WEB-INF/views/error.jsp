<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />
<t:narrow_layout title="Ops...">
	<div class="alert alert-danger" role="alert">
		<strong>Ops...</strong> Tem alguma coisa errada aqui...
	</div>
</t:narrow_layout>