<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="main.Global" />
<jsp:useBean id="linkDao" class="main.dao.LinkDAO" />
<% linkDao.setNewConnection(); %>

<t:narrow_layout>
	<c:if test="${empty user}">
		<c:redirect url="/login" />
	</c:if>

	<div class="container">
		<h2>Olá, ${user.firstName}!</h2>
		<h4>Estes são os seus links:</h4>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>URL curta</th>
					<th>URL longa</th>
					<th>Clicks</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="lista" value="${linkDao.getLinksByUser(user)}" />
				<c:forEach var="link" items="${lista}">
				<tr>
					<td><a href="${link.statisticsUrl}" target="_blank">${link.shortUrl}</a></td>
					<td><a href="${link.longUrl}" target="_blank">${link.longUrl}</a></td>
					<td>${link.clicks}</td>
					<td>
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</t:narrow_layout>