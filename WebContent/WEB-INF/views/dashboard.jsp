<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />

<t:narrow_layout>
	<link href="assets/css/cards.css" rel="stylesheet">

	<c:if test="${empty user}">
		<c:redirect url="/login" />
	</c:if>

	<div class="container">
		<h2>Ol�, ${user.firstName}!</h2>
		
		<div class="container">
			<div class="row">
				<div class="span6">
		            <form action="shorten" method="post">
		                <div class="controls controls-row">
		                    <input id="longUrl" name="longUrl" type="url" class="form-control" placeholder="Uma URL bem grande" required> 
		                    <input id="tags" name="tags" type="text" class="form-control" placeholder="escreva tags para encontrarem seu link! (ex: Universidade, Esporte, Maquinas Agricolas)">
		                </div>
		                <div class="controls">
		                    <textarea id="description" name="description" class="form-control" placeholder="descri��o" rows="5"></textarea>
		                </div>
		                <div class="controls">
		                    <button  type="submit" class="btn btn-primary input-medium pull-right">Encurtar !</button>
		                </div>
		            </form>
		        </div>
			</div>
		</div>
		
		<!-- <form action="shorten" method="post">
		    <div class="input-group">
		      <input id="longUrl" name="longUrl" type="url" class="form-control" placeholder="Uma URL bem grande" required>
		      	<div class="input-group-btn">
                	<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-send"></i></button>
            	</div>
		  	</div>
        </form> -->
        <hr>
		<c:set var="lista" value="${linkDAO.getLinksByUser(user)}" />
		
		<c:choose>
			<c:when test="${empty lista}">
				<div class="alert alert-warning" role="alert">
                	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span> Voc� ainda n�o encurtou nenhuma URL!</span>
               	</div>
			</c:when>
			<c:otherwise>
				<h4>Estes s�o os seus links:</h4>
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
										Clicks: ${link.clicks}
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
			if (confirm("Voc� deseja mesmo excluir esta URL?")) {
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