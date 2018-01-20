package com.anonymous;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

/**
 * Servlet implementation class SearchVehicleServlet
 */
@WebServlet("/SearchVehicleServlet")
public class SearchVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchVehicleServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("vehicle_id");
		PrintWriter out = response.getWriter();
		String output = "";
		try {
			Connection conn = DBConnection.createConnection();
			Statement stmt = conn.createStatement();
			String sql;
			SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
	         Date date=new Date();
	         sql = "select * from Booking where B_date='"+format.format(date).toString()+"' and B_regNo='" + id + "' and B_actExit='00:00' order by B_id desc";
			//sql = "select * from Booking where B_regNo='" + id + "' and B_actExit='00:00'";
			ResultSet rs = stmt.executeQuery(sql);
			// while (rs.next()) {
			if (rs.next()) {

				output = output + "<table class='table table-striped table-list' height=30px border=1px>";
				output = output
						+ "<th>Date</th><th>Booking Id</th><th>Entry Time</th><th>Vehicle Type</th><th>Registration Number</th><th>Contact Number</th><th>QR Code</th><th>Name</th>";
				output = output + "<tr>";
				int B_id = rs.getInt("B_id");
				String B_vehicleType = rs.getString("B_vehicleType");
				String B_date = rs.getString("B_date");
				String B_actEntry = rs.getString("B_actEntry");
				String contact = rs.getString("B_contact_no");
				String Name = rs.getString("B_Name");
				String RegNo = rs.getString("B_RegNo");
				output = output + "<td>" + B_date + "</td><td>" + B_id + "</td><td>" + B_actEntry + "</td><td>"
						+ B_vehicleType + "</td><td>" + RegNo + "</td><td>" + contact + "</td><td><img alt='' src='https://chart.googleapis.com/chart?cht=qr&chl="+RegNo+"&chs=50x50&chld=L|0 '></td><td>" + Name + "</td>";
				output = output + "</tr>";
				// }
				output = output + "</table>";
				rs.close();
				stmt.close();
				conn.close();
			} else {
				out.println("No booking Found");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
