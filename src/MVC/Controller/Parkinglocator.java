package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

@WebServlet("/Parkinglocator")
public class Parkinglocator extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Parkinglocator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getData() {
		String output = "";
		try {
			Connection conn = DBConnection.createConnection();
			Statement stmt = conn.createStatement();
			String sql;
			sql = "select * from parking";
			ResultSet rs = stmt.executeQuery(sql);
			output = output + "<table class='table table-striped table-list' height=30px border=1px>";
			output = output
					+ "<th>NAME</th><th>Address</th><th>Zip</th><th>Opening time</th><th>Closing time</th>";
			while (rs.next()) {
				output = output + "<tr>";
				//int P_id = rs.getInt("P_id");
				String P_Name = rs.getString("P_Name");
				//String P_State = rs.getString("P_State");
				//String P_City = rs.getString("P_City");
				String Address = rs.getString("P_Address");
				String P_Zip = rs.getString("P_Zip");
				String P_opentime = rs.getString("P_opentime");
				String P_closetime = rs.getString("P_closetime");
				output = output + "<td>" + P_Name + "</td><td>" + Address + "</td><td>" + P_Zip + "</td><td>" + P_opentime
						+ "</td><td>" + P_closetime + "</td>";
				output = output + "</tr>";
			}
			output = output + "</table>";
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public String getData1(String state, String city) {

		String output = "";
		System.out.println(state);
		System.out.println(city);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DBConnection.createConnection();
			Statement stmt = conn.createStatement();
			String sql;
			sql = "select * from parking where p_state = '" + state + "' and p_city = '" + city + "'";
			ResultSet rs = stmt.executeQuery(sql);
			output = output + "<table class='table table-striped table-list' height=30px border=1px>";
			output = output
					+ "<th>P_ID</th><th>P_NAME</th><th>P_State</th><th>P_City</th><th>P_Address</th><th>P_Zip</th><th>Opentime</th>";
			while (rs.next()) {
				output = output + "<tr>";
				int P_id = rs.getInt("P_id");
				String P_Name = rs.getString("P_Name");
				String P_State = rs.getString("P_State");
				String P_City = rs.getString("P_City");
				String Address = rs.getString("P_Address");
				String P_Zip = rs.getString("P_Zip");
				String P_opentime = rs.getString("P_opentime");
				output = output + "<td>" + P_id + "</td><td>" + P_Name + "</td><td>" + P_State + "</td><td>" + P_City
						+ "</td><td>" + Address + "</td><td>" + P_Zip + "</td><td>" + P_opentime + "</td>";
				output = output + "</tr>";
			}
			output = output + "</table>";
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println(getData());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String state = request.getParameter("country");
//		String city = request.getParameter("state");
		//System.out.println(state);
		//System.out.println(city);

		// RequestDispatcher rd=request.getRequestDispatcher("/parkingLocator.jsp");
		// rd.include(request,response);
//		PrintWriter out = response.getWriter();
//		out.println(getData1(state, city));
//		RequestDispatcher rd=request.getRequestDispatcher("/parkingLocator.jsp");    
//        rd.include(request,response);
		

	}

}
