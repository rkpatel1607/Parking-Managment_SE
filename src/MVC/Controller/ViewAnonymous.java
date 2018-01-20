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
import com.mvc.dao.AnonymousDAO;;

/**
 * Servlet implementation class ViewAnonymous
 */
@WebServlet("/ViewAnonymous")
public class ViewAnonymous extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String email, fullName, parkingName, contactNo, password;
	public AnonymousDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewAnonymous() {
		super();
		// TODO Auto-generated constructor stub
		dao = new AnonymousDAO();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Get all user");
		int pId = (int) request.getSession().getAttribute("pId");
		if (request.getParameter("action") != null) {
			List<AnonymousUser> lstUser = new ArrayList<AnonymousUser>();
			String action = (String) request.getParameter("action");
			Gson gson = new Gson();
			response.setContentType("application/json");

			if (action.equals("list")) {
				try {
					// Fetch Data from User Table
					lstUser = dao.getAllUsers(pId);
					// Convert Java Object to Json
					JsonElement element = gson.toJsonTree(lstUser, new TypeToken<List<AnonymousUser>>() {
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
			} else if (action.equals("create") || action.equals("update")) {
				AnonymousUser user = new AnonymousUser();
				if (request.getParameter("parkingNameList") != null) {
					parkingName = request.getParameter("parkingNameList");
					user.setParkingName(parkingName);
				}
				if (request.getParameter("fullName") != null) {
					fullName= (String) request.getParameter("fullName");
					user.setFullName(fullName);
				}
				if (request.getParameter("contactNo") != null) {
					contactNo = (String) request.getParameter("contactNo");
					user.setContactNo(contactNo);
				}
				if (request.getParameter("email") != null) {
					email = (String) request.getParameter("email");
					user.setEmail(email);
				}
				if (request.getParameter("password") != null) {
					password = (String) request.getParameter("password");
					user.setPassword(password);
				}
				try {
					if (action.equals("create")) {// Create new record
						dao.addUser(user);
						lstUser.add(user);
						// Convert Java Object to Json
						String json = gson.toJson(user);
						// Return Json in the format required by jTable plugin
						String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
						response.getWriter().print(listData);
					} else if (action.equals("update")) {// Update existing record
						dao.updateUser(user);
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			} else if (action.equals("delete")) {// Delete record
				try {
					if (request.getParameter("userid") != null) {
						String email = (String) request.getParameter("email");
						dao.deleteUser(email);
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
