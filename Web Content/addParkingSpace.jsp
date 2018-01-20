<!-- Harnish -->
<%@page import="java.sql.*"%>
<%@page import="com.mvc.util.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.mvc.util.DBConnection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Parking Zone:Car Parking Service Provider</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />
		<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>

	<link href="css/pages/signin.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>

<style type="text/css">
body {
	background:
		url('img/bodybg.png');
}
</style>
<script type="text/javascript">
	$(document).ready(function() {

		function addVehicle() {
			var offSlots = $('#offSlots').val();
			console.log(offSlots);
		}
	});
</script>
</head>
<body class="home blog">
<%
		request.getAttribute("user_id");
	%>
	<div class="body-content container">
			<jsp:include page="./parkingHeader.jsp"/>	
		
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
				<div class="col-md-3 col-sm-3 feature_box"></div>
					<div class="col-md-5 col-sm-5">
					<div class="feature_inner" style="background-color: grey; color: white; height: 750px;">
					<div class="widget_inner">
					
					<form action="AddSpace" method="post">

						<h1>Add New Space</h1>

						<div class="login-fields">

							<p style="color: white; ">Please provide your details</p>
							<div class="field">
								<label for="pName">Parking Name</label> <input type="text"
									id="pName" name="pName" value="" placeholder="Parking Name"
									class="login username-field" style="width: 390px;" pattern="[A-Za-z ]{2,}" required />
							</div>

							<div class="field">
								<label for="address">Address</label> <input type="text"
									id="address" name="address" style="width: 390px;" value="" placeholder="Address"
									class="login username-field" required />
							</div>

							<div class="field">
								<label for="city">City</label> <input type="text" id="city"
									name="city" value="" placeholder="City" style="width: 390px;"
									class="login username-field" pattern="[A-Za-z ]{2,}" required />
							</div>

							<div class="field">
								<label for="state">State</label> <input type="text" id="state"
									name="state" value="" placeholder="State" style="width: 390px;"
									class="login username-field" pattern="[A-Za-z ]{2,}" required />
							</div>

							<div class="field">
								<label for="zipcode">Zip code</label> <input type="number" style="width: 390px;"
									id="zipcode" name="zipcode" value="" placeholder="Zip Code"
									class="login username-field" min="11111" max="99999" required />
							</div>
							<div class="field">
								<label style="display: block !important;" for="oTime" title="Opening Time">Opening Time</label> <input type="time"
									id="oTime" name="oTime" value="" placeholder="HH:MM" style="width: 390px;"
									class="login username-field" title="Opening Time" required />
							</div>
							<div class="field">
								<label style="display: block !important;" for="cTime" title="Closing Time">Closing Time</label> <input type="time"
									id="cTime" name="cTime" value="" placeholder="HH:MM" style="width: 390px;"
									class="login username-field" title="Closing Time" required />
							</div>
						
						<!-- /login-fields -->
						<input id="allVehicles" name="allVehicles" type="hidden" value="">

						<div class="login-actions">
							<button type="button" class="btn btn-info" data-toggle="modal"
								data-target="#myModal">Add Vehicle Spaces</button>
							<button style="float: initial; margin: 50px;" id="addSpaces"
								class="button btn btn-success btn-large" disabled="true">Add
								Space</button>
						</div>
						<!-- .actions -->
</div>
					</form>

					<!-- Modal -->
					<div id="myModal" class="modal fade" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
							
					
						
								<div class="modal-header">
								<p style="font-size:x-large; color: black; height: 5px;">New Parking Space </p>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									
								</div>
								<div class="modal-body">
								<div class="feature_inner">
								<div class="widget_inner">
								<form >
									<div class="Login-fields" style="color: black;">
									<div class="field">
										<label for="vehicleType">Vehicle Type</label> <select
											id="vehicleType" name="vehicleType" class="select-input">
											<option value="default" selected="selected">Select Vehicle Type</option>
											<%
												Connection con = DBConnection.createConnection();
												Statement stmt = con.createStatement();
												ResultSet rs = stmt.executeQuery("SELECT * FROM vehicletype;");
												while (rs.next()) {
											%>
											<option value="<%=rs.getString("type")%>"><%=rs.getString("type")%></option>
											<%
												}
											%>
										</select></div>
										<!-- Online -->
										<div class="field">
											<label for="onSlots">Total Online Slots</label> <input
												type="number" id="onSlots" name="onSlots" value=""
												placeholder="Total Online Slots" class="login username-field" style="width:400px;" min="1" max="999"
												required />
										</div>
										<div class="field">
											<label for="onCharge">Online Charges</label> <input
												type="number" id="onCharge" name="onCharge" value=""
												placeholder="Online Charges" class="login username-field" style="width:400px;" min="1" max="999" required />
										</div>
										<!-- Offline -->
										<div class="field">
											<label for="offSlots">Total Offline Slots</label> <input
												type="number" id="offSlots" name="offSlots" value=""
												placeholder="Total Offline Slots" class="login username-field" style="width:400px;" min="1" max="999" required />
										</div>
										<div class="field">
											<label for="offCharge">Offline Charges</label> <input
												type="number" id="offCharge" name="offCharge" value=""
												placeholder="Offline Charges" class="login username-field" style="width:400px;" min="1" max="999" required />
										</div>
										<!-- Tax --><br/>
										<div class="field">
											<label for="tax">Tax in %</label> <input type="number"
												id="tax" name="tax" value="" class="login username-field" style="width:400px;" placeholder="Tax in %" min="1"
												max="99" required />
										</div>
									
								
										<button type="button" class="btn btn-default"
											data-dismiss="modal" onclick="addVehicle()">Add</button>
									</div>
									</form></div>
								</div>
								</div>
							</div>
						</div>
					</div>
</div>


					<!-- /widget -->
					<!-- /widget -->
				</div></div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div></div>


	<!-- /Main -->


	<!-- footer -->
	<jsp:include page="./footer.jsp" />
	<!-- /footer -->
	<script type="text/javascript">
		var vehicles = [];
		function addVehicle() {
			console.log('asdasdasd');
			var onSlots = $('#onSlots').val();
			var onCharge = $('#onCharge').val();
			var offSlots = $('#offSlots').val();
			var offCharge = $('#offCharge').val();
			var tax = $('#tax').val();
			var e = document.getElementById("vehicleType");
			var vType = e.options[e.selectedIndex].value;
			var obj = {
				'VehicleType' : vType,
				'OnlineCharges' : onCharge,
				'AvailableOnlineSlots' : onSlots,
				'AvailableOfflineSlots' : offSlots,
				'OfflineCharges' : offCharge,
				'Tax' : tax
			}
			vehicles.push(obj);
			console.log(JSON.stringify(vehicles));
			if (vehicles.length > 0) {
				document.getElementById('addSpaces').disabled = false;
				$('#allVehicles').val(JSON.stringify(vehicles));
			}
			$('#onSlots').setText("");
			$('#onCharge').setText("");
			$('#offSlots').setText("");
			$('#offCharge').setText("");
			$('#tax').setText("");
			$('#onSlots').setText("");
			$('#vehicleType option').prop('selected', function() {
				return this.defaultSelected;
			});

		}
	</script>

</body>
</html>