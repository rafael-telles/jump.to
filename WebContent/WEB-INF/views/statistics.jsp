<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="to.jump.Global" />
<t:narrow_layout>
    <link href="assets/css/share-buttons.css" rel="stylesheet">
    <script src="assets/js/qrcode.min.js"></script>
    
	<div class="container">
	    <c:choose>
			<c:when test="${empty link.userId}">
				<div class="alert alert-warning" role="alert">
					<span>Essa URL curtinha não tem dono, suas estatísticas podem ser visualizadas por qualquer um!</span>
	        	</div>
			</c:when>
			<c:otherwise>
				<c:if test="${empty user || link.userId != user.id}">
					<c:redirect url="/login" />
				</c:if>
			</c:otherwise>
		</c:choose>
	
		<h3><a href="${link.shortUrl}" target="_blank">${link.shortUrl}</a></h3>
		<small>URL original: <a href="${link.longUrl}" target="_blank">${link.longUrl}</a></small>
		
		<img src="http://free.pagepeeker.com/v2/thumbs.php?size=x&url=${link.longUrl}">
		
		<div class="ssb">	
			<!-- Twitter -->
			<a href="#" onclick="window.open('https://twitter.com/intent/tweet?text=${link.shortUrl}&source=webclient', 'Twitter', 'WIDTH=700, HEIGHT=280');" title="Compartilhar no Twitter" target="_blank" class="btn btn-twitter">
			<i class="fa fa-twitter" ></i> Twitter
			</a>
			
			<!-- Facebook -->
			<a href="#" onclick="window.open('https://www.facebook.com/sharer/sharer.php?u=${link.shortUrl}', 'Facebook', 'WIDTH=700, HEIGHT=341');" title="Compartilhar no Facebook" target="_blank" class="btn btn-facebook">
				<i class="fa fa-facebook"></i> Facebook
			</a>
			
			<!-- Google+ -->
			<a href="#" onclick="window.open('https://plus.google.com/share?url=${link.shortUrl}', 'Google+', 'WIDTH = 500, HEIGHT = 450');" title="Compartilhar no Google+" target="_blank" class="btn btn-googleplus">
				<i class="fa fa-google-plus"></i> Google+
			</a>
		</div>
		
		
		<p>Clicks até agora: ${clickDAO.countClicks(link)}</p>
		
		<div id="qrcode"></div>
		
		<button style="margin-bottom:10px" class="btn btn-danger pull-right" type="button" onclick="removeLink(${link.id})">Remover</button>
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
					location.href = "/";
				}
			});
		}
	}
	
	var qrcode = new QRCode("qrcode", {
	    text: "${link.shortUrl}",
	    colorDark : "#000000",
	    colorLight : "#ffffff",
	    correctLevel : QRCode.CorrectLevel.H
	});
	</script>
</t:narrow_layout>