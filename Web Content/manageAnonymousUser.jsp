<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.mvc.util.DBConnection"%>
<%@page import="java.sql.*"%>
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
		console.log('jjjj');
		$('#AnonymousUserContainer').jtable({
			title : 'Anonymous User',
			actions : {
				listAction : 'ViewAnonymous?action=list',
				createAction : 'ViewAnonymous?action=create',
				updateAction : 'ViewAnonymous?action=update',
				deleteAction : 'ViewAnonymous?action=delete'
			},
			fields : {
				parkingName:{
					title : 'Parking Space Name',
					list : true,
					edit:false,
					width : '20%',
					create : false
				},
				parkingNameList : {
					title : 'Parking Space Name',
					list:false,
					options: function(){
						var options = [];
						 
                        $.ajax({ //Not found in cache, get from server
                            url: 'GenerateList?array=parking',
                            type: 'POST',
                            dataType: 'json',
                            async: false,
                            success: function (data) {
                                options = data.Options;
                            }
                        });
                         
                        return options;
					}
				},
				fullName : {
					title : 'Full Name',
					width : '20%',
					edit : true
				},
				email : {
					title : 'Email',
					width : '20%',
					edit : false
				},
				password : {
					title : 'Password',
					list : true,
					type:'password',
					input : function(data) {
						return '<input type="password" name="password">'
					},
					width : '20%'
				},
				contactNo : {
					title : 'Contact Number',
					width : '20%',
					edit : true
				}
			}
		});
		$('#AnonymousUserContainer').jtable('load');
		function callLogin(email, password) {
			console.log(email);
			console.log(password)
			//document.location = 'LoginANonymous=' + id;
		}
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
						<div id="AnonymousUserContainer"></div>
						<!-- /widget-content -->
					</div>
					<!-- /widget -->
					<!-- /widget -->
				</div>
				<!-- /span6 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>


	<!-- /Main -->
	<!-- footer -->
	<jsp:include page="./footer.jsp" />
	<!-- /footer -->

	<!-- Placed at the end of the document so the pages load faster -->
	
	<script type="text/javascript">
		function nameOptions() {
		}
		function callLogin(email, password) {
			//document.location = 'LoginANonymous=' + id;
		}
		function myclick(table) {
			console.log('aaaaaa');
		}
	</script>
</body>
</html>