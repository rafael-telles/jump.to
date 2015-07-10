<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="global" class="main.Global" />

<t:cover_layout title="Home">
	<jsp:attribute name="navbar">
        <li class="active"><a href="/">Início</a></li>
        <li><a href="/contact.jsp">Contato</a></li>
    </jsp:attribute>
	<jsp:body>
		<div class="inner cover">
            <h1 class="cover-heading">Bem-vindo!</h1>
            <p class="lead"><strong>${global.siteName}</strong> é um simples encurtador de URLs.</p>
            <p class="lead">
              <a href="#" class="btn btn-lg btn-default">Descubra</a>
            </p>
          </div>
    </jsp:body>
</t:cover_layout>