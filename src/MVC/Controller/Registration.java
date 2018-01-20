package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.util.DBConnection;
import com.mvc.util.EncryptPassword;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Connection conn = null;
	public Statement stmt = null;
	public String email, pass, loginType, fName, lName, address, city, zip, state, contactno;
	public ResultSet res = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		PrintWriter out = response.getWriter();

		try {

			Connection conn = DBConnection.createConnection();
			String mobile = (String) request.getParameter("mobile");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from users where username= '" + username + "'");
			rs.next();
			int user_id = rs.getInt("user_id");
			System.out.println(user_id);
			String sql = "Update vehicleowner set vo_contactno = ? WHERE users_user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mobile);
			ps.setInt(2, user_id);
			ps.executeUpdate();

			out.print("<p style=\"color:Green\">Mobile number updated successfully!!</p>");
			RequestDispatcher rd = request.getRequestDispatcher("/Profile.jsp");
			rd.include(request, response);
			st.close();
			conn.close();

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = null;
			fName = request.getParameter("firstname");
			lName = request.getParameter("lastname");
			email = request.getParameter("email");
			pass = EncryptPassword.encryption(request.getParameter("password"));
			address = request.getParameter("address");
			city = request.getParameter("city");
			state = request.getParameter("state");
			zip = (String) request.getParameter("zipcode");
			contactno = (String) request.getParameter("number");
			loginType = request.getParameter("loginType");

			conn = DBConnection.createConnection();
			stmt = conn.createStatement();
			if (loginType.equals("owner"))
				if (loginType.equals("owner"))
					parkingOwner(request, response);
			if (loginType.equals("user"))
				vehicleOwner(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void vehicleOwner(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			stmt.executeUpdate(
					"insert into users (`username`, `password`, `type`) values('" + email + "','" + pass + "','user')");
			res = null;
			res = stmt.executeQuery("select user_id from users where (username='" + email + "')");
			res.next();
			int u_id = res.getInt("user_id");
			stmt.executeUpdate(
					"insert into vehicleowner (`VO_fullName`,`VO_contactNo`,`Users_user_id`,`VO_address`,`VO_city`,`VO_state`,`VO_zip`) values('"
							+ fName + " " + lName + "','" + contactno + "','" + u_id + "','" + address + "','" + city
							+ "','" + state + "','" + zip + "')");

			request.getSession().setAttribute("userName", email);
			request.getSession().setAttribute("username", email);
			request.getSession().setAttribute("user_id", Integer.toString(u_id));
			request.getSession().setAttribute("uName", fName + " " + lName);
			request.getRequestDispatcher("/vehicleOwnerDashboard.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void parkingOwner(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			stmt.executeUpdate("insert into users (`username`, `password`, `type`) values('" + email + "','" + pass
					+ "','owner')");
			res = null;
			res = stmt.executeQuery("select user_id from users where (username='" + email + "')");
			res.next();
			int u_id = res.getInt("user_id");
			stmt.executeUpdate(
					"insert into parkingowner (`PO_fullName`,`PO_contactNo`,`Users_user_id`,`PO_address`,`PO_city`,`PO_state`,`PO_zip`) values('"
							+ fName + " " + lName + "','" + contactno + "','" + u_id + "','" + address + "','" + city
							+ "','" + state + "','" + zip + "')");

			request.getSession().setAttribute("userName", email);
			request.getSession().setAttribute("uName", fName + " " + lName);
			request.getSession().setAttribute("username", email);
			request.getSession().setAttribute("user_id", Integer.toString(u_id));
			request.getRequestDispatcher("/parkingOwnerDashboard.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
