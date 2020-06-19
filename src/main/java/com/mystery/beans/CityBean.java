package com.mystery.beans;

public class CityBean extends DatabaseManagementBean {
	private String sk_city_id;
	private String city_name,tier,metro;
	public String getSk_city_id() {
		return sk_city_id;
	}
	public void setSk_city_id(String sk_city_id) {
		this.sk_city_id = sk_city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getMetro() {
		return metro;
	}
	public void setMetro(String metro) {
		this.metro = metro;
	}
	
}
