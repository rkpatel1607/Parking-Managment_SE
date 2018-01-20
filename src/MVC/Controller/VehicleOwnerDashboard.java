package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

/**
 * Servlet implementation class VehicleOwnerDashboard
 */
@WebServlet("/VehicleOwnerDashboard")
public class VehicleOwnerDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet rSet = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VehicleOwnerDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = "";
		String id = (String) request.getSession().getAttribute("user_id");
		PrintWriter out = null;
		try {
			out = response.getWriter();

			connection = DBConnection.createConnection();
			statement = connection.createStatement();
			String sql = "select * from booking where B_flag=1 and  VehicleOwner_VO_id=(select VO_id from vehicleowner where Users_user_id="+Integer.parseInt(id)+")";
			rSet = statement.executeQuery(sql);

			output = output + "<table class='table table-striped table-list' height=30px border=1px>";
			output = output + "<th>Booking ID</th><th>Vehicle Type</th><th>Registration Number</th><th>Date</th>";

			while (rSet.next()) {
				output = output + "<tr>";

				output = output + "<td>" + rSet.getString("B_id") + "</td><td>" + rSet.getString("B_vehicleType")
						+ "</td><td>" + rSet.getString("B_regNo") + "</td><td>" + rSet.getString("B_date") + "</td>";

				output = output + "</tr>";
			}
			output = output + "</table>";

			/*
			 * if (!rSet.isBeforeFirst() ) { output=null; }
			 */

			statement.close();
			rSet.close();

			out.println(output);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
