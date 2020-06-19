package com.mystery.beans;

public class DashboardBean {

	private String national_avg;
	private String mys_conducted;
	private String mys_tobe_conducted;
	private String PE_avg;
	private String CT_avg;
	private String osc_avg;
	private String brand_id,count,year,longitute,latitude,brand_name;

	private String ytd_dealer_avg,movement,dealer_rank,dealer_name;
	private double ytd_dealer_avg1; 

	private int rank;
	private String user_type,user_type_id;
	 


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	 


	public double getYtd_dealer_avg1() {
		return ytd_dealer_avg1;
	}


	public void setYtd_dealer_avg1(double ytd_dealer_avg1) {
		this.ytd_dealer_avg1 = ytd_dealer_avg1;
	}


	public String getDealer_name() {
		return dealer_name;
	}


	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}


	public DashboardBean() {
		brand_id="1";
	}

	public DashboardBean(int rank, String Dealer_name, double ytd_dealer_avg,String pe,String ct,String osc,String movement) {
		this.rank=rank;
		this.dealer_name=Dealer_name;
		this.ytd_dealer_avg1=ytd_dealer_avg;
		this.PE_avg=pe;
		this.CT_avg=ct;
		this.osc_avg=osc;
		this.movement=movement;
	}

	public DashboardBean(int rank,int ytd_dealer_avg) {
		this.rank=rank;
		this.ytd_dealer_avg1=ytd_dealer_avg;
	}


	public String getNational_avg() {
		return national_avg;
	}
	public void setNational_avg(String national_avg) {
		this.national_avg = national_avg;
	}
	public String getMys_conducted() {
		return mys_conducted;
	}
	public void setMys_conducted(String mys_conducted) {
		this.mys_conducted = mys_conducted;
	}
	public String getMys_tobe_conducted() {
		return mys_tobe_conducted;
	}
	public void setMys_tobe_conducted(String mys_tobe_conducted) {
		this.mys_tobe_conducted = mys_tobe_conducted;
	}
	public String getPE_avg() {
		return PE_avg;
	}
	public void setPE_avg(String pE_avg) {
		PE_avg = pE_avg;
	}
	public String getCT_avg() {
		return CT_avg;
	}
	public void setCT_avg(String cT_avg) {
		CT_avg = cT_avg;
	}
	public String getOsc_avg() {
		return osc_avg;
	}
	public void setOsc_avg(String osc_avg) {
		this.osc_avg = osc_avg;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getLongitute() {
		return longitute;
	}


	public void setLongitute(String longitute) {
		this.longitute = longitute;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getYtd_dealer_avg() {
		return ytd_dealer_avg;
	}


	public void setYtd_dealer_avg(String ytd_dealer_avg) {
		this.ytd_dealer_avg = ytd_dealer_avg;
	}


	public String getMovement() {
		return movement;
	}


	public void setMovement(String movement) {
		this.movement = movement;
	}


	public String getDealer_rank() {
		return dealer_rank;
	}


	public void setDealer_rank(String dealer_rank) {
		this.dealer_rank = dealer_rank;
	}


	public String getBrand_name() {
		return brand_name;
	}


	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}


	public String getUser_type() {
		return user_type;
	}


	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}


	public String getUser_type_id() {
		return user_type_id;
	}


	public void setUser_type_id(String user_type_id) {
		this.user_type_id = user_type_id;
	}
	
}
