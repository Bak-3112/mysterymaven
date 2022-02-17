package com.mystery.beans;

public class DatabaseManagementBean extends StateBean {

	private String sk_brand_id;
	private String brand_name;
	private String message;
	private String brand_description;
	private String sk_model_id;
	private String model_name;
	
	private String model_status;
	

	private int brand_id;//for foriegn key
	private int model_id;//for forigen key
	
	private int sk_varient_id;
	private String varient_name;
	/*outlet*/
	private int sk_outlet_id;
	private String outlet_name;
	private String outlet_id;
	private String outlet_type;
	private String region_id;
	private String dealer_id;
	private String state;
	private String city;
	private String sub_region;
	private String lat;
	private String lang;
	private String contact_person;
	private String email;
	private String mobile;
	private String password;
	
	private String outlet_status;
	private String outlet_size;
	private String address;
	
	
	
  private String sk_section_id;
  private String section_name;
  private String sk_subsection_id;
  private String subsection_name;
  /*new outlet*/
  private String pincode;
  private String city_id;
  private String sk_dealer_id;
  private String sk_region_id;
  private String sk_city_id;
  private String dealer_name;
  private String region_name;
  
  
  /*new outlet*/
  /*new beans for reports d manoj*/
  private String year;
  private String month;
  /*new beans for reports d manoj*/
  
  private String sk_id;
  private String section_id;
   private String weightage;
  
  
	public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}
public String getCity_id() {
	return city_id;
}
public String setCity_id(String city_id) {
	return this.city_id = city_id;
}
public String getSk_dealer_id() {
	return sk_dealer_id;
}
public void setSk_dealer_id(String sk_dealer_id) {
	this.sk_dealer_id = sk_dealer_id;
}
public String getSk_region_id() {
	return sk_region_id;
}
public void setSk_region_id(String sk_region_id) {
	this.sk_region_id = sk_region_id;
}
public String getSk_city_id() {
	return sk_city_id;
}
public void setSk_city_id(String sk_city_id) {
	this.sk_city_id = sk_city_id;
}
public String getDealer_name() {
	return dealer_name;
}
public void setDealer_name(String dealer_name) {
	this.dealer_name = dealer_name;
}
public String getRegion_name() {
	return region_name;
}
public void setRegion_name(String region_name) {
	this.region_name = region_name;
}
	/*outlet*/
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String status;
	
	
	public int getSk_varient_id() {
		return sk_varient_id;
	}
	public void setSk_varient_id(int sk_varient_id) {
		this.sk_varient_id = sk_varient_id;
	}
	public String getVarient_name() {
		return varient_name;
	}
	public void setVarient_name(String varient_name) {
		this.varient_name = varient_name;
	}
	public int getModel_id() {
		return model_id;
	}
	public void setModel_id(int model_id) {
		this.model_id = model_id;
	}
	public int getSk_outlet_id() {
		return sk_outlet_id;
	}
	public void setSk_outlet_id(int sk_outlet_id) {
		this.sk_outlet_id = sk_outlet_id;
	}
	public String getOutlet_name() {
		return outlet_name;
	}
	public void setOutlet_name(String outlet_name) {
		this.outlet_name = outlet_name;
	}
	public String getOutlet_id() {
		return outlet_id;
	}
	public void setOutlet_id(String outlet_id) {
		this.outlet_id = outlet_id;
	}
	public String getOutlet_type() {
		return outlet_type;
	}
	public void setOutlet_type(String outlet_type) {
		this.outlet_type = outlet_type;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getDealer_id() {
		return dealer_id;
	}
	public void setDealer_id(String dealer_id) {
		this.dealer_id = dealer_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSub_region() {
		return sub_region;
	}
	public void setSub_region(String sub_region) {
		this.sub_region = sub_region;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getOutlet_status() {
		return outlet_status;
	}
	public void setOutlet_status(String outlet_status) {
		this.outlet_status = outlet_status;
	}
	public String getOutlet_size() {
		return outlet_size;
	}
	public void setOutlet_size(String outlet_size) {
		this.outlet_size = outlet_size;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSk_brand_id() {
		return sk_brand_id;
	}
	public void setSk_brand_id(String sk_brand_id) {
		this.sk_brand_id = sk_brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBrand_description() {
		return brand_description;
	}
	public void setBrand_description(String brand_description) {
		this.brand_description = brand_description;
	}
	public String getSk_model_id() {
		return sk_model_id;
	}
	public void setSk_model_id(String sk_model_id) {
		this.sk_model_id = sk_model_id;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public String getModel_status() {
		return model_status;
	}
	public void setModel_status(String model_status) {
		this.model_status = model_status;
	}
	public String getSk_section_id() {
		return sk_section_id;
	}
	public void setSk_section_id(String sk_section_id) {
		this.sk_section_id = sk_section_id;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getSk_subsection_id() {
		return sk_subsection_id;
	}
	public void setSk_subsection_id(String sk_subsection_id) {
		this.sk_subsection_id = sk_subsection_id;
	}
	public String getSubsection_name() {
		return subsection_name;
	}
	public void setSubsection_name(String subsection_name) {
		this.subsection_name = subsection_name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSk_id() {
		return sk_id;
	}
	public void setSk_id(String sk_id) {
		this.sk_id = sk_id;
	}
	public String getSection_id() {
		return section_id;
	}
	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}
	public String getWeightage() {
		return weightage;
	}
	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}
	
}
