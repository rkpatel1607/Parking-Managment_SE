package com.mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;
import com.mysql.jdbc.ResultSet;

@WebServlet("/Update")
public class Update extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public Connection conn = null;
	public PreparedStatement stmt = null;
	public String fullName, address, city, zip, state, contactno;
	public ResultSet res = null;
	public int user_id;

	public Update() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in update profile");
		try {
			fullName = request.getParameter("fullname");
			address = request.getParameter("address");
			city = request.getParameter("city");
			state = request.getParameter("state");
			zip = (String) request.getParameter("zipcode");
			contactno = (String) request.getParameter("number");
			user_id = Integer.parseInt(request.getParameter("user_id"));

			conn = DBConnection.createConnection();
			stmt = conn.prepareStatement(
					"update parkingowner set PO_fullName=?,PO_contactNo=?,PO_address=?,PO_city=?,PO_state=?,PO_zip=? where PO_id=?");
			stmt.setString(1, fullName);
			stmt.setString(2, contactno);
			stmt.setString(3, address);
			stmt.setString(4, city);
			stmt.setString(5, state);
			stmt.setString(6, zip);
			stmt.setInt(7, user_id);

			stmt.executeUpdate();

			request.getRequestDispatcher("/parkingOwnerDashboard.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
