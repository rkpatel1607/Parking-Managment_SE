package com.mvc.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.util.DBConnection;

@WebServlet("/VehicleManagement")
public class VehicleManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int VOid;
	private String reg_number;
	private String VehicleName;
	private String VehicleType;

	public int getVOid() {
		return VOid;
	}

	public void setVOid(int vOid) {
		VOid = vOid;
	}

	public String getReg_number() {
		return reg_number;
	}

	public void setReg_number(String reg_number) {
		this.reg_number = reg_number;
	}

	public String getVehicleName() {
		return VehicleName;
	}

	public void setVehicleName(String vehicleName) {
		VehicleName = vehicleName;
	}

	public String getVehicleType() {
		return VehicleType;
	}

	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}

	@Override
	public String toString() {
		return "mvehicle [userid=" + VOid + ",Vehicle name=" + VehicleName + ",Vehicle type=" + VehicleType + "]";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// public void add(){
		try {
			Connection conn = DBConnection.createConnection();
			HttpSession hs = request.getSession();
			String username = (String) hs.getAttribute("username");
			System.out.println(username);
			Statement stmt2 = conn.createStatement();
			ResultSet resultSet = stmt2.executeQuery(
					"select * from vehicleowner where users_user_id=(select user_id from users where username='"
							+ username + "')");
			resultSet.next();
			int VOid = resultSet.getInt("vo_id");
			String reg_number = request.getParameter("reg_num");
			String vehicle_name = request.getParameter("vehicle_name");
			String vehicle_type = request.getParameter("vehicle_type");

			Statement stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO vehicle(V_type,V_regNo,VehicleOwner_VO_id,V_name)VALUES(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vehicle_type);
			ps.setString(2, reg_number);
			ps.setInt(3, VOid);
			ps.setString(4, vehicle_name);
			ps.executeUpdate();
			conn.close();
			response.sendRedirect("vehiclemanagement.jsp");

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
