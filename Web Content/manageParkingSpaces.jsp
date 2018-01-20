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
    $(document).ready(function () {
        //initialize jTable
        $('#ParkingSpaceContainer').jtable({
            title: 'Parking Space Details',
			sorting:true,
		//	paging:true,
			// pagesize:10,
            actions: {
                listAction: 'ManageParkingSpaceServlet?action=list',
                updateAction: 'ManageParkingSpaceServlet?action=update',
                deleteAction: 'ManageParkingSpaceServlet?action=delete'
            },
            fields: {
                po_id: {
					title:'PO_id',
					list: false,
					edit: false,
					key: true
                },
 				po_name: {
	                    title: 'Parking Owner Name',
	                    width: '20%',
						edit: false
	            },
				po_state: {
		                    title: 'State',
		                    width: '10%',
							edit: false
		       },
				po_city: {
			                title: 'City',
			                width: '10%',
							edit: false
			    },
				p_id: {
				             title: 'Parking id',
							list: false,
							key: true
				},
				p_name: {
					         title: 'Parking Name',
					         width: '20%',
							edit: true
				},
				p_address: {
						      title: 'Address',
						      width: '20%',
							edit: true
				}
            }
        });     
		$('#ParkingSpaceContainer').jtable('load');
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
		<div id="ParkingSpaceContainer"></div>
</div>

					</div>			
					
				</div>
			</div>
		</div>
	
	
<jsp:include page="./footer.jsp"/>
	
	
</body>
</html>
