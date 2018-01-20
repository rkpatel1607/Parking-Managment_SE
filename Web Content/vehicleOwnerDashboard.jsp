<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Vehicle Owner Dashboard</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />

<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<style>
body {
	background:
		url('img/bodybg.png');
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			type : 'POST',
			url : 'VehicleOwnerDashboard',
			success : function(responseText) {
				$('#BookingDetails').html(responseText);
			}
		});
	});
</script>
</head>
<body class="home blog">

	<%
		if (session.getAttribute("username") == null) {

			response.sendRedirect("home.jsp");

		}
	%>

	<div class="body-content container">
	<jsp:include page="./vehicleOwnerHeader.jsp" />
			<div class="feature_content col-md-12" style="padding-top: 10px;">
				<div class="two_col-div row">
					
					<div
		style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center; margin-bottom: 10px;">
								<h3>Your Booking Details</h3>
							</div>

							<div id="BookingDetails"></div>
						</div>
					</div>
				</div>

	<jsp:include page="./footer.jsp" />
	<!-- /footer -->

</body>
</html>
