package com.mvc.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mvc.bean.LoginBean;
import com.mvc.dao.LoginDao;
import com.mvc.util.EncryptPassword;

public class LoginServlet extends HttpServlet 
	{
		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			// TODO Auto-generated method stub
			
			
			String username = request.getParameter("username");
			String password = EncryptPassword.encryption(request.getParameter("password"));
			System.out.println("Encrypt Admin: "+password);
			
			LoginBean loginbean=new LoginBean();
			loginbean.setUserName(username);
			loginbean.setPassword(password);
			
			LoginDao logindao=new LoginDao();
			String validateUser=logindao.authenticateUser(loginbean);
			if (validateUser.equals("SUCCESS"))
			{
				request.setAttribute("username",username);
				request.getSession().setAttribute("username",username);
				request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);;
			}
			else
			{
				request.setAttribute("errMessage",validateUser);
				request.getRequestDispatcher("/adminLogin.jsp").forward(request,response);
			}

			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			
			

			}

}
