package com.anonymous;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

/**
 * Servlet implementation class OnlineBookingEntry
 */
@WebServlet("/OnlineBookingEntry")
public class OnlineBookingEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnlineBookingEntry() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getData(){
		String output="";
		try {
	         Connection conn = DBConnection.createConnection();
	         Statement stmt = conn.createStatement();
	         String sql;
	         sql = "select * from Booking order by B_id  desc";
	         ResultSet rs = stmt.executeQuery(sql);
	         output=output+"<table class='table table-striped table-list' height=30px border=1px>";
	         output=output+"<th>Date</th><th>Booking Id</th><th>Entry Time</th><th>Vehicle Type</th><th>Registration Number</th><th>Contact Number</th><th>QR Code</th><th>Name</th>";
	         while(rs.next()){
	        	 	output=output+"<tr>";
	        	 	int B_id  = rs.getInt("B_id");
	        	 	String B_vehicleType  = rs.getString("B_vehicleType");
	        	 	String B_date  = rs.getString("B_date");
	        	 	String B_actEntry  = rs.getString("B_actEntry");
	        	 	String contact  = rs.getString("B_contact_no");
	        	 	String Name  = rs.getString("B_Name");
	        	 	String RegNo  = rs.getString("B_RegNo");
	        	 	output=output+"<td>"+B_date+"</td><td>"+B_id+"</td><td>"+B_actEntry+"</td><td>"+B_vehicleType+"</td><td>"+RegNo+"</td><img alt='' src='https://chart.googleapis.com/chart?cht=qr&chl="+RegNo+"&chs=50x50&chld=L|0 '><td>"+contact+"</td><td>"+Name+"</td>";
	        	 	output=output+"</tr>";
	         }
	         output=output+"</table>";
	         rs.close();
	         stmt.close();
	         conn.close();
	      } catch(SQLException se) {
	         se.printStackTrace();
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		return output;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String bid=request.getParameter("bookingid"); 
		int id= Integer.parseInt(bid);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		String dateStr=dateFormat.format(date);
		String ds[]=dateStr.split(" ");
		//String todays_date=ds[0];
		String entry_time=ds[1];
		String act_exit="00:00";
	    try {
	         Connection conn = DBConnection.createConnection();
	         Statement stmt = conn.createStatement();
	         String sqlBooking;
	         sqlBooking="update booking set B_actEntry='"+entry_time+"', B_actExit='"+act_exit+"' where B_id="+id;
	         stmt.executeUpdate(sqlBooking);
	         out.println("Entered Sucessfully");
	         stmt.close();
	         conn.close();
	      } catch(SQLException se) {
	         se.printStackTrace();
	      } catch(Exception e) {
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
