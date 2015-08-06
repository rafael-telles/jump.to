<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />

<t:narrow_layout title="Resultados de busca">
	<link href="assets/css/cards.css" rel="stylesheet">

	<div class="container">
		<h2>Resultados da busca:</h2>

		<c:choose>
			<c:when test="${empty results}">
				<h3>Nada!</h3>
			</c:when>
			<c:otherwise>
				<div class="row">
					<c:forEach var="link" items="${results}">
						<div class="col-lg-4 col-md-6">
							<div class="card">
								<div class="card-image">
									<img class="img-responsive"
										src="http://free.pagepeeker.com/v2/thumbs.php?size=x&url=${link.longUrl}">
								</div>

								<div class="card-content">
									<p class="truncate-text">
										<a href="${link.shortUrl}">${link.title}</a>
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>

	</div>
</t:narrow_layout>