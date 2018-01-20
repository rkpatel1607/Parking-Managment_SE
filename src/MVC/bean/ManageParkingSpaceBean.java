package com.mvc.bean;


public class ManageParkingSpaceBean {

	private int p_id;
	private int po_id;
	private String po_name;
	private String po_state;
	private String po_city;
	private String p_name;
	private String p_address;
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getPo_id() {
		return po_id;
	}
	public void setPo_id(int po_id) {
		this.po_id = po_id;
	}
	public String getPo_name() {
		return po_name;
	}
	public void setPo_name(String po_name) {
		this.po_name = po_name;
	}
	public String getPo_state() {
		return po_state;
	}
	public void setPo_state(String po_state) {
		this.po_state = po_state;
	}
	public String getPo_city() {
		return po_city;
	}
	public void setPo_city(String po_city) {
		this.po_city = po_city;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_address() {
		return p_address;
	}
	public void setP_address(String p_address) {
		this.p_address = p_address;
	}
	
	@Override
	public String toString()
	{
		return "ParkingSpace [po_id="+po_id+",po_fullname="+po_name+","
				+ "po_state="+po_state+",po_city="+po_city+",p_id="+p_id+
				"p_name="+p_name+"p_address="+p_address+"]";
	}
}
