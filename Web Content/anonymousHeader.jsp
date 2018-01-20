<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<title>Parking Zone:Car Parking Service Provider</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	
<link rel='stylesheet' href='css/bootstrap2.css'	type='text/css' media='all' />
<link rel='stylesheet' href='css/font-awesome.css' type='text/css' media='all' />
<link rel='stylesheet' href='css/reset.css'	type='text/css' media='all' />
<link rel='stylesheet' href='css/style2.css' type='text/css' media='all' /> 
<script src="js/bootstrap.js"></script>
<script type='text/javascript' src='js/jquery.js'></script>

<style type="text/css">
body {
	background:
		url('img/bodybg.png');
}
</style>

</head>
<body class="home blog">

		<!--Start Container-->
		<div>
			<!--Start Header Wrapper-->
			<div class="row header_wrapper">
				<!--Start Header-->
				<div class="header">
					<div class="col-md-6 col-sm-6">
						<div class="logo">
							<a href="index.jsp">
								<img src="img/logo.jpg"
								alt="Car Parking Service" />
							</a>
						</div>
					</div>
					<div class="col-md-2 col-sm-2"></div>
					<div class="col-md-4 col-sm-4">
						
							<div class="nav-collapse">
        <ul class="nav pull-right">
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="icon-user"></i> <%=session.getAttribute("uName") %> <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="LogoutServlet">Logout</a></li>
            </ul>
          </li>
        </ul>
      </div>
	</div>
					<div class="clear"></div>
					<div class="menu_wrapper col-md-12">
						<div id="MainNav">
							<div id="menu" class="menu-menu-1-container">
								<ul id="menu-menu-1" class="ddsmoothmenu">
									<li id="menu-item-27"
										class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item- menu-item-27"><a
										href="anonymousDashboard.jsp">Dashboard</a></li>
									<li id="menu-item-31"
										class="menu-item menu-item-type-post_type menu-item-object-page menu-item-31"><a
										href="AnonymousExit.jsp">Exit Time Entry</a>
									</li>
									
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
