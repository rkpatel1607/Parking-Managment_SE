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
			url : 'ViewParkinSpace',
			success : function(response) {
				if (response != null)
					$('#parkingSpace').html(response);
				else
					$('#msg').html("No Parking Space Added Yet");
			},
			failure : function(error) {
				console.log(error)
			}
		});
		$('#').click(function() {
			var uname = $('#username').val();
			var mobile = $('#mobile').val();
			var regNum = $('#regNum').val();
			var vehicleType = $('#vehicleType').val();
			$.ajax({
				type : 'POST',
				url : 'AnonymousUser',
				data : {
					name : uname,
					mobile_number : mobile,
					RegistrationNo : regNum,
					type : vehicleType
				},
				success : function(responseText) {
					console.log(responseText);
				}
			});
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
						<div id="parkingSpace">
							<h4 id="msg"></h4>
						
					</div>
					<div>
						<a href="addParkingSpace.jsp" class="btn btn-primary">Add New
							Space</a>
					</div>
				</div>
				<div class="row"></div>
				<!-- /span6 -->
			</div>
	</div>
		</div>

	<!-- /Main -->


	<!-- footer -->
	<jsp:include page="./footer.jsp" />
	<!-- /footer -->
	<script type="text/javascript">
	function editRecord(id) {
		
		document.location = 'editParkingSpace.jsp?id=' + id;
	}
	
	</script>
	<!-- Placed at the end of the document so the pages load faster -->

</body>
</html>