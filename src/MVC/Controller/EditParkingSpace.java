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
import com.mvc.bean.AnonymousUser;
import com.mvc.bean.ParkingSpace;
import com.mvc.dao.ParkingSpaceDAO;

/**
 * Servlet implementation class EditParkingSpace
 */
@WebServlet("/EditParkingSpace")
public class EditParkingSpace extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ParkingSpaceDAO dao;
	int onlineSlots, onlineCharge, offlineSlots, offlineCharge, tax,psId;
	String vehicleType;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditParkingSpace() {
		super();
		// TODO Auto-generated constructor stub
		dao = new ParkingSpaceDAO();
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
		System.out.println("list");
		if (request.getParameter("action") != null) {
			
			List<ParkingSpace> lstUser = new ArrayList<ParkingSpace>();
			String action = (String) request.getParameter("action");
			int pId = Integer.parseInt(request.getParameter("pId"));
			
			if(action.equals("deleteEntry")) {
				dao.deleteParking(pId);
				request.getRequestDispatcher("/parkingSpace.jsp").forward(request, response);
			}
			
			Gson gson = new Gson();
			response.setContentType("application/json");

			if (action.equals("list")) {
				try {
					// Fetch Data from User Table
					lstUser = dao.getAllSpaces(pId);
					// Convert Java Object to Json
					JsonElement element = gson.toJsonTree(lstUser, new TypeToken<List<AnonymousUser>>() {
					}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData = jsonArray.toString();
					System.out.println(listData);
					// Return Json in the format required by jTable plugin
					listData = "{\"Result\":\"OK\",\"Records\":" + listData + "}";
					response.getWriter().print(listData);
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}
			} else if (action.equals("create") || action.equals("update")) {
				ParkingSpace space = new ParkingSpace();
				if (request.getParameter("onlineCharge") != null) {
					onlineCharge = Integer.parseInt(request.getParameter("onlineCharge"));
					space.setOnlineCharge(onlineCharge);
				}
				if (request.getParameter("onlineSlots") != null) {
					onlineSlots = Integer.parseInt(request.getParameter("onlineSlots"));
					space.setOnlineSlots(onlineSlots);
				}
				if (request.getParameter("offlineSlots") != null) {
					offlineSlots = Integer.parseInt(request.getParameter("offlineSlots"));
					space.setOfflineCharge(offlineSlots);
				}
				if (request.getParameter("offlineCharge") != null) {
					offlineCharge = Integer.parseInt(request.getParameter("offlineCharge"));
					space.setOfflineSlots(offlineCharge);
				}
				if (request.getParameter("tax") != null) {
					tax = Integer.parseInt(request.getParameter("tax"));
					space.setTax(tax);
				}
				if (request.getParameter("vehicleType") != null) {
					vehicleType = request.getParameter("vehicleType");
					space.setVehicleType(vehicleType);
				}
				if (request.getParameter("psId") != null) {
					psId = Integer.parseInt(request.getParameter("psId"));
					space.setPsId(psId);
				}
				try {
					if (action.equals("create")) {// Create new record
						dao.addSpace(space,pId);
						lstUser.add(space);
						// Convert Java Object to Json
						String json = gson.toJson(space);
						// Return Json in the format required by jTable plugin
						String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
						response.getWriter().print(listData);
					} else if (action.equals("update")) {// Update existing record
						dao.updateSpace(space);
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			} else if (action.equals("delete")) {// Delete record
				try {
					if (request.getParameter("psId") != null) {
						psId = Integer.parseInt(request.getParameter("psId"));
					}
					dao.deleteSpace(psId);
					String listData = "{\"Result\":\"OK\"}";
					response.getWriter().print(listData);
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			}
		}

	}

}
