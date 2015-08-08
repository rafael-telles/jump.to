<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="global" class="to.jump.Global" />

<t:narrow_layout>
	<style>
	.hero-wrapper {
		text-align: center;
		background: #CBCBCB;
		border-radius: 50%;
		width: 200px;
		height: 200px;
		margin: 0 auto;
		padding: 10px;
	}
	.hero {
		width: 100%;
	}
	</style>
	<c:if test="${not empty user}">
		<c:redirect url="/dashboard"/>
	</c:if>

	<div class="jumbotron">
		<div class="hero-wrapper">
			<img class="hero" src="assets/imgs/big_logo.png">
		</div>
		<h1>Bem-vindo!</h1>
		<p class="lead">${global.siteName} é um simples encurtador de URLs.</p>
		<form action="shorten" method="post">
		    <div class="input-group input-group-lg">
		      <input id="longUrl" name="longUrl" type="url" class="form-control" placeholder="Uma URL bem grande" required>
		      	<div class="input-group-btn">
                	<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-send"></i></button>
            	</div>
		  	</div>
        </form>
        <br>
		<a class="btn btn-success btn-lg" href="/login">Login</a>
	</div>
</t:narrow_layout>