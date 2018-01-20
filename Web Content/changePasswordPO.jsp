<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@page import="java.sql.*"%>
<%@ page import="com.mvc.util.DBConnection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Parking Owner Profile</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />
	<link href="css/pages/signin.css" rel="stylesheet" type="text/css">
		<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>

<style type="text/css">
body {
	background:
		url('img/bodybg.png');
}
</style>
<script language="javascript">
				function fncSubmit() {

					if (document.ChangePasswordForm.OldPassword.value == "") {
						alert('Please input old password');
						document.ChangePasswordForm.OldPassword.focus();
						return false;
					}

					if (document.ChangePasswordForm.newpassword.value == "") {
						alert('Please input Password');
						document.ChangePasswordForm.newpassword.focus();
						return false;
					}

					if (document.ChangePasswordForm.conpassword.value == "") {
						alert('Please input Confirm Password');
						document.ChangePasswordForm.conpassword.focus();
						return false;
					}

					if (document.ChangePasswordForm.newpassword.value != document.ChangePasswordForm.conpassword.value) {
						alert('Confirm Password Not Match');
						document.ChangePasswordForm.conpassword.focus();
						return false;
					}

					document.ChangePasswordForm.submit();
				}
</script>

</head>
<body class="home blog">
<%
		request.getAttribute("user_id");
	%>
	<div class="body-content container">
	<jsp:include page="./parkingHeader.jsp" />
	
	<div class="row home_content_wrapper">
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
				<div class="col-md-3 col-sm-3 feature_box"></div>
					<div class="col-md-5 col-sm-5">
					<div class="feature_inner" style="background-color: grey; color: white; height: 400px;">
					<div class="widget_inner">
						<p style="font-size:x-large; color: white;">Change password </p>
						--------------------------------------------------
						<form name="ChangePasswordForm" method="post"
				action="processChangePWDPO.jsp" OnSubmit="return fncSubmit();">
			<div class="login-fields">
			
			<div class="field">
						<label for="oldpwd" title="oldpassword">Old Password</label> <input type="password"
							id="OLDpwd" name="OldPassword" title="password" style="width:400px;"
							class="login username-field" required />
					</div>
					
			<div class="field">
						<label for="newpwd" title="newpassword">New Password</label> <input type="password"
							id="newpassword" name="newpassword" title="password" style="width:400px;"
							class="login username-field" required />
					</div>
					
					<div class="field">
						<label for="conpwd" title="conpassword">Confirm Password</label> <input type="password"
							id="conpassword" name="conpassword" title="password" style="width:400px;"
							class="login username-field" required />
					</div>
	<div class="login-actions">
					<button type="submit" class="btn btn-group-justified btn-large button" style="background-color: #1d3c50; color: white;">Save</button>
				</div>
			</div>	
			</form>
</div>
		</div>
</div>
	</div>
</div>
</div>
</div>
	<jsp:include page="./footer.jsp" />

	<script>
		$(document).ready(function() {

			$(".mainnav li").removeClass("active");
			$("#change_password").addClass("active");

		});
	</script>
</body>
</html>