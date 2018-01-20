package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.mvc.util.DBConnection;
import com.mvc.util.SendEmail;

/**
 * Servlet implementation class Bookparking
 */
@WebServlet("/Bookparking")
public class Bookparking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Statement stmt = null;
	public ResultSet res = null;

	public Bookparking() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	public static boolean isTimeWith_in_Interval(String valueToCheck, String endTime, String startTime) {
		boolean isBetween = false;
		try {
			Date time2 = new SimpleDateFormat("HH:mm").parse(startTime);
			System.out.println(time2);

			Date time1 = new SimpleDateFormat("HH:mm").parse(endTime);
			System.out.println(time1);

			Date d = new SimpleDateFormat("HH:mm").parse(valueToCheck);
			System.out.println(d);

			if (time1.after(d) && time2.before(d)) {
				isBetween = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isBetween;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		request.getSession();
		String username = request.getParameter("username");

		try {
			String regno = request.getParameter("regno");
			String parkingname = request.getParameter("parkingname");
			String entry = request.getParameter("entry");
			String exit = request.getParameter("exit");
			System.out.println(entry);

			String[] entrydate = entry.split("T");
			System.out.println(entrydate[0]);
			System.out.println(entrydate[1]);
			LocalDate d1 = new LocalDate(entrydate[0]);

			String[] exitdate = exit.split("T");
			System.out.println(exitdate[0]);
			System.out.println(exitdate[1]);
			LocalDate d2 = new LocalDate(exitdate[0]);
			int diffDate = Days.daysBetween(d1, d2).getDays();
			System.out.println("Days are:-" + diffDate);
			Connection conn = DBConnection.createConnection();
			Statement st = conn.createStatement();
			ResultSet rs1 = st.executeQuery(
					"select * from parkingSpace where parking_p_id = (select p_id from parking where p_name = '"
							+ parkingname + "')");
			rs1.next();
			int p_id = rs1.getInt("parking_p_id");
			System.out.println(p_id);

			System.out.println(username);
			Statement st2 = conn.createStatement();
			ResultSet rs3 = st2.executeQuery(
					"select * from vehicleowner where users_user_id = (select user_id from users where username = '"
							+ username + "')");
			rs3.next();
			int vo_id = rs3.getInt("vo_id");
			String name = rs3.getString("vo_fullname");
			String contact = rs3.getString("vo_contactno");

			Statement st3 = conn.createStatement();
			ResultSet rs4 = st3.executeQuery("select * from vehicle where v_regno =  '" + regno + "'");
			rs4.next();
			String vtype = rs4.getString("v_type");

			int onlineslot = rs1.getInt("ps_availableonlineslots");
			Statement checkSlots = conn.createStatement();

			if (onlineslot < 1) {
				System.out.println("No slots available at this location");
				out.print("<p style=\"color:red\">No slots available at this location!!</p>");
				RequestDispatcher rd = request.getRequestDispatcher("/bookparking.jsp");
				rd.include(request, response);
			} else {
				ResultSet rs2 = st.executeQuery("select * from parking where p_id = '" + p_id + "'");
				rs2.next();
				String opentime = rs2.getString("p_opentime");
				String closetime = rs2.getString("p_closetime");

				Boolean b1 = isTimeWith_in_Interval(entrydate[1], closetime, opentime);
				System.out.println(b1);
				Boolean b2 = isTimeWith_in_Interval(exitdate[1], closetime, opentime);
				System.out.println(b2);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				System.out.println(dateFormat.format(date)); // 2016/11/16 12:08:43
				LocalDate checkingDate = d1;
				ResultSet rs5 = checkSlots
						.executeQuery("select count(*) from booking where B_date='" + checkingDate.toString() + "'");
				rs5.next();
				int bookedSlots = rs5.getInt(1);
				boolean isTrue = false;
				for (int i = 0; i < diffDate; i++) {
					if (onlineslot > bookedSlots) {
						checkingDate = checkingDate.plusDays(1);
						isTrue = true;
					} else {
						isTrue = false;
						break;
					}
				}
				System.out.println("condition written " + isTrue);

				if (b1.equals(true) && (b2.equals(true)) && isTrue) {
					System.out.println(d1.toString());
					String sql = "insert into booking(b_estentry,b_estexit,b_date,b_vehicletype,vehicleowner_vo_id,parking_p_id,b_contact_no,b_regno,b_name,b_bookingtype,B_bookingDate,B_flag) values(?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					String datefor = entrydate[0];
					String dateforex = exitdate[0];

					ps.setString(1, entrydate[1]);
					ps.setString(2, exitdate[1]);
					ps.setString(3, d1.toString());
					ps.setString(4, vtype);
					ps.setInt(5, vo_id);
					ps.setInt(6, p_id);
					ps.setString(7, contact);
					ps.setString(8, regno);
					ps.setString(9, name);
					ps.setString(10, "ONLINE");
					ps.setString(11, dateFormat.format(date));
					ps.setInt(12, 1);
					ps.executeUpdate();
					d1 = d1.plusDays(1);

					for (int i = 1; i <= diffDate; i++) {
						System.out.println(d1.toString());
						sql = "insert into booking(b_estentry,b_estexit,b_date,b_vehicletype,vehicleowner_vo_id,parking_p_id,b_contact_no,b_regno,b_name,b_bookingtype,B_bookingDate) values(?,?,?,?,?,?,?,?,?,?,?)";
						ps = conn.prepareStatement(sql);
						ps.setString(1, entrydate[1]);
						ps.setString(2, exitdate[1]);
						ps.setString(3, d1.toString());
						ps.setString(4, vtype);
						ps.setInt(5, vo_id);
						ps.setInt(6, p_id);
						ps.setString(7, contact);
						ps.setString(8, regno);
						ps.setString(9, name);
						ps.setString(10, "ONLINE");
						ps.setString(11, dateFormat.format(date));
						ps.executeUpdate();
						d1 = d1.plusDays(1);
					}
					String sql17 = "Select * from booking where B_date='" + datefor + "' and B_flag=1 and B_name='"
							+ name + "'";
					System.out.println(sql17);

					ResultSet rs17 = st.executeQuery(sql17);
					rs17.next();
					int bid = rs17.getInt("B_id");
					// SendEmail email=new SendEmail();
					String data = "";
					data = name + "," + contact + "," + regno + "," + parkingname + "," + datefor + "," + dateforex
							+ "," + bid +","+regno;
					System.out.println(data + username);
					out.print("<p style=\"color:Green\">Booking Successful!!</p>");
					RequestDispatcher rd = request.getRequestDispatcher("/bookparking.jsp");
					rd.include(request, response);
					SendEmail.send(username, data);
					st.close();
					conn.close();

				} else {
					System.out.println(
							"No Slots availble between the dates you have choosen. Please change the dates and try Again.");
					out.print("<html><head>");
					out.print("<script type=\"text/javascript\">alert(\"No Slots availble between the dates you have choosen. Please change the dates and try Again.\");</script>");
					out.print("</head><body></body></html>");
					RequestDispatcher rd = request.getRequestDispatcher("/bookparking.jsp");
					rd.include(request, response);
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

}
