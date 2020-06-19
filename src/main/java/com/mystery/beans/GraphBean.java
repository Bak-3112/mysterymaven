package com.mystery.beans;

import java.util.List;

public class GraphBean {

	private String mode_of_contact;
	private String month,brand_id,brand_name,month_name;
	private String region_id,region_name;
	private String dealer_id,dealer_name,outlet_id,outlet_name;
	private String location_id,location;
	private String yesCount,noCount,quarter;

	private String percentage;
	private String subsection_name;

	private String option_text,count,year;
	private String After4hoursonthesameworkingdayp,Within2hoursaftersendingtherequestp,Within4hoursaftersendingtherequestp,Notatallp;

	
	private String WithoutwaitingloopWithin30secondsatthefirstattemptp;
	private String  WithoutwaitingloopWithin30secondsatthesecondattempp;
	private String  InwaitingloopWithin120secondsatthefirstattemptp;
	private String  InwaitingloopWithin120secondsatthesecondattemptp;
	private String  Callbackwasnotconductedwithinoneworkingdayp;
	private String  IcalledtwotimesbutIcouldnotreachthedealershipatallp;
	
	private String Yeshandedoverduringtheconsultationonofficialpaperp;
	private String Yesonofficialpapersentperemailduringrightaftertheconsultationp;
	private String YesIgotanwrittenofferbutnotonofficialpaperp;
	private String NoIdidnotreceiveanofferatallp;
	
	 private String Withinnextworkingdayaftersendingtherequestp;
	 private String Nopersonalresponseuntilendofnextworkingdayp;
	 
	 
	 /* beans for CT analysis */
	 private String PromotorCountpercentage;
	 private String PassiveCountpercentage;
	 private String DetractorCountpercentage;
	
	
	
	 public GraphBean() { brand_id="1"; } 
	 
	/*******manoj d for ytd and mtd graph*******/
	private String YTD;
	private String MTD;
	private Double YTD_YTD;
	private String section_id;
	private String section_name;
	private String subSection_id,role_id;;
	private List<GraphBean> sectionScore;
	/*******manoj d for ytd and mtd graph*******/
	
	/*manoj d for Discount Analysis*/
	private String oneValue;
	private List<GraphBean> answerDetails;
	private String brand;
	private String outlets;
	private String model_name;
	private String subQuestion;
	private String sk_model_id;
	private String varient_name;
	private String answer;
	private String yes;
	private String no;
	private String status;
	/*manoj d for Discount Analysis*/
	
  
	
	public String getMode_of_contact() {
		return mode_of_contact;
	}
	public void setMode_of_contact(String mode_of_contact) {
		this.mode_of_contact = mode_of_contact;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getDealer_id() {
		return dealer_id;
	}
	public void setDealer_id(String dealer_id) {
		this.dealer_id = dealer_id;
	}
	public String getDealer_name() {
		return dealer_name;
	}
	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getYesCount() {
		return yesCount;
	}
	public void setYesCount(String yesCount) {
		this.yesCount = yesCount;
	}
	public String getNoCount() {
		return noCount;
	}
	public void setNoCount(String noCount) {
		this.noCount = noCount;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String getMonth_name() {
		return month_name;
	}
	public void setMonth_name(String month_name) {
		this.month_name = month_name;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}


	public String getOutlet_id() {
		return outlet_id;
	}


	public void setOutlet_id(String outlet_id) {
		this.outlet_id = outlet_id;
	}


	public String getOutlet_name() {
		return outlet_name;
	}


	public void setOutlet_name(String outlet_name) {
		this.outlet_name = outlet_name;
	}
	public String getOption_text() {
		return option_text;
	}
	public void setOption_text(String option_text) {
		this.option_text = option_text;
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
	public String getAfter4hoursonthesameworkingdayp() {
		return After4hoursonthesameworkingdayp;
	}
	public void setAfter4hoursonthesameworkingdayp(String after4hoursonthesameworkingdayp) {
		After4hoursonthesameworkingdayp = after4hoursonthesameworkingdayp;
	}
	public String getWithin2hoursaftersendingtherequestp() {
		return Within2hoursaftersendingtherequestp;
	}
	public void setWithin2hoursaftersendingtherequestp(String within2hoursaftersendingtherequestp) {
		Within2hoursaftersendingtherequestp = within2hoursaftersendingtherequestp;
	}
	public String getWithin4hoursaftersendingtherequestp() {
		return Within4hoursaftersendingtherequestp;
	}
	public void setWithin4hoursaftersendingtherequestp(String within4hoursaftersendingtherequestp) {
		Within4hoursaftersendingtherequestp = within4hoursaftersendingtherequestp;
	}
	public String getNotatallp() {
		return Notatallp;
	}
	public void setNotatallp(String notatallp) {
		Notatallp = notatallp;
	}
	public String getWithoutwaitingloopWithin30secondsatthefirstattemptp() {
		return WithoutwaitingloopWithin30secondsatthefirstattemptp;
	}
	public void setWithoutwaitingloopWithin30secondsatthefirstattemptp(
			String withoutwaitingloopWithin30secondsatthefirstattemptp) {
		WithoutwaitingloopWithin30secondsatthefirstattemptp = withoutwaitingloopWithin30secondsatthefirstattemptp;
	}
	public String getWithoutwaitingloopWithin30secondsatthesecondattempp() {
		return WithoutwaitingloopWithin30secondsatthesecondattempp;
	}
	public void setWithoutwaitingloopWithin30secondsatthesecondattempp(
			String withoutwaitingloopWithin30secondsatthesecondattempp) {
		WithoutwaitingloopWithin30secondsatthesecondattempp = withoutwaitingloopWithin30secondsatthesecondattempp;
	}
	public String getInwaitingloopWithin120secondsatthefirstattemptp() {
		return InwaitingloopWithin120secondsatthefirstattemptp;
	}
	public void setInwaitingloopWithin120secondsatthefirstattemptp(String inwaitingloopWithin120secondsatthefirstattemptp) {
		InwaitingloopWithin120secondsatthefirstattemptp = inwaitingloopWithin120secondsatthefirstattemptp;
	}
	public String getInwaitingloopWithin120secondsatthesecondattemptp() {
		return InwaitingloopWithin120secondsatthesecondattemptp;
	}
	public void setInwaitingloopWithin120secondsatthesecondattemptp(
			String inwaitingloopWithin120secondsatthesecondattemptp) {
		InwaitingloopWithin120secondsatthesecondattemptp = inwaitingloopWithin120secondsatthesecondattemptp;
	}
	public String getCallbackwasnotconductedwithinoneworkingdayp() {
		return Callbackwasnotconductedwithinoneworkingdayp;
	}
	public void setCallbackwasnotconductedwithinoneworkingdayp(String callbackwasnotconductedwithinoneworkingdayp) {
		Callbackwasnotconductedwithinoneworkingdayp = callbackwasnotconductedwithinoneworkingdayp;
	}
	public String getIcalledtwotimesbutIcouldnotreachthedealershipatallp() {
		return IcalledtwotimesbutIcouldnotreachthedealershipatallp;
	}
	public void setIcalledtwotimesbutIcouldnotreachthedealershipatallp(
			String icalledtwotimesbutIcouldnotreachthedealershipatallp) {
		IcalledtwotimesbutIcouldnotreachthedealershipatallp = icalledtwotimesbutIcouldnotreachthedealershipatallp;
	}


	public String getPercentage() {
		return percentage;
	}


	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}


	public String getSubsection_name() {
		return subsection_name;
	}


	public void setSubsection_name(String subsection_name) {
		this.subsection_name = subsection_name;
	}
	public String getYTD() {
		return YTD;
	}
	public void setYTD(String yTD) {
		YTD = yTD;
	}
	public String getMTD() {
		return MTD;
	}
	public void setMTD(String mTD) {
		MTD = mTD;
	}
	public String getOneValue() {
		return oneValue;
	}
	public void setOneValue(String oneValue) {
		this.oneValue = oneValue;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getOutlets() {
		return outlets;
	}
	public void setOutlets(String outlets) {
		this.outlets = outlets;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getSk_model_id() {
		return sk_model_id;
	}
	public void setSk_model_id(String sk_model_id) {
		this.sk_model_id = sk_model_id;
	}
	public String getVarient_name() {
		return varient_name;
	}
	public void setVarient_name(String varient_name) {
		this.varient_name = varient_name;
	}
	public String getSubQuestion() {
		return subQuestion;
	}
	public void setSubQuestion(String subQuestion) {
		this.subQuestion = subQuestion;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getYes() {
		return yes;
	}
	public void setYes(String yes) {
		this.yes = yes;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getYeshandedoverduringtheconsultationonofficialpaperp() {
		return Yeshandedoverduringtheconsultationonofficialpaperp;
	}



	public void setYeshandedoverduringtheconsultationonofficialpaperp(
			String yeshandedoverduringtheconsultationonofficialpaperp) {
		Yeshandedoverduringtheconsultationonofficialpaperp = yeshandedoverduringtheconsultationonofficialpaperp;
	}



	public String getYesonofficialpapersentperemailduringrightaftertheconsultationp() {
		return Yesonofficialpapersentperemailduringrightaftertheconsultationp;
	}



	public void setYesonofficialpapersentperemailduringrightaftertheconsultationp(
			String yesonofficialpapersentperemailduringrightaftertheconsultationp) {
		Yesonofficialpapersentperemailduringrightaftertheconsultationp = yesonofficialpapersentperemailduringrightaftertheconsultationp;
	}



	public String getYesIgotanwrittenofferbutnotonofficialpaperp() {
		return YesIgotanwrittenofferbutnotonofficialpaperp;
	}



	public void setYesIgotanwrittenofferbutnotonofficialpaperp(String yesIgotanwrittenofferbutnotonofficialpaperp) {
		YesIgotanwrittenofferbutnotonofficialpaperp = yesIgotanwrittenofferbutnotonofficialpaperp;
	}



	public String getNoIdidnotreceiveanofferatallp() {
		return NoIdidnotreceiveanofferatallp;
	}



	public void setNoIdidnotreceiveanofferatallp(String noIdidnotreceiveanofferatallp) {
		NoIdidnotreceiveanofferatallp = noIdidnotreceiveanofferatallp;
	}



	public List<GraphBean> getAnswerDetails() {
		return answerDetails;
	}



	public void setAnswerDetails(List<GraphBean> answerDetails) {
		this.answerDetails = answerDetails;
	}



	public String getQuarter() {
		return quarter;
	}



	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public Double getYTD_YTD() {
		return YTD_YTD;
	}
	public void setYTD_YTD(Double yTD_YTD) {
		YTD_YTD = yTD_YTD;
	}
	public String getSection_id() {
		return section_id;
	}
	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getSubSection_id() {
		return subSection_id;
	}
	public void setSubSection_id(String subSection_id) {
		this.subSection_id = subSection_id;
	}
	public List<GraphBean> getSectionScore() {
		return sectionScore;
	}
	public void setSectionScore(List<GraphBean> sectionScore) {
		this.sectionScore = sectionScore;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getWithinnextworkingdayaftersendingtherequestp() {
		return Withinnextworkingdayaftersendingtherequestp;
	}
	public void setWithinnextworkingdayaftersendingtherequestp(String withinnextworkingdayaftersendingtherequestp) {
		Withinnextworkingdayaftersendingtherequestp = withinnextworkingdayaftersendingtherequestp;
	}
	public String getNopersonalresponseuntilendofnextworkingdayp() {
		return Nopersonalresponseuntilendofnextworkingdayp;
	}
	public void setNopersonalresponseuntilendofnextworkingdayp(String nopersonalresponseuntilendofnextworkingdayp) {
		Nopersonalresponseuntilendofnextworkingdayp = nopersonalresponseuntilendofnextworkingdayp;
	}
	public String getPromotorCountpercentage() {
		return PromotorCountpercentage;
	}
	public void setPromotorCountpercentage(String promotorCountpercentage) {
		PromotorCountpercentage = promotorCountpercentage;
	}
	public String getPassiveCountpercentage() {
		return PassiveCountpercentage;
	}
	public void setPassiveCountpercentage(String passiveCountpercentage) {
		PassiveCountpercentage = passiveCountpercentage;
	}
	public String getDetractorCountpercentage() {
		return DetractorCountpercentage;
	}
	public void setDetractorCountpercentage(String detractorCountpercentage) {
		DetractorCountpercentage = detractorCountpercentage;
	}
	
	
}
