package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.ManageVehicleBean;
import com.mvc.bean.ManageVehicleDetailsBean;
import com.mvc.util.DBConnection;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class ManageVehicleDetailsDao {

private Connection conn;
	
	public ManageVehicleDetailsDao()
	{
		conn=DBConnection.createConnection();
	}
	
	public void deleteVehicleDetails(int vehicleid)
	{
		try
		{
			String query="delete from vehicle where V_details_id=?";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, vehicleid);
			pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateVehicleDetails(ManageVehicleDetailsBean vehicle) throws ParseException
	{
		try
		{
			String query="update vehicle set V_regNo=? where V_details_id=?";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, vehicle.getReg_no());
			pstmt.setInt(2, vehicle.getVehicleDetails_id());
			pstmt.executeUpdate();
			System.out.println("In update");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<ManageVehicleDetailsBean> getAllVehicleDetails()
	{
		System.out.println("In getAllUsers");
		List <ManageVehicleDetailsBean> vehicles=new ArrayList<ManageVehicleDetailsBean>();
		try
		{
			String query="select V.V_details_id,VO.VO_fullName,V.V_regNo,V.V_type from vehicle V,vehicleowner VO where V.VehicleOwner_VO_id=VO.VO_id";
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				System.out.println("In while of resultset");
				ManageVehicleDetailsBean vehicle=new ManageVehicleDetailsBean();
				vehicle.setVehicleDetails_id(rs.getInt("V_details_id"));
				vehicle.setVehicleOwnerName(rs.getString("VO_fullName"));
				vehicle.setReg_no(rs.getString("V_regNo"));
				vehicle.setV_type(rs.getString("V_type"));
				vehicles.add(vehicle);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return vehicles;
	}
	
	public ManageVehicleBean getVehicleByName(String vtype)
	{
		ManageVehicleBean vehicle=new ManageVehicleBean();
		try
		{
			String query="select * from vehicletype where type=?";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, vtype);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				vehicle.setVehicleType_id(rs.getInt("vt_id"));
				vehicle.setVehicleType(rs.getString("type"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return vehicle;
	}
	
}
