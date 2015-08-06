<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />

<t:narrow_layout title="Resultados de busca">
	<link href="assets/css/search-results.css" rel="stylesheet">

	<div class="container">
		<h2>Resultados da busca:</h2>

		<c:choose>
			<c:when test="${empty results}">
				<h3>Nada!</h3>
			</c:when>
			<c:otherwise>
				<section class="col-xs-12 col-sm-6 col-md-12">
					<c:forEach var="link" items="${results}">
						<article class="search-result row">
							<div class="col-xs-12 col-sm-12 col-md-3">
								<a href="#" title="Lorem ipsum" class="thumbnail"><img
									src="http://free.pagepeeker.com/v2/thumbs.php?size=x&url=${link.longUrl}" alt="${link.title}" /></a>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-2">
								<ul class="meta-search">
									<li><i class="glyphicon glyphicon-calendar"></i> <span>02/15/2014</span></li>
									<li><i class="glyphicon glyphicon-time"></i> <span>4:28
											pm</span></li>
									<li><i class="glyphicon glyphicon-tags"></i> <span>${link.tags}</span></li>
								</ul>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-7 excerpet">
								<h3>
									<a href="${link.shortUrl}" title="${link.title}">${link.title}</a>
								</h3>
								<p>${link.description}</p>
							</div>
							<span class="clearfix borda"></span>
						</article>
					</c:forEach>
				</section>
	</div>
	</c:otherwise>
	</c:choose>

	</div>
</t:narrow_layout>