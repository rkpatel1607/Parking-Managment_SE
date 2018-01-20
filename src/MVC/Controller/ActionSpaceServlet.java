package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.*;

@WebServlet("/ActionSpaceServlet")
public class ActionSpaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ActionSpaceDao dao;   

    public ActionSpaceServlet() {
        dao=new ActionSpaceDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		PrintWriter out=response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));

		if (action.equals("approve")) {
			try {
				dao.approveRequest(id);
				//out.print("<h4>Request approved</h4>");
				//RequestDispatcher rd=request.getRequestDispatcher("/approveParkingSpace.jsp");
				//rd.include(request, response);
				response.sendRedirect("approveParkingSpace.jsp");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (action.equals("decline")) {
			try {
				dao.declineRequest(id);
				//out.print("<h4>Request declined</h4>");
				//RequestDispatcher rd=request.getRequestDispatcher("/approveParkingSpace.jsp");
				//rd.include(request, response);
				response.sendRedirect("approveParkingSpace.jsp");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
