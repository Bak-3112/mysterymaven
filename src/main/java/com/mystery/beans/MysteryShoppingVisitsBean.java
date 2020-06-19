package com.mystery.beans;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MysteryShoppingVisitsBean {

	public MysteryShoppingVisitsBean(int ytd_dealer_rank){
		this.ytd_dealer_rank=	ytd_dealer_rank;
		}
	public MysteryShoppingVisitsBean() {
			// TODO Auto-generated constructor stub
		}
	public MysteryShoppingVisitsBean(long id, double scores) {
		this.id=id;
		this.name=name;
		this.ytd_dealer_avg1=scores;
	}
	private long id;
		/*new Beans added by mahantesh*/
		private String shoppers_car_owned_brand;
		private String  dealer_id;
		private String  variant_id;
		private String  variant_name;
		private String limit;
		private String outlet_score,region_name,weekday;
		/*new Beans added by mahantesh*/
		
		/*new Beans added by mahantesh*/
		private String count;
		/*new Beans added by mahantesh*/
		private String 	sk_shopper_id;
		private String 	name;
		private String  shoppers_car_owned;
		private String 	shoppers_car_yop,finalpage,question_optiontype,mandatory_optional_status,offset;



		public String getShoppers_car_yop() {
			return shoppers_car_yop;
		}
		/*new beans by manoj for OLR*/
		private String osc_shopper_id;
		private String prevmonth;
		private String currmonth;
		/*new beans by manoj for OLR*/

		public void setShoppers_car_yop(String shoppers_car_yop) {
			this.shoppers_car_yop = shoppers_car_yop;
		}
		private String 	answer;
		private String 	email;
		private String 	contact_number;
		private String 	age;
		private String 	gender;
		private String 	quarter;
		private String 	year;
		private String 	mode_of_contact;
		private String 	visit_date;
		private String 	brand_id;
		private String 	outlet_id;
		private String outlet_name;
		private String 	brand_model_id;
		private String 	model_name;
		private String 	type;
		private String 	status;
		private String 	dealer_name;
		private String 	brand_name;
		private String address;
		private String max_option_point;
		private int ytd_dealer_rank;

		private String file,subQuestionAnswer,report_type,month,region_id;

		private String standard_number_sequence,question_code,formula_flag,question_type,question_points,questions,answerParagraph,answerDate,answerTime,main_ques_comment,option_comment,answer_type,option_points,sk_answer_id;
		List<MysteryShoppingVisitsBean> answerdata ;

		
	private String formula_id,result,sk_formula_map_id,super_question_selected_id,super_selected_qid,section_id,section_name,subsection_id,subsection_name;
	private String selected_option_id,selected_date,selected_time,selected_pagaragraph,selected_main_comment,selected_option_comment,super_question_id,super_question_answer;
	private String obtained_points;

		private String start_date;
		private String end_date;
		 List<MysteryShoppingVisitsBean> getMSNResultAns;
		private String met_sc;
		private String sc_name;
		private String sc_designation;
		private String sc_description;
		private String shopper_link_name;
		private String shopper_link_age;
		private String shopper_link_email;
		private String shopper_link_contact;
		private String shopper_link_gender;
		private String shoppers_link_shoppersCarBrand;

		private String shoppers_link_shoppersCarModel;
		private String shoppers_link_shoppersCarYop;
		private String Shopper_link_productMet;

		private String Shopper_link_productNameProvided;
		private String Shopper_link_productName;
		private String Shopper_link_productDesc;
		private String shopper_link_visit_date;
		private String state_id;
		private String state_name;
		private String city_id;
		private String city_name;

		private String outlet_location_id;
		private String pincode;
		private String start_time;
		private String end_time;
		private String visit_status;
		private String mainquestiontype;
		private String mainquestionComment;
		private String standard_number;
		private String question_id;
		private String mainquestion;
		private String mainanswertype;
		private String subquestionid;
		private String subquestion;
		private String subanswertype;
		private String optionid;
		private String options;
		private String optioncomment;
		private String documents;
		private String comment;
		private String sid;
		private String shopper_document_count;
		private List<MysteryShoppingVisitsBean> documentType;
		private String document_name;
		private String document_type;
		private String sk_document_id;
		private String shopper_folder_name;
		private String extension;
		private String shopper_question_count;
		private String question_text;
		private String answer_status;
		private String scored_points;
		private String selected_option_text;
		private String created_by;
		private String sub_question_text;
		private String optionId;
		private String oldselectOptioncomment;
		private String old_selected_option;
		private String sk_question_id;
		private String super_quesiton_id;
		private String sp;
		private String percentage;
		private String outlet_level_score;
		private String process_excellence_score;
		private String customer_treatment_score;
		private String not_answered_status_count;
		private String points;
		private String contact_person;
		private String model_id;
	    private String metro;
	    private String subquestion_count;
	    private String oldselectedoptionid;
	    private String sk_id;
	    private String weightage;
	    private String pe_weightage;
	    private String ct_weightage;
	    private String osc_weightage;
		
		
		public String getOld_selected_option() {
			return old_selected_option;
		}

		public void setOld_selected_option(String old_selected_option) {
			this.old_selected_option = old_selected_option;
		}
		private String old_question_id,old_question_text,old_subquestion_id,old_subquestion_text, old_selectoption_id,old_selectionoption_text,old_paragraph,old_date,old_time,old_scored_points,old_mainquestion_comment,old_selectoption_comment;
		public String getOld_selectoption_id() {
			return old_selectoption_id;
		}

		public void setOld_selectoption_id(String old_selectoption_id) {
			this.old_selectoption_id = old_selectoption_id;
		}

		public String getOld_selectionoption_text() {
			return old_selectionoption_text;
		}

		public void setOld_selectionoption_text(String old_selectionoption_text) {
			this.old_selectionoption_text = old_selectionoption_text;
		}

		
		List<MysteryShoppingVisitsBean> missedOpportunitesList;
		
		
		 
		List<MysteryShoppingVisitsBean> optionslist;
		List<MysteryShoppingVisitsBean> sectionsList;
		List<MysteryShoppingVisitsBean> subquestionOptionList;
		List<MysteryShoppingVisitsBean> eachTableData;
		List<MysteryShoppingVisitsBean>subquestionDetails;
		private String overAllScore;
		
	private double ytd_dealer_avg1;


		public String getSk_shopper_id() {
			return sk_shopper_id;
		}

		public void setSk_shopper_id(String sk_shopper_id) {
			this.sk_shopper_id = sk_shopper_id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		
		
		public String getShoppers_car_owned() {
			return shoppers_car_owned;
		}
		public void setShoppers_car_owned(String shoppers_car_owned) {
			this.shoppers_car_owned = shoppers_car_owned;
		}
		

		

		

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getContact_number() {
			return contact_number;
		}

		public void setContact_number(String contact_number) {
			this.contact_number = contact_number;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getQuarter() {
			return quarter;
		}

		public void setQuarter(String quarter) {
			this.quarter = quarter;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getMode_of_contact() {
			return mode_of_contact;
		}

		public void setMode_of_contact(String mode_of_contact) {
			this.mode_of_contact = mode_of_contact;
		}

		public String getVisit_date() {
			return visit_date;
		}

		public void setVisit_date(String visit_date) {
			this.visit_date = visit_date;
		}


		

		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getOutlet_name() {
			return outlet_name;
		}

		public void setOutlet_name(String outlet_name) {
			this.outlet_name = outlet_name;
		}

		public String getDealer_name() {
			return dealer_name;
		}

		public void setDealer_name(String dealer_name) {
			this.dealer_name = dealer_name;
		}

		public String getBrand_name() {
			return brand_name;
		}

		public void setBrand_name(String brand_name) {
			this.brand_name = brand_name;
		}

		public String getModel_name() {
			return model_name;
		}

		public void setModel_name(String model_name) {
			this.model_name = model_name;
		}

		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		
		public String getOutlet_id() {
			return outlet_id;
		}
		public void setOutlet_id(String outlet_id) {
			this.outlet_id = outlet_id;
		}
		public String getBrand_model_id() {
			return brand_model_id;
		}
		public void setBrand_model_id(String brand_model_id) {
			this.brand_model_id = brand_model_id;
		}
		
		


		public String getBrand_id() {
			return brand_id;
		}

		public void setBrand_id(String brand_id) {
			this.brand_id = brand_id;
		}

		public String getStart_date() {
			return start_date;
		}

		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}

		public String getEnd_date() {
			return end_date;
		}

		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}

		public String getMet_sc() {
			return met_sc;
		}

		public void setMet_sc(String met_sc) {
			this.met_sc = met_sc;
		}

		public String getSc_name() {
			return sc_name;
		}

		public void setSc_name(String sc_name) {
			this.sc_name = sc_name;
		}

		public String getSc_designation() {
			return sc_designation;
		}

		public void setSc_designation(String sc_designation) {
			this.sc_designation = sc_designation;
		}

		public String getSc_description() {
			return sc_description;
		}

		public void setSc_description(String sc_description) {
			this.sc_description = sc_description;
		}

		public String getShopper_link_name() {
			return shopper_link_name;
		}

		public void setShopper_link_name(String shopper_link_name) {
			this.shopper_link_name = shopper_link_name;
		}

		public String getShopper_link_age() {
			return shopper_link_age;
		}

		public void setShopper_link_age(String shopper_link_age) {
			this.shopper_link_age = shopper_link_age;
		}

		public String getShopper_link_email() {
			return shopper_link_email;
		}

		public void setShopper_link_email(String shopper_link_email) {
			this.shopper_link_email = shopper_link_email;
		}

		public String getShopper_link_contact() {
			return shopper_link_contact;
		}

		public void setShopper_link_contact(String shopper_link_contact) {
			this.shopper_link_contact = shopper_link_contact;
		}

		public String getShopper_link_gender() {
			return shopper_link_gender;
		}

		public void setShopper_link_gender(String shopper_link_gender) {
			this.shopper_link_gender = shopper_link_gender;
		}

		public String getShoppers_link_shoppersCarBrand() {
			return shoppers_link_shoppersCarBrand;
		}

		public void setShoppers_link_shoppersCarBrand(String shoppers_link_shoppersCarBrand) {
			this.shoppers_link_shoppersCarBrand = shoppers_link_shoppersCarBrand;
		}

		public String getShoppers_link_shoppersCarModel() {
			return shoppers_link_shoppersCarModel;
		}

		public void setShoppers_link_shoppersCarModel(String shoppers_link_shoppersCarModel) {
			this.shoppers_link_shoppersCarModel = shoppers_link_shoppersCarModel;
		}

		public String getShoppers_link_shoppersCarYop() {
			return shoppers_link_shoppersCarYop;
		}

		public void setShoppers_link_shoppersCarYop(String shoppers_link_shoppersCarYop) {
			this.shoppers_link_shoppersCarYop = shoppers_link_shoppersCarYop;
		}

		public String getShopper_link_productMet() {
			return Shopper_link_productMet;
		}

		public void setShopper_link_productMet(String shopper_link_productMet) {
			Shopper_link_productMet = shopper_link_productMet;
		}

		public String getShopper_link_productNameProvided() {
			return Shopper_link_productNameProvided;
		}

		public void setShopper_link_productNameProvided(String shopper_link_productNameProvided) {
			Shopper_link_productNameProvided = shopper_link_productNameProvided;
		}

		public String getShopper_link_productName() {
			return Shopper_link_productName;
		}

		public void setShopper_link_productName(String shopper_link_productName) {
			Shopper_link_productName = shopper_link_productName;
		}

		public String getShopper_link_productDesc() {
			return Shopper_link_productDesc;
		}

		public void setShopper_link_productDesc(String shopper_link_productDesc) {
			Shopper_link_productDesc = shopper_link_productDesc;
		}

		

		public String getState_id() {
			return state_id;
		}

		public void setState_id(String state_id) {
			this.state_id = state_id;
		}

		public String getState_name() {
			return state_name;
		}

		public void setState_name(String state_name) {
			this.state_name = state_name;
		}

		public String getCity_id() {
			return city_id;
		}

		public void setCity_id(String city_id) {
			this.city_id = city_id;
		}

		public String getCity_name() {
			return city_name;
		}

		public void setCity_name(String city_name) {
			this.city_name = city_name;
		}

		public String getShoppers_car_owned_brand() {
			return shoppers_car_owned_brand;
		}

		public void setShoppers_car_owned_brand(String shoppers_car_owned_brand) {
			this.shoppers_car_owned_brand = shoppers_car_owned_brand;
		}

		public String getDealer_id() {
			return dealer_id;
		}

		public void setDealer_id(String dealer_id) {
			this.dealer_id = dealer_id;
		}

		public String getVariant_id() {
			return variant_id;
		}

		public void setVariant_id(String variant_id) {
			this.variant_id = variant_id;
		}

		public String getVariant_name() {
			return variant_name;
		}

		public void setVariant_name(String variant_name) {
			this.variant_name = variant_name;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}


		public String getShopper_link_visit_date() {
			return shopper_link_visit_date;
		}

		public void setShopper_link_visit_date(String shopper_link_visit_date) {
			this.shopper_link_visit_date = shopper_link_visit_date;
		}

		public String getOutlet_location_id() {
			return outlet_location_id;
		}

		public void setOutlet_location_id(String outlet_location_id) {
			this.outlet_location_id = outlet_location_id;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getStart_time() {
			return start_time;
		}

		public void setStart_time(String start_time) {
			this.start_time = start_time;
		}

		public String getEnd_time() {
			return end_time;
		}

		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}

		public String getVisit_status() {
			return visit_status;
		}

		public void setVisit_status(String visit_status) {
			this.visit_status = visit_status;
		}

		public String getMainquestiontype() {
			return mainquestiontype;
		}

		public void setMainquestiontype(String mainquestiontype) {
			this.mainquestiontype = mainquestiontype;
		}

		public String getMainquestionComment() {
			return mainquestionComment;
		}

		public void setMainquestionComment(String mainquestionComment) {
			this.mainquestionComment = mainquestionComment;
		}

		public String getStandard_number() {
			return standard_number;
		}

		public void setStandard_number(String standard_number) {
			this.standard_number = standard_number;
		}

		public String getQuestion_id() {
			return question_id;
		}

		public void setQuestion_id(String question_id) {
			this.question_id = question_id;
		}

		public String getMainquestion() {
			return mainquestion;
		}

		public void setMainquestion(String mainquestion) {
			this.mainquestion = mainquestion;
		}

		public String getMainanswertype() {
			return mainanswertype;
		}

		public void setMainanswertype(String mainanswertype) {
			this.mainanswertype = mainanswertype;
		}

		public String getSubquestionid() {
			return subquestionid;
		}

		public void setSubquestionid(String subquestionid) {
			this.subquestionid = subquestionid;
		}

		public String getSubquestion() {
			return subquestion;
		}

		public void setSubquestion(String subquestion) {
			this.subquestion = subquestion;
		}

		public String getSubanswertype() {
			return subanswertype;
		}

		public void setSubanswertype(String subanswertype) {
			this.subanswertype = subanswertype;
		}

		public String getOptionid() {
			return optionid;
		}

		public void setOptionid(String optionid) {
			this.optionid = optionid;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}

		public String getOptioncomment() {
			return optioncomment;
		}

		public void setOptioncomment(String optioncomment) {
			this.optioncomment = optioncomment;
		}

		public List<MysteryShoppingVisitsBean> getOptionslist() {
			return optionslist;
		}

		public void setOptionslist(List<MysteryShoppingVisitsBean> optionslist) {
			this.optionslist = optionslist;

		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}


		public String getFile() {
			return file;
		}

		public void setFile(String file) {
			this.file = file;
		}

		public List<MysteryShoppingVisitsBean> getDocumentType() {
			return documentType;
		}

		public void setDocumentType(List<MysteryShoppingVisitsBean> documentType) {
			this.documentType = documentType;
		}
		public String getDocuments() {
			return documents;
		}

		public void setDocuments(String documents) {
			this.documents = documents;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public String getSid() {
			return sid;
		}

		public void setSid(String sid) {
			this.sid = sid;
		}

		public String getShopper_document_count() {
			return shopper_document_count;
		}

		public void setShopper_document_count(String shopper_document_count) {
			this.shopper_document_count = shopper_document_count;
		}

		public String getDocument_name() {
			return document_name;
		}

		public void setDocument_name(String document_name) {
			this.document_name = document_name;
		}

		public String getSk_document_id() {
			return sk_document_id;
		}

		public void setSk_document_id(String sk_document_id) {
			this.sk_document_id = sk_document_id;
		}

		public String getShopper_folder_name() {
			return shopper_folder_name;
		}

		public void setShopper_folder_name(String shopper_folder_name) {
			this.shopper_folder_name = shopper_folder_name;
		}

		public String getDocument_type() {
			return document_type;
		}

		public void setDocument_type(String document_type) {
			this.document_type = document_type;
		}

		public String getExtension() {
			return extension;
		}

		public void setExtension(String extension) {
			this.extension = extension;
		}


		public String getStandard_number_sequence() {
			return standard_number_sequence;
		}

		public void setStandard_number_sequence(String standard_number_sequence) {
			this.standard_number_sequence = standard_number_sequence;
		}

		public String getQuestion_code() {
			return question_code;
		}

		public void setQuestion_code(String question_code) {
			this.question_code = question_code;
		}

		public String getFormula_flag() {
			return formula_flag;
		}

		public void setFormula_flag(String formula_flag) {
			this.formula_flag = formula_flag;
		}

		public String getQuestion_type() {
			return question_type;
		}

		public void setQuestion_type(String question_type) {
			this.question_type = question_type;
		}

		public String getQuestion_points() {
			return question_points;
		}

		public void setQuestion_points(String question_points) {
			this.question_points = question_points;
		}

		public String getQuestions() {
			return questions;
		}

		public void setQuestions(String questions) {
			this.questions = questions;
		}

		public String getAnswerParagraph() {
			return answerParagraph;
		}

		public void setAnswerParagraph(String answerParagraph) {
			this.answerParagraph = answerParagraph;
		}

		public String getAnswerDate() {
			return answerDate;
		}

		public void setAnswerDate(String answerDate) {
			this.answerDate = answerDate;
		}

		 

		public String getAnswerTime() {
			return answerTime;
		}

		public void setAnswerTime(String answerTime) {
			this.answerTime = answerTime;
		}

		public String getMain_ques_comment() {
			return main_ques_comment;
		}

		public void setMain_ques_comment(String main_ques_comment) {
			this.main_ques_comment = main_ques_comment;
		}

		public String getOption_comment() {
			return option_comment;
		}

		public void setOption_comment(String option_comment) {
			this.option_comment = option_comment;
		}

		public String getAnswer_type() {
			return answer_type;
		}

		public void setAnswer_type(String answer_type) {
			this.answer_type = answer_type;
		}

		public List<MysteryShoppingVisitsBean> getAnswerdata() {
			return answerdata;
		}

		public void setAnswerdata(List<MysteryShoppingVisitsBean> answerdata) {
			this.answerdata = answerdata;
		}

		public String getOption_points() {
			return option_points;
		}

		public void setOption_points(String option_points) {
			this.option_points = option_points;
		}

		public String getSk_answer_id() {
			return sk_answer_id;
		}

		public void setSk_answer_id(String sk_answer_id) {
			this.sk_answer_id = sk_answer_id;
		}

		public String getFormula_id() {
			return formula_id;
		}

		public void setFormula_id(String formula_id) {
			this.formula_id = formula_id;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getSk_formula_map_id() {
			return sk_formula_map_id;
		}

		public void setSk_formula_map_id(String sk_formula_map_id) {
			this.sk_formula_map_id = sk_formula_map_id;
		}

		public String getSelected_option_id() {
			return selected_option_id;
		}

		public void setSelected_option_id(String selected_option_id) {
			this.selected_option_id = selected_option_id;
		}

		public String getSelected_date() {
			return selected_date;
		}

		public void setSelected_date(String selected_date) {
			this.selected_date = selected_date;
		}

		public String getSelected_time() {
			return selected_time;
		}

		public void setSelected_time(String selected_time) {
			this.selected_time = selected_time;
		}

		public String getSelected_pagaragraph() {
			return selected_pagaragraph;
		}

		public void setSelected_pagaragraph(String selected_pagaragraph) {
			this.selected_pagaragraph = selected_pagaragraph;
		}

		public String getSelected_main_comment() {
			return selected_main_comment;
		}

		public void setSelected_main_comment(String selected_main_comment) {
			this.selected_main_comment = selected_main_comment;
		}

		public String getSelected_option_comment() {
			return selected_option_comment;
		}

		public void setSelected_option_comment(String selected_option_comment) {
			this.selected_option_comment = selected_option_comment;
		}

		public String getSuper_question_id() {
			return super_question_id;
		}

		public void setSuper_question_id(String super_question_id) {
			this.super_question_id = super_question_id;
		}

		public String getSuper_question_answer() {
			return super_question_answer;
		}

		public void setSuper_question_answer(String super_question_answer) {
			this.super_question_answer = super_question_answer;
		}

		public String getSuper_question_selected_id() {
			return super_question_selected_id;
		}

		public void setSuper_question_selected_id(String super_question_selected_id) {
			this.super_question_selected_id = super_question_selected_id;
		}

		public String getSuper_selected_qid() {
			return super_selected_qid;
		}

		public void setSuper_selected_qid(String super_selected_qid) {
			this.super_selected_qid = super_selected_qid;
		}

		public String getFinalpage() {
			return finalpage;
		}

		public void setFinalpage(String finalpage) {
			this.finalpage = finalpage;
		}


		public String getShopper_question_count() {
			return shopper_question_count;
		}

		public void setShopper_question_count(String shopper_question_count) {
			this.shopper_question_count = shopper_question_count;
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


		public String getSubsection_name() {
			return subsection_name;
		}

		public void setSubsection_name(String subsection_name) {
			this.subsection_name = subsection_name;
		}

		public String getQuestion_text() {
			return question_text;
		}

		public void setQuestion_text(String question_text) {
			this.question_text = question_text;
		}

		public String getAnswer_status() {
			return answer_status;
		}

		public void setAnswer_status(String answer_status) {
			this.answer_status = answer_status;
		}

		

		public String getSubsection_id() {
			return subsection_id;
		}

		public void setSubsection_id(String subsection_id) {
			this.subsection_id = subsection_id;
		}

		

		public List<MysteryShoppingVisitsBean> getSectionsList() {
			return sectionsList;
		}

		public void setSectionsList(List<MysteryShoppingVisitsBean> sectionsList) {
			this.sectionsList = sectionsList;
		}

		public String getMax_option_point() {
			return max_option_point;
		}

		public void setMax_option_point(String max_option_point) {
			this.max_option_point = max_option_point;
		}

		public String getCreated_by() {
			return created_by;
		}

		public void setCreated_by(String created_by) {
			this.created_by = created_by;
		}

		public String getSelected_option_text() {
			return selected_option_text;
		}

		public void setSelected_option_text(String selected_option_text) {
			this.selected_option_text = selected_option_text;
		}

		public List<MysteryShoppingVisitsBean> getSubquestionOptionList() {
			return subquestionOptionList;
		}

		public void setSubquestionOptionList(List<MysteryShoppingVisitsBean> subquestionOptionList) {
			this.subquestionOptionList = subquestionOptionList;
		}

		public String getSub_question_text() {
			return sub_question_text;
		}

		public void setSub_question_text(String sub_question_text) {
			this.sub_question_text = sub_question_text;
		}

		public String getOptionId() {
			return optionId;
		}

		public void setOptionId(String optionId) {
			this.optionId = optionId;
		}

		public String getScored_points() {
			return scored_points;
		}

		public void setScored_points(String scored_points) {
			this.scored_points = scored_points;
		}
		public String getSubQuestionAnswer() {
			return subQuestionAnswer;
		}

		public void setSubQuestionAnswer(String subQuestionAnswer) {
			this.subQuestionAnswer = subQuestionAnswer;
		}

		public String getReport_type() {
			return report_type;
		}

		public void setReport_type(String report_type) {
			this.report_type = report_type;
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


		public String getOldselectOptioncomment() {
			return oldselectOptioncomment;
		}

		public void setOldselectOptioncomment(String oldselectOptioncomment) {
			this.oldselectOptioncomment = oldselectOptioncomment;
		}

		public String getOld_question_id() {
			return old_question_id;
		}

		public void setOld_question_id(String old_question_id) {
			this.old_question_id = old_question_id;
		}

		public String getOld_question_text() {
			return old_question_text;
		}

		public void setOld_question_text(String old_question_text) {
			this.old_question_text = old_question_text;
		}

		public String getOld_subquestion_id() {
			return old_subquestion_id;
		}

		public void setOld_subquestion_id(String old_subquestion_id) {
			this.old_subquestion_id = old_subquestion_id;
		}

		public String getOld_subquestion_text() {
			return old_subquestion_text;
		}

		public void setOld_subquestion_text(String old_subquestion_text) {
			this.old_subquestion_text = old_subquestion_text;
		}

		public String getOld_paragraph() {
			return old_paragraph;
		}

		public void setOld_paragraph(String old_paragraph) {
			this.old_paragraph = old_paragraph;
		}

		public String getOld_date() {
			return old_date;
		}

		public void setOld_date(String old_date) {
			this.old_date = old_date;
		}

		public String getOld_time() {
			return old_time;
		}

		public void setOld_time(String old_time) {
			this.old_time = old_time;
		}

		public String getOld_scored_points() {
			return old_scored_points;
		}

		public void setOld_scored_points(String old_scored_points) {
			this.old_scored_points = old_scored_points;
		}

		public String getOld_mainquestion_comment() {
			return old_mainquestion_comment;
		}

		public void setOld_mainquestion_comment(String old_mainquestion_comment) {
			this.old_mainquestion_comment = old_mainquestion_comment;
		}

		public String getOld_selectoption_comment() {
			return old_selectoption_comment;
		}

		public void setOld_selectoption_comment(String old_selectoption_comment) {
			this.old_selectoption_comment = old_selectoption_comment;
		}

		public String getSk_question_id() {
			return sk_question_id;
		}

		public void setSk_question_id(String sk_question_id) {
			this.sk_question_id = sk_question_id;
		}

		public String getSuper_quesiton_id() {
			return super_quesiton_id;
		}

		public void setSuper_quesiton_id(String super_quesiton_id) {
			this.super_quesiton_id = super_quesiton_id;
		}

		public String getSp() {
			return sp;
		}

		public void setSp(String sp) {
			this.sp = sp;
		}
		public String getQuestion_optiontype() {
			return question_optiontype;
		}

		public void setQuestion_optiontype(String question_optiontype) {
			this.question_optiontype = question_optiontype;
		}

		public String getMandatory_optional_status() {
			return mandatory_optional_status;
		}

		public void setMandatory_optional_status(String mandatory_optional_status) {
			this.mandatory_optional_status = mandatory_optional_status;
		}


		public String getOsc_shopper_id() {
			return osc_shopper_id;
		}

		public void setOsc_shopper_id(String osc_shopper_id) {
			this.osc_shopper_id = osc_shopper_id;
		}
		public String getLimit() {
			return limit;
		}

		public void setLimit(String limit) {
			this.limit = limit;
		}

		public String getOutlet_score() {
			return outlet_score;
		}

		public void setOutlet_score(String outlet_score) {
			this.outlet_score = outlet_score;
		}

		

		public String getRegion_name() {
			return region_name;
		}

		public void setRegion_name(String region_name) {
			this.region_name = region_name;
		}

		public List<MysteryShoppingVisitsBean> getMissedOpportunitesList() {
			return missedOpportunitesList;
		}

		public void setMissedOpportunitesList(List<MysteryShoppingVisitsBean> missedOpportunitesList) {
			this.missedOpportunitesList = missedOpportunitesList;
		}

		public String getPercentage() {
			return percentage;
		}

		public void setPercentage(String percentage) {
			this.percentage = percentage;
		}

		/***summarysheet fields***/
		private String  regional_manager,monthname,outlet_city_name,metroandnonmetro,tier,point_reached,maximum_points,ytd_dealer_avg,ytd_outlet_avg,monthly_dealer_rank,monthly_dealer_avg;
		private String sectionScores,subsectionScores,data_accuracy,crm_compliance;
		private int rank;
		List<MysteryShoppingVisitsBean> sectionscore;
		List<MysteryShoppingVisitsBean> subsectionscore;
		
		List<MysteryShoppingVisitsBean> nonOscSubSectiobPoint;
		
		private double monthly_dealer_avg1;
		 

		public double getMonthly_dealer_avg1() {
			return monthly_dealer_avg1;
		}

		public void setMonthly_dealer_avg1(double monthly_dealer_avg1) {
			this.monthly_dealer_avg1 = monthly_dealer_avg1;
		}

		public String getRegional_manager() {
			return regional_manager;
		}

		public void setRegional_manager(String regional_manager) {
			this.regional_manager = regional_manager;
		}

		public String getMonthname() {
			return monthname;
		}

		public void setMonthname(String monthname) {
			this.monthname = monthname;
		}

		public String getOutlet_city_name() {
			return outlet_city_name;
		}

		public void setOutlet_city_name(String outlet_city_name) {
			this.outlet_city_name = outlet_city_name;
		}

		public String getMetroandnonmetro() {
			return metroandnonmetro;
		}

		public void setMetroandnonmetro(String metroandnonmetro) {
			this.metroandnonmetro = metroandnonmetro;
		}

		public String getTier() {
			return tier;
		}

		public void setTier(String tier) {
			this.tier = tier;
		}

		public String getPoint_reached() {
			return point_reached;
		}

		public void setPoint_reached(String point_reached) {
			this.point_reached = point_reached;
		}

		public String getMaximum_points() {
			return maximum_points;
		}

		public void setMaximum_points(String maximum_points) {
			this.maximum_points = maximum_points;
		}

		public String getYtd_dealer_avg() {
			return ytd_dealer_avg;
		}

		public void setYtd_dealer_avg(String ytd_dealer_avg) {
			this.ytd_dealer_avg = ytd_dealer_avg;
		}

		public String getYtd_outlet_avg() {
			return ytd_outlet_avg;
		}

		public void setYtd_outlet_avg(String ytd_outlet_avg) {
			this.ytd_outlet_avg = ytd_outlet_avg;
		}

		public String getMonthly_dealer_rank() {
			return monthly_dealer_rank;
		}

		public void setMonthly_dealer_rank(String monthly_dealer_rank) {
			this.monthly_dealer_rank = monthly_dealer_rank;
		}

		public String getMonthly_dealer_avg() {
			return monthly_dealer_avg;
		}

		public void setMonthly_dealer_avg(String monthly_dealer_avg) {
			this.monthly_dealer_avg = monthly_dealer_avg;
		}

		 

		public String getSectionScores() {
			return sectionScores;
		}

		public void setSectionScores(String sectionScores) {
			this.sectionScores = sectionScores;
		}

		public String getSubsectionScores() {
			return subsectionScores;
		}

		public void setSubsectionScores(String subsectionScores) {
			this.subsectionScores = subsectionScores;
		}

		public List<MysteryShoppingVisitsBean> getSectionscore() {
			return sectionscore;
		}

		public void setSectionscore(List<MysteryShoppingVisitsBean> sectionscore) {
			this.sectionscore = sectionscore;
		}

		public String getData_accuracy() {
			return data_accuracy;
		}

		public void setData_accuracy(String data_accuracy) {
			this.data_accuracy = data_accuracy;
		}

		public String getCrm_compliance() {
			return crm_compliance;
		}

		public void setCrm_compliance(String crm_compliance) {
			this.crm_compliance = crm_compliance;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public List<MysteryShoppingVisitsBean> getSubsectionscore() {
			return subsectionscore;
		}

		public void setSubsectionscore(List<MysteryShoppingVisitsBean> subsectionscore) {
			this.subsectionscore = subsectionscore;
		}

		public String getOutlet_level_score() {
			return outlet_level_score;
		}

		public void setOutlet_level_score(String outlet_level_score) {
			this.outlet_level_score = outlet_level_score;
		}

		public String getProcess_excellence_score() {
			return process_excellence_score;
		}

		public void setProcess_excellence_score(String process_excellence_score) {
			this.process_excellence_score = process_excellence_score;
		}

		public String getCustomer_treatment_score() {
			return customer_treatment_score;
		}

		public void setCustomer_treatment_score(String customer_treatment_score) {
			this.customer_treatment_score = customer_treatment_score;
		}

		public List<MysteryShoppingVisitsBean> getEachTableData() {
			return eachTableData;
		}

		public void setEachTableData(List<MysteryShoppingVisitsBean> eachTableData) {
			this.eachTableData = eachTableData;
		}

		public String getOverAllScore() {
			return overAllScore;
		}

		public void setOverAllScore(String overAllScore) {
			this.overAllScore = overAllScore;
		}


		public String getNot_answered_status_count() {
			return not_answered_status_count;
		}

		public void setNot_answered_status_count(String not_answered_status_count) {
			this.not_answered_status_count = not_answered_status_count;
		}

		public List<MysteryShoppingVisitsBean> getSubquestionDetails() {
			return subquestionDetails;
		}

		public void setSubquestionDetails(List<MysteryShoppingVisitsBean> subquestionDetails) {
			this.subquestionDetails = subquestionDetails;
		}
		public double getYtd_dealer_avg1() {
			return ytd_dealer_avg1;
		}

		public void setYtd_dealer_avg1(double ytd_dealer_avg1) {
			this.ytd_dealer_avg1 = ytd_dealer_avg1;
		}

		public List<MysteryShoppingVisitsBean> getGetMSNResultAns() {
			return getMSNResultAns;
		}

		public void setGetMSNResultAns(List<MysteryShoppingVisitsBean> getMSNResultAns) {
			this.getMSNResultAns = getMSNResultAns;
		}

		public String getOffset() {
			return offset;
		}

		public void setOffset(String offset) {
			this.offset = offset;
		}
		public String getPrevmonth() {
			return prevmonth;
		}

		public void setPrevmonth(String prevmonth) {
			this.prevmonth = prevmonth;
		}

		public String getCurrmonth() {
			return currmonth;
		}

		public void setCurrmonth(String currmonth) {
			this.currmonth = currmonth;
		}

		public String getPoints() {
			return points;
		}

		public void setPoints(String points) {
			this.points = points;
		}

		public String getContact_person() {
			return contact_person;
		}

		public void setContact_person(String contact_person) {
			this.contact_person = contact_person;
		}

		public String getModel_id() {
			return model_id;
		}

		public void setModel_id(String model_id) {
			this.model_id = model_id;
		}

		public String getMetro() {
			return metro;
		}

		public void setMetro(String metro) {
			this.metro = metro;
		}

		public String getSubquestion_count() {
			return subquestion_count;
		}

		public void setSubquestion_count(String subquestion_count) {
			this.subquestion_count = subquestion_count;
		}

		public String getOldselectedoptionid() {
			return oldselectedoptionid;
		}

		public void setOldselectedoptionid(String oldselectedoptionid) {
			this.oldselectedoptionid = oldselectedoptionid;
		}

		public String getWeekday() {
			return weekday;
		}

		public void setWeekday(String weekday) {
			this.weekday = weekday;
		}

		public int getYtd_dealer_rank() {
			return ytd_dealer_rank;
		}

		public void setYtd_dealer_rank(int ytd_dealer_rank) {
			this.ytd_dealer_rank = ytd_dealer_rank;
		}
		public String getObtained_points() {
			return obtained_points;
		}
		public void setObtained_points(String obtained_points) {
			this.obtained_points = obtained_points;
		}
		public List<MysteryShoppingVisitsBean> getNonOscSubSectiobPoint() {
			return nonOscSubSectiobPoint;
		}
		public void setNonOscSubSectiobPoint(List<MysteryShoppingVisitsBean> nonOscSubSectiobPoint) {
			this.nonOscSubSectiobPoint = nonOscSubSectiobPoint;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getSk_id() {
			return sk_id;
		}
		public void setSk_id(String sk_id) {
			this.sk_id = sk_id;
		}
		public String getWeightage() {
			return weightage;
		}
		public void setWeightage(String weightage) {
			this.weightage = weightage;
		}
		public String getPe_weightage() {
			return pe_weightage;
		}
		public void setPe_weightage(String pe_weightage) {
			this.pe_weightage = pe_weightage;
		}
		public String getCt_weightage() {
			return ct_weightage;
		}
		public void setCt_weightage(String ct_weightage) {
			this.ct_weightage = ct_weightage;
		}
		public String getOsc_weightage() {
			return osc_weightage;
		}
		public void setOsc_weightage(String osc_weightage) {
			this.osc_weightage = osc_weightage;
		}
		 
}
