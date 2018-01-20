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
import com.mvc.bean.ManageVehicleBean;
import com.mvc.dao.ManageVehicleDao;

@WebServlet("/ManageVehicleServlet")
public class ManageVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ManageVehicleDao dao;
       
    public ManageVehicleServlet() {
        dao=new ManageVehicleDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost");
		if(request.getParameter("action")!=null)
		{
			List<ManageVehicleBean> lstVehicle=new ArrayList<ManageVehicleBean>();
			String action=(String)request.getParameter("action");
			Gson gson=new Gson();
			response.setContentType("application/json");
			if(action.equals("list"))
			{
				try
				{
					lstVehicle=dao.getAllVehicles();
					JsonElement element=gson.toJsonTree(lstVehicle,new TypeToken<List<ManageVehicleBean>>() {}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData=jsonArray.toString();
					//Return Json in the format required by jTable plugin
					listData="{\"Result\":\"OK\",\"Records\":"+listData+"}";
					response.getWriter().print(listData);
				}
				catch(Exception ex)
				{
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}
			}
			else if(action.equals("create") || action.equals("update"))
			{
				ManageVehicleBean vehicle=new ManageVehicleBean();
				if(request.getParameter("vehicleType_id")!=null)
				{ 
					int vehicleid=Integer.parseInt(request.getParameter("vehicleType_id"));
					vehicle.setVehicleType_id(vehicleid);;
				}
				if(request.getParameter("vehicleType")!=null)
				{
					String type=(String)request.getParameter("vehicleType");
					vehicle.setVehicleType(type);;
				}
				
				try
				{
					if(action.equals("create"))
					{
						//Create new record
						dao.addVehicle(vehicle);
						lstVehicle.add(vehicle);
						//Convert Java Object to Json��� 
						String json=gson.toJson(vehicle);
						//Return Json in the format required by jTable plugin
						String listData="{\"Result\":\"OK\",\"Record\":"+json+"}"; 
						response.getWriter().print(listData);
					}
					else if(action.equals("update"))
					{
						//Update existing record
						dao.updateVehicle(vehicle);
						String listData="{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				}
				catch(Exception ex)
				{
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
					response.getWriter().print(error);
				}
			}
			else if(action.equals("delete"))
			{
				//Delete record
				try
				{
					if(request.getParameter("vehicleType_id")!=null)
					{
						String vehicleid=(String)request.getParameter("vehicleType_id");
						dao.deleteVehicle(Integer.parseInt(vehicleid));
						String listData="{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				}
				catch(Exception ex)
				{
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
					response.getWriter().print(error);
				} 
			}
		}
	}

}
