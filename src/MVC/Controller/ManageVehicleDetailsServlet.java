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
import com.mvc.bean.ManageVehicleDetailsBean;
import com.mvc.dao.ManageVehicleDetailsDao;

@WebServlet("/ManageVehicleDetailsServlet")
public class ManageVehicleDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ManageVehicleDetailsDao dao;
    
    public ManageVehicleDetailsServlet() {
        dao=new ManageVehicleDetailsDao();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost");
		if(request.getParameter("action")!=null)
		{
			List<ManageVehicleDetailsBean> lstVehicle=new ArrayList<ManageVehicleDetailsBean>();
			String action=(String)request.getParameter("action");
			Gson gson=new Gson();
			response.setContentType("application/json");
			if(action.equals("list"))
			{
				try
				{
					lstVehicle=dao.getAllVehicleDetails();
					JsonElement element=gson.toJsonTree(lstVehicle,new TypeToken<List<ManageVehicleDetailsBean>>() {}.getType());
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
			else if(action.equals("update"))
			{
				ManageVehicleDetailsBean vehicle=new ManageVehicleDetailsBean();
				if(request.getParameter("vehicleDetails_id")!=null)
				{ 
					int vehicleid=Integer.parseInt(request.getParameter("vehicleDetails_id"));
					vehicle.setVehicleDetails_id(vehicleid);;
				}
				if(request.getParameter("vehicleOwnerName")!=null)
				{
					String vo_name=(String)request.getParameter("vehicleOwnerName");
					vehicle.setVehicleOwnerName(vo_name);;
				}
				if(request.getParameter("reg_no")!=null)
				{
					String reg_no=(String)request.getParameter("reg_no");
					vehicle.setReg_no(reg_no);;
				}
				if(request.getParameter("v_type")!=null)
				{
					String type=(String)request.getParameter("v_type");
					vehicle.setV_type(type);;
				}
				
				try
				{
						dao.updateVehicleDetails(vehicle);
						String listData="{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
				}
				catch(Exception ex)
				{
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
					response.getWriter().print(error);
				}
			}
			else if(action.equals("delete"))
			{
				try
				{
					if(request.getParameter("vehicleDetails_id")!=null)
					{
						String vehicleid=(String)request.getParameter("vehicleDetails_id");
						dao.deleteVehicleDetails(Integer.parseInt(vehicleid));
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
