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

@WebServlet("/CheckSpaceDetails")
public class CheckSpaceDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckSpaceDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Connection conn = null;
		try {
			String output = "";
			System.out.println(request.getParameter("p_id"));
			int p_id = Integer.parseInt(request.getParameter("id"));
			conn=DBConnection.createConnection();
			Statement statement = conn.createStatement();
			System.out.println("1");
			String query = "select ps.PS_vehicleType,ps.PS_AvailableOnlineSlots,ps.PS_OnlineCharges,ps.PS_AvailableOfflineSlots,ps.PS_OfflineCharges,ps.PS_Tax "
					+ "from parkingspace ps where ps.Parking_P_id="+p_id;
			statement.executeQuery(query);
			ResultSet rs = statement.getResultSet();
			System.out.println("2");
			output = output + "<table class='table table-striped table-list' height=30px border=5px>";
			output = output
					+ "<th>Vehicle Type</th><th>Available Online Slots</th><th>Online Charges($)</th><th>Available Offline Slots</th><th>Offline Charges($)</th><th>Tax(%)</th>";

			System.out.println("3");
			while (rs.next()) {
				System.out.println("5");
				output = output + "<tr>";
				String vehicle_type = rs.getString("PS_vehicleType");
				int online_slots = rs.getInt("PS_AvailableOnlineSlots");
				int online_charges = rs.getInt("PS_OnlineCharges");
				int offline_slots = rs.getInt("PS_AvailableOfflineSlots");
				int offline_charges = rs.getInt("PS_OfflineCharges");
				int tax=rs.getInt("PS_Tax");

				System.out.println("Inside try");

				output = output + "<td>" + vehicle_type + "</td><td>"
						+ online_slots + "</td><td>" + online_charges + "</td><td>" + offline_slots + "</td><td>" + offline_charges + "</td><td>" + tax + "</td>";
				
				//output = output + "<td><a href='ActionSpaceServlet?action=approve&id="+p_id+"'> Approve</a>/<a href='ActionSpaceServlet?action=decline&id="+p_id+"'> Decline</a></td>";
				
				output = output + "</tr>";
			}
			output = output + "</table>";
			
			rs.close();
			out.println(output);
			System.out.println("4");
			statement.close();

		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
