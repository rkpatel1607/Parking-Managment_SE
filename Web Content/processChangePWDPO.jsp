<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<%@ page import="com.mvc.util.DBConnection"%>
<%@page import="com.mvc.util.EncryptPassword" %>
<%
	String password = EncryptPassword.encryption(request.getParameter("OldPassword"));
	String Newpass = EncryptPassword.encryption(request.getParameter("newpassword"));
	String conpass = EncryptPassword.encryption(request.getParameter("conpassword"));

	Connection con = null;
	Statement st = null;
	String pass = "";
	int id = 0;
	try {
		con = DBConnection.createConnection();
		st = con.createStatement();
		String username = session.getAttribute("username").toString();
		ResultSet rs = st.executeQuery("select * from users where username= '" + username + "'");
		if (rs.next()) {
			pass = rs.getString("password");
		}
		if (Newpass.equals(conpass)) {
			if (pass.equals(password)) {
				st = con.createStatement();
				int i = st.executeUpdate(
						"update users set password='" + Newpass + "' WHERE username='" + username+"'");
				request.getRequestDispatcher("/parkingOwnerDashboard.jsp").forward(request, response);
				st.close();
				con.close();
			} else {
				out.print("<p style=\"color:red\">Please check your inputs!!</p>");  
		        RequestDispatcher rd=request.getRequestDispatcher("/changePasswordPO.jsp");    
		        rd.include(request,response);
			}
			/*}else{
			out.println("new password and confirm new password is not matching");
			}*/
		}
	} catch (Exception e) {
		out.println(e);
	}
%>