<!-- RAHUL -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Parking Owner Dashboard</title>
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
			type : 'GET',
			url : 'ViewBookingsPO',
			success : function(response) {
				$('#bookingTable').html(response);
				console.log(response);
			},
			failure : function(error) {
				console.log(error)
			}
		});
	});
</script>
</head>
<body class="home blog">
	<%
		request.getAttribute("user_id");
	%>
	<div class="body-content container">
	<jsp:include page="./parkingHeader.jsp" />
	<p style="font-size: medium; color: black;">Your Parking Space will be subject to verification. Please send the documents to <u><b>SE_team5project@gmail.com</b></u></p>

			<div class="feature_content col-md-12">
				<div class="two_col-div row">
					
					<div
		style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center; margin-bottom: 65px;">
		
					<div id="anonymomusUser">
					
					</div>
						<div id="bookingTable">
							<h4 id="msg"></h4>
						</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="./footer.jsp" />

	<!-- /Calendar -->
</body>
</html>
