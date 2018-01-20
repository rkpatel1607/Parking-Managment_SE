<%@page import="java.sql.*"%>
<%@ page import="com.mvc.util.DBConnection"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Parking Zone: Login</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link href="css/pages/signin.css" rel="stylesheet" type="text/css">
<link href="css/pages/signin.css" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<style type="text/css">
body {
	background:
		url('img/bodybg.png');
}

.btn-success, .btn-large {
	background-color: #49635c;
}
</style>
</head>
<body class="home blog">

	<%
		if (session.getAttribute("username") == null) {

			response.sendRedirect("home.jsp");

		}
	%>
<div class="body-content container">
			<jsp:include page="./vehicleOwnerHeader.jsp"/>	

	<div class="row home_content_wrapper">
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
				<div class="col-md-4 col-sm-4">
				</div>
					<div class="col-md-4 col-sm-4">
					<div class="feature_inner" style="color: black; height: 580px;">
					<div class="widget_inner">
								<h3>Book Parking Space</h3>
							</div>
							<!-- /widget-header -->

										<form action="Bookparking" method="post">
										<div class="login-fields">
										<p style="color: black;">Please provide your details</p>
											<div class="field">
													<label for="username">User
														Name</label>
														<input type="text" name="username" class="login username-field"
															value="<%=session.getAttribute("username")%>"
															readonly="readonly" required="required">
													</div>
													<!-- /controls -->
											
												<div class="field">
													<label for="regNo">Vehicle
														Registration no</label>
													<br/>
														<select name='regno' style="height: 35px; width: 250px;">
															<option value="none">Select</option>
															<%
																Connection con1 = DBConnection.createConnection();
																Statement stmt1 = con1.createStatement();

																String username = (String) session.getAttribute("username");

																ResultSet rs1 = stmt1.executeQuery(
																		"Select distinct V_name,v_regNo from vehicle where vehicleowner_vo_id =(select vo_id from vehicleowner where users_user_id = (select user_id from users where username = '"
																				+ username + "')) ");
																while (rs1.next()) {
															%>
															<option value="<%=rs1.getString("v_regNo")%>"><%=rs1.getString("V_name")%></option>
															<%
																}
															%>
														</select>

													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->

												<div class="field">
													<label for="regNo">Parking
														Space</label><br/>
														<select name='parkingname' style="height: 35px; width: 250px;">
															<option value="none">Select</option>
															<%
																Connection con2 = DBConnection.createConnection();
																Statement stmt2 = con2.createStatement();
																ResultSet rs2 = stmt2.executeQuery("Select distinct p_name from parking");
																while (rs2.next()) {
															%>
															<option value="<%=rs2.getString("p_name")%>"><%=rs2.getString("p_name")%></option>
															<%
																}
															%>
														</select>

													</div>
													<!-- /controls -->
												
												<!-- /control-group -->
<br/>
												<div class="field">
													<label for="entry">Entry
														Date and time</label>
														<br/>
														<input type="datetime-local"  style="height: 35px; width: 250px;" id="entry"
															name="entry">
												</div>
												<!-- /control-group -->
<br/>
												<div class="field">
													<label for="exit">Exit Date
														and time</label><br/>
														<input type="datetime-local" style="height: 35px; width: 250px;" id="exit"
															name="exit">
													</div>

												<div class="login-actions" style="margin-top: 40px;">
													<button type="submit" class="btn btn-primary" style="width: 120px;" id="submit">Save</button>
													<button class="btn" style="width: 120px;" type="reset">Reset</button>
												</div>
										</form>
									</div>
									</div>
								</div>

							</div>
							<!-- /widget-content -->

						</div>
						<!-- /widget -->

					</div>
				

	<!-- footer -->
	<jsp:include page="./footer.jsp" />

</body>
</html>
