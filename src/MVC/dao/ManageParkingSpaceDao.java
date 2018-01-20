package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.ManageParkingSpaceBean;
import com.mvc.util.DBConnection;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class ManageParkingSpaceDao {
	private Connection conn;

	public ManageParkingSpaceDao() {
		conn = DBConnection.createConnection();
	}

	public void deleteParkingSpaceDetails(int vehicleid) {
		System.out.println("in delete");
		try {
			String query1 = "delete from parkingspace where Parking_P_id=?";
			String query2 = "delete from booking where Parking_P_id=?";
			String query3 = "delete from parking where P_id=?";

			PreparedStatement pstmt2 = conn.prepareStatement(query2);
			pstmt2.setInt(1, vehicleid);
			pstmt2.executeUpdate();

			PreparedStatement pstmt1 = conn.prepareStatement(query1);
			pstmt1.setInt(1, vehicleid);
			pstmt1.executeUpdate();

			PreparedStatement pstmt3 = conn.prepareStatement(query3);
			pstmt3.setInt(1, vehicleid);
			pstmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateParkingSpaceDetails(ManageParkingSpaceBean space) throws ParseException {
		try {
			String query1 = "update parkingowner set PO_city=?,PO_state=? where PO_id=?";
			String query2 = "update parking set P_name=?,P_address=? where P_id=?";

			PreparedStatement pstmt1 = conn.prepareStatement(query1);
			pstmt1.setString(1, space.getPo_city());
			pstmt1.setString(2, space.getPo_state());
			pstmt1.setInt(3, space.getPo_id());
			pstmt1.executeUpdate();

			PreparedStatement pstmt2 = conn.prepareStatement(query2);
			pstmt2.setString(1, space.getP_name());
			pstmt2.setString(2, space.getP_address());
			pstmt2.setInt(3, space.getP_id());
			pstmt2.executeUpdate();
			System.out.println("In update");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ManageParkingSpaceBean> getAllParkingSpaceDetails() {
		System.out.println("In getAllUsers");
		List<ManageParkingSpaceBean> spaces = new ArrayList<ManageParkingSpaceBean>();
		try {
			String query = "select PO.PO_id,PO.PO_fullName,PO.PO_city,PO.PO_state,P.P_id,P.P_name,P.P_address from parkingowner PO, parking P where PO.PO_id=P.Owner_PO_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In while of resultset");
				ManageParkingSpaceBean space = new ManageParkingSpaceBean();
				space.setPo_id(rs.getInt("PO.PO_id"));
				space.setP_id(rs.getInt("P_id"));
				space.setPo_name(rs.getString("PO_fullName"));
				space.setPo_city(rs.getString("PO_city"));
				space.setPo_state(rs.getString("PO_state"));
				space.setP_name(rs.getString("P_name"));
				space.setP_address(rs.getString("P_address"));
				spaces.add(space);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spaces;
	}

	/*
	 * public ManageVehicleBean getVehicleByName(String vtype) { ManageVehicleBean
	 * vehicle=new ManageVehicleBean(); try { String
	 * query="select * from vehicletype where type=?"; PreparedStatement
	 * pstmt=conn.prepareStatement(query); pstmt.setString(1, vtype); ResultSet
	 * rs=pstmt.executeQuery();
	 * 
	 * if(rs.next()) { vehicle.setVehicleType_id(rs.getInt("vt_id"));
	 * vehicle.setVehicleType(rs.getString("type")); } } catch(SQLException e) {
	 * e.printStackTrace(); } return vehicle; }
	 */
}
