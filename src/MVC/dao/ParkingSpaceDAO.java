package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.ParkingSpace;
import com.mvc.util.DBConnection;

public class ParkingSpaceDAO {
	public Connection connection;
	
	public ParkingSpaceDAO(){
		connection = DBConnection.createConnection();
		
	}

	public List<ParkingSpace> getAllSpaces(int pId) {
		// TODO Auto-generated method stub
		List<ParkingSpace> spaces = new ArrayList<ParkingSpace>();
		try {
			PreparedStatement pStatement = connection.prepareStatement("select * from parkingspace where Parking_P_id = ?");
			pStatement.setInt(1, pId);
			ResultSet rs = pStatement.executeQuery();
			while(rs.next()) {
				ParkingSpace space = new ParkingSpace();
				
				space.setOfflineCharge(rs.getInt("PS_OfflineCharges"));
				space.setOfflineSlots(rs.getInt("PS_AvailableOfflineSlots"));
				space.setOnlineCharge(rs.getInt("PS_OnlineCharges"));
				space.setOnlineSlots(rs.getInt("PS_AvailableOnlineSlots"));
				space.setTax(rs.getInt("PS_Tax"));
				space.setVehicleType(rs.getString("PS_vehicleType"));
				space.setPsId(rs.getInt("PS_id"));
				
				spaces.add(space);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return spaces;
	}

	public void addSpace(ParkingSpace space, int pId) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pStatement = connection.prepareStatement("insert into parkingspace (PS_vehicleType,PS_AvailableOnlineSlots,PS_OnlineCharges,PS_AvailableOfflineSlots,PS_OfflineCharges,PS_Tax,Parking_P_id) values(?,?,?,?,?,?,?)");
			pStatement.setString(1, space.getVehicleType());
			pStatement.setInt(2, space.getOnlineSlots());
			pStatement.setInt(3, space.getOnlineCharge());
			pStatement.setInt(4, space.getOfflineSlots());
			pStatement.setInt(5, space.getOfflineCharge());
			pStatement.setInt(6, space.getTax());
			pStatement.setInt(7, pId);
			
			pStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}

	public void updateSpace(ParkingSpace space) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement pStatement = connection.prepareStatement("update parkingspace SET PS_vehicleType=?,PS_AvailableOnlineSlots=?,PS_OnlineCharges=?,PS_AvailableOfflineSlots=?,PS_OfflineCharges=?,PS_Tax=? WHERE PS_id=?");
			pStatement.setString(1, space.getVehicleType());
			pStatement.setInt(2, space.getOnlineSlots());
			pStatement.setInt(3, space.getOnlineCharge());
			pStatement.setInt(4, space.getOfflineSlots());
			pStatement.setInt(5, space.getOfflineCharge());
			pStatement.setInt(6, space.getTax());
			pStatement.setInt(7, space.getPsId());
			
			pStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	public void deleteSpace(int psId) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pStatement = connection.prepareStatement("delete from parkingspace where PS_id=?");
			pStatement.setInt(1, psId);
			
			pStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void deleteParking(int parkingId) {
		
		try {
			PreparedStatement pStatement = connection.prepareStatement("delete from parking where P_id=?");
			pStatement.setInt(1, parkingId);
			
			pStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
