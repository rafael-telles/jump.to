<%@tag pageEncoding="UTF-8"%>
<%@attribute name="title"%>
<%@attribute name="head" fragment="true"%>
<jsp:useBean id="global" class="main.Global"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>${global.siteName}${title.length() > 0 ? (" x "+title) : ""}</title>

		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet">

		<!--[if lt IE 9]>
		      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		<jsp:invoke fragment="head" />
	</head>
	<body>
		<jsp:doBody />

		<script src="js/jquery-2.1.4.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>