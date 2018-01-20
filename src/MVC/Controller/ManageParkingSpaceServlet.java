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
import com.mvc.bean.ManageParkingSpaceBean;
import com.mvc.dao.ManageParkingSpaceDao;

@WebServlet("/ManageParkingSpaceServlet")
public class ManageParkingSpaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ManageParkingSpaceDao dao;
	
    public ManageParkingSpaceServlet() {
    	dao=new ManageParkingSpaceDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost");
		if(request.getParameter("action")!=null)
		{
			List<ManageParkingSpaceBean> lstVehicle=new ArrayList<ManageParkingSpaceBean>();
			String action=(String)request.getParameter("action");
			Gson gson=new Gson();
			response.setContentType("application/json");
			if(action.equals("list"))
			{
				try
				{
					lstVehicle=dao.getAllParkingSpaceDetails();
					JsonElement element=gson.toJsonTree(lstVehicle,new TypeToken<List<ManageParkingSpaceBean>>() {}.getType());
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
				ManageParkingSpaceBean vehicle=new ManageParkingSpaceBean();
				if(request.getParameter("po_id")!=null)
				{ 
					int po_id=Integer.parseInt(request.getParameter("po_id"));
					vehicle.setPo_id(po_id);;
				}
				if(request.getParameter("po_name")!=null)
				{
					String po_name=(String)request.getParameter("po_name");
					vehicle.setPo_name(po_name);;
				}
				if(request.getParameter("reg_no")!=null)
				{
					String po_state=(String)request.getParameter("po_state");
					vehicle.setPo_state(po_state);;
				}
				if(request.getParameter("po_city")!=null)
				{
					String po_city=(String)request.getParameter("po_city");
					vehicle.setPo_city(po_city);;
				}
				if(request.getParameter("p_id")!=null)
				{ 
					int p_id=Integer.parseInt(request.getParameter("p_id"));
					vehicle.setP_id(p_id);;
				}
				if(request.getParameter("p_name")!=null)
				{
					String p_name=(String)request.getParameter("p_name");
					vehicle.setP_name(p_name);;
				}
				if(request.getParameter("p_address")!=null)
				{
					String p_address=(String)request.getParameter("p_address");
					vehicle.setP_address(p_address);;
				}
				try
				{
						dao.updateParkingSpaceDetails(vehicle);
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
				System.out.println("in servlet delete");
				try
				{
					System.out.println(request.getParameter("p_id"));
					if(request.getParameter("p_id")!=null)
					{
						String p_id=(String)request.getParameter("p_id");
						dao.deleteParkingSpaceDetails(Integer.parseInt(p_id));
						System.out.println("p_id="+p_id);
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
