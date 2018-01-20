<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Parking Zone: Login</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">

<link href="css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
<link href="css/pages/signin.css" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />

<style type="text/css">
body {
	background:
		url('img/bodybg.png');
}

.btn-success, .btn-large {
	background-color: #49635c;
}
</style>
</head>
<body class="home blog">
<div class="body-content container">
			<jsp:include page="./homeHeader.jsp"/>	

	<div class="row home_content_wrapper">
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
					<div class="col-md-4 col-sm-4 feature_box"></div>
					<div class="col-md-4 col-sm-4">
					<div class="feature_inner" style="background-color: grey; color: white;">
					<div class="widget_inner">
						

			<form action="HomeLogin" method="post">

				<h1>Login</h1>

				<div class="login-fields">

					<p style="color:white;">Please provide your details</p>

					<div class="field">

						<label for="username" style="color:white;">Username</label> <input type="text"
							id="username" name="username" value="" placeholder="Enter Username"
							class="login username-field" style="width: 305px;"/>
					</div>
					<!-- /field -->

					<div class="field">
						<label for="password" style="color:white;">Password:</label> <input type="password"
							id="password" name="password" value="" placeholder="Enter Password"
							class="login password-field" />
					</div>
					<!-- /password -->
					<div class="field">
						<label for="loginType" style="color: white;">User Type:</label> <select id="loginType"
							name="loginType" class="select-input">
							<option value="user">Vehicle Owner</option>
							<option value="owner">Parking Owner</option>
							<option value="anonymous">Parking Attendent</option>
						</select>
					</div>
					<!-- /password -->

				</div>
				<!-- /login-fields -->

				<div class="login-actions">

					<button class="btn btn-group-justified btn-large button" style="background-color: #1d3c50; color: white;">Sign In</button>
					<br /> <br />
					<h3 style="color: red;">
						 ${msg} 
					</h3>
					<br>
					
					<c:remove var="message" scope="session" />
				</div>
				<!-- .actions -->

			</form>

		</div>
	</div>
	</div>
	<div class="col-md-4 col-sm-4 feature_box"></div>
</div>
</div>
</div>
</div>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/signin.js"></script>

<jsp:include page="./homeFooter.jsp"/>
	
</body>
</html>
