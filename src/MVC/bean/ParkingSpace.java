package com.mvc.bean;

public class ParkingSpace {
	
	int onlineCharge;
	int onlineSlots;
	int offlineSlots;
	int offlineCharge;
	int tax;
	int psId;
	String vehicleType;
	
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public int getOnlineCharge() {
		return onlineCharge;
	}
	public void setOnlineCharge(int onlineCharge) {
		this.onlineCharge = onlineCharge;
	}
	public int getOnlineSlots() {
		return onlineSlots;
	}
	public void setOnlineSlots(int onlineSlots) {
		this.onlineSlots = onlineSlots;
	}
	public int getOfflineSlots() {
		return offlineSlots;
	}
	public void setOfflineSlots(int offlineSlots) {
		this.offlineSlots = offlineSlots;
	}
	public int getOfflineCharge() {
		return offlineCharge;
	}
	public void setOfflineCharge(int offlineCharge) {
		this.offlineCharge = offlineCharge;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public int getPsId() {
		return psId;
	}
	public void setPsId(int psId) {
		this.psId = psId;
	}
	
	
	
	

}
