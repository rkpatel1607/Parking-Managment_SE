package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.VehicleManagement;
import com.mvc.util.DBConnection;

public class VehicleManagementDao {
	private Connection conn;

	public VehicleManagementDao() {
		conn = DBConnection.createConnection();

	}

	public void deleteUser(int VOid) {
		try {

			String query = "delete from vehicle where VehicleOwner_VO_id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, VOid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<VehicleManagement> getAllUsers(String id) {
		System.out.println("In getAllUsers");
		List<VehicleManagement> vehicles = new ArrayList<VehicleManagement>();
		try {
			String query = "SELECT * FROM se_pms.users ,se_pms.vehicle,vehicleowner where vehicle.VehicleOwner_VO_id=vehicleowner.VO_id and vehicleowner.Users_user_id=users.user_id and username='"+ id +"' ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// System.out.println("In while of resultset");
				VehicleManagement Mvehicle = new VehicleManagement();
				Mvehicle.setVOid(rs.getInt("VehicleOwner_VO_id"));
				Mvehicle.setReg_number(rs.getString("V_regNo"));
				Mvehicle.setVehicleName(rs.getString("V_name"));
				Mvehicle.setVehicleType(rs.getString("V_type"));
				vehicles.add(Mvehicle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;
	}

}
