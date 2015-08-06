<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />

<t:narrow_layout>
	<link href="assets/css/cards.css" rel="stylesheet">

	<c:if test="${empty user}">
		<c:redirect url="/login" />
	</c:if>

	<div class="container">
		<h2>Olá, ${user.firstName}!</h2>

		<c:set var="lista" value="${linkDAO.getLinksByUser(user)}" />
		<c:choose>
			<c:when test="${empty lista}">
				<h3>Você ainda não encurtou nenhuma URL!</h3>
			</c:when>
			<c:otherwise>
				<h4>Estes são os seus links:</h4>
				<div class="row">
					<c:set var="lista" value="${linkDAO.getLinksByUser(user)}" />
					<c:forEach var="link" items="${lista}">
						<div class="col-lg-4 col-md-6">
							<div class="card">
								<div class="card-image">
									<img class="img-responsive"
										src="http://free.pagepeeker.com/v2/thumbs.php?size=x&url=${link.longUrl}">
								</div>

								<div class="card-content">
									<p class="truncate-text">
										<a href="${link.shortUrl}">${link.title}</a>
										<br><br>
										Clicks: ${clickDAO.countClicks(link)}
									</p>
								</div>

								<div class="card-action">
									<a href="${link.shortUrl}" class="text-default pull-left">
										<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
									</a>
									<a href="${link.statisticsUrl}" class="text-default">
										<span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
									</a>
									<a onclick="removeLink(${link.id})" class="text-danger">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>

	</div>

	<script>
		function removeLink(id) {
			if (confirm("Você deseja mesmo excluir esta URL?")) {
				$.ajax('/removeLink', {
					method : 'POST',
					dataType : 'json',
					data : {
						id : id
					}
				}).done(function(data) {
					if (data.error) {
						alert(data.msg);
					} else {
						location.reload();
					}
				});
			}
		}
	</script>
</t:narrow_layout>