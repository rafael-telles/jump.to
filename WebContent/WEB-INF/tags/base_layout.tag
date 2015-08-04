<%@tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title"%>
<%@attribute name="head" fragment="true"%>
<jsp:useBean id="global" class="to.jump.Global"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<base href="http://${global.domainName}/">
		<c:choose>
		    <c:when test="${title.length() > 0}">
				<title>${global.siteName} - ${title}</title>
		    </c:when>    
		    <c:otherwise>
				<title>${global.siteName}</title>
		    </c:otherwise>
		</c:choose>

		<!-- Bootstrap -->
		<link href="assets/css/bootstrap.min.css" rel="stylesheet">

		<!--[if lt IE 9]>
		      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		<jsp:invoke fragment="head" />

		<script src="assets/js/jquery-2.1.4.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		
		<link rel="icon" type="image/png" href="assets/imgs/small_logo.png" />
	</head>
	<body>
		<jsp:doBody />
	</body>
</html>