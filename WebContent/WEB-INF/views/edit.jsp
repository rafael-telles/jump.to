<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />
<t:narrow_layout>

	<div class="container">

		<c:if
			test="${empty link.userId || empty user || link.userId != user.id}">
			<c:redirect url="/login" />
		</c:if>

		<h3>Edite seu link para que ele seja encontrado por outros usuarios!</h3>
		<p>
			Link: <a href="${link.shortUrl}" target="_blank">${link.title}</a><br>
			<small>${link.longUrl}</small>
		</p>
		<br>

		<div class="row">
			<div class="col-lg-5">
				<a href="${link.shortUrl}" title="${link.title}" class="thumbnail" target="_blank">
					<img class="img-responsive"
						src="http://free.pagepeeker.com/v2/thumbs.php?size=x&url=${link.longUrl}">
				</a>
			</div>
			<div class="col-lg-7">
				<form action="updatelink" method="post">
					<div class="row">
						<div class="form-group">
							<label for="tags" class="control-label">Tags</label> <input
								id="tags" name="tags" type="text" class="form-control"
								value="${link.tags}"
								placeholder="Escreva tags para encontrarem seu link! (ex: Universidade, Esporte, Maquinas Agricolas)">
							<span class="help-block"></span>
						</div>
						<div class="form-group">
							<label for="description" class="control-label">Descrição</label>
							<textarea id="description" name="description"
								class="form-control"
								placeholder="Uma descrição do conteudo que você quer compartilhar"
								rows="5">${link.description}</textarea>
							<span class="help-block"></span>
						</div>
						<input id="id" name="id" type="hidden" value="${link.id}">
						<button type="submit"
							class="btn btn-primary input-medium pull-right">Feito!</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</t:narrow_layout>