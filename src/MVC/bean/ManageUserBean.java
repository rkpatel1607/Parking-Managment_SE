package com.mvc.bean;

public class ManageUserBean 
{
	private int count;
	private int userid;
	private String type;
	private String fullName;
	private double contact;
	private String email;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String parkingname;
	
	
	public String getParkingname() {
		return parkingname;
	}
	public void setParkingname(String parkingname) {
		this.parkingname = parkingname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public double getContact() {
		return contact;
	}
	public void setContact(double contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ManageUserBean()
	{
		
	}
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
