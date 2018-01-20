package com.mvc.dao;

import java.util.*;

import com.mvc.util.DBConnection;
import com.mvc.bean.*;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.sql.*;

public class ManageUserDao {
	private Connection conn;

	public ManageUserDao() {
		conn = DBConnection.createConnection();
		System.out.println("parkingowner");
	}

	// ---------------------Parking Owners-----------------------------------------

	public void deleteUser(int userid) {
		try {
			String query1 = "delete from parkingspace where Parking_P_id in (select P_id from parking where Owner_PO_id = (select PO_id from parkingowner where Users_user_id = ?))";
			String query2 = "delete from parking where Owner_PO_id in (select PO_id from parkingowner where Users_user_id = ?)";
			String query3 = "delete from parkingowner where Users_user_id=?";
			String query4 = "delete from users where user_id =?";

			PreparedStatement pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(query3);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(query4);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(ManageUserBean user) throws ParseException {
		try {
			String query = "update parkingowner set PO_fullName=?,PO_contactNo=?,PO_address=?,PO_city=?,PO_state=?,PO_zip=? where users_user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getFullName());
			pstmt.setDouble(2, user.getContact());
			pstmt.setString(3, user.getAddress());
			pstmt.setString(4, user.getCity());
			pstmt.setString(5, user.getState());
			pstmt.setString(6, user.getZip());
			pstmt.setInt(7, user.getUserid());
			pstmt.executeUpdate();
			System.out.println("In update");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ManageUserBean> getAllUsers() {
		System.out.println("In getAllUsers");
		List<ManageUserBean> users = new ArrayList<ManageUserBean>();
		try {
			String query = "select count(p.P_id),u.user_id,u.username,u.type,po.PO_fullName,po.PO_address,po.PO_city,po.PO_state,po.PO_zip,po.PO_contactNo from parking p,users u,parkingowner po where u.user_id=po.Users_user_id and p.Owner_PO_id=po.PO_id "
					+ "group by u.user_id,u.username,u.type,po.PO_fullName,po.PO_contactNo";
			System.out.println(query);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In while of resultset");
				ManageUserBean user = new ManageUserBean();
				user.setUserid(rs.getInt("user_id"));
				user.setFullName(rs.getString("PO_fullName"));
				user.setContact(rs.getDouble("PO_contactNo"));
				user.setEmail(rs.getString("username"));
				user.setType(rs.getString("type"));
				user.setAddress(rs.getString("PO_address"));
				user.setCity(rs.getString("PO_city"));
				user.setState(rs.getString("PO_state"));
				user.setZip(rs.getString("PO_zip"));
				user.setCount(rs.getInt("count(p.P_id)"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public ManageUserBean getUserById(String fullName) {
		ManageUserBean user = new ManageUserBean();
		try {
			String query = "select u.user_id,u.username,u.type,po.PO_fullName,po.PO_contactNo from users u,parkingowner po"
					+ " where po.PO_fullName=? u.user_id = po.Users_user_id";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fullName);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				user.setUserid(rs.getInt("user_id"));
				user.setFullName(rs.getString("PO_fullName"));
				user.setContact(rs.getDouble("PO_contactNo"));
				user.setEmail(rs.getString("username"));
				user.setType(rs.getString("type"));
				// user.setCount(rs.getInt("count(p.P_id)"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// ------------------------Vehicle Owners----------------------------

	public void deleteVOUser(int userid) {
		try {
			String query1="delete from booking where VehicleOwner_VO_id in (select VO_id from vehicleowner where Users_user_id=?)";
			String query2="delete from vehicle where VehicleOwner_VO_id in (select VO_id from vehicleowner where Users_user_id=?)";
			String query3="delete from vehicleowner where users_user_id=?";			
			String query4="delete from users where user_id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query3);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query4);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateVOUser(ManageUserBean user) throws ParseException {
		try {
			String query = "update vehicleowner set VO_fullName=?,VO_contactNo=?,VO_address=?,VO_city=?,VO_state=?,VO_zip=? where users_user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getFullName());
			pstmt.setDouble(2, user.getContact());
			pstmt.setString(3, user.getAddress());
			pstmt.setString(4, user.getCity());
			pstmt.setString(5, user.getState());
			pstmt.setString(6, user.getZip());
			pstmt.setInt(7, user.getUserid());
			pstmt.executeUpdate();
			System.out.println("In update");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ManageUserBean> getAllVOUsers() {
		System.out.println("In getAllUsers");
		List<ManageUserBean> users = new ArrayList<ManageUserBean>();
		try {
			String query = "select u.user_id,u.username,u.type,vo.VO_fullName,vo.VO_address,vo.VO_state,vo.VO_city,vo.VO_zip,vo.VO_contactNo from users u,vehicleowner vo where u.user_id=vo.Users_user_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In while of resultset");
				ManageUserBean user = new ManageUserBean();
				user.setUserid(rs.getInt("user_id"));
				user.setFullName(rs.getString("VO_fullName"));
				user.setContact(rs.getDouble("VO_contactNo"));
				user.setEmail(rs.getString("username"));
				user.setAddress(rs.getString("VO_address"));
				user.setCity(rs.getString("VO_city"));
				user.setState(rs.getString("VO_state"));
				user.setZip(rs.getString("VO_zip"));
				user.setType(rs.getString("type"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	// -------------------------Anonymous Users----------------------------

	public void deleteAUUser(int userid) {
		try {
			String query = "delete from anonymoususer where users_user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAUUser(ManageUserBean user) throws ParseException {
		try {
			String query = "update anonymoususer set AU_fullName=?,AU_contactNo=?,AU_parkingName=? where users_user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getFullName());
			pstmt.setDouble(2, user.getContact());
			pstmt.setString(3, user.getParkingname());
			pstmt.setInt(4, user.getUserid());
			pstmt.executeUpdate();
			System.out.println("In update");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ManageUserBean> getAllAUUsers() {
		System.out.println("In getAllUsers");
		List<ManageUserBean> users = new ArrayList<ManageUserBean>();
		try {
			String query = "select u.user_id,u.username,u.type,au.AU_fullName,au.AU_contactNo,au.AU_parkingName from users u,anonymoususer au where u.user_id=au.Users_user_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In while of resultset");
				ManageUserBean user = new ManageUserBean();
				user.setUserid(rs.getInt("user_id"));
				user.setFullName(rs.getString("AU_fullName"));
				user.setContact(rs.getDouble("AU_contactNo"));
				user.setEmail(rs.getString("username"));
				user.setType(rs.getString("type"));
				user.setParkingname(rs.getString("AU_parkingName"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
