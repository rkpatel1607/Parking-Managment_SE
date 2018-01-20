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
 * Servlet implementation class AnonymousUser
 */
@WebServlet("/AnonymousUser")
public class AnonymousUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnonymousUser() {
        super();
        // TODO Auto-generated constructor stub
    }
	public String getData(){
		String output="";
		try {
	         Connection conn = DBConnection.createConnection();
	         Statement stmt = conn.createStatement();
	         String sql;
	         SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
	         Date date=new Date();
	         sql = "select * from Booking where B_date='"+format.format(date).toString()+"' order by B_id desc";
	         ResultSet rs = stmt.executeQuery(sql);
	         output=output+"<table class='table table-striped table-list' height=30px border=1px>";
	         output=output+"<th>Date</th><th>Booking Id</th><th>Vehicle Type</th><th>Registration Number</th><th>Contact Number</th><th>QR Code</th><th>Name</th>";
	         while(rs.next()){
	        	 	output=output+"<tr>";
	        	 	int B_id  = rs.getInt("B_id");
	        	 	String B_vehicleType  = rs.getString("B_vehicleType");
	        	 	String B_date  = rs.getString("B_date");
	        	 	String contact  = rs.getString("B_contact_no");
	        	 	String Name  = rs.getString("B_Name");
	        	 	String RegNo  = rs.getString("B_RegNo");
	        	 	output=output+"<td>"+B_date+"</td><td>"+B_id+"</td><td>"+B_vehicleType+"</td><td>"+RegNo+"</td><td>"+contact+"</td><td><img alt='' src='https://chart.googleapis.com/chart?cht=qr&chl="+RegNo+"&chs=50x50&chld=L|0 '></td><td>"+Name+"</td>";
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		out.println(getData());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name"); 
		String contact=request.getParameter("mobile_number"); 
		String reg=request.getParameter("RegistrationNo"); 
		String type=request.getParameter("type");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		String dateStr=dateFormat.format(date);
		String ds[]=dateStr.split(" ");
		String todays_date=ds[0];
		String entry_time=ds[1];
		String est_entry="00:00";
		String est_exit="00:00";
		String act_exit="00:00";
	    try {
	         Connection conn = DBConnection.createConnection();
	         Statement stmt = conn.createStatement();
	         String sqlBooking;
	         sqlBooking="insert into Booking(B_vehicleType,B_date,B_actExit,B_actEntry,B_estExit,B_estEntry,B_contact_no,B_RegNo,B_Name,B_bookingDate) values('"+type+"','"+todays_date+"','"+act_exit+"','"+entry_time+"','"+est_entry+"','"+est_exit+"','"+contact+"','"+reg+"','"+name+"','"+todays_date+"')";
	         stmt.executeUpdate(sqlBooking);
	         out.println(getData());
	         stmt.close();
	         conn.close();
	      } catch(SQLException se) {
	         se.printStackTrace();
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	   
		
	}

}
