<%@page import="java.sql.*"%>
<%@page import="com.mvc.util.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Dashboard</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"
	type="text/css" />
	<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<link href="css/invoice.css" rel="stylesheet">
<script src="js/bootstrap.js"></script>

<style>
body {
	background:
		url('img/bodybg.png');
}
</style>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#exitTime').hide();
	
	$('#exitVehicle').click(function() {
		//$('#invoice').hide();
		var vid = $('#vehicleId').val();
		$.ajax({
			type : 'GET',
			url : 'SearchVehicleServlet',
			data : {
				vehicle_id : vid
			},
			success : function(responseText) {
				//alert(responseText);
				$('#searchedBooking').html(responseText);
				//$('#bookingId').text(vid);
				$('#exitTime').show();
			}
		});
	});
	$('#exitTime').click(function() {
		var vid = $('#vehicleId').val();
		$.ajax({
			type : 'GET',
			url : 'AnonymousExitTime',
			data : {
				vehicle_id : vid
			},
			success : function(responseText) {
				//alert(responseText);
				$('#invoice').html(responseText);
			}
		});
	});
});
</script>
</head>
<body class="home blog"> 
	<div class="body-content container">
	<jsp:include page="./anonymousHeader.jsp" />
	<!-- /navbar -->
<input type="hidden" id="bookingId">
	
		
		<div class="row home_content_wrapper">
			<div class="feature_content col-md-12"  style="padding-top: 0px;">
				<div class="two_col-div row">
					
					<div
		style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center; margin-bottom: 5px;">

								<%
												int id =Integer.parseInt((String)session.getAttribute("user_id"));
												Connection con1 = DBConnection.createConnection();
												Statement stmt1 = con1.createStatement();
												ResultSet rs1 = stmt1.executeQuery("Select parking.P_name from parking,anonymoususer,users where anonymoususer.Parking_P_id=parking.P_id and anonymoususer.Users_user_id=users.user_id and users.user_id="+id);
												rs1.next();
												String P_name=rs1.getString("P_name");
									%>
							<h4 style="text-align: left; border: thin; border-style:solid; padding: 7px;"> Parking Name:
							<% out.println(P_name); %></h4>
							</div>
							

								<div class="tab-pane" id="onlineBooking">
<div class="feature_content col-md-12" style="padding-top: 10px;">
										<div class="col-md-5 col-sm-5 feature_box">
										<div class="feature_inner" style="height: 150px; margin-left: 5%; margin-right: 5%;">
																		
									<fieldset>
										<div class="control-group">
											<label class="control-label" for="bookingId">Vehicle
												Name</label>
											<div class="controls">
												<input type="text" class="span6" id="vehicleId">
											</div>
											<!-- /controls -->
										</div>
										<!-- /control-group -->
										<div class="form-actions">
											<button id="exitVehicle" class="btn btn-primary">Search</button>
										</div>
										<!-- /form-actions -->
										

									</fieldset>
									</div>
									</div>
									</div>
									</div>
									
									<div class="feature_content col-md-12" style="padding-top: 10px;">
										<div class="col-md-10 col-sm-10 feature_box">
										<div class="feature_inner" style="height:inherit; margin-left: 5%; margin-right: 5%;">
									<div id="searchedBooking"> </div>
									<button id="exitTime" class="btn btn-primary">Exit Vehicle</button>
								
								<div id ="invoice" class="invoice-box">
									
								</div>
</div>
</div>
</div>

							</div>
							<!-- /widget-content -->
						</div>
						<!-- /widget -->

					</div>
					<!-- /span8 -->


				</div>
				<!-- /row -->

			
	<!-- /main -->






	<!-- footer -->
	<jsp:include page="./footer.jsp" />
	<!-- /footer -->

	<!-- /Calendar -->
</body>
</html>