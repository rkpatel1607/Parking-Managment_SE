package com.mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.util.DBConnection;
import com.mvc.util.EncryptPassword;

/**
 * Servlet implementation class HomeLogin
 */
@WebServlet("/HomeLogin")
public class HomeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Connection conn = null;
	public Statement stmt = null;
	public String uName, pass, loginType;
	public ResultSet res = null;
	public String userName;
	public int ownerId;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Begin");
		HttpSession session = null;
		// PrintWriter out;

		try {
			// out = response.getWriter();
			uName = request.getParameter("username");
			pass = EncryptPassword.encryption(request.getParameter("password"));
			System.out.println("encrypted Password: " + pass);
			loginType = request.getParameter("loginType");
			conn = DBConnection.createConnection();
			stmt = conn.createStatement();

			String sql = "select * from users where(username='" + uName + "'&&password='" + pass + "'&&type='"
					+ loginType + "')";
			res = stmt.executeQuery(sql);
			if (res.next()) {
				System.out.println("success");
				request.setAttribute("msg", "");
				session = request.getSession();
				session.setAttribute("userName", uName);
				session.setAttribute("user_id", res.getString("user_id"));
				session.setAttribute("username", uName);
				if (loginType.equals("owner")) {
					getPOProfileDetails(res.getInt("user_id"));
					session.setAttribute("pId", ownerId);
					session.setAttribute("uName", userName);
					request.getRequestDispatcher("/parkingOwnerDashboard.jsp").forward(request, response);
				}
				if (loginType.equals("user")) {
					getVOProfileDetails(res.getInt("user_id"));
					session.setAttribute("uName", userName);
					request.getRequestDispatcher("/vehicleOwnerDashboard.jsp").forward(request, response);
				}
				if (loginType.equals("anonymous")) {
					getAUProfileDetails(res.getInt("user_id"));
					session.setAttribute("uName", userName);
					response.sendRedirect("anonymousDashboard.jsp");
				}
				// request.getRequestDispatcher("/anonymousDashboard.jsp").forward(request,
				// response);
			} else {
				request.setAttribute("msg", "Invalid Credentials");
				// out.println("<p style=\"color:red\">Old Password doesn't match</p>");
				RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
				rd.include(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("2nd error");
			e.printStackTrace();
		}

	}

	public void getPOProfileDetails(int userId) {
		try {
			Statement statement = conn.createStatement();
			ResultSet rSet = statement
					.executeQuery("select PO_id,PO_fullName from parkingowner where Users_user_id=" + userId);
			if (rSet.next()) {
				ownerId = rSet.getInt("PO_id");
				userName = rSet.getString("PO_fullName");
			} else {
				ownerId = 1;
				userName = "Owner";
			}
			System.out.println("PO: " + userName + "  " + ownerId);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void getVOProfileDetails(int userId) {

		try {
			Statement statement = conn.createStatement();
			ResultSet rSet = statement
					.executeQuery("select VO_id,VO_fullName from vehicleowner where Users_user_id=" + userId);
			if (rSet.next()) {
				ownerId = rSet.getInt("VO_id");
				userName = rSet.getString("VO_fullName");
			} else {
				ownerId = 1;
				userName = "User";
			}
			System.out.println("VO: " + userName + "  " + ownerId);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void getAUProfileDetails(int userId) {

		try {
			Statement statement = conn.createStatement();
			ResultSet rSet = statement
					.executeQuery("select AU_id,AU_fullName from anonymoususer where Users_user_id=" + userId);
			if (rSet.next()) {
				userName = rSet.getString("AU_fullName");
			} else {
				userName = "Attendant";
			}
			System.out.println("AU: " + userName);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
