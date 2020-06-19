package com.mystery.beans;
import java.util.List;

public class ReportsBean {

	private String max_points,scored_points,outlet_score,nonosc_ytd_score;
	private String osc_max_points,osc_scored_points,osc_outlet_score,osc_ytd_score;
	private String brand_name,dealer_name,location,outlet_id,month_name,mode_of_contact,outlet_address;
	private String subsection_name,currtmonth_percentage,prevmonth_percentage,curr_month,prev_month,diff,section_name;
	List<ReportsBean> getNonOscSectionPercentage;
	List<ReportsBean> getoscsectionscore;
	List<ReportsBean> subsections;
	private Double ytd;
	private String mtd;
	/*******manoj d for OLR 3 tables**********/
	private String answer_count;
	private String one;
	private String two;
	private String brand;
	private String quarter;
	private String count;
	private String percentage;
	/*******manoj d for OLR 3 tables**********/
	public List<ReportsBean> getSubsections() {
		return subsections;
	}

	public void setSubsections(List<ReportsBean> subsections) {
		this.subsections = subsections;
	}

	public String getOsc_max_points() {
		return osc_max_points;
	}

	public void setOsc_max_points(String osc_max_points) {
		this.osc_max_points = osc_max_points;
	}

	public String getOsc_scored_points() {
		return osc_scored_points;
	}

	public void setOsc_scored_points(String osc_scored_points) {
		this.osc_scored_points = osc_scored_points;
	}

	public String getOsc_outlet_score() {
		return osc_outlet_score;
	}

	public void setOsc_outlet_score(String osc_outlet_score) {
		this.osc_outlet_score = osc_outlet_score;
	}

	public String getOsc_ytd_score() {
		return osc_ytd_score;
	}

	public void setOsc_ytd_score(String osc_ytd_score) {
		this.osc_ytd_score = osc_ytd_score;
	}

	public String getMax_points() {
		return max_points;
	}

	public void setMax_points(String max_points) {
		this.max_points = max_points;
	}

	public String getScored_points() {
		return scored_points;
	}

	public void setScored_points(String scored_points) {
		this.scored_points = scored_points;
	}

	public String getOutlet_score() {
		return outlet_score;
	}

	public void setOutlet_score(String outlet_score) {
		this.outlet_score = outlet_score;
	}

	public String getNonosc_ytd_score() {
		return nonosc_ytd_score;
	}

	public void setNonosc_ytd_score(String nonosc_ytd_score) {
		this.nonosc_ytd_score = nonosc_ytd_score;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getDealer_name() {
		return dealer_name;
	}

	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOutlet_id() {
		return outlet_id;
	}

	public void setOutlet_id(String outlet_id) {
		this.outlet_id = outlet_id;
	}

	 
	public String getMonth_name() {
		return month_name;
	}

	public void setMonth_name(String month_name) {
		this.month_name = month_name;
	}

	public String getMode_of_contact() {
		return mode_of_contact;
	}

	public void setMode_of_contact(String mode_of_contact) {
		this.mode_of_contact = mode_of_contact;
	}

	public String getOutlet_address() {
		return outlet_address;
	}

	public void setOutlet_address(String outlet_address) {
		this.outlet_address = outlet_address;
	}

	public String getSubsection_name() {
		return subsection_name;
	}

	public void setSubsection_name(String subsection_name) {
		this.subsection_name = subsection_name;
	}

	public String getCurrtmonth_percentage() {
		return currtmonth_percentage;
	}

	public void setCurrtmonth_percentage(String currtmonth_percentage) {
		this.currtmonth_percentage = currtmonth_percentage;
	}

	public String getPrevmonth_percentage() {
		return prevmonth_percentage;
	}

	public void setPrevmonth_percentage(String prevmonth_percentage) {
		this.prevmonth_percentage = prevmonth_percentage;
	}

	public String getCurr_month() {
		return curr_month;
	}

	public void setCurr_month(String curr_month) {
		this.curr_month = curr_month;
	}

	public String getPrev_month() {
		return prev_month;
	}

	public void setPrev_month(String prev_month) {
		this.prev_month = prev_month;
	}

	public String getDiff() {
		return diff;
	}

	public void setDiff(String diff) {
		this.diff = diff;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	public List<ReportsBean> getGetNonOscSectionPercentage() {
		return getNonOscSectionPercentage;
	}

	public void setGetNonOscSectionPercentage(List<ReportsBean> getNonOscSectionPercentage) {
		this.getNonOscSectionPercentage = getNonOscSectionPercentage;
	}

	public List<ReportsBean> getGetoscsectionscore() {
		return getoscsectionscore;
	}

	public void setGetoscsectionscore(List<ReportsBean> getoscsectionscore) {
		this.getoscsectionscore = getoscsectionscore;
	}

	public Double getYtd() {
		return ytd;
	}

	public void setYtd(Double ytd) {
		this.ytd = ytd;
	}

	public String getMtd() {
		return mtd;
	}

	public void setMtd(String mtd) {
		this.mtd = mtd;
	}

	public String getAnswer_count() {
		return answer_count;
	}

	public void setAnswer_count(String answer_count) {
		this.answer_count = answer_count;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
}
