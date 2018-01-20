<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Parking Space</title>

<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />
<link href="css/metro/crimson/jtable.css" rel="stylesheet"
	type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"
	type="text/css" />
	<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>

<style>
body {
	background:
		url('img/bodybg.png');
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var url_string = window.location;
		var url = new URL(url_string);
		var c = url.searchParams.get("id");
		console.log(c);
		$('#parkingSpaces').jtable({
			title : 'Anonymous User',
			actions : {
				listAction : 'EditParkingSpace?action=list&pId=' + c,
				createAction : 'EditParkingSpace?action=create&pId=' + c,
				updateAction : 'EditParkingSpace?action=update&pId=' + c,
				deleteAction : 'EditParkingSpace?action=delete&pId=' + c
			},
			fields : {
				vehicleType : {
					title : 'Vehicle Type',
					list : true,
					create : true,
					width : '30%'
				},
				onlineSlots : {
					title : 'Online Slots',
					width : '15%',
					edit : true
				},
				onlineCharge : {
					title : 'Online Charges',
					width : '15%',
					edit : true
				},
				offlineSlots : {
					title : 'Offline Slots',
					width : '15%',
					edit : true

				},
				offlineCharge : {
					title : 'Offline Charges',
					width : '15%',
					edit : true
				},
				tax : {
					title : 'Tax',
					width : '20%',
					edit : true
				},
				psId : {
					title : 'Parking Space ID',
					edit : false,
					list : false,
					key:true
				}
			}
		});
		$('#parkingSpaces').jtable('load');

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

				<div id="parkingSpaces"></div>
				<form action="editParkingSpace?action=deleteEntry&pId=0">
					<button type="submit" class="btn">Delete Parking Space</button>
				</form>
			</div>
			<!-- /row -->
		</div>
		
	</div>
</div>

	<!-- /Main -->


	<!-- footer -->
	<jsp:include page="./footer.jsp" />
	<!-- /footer -->
	
</body>
</html>