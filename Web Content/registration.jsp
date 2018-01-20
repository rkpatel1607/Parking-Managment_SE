<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Parking Zone: Login</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">

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
					<div class="col-md-3 col-sm-3 feature_box"></div>
					<div class="col-md-5 col-sm-5">
					<div class="feature_inner" style="background-color: grey; color: white; height: 950px;">
					<div class="widget_inner">
						

			<form action="Registration" method="post">

				<h1>Registration</h1>

				<div class="login-fields">

					<p style="color: white;">Please provide your details</p>
					<div class="field">
						<label for="firstname">First Name</label> <input type="text"
							id="firstname" name="firstname" value="" placeholder="First Name"
							class="login username-field" style="width:400px;" pattern="[A-Za-z]{2,}" required />
					</div>

					<div class="field">
						<label for="lastname">Last Name</label> <input type="text"
							id="lastname" name="lastname" value="" placeholder="Last Name"
							class="login username-field" style="width:400px;" pattern="[A-Za-z]{2,}" required />
					</div>

					<div class="field">
						<label for="email">Email</label> <input type="email" id="email"
							name="email" value="" placeholder="Email"
							class="login username-field" style="width:400px;" required />
					</div>
					<!-- /field -->

					<div class="field">
						<label for="password">Password:</label> <input type="password"
							id="password" name="password" value="" placeholder="Password"
							class="login password-field" style="width:400px;" required />
					</div>
					<!-- /password -->

					<div class="field">
					<label for="user_type">User Type: &nbsp;&nbsp;&nbsp;</label>
						<label class="radio-inline"> <input
							class="radio" style="width: 15px; height: 15px;" type="radio" name="loginType"
							value="owner" checked> Parking Owner

						</label> <label class="radio-inline"> <input
							class="radio" style="width: 15px; height: 15px;" type="radio" name="loginType"
							value="user"> Vehicle Owner
						</label>
					</div>
					<!-- /select -->
					<div class="field">
						<label for="number">Contact Number</label> <input type="number"
							id="number" name="number" value="" placeholder="Contact Number"
							class="login username-field" style="width:400px;" min="1111111111" max="9999999999"
							required />
					</div>

					<div class="field">
						<label for="address">Address</label> <input type="text"
							id="address" name="address" value="" placeholder="Address"
							class="login username-field" style="width:400px;" required />
					</div>

					<div class="field">
						<label for="city">City</label> <input type="text" id="city"
							name="city" value="" placeholder="City"
							class="login username-field" style="width:400px;" pattern="[A-Za-z ]{2,}" required />
					</div>

					<div class="field">
						<label for="state">State</label> <input type="text" id="state"
							name="state" value="" placeholder="State"
							class="login username-field" style="width:400px;" pattern="[A-Za-z ]{2,}" required />
					</div>

					<div class="field">
						<label for="zipcode">Zip code</label> <input type="number"
							id="zipcode" name="zipcode" value="" placeholder="Zip Code"
							class="login username-field" style="width:400px;" min="11111" max="99999" required />
					</div>
				</div>
				<!-- /login-fields -->

				<div class="login-actions">
					
					<button class="btn btn-group-justified btn-large button" style="background-color: #1d3c50; color: white;">Register</button>
					<br /> <br /> ${message} <br>
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
