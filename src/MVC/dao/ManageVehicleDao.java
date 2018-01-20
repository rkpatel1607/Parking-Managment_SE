package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.util.DBConnection;
import com.mvc.bean.*; 
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class ManageVehicleDao
{
private Connection conn;
	
	public ManageVehicleDao()
	{
		conn=DBConnection.createConnection();
	}
	
	public void addVehicle(ManageVehicleBean vehicle)
	{
		try
		{
			String query="insert into vehicletype(type) values(?)";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, vehicle.getVehicleType());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteVehicle(int vehicleid)
	{
		try
		{
			String query="delete from vehicletype where vt_id=?";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, vehicleid);
			pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateVehicle(ManageVehicleBean vehicle) throws ParseException
	{
		try
		{
			String query="update vehicletype set type=? where vt_id=?";
			String query2="update parkingspace set PS_vehicleType=? where vehicle_vt_id=?";
			
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, vehicle.getVehicleType());
			pstmt.setInt(2, vehicle.getVehicleType_id());
			pstmt.executeUpdate();
			PreparedStatement pstmt2=conn.prepareStatement(query2);
			pstmt2.setString(1, vehicle.getVehicleType());
			pstmt2.setInt(2, vehicle.getVehicleType_id());
			pstmt2.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<ManageVehicleBean> getAllVehicles()
	{
		System.out.println("In getAllUsers");
		List <ManageVehicleBean> vehicles=new ArrayList<ManageVehicleBean>();
		try
		{
			String query="select vt_id,type from vehicletype";
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				System.out.println("In while of resultset");
				ManageVehicleBean vehicle=new ManageVehicleBean();
				vehicle.setVehicleType_id(rs.getInt("vt_id"));
				vehicle.setVehicleType(rs.getString("type"));
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
