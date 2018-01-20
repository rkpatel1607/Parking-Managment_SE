<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>My Profile</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery-1.7.2.min.js"></script>
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<style>
   body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif;}
   body {font-size:16px;} 
</style>
</head>
<body>
<%
				if(session.getAttribute("username")==null){
					
					response.sendRedirect("adminLogin.jsp");
					
				}
			
			%>
<jsp:include page="./adminHeader.jsp"/>
<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
      <ul class="mainnav">
        <li class="active"><a href="adminDashboard.jsp"><i class="icon-dashboard"></i><span>Dashboard</span> </a> </li>
       <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-automobile"></i><span>Manage</span> 
       <b class="caret"></b></a>
        <ul class="dropdown-menu">
            <li><a href="#">Vehicle Types</a> </li>
            <li><a href="manageUsers.jsp">Users</a> </li>
            <li><a href="manageVehicleDetails.jsp">Vehicle Details </a> </li>
            <li><a href="manageParkingSpaces.jsp">Parking Spaces </a> </li>
          </ul>
        </li>
        <li><a href="#"><i class="fa fa-automobile"></i><span>Approve Parking Space</span></a> </li>
         
      </ul>
    </div>
    <!-- /container --> 
  </div>
  <!-- /subnavbar-inner --> 
</div>
<div style="width:80%;margin-right:10%; font-size:50px; color: #609084; margin-left:10%;text-align:center;">
	<b>Welcome to Admin Section </b>
</div>
<jsp:include page="./footer.jsp"/>
</body>
</html>