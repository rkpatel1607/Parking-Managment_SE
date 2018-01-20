<!-- RAHUL -->
<%@page import="javafx.scene.control.Alert"%>
<%@page import="java.sql.*"%>
<%@page import="com.mvc.util.DBConnection"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Vehicle Owner</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />
	<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<style>
body {
	background:
		url('img/bodybg.png');
}
</style>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script language="javascript" type="text/javascript">
	var xmlHttp
	var xmlHttp
	function showState(str) {
		if (typeof XMLHttpRequest != "undefined") {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (xmlHttp == null) {
			alert("Browser does not support XMLHTTP Request")
			return;
		}
		var url = "state.jsp";
		url += "?count=" + str;
		xmlHttp.onreadystatechange = stateChange;
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
	}

	function stateChange() {
		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			document.getElementById("state").innerHTML = xmlHttp.responseText
		}
	}

	function showCity(str) {
		if (typeof XMLHttpRequest != "undefined") {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (xmlHttp == null) {
			alert("Browser does not support XMLHTTP Request")
			return;
		}
		var url = "city.jsp";
		url += "?count=" + str;
		xmlHttp.onreadystatechange = stateChange1;
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
	}
	function stateChange1() {
		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			document.getElementById("city").innerHTML = xmlHttp.responseText
		}
	}
</script>
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
				<div class="feature_inner" style="color: black; height: inherit;">
				<div class="widget_inner">
								<h3>Parking Locator</h3>
							</div>
							<!-- /widget-header -->

							

										<form action="parkingLocatorResult.jsp" method="post">
											<table border="1">
												<tr>
													<th>State</th>
													<th>City</th>
												</tr>
												<tr>
													<td ><select name="country" style="width: 120px;"
														onchange="showState(this.value)">
															<option value="none">Select</option>
															<%
																Connection con = DBConnection.createConnection();
																Statement stmt = con.createStatement();
																ResultSet rs = stmt.executeQuery("Select distinct p_state from parking");
																while (rs.next()) {
															%>
															<option value="<%=rs.getString("p_state")%>"><%=rs.getString("p_state")%></option>
															<%
																}
															%>
													</select></td>
													<td id='state'><select name='state'  style="width: 120px;">
															<option value='Select'></option>
													</select></td>
												</tr>
											</table>
													
											<div class="login-actions">
												<button type="submit" class="btn btn-primary" id="submit" style="margin-top: 30px; width: 150px;">Search</button>
												<button class="btn" type="reset" style="margin-top: 30px; width: 150px;">Reset</button>
											</div>

										</form>		
							
						</div>	
				</div>
<div class="col-md-8 col-sm-8">
						<div class="feature_inner" style="color: black; height: inherit;">
						<div class="widget_inner">
								<h3>Available Parking Locations</h3>
							</div>

							<%!public class Actor {

		String URL = "jdbc:mysql://localhost:3306/se_pms";
		String USERNAME = "root";
		String PASSWORD = "admin";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		public Actor() {

			try {
				con = DBConnection.createConnection();
				ps = con.prepareStatement("select * from parking where p_state = ? AND p_city = ?");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		public ResultSet getActors(String state, String city) {

			try {
				ps.setString(1, state);
				ps.setString(2, city);
				rs = ps.executeQuery();

			} catch (SQLException e) {

				e.printStackTrace();
			}

			return rs;

		}

	}%>
							<%
								String state = new String();
								String city = new String();

								if (request.getParameter("country") != null) {
									state = request.getParameter("country");
									System.out.println(state);

								}
								if (request.getParameter("state") != null) {
									city = request.getParameter("state");
									System.out.println(city);
								}
								Actor actor = new Actor();
								ResultSet actors = actor.getActors(state, city);
							%>
						<table border="1">
									<tbody>
										<tr style="background-color: #2e6da4">

											<td>Parking Name</td>
											<td>Parking Address</td>
											<td>Parking ZIP</td>
											<td>Opening Time</td>
											<td>Closing Time</td>
										</tr>
										<%
											while (actors.next()) {
										%>
										<tr>

											<td><%=actors.getString("P_NAME")%></td>

											<td><%=actors.getString("P_ADDRESS")%></td>
											<td><%=actors.getString("P_ZIP")%></td>
											<td><%=actors.getString("P_opentime")%></td>
											<td><%=actors.getString("P_closetime")%></td>


										</tr>
										<%
											}
										%>


									</tbody>

								</table>
							</div>
							<!-- /widget-content -->
						</div>
						<!-- /widget -->

					</div>
					<!-- /span8 -->

				</div>

			</div>


		</div>
		<!-- /row -->

	
	<jsp:include page="./footer.jsp" />
	<!-- /footer -->

	<!-- Placed at the end of the document so the pages load faster -->
	<!-- /Calendar -->

</body>


</html>