<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />

<t:narrow_layout>
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
				<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>URL curta</th>
							<th>URL longa</th>
							<th>Clicks</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:set var="lista" value="${linkDAO.getLinksByUser(user)}" />
						<c:forEach var="link" items="${lista}">
						<tr>
							<td>
								<img src="http://free.pagepeeker.com/v2/thumbs.php?size=x&url=${link.longUrl}" width="150">
							</td>
							<td><a href="${link.shortUrl}" target="_blank">${link.shortUrl}</a></td>
							<td><a href="${link.longUrl}" target="_blank">${link.longUrl}</a></td>
							<td>${clickDAO.countClicks(link)}</td>
							<td>
							<div class="btn-group" role="group" aria-label="...">
								<a href="${link.statisticsUrl}" class="btn btn-default btn-xs">
									<span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
								</a>
								<button onclick="removeLink(${link.id})" type="button" class="btn btn-danger btn-xs">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button>
								</div>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
			</c:otherwise>
		</c:choose>
		
	</div>
	
	<script>
	function removeLink(id) {
		if(confirm("Você deseja mesmo excluir esta URL?")) {
			$.ajax('/removeLink', {
				method: 'POST',
				dataType: 'json',
				data: {
					id: id
					}
			}).done(function(data) {
				if(data.error) {
					alert(data.msg);
				} else {
					location.reload();
				}
			});
		}
	}
	</script>
</t:narrow_layout>