package com.mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

/**
 * Servlet implementation class ViewProfile
 */
@WebServlet("/ViewProfile")
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public String DB_URL = "jdbc:mysql://localhost:3306/SE_PMS";
	public String USER = "root";
	public String PASS = "Admin123";
	public Connection conn = null;
	public Statement stmt = null;
	public String email, pass, loginType, fName, lName, address, city, zip, state, contactno;
	public ResultSet res = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = (String) request.getSession().getAttribute("uName");
		System.out.println("Name is ");
		System.out.println(name);
		int u_id;
		try {
			conn = DBConnection.createConnection();
			stmt=conn.createStatement();
			String sql="select * from parkingowner where PO_fullName='"+name+"'";
			System.out.println(sql);
			res = stmt.executeQuery(sql);
			res.next();
			u_id=res.getInt("Users_user_id");
			contactno=res.getString("PO_contactNo");
			address=res.getString("PO_address");
			city=res.getString("PO_city");
			state=res.getString("PO_state");
			zip=res.getString("PO_zip");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
