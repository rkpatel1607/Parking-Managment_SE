package com.mvc.bean;


public class ManageVehicleDetailsBean {
	
	private int vehicleDetails_id;
	private String vehicleOwnerName;
	private String reg_no;
	private String v_type;
	
	public int getVehicleDetails_id() {
		return vehicleDetails_id;
	}
	public void setVehicleDetails_id(int vehicleDetails_id) {
		this.vehicleDetails_id = vehicleDetails_id;
	}
	public String getVehicleOwnerName() {
		return vehicleOwnerName;
	}
	public void setVehicleOwnerName(String vehicleOwnerName) {
		this.vehicleOwnerName = vehicleOwnerName;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	public String getV_type() {
		return v_type;
	}
	public void setV_type(String v_type) {
		this.v_type = v_type;
	}
	
	@Override
	public String toString()
	{
		return "VehicleDetails [vehicleDetailsid="+vehicleDetails_id+",VO_fullname="+vehicleOwnerName+",vo_regNo="+reg_no+",vehicle_type="+v_type+"]";
	}

}
