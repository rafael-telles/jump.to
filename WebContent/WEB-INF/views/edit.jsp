<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />
<t:narrow_layout>

	<div class="container">
	
		<c:if test="${empty link.userId || empty user || link.userId != user.id}">
			<c:redirect url="/login" />
		</c:if>
		<div class="container">
			<div class="row">
				<div class="span6">
		            <form action="updatelink" method="post">
		                <div class="controls controls-row">
		                	<h3>edite seu link "<a href="${link.shortUrl}" target="_blank">${link.title}</a>" 
		                		 para que ele seja encontrado por outros usuarios!</h3>
		                	<br>
		                    <input id="tags" name="tags" type="text" class="form-control" value="${link.tags}" placeholder="escreva tags para encontrarem seu link! (ex: Universidade, Esporte, Maquinas Agricolas)">
		                </div>
		                <div class="controls">
		                    <textarea id="description" name="description" class="form-control" placeholder="descrição" rows="5" value="${link.description}"></textarea>
		                </div>
		                <div class="controls">
		                	<input hidden id="id" name="id" type="text" value="${link.id}">
		                    <button  type="submit" class="btn btn-primary input-medium pull-right">Feito !</button>
		                </div>
		            </form>
		        </div>
			</div>
		</div>
	</div>

</t:narrow_layout>