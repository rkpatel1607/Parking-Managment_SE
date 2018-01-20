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

@WebServlet("/ParkingSpaceApproval")
public class ParkingSpaceApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ParkingSpaceApproval() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Connection conn = null;
		try {
			String output = "";
			conn=DBConnection.createConnection();
			Statement statement = conn.createStatement();
			System.out.println("1");
			String query = "select P.P_id,PO.PO_fullName,P.P_name,P.P_address,P.Status from parking P,parkingowner PO where P.Status='pending' and P.Owner_PO_id=PO.PO_id";
			statement.executeQuery(query);
			ResultSet rs = statement.getResultSet();
			System.out.println("2");
			output = output + "<table class='table table-striped table-list' height=30px border=5px'>";
			//output=output+ "<thead class='thead-dark'><tr><th scope='col'>Parking Owner Name</th><th scope='col'>Parking Name</th><th scope='col'>Address</th><th scope='col'>Status</th><th scope='col'>Action</th>";
			
			output = output
					+ "<th>Parking Owner Name</th><th>Parking Name</th><th>Address</th><th>Status</th><th>Action</th>";

			System.out.println("3");
			while (rs.next()) {
				System.out.println("5");
				output = output + "<tr>";
				String Parking_Owner_Name = rs.getString("PO_fullName");
				String Parking_Name = rs.getString("P_name");
				String Parking_Address = rs.getString("P_address");
				String status=rs.getString("Status");

				System.out.println("Inside try");

				output = output + "<td>" + Parking_Owner_Name + "</td><td>"
						+ Parking_Name + "</td><td>" + Parking_Address + "</td><td>" + status + "</td>";
				
				output = output + "<td><button class='btn btn-primary' onclick=\"editRecord(" + rs.getInt("P_id")
				+ ");\"> Check Details</button></td>";
				
				output = output + "</tr>";
			}
			output = output + "</table>";
			
			rs.close();
			out.println(output);
			System.out.println("4");
			statement.close();

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

}
