<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<title>Parking Zone:Car Parking Service Provider</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"
	type="text/css" />
	<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>

<style type="text/css">
body {
	background:
		url('img/bodybg.png');
}
</style>
<script type="text/javascript">
    $(document).ready(function() {
	$.ajax({
		type : 'GET',
		url : 'ParkingSpaceApproval',
		success : function(response) {
			if (response != null)
				$('#parkingSpace').html(response);
			else
				$('#msg').html("No Requests");
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
				if(session.getAttribute("username")==null){
					
					response.sendRedirect("adminLogin.jsp");					
				}
			
			%>
	<div class="body-content container">
			<jsp:include page="./adminHeader.jsp"/>	
		
		<div class="row home_content_wrapper">
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
					
					<div
		style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center; margin-bottom: 65px;">
		<div id="parkingSpace"></div>
            				<h4 id="msg"></h4>
</div>

					</div>			
					
				</div>
			</div>
		</div>
	
<script type="text/javascript">
	function editRecord(id) {
		document.location = 'checkSpaceDetails.jsp?id=' + id;
	}
	
	</script>	
<jsp:include page="./footer.jsp"/>
	
	
</body>
</html>
