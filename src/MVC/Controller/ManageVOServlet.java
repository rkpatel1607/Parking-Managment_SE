package com.mvc.controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mvc.bean.ManageUserBean;
import com.mvc.dao.ManageUserDao;

@WebServlet("/ManageVOServlet")
public class ManageVOServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private ManageUserDao dao;
	
    public ManageVOServlet() 
    {
        dao=new ManageUserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("In doPost");
		if(request.getParameter("action")!=null)
		{
			List<ManageUserBean> lstUser=new ArrayList<ManageUserBean>();
			String action=(String)request.getParameter("action");
			Gson gson=new Gson();
			response.setContentType("application/json");
			if(action.equals("list"))
			{
				try
				{
					lstUser=dao.getAllVOUsers();
					JsonElement element=gson.toJsonTree(lstUser,new TypeToken<List<ManageUserBean>>() {}.getType());
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
				ManageUserBean user=new ManageUserBean();
				if(request.getParameter("userid")!=null)
				{ 
					int userid=Integer.parseInt(request.getParameter("userid"));
					user.setUserid(userid);
				}
				if(request.getParameter("fullName")!=null)
				{
					String fullname=(String)request.getParameter("fullName");
					user.setFullName(fullname);
				}
				if(request.getParameter("address")!=null)
				{
					String address=(String)request.getParameter("address");
					user.setAddress(address);
				}
				if(request.getParameter("city")!=null)
				{
					String city=(String)request.getParameter("city");
					user.setCity(city);
				}
				if(request.getParameter("state")!=null)
				{
					String state=(String)request.getParameter("state");
					user.setState(state);
				}
				if(request.getParameter("zip")!=null)
				{
					String zip=(String)request.getParameter("zip");
					user.setZip(zip);
				}
				if(request.getParameter("contact")!=null)
				{
					Double contact=Double.parseDouble(request.getParameter("contact"));
					user.setContact(contact);
				}
				if(request.getParameter("email")!=null)
				{
					String email=(String)request.getParameter("email");
					user.setEmail(email);
				}
				if(request.getParameter("type")!=null)
				{
					String type=(String)request.getParameter("type");
					user.setType(type);
				}
				
				try
				{
					//Update existing record
					dao.updateVOUser(user);
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
				//Delete record
				try
				{
					if(request.getParameter("userid")!=null)
					{
						String userid=(String)request.getParameter("userid");
						dao.deleteVOUser(Integer.parseInt(userid));
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
