<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@ page import="com.mvc.util.DBConnection"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<title>Parking Zone:Car Parking Service Provider</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"
	type="text/css" />
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>

<style type="text/css">
body {
	background: url('img/bodybg.png');
}
</style>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#entryTimeOnline').hide();
		$.ajax({
			type : 'GET',
			url : 'AnonymousUser',
			success : function(responseText) {
				$('#responseNote').html(responseText);
			}
		});
		$('#searchBooking').click(function() {
			var bid = $('#bookingId').val();
			$.ajax({
				type : 'GET',
				url : 'SearchBookingServlet',
				data : {
					booking_id : bid
				},
				success : function(responseText) {
					$('#searchedBooking').html(responseText);
					$('#entryTimeOnline').show();
				}
			});
		});
		$('#submit123').click(function() {
			var uname = $('#username').val();
			var mobile = $('#mobile').val();
			var regNum = $('#regNum').val();
			var vehicleType = $('#vehicleType').val();
			console.log(uname);
			console.log(mobile);
			console.log(regNum);
			console.log(vehicleType);
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
				},
				failure : function(error) {
					console.log(error);
				}
			});
		});
		$('#entryTimeOnline').click(function() {
			var bid = $('#bookingId').val();
			alert(bid);
			$.ajax({
				type : 'GET',
				url : 'OnlineBookingEntry',
				data : {
					bookingid : bid
				},
				success : function(responseText) {
					$('#searchedBooking').html(responseText);
				},
				failure : function(error) {
					console.log(error);
				}
			});
		});
	});
</script>
</head>
<body class="home blog">
	>

	<div class="body-content container">
		<jsp:include page="./anonymousHeader.jsp" />
		<!-- /navbar -->


		<div class="row home_content_wrapper">
			<div class="feature_content col-md-12" style="padding-top: 0px;">
				<div class="two_col-div row">

					<div
						style="width: 90%; margin-right: 5%; margin-left: 5%; margin-bottom: 65px;">



						<%
							int id = Integer.parseInt((String) session.getAttribute("user_id"));
							Connection con1 = DBConnection.createConnection();
							Statement stmt1 = con1.createStatement();
							ResultSet rs1 = stmt1.executeQuery(
									"Select parking.P_name, parking.P_id from parking,anonymoususer,users where anonymoususer.Parking_P_id=parking.P_id and anonymoususer.Users_user_id=users.user_id and users.user_id="
											+ id);
							rs1.next();
							String P_name = rs1.getString("P_name");
							int P_id = rs1.getInt("P_id");
						%>
						<h4
							style="text-align: left; border: thin; border-style: solid; padding: 7px;">
							Parking Name:
							<%
							out.println(P_name);
						%>
						</h4>
						<br />

						<!-- /widget-header -->



						<div class="tabbable">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#booking" data-toggle="tab">Booking</a></li>
								<li><a href="#onlineBooking" data-toggle="tab">Online
										Booking Time Entry</a></li>
								<li><a href="#parkingSlots" data-toggle="tab">Available
										Parking Slots</a></li>
							</ul>
							<br>

							<div class="tab-content">
								<div class="tab-pane active" id="booking"
									style="padding-top: 0px;">
									<div class="feature_content col-md-12">
										<div class="col-md-5 col-sm-5 feature_box">
											<div class="feature_inner">
												<form id="bookingForm" class="form-horizontal">
													<div class="login-fields">

														<div class="field">
															<label for="username">Name</label>
															<div class="controls">
																<input type="text" class="span6 disabled" id="username"
																	required="required">
															</div>
															<!-- /controls -->
														</div>
														<!-- /control-group -->


														<div class="field">
															<label for="mobile">Mobile Number</label>
															<div class="controls">
																<input type="number" min="1111111111" max="9999999999"
																	class="span6" id="mobile" required="required">
															</div>
															<!-- /controls -->
														</div>
														<!-- /control-group -->


														<div class="control-group">
															<label class="control-label" for="regNum">Registration
																Number</label>
															<div class="controls">
																<input type="number" class="span6" id="regNum"
																	required="required">
															</div>
															<!-- /controls -->
														</div>
														<!-- /control-group -->

														<div class="control-group">
															<label class="control-label">Vehicle Type</label>
															<div class="controls">
																<select class="form-control" id="vehicleType">
																	<option>Please Select Vehicle Type</option>
																	<%
																		Connection con3 = DBConnection.createConnection();
																		Statement stmt3 = con3.createStatement();
																		ResultSet rs3 = stmt3.executeQuery("SELECT * FROM vehicletype;");
																		while (rs3.next()) {
																	%>
																	<option value="<%=rs3.getString("type")%>"><%=rs3.getString("type")%></option>
																	<%
																		}
																	%>
																</select>

															</div>
															<!-- /controls -->
														</div>
														<!-- /control-group -->

														<br />
														<div class="form-actions">
															<button class="btn btn-primary" onclick="bookingCalled()">Save</button>
															<button class="btn">Reset</button>
														</div>
														<!-- /form-actions -->
													</div>
												</form>
											</div>
										</div>
									</div>
									<div id="responseNote"></div>
								</div>

								<div class="tab-pane" id="onlineBooking">
									<div class="feature_content col-md-12">
										<div class="col-md-5 col-sm-5 feature_box">
											<div class="feature_inner" style="height: 200px;">
												<fieldset>
													<div class="control-group">
														<label class="control-label" for="bookingId">Booking
															ID</label>
														<div class="controls">
															<input type="text" class="span6 disabled" id="bookingId">
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
													<div class="form-actions">
														<button id="searchBooking" class="btn btn-primary">Search
															Booking</button>
													</div>
													<!-- /form-actions -->

												</fieldset>

												<button id="entryTimeOnline" class="btn btn-primary">Entry
													Time</button>
											</div>
										</div>
									</div>
								</div>

								<div class="tab-pane" id="parkingSlots">
									<div class="feature_content col-md-12">
										<div class="col-md-5 col-sm-5 feature_box">
											<div class="feature_inner"
												style="height: 1900px; font-size: small;">
												<div id="searchedBooking"></div>
												<%
													try {
														Connection con = DBConnection.createConnection();
														Statement stmt = con.createStatement();

														ResultSet rs = stmt.executeQuery("Select * from parkingspace where Parking_P_id=" + P_id);
														while (rs.next()) {
															Statement stmtoff = con.createStatement(), stmton = con.createStatement();
												%>
												<h3>
													Vehicle Type:<%=rs.getString("PS_vehicleType")%>
												</h3>
												<%
													String offlineCount = "Select count(*) from booking where Parking_P_id=" + P_id
																	+ " and B_bookingType='OFFLINE' and B_vehicleType='" + rs.getString("PS_vehicleType") + "'";
															ResultSet rs_count_offline = stmtoff.executeQuery(offlineCount);
															String onlineCount = "Select count(*) from booking where Parking_P_id=" + P_id
																	+ " and B_bookingType='ONLINE' and B_vehicleType='" + rs.getString("PS_vehicleType") + "'";
															ResultSet rs_count_online = stmton.executeQuery(onlineCount);
															rs_count_offline.next();
															rs_count_online.next();
															int offline = rs_count_offline.getInt("count(*)");
															int online = rs_count_online.getInt("count(*)");
												%>
												<h5>-----------------------------------------------------------------</h5>
												<h4>
													Total Available Online Slots :<%=rs.getInt("PS_AvailableOnlineSlots") - online%>
												</h4>
												<h4>
													Total Available Offline Slots:<%
													int x = rs.getInt("PS_AvailableOfflineSlots");
															out.println(x - offline);
												%>
												</h4>
												</br>
												<%
													}
													} catch (Exception e) {
														e.printStackTrace();
													}
												%>


											</div>
										</div>
									</div>
								</div>

								<!-- /widget-content -->
							</div>
							<!-- /widget -->
						</div>
					</div>
				</div>
				<!-- /span8 -->

			</div>

		</div>


	</div>
	<!-- /row -->


	<!-- footer -->
	<jsp:include page="./footer.jsp" />
	<!-- /footer -->
	<script type="text/javascript">
		function bookingCalled() {
			var uname = $('#username').val();
			var mobile = $('#mobile').val();
			var regNum = $('#regNum').val();
			var vehicleType = $('#vehicleType').val();
			console.log(uname);
			console.log(mobile);
			console.log(regNum);
			console.log(vehicleType);
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
				},
				failure : function(error) {
					console.log(error);
				}
			});
		}
	</script>

	<!-- Placed at the end of the document so the pages load faster -->
	<!-- /Calendar -->
</body>
</html>
