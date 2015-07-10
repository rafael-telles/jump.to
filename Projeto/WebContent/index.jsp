<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="global" class="main.Global" />

<t:base_layout title="">
	<jsp:attribute name="head">
    <link href="css/jumbotron-narrow.css" rel="stylesheet">
</jsp:attribute>
	<jsp:body>
	<div class="container">
      <div class="header clearfix">
        <h3 class="text-muted pull-left">${global.siteName}</h3>
        <nav>
          <form class="nav navbar-form pull-right">
          	<div class="form-group">
			    <div class="input-group">
			      <input type="url" class="form-control" placeholder="Uma URL bem grande" required>
			      <div class="input-group-addon"><span class="glyphicon glyphicon-send" aria-hidden="true"></span></div>
			    </div>
		  	</div>
          </form>
        </nav>
      </div>
      
      <div class="alert alert-success" role="alert">
      	<strong>Pronto!</strong> Use este link para compartilhar com os seus amigos: <a href="#">http://jump.to/u/XYZ</a>
      </div>

      <div class="jumbotron">
        <h1>Bem-vindo!</h1>
        <p class="lead">
        ${global.siteName} é um simples encurtador de URLs.
		</p>
      </div>

      <div class="row marketing">
        <div class="col-lg-6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>

        <div class="col-lg-6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
      </div>

      <footer class="footer">
        <p>Feito por <a href="#">Alexsandro</a>, <a href="#">Leandro</a> e <a	href="#">Rafael</a> para a UFABC.</p>
      </footer>

    </div> <!-- /container -->
    </jsp:body>
</t:base_layout>