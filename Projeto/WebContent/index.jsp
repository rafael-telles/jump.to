<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="wrapper" tagdir="/WEB-INF/tags" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Url Shortener</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <!--<link rel="icon" sizes="192x192" href="images/touch/chrome-touch-icon-192x192.png"> -->

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="apple-touch-icon-precomposed.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <link rel="stylesheet" href="material/material.min.css">
    <link rel="stylesheet" href="material/styles.css">
    <style>
    #view-source {
      position: fixed;
      display: block;
      right: 0;
      bottom: 0;
      margin-right: 40px;
      margin-bottom: 40px;
      z-index: 900;
    }
    </style>
  </head>
  <body class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    
        <wrapper:wheader>
            <jsp:attribute name="tabbarcontent">
                <a href="#overview" class="mdl-layout__tab is-active">Gerar Link</a>
                <a href="#maisvisitados" class="mdl-layout__tab">Mais Visitados</a>
                <a href="#about" class="mdl-layout__tab">Sobre</a>
                <a href="#faq" class="mdl-layout__tab">FAQ</a>
            </jsp:attribute>
            <jsp:attribute name="tabbarnavcontent">
                <a href="#overview" class="mdl-navigation__link">Gerar Link</a>
                <a href="#maisvisitados" class="mdl-navigation__link">Mais Visitados</a>
                <a href="#about" class="mdl-navigation__link">Sobre</a>
                <a href="#faq" class="mdl-navigation__link">FAQ</a>
            </jsp:attribute> 
        </wrapper:wheader>
        
        <main class="mdl-layout__content">
            <div class="mdl-layout__tab-panel is-active" id="overview">
     
            </div>
            
            <div class="mdl-layout__tab-panel" id="maisvisitados">
            	<section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
	                <wrapper:wcard>
		                <jsp:attribute name="cardtitle">a</jsp:attribute>
		                
		                <jsp:attribute name="cardcontent">Encurte seu link aqui
		                </jsp:attribute>
		                <jsp:attribute name="cardlink">Samu? seu cú!</jsp:attribute>
	                </wrapper:wcard> 
            	</section>
            </div>
            
            <div class="mdl-layout__tab-panel" id="about">
            </div>
            
            <div class="mdl-layout__tab-panel" id="faq">
            </div>
        </main>
    </div>
    <script src="material/material.min.js"></script>
  </body>
</html>