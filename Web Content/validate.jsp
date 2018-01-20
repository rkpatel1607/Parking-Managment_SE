<!-- RAHUL -->
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page import="com.mvc.util.DBConnection"%>

<%
	Connection con = DBConnection.createConnection();
	PreparedStatement ps = con.prepareStatement("select * from users where username = ? and password =?");
	ps.setString(1, request.getParameter("username"));
	ps.setString(2, request.getParameter("password"));
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {

		String username = request.getParameter("username");
		session.setAttribute("username", username);
		RequestDispatcher rd = request.getRequestDispatcher("vehicleOwnerDashboard.jsp");
		rd.forward(request, response);
	} else {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
%>