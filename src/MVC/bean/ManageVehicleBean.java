package com.mvc.bean;


public class ManageVehicleBean {
	
	private int vehicleType_id;
	private String vehicleType;
	
	public int getVehicleType_id() {
		return vehicleType_id;
	}
	public void setVehicleType_id(int vehicleType_id) {
		this.vehicleType_id = vehicleType_id;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	@Override
	public String toString()
	{
		return "Vehicle [vehicleType_id="+vehicleType_id+",VehicleType="+vehicleType+"]";
	}

}
