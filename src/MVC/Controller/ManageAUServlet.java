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
import com.mvc.bean.ManageUserBean;
import com.mvc.dao.ManageUserDao;

@WebServlet("/ManageAUServlet")
public class ManageAUServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private ManageUserDao dao;
	
    public ManageAUServlet() 
    {
        dao=new ManageUserDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					lstUser=dao.getAllAUUsers();
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
				if(request.getParameter("parkingname")!=null)
				{
					String parkingname=(String)request.getParameter("parkingname");
					user.setParkingname(parkingname);
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
					dao.updateAUUser(user);
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
						dao.deleteAUUser(Integer.parseInt(userid));
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
