<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@page import="java.sql.*"%>
<%@ page import="com.mvc.util.DBConnection"%>
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
		$.ajax({
			type : 'GET',
			url : 'ViewProfile',
			success : function(responseText) {
				$('#responseNote').html(responseText);
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
			<jsp:include page="./parkingHeader.jsp"/>	
		
		<div class="row home_content_wrapper">
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
				<div class="col-md-3 col-sm-3 feature_box"></div>
					<div class="col-md-5 col-sm-5">
					<div class="feature_inner" style="background-color: grey; color: white; height: 570px;">
					<div class="widget_inner">
					
					<form action="Update" method="post">
				<%
					String a = (String) request.getSession().getAttribute("username");

					try {

						Connection conn = DBConnection.createConnection();
						String query = "select * from parkingowner inner join users on users.user_id= parkingowner.Users_user_id where users.username= '" + a + "'";
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {
				%>
				<input type="hidden" name="user_id" value="<%=rs.getString("PO_id")%>">

				<div class="login-fields">

					<div class="field">
						<label for="fullname" title="Full Name">Full Name</label><input type="text"
							id="fullname" name="fullname"
							value="<%=rs.getString("PO_fullName")%>"
							class="login username-field" style="width:400px;" title="Full Name" pattern="[A-Za-z ]{2,}" required />
					
					<div class="field">
						<label for="number" title="Contact Number">Contact Number</label> <input type="number"
							id="number" name="number" title="Contact Number"
							value="<%=rs.getString("PO_contactNo")%>"
							placeholder="Contact Number" class="login username-field" style="width:400px;" 
							min="1111111111" max="9999999999" required />
					</div>
					
					<div class="field">
						<label for="address" title="Address">Address</label> <input type="text"
							id="address" name="address" title="Address"
							value="<%=rs.getString("PO_address")%>" style="width:400px;" placeholder="Address"
							class="login username-field" required />
					</div>

					<div class="field">
						<label for="city" title="City">City</label> <input type="text" id="city"
							name="city" value="<%=rs.getString("PO_city")%>" title="City"
							placeholder="City" class="login username-field" style="width:400px;"
							pattern="[A-Za-z ]{2,}" required />
					</div>

					<div class="field">
						<label for="state" title="State">State</label><input type="text" id="state"
							name="state" value="<%=rs.getString("PO_state")%>" title="State"
							placeholder="State" class="login username-field" style="width:400px;"
							pattern="[A-Za-z ]{2,}" required />
					</div>

					<div class="field">
						<label for="zipcode" title="Zip Code">Zip code</label> <input type="number"
							id="zipcode" name="zipcode" value="<%=rs.getString("PO_zip")%>" title="Zip Code"
							placeholder="Zip Code" class="login username-field" style="width:400px;" min="11111"
							max="99999" required />
					</div>
				</div>
				<!-- /login-fields -->
				<%
					}
					} catch (Exception e) {
						out.print(e.getMessage());
						e.printStackTrace();

					}
				%>
				<div class="login-actions">

					<button type="submit" class="btn btn-group-justified btn-large button" style="background-color: #1d3c50; color: white;">Update</button>

				</div>
				<!-- .actions -->
</div>
			</form>
			</div>
			</div>
			</div>
					</div>			
					
				</div>
			</div>
			
		</div>
	
	
<jsp:include page="./footer.jsp"/>	
	
</body>
</html>
