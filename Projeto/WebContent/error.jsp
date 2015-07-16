<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="main.Global" />
${expection.printStackTrace() }
<t:narrow_layout title="Ops...">
	<div class="alert alert-danger" role="alert">
		<strong>Ops...</strong> Tem alguma coisa errada aqui... <br>
		A culpa é desse cara: ${exception.toString()}
	</div>
</t:narrow_layout>