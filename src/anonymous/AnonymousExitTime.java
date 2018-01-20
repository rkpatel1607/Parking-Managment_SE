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
import javax.swing.JTextPane;

import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDateTime;

import com.mvc.util.DBConnection;

/**
 * Servlet implementation class AnonymousExitTime
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AnonymousExitTime" })
public class AnonymousExitTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnonymousExitTime() {
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
		String id = request.getParameter("vehicle_id");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String output = "";
		try {
			Connection conn = DBConnection.createConnection();
			Statement stmt = conn.createStatement();
			String sql;
			sql = "select * from booking where B_regNo='" + id + "' and B_actExit='00:00'";
			ResultSet rs = stmt.executeQuery(sql);
			int parking_id = 0;
			String B_vehicleType = "";
			int booking_id = 0;
			String parkingName = "";
			String parkingAddress = "";
			String name = "";
			int diffHours = 1;
			int days = 0;
			float total = 0;
			float Tax = 0;
			float totalwithTax = 0;
			// while (rs.next()) {
			if (rs.next()) {
				parking_id = rs.getInt("Parking_P_id");
				booking_id = rs.getInt("B_id");
				name = rs.getString("B_name");
				B_vehicleType = rs.getString("B_vehicleType");
				String B_date = rs.getString("B_date");
				String B_time = rs.getString("B_actEntry");
				String dateTimeEntry = "" + B_date + " " + B_time;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date entry = dateFormat.parse(dateTimeEntry);
				Date dateNow = new Date();
				String dateNow_string = dateFormat.format(dateNow);
				dateNow = dateFormat.parse(dateNow_string);
				LocalDateTime entryDate = new LocalDateTime(entry);
				LocalDateTime now = new LocalDateTime(dateNow);
				Days d = Days.daysBetween(entryDate, now);
				Hours hours = Hours.hoursBetween(entryDate, now);
				diffHours = hours.getHours() % 24;
				days = d.getDays();

				sql = "select * from parkingspace , parking where parkingspace.Parking_P_id = parking.P_id and Parking_P_id='"
						+ parking_id + "' and PS_vehicleType='" + B_vehicleType + "'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					parkingName = rs.getString("P_name");
					parkingAddress = rs.getString("P_address");
					int charge_day = rs.getInt("PS_dayCharge");
					int charge_hours = rs.getInt("PS_OfflineCharges");
					Tax = (float) rs.getInt("PS_Tax");
					if (days == 0 && diffHours == 0) {
						diffHours = 1;
					}
					total = (days * charge_day) + (diffHours * charge_hours);
					float Tax_total = (total * (Tax / 100));
					System.out.println(total * (Tax / 100));
					totalwithTax = total + Tax_total;
				}

				Date outputdate = new Date();
				output = "<style>.invoice-box {\r\n" + "        max-width: 800px;\r\n" + "        margin: auto;\r\n"
						+ "        padding: 30px;\r\n" + "        border: 1px solid #eee;\r\n"
						+ "        box-shadow: 0 0 10px rgba(0, 0, 0, .15);\r\n" + "        font-size: 16px;\r\n"
						+ "        line-height: 24px;\r\n"
						+ "        font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\r\n"
						+ "        color: #555;\r\n" + "    }\r\n" + "    \r\n" + "    .invoice-box table {\r\n"
						+ "        width: 100%;\r\n" + "        line-height: inherit;\r\n"
						+ "        text-align: left;\r\n" + "    }\r\n" + "    \r\n" + "    .invoice-box table td {\r\n"
						+ "        padding: 5px;\r\n" + "        vertical-align: top;\r\n" + "    }\r\n" + "    \r\n"
						+ "    .invoice-box table tr td:nth-child(2) {\r\n" + "        text-align: right;\r\n"
						+ "    }\r\n" + "    \r\n" + "    .invoice-box table tr.top table td {\r\n"
						+ "        padding-bottom: 20px;\r\n" + "    }\r\n" + "    \r\n"
						+ "    .invoice-box table tr.top table td.title {\r\n" + "        font-size: 45px;\r\n"
						+ "        line-height: 45px;\r\n" + "        color: #333;\r\n" + "    }\r\n" + "    \r\n"
						+ "    .invoice-box table tr.information table td {\r\n" + "        padding-bottom: 40px;\r\n"
						+ "    }\r\n" + "    \r\n" + "    .invoice-box table tr.heading td {\r\n"
						+ "        background: #eee;\r\n" + "        border-bottom: 1px solid #ddd;\r\n"
						+ "        font-weight: bold;\r\n" + "    }\r\n" + "    \r\n"
						+ "    .invoice-box table tr.details td {\r\n" + "        padding-bottom: 20px;\r\n"
						+ "    }\r\n" + "    \r\n" + "    .invoice-box table tr.item td{\r\n"
						+ "        border-bottom: 1px solid #eee;\r\n" + "    }\r\n" + "    \r\n"
						+ "    .invoice-box table tr.item.last td {\r\n" + "        border-bottom: none;\r\n"
						+ "    }\r\n" + "    \r\n" + "    .invoice-box table tr.total td:nth-child(2) {\r\n"
						+ "        border-top: 2px solid #eee;\r\n" + "        font-weight: bold;\r\n" + "    }\r\n"
						+ "    \r\n" + "    @media only screen and (max-width: 600px) {\r\n"
						+ "        .invoice-box table tr.top table td {\r\n" + "            width: 100%;\r\n"
						+ "            display: block;\r\n" + "            text-align: center;\r\n" + "        }\r\n"
						+ "        \r\n" + "        .invoice-box table tr.information table td {\r\n"
						+ "            width: 100%;\r\n" + "            display: block;\r\n"
						+ "            text-align: center;\r\n" + "        }\r\n" + "    }\r\n"
						+ "</style><table cellpadding=\"0\" cellspacing=\"0\">\r\n"
						+ "										<tr class=\"top\">\r\n"
						+ "											<td colspan=\"2\">\r\n"
						+ "												<table>\r\n"
						+ "													<tr>\r\n" + "\r\n" + "\r\n"
						+ "														<td colspan=\"2\" align=\"center\">Invoice #:"
						+ booking_id + "<br></br>\r\n" + "															 "
						+ outputdate.toString() + "<br></br>\r\n"
						+ "														</td>\r\n"
						+ "													</tr>\r\n"
						+ "												</table>\r\n"
						+ "											</td>\r\n"
						+ "										</tr>\r\n" + "\r\n"
						+ "										<tr class=\"information\">\r\n"
						+ "											<td colspan=\"2\">\r\n"
						+ "												<table>\r\n"
						+ "													<tr>\r\n"
						+ "														<td>" + parkingName + ".<br></br>"
						+ parkingAddress + "\r\n" + "															\r\n"
						+ "														</td>\r\n" + "\r\n"
						+ "														<td>PMS.<br></br>" + name
						+ "<br></br>\r\n" + "															\r\n"
						+ "														</td>\r\n"
						+ "													</tr>\r\n"
						+ "												</table>\r\n"
						+ "											</td>\r\n"
						+ "										</tr>\r\n" + "\r\n" + "\r\n" + "\r\n"
						+ "										<tr class=\"heading\">\r\n"
						+ "											<td>Item</td>\r\n" + "\r\n"
						+ "											<td>Price</td>\r\n"
						+ "										</tr>\r\n" + "\r\n"
						+ "										<tr class=\"item\">\r\n"
						+ "											<td>" + B_vehicleType + "(" + days + " days and "
						+ diffHours + " hours)</td>\r\n" + "\r\n" + "											<td>"
						+ total + "</td>\r\n" + "										</tr>\r\n" + "\r\n"
						+ "										<tr class=\"item\">\r\n"
						+ "											<td>TAX</td>\r\n" + "\r\n"
						+ "											<td>" + Tax + " % </td>\r\n"
						+ "										</tr>\r\n" + "\r\n"
						+ "											<tr><td>Total: " + totalwithTax + "</td>\r\n"
						+ "										</tr>\r\n"
						+ "									</table>";

				out.println(output);
				JTextPane jtp = new JTextPane();
				jtp.setContentType("text/html");
				jtp.setText(output);
				jtp.print();
				// PDFMaker.makePDF(output,booking_id);
			} else {
				out.println("No booking Found");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// out.println(output);
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
