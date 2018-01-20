package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

@WebServlet("/SpaceApprovalServlet")
public class SpaceApprovalServlet extends HttpServlet
{	
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	  //Setting JSP page
	  String page="parkingSpaceApproval.jsp";
	  
    public SpaceApprovalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException 
	{
		this.config=config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		Connection conn = null;
		ResultSet resultSet = null;		
		List<String> dataList = new ArrayList(); 
		
		try
		{
			conn = DBConnection.createConnection(); //establishing connection
			String sql="select username,password from user";
			Statement stmt = conn.createStatement();
			stmt.executeQuery (sql);
			resultSet = stmt.getResultSet();
			
			while(resultSet.next()) // Until next row is present otherwise it return false
			{
				
				{
					dataList.add(resultSet.getString("username"));
					dataList.add(resultSet.getString("password"));
				}
			}
			resultSet.close ();

			stmt.close ();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		request.setAttribute("data",dataList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);

		  if (dispatcher != null)
		  {

		  dispatcher.forward(request, response);

		  } 
	}

}
