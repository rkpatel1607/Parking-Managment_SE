package com.mvc.controller;

import java.io.IOException;
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
 * Servlet implementation class DeleteBookingRecord
 */
@WebServlet("/DeleteBookingRecord")
public class DeleteBookingRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	private Statement statement = null;
	private int rSet;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookingRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int id = Integer.parseInt(request.getParameter("Bid"));
			System.out.println(id);
			connection = DBConnection.createConnection();
			statement = connection.createStatement();
			rSet=statement.executeUpdate("delete from booking where B_id ="+id);
			System.out.println(rSet);
			
		} catch (Exception e) {
			// TODO: handle exception
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
