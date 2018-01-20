package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.mvc.bean.AnonymousUser;
import com.mvc.util.DBConnection;

public class AnonymousDAO {

	public Connection connection;

	public AnonymousDAO() {
		// TODO Auto-generated constructor stub
		connection = DBConnection.createConnection();
	}

	public List<AnonymousUser> getAllUsers(int pId) {
		// TODO Auto-generated method stub
		List<AnonymousUser> users = new ArrayList<AnonymousUser>();
		String parkingName,email,fullName,contactno,password;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from users inner join anonymoususer on users.user_id= anonymoususer.Users_user_id AND anonymoususer.Parking_P_id="+pId);
			while (rs.next()) {
				AnonymousUser user = new AnonymousUser(); 

				parkingName = (String)rs.getString("AU_parkingName");
				contactno = rs.getString("AU_contactNo");
				fullName = rs.getString("AU_fullname");
				email = rs.getString("userName");
				password = rs.getString("password");
				
				user.setFullName(fullName);
				//user.setParkingName(getParkingName(parkingName));
				user.setParkingName(parkingName);
				user.setContactNo(contactno);				
				user.setEmail(email);
				user.setPassword(password);
				users.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return users;
	}

	private String getParkingName(String parkingName) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		try {
			obj.put("DisplayText", parkingName);
			obj.put("Value", parkingName);
			System.out.println(obj);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return obj.toString();
	}

	public void addUser(AnonymousUser user) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement pst1 = connection.prepareStatement("insert into users (username,password,type) values(?,?,?)");
			PreparedStatement pst2 = connection.prepareStatement("insert into anonymoususer (Users_user_id,AU_fullname,AU_contactNo,AU_parkingName,Parking_P_id) values((select user_id from users where username=?),?,?,?,(select P_id from parking where P_name=?))");
			
			pst1.setString(1, user.getEmail());
			pst1.setString(2, user.getPassword());
			pst1.setString(3, "anonymous");
			
			pst2.setString(1, user.getEmail());
			pst2.setString(2, user.getFullName());
			pst2.setString(3, user.getContactNo());
			pst2.setString(4, user.getParkingName());
			pst2.setString(5, user.getParkingName());
			
			pst1.executeUpdate();
			pst2.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void updateUser(AnonymousUser user) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst1 = connection.prepareStatement("update anonymoususer SET AU_fullname=?,AU_contactNo=?,AU_parkingName=? WHERE Users_user_id=(select user_id from users where username = ?)");
			
			
			pst1.setString(1, user.getFullName());
			pst1.setString(2, user.getContactNo());
			pst1.setString(3, user.getParkingName());
			pst1.setString(4, user.getEmail());

			pst1.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

	public void deleteUser(String email) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst1 = connection.prepareStatement("delete from anonymoususer where Users_user_id=(select user_id from users where username = ?)");
			PreparedStatement pst2 = connection.prepareStatement("delete from users where username=?");
			
			pst1.setString(1, email);
			pst2.setString(1, email);
			
			pst1.executeUpdate();
			pst2.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		

	}

}
