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

import org.json.JSONArray;
import org.json.JSONObject;

import com.mvc.util.DBConnection;

/**
 * Servlet implementation class GenerateList
 */
@WebServlet("/GenerateList")
public class GenerateList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String list = request.getParameter("array");
		JSONArray parking = new JSONArray();
		int user_id = Integer.parseInt(request.getSession().getAttribute("user_id").toString());
		if(list.equals("parking")) {
			try {
				Connection connection = DBConnection.createConnection();
				Statement statement = connection.createStatement();
				
				ResultSet rSet = statement.executeQuery("Select P_name from parking where Owner_PO_id = (select PO_id from parkingowner where status ='approved' and Users_user_id="+user_id+")");
				while(rSet.next()) {
					JSONObject obj = new JSONObject();
					obj.put("DisplayText", rSet.getString("P_name"));
					obj.put("Value", rSet.getString("P_name"));
					parking.put(obj);
				}
				String listData = "{\"Result\":\"OK\",\"Options\":" + parking.toString() + "}";
				System.out.println(listData);
				response.getWriter().print(listData);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
	}

}
