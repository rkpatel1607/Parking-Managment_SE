<%@page import="java.sql.*"%>
<%@page import="com.mvc.util.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<link href="css/metro/crimson/jtable.css" rel="stylesheet"
	type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"
	type="text/css" />
	<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>


<style type="text/css">
body {
	background:
		url('img/bodybg.png');
}
</style>
<script type="text/javascript">
	    $(document).ready(function() {
		//initialize jTable
		$('#UserTableContainer').jtable({
			title : 'Your Vehicles',
			sorting : true,
			actions : {
				listAction : 'VehicleManagementServlet?action=list',
				deleteAction : 'VehicleManagementServlet?action=delete'
			},
			fields : {
				reg_number : {
					title : 'Registration Number',
					width : '30%',
					list : true,
				//edit: true
				},
				VehicleName : {
					title : 'Vehicle Name',
					width : '30%',
					list : true,
				//edit: true
				},
				VehicleType : {
					title : 'Vehicle Type',
					width : '30%',
					list : true,
				//edit: true
				}
			}
		});
		$('#UserTableContainer').jtable('load');
	});
</script>
</head>
<body class="home blog">
	<div class="body-content container">
			<jsp:include page="./vehicleOwnerHeader.jsp"/>	

	<div class="row home_content_wrapper">
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
					<div class="col-md-3 col-sm-3">
					<div class="feature_inner" style="color: black; height: 350px;">
					<div class="widget_inner">
								<h3>Add New Vehicle</h3>
							</div>
											<form method="get" action="VehicleManagement">
											<div class="login-fields">
													<div class="field">
														<label for="reg_num">Registration
															Number</label>
															<input type="text" id="reg_num" style="height: 35px; width: 200px;"
																name="reg_num">
														
														<!-- /controls -->
													</div>													
														<div class="field">
															<label for="vehicle_name">Vehicle
																Name</label>
																<input type="text" style="height: 35px; width: 200px;" id="vehicle_name" name="vehicle_name">
															</div>

														<div class="field">
															<label for="vehicle_type">Vehicle
																Type</label>
																<select id="vehicle_type" name="vehicle_type" style="height: 35px; width: 200px;">
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
																
																</select>
																</div>
																
															</div>

														<div class="login-actions" style="margin-top: 15px;">
															<button type="submit" style="width: 200px;" class="btn btn-primary" id="submit">Add</button>

														</div>
											</form>
										</div>
										</div>
										
										<div class="col-md-9 col-sm-9">
										<div class="feature_inner">
										<div id="UserTableContainer"></div>
										</div>
										</div>
									</div>
								</div>
							</div>
						</div>	
										<jsp:include page="./footer.jsp" />
										<!-- /footer -->
</body>
</html>