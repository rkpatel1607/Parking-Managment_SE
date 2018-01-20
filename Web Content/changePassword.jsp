
<%@page import="java.sql.*"%>
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
	</head>
<body class="home blog">

	<%
		if (session.getAttribute("username") == null) {

			response.sendRedirect("home.jsp");

		}
	%>

	<div class="body-content container">
	<jsp:include page="./vehicleOwnerHeader.jsp" />
	
	<div class="row home_content_wrapper">
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
				<div class="col-md-4 col-sm-4 feature_box"></div>
					<div class="col-md-4 col-sm-4">
					<div class="feature_inner" style="color: black; height: 450px;">
					<div class="widget_inner">
					      				<h3>Update Password</h3>
					  				</div>
				
				
											<form method="post" action="Password">
														
															<div class="login-fields">
															<div class="field">											
																<label for="username" >User Name</label>
																	<input type="text"  class="login username-field" style="width: 300px;" name="username" value="<%=session.getAttribute("username")%>" readonly="readonly" required="required">			
															</div> <!-- /control-group -->
															
															<div class="field">											
																<label for="cpassword" >Current Password</label>
															
																	<input type="password"  class="login username-field"  style="width: 300px;"  id="cpassword" name="cpassword" placeholder="Current Password">
																			
															</div> <!-- /control-group -->
															
															
															<div class="field">											
																<label for="password1" >New Password</label>
														
																	<input type="password"  class="login username-field" id="password1"  style="width: 300px;"  name="password1" placeholder="New Password">
																		
															</div> <!-- /control-group -->
															
															
															<div class="field">											
																<label for="password2" >Confirm Password</label>
																
																	<input type="password"  class="login username-field" id="password2"  style="width: 300px;"  name="password2" placeholder="Confirm Password">
																			
															</div> <!-- /control-group -->
															</div>
															<div class="login-actions">
																<button type="submit" class="btn btn-group-justified btn-large button" style=" background-color: #1d3c50; color: white; margin-top: 20px;" id="submit">Update</button> 
																
															</div> 
											</form>
												</div>
													
													
														
											  
											</div>
																	
										</div> <!-- /widget-content -->
											<div id="responseNote"></div>
									</div> <!-- /widget -->
						      		
							    </div> <!-- /span8 -->
						      	
						      	</div>
						      	
						      	</div>
						      	
						      	
						      </div> <!-- /row -->
						
						    </div> <!-- /container -->
	    
				</div> <!-- /main-inner -->
    
			</div> <!-- /main -->

			<!-- footer -->
			<jsp:include page="./footer.jsp"/>
			<!-- /footer --> 
 
	</body>
</html>
