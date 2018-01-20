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
		$('#UserTableContainer1').jtable({
			title : 'Vehicle Owners',
			sorting : true,
			actions : {
				listAction : 'ManageVOServlet?action=list',
				updateAction : 'ManageVOServlet?action=update',
				deleteAction : 'ManageVOServlet?action=delete'
			},
			fields : {
				userid : {
					title : 'User Id',
					key : true,
					list : true,
					width : '10%'
				},
				fullName : {
					title : 'Full Name',
					width : '15%',
					edit : true
				},
				contact : {
					title : 'Contact',
					width : '10%',
					edit : true
				},
				email : {
					title : 'Email Id',
					width : '10%',
					edit : true
				},
				username : {
					title : 'User Name',
					width : '5%',
					list : false,
					edit : false
				},
				password : {
					title : 'Password',
					width : '5%',
					type : 'password',
					list : false,
					edit : false
				},
				address : {
					title : 'Address',
					width : '5%',
					list : false,
					edit : true
				},
				city : {
					title : 'City',
					width : '5%',
					list : false,
					edit : true
				},
				state : {
					title : 'State',
					width : '5%',
					list : false,
					edit : true
				},
				zip : {
					title : 'Zip Code',
					width : '5%',
					list : false,
					edit : true
				},
				type : {
					title : 'User Type',
					width : '10%',
					edit : true
				}
			}
		});
		$('#UserTableContainer1').jtable('load');
	});

	$(document).ready(function() {
		//initialize jTable
		$('#UserTableContainer2').jtable({
			title : 'Parking Attendant',
			sorting : true,
			actions : {
				listAction : 'ManageAUServlet?action=list',
				updateAction : 'ManageAUServlet?action=update',
				deleteAction : 'ManageAUServlet?action=delete'
			},
			fields : {
				userid : {
					title : 'User Id',
					key : true,
					list : true,
					width : '10%'
				},
				fullName : {
					title : 'Full Name',
					width : '15%',
					edit : true
				},
				contact : {
					title : 'Contact',
					width : '10%',
					edit : true
				},
				email : {
					title : 'Email Id',
					width : '10%',
					edit : true
				},
				username : {
					title : 'User Name',
					width : '5%',
					list : false,
					edit : false
				},
				password : {
					title : 'Password',
					width : '5%',
					type : 'password',
					list : false,
					edit : false
				},
				parkingname : {
					title : 'Parking Name',
					width : '15%',
					list : true,
					edit : true
				},
				type : {
					title : 'User Type',
					width : '10%',
					edit : true
				}
			}
		});
		$('#UserTableContainer2').jtable('load');
	});

	$(document).ready(function() {
		//initialize jTable
		$('#UserTableContainer').jtable({
			title : 'Parking Owners',
			sorting : true,
			actions : {
				listAction : 'ManageUserServlet?action=list',
				updateAction : 'ManageUserServlet?action=update',
				deleteAction : 'ManageUserServlet?action=delete'
			},
			fields : {
				userid : {
					title : 'User Id',
					key : true,
					list : true,
					width : '10%'
				},
				fullName : {
					title : 'Full Name',
					width : '10%',
					edit : true
				},
				contact : {
					title : 'Contact',
					width : '10%',
					edit : true
				},
				email : {
					title : 'Email Id',
					width : '10%',
					edit : true
				},
				username : {
					title : 'User Name',
					width : '5%',
					list : false,
					edit : false
				},
				password : {
					title : 'Password',
					width : '5%',
					type : 'password',
					list : false,
					edit : false
				},
				address : {
					title : 'Address',
					width : '5%',
					list : false,
					edit : true
				},
				city : {
					title : 'City',
					width : '5%',
					list : false,
					edit : true
				},
				state : {
					title : 'State',
					width : '5%',
					list : false,
					edit : true
				},
				zip : {
					title : 'Zip Code',
					width : '5%',
					list : false,
					edit : true
				},
				type : {
					title : 'User Type',
					width : '10%',
					edit : true
				},
				count : {
					title : 'No. of Parking Spaces',
					width : '15%',
					edit : false,
					create : false,
					list : true
				}
			}
		});
		$('#UserTableContainer').jtable('load');
	});
</script>


</head>
<body class="home blog">
<%
				if(session.getAttribute("username")==null){
					
					response.sendRedirect("adminLogin.jsp");
					
				}
			
			%>
	<div class="body-content container">
			<jsp:include page="./adminHeader.jsp"/>	
		
		<div class="row home_content_wrapper">
			<div class="feature_content col-md-12">
				<div class="two_col-div row">
					
					<div
		style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center; margin-bottom: 65px;">
		<div id="UserTableContainer"></div>
		<br /> <br />
		<div id="UserTableContainer1"></div>
		<br /> <br />
		<div id="UserTableContainer2"></div>
	</div>

					</div>			
					
				</div>
			</div>
		</div>
	
	
<jsp:include page="./footer.jsp"/>
	
	
</body>
</html>
