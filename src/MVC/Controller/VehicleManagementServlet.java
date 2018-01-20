package com.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.mvc.bean.VehicleManagement;
import com.mvc.dao.VehicleManagementDao;

/**
 * Servlet implementation class VehicleManagementServlet
 */
@WebServlet("/VehicleManagementServlet")
public class VehicleManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleManagementDao dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VehicleManagementServlet() {
		// super();
		// TODO Auto-generated constructor stub
		dao = new VehicleManagementDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		System.out.println("In doPost");
		if (request.getParameter("action") != null) {
			List<VehicleManagement> lstUser = new ArrayList<VehicleManagement>();
			String action = (String) request.getParameter("action");
			Gson gson = new Gson();
			response.setContentType("application/json");
			if (action.equals("list")) {
				try {
					String id=request.getSession().getAttribute("username").toString();
					lstUser = dao.getAllUsers(id);
					JsonElement element = gson.toJsonTree(lstUser, new TypeToken<List<VehicleManagement>>() {
					}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData = jsonArray.toString();
					// Return Json in the format required by jTable plugin
					listData = "{\"Result\":\"OK\",\"Records\":" + listData + "}";
					response.getWriter().print(listData);
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}
			} else if (action.equals("delete")) {
				// Delete record
				try {
					if (request.getParameter("VehicleOwner_VO_id") != null) {
						String vehicleoid = (String) request.getParameter("VehicleOwner_VO_id");
						dao.deleteUser(Integer.parseInt(vehicleoid));
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			}
		}
	}

}
