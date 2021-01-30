package com.mystery.beans;
import java.util.List;

public class QuestionnaireBean extends DatabaseManagementBean  {

	private String sk_question_id;
	private String formulFlag;
	private String mode_of_contact;
	private String standard_number;
	private String standard_number_sequence;
	private String question_type;
	private String question_optiontype;
	private String question_points;
	private String question_code;
	private String question_response;
	private String question_routing;
	private String question;
	private String answer_type;
	private String comment_mandatory;
	private String comment_criteria;
	private String upload_file;
	private String sk_subquestion_id;
	private String sk_brand_id;
	private String formulaCount;
	private String sk_formula_id;
	private String year_applied;
	
	private String formulaFinalResult;
	
	private String selectedSubquestion_id;
	private String selectedanswerid;
	List<QuestionnaireBean> selectedoptionsList;
	List<QuestionnaireBean> optionsList;
	List<QuestionnaireBean> scoreList;
	

	public QuestionnaireBean() {
		//super();
		this.mode_of_contact = "ALL";
		this.sk_brand_id ="1";
	}

	public String getSk_brand_id() {
		return sk_brand_id;
	}

	public void setSk_brand_id(String sk_brand_id) {
		this.sk_brand_id = sk_brand_id;
	}

	//for date and time
	private String date_points;
	private String date_code;
	private String date_response;
	private String date_routing;
	private String time_points;
	private String time_code;
	private String time_routing;
	private String time_response;

	private List<QuestionnaireBean> answerdata;
	private List<QuestionnaireBean> subquestionanswers;
	private List<QuestionnaireBean> subquestions;
	private List<QuestionnaireBean> formula;
	private List<QuestionnaireBean> formuladata;

	private String answer;
	private String answer_points;
	private String answer_code;
	private String answer_response;
	private String answer_optiontype;
	private String answer_comment;
	private String routing_type;
	private String sk_answer_id;
	private String sk_datetime_id;
	private String super_question_id;
    private List<QuestionnaireBean> superquestions;
	private String super_question_answer;
	private String super_question_section_id;
	private String super_question_section_name;
	private String super_question_subsection_id;
	private String super_question_subsection_name;
	private String uploadFile;
	private String super_question_standard_number;

	private String subQuestion;
	private String subQuestionAnswerType;
	
	/*new beans by manoj d for OLR report*/
	private String scored_points;
	private String max_scored_points;
	private String max_question_points;
	private String paragraph;
	private String options;
	private String percentage;
	private String selected_option_comment;
	private String questionCount;
	private String formulapoints;
	private String total_ques_points;
	private String storevisitId;
	private String sumOfScoredPoints;
	private String sumOfQuesPoints;
	/*new beans by manoj d for OLR report*/
	
	/*new beans by manoj d for overallPerf Graph*/
	private String outlets;
	private String shopperIds;
	private String avg;
	/*new beans by manoj d for overallPerf Graph*/
	/*manoj d for crm compilance*/
	private String name;
	private String nodelay;
	private String upto2days;
	private String more2days;
	private String accurate;
	private String totalquestions;
	private String count;
	private String norecords;
	private String shopperId;
	private String yes;
	private String no;
	
	/*manoj d for crm compilance*/
	/*manoj d for Discount Analysis*/
	private String oneValue;
	private List<QuestionnaireBean> answerDetails;
	private String brand;
	/*manoj d for Discount Analysis*/
	/***********manoj d for training need analysis************/
	private String one;
	private String two;
	private String date;
	private String score;
	/***********manoj d for training need analysis************/
	public String getOneValue() {
		return oneValue;
	}

	public void setOneValue(String oneValue) {
		this.oneValue = oneValue;
	}

	public String getOutlets() {
		return outlets;
	}

	public void setOutlets(String outlets) {
		this.outlets = outlets;
	}

	public String getSubQuestion() {
		return subQuestion;
	}

	public void setSubQuestion(String subQuestion) {
		this.subQuestion = subQuestion;
	}

	public String getSubQuestionAnswerType() {
		return subQuestionAnswerType;
	}

	public void setSubQuestionAnswerType(String subQuestionAnswerType) {
		this.subQuestionAnswerType = subQuestionAnswerType;
	}

	public String getSk_question_id() {
		return sk_question_id;
	}

	public void setSk_question_id(String sk_question_id) {
		this.sk_question_id = sk_question_id;
	}

	public String getMode_of_contact() {
		return mode_of_contact;
	}

	public void setMode_of_contact(String mode_of_contact) {
		this.mode_of_contact = mode_of_contact;
	}

	public String getStandard_number() {
		return standard_number;
	}

	public void setStandard_number(String standard_number) {
		this.standard_number = standard_number;
	}

	public String getStandard_number_sequence() {
		return standard_number_sequence;
	}

	public void setStandard_number_sequence(String standard_number_sequence) {
		this.standard_number_sequence = standard_number_sequence;
	}

	public String getQuestion_type() {
		return question_type;
	}

	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}

	public String getQuestion_optiontype() {
		return question_optiontype;
	}

	public void setQuestion_optiontype(String question_optiontype) {
		this.question_optiontype = question_optiontype;
	}

	public String getQuestion_points() {
		return question_points;
	}

	public void setQuestion_points(String question_points) {
		this.question_points = question_points;
	}

	public String getQuestion_code() {
		return question_code;
	}

	public void setQuestion_code(String question_code) {
		this.question_code = question_code;
	}

	public String getQuestion_response() {
		return question_response;
	}

	public void setQuestion_response(String question_response) {
		this.question_response = question_response;
	}

	public String getQuestion_routing() {
		return question_routing;
	}

	public void setQuestion_routing(String question_routing) {
		this.question_routing = question_routing;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer_type() {
		return answer_type;
	}

	public void setAnswer_type(String answer_type) {
		this.answer_type = answer_type;
	}

	public String getComment_mandatory() {
		return comment_mandatory;
	}

	public void setComment_mandatory(String comment_mandatory) {
		this.comment_mandatory = comment_mandatory;
	}

	public String getComment_criteria() {
		return comment_criteria;
	}

	public void setComment_criteria(String comment_criteria) {
		this.comment_criteria = comment_criteria;
	}

	public String getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(String upload_file) {
		this.upload_file = upload_file;
	}

	public String getDate_points() {
		return date_points;
	}

	public void setDate_points(String date_points) {
		this.date_points = date_points;
	}

	public String getDate_code() {
		return date_code;
	}

	public void setDate_code(String date_code) {
		this.date_code = date_code;
	}

	public String getDate_response() {
		return date_response;
	}

	public void setDate_response(String date_response) {
		this.date_response = date_response;
	}

	public String getDate_routing() {
		return date_routing;
	}

	public void setDate_routing(String date_routing) {
		this.date_routing = date_routing;
	}

	public String getTime_points() {
		return time_points;
	}

	public void setTime_points(String time_points) {
		this.time_points = time_points;
	}

	public String getTime_code() {
		return time_code;
	}

	public void setTime_code(String time_code) {
		this.time_code = time_code;
	}

	public String getTime_routing() {
		return time_routing;
	}

	public void setTime_routing(String time_routing) {
		this.time_routing = time_routing;
	}

	public String getTime_response() {
		return time_response;
	}

	public void setTime_response(String time_response) {
		this.time_response = time_response;
	}

	public List<QuestionnaireBean> getAnswerdata() {
		return answerdata;
	}

	public void setAnswerdata(List<QuestionnaireBean> answerdata) {
		this.answerdata = answerdata;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer_points() {
		return answer_points;
	}

	public void setAnswer_points(String answer_points) {
		this.answer_points = answer_points;
	}

	public String getAnswer_code() {
		return answer_code;
	}

	public void setAnswer_code(String answer_code) {
		this.answer_code = answer_code;
	}

	public String getAnswer_response() {
		return answer_response;
	}

	public void setAnswer_response(String answer_response) {
		this.answer_response = answer_response;
	}

	public String getAnswer_optiontype() {
		return answer_optiontype;
	}

	public void setAnswer_optiontype(String answer_optiontype) {
		this.answer_optiontype = answer_optiontype;
	}

	public String getAnswer_comment() {
		return answer_comment;
	}

	public void setAnswer_comment(String answer_comment) {
		this.answer_comment = answer_comment;
	}

	public String getRouting_type() {
		return routing_type;
	}

	public void setRouting_type(String routing_type) {
		this.routing_type = routing_type;
	}

	public String getSk_answer_id() {
		return sk_answer_id;
	}

	public void setSk_answer_id(String sk_answer_id) {
		this.sk_answer_id = sk_answer_id;
	}

	public String getSk_datetime_id() {
		return sk_datetime_id;
	}

	public void setSk_datetime_id(String sk_datetime_id) {
		this.sk_datetime_id = sk_datetime_id;
	}

	public String getSuper_question_answer() {
		return super_question_answer;
	}

	public void setSuper_question_answer(String super_question_answer) {
		this.super_question_answer = super_question_answer;
	}

	public String getSuper_question_standard_number() {
		return super_question_standard_number;
	}

	public void setSuper_question_standard_number(String super_question_standard_number) {
		this.super_question_standard_number = super_question_standard_number;
	}

	public String getSuper_question_id() {
		return super_question_id;
	}

	public void setSuper_question_id(String super_question_id) {
		this.super_question_id = super_question_id;
	}

	public String getSuper_question_section_id() {
		return super_question_section_id;
	}

	public void setSuper_question_section_id(String super_question_section_id) {
		this.super_question_section_id = super_question_section_id;
	}

	public String getSuper_question_section_name() {
		return super_question_section_name;
	}

	public void setSuper_question_section_name(String super_question_section_name) {
		this.super_question_section_name = super_question_section_name;
	}

	public String getSuper_question_subsection_id() {
		return super_question_subsection_id;
	}

	public void setSuper_question_subsection_id(String super_question_subsection_id) {
		this.super_question_subsection_id = super_question_subsection_id;
	}

	public String getSuper_question_subsection_name() {
		return super_question_subsection_name;
	}

	public void setSuper_question_subsection_name(String super_question_subsection_name) {
		this.super_question_subsection_name = super_question_subsection_name;
	}

	public List<QuestionnaireBean> getSubquestions() {
		return subquestions;
	}

	public List<QuestionnaireBean> getSubquestionanswers() {
		return subquestionanswers;
	}

	public void setSubquestionanswers(List<QuestionnaireBean> subquestionanswers) {
		this.subquestionanswers = subquestionanswers;
	}

	public void setSubquestions(List<QuestionnaireBean> subquestions) {
		this.subquestions = subquestions;
	}

	public String getSk_subquestion_id() {
		return sk_subquestion_id;
	}

	public void setSk_subquestion_id(String sk_subquestion_id) {
		this.sk_subquestion_id = sk_subquestion_id;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public List<QuestionnaireBean> getFormula() {
		return formula;
	}

	public void setFormula(List<QuestionnaireBean> formula) {
		this.formula = formula;
	}

	public List<QuestionnaireBean> getFormuladata() {
		return formuladata;
	}

	public void setFormuladata(List<QuestionnaireBean> formuladata) {
		this.formuladata = formuladata;
	}

	public String getFormulaFinalResult() {
		return formulaFinalResult;
	}

	public void setFormulaFinalResult(String formulaFinalResult) {
		this.formulaFinalResult = formulaFinalResult;
	}

	public String getFormulaCount() {
		return formulaCount;
	}

	public void setFormulaCount(String formulaCount) {
		this.formulaCount = formulaCount;
	}

	public String getFormulFlag() {
		return formulFlag;
	}

	public void setFormulFlag(String formulFlag) {
		this.formulFlag = formulFlag;
	}

	public List<QuestionnaireBean> getSuperquestions() {
		return superquestions;
	}

	public void setSuperquestions(List<QuestionnaireBean> superquestions) {
		this.superquestions = superquestions;
	}

	public String getSk_formula_id() {
		return sk_formula_id;
	}

	public void setSk_formula_id(String sk_formula_id) {
		this.sk_formula_id = sk_formula_id;
	}

	public String getSelectedSubquestion_id() {
		return selectedSubquestion_id;
	}

	public void setSelectedSubquestion_id(String selectedSubquestion_id) {
		this.selectedSubquestion_id = selectedSubquestion_id;
	}

	public String getSelectedanswerid() {
		return selectedanswerid;
	}

	public void setSelectedanswerid(String selectedanswerid) {
		this.selectedanswerid = selectedanswerid;
	}

	public List<QuestionnaireBean> getSelectedoptionsList() {
		return selectedoptionsList;
	}

	public void setSelectedoptionsList(List<QuestionnaireBean> selectedoptionsList) {
		this.selectedoptionsList = selectedoptionsList;
	}

	public List<QuestionnaireBean> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<QuestionnaireBean> optionsList) {
		this.optionsList = optionsList;
	}

	public String getScored_points() {
		return scored_points;
	}

	public void setScored_points(String scored_points) {
		this.scored_points = scored_points;
	}

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getMax_scored_points() {
		return max_scored_points;
	}

	public void setMax_scored_points(String max_scored_points) {
		this.max_scored_points = max_scored_points;
	}

	public String getMax_question_points() {
		return max_question_points;
	}

	public void setMax_question_points(String max_question_points) {
		this.max_question_points = max_question_points;
	}

	public String getSelected_option_comment() {
		return selected_option_comment;
	}

	public void setSelected_option_comment(String selected_option_comment) {
		this.selected_option_comment = selected_option_comment;
	}

	public String getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(String questionCount) {
		this.questionCount = questionCount;
	}

	public String getFormulapoints() {
		return formulapoints;
	}

	public void setFormulapoints(String formulapoints) {
		this.formulapoints = formulapoints;
	}

	public String getTotal_ques_points() {
		return total_ques_points;
	}

	public void setTotal_ques_points(String total_ques_points) {
		this.total_ques_points = total_ques_points;
	}

	public String getStorevisitId() {
		return storevisitId;
	}

	public void setStorevisitId(String storevisitId) {
		this.storevisitId = storevisitId;
	}

	public String getShopperIds() {
		return shopperIds;
	}

	public void setShopperIds(String shopperIds) {
		this.shopperIds = shopperIds;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	public String getNodelay() {
		return nodelay;
	}

	public void setNodelay(String nodelay) {
		this.nodelay = nodelay;
	}

	public String getUpto2days() {
		return upto2days;
	}

	public void setUpto2days(String upto2days) {
		this.upto2days = upto2days;
	}

	public String getMore2days() {
		return more2days;
	}

	public void setMore2days(String more2days) {
		this.more2days = more2days;
	}

	public String getAccurate() {
		return accurate;
	}

	public void setAccurate(String accurate) {
		this.accurate = accurate;
	}

	public String getTotalquestions() {
		return totalquestions;
	}

	public void setTotalquestions(String totalquestions) {
		this.totalquestions = totalquestions;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getNorecords() {
		return norecords;
	}

	public void setNorecords(String norecords) {
		this.norecords = norecords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShopperId() {
		return shopperId;
	}

	public void setShopperId(String shopperId) {
		this.shopperId = shopperId;
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

	public List<QuestionnaireBean> getAnswerDetails() {
		return answerDetails;
	}

	public void setAnswerDetails(List<QuestionnaireBean> answerDetails) {
		this.answerDetails = answerDetails;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public List<QuestionnaireBean> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<QuestionnaireBean> scoreList) {
		this.scoreList = scoreList;
	}

	public String getSumOfScoredPoints() {
		return sumOfScoredPoints;
	}

	public void setSumOfScoredPoints(String sumOfScoredPoints) {
		this.sumOfScoredPoints = sumOfScoredPoints;
	}

	public String getSumOfQuesPoints() {
		return sumOfQuesPoints;
	}

	public void setSumOfQuesPoints(String sumOfQuesPoints) {
		this.sumOfQuesPoints = sumOfQuesPoints;
	}

	public String getYear_applied() {
		return year_applied;
	}

	public void setYear_applied(String year_applied) {
		this.year_applied = year_applied;
	}

	
}
