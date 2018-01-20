<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Parking Space</title>
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
			url : 'ManagePOBookings',
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
	
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
					
					<div
		style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center; margin-bottom: 65px;">
				
						<!-- /widget-header -->
						<div id="bookingTable">
							<h4 id="msg"></h4>
						</div>
						<!-- /widget-content -->
					</div>
				
					<!-- /widget -->
					<!-- /widget -->
				</div>
				<div class="row"></div>
				<!-- /span6 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<script type="text/javascript">
		function deleteRecord(id) {
			$.ajax({
				type : 'GET',
				url : 'DeleteBookingRecord?Bid=' + id,
				success : function(response) {
					console.log('deleted');
					location.reload();
				},
				failure : function(error) {
					console.log(error)
				}
			});
		}
	</script>
	<!-- footer -->
	<jsp:include page="./footer.jsp" />
	<!-- /footer -->
</body>
</html>
