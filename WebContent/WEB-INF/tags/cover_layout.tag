<%@tag pageEncoding="UTF-8"%>
<%@attribute name="navbar" fragment="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="global" class="to.jump.Global"/>

<t:base_layout title="Home">
	<jsp:attribute name="head">
    	<link href="assets/css/cover.css" rel="stylesheet">
	</jsp:attribute>
	<jsp:body>
		<div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">${global.siteName}</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <jsp:invoke fragment="navbar" />
                </ul>
              </nav>
            </div>
          </div>

          <jsp:doBody />

          <div class="mastfoot">
            <div class="inner">
              <p>Feito por <a href="#">Alexsandro</a>, <a href="#">Leandro</a> e <a	href="#">Rafael</a>
              <br> para a UFABC.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
	</jsp:body>
</t:base_layout>