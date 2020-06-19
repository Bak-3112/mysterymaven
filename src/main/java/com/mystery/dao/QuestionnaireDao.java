package com.mystery.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mystery.api.Encryption;
import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.GraphBean;
import com.mystery.beans.MysteryShoppingVisitsBean;
import com.mystery.beans.QuestionnaireBean;




public class QuestionnaireDao {
	@Autowired
    JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}


	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	
	@Autowired
	private HttpServletRequest request;

	public QuestionnaireBean addMainQuestion(QuestionnaireBean qBean, List<QuestionnaireBean> questionSelectList) {

		if (qBean.getUpload_file() == null) {
			qBean.setUpload_file("no");
		}
		if (qBean.getAnswer_type().equals("Paragraph")) {
			System.out.println(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "')");
			template.execute(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "')");

		} else if (qBean.getAnswer_type().equals("Date")) {
			System.out.println(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`, `date_points`, `date_code`, `date_response`, `date_routing`) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "','"
							+ qBean.getDate_points() + "','" + qBean.getDate_code() + "','" + qBean.getDate_response()
							+ "','" + qBean.getDate_routing() + "')");
			template.execute(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`, `date_points`, `date_code`, `date_response`, `date_routing`) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "','"
							+ qBean.getDate_points() + "','" + qBean.getDate_code() + "','" + qBean.getDate_response()
							+ "','" + qBean.getDate_routing() + "')");

		}

		else if (qBean.getAnswer_type().equals("Date & Time")) {
			System.out.println(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`, `date_points`, `date_code`, `date_response`, `date_routing`,time_points,time_code,time_response,time_routing) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type() + "','" + qBean.getComment_mandatory().replace("'", "\\'") + "','"
							+ qBean.getUpload_file() + "','" + qBean.getDate_points() + "','" + qBean.getDate_code()
							+ "','" + qBean.getDate_response() + "','" + qBean.getDate_routing() + "','"
							+ qBean.getTime_points() + "','" + qBean.getTime_code() + "','" + qBean.getTime_response()
							+ "','" + qBean.getTime_routing() + "')");
			template.execute(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`, `date_points`, `date_code`, `date_response`, `date_routing`,time_points,time_code,time_response,time_routing) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type() + "','" + qBean.getComment_mandatory().replace("'", "\\'") + "','"
							+ qBean.getUpload_file() + "','" + qBean.getDate_points() + "','" + qBean.getDate_code()
							+ "','" + qBean.getDate_response() + "','" + qBean.getDate_routing() + "','"
							+ qBean.getTime_points() + "','" + qBean.getTime_code() + "','" + qBean.getTime_response()
							+ "','" + qBean.getTime_routing() + "')");

		} else {
			System.out.println(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number() + "','" + qBean.getQuestion_type().replace("'", "\\'") + "','"
							+ qBean.getQuestion_optiontype().replace("'", "\\'") + "','"
							+ qBean.getQuestion_points().replace("'", "\\'") + "','"
							+ qBean.getQuestion_code().replace("'", "\\'") + "','"
							+ qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "')");
			template.execute(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number() + "','" + qBean.getQuestion_type().replace("'", "\\'") + "','"
							+ qBean.getQuestion_optiontype().replace("'", "\\'") + "','"
							+ qBean.getQuestion_points().replace("'", "\\'") + "','"
							+ qBean.getQuestion_code().replace("'", "\\'") + "','"
							+ qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "')");

			template.queryForObject("select max(sk_question_id) as questionId from mst_questionare",
					new RowMapper<QuestionnaireBean>() {
						@Override
						public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
							// QuestionBean homeBean = new QuestionBean();
							String questionId = rs.getString("questionId");
							
							for (int i = 0; i < questionSelectList.size(); i++) {
								if (!(questionSelectList.get(i).getAnswer()==null)) {
								System.out.println(questionSelectList.get(i).getAnswer());
								template.execute(
										"INSERT INTO `mst_questionare_selectoption`(`question_id`, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`,option_routing_type) VALUES ('"
												+ questionId + "','" + qBean.getQuestion_type().replace("'", "\\'")
												+ "','" + questionSelectList.get(i).getAnswer().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'")
												+ "','" + questionSelectList.get(i).getAnswer_code().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getAnswer_comment().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getRouting_type().replace("'", "\\'")
												+ "')");
							}else {
								System.out.println("no index");
							}
							}

							return qBean;
						}
					});
		}
		return qBean;

	}

	public List<QuestionnaireBean> getQuestionnaireList(QuestionnaireBean qBean) {
	String sql="";
	

	sql="SELECT * FROM `mst_questionare` where question_status='active' and brand_id='"+qBean.getSk_brand_id()+"' and mode_of_contact='"+qBean.getMode_of_contact()+"' ORDER BY standard_number";
	System.out.println(sql);
			return template.query(sql,
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setQuestion(rs.getString("question"));
						qBean.setSk_question_id(rs.getString("sk_question_id"));
						qBean.setQuestion_code(rs.getString("question_code"));
						qBean.setQuestion_points(rs.getString("question_points"));
						qBean.setQuestion_response(rs.getString("question_response"));
						qBean.setQuestion_type(rs.getString("question_type"));
						qBean.setStandard_number(rs.getString("standard_number"));
						qBean.setStandard_number_sequence(rs.getString("standard_number_sequence"));
						qBean.setYear(rs.getString("year_applied"));
						return qBean;
					}
				});
	}

	public QuestionnaireBean getQuestionById(QuestionnaireBean qBean) {
		
		System.out.println(
				"main query====SELECT mst_questionare.*,mst_brand.*,mst_section.section_name,mst_subsection.subsection_name FROM `mst_questionare`,mst_brand,mst_subsection,mst_section WHERE mst_brand.sk_brand_id=mst_questionare.brand_id and  mst_questionare.subsection_id=mst_subsection.sk_subsection_id and mst_section.sk_section_id=mst_questionare.section_id and mst_questionare.question_status='active' and  `sk_question_id`='"
						+ qBean.getSk_question_id() + "'  order by standard_number ;");
		return template.queryForObject(
				"SELECT mst_questionare.*,mst_brand.*,mst_section.section_name,mst_subsection.subsection_name FROM `mst_questionare`,mst_brand,mst_subsection,mst_section WHERE mst_brand.sk_brand_id=mst_questionare.brand_id and  mst_questionare.subsection_id=mst_subsection.sk_subsection_id and mst_section.sk_section_id=mst_questionare.section_id and mst_questionare.question_status='active' and  `sk_question_id`='"
						+ qBean.getSk_question_id() + "'  order by standard_number  ;",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setQuestion(rs.getString("question"));
						qBean.setSk_question_id(rs.getString("sk_question_id"));
						qBean.setQuestion_code(rs.getString("question_code"));
						qBean.setQuestion_points(rs.getString("question_points"));
						qBean.setQuestion_response(rs.getString("question_response"));
						qBean.setQuestion_type(rs.getString("question_type"));
						qBean.setStandard_number(rs.getString("standard_number"));
						qBean.setQuestion_optiontype(rs.getString("question_optiontype"));
						qBean.setStandard_number_sequence(rs.getString("standard_number_sequence"));
						qBean.setAnswer_type(rs.getString("answer_type"));
						qBean.setSk_brand_id(rs.getString("brand_id"));
						qBean.setBrand_name(rs.getString("brand_name"));
						qBean.setSk_section_id(rs.getString("section_id"));
						qBean.setSection_name(rs.getString("section_name"));
						qBean.setSk_subsection_id(rs.getString("subsection_id"));
						qBean.setSubsection_name(rs.getString("subsection_name"));
						qBean.setMode_of_contact(rs.getString("mode_of_contact"));
						qBean.setComment_mandatory(rs.getString("comment_mandatory"));
						qBean.setUpload_file(rs.getString("upload_file"));
						qBean.setDate_code(rs.getString("date_code"));
						qBean.setDate_points(rs.getString("date_points"));
						qBean.setDate_response(rs.getString("date_response"));
						qBean.setDate_routing(rs.getString("date_routing"));
						qBean.setTime_code(rs.getString("time_code"));
						qBean.setTime_points(rs.getString("time_points"));
						qBean.setTime_response(rs.getString("time_response"));
						qBean.setTime_routing(rs.getString("time_routing"));
						qBean.setSuper_question_answer(rs.getString("super_question_answer"));
						qBean.setSuper_question_id(rs.getString("super_question_id"));
						qBean.setFormulFlag(rs.getString("formula_flag"));
						qBean.setYear(rs.getString("year_applied"));
						return qBean;
					}
						
				});
	}
	

	public List<QuestionnaireBean> getsubsectionsbyid(DatabaseManagementBean dbBean, String sk_section_id) {
		System.out.println("SELECT * from mst_subsection where mst_subsection.section_id='" + sk_section_id + "';");
		return template.query("SELECT * from mst_subsection where mst_subsection.section_id='" + sk_section_id + "'",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean dbBean = new QuestionnaireBean();
						dbBean.setSk_subsection_id(rs.getString("sk_subsection_id"));
						dbBean.setSubsection_name(rs.getString("subsection_name"));
						return dbBean;
					}
				});
	}

	public void updateQuestion(QuestionnaireBean qBean, String qid, List<QuestionnaireBean> questionSelectList, List<QuestionnaireBean> superQuestions) {
		if (qBean.getUpload_file() == null) {
			qBean.setUpload_file("no");
		}
		
		
		
		
		
		if (qBean.getAnswer_type().equals("Paragraph")) {
			System.out.println("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "' WHERE `sk_question_id`='" + qid + "'");
			template.execute("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "'  WHERE `sk_question_id`='" + qid + "'");
		} else if (qBean.getAnswer_type().equals("Date")) {
			System.out.println("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "' ,`date_points`='" + qBean.getDate_points().replace("'", "\\'")
					+ "',`date_code`='" + qBean.getDate_code().replace("'", "\\'") + "',`date_response`='"
					+ qBean.getDate_response().replace("'", "\\'") + "',`date_routing`='"
					+ qBean.getDate_routing().replace("'", "\\'") + "' WHERE `sk_question_id`='" + qid + "'");
			template.execute("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "',`date_points`='" + qBean.getDate_points().replace("'", "\\'")
					+ "',`date_code`='" + qBean.getDate_code().replace("'", "\\'") + "',`date_response`='"
					+ qBean.getDate_response().replace("'", "\\'") + "',`date_routing`='"
					+ qBean.getDate_routing().replace("'", "\\'") + "' WHERE `sk_question_id`='" + qid + "'");

		} else if (qBean.getAnswer_type().equals("Date & Time")) {
			System.out.println("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "',`date_points`='" + qBean.getDate_points().replace("'", "\\'")
					+ "',`date_code`='" + qBean.getDate_code().replace("'", "\\'") + "',`date_response`='"
					+ qBean.getDate_response().replace("'", "\\'") + "',`date_routing`='"
					+ qBean.getDate_routing().replace("'", "\\'") + "',`time_points`='"
					+ qBean.getTime_points().replace("'", "\\'").replace("'", "\\'") + "',`time_code`='"
					+ qBean.getTime_code().replace("'", "\\'") + "',`time_response`='"
					+ qBean.getTime_response().replace("'", "\\'") + "',`time_routing`='"
					+ qBean.getTime_routing().replace("'", "\\'") + "' WHERE `sk_question_id`='" + qid + "'");
			template.execute("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "',`date_points`='" + qBean.getDate_points().replace("'", "\\'")
					+ "',`date_code`='" + qBean.getDate_code().replace("'", "\\'") + "',`date_response`='"
					+ qBean.getDate_response().replace("'", "\\'") + "',`date_routing`='"
					+ qBean.getDate_routing().replace("'", "\\'") + "',`time_points`='"
					+ qBean.getTime_points().replace("'", "\\'").replace("'", "\\'") + "',`time_code`='"
					+ qBean.getTime_code().replace("'", "\\'") + "',`time_response`='"
					+ qBean.getTime_response().replace("'", "\\'") + "',`time_routing`='"
					+ qBean.getTime_routing().replace("'", "\\'") + "' WHERE `sk_question_id`='" + qid + "'");

		} else {
			System.out.println("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
			+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
			+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
			+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
			+ qBean.getUpload_file() + "'  WHERE `sk_question_id`='" + qid + "'");
	template.execute("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
			+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
			+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
			+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
			+ qBean.getUpload_file() + "'  WHERE `sk_question_id`='" + qid + "'");

			for (int i = 0; i < questionSelectList.size(); i++) {
				if (!(questionSelectList.get(i).getAnswer()==null)) {
				System.out.println(questionSelectList.get(i).getAnswer());
				if (questionSelectList.get(i).getSk_answer_id().equals("no_id")) {
					System.out.println(
							"INSERT INTO `mst_questionare_selectoption`(`question_id`, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`,option_routing_type) VALUES ('"
									+ qid + "','" + qBean.getQuestion_type() + "','"
									+ questionSelectList.get(i).getAnswer().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_code().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_comment().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getRouting_type().replace("'", "\\'") + "')");
					template.execute(
							"INSERT INTO `mst_questionare_selectoption`(`question_id`, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`,option_routing_type) VALUES ('"
									+ qid + "','" + qBean.getQuestion_type() + "','"
									+ questionSelectList.get(i).getAnswer().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_code().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_comment().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getRouting_type().replace("'", "\\'") + "')");

				} else {
					System.out.println("UPDATE `mst_questionare_selectoption SET `options`='"
							+ questionSelectList.get(i).getAnswer().replace("'", "\\'") + "',`option_points`='"
							+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'") + "',`option_code`='"
							+ questionSelectList.get(i).getAnswer_code().replace("'", "\\'") + "',`option_response`='"
							+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'")
							+ "',`option_optiontype`='"
							+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'")
							+ "',`option_comment`='" + questionSelectList.get(i).getAnswer_comment().replace("'", "\\'")
							+ "',`option_routing_type`='"
							+ questionSelectList.get(i).getRouting_type().replace("'", "\\'")
							+ "' WHERE `sk_answer_id`='"
							+ questionSelectList.get(i).getSk_answer_id().replace("'", "\\'") + "' and `question_id`='"
							+ qid + "';");
					template.execute("UPDATE mst_questionare_selectoption SET `options`='"
							+ questionSelectList.get(i).getAnswer().replace("'", "\\'") + "',`option_points`='"
							+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'") + "',`option_code`='"
							+ questionSelectList.get(i).getAnswer_code().replace("'", "\\'") + "',`option_response`='"
							+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'")
							+ "',`option_optiontype`='"
							+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'")
							+ "',`option_comment`='" + questionSelectList.get(i).getAnswer_comment().replace("'", "\\'")
							+ "',`option_routing_type`='"
							+ questionSelectList.get(i).getRouting_type().replace("'", "\\'")
							+ "' WHERE `sk_answer_id`='"
							+ questionSelectList.get(i).getSk_answer_id().replace("'", "\\'") + "' and `question_id`='"
							+ qid + "';");
				}
				}else {
					
				}
			}
		}

	}
	
	
	
	public void updateQuestion1(QuestionnaireBean qBean, String qid, List<QuestionnaireBean> questionSelectList, List<QuestionnaireBean> superQuestions) {
		if (qBean.getUpload_file() == null) {
			qBean.setUpload_file("no");
		}
		
		
		
		
		
		String superQuestionIds = "";
		String superQuestionAnswers="";
           if(null!=superQuestions && superQuestions.size()>0){
			for (QuestionnaireBean superQuestionsList : superQuestions) {
				if (!(superQuestionsList.getSuper_question_standard_number()==null)) {
			qBean.setSuper_question_standard_number(superQuestionsList.getSuper_question_standard_number());
			qBean.setSuper_question_section_id(superQuestionsList.getSuper_question_section_id());
			qBean.setSuper_question_subsection_id(superQuestionsList.getSuper_question_subsection_id());
			qBean.setSuper_question_answer(superQuestionsList.getSuper_question_answer());
			//System.out.println("super question section id="+superQuestionsList.getSuper_question_section_id()+"super question subsection id="+superQuestionsList.getSuper_question_subsection_id()+"super question standard number="+superQuestionsList.getSuper_question_standard_number()+"super question answer="+superQuestionsList.getSuper_question_answer());
				
			getSuperQuestionId(qBean);
			
				superQuestionIds+="##"+qBean.getSk_question_id();
				superQuestionAnswers+="##"+qBean.getSuper_question_answer();
			}else {
				
			}
			}
			
			}
		System.out.println(superQuestionIds.substring(2));
		System.out.println(superQuestionAnswers.substring(2));
		
		
		
		if (qBean.getAnswer_type().equals("Paragraph")) {
			System.out.println("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "' ,super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"' WHERE `sk_question_id`='" + qid + "'");
			template.execute("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "' ,super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"' WHERE `sk_question_id`='" + qid + "'");
		} else if (qBean.getAnswer_type().equals("Date")) {
			System.out.println("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "',super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"' ,`date_points`='" + qBean.getDate_points().replace("'", "\\'")
					+ "',`date_code`='" + qBean.getDate_code().replace("'", "\\'") + "',`date_response`='"
					+ qBean.getDate_response().replace("'", "\\'") + "',`date_routing`='"
					+ qBean.getDate_routing().replace("'", "\\'") + "' WHERE `sk_question_id`='" + qid + "'");
			template.execute("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "',super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"',`date_points`='" + qBean.getDate_points().replace("'", "\\'")
					+ "',`date_code`='" + qBean.getDate_code().replace("'", "\\'") + "',`date_response`='"
					+ qBean.getDate_response().replace("'", "\\'") + "',`date_routing`='"
					+ qBean.getDate_routing().replace("'", "\\'") + "' WHERE `sk_question_id`='" + qid + "'");

		} else if (qBean.getAnswer_type().equals("Date & Time")) {
			System.out.println("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "',super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"',`date_points`='" + qBean.getDate_points().replace("'", "\\'")
					+ "',`date_code`='" + qBean.getDate_code().replace("'", "\\'") + "',`date_response`='"
					+ qBean.getDate_response().replace("'", "\\'") + "',`date_routing`='"
					+ qBean.getDate_routing().replace("'", "\\'") + "',`time_points`='"
					+ qBean.getTime_points().replace("'", "\\'").replace("'", "\\'") + "',`time_code`='"
					+ qBean.getTime_code().replace("'", "\\'") + "',`time_response`='"
					+ qBean.getTime_response().replace("'", "\\'") + "',`time_routing`='"
					+ qBean.getTime_routing().replace("'", "\\'") + "' WHERE `sk_question_id`='" + qid + "'");
			template.execute("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
					+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
					+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
					+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
					+ qBean.getUpload_file() + "',super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"',`date_points`='" + qBean.getDate_points().replace("'", "\\'")
					+ "',`date_code`='" + qBean.getDate_code().replace("'", "\\'") + "',`date_response`='"
					+ qBean.getDate_response().replace("'", "\\'") + "',`date_routing`='"
					+ qBean.getDate_routing().replace("'", "\\'") + "',`time_points`='"
					+ qBean.getTime_points().replace("'", "\\'").replace("'", "\\'") + "',`time_code`='"
					+ qBean.getTime_code().replace("'", "\\'") + "',`time_response`='"
					+ qBean.getTime_response().replace("'", "\\'") + "',`time_routing`='"
					+ qBean.getTime_routing().replace("'", "\\'") + "' WHERE `sk_question_id`='" + qid + "'");

		} else {
			System.out.println("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
			+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
			+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
			+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
			+ qBean.getUpload_file() + "' ,super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"' WHERE `sk_question_id`='" + qid + "'");
	template.execute("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
			+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
			+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
			+ "',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='"
			+ qBean.getUpload_file() + "' ,super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"' WHERE `sk_question_id`='" + qid + "'");

			for (int i = 0; i < questionSelectList.size(); i++) {
				if (!(questionSelectList.get(i).getAnswer()==null)) {
				System.out.println(questionSelectList.get(i).getAnswer());
				if (questionSelectList.get(i).getSk_answer_id().equals("no_id")) {
					System.out.println(
							"INSERT INTO `mst_questionare_selectoption`(`question_id`, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`,option_routing_type) VALUES ('"
									+ qid + "','" + qBean.getQuestion_type() + "','"
									+ questionSelectList.get(i).getAnswer().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_code().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_comment().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getRouting_type().replace("'", "\\'") + "')");
					template.execute(
							"INSERT INTO `mst_questionare_selectoption`(`question_id`, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`,option_routing_type) VALUES ('"
									+ qid + "','" + qBean.getQuestion_type() + "','"
									+ questionSelectList.get(i).getAnswer().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_code().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getAnswer_comment().replace("'", "\\'") + "','"
									+ questionSelectList.get(i).getRouting_type().replace("'", "\\'") + "')");

				} else {
					System.out.println("UPDATE `mst_questionare_selectoption SET `options`='"
							+ questionSelectList.get(i).getAnswer().replace("'", "\\'") + "',`option_points`='"
							+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'") + "',`option_code`='"
							+ questionSelectList.get(i).getAnswer_code().replace("'", "\\'") + "',`option_response`='"
							+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'")
							+ "',`option_optiontype`='"
							+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'")
							+ "',`option_comment`='" + questionSelectList.get(i).getAnswer_comment().replace("'", "\\'")
							+ "',`option_routing_type`='"
							+ questionSelectList.get(i).getRouting_type().replace("'", "\\'")
							+ "' WHERE `sk_answer_id`='"
							+ questionSelectList.get(i).getSk_answer_id().replace("'", "\\'") + "' and `question_id`='"
							+ qid + "';");
					template.execute("UPDATE mst_questionare_selectoption SET `options`='"
							+ questionSelectList.get(i).getAnswer().replace("'", "\\'") + "',`option_points`='"
							+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'") + "',`option_code`='"
							+ questionSelectList.get(i).getAnswer_code().replace("'", "\\'") + "',`option_response`='"
							+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'")
							+ "',`option_optiontype`='"
							+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'")
							+ "',`option_comment`='" + questionSelectList.get(i).getAnswer_comment().replace("'", "\\'")
							+ "',`option_routing_type`='"
							+ questionSelectList.get(i).getRouting_type().replace("'", "\\'")
							+ "' WHERE `sk_answer_id`='"
							+ questionSelectList.get(i).getSk_answer_id().replace("'", "\\'") + "' and `question_id`='"
							+ qid + "';");
				}
				}else {
					
				}
			}
		}

	}
	
	

	public List<QuestionnaireBean> getStandardNumberByIds(QuestionnaireBean qBean, String section_id, String brand_id,
			String modeOfContact, String subsection_id) {
		System.out.println("SELECT * FROM `mst_questionare` WHERE `section_id`='" + section_id
				+ "' and `subsection_id`='" + subsection_id + "' and `brand_id`='" + brand_id
				+ "' and `mode_of_contact`='" + modeOfContact + "' and question_status='active';");
		return template.query("SELECT * FROM `mst_questionare` WHERE `section_id`='" + section_id
				+ "' and `subsection_id`='" + subsection_id + "' and `brand_id`='" + brand_id
				+ "' and `mode_of_contact`='" + modeOfContact + "' and question_status='active'", new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setStandard_number(rs.getString("standard_number"));
						qBean.setYear(rs.getString("year_applied"));
						return qBean;
					}
				});
	}

	public QuestionnaireBean addDependentQuestion(QuestionnaireBean qBean, List<QuestionnaireBean> questionSelectList,List<QuestionnaireBean> superQuestions) {
		if (qBean.getUpload_file() == null) {
			qBean.setUpload_file("no");
		}
		
		
		String superQuestionIds = "";
		String superQuestionAnswers="";
           if(null!=superQuestions && superQuestions.size()>0){
			for (QuestionnaireBean superQuestionsList : superQuestions) {
				if (!(superQuestionsList.getSuper_question_standard_number()==null)) {
			qBean.setSuper_question_standard_number(superQuestionsList.getSuper_question_standard_number());
			qBean.setSuper_question_section_id(superQuestionsList.getSuper_question_section_id());
			qBean.setSuper_question_subsection_id(superQuestionsList.getSuper_question_subsection_id());
			qBean.setSuper_question_answer(superQuestionsList.getSuper_question_answer());
			//System.out.println("super question section id="+superQuestionsList.getSuper_question_section_id()+"super question subsection id="+superQuestionsList.getSuper_question_subsection_id()+"super question standard number="+superQuestionsList.getSuper_question_standard_number()+"super question answer="+superQuestionsList.getSuper_question_answer());
				getSuperQuestionId(qBean);
				superQuestionIds+="##"+qBean.getSk_question_id();
				superQuestionAnswers+="##"+qBean.getSuper_question_answer();
				}else {
					
				}
			}
			
			}
		System.out.println(superQuestionIds.substring(2));

		if (qBean.getAnswer_type().equals("Paragraph")) {
			System.out.println(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "','"
							+ superQuestionIds.substring(2)+ "','" + superQuestionAnswers.substring(2) + "')");
			template.execute(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "','"
							+ superQuestionIds.substring(2) + "','" + superQuestionAnswers.substring(2) + "')");

		} else if (qBean.getAnswer_type().equals("Date")) {
			System.out.println(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer, `date_points`, `date_code`, `date_response`, `date_routing`) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "','"
							+ superQuestionIds.substring(2) + "','" + superQuestionAnswers.substring(2) + "','"
							+ qBean.getDate_points() + "','" + qBean.getDate_code() + "','" + qBean.getDate_response()
							+ "','" + qBean.getDate_routing() + "')");
			template.execute(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer, `date_points`, `date_code`, `date_response`, `date_routing`) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "','"
							+ superQuestionIds.substring(2) + "','" + superQuestionAnswers.substring(2) + "','"
							+ qBean.getDate_points() + "','" + qBean.getDate_code() + "','" + qBean.getDate_response()
							+ "','" + qBean.getDate_routing() + "')");

		} else if (qBean.getAnswer_type().equals("Date & Time")) {
			System.out.println(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer, `date_points`, `date_code`, `date_response`, `date_routing`,time_points,time_code,time_response,time_routing) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type() + "','" + qBean.getComment_mandatory().replace("'", "\\'") + "','"
							+ qBean.getUpload_file() + "','" + superQuestionIds.substring(2) + "','"
							+ superQuestionAnswers.substring(2) + "','" + qBean.getDate_points() + "','"
							+ qBean.getDate_code() + "','" + qBean.getDate_response() + "','" + qBean.getDate_routing()
							+ "','" + qBean.getTime_points() + "','" + qBean.getTime_code() + "','"
							+ qBean.getTime_response() + "','" + qBean.getTime_routing() + "')");
			template.execute(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer, `date_points`, `date_code`, `date_response`, `date_routing`,time_points,time_code,time_response,time_routing) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
							+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
							+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type() + "','" + qBean.getComment_mandatory().replace("'", "\\'") + "','"
							+ qBean.getUpload_file() + "','" + superQuestionIds.substring(2) + "','"
							+ superQuestionAnswers.substring(2) + "','" + qBean.getDate_points() + "','"
							+ qBean.getDate_code() + "','" + qBean.getDate_response() + "','" + qBean.getDate_routing()
							+ "','" + qBean.getTime_points() + "','" + qBean.getTime_code() + "','"
							+ qBean.getTime_response() + "','" + qBean.getTime_routing() + "')");

		} else {

			System.out.println(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number() + "','" + qBean.getQuestion_type().replace("'", "\\'") + "','"
							+ qBean.getQuestion_optiontype().replace("'", "\\'") + "','"
							+ qBean.getQuestion_points().replace("'", "\\'") + "','"
							+ qBean.getQuestion_code().replace("'", "\\'") + "','"
							+ qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "','"
							+ superQuestionIds.substring(2) + "','" + superQuestionAnswers.substring(2) + "')");
			template.execute(
					"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `answer_type`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer) VALUES ('"
							+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
							+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
							+ qBean.getStandard_number() + "','" + qBean.getQuestion_type().replace("'", "\\'") + "','"
							+ qBean.getQuestion_optiontype().replace("'", "\\'") + "','"
							+ qBean.getQuestion_points().replace("'", "\\'") + "','"
							+ qBean.getQuestion_code().replace("'", "\\'") + "','"
							+ qBean.getQuestion().replace("'", "\\'") + "','"
							+ qBean.getAnswer_type().replace("'", "\\'") + "','"
							+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "','"
							+ superQuestionIds.substring(2) + "','" + superQuestionAnswers.substring(2) + "')");

			template.queryForObject("select max(sk_question_id) as questionId from mst_questionare",
					new RowMapper<QuestionnaireBean>() {
						@Override
						public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
							// QuestionBean homeBean = new QuestionBean();
							String questionId = rs.getString("questionId");

							for (int i = 0; i < questionSelectList.size(); i++) {
								if (!(questionSelectList.get(i).getAnswer()==null)) {
								System.out.println(questionSelectList.get(i).getAnswer());
								template.execute(
										"INSERT INTO `mst_questionare_selectoption`(`question_id`, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`,option_routing_type) VALUES ('"
												+ questionId + "','" + qBean.getQuestion_type().replace("'", "\\'")
												+ "','" + questionSelectList.get(i).getAnswer().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getAnswer_points().replace("'", "\\'")
												+ "','" + questionSelectList.get(i).getAnswer_code().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getAnswer_response().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getAnswer_optiontype().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getAnswer_comment().replace("'", "\\'")
												+ "','"
												+ questionSelectList.get(i).getRouting_type().replace("'", "\\'")
												+ "')");
							
							}else {
							}	
							}

							return qBean;
						}
					});

		}
		return qBean;

	}

	private QuestionnaireBean getSuperQuestionId(QuestionnaireBean qBean) {
    System.out.println(
        "SELECT * FROM mst_questionare WHERE standard_number='" + qBean.getSuper_question_standard_number()
            + "'  and brand_id='" +qBean.getSk_brand_id()+ "' and mode_of_contact='"
            + qBean.getMode_of_contact() + "' and section_id='" + qBean.getSuper_question_section_id()
            + "' and  subsection_id='" + qBean.getSuper_question_subsection_id() + "';");
    return template.queryForObject(
        "SELECT * FROM mst_questionare WHERE standard_number='" + qBean.getSuper_question_standard_number()
            + "'  and brand_id='" +qBean.getSk_brand_id()+ "' and mode_of_contact='"
            + qBean.getMode_of_contact() + "' and section_id='" + qBean.getSuper_question_section_id()
            + "' and  subsection_id='" + qBean.getSuper_question_subsection_id() + "'  ",
        new RowMapper<QuestionnaireBean>() {
          @Override
          public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
            qBean.setSk_question_id(rs.getString("sk_question_id"));
            return qBean;
          }
        });
  }
	public List<QuestionnaireBean> getSuperQuestionAnswerBystdnum(QuestionnaireBean qBean, String stdnum,
		      String brand_id, String section_id, String subsection_id, String modeOfContact) {
		    System.out.println("SELECT * FROM mst_questionare,mst_questionare_selectoption WHERE standard_number='"
		        + stdnum
		        + "' and mst_questionare_selectoption.question_id=mst_questionare.sk_question_id and mst_questionare.question_status='active' and mst_questionare_selectoption.options_status='active' AND mst_questionare.answer_type='Select/List'  and mst_questionare.brand_id='"
		        + brand_id + "' and mst_questionare.mode_of_contact='" + modeOfContact
		        + "' and mst_questionare.section_id='" + section_id + "' and mst_questionare.subsection_id='"
		        + subsection_id + "';");
		    return template.query("SELECT * FROM mst_questionare,mst_questionare_selectoption WHERE standard_number='"
		        + stdnum
		        + "' and mst_questionare_selectoption.question_id=mst_questionare.sk_question_id and mst_questionare.question_status='active' and mst_questionare_selectoption.options_status='active' AND mst_questionare.answer_type='Select/List'  and mst_questionare.brand_id='"
		        + brand_id + "' and mst_questionare.mode_of_contact='" + modeOfContact
		        + "' and mst_questionare.section_id='" + section_id + "' and mst_questionare.subsection_id='"
		        + subsection_id + "' ", new RowMapper<QuestionnaireBean>() {
		          @Override
		          public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
		            QuestionnaireBean qBean = new QuestionnaireBean();
		            qBean.setSk_answer_id(rs.getString("sk_answer_id"));
		            qBean.setAnswer(rs.getString("options"));
		            return qBean;
		          }
		        });
		  }
	public List<QuestionnaireBean> getsuperquestionsubsectionsbyid(DatabaseManagementBean dbBean,
			String super_question_section_id) {
		System.out.println(
				"SELECT * from mst_subsection where mst_subsection.section_id='" + super_question_section_id + "';");
		return template.query(
				"SELECT * from mst_subsection where mst_subsection.section_id='" + super_question_section_id + "'",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean dbBean = new QuestionnaireBean();
						dbBean.setSk_subsection_id(rs.getString("sk_subsection_id"));
						dbBean.setSubsection_name(rs.getString("subsection_name"));
						return dbBean;
					}
				});
	}

	public void deletequestion(QuestionnaireBean qBean, String qid) {
		System.out.println("UPDATE mst_questionare SET question_status='inactive' WHERE sk_question_id='" + qid + "'");
		template.execute("UPDATE mst_questionare SET question_status='inactive' WHERE sk_question_id='" + qid + "'");
	}

	public void deleteanwerOptions(QuestionnaireBean qBean, String aid) {
		System.out.println(
				"UPDATE mst_questionare_selectoption SET options_status='inactive' WHERE sk_answer_id='" + aid + "'");
		template.execute(
				"UPDATE mst_questionare_selectoption SET options_status='inactive' WHERE sk_answer_id='" + aid + "'");
	}

	public QuestionnaireBean getStandardNumberExistance(QuestionnaireBean qBean, String section_id, String brand_id,
			String modeOfContact, String subsection_id, String standardNumber) {

		// TODO Auto-generated method stub
		String count = "";
		String sql = "SELECT count(*) as exist FROM `mst_questionare` WHERE `section_id`='" + section_id
				+ "' and `subsection_id`='" + subsection_id + "' and `brand_id`='" + brand_id
				+ "' and `mode_of_contact`='" + modeOfContact + "' and standard_number='" + standardNumber + "' and question_status='active';";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
		}
		int id_status = Integer.parseInt(count);
		if (id_status > 0) {
			qBean.setMessage("exist");
		} else {
			qBean.setMessage("notexist");
		}

		return qBean;

	}

	public QuestionnaireBean insertMainquestion(QuestionnaireBean qBean, List<QuestionnaireBean> superQuestions) {
	String superQuestionIds = "";
		String superQuestionAnswers="";
           if(null!=superQuestions && superQuestions.size()>0){
			for (QuestionnaireBean superQuestionsList : superQuestions) {
				if (!(superQuestionsList.getSuper_question_standard_number()==null)) {
			qBean.setSuper_question_standard_number(superQuestionsList.getSuper_question_standard_number());
			qBean.setSuper_question_section_id(superQuestionsList.getSuper_question_section_id());
			qBean.setSuper_question_subsection_id(superQuestionsList.getSuper_question_subsection_id());
			qBean.setSuper_question_answer(superQuestionsList.getSuper_question_answer());
			//System.out.println("super question section id="+superQuestionsList.getSuper_question_section_id()+"super question subsection id="+superQuestionsList.getSuper_question_subsection_id()+"super question standard number="+superQuestionsList.getSuper_question_standard_number()+"super question answer="+superQuestionsList.getSuper_question_answer());
				getSuperQuestionId(qBean);
				superQuestionIds+="##"+qBean.getSk_question_id();
				superQuestionAnswers+="##"+qBean.getSuper_question_answer();
			}else {
				
			}
			}
			
			}
		System.out.println(superQuestionIds.substring(2));
		
		if (qBean.getUpload_file() == null) {
			qBean.setUpload_file("no");
		}
		

		System.out.println(
				"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer) VALUES ('"
						+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
						+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
						+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
						+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
						+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"+ qBean.getComment_mandatory().replace("'", "\\") + "','" + qBean.getUpload_file() + "','"
						+ superQuestionIds.substring(2) + "','" + superQuestionAnswers.substring(2) + "')");
		template.execute(
				"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `comment_mandatory`, `upload_file`,super_question_id,	super_question_answer) VALUES ('"
						+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
						+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
						+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
						+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
						+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"+ qBean.getComment_mandatory().replace("'", "\\") + "','" + qBean.getUpload_file() + "','"
						+ superQuestionIds.substring(2) + "','" + superQuestionAnswers.substring(2) + "')");
		return template.queryForObject("select max(sk_question_id) as questionId from mst_questionare",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setSk_question_id(rs.getString("questionId"));
						return qBean;
					}
				});

	}

	public List<QuestionnaireBean> getsubquestionsList(DatabaseManagementBean dbBean, String sk_question_id) {
		System.out.println(
				"SELECT mst_questionare_subquestion.* FROM `mst_questionare_subquestion`,mst_questionare WHERE `question_id`='"
						+ sk_question_id
						+ "' and mst_questionare_subquestion.question_id=mst_questionare.sk_question_id");
		return template.query(
				"SELECT mst_questionare_subquestion.* FROM `mst_questionare_subquestion`,mst_questionare WHERE `question_id`='"
						+ sk_question_id
						+ "' and mst_questionare_subquestion.question_id=mst_questionare.sk_question_id",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setSuper_question_id(rs.getString("question_id"));
						qBean.setSk_subquestion_id(rs.getString("sk_subquestion_id"));
						qBean.setQuestion_type(rs.getString("question_type"));
						qBean.setSubQuestion(rs.getString("subquestion"));
						qBean.setSubQuestionAnswerType(rs.getString("subanswer_type"));
						qBean.setUploadFile(rs.getString("subupload_type"));
						qBean.setDate_code(rs.getString("subdate_code"));
						qBean.setDate_points(rs.getString("subdate_points"));
						qBean.setDate_response(rs.getString("subdate_response"));
						qBean.setDate_routing(rs.getString("subdate_routing"));
						qBean.setTime_code(rs.getString("subtime_code"));
						qBean.setTime_points(rs.getString("subtime_points"));
						qBean.setTime_response(rs.getString("subtime_response"));
						qBean.setTime_routing(rs.getString("subtime_routing"));
						return qBean;
					}
				});
	}

	public List<QuestionnaireBean> dependentsubquestionsDateList(DatabaseManagementBean dbBean, String sk_question_id) {
		System.out.println(
				"SELECT mst_questionare_subquestion.* FROM `mst_questionare_subquestion`,mst_questionare WHERE `question_id`='"
						+ sk_question_id
						+ "' and mst_questionare_subquestion.question_id=mst_questionare.sk_question_id and subanswer_type='Date'");
		return template.query(
				"SELECT mst_questionare_subquestion.* FROM `mst_questionare_subquestion`,mst_questionare WHERE `question_id`='"
						+ sk_question_id
						+ "' and mst_questionare_subquestion.question_id=mst_questionare.sk_question_id and subanswer_type='Date'",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setSuper_question_id(rs.getString("question_id"));
						qBean.setSk_subquestion_id(rs.getString("sk_subquestion_id"));
						qBean.setQuestion_type(rs.getString("question_type"));
						qBean.setSubQuestion(rs.getString("subquestion"));
						qBean.setSubQuestionAnswerType(rs.getString("subanswer_type"));
						qBean.setUploadFile(rs.getString("subupload_type"));
						qBean.setDate_code(rs.getString("subdate_code"));
						qBean.setDate_points(rs.getString("subdate_points"));
						qBean.setDate_response(rs.getString("subdate_response"));
						qBean.setDate_routing(rs.getString("subdate_routing"));
						qBean.setTime_code(rs.getString("subtime_code"));
						qBean.setTime_points(rs.getString("subtime_points"));
						qBean.setTime_response(rs.getString("subtime_response"));
						qBean.setTime_routing(rs.getString("subtime_routing"));
						return qBean;
					}
				});
	}

	public List<QuestionnaireBean> dependentsubquestionsDateTimeList(DatabaseManagementBean dbBean,
			String sk_question_id) {
		System.out.println(
				"SELECT mst_questionare_subquestion.* FROM `mst_questionare_subquestion`,mst_questionare WHERE `question_id`='"
						+ sk_question_id
						+ "' and mst_questionare_subquestion.question_id=mst_questionare.sk_question_id and subanswer_type='Date & Time'");
		return template.query(
				"SELECT mst_questionare_subquestion.* FROM `mst_questionare_subquestion`,mst_questionare WHERE `question_id`='"
						+ sk_question_id
						+ "' and mst_questionare_subquestion.question_id=mst_questionare.sk_question_id and subanswer_type='Date & Time'",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setSuper_question_id(rs.getString("question_id"));
						qBean.setSk_subquestion_id(rs.getString("sk_subquestion_id"));
						qBean.setQuestion_type(rs.getString("question_type"));
						qBean.setSubQuestion(rs.getString("subquestion"));
						qBean.setSubQuestionAnswerType(rs.getString("subanswer_type"));
						qBean.setUploadFile(rs.getString("subupload_type"));
						qBean.setDate_code(rs.getString("subdate_code"));
						qBean.setDate_points(rs.getString("subdate_points"));
						qBean.setDate_response(rs.getString("subdate_response"));
						qBean.setDate_routing(rs.getString("subdate_routing"));
						qBean.setTime_code(rs.getString("subtime_code"));
						qBean.setTime_points(rs.getString("subtime_points"));
						qBean.setTime_response(rs.getString("subtime_response"));
						qBean.setTime_routing(rs.getString("subtime_routing"));
						return qBean;
					}
				});
	}

	public void updateMainquestion(QuestionnaireBean qBean, String qid) {
		System.out.println("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
				+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
				+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
				+ "' WHERE `sk_question_id`='" + qid + "'");
		template.execute("UPDATE `mst_questionare` SET `question_optiontype`='" + qBean.getQuestion_optiontype()
				+ "',`question_points`='" + qBean.getQuestion_points() + "',`question_code`='"
				+ qBean.getQuestion_code() + "',`question`='" + qBean.getQuestion().replace("'", "\\'")
				+ "'  WHERE `sk_question_id`='" + qid + "'");

	}

	public void updateDependentQuestionwithSubquestion(QuestionnaireBean qBean, String subanswertype,
			List<QuestionnaireBean> subquestionsList, String qid, int i) {
		if (subanswertype.equals("Paragraph")) {

			System.out.println(
					"update  `mst_questionare_subquestion` set subquestion='" + subquestionsList.get(i).getSubQuestion()
							+ "', subupload_type='" + subquestionsList.get(i).getUploadFile() + "' where question_id='"
							+ qid + "' and sk_subquestion_id='" + subquestionsList.get(i).getSubQuestion() + "'");
			template.execute(
					"update  `mst_questionare_subquestion` set subquestion='" + subquestionsList.get(i).getSubQuestion()
							+ "', subupload_type='" + subquestionsList.get(i).getUploadFile() + "' where question_id='"
							+ qid + "' and sk_subquestion_id='" + subquestionsList.get(i).getSubQuestion() + "'");

		} else if (subanswertype.equals("Date")) {
			System.out.println(
					"update  `mst_questionare_subquestion` set subquestion='" + subquestionsList.get(i).getSubQuestion()
							+ "', subupload_type='" + subquestionsList.get(i).getUploadFile() + "',subdate_points='"
							+ subquestionsList.get(i).getDate_points() + "',subdate_code='"
							+ subquestionsList.get(i).getDate_code() + "',subdate_response='"
							+ subquestionsList.get(i).getDate_response() + "',subdate_routing='"
							+ subquestionsList.get(i).getDate_routing() + "' where question_id='" + qid
							+ "' and sk_subquestion_id='" + subquestionsList.get(i).getSk_subquestion_id() + "'");
			template.execute(
					"update  `mst_questionare_subquestion` set subquestion='" + subquestionsList.get(i).getSubQuestion()
							+ "', subupload_type='" + subquestionsList.get(i).getUploadFile() + "',subdate_points='"
							+ subquestionsList.get(i).getDate_points() + "',subdate_code='"
							+ subquestionsList.get(i).getDate_code() + "',subdate_response='"
							+ subquestionsList.get(i).getDate_response() + "',subdate_routing='"
							+ subquestionsList.get(i).getDate_routing() + "' where question_id='" + qid
							+ "' and sk_subquestion_id='" + subquestionsList.get(i).getSk_subquestion_id() + "'");
		} else if (subanswertype.equals("Date & Time")) {

			System.out.println(
					"update  `mst_questionare_subquestion` set`subquestion='" + subquestionsList.get(i).getSubQuestion()
							+ "', subupload_type='" + subquestionsList.get(i).getUploadFile() + "',subdate_points='"
							+ subquestionsList.get(i).getDate_points() + "',subdate_code='"
							+ subquestionsList.get(i).getDate_code() + "',subdate_response='"
							+ subquestionsList.get(i).getDate_response() + "',subdate_routing='"
							+ subquestionsList.get(i).getDate_routing() + "'  , `subtime_points`='"
							+ subquestionsList.get(i).getTime_points() + "', `subtime_code`='"
							+ subquestionsList.get(i).getTime_code() + "', `subtime_response`='"
							+ subquestionsList.get(i).getTime_response() + "', `subtime_routing`='"
							+ subquestionsList.get(i).getTime_routing() + "'   where question_id='" + qid
							+ "' and sk_subquestion_id='" + subquestionsList.get(i).getSk_subquestion_id() + "'");
			template.execute(
					"update  `mst_questionare_subquestion` set`subquestion='" + subquestionsList.get(i).getSubQuestion()
							+ "', subupload_type='" + subquestionsList.get(i).getUploadFile() + "',subdate_points='"
							+ subquestionsList.get(i).getDate_points() + "',subdate_code='"
							+ subquestionsList.get(i).getDate_code() + "',subdate_response='"
							+ subquestionsList.get(i).getDate_response() + "',subdate_routing='"
							+ subquestionsList.get(i).getDate_routing() + "'  , `subtime_points`='"
							+ subquestionsList.get(i).getTime_points() + "', `subtime_code`='"
							+ subquestionsList.get(i).getTime_code() + "', `subtime_response`='"
							+ subquestionsList.get(i).getTime_response() + "', `subtime_routing`='"
							+ subquestionsList.get(i).getTime_routing() + "'   where question_id='" + qid
							+ "' and sk_subquestion_id='" + subquestionsList.get(i).getSk_subquestion_id() + "'");

		}

	}

	public QuestionnaireBean updateSubquestion(QuestionnaireBean qBean, String questionId, int i,
			List<QuestionnaireBean> subquestionsList) {
		if (subquestionsList.get(i).getUploadFile() == null) {
			subquestionsList.get(i).setUploadFile("no");
		}

		if (subquestionsList.get(i).getSk_subquestion_id() == "no_sub_id") {

			System.out.println(
					"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type) VALUES ('"
							+ questionId + "','" + qBean.getQuestion_type() + "','"
							+ subquestionsList.get(i).getSubQuestion() + "','"
							+ subquestionsList.get(i).getSubQuestionAnswerType() + "','"
							+ subquestionsList.get(i).getUploadFile() + "')");
			template.execute(
					"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type) VALUES ('"
							+ questionId + "','" + qBean.getQuestion_type() + "','"
							+ subquestionsList.get(i).getSubQuestion() + "','"
							+ subquestionsList.get(i).getSubQuestionAnswerType() + "','"
							+ subquestionsList.get(i).getUploadFile() + "')");
			return template.queryForObject(
					"select max(sk_subquestion_id) as sk_subquestion_id,question_id from mst_questionare_subquestion",
					new RowMapper<QuestionnaireBean>() {
						@Override
						public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
							qBean.setSuper_question_id(questionId);
							qBean.setSk_subquestion_id(rs.getString("sk_subquestion_id"));
							if (subquestionsList.get(i).getSubQuestionAnswerType() == "select/List") {
								for (int j = 0; j < subquestionsList.get(i).getSubquestionanswers().size(); j++) {
									System.out.println(
											"INSERT INTO `mst_questionare_selectoption`(`question_id`,subquestion_id, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`) VALUES ('"
													+ questionId + "','" + rs.getString("sk_subquestion_id") + "','"
													+ qBean.getQuestion_type().replace("'", "\\'") + "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j).getAnswer()
															.replace("'", "\\'")
													+ "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_points().replace("'", "\\'")
													+ "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_code().replace("'", "\\'")
													+ "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_response().replace("'", "\\'")
													+ "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_optiontype().replace("'", "\\'")
													+ "','" + subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_comment().replace("'", "\\'")
													+ "')");
									template.execute(
											"INSERT INTO `mst_questionare_selectoption`(`question_id`,subquestion_id, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`) VALUES ('"
													+ questionId + "','" + rs.getString("sk_subquestion_id") + "','"
													+ qBean.getQuestion_type().replace("'", "\\'") + "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j).getAnswer()
															.replace("'", "\\'")
													+ "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_points().replace("'", "\\'")
													+ "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_code().replace("'", "\\'")
													+ "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_response().replace("'", "\\'")
													+ "','"
													+ subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_optiontype().replace("'", "\\'")
													+ "','" + subquestionsList.get(i).getSubquestionanswers().get(j)
															.getAnswer_comment().replace("'", "\\'")
													+ "')");
								}
							} else {

							}
							return qBean;
						}
					});
		} else {

			System.out.println("update  `mst_questionare_subquestion` set subquestion='"
					+ subquestionsList.get(i).getSubQuestion() + "', subupload_type='"
					+ subquestionsList.get(i).getUploadFile() + "' where question_id='" + questionId
					+ "' and sk_subquestion_id='" + subquestionsList.get(i).getSk_subquestion_id() + "'");
			template.execute("update  `mst_questionare_subquestion` set subquestion='"
					+ subquestionsList.get(i).getSubQuestion() + "', subupload_type='"
					+ subquestionsList.get(i).getUploadFile() + "' where question_id='" + questionId
					+ "' and sk_subquestion_id='" + subquestionsList.get(i).getSk_subquestion_id() + "'");
			for (int j = 0; j < subquestionsList.get(i).getSubquestionanswers().size(); j++) {
				String subanswertype = subquestionsList.get(i).getSubQuestionAnswerType();
				updateDependentQuestionwithSubquestionSelectList(qBean, subanswertype, i, j, subquestionsList,
						questionId, subquestionsList.get(i).getSk_subquestion_id());
			}
		}
		return qBean;

	}

	public void updateDependentQuestionwithSubquestionSelectList(QuestionnaireBean qBean, String subanswertype, int i,
			int j, List<QuestionnaireBean> subquestionsList, String questionId, String subquestionId) {
		System.out.println("UPDATE `mst_questionare_selectoption SET `options`='"
				+ subquestionsList.get(i).getAnswer().replace("'", "\\'") + "',`option_points`='"
				+ subquestionsList.get(i).getAnswer_points().replace("'", "\\'") + "',`option_code`='"
				+ subquestionsList.get(i).getAnswer_code().replace("'", "\\'") + "',`option_response`='"
				+ subquestionsList.get(i).getAnswer_response().replace("'", "\\'") + "',`option_optiontype`='"
				+ subquestionsList.get(i).getAnswer_optiontype().replace("'", "\\'") + "',`option_comment`='"
				+ subquestionsList.get(i).getAnswer_comment().replace("'", "\\'") + "',`option_routing_type`='"
				+ subquestionsList.get(i).getRouting_type().replace("'", "\\'") + "' WHERE `sk_answer_id`='"
				+ subquestionsList.get(i).getSk_answer_id().replace("'", "\\'") + "' and `question_id`='" + questionId
				+ "' and subquestion_id='" + subquestionId + "';");
		template.execute("UPDATE `mst_questionare_selectoption SET `options`='"
				+ subquestionsList.get(i).getAnswer().replace("'", "\\'") + "',`option_points`='"
				+ subquestionsList.get(i).getAnswer_points().replace("'", "\\'") + "',`option_code`='"
				+ subquestionsList.get(i).getAnswer_code().replace("'", "\\'") + "',`option_response`='"
				+ subquestionsList.get(i).getAnswer_response().replace("'", "\\'") + "',`option_optiontype`='"
				+ subquestionsList.get(i).getAnswer_optiontype().replace("'", "\\'") + "',`option_comment`='"
				+ subquestionsList.get(i).getAnswer_comment().replace("'", "\\'") + "',`option_routing_type`='"
				+ subquestionsList.get(i).getRouting_type().replace("'", "\\'") + "' WHERE `sk_answer_id`='"
				+ subquestionsList.get(i).getSk_answer_id().replace("'", "\\'") + "' and `question_id`='" + questionId
				+ "' and subquestion_id='" + subquestionId + "';");
	}

	public QuestionnaireBean insertMainsetofsubquestionsquestion(QuestionnaireBean qBean) {

		if (qBean.getUpload_file() == null) {
			qBean.setUpload_file("no");
		}
		
		System.out.println(
				"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `comment_mandatory`, `upload_file`) VALUES ('"
						+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
						+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
						+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
						+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
						+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "')");
		template.execute(
				"INSERT INTO `mst_questionare`( `brand_id`, `mode_of_contact`, `section_id`, `subsection_id`, `standard_number`, `question_type`, `question_optiontype`, `question_points`, `question_code`,  `question`, `comment_mandatory`, `upload_file`) VALUES ('"
						+ qBean.getSk_brand_id() + "','" + qBean.getMode_of_contact().replace("'", "\\'") + "','"
						+ qBean.getSk_section_id() + "','" + qBean.getSk_subsection_id() + "','"
						+ qBean.getStandard_number().replace("'", "\\'") + "','" + qBean.getQuestion_type() + "','"
						+ qBean.getQuestion_optiontype() + "','" + qBean.getQuestion_points() + "','"
						+ qBean.getQuestion_code() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"+ qBean.getComment_mandatory().replace("'", "\\'") + "','" + qBean.getUpload_file() + "')");
		return template.queryForObject("select max(sk_question_id) as questionId from mst_questionare",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setSk_question_id(rs.getString("questionId"));
						return qBean;
					}
				});

	}

	public QuestionnaireBean addDependentQuestionwithSubquestion(QuestionnaireBean qBean, String subanswertype,
			QuestionnaireBean subQuestionList, String questionId) {
		if (subQuestionList.getUploadFile() == null) {
			subQuestionList.setUploadFile("no");
		}
		if (subanswertype.equals("Paragraph")) {

			System.out.println(
					"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type) VALUES ('"
							+ questionId + "','" + qBean.getQuestion_type() + "','" + subQuestionList.getSubQuestion()
							+ "','" + subQuestionList.getSubQuestionAnswerType() + "','"
							+ subQuestionList.getUploadFile() + "')");
			template.execute(
					"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type) VALUES ('"
							+ questionId + "','" + qBean.getQuestion_type() + "','" + subQuestionList.getSubQuestion()
							+ "','" + subQuestionList.getSubQuestionAnswerType() + "','"
							+ subQuestionList.getUploadFile() + "')");

		} else if (subanswertype.equals("Date")) {
			System.out.println(
					"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type, `subdate_points`, `subdate_code`, `subdate_response`, `subdate_routing`) VALUES ('"
							+ questionId + "','" + qBean.getQuestion_type() + "','" + subQuestionList.getSubQuestion()
							+ "','" + subQuestionList.getSubQuestionAnswerType() + "','"
							+ subQuestionList.getUploadFile() + "','" + subQuestionList.getDate_points() + "','"
							+ subQuestionList.getDate_code() + "','" + subQuestionList.getDate_response() + "','"
							+ subQuestionList.getDate_routing() + "')");
			template.execute(
					"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type, `subdate_points`, `subdate_code`, `subdate_response`, `subdate_routing`) VALUES ('"
							+ questionId + "','" + qBean.getQuestion_type() + "','" + subQuestionList.getSubQuestion()
							+ "','" + subQuestionList.getSubQuestionAnswerType() + "','"
							+ subQuestionList.getUploadFile() + "','" + subQuestionList.getDate_points() + "','"
							+ subQuestionList.getDate_code() + "','" + subQuestionList.getDate_response() + "','"
							+ subQuestionList.getDate_routing() + "')");
		} else if (subanswertype.equals("Date & Time")) {

			System.out.println(
					"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type, `subdate_points`, `subdate_code`, `subdate_response`, `subdate_routing`, `subtime_points`, `subtime_code`, `subtime_response`, `subtime_routing`) VALUES ('"
							+ questionId + "','" + qBean.getQuestion_type() + "','" + subQuestionList.getSubQuestion()
							+ "','" + subQuestionList.getSubQuestionAnswerType() + "','"
							+ subQuestionList.getUploadFile() + "','" + subQuestionList.getDate_points() + "','"
							+ subQuestionList.getDate_code() + "','" + subQuestionList.getDate_response() + "','"
							+ subQuestionList.getDate_routing() + "','" + subQuestionList.getTime_points() + "','"
							+ subQuestionList.getDate_code() + "','" + subQuestionList.getTime_response() + "','"
							+ subQuestionList.getTime_routing() + "')");
			template.execute(
					"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type, `subdate_points`, `subdate_code`, `subdate_response`, `subdate_routing`, `subtime_points`, `subtime_code`, `subtime_response`, `subtime_routing`) VALUES ('"
							+ questionId + "','" + qBean.getQuestion_type() + "','" + subQuestionList.getSubQuestion()
							+ "','" + subQuestionList.getSubQuestionAnswerType() + "','"
							+ subQuestionList.getUploadFile() + "','" + subQuestionList.getDate_points() + "','"
							+ subQuestionList.getDate_code() + "','" + subQuestionList.getDate_response() + "','"
							+ subQuestionList.getDate_routing() + "','" + subQuestionList.getTime_points() + "','"
							+ subQuestionList.getDate_code() + "','" + subQuestionList.getTime_response() + "','"
							+ subQuestionList.getTime_routing() + "')");

		}
		return qBean;
	}

	public QuestionnaireBean insertSubquestion(QuestionnaireBean qBean, String questionId,
			QuestionnaireBean subQuestionList) {
		if (subQuestionList.getUploadFile() == null) {
			subQuestionList.setUploadFile("no");
		}
		System.out.println(
				"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type) VALUES ('"
						+ questionId + "','" + qBean.getQuestion_type() + "','" + subQuestionList.getSubQuestion()
						+ "','" + subQuestionList.getSubQuestionAnswerType() + "','" + subQuestionList.getUploadFile()
						+ "')");
		template.execute(
				"INSERT INTO `mst_questionare_subquestion`( `question_id`, `question_type`, `subquestion`, `subanswer_type`,subupload_type) VALUES ('"
						+ questionId + "','" + qBean.getQuestion_type() + "','" + subQuestionList.getSubQuestion()
						+ "','" + subQuestionList.getSubQuestionAnswerType() + "','" + subQuestionList.getUploadFile()
						+ "')");
		return template.queryForObject(
				"select max(sk_subquestion_id) as sk_subquestion_id,question_id from mst_questionare_subquestion",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setSuper_question_id(questionId);
						qBean.setSk_subquestion_id(rs.getString("sk_subquestion_id"));

						return qBean;
					}
				});
	}

	public void addDependentQuestionwithSubquestionSelectList(QuestionnaireBean qBean, String subanswertype,
			QuestionnaireBean subQuestionList, String questionId, String subquestionId) {
		System.out.println(
				"INSERT INTO `mst_questionare_selectoption`(`question_id`,subquestion_id, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`) VALUES ('"
						+ questionId + "','" + subquestionId + "','" + qBean.getQuestion_type().replace("'", "\\'")
						+ "','" + subQuestionList.getAnswer().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_points().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_code().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_response().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_optiontype().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_comment().replace("'", "\\'") + "')");
		template.execute(
				"INSERT INTO `mst_questionare_selectoption`(`question_id`,subquestion_id, question_type,`options`, `option_points`, `option_code`, `option_response`, `option_optiontype`, `option_comment`) VALUES ('"
						+ questionId + "','" + subquestionId + "','" + qBean.getQuestion_type().replace("'", "\\'")
						+ "','" + subQuestionList.getAnswer().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_points().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_code().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_response().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_optiontype().replace("'", "\\'") + "','"
						+ subQuestionList.getAnswer_comment().replace("'", "\\'") + "')");

	}


	public QuestionnaireBean updateStandardSequenceNumber(QuestionnaireBean qBean) {

		System.out.println("UPDATE `mst_questionare SET standard_number_sequence='" + qBean.getStandard_number_sequence()
				+ "' WHERE sk_question_id='" + qBean.getSk_question_id() + "';");
		template.execute("UPDATE mst_questionare SET standard_number_sequence='" + qBean.getStandard_number_sequence()
				+ "' WHERE sk_question_id='" + qBean.getSk_question_id()+ "';");

		System.out.println("SELECT *  FROM `mst_questionare` WHERE sk_question_id='" +qBean.getSk_question_id()+ "';");
		return template.queryForObject(
				"SELECT *  FROM `mst_questionare` WHERE sk_question_id='" + qBean.getSk_question_id() + "';",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						
						
						qBean.setStandard_number_sequence(rs.getString("standard_number_sequence"));
						return qBean;
					}
				});
	}

	public void addFormula(QuestionnaireBean qBean, String finalresult, String finalcount, QuestionnaireBean formulaList, String qid) {
		System.out.println("INSERT INTO `mst_formula_map`( `formula_id`, `question_id`, `subquestion_id`, `option_id`, `result`) VALUES ('"+finalcount+"','"+qid+"','"+formulaList.getSk_subquestion_id()+"','"+formulaList.getSk_answer_id()+"','"+finalresult+"')");
		template.execute("INSERT INTO `mst_formula_map`( `formula_id`, `question_id`, `subquestion_id`, `option_id`, `result`) VALUES ('"+finalcount+"','"+qid+"','"+formulaList.getSk_subquestion_id()+"','"+formulaList.getSk_answer_id()+"','"+finalresult+"')");
		template.execute("update mst_questionare set formula_flag='1' where sk_question_id='"+qid+"'");
	}

	public void uploadMainWithsetofsubquestions(QuestionnaireBean qBean, String qid) {
		if (qBean.getUpload_file() == null) {
			qBean.setUpload_file("no");
		}
	System.out.println("update mst_questionare set   `question`='"+qBean.getQuestion().replace("'", "\\'")+"' ,`question_optiontype`='"+qBean.getQuestion_optiontype()+"', `question_points`='"+qBean.getQuestion_points()+"', `question_code`='"+qBean.getQuestion_code()+"' ,`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='" +qBean.getUpload_file() + "'   where sk_question_id='"+qid+"' ");
			template.execute("update mst_questionare set   `question`='"+qBean.getQuestion().replace("'", "\\'")+"' , `question_optiontype`='"+qBean.getQuestion_optiontype()+"', `question_points`='"+qBean.getQuestion_points()+"', `question_code`='"+qBean.getQuestion_code()+"' ,`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='" +qBean.getUpload_file() + "'  where sk_question_id='"+qid+"'");	

	}

	public QuestionnaireBean updatemainQuestionwithSubquestion(QuestionnaireBean qBean, String subanswertype,
			QuestionnaireBean subQuestionList, String qid) {
		if (subQuestionList.getUploadFile() == null) {
			subQuestionList.setUploadFile("no");
		}
		if (subanswertype.equals("Paragraph")) {
		System.out.println("update mst_questionare_subquestion set subquestion='"+subQuestionList.getSubQuestion().replace("'", "\\'")+"',subupload_type='"+subQuestionList.getUploadFile()+"' where question_id='"+qid+"' and sk_subquestion_id='"+subQuestionList.getSk_subquestion_id()+"' ");

			template.execute("update mst_questionare_subquestion set subquestion='"+subQuestionList.getSubQuestion().replace("'", "\\'")+"',subupload_type='"+subQuestionList.getUploadFile()+"' where question_id='"+qid+"' and sk_subquestion_id='"+subQuestionList.getSk_subquestion_id()+"'");

		} else if (subanswertype.equals("Date")) {
		System.out.println("update mst_questionare_subquestion set subquestion='"+subQuestionList.getSubQuestion().replace("'", "\\'")+"',subupload_type='"+subQuestionList.getUploadFile()+"' , `subdate_points`='"+subQuestionList.getDate_points()+"', `subdate_code`='"+subQuestionList.getDate_code()+"', `subdate_response`='"+subQuestionList.getDate_response()+"', `subdate_routing`='"+subQuestionList.getDate_response()+"' where question_id='"+qid+"' and sk_subquestion_id='"+subQuestionList.getSk_subquestion_id()+"'");
			
			template.execute(
					"update mst_questionare_subquestion set subquestion='"+subQuestionList.getSubQuestion().replace("'", "\\'")+"',subupload_type='"+subQuestionList.getUploadFile()+"' , `subdate_points`='"+subQuestionList.getDate_points()+"', `subdate_code`='"+subQuestionList.getDate_code()+"', `subdate_response`='"+subQuestionList.getDate_response()+"', `subdate_routing`='"+subQuestionList.getDate_response()+"' where question_id='"+qid+"' and sk_subquestion_id='"+subQuestionList.getSk_subquestion_id()+"'");
		} else if (subanswertype.equals("Date & Time")) {
		System.out.println("update mst_questionare_subquestion set subquestion='"+subQuestionList.getSubQuestion().replace("'", "\\'")+"',subupload_type='"+subQuestionList.getUploadFile()+"' , `subdate_points`='"+subQuestionList.getDate_points()+"', `subdate_code`='"+subQuestionList.getDate_code()+"', `subdate_response`='"+subQuestionList.getDate_response()+"', `subdate_routing`='"+subQuestionList.getDate_response()+"', `subtime_points`='"+subQuestionList.getTime_points()+"', `subtime_code`='"+subQuestionList.getTime_code()+"', `subtime_response`='"+subQuestionList.getTime_response()+"', `subtime_routing`='"+subQuestionList.getTime_routing()+"' where question_id='"+qid+"' and sk_subquestion_id='"+subQuestionList.getSk_subquestion_id()+"'");
			
			template.execute(
					"update mst_questionare_subquestion set subquestion='"+subQuestionList.getSubQuestion().replace("'", "\\'")+"',subupload_type='"+subQuestionList.getUploadFile()+"' , `subdate_points`='"+subQuestionList.getDate_points()+"', `subdate_code`='"+subQuestionList.getDate_code()+"', `subdate_response`='"+subQuestionList.getDate_response()+"', `subdate_routing`='"+subQuestionList.getDate_response()+"', `subtime_points`='"+subQuestionList.getTime_points()+"', `subtime_code`='"+subQuestionList.getTime_code()+"', `subtime_response`='"+subQuestionList.getTime_response()+"', `subtime_routing`='"+subQuestionList.getTime_routing()+"' where question_id='"+qid+"' and sk_subquestion_id='"+subQuestionList.getSk_subquestion_id()+"'");

		}
		return qBean;
		
	}

	public QuestionnaireBean updateSubquestion(QuestionnaireBean qBean, String qid, QuestionnaireBean subQuestionList) {
			if (subQuestionList.getUploadFile() == null) {
			subQuestionList.setUploadFile("no");
		}
		System.out.println("update mst_questionare_subquestion set subquestion='"+subQuestionList.getSubQuestion().replace("'", "\\'")+"',subupload_type='"+subQuestionList.getUploadFile()+"' where question_id='"+qid+"' and sk_subquestion_id='"+subQuestionList.getSk_subquestion_id()+"'");
	
		template.execute(
				"update mst_questionare_subquestion set subquestion='"+subQuestionList.getSubQuestion().replace("'", "\\'")+"',subupload_type='"+subQuestionList.getUploadFile()+"' where question_id='"+qid+"' and sk_subquestion_id='"+subQuestionList.getSk_subquestion_id()+"'");
		return qBean;
		
	}

	public QuestionnaireBean updatemainQuestionwithSubquestionSelectList(QuestionnaireBean qBean, String subanswertype,
			QuestionnaireBean subQuestionList, String qid, String subid) {
			
			System.out.println("update mst_questionare_selectoption set `options`='"+subQuestionList.getAnswer().replace("'", "\\'")+"', `option_points`='"+subQuestionList.getAnswer_points().replace("'", "\\'")+"', `option_code`='"+subQuestionList.getAnswer_code().replace("'", "\\'")+"', `option_response`='"+subQuestionList.getAnswer_response().replace("'", "\\'")+"', `option_optiontype`='"+subQuestionList.getAnswer_optiontype().replace("'", "\\'")+"', `option_comment`='"+subQuestionList.getAnswer_comment().replace("'", "\\'")+"' where `question_id`='"+qid+"' and subquestion_id='"+subid+"' and sk_answer_id='"+subQuestionList.getSk_answer_id()+"'");
		
		template.execute(
				"update mst_questionare_selectoption set `options`='"+subQuestionList.getAnswer().replace("'", "\\'")+"', `option_points`='"+subQuestionList.getAnswer_points().replace("'", "\\'")+"', `option_code`='"+subQuestionList.getAnswer_code().replace("'", "\\'")+"', `option_response`='"+subQuestionList.getAnswer_response().replace("'", "\\'")+"', `option_optiontype`='"+subQuestionList.getAnswer_optiontype().replace("'", "\\'")+"', `option_comment`='"+subQuestionList.getAnswer_comment().replace("'", "\\'")+"' where `question_id`='"+qid+"' and subquestion_id='"+subid+"' and sk_answer_id='"+subQuestionList.getSk_answer_id()+"'");
return qBean;
	}

	public List<QuestionnaireBean> formulacount(QuestionnaireBean qBean, String qid) {
		System.out.println("SELECT formula_id,GROUP_CONCAT(mst_formula_map.sk_formula_map_id) as sk_formula_map_id,mst_formula_map.result,GROUP_CONCAT(mst_formula_map.subquestion_id) as subquestion_id,GROUP_CONCAT(mst_formula_map.option_id) as option_id FROM `mst_formula_map` WHERE `question_id`='"+qid+"' GROUP by `formula_id`");
		return template.query(
				"SELECT formula_id,GROUP_CONCAT(mst_formula_map.sk_formula_map_id) as sk_formula_map_id,mst_formula_map.result,GROUP_CONCAT(mst_formula_map.subquestion_id) as subquestion_id,GROUP_CONCAT(mst_formula_map.option_id) as option_id FROM `mst_formula_map` WHERE `question_id`='"+qid+"' GROUP by `formula_id`;",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean=new QuestionnaireBean();
						qBean.setFormulaCount(rs.getString("formula_id"));
						qBean.setSk_answer_id(rs.getString("option_id"));
						qBean.setSk_subquestion_id(rs.getString("subquestion_id"));
						qBean.setSk_formula_id(rs.getString("sk_formula_map_id"));
							qBean.setFormulaFinalResult(rs.getString("result"));
						return qBean;
					}
				});
	}

	public void updateFormula(QuestionnaireBean qBean, String finalresult, String finalcount,
			QuestionnaireBean formulaList, String qid, String formulaid) {
			System.out.println("update mst_formula_map set option_id='"+formulaList.getSk_answer_id()+"',result='"+finalresult+"' where question_id='"+qid+"' and subquestion_id='"+formulaList.getSk_subquestion_id()+"' and sk_formula_map_id='"+formulaid+"'");
		template.execute("update mst_formula_map set option_id='"+formulaList.getSk_answer_id()+"',result='"+finalresult+"' where question_id='"+qid+"' and subquestion_id='"+formulaList.getSk_subquestion_id()+"' and sk_formula_map_id='"+formulaid+"'");
		template.execute("update mst_questionare set formula_flag='1' where sk_question_id='"+qid+"'");
	}

	public List<QuestionnaireBean> superquestionsList(QuestionnaireBean qBean, String super_question_id, String qid, String soid) {
		
		System.out.println(
				"super sections===SELECT *,mst_brand.*,mst_section.section_name,mst_subsection.subsection_name FROM `mst_questionare`,mst_brand,mst_subsection,mst_section WHERE mst_brand.sk_brand_id=mst_questionare.brand_id and  mst_questionare.subsection_id=mst_subsection.sk_subsection_id and mst_section.sk_section_id=mst_questionare.section_id and mst_questionare.question_status='active' and  `sk_question_id` IN("+super_question_id+ ")  order by standard_number ");
		return template.query(
				"SELECT *,mst_brand.*,mst_section.section_name,mst_subsection.subsection_name FROM `mst_questionare`,mst_brand,mst_subsection,mst_section WHERE mst_brand.sk_brand_id=mst_questionare.brand_id and  mst_questionare.subsection_id=mst_subsection.sk_subsection_id and mst_section.sk_section_id=mst_questionare.section_id and mst_questionare.question_status='active' and  `sk_question_id` IN("+super_question_id+ ") order by standard_number ;",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean=new QuestionnaireBean();
						qBean.setSuper_question_section_id(rs.getString("section_id"));
						qBean.setSuper_question_section_name(rs.getString("section_name"));
						qBean.setSuper_question_subsection_id(rs.getString("subsection_id"));
						qBean.setSuper_question_subsection_name(rs.getString("subsection_name"));
						qBean.setSuper_question_standard_number(rs.getString("standard_number"));
						qBean.setSk_question_id(rs.getString("sk_question_id"));
						qBean.setYear(rs.getString("year_applied"));

						System.out.println("soid=="+soid);
						
					 List<QuestionnaireBean> optionsList=getOptionsList(qBean,rs.getString("sk_question_id"));
					 qBean.setOptionsList(optionsList);
					 if(soid.isEmpty()) {
						 
					 }else {
						 
					 
					 List<QuestionnaireBean> selectedoptionsList=getselectedOptionsList(qBean,rs.getString("sk_question_id"),soid);
					 qBean.setSelectedoptionsList(selectedoptionsList);
					 }
						return qBean;

					}

					
				});

	
	}
	

	private List<QuestionnaireBean> getOptionsList(QuestionnaireBean qBean, String qid) {
		System.out.println(
				"SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='"+qid+"';");
		return template.query(
				"SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='"+qid+"' ;",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean=new QuestionnaireBean();
						qBean.setSk_answer_id(rs.getString("sk_answer_id"));
						qBean.setAnswer(rs.getString("options"));
						System.out.println("options==="+(rs.getString("options")));
						return qBean;

					}

					
				});
	}
	private List<QuestionnaireBean> getOLROptionsList(QuestionnaireBean qBean, String qid) {
		System.out.println(
				"SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='"+qid+"';");
		return template.query(
				"SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='"+qid+"' ;",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean=new QuestionnaireBean();
						qBean.setSk_answer_id(rs.getString("sk_answer_id"));
						qBean.setOptions(rs.getString("options"));
						System.out.println("options==="+(rs.getString("options")));
						return qBean;

					}

					
				});
	}
	
	protected List<QuestionnaireBean> getselectedOptionsList(QuestionnaireBean qBean, String qid, String soid) {
		System.out.println(
				"SELECT * FROM `mst_questionare_selectoption` WHERE `sk_answer_id` IN("+soid+") and `question_id`='"+qid+"';");
		return template.query(
				"SELECT * FROM `mst_questionare_selectoption` WHERE `sk_answer_id` IN("+soid+") and `question_id`='"+qid+"'; ;",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean=new QuestionnaireBean();
						qBean.setSk_answer_id(rs.getString("sk_answer_id"));
						qBean.setAnswer(rs.getString("options"));
						return qBean;

					}

					
				});
	}

	public QuestionnaireBean getformulaid(QuestionnaireBean qBean, String finalcount, QuestionnaireBean formulaList1, String qid) {
		System.out.println("SELECT count(`sk_formula_map_id`),IFNULL(`sk_formula_map_id`, 'noformulaid') as formula_id FROM `mst_formula_map` WHERE `formula_id`='"+finalcount+"' and `question_id`='"+qid+"' and `subquestion_id`='"+formulaList1.getSk_subquestion_id()+"'");
		return template.queryForObject(
				"SELECT count(`sk_formula_map_id`),IFNULL(`sk_formula_map_id`, 'noformulaid') as formula_id FROM `mst_formula_map` WHERE `formula_id`='"+finalcount+"' and `question_id`='"+qid+"' and `subquestion_id`='"+formulaList1.getSk_subquestion_id()+"';",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setSk_formula_id(rs.getString("formula_id"));
						return qBean;
					}
				});
	}

	public void uploadDependentWithsetofsubquestions(QuestionnaireBean qBean, String qid, List<QuestionnaireBean> superQuestions) {
		
		if (qBean.getUpload_file() == null) {
			qBean.setUpload_file("no");
		}
		
		String superQuestionIds = "";
		String superQuestionAnswers="";
           if(null!=superQuestions && superQuestions.size()>0){
			for (QuestionnaireBean superQuestionsList : superQuestions) {
				if (!(superQuestionsList.getSuper_question_standard_number()==null)) {
			qBean.setSuper_question_standard_number(superQuestionsList.getSuper_question_standard_number());
			qBean.setSuper_question_section_id(superQuestionsList.getSuper_question_section_id());
			qBean.setSuper_question_subsection_id(superQuestionsList.getSuper_question_subsection_id());
			qBean.setSuper_question_answer(superQuestionsList.getSuper_question_answer());
			//System.out.println("super question section id="+superQuestionsList.getSuper_question_section_id()+"super question subsection id="+superQuestionsList.getSuper_question_subsection_id()+"super question standard number="+superQuestionsList.getSuper_question_standard_number()+"super question answer="+superQuestionsList.getSuper_question_answer());
				getSuperQuestionId(qBean);
				superQuestionIds+="##"+qBean.getSk_question_id();
				superQuestionAnswers+="##"+qBean.getSuper_question_answer();
			}else {
				
			}
			}
			
			}
		System.out.println(superQuestionIds.substring(2));
		System.out.println(superQuestionAnswers.substring(2));
		
		
		
		System.out.println("update mst_questionare set   `question`='"+qBean.getQuestion().replace("'", "\\'")+"' ,`question_optiontype`='"+qBean.getQuestion_optiontype()+"', `question_points`='"+qBean.getQuestion_points()+"', `question_code`='"+qBean.getQuestion_code()+"',super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"',`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='" +qBean.getUpload_file() + "'   where sk_question_id='"+qid+"' ");
		template.execute("update mst_questionare set   `question`='"+qBean.getQuestion().replace("'", "\\'")+"' , `question_optiontype`='"+qBean.getQuestion_optiontype()+"', `question_points`='"+qBean.getQuestion_points()+"', `question_code`='"+qBean.getQuestion_code()+"',super_question_id='"+superQuestionIds.substring(2)+"',super_question_answer='"+superQuestionAnswers.substring(2)+"' ,`comment_mandatory`='" + qBean.getComment_mandatory() + "',`upload_file`='" +qBean.getUpload_file() + "' where sk_question_id='"+qid+"'");	

}

	public List<QuestionnaireBean> questionsandoptions(QuestionnaireBean qBean, int pageid, int total, String encrypt_osc_shopper_id,String encrypt_dealer_id,String encrypt_NonOsc_shopper_id) {
		
		 String osc_shopper_id=Encryption.decrypt(encrypt_osc_shopper_id);
		 String NonOsc_shopper_id=Encryption.decrypt(encrypt_NonOsc_shopper_id);
		 String dealer_id=Encryption.decrypt(encrypt_dealer_id);
		
		String sql = "SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mst_questionare.question_points as totalQuesPoints from mys_txn_answers,mst_shopper_details,mys_score,mst_questionare WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.shopper_id='"
				+ NonOsc_shopper_id
				+ "' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_score.shopper_id=mys_txn_answers.shopper_id AND  mys_txn_answers.question_id=mst_questionare.sk_question_id AND mys_txn_answers.status='active' GROUP BY mys_txn_answers.question_id  UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mst_questionare.question_points from mys_txn_answers,mst_shopper_details,mys_score,mst_questionare WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.shopper_id='"
				+ osc_shopper_id
				+ "' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_score.shopper_id=mys_txn_answers.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id AND mys_txn_answers.status='active' GROUP BY mys_txn_answers.question_id  ORDER BY mode_of_contact DESC, CAST(standard_number_sequence as decimal(10,2)) ASC LIMIT "
				+ (pageid - 1) + "," + total + " ";
		//String sql="SELECT mys_txn_answers.*,SUM(mys_txn_answers.question_points) as maxQues_points,SUM(mys_txn_answers.scored_points) AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers,mst_shopper_details,mys_score,mys_txn_formulaanswer,mst_questionare WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.shopper_id='9' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id GROUP BY mys_txn_answers.question_id  UNION SELECT mys_txn_answers.*,SUM(mys_txn_answers.question_points) as maxQues_points,SUM(mys_txn_answers.scored_points) AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points from mys_txn_answers,mst_shopper_details,mys_score,mys_txn_formulaanswer,mst_questionare WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.shopper_id='6' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id GROUP BY mys_txn_answers.question_id ORDER BY mode_of_contact ASC,standard_number_sequence ASC LIMIT 5,1";
		//String sql="SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers,mst_shopper_details,mys_score,mys_txn_formulaanswer,mst_questionare WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.shopper_id='"+NoscShopper_id+"' AND mys_txn_answers.formula_flag=0 AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id GROUP BY mys_txn_answers.question_id UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers LEFT JOIN mst_shopper_details ON mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id LEFT JOIN mys_score ON mys_txn_answers.subsection_id=mys_score.subsection_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id LEFT JOIN mst_questionare on mys_txn_answers.question_id=mst_questionare.sk_question_id WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.formula_flag=1 AND mys_txn_answers.shopper_id='"+NoscShopper_id+"' GROUP BY mys_txn_answers.sk_answer_id UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers LEFT JOIN mst_shopper_details ON mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id LEFT JOIN mys_score ON mys_txn_answers.subsection_id=mys_score.subsection_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id LEFT JOIN mst_questionare on mys_txn_answers.question_id=mst_questionare.sk_question_id WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.formula_flag=0 AND mys_txn_answers.shopper_id='"+shopper_id+"' GROUP BY mys_txn_answers.sk_answer_id UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers LEFT JOIN mst_shopper_details ON mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id LEFT JOIN mys_score ON mys_txn_answers.subsection_id=mys_score.subsection_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id LEFT JOIN mst_questionare on mys_txn_answers.question_id=mst_questionare.sk_question_id WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.formula_flag=1 AND mys_txn_answers.shopper_id='"+shopper_id+"' GROUP BY mys_txn_answers.sk_answer_id  ORDER BY mode_of_contact ASC,standard_number_sequence ASC LIMIT " + (pageid - 1) + "," + total + " ";
		System.out.println("dealer id in questionand options dao"+dealer_id);
		System.out.println("Manoj d questions and options list=" + sql);
		//final String sid=NonOsc_shopper_id;
		//final String oscsid=osc_shopper_id;
		return template.query(sql, new RowMapper<QuestionnaireBean>() {
			public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
				QuestionnaireBean qBean = new QuestionnaireBean();
				qBean.setQuestion(rs.getString("question_text"));
				qBean.setStandard_number(rs.getString("standard_number"));
				qBean.setSk_question_id(rs.getString("question_id"));
				qBean.setQuestion_type(rs.getString("question_type"));
				qBean.setSubsection_name(rs.getString("subsection_name"));
				String Question_type=rs.getString("question_type");
				qBean.setStorevisitId(rs.getString("shopper_id"));
				System.out.println("Question type===="+Question_type);
				  List<QuestionnaireBean> optionsList=getOLROptionsList(qBean,rs.getString("question_id"));
				  qBean.setOptionsList(optionsList);
				  List<QuestionnaireBean> subQuesList=getSubQuesList(qBean,rs.getString("question_id"));
				  qBean.setSubquestions(subQuesList);
				  if(!Question_type.equals("Main Question With Set Of SubQuestions") && !Question_type.equals("Dependent Question With Set Of SubQuestions")){
				 qBean.setSelectedanswerid(rs.getString("select_option_text"));
				 }
				  else{
					  List<QuestionnaireBean> selectAnsList=getDepeSeleAnsList(qBean,rs.getString("question_id"),rs.getString("shopper_id"));
					  qBean.setSelectedoptionsList(selectAnsList);
					  QuestionnaireBean sumOfPoints=getSumOfPointsForDepeQues(qBean,rs.getString("question_id"),rs.getString("shopper_id"));
					  qBean.setSumOfScoredPoints(sumOfPoints.getSumOfScoredPoints());
					  qBean.setSumOfQuesPoints(sumOfPoints.getSumOfQuesPoints());
				  }
				  
				System.out.println("select option text====="+rs.getString("select_option_text"));
				qBean.setAnswer_type(rs.getString("answer_type"));		
				qBean.setParagraph(rs.getString("main_ques_comment"));
				System.out.println();
				qBean.setComment_criteria(rs.getString("main_ques_comment"));
				qBean.setSelected_option_comment(rs.getString("select_option_comment"));
				System.out.println("select option comment====="+rs.getString("select_option_comment"));
				qBean.setParagraph(rs.getString("paragraph"));
				qBean.setDate_code(rs.getString("date"));
				qBean.setTime_code(rs.getString("dateandtime"));
				qBean.setMode_of_contact(rs.getString("mode_of_contact"));			
				qBean.setPercentage(rs.getString("percentage"));
				qBean.setQuestion_points(rs.getString("question_points"));
				qBean.setScored_points(rs.getString("scored_points"));
				qBean.setMax_question_points(rs.getString("maxQues_points"));
				qBean.setMax_scored_points(rs.getString("maxScore_points"));
				qBean.setFormulFlag(rs.getString("formula_flag"));
				if(rs.getString("formula_flag").equals("1")){
				QuestionnaireBean getformulaPoint =getformulaPoints(qBean, rs.getString("shopper_id"),rs.getString("question_id"));
				qBean.setFormulapoints(getformulaPoint.getFormulapoints());
				System.out.println("formula Points in If condition===="+getformulaPoint.getFormulapoints());
				}
				qBean.setTotal_ques_points(rs.getString("totalQuesPoints"));
				System.out.println("formula flag"+rs.getString("formula_flag")+"===="+rs.getString("standard_number")+"==="+rs.getString("question_points"));
				System.out.println("first=" + rs.getString("mode_of_contact"));
				return qBean;
			}

		});
	}
public QuestionnaireBean getSumOfPointsForDepeQues(QuestionnaireBean qBean,String qid,String sid) {
		
		
		System.out.println("SELECT SUM(scored_points) as sumofScoredPoints,SUM(question_points) as sumofQuesPoints FROM `mys_txn_answers` WHERE shopper_id='"+sid+"' AND question_id='"+qid+"'");
		return template.queryForObject(
				"SELECT SUM(scored_points) as sumofScoredPoints,SUM(question_points) as sumofQuesPoints FROM `mys_txn_answers` WHERE shopper_id='"+sid+"' AND question_id='"+qid+"'",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setSumOfScoredPoints(rs.getString("sumofScoredPoints"));
						System.out.println("sumofScoredPoints===="+rs.getString("sumofScoredPoints"));
						qBean.setSumOfQuesPoints(String.valueOf(rs.getInt("sumofQuesPoints")));
						System.out.println("sumOfQuesPointsss===="+rs.getString("sumofQuesPoints"));
						return qBean;
					}
				});
	}
public QuestionnaireBean getformulaPoints(QuestionnaireBean qBean,String sid,String qid) {
	
	
	System.out.println("SELECT * FROM `mys_txn_formulaanswer` WHERE `shopper_id`='"+sid+"' AND question_id='"+qid+"'");
	return template.queryForObject(
			"SELECT * FROM `mys_txn_formulaanswer` WHERE `shopper_id`='"+sid+"' AND question_id='"+qid+"'",
			new RowMapper<QuestionnaireBean>() {
				public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
					qBean.setFormulapoints(rs.getString("points"));
					return qBean;
				}
			});
}
	private List<QuestionnaireBean> getSubQuesList(QuestionnaireBean qBean, String qid) {
		System.out.println(
				"SELECT * FROM `mst_questionare_subquestion` WHERE `question_id`='"+qid+"';");
		return template.query(
				"SELECT * FROM `mst_questionare_subquestion` WHERE `question_id`='"+qid+"'; ;",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean=new QuestionnaireBean();
						qBean.setSk_subquestion_id(rs.getString("sk_subquestion_id"));
						qBean.setSubQuestion(rs.getString("subquestion"));
						System.out.println("subQuestions==="+(rs.getString("subquestion")));
						return qBean;

					}

					
				});
	}
	private List<QuestionnaireBean> getDepeSeleAnsList(QuestionnaireBean qBean, String qid,String shopper_id) {
		System.out.println(
				"SELECT mys_txn_answers.question_id,mys_txn_answers.subquestion_id,mys_txn_answers.select_option_text,mys_txn_answers.sub_question_text,mys_txn_answers.select_option_comment,mys_txn_answers.paragraph,mys_txn_answers.date,mys_txn_answers.dateandtime,mys_txn_answers.answer_type FROM `mys_txn_answers` WHERE `question_id`='"+qid+"' AND shopper_id='"+shopper_id+"';");
		return template.query(
				"SELECT mys_txn_answers.question_id,mys_txn_answers.subquestion_id,mys_txn_answers.select_option_text,mys_txn_answers.sub_question_text,mys_txn_answers.select_option_comment,mys_txn_answers.paragraph,mys_txn_answers.date,mys_txn_answers.dateandtime,mys_txn_answers.answer_type FROM `mys_txn_answers` WHERE `question_id`='"+qid+"' AND shopper_id='"+shopper_id+"'; ",
				new RowMapper<QuestionnaireBean>() {
					@Override
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean=new QuestionnaireBean();
						qBean.setSk_subquestion_id(rs.getString("subquestion_id"));
						qBean.setSubQuestion(rs.getString("sub_question_text"));
						qBean.setComment_criteria(rs.getString("select_option_comment"));
						System.out.println("selected option comment==="+(rs.getString("select_option_comment")));
						qBean.setParagraph(rs.getString("paragraph"));
						System.out.println("Paragraph==="+(rs.getString("paragraph")));
						System.out.println("date==="+(rs.getString("date")));
						qBean.setDate_code(rs.getString("date"));
						qBean.setTime_code(rs.getString("dateandtime"));
						qBean.setSelectedanswerid(rs.getString("select_option_text"));
						qBean.setSubQuestionAnswerType(rs.getString("answer_type"));
						System.out.println("selected subQues Answer==="+(rs.getString("select_option_text")));
						System.out.println("selected subQues AnswerTYPEEEE==="+(rs.getString("answer_type")));
						return qBean;

					}

					
				});
	}
	
	public List<QuestionnaireBean> getIds( QuestionnaireBean qBean, String nonoscid,String oscId) {
		String sql = "select count(*) as count from (SELECT mys_txn_answers.*,SUM(mys_txn_answers.question_points) as maxQues_points,SUM(mys_txn_answers.scored_points) AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage from mys_txn_answers,mst_shopper_details,mys_score WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.shopper_id='"+nonoscid+"' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id GROUP BY mys_txn_answers.question_id  UNION SELECT mys_txn_answers.*,SUM(mys_txn_answers.question_points) as maxQues_points,SUM(mys_txn_answers.scored_points) AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage from mys_txn_answers,mst_shopper_details,mys_score WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.shopper_id='"+oscId+"' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id GROUP BY mys_txn_answers.question_id ORDER BY mode_of_contact ASC,standard_number_sequence ASC )as count";
		//String sql= "select count(*) as count from (SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers,mst_shopper_details,mys_score,mys_txn_formulaanswer,mst_questionare WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.shopper_id='"+nonoscid+"' AND mys_txn_answers.formula_flag=0 AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id GROUP BY mys_txn_answers.question_id UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers LEFT JOIN mst_shopper_details ON mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id LEFT JOIN mys_score ON mys_txn_answers.subsection_id=mys_score.subsection_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id LEFT JOIN mst_questionare on mys_txn_answers.question_id=mst_questionare.sk_question_id WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.formula_flag=1 AND mys_txn_answers.shopper_id='"+nonoscid+"' GROUP BY mys_txn_answers.sk_answer_id UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers LEFT JOIN mst_shopper_details ON mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id LEFT JOIN mys_score ON mys_txn_answers.subsection_id=mys_score.subsection_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id LEFT JOIN mst_questionare on mys_txn_answers.question_id=mst_questionare.sk_question_id WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.formula_flag=0 AND mys_txn_answers.shopper_id='"+oscId+"' GROUP BY mys_txn_answers.sk_answer_id UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers LEFT JOIN mst_shopper_details ON mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id LEFT JOIN mys_score ON mys_txn_answers.subsection_id=mys_score.subsection_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id LEFT JOIN mst_questionare on mys_txn_answers.question_id=mst_questionare.sk_question_id WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.formula_flag=1 AND mys_txn_answers.shopper_id='"+oscId+"' GROUP BY mys_txn_answers.sk_answer_id  ORDER BY mode_of_contact ASC,standard_number_sequence ASC )as count";
		System.out.println(sql);
		return template.query(sql, new RowMapper<QuestionnaireBean>() {
			public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
				QuestionnaireBean qBean = new QuestionnaireBean();
				qBean.setQuestionCount(rs.getString("count"));
				System.out.println(rs.getString("count"));
				return qBean;
			}
		});
	}
	/**manoj d for overall perf graph*/
	public QuestionnaireBean getOutletsoverallperFilter1(QuestionnaireBean qBean,String did,String bid,String rid) {
		
		if(bid==null){
			bid="all";
		}
		if(did==null){
			did="all";
		}
		System.out.println("SELECT GROUP_CONCAT(sk_outlet_id) as outlets  FROM `mst_dealer_outlet` WHERE (`brand_id`='"+bid+"' or '"+bid+"'='all') AND (dealer_id='"+did+"' or '"+did+"'='all'");
		return template.queryForObject(
				"SELECT GROUP_CONCAT(sk_outlet_id) as outlets  FROM `mst_dealer_outlet` WHERE (`brand_id`='"+bid+"' or '"+bid+"'='all') AND (dealer_id='"+did+"' or '"+did+"'='all')",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setOutlets(rs.getString("outlets"));
						return qBean;
					}
				});
	}
	/**manoj d for overall perf graph*/
	public QuestionnaireBean getshoppersByOutletMode(QuestionnaireBean qBean,GraphBean gBean,String mid) {
		
		//int year=2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		 if(mid==null || mid=="") {
			 mid="all";
		 }
		System.out.println("SELECT GROUP_CONCAT(sk_shopper_id) as shopperIds FROM `mst_shopper_details` WHERE outlet_id IN("+ gBean.getOutlet_id() + ") AND visit_status='published' AND (month(visit_date)='"+mid+"' or '"+mid+"'='all') AND mode_of_contact!='Online Sales Channel'");
		return template.queryForObject(
				"SELECT GROUP_CONCAT(sk_shopper_id) as shopperIds FROM `mst_shopper_details` WHERE outlet_id IN("+ gBean.getOutlet_id() + ") AND visit_status='published' AND (month(visit_date)='"+mid+"' or '"+mid+"'='all') AND mode_of_contact!='Online Sales Channel'",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setShopperIds(rs.getString("shopperIds"));
						return qBean;
					}
				});
	}
	/**manoj d end for overall perf graph*/
	/***manoj d start for crm compilance*/
	public QuestionnaireBean crm(QuestionnaireBean qBean,String brand,String outlet,String dealer,String region,String month) {

		//final String shopperIds= qBean.getShopperIds();
		
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region1 = (String) session.getAttribute("region");

		if(roleId.equals("7"))
		{
			dealer=dealers;
			region=region1;
		}
	    else  if((dealer=="" || dealer==null) &&(region=="" || region==null))
        {
	    	dealer="all";
	    	region="all";
        }
	    
	    else  if((dealer!="") &&(region=="" || region==null))
	    {
	    	dealer= dealer;
	    	region="all";	
		}
	    else  if((dealer=="" || dealer==null) &&(region!=""))
	    {
	    	dealer= "all";
	    	region=region;	
		}
	    else 
        {
	    	dealer= dealer;
	    	region=region;  
        }
		
//		if(dealer==null || dealer=="") {
//			dealer="all";
//		 }
//		 if(region==null || region=="") {
//			 region="all";
//		 }
		 if(outlet==null || outlet=="") {
			 outlet="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 if(brand==null || brand=="") {
			 brand="1";
		 }
		// int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		//System.out.println("shopperIds in CRM====="+qBean.getShopperIds());
		System.out.println(
				"select (select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No delay' and standard_number='CRM2') as nodelay,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='Within 2 days' and standard_number='CRM2') as upto_2_days,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='Beyond 2 days' and standard_number='CRM2') as more_2_days,(select count(shopper_id) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL')))and standard_number='CRM2')-(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No record Found' and standard_number='CRM2') as total,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No record Found' and standard_number='CRM2') as norecords,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No delay' and standard_number='CRM2') as accurate,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='Incorrect Name' or select_option_text='Incorrect Name & Phone number' and standard_number='CRM3') as name,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='Incorrect Email address' or select_option_text='Incorrect Phone number & Email address' and standard_number='CRM3') as email,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and standard_number='CRM2') as count");
		return template.queryForObject(
				"select (select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No delay' and standard_number='CRM2') as nodelay,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='Within 2 days' and standard_number='CRM2') as upto_2_days,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='Beyond 2 days' and standard_number='CRM2') as more_2_days,(select count(shopper_id) from mys_txn_answers  where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL')))and standard_number='CRM2')-(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No record Found' and standard_number='CRM2') as total,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No record Found' and standard_number='CRM2') as norecords,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No delay' and standard_number='CRM2') as accurate,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='Incorrect Name' or select_option_text='Incorrect Name & Phone number' and standard_number='CRM3') as name,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='Incorrect Email address' or select_option_text='Incorrect Phone number & Email address' and standard_number='CRM3') as email,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and standard_number='CRM2') as count",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setName(rs.getString("name"));
						qBean.setEmail(rs.getString("email"));
						qBean.setNodelay(rs.getString("nodelay"));
						qBean.setUpto2days(rs.getString("upto_2_days"));
						qBean.setMore2days(rs.getString("more_2_days"));
						qBean.setAccurate(rs.getString("accurate"));
						qBean.setTotalquestions(rs.getString("total"));
						qBean.setCount(rs.getString("count"));
						qBean.setNorecords(rs.getString("norecords"));
						return qBean;
					}
				});
	}
	
	public List<QuestionnaireBean> getcrmbargraph(QuestionnaireBean qBean,String brand,String outlet,String dealer,String region,String month) {
		//final String shopperId = qBean.getShopperIds();
		//System.out.println("shopper Ids in crmbbargraph==="+shopperId);
		
		    HttpSession session = request.getSession(true);
			String dealers = (String) session.getAttribute("dealers");
			String roleId = (String) session.getAttribute("role_id");
			String region1 = (String) session.getAttribute("region");
             if(roleId.equals("7"))
			{
				dealer=dealers;
				region=region1;
			}
		    else  if((dealer=="" || dealer==null) &&(region=="" || region==null))
	        {
		    	dealer="all";
		    	region="all";
	        }
		    
		    else  if((dealer!="") &&(region=="" || region==null))
		    {
		    	dealer= dealer;
		    	region="all";	
			}
		    else  if((dealer=="" || dealer==null) &&(region!=""))
		    {
		    	dealer= "all";
		    	region=region;	
			}
		    else 
	        {
		    	dealer= dealer;
		    	region=region;  
	        }

		
//		if(dealer==null || dealer=="") {
//			dealer="all";
//		 }
//		 if(region==null || region=="") {
//			 region="all";
//		 }
		 if(outlet==null || outlet=="") {
			 outlet="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 if(brand==null || brand=="") {
			 brand="1";
		 }
		// int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		    final String bid= brand;
			final String oid = outlet;
			final String did= dealer;
			final String rid = region;
			final String mid = month;
		System.out.println(
				"select distinct MONTHNAME(visit_date) as month from mst_shopper_details where sk_shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) order by month(visit_date);");
		return template.query(
				"select distinct MONTHNAME(visit_date) as month from mst_shopper_details where sk_shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) order by month(visit_date) ;", 
						new RowMapper<QuestionnaireBean>() {
							public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
								QuestionnaireBean qBean = new QuestionnaireBean();
								qBean.setMonth(rs.getString("month"));
								//qBean.setShopperIds(shopperId);
								QuestionnaireBean data = getmonthbargraphcrm(qBean,bid,oid,did,rid,mid);
                        
								qBean.setYes(data.getYes());
								qBean.setNo(data.getNo());
								System.out.println("here" + qBean.getMonth());
								return qBean;
							}
						});
	}
	
	public List<QuestionnaireBean> getcrmbargraphm(QuestionnaireBean qBean) {
		String month= qBean.getMonth();
		if(month==null || month==""){
			month="all";
		}
		System.out.println("select sk_shopper_id from mst_shopper_details where month(visit_date)='"
				+ qBean.getMonth() + "' and sk_shopper_id in (" + qBean.getShopperIds() + ")");
		return template.query("select sk_shopper_id from mst_shopper_details where month(visit_date)='"
				+ qBean.getMonth() + "' and sk_shopper_id in (" + qBean.getShopperIds() + ")",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						System.out.println("hellooooo");
						qBean.setShopperId(rs.getString("sk_shopper_id"));
						return qBean;
					}
				});
	}
	
	public QuestionnaireBean getmonthbargraphcrm(QuestionnaireBean qBean,String brand,String outlet,String dealer,String region,String month) {
		
		System.out.println("This is CRM bar Graph========CRM");
		if(dealer==null || dealer=="") {
			dealer="all";
		 }
		 if(region==null || region=="") {
			 region="all";
		 }
		 if(outlet==null || outlet=="") {
			 outlet="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 if(brand==null || brand=="") {
			 brand="1";
		 }
		// int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(
				"select (select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (monthName(visit_date)='" + qBean.getMonth() + "' or '"
						+ qBean.getMonth() + "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text!='No record Found' and standard_number='CRM2') as yes,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (monthName(visit_date)='" + qBean.getMonth() + "' or '"
						+ qBean.getMonth() + "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No record Found' and standard_number='CRM2') as no,(select count(shopper_id) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (monthName(visit_date)='" + qBean.getMonth() + "' or '"
						+ qBean.getMonth() + "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL')))and standard_number='CRM2') as total;");
		return template.queryForObject(
				"select (select count(select_option_text) from mys_txn_answers where shopper_id in (select sk_shopper_id  from mst_shopper_details where mys_txn_answers.status='active' AND sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (monthName(visit_date)='" + qBean.getMonth() + "' or '"
						+ qBean.getMonth() + "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text!='No record Found' and standard_number='CRM2') as yes,(select count(select_option_text) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (monthName(visit_date)='" + qBean.getMonth() + "' or '"
						+ qBean.getMonth() + "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) and select_option_text='No record Found' and standard_number='CRM2') as no,(select count(shopper_id) from mys_txn_answers where mys_txn_answers.status='active' AND shopper_id in (select sk_shopper_id  from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (monthName(visit_date)='" + qBean.getMonth() + "' or '"
						+ qBean.getMonth() + "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL')))and standard_number='CRM2') as total;",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						DecimalFormat f = new DecimalFormat(".##");
						double yes = (Double.parseDouble(rs.getString("yes"))
								/ Double.parseDouble(rs.getString("total"))) * 100;
						double no = (Double.parseDouble(rs.getString("no")) / Double.parseDouble(rs.getString("total")))
								* 100;
						qBean.setYes(String.valueOf(f.format(Double.parseDouble(String.valueOf(yes)))));
						qBean.setNo(String.valueOf(f.format(Double.parseDouble(String.valueOf(no)))));
						return qBean;
					}
				});
	}
	/**manoj end for crm compilance**/
	
	/*********for discount analysis********/
public GraphBean getOutletsoverallperFilter(GraphBean rgBean,String did,String bid,String rid) {
		
		if(bid==null){
			bid="1";
		}
		if(rid==null){
			rid="all";
		}
		if(did==null){
			did="all";
		}
		System.out.println("SELECT GROUP_CONCAT(sk_outlet_id) as outlets  FROM `mst_dealer_outlet` WHERE (`brand_id`='"+bid+"' or '"+bid+"'='all') AND (`region_id`='"+rid+"' or '"+rid+"'='all') AND (dealer_id='"+did+"' or '"+did+"'='all'");
		return template.queryForObject(
				"SELECT GROUP_CONCAT(sk_outlet_id) as outlets  FROM `mst_dealer_outlet` WHERE (`brand_id`='"+bid+"' or '"+bid+"'='all') AND (`region_id`='"+rid+"' or '"+rid+"'='all') AND (dealer_id='"+did+"' or '"+did+"'='all')",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						rgBean.setOutlets(rs.getString("outlets"));
						return rgBean;
					}
				});
	}
	/********for discount analysis**********/
/*
 *manoj d shoopers by outlet
 * 
 */
public QuestionnaireBean getshoppersByOutlet(QuestionnaireBean qBean,GraphBean gBean,String mid) {
	
	//int year=2019;
	int year = Calendar.getInstance().get(Calendar.YEAR);
	 if(mid==null || mid=="") {
		 mid="all";
	 }
	System.out.println("SELECT GROUP_CONCAT(sk_shopper_id) as shopperIds FROM `mst_shopper_details` WHERE outlet_id IN("+ gBean.getOutlet_id() + ") AND visit_status='published' AND (month(visit_date)='"+mid+"' or '"+mid+"'='all') ");
	return template.queryForObject(
			"SELECT GROUP_CONCAT(sk_shopper_id) as shopperIds FROM `mst_shopper_details` WHERE outlet_id IN("+ gBean.getOutlet_id() + ") AND visit_status='published' AND (month(visit_date)='"+mid+"' or '"+mid+"'='all') ",
			new RowMapper<QuestionnaireBean>() {
				public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
					qBean.setShopperIds(rs.getString("shopperIds"));
					return qBean;
				}
			});
}
/*
 * olr download manoj d
 */
public List<QuestionnaireBean> questionsandoptions(QuestionnaireBean qBean,String osc_shopper_id,String dealer_id,String NonOsc_shopper_id) {
	
		/*
		 * String sql =
		 * "SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mst_questionare.question_points as totalQuesPoints from mys_txn_answers,mst_shopper_details,mys_score,mst_questionare WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.shopper_id='"
		 * + NonOsc_shopper_id +
		 * "' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_score.shopper_id=mys_txn_answers.shopper_id AND  mys_txn_answers.question_id=mst_questionare.sk_question_id GROUP BY mys_txn_answers.question_id  UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mst_questionare.question_points from mys_txn_answers,mst_shopper_details,mys_score,mst_questionare WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.shopper_id='"
		 * + osc_shopper_id +
		 * "' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id  AND mys_score.shopper_id=mys_txn_answers.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id GROUP BY mys_txn_answers.question_id  ORDER BY mode_of_contact DESC, CAST(standard_number_sequence as decimal(10,2)) ASC  "
		 * ;
		 */
	String sql = "SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mst_questionare.question_points as totalQuesPoints from mys_txn_answers,mst_shopper_details,mys_score,mst_questionare WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.shopper_id='"
			+ NonOsc_shopper_id
			+ "' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_score.shopper_id=mys_txn_answers.shopper_id AND  mys_txn_answers.question_id=mst_questionare.sk_question_id AND mys_txn_answers.status='active' GROUP BY mys_txn_answers.question_id  UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mst_questionare.question_points from mys_txn_answers,mst_shopper_details,mys_score,mst_questionare WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.shopper_id='"
			+ osc_shopper_id
			+ "' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_score.shopper_id=mys_txn_answers.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id AND mys_txn_answers.status='active' GROUP BY mys_txn_answers.question_id  ORDER BY mode_of_contact DESC, CAST(standard_number_sequence as decimal(10,2)) ASC ";
	//String sql="SELECT mys_txn_answers.*,SUM(mys_txn_answers.question_points) as maxQues_points,SUM(mys_txn_answers.scored_points) AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers,mst_shopper_details,mys_score,mys_txn_formulaanswer,mst_questionare WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.shopper_id='9' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id GROUP BY mys_txn_answers.question_id  UNION SELECT mys_txn_answers.*,SUM(mys_txn_answers.question_points) as maxQues_points,SUM(mys_txn_answers.scored_points) AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points from mys_txn_answers,mst_shopper_details,mys_score,mys_txn_formulaanswer,mst_questionare WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.shopper_id='6' AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id GROUP BY mys_txn_answers.question_id ORDER BY mode_of_contact ASC,standard_number_sequence ASC LIMIT 5,1";
	//String sql="SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers,mst_shopper_details,mys_score,mys_txn_formulaanswer,mst_questionare WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.shopper_id='"+NoscShopper_id+"' AND mys_txn_answers.formula_flag=0 AND mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id AND mys_txn_answers.subsection_id=mys_score.subsection_id AND mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id AND mys_txn_answers.question_id=mst_questionare.sk_question_id GROUP BY mys_txn_answers.question_id UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers LEFT JOIN mst_shopper_details ON mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id LEFT JOIN mys_score ON mys_txn_answers.subsection_id=mys_score.subsection_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id LEFT JOIN mst_questionare on mys_txn_answers.question_id=mst_questionare.sk_question_id WHERE mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mys_txn_answers.formula_flag=1 AND mys_txn_answers.shopper_id='"+NoscShopper_id+"' GROUP BY mys_txn_answers.sk_answer_id UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers LEFT JOIN mst_shopper_details ON mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id LEFT JOIN mys_score ON mys_txn_answers.subsection_id=mys_score.subsection_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id LEFT JOIN mst_questionare on mys_txn_answers.question_id=mst_questionare.sk_question_id WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.formula_flag=0 AND mys_txn_answers.shopper_id='"+shopper_id+"' GROUP BY mys_txn_answers.sk_answer_id UNION SELECT mys_txn_answers.*,mys_txn_answers.question_points as maxQues_points,mys_txn_answers.scored_points AS maxScore_points,mst_shopper_details.mode_of_contact,mys_score.percentage,mys_txn_formulaanswer.points as formulapoints,mst_questionare.question_points as totalQuesPoints from mys_txn_answers LEFT JOIN mst_shopper_details ON mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id LEFT JOIN mys_score ON mys_txn_answers.subsection_id=mys_score.subsection_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id LEFT JOIN mst_questionare on mys_txn_answers.question_id=mst_questionare.sk_question_id WHERE mst_shopper_details.mode_of_contact='Online Sales Channel' AND mys_txn_answers.formula_flag=1 AND mys_txn_answers.shopper_id='"+shopper_id+"' GROUP BY mys_txn_answers.sk_answer_id  ORDER BY mode_of_contact ASC,standard_number_sequence ASC LIMIT " + (pageid - 1) + "," + total + " ";
	System.out.println("dealer id in questionand options dao"+dealer_id);
	System.out.println("Manoj d questions and options list=" + sql);
	//final String sid=NonOsc_shopper_id;
	//final String oscsid=osc_shopper_id;
	return template.query(sql, new RowMapper<QuestionnaireBean>() {
		public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
			QuestionnaireBean qBean = new QuestionnaireBean();
			qBean.setQuestion(rs.getString("question_text"));
			qBean.setStandard_number(rs.getString("standard_number"));
			qBean.setSk_question_id(rs.getString("question_id"));
			qBean.setQuestion_type(rs.getString("question_type"));
			qBean.setSubsection_name(rs.getString("subsection_name"));
			String Question_type=rs.getString("question_type");
			qBean.setStorevisitId(rs.getString("shopper_id"));
			System.out.println("Question type===="+Question_type);
			  List<QuestionnaireBean> optionsList=getOLROptionsList(qBean,rs.getString("question_id"));
			  qBean.setOptionsList(optionsList);
			  List<QuestionnaireBean> subQuesList=getSubQuesList(qBean,rs.getString("question_id"));
			  qBean.setSubquestions(subQuesList);
			  if(!Question_type.equals("Main Question With Set Of SubQuestions") && !Question_type.equals("Dependent Question With Set Of SubQuestions")){
			 qBean.setSelectedanswerid(rs.getString("select_option_text"));
			 }
			  else{
				  List<QuestionnaireBean> selectAnsList=getDepeSeleAnsList(qBean,rs.getString("question_id"),rs.getString("shopper_id"));
				  qBean.setSelectedoptionsList(selectAnsList);
				  QuestionnaireBean sumOfPoints=getSumOfPointsForDepeQues(qBean,rs.getString("question_id"),rs.getString("shopper_id"));
				  qBean.setSumOfScoredPoints(sumOfPoints.getSumOfScoredPoints());
				  qBean.setSumOfQuesPoints(sumOfPoints.getSumOfQuesPoints());
			  }
			  
			System.out.println("select option text====="+rs.getString("select_option_text"));
			qBean.setAnswer_type(rs.getString("answer_type"));		
			qBean.setParagraph(rs.getString("main_ques_comment"));
			System.out.println();
			qBean.setComment_criteria(rs.getString("main_ques_comment"));
			qBean.setSelected_option_comment(rs.getString("select_option_comment"));
			System.out.println("select option comment====="+rs.getString("select_option_comment"));
			qBean.setParagraph(rs.getString("paragraph"));
			qBean.setDate_code(rs.getString("date"));
			qBean.setTime_code(rs.getString("dateandtime"));
			qBean.setMode_of_contact(rs.getString("mode_of_contact"));			
			qBean.setPercentage(rs.getString("percentage"));
			qBean.setQuestion_points(rs.getString("question_points"));
			qBean.setScored_points(rs.getString("scored_points"));
			qBean.setMax_question_points(rs.getString("maxQues_points"));
			qBean.setMax_scored_points(rs.getString("maxScore_points"));
			qBean.setFormulFlag(rs.getString("formula_flag"));
			if(rs.getString("formula_flag").equals("1")){
			QuestionnaireBean getformulaPoint =getformulaPoints(qBean, rs.getString("shopper_id"),rs.getString("question_id"));
			qBean.setFormulapoints(getformulaPoint.getFormulapoints());
			System.out.println("formula Points in If condition===="+getformulaPoint.getFormulapoints());
			}
			qBean.setTotal_ques_points(rs.getString("totalQuesPoints"));
			System.out.println("formula flag"+rs.getString("formula_flag")+"===="+rs.getString("standard_number")+"==="+rs.getString("question_points"));
			System.out.println("first=" + rs.getString("mode_of_contact"));
			return qBean;
		}

	});
}
/*
 * olr download manoj d
 */
}
