package com.mystery.dao;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.GraphBean;
import com.mystery.beans.MysteryShoppingVisitsBean;
import com.mystery.beans.QuestionnaireBean;
import com.mystery.beans.RegionBean;
import com.mystery.beans.ReportsBean;



public class ReportsDao {

	@Autowired
    JdbcTemplate template;
	@Autowired
	HelperDao hDao;
	@Autowired
	private HttpServletRequest request;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	
	public List<DatabaseManagementBean> getDealersbySubregion(DatabaseManagementBean dbBean, String region_id,String brand_id) {
		System.out
				.println("SELECT * FROM `mst_dealer`,mst_dealer_outlet WHERE (mst_dealer_outlet.region_id='" + region_id + "' OR '" + region_id + "'='all')  AND mst_dealer_outlet.`brand_id`='"+brand_id+"' AND `dealer_status`='active' and mst_dealer_outlet.dealer_id=mst_dealer.sk_dealer_id GROUP by mst_dealer.sk_dealer_id;");
		return template.query(
				"SELECT * FROM `mst_dealer`,mst_dealer_outlet WHERE (mst_dealer_outlet.region_id='" + region_id + "' OR '" + region_id + "'='all')  AND mst_dealer_outlet.`brand_id`='"+brand_id+"' AND `dealer_status`='active' and mst_dealer_outlet.dealer_id=mst_dealer.sk_dealer_id GROUP by mst_dealer.sk_dealer_id;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_dealer_id(rs.getString("sk_dealer_id"));
						dbBean.setDealer_name(rs.getString("dealer_name"));
						return dbBean;
					}
				});
	}
	public List<DatabaseManagementBean> getOutletsbyDealers(DatabaseManagementBean dbBean, String dealer_id, String brand_id) {
		System.out
				.println("SELECT * FROM `mst_dealer_outlet` WHERE (dealer_id='" + dealer_id + "' OR '" + dealer_id + "'='all') and brand_id='"+brand_id+"' AND `outlet_status`='active';");
		return template.query(
				"SELECT * FROM `mst_dealer_outlet` WHERE (dealer_id='" + dealer_id + "' OR '" + dealer_id + "'='all') and brand_id='"+brand_id+"' AND `outlet_status`='active';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setOutlet_id(rs.getString("sk_outlet_id"));
						dbBean.setOutlet_name(rs.getString("outlet_name"));
						return dbBean;
					}
				});
	}
	public List<MysteryShoppingVisitsBean> getYearsList(MysteryShoppingVisitsBean mvBean) {
		System.out.println("SELECT mst_shopper_details.year FROM `mst_shopper_details` GROUP BY year;");
		return template.query("SELECT mst_shopper_details.year FROM `mst_shopper_details` GROUP BY year;",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setYear(rs.getString("year"));
						return mvBean;
					}
				});
	}
	public List<MysteryShoppingVisitsBean> getMSNResultHeadders(MysteryShoppingVisitsBean mvBean, String brand_id, String year) {
		 String sql = "SELECT mst_questionare.question_code,mst_questionare.standard_number,mst_questionare.question,GROUP_CONCAT(mst_questionare_subquestion.subquestion SEPARATOR '#') as subquestion  from mst_questionare LEFT JOIN   mst_questionare_subquestion on mst_questionare_subquestion.question_id=mst_questionare.sk_question_id WHERE mst_questionare.question_status='active' and mst_questionare.brand_id='"+brand_id+"' and mst_questionare.mode_of_contact!='Online Sales Channel' AND mst_questionare.year_applied='"+year+"'    GROUP by mst_questionare.sk_question_id   ORDER BY mst_questionare.standard_number_sequence ";
			    System.out.println("MSN headders="+sql);
			    return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
			    	  MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
			    	  mvBean.setMainquestion(rs.getString("question"));
			    	  mvBean.setSubquestion(rs.getString("subquestion"));
			    	  mvBean.setQuestion_code(rs.getString("question_code"));
			    	  mvBean.setStandard_number(rs.getString("standard_number"));
			        return mvBean;
			      }
			    });
			  }
	public List<MysteryShoppingVisitsBean> getMSNResultAns(MysteryShoppingVisitsBean mvBean, String year, String month, String region, String location, String brand_id, String dealer, int total) {
	int	pageid = (Integer.parseInt(mvBean.getLimit())) * total;
		    String sql="SELECT WEEK(`shopper_link_visit_date`) as weekday,concat(mst_msm_result.year,'_',mst_msm_result.quarter) as wave,concat(mst_msm_result.visit_brand_model_name,'-',mst_msm_result.brand_varient_name) as model,mst_msm_result.* FROM mys_txn_answers,mst_msm_result WHERE mys_txn_answers.shopper_id =mst_msm_result.shopper_id and mys_txn_answers.status='active' and  mst_msm_result.year='"+year+"' AND (month(mst_msm_result.scheduled_date)='"+month+"' OR '"+month+"'='all') AND (mst_msm_result.outlet_region_id='"+region+"' OR '"+region+"'='all') AND (mst_msm_result.dealership_id='"+dealer+"' OR '"+dealer+"'='all') AND (mst_msm_result.outlet_id='"+location+"' OR '"+location+"'='all') and mst_msm_result.visit_brand_id='"+brand_id+"' and mst_msm_result.visit_status='published' and mode_of_contact!='Online Sales Channel' GROUP BY mst_msm_result.shopper_id   Limit " + pageid  + "," + total + " ";
		    System.out.println("test sql=" + sql);
		    return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
		      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
		    	  MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
		    	  mvBean.setSk_shopper_id(rs.getString("shopper_id"));
		    	  mvBean.setQuarter(rs.getString("wave"));
		    	  mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
		    	  mvBean.setOutlet_id(rs.getString("location_id"));
		    	  mvBean.setPincode(rs.getString("outlet_pincode"));
		    	  mvBean.setOutlet_name(rs.getString("outlet_name"));
		    	  mvBean.setDealer_name(rs.getString("dealership_name"));
			        mvBean.setAddress(rs.getString("outlet_address"));
			        mvBean.setBrand_name(rs.getString("visit_brand_name"));
			        mvBean.setShopper_link_name(rs.getString("shopper_link_name"));
			        mvBean.setShopper_link_age(rs.getString("shopper_link_age"));
			        mvBean.setShopper_link_email(rs.getString("shopper_link_email"));
			        mvBean.setShopper_link_contact(rs.getString("shopper_link_contact"));
			        mvBean.setShoppers_link_shoppersCarBrand(rs.getString("shoppers_link_shoppersCarBrand"));
			        mvBean.setShoppers_link_shoppersCarModel(rs.getString("shoppers_link_shoppersCarModel"));
			        mvBean.setShoppers_car_yop(rs.getString("shoppers_link_shoppersCarYop"));
		            mvBean.setModel_name(rs.getString("model"));
		            mvBean.setVisit_date(rs.getString("shopper_link_visit_date"));
		            mvBean.setStart_time(rs.getString("start_time"));
		            mvBean.setEnd_time(rs.getString("end_time"));
		            mvBean.setBrand_id(brand_id);
		            mvBean.setShopper_link_gender(rs.getString("shopper_link_gender"));
		            mvBean.setWeekday(rs.getString("weekday"));
		             List<MysteryShoppingVisitsBean> getMSNResultAnslist=MSNResultAns( mvBean, rs.getString("shopper_id"),year);
		         mvBean.setGetMSNResultAns(getMSNResultAnslist);
		        return mvBean;
		      }
		    });
	}
	public List<MysteryShoppingVisitsBean> MSNResultAns(MysteryShoppingVisitsBean mvBean, String id,String year) {
		String sql="SELECT mst_questionare.sk_question_id,mst_questionare.standard_number as sn,mst_questionare.question_type,mys_txn_answers.answer_type,(case when mys_txn_answers.main_ques_comment is null then 'NA' else main_ques_comment end) as main_ques_comment,(CASE WHEN mys_txn_answers.answer_type='Select/List' THEN select_option_text WHEN mys_txn_answers.answer_type='Date & Time' THEN CONCAT(date,' ',mys_txn_answers.dateandtime) WHEN mys_txn_answers.answer_type='Paragraph' THEN mys_txn_answers.paragraph WHEN mys_txn_answers.answer_type='Date' THEN date WHEN mys_txn_answers.answer_type='Time'  THEN mys_txn_answers.dateandtime ELSE 'N/A' END ) as mainanswer ,GROUP_CONCAT(DISTINCT mst_questionare_subquestion.sk_subquestion_id),GROUP_CONCAT(case when mys_txn_answers.subquestion_id is null and mst_questionare.sk_question_id=mst_questionare_subquestion.question_id then 'NA'   when mys_txn_answers.subquestion_id=mst_questionare_subquestion.sk_subquestion_id THEN mys_txn_answers.subquestion_id end) as subquestion_id ,GROUP_CONCAT(CASE  WHEN mys_txn_answers.answer_type='Select/List'  and  mys_txn_answers.subquestion_id=mst_questionare_subquestion.sk_subquestion_id  THEN select_option_text  WHEN mys_txn_answers.answer_type='Date & Time'  and  mys_txn_answers.subquestion_id=mst_questionare_subquestion.sk_subquestion_id THEN CONCAT(date,' ',mys_txn_answers.dateandtime) WHEN mys_txn_answers.answer_type='Paragraph'  and  mys_txn_answers.subquestion_id=mst_questionare_subquestion.sk_subquestion_id THEN mys_txn_answers.paragraph WHEN mys_txn_answers.answer_type='Date'  and  mys_txn_answers.subquestion_id=mst_questionare_subquestion.sk_subquestion_id THEN date WHEN mys_txn_answers.answer_type='Time'  and  mys_txn_answers.subquestion_id=mst_questionare_subquestion.sk_subquestion_id THEN mys_txn_answers.dateandtime WHEN mys_txn_answers.sub_question_text IS NULL THEN 'N/A'  END,'$$', CASE WHEN mys_txn_answers.select_option_comment IS NULL and mst_questionare.sk_question_id=mst_questionare_subquestion.question_id THEN 'N/A' WHEN mys_txn_answers.select_option_comment = '' and mst_questionare.sk_question_id=mst_questionare_subquestion.question_id THEN 'N/A' when mys_txn_answers.subquestion_id=mst_questionare_subquestion.sk_subquestion_id THEN mys_txn_answers.select_option_comment END SEPARATOR '#')  AS subanswercomment FROM `mys_txn_answers` RIGHT JOIN mst_questionare on mst_questionare.sk_question_id=mys_txn_answers.question_id and mys_txn_answers.status='active' and mys_txn_answers.shopper_id='"+id+"' left join mst_questionare_subquestion on mst_questionare_subquestion.question_id=mst_questionare.sk_question_id    WHERE mst_questionare.brand_id='"+mvBean.getBrand_id()+"' and mst_questionare.question_status='active' and mode_of_contact!='Online Sales Channel' AND mst_questionare.year_applied='"+year+"'  GROUP by mst_questionare.sk_question_id ORDER BY mst_questionare.standard_number_sequence  ";
		   System.out.println(sql);
		    return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
		      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
		    	  MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();

		    	 
		    	  mvBean.setStandard_number(rs.getString("sn"));
		    	  if(rs.getString("question_type").equals("Main Question With Set Of SubQuestions") || rs.getString("question_type").equals("Dependent Question With Set Of SubQuestions")) {
		    		  mvBean.setSubQuestionAnswer(rs.getString("subanswercomment"));
		    		  mvBean.setAnswer("");
		    	  }else {
		    		  mvBean.setAnswer(rs.getString("mainanswer"));
		    		    mvBean.setMain_ques_comment(rs.getString("main_ques_comment"));
		    	  }
		    	 // mvBean.setSubQuestionAnswer(rs.getString("subanswercomment"));
		       
		    
		        //mvBean.setOption_comment(rs.getString("optioncomment"));
		   
		        return mvBean;
		      }
		    });
		  }
	public MysteryShoppingVisitsBean getmsmresultcountIds(MysteryShoppingVisitsBean mBean, String report_type,
			String month, String dealer_id, String outlet_id, String brand_id, String year, String region) {
	    String sql="SELECT count(DISTINCT mys_txn_answers.shopper_id) as count FROM mys_txn_answers,mst_msm_result WHERE mys_txn_answers.shopper_id =mst_msm_result.shopper_id and mys_txn_answers.status='active'  and  mst_msm_result.year='"+year+"' AND (month(mst_msm_result.scheduled_date)='"+month+"' OR '"+month+"'='all') AND (mst_msm_result.outlet_region_id='"+region+"' OR '"+region+"'='all') AND (mst_msm_result.dealership_id='"+dealer_id+"' OR '"+dealer_id+"'='all') AND (mst_msm_result.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all') and mst_msm_result.visit_brand_id='"+brand_id+"' and mst_msm_result.visit_status='published' ";
	    System.out.println("count ="+sql);
	    return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
	      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
	    	  mBean.setCount(rs.getString("count"));
	        return mBean;
	      }
	    });
	  }
	public List<MysteryShoppingVisitsBean> getMSNResultAns1(MysteryShoppingVisitsBean mvBean, String year, String month,
			String region, String location, String brand_id, String dealer) {


		 String sql="SELECT WEEK(`shopper_link_visit_date`) as weekday,concat(mst_msm_result.year,'_',mst_msm_result.quarter) as wave,concat(mst_msm_result.visit_brand_model_name,'-',mst_msm_result.brand_varient_name) as model,mst_msm_result.* FROM mys_txn_answers,mst_msm_result WHERE mys_txn_answers.shopper_id =mst_msm_result.shopper_id and mys_txn_answers.status='active'  and  mst_msm_result.year='"+year+"' AND (month(mst_msm_result.scheduled_date)='"+month+"' OR '"+month+"'='all') AND (mst_msm_result.outlet_region_id='"+region+"' OR '"+region+"'='all') AND (mst_msm_result.dealership_id='"+dealer+"' OR '"+dealer+"'='all') AND (mst_msm_result.outlet_id='"+location+"' OR '"+location+"'='all') and mst_msm_result.visit_brand_id='"+brand_id+"' and mst_msm_result.visit_status='published' and mode_of_contact!='Online Sales Channel' GROUP BY mst_msm_result.shopper_id  ";
		    System.out.println("test sql=" + sql);
		    return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
		      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
		    	  MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
		    	  mvBean.setSk_shopper_id(rs.getString("shopper_id"));
		    	  mvBean.setQuarter(rs.getString("wave"));
		    	  mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
		    	  mvBean.setOutlet_id(rs.getString("location_id"));
		    	  mvBean.setPincode(rs.getString("outlet_pincode"));
		    	  mvBean.setOutlet_name(rs.getString("outlet_name"));
		    	  mvBean.setDealer_name(rs.getString("dealership_name"));
			        mvBean.setAddress(rs.getString("outlet_address"));
			        mvBean.setBrand_name(rs.getString("visit_brand_name"));
			        mvBean.setShopper_link_name(rs.getString("shopper_link_name"));
			        mvBean.setShopper_link_age(rs.getString("shopper_link_age"));
			        mvBean.setShopper_link_email(rs.getString("shopper_link_email"));
			        mvBean.setShopper_link_contact(rs.getString("shopper_link_contact"));
			        mvBean.setShoppers_link_shoppersCarBrand(rs.getString("shoppers_link_shoppersCarBrand"));
			        mvBean.setShoppers_link_shoppersCarModel(rs.getString("shoppers_link_shoppersCarModel"));
			        mvBean.setShoppers_car_yop(rs.getString("shoppers_link_shoppersCarYop"));
		            mvBean.setModel_name(rs.getString("model"));
		            mvBean.setVisit_date(rs.getString("shopper_link_visit_date"));
		            mvBean.setStart_time(rs.getString("start_time"));
		            mvBean.setEnd_time(rs.getString("end_time"));
		            mvBean.setBrand_id(brand_id);
		            mvBean.setShopper_link_gender(rs.getString("shopper_link_gender"));
		            mvBean.setWeekday(rs.getString("weekday"));
		             List<MysteryShoppingVisitsBean> getMSNResultAnslist=MSNResultAns( mvBean, rs.getString("shopper_id"),year);
		         mvBean.setGetMSNResultAns(getMSNResultAnslist);
		        return mvBean;
		      }
		    });
	}
	 
	public List<MysteryShoppingVisitsBean> getLoweringScore(MysteryShoppingVisitsBean mvBean, String year, String month,
			String region, String outlet, String brand_id, String dealer_id,String pageid1,int total) {
		int	pageid = (Integer.parseInt(mvBean.getLimit())) * total;
		MysteryShoppingVisitsBean mvBean1= new MysteryShoppingVisitsBean();
		mvBean1=hDao.getScoreWeightage(mvBean,year);
		String pe_weightage=mvBean1.getPe_weightage();
		String ct_weightage=mvBean1.getCt_weightage();
		String osc_weightage=mvBean1.getOsc_weightage();
		System.out.println("pe weightage"+pe_weightage);
		System.out.println("ct_weightage"+ct_weightage);
		System.out.println("osc_weightage"+osc_weightage);
		
		String sql="SELECT * FROM(SELECT *,IFNULL(round(((pe_sc_points+ct_sc_points)/(pe_max_points+ct_max_points))*100,2),0) as percentage FROM(SELECT * FROM(SELECT * FROM(SELECT  mst_brand.brand_name,monthname(visit_date)as monthname,month(visit_date)as month, mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_name,mst_shopper_details.sc_name, mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_geo_region.region_name, mst_shopper_details.sk_shopper_id,mst_shopper_details.name,mst_shopper_details.dealer_id,mst_shopper_details.outlet_id,mst_shopper_details.visit_date,mst_shopper_details.year,mst_shopper_details.mode_of_contact,mst_shopper_details.brand_id,mst_shopper_details.visit_status,mst_dealer.region_id FROM mst_shopper_details,mst_dealer,mst_dealer_outlet,mst_geo_region,mst_brand WHERE mst_brand.sk_brand_id=mst_shopper_details.brand_id and mst_geo_region.sk_region_id=mst_dealer_outlet.region_id and mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id and mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND visit_status='published' AND year='"+year+"' and mode_of_contact!='Online Sales Channel' AND (EXTRACT(MONTH FROM visit_date)='"+month+"' OR '"+month+"'='ALL') AND (mst_dealer.region_id='"+region+"' OR '"+region+"'='ALL') AND (mst_shopper_details.brand_id='"+brand_id+"' OR '"+brand_id+"'='ALL') AND (mst_shopper_details.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='ALL')) as res1 LEFT JOIN (SELECT shopper_id as pe_shopper_id,SUM(scored_points)*"+pe_weightage+" as pe_sc_points,SUM(max_points)*"+pe_weightage+" as pe_max_points FROM mys_score WHERE section_id=2 GROUP BY shopper_id) as res2 ON res1.sk_shopper_id=res2.pe_shopper_id) as res3 LEFT JOIN (SELECT shopper_id as ct_shopper_id,SUM(scored_points)*"+ct_weightage+" as ct_sc_points,SUM(max_points)*"+ct_weightage+" as ct_max_points FROM mys_score WHERE section_id=3 GROUP BY shopper_id) as res4 ON res3.sk_shopper_id=res4.ct_shopper_id) as res5 GROUP BY sk_shopper_id) as res6 WHERE percentage<90 order by month    Limit " + pageid  + "," + total + " ";
		System.out.println(sql);
		return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
				mvBean.setMonth(rs.getString("monthname"));
				mvBean.setDealer_name(rs.getString("dealer_name"));
				mvBean.setOutlet_name(rs.getString("outlet_name"));
				mvBean.setVisit_date(rs.getString("visit_date"));
				mvBean.setName(rs.getString("shopper_link_name"));
				mvBean.setContact_number(rs.getString("shopper_link_contact"));
				mvBean.setEmail(rs.getString("shopper_link_email"));
				mvBean.setSc_name(rs.getString("sc_name"));
				mvBean.setOutlet_score(rs.getString("percentage"));
				double d = Double.parseDouble(rs.getString("percentage"));
				mvBean.setOutlet_score(String.format("%.2f", d));
				mvBean.setRegion_name(rs.getString("region_name"));
				mvBean.setBrand_name(rs.getString("brand_name"));
				List<MysteryShoppingVisitsBean> missedOpportunitesList=getMissedOpportunities(mvBean, rs.getString("sk_shopper_id"));
				mvBean.setMissedOpportunitesList(missedOpportunitesList);
				
				return mvBean;
			}
		});

	}
	
	public List<MysteryShoppingVisitsBean> getLoweringScore1(MysteryShoppingVisitsBean mvBean, String year, String month,
			String region, String outlet, String brand_id, String dealer_id) {
		MysteryShoppingVisitsBean mvBean1=new MysteryShoppingVisitsBean();
		mvBean1=hDao.getScoreWeightage(mvBean,year);
		String pe_weightage=mvBean1.getPe_weightage();
		String ct_weightage=mvBean1.getCt_weightage();
		String osc_weightage=mvBean1.getOsc_weightage();
		
		String sql="SELECT * FROM(SELECT *,IFNULL(round(((pe_sc_points+ct_sc_points)/(pe_max_points+ct_max_points))*100,2),0) as percentage FROM(SELECT * FROM(SELECT * FROM(SELECT  mst_brand.brand_name,monthname(visit_date)as monthname,month(visit_date)as month, mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_name,mst_shopper_details.sc_name, mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_geo_region.region_name, mst_shopper_details.sk_shopper_id,mst_shopper_details.name,mst_shopper_details.dealer_id,mst_shopper_details.outlet_id,mst_shopper_details.visit_date,mst_shopper_details.year,mst_shopper_details.mode_of_contact,mst_shopper_details.brand_id,mst_shopper_details.visit_status,mst_dealer.region_id FROM mst_shopper_details,mst_dealer,mst_dealer_outlet,mst_geo_region,mst_brand WHERE mst_brand.sk_brand_id=mst_shopper_details.brand_id and mst_geo_region.sk_region_id=mst_dealer_outlet.region_id and mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id and mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND visit_status='published' AND year='"+year+"' and mode_of_contact!='Online Sales Channel' AND (EXTRACT(MONTH FROM visit_date)='"+month+"' OR '"+month+"'='ALL') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL') AND (mst_shopper_details.brand_id='"+brand_id+"' OR '"+brand_id+"'='ALL') AND (mst_shopper_details.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='ALL')) as res1 LEFT JOIN (SELECT shopper_id as pe_shopper_id,SUM(scored_points)*"+pe_weightage+" as pe_sc_points,SUM(max_points)*"+pe_weightage+" as pe_max_points FROM mys_score WHERE section_id=2 GROUP BY shopper_id) as res2 ON res1.sk_shopper_id=res2.pe_shopper_id) as res3 LEFT JOIN (SELECT shopper_id as ct_shopper_id,SUM(scored_points)*"+ct_weightage+" as ct_sc_points,SUM(max_points)*"+ct_weightage+" as ct_max_points FROM mys_score WHERE section_id=3 GROUP BY shopper_id) as res4 ON res3.sk_shopper_id=res4.ct_shopper_id) as res5 GROUP BY sk_shopper_id) as res6 WHERE percentage<90 order by month ";
		System.out.println(sql);
		return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
				mvBean.setMonth(rs.getString("monthname"));
				mvBean.setDealer_name(rs.getString("dealer_name"));
				mvBean.setOutlet_name(rs.getString("outlet_name"));
				mvBean.setVisit_date(rs.getString("visit_date"));
				mvBean.setName(rs.getString("shopper_link_name"));
				mvBean.setContact_number(rs.getString("shopper_link_contact"));
				mvBean.setEmail(rs.getString("shopper_link_email"));
				mvBean.setSc_name(rs.getString("sc_name"));
				mvBean.setOutlet_score(rs.getString("percentage"));
				double d = Double.parseDouble(rs.getString("percentage"));
				mvBean.setOutlet_score(String.format("%.2f", d));
				mvBean.setRegion_name(rs.getString("region_name"));
				mvBean.setBrand_name(rs.getString("brand_name"));
				List<MysteryShoppingVisitsBean> missedOpportunitesList=getMissedOpportunities(mvBean, rs.getString("sk_shopper_id"));
				mvBean.setMissedOpportunitesList(missedOpportunitesList);
				
				return mvBean;
			}
		});

	}
	protected List<MysteryShoppingVisitsBean> getMissedOpportunities(MysteryShoppingVisitsBean mvBean, String sk_shopper_id) {
			String sql="SELECT shopper_id, question_id, question_text,standard_number_sequence,standard_number FROM mys_txn_answers WHERE  question_points>0 AND formula_flag=0 AND scored_points=0 AND shopper_id='"+sk_shopper_id+"' GROUP BY question_id UNION SELECT mys_txn_answers.shopper_id, mys_txn_answers.question_id, question_text,mys_txn_answers.standard_number_sequence,mys_txn_answers.standard_number FROM mys_txn_answers LEFT JOIN mst_questionare ON mys_txn_answers.question_id=mst_questionare.sk_question_id LEFT JOIN mys_txn_formulaanswer ON mys_txn_answers.question_id=mys_txn_formulaanswer.question_id and mys_txn_answers.shopper_id=mys_txn_formulaanswer.shopper_id WHERE mys_txn_answers.formula_flag=1 AND mys_txn_answers.shopper_id='"+sk_shopper_id+"'  AND mst_questionare.question_points>0 AND mys_txn_formulaanswer.points=0 GROUP BY question_id ORDER BY CAST(standard_number_sequence AS decimal(10,2)) asc";
			System.out.println(sql);
			return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					MysteryShoppingVisitsBean mvBean=new MysteryShoppingVisitsBean();
					mvBean.setQuestion_text(rs.getString("question_text"));
					return mvBean;
				}
			});

	}
	public List<MysteryShoppingVisitsBean> getdealer_performance(MysteryShoppingVisitsBean mvBean, String year, String month, String region, String location, String brand_id, String dealer, String pageid1, int total) {
	 	int	pageid = (Integer.parseInt(mvBean.getLimit())) * total;
			//String sql="select * from(select * from (SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month, mys_score.Outlet_name,mys_score.dealer_name,mys_score.region_name,mst_msm_result.visit_brand_name,mst_msm_result.outlet_region_name,mst_msm_result.outlet_city_name,mst_shopper_details.shopper_link_name,mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_visit_date,mst_shopper_details.sc_name as salesperson_name,mst_msm_result.city_tier,mst_msm_result.city_metro,mst_dealer_outlet.contact_person as regional_manager,monthname(mst_msm_result.scheduled_date)as monthname,month(mst_msm_result.scheduled_date)as months,IFNULL(SUM(mys_score.scored_points),0) as point_reached,IFNULL(SUM(mys_score.max_points),0) as maximum_points,mys_txn_outlet_score.monthly_score_percentage as monthly_outlet_score,mys_score.shopper_id as sid FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'    and mys_txn_outlet_score.shopper_id=mys_score.shopper_id    GROUP BY  mys_score.shopper_id) res1 left  join (SELECT substr((month+100),2) as mon,mys_txn_dealer_score.year as y1,month as m1,brand_id as b1,dealer_id as d1,monthly_score_percentage,monthly_score_percentage as dscore,@rank := CASE WHEN @year= year    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN @month= month    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN (@year := year  and @month := month  ) IS NOT NULL AND (@rankval := monthly_score_percentage) IS NOT NULL THEN 1 END AS rnk FROM mys_txn_dealer_score,  (SELECT @rank := 1, @year := 1,@month := 1,@brand_id := 1, @rankval := 0) AS x WHERE brand_id='1' ORDER BY mon asc,monthly_score_percentage desc) as res2  on      res1.dealer_id=res2.d1 and res1.month=res2.m1  GROUP by res1.shopper_id  ORDER by mon,rnk) as rr1 left join (SELECT shopper_id as sid,substr((month+100),2) as mon,IF(ytd_dealer_avg=@d_last_score,@dcurRank:=@dcurRank,@dcurRank:=@d_sequence) AS drank,@d_sequence:=@dcurRank+1 as sequence,@d_last_score:=ytd_dealer_avg AS dlast_score,dealer_id as did1 FROM(SELECT *,(SELECT ifnull(round(((sum(scores_earned)+sum(scored_point_721))/(sum(maximum_scores)+sum(maximum_point_721)))*100,2),0) as ytd_dealer_avg FROM mys_txn_dealer_score WHERE dealer_id=q1.dealer_id AND month<="+month+" AND year=q1.year AND brand_id=q1.brand_id) as ytd_dealer_avg FROM(SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'    and mys_txn_outlet_score.shopper_id=mys_score.shopper_id  GROUP BY  mys_score.shopper_id) as q1,(SELECT @dcurRank := 1, @d_sequence:=1, @d_last_score:=0) dr) as dr1 ORDER BY ytd_dealer_avg DESC,mon ASC) as rrr1 on rr1.shopper_id=rrr1.sid Limit " + pageid  + "," + total + "";
	   //		String sql="select * from(select * from (SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month, mys_score.Outlet_name,mys_score.dealer_name,mys_score.region_name,mst_msm_result.visit_brand_name,mst_msm_result.outlet_region_name,mst_msm_result.outlet_city_name,mst_shopper_details.shopper_link_name,mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_visit_date,mst_shopper_details.sc_name as salesperson_name,mst_msm_result.city_tier,mst_msm_result.city_metro,mst_dealer_outlet.contact_person as regional_manager,monthname(mst_msm_result.scheduled_date)as monthname,month(mst_msm_result.scheduled_date)as months,IFNULL(SUM(mys_score.scored_points),0) as point_reached,IFNULL(SUM(mys_score.max_points),0) as maximum_points,mys_txn_outlet_score.monthly_score_percentage as monthly_outlet_score,mys_score.shopper_id as sid FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'    and mys_txn_outlet_score.shopper_id=mys_score.shopper_id    GROUP BY  mys_score.shopper_id) res1 left  join (SELECT substr((month+100),2) as mon,mys_txn_dealer_score.year as y1,month as m1,brand_id as b1,dealer_id as d1,monthly_score_percentage,monthly_score_percentage as dscore,@rank := CASE WHEN @year= year    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN @month= month    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN (@year := year  and @month := month  ) IS NOT NULL AND (@rankval := monthly_score_percentage) IS NOT NULL THEN 1 END AS rnk FROM mys_txn_dealer_score,  (SELECT @rank := 1, @year := 1,@month := 1,@brand_id := 1, @rankval := 0) AS x WHERE brand_id='"+brand_id+"' and year='"+year+"' ORDER BY mon asc,monthly_score_percentage desc) as res2  on      res1.dealer_id=res2.d1 and res1.month=res2.m1  GROUP by res1.shopper_id  ORDER by mon,rnk) as rr1 left join (SELECT shopper_id as sid,substr((month+100),2) as mon,IF(ytd_dealer_avg=@d_last_score,@dcurRank:=@dcurRank,@dcurRank:=@d_sequence) AS drank,@d_sequence:=@dcurRank+1 as sequence,@d_last_score:=ytd_dealer_avg AS dlast_score,dealer_id as did1 FROM(SELECT *,(SELECT ifnull(round(((sum(scored_point_721))/(sum(maximum_point_721)))*100,2),0) as ytd_dealer_avg FROM mys_txn_dealer_score WHERE dealer_id=q1.dealer_id AND (month<='"+month+"' or '"+month+"'='all') AND year=q1.year AND brand_id=q1.brand_id) as ytd_dealer_avg FROM(SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'    and mys_txn_outlet_score.shopper_id=mys_score.shopper_id  GROUP BY  mys_score.shopper_id) as q1,(SELECT @dcurRank := 1, @d_sequence:=1, @d_last_score:=0) dr) as dr1 ORDER BY ytd_dealer_avg DESC,mon ASC) as rrr1 on rr1.shopper_id=rrr1.sid Limit " + pageid  + "," + total + "";
	 		String sql="SELECT* from(select * from(select * from (SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month, mys_score.Outlet_name,mys_score.dealer_name,mys_score.region_name,mst_msm_result.visit_brand_name,mst_msm_result.outlet_region_name,mst_msm_result.outlet_city_name,mst_shopper_details.shopper_link_name,mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_visit_date,mst_shopper_details.sc_name as salesperson_name,mst_msm_result.city_tier,mst_msm_result.city_metro,mst_dealer_outlet.contact_person as regional_manager,monthname(mst_msm_result.scheduled_date)as monthname,month(mst_msm_result.scheduled_date)as months,IFNULL(SUM(mys_score.scored_points),0) as point_reached,IFNULL(SUM(mys_score.max_points),0) as maximum_points,mys_txn_outlet_score.monthly_score_percentage as monthly_outlet_score,mys_score.shopper_id as sid FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'     and mys_txn_outlet_score.shopper_id=mys_score.shopper_id    GROUP BY  mys_score.shopper_id) res1 left  join (SELECT substr((month+100),2) as mon,mys_txn_dealer_score.year as y1,month as m1,brand_id as b1,dealer_id as d1,monthly_score_percentage,monthly_score_percentage as dscore,@rank := CASE WHEN @year= year    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN @month= month    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN (@year := year  and @month := month  ) IS NOT NULL AND (@rankval := monthly_score_percentage) IS NOT NULL THEN 1 END AS rnk FROM mys_txn_dealer_score,  (SELECT @rank := 0, @year := 1,@month := 1,@brand_id := 1, @rankval := 0) AS x WHERE brand_id='"+brand_id+"' and year='"+year+"' ORDER BY mon asc,monthly_score_percentage desc) as res2  on  res1.dealer_id=res2.d1 and res1.month=res2.m1 left join (SELECT *,@rank := CASE WHEN @year= y2    AND (@rankval := ytdscore) IS NOT NULL THEN @rank + 1 WHEN @month= m2    AND (@rankval := ytdscore) IS NOT NULL THEN @rank + 1 WHEN (@year := y2  and @month := m2  ) IS NOT NULL AND (@rankval := ytdscore) IS NOT NULL THEN 1 END AS drnk FROM(SELECT *,(CASE WHEN seq=@m THEN @_sequence:=@_sequence+1 ELSE @currow:=@currow+1 END) as sequence FROM(SELECT *,@a:=m2-1 as seq from(SELECT s2.dealer_id as d2,month as m2,substr((month+100),2) as mon1,year as y2,brand_id as b2 ,(select round((sum(scored_point_721)/sum(maximum_point_721))*100,2) from mys_txn_dealer_score s1 WHERE s1.month<=s2.month and s1.year=s2.year and   s1.dealer_id=s2.dealer_id and s1.brand_id=s2.brand_id)as ytdscore FROM mys_txn_dealer_score s2 WHERE year='"+year+"' and brand_id='"+brand_id+"' and (substr((month+100),2)<='"+month+"' or '"+month+"'='all') and ( dealer_id='"+dealer+"' OR '"+dealer+"'='all') ORDER by mon1 asc, ytdscore DESC)as res1 ORDER by mon1 asc, ytdscore DESC) as res1,(SELECT @m := 0, @_sequence:=0,@currow:=0) r) as res2,(SELECT @rank := 0, @year := 1,@month := 1,@brand_id := 1, @rankval := 0) AS x) as res4 on res4.d2=res2.d1 and res4.m2=res2.m1  GROUP by res1.shopper_id  ORDER by res4.mon1,rnk) as rr1)as rrr1 Limit " + pageid  + "," + total + "";
	 	System.out.println("test sql=" + sql);
	    return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
	      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
	    	  MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
	    	  mvBean.setBrand_name(rs.getString("visit_brand_name"));
	    	  mvBean.setRegion_name(rs.getString("region_name"));
	    	  mvBean.setRegional_manager(rs.getString("regional_manager"));
	    	  mvBean.setMonthname(rs.getString("monthname"));
	    	  mvBean.setOutlet_city_name(rs.getString("outlet_city_name"));
	    	  mvBean.setDealer_name(rs.getString("dealer_name"));
	    	  mvBean.setOutlet_name(rs.getString("Outlet_name"));
	    	  mvBean.setShopper_link_name(rs.getString("shopper_link_name"));
	    	  mvBean.setVisit_date(rs.getString("shopper_link_visit_date"));
	    	  mvBean.setShopper_link_email(rs.getString("shopper_link_email"));
	    	  mvBean.setShopper_link_contact(rs.getString("shopper_link_contact"));
	    	 mvBean.setMetroandnonmetro(rs.getString("city_metro"));
	    	 try {
	    	 mvBean.setRank(rs.getInt("rnk"));
	    	 mvBean.setYtd_dealer_rank(rs.getInt("drnk"));
	    	 }catch (Exception e) {
	    		 mvBean.setYtd_dealer_rank(0);
	    		 mvBean.setRank(0);
			}
	    	  mvBean.setTier(rs.getString("city_tier"));
	    	  mvBean.setYear(year);
	    	  mvBean.setDealer_id(rs.getString("dealer_id"));
	    	  mvBean.setOutlet_id(rs.getString("outlet_id"));
	    	  mvBean.setMonth(rs.getString("month"));
	    	 // mvBean.setMonthly_dealer_avg(rs.getString("dscore"));
	    	  try {
		    	  if(rs.getString("dscore")==null || rs.getString("dscore").equals("null")) {
		    		  mvBean.setMonthly_dealer_avg1(0);
		    	  }else {
		    		  mvBean.setMonthly_dealer_avg1(rs.getDouble("dscore"));
		    	  }
		    	  }catch (Exception e) {
		    		  mvBean.setMonthly_dealer_avg1(0);
				}
	    	//  mvBean.setOutlet_score(rs.getString("monthly_outlet_score"));

	    	  if(rs.getString("monthly_outlet_score")==null || rs.getString("monthly_outlet_score").equals("null")) {
	    		  mvBean.setOutlet_score("0");
	    	  }else {
	    		  mvBean.setOutlet_score(rs.getString("monthly_outlet_score"));
	    	  }
	    	
	    	  
	    	  getnonoscShopperIdByOid(mvBean,rs.getString("outlet_id"),rs.getString("brand_id"),mvBean.getYear(),rs.getString("months"));
	    	  getoscShopperIdByOid(mvBean,rs.getString("dealer_id"),rs.getString("brand_id"),mvBean.getYear(),rs.getString("months"));
	    	  
	    	 
	    	 
	    	  
	    	  mvBean.setSc_name(rs.getString("salesperson_name"));
	    	//  getytddealeravg(mvBean,mvBean.getYear(),mvBean.getDealer_id(),mvBean.getMonth(),rs.getString("brand_id"));
	    	  //mvBean.setYtd_dealer_avg1(mvBean.getYtd_dealer_avg1());
	    	  mvBean.setYtd_dealer_avg1(rs.getDouble("ytdscore"));
	    	  getytdoutletavg(mvBean,mvBean.getMonth(),rs.getString("brand_id"),mvBean.getYear(),mvBean.getOutlet_id(),mvBean.getDealer_id());
	    	
	    	  mvBean.setYtd_outlet_avg(mvBean.getYtd_outlet_avg());
	    	 
	    	   // getmonthlydealeravg(mvBean,mvBean.getSk_shopper_id(),mvBean.getYear(),mvBean.getDealer_id(),mvBean.getOsc_shopper_id());
	    	
	    	 // getoutletscore(mvBean,mvBean.getSk_shopper_id(),mvBean.getYear(),mvBean.getOutlet_id());
	    	 
	    	  
	    	  List<MysteryShoppingVisitsBean> sectionscores=  getsectionScore(mvBean, rs.getString("shopper_id"),mvBean.getOsc_shopper_id());
	    	  mvBean.setSectionscore(sectionscores);
	    	  
	    	  List<MysteryShoppingVisitsBean> nonOscSubSectiobPoints=  getsectionScore1(mvBean, rs.getString("shopper_id"),mvBean.getOsc_shopper_id());
	    	  mvBean.setNonOscSubSectiobPoint(nonOscSubSectiobPoints);
	    	 
	    	  getdataaccuracyandcrmcompliance(mvBean, rs.getString("shopper_id"));
	    	  mvBean.setData_accuracy(mvBean.getData_accuracy());
	    	  mvBean.setCrm_compliance(mvBean.getCrm_compliance());
	    	  getPointsreachedandmaxpoints(mvBean, rs.getString("shopper_id"));
	    	  mvBean.setPoint_reached(mvBean.getPoint_reached());
	    	  mvBean.setMaximum_points(mvBean.getMaximum_points());
	    	  try {
	    	 // getmonthlydealerRank(mvBean,rs.getString("brand_id"),mvBean.getYear(),rs.getString("months"),rs.getString("dealer_id"));
	      }catch (Exception e) {
			// TODO: handle exception
		}
	    		List<MysteryShoppingVisitsBean> missedOpportunitesList=getMissedOpportunities(mvBean, rs.getString("shopper_id"));
				mvBean.setMissedOpportunitesList(missedOpportunitesList);
	        return mvBean;
	      }
	      
	    });
	}
	
	public List<MysteryShoppingVisitsBean> getdealer_performance1(MysteryShoppingVisitsBean mvBean, String year, String month, String region, String location, String brand_id, String dealer) {
				//		String sql="select * from(select * from (SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month, mys_score.Outlet_name,mys_score.dealer_name,mys_score.region_name,mst_msm_result.visit_brand_name,mst_msm_result.outlet_region_name,mst_msm_result.outlet_city_name,mst_shopper_details.shopper_link_name,mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_visit_date,mst_shopper_details.sc_name as salesperson_name,mst_msm_result.city_tier,mst_msm_result.city_metro,mst_dealer_outlet.contact_person as regional_manager,monthname(mst_msm_result.scheduled_date)as monthname,month(mst_msm_result.scheduled_date)as months,IFNULL(SUM(mys_score.scored_points),0) as point_reached,IFNULL(SUM(mys_score.max_points),0) as maximum_points,mys_txn_outlet_score.monthly_score_percentage as monthly_outlet_score,mys_score.shopper_id as sid FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'    and mys_txn_outlet_score.shopper_id=mys_score.shopper_id    GROUP BY  mys_score.shopper_id) res1 left  join (SELECT substr((month+100),2) as mon,mys_txn_dealer_score.year as y1,month as m1,brand_id as b1,dealer_id as d1,monthly_score_percentage,monthly_score_percentage as dscore,@rank := CASE WHEN @year= year    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN @month= month    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN (@year := year  and @month := month  ) IS NOT NULL AND (@rankval := monthly_score_percentage) IS NOT NULL THEN 1 END AS rnk FROM mys_txn_dealer_score,  (SELECT @rank := 1, @year := 1,@month := 1,@brand_id := 1, @rankval := 0) AS x WHERE brand_id='1' ORDER BY mon asc,monthly_score_percentage desc) as res2  on      res1.dealer_id=res2.d1 and res1.month=res2.m1  GROUP by res1.shopper_id  ORDER by mon,rnk) as rr1 left join (SELECT shopper_id as sid,substr((month+100),2) as mon,IF(ytd_dealer_avg=@d_last_score,@dcurRank:=@dcurRank,@dcurRank:=@d_sequence) AS drank,@d_sequence:=@dcurRank+1 as sequence,@d_last_score:=ytd_dealer_avg AS dlast_score,dealer_id as did1 FROM(SELECT *,(SELECT ifnull(round(((sum(scores_earned)+sum(scored_point_721))/(sum(maximum_scores)+sum(maximum_point_721)))*100,2),0) as ytd_dealer_avg FROM mys_txn_dealer_score WHERE dealer_id=q1.dealer_id AND month<="+month+" AND year=q1.year AND brand_id=q1.brand_id) as ytd_dealer_avg FROM(SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'    and mys_txn_outlet_score.shopper_id=mys_score.shopper_id  GROUP BY  mys_score.shopper_id) as q1,(SELECT @dcurRank := 1, @d_sequence:=1, @d_last_score:=0) dr) as dr1 ORDER BY ytd_dealer_avg DESC,mon ASC) as rrr1 on rr1.shopper_id=rrr1.sid ";
		
	//	String sql="select * from(select * from (SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month, mys_score.Outlet_name,mys_score.dealer_name,mys_score.region_name,mst_msm_result.visit_brand_name,mst_msm_result.outlet_region_name,mst_msm_result.outlet_city_name,mst_shopper_details.shopper_link_name,mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_visit_date,mst_shopper_details.sc_name as salesperson_name,mst_msm_result.city_tier,mst_msm_result.city_metro,mst_dealer_outlet.contact_person as regional_manager,monthname(mst_msm_result.scheduled_date)as monthname,month(mst_msm_result.scheduled_date)as months,IFNULL(SUM(mys_score.scored_points),0) as point_reached,IFNULL(SUM(mys_score.max_points),0) as maximum_points,mys_txn_outlet_score.monthly_score_percentage as monthly_outlet_score,mys_score.shopper_id as sid FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'    and mys_txn_outlet_score.shopper_id=mys_score.shopper_id    GROUP BY  mys_score.shopper_id) res1 left  join (SELECT substr((month+100),2) as mon,mys_txn_dealer_score.year as y1,month as m1,brand_id as b1,dealer_id as d1,monthly_score_percentage,monthly_score_percentage as dscore,@rank := CASE WHEN @year= year    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN @month= month    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN (@year := year  and @month := month  ) IS NOT NULL AND (@rankval := monthly_score_percentage) IS NOT NULL THEN 1 END AS rnk FROM mys_txn_dealer_score,  (SELECT @rank := 1, @year := 1,@month := 1,@brand_id := 1, @rankval := 0) AS x WHERE brand_id='"+brand_id+"' and year='"+year+"' ORDER BY mon asc,monthly_score_percentage desc) as res2  on      res1.dealer_id=res2.d1 and res1.month=res2.m1  GROUP by res1.shopper_id  ORDER by mon,rnk) as rr1 left join (SELECT shopper_id as sid,substr((month+100),2) as mon,IF(ytd_dealer_avg=@d_last_score,@dcurRank:=@dcurRank,@dcurRank:=@d_sequence) AS drank,@d_sequence:=@dcurRank+1 as sequence,@d_last_score:=ytd_dealer_avg AS dlast_score,dealer_id as did1 FROM(SELECT *,(SELECT ifnull(round(((sum(scored_point_721))/(sum(maximum_point_721)))*100,2),0) as ytd_dealer_avg FROM mys_txn_dealer_score WHERE dealer_id=q1.dealer_id AND (month<='"+month+"' or '"+month+"'='all') AND year=q1.year AND brand_id=q1.brand_id) as ytd_dealer_avg FROM(SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'    and mys_txn_outlet_score.shopper_id=mys_score.shopper_id  GROUP BY  mys_score.shopper_id) as q1,(SELECT @dcurRank := 1, @d_sequence:=1, @d_last_score:=0) dr) as dr1 ORDER BY ytd_dealer_avg DESC,mon ASC) as rrr1 on rr1.shopper_id=rrr1.sid ";
		String sql="SELECT* from(select * from(select * from (SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month, mys_score.Outlet_name,mys_score.dealer_name,mys_score.region_name,mst_msm_result.visit_brand_name,mst_msm_result.outlet_region_name,mst_msm_result.outlet_city_name,mst_shopper_details.shopper_link_name,mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_visit_date,mst_shopper_details.sc_name as salesperson_name,mst_msm_result.city_tier,mst_msm_result.city_metro,mst_dealer_outlet.contact_person as regional_manager,monthname(mst_msm_result.scheduled_date)as monthname,month(mst_msm_result.scheduled_date)as months,IFNULL(SUM(mys_score.scored_points),0) as point_reached,IFNULL(SUM(mys_score.max_points),0) as maximum_points,mys_txn_outlet_score.monthly_score_percentage as monthly_outlet_score,mys_score.shopper_id as sid FROM mys_score, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+location+"' OR '"+location+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'     and mys_txn_outlet_score.shopper_id=mys_score.shopper_id    GROUP BY  mys_score.shopper_id) res1 left  join (SELECT substr((month+100),2) as mon,mys_txn_dealer_score.year as y1,month as m1,brand_id as b1,dealer_id as d1,monthly_score_percentage,monthly_score_percentage as dscore,@rank := CASE WHEN @year= year    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN @month= month    AND (@rankval := monthly_score_percentage) IS NOT NULL THEN @rank + 1 WHEN (@year := year  and @month := month  ) IS NOT NULL AND (@rankval := monthly_score_percentage) IS NOT NULL THEN 1 END AS rnk FROM mys_txn_dealer_score,  (SELECT @rank := 0, @year := 1,@month := 1,@brand_id := 1, @rankval := 0) AS x WHERE brand_id='"+brand_id+"' and year='"+year+"' ORDER BY mon asc,monthly_score_percentage desc) as res2  on  res1.dealer_id=res2.d1 and res1.month=res2.m1 left join (SELECT *,@rank := CASE WHEN @year= y2    AND (@rankval := ytdscore) IS NOT NULL THEN @rank + 1 WHEN @month= m2    AND (@rankval := ytdscore) IS NOT NULL THEN @rank + 1 WHEN (@year := y2  and @month := m2  ) IS NOT NULL AND (@rankval := ytdscore) IS NOT NULL THEN 1 END AS drnk FROM(SELECT *,(CASE WHEN seq=@m THEN @_sequence:=@_sequence+1 ELSE @currow:=@currow+1 END) as sequence FROM(SELECT *,@a:=m2-1 as seq from(SELECT s2.dealer_id as d2,month as m2,substr((month+100),2) as mon1,year as y2,brand_id as b2 ,(select round((sum(scored_point_721)/sum(maximum_point_721))*100,2) from mys_txn_dealer_score s1 WHERE s1.month<=s2.month and s1.year=s2.year and   s1.dealer_id=s2.dealer_id and s1.brand_id=s2.brand_id)as ytdscore FROM mys_txn_dealer_score s2 WHERE year='"+year+"' and brand_id='"+brand_id+"' and (substr((month+100),2)<='"+month+"' or '"+month+"'='all') and ( dealer_id='"+dealer+"' OR '"+dealer+"'='all') ORDER by mon1 asc, ytdscore DESC)as res1 ORDER by mon1 asc, ytdscore DESC) as res1,(SELECT @m := 0, @_sequence:=0,@currow:=0) r) as res2,(SELECT @rank := 0, @year := 1,@month := 1,@brand_id := 1, @rankval := 0) AS x) as res4 on res4.d2=res2.d1 and res4.m2=res2.m1  GROUP by res1.shopper_id  ORDER by res4.mon1,rnk) as rr1)as rrr1";
		System.out.println("test sql1=" + sql);
		    return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
		      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
		    	  MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
		    	  mvBean.setBrand_name(rs.getString("visit_brand_name"));
		    	  mvBean.setRegion_name(rs.getString("region_name"));
		    	  mvBean.setRegional_manager(rs.getString("regional_manager"));
		    	  mvBean.setMonthname(rs.getString("monthname"));
		    	  mvBean.setOutlet_city_name(rs.getString("outlet_city_name"));
		    	  mvBean.setDealer_name(rs.getString("dealer_name"));
		    	  mvBean.setOutlet_name(rs.getString("Outlet_name"));
		    	  mvBean.setShopper_link_name(rs.getString("shopper_link_name"));
		    	  mvBean.setVisit_date(rs.getString("shopper_link_visit_date"));
		    	  mvBean.setShopper_link_email(rs.getString("shopper_link_email"));
		    	  mvBean.setShopper_link_contact(rs.getString("shopper_link_contact"));
		    	 mvBean.setMetroandnonmetro(rs.getString("city_metro"));
		    	 try {
	    	 mvBean.setRank(rs.getInt("rnk"));
	    	 mvBean.setYtd_dealer_rank(rs.getInt("drnk"));
	    	 }catch (Exception e) {
	    		 mvBean.setYtd_dealer_rank(0);
	    		 mvBean.setRank(0);
			}
		    	  mvBean.setTier(rs.getString("city_tier"));
		    	  mvBean.setYear(year);
		    	  mvBean.setDealer_id(rs.getString("dealer_id"));
		    	  mvBean.setOutlet_id(rs.getString("outlet_id"));
		    	  mvBean.setMonth(rs.getString("month"));
		    	  try {
		    	  if(rs.getString("dscore")==null || rs.getString("dscore").equals("null")) {
		    		  mvBean.setMonthly_dealer_avg1(0);
		    	  }else {
		    		  mvBean.setMonthly_dealer_avg1(rs.getDouble("dscore"));
		    	  }
		    	  }catch (Exception e) {
		    		  mvBean.setMonthly_dealer_avg1(0);
				}
		    	  
		    	  
			    	  if(rs.getString("monthly_outlet_score")==null || rs.getString("monthly_outlet_score").equals("null")) {
			    		  mvBean.setOutlet_score("0");
			    	  }else {
			    		  mvBean.setOutlet_score(rs.getString("monthly_outlet_score"));
			    	  }
			    	
		    	  
		    	  
		    	  getnonoscShopperIdByOid(mvBean,rs.getString("outlet_id"),rs.getString("brand_id"),mvBean.getYear(),rs.getString("months"));
		    	  getoscShopperIdByOid(mvBean,rs.getString("dealer_id"),rs.getString("brand_id"),mvBean.getYear(),rs.getString("months"));
		    	  
		    	 
		    	 
		    	  
		    	  mvBean.setSc_name(rs.getString("salesperson_name"));
		    	 // getytddealeravg(mvBean,mvBean.getYear(),mvBean.getDealer_id(),mvBean.getMonth(),rs.getString("brand_id"));
		    	  //mvBean.setYtd_dealer_avg1(mvBean.getYtd_dealer_avg1());
		    	  try {
		    	  if(rs.getString("ytdscore")==null || rs.getString("ytdscore").equals("null")) {
		    		  mvBean.setYtd_dealer_avg1(0);
		    	  }else {
		    		  mvBean.setYtd_dealer_avg1(rs.getDouble("ytdscore"));
		    	  }
		    	  }catch (Exception e) {
		    		  mvBean.setYtd_dealer_avg1(0);
				}
		    	 
		    	
		    	  getytdoutletavg(mvBean,mvBean.getMonth(),rs.getString("brand_id"),mvBean.getYear(),mvBean.getOutlet_id(),mvBean.getDealer_id());
		    	
		    	  mvBean.setYtd_outlet_avg(mvBean.getYtd_outlet_avg());
		    	 
		    	   // getmonthlydealeravg(mvBean,mvBean.getSk_shopper_id(),mvBean.getYear(),mvBean.getDealer_id(),mvBean.getOsc_shopper_id());
		    	
		    	 // getoutletscore(mvBean,mvBean.getSk_shopper_id(),mvBean.getYear(),mvBean.getOutlet_id());
		    	 
		    	  
		    	  List<MysteryShoppingVisitsBean> sectionscores=  getsectionScore(mvBean, rs.getString("shopper_id"),mvBean.getOsc_shopper_id());
		    	  mvBean.setSectionscore(sectionscores);
		    	  Gson gson = new Gson();
		    	  System.out.println(gson.toJson(sectionscores));
		    	  
		    	  
		    	  List<MysteryShoppingVisitsBean> nonOscSubSectiobPoints=  getsectionScore1(mvBean, rs.getString("shopper_id"),mvBean.getOsc_shopper_id());
		    	  mvBean.setNonOscSubSectiobPoint(nonOscSubSectiobPoints); 

		    	  getdataaccuracyandcrmcompliance(mvBean, rs.getString("shopper_id"));
		    	  mvBean.setData_accuracy(mvBean.getData_accuracy());
		    	  mvBean.setCrm_compliance(mvBean.getCrm_compliance());
		    	  getPointsreachedandmaxpoints(mvBean, rs.getString("shopper_id"));
		    	  mvBean.setPoint_reached(mvBean.getPoint_reached());
		    	  mvBean.setMaximum_points(mvBean.getMaximum_points());
		    	  try {
		    	// getmonthlydealerRank(mvBean,rs.getString("brand_id"),mvBean.getYear(),rs.getString("months"),rs.getString("dealer_id"));
		      }catch (Exception e) {
				// TODO: handle exception
			}
		    		List<MysteryShoppingVisitsBean> missedOpportunitesList=getMissedOpportunities(mvBean, rs.getString("shopper_id"));
					mvBean.setMissedOpportunitesList(missedOpportunitesList);
		        return mvBean;
		      }
		      
		    });
		}

	public MysteryShoppingVisitsBean getPointsreachedandmaxpoints(MysteryShoppingVisitsBean mvBean, String shopper_id) {
		String sql="(SELECT '11' as id,'Points Data' as name,sum(scored_points) as points_reached ,sum(max_points) as max_points,'0' as percentage1 FROM mys_score WHERE shopper_id='"+shopper_id+"')";
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				 mvBean.setPoint_reached(rs.getString("points_reached"));
		    	  mvBean.setMaximum_points(rs.getString("max_points"));
		    	  
				return mvBean;
			}
		});

}
	public MysteryShoppingVisitsBean getmonthlydealerRank(MysteryShoppingVisitsBean mvBean, String visit_brand_id, String year, String month, String dealer_id) {
			String sql="SELECT   mys_txn_dealer_score.*, @rank := CASE WHEN @year= year    AND (@rankval := ( ifnull(round(((sum(scored_point_721))/(sum(maximum_point_721)))*100,2),0)  )) IS NOT NULL THEN @rank + 1 WHEN (@year := year ) IS NOT NULL AND (@rankval := ( ifnull(round(((sum(scored_point_721))/(sum(maximum_point_721)))*100,2),0)  )) IS NOT NULL THEN 1 END AS monthly_dealer_rank FROM mys_txn_dealer_score,  (SELECT @rank := 0, @year := 1, @rankval := 0) AS x WHERE year='"+year+"' and month<="+month+" and brand_id='"+visit_brand_id+"' and dealer_id='"+dealer_id+"' ORDER BY year";
			System.out.println(sql);
			return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					mvBean.setYtd_dealer_rank(rs.getInt("monthly_dealer_rank"));
					return mvBean;
				}
			});

	}
		public MysteryShoppingVisitsBean getdataaccuracyandcrmcompliance(MysteryShoppingVisitsBean mvBean, String sk_shopper_id) {
			try {
			String sql="select data_accuracy,crm_compliance from(SELECT mys_txn_answers.select_option_text as data_accuracy  FROM `mys_txn_answers` WHERE `shopper_id`='"+sk_shopper_id+"' and `standard_number`='crm3')as res1 JOIN (SELECT mys_txn_answers.select_option_text as crm_compliance  FROM `mys_txn_answers` WHERE `shopper_id`='"+sk_shopper_id+"' and `standard_number`='crm2')res2";
			//System.out.println(sql);
			return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					mvBean.setData_accuracy(rs.getString("data_accuracy"));
					mvBean.setCrm_compliance(rs.getString("crm_compliance"));
					return mvBean;
				}
			});
			}catch (Exception e) {
				// TODO: handle exception
			}
			return mvBean;

	}
		public List<MysteryShoppingVisitsBean> getsectionScore(MysteryShoppingVisitsBean mvBean, String sk_shopper_id, String osc_shopper_id) {
			//String sql="(SELECT section_id,section_name,sum(scored_points) as points_reached,sum(max_points)as max_points,round((sum(scored_points)/sum(max_points))*100,2) as percentage_score,mys_score.shopper_id FROM mys_score WHERE shopper_id='"+sk_shopper_id+"'  GROUP BY section_id ORDER BY mys_score.section_id ASC ) UNION (SELECT ifnull(GROUP_CONCAT(DISTINCT section_id),'4,5'),'Osc Overall' as name,sum(scored_points),sum(max_points),round((sum(scored_points)/sum(max_points))*100,2) as percentage_score,mys_score.shopper_id FROM mys_score WHERE shopper_id='"+osc_shopper_id+"')";
			String sql="(select res2.sk_section_id,res2.section_name,ifnull(points_reached,0) as points_reached ,ifnull(max_points,0) as max_points ,ifnull(percentage_score,0) as percentage_score,shopper_id from (SELECT section_id,section_name,sum(scored_points) as points_reached,sum(max_points)as max_points,round((sum(scored_points)/sum(max_points))*100,2) as percentage_score,mys_score.shopper_id FROM mys_score WHERE shopper_id='"+sk_shopper_id+"'  GROUP BY section_id ORDER BY mys_score.section_id ASC )as r1 RIGHT join (select * from mst_section where sk_section_id in (1,2,3))as res2 on  r1.section_id=res2.sk_section_id ) UNION (SELECT ifnull(GROUP_CONCAT(DISTINCT section_id),'4,5'),'Osc Overall' as name,ifnull(sum(scored_points),0),ifnull(sum(max_points),0),round((ifnull(sum(scored_points),0)/ifnull(sum(max_points),0))*100,2) as percentage_score,mys_score.shopper_id FROM mys_score WHERE shopper_id='"+osc_shopper_id+"')";
			System.out.println("section score="+sql);
			return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					MysteryShoppingVisitsBean mvBean=new MysteryShoppingVisitsBean();
					
					mvBean.setSectionScores(rs.getString("percentage_score"));
					if(rs.getString("percentage_score")==null) {
						mvBean.setSectionScores("0");
					}
					mvBean.setSection_id(rs.getString("sk_section_id"));
					  List<MysteryShoppingVisitsBean> subsectionScores=  getsubsectionScores(mvBean, rs.getString("sk_section_id"),rs.getString("shopper_id"));
			    	  mvBean.setSubsectionscore(subsectionScores);
			    	  
			    	 
					return mvBean;
				}
			});

	}
		public List<MysteryShoppingVisitsBean> getsectionScore1(MysteryShoppingVisitsBean mvBean, String sk_shopper_id, String osc_shopper_id) {
			//String sql="(SELECT section_id,section_name,sum(scored_points) as points_reached,sum(max_points)as max_points,round((sum(scored_points)/sum(max_points))*100,2) as percentage_score,mys_score.shopper_id FROM mys_score WHERE shopper_id='"+sk_shopper_id+"'  GROUP BY section_id ORDER BY mys_score.section_id ASC ) UNION (SELECT ifnull(GROUP_CONCAT(DISTINCT section_id),'4,5'),'Osc Overall' as name,sum(scored_points),sum(max_points),round((sum(scored_points)/sum(max_points))*100,2) as percentage_score,mys_score.shopper_id FROM mys_score WHERE shopper_id='"+osc_shopper_id+"')";
			String sql="(select res2.sk_section_id,res2.section_name,ifnull(points_reached,0) as points_reached ,ifnull(max_points,0) as max_points ,ifnull(percentage_score,0) as percentage_score,shopper_id from (SELECT section_id,section_name,sum(scored_points) as points_reached,sum(max_points)as max_points,round((sum(scored_points)/sum(max_points))*100,2) as percentage_score,mys_score.shopper_id FROM mys_score WHERE shopper_id='"+sk_shopper_id+"'  GROUP BY section_id ORDER BY mys_score.section_id ASC )as r1 RIGHT join (select * from mst_section where sk_section_id in (2,3))as res2 on  r1.section_id=res2.sk_section_id ) ";
			System.out.println("section score="+sql);
			return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					MysteryShoppingVisitsBean mvBean=new MysteryShoppingVisitsBean();
					
					mvBean.setSectionScores(rs.getString("percentage_score"));
					if(rs.getString("percentage_score")==null) {
						mvBean.setSectionScores("0");
					}
					mvBean.setSection_id(rs.getString("sk_section_id"));
					  List<MysteryShoppingVisitsBean> subsectionScores=  getsubsectionScores(mvBean, rs.getString("sk_section_id"),rs.getString("shopper_id"));
			    	  mvBean.setSubsectionscore(subsectionScores);
			    	  
			    	 
					return mvBean;
				}
			});

	}
		public List<MysteryShoppingVisitsBean> getsubsectionScores(MysteryShoppingVisitsBean mvBean, String section_id, String shopper_id) {
			String sql="(SELECT * FROM(SELECT '0' as osc_flag,mst_subsection.sk_subsection_id,mst_subsection.subsection_name,mst_subsection.section_id as subsection FROM mst_subsection WHERE mst_subsection.section_id IN ("+section_id+")) as res1 LEFT JOIN (SELECT section_id,section_name,subsection_id,subsection_name,IFNULL(sum(scored_points),0) as scored_points ,IFNULL(sum(max_points),0) as max_points,IFNULL(round((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100,2),0) as percentage_score FROM mys_score WHERE shopper_id='"+shopper_id+"'  GROUP BY subsection_id ) as res2 ON res1.sk_subsection_id=res2.subsection_id AND res1.subsection=res2.section_id ORDER BY subsection)";
			System.out.println("sub section score="+sql);
			return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					MysteryShoppingVisitsBean mvBean=new MysteryShoppingVisitsBean();
					mvBean.setSubsection_name(rs.getString("subsection_name"));
					mvBean.setSubsectionScores(rs.getString("percentage_score"));
					mvBean.setMaximum_points(rs.getString("max_points"));
					mvBean.setObtained_points(rs.getString("scored_points"));
					if(rs.getString("percentage_score")==null) {
						mvBean.setSubsectionScores("0");
					}
					
					return mvBean;
				}
			});

		}
		private MysteryShoppingVisitsBean getoscShopperIdByOid(MysteryShoppingVisitsBean mvBean, String did, String brand,
				String year, String month) {
			try {
					System.out.println("select * from mst_shopper_details where dealer_id='" + did 
							+ "' and year='" + year + "' and month(visit_date)='" + month
							+ "' and brand_id='"+brand+"' and mst_shopper_details.mode_of_contact='Online Sales Channel' and visit_status='published';");
					return template.queryForObject(
							"select * from mst_shopper_details where dealer_id='" + did
							+ "' and year='" + year + "' and month(visit_date)='" + month
							+ "' and brand_id='"+brand+"' and mst_shopper_details.mode_of_contact='Online Sales Channel' and visit_status='published';",
							new RowMapper<MysteryShoppingVisitsBean>() {
								public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
									mvBean.setOsc_shopper_id(rs.getString("sk_shopper_id"));		
									System.out.println("OSC shopper ID in dao====="+rs.getString("sk_shopper_id"));
									return mvBean;
								}
							});
				} catch (Exception e) {
				mvBean.setOsc_shopper_id("0");
				}
							return mvBean;
						}
			

		protected MysteryShoppingVisitsBean getnonoscShopperIdByOid(MysteryShoppingVisitsBean mvBean, String outlet_id, String brand, String year, String month) {
			try {
					System.out.println("select * from mst_shopper_details where (mst_shopper_details.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all') and year='" + year + "' and (month(mst_shopper_details.visit_date)='"+month+"' OR '"+month+"'='all') and (mst_shopper_details.brand_id='"+brand+"' OR '"+brand+"'='all') and mst_shopper_details.mode_of_contact!='Online Sales Channel' and visit_status='published';");
					return template.queryForObject(
							"select * from mst_shopper_details where (mst_shopper_details.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all') and year='" + year + "' and (month(mst_shopper_details.visit_date)='"+month+"' OR '"+month+"'='all') and (mst_shopper_details.brand_id='"+brand+"' OR '"+brand+"'='all') and mst_shopper_details.mode_of_contact!='Online Sales Channel' and visit_status='published';",
							new RowMapper<MysteryShoppingVisitsBean>() {
								public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
									mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));		
									System.out.println("non OSC shopper ID in dao====="+rs.getString("sk_shopper_id"));
									return mvBean;
								}
							});
				} catch (Exception e) {
				mvBean.setSk_shopper_id("0");
				}
							return mvBean;
						}
		protected MysteryShoppingVisitsBean getoutletscore(MysteryShoppingVisitsBean mvBean, String sid, String year, String outlet_id) {
			MysteryShoppingVisitsBean mvBean1=new MysteryShoppingVisitsBean();
			mvBean1=hDao.getScoreWeightage(mvBean,year);
			String pe_weightage=mvBean1.getPe_weightage();
			String ct_weightage=mvBean1.getCt_weightage();
			String osc_weightage=mvBean1.getOsc_weightage();
			
			String sql="SELECT round((sec2_sp+sec3_sp)/(sec2_mp+sec3_mp)*100,2) as outlet_score FROM(SELECT(SELECT SUM(scored_points)*"+pe_weightage+" from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all')  and status='active' and mys_score.section_id='2' and osc_flag!=1)as sec2_sp,(SELECT SUM(scored_points)*"+ct_weightage+" from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all')  and status='active' and mys_score.section_id='3' and osc_flag!=1)as sec3_sp,(SELECT SUM(max_points)*"+pe_weightage+" from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all')  and status='active' and mys_score.section_id='2' and osc_flag!=1)as sec2_mp,(SELECT SUM(max_points)*"+ct_weightage+" from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all')  and status='active' and mys_score.section_id='3' and  osc_flag!=1)as sec3_mp )res1";
			//System.out.println(sql);
			return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					mvBean.setOutlet_score(rs.getString("outlet_score"));
					return mvBean;
				}
			});

	}
		private MysteryShoppingVisitsBean getmonthlydealeravg(MysteryShoppingVisitsBean mvBean, String sid, String year,
				String dealer_id, String osc_shopper_id) {
			MysteryShoppingVisitsBean mvBean1=new MysteryShoppingVisitsBean();
	        mvBean1=hDao.getScoreWeightage(mvBean,year);
			String pe_weightage=mvBean1.getPe_weightage();
			String ct_weightage=mvBean1.getCt_weightage();
			String osc_weightage=mvBean1.getOsc_weightage();
			
			String sql="SELECT round(((sec2_md_sp+sec3_md_sp+IFNULL(osc_md_sp,0))/(sec2_md_mp+sec3_md_mp+IFNULL(osc_md_mp,0)))*100,2) as monthly_dealer_average FROM(SELECT(SELECT SUM(scored_points)*"+pe_weightage+" from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='all') and mys_score.section_id='2'  and status='active' and osc_flag!=1)as sec2_md_sp,(SELECT SUM(scored_points)*"+ct_weightage+" from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='all') and mys_score.section_id='3'  and status='active' and osc_flag!=1)as sec3_md_sp,(SELECT SUM(mys_score.scored_points)*"+osc_weightage+" from mys_score WHERE  shopper_id='"+osc_shopper_id+"' and mys_score.year='"+year+"' AND (  mys_score.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='all')  and status='active'  and osc_flag=1)as osc_md_sp,(SELECT SUM(max_points)*"+pe_weightage+" from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='all') and mys_score.section_id='2'  and status='active' and osc_flag!=1)as sec2_md_mp,(SELECT SUM(max_points)*"+ct_weightage+" from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='all') and mys_score.section_id='3'  and status='active' and osc_flag!=1)as sec3_md_mp,(SELECT SUM(mys_score.max_points)*"+osc_weightage+" from mys_score WHERE  shopper_id='"+osc_shopper_id+"' and mys_score.year='"+year+"' AND (  mys_score.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='all')  and status='active' and osc_flag=1)as osc_md_mp)res1";
			//String sql="";
			System.out.println(sql);
			return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					mvBean.setMonthly_dealer_avg(rs.getString("monthly_dealer_average"));
					return mvBean;
				}
			});

	
}
	protected MysteryShoppingVisitsBean getytdoutletavg(MysteryShoppingVisitsBean mvBean, String month, String brand_id, String year, String outlet_id, String did) {
		//String sql="SELECT round((sec2_sp+sec3_sp)/(sec2_mp+sec3_mp)*100,2) as ytd_outlet_average FROM(SELECT(SELECT SUM(scored_points)*0.7 from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all')  and status='active' and mys_score.section_id='2' and osc_flag!=1)as sec2_sp,(SELECT SUM(scored_points)*0.2 from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all')  and status='active' and mys_score.section_id='3' and osc_flag!=1)as sec3_sp,(SELECT SUM(max_points)*0.7 from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all')  and status='active' and mys_score.section_id='2' and osc_flag!=1)as sec2_mp,(SELECT SUM(max_points)*0.2 from mys_score WHERE  shopper_id='"+sid+"' and mys_score.year='"+year+"' AND (  mys_score.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all')  and status='active' and mys_score.section_id='3' and  osc_flag!=1)as sec3_mp )res1";
		String sql="SELECT ifnull(round((sum(scored_point)/sum(maximum_point))*100,2),0)as ytd_outlet_average FROM `mys_txn_outlet_score` WHERE  dealer_id='"+did+"' and  outlet_id='"+outlet_id+"' AND osc_flag=0 AND month<="+month+" AND year='"+year+"' AND brand_id='"+brand_id+"'";
		System.out.println("ytd outket avg===="+sql);
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mvBean.setYtd_outlet_avg(rs.getString("ytd_outlet_average"));
				return mvBean;
			}
		});

}
	protected MysteryShoppingVisitsBean getytddealeravg(MysteryShoppingVisitsBean mvBean,String year, String dealer_id,String month, String brand_id) {
		//String sql="SELECT round(((sec2_md_sp+sec3_md_sp+IFNULL(osc_md_sp,0))/(sec2_md_mp+sec3_md_mp+IFNULL(osc_md_mp,0)))*100,2) as ytd_dealer_average FROM (SELECT(SELECT SUM(scored_points)*0.7 from mys_score WHERE  shopper_id='"+sid+"' and status='active'and mys_score.section_id='2')as sec2_md_sp,(SELECT SUM(scored_points)*0.2 from mys_score WHERE  shopper_id='"+sid+"'  and status='active' and mys_score.section_id='3')as sec3_md_sp,(SELECT SUM(mys_score.scored_points)*0.1 from mys_score WHERE  shopper_id='"+osc_shopper_id+"' and status='active')as osc_md_sp ,(SELECT SUM(max_points)*0.7 from mys_score WHERE  shopper_id='"+sid+"'  and status='active' and mys_score.section_id='2')as sec2_md_mp,(SELECT SUM(max_points)*0.2 from mys_score WHERE  shopper_id='"+sid+"'  and status='active' and mys_score.section_id='3' )as sec3_md_mp,(SELECT SUM(mys_score.max_points)*0.1 from mys_score WHERE  shopper_id='"+osc_shopper_id+"' and status='active')as osc_md_mp)res1";
			//	String sql="SELECT round(((sum(`scores_earned`)+sum(scored_point_721))/(sum(`maximum_scores`)+sum(maximum_point_721)))*100,2) as ytd_dealer_avg FROM `mys_txn_dealer_score` WHERE dealer_id='"+dealer_id+"' AND month<="+month+" AND year='"+year+"' AND brand_id='"+brand_id+"'";

		String sql="SELECT ifnull(round(((sum(scored_point_721))/(sum(maximum_point_721)))*100,2),0) as ytd_dealer_avg FROM `mys_txn_dealer_score` WHERE dealer_id='"+dealer_id+"' AND month<="+month+" AND year='"+year+"' AND brand_id='"+brand_id+"'";
		System.out.println("ytd dealer average=="+sql);
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mvBean.setYtd_dealer_avg1(rs.getDouble("ytd_dealer_avg"));
				return mvBean;
			}
		});
	}
	public List<MysteryShoppingVisitsBean> getsectionSubsection(MysteryShoppingVisitsBean mvBean) {
		String sql="  (SELECT mst_section.sk_section_id,mst_section.section_name,GROUP_CONCAT(mst_subsection.sk_subsection_id)as sk_subsection_id,GROUP_CONCAT(mst_subsection.subsection_name SEPARATOR '#') as subsection_name FROM `mst_section`,mst_subsection WHERE mst_subsection.section_id=mst_section.sk_section_id and mst_section.section_status='active' and mst_subsection.subsection_status='active' and sk_section_id in(1,2,3) GROUP by mst_section.sk_section_id) union (SELECT mst_section.sk_section_id,'OSC' as section_name,GROUP_CONCAT(mst_subsection.sk_subsection_id)as sk_subsection_id,GROUP_CONCAT(mst_subsection.subsection_name SEPARATOR '#') as subsection_name FROM `mst_section`,mst_subsection WHERE mst_subsection.section_id=mst_section.sk_section_id and mst_section.section_status='active' and mst_subsection.subsection_status='active' and sk_section_id in(4,5) ORDER BY mst_subsection.sk_subsection_id) ";
		return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				MysteryShoppingVisitsBean mvBean=new MysteryShoppingVisitsBean();
				mvBean.setSection_name(rs.getString("section_name"));
				mvBean.setSubsection_name(rs.getString("subsection_name"));
				return mvBean;
			}
		});
	}
	public List<MysteryShoppingVisitsBean> getsectionSubsection1(MysteryShoppingVisitsBean mvBean) {
		String sql="  (SELECT mst_section.sk_section_id,mst_section.section_name,GROUP_CONCAT(mst_subsection.sk_subsection_id)as sk_subsection_id,GROUP_CONCAT(mst_subsection.subsection_name SEPARATOR '#') as subsection_name FROM `mst_section`,mst_subsection WHERE mst_subsection.section_id=mst_section.sk_section_id and mst_section.section_status='active' and mst_subsection.subsection_status='active' and sk_section_id in(2,3) GROUP by mst_section.sk_section_id) ";
		return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				MysteryShoppingVisitsBean mvBean=new MysteryShoppingVisitsBean();
				mvBean.setSection_name(rs.getString("section_name"));
				mvBean.setSubsection_name(rs.getString("subsection_name"));
				return mvBean;
			}
		});
	}
	public List<GraphBean> getMonthsByMode(GraphBean gBean) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		// int year = 2019;
		 String sql = "";
		 if(gBean.getBrand_id()==null) {
				  sql = "SELECT distinct month(visit_date) as month, monthName(visit_date) as monthName FROM `mst_shopper_details` where mode_of_contact='"
						+ gBean.getMode_of_contact() + "' and year='" + year
						+ "' and visit_status='published' order by month(visit_date) ASC";
		 }else {
				  sql = "SELECT distinct month(visit_date) as month, monthName(visit_date) as monthName FROM `mst_shopper_details` where mode_of_contact='"
						+ gBean.getMode_of_contact() + "' and brand_id='" + gBean.getBrand_id() + "' and year='" + year
						+ "' and visit_status='published' order by month(visit_date) ASC";
		 }
	
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setMonth_name(rs.getString("monthName"));
				return gBean;
			}
		});
	}
	
	public List<GraphBean> getBrands(GraphBean gBean) {
			String sql = "SELECT * FROM mst_brand WHERE brand_status='active'";

			System.out.println(sql);
			return template.query(sql, new RowMapper<GraphBean>() {
				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
					GraphBean gBean = new GraphBean();
					gBean.setBrand_id(rs.getString("sk_brand_id"));
					gBean.setBrand_name(rs.getString("brand_name"));
					return gBean;
				}
			});
		}
	public List<GraphBean> getMonthsByMode(GraphBean gBean, String bid) {
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		 //int year = 2019;
			String sql = "SELECT distinct month(visit_date) as month, monthName(visit_date) as monthName FROM `mst_shopper_details` where mode_of_contact='"
					+ gBean.getMode_of_contact() + "' and brand_id='" + bid + "' and year='" + year
					+ "' and visit_status='published' order by month(visit_date) ASC";
			System.out.println(sql);
			return template.query(sql, new RowMapper<GraphBean>() {
				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
					GraphBean gBean = new GraphBean();
					gBean.setMonth(rs.getString("month"));
					gBean.setMonth_name(rs.getString("monthName"));
					return gBean;
				}
			});
		}
		
	public List<GraphBean> getDealers(GraphBean gBean, String bid, String region, String dealers) {

System.out.println(dealers+"=====");
		try {
		if(gBean.getRole_id().contentEquals("2") || gBean.getRole_id().contentEquals("4") || gBean.getRole_id().contentEquals("5")) {
			if(gBean.getRegion_id()=="" || gBean.getRegion_id()==null) {
				
			region="all";
			
			}else {
				region=gBean.getRegion_id();	
			}
			dealers="all";
		}else if(gBean.getRole_id().contentEquals("6") ){
			region=region;
			dealers="all";
		}else {
			region=region;
			dealers=dealers;
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		String sql = "SELECT * FROM `mst_dealer`,mst_dealer_outlet WHERE (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='all')  AND mst_dealer_outlet.`brand_id`='"+bid+"' AND mst_dealer_outlet.dealer_id=mst_dealer.sk_dealer_id and  `dealer_status`='active' and (sk_dealer_id='"+dealers+"' or '"+dealers+"'='all')  GROUP by mst_dealer.sk_dealer_id;";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setDealer_id(rs.getString("dealer_id"));
				gBean.setDealer_name(rs.getString("dealer_name"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getDealers1(GraphBean gBean, String bid, String region, String dealers) {

		System.out.println(dealers+"=====");
				try {
				if(dealers=="" || dealers==null || dealers.isEmpty()) {
					dealers="all";
				}else {
					dealers=dealers;
				}
				if(region=="") {
					region="all";
				}else {
					region=region;
				}
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				String sql = "SELECT * FROM `mst_dealer`,mst_dealer_outlet WHERE (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='all')  AND mst_dealer_outlet.`brand_id`='"+bid+"' AND mst_dealer_outlet.dealer_id=mst_dealer.sk_dealer_id and  `dealer_status`='active' and (sk_dealer_id='"+dealers+"' or '"+dealers+"'='all')  GROUP by mst_dealer.sk_dealer_id;";
				System.out.println(sql);
				return template.query(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setDealer_id(rs.getString("dealer_id"));
						gBean.setDealer_name(rs.getString("dealer_name"));
						return gBean;
					}
				});
			}
	public List<GraphBean> getDealersforOverp(GraphBean gBean, String bid, String rid) {
		String sql = "SELECT * FROM `mst_dealer`,mst_dealer_outlet WHERE (mst_dealer_outlet.region_id='"+rid+"' OR '"+rid+"'='all')  AND mst_dealer_outlet.`brand_id`='"+bid+"' AND mst_dealer_outlet.dealer_id=mst_dealer.sk_dealer_id and  `dealer_status`='active'";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setDealer_id(rs.getString("sk_dealer_id"));
				gBean.setDealer_name(rs.getString("dealer_name"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getoutlets(GraphBean gBean, String did, String bid, String rid) {
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		
		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
		else  if((did=="" || did==null) &&(rid=="" || rid==null))
		{
			did="all";
			rid="all";
		}
		
		else  if((did!="") &&(rid=="" || rid==null))
		    {
				did= did;
				rid="all";	
			}
		
		else  if((did=="" || did==null) &&(rid!=""))
	    {
			did= "all";
			rid=rid;	
		}
	    else 
	    {
			did= did;
			rid=rid;	
		} 
		
		String sql = "SELECT * FROM `mst_dealer_outlet` WHERE `outlet_status`='active'  and (`dealer_id`='"+did+"' or '"+did+"'='All') and (region_id='"+rid+"' or '"+rid+"' ='All') and (brand_id='"+bid+"' or '"+bid+"' ='All')";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setOutlet_id(rs.getString("sk_outlet_id"));
				gBean.setOutlet_name(rs.getString("outlet_name"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getoutletsforOverp(GraphBean gBean, String did, String bid) {
		String sql = "SELECT * FROM `mst_dealer_outlet` WHERE `outlet_status`='active' and `brand_id`='"+bid+"' and `dealer_id`='"+did+"'";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setOutlet_id(rs.getString("sk_outlet_id"));
				gBean.setOutlet_name(rs.getString("outlet_name"));
				return gBean;
			}
		});
	}
	/***********manoj d overallPerformance Starts**********/
	public List<GraphBean> getOverallPerformanceSectionWiseScore(QuestionnaireBean qBean, GraphBean gBean, String section_id,String mid,String oid,String bid,String did,String rid) {
		//final String brand = gBean.getBrand_id();
		final String outlets = qBean.getOutlets();
		String brand= bid;
		String outlet= oid;
		String dealer= did;
		String region= rid;
		String month= mid;
		System.out.println("select * from mst_section where sk_section_id='" + section_id + ";");
		return template.query("select * from mst_section where sk_section_id='" + section_id + "';",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setBrand(brand);
						gBean.setOutlets(outlets);
						gBean.setSection_name(rs.getString("section_name"));
						gBean.setSection_id(rs.getString("sk_section_id"));
						List<GraphBean> data = getsectionsscoreoverallperformance(gBean,brand,outlet,dealer,region,month);
						gBean.setSectionScore(data);
						GraphBean data1 = getSectionScoreoverallperformance(gBean,brand,outlet,dealer,region,month);
						gBean.setPercentage(data1.getPercentage());

						return gBean;
					}
				});
	}
	public List<GraphBean> getsectionsscoreoverallperformance(GraphBean gBean,String brand,String outlet,String dealer,String region,String month) {
		
		
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
		//int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		//System.out.println("section 2 process");
		//System.out.println("SELECT mst_shopper_details.visit_date,mys_score.* FROM `mys_score`, mst_shopper_details where (mys_score.outlet_id='"+oid+"' or '"+oid+"'='all') AND (mys_score.dealer_id='"+did+"' or '"+did+"'='all') AND (mys_score.region_id='"+rid+"' or '"+rid+"'='all') AND mys_score.shopper_id=mst_shopper_details.sk_shopper_id AND (month(visit_date)='"+mid+"' or '"+mid+"'='all') AND mode_of_contact!='Online Sales Channel' AND mys_score.section_id='"+section_id+"'  AND mys_score.status='active' GROUP BY mys_score.subsection_id;");
		 System.out.println("select sk_subsection_id,mst_subsection.subsection_name,round(AVG(mys_score.percentage),2) as average from mys_score,mst_subsection where mys_score.section_id='"
				+ gBean.getSection_id()
				+ "' and subsection_id=sk_subsection_id and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published'   AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) GROUP BY mys_score.subsection_id");
		return template.query("select sk_subsection_id,mst_subsection.subsection_name,round(AVG(mys_score.percentage),2) as average from mys_score,mst_subsection where mys_score.section_id='"
				+ gBean.getSection_id()
				+ "' and subsection_id=sk_subsection_id and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published'  AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) GROUP BY mys_score.subsection_id;",
				
				
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setSubsection_name(rs.getString("subsection_name"));
						gBean.setSubSection_id(rs.getString("sk_subsection_id"));
						gBean.setPercentage(rs.getString("average"));
						System.out.println(("Section Average======="+rs.getString("average")+rs.getString("subsection_name")));
						

						return gBean;
					}
				});
	}

	public List<GraphBean> getMonths(GraphBean gBean) {
		//int year=2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String brand=gBean.getBrand_id();
		if(brand==null){
			
			brand="all";
		}
		String sql = "SELECT distinct month(visit_date) as month, monthName(visit_date) as monthName FROM `mst_shopper_details` where mode_of_contact!='Online Sales Channel' and (brand_id='" + brand + "' or '" + brand + "'='all') and year='" + year
				+ "' and visit_status='published' order by month(visit_date) ASC";
		
		System.out.println("manoj d brand ");
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setMonth_name(rs.getString("monthName"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getMonthsNames(GraphBean gBean, String bid) {
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		// int year = 2019;
			String sql = "SELECT distinct month(visit_date) as month, monthName(visit_date) as monthName FROM `mst_shopper_details` where mode_of_contact!='Online Sales Channel' and brand_id='" + bid + "' and year='" + year
					+ "' and visit_status='published' order by month(visit_date) ASC";
			return template.query(sql, new RowMapper<GraphBean>() {
				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
					GraphBean gBean = new GraphBean();
					gBean.setMonth(rs.getString("month"));
					gBean.setMonth_name(rs.getString("monthName"));
					return gBean;
				}
			});
	}
	public List<GraphBean> getautoresponseemailgraph(GraphBean gBean, String did, String bid,
			String rid,String oid,String month) {
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }
	    
	 if(bid==null || bid=="") {
		 bid="all";
	 }
	 if(oid==null || oid=="") {
		 oid="all";
	 }
	 if(month==null || month=="") {
		 month="all";
	 }
		
		
		    int  year;
		    String standard_number="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="1a.6";
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number="1.2";
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	standard_number="1a.6";
		    }
		   
		 String sql = "SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null  THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
			System.out.println("getautoresponseemailgraph=="+sql);
			return template.query(sql, new RowMapper<GraphBean>() {
				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
					GraphBean gBean = new GraphBean();
					gBean.setMonth(rs.getString("month"));
					gBean.setYear(String.valueOf(year));
					gBean.setYesCount(rs.getString("yespercentage"));
					gBean.setNoCount(rs.getString("nopercentage"));
					return gBean;
				}
			});
		
	}
	public List<GraphBean> getdealershipgraphresponse(GraphBean gBean, String did, String bid, String rid,
			String oid, String month) {
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");

		    if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
		
		
//		if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(bid==null || bid=="") {
			 bid="all";
		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 
		   int  year;
		   String standard_number="";
		   String sql="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="1A.7";
			  sql = "SELECT mon,month,totalcount,Within4hoursaftersendingtherequest,After4hoursonthesameworkingday,Withinnextworkingdayaftersendingtherequest,Nopersonalresponseuntilendofnextworkingday, round(((Within4hoursaftersendingtherequest / totalcount)*100), 2)as Within4hoursaftersendingtherequestp,round(((After4hoursonthesameworkingday / totalcount)*100), 2)as After4hoursonthesameworkingdayp,round(((Withinnextworkingdayaftersendingtherequest / totalcount)*100), 2)as Withinnextworkingdayaftersendingtherequestp,round(((Nopersonalresponseuntilendofnextworkingday / totalcount)*100), 2)as Nopersonalresponseuntilendofnextworkingdayp FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(CASE WHEN mys_txn_answers.select_option_text is not null and select_option_text in ('a) Within 4 hours after sending the request','b) After 4 hours on the same working day','c) Within the next working day after sending the request', 'd) No personal response until end of next working day') then 1 else null  end)as totalcount, count(CASE WHEN mys_txn_answers.select_option_text ='a) Within 4 hours after sending the request' and select_option_text is not null THEN 1 ELSE null END) AS Within4hoursaftersendingtherequest, count(CASE WHEN mys_txn_answers.select_option_text = 'b) After 4 hours on the same working day' and select_option_text is not null THEN 1 ELSE null  END) AS After4hoursonthesameworkingday, count(CASE WHEN mys_txn_answers.select_option_text = 'c) Within the next working day after sending the request' and select_option_text is not null THEN 1 ELSE null  END) AS Withinnextworkingdayaftersendingtherequest, count( CASE WHEN mys_txn_answers.select_option_text = 'd) No personal response until end of next working day' and select_option_text is not null  THEN 1 ELSE null  END) AS Nopersonalresponseuntilendofnextworkingday  from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
              
             return template.query(sql, new RowMapper<GraphBean>() {
  				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
  					GraphBean gBean = new GraphBean();
  					gBean.setMonth(rs.getString("month"));
  					gBean.setYear(String.valueOf(year));
  					gBean.setAfter4hoursonthesameworkingdayp(rs.getString("After4hoursonthesameworkingdayp"));
  					gBean.setWithinnextworkingdayaftersendingtherequestp(rs.getString("Withinnextworkingdayaftersendingtherequestp"));
  					gBean.setWithin4hoursaftersendingtherequestp(rs.getString("Within4hoursaftersendingtherequestp"));
  					gBean.setNopersonalresponseuntilendofnextworkingdayp(rs.getString("Nopersonalresponseuntilendofnextworkingdayp"));
  					return gBean;
  				}
  			});
              
             
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number= "1.3";
			 sql = "SELECT mon,month,totalcount,After4hoursonthesameworkingday,Within2hoursaftersendingtherequest,Within4hoursaftersendingtherequest,Notatall,round(((After4hoursonthesameworkingday/totalcount)*100),2)as After4hoursonthesameworkingdayp,round(((Within2hoursaftersendingtherequest/totalcount)*100),2)as Within2hoursaftersendingtherequestp,round(((Within4hoursaftersendingtherequest/totalcount)*100),2)as Within4hoursaftersendingtherequestp,round(((Notatall/totalcount)*100),2)as Notatallp FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon, count(CASE  WHEN mys_txn_answers.select_option_text is not null and select_option_text in ('After 4 hours on the same working day','Within 2 hours after sending the request','Within 4 hours after sending the request','Not at all') then 1 else null end )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='After 4 hours on the same working day'  and select_option_text is not null THEN 1 ELSE null  END) AS After4hoursonthesameworkingday,count(CASE  WHEN mys_txn_answers.select_option_text='Within 2 hours after sending the request' and select_option_text is not null THEN 1 ELSE null END ) AS Within2hoursaftersendingtherequest,count(CASE  WHEN mys_txn_answers.select_option_text='Within 4 hours after sending the request' and select_option_text is not null THEN 1 ELSE null END ) AS Within4hoursaftersendingtherequest,count(CASE  WHEN mys_txn_answers.select_option_text='Not at all' and select_option_text is not null THEN 1 ELSE null END ) AS Notatall from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
             
             return template.query(sql, new RowMapper<GraphBean>() {
 				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
 					GraphBean gBean = new GraphBean();
 					gBean.setMonth(rs.getString("month"));
 					gBean.setYear(String.valueOf(year));
 					gBean.setAfter4hoursonthesameworkingdayp(rs.getString("After4hoursonthesameworkingdayp"));
 					gBean.setWithin2hoursaftersendingtherequestp(rs.getString("Within2hoursaftersendingtherequestp"));
 					gBean.setWithin4hoursaftersendingtherequestp(rs.getString("Within4hoursaftersendingtherequestp"));
 					gBean.setNotatallp(rs.getString("Notatallp"));
 					return gBean;
 				}
 			});
             
             
             
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	 standard_number="1A.7";
				  sql = "SELECT mon,month,totalcount,Within4hoursaftersendingtherequest,After4hoursonthesameworkingday,Withinnextworkingdayaftersendingtherequest,Nopersonalresponseuntilendofnextworkingday, round(((Within4hoursaftersendingtherequest / totalcount)*100), 2)as Within4hoursaftersendingtherequestp,round(((After4hoursonthesameworkingday / totalcount)*100), 2)as After4hoursonthesameworkingdayp,round(((Withinnextworkingdayaftersendingtherequest / totalcount)*100), 2)as Withinnextworkingdayaftersendingtherequestp,round(((Nopersonalresponseuntilendofnextworkingday / totalcount)*100), 2)as Nopersonalresponseuntilendofnextworkingdayp FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(CASE WHEN mys_txn_answers.select_option_text is not null and select_option_text in ('a) Within 4 hours after sending the request','b) After 4 hours on the same working day','c) Within the next working day after sending the request', 'd) No personal response until end of next working day') then 1 else null  end)as totalcount, count(CASE WHEN mys_txn_answers.select_option_text ='a) Within 4 hours after sending the request' and select_option_text is not null THEN 1 ELSE null END) AS Within4hoursaftersendingtherequest, count(CASE WHEN mys_txn_answers.select_option_text = 'b) After 4 hours on the same working day' and select_option_text is not null THEN 1 ELSE null  END) AS After4hoursonthesameworkingday, count(CASE WHEN mys_txn_answers.select_option_text = 'c) Within the next working day after sending the request' and select_option_text is not null THEN 1 ELSE null  END) AS Withinnextworkingdayaftersendingtherequest, count( CASE WHEN mys_txn_answers.select_option_text = 'd) No personal response until end of next working day' and select_option_text is not null  THEN 1 ELSE null  END) AS Nopersonalresponseuntilendofnextworkingday  from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
		             System.out.println(sql);
		             return template.query(sql, new RowMapper<GraphBean>() {
		  				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
		  					GraphBean gBean = new GraphBean();
		  					gBean.setMonth(rs.getString("month"));
		  					gBean.setYear(String.valueOf(year));
		  					gBean.setAfter4hoursonthesameworkingdayp(rs.getString("After4hoursonthesameworkingdayp"));
		  					gBean.setWithinnextworkingdayaftersendingtherequestp(rs.getString("Withinnextworkingdayaftersendingtherequestp"));
		  					gBean.setWithin4hoursaftersendingtherequestp(rs.getString("Within4hoursaftersendingtherequestp"));
		  					gBean.setNopersonalresponseuntilendofnextworkingdayp(rs.getString("Nopersonalresponseuntilendofnextworkingdayp"));
		  					return gBean;
		  				}
		  			});
		    }
		// String sql = "SELECT mon,month,totalcount,After4hoursonthesameworkingday,Within2hoursaftersendingtherequest,Within4hoursaftersendingtherequest,Notatall,round(((After4hoursonthesameworkingday/totalcount)*100),2)as After4hoursonthesameworkingdayp,round(((Within2hoursaftersendingtherequest/totalcount)*100),2)as Within2hoursaftersendingtherequestp,round(((Within4hoursaftersendingtherequest/totalcount)*100),2)as Within4hoursaftersendingtherequestp,round(((Notatall/totalcount)*100),2)as Notatallp FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon, count(CASE  WHEN mys_txn_answers.select_option_text is not null and select_option_text in ('After 4 hours on the same working day','Within 2 hours after sending the request','Within 4 hours after sending the request','Not at all') then 1 else null end )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='After 4 hours on the same working day'  and select_option_text is not null THEN 1 ELSE null  END) AS After4hoursonthesameworkingday,count(CASE  WHEN mys_txn_answers.select_option_text='Within 2 hours after sending the request' and select_option_text is not null THEN 1 ELSE null END ) AS Within2hoursaftersendingtherequest,count(CASE  WHEN mys_txn_answers.select_option_text='Within 4 hours after sending the request' and select_option_text is not null THEN 1 ELSE null END ) AS Within4hoursaftersendingtherequest,count(CASE  WHEN mys_txn_answers.select_option_text='Not at all' and select_option_text is not null THEN 1 ELSE null END ) AS Notatall from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
			//System.out.println("dealership response"+sql);
//			return template.query(sql, new RowMapper<GraphBean>() {
//				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
//					GraphBean gBean = new GraphBean();
//					gBean.setMonth(rs.getString("month"));
//					gBean.setYear(String.valueOf(year));
//					gBean.setAfter4hoursonthesameworkingdayp(rs.getString("After4hoursonthesameworkingdayp"));
//					gBean.setWithin2hoursaftersendingtherequestp(rs.getString("Within2hoursaftersendingtherequestp"));
//					gBean.setWithin4hoursaftersendingtherequestp(rs.getString("Within4hoursaftersendingtherequestp"));
//					gBean.setNotatallp(rs.getString("Notatallp"));
//
//System.out.println(rs.getString("After4hoursonthesameworkingdayp"));
//					return gBean;
//				}
//			});
		
	}
	public List<GraphBean> getappointmentchart(GraphBean gBean, String did, String bid, String rid,
			String oid, String month) {
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }
		 if(bid==null || bid=="") {
			 bid="all";
		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		
		   int  year;
		   String standard_number="";
		   String sub_question_id="";
		   if(gBean.getYear()==null || gBean.getYear()=="")
		   {
		     year=Calendar.getInstance().get(Calendar.YEAR);
			 standard_number="1a.9";
			 sub_question_id="574";
		   }
		  else if(gBean.getYear().equals("2019"))
		   {
			 year=Integer.parseInt(gBean.getYear());
			 standard_number= "1.6";
			 sub_question_id="all";
		   }
		   else
			 {
			   year=Integer.parseInt(gBean.getYear());
			   standard_number="1a.9";
         	   sub_question_id="574";
			}	 
		  
		 String sql = "SELECT mon, month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"') and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";
			System.out.println("appointment chart"+sql);
			return template.query(sql, new RowMapper<GraphBean>() {
				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
					GraphBean gBean = new GraphBean();
					gBean.setMonth(rs.getString("month"));
					gBean.setYear(String.valueOf(year));
					gBean.setYesCount(rs.getString("yespercentage"));
					gBean.setNoCount(rs.getString("nopercentage"));
					return gBean;
				}
			});
		
	}
	public List<GraphBean> getmeetthestandards(GraphBean gBean, String did, String bid, String rid,
			String oid, String month) {
		    HttpSession session = request.getSession(true);
			String dealers = (String) session.getAttribute("dealers");
			String roleId = (String) session.getAttribute("role_id");
			String region = (String) session.getAttribute("region");  
            
			if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
			
			
			
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(bid==null || bid=="") {
			 bid="all";
		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 
		   int  year;
		   String sql="";
		   String standard_number="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="1a.9";
			  sql = "SELECT *,IFNULL(ycount,0) as yes_count,IFNULL(ncount,0) as no_count,( IFNULL(ycount,0) + IFNULL(ncount,0) ) as total_count, round(IFNULL(ycount,0)/( IFNULL(ycount,0) + IFNULL(ncount,0) )*100,2) as yespercentage,round(IFNULL(ncount,0)/( IFNULL(ycount,0) + IFNULL(ncount,0) )*100,2) as nopercentage FROM(SELECT * FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon from  mys_txn_answers left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE   mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active'  GROUP by month) as res1 LEFT JOIN (SELECT *,SUM(c2) as ycount FROM (SELECT month(mst_shopper_details.visit_date) as yes_month,(CASE WHEN Count(select_option_text)=4 THEN 1 ELSE 0 END) as c2 FROM mys_txn_answers,mst_shopper_details WHERE standard_number='"+standard_number+"'  and subquestion_id in (572,573,574,575) and mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id AND mst_shopper_details.year='"+year+"' AND (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='ALL') and mst_shopper_details.visit_status='published' and mys_txn_answers.select_option_text='yes' GROUP BY shopper_id,month(mst_shopper_details.visit_date)) as res1 GROUP by yes_month) as res2 on res1.mon=res2.yes_month) as res3 LEFT JOIN (SELECT *,SUM(c1) as ncount FROM (SELECT month(mst_shopper_details.visit_date) as no_month,(CASE WHEN Count(select_option_text)>=1 THEN 1 ELSE 0 END) as c1 FROM mys_txn_answers,mst_shopper_details WHERE standard_number='"+standard_number+"'  and subquestion_id in (572,573,574,575) and mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id AND mst_shopper_details.year='"+year+"' AND (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='ALL') and mst_shopper_details.visit_status='published' and mys_txn_answers.select_option_text='no' GROUP BY shopper_id,month(mst_shopper_details.visit_date)) as res1 GROUP by no_month) as res4 on res3.mon=res4.no_month";
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number= "4.1"; // but in the sheet 1.5 is there, but in 2019 4.1 is passing
			  sql = "SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active'  GROUP by month)res1 order by mon";
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	standard_number="1a.9";
				sql = "SELECT *,IFNULL(ycount,0) as yes_count,IFNULL(ncount,0) as no_count,( IFNULL(ycount,0) + IFNULL(ncount,0) ) as total_count, round(IFNULL(ycount,0)/( IFNULL(ycount,0) + IFNULL(ncount,0) )*100,2) as yespercentage,round(IFNULL(ncount,0)/( IFNULL(ycount,0) + IFNULL(ncount,0) )*100,2) as nopercentage FROM(SELECT * FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon from  mys_txn_answers left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE   mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active'  GROUP by month) as res1 LEFT JOIN (SELECT *,SUM(c2) as ycount FROM (SELECT month(mst_shopper_details.visit_date) as yes_month,(CASE WHEN Count(select_option_text)=4 THEN 1 ELSE 0 END) as c2 FROM mys_txn_answers,mst_shopper_details WHERE standard_number='"+standard_number+"'  and subquestion_id in (572,573,574,575) and mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id AND mst_shopper_details.year='"+year+"' AND (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='ALL') and mst_shopper_details.visit_status='published' and mys_txn_answers.select_option_text='yes' GROUP BY shopper_id,month(mst_shopper_details.visit_date)) as res1 GROUP by yes_month) as res2 on res1.mon=res2.yes_month) as res3 LEFT JOIN (SELECT *,SUM(c1) as ncount FROM (SELECT month(mst_shopper_details.visit_date) as no_month,(CASE WHEN Count(select_option_text)>=1 THEN 1 ELSE 0 END) as c1 FROM mys_txn_answers,mst_shopper_details WHERE standard_number='"+standard_number+"'  and subquestion_id in (572,573,574,575) and mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id AND mst_shopper_details.year='"+year+"' AND (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='ALL') and mst_shopper_details.visit_status='published' and mys_txn_answers.select_option_text='no' GROUP BY shopper_id,month(mst_shopper_details.visit_date)) as res1 GROUP by no_month) as res4 on res3.mon=res4.no_month";

		    }
		// String sql = "SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active'  GROUP by month)res1 order by mon";
			System.out.println("response meet"+sql);
			return template.query(sql, new RowMapper<GraphBean>() {
				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
					GraphBean gBean = new GraphBean();
					gBean.setMonth(rs.getString("month"));
					gBean.setYear(String.valueOf(year));
					gBean.setYesCount(rs.getString("yespercentage"));
					gBean.setNoCount(rs.getString("nopercentage"));
					return gBean;
				}
			});
		}
	public List<GraphBean> getytdmtd1(GraphBean gBean, String mid,String did,String bid) {
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		 //int year = 2019;
		 if(mid==null){
			 mid="all";
		 }
		 if(bid==null){
			 bid="all";
		 }
		 if(did==null){
			 did="all";
		 }
			//String sql = "SELECT *,(CASE WHEN sname='Jan' THEN 'January' WHEN sname='FEB' THEN 'Feburary' WHEN sname='MAR' THEN 'March' WHEN sname='APR' THEN 'April' WHEN sname='MAY' THEN 'May' WHEN sname='Jun' THEN 'June' WHEN sname='JUL' THEN 'July' WHEN sname='AUG' THEN 'August' WHEN sname='SEP' THEN 'September' WHEN sname='OCT' THEN 'October' WHEN sname='Nov' THEN 'November' WHEN sname='DEC' THEN 'December' END) as monthname,'2019' as year FROM(SELECT round(SUM(scored_point_721)/SUM(maximum_point_721)*100,2) as MTD,(SELECT round(SUM(scored_point_721)/ SUM(maximum_point_721)*100,2) as YTD FROM mys_txn_dealer_score WHERE (dealer_id='"+did+"' or '"+did+"'='all') AND (month<='"+mid+"' or '"+mid+"'='all') and year='"+year+"' AND (brand_id='"+bid+"' or '"+bid+"') as YTD,SUBSTRING('JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC ', (('"+mid+"' or '"+mid+"'='all') * 4) - 3, 3) as sname FROM mys_txn_dealer_score WHERE (dealer_id='"+did+"' or '"+did+"='all') AND (month='"+mid+"' or '"+mid+"'='all') and year='"+year+"' AND (brand_id='"+bid+"' or '"+bid+"'='all')) as res1";
		 String sql="SELECT *,(CASE WHEN sname='Jan' THEN 'January' WHEN sname='FEB' THEN 'Feburary' WHEN sname='MAR' THEN 'March' WHEN sname='APR' THEN 'April' WHEN sname='MAY' THEN 'May' WHEN sname='Jun' THEN 'June' WHEN sname='JUL' THEN 'July' WHEN sname='AUG' THEN 'August' WHEN sname='SEP' THEN 'September' WHEN sname='OCT' THEN 'October' WHEN sname='Nov' THEN 'November' WHEN sname='DEC' THEN 'December'  END) as monthname,'"+year+"' as year FROM(SELECT round(SUM(scored_point_721)/SUM(maximum_point_721)*100,2) as MTD,(SELECT round(SUM(scored_point_721)/ SUM(maximum_point_721)*100,2) as YTD FROM mys_txn_dealer_score WHERE (dealer_id='"+did+"'  OR '"+did+"'='all' ) AND (month<='"+mid+"' OR '"+mid+"'='all') and year='"+year+"' AND (brand_id='"+bid+"' OR '"+bid+"'='all')) as YTD,SUBSTRING('JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC ', (('"+mid+"' OR '"+mid+"'='all') * 4) - 3, 3) as sname FROM mys_txn_dealer_score WHERE (dealer_id='"+did+"' OR '"+did+"'='all') AND (month='"+mid+"' OR 'all'='all') and year='"+year+"' AND (brand_id='"+bid+"' OR 'all'='all')) as res1";
			System.out.println(sql);
			return template.query(sql, new RowMapper<GraphBean>() {
				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
					GraphBean gBean = new GraphBean();
					gBean.setYTD(rs.getString("YTD"));
					gBean.setMTD(rs.getString("MTD"));
					gBean.setMonth(rs.getString("monthName"));
					return gBean;
				}
			});
		 
		}
	
	public List<GraphBean> getstepstotakegraph(GraphBean gBean, String did, String bid, String rid,
			String oid, String month) {
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		// int year = 2019;
		
		    HttpSession session = request.getSession(true);
			String dealers = (String) session.getAttribute("dealers");
			String roleId = (String) session.getAttribute("role_id");
			String region = (String) session.getAttribute("region");			
			 if(roleId.equals("7"))
				{
					did=dealers;
					rid=region;
				}
			    else  if((did=="" || did==null) &&(rid=="" || rid==null))
		        {
		          did="all";
		          rid="all";
		        }
			    
			    else  if((did!="") &&(rid=="" || rid==null))
			    {
				  did= did;
				  rid="all";	
				}
			    else  if((did=="" || did==null) &&(rid!=""))
			    {
				  did= "all";
				  rid=rid;	
				}
			    else 
		        {
		          did= did;
		          rid=rid;  
		        }

		
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(bid==null || bid=="") {
			 bid="all";
		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
	     int  year;
	       String standard_number="";
	       String sub_question_id="";
	        if(gBean.getYear()==null || gBean.getYear()=="")
	        {
	          year=Calendar.getInstance().get(Calendar.YEAR);
	          standard_number="1b.2";
	          sub_question_id="592";
	          System.out.println("current year in if auto response"+year);
	        }
	        else if(gBean.getYear().equals("2019"))
	        {
	          year=Integer.parseInt(gBean.getYear());
	          System.out.println("current year in  else if auto response"+year);
	          standard_number="1.9";
	          sub_question_id="all";
	        }
	        else
	        {
	          year=Integer.parseInt(gBean.getYear());
	          System.out.println("current year in  else auto response"+year);
	          standard_number="1b.2";
	          sub_question_id="592";
	        }
	     
		 String sql = "SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"') and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
			System.out.println(sql);
			return template.query(sql, new RowMapper<GraphBean>() {
				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
					GraphBean gBean = new GraphBean();
					gBean.setMonth(rs.getString("month"));
					//gBean.setMonth_name(rs.getString("monthName"));
					gBean.setYear(String.valueOf(year));
					gBean.setYesCount(rs.getString("yespercentage"));
					gBean.setNoCount(rs.getString("nopercentage"));
					return gBean;
					}
					});
					
		}
	
	public QuestionnaireBean getOverallperformanceFormula1(final QuestionnaireBean qBean, final String standard_number,
			final String answer,String brand,String outlet,String dealer,String region,String month) {
		
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
		//int year=2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(
				"SELECT (select COUNT(DISTINCT(shopper_id)) as visited_count FROM `mys_txn_answers` WHERE `standard_number`='"+standard_number+"' AND mys_txn_answers.status='active' AND shopper_id IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as visited_count,(select COUNT(DISTINCT(shopper_id)) as no_count FROM mys_txn_answers WHERE standard_number='"+standard_number+"' AND select_option_text='"+answer+"' AND mys_txn_answers.status='active' AND shopper_id  IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as no_count");
		return template.queryForObject(
				"SELECT (select COUNT(DISTINCT(shopper_id)) as visited_count FROM `mys_txn_answers` WHERE `standard_number`='"+standard_number+"' AND mys_txn_answers.status='active' AND shopper_id IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as visited_count,(select COUNT(DISTINCT(shopper_id)) as no_count FROM mys_txn_answers WHERE standard_number='"+standard_number+"'AND select_option_text='"+answer+"' AND mys_txn_answers.status='active' AND shopper_id  IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as no_count;",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						Double visited_count = rs.getDouble("visited_count");

						Double no_count = rs.getDouble("no_count");
						Double val1 = visited_count - no_count; // yes count
						Double val2 = visited_count; // total visit count
						Double val = (val1 / val2) * 100;

						qBean.setAvg(val.toString());
						String valu = val.toString();
						System.out.println("Value for 1.3 Standard Number ======= And Not At All answer"+valu);
						if (valu.equals("Infinity")) {
							qBean.setAvg("0.0");
						} else {

							if (valu.equals("NaN")) {
								qBean.setAvg("0.0");
							}
						}
						return qBean;
					}
				});

	}
	public QuestionnaireBean getOverallperformanceFormula3(final QuestionnaireBean qBean, final String standard_number,String brand,String outlet,String dealer,String region,String month) {
		
		
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
		//int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println("vehicle presentation============="+standard_number);
		System.out.println(
				"SELECT (select COUNT(DISTINCT(shopper_id)) as visited_count FROM mys_txn_answers WHERE standard_number='"
						+ standard_number
						+ "' AND mys_txn_answers.status='active' AND shopper_id IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) as visited_count,(select COUNT(standard_num) as no_count FROM mys_txn_formulaanswer WHERE standard_num='"
						+ standard_number
						+ "' AND points >0 and mys_txn_formulaanswer.status='active' AND shopper_id IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) as no_count");
		return template.queryForObject(
				"SELECT (select COUNT(DISTINCT(shopper_id)) as visited_count FROM mys_txn_answers WHERE standard_number='"
						+ standard_number
						+ "' AND mys_txn_answers.status='active' AND shopper_id IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) as visited_count,(select COUNT(standard_num) as no_count FROM mys_txn_formulaanswer WHERE standard_num='"
						+ standard_number
						+ "' AND points >0 and mys_txn_formulaanswer.status='active' AND shopper_id IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active' AND (mst_shopper_details.dealer_id='"
						+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet
						+ "' OR '" + outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))) as no_count;",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						Double visited_count = rs.getDouble("visited_count");

						Double no_count = rs.getDouble("no_count");
						Double val1 = visited_count ;
						Double val2 = no_count;
						Double val = (val2 / val1) * 100;

						qBean.setAvg(val.toString());
						String valu = val.toString();
						System.out.println("Value for 2.4 Standard Number ======= vehicle presentation"+valu);
						if (valu.equals("Infinity")) {
							qBean.setAvg("0.0");
						} else {

							if (valu.equals("NaN")) {
								qBean.setAvg("0.0");
							}
						}
						return qBean;
					}
				});

	}
	
	public QuestionnaireBean getOverallperformanceFormula2(final QuestionnaireBean qBean, final String standard_number,
			final String answer,String brand,String outlet,String dealer,String region,String month) {

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
		String sql = "";
		if (standard_number.equals("4.3")) {
			System.out.println("car offer===========4.3");
			sql = "SELECT (select COUNT(DISTINCT(shopper_id)) as visited_count FROM `mys_txn_answers` WHERE `standard_number`='"
					+ standard_number
					+ "' AND mys_txn_answers.status='active' AND shopper_id IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
					+ year + "' and mst_shopper_details.brand_id='" + brand
					+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
					+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '"
					+ outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
					+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
					+ "'='ALL'))) as visited_count,(select COUNT(DISTINCT(shopper_id)) as no_count FROM mys_txn_answers WHERE standard_number='"
					+ standard_number
					+ "'AND select_option_text='Yes, handed over during the consultation on official paper' AND mys_txn_answers.status='active' AND shopper_id  IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
					+ year + "' and mst_shopper_details.brand_id='" + brand
					+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
					+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '"
					+ outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
					+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
					+ "'='ALL'))) as no_count";
		} else {
			sql = "SELECT (select COUNT(DISTINCT(shopper_id)) as visited_count FROM `mys_txn_answers` WHERE `standard_number`='"
					+ standard_number
					+ "' AND mys_txn_answers.status='active' AND shopper_id IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
					+ year + "' and mst_shopper_details.brand_id='" + brand
					+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
					+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '"
					+ outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
					+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
					+ "'='ALL'))) as visited_count,(select COUNT(DISTINCT(shopper_id)) as no_count FROM mys_txn_answers WHERE standard_number='"
					+ standard_number + "'AND select_option_text='" + answer
					+ "' AND mys_txn_answers.status='active' AND shopper_id  IN(SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
					+ year + "' and mst_shopper_details.brand_id='" + brand
					+ "' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"
					+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '"
					+ outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
					+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
					+ "'='ALL'))) as no_count";
		}
		System.out.println(sql);

		return template.queryForObject(sql, new RowMapper<QuestionnaireBean>() {
			public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
				final Double visited_count = rs.getDouble("visited_count");

				Double no_count = rs.getDouble("no_count");
				Double val1 = no_count;
				Double val2 = visited_count;
				Double val = (val1 / val2) * 100;
				qBean.setAvg(val.toString());
				String valu = val.toString();
				System.out.println("Value for 3.1 Standard Number ======= And Yes, actively offered answer"+valu);
				System.out.println("Value for 4.3 Standard Number ======= And Yes, actively offered answer"+valu);
				if (valu.equals("Infinity")) {
					qBean.setAvg("0.0");
				} else {

					if (valu.equals("NaN")) {
						qBean.setAvg("0.0");
					}
				}

				System.out.println("percentrage" + qBean.getAvg());
				return qBean;
			}
		});

	}
	/***********manoj d overallPerformance Ends**********/

	public List<GraphBean> getmeetthestandardtelephonesjson(GraphBean gBean, String did, String bid, String rid,
			String oid, String month) {
		 //int year = Calendar.getInstance().get(Calendar.YEAR);
		// int year = 2019;
		 
		 System.out.println("helleo here in second graph  1111============");
		 
		   HttpSession session = request.getSession(true);
			String dealers = (String) session.getAttribute("dealers");
			String roleId = (String) session.getAttribute("role_id");
			String region = (String) session.getAttribute("region");
			if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
			
			

//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(bid==null || bid=="") {
			 bid="all";
		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
	     int  year;
	       String standard_number="";
	       String sub_question_id="";
	        if(gBean.getYear()==null || gBean.getYear()=="")
	        {
	          year=Calendar.getInstance().get(Calendar.YEAR);
	          standard_number="1a.9";
	          sub_question_id="all";
	          System.out.println("current year in if auto response"+year);
	        }
	        else if(gBean.getYear().equals("2019"))
	        {
	          year=Integer.parseInt(gBean.getYear());
	          System.out.println("current year in  else if auto response"+year);
	          standard_number="1.8";
	          sub_question_id="all";
	        }
	        else
	        {
	          year=Integer.parseInt(gBean.getYear());
	          System.out.println("current year in  else auto response"+year);
	          standard_number="1a.9";
	          sub_question_id="all";
	        }
	        System.out.println("helleo here in second graph 22222============");
		 String sql = "SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"')  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active'  GROUP by month)res1 order by mon";
			System.out.println(sql);
			return template.query(sql, new RowMapper<GraphBean>() {
				public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
					GraphBean gBean = new GraphBean();
					gBean.setMonth(rs.getString("month"));
					gBean.setYear(String.valueOf(year));
					gBean.setYesCount(rs.getString("yespercentage"));
					gBean.setNoCount(rs.getString("nopercentage"));
					return gBean;
				}
			});
		
	}
	public List<GraphBean> getanyoneatdealershipjson(GraphBean gBean, String did, String bid, String rid,
			String oid, String month) {
		// int year = Calendar.getInstance().get(Calendar.YEAR);
		// int year = 2019;
		 
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }
		
		
		
//		  if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(bid==null || bid=="") {
			 bid="all";
		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 int  year;
		   String standard_number="";
		   String sub_question_id="";
		   String sql="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="1.b1";
		      sub_question_id="all";
		       sql = "SELECT mon,month,totalcount,After21secondsbutwithin60secondsatthefirstattempt,Within20secondsatthefirstattempt,Within60secondsatthesecondattempt,IcalledtwotimesbutIcouldnotreachthedealershipatall,round(((After21secondsbutwithin60secondsatthefirstattempt/totalcount)*100),2)as After21secondsbutwithin60secondsatthefirstattempt,round(((Within20secondsatthefirstattempt/totalcount)*100),2)as Within20secondsatthefirstattempt,round(((Within60secondsatthesecondattempt/totalcount)*100),2)as Within60secondsatthesecondattempt,round(((IcalledtwotimesbutIcouldnotreachthedealershipatall/totalcount)*100),2)as IcalledtwotimesbutIcouldnotreachthedealershipatall FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(CASE  WHEN mys_txn_answers.select_option_text is not null  then 1 else null end )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Within 20 seconds at the first attempt'  and select_option_text is not null THEN 1 ELSE null  END) AS Within20secondsatthefirstattempt,count(CASE  WHEN mys_txn_answers.select_option_text='After 21 seconds but within 60 seconds at the first attempt' and select_option_text is not null THEN 1 ELSE null END ) AS After21secondsbutwithin60secondsatthefirstattempt,count(CASE  WHEN mys_txn_answers.select_option_text='Within 60 seconds at the second attempt' and select_option_text is not null THEN 1 ELSE null END ) AS Within60secondsatthesecondattempt,count(CASE  WHEN mys_txn_answers.select_option_text='I called two times but I could not reach the dealership at all' and select_option_text is not null THEN 1 ELSE null END ) AS IcalledtwotimesbutIcouldnotreachthedealershipatall from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id  WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"')  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
				System.out.println(sql);
				return template.query(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setMonth(rs.getString("month"));
						gBean.setYear(String.valueOf(year));
						gBean.setIcalledtwotimesbutIcouldnotreachthedealershipatallp(rs.getString("IcalledtwotimesbutIcouldnotreachthedealershipatall"));
						gBean.setCallbackwasnotconductedwithinoneworkingdayp(rs.getString("After21secondsbutwithin60secondsatthefirstattempt"));
						gBean.setInwaitingloopWithin120secondsatthefirstattemptp(rs.getString("Within20secondsatthefirstattempt"));
						gBean.setInwaitingloopWithin120secondsatthesecondattemptp(rs.getString("Within60secondsatthesecondattempt"));
						//gBean.setWithoutwaitingloopWithin30secondsatthesecondattempp(rs.getString("WithoutwaitingloopWithin30secondsatthesecondattempp"));
						//gBean.setWithoutwaitingloopWithin30secondsatthefirstattemptp(rs.getString("WithoutwaitingloopWithin30secondsatthefirstattemptp"));
						return gBean;
					}
				});
            
           
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number= "1.7";
		      sub_question_id="all";
		       sql = "SELECT mon,month,totalcount,WithoutwaitingloopWithin30secondsatthefirstattempt,WithoutwaitingloopWithin30secondsatthesecondattemp,InwaitingloopWithin120secondsatthefirstattempt,InwaitingloopWithin120secondsatthesecondattempt,Callbackwasnotconductedwithinoneworkingday,IcalledtwotimesbutIcouldnotreachthedealershipatall,round(((WithoutwaitingloopWithin30secondsatthefirstattempt/totalcount)*100),2)as WithoutwaitingloopWithin30secondsatthefirstattemptp,round(((WithoutwaitingloopWithin30secondsatthesecondattemp/totalcount)*100),2)as WithoutwaitingloopWithin30secondsatthesecondattempp,round(((InwaitingloopWithin120secondsatthefirstattempt/totalcount)*100),2)as InwaitingloopWithin120secondsatthefirstattemptp,round(((InwaitingloopWithin120secondsatthesecondattempt/totalcount)*100),2)as InwaitingloopWithin120secondsatthesecondattemptp,round(((Callbackwasnotconductedwithinoneworkingday/totalcount)*100),2)as Callbackwasnotconductedwithinoneworkingdayp,round(((IcalledtwotimesbutIcouldnotreachthedealershipatall/totalcount)*100),2)as IcalledtwotimesbutIcouldnotreachthedealershipatallp FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(CASE  WHEN mys_txn_answers.select_option_text is not null  then 1 else null end )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Without waiting loop- Within 30 seconds at the first attempt'  and select_option_text is not null THEN 1 ELSE null  END) AS WithoutwaitingloopWithin30secondsatthefirstattempt,count(CASE  WHEN mys_txn_answers.select_option_text='Without waiting loop- Within 30 seconds at the second attempt' and select_option_text is not null THEN 1 ELSE null END ) AS WithoutwaitingloopWithin30secondsatthesecondattemp,count(CASE  WHEN mys_txn_answers.select_option_text='In waiting loop: Within 120 seconds at the first attempt' and select_option_text is not null THEN 1 ELSE null END ) AS InwaitingloopWithin120secondsatthefirstattempt,count(CASE  WHEN mys_txn_answers.select_option_text='In waiting loop: Within 120 seconds at the second attempt' and select_option_text is not null THEN 1 ELSE null END ) AS InwaitingloopWithin120secondsatthesecondattempt,count(CASE  WHEN mys_txn_answers.select_option_text='Call back was not conducted within one working day' and select_option_text is not null THEN 1 ELSE null END ) AS Callbackwasnotconductedwithinoneworkingday ,count(CASE  WHEN mys_txn_answers.select_option_text='I called two times but I could not reach the dealership at all' and select_option_text is not null THEN 1 ELSE null END ) AS IcalledtwotimesbutIcouldnotreachthedealershipatall from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id  WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"')  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
				System.out.println(sql);
				return template.query(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setMonth(rs.getString("month"));
						gBean.setYear(String.valueOf(year));
						gBean.setIcalledtwotimesbutIcouldnotreachthedealershipatallp(rs.getString("IcalledtwotimesbutIcouldnotreachthedealershipatallp"));
						gBean.setCallbackwasnotconductedwithinoneworkingdayp(rs.getString("Callbackwasnotconductedwithinoneworkingdayp"));
						gBean.setInwaitingloopWithin120secondsatthefirstattemptp(rs.getString("InwaitingloopWithin120secondsatthefirstattemptp"));
						gBean.setInwaitingloopWithin120secondsatthesecondattemptp(rs.getString("InwaitingloopWithin120secondsatthesecondattemptp"));
						gBean.setWithoutwaitingloopWithin30secondsatthesecondattempp(rs.getString("WithoutwaitingloopWithin30secondsatthesecondattempp"));
						gBean.setWithoutwaitingloopWithin30secondsatthefirstattemptp(rs.getString("WithoutwaitingloopWithin30secondsatthefirstattemptp"));
						return gBean;
					}
				});
           
           
           
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	 standard_number="1.b1";
		    	 sub_question_id="all";
		    	  sql = "SELECT mon,month,totalcount,After21secondsbutwithin60secondsatthefirstattempt,Within20secondsatthefirstattempt,Within60secondsatthesecondattempt,IcalledtwotimesbutIcouldnotreachthedealershipatall,round(((After21secondsbutwithin60secondsatthefirstattempt/totalcount)*100),2)as After21secondsbutwithin60secondsatthefirstattempt,round(((Within20secondsatthefirstattempt/totalcount)*100),2)as Within20secondsatthefirstattempt,round(((Within60secondsatthesecondattempt/totalcount)*100),2)as Within60secondsatthesecondattempt,round(((IcalledtwotimesbutIcouldnotreachthedealershipatall/totalcount)*100),2)as IcalledtwotimesbutIcouldnotreachthedealershipatall FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(CASE  WHEN mys_txn_answers.select_option_text is not null  then 1 else null end )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Within 20 seconds at the first attempt'  and select_option_text is not null THEN 1 ELSE null  END) AS Within20secondsatthefirstattempt,count(CASE  WHEN mys_txn_answers.select_option_text='After 21 seconds but within 60 seconds at the first attempt' and select_option_text is not null THEN 1 ELSE null END ) AS After21secondsbutwithin60secondsatthefirstattempt,count(CASE  WHEN mys_txn_answers.select_option_text='Within 60 seconds at the second attempt' and select_option_text is not null THEN 1 ELSE null END ) AS Within60secondsatthesecondattempt,count(CASE  WHEN mys_txn_answers.select_option_text='I called two times but I could not reach the dealership at all' and select_option_text is not null THEN 1 ELSE null END ) AS IcalledtwotimesbutIcouldnotreachthedealershipatall from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id  WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"')  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact='"+gBean.getMode_of_contact()+"' and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
					System.out.println(sql);
					return template.query(sql, new RowMapper<GraphBean>() {
						public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
							GraphBean gBean = new GraphBean();
							gBean.setMonth(rs.getString("month"));
							gBean.setYear(String.valueOf(year));
							gBean.setIcalledtwotimesbutIcouldnotreachthedealershipatallp(rs.getString("IcalledtwotimesbutIcouldnotreachthedealershipatall"));
							gBean.setCallbackwasnotconductedwithinoneworkingdayp(rs.getString("After21secondsbutwithin60secondsatthefirstattempt"));
							gBean.setInwaitingloopWithin120secondsatthefirstattemptp(rs.getString("Within20secondsatthefirstattempt"));
							gBean.setInwaitingloopWithin120secondsatthesecondattemptp(rs.getString("Within60secondsatthesecondattempt"));
							//gBean.setWithoutwaitingloopWithin30secondsatthesecondattempp(rs.getString("WithoutwaitingloopWithin30secondsatthesecondattempp"));
							//gBean.setWithoutwaitingloopWithin30secondsatthefirstattemptp(rs.getString("WithoutwaitingloopWithin30secondsatthefirstattemptp"));
							return gBean;
						}
					});
		    }
		
	}

	public List<GraphBean> getMonthsforconquestcontactFocus(GraphBean gBean) {
		//int year = 2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		String sql = "select distinct month(visit_date) as month, monthName(visit_date) as monthName from mst_shopper_details where year='"
				+ year + "' and visit_status='published' order by month(visit_date) ASC";

		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setMonth_name(rs.getString("monthName"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getcontactdetailsspeltcorrectly(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		// int year = 2019;
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		 if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
			
		
		
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
	     
	        int  year;
			   String standard_number="";
			   String sub_question_id="";
			   String sql="";
			    if(gBean.getYear()==null || gBean.getYear()=="")
			    {
			      year=Calendar.getInstance().get(Calendar.YEAR);
			      System.out.println("current year in if dealership"+year);
			      standard_number="1A.7";
			      sub_question_id="1991";
			       sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='d) No personal response until end of next working day'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='c) Within the next working day after sending the request' OR mys_txn_answers.select_option_text='b) After 4 hours on the same working day' OR mys_txn_answers.select_option_text='a) Within 4 hours after sending the request' and select_option_text is not null THEN 1 ELSE null  END) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id  left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"'  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
			  		System.out.println(sql);
			  		System.out.println("hello this is is first=======");
			  		return template.query(sql, new RowMapper<GraphBean>() {
			  			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
			  				GraphBean gBean = new GraphBean();
			  				gBean.setMonth(rs.getString("month"));
			  				gBean.setYear(String.valueOf(year));
			  				gBean.setYesCount(rs.getString("yespercentage"));
			  				gBean.setNoCount(rs.getString("nopercentage"));
			  				return gBean;
			  			}
			  		});
	              
	             
			    }
			    else if(gBean.getYear().equals("2019"))
			    {
			      year=Integer.parseInt(gBean.getYear());
			      System.out.println("current year in else  if dealership"+year);
			      standard_number= "4.5.1";
			      sub_question_id="all";
			       sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id  left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"'  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
			  		System.out.println(sql);
			  		return template.query(sql, new RowMapper<GraphBean>() {
			  			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
			  				GraphBean gBean = new GraphBean();
			  				gBean.setMonth(rs.getString("month"));
			  				gBean.setYear(String.valueOf(year));
			  				gBean.setYesCount(rs.getString("yespercentage"));
			  				gBean.setNoCount(rs.getString("nopercentage"));
			  				return gBean;
			  			}
			  		});
	             
	             
	             
			    }
			    else
			    {
			    	year=Integer.parseInt(gBean.getYear());
			    	 System.out.println("current year in else dealership"+year);
			    	 standard_number="1A.7";
			    	 sub_question_id="1991";
			    	  sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_id='1991'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_id!='1991' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id  left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"'  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
				  		System.out.println(sql);
				  		return template.query(sql, new RowMapper<GraphBean>() {
				  			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				  				GraphBean gBean = new GraphBean();
				  				gBean.setMonth(rs.getString("month"));
				  				gBean.setYear(String.valueOf(year));
				  				gBean.setYesCount(rs.getString("yespercentage"));
				  				gBean.setNoCount(rs.getString("nopercentage"));
				  				return gBean;
				  			}
				  		});
			              
			             
			             
			    }
		
	}
	
	/***********discount analysis manoj d****/
	public List<GraphBean> getDiscountPrices( GraphBean gBean,final String bid, String mid, String did, String oid,String rid) {
		final String brand = gBean.getBrand();
		System.out.println("brand in discount prices"+bid);
		GraphBean subquestion = getDiscountOfferedSubQuestionNumber(gBean,bid);
		final String subquestionid = subquestion.getSubQuestion();
		System.out.println(subquestionid);
		String sql = "SELECT sk_answer_id,options FROM `mst_questionare_selectoption` WHERE `subquestion_id`='"+gBean.getSubQuestion()+"'";
		System.out.println("this query with subquestionid");
		//System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {

			public GraphBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				GraphBean gbBean = new GraphBean();
				gbBean.setAnswer(rs.getString("options"));
				gbBean.setBrand(brand);
				gbBean.setSubQuestion(subquestionid);
				GraphBean data = getBranddiscountcount(gbBean,mid,did,oid,rid);
				gbBean.setOneValue(data.getOneValue());
				return gbBean;
			}

		});

	}
	
	public GraphBean getDiscountOfferedSubQuestionNumber(final GraphBean gbBean, String brand) {
		
		if(brand==null || brand==""){
			brand="1";
		}
        System.out.println("subquestion Id in discount");
		//String sql = "SELECT Distinct sk_subquestion_id FROM `mst_questionare_subquestion`,mst_questionare WHERE mst_questionare_subquestion.subquestion='Discount Offered' and sk_question_id=question_id and standard_number='IN-6' and brand_id='"+brand+"' ";
		String sql="SELECT Distinct sk_subquestion_id FROM `mst_questionare_subquestion`,mst_questionare WHERE mst_questionare_subquestion.subquestion='Discount Offered' and sk_question_id=question_id and standard_number='IN-6' and brand_id='"+brand+"' AND mst_questionare.mode_of_contact!='Online Sales Channel' AND mst_questionare_subquestion.subquestion_status='active'";

		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<GraphBean>() {

			public GraphBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				gbBean.setSubQuestion(rs.getString("sk_subquestion_id"));
				return gbBean;
			}

		});

	}
	
	public GraphBean getBranddiscountcount(GraphBean gbBean,String mid,String did,String oid, String rid) {
		System.out.println("this is disount");
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		String brand = gbBean.getBrand();
		if(brand==null || brand==""){
			brand="1";
		}
		if(mid=="" || mid==null){
			mid="all";
		}
		
		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }
		
//		if(did=="" || did==null){
//			did="all";
//		}
		if(oid=="" || oid==null){
			oid="all";
		}
//		if(rid=="" || rid==null){
//			oid="all";
//		}
		//int year=2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		/*
		 * System.out.println(
		 * "select (select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where (mst_shopper_details.brand_id='"
		 * + brand + "')) and select_option_text='" + gbBean.getAnswer() +
		 * "' and subquestion_id='" + gbBean.getSubQuestion() +
		 * "') as one,(select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where (mst_shopper_details.brand_id='"
		 * + brand + "' )) and subquestion_id='" + gbBean.getSubQuestion() +
		 * "') as total");
		 */
		System.out.println("query to get one value========");
		System.out.println("select (select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model,mst_dealer_outlet where (mst_shopper_details.brand_model_variant_id='"+brand+"'  AND mst_shopper_details.visit_status='published' AND mst_shopper_details.mode_of_contact!='Online Sales Channel' AND  (month(visit_date)='"+mid+"' or '"+mid+"'='all') AND  (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') AND  (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') AND mst_shopper_details.year='"+year+"' AND mst_shopper_details.status='active')) and select_option_text='" + gbBean.getAnswer() + "' and subquestion_id='"+gbBean.getSubQuestion()+"' AND mys_txn_answers.status='active') as one,(select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where (mst_shopper_details.brand_model_variant_id='"+brand+"' AND mst_shopper_details.visit_status='published' AND mst_shopper_details.mode_of_contact!='Online Sales Channel' AND  (month(visit_date)='"+mid+"' or '"+mid+"'='all') AND mst_shopper_details.year='"+year+"' AND mst_shopper_details.status='active')) and subquestion_id='" + gbBean.getSubQuestion() + "' AND mys_txn_answers.status='active') as total");
		return template.queryForObject(
				"select (select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model,mst_dealer_outlet where (mst_shopper_details.brand_model_variant_id='"+brand+"'  AND mst_shopper_details.visit_status='published' AND mst_shopper_details.mode_of_contact!='Online Sales Channel' AND  (month(visit_date)='"+mid+"' or '"+mid+"'='all') AND  (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') AND  (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') AND mst_shopper_details.year='"+year+"' AND mst_shopper_details.status='active')) and select_option_text='" + gbBean.getAnswer() + "' and subquestion_id='"+gbBean.getSubQuestion()+"' AND mys_txn_answers.status='active') as one,(select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where (mst_shopper_details.brand_model_variant_id='"+brand+"' AND mst_shopper_details.visit_status='published' AND mst_shopper_details.mode_of_contact!='Online Sales Channel' AND  (month(visit_date)='"+mid+"' or '"+mid+"'='all') AND mst_shopper_details.year='"+year+"' AND mst_shopper_details.status='active')) and subquestion_id='" + gbBean.getSubQuestion() + "' AND mys_txn_answers.status='active') as total",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gbBean = new GraphBean();

						gbBean.setOneValue(rs.getString("one"));
						System.out.println("One value in brand discountcount"+rs.getString("one"));

						return gbBean;
					}
				});
	}

	public List<GraphBean> getdiscountbrands1(GraphBean gBean,String bid,String mid, String did, String oid, String rid) {
		System.out.println("hi in discount brand1 to get model Id");
		
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region1 = (String) session.getAttribute("region");
		if(bid==null || bid==""){
			bid="1";
		}
		if(mid=="" || mid==null){
			mid="all";
		}
		
		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region1;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }
		
		
		
//		if(did=="" || did==null){
//			did="all";
//		}
//		if(rid=="" || rid==null){
//			rid="all";
//		}
		//int year=2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		final String outlets = gBean.getOutlet_id();
		System.out.println("outlets in  dis"+outlets);
		final String brand = bid;
		final String month = mid;
		final String dealer = did;
		final String outlet = oid;
		final String region = rid;
		System.out.println("brand1 list");
		/*
		 * System.out.println(
		 * "SELECT DISTINCT mst_brand_model.model_name,mst_brand_model.sk_model_id FROM mst_brand_model,mst_shopper_details,mst_brand_model_variant WHERE  mst_brand_model.sk_model_id=mst_brand_model.model_id AND mst_shopper_details.brand_id=mst_brand_model_variant.brand_id  and mst_shopper_details.outlet_id in('"
		 * +outlets+"') and mst_brand_model.model_status='active' order by sk_model_id"
		 * );
		 */
		System.out.println(
				"SELECT DISTINCT mst_brand_model.model_name,mst_brand_model.sk_model_id FROM mst_brand_model,mst_shopper_details,mst_brand_model_variant WHERE mst_shopper_details.brand_model_variant_id=mst_brand_model_variant.sk_variant_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id and mst_shopper_details.outlet_id in("
						+ outlets + ") AND mst_shopper_details.brand_id='"+bid+"' AND mst_shopper_details.year='"+year+"' AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active' AND (month(visit_date)='"+mid+"' or '"+mid+"'='all') and mst_brand_model.model_status='active' order by sk_model_id");
		return template.query(
				"SELECT DISTINCT mst_brand_model.model_name,mst_brand_model.sk_model_id FROM mst_brand_model,mst_shopper_details,mst_brand_model_variant WHERE mst_shopper_details.brand_id=mst_brand_model_variant.brand_id AND mst_shopper_details.brand_model_variant_id=mst_brand_model_variant.sk_variant_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id and mst_shopper_details.outlet_id in("
						+ outlets + ") AND mst_shopper_details.brand_id='"+bid+"' AND mst_shopper_details.year='"+year+"' AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active' AND (month(visit_date)='"+mid+"' or '"+mid+"'='all') and mst_brand_model.model_status='active' order by sk_model_id", new RowMapper<GraphBean>() {
							public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
								GraphBean gBean = new GraphBean();
								gBean.setSk_model_id(rs.getString("sk_model_id"));
								gBean.setModel_name(rs.getString("model_name"));
								System.out.println("model names==="+rs.getString("model_name"));
								gBean.setOutlets(outlets);
								List<GraphBean> data = getdiscountbrands(gBean, brand,month,dealer,outlet,region);
								gBean.setAnswerDetails(data);
								return gBean;
							}
						});
	}
	public List<GraphBean> getdiscountbrands(GraphBean gBean, final String brand,String mid,String did,String oid,String rid) {
		System.out.println("hi this this to get varient details");
		System.out.println(
				"SELECT distinct sk_model_id,sk_variant_id,mst_brand_model.model_name,mst_brand_model_variant.variant_name FROM mst_shopper_details,mst_brand_model,mst_brand_model_variant where  mst_brand_model.sk_model_id=mst_brand_model_variant.model_id AND mst_shopper_details.brand_id=mst_brand_model_variant.brand_id and outlet_id in ("
						+ gBean.getOutlets() + ") AND mst_shopper_details.visit_status='published' AND mst_shopper_details.mode_of_contact!='Online Sales Channel'  and mst_brand_model_variant.model_id='" + gBean.getSk_model_id()
						+ "' order by sk_model_id");
		return template.query(
				"SELECT distinct sk_model_id,sk_variant_id,mst_brand_model.model_name,mst_brand_model_variant.variant_name FROM mst_shopper_details,mst_brand_model,mst_brand_model_variant where  mst_brand_model.sk_model_id=mst_brand_model_variant.model_id AND mst_shopper_details.brand_id=mst_brand_model_variant.brand_id and outlet_id in ("
						+ gBean.getOutlets() + ") AND mst_shopper_details.visit_status='published' AND mst_shopper_details.mode_of_contact!='Online Sales Channel' and mst_brand_model_variant.model_id='" + gBean.getSk_model_id()
						+ "' order by sk_model_id", 
						new RowMapper<GraphBean>() {
							public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
								GraphBean gBean = new GraphBean();
								gBean.setSk_model_id(rs.getString("sk_model_id"));
								gBean.setBrand(rs.getString("sk_variant_id"));
                                  System.out.println("model Id in getdiscountbrands======"+rs.getString("sk_model_id"));
                                  System.out.println("variant Id in getdiscountbrands======"+rs.getString("sk_variant_id"));
								GraphBean getsubquest = getDiscountOfferedSubQuestionNumber(gBean,brand);
								gBean.setSubQuestion(getsubquest.getSubQuestion());
								List<GraphBean> data = getDiscountPrices(gBean,brand,mid,did,oid,rid);
								gBean.setAnswerDetails(data);
								gBean.setModel_name(rs.getString("model_name"));
								System.out.println("model name in discount brands==="+rs.getString("model_name"));
								gBean.setVarient_name(rs.getString("variant_name"));
								System.out.println("varient name in discount brands==="+rs.getString("variant_name"));
								return gBean;
							}
						});
	}
	
	public GraphBean getBranddiscountpiechart(GraphBean gBean,String mid) {
		final String brand = gBean.getBrand_id();
		GraphBean subquestion = getDiscountOfferedSubQuestionNumber(gBean,brand);
		final String subquestionid = subquestion.getSubQuestion();
		if(mid=="" || mid==null){
			mid="all";
		}
		//int year=2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		/*
		 * System.out.println(
		 * "select (select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where mst_shopper_details.brand_id=mst_brand_model.brand_id and outlet_id in("
		 * +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		 * +mid+"'='all') AND year='"
		 * +year+"' AND visit_status='published') and (select_option_text!='Amount Not Mentioned' and select_option_text!='No Discount') and subquestion_id='"
		 * +subquestionid+"' and standard_number='IN-6') as Yes,(select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where mst_shopper_details.brand_id=mst_brand_model.brand_id and outlet_id in("
		 * +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		 * +mid+"'='all') AND year='"
		 * +year+"' AND visit_status='published') and select_option_text='Amount Not Mentioned' and subquestion_id='"
		 * +subquestionid+"' and standard_number='IN-6') as amount,(select count(distinct shopper_id) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where mst_shopper_details.brand_id=mst_brand_model.brand_id and outlet_id in("
		 * +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		 * +mid+"'='all') AND year='"
		 * +year+"' AND visit_status='published') and select_option_text='No Discount' and subquestion_id='"
		 * +subquestionid+"' and standard_number='IN-6') as No,(select count(distinct shopper_id) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where mst_shopper_details.brand_id=mst_brand_model.brand_id and outlet_id in("
		 * +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		 * +mid+"'='all') AND year='"
		 * +year+"' AND visit_status='published') and standard_number='IN-6') as total"
		 * );
		 */
		 
		//System.out.println(
				//"select (select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where mst_shopper_details.brand_id=mst_brand_model.brand_id and outlet_id in("+gBean.getOutlet_id()+")) and (select_option_text!='Amount Not Mentioned' and select_option_text!='No Discount') and subquestion_id='"+subquestionid+"' and standard_number='IN-6') as Yes,(select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where mst_shopper_details.brand_id=mst_brand_model.brand_id and outlet_id in("+gBean.getOutlet_id()+")) and select_option_text='Amount Not Mentioned' and subquestion_id='"+subquestionid+"' and standard_number='IN-6') as amount,(select count(distinct shopper_id) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where mst_shopper_details.brand_id=mst_brand_model.brand_id and outlet_id in("+gBean.getOutlet_id()+")) and select_option_text='No Discount' and subquestion_id='"+subquestionid+"' and standard_number='IN-6') as No,(select count(distinct shopper_id) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where mst_shopper_details.brand_id=mst_brand_model.brand_id and outlet_id in("+gBean.getOutlet_id()+")) and standard_number='IN-6') as total");
		System.out.println(
				"select (select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where outlet_id in("
		  +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		  +mid+"'='all') AND year='"
		  +year+"' AND visit_status='published') and (select_option_text!='Amount Not Mentioned' and select_option_text!='No Discount') and subquestion_id='"
		  +subquestionid+"' and standard_number='IN-6' AND mys_txn_answers.status='active') as Yes,(select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where outlet_id in("
		  +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		  +mid+"'='all') AND year='"
		  +year+"' AND visit_status='published') and select_option_text='Amount Not Mentioned' and subquestion_id='"
		  +subquestionid+"' and standard_number='IN-6' AND mys_txn_answers.status='active') as amount,(select count(distinct shopper_id) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where outlet_id in("
		  +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		  +mid+"'='all') AND year='"
		  +year+"' AND visit_status='published') and select_option_text='No Discount' and subquestion_id='"
		  +subquestionid+"' and standard_number='IN-6' AND mys_txn_answers.status='active') as No,(select count(distinct shopper_id) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where outlet_id in("
		  +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		  +mid+"'='all') AND year='"
		  +year+"' AND visit_status='published') and standard_number='IN-6' AND mys_txn_answers.status='active') as total");
		return template.queryForObject(
				"select (select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where outlet_id in("
		  +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		  +mid+"'='all') AND year='"
		  +year+"' AND visit_status='published') and (select_option_text!='Amount Not Mentioned' and select_option_text!='No Discount') and subquestion_id='"
		  +subquestionid+"' and standard_number='IN-6' AND mys_txn_answers.status='active') as Yes,(select count(select_option_text) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where outlet_id in("
		  +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		  +mid+"'='all') AND year='"
		  +year+"' AND visit_status='published') and select_option_text='Amount Not Mentioned' and subquestion_id='"
		  +subquestionid+"' and standard_number='IN-6' AND mys_txn_answers.status='active') as amount,(select count(distinct shopper_id) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where outlet_id in("
		  +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		  +mid+"'='all') AND year='"
		  +year+"' AND visit_status='published') and select_option_text='No Discount' and subquestion_id='"
		  +subquestionid+"' and standard_number='IN-6' AND mys_txn_answers.status='active') as No,(select count(distinct shopper_id) from mys_txn_answers where shopper_id in (SELECT  distinct sk_shopper_id FROM mst_shopper_details,mst_brand_model where outlet_id in("
		  +gBean.getOutlet_id()+") AND (month(visit_date)='"+mid+"' or '"
		  +mid+"'='all') AND year='"
		  +year+"' AND visit_status='published') and standard_number='IN-6' AND mys_txn_answers.status='active') as total",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean qBean = new GraphBean();
						DecimalFormat f = new DecimalFormat("#.##");
						f.setRoundingMode(RoundingMode.CEILING);
						qBean.setYes(String.valueOf(f.format(
								(Double.parseDouble(rs.getString("yes")) / Double.parseDouble(rs.getString("total")))
										* 100)));
						qBean.setNo(String.valueOf(f.format(
								(Double.parseDouble(rs.getString("no")) / Double.parseDouble(rs.getString("total")))
										* 100)));
						qBean.setStatus(String.valueOf(f.format(
								(Double.parseDouble(rs.getString("amount")) / Double.parseDouble(rs.getString("total")))
										* 100)));

						System.out.println(rs.getString("yes") + "cfgvhb" + rs.getString("no") + "ghj"
								+ rs.getString("amount") + "rdtcftgv" + rs.getString("total"));
						return qBean;
					}
				});
	}
	/***********discount analysis manoj d****/
	/************manoj d training need analysis*********/
	public List<QuestionnaireBean> trainingneedanalysis(QuestionnaireBean qBean,GraphBean gBean,String rid,String did,String oid,String bid,String mid) {

		final String storeVisitId = qBean.getShopperIds();
		final String standardNumber = qBean.getStandard_number();
		
		    HttpSession session = request.getSession(true);
			String dealers = (String) session.getAttribute("dealers");
			String roleId = (String) session.getAttribute("role_id");
			String region1 = (String) session.getAttribute("region");			
			if(roleId.equals("7"))
			{
				did=dealers;
				rid=region1;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
			
			
//		if(rid==null || rid==""){
//			rid="All";
//		}
//		if(did==null || did==""){
//			did="All";
//		}
		if(oid==null || oid==""){
			oid="All";
		}
		if(mid==null || mid==""){
			mid="All";
		}
		String brand= bid;
		String outlet = oid;
		String dealer= did;
		String region = rid;
		String month = mid;
		String sql = "";
		if (qBean.getStandard_number().equals("2.2")) {
			sql = "SELECT * FROM(SELECT p.subquestion_id,p.question_id,( CASE shopper_id WHEN @curType THEN @curRow := @curRow + 1 ELSE @curRow := 1 AND @curType := shopper_id END ) + 0 AS rank FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE standard_number='"+qBean.getStandard_number()+"'  AND question_id='15' AND status='active' GROUP BY rank ORDER BY shopper_id,subquestion_id asc) as res1 LEFT JOIN (SELECT sk_subquestion_id,p.subquestion,question_id,(@row:=@row+1) + 0 AS sequence FROM mst_questionare_subquestion p ,mst_questionare q,(SELECT @row := 0) r WHERE standard_number='"+qBean.getStandard_number()+"' AND question_id=sk_question_id GROUP BY sk_subquestion_id ORDER BY sk_subquestion_id) as res2 ON res1.rank=res2.sequence ";
		} else if (qBean.getStandard_number().equals("2.4")) {
			sql = "SELECT * FROM(SELECT p.subquestion_id,p.question_id,( CASE shopper_id  WHEN @curType THEN @curRow := @curRow + 1  ELSE @curRow := 1 AND @curType := shopper_id END ) + 0 AS rank FROM  mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE standard_number='"+qBean.getStandard_number()+"' AND status='active' GROUP BY rank ORDER BY  shopper_id,subquestion_id asc) as res1 LEFT JOIN (SELECT sk_subquestion_id,p.subquestion,question_id,(@row:=@row+1) + 0 AS sequence FROM  mst_questionare_subquestion p ,mst_questionare q,(SELECT @row := 0) r WHERE standard_number='"+qBean.getStandard_number()+"' AND question_id=sk_question_id GROUP BY sk_subquestion_id ORDER BY sk_subquestion_id) as res2 ON res1.rank=res2.sequence ";
		} else {
			sql = "SELECT * FROM(SELECT p.subquestion_id,p.question_id,( CASE shopper_id WHEN @curType THEN @curRow := @curRow + 1 ELSE @curRow := 1 AND @curType := shopper_id END ) + 0 AS rank FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE standard_number='"+qBean.getStandard_number()+"' AND status='active' GROUP BY rank ORDER BY shopper_id,subquestion_id asc) as res1 LEFT JOIN (SELECT sk_subquestion_id,p.subquestion,(@row:=@row+1) + 0 AS sequence FROM mst_questionare_subquestion p ,mst_questionare q,(SELECT @row := 0) r WHERE standard_number='"+qBean.getStandard_number()+"' AND question_id=sk_question_id GROUP BY sk_subquestion_id ORDER BY sk_subquestion_id) as res2 ON res1.rank=res2.sequence";
		}
		System.out.println(sql);
		return template.query(sql, new RowMapper<QuestionnaireBean>() {
			public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
				QuestionnaireBean qBean = new QuestionnaireBean();

				qBean.setSubQuestion(rs.getString("subquestion"));
				qBean.setSk_question_id(rs.getString("question_id"));
				qBean.setSk_subquestion_id(rs.getString("subquestion_id"));
				qBean.setShopperIds(storeVisitId);
				qBean.setStandard_number(standardNumber);
				QuestionnaireBean getTrainingscore = trainingneedanalysisscore(qBean, brand,outlet,dealer,region,month);
				qBean.setScore(getTrainingscore.getScore());
				return qBean;
			}
		});
	}
	public QuestionnaireBean trainingneedanalysisscore(QuestionnaireBean qBean, String brand,String outlet,String dealer,String region,String month) {

		// int year = Calendar.getInstance().get(Calendar.YEAR);
		
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
		//int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
try {
	

		String sql = "";

		if (qBean.getStandard_number().equals("IN-9")) {
			//sql = "select *,round((yescount/total)*100,2) as score FROM(select SUM((CASE WHEN select_option_text='5' THEN 1 ELSE 0 END)) as yescount,sub_question_text,subquestion_id,standard_number,(SELECT COUNT(DISTINCT(shopper_id))*5 as total_count1 FROM mys_txn_answers WHERE subquestion_id='"+qBean.getSk_subquestion_id()+"' and shopper_id IN ("+qBean.getShopperIds()+") AND standard_number='"+qBean.getStandard_number()+"') as total FROM mys_txn_answers WHERE subquestion_id='"+qBean.getSk_subquestion_id()+"' and shopper_id IN ("+qBean.getShopperIds()+") AND standard_number='"+qBean.getStandard_number()+"' GROUP by subquestion_id) as res1";
			// *5 is for standard numb IN-9
			sql="select *,round((yescount/(total*5))*100,2) as score FROM(select SUM(select_option_text) as yescount,sub_question_text,subquestion_id,standard_number,(SELECT COUNT(DISTINCT(shopper_id)) as total_count1 FROM mys_txn_answers WHERE subquestion_id='"+qBean.getSk_subquestion_id()+"' and mys_txn_answers.status='active' AND shopper_id IN (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) AND standard_number='"+qBean.getStandard_number()+"') as total FROM mys_txn_answers WHERE subquestion_id='"+qBean.getSk_subquestion_id()+"' and shopper_id IN (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) AND standard_number='"+qBean.getStandard_number()+"' GROUP by subquestion_id) as res1";
		} else {
			sql = "select *,round((yescount/total)*100,2) as score FROM(select SUM((CASE WHEN select_option_text='yes' THEN 1 ELSE 0 END)) as yescount,sub_question_text,subquestion_id,standard_number,(SELECT COUNT(DISTINCT(shopper_id)) as total_count1 FROM mys_txn_answers WHERE subquestion_id='"+qBean.getSk_subquestion_id()+"' and mys_txn_answers.status='active' AND shopper_id IN (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) AND standard_number='"+qBean.getStandard_number()+"') as total FROM mys_txn_answers WHERE subquestion_id='"+qBean.getSk_subquestion_id()+"' and shopper_id IN (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) AND standard_number='"+qBean.getStandard_number()+"' GROUP by subquestion_id) as res1";
		}
		//System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<QuestionnaireBean>() {
			public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
				QuestionnaireBean qBean = new QuestionnaireBean();

				qBean.setScore(rs.getString("score"));

				return qBean;
			}
		});} 
		catch (Exception e) {
			// TODO: handle exception
		}
return qBean;
	}
	
	public QuestionnaireBean getProductAndDigitalPercentages(QuestionnaireBean qBean, String brand,String outlet,String dealer,String region,String month) {

		// int year = Calendar.getInstance().get(Calendar.YEAR);
		
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
		//int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		 
		//String sql = "select round((SELECT count(shopper_id) FROM mys_txn_formulaanswer WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) and standard_num='"+qBean.getStandard_number()+"')/(SELECT count(distinct shopper_id) FROM `mys_txn_answers` WHERE standard_number='"+qBean.getStandard_number()+"' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')))*100,2) as product_percentage,round((SELECT count(select_option_text) FROM(SELECT p.*,( CASE shopper_id WHEN @curType THEN @curRow := @curRow + 1 ELSE @curRow := 1 AND @curType := shopper_id END ) + 0 AS ranking FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE standard_number='"+qBean.getStandard_number()+"' ORDER BY shopper_id,subquestion_id asc) as t WHERE t.ranking=7 and select_option_text='yes' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')))/(SELECT count(distinct shopper_id) FROM `mys_txn_answers` WHERE standard_number='"+qBean.getStandard_number()+"' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')))*100,2) as digital";
		String sql = "select round((SELECT count(shopper_id) FROM mys_txn_formulaanswer WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
				+ year + "' and mst_shopper_details.brand_id='" + brand + "' and (mst_shopper_details.dealer_id='"
				+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '"
				+ outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
				+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
				+ "'='ALL') AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active') and standard_num='"
				+ qBean.getStandard_number()
				+ "' AND mys_txn_formulaanswer.points>0 AND mys_txn_formulaanswer.status='active')/(SELECT count(shopper_id) FROM mys_txn_formulaanswer WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
				+ year + "' and mst_shopper_details.brand_id='" + brand + "' and (mst_shopper_details.dealer_id='"
				+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '"
				+ outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
				+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
				+ "'='ALL') AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active' AND mys_txn_formulaanswer.status='active') and standard_num='"
				+ qBean.getStandard_number()
				+ "')*100,2) as product_percentage,round((SELECT count(DISTINCT shopper_id) FROM mys_txn_answers WHERE standard_number='2.4' and subquestion_id=25 and select_option_text='yes' AND mys_txn_answers.status='active' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
				+ year + "' and mst_shopper_details.brand_id='" + brand + "' and (mst_shopper_details.dealer_id='"
				+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '"
				+ outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
				+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
				+ "'='ALL') AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active'))/(SELECT count(distinct shopper_id) FROM mys_txn_answers WHERE standard_number='2.4' and subquestion_id=25 AND mys_txn_answers.status='active' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
				+ year + "' and mst_shopper_details.brand_id='" + brand + "' and (mst_shopper_details.dealer_id='"
				+ dealer + "' or '" + dealer + "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '"
				+ outlet + "'='ALL') and (month(visit_date)='" + month + "' or '" + month
				+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
				+ "'='ALL') AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active'))*100,2) as digital";
		System.out.println(sql);
		System.out.println("product AND digital per QUERY");
		return template.queryForObject(sql, new RowMapper<QuestionnaireBean>() {
			public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
				QuestionnaireBean qBean = new QuestionnaireBean();

				qBean.setOne(rs.getString("product_percentage"));
				qBean.setTwo(rs.getString("digital"));

				return qBean;
			}
		});
	}
	public List<QuestionnaireBean> getdayCount(QuestionnaireBean qBean, final String standard_number,String brand,String outlet,String dealer,String region,String month) {
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
		//int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(
				"select sk_shopper_id from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and (mst_shopper_details.dealer_id='" + dealer + "' or '" + dealer
						+ "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '" + outlet
						+ "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region + "'='ALL') AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active')");
		return template.query(
				"select sk_shopper_id from mst_shopper_details where sk_shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and (mst_shopper_details.dealer_id='" + dealer + "' or '" + dealer
						+ "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '" + outlet
						+ "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region + "'='ALL') AND mst_shopper_details.visit_status='published' AND mst_shopper_details.status='active')",
				new RowMapper<QuestionnaireBean>() {
					int b;
					int count;
					int days;

					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setShopperId(rs.getString("sk_shopper_id"));
						System.out.println("shopperId in day count method==="+rs.getString("sk_shopper_id"));
						QuestionnaireBean data = getdayCount1(qBean, standard_number);
						b = b + Integer.parseInt(data.getCount());
						count = rs.getRow();
						System.out.println("count data====="+rs.getRow());
						days = Math.round(b / count);
						qBean.setDate(String.valueOf(days));
						System.out.println("final day count after cal."+String.valueOf(days));
						return qBean;
					}
				});
	}
	public QuestionnaireBean getdayCount1(QuestionnaireBean qBean, String standard_number) {
		System.out.println("SELECT IFNULL(datediff((select date FROM `mys_txn_answers` where standard_number='"+standard_number+"' and shopper_id='"+qBean.getShopperId()+"' AND mys_txn_answers.status='active' order by sk_answer_id desc limit 1),(select visit_date from mst_shopper_details where sk_shopper_id='"+qBean.getShopperId()+"' AND mst_shopper_details.status='active')),0) as days");
		return template
				.queryForObject("SELECT IFNULL(datediff((select date FROM `mys_txn_answers` where standard_number='"+standard_number+"' and shopper_id='"+qBean.getShopperId()+"' AND mys_txn_answers.status='active' order by sk_answer_id desc limit 1),(select visit_date from mst_shopper_details where sk_shopper_id='"+qBean.getShopperId()+"' AND mst_shopper_details.status='active')),0) as days", 
						new RowMapper<QuestionnaireBean>() {
							public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
								QuestionnaireBean qBean = new QuestionnaireBean();
								qBean.setCount(rs.getString("days"));
								System.out.println("days in day count1===" + qBean.getCount());
								return qBean;
							}
						});
	}
	/****************training need analysis*************/

	public List<GraphBean> getFinancingorleasingoptions(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		 //int year = 2019;
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
         
		 if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
			
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
	     int  year;
	       String standard_number="";
	       String sub_question_id="";
	        if(gBean.getYear()==null || gBean.getYear()=="")
	        {
	          year=Calendar.getInstance().get(Calendar.YEAR);
	          standard_number="2.7";
	          sub_question_id="588";
	          System.out.println("current year in if auto response"+year);
	        }
	        else if(gBean.getYear().equals("2019"))
	        {
	          year=Integer.parseInt(gBean.getYear());
	          System.out.println("current year in  else if auto response"+year);
	          standard_number="4.2";
	          sub_question_id="all";
	        }
	        else
	        {
	          year=Integer.parseInt(gBean.getYear());
	          System.out.println("current year in  else auto response"+year);
	          standard_number="2.7";
	          sub_question_id="588";
	        }
		String sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,TRUNCATE(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"')  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setYear(String.valueOf(year));
				gBean.setYesCount(rs.getString("yespercentage"));
				gBean.setNoCount(rs.getString("nopercentage"));
				return gBean;
			}
		});
	
}
	/*******new graph implemented in 2020 **********/
	public List<GraphBean> getCourtesyGraphoptions(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		 //int year = 2019;
		    HttpSession session = request.getSession(true);
			String dealers = (String) session.getAttribute("dealers");
			String roleId = (String) session.getAttribute("role_id");
			String region = (String) session.getAttribute("region");

		int year = Calendar.getInstance().get(Calendar.YEAR);
		String standard_number ="5a.1";
		 if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
			
		
		
		
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		String sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,TRUNCATE(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"'  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setYear(String.valueOf(year));
				gBean.setYesCount(rs.getString("yespercentage"));
				gBean.setNoCount(rs.getString("nopercentage"));
				return gBean;
			}
		});
	
}
	/*********new graph end implemented in 2020 ************/
	public List<GraphBean> gettestdriveactivelyoffered(GraphBean gBean, String did, String bid,
				String rid, String oid, String month) {
			// int year = 2019;
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");		
		 if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
			
		
		
		
//			 if(did==null || did=="") {
//				 did="all";
//			 }
//			 if(rid==null || rid=="") {
//				 rid="all";
//			 }
			 if(oid==null || oid=="") {
				 oid="all";
			 }
			 if(month==null || month=="") {
				 month="all";
			 }
			 int  year;
		       String standard_number="";
		       String sql="";
		        if(gBean.getYear()==null || gBean.getYear()=="")
		        {
		          year=Calendar.getInstance().get(Calendar.YEAR);
		          standard_number="3.1";
		          
		          System.out.println("current year in if auto response"+year);
		          //sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,TRUNCATE(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes, actively offered'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No, not at all' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id  left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";
		          sql="select *,round((yes1count/totalcount)*100,2) as yes1percentage from(SELECT mon,month,totalcount,yescount,nocount,(totalcount-nocount) as yes1count, TRUNCATE((((totalcount-yescount)/nocount)*100),2)as yespercentage,TRUNCATE(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes, actively offered'  and select_option_text is not null THEN 1 ELSE null  END)  AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No, not at all' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  mys_txn_answers left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id  left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc) as res1";
					System.out.println(sql);
					return template.query(sql, new RowMapper<GraphBean>() {
						public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
							GraphBean gBean = new GraphBean();
							gBean.setMonth(rs.getString("month"));
							gBean.setYear(String.valueOf(year));
							gBean.setYesCount(rs.getString("yes1percentage"));
							gBean.setNoCount(rs.getString("nopercentage"));
							return gBean;
						}
					});
		        }
		        else if(gBean.getYear().equals("2019"))
		        {
		          year=Integer.parseInt(gBean.getYear());
		          System.out.println("current year in  else if auto response"+year);
		          standard_number="4.1";
		          sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,TRUNCATE(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id  left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";
					System.out.println(sql);
					return template.query(sql, new RowMapper<GraphBean>() {
						public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
							GraphBean gBean = new GraphBean();
							gBean.setMonth(rs.getString("month"));
							gBean.setYear(String.valueOf(year));
							gBean.setYesCount(rs.getString("yespercentage"));
							gBean.setNoCount(rs.getString("nopercentage"));
							return gBean;
						}
					});
		        }
		        else
		        {
		          year=Integer.parseInt(gBean.getYear());
		          System.out.println("current year in  else auto response"+year);
		          standard_number="3.1";
		           sql="select *,round((yes1count/totalcount)*100,2) as yes1percentage from(SELECT mon,month,totalcount,yescount,nocount,(totalcount-nocount) as yes1count, TRUNCATE((((totalcount-yescount)/nocount)*100),2)as yespercentage,TRUNCATE(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes, actively offered'  and select_option_text is not null THEN 1 ELSE null  END)  AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No, not at all' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  mys_txn_answers left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id  left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc) as res1";
					System.out.println(sql);
					return template.query(sql, new RowMapper<GraphBean>() {
						public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
							GraphBean gBean = new GraphBean();
							gBean.setMonth(rs.getString("month"));
							gBean.setYear(String.valueOf(year));
							gBean.setYesCount(rs.getString("yes1percentage"));
							gBean.setNoCount(rs.getString("nopercentage"));
							return gBean;
						}
					});
		        }
}
	public List<GraphBean> getindividualizedOffergson(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		// int year = 2019;
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		 if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
			
		
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
	       int  year;
		   String standard_number="";
		   String sub_question_id="";
		   String sql="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      System.out.println("current year in if dealership"+year);
		      standard_number="4.1";
		      sub_question_id="542";
		       //sql="SELECT mon,month,totalcount,Yeshandedoverduringtheconsultationonofficialpaper,Yesonofficialpapersentperemailduringrightaftertheconsultation,YesIgotanwrittenofferbutnotonofficialpaper,NoIdidnotreceiveanofferatall,TRUNCATE(((Yeshandedoverduringtheconsultationonofficialpaper/totalcount)*100),2)as Yeshandedoverduringtheconsultationonofficialpaperp,TRUNCATE(((Yesonofficialpapersentperemailduringrightaftertheconsultation/totalcount)*100),2)as Yesonofficialpapersentperemailduringrightaftertheconsultationp,TRUNCATE(((YesIgotanwrittenofferbutnotonofficialpaper/totalcount)*100),2)as YesIgotanwrittenofferbutnotonofficialpaperp, TRUNCATE(((NoIdidnotreceiveanofferatall/totalcount)*100),2)as NoIdidnotreceiveanofferatallp FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes, handed over during the consultation on official paper'  and select_option_text is not null THEN 1 ELSE null  END) AS Yeshandedoverduringtheconsultationonofficialpaper, count(CASE  WHEN mys_txn_answers.select_option_text='Yes, on official paper sent per e-mail during/right after the consultation' and select_option_text is not null THEN 1 ELSE null  END ) AS Yesonofficialpapersentperemailduringrightaftertheconsultation,count(CASE  WHEN mys_txn_answers.select_option_text='Yes, I got an written offer but not on official paper' and select_option_text is not null THEN 1 ELSE null  END ) AS YesIgotanwrittenofferbutnotonofficialpaper, count(CASE  WHEN mys_txn_answers.select_option_text='No, I did not receive an offer at all' and select_option_text is not null THEN 1 ELSE null  END ) AS NoIdidnotreceiveanofferatall from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='4.3' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
		      sql="SELECT mon,month,totalcount,YesCount,noCount,TRUNCATE(((YesCount/totalcount)*100),2)as YesCountPer,TRUNCATE(((noCount/totalcount)*100),2)as noCountPer FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS YesCount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS noCount  from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"') and mst_shopper_details.visit_status='published'  and mst_shopper_details.mode_of_contact!='Online sales channel' and mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
				System.out.println(sql);
				return template.query(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setMonth(rs.getString("month"));
						gBean.setYear(String.valueOf(year));
						gBean.setYeshandedoverduringtheconsultationonofficialpaperp(rs.getString("YesCountPer"));
						//gBean.setYesIgotanwrittenofferbutnotonofficialpaperp(rs.getString("yesIgotanwrittenofferbutnotonofficialpaperp"));
						//gBean.setYesonofficialpapersentperemailduringrightaftertheconsultationp(rs.getString("yesonofficialpapersentperemailduringrightaftertheconsultationp"));
						gBean.setNoIdidnotreceiveanofferatallp(rs.getString("noCountPer"));
						return gBean;
					}
				});
              
             
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      System.out.println("current year in else  if dealership"+year);
		      standard_number= "4.3";
		      sub_question_id="";
		     // sql="SELECT mon,month,totalcount,YesCount,noCount,TRUNCATE(((YesCount/totalcount)*100),2)as YesCountPer,TRUNCATE(((noCount/totalcount)*100),2)as noCountPer FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS YesCount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS noCount  from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"') and mst_shopper_details.visit_status='published'  and mst_shopper_details.mode_of_contact!='Online sales channel' and mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
		      sql="SELECT mon,month,totalcount,Yeshandedoverduringtheconsultationonofficialpaper,Yesonofficialpapersentperemailduringrightaftertheconsultation,YesIgotanwrittenofferbutnotonofficialpaper,NoIdidnotreceiveanofferatall,TRUNCATE(((Yeshandedoverduringtheconsultationonofficialpaper/totalcount)*100),2)as Yeshandedoverduringtheconsultationonofficialpaperp,TRUNCATE(((Yesonofficialpapersentperemailduringrightaftertheconsultation/totalcount)*100),2)as Yesonofficialpapersentperemailduringrightaftertheconsultationp,TRUNCATE(((YesIgotanwrittenofferbutnotonofficialpaper/totalcount)*100),2)as YesIgotanwrittenofferbutnotonofficialpaperp, TRUNCATE(((NoIdidnotreceiveanofferatall/totalcount)*100),2)as NoIdidnotreceiveanofferatallp FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes, handed over during the consultation on official paper'  and select_option_text is not null THEN 1 ELSE null  END) AS Yeshandedoverduringtheconsultationonofficialpaper, count(CASE  WHEN mys_txn_answers.select_option_text='Yes, on official paper sent per e-mail during/right after the consultation' and select_option_text is not null THEN 1 ELSE null  END ) AS Yesonofficialpapersentperemailduringrightaftertheconsultation,count(CASE  WHEN mys_txn_answers.select_option_text='Yes, I got an written offer but not on official paper' and select_option_text is not null THEN 1 ELSE null  END ) AS YesIgotanwrittenofferbutnotonofficialpaper, count(CASE  WHEN mys_txn_answers.select_option_text='No, I did not receive an offer at all' and select_option_text is not null THEN 1 ELSE null  END ) AS NoIdidnotreceiveanofferatall from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='4.3' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
				System.out.println(sql);
				return template.query(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setMonth(rs.getString("month"));
						gBean.setYear(String.valueOf(year));
						gBean.setYeshandedoverduringtheconsultationonofficialpaperp(rs.getString("yeshandedoverduringtheconsultationonofficialpaperp"));
						gBean.setYesIgotanwrittenofferbutnotonofficialpaperp(rs.getString("yesIgotanwrittenofferbutnotonofficialpaperp"));
						gBean.setYesonofficialpapersentperemailduringrightaftertheconsultationp(rs.getString("yesonofficialpapersentperemailduringrightaftertheconsultationp"));
						gBean.setNoIdidnotreceiveanofferatallp(rs.getString("noIdidnotreceiveanofferatallp"));
						return gBean;
					}
				});
             
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	 System.out.println("current year in else dealership"+year);
		    	 standard_number="4.1";
			      sub_question_id="542";
			      sql="SELECT mon,month,totalcount,YesCount,noCount,TRUNCATE(((YesCount/totalcount)*100),2)as YesCountPer,TRUNCATE(((noCount/totalcount)*100),2)as noCountPer FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS YesCount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS noCount  from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and (subquestion_id='"+sub_question_id+"' oR 'ALL'='"+sub_question_id+"') and mst_shopper_details.visit_status='published'  and mst_shopper_details.mode_of_contact!='Online sales channel' and mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
					System.out.println(sql);
					return template.query(sql, new RowMapper<GraphBean>() {
						public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
							GraphBean gBean = new GraphBean();
							gBean.setMonth(rs.getString("month"));
							gBean.setYear(String.valueOf(year));
							gBean.setYeshandedoverduringtheconsultationonofficialpaperp(rs.getString("YesCountPer"));
							//gBean.setYesIgotanwrittenofferbutnotonofficialpaperp(rs.getString("yesIgotanwrittenofferbutnotonofficialpaperp"));
							//gBean.setYesonofficialpapersentperemailduringrightaftertheconsultationp(rs.getString("yesonofficialpapersentperemailduringrightaftertheconsultationp"));
							gBean.setNoIdidnotreceiveanofferatallp(rs.getString("noCountPer"));
							return gBean;
						}
					});   
		    }
		
	}
	public List<GraphBean> getcorrespondingtimeframe(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		// int year = 2019;
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		 if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
			
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
			int  year;
			   String standard_number="";
			   String sql="";
			    if(gBean.getYear()==null || gBean.getYear()=="")
			    {
			      year=Calendar.getInstance().get(Calendar.YEAR);
			      System.out.println("current year in if dealership"+year);
			      standard_number="5a.2.2 or 5a.2.1";
			       sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,TRUNCATE(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_id in (1824,1827)  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_id in (1825,1826,1828,1829) and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE  mys_txn_answers.question_id IN(327,328) AND mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
					System.out.println(sql);
					return template.query(sql, new RowMapper<GraphBean>() {
						public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
							GraphBean gBean = new GraphBean();
							gBean.setMonth(rs.getString("month"));
							gBean.setYear(String.valueOf(year));
							gBean.setYesCount(rs.getString("yespercentage"));
							gBean.setNoCount(rs.getString("nopercentage"));
							return gBean;
						}
					});
			    }
			    else if(gBean.getYear().equals("2019"))
			    {
			      year=Integer.parseInt(gBean.getYear());
			      System.out.println("current year in else  if dealership"+year);
			      standard_number= "5.2";
			       sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,TRUNCATE(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes, Namely'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='5.2' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
					System.out.println(sql);
					return template.query(sql, new RowMapper<GraphBean>() {
						public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
							GraphBean gBean = new GraphBean();
							gBean.setMonth(rs.getString("month"));
							gBean.setYear(String.valueOf(year));
							gBean.setYesCount(rs.getString("yespercentage"));
							gBean.setNoCount(rs.getString("nopercentage"));
							return gBean;
						}
					});
			    }
			    else
			    {
			    	year=Integer.parseInt(gBean.getYear());
			    	 System.out.println("current year in else dealership"+year);
			    	 standard_number="5a.2.2 or 5a.2.1";
			    	  sql="SELECT mon,month,totalcount,yescount,nocount,TRUNCATE(((yescount/totalcount)*100),2)as yespercentage,TRUNCATE(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_id in (1824,1827)  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_id in (1825,1826,1828,1829) and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE mys_txn_answers.question_id IN(327,328) AND mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
						System.out.println(sql);
						return template.query(sql, new RowMapper<GraphBean>() {
							public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
								GraphBean gBean = new GraphBean();
								gBean.setMonth(rs.getString("month"));
								gBean.setYear(String.valueOf(year));
								gBean.setYesCount(rs.getString("yespercentage"));
								gBean.setNoCount(rs.getString("nopercentage"));
								return gBean;
							}
						});   
			    }
	}
	public List<GraphBean> getconfigurationprocess(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		// int year = 2019;
		   // int year = Calendar.getInstance().get(Calendar.YEAR);
		    HttpSession session = request.getSession(true);
			String dealers = (String) session.getAttribute("dealers");
			String roleId = (String) session.getAttribute("role_id");
			String region = (String) session.getAttribute("region");			
			if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
	
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 
		    int  year;
		    String standard_number="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="2.3";
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number="IN-3";
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	standard_number="2.3";
		    }
		 
		String sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id  left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'  GROUP by month)res1 order by mon asc";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setYear(String.valueOf(year));
				gBean.setYesCount(rs.getString("yespercentage"));
				gBean.setNoCount(rs.getString("nopercentage"));
				return gBean;
			}
		});
	}
	
	
	public List<GraphBean> getWhattakehomematerial(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		// int year = 2019;
		  //  int year = Calendar.getInstance().get(Calendar.YEAR);
		    HttpSession session = request.getSession(true);
			String dealers = (String) session.getAttribute("dealers");
			String roleId = (String) session.getAttribute("role_id");
			String region = (String) session.getAttribute("region");
			if(roleId.equals("7"))
			{
				did=dealers;
				rid=region;
			}
		    else  if((did=="" || did==null) &&(rid=="" || rid==null))
	        {
	          did="all";
	          rid="all";
	        }
		    
		    else  if((did!="") &&(rid=="" || rid==null))
		    {
			  did= did;
			  rid="all";	
			}
		    else  if((did=="" || did==null) &&(rid!=""))
		    {
			  did= "all";
			  rid=rid;	
			}
		    else 
	        {
	          did= did;
	          rid=rid;  
	        }
		
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		    int  year;
		    String sql="";
		    String standard_number="";
		    String questionId="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="2.7";
		      questionId="370";
		      sql="SELECT mon,month,totalcount,yescount,round(((yescount/totalcount)*100),2)as yespercentage FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE question_id='"+questionId+"' and select_option_id IN (2029,2031,2033,2035,2037,2039)and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')   and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";
		      System.out.println("in what makes other special"+sql);
		      return template.query(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setMonth(rs.getString("month"));
						gBean.setYear(String.valueOf(year));
						gBean.setYesCount(rs.getString("yespercentage"));
						//gBean.setNoCount(rs.getString("nopercentage"));
						return gBean;
					}
				});
		      
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number="IN-7";
		      questionId="370";
			  sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')   and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";
			  System.out.println("sql in else if"+sql);
			  return template.query(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setMonth(rs.getString("month"));
						gBean.setYear(String.valueOf(year));
						gBean.setYesCount(rs.getString("yespercentage"));
						gBean.setNoCount(rs.getString("nopercentage"));
						return gBean;
					}
				});
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	//standard_number="2.7";
		    	questionId="370";
			     sql="SELECT mon,month,totalcount,yescount,round(((yescount/totalcount)*100),2)as yespercentage FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE question_id='"+questionId+"' and select_option_id IN (2029,2031,2033,2035,2037,2039)and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')   and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";
                System.out.println("in what makes other special in else"+sql);
			    return template.query(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setMonth(rs.getString("month"));
						gBean.setYear(String.valueOf(year));
						gBean.setYesCount(rs.getString("yespercentage"));
						//gBean.setNoCount(rs.getString("nopercentage"));
						return gBean;
					}
				});
		    }
		 //	String sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='IN-7' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')   and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";
		
//		return template.query(sql, new RowMapper<GraphBean>() {
//			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
//				GraphBean gBean = new GraphBean();
//				gBean.setMonth(rs.getString("month"));
//				gBean.setYear(String.valueOf(year));
//				gBean.setYesCount(rs.getString("yespercentage"));
//				gBean.setNoCount(rs.getString("nopercentage"));
//				return gBean;
//			}
//		});
	}
	
	
	public List<GraphBean> getbmwfinancialservice(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {

		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
        if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }
		
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 
		    int  year;
			String standard_number="";
			String subquestion_id="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="4.1";
	          subquestion_id="541";
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number="IN-4";
		      subquestion_id="all";// here for standard number IN -4 there is no subquestion id. so  we are passing all in the subquestionid to get the query output.
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	standard_number="4.1";
		        subquestion_id="541";
		    }
		 
		String sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and (subquestion_id='"+subquestion_id+"' oR 'ALL'='"+subquestion_id+"')  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon";
		System.out.println("BMW financial service"+sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setYear(String.valueOf(year));
				gBean.setYesCount(rs.getString("yespercentage"));
				gBean.setNoCount(rs.getString("nopercentage"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getbuybackprogram(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");

		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }

//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 
		 int  year;
		 String sql="";
         String standard_number="";
         String section_id="";
         String subsection_id="";// we are passing seection id and subsection id because standard number  there are two 2.4 standard numbers.
	        if(gBean.getYear()==null || gBean.getYear()=="")
	        {
	          year=Calendar.getInstance().get(Calendar.YEAR);
	          standard_number="2.4";
	          section_id="3";
	          subsection_id="34"; // here section id and sub section id are passed because 2.4 standard number is repeating for diffrent section.. so to get the yes and no count for In sec B we are passing respective section id and sub section id.
		  	  sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and section_id='"+section_id+"' and subsection_id='"+subsection_id+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";

	        }
	        else if(gBean.getYear().equals("2019"))
	        {
	          year=Integer.parseInt(gBean.getYear());
	          standard_number="4.2";
	  		 sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";

	        }
	        else
	        {
	          year=Integer.parseInt(gBean.getYear());
	          standard_number="2.4";
	          section_id="3";
	          subsection_id="34";
		  	  sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and section_id='"+section_id+"' and subsection_id='"+subsection_id+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";

	        }
		 
		//String sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT month(mst_shopper_details.visit_date)as mon,monthname(mst_shopper_details.visit_date)as month,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='4.2' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon asc";
		System.out.println("buy back program"+sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setYear(String.valueOf(year));
				gBean.setYesCount(rs.getString("yespercentage"));
				gBean.setNoCount(rs.getString("nopercentage"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getcurrentcustomervehiclegson(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }
		
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 
		    int  year;
		    String standard_number="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="4.2";
		    
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number="in-8";
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	standard_number="4.2";
		    }
		 String sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active'   GROUP by month)res1 order by mon asc";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setYear(String.valueOf(year));
				gBean.setYesCount(rs.getString("yespercentage"));
				gBean.setNoCount(rs.getString("nopercentage"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getDealersByRegionid(GraphBean gBean, String bid, String region, String dealers) {
		try {
			if(gBean.getRole_id().contentEquals("2") || gBean.getRole_id().contentEquals("4") || gBean.getRole_id().contentEquals("5")) {
				if(gBean.getRegion_id()=="" || gBean.getRegion_id()==null) {
					
				region="all";
				
				}else {
					region=gBean.getRegion_id();	
				}
				dealers="all";
			}else if(gBean.getRole_id().contentEquals("6") ){
				region=region;
				dealers="all";
			}else {
				region=region;
				dealers=dealers;
			}
			
			}catch (Exception e) {
				// TODO: handle exception
			}
		String sql = "SELECT DISTINCT mst_dealer_outlet.`dealer_id`,mst_dealer.dealer_name from mst_dealer_outlet, mst_dealer WHERE mst_dealer_outlet.region_id='"+region+"'  and mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id and mst_dealer.dealer_status='active' AND (mst_dealer.sk_dealer_id='"+dealers+"' or '"+dealers+"'='all')";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setDealer_id(rs.getString("dealer_id"));
				gBean.setDealer_name(rs.getString("dealer_name"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getDealersbyid(GraphBean gBean, String rid) {
		String sql = "SELECT DISTINCT mst_dealer_outlet.`dealer_id`,mst_dealer.dealer_name from mst_dealer_outlet, mst_dealer WHERE mst_dealer_outlet.region_id='"+rid+"'  and mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id and mst_dealer.dealer_status='active'";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setDealer_id(rs.getString("dealer_id"));
				gBean.setDealer_name(rs.getString("dealer_name"));
				return gBean;
			}
		});
	}
	public List<GraphBean> getofferedbreakdown(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }
		
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 int  year;
			String standard_number="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="4.1.1";
	          
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number="4.2.1";
		      
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	standard_number="4.1.1";
		    }
		 
		String sql="SELECT sk_answer_id,options,COUNT(res2.select_option_id)as count,res1.question_id,total_count,round(((COUNT(res2.select_option_id)/total_count)*100),2)as per from (SELECT mst_questionare_selectoption.sk_answer_id,mst_questionare_selectoption.options,question_id FROM `mst_questionare_selectoption`,mst_questionare WHERE mst_questionare_selectoption.question_id=mst_questionare.sk_question_id  and mst_questionare.standard_number='"+standard_number+"' and mst_questionare.brand_id='"+bid+"' and mst_questionare.mode_of_contact!='online sales channel' and mst_questionare.question_status='active' )as res1 left join (SELECT mys_txn_answers.select_option_id from mys_txn_answers,mst_shopper_details left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id  AND (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and mst_shopper_details.visit_status='published' and mys_txn_answers.status='active')  res2 on res1.sk_answer_id=res2.select_option_id left join (SELECT count(mys_txn_answers.select_option_id)as total_count,mys_txn_answers.question_id from mys_txn_answers,mst_shopper_details WHERE mys_txn_answers.shopper_id=mst_shopper_details.sk_shopper_id  and mst_shopper_details.brand_id='"+bid+"' and mst_shopper_details.mode_of_contact!='Online sales channel' and mst_shopper_details.year='"+year+"' and mys_txn_answers.standard_number='"+standard_number+"' and   (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all') and mst_shopper_details.visit_status='published' and mys_txn_answers.status='active' )res3 on res3.question_id=res1.question_id GROUP by sk_answer_id";
		System.out.println("do nut"+sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setPercentage(rs.getString("per"));
				gBean.setOption_text(rs.getString("options"));
				return gBean;
			}
		});
	}
	
	
	public List<GraphBean> gethandoverfinanceoffer(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {

		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		if(roleId.equals("7"))
		{
			did=dealers;
			rid=region;
		}
	    else  if((did=="" || did==null) &&(rid=="" || rid==null))
        {
          did="all";
          rid="all";
        }
	    
	    else  if((did!="") &&(rid=="" || rid==null))
	    {
		  did= did;
		  rid="all";	
		}
	    else  if((did=="" || did==null) &&(rid!=""))
	    {
		  did= "all";
		  rid=rid;	
		}
	    else 
        {
          did= did;
          rid=rid;  
        }
		
//		 if(did==null || did=="") {
//			 did="all";
//		 }
//		 if(rid==null || rid=="") {
//			 rid="all";
//		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 
		    int  year;
			String standard_number="";
			String subquestion_id="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      standard_number="2.7";
	          subquestion_id="588";
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      standard_number="";
		      subquestion_id="";
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	standard_number="2.7";
		        subquestion_id="588";
		    }
		 
		String sql="SELECT mon,month,totalcount,yescount,nocount,round(((yescount/totalcount)*100),2)as yespercentage,round(((nocount/totalcount)*100),2)as nopercentage FROM(SELECT monthname(mst_shopper_details.visit_date)as month,month(mst_shopper_details.visit_date)as mon,count(* )as totalcount, count(CASE  WHEN mys_txn_answers.select_option_text='Yes'  and select_option_text is not null THEN 1 ELSE null  END) AS yescount, count(CASE  WHEN mys_txn_answers.select_option_text='No' and select_option_text is not null THEN 1 ELSE null  END ) AS nocount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE standard_number='"+standard_number+"' and (subquestion_id='"+subquestion_id+"' oR 'ALL'='"+subquestion_id+"')  and mst_shopper_details.visit_status='published' and mst_shopper_details.mode_of_contact!='Online sales channel' and    mst_shopper_details.brand_id='"+bid+"'  and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all') and mys_txn_answers.status='active' GROUP by month)res1 order by mon";
		System.out.println("hand over finance offer"+sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setYear(String.valueOf(year));
				gBean.setYesCount(rs.getString("yespercentage"));
				gBean.setNoCount(rs.getString("nopercentage"));
				return gBean;
			}
		});
	}
	
	
	
	public List<GraphBean> getcompetionBrandScore(GraphBean gBean) {
		final String outlet = gBean.getOutlets();
		System.out.println("outlets in brand score"+outlet);
		System.out.println(
				"SELECT distinct brand_name,sk_brand_id FROM mst_shopper_details,mst_brand where mst_shopper_details.brand_id=sk_brand_id and mst_shopper_details.brand_id!='2' order by sk_brand_id");
		return template.query(
				"SELECT distinct brand_name,sk_brand_id FROM mst_shopper_details,mst_brand where mst_shopper_details.brand_id=sk_brand_id and mst_shopper_details.brand_id!='2' order by sk_brand_id;",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setBrand_name(rs.getString("brand_name"));
						gBean.setBrand_id(rs.getString("sk_brand_id"));
						gBean.setOutlet_id(outlet);
						GraphBean data = getBrandcompetionScore(gBean);
						gBean.setPercentage(data.getPercentage());
						return gBean;

					}

				});
	}
	protected GraphBean getBrandcompetionScore(GraphBean gBean) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		//int year=2019;
		//String year1="2019";
		String year1=String.valueOf(year);
		System.out.println("year in brand competioion score"+year1);
		//String pe_weightage="";
		//String ct_weightage="";
		//String osc_weightage="";
		
		MysteryShoppingVisitsBean mvBean1=new MysteryShoppingVisitsBean();
        mvBean1=hDao.getScoreWeightage(mvBean1,year1);
        String  pe_weightage="";
        String ct_weightage="";
        String osc_weightage="";
        if(year == 2020) {
        if(gBean.getBrand_id().equals("3") || gBean.getBrand_id().equals("4")) {
        	  pe_weightage="0.50";
             ct_weightage="0.50";
             osc_weightage="0.00";
            System.out.println("pe_weightage in if year 2020"+pe_weightage);
    		System.out.println("ct_weightage in if year 2020"+ct_weightage); 
    		System.out.println("osc_weightage in if year 2020"+osc_weightage);
        }
        else {
        	  pe_weightage=mvBean1.getPe_weightage();
             ct_weightage=mvBean1.getCt_weightage();
             osc_weightage=mvBean1.getOsc_weightage();
            System.out.println("pe_weightage in else year 2020"+pe_weightage);
    		System.out.println("ct_weightage in else year 2020"+ct_weightage); 
    		System.out.println("osc_weightage in else year 2020"+osc_weightage);
        }
        }
        else {
        	pe_weightage=mvBean1.getPe_weightage();
            ct_weightage=mvBean1.getCt_weightage();
            osc_weightage=mvBean1.getOsc_weightage();
            System.out.println("pe_weightage in else year 2019"+pe_weightage);
    		System.out.println("ct_weightage in else year 2019"+ct_weightage); 
    		System.out.println("osc_weightage in else year 2019"+osc_weightage);
        }
		/*
		 * String pe_weightage=mvBean1.getPe_weightage(); String
		 * ct_weightage=mvBean1.getCt_weightage(); String
		 * osc_weightage=mvBean1.getOsc_weightage();
		 */
		
		
		System.out.println(
				"  select round(((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='2')+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='3')+(SELECT IFNULL(SUM(scored_points)*"+osc_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ")))/((SELECT IFNULL(SUM(max_points)*"+pe_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='2')+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='3')+(SELECT IFNULL(SUM(max_points)*"+osc_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id() + ")))*100,2) as percentage");

		return template.queryForObject(
				"select round(((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='2')+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='3')+(SELECT IFNULL(SUM(scored_points)*"+osc_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ")))/((SELECT IFNULL(SUM(max_points)*"+pe_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='2')+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "'  and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='3')+(SELECT IFNULL(SUM(max_points)*"+osc_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact='Online Sales Channel' and year='" + year
						+ "') and outlet_id in (" + gBean.getOutlet_id() + ")))*100,2) as percentage",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setPercentage(rs.getString("percentage"));
						if(rs.getString("percentage")==null) {
							gBean.setPercentage("0");
						}
						return gBean;

					}

				});

	}
	public List<GraphBean> getregionScore(GraphBean gBean) {
		final String outlet = gBean.getOutlets();
		System.out.println("outlets in region score"+outlet);
		System.out.println("SELECT * FROM `mst_geo_region` WHERE `region_status`='active'");
		return template.query("SELECT * FROM `mst_geo_region` WHERE `region_status`='active';",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setRegion_id(rs.getString("sk_region_id"));
						gBean.setOutlets(outlet);
						List<GraphBean> data = getregionScore1(gBean);
						gBean.setAnswerDetails(data);
						return gBean;

					}

				});
	}
	protected List<GraphBean> getregionScore1(GraphBean gBean) {
		final String region = gBean.getRegion_id();
		final String outlet = gBean.getOutlets();
		System.out.println("outlets in regionscore1");
		System.out.println(
				"SELECT distinct brand_name,sk_brand_id FROM `mst_shopper_details`,`mst_brand` where mst_shopper_details.brand_id=sk_brand_id and mst_shopper_details.brand_id!='2' order by sk_brand_id");
		return template.query(
				"SELECT distinct brand_name,sk_brand_id FROM `mst_shopper_details`,`mst_brand` where mst_shopper_details.brand_id=sk_brand_id and mst_shopper_details.brand_id!='2' order by sk_brand_id;",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setBrand_name(rs.getString("brand_name"));
						gBean.setBrand_id(rs.getString("sk_brand_id"));
						gBean.setRegion_id(region);
						gBean.setOutlet_id(outlet);
						GraphBean data = getregionScore2(gBean);
						gBean.setPercentage(data.getPercentage());
						return gBean;

					}

				});
	}
	protected GraphBean getregionScore2(GraphBean gBean) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		//int year=2019;
		//String year1="2019";
		String year1=String.valueOf(year);
		MysteryShoppingVisitsBean mvBean1=new MysteryShoppingVisitsBean();
        mvBean1=hDao.getScoreWeightage(mvBean1,year1);
        String  pe_weightage="";
        String ct_weightage="";
        String osc_weightage="";
        if(year == 2020) {
        if(gBean.getBrand_id().equals("3") || gBean.getBrand_id().equals("4")) {
        	  pe_weightage="0.50";
             ct_weightage="0.50";
             osc_weightage="0.00";
            System.out.println("pe_weightage in if year 2020"+pe_weightage);
    		System.out.println("ct_weightage in if year 2020"+ct_weightage); 
    		System.out.println("osc_weightage in if year 2020"+osc_weightage);
        }
        else {
        	  pe_weightage=mvBean1.getPe_weightage();
             ct_weightage=mvBean1.getCt_weightage();
             osc_weightage=mvBean1.getOsc_weightage();
            System.out.println("pe_weightage in else year 2020"+pe_weightage);
    		System.out.println("ct_weightage in else year 2020"+ct_weightage); 
    		System.out.println("osc_weightage in else year 2020"+osc_weightage);
        }
        }
        else {
        	pe_weightage=mvBean1.getPe_weightage();
            ct_weightage=mvBean1.getCt_weightage();
            osc_weightage=mvBean1.getOsc_weightage();
            System.out.println("pe_weightage in else year 2019"+pe_weightage);
    		System.out.println("ct_weightage in else year 2019"+ct_weightage); 
    		System.out.println("osc_weightage in else year 2019"+osc_weightage);
        }
		/*
		 * String pe_weightage=mvBean1.getPe_weightage(); String
		 * ct_weightage=mvBean1.getCt_weightage(); String
		 * osc_weightage=mvBean1.getOsc_weightage();
		 */
		System.out.println( 
				"hema==select round(((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id()+ "' and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='2'))+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='3'))+(SELECT IFNULL(SUM(scored_points)*"+osc_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ "))))/((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='2'))+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='3'))+(SELECT IFNULL(SUM(max_points)*"+osc_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id() + "))))*100,2) as percentage");
		
		return template.queryForObject(
				"select round(((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id()+ "' and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='2'))+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='3'))+(SELECT IFNULL(SUM(scored_points)*"+osc_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ "))))/((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='2'))+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact!='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id()
						+ ") and section_id='3'))+(SELECT IFNULL(SUM(max_points)*"+osc_weightage+",0) FROM `mys_score` where outlet_id in (SELECT sk_outlet_id FROM `mst_dealer_outlet` WHERE region_id='"
						+ gBean.getRegion_id()
						+ "') and outlet_id in (select outlet_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and mode_of_contact='Online Sales Channel' and year='" + year
						+ "' and outlet_id in (" + gBean.getOutlet_id() + "))))*100,2) as percentage",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setPercentage(rs.getString("percentage"));
						if(rs.getString("percentage")==null) {
							gBean.setPercentage("0");
						}
						return gBean;

					}

				});

	}
	public List<GraphBean> getShoppingScore(GraphBean gBean) {
		final String outlet = gBean.getOutlets();
		System.out.println(
				"SELECT distinct brand_name,sk_brand_id FROM `mst_shopper_details`,`mst_brand` where mst_shopper_details.brand_id=sk_brand_id and mst_shopper_details.brand_id!='2' order by sk_brand_id");
		return template.query(
				"SELECT distinct brand_name,sk_brand_id FROM `mst_shopper_details`,`mst_brand` where mst_shopper_details.brand_id=sk_brand_id and mst_shopper_details.brand_id!='2' order by sk_brand_id;",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setBrand_name(rs.getString("brand_name"));
						gBean.setBrand_id(rs.getString("sk_brand_id"));
						gBean.setOutlets(outlet);
						List<GraphBean> data = getShoppingScore1(gBean);
						gBean.setAnswerDetails(data);
						return gBean;

					}

				});
	}
	protected List<GraphBean> getShoppingScore1(GraphBean gBean) {
		final String brand_id = gBean.getBrand_id();
		final String outlet = gBean.getOutlets();
		System.out.println(
				"select distinct quarter from mst_shopper_details where visit_status='published' order by quarter");
		return template.query(
				"select distinct quarter from mst_shopper_details where visit_status='published' order by quarter",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setQuarter(rs.getString("quarter"));
						gBean.setBrand_id(brand_id);
						gBean.setOutlet_id(outlet);
						GraphBean data = getShoppingScore2(gBean);
						gBean.setPercentage(data.getPercentage());
						return gBean;

					}

				});

	}
	protected GraphBean getShoppingScore2(GraphBean gBean) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		//int year=2019;
		//String year1="2019";
		String year1=String.valueOf(year);
		MysteryShoppingVisitsBean mvBean1=new MysteryShoppingVisitsBean();
        mvBean1=hDao.getScoreWeightage(mvBean1,year1);
		String pe_weightage=mvBean1.getPe_weightage();
		String ct_weightage=mvBean1.getCt_weightage();
		String osc_weightage=mvBean1.getOsc_weightage();
		
		System.out.println(
				"select round(((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact!='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ") and section_id='2')+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact!='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ") and section_id='3')+(SELECT IFNULL(SUM(scored_points)*"+osc_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ")))/((SELECT IFNULL(SUM(max_points)*"+pe_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact!='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ") and section_id='2')+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact!='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ") and section_id='3')+(SELECT IFNULL(SUM(max_points)*"+osc_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id() + ")))*100,2) as percentage");
		return template
				.queryForObject(
						"select round(((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact!='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ") and section_id='2')+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact!='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ") and section_id='3')+(SELECT IFNULL(SUM(scored_points)*"+osc_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ")))/((SELECT IFNULL(SUM(max_points)*"+pe_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact!='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ") and section_id='2')+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact!='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id()
						+ ") and section_id='3')+(SELECT IFNULL(SUM(max_points)*"+osc_weightage+",0) FROM `mys_score` where shopper_id in (select sk_shopper_id from mst_shopper_details where visit_status='published' and brand_id='"
						+ gBean.getBrand_id() + "' and quarter='" + gBean.getQuarter()
						+ "' and mode_of_contact='Online Sales Channel' and year='" + year + "') and outlet_id in ("
						+ gBean.getOutlet_id() + ")))*100,2) as percentage",
						new RowMapper<GraphBean>() {
							public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
								GraphBean gBean = new GraphBean();
								gBean.setPercentage(rs.getString("percentage"));
								if(rs.getString("percentage")==null) {
									gBean.setPercentage("0");
								}
								return gBean;

							}

						});

	}
	public GraphBean getOutletsoverallperFilterCompetition(GraphBean gBean,String oid,String rid,String bid,String did) {
		
		System.out.println("Hi in compefilterDea=="+did);
		System.out.println("Hi in compefilterout=="+oid);
		System.out.println("Hi in compefilterreg=="+rid);
		
		System.out.println("Hi in compefilterDealerrrr=="+gBean.getDealer_id());
		System.out.println("Hi in compefilteroutlettt=="+gBean.getOutlet_id());
		System.out.println("Hi in compefilterregionnn=="+gBean.getRegion_id());
		if(gBean.getDealer_id()==null) {
		gBean.setDealer_id("");	
		}
		if(oid==null) {
			oid="";	
			}
		if(gBean.getRegion_id()==null) {
			gBean.setRegion_id("");	
			}
		
		
		// int year = 2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
				String sql = "";

				if ((gBean.getRegion_id() == null && gBean.getDealer_id() == null && oid == null)
						|| (gBean.getRegion_id().equals("") && gBean.getDealer_id().equals("")
								&& oid.equals(""))) {
					sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published'";
				} else if (gBean.getRegion_id().equals("All") && gBean.getDealer_id().equals("")
						&& oid.equals("")) {
					sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published'";
				} else if (!gBean.getRegion_id().equals("") && gBean.getDealer_id().equals("")
						&& oid.equals("")) {
					sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published'  and outlet_id in (select sk_outlet_id from mst_dealer_outlet where region_id='"
							+ gBean.getRegion_id() + "')";

				}

				else if (gBean.getRegion_id().equals("")) {
					if (!gBean.getDealer_id().equals("")) {
						System.out.println("here");
						if (gBean.getRegion_id().equals("") && !gBean.getDealer_id().equals("")
								&& oid.equals("")) {
							sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published' and outlet_id in (select sk_outlet_id from mst_dealer_outlet where dealer_id='"
									+ gBean.getDealer_id() + "')";

						} else if (!oid.equals("")) {
							if (gBean.getRegion_id().equals("") && !gBean.getDealer_id().equals("")
									&& !oid.equals("")) {
								sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published' and outlet_id='"
										+ oid + "'";

							}
						}
					}
				}

				else if (!gBean.getDealer_id().equals("")) {
					System.out.println("here");
					if (!gBean.getRegion_id().equals("") && !gBean.getDealer_id().equals("")
							&& oid.equals("")) {
						sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published' and outlet_id in (select sk_outlet_id from mst_dealer_outlet where dealer_id='"
								+ gBean.getDealer_id() + "')";

					} else if (!oid.equals("")) {
						if (!gBean.getRegion_id().equals("") && !gBean.getDealer_id().equals("")
								&& !oid.equals("")) {
							sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published' and outlet_id='"
									+ oid + "'";

						}
					}
				}

				System.out.println(sql);
				return template.queryForObject(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setOutlets(rs.getString("outlets"));
						return gBean;
					}
				});
	}
	public ReportsBean getNonOscScore(ReportsBean rBean, String sk_shopper_id, String oid, String year, String month,
			String brand) {
		
	String sql="SELECT  count(*), outlet_score,YTD,scored_point,maximum_point FROM (SELECT outlet_id,shopper_id, monthly_score_percentage AS outlet_score,scored_point,maximum_point FROM mys_txn_outlet_score WHERE brand_id='"+brand+"' and year='"+year+"' and outlet_id='"+oid+"' AND month = "+month+" and osc_flag='0' )AS RES1 JOIN (SELECT round(((ifnull(sum(scored_point),0)/ifnull(sum(maximum_point),0))*100),2) AS YTD FROM mys_txn_outlet_score WHERE brand_id='"+brand+"' and year='"+year+"' and outlet_id='"+oid+"' AND month <= "+month+" and osc_flag='0')AS RES2  ";
	System.out.println(sql);	
	return template.queryForObject(sql, new RowMapper<ReportsBean>() {
			public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
				//rBean.setMax_points(rs.getString("maximum_point"));
				//rBean.setScored_points(rs.getString("scored_point"));
				
				try {
				if(rs.getString("outlet_score").equals("null") || rs.getString("outlet_score")==null) {
					rBean.setOutlet_score("0");
				}else {
					rBean.setOutlet_score(rs.getString("outlet_score"));
				}
				}catch (Exception e) {
					rBean.setOutlet_score("0");
				}
				
				try {
				if(rs.getString("ytd").equals("null") || rs.getString("ytd")==null) {
					rBean.setNonosc_ytd_score("0");
				}else {
					rBean.setNonosc_ytd_score(rs.getString("ytd"));
				}
				}catch (Exception e) {
					rBean.setNonosc_ytd_score("0");
				}
				return rBean;
			}
		});
	}
	
	public ReportsBean getOscScore(ReportsBean rBean, String sk_shopper_id, String did, String year, String month,
			String brand) {
		
		String sql="SELECT count(*), ifnull(oscscore,0) as oscscore,ifnull(oscytd,0) as oscytd,ifnull(scored_point,0)scored_point,ifnull(maximum_point,0)as maximum_point FROM (SELECT outlet_id,shopper_id, round((((scored_point)/(maximum_point))*100),2) as oscscore,mys_txn_outlet_score.scored_point,mys_txn_outlet_score.maximum_point FROM mys_txn_outlet_score WHERE shopper_id='"+sk_shopper_id+"' and osc_flag='1')AS RES1 JOIN (SELECT round(((ifnull(sum(scored_point),0)/ifnull(sum(maximum_point),0))*100),2) AS oscytd FROM mys_txn_outlet_score WHERE brand_id='"+brand+"' and year='"+year+"' and dealer_id='"+did+"' AND month <= "+month+" and osc_flag='1')AS RES2 ";
		System.out.println(sql);	
		return template.queryForObject(sql, new RowMapper<ReportsBean>() {
				public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
					rBean.setOsc_max_points(rs.getString("maximum_point"));
					rBean.setOsc_scored_points(rs.getString("scored_point"));
					rBean.setOsc_outlet_score(rs.getString("oscscore"));
					rBean.setOsc_ytd_score(rs.getString("oscytd"));
					return rBean;
				}
			});
		}
	public ReportsBean getnonshopperDetails(ReportsBean rBean, String sk_shopper_id) {
		String sql="SELECT monthname(mst_shopper_details.visit_date)as monthname, mst_shopper_details.mode_of_contact,mst_dealer_outlet.outlet_name,mst_dealer_outlet.outlet_id,mst_dealer_outlet.address,mst_brand.brand_name,mst_dealer.dealer_name FROM `mst_shopper_details`,mst_dealer_outlet,mst_brand,mst_dealer WHERE `sk_shopper_id`='"+sk_shopper_id+"' and mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id and mst_brand.sk_brand_id=mst_shopper_details.brand_id and mst_dealer.sk_dealer_id=mst_shopper_details.dealer_id ";
		System.out.println(sql);	
		return template.queryForObject(sql, new RowMapper<ReportsBean>() {
				public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
					rBean.setBrand_name(rs.getString("brand_name"));
					rBean.setDealer_name(rs.getString("dealer_name"));
					rBean.setLocation(rs.getString("outlet_name"));
					rBean.setOutlet_address(rs.getString("address"));
					rBean.setMode_of_contact(rs.getString("mode_of_contact"));
					rBean.setMonth_name(rs.getString("monthname"));
					rBean.setOutlet_id(rs.getString("outlet_id"));
					return rBean;
				}
			});
		}
	public List<ReportsBean> getNonOscSectionPercentage(ReportsBean rBean,
			String sk_shopper_id, String month, String year, String sk_section_id, String brand, String oid) {
		
		int mon=Integer.parseInt(month);
		int premonth=mon-1;
		int prevYear = Calendar.getInstance().get(Calendar.YEAR);
		if(premonth == 0) {
			  
			premonth = 12;
			int Currentyear = Calendar.getInstance().get(Calendar.YEAR);
			System.out.println("current year==="+Currentyear);
			int year1 = Integer.parseInt(year);
			 prevYear = year1 - 1;
			System.out.println("prev Year==="+prevYear);
		  }
		String sql="SELECT res5.subsection_name ,ifnull(round(prevmonthpercentage,2),0) as prevmonthpercentage1,ifnull(round(currmonthpercentage,2),0) as currmonthpercentage1, round((ifnull(currmonthpercentage,0)-ifnull(prevmonthpercentage,0)),2) as diff FROM (SELECT * from (select  mst_subsection.sk_subsection_id,mst_subsection.subsection_name from mst_subsection where mst_subsection.section_id='"+sk_section_id+"' and mst_subsection.subsection_status='active')AS res4 LEFT JOIN (SELECT  monthname(mst_shopper_details.visit_date) as currmnoth,mys_score.section_id, mys_score.subsection_id,ifnull(mys_score.percentage,0)as currmonthpercentage  FROM mys_score,mst_shopper_details where shopper_id='"+sk_shopper_id+"' and section_id='"+sk_section_id+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and month(mst_shopper_details.visit_date)='"+month+"' )res1  ON res1.subsection_id=res4.sk_subsection_id ) AS res5 left join (SELECT shopper_id,monthname(mst_shopper_details.visit_date) as prevmnoth,mys_score.subsection_id, mys_score.subsection_name,ifnull((mys_score.percentage),0)as prevmonthpercentage FROM mys_score,mst_shopper_details where brand_id='"+brand+"' and mys_score.year='"+prevYear+"' and mys_score.outlet_id='"+oid+"' and osc_flag='0' and section_id='"+sk_section_id+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and month(mst_shopper_details.visit_date)='"+premonth+"') as res6 on res6.subsection_id=res5.sk_subsection_id ";
		System.out.println(sql);	
		return template.query(sql, new RowMapper<ReportsBean>() {
				public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
					ReportsBean rBean=new ReportsBean();
					rBean.setSubsection_name(rs.getString("subsection_name"));
					rBean.setCurrtmonth_percentage(rs.getString("currmonthpercentage1"));
					rBean.setPrevmonth_percentage(rs.getString("prevmonthpercentage1"));
					rBean.setDiff(rs.getString("diff"));
					return rBean;
				}
			});
		}
	public List<ReportsBean> getOscSectionPercentage(ReportsBean rBean, String osc_shopper_id, String month,
			String year,String sk_section_id, String brand, String did) {
		int mon=Integer.parseInt(month);
		int premonth=mon-1;
		int prevYear = Calendar.getInstance().get(Calendar.YEAR);
		if(premonth == 0) {
			  
			premonth = 12;
			int Currentyear = Calendar.getInstance().get(Calendar.YEAR);
			System.out.println("current year==="+Currentyear);
			 prevYear = Currentyear - 1;
			System.out.println("prev Year==="+prevYear);
		  }
		String sql="SELECT res5.subsection_name ,ifnull(round(prevmonthpercentage,2),0) as prevmonthpercentage1,ifnull(round(currmonthpercentage,2),0) as currmonthpercentage1, round((ifnull(currmonthpercentage,0)-ifnull(prevmonthpercentage,0)),2) as diff FROM (SELECT * from (select  mst_subsection.sk_subsection_id,mst_subsection.subsection_name from mst_subsection where mst_subsection.section_id='"+sk_section_id+"' and mst_subsection.subsection_status='active')AS res4 LEFT JOIN (SELECT  monthname(mst_shopper_details.visit_date) as currmnoth,mys_score.section_id, mys_score.subsection_id,ifnull(mys_score.percentage,0)as currmonthpercentage  FROM mys_score,mst_shopper_details where shopper_id='"+osc_shopper_id+"' and section_id='"+sk_section_id+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and month(mst_shopper_details.visit_date)='"+month+"' )res1  ON res1.subsection_id=res4.sk_subsection_id ) AS res5 left join (SELECT shopper_id,monthname(mst_shopper_details.visit_date) as prevmnoth,mys_score.subsection_id, mys_score.subsection_name,ifnull((mys_score.percentage),0)as prevmonthpercentage FROM mys_score,mst_shopper_details where brand_id='"+brand+"' and mys_score.year='"+prevYear+"' and mys_score.dealer_id='"+did+"' and osc_flag='1' and section_id='"+sk_section_id+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and month(mst_shopper_details.visit_date)='"+premonth+"') as res6 on res6.subsection_id=res5.sk_subsection_id ";
		System.out.println(sql);	
		return template.query(sql, new RowMapper<ReportsBean>() {
				public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
					ReportsBean rBean=new ReportsBean();
					rBean.setSubsection_name(rs.getString("subsection_name"));
					rBean.setCurrtmonth_percentage(rs.getString("currmonthpercentage1"));
					rBean.setPrevmonth_percentage(rs.getString("prevmonthpercentage1"));
					rBean.setDiff(rs.getString("diff"));
					return rBean;
				}
			});
		}
	public List<ReportsBean> getNonOscSection(ReportsBean rBean, String sk_shopper_id, String month, String year, String brand, String oid) {
		int mon=Integer.parseInt(month);
		int premonth=mon-1;
		String sql="SELECT * FROM `mst_section` WHERE `sk_section_id`in (2,3) and `section_status`='active'";
		System.out.println(sql);	
		return template.query(sql, new RowMapper<ReportsBean>() {
				public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
					ReportsBean rBean=new ReportsBean();
					rBean.setSection_name(rs.getString("section_name"));
					List<ReportsBean> sectionscore = getNonOscSectionPercentage(rBean,sk_shopper_id,month,year,rs.getString("sk_section_id"),brand,oid);
					rBean.setGetNonOscSectionPercentage(sectionscore);
					ReportsBean data1 = getSectionScore1(rBean,sk_shopper_id,year,oid,rs.getString("sk_section_id"));
					rBean.setMtd(data1.getMtd());
					rBean.setYtd(data1.getYtd());
					return rBean;
				}

				
			});
		}
	
	
	private ReportsBean getSectionScore1(ReportsBean rBean, String sk_shopper_id, String year, String oid, String sk_section_id) {
		/*System.out.println(
				"section score score non osce 2 = select(SELECT IFNULL(round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2),0) as percentage from mys_score,mst_shopper_details where mys_score.subsection_id IN (2,3,4,5,8) and mys_score.shopper_id='"
						+ sk_shopper_id + "'  and mys_score.section_id='" + sk_section_id
						+ "') as MTD_sec2,(SELECT IFNULL(round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2),0) as percentage from mys_score,mst_shopper_details where mys_score.subsection_id IN (7,10) and mys_score.shopper_id='"
						+ sk_shopper_id + "' and mys_score.section_id='" + sk_section_id
						+ "') as MTD_sec3,(SELECT IFNULL(round((sum(scored_points)/sum(max_points))*100,2),0) from mys_score LEFT JOIN mst_subsection ON mst_subsection.sk_subsection_id=mys_score.subsection_id where mys_score.subsection_id IN (2,3,4,5,8) and outlet_id='"
						+ oid + "'  and mys_score.section_id='" +sk_section_id
						+ "') as YTD_sec2,(SELECT IFNULL(round((sum(scored_points)/sum(max_points))*100,2),0) from mys_score LEFT JOIN mst_subsection ON mst_subsection.sk_subsection_id=mys_score.subsection_id where mys_score.subsection_id IN (7,10) and outlet_id='"
						+ oid+ "' and mys_score.section_id='" + sk_section_id
						+ "') as YTD_sec3");*/
		
						String sql="select(SELECT IFNULL(round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2),0) as percentage from mys_score,mst_shopper_details where  mys_score.shopper_id='"
						+ sk_shopper_id + "'  and mys_score.section_id='" + sk_section_id
						+ "') as MTD_sec2,(SELECT IFNULL(round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2),0) as percentage from mys_score,mst_shopper_details where  mys_score.shopper_id='"
						+ sk_shopper_id + "' and mys_score.section_id='" + sk_section_id
						+ "') as MTD_sec3,(SELECT IFNULL(round((sum(scored_points)/sum(max_points))*100,2),0) from mys_score LEFT JOIN mst_subsection ON mst_subsection.sk_subsection_id=mys_score.subsection_id where  outlet_id='"
						+ oid + "'  and mys_score.section_id='" + sk_section_id
						+ "') as YTD_sec2,(SELECT IFNULL(round((sum(scored_points)/sum(max_points))*100,2),0) from mys_score LEFT JOIN mst_subsection ON mst_subsection.sk_subsection_id=mys_score.subsection_id where  outlet_id='"
						+ oid + "' and mys_score.section_id='" + sk_section_id
						+ "') as YTD_sec3";
		return template.queryForObject(
				sql,
				new RowMapper<ReportsBean>() {
					public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
						if (sk_section_id.equals("2")) {
							rBean.setMtd(rs.getString("MTD_sec2"));
							rBean.setYtd(rs.getDouble("YTD_sec2"));
          } else if (sk_section_id.equals("3")) {
        	  rBean.setMtd(rs.getString("MTD_sec3"));
        	  rBean.setYtd(rs.getDouble("YTD_sec3"));
          }
						return rBean;
					}
				});
	
}
	
	public List<ReportsBean> getOscSectionPercentage(ReportsBean rBean, String osc_shopper_id, String month,
			String year, String brand, String did) {
		int mon=Integer.parseInt(month);
		int premonth=mon-1;
		String sql="SELECT * FROM `mst_section` WHERE `sk_section_id`in (4,5) and `section_status`='active'";
		System.out.println(sql);	
		return template.query(sql, new RowMapper<ReportsBean>() {
				public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
					ReportsBean rBean=new ReportsBean();
					rBean.setSection_name(rs.getString("section_name"));
					List<ReportsBean> oscsectionscore = getOscSectionPercentage(rBean,osc_shopper_id,month,year,rs.getString("sk_section_id"),brand,did);
					rBean.setGetoscsectionscore(oscsectionscore);
					try{
						ReportsBean data1 = getSectionScore2(rBean,osc_shopper_id,rs.getString("sk_section_id"),did);
						rBean.setMtd(data1.getMtd());
						rBean.setYtd(data1.getYtd());
						}catch (Exception e) {
							System.out.println("kjsfjsndfjnsd"+e);
						}
					return rBean;
				}

			});
		}
	

	private ReportsBean getSectionScore2(ReportsBean rBean, String osc_shopper_id, String sk_section_id, String did) {
	/*	System.out.println(
				"select(SELECT IFNULL(round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2),0) as percentage from mys_score,mst_shopper_details where mys_score.subsection_id IN (9,10,11,12) and mys_score.shopper_id='"
						+osc_shopper_id + "' and mys_score.section_id='" + sk_section_id
						+ "') as MTD_sec2,(SELECT IFNULL(round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2),0) as percentage from mys_score,mst_shopper_details where mys_score.subsection_id IN (13,14,15) and mys_score.shopper_id='"
						+ osc_shopper_id + "' and mys_score.section_id='" + sk_section_id
						+ "') as MTD_sec3,(SELECT IFNULL(round((sum(scored_points)/sum(max_points))*100,2),0) from mys_score LEFT JOIN mst_subsection ON mst_subsection.sk_subsection_id=mys_score.subsection_id where mys_score.subsection_id IN (9,10,11,12) and dealer_id='"
						+ did + "' and mys_score.section_id='" + sk_section_id
						+ "') as YTD_sec2,(SELECT IFNULL(round((sum(scored_points)/sum(max_points))*100,2),0) from mys_score LEFT JOIN mst_subsection ON mst_subsection.sk_subsection_id=mys_score.subsection_id where mys_score.subsection_id IN (13,14,15) and dealer_id='"
						+ did + "' and mys_score.section_id='" + sk_section_id
						+ "') as YTD_sec3");*/
		return template.queryForObject(
				"select(SELECT IFNULL(round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2),0) as percentage from mys_score,mst_shopper_details where  mys_score.shopper_id='"
						+osc_shopper_id + "' and mys_score.section_id='" + sk_section_id
						+ "') as MTD_sec2,(SELECT IFNULL(round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2),0) as percentage from mys_score,mst_shopper_details where  mys_score.shopper_id='"
						+ osc_shopper_id + "' and mys_score.section_id='" + sk_section_id
						+ "') as MTD_sec3,(SELECT IFNULL(round((sum(scored_points)/sum(max_points))*100,2),0) from mys_score LEFT JOIN mst_subsection ON mst_subsection.sk_subsection_id=mys_score.subsection_id where  dealer_id='"
						+ did + "' and mys_score.section_id='" + sk_section_id
						+ "') as YTD_sec2,(SELECT IFNULL(round((sum(scored_points)/sum(max_points))*100,2),0) from mys_score LEFT JOIN mst_subsection ON mst_subsection.sk_subsection_id=mys_score.subsection_id where  dealer_id='"
						+ did + "' and mys_score.section_id='" + sk_section_id
						+ "') as YTD_sec3",
				new RowMapper<ReportsBean>() {
					public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
						if (sk_section_id.equals("4")) {
							rBean.setMtd(rs.getString("MTD_sec2"));
							rBean.setYtd(rs.getDouble("YTD_sec2"));
						} else if (sk_section_id.equals("5")) {
							rBean.setMtd(rs.getString("MTD_sec3"));
							rBean.setYtd(rs.getDouble("YTD_sec3"));
						}

						return rBean;
					}
				});
	}
/******************manoj d MLR report***********************/
	public QuestionnaireBean checkCurrentMonthClosedVisits(QuestionnaireBean qBean, String month, String brand) {

		String sql = "select count(*) as count from mst_shopper_details where visit_status='CLOSED' and monthName(visit_date)='"
				+ month + "' and brand='" + brand + "';";
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<QuestionnaireBean>() {
			public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
				QuestionnaireBean qBean = new QuestionnaireBean();
				qBean.setCount(rs.getString("count"));
				return qBean;
			}
		});
	}
	/******************manoj d MLR report***********************/
	
	/*************manoj d OLR last 3 Tables*****************/
	public List<ReportsBean> getDealerPerformance(ReportsBean rBean,MysteryShoppingVisitsBean mvBean, final String standard_num, final String answer) {
		final String outlet_id = mvBean.getOutlet_id();
		String sql = "select distinct quarter from mst_shopper_details order by quarter";
		System.out.println(sql);
			String brand=mvBean.getBrand_id();
		return template.query(sql, new RowMapper<ReportsBean>() {
			int a;
			int b;
			double avg;

			public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
				ReportsBean rBean = new ReportsBean();
				rBean.setQuarter(rs.getString("quarter"));
				rBean.setOutlet_id(outlet_id);
				rBean.setBrand(brand);

				ReportsBean getdata = getPerformacedata(rBean, standard_num, answer);
				rBean.setAnswer_count(getdata.getAnswer_count());
				rBean.setCount(getdata.getCount());
				a = a + Integer.parseInt(getdata.getAnswer_count());
				b = b + Integer.parseInt(getdata.getCount());

				avg = ((double) a / (double) b) * 100;

				System.out.println("avg" + avg);
				System.out.println("a" + a);
				System.out.println("b" + b);

				rBean.setOne(String.valueOf(a));
				rBean.setTwo(String.valueOf(b));

				DecimalFormat df = new DecimalFormat("#.00");
				if (df.format(avg).equals(".00")) {
					rBean.setPercentage("0");
				} else {
					rBean.setPercentage(df.format(avg));
				}
				return rBean;
			}
		});
	}
	public ReportsBean getPerformacedata(ReportsBean rBean, String standard_number, String answer) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		//int year =2019;
		final String quarter = rBean.getQuarter();
			           
		String sql = "";
		if (standard_number.equals("2.1") || standard_number.equals("2.4")) {
			System.out.println("performance data when===standard number=== 2.1 or 2.4"+standard_number);
			sql = "select(select count(*) from mst_shopper_details where quarter='" + rBean.getQuarter()
					+ "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') as denominator,(SELECT COUNT(standard_num) FROM mys_txn_formulaanswer WHERE standard_num='"
					+ standard_number
					+ "' AND mys_txn_formulaanswer.status='active' and shopper_id in (select sk_shopper_id from mst_shopper_details where quarter='"
					+ rBean.getQuarter() + "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel')) as numerator";
					System.out.println("2.1"+sql);
		} else if (standard_number.equals("5.2")) {
			System.out.println("performance data when===standard number===  5.2"+standard_number);
			sql = "select(select count(*) from mst_shopper_details where quarter='" + rBean.getQuarter()
					+ "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') as denominator,(SELECT count(sk_answer_id) FROM(SELECT p.*,( CASE shopper_id WHEN @curType THEN @curRow := @curRow + 1 ELSE @curRow := 1 AND @curType := shopper_id END ) + 0 AS ranking FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE (standard_number='5.2.1' or standard_number='5.2.2') AND select_option_text='"
					+ answer
					+ "' AND status='active' and shopper_id in (select sk_shopper_id from mst_shopper_details where quarter='"
					+ rBean.getQuarter() + "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='closed' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') ORDER BY shopper_id,subquestion_id asc) as t WHERE t.ranking=1) as numerator";
		System.out.println("5.2"+sql);
		} else if (standard_number.equals("6.2")) {
			System.out.println("performance data when===standard number===  6.2"+standard_number);
			sql = "select(select count(*) from mst_shopper_details where quarter='" + rBean.getQuarter()
					+ "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') as denominator,(SELECT COUNT(sk_answer_id) FROM mys_txn_answers WHERE standard_number='6.2' AND mys_txn_answers.status='active' AND (select_option_text='5 - Applies entirely' or select_option_text='4 - Applies') and shopper_id in (select sk_shopper_id from mst_shopper_details where quarter='"
					+ rBean.getQuarter() + "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel')) as numerator";
		System.out.println("6.2"+sql);
		} else if (standard_number.equals("1.5")) {
			System.out.println("performance data when===standard number===  1.5"+standard_number);
			sql = "select(select count(*) from mst_shopper_details where quarter='" + rBean.getQuarter()
					+ "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') as denominator,(SELECT COUNT(*) FROM(SELECT *,sum(CASE WHEN select_option_text='"
					+ answer
					+ "' THEN 1 ELSE 0 END) as c1  FROM mys_txn_answers WHERE  mys_txn_answers.status='active' AND (standard_number='1.5' or standard_number='1.5a') AND shopper_id IN (select sk_shopper_id from mst_shopper_details where quarter='"
					+ rBean.getQuarter() + "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') GROUP BY shopper_id) as t WHERE c1=(SELECT COUNT(*) FROM mst_questionare_subquestion WHERE question_id IN (SELECT sk_question_id FROM mst_questionare WHERE standard_number='"
					+ standard_number + "'))) as numerator";
		System.out.println("1.5"+sql);
		
		} else if (standard_number.equals("3.2")) {
			System.out.println("performance data when===standard number===  3.2"+standard_number);
			sql = "select(select count(*) from mst_shopper_details where quarter='" + rBean.getQuarter()
					+ "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') as denominator,(SELECT COUNT(*) FROM(SELECT *,sum(CASE WHEN select_option_text='"
					+ answer
					+ "' THEN 1 ELSE 0 END) as c1  FROM mys_txn_answers WHERE standard_number='3.2' AND mys_txn_answers.status='active' AND shopper_id IN (select sk_shopper_id from mst_shopper_details where quarter='"
					+ rBean.getQuarter() + "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') GROUP BY shopper_id) as t WHERE c1=(3)) as numerator";
		System.out.println("3.2"+sql);
		
		} else if (standard_number.equals("3.2a")) {
			System.out.println("performance data when===standard number===  3.2a"+standard_number);
			sql = "select(select count(*) from mst_shopper_details where quarter='" + rBean.getQuarter()
					+ "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') as denominator,(SELECT count(sk_answer_id) FROM(SELECT p.*,(  CASE shopper_id  WHEN @curType   THEN @curRow := @curRow + 1  ELSE @curRow := 1 AND @curType := shopper_id END  ) + 0 AS ranking FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE standard_number='3.2' and select_option_text='yes' AND status='active'  and shopper_id in (select sk_shopper_id from mst_shopper_details where quarter='"
					+ rBean.getQuarter() + "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') ORDER BY  shopper_id,subquestion_id asc) as t WHERE t.ranking=4) as numerator";
		System.out.println("3.2a"+sql);
		} else if (standard_number.equals("4.5.1")) {
			System.out.println("performance data when===standard number===  4.5.1"+standard_number);
			sql = "select(select count(*) from mst_shopper_details where quarter='" + rBean.getQuarter()
					+ "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') as denominator,(SELECT count(sk_answer_id) FROM(SELECT p.*,(  CASE shopper_id  WHEN @curType   THEN @curRow := @curRow + 1  ELSE @curRow := 1 AND @curType := shopper_id END  ) + 0 AS ranking FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE standard_number='4.5.1' and select_option_text='yes' AND status='active' and shopper_id in (select sk_shopper_id from mst_shopper_details where quarter='"
					+ rBean.getQuarter() + "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') ORDER BY  shopper_id,subquestion_id asc) as t WHERE t.ranking=1) as numerator";
		System.out.println("4.5.1"+sql);
		} else {
			System.out.println("performance data when===else condition==="+standard_number);
			sql = "select(select count(*) from mst_shopper_details where quarter='" + rBean.getQuarter()
					+ "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel') as denominator,(SELECT COUNT(sk_answer_id) FROM mys_txn_answers WHERE standard_number='"
					+ standard_number + "' AND mys_txn_answers.status='active' AND select_option_text='" + answer
					+ "' and shopper_id in (select sk_shopper_id from mst_shopper_details where quarter='"
					+ rBean.getQuarter() + "' and year='" + year + "' and outlet_id = '" + rBean.getOutlet_id()
					+ "' and visit_status='published' and brand_id='"+rBean.getBrand()+"' and mode_of_contact!='Online Sales Channel')) as numerator";
		System.out.println("else="+sql);
		}
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<ReportsBean>() {
			public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
				ReportsBean qBean = new ReportsBean();
				qBean.setQuarter(quarter);
				qBean.setAnswer_count(rs.getString("numerator"));
				qBean.setCount(rs.getString("denominator"));
				return qBean;
			}
		});
	}
	/*************manoj d OLR last 3 tables******************/
	
	/*
	 * manoj d overall perf formula 4
	 */
		
	public QuestionnaireBean getOverallperformanceFormula4(final QuestionnaireBean qBean, final String standard_number,
			final String answer,String brand,String outlet,String dealer,String region,String month) {
		
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
		//int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		String sql = "";
		if (standard_number.equals("1.5")) {
			sql = "Select(SELECT COUNT(distinct shopper_id) as visited_count FROM mys_txn_answers WHERE (standard_number='"
					+ standard_number + "' or standard_number='1.5a') and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as visited_count,(SELECT count(select_option_text) FROM(SELECT p.*,(  CASE shopper_id  WHEN @curType   THEN @curRow := @curRow + 1  ELSE @curRow := 1 AND @curType := shopper_id END  ) + 0 AS ranking FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE (standard_number='1.5a' or standard_number='1.5') and select_option_text='"
					+ answer + "' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) ORDER BY  shopper_id,subquestion_id asc) as t WHERE t.ranking=1) as no_count";
		} else if (standard_number.equals("4.5.1")) {
			sql = "Select(SELECT COUNT(DISTINCT(shopper_id))  as visited_count FROM mys_txn_answers WHERE standard_number='"
					+ standard_number + "' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as visited_count,(SELECT count(select_option_text) FROM(SELECT p.*,(  CASE shopper_id  WHEN @curType   THEN @curRow := @curRow + 1  ELSE @curRow := 1 AND @curType := shopper_id END  ) + 0 AS ranking FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE standard_number='"
					+ standard_number + "' and select_option_text='" + answer + "' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) ORDER BY  shopper_id,subquestion_id asc) as t WHERE t.ranking=1) as no_count";
		} else if (standard_number.equals("5.2")) {
			sql = "Select(SELECT COUNT(sk_answer_id) as visited_count FROM mys_txn_answers WHERE standard_number='"
					+ standard_number + "' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as visited_count,(SELECT count(select_option_text) FROM(SELECT p.*,(  CASE shopper_id  WHEN @curType   THEN @curRow := @curRow + 1  ELSE @curRow := 1 AND @curType := shopper_id END  ) + 0 AS ranking FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE (standard_number='5.2.1' or standard_number='5.2.2') and select_option_text='"
					+ answer + "' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) ORDER BY  shopper_id,subquestion_id asc) as t WHERE t.ranking=1) as no_count";
		} else if (standard_number.equals("6.2")) {
			System.out.println("here we are");
			sql = "Select(SELECT COUNT(sk_answer_id) as visited_count FROM mys_txn_answers WHERE standard_number='"
					+ standard_number + "' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as visited_count,(SELECT COUNT(sk_answer_id) as no_count FROM mys_txn_answers WHERE standard_number='"
					+ standard_number
					+ "' AND (select_option_text='4 - Applies' or select_option_text='5 - Applies entirely') and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as no_count";
		} else if (standard_number.equals("3.2")) {
			sql = "Select(SELECT COUNT(sk_answer_id) as visited_count FROM mys_txn_answers WHERE standard_number='"
					+ standard_number + "' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))) as visited_count,(SELECT count(select_option_text) FROM(SELECT p.*,(  CASE shopper_id  WHEN @curType   THEN @curRow := @curRow + 1  ELSE @curRow := 1 AND @curType := shopper_id END  ) + 0 AS ranking FROM mys_txn_answers p , (SELECT @curRow := 0, @curType := '') r WHERE standard_number='3.2' and select_option_text='"
					+ answer + "' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published' AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) ORDER BY  shopper_id,subquestion_id asc) as t WHERE t.ranking=4) as no_count";
		}
		System.out.println(sql);

		return template.queryForObject(sql, new RowMapper<QuestionnaireBean>() {
			public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
				final Double visited_count = rs.getDouble("visited_count");

				Double no_count = rs.getDouble("no_count");
				Double val1 = no_count;
				Double val2 = visited_count;
				Double val = (val1 / val2) * 100;
				qBean.setAvg(val.toString());
				String valu = val.toString();
				if (valu.equals("Infinity")) {
					qBean.setAvg("0.0");
				} else {

					if (valu.equals("NaN")) {
						qBean.setAvg("0.0");
					}
				}

				System.out.println("percentrage" + qBean.getAvg());
				return qBean;
			}
		});

	}
	
	public QuestionnaireBean getOverallperformanceFormula4(final QuestionnaireBean qBean, final String standard_number,String brand,String outlet,String dealer,String region,String month) {
		
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
		
		
//		if(dealer==null) {
//			dealer="all";
//		 }
//		 if(region==null) {
//			 region="all";
//		 }
		 if(outlet==null) {
			 outlet="all";
		 }
		 if(month==null) {
			 month="all";
		 }
		 if(brand==null) {
			 brand="1";
		 }
		 //int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(
				"Select(SELECT COUNT(Distinct(shopper_id)) as visited_count FROM mys_txn_answers WHERE standard_number='3.1.3' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and (mst_shopper_details.dealer_id='" + dealer + "' or '" + dealer
						+ "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '" + outlet
						+ "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))  and select_option_text='Yes') as visited_count,(SELECT COUNT(*) FROM(SELECT *,sum(CASE WHEN select_option_text='yes' THEN 1 ELSE 0 END) as c1  FROM mys_txn_answers WHERE standard_number='3.2'  AND shopper_id IN (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and (mst_shopper_details.dealer_id='" + dealer + "' or '" + dealer
						+ "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '" + outlet
						+ "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL')) GROUP BY shopper_id) as t WHERE c1=(4)) as no_count");

		return template.queryForObject(
				"Select(SELECT COUNT(Distinct(shopper_id)) as visited_count FROM mys_txn_answers WHERE standard_number='3.1.3' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and (mst_shopper_details.dealer_id='" + dealer + "' or '" + dealer
						+ "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '" + outlet
						+ "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL'))  and select_option_text='Yes') as visited_count,(SELECT COUNT(*) FROM(SELECT *,sum(CASE WHEN select_option_text='yes' THEN 1 ELSE 0 END) as c1  FROM mys_txn_answers WHERE standard_number='3.2'  AND shopper_id IN (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"
						+ year + "' and mst_shopper_details.brand_id='" + brand
						+ "' and (mst_shopper_details.dealer_id='" + dealer + "' or '" + dealer
						+ "'='ALL') and (mst_shopper_details.outlet_id='" + outlet + "' OR '" + outlet
						+ "'='ALL') and (month(visit_date)='" + month + "' or '" + month
						+ "'='All') AND (mst_dealer_outlet.region_id='" + region + "' OR '" + region
						+ "'='ALL')) GROUP BY shopper_id) as t WHERE c1=(4)) as no_count;",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						final Double visited_count = rs.getDouble("visited_count");

						Double no_count = rs.getDouble("no_count");
						Double val1 = no_count;
						Double val2 = visited_count;
						Double val = (val1 / val2) * 100;
						qBean.setAvg(val.toString());
						String valu = val.toString();
						if (valu.equals("Infinity")) {
							qBean.setAvg("0.0");
						} else {

							if (valu.equals("NaN")) {
								qBean.setAvg("0.0");
							}
						}
						return qBean;
					}
				});

	}
	/*
	 * manoj d overall perf formula 4
	 */
	/*
	 * ytd mtd manoj
	 * 
	 */
	
	public List<GraphBean> getMonthsForYtdMtd(GraphBean gBean, String month) {

		//int year = 2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		String sql = "SELECT DISTINCT(month(visit_date)) as month,(monthName(visit_date)) as monthName FROM `mst_shopper_details` WHERE visit_date BETWEEN '"
				+ year + "-01-01' and subdate(current_date, 1)  " + month
				+ " and visit_status='published' ORDER by visit_date ";

		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setMonth(rs.getString("month"));
				gBean.setMonth_name(rs.getString("monthName"));
				return gBean;
			}
		});
	}
	
	public GraphBean getOutletsoverallperFilterForYtdMtd(GraphBean gBean) {
//		if(gBean.getDealer_id()==null) {
//			gBean.setDealer_id("");	
//			}
//			if(gBean.getOutlet_id()==null) {
//				gBean.setOutlet_id("");	
//				}
//			if(gBean.getRegion_id()==null) {
//				gBean.setRegion_id("");	
//				}
		
		System.out.println("hello this is overall perf YTD MTD");
		//int year = 2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String sql = "";
		System.out.println(gBean.getBrand_id() + "brnd" + gBean.getMonth() + "month" + gBean.getRegion_id() + "region");
		// int year = Calendar.getInstance().get(Calendar.YEAR);
		if (gBean.getBrand_id() != null && gBean.getMonth() == null && gBean.getRegion_id() == null
				 && gBean.getOutlet_id() == null) {
			sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where brand_id='"
					+ gBean.getBrand_id() + "' and year='" + year + "' and visit_status='published' order by outlet_id asc";
		} 
		else if (!gBean.getBrand_id().equals("") && gBean.getMonth().equals("") && gBean.getRegion_id().equals("")
				&& gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
			sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where brand_id='"
					+ gBean.getBrand_id() + "' and year='" + year + "' and visit_status='published' order by outlet_id asc";
		} 
		
		else if (gBean.getBrand_id() != null && !gBean.getMonth().equals("") && gBean.getRegion_id().equals("")
				&& gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
			sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where brand_id='"
					+ gBean.getBrand_id() + "' and year='" + year + "' and month(visit_date)='" + gBean.getMonth()
					+ "' and visit_status='published' order by outlet_id asc";
		} 
		else if (gBean.getBrand_id() != null && gBean.getMonth().equals("") && gBean.getRegion_id().equals("")
				&& gBean.getDealer_id().equals("") && !gBean.getOutlet_id().equals("")) {
			sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
					+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year + "' and sk_outlet_id='" + gBean.getOutlet_id()
					+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
		}else if (gBean.getBrand_id() != null && !gBean.getMonth().equals("") && gBean.getRegion_id().equals("") //3/18/2020
				&& gBean.getDealer_id().equals("") && !gBean.getOutlet_id().equals("")) {
			sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
					+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year
					+ "' and month(visit_date)='" + gBean.getMonth() + "' and sk_outlet_id='" + gBean.getOutlet_id()
					+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
		}else if (gBean.getRegion_id().equals("")) {
			if (!gBean.getDealer_id().equals("")) {
				System.out.println("here");
				if (gBean.getBrand_id() != null && !gBean.getMonth().equals("") && gBean.getRegion_id().equals("")
						&& !gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
					sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
							+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year
							+ "' and month(visit_date)='" + gBean.getMonth() + "' and mst_shopper_details.dealer_id='"
							+ gBean.getDealer_id() + "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
				} else if (gBean.getBrand_id() != null && gBean.getMonth().equals("") && gBean.getRegion_id().equals("")
						&& !gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
					sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
							+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year
							+ "'  and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
							+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
				} else if (!gBean.getOutlet_id().equals("")) {
					if (gBean.getBrand_id() != null && !gBean.getMonth().equals("") && gBean.getRegion_id().equals("")
							&& !gBean.getDealer_id().equals("") && !gBean.getOutlet_id().equals("")) {
						sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
								+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and sk_outlet_id='"
								+ gBean.getOutlet_id() + "' and year='" + year + "' and month(visit_date)='"
								+ gBean.getMonth() + "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
								+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
					} else if (gBean.getBrand_id() != null && gBean.getMonth().equals("") && gBean.getRegion_id().equals("")
							&& !gBean.getDealer_id().equals("") && !gBean.getOutlet_id().equals("")) {
						sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
								+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and sk_outlet_id="
								+ gBean.getOutlet_id() + " and year='" + year + "' and mst_shopper_details.dealer_id='"
								+ gBean.getDealer_id() + "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
					}
				}
			}
		} else if (!gBean.getRegion_id().equals("")) {
			if (gBean.getBrand_id() != null && !gBean.getMonth().equals("") && !gBean.getRegion_id().equals("")
					&& gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
				sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
						+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year
						+ "' and month(visit_date)='" + gBean.getMonth() + "' and region_id='"
						+ gBean.getRegion_id() + "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
			} else if (gBean.getBrand_id() != null && gBean.getMonth().equals("") && !gBean.getRegion_id().equals("")
					&& gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
				sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
						+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year + "' and region_id='"
						+ gBean.getRegion_id() + "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
			}else if (gBean.getBrand_id() != null && gBean.getMonth().equals("") && !gBean.getRegion_id().equals("") //3/18/2020
					&& gBean.getDealer_id().equals("") && !gBean.getOutlet_id().equals("")) {
				sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
						+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year + "' and region_id='"
						+ gBean.getRegion_id() + "' and sk_outlet_id='"+gBean.getOutlet_id()+"' and visit_status='published' order by mst_shopper_details.outlet_id asc";
			}else if (gBean.getBrand_id() != null && !gBean.getMonth().equals("") && !gBean.getRegion_id().equals("") //3/18/2020
					&& gBean.getDealer_id().equals("") && !gBean.getOutlet_id().equals("")) {
				sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
						+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year + "' and region_id='"
						+ gBean.getRegion_id() + "' and sk_outlet_id='"+gBean.getOutlet_id()+"' and month(visit_date)='"+gBean.getMonth()+"' and visit_status='published' order by mst_shopper_details.outlet_id asc";
			}else if (!gBean.getDealer_id().equals("")) {
				System.out.println("here");
				if (gBean.getBrand_id() != null && !gBean.getMonth().equals("") && !gBean.getRegion_id().equals("")
						&& !gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
					sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
							+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year
							+ "' and month(visit_date)='" + gBean.getMonth() + "'  and region_id='"
							+ gBean.getRegion_id() + "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
							+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
				} else if (gBean.getBrand_id() != null && gBean.getMonth().equals("") && !gBean.getRegion_id().equals("")
						&& !gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
					sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
							+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and year='" + year + "' and region_id='"
							+ gBean.getRegion_id() + "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
							+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
				} else if (!gBean.getOutlet_id().equals("")) {
					if (gBean.getBrand_id() != null && !gBean.getMonth().equals("") && !gBean.getRegion_id().equals("")
							&& !gBean.getDealer_id().equals("") && !gBean.getOutlet_id().equals("")) {
						sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
								+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and sk_outlet_id='"
								+ gBean.getOutlet_id() + "' and year='" + year + "' and month(visit_date)='"
								+ gBean.getMonth() + "'  and region_id='" + gBean.getRegion_id()
								+ "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
								+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
					} else if (gBean.getBrand_id() != null && gBean.getMonth().equals("")
							&& !gBean.getRegion_id().equals("") && !gBean.getDealer_id().equals("")
							&& !gBean.getOutlet_id().equals("")) {
						sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
								+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and sk_outlet_id="
								+ gBean.getOutlet_id() + " and year='" + year + "' and region_id='"
								+ gBean.getRegion_id() + "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
								+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
					}
				}
			}
		}

		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setOutlet_id(rs.getString("outlets"));

				return gBean;
			}
		});
	}
	/*get outlets method for role 7 for overallperformance**/
	public GraphBean getOutletsoverallperFilterForYtdMtdForRole(GraphBean gBean) {
//		if(gBean.getDealer_id()==null) {
//			gBean.setDealer_id("");	
//			}
//			if(gBean.getOutlet_id()==null) {
//				gBean.setOutlet_id("");	
//				}
//			if(gBean.getRegion_id()==null) {
//				gBean.setRegion_id("");	
//				}
		HttpSession session = request.getSession(true);
	    String dealers = (String) session.getAttribute("dealers");
	    String roleId = (String) session.getAttribute("role_id");
	    String region = (String) session.getAttribute("region");
	    if(roleId.equals("7"))
	    {
	      String did=dealers;
	      String rid=region;
	      gBean.setDealer_id(did);
	      gBean.setRegion_id(rid);
	      System.out.println("role 7 in if"+gBean.getDealer_id());
	      System.out.println("role 7 in if"+gBean.getRegion_id());
	      
	    }
		System.out.println("hello this is overall perf YTD MTD");
		//int year = 2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String sql = "";
		System.out.println(gBean.getBrand_id() + "brnd" + gBean.getMonth() + "month" + gBean.getRegion_id() + "region"+ gBean.getDealer_id() + "Dealer");
		// int year = Calendar.getInstance().get(Calendar.YEAR);
		if (gBean.getBrand_id() != null && gBean.getMonth() == null && gBean.getRegion_id() != null
				 && gBean.getDealer_id() != null && gBean.getOutlet_id()  == null) {
			sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
					+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id  and year='" + year + "' and region_id='" + gBean.getRegion_id()
					+ "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
					+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
			System.out.println("1111111111111111111 if");
		} 
		else if (!gBean.getBrand_id().equals("") && gBean.getMonth().equals("") && !gBean.getRegion_id().equals("")
				&& !gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
			sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
					+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id  and year='" + year + "' and region_id='" + gBean.getRegion_id()
					+ "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
					+ "' and visit_status='published' order by mst_shopper_details.outlet_id asc";
			System.out.println("222222222222222 elseif");
		} 
		
		else if (!gBean.getBrand_id().equals("") && !gBean.getMonth().equals("") && !gBean.getRegion_id().equals("")
				&& !gBean.getDealer_id().equals("") && gBean.getOutlet_id().equals("")) {
			sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
					+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id  and year='" + year + "' and region_id='" + gBean.getRegion_id()
					+ "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
					+ "' and month(visit_date)='" + gBean.getMonth()+"' and visit_status='published' order by mst_shopper_details.outlet_id asc";
			System.out.println("3333333333333 elseif");
		}
		else if (!gBean.getBrand_id().equals("") && gBean.getMonth().equals("") && !gBean.getRegion_id().equals("")
				&& !gBean.getDealer_id().equals("") && !gBean.getOutlet_id().equals("")) {
			sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
					+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and sk_outlet_id='"
					+ gBean.getOutlet_id() + "' and year='" + year + "' and region_id='" + gBean.getRegion_id()
					+ "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
					+ "'  and visit_status='published' order by mst_shopper_details.outlet_id asc";
			System.out.println("444444444444 elseif");
		}else {
			if(!gBean.getBrand_id().equals("") && !gBean.getMonth().equals("") && !gBean.getRegion_id().equals("")
					&& !gBean.getDealer_id().equals("") && !gBean.getOutlet_id().equals("")) {
				sql = "select GROUP_CONCAT(distinct mst_shopper_details.outlet_id) as outlets from mst_dealer_outlet,mst_shopper_details where mst_shopper_details.brand_id='"
						+ gBean.getBrand_id() + "' and mst_shopper_details.outlet_id=sk_outlet_id and sk_outlet_id='"
						+ gBean.getOutlet_id() + "' and year='" + year + "' and region_id='" + gBean.getRegion_id()
						+ "' and mst_shopper_details.dealer_id='" + gBean.getDealer_id()
						+ "' and month(visit_date)='" + gBean.getMonth()+"' and visit_status='published' order by mst_shopper_details.outlet_id asc";
				System.out.println("5555555555555555555 final else");
			}
		}
		

		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setOutlet_id(rs.getString("outlets"));

				return gBean;
			}
		});
	}
	/*get outlets method for role 7 for overallperformance ends**/
	public List<GraphBean> getytdmtd(QuestionnaireBean qBean,GraphBean gBean,String oid, String StartData, String EndData, final String monthName,String did) {
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		//final int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println("hello this is ytdmtd");
		// int year = 2019;
		  int year = Calendar.getInstance().get(Calendar.YEAR);
		 //String year1 = "2019";
		  String year1=String.valueOf(year);
		 MysteryShoppingVisitsBean mvBean1=new MysteryShoppingVisitsBean();
			mvBean1=hDao.getScoreWeightage(mvBean1,year1);
			String pe_weightage=mvBean1.getPe_weightage();
			String ct_weightage=mvBean1.getCt_weightage();
			String osc_weightage=mvBean1.getOsc_weightage();
		 
		 String sql="";
//		 if(did==""||did==null){
//			 did="all";
//		 }
		 
		 if(roleId.equals("7"))
			{
				did=dealers;
				System.out.println("role 7 in if condition========"+did);
			}
		    else  if((did=="" || did==null))
	        {
	          did="all";
	          System.out.println("role 8 in elseif condition==========="+did);
	        }		   
		    else 
	        {
	          did= did;  
	          System.out.println("final else when we select dealer========"+did);
	        }
		if(oid == "" || oid == "all" || oid == null){
		
		
		 sql = "select round((select ((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
				+ gBean.getOutlet_id()
				+ ") and section_id='2')+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
				+ gBean.getOutlet_id()
				+ ") and section_id='3')+(SELECT IFNULL(SUM(scored_points)*"+osc_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and (mys_score.dealer_id='"+did+"' or '"+did+"'='all')))/((SELECT IFNULL(SUM(max_points)*"+pe_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
				+ gBean.getOutlet_id()
				+ ") and section_id='2')+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
				+ gBean.getOutlet_id()
				+ ") and section_id='3')+(SELECT IFNULL(SUM(max_points)*"+osc_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and (mys_score.dealer_id='"+did+"' or '"+did+"'='all')))*100),2) as ytd,round((select ((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
				+ gBean.getOutlet_id()
				+ ") and section_id='2')+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
				+ gBean.getOutlet_id()
				+ ") and section_id='3')+(SELECT IFNULL(SUM(scored_points)*"+osc_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and (mys_score.dealer_id='"+did+"' or '"+did+"'='all')))/((SELECT IFNULL(SUM(max_points)*"+pe_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
				+ gBean.getOutlet_id()
				+ ") and section_id='2')+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
				+ gBean.getOutlet_id() 
				+ ") and section_id='3')+(SELECT IFNULL(SUM(max_points)*"+osc_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
				+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
				+ "' and mode_of_contact='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and (mys_score.dealer_id='"+did+"' or '"+did+"'='all')))*100),2) as mtd";

		System.out.println("YTDMTD in If part================"+sql);
		}
		else{
			 sql = "select round((select ((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
					+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
					+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
					+ gBean.getOutlet_id()
					+ ") and section_id='2')+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
					+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
					+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
					+ gBean.getOutlet_id()
					+ ") and section_id='3'))/((SELECT IFNULL(SUM(max_points)*"+pe_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
					+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
					+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
					+ gBean.getOutlet_id()
					+ ") and section_id='2')+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
					+ year + "-01-01' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
					+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
					+ gBean.getOutlet_id()
					+ ") and section_id='3'))*100),2) as ytd,round((select ((SELECT IFNULL(SUM(scored_points)*"+pe_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
					+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
					+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
					+ gBean.getOutlet_id()
					+ ") and section_id='2')+(SELECT IFNULL(SUM(scored_points)*"+ct_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
					+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
					+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
					+ gBean.getOutlet_id()
					+ ") and section_id='3'))/((SELECT IFNULL(SUM(max_points)*"+pe_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
					+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
					+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
					+ gBean.getOutlet_id()
					+ ") and section_id='2')+(SELECT IFNULL(SUM(max_points)*"+ct_weightage+",0) FROM mys_score LEFT JOIN mst_dealer_outlet on mys_score.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details WHERE visit_date BETWEEN '"
					+ StartData + "' and '" + EndData + "' and mst_shopper_details.brand_id='" + gBean.getBrand_id()
					+ "' and mode_of_contact!='Online Sales Channel' and visit_status='published' AND mst_shopper_details.status='active') and mys_score.outlet_id in ("
					+ gBean.getOutlet_id()
					+ ") and section_id='3'))*100),2) as mtd";
			 System.out.println("YTDMTD in else part================"+sql);
		}
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setYTD_YTD(rs.getDouble("ytd"));
				System.out.println("ytd in DAO is====="+rs.getDouble("ytd"));
				gBean.setMTD(rs.getString("mtd"));
				System.out.println("mtd in DAO is====="+rs.getDouble("mtd"));
				gBean.setMonth(monthName + " " + year);
				System.out.println("mtd in DAO is====="+monthName + " " + year);
				return gBean;
			}
		});
	}
	
	public GraphBean getSectionScoreoverallperformance(GraphBean gBean,String brand,String outlet,String dealer,String region,String month) {

		// int year = 2019;
		// int year = Calendar.getInstance().get(Calendar.YEAR);
		
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
		//int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		 
		String sql = "SELECT round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2) as percentage from mys_score where section_id='"
				+ gBean.getSection_id()
				+ "' and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact!='Online Sales Channel' AND mst_shopper_details.visit_status='published'  AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))";
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setPercentage(rs.getString("percentage"));
   System.out.println("final percentage"+rs.getString("percentage"));
				return gBean;
			}
		});
	}
	/*
	 * ytd mtd manoj
	 */
	
	/*
	 * overall performance pending functionality of(pie chart and tables) manoj d
	 */
	public List<GraphBean> getOverallPerformanceSectionWiseScoreData(QuestionnaireBean qBean, GraphBean gBean, String section_id,String mid,String oid,String bid,String did,String rid) {
		//final String brand = gBean.getBrand_id();
		final String outlets = qBean.getOutlets();
		String brand= bid;
		String outlet = oid;
		String dealer= did;
		String region = rid;
		String month = mid;
		System.out.println("select * from mst_section where sk_section_id='" + section_id + ";");
		return template.query("select * from mst_section where sk_section_id='" + section_id + "';",
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setBrand_id(brand);
						gBean.setOutlets(outlets);
						gBean.setSection_name(rs.getString("section_name"));
						gBean.setSection_id(rs.getString("sk_section_id"));
						List<GraphBean> data = getsectionsscoreoverallperformanceData(gBean,brand,outlet,dealer,region,month);
						gBean.setSectionScore(data);
						GraphBean data1 = getSectionScoreoverallperformanceData(gBean,brand,outlet,dealer,region,month);
						gBean.setPercentage(data1.getPercentage());

						return gBean;
					}
				});
	}
	public List<GraphBean> getsectionsscoreoverallperformanceData(GraphBean gBean,String brand,String outlet,String dealer, String region,String month) {
		
		
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
		//int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		//System.out.println("section 2 process");
		//System.out.println("SELECT mst_shopper_details.visit_date,mys_score.* FROM `mys_score`, mst_shopper_details where (mys_score.outlet_id='"+oid+"' or '"+oid+"'='all') AND (mys_score.dealer_id='"+did+"' or '"+did+"'='all') AND (mys_score.region_id='"+rid+"' or '"+rid+"'='all') AND mys_score.shopper_id=mst_shopper_details.sk_shopper_id AND (month(visit_date)='"+mid+"' or '"+mid+"'='all') AND mode_of_contact!='Online Sales Channel' AND mys_score.section_id='"+section_id+"'  AND mys_score.status='active' GROUP BY mys_score.subsection_id;");
		 System.out.println("select sk_subsection_id,mst_subsection.subsection_name,round(AVG(mys_score.percentage),2) as average from mys_score,mst_subsection where subsection_id=sk_subsection_id and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact='Online Sales Channel' AND mst_shopper_details.visit_status='published'  AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL')  and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) GROUP BY mys_score.subsection_id");
		return template.query("select sk_subsection_id,mst_subsection.subsection_name,round(AVG(mys_score.percentage),2) as average from mys_score,mst_subsection where subsection_id=sk_subsection_id and shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact='Online Sales Channel' AND mst_shopper_details.visit_status='published'  AND (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL')  and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL')) GROUP BY mys_score.subsection_id;",
				
				
				new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setSubsection_name(rs.getString("subsection_name"));
						gBean.setSubSection_id(rs.getString("sk_subsection_id"));
						gBean.setPercentage(rs.getString("average"));
						System.out.println(("Section Average======="+rs.getString("average")+rs.getString("subsection_name")));
						

						return gBean;
					}
				});
	}
	public GraphBean getSectionScoreoverallperformanceData(GraphBean gBean,String brand,String outlet,String dealer, String region,String month) {

		// int year = 2019;
		// int year = Calendar.getInstance().get(Calendar.YEAR);
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
		//int year=2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		
		String sql = "SELECT round(((IFNULL(sum(scored_points),0)/IFNULL(sum(max_points),0))*100),2) as percentage from mys_score where shopper_id in (SELECT sk_shopper_id FROM mst_shopper_details LEFT JOIN mst_dealer_outlet ON mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  WHERE year='"+year+"' and mst_shopper_details.brand_id='"+brand+"' and  mst_shopper_details.mode_of_contact='Online Sales Channel' AND  (mst_shopper_details.dealer_id='"+dealer+"' or '"+dealer+"'='ALL') and (mst_shopper_details.outlet_id='"+outlet+"' OR '"+outlet+"'='ALL') and (month(visit_date)='"+month+"' or '"+month+"'='All') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL'))";
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setPercentage(rs.getString("percentage"));
   System.out.println("final percentage"+rs.getString("percentage"));
				return gBean;
			}
		});
	}
	
	public GraphBean getModeByMonth(GraphBean gBean) {

		//int year = 2019;
		 int year = Calendar.getInstance().get(Calendar.YEAR);

		String sql = "select count(mode_of_contact) as count from mst_shopper_details where (month(visit_date)='"
				+ gBean.getMonth() + "' or'"+gBean.getMonth()+"'='ALL') and year='" + year + "' and mode_of_contact='EMail/Website'";
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setCount(rs.getString("count"));

				return gBean;
			}
		});
	}
	/*
	 * overall performance pending functionality of(pie chart and tables) manoj d
	 */
	public MysteryShoppingVisitsBean getlowscoringcountIds(MysteryShoppingVisitsBean mBean, String report_type,
			String month, String dealer_id, String outlet_id, String brand_id, String year, String region) {
		MysteryShoppingVisitsBean mvBean1=new MysteryShoppingVisitsBean();
        mvBean1=hDao.getScoreWeightage(mvBean1,year);
		String pe_weightage=mvBean1.getPe_weightage();
		String ct_weightage=mvBean1.getCt_weightage();
		String osc_weightage=mvBean1.getOsc_weightage();
		
		String sql="SELECT count(*) as count FROM(SELECT *,IFNULL(round(((pe_sc_points+ct_sc_points)/(pe_max_points+ct_max_points))*100,2),0) as percentage FROM(SELECT * FROM(SELECT * FROM(SELECT  mst_brand.brand_name,monthname(visit_date)as monthname,month(visit_date)as month, mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_name,mst_shopper_details.sc_name, mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_geo_region.region_name, mst_shopper_details.sk_shopper_id,mst_shopper_details.name,mst_shopper_details.dealer_id,mst_shopper_details.outlet_id,mst_shopper_details.visit_date,mst_shopper_details.year,mst_shopper_details.mode_of_contact,mst_shopper_details.brand_id,mst_shopper_details.visit_status,mst_dealer.region_id FROM mst_shopper_details,mst_dealer,mst_dealer_outlet,mst_geo_region,mst_brand WHERE mst_brand.sk_brand_id=mst_shopper_details.brand_id and mst_geo_region.sk_region_id=mst_dealer_outlet.region_id and mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id and mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND visit_status='published' AND year='"+year+"' and mode_of_contact!='Online Sales Channel' AND (EXTRACT(MONTH FROM visit_date)='"+month+"' OR '"+month+"'='ALL') AND (mst_dealer_outlet.region_id='"+region+"' OR '"+region+"'='ALL') AND (mst_shopper_details.brand_id='"+brand_id+"' OR '"+brand_id+"'='ALL') AND (mst_shopper_details.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='ALL')) as res1 LEFT JOIN (SELECT shopper_id as pe_shopper_id,SUM(scored_points)*"+pe_weightage+" as pe_sc_points,SUM(max_points)*"+pe_weightage+" as pe_max_points FROM mys_score WHERE section_id=2 GROUP BY shopper_id) as res2 ON res1.sk_shopper_id=res2.pe_shopper_id) as res3 LEFT JOIN (SELECT shopper_id as ct_shopper_id,SUM(scored_points)*"+ct_weightage+" as ct_sc_points,SUM(max_points)*"+ct_weightage+" as ct_max_points FROM mys_score WHERE section_id=3 GROUP BY shopper_id) as res4 ON res3.sk_shopper_id=res4.ct_shopper_id) as res5 GROUP BY sk_shopper_id) as res6 WHERE percentage<90 order by month";
	    System.out.println("count ="+sql);
	    return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
	      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
	    	  mBean.setCount(rs.getString("count"));
	        return mBean;
	      }
	    });
	  }
	public MysteryShoppingVisitsBean getsummarycountIds(MysteryShoppingVisitsBean mBean, String report_type,
			String month, String dealer, String outlet_id, String brand_id, String year, String region) {
		String sql="select count(*) as count from (SELECT mst_shopper_details.brand_id,mys_score.osc_flag, mys_score.shopper_id, mys_score.quarter, mys_score.dealer_id,mys_score.outlet_id,mys_score.year,mys_score.month, mys_score.Outlet_name,mys_score.dealer_name,mys_score.region_name,mst_msm_result.visit_brand_name,mst_msm_result.outlet_region_name,mst_msm_result.outlet_city_name,mst_shopper_details.shopper_link_name,mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_visit_date,mst_shopper_details.sc_name as salesperson_name,mst_msm_result.city_tier,mst_msm_result.city_metro,mst_dealer_outlet.contact_person as regional_manager,monthname(mst_msm_result.scheduled_date)as monthname,month(mst_msm_result.scheduled_date)as months,IFNULL(SUM(mys_score.scored_points),0) as point_reached,IFNULL(SUM(mys_score.max_points),0) as maximum_points,mys_txn_outlet_score.monthly_score_percentage as monthly_outlet_score,mys_score.shopper_id as sid FROM `mys_score`, mst_shopper_details, mst_msm_result,mst_dealer_outlet ,mys_txn_outlet_score,mys_txn_dealer_score WHERE mys_score.shopper_id=mst_shopper_details.sk_shopper_id and mst_msm_result.shopper_id=mys_score.shopper_id and   mys_score.osc_flag='0' and mst_dealer_outlet.sk_outlet_id=mys_score.outlet_id AND mys_score.year='"+year+"' AND (month(mst_shopper_details.visit_date)<='"+month+"' OR '"+month+"'='all') AND (mys_score.region_id='"+region+"' OR '"+region+"'='all') AND (mys_score.dealer_id='"+dealer+"' OR '"+dealer+"'='all') AND (mys_score.outlet_id='"+outlet_id+"' OR '"+outlet_id+"'='all') and mst_shopper_details.brand_id='"+brand_id+"' and mst_shopper_details.visit_status='published'    and mys_txn_outlet_score.shopper_id=mys_score.shopper_id    GROUP BY  mys_score.shopper_id) res1 ";
	    System.out.println("count ="+sql);
	    return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
	      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
	    	  mBean.setCount(rs.getString("count"));
	        return mBean;
	      }
	    });
	  }
	
	public List<MysteryShoppingVisitsBean> getLoweringScore1(MysteryShoppingVisitsBean mvBean, String year, String month,
		      String region, String outlet, String brand_id, String dealer_id,String limit,String offset) {
		    int  pageid = (Integer.parseInt(mvBean.getLimit()));
		    int  offset1 = (Integer.parseInt(mvBean.getOffset()));
		    MysteryShoppingVisitsBean mvBean1=new MysteryShoppingVisitsBean();
	        mvBean1=hDao.getScoreWeightage(mvBean1,year);
			String pe_weightage=mvBean1.getPe_weightage();
			String ct_weightage=mvBean1.getCt_weightage();
			String osc_weightage=mvBean1.getOsc_weightage();
			
		    String sql="SELECT * FROM(SELECT *,IFNULL(round(((pe_sc_points+ct_sc_points)/(pe_max_points+ct_max_points))*100,2),0) as percentage FROM(SELECT * FROM(SELECT * FROM(SELECT  mst_brand.brand_name,monthname(visit_date)as monthname,month(visit_date)as month, mst_shopper_details.shopper_link_email,mst_shopper_details.shopper_link_contact,mst_shopper_details.shopper_link_name,mst_shopper_details.sc_name, mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_geo_region.region_name, mst_shopper_details.sk_shopper_id,mst_shopper_details.name,mst_shopper_details.dealer_id,mst_shopper_details.outlet_id,mst_shopper_details.visit_date,mst_shopper_details.year,mst_shopper_details.mode_of_contact,mst_shopper_details.brand_id,mst_shopper_details.visit_status,mst_dealer.region_id FROM mst_shopper_details,mst_dealer,mst_dealer_outlet,mst_geo_region,mst_brand WHERE mst_brand.sk_brand_id=mst_shopper_details.brand_id and mst_geo_region.sk_region_id=mst_dealer_outlet.region_id and mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id and mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND visit_status='published' AND year='"+year+"' and mode_of_contact!='Online Sales Channel' AND (EXTRACT(MONTH FROM visit_date)='"+month+"' OR '"+month+"'='ALL') AND (mst_dealer.region_id='"+region+"' OR '"+region+"'='ALL') AND (mst_shopper_details.brand_id='"+brand_id+"' OR '"+brand_id+"'='ALL') AND (mst_shopper_details.dealer_id='"+dealer_id+"' OR '"+dealer_id+"'='ALL')) as res1 LEFT JOIN (SELECT shopper_id as pe_shopper_id,SUM(scored_points)*"+pe_weightage+" as pe_sc_points,SUM(max_points)*"+pe_weightage+" as pe_max_points FROM mys_score WHERE section_id=2 GROUP BY shopper_id) as res2 ON res1.sk_shopper_id=res2.pe_shopper_id) as res3 LEFT JOIN (SELECT shopper_id as ct_shopper_id,SUM(scored_points)*"+ct_weightage+" as ct_sc_points,SUM(max_points)*"+ct_weightage+" as ct_max_points FROM mys_score WHERE section_id=3 GROUP BY shopper_id) as res4 ON res3.sk_shopper_id=res4.ct_shopper_id) as res5 GROUP BY sk_shopper_id) as res6 WHERE percentage<90 order by month    Limit " + pageid  + " OFFSET " + offset1 + " ";
		    System.out.println(sql);
		    return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
		      public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
		        MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
		        mvBean.setMonth(rs.getString("monthname"));
		        mvBean.setDealer_name(rs.getString("dealer_name"));
		        mvBean.setOutlet_name(rs.getString("outlet_name"));
		        mvBean.setVisit_date(rs.getString("visit_date"));
		        mvBean.setName(rs.getString("shopper_link_name"));
		        mvBean.setContact_number(rs.getString("shopper_link_contact"));
		        mvBean.setEmail(rs.getString("shopper_link_email"));
		        mvBean.setSc_name(rs.getString("sc_name"));
		        mvBean.setOutlet_score(rs.getString("percentage"));
		        double d = Double.parseDouble(rs.getString("percentage"));
		        mvBean.setOutlet_score(String.format("%.2f", d));
		        mvBean.setRegion_name(rs.getString("region_name"));
		        mvBean.setBrand_name(rs.getString("brand_name"));
		        List<MysteryShoppingVisitsBean> missedOpportunitesList=getMissedOpportunities(mvBean, rs.getString("sk_shopper_id"));
		        mvBean.setMissedOpportunitesList(missedOpportunitesList);
		        
		        return mvBean;
		      }
		    });

		  }
	public ReportsBean getNonOscScorepoints(ReportsBean rBean, String sk_shopper_id) {
		String sql="SELECT sum(`scored_points`) as scored_points ,sum(`max_points`) as max_points FROM `mys_score` WHERE `shopper_id`='"+sk_shopper_id+"' and osc_flag='0'";
		System.out.println(sql);	
		return template.queryForObject(sql, new RowMapper<ReportsBean>() {
				public ReportsBean mapRow(ResultSet rs, int row) throws SQLException {
					rBean.setMax_points(rs.getString("max_points"));
					rBean.setScored_points(rs.getString("scored_points"));
					return rBean;
				}
			});
		}
	public GraphBean getOutletsoverallperFilter(GraphBean gBean) {
		// int year = 2019;
		int year = Calendar.getInstance().get(Calendar.YEAR);
				String sql = "";

				if ((gBean.getRegion_id() == null && gBean.getDealer_id() == null && gBean.getOutlet_id() == null)
						|| (gBean.getRegion_id().equals("") && gBean.getDealer_id().equals("")
								&& gBean.getOutlet_id().equals(""))) {
					sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published'";
				} else if (gBean.getRegion_id().equals("All") && gBean.getDealer_id().equals("")
						&& gBean.getOutlet_id().equals("")) {
					sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published'";
				} else if (!gBean.getRegion_id().equals("") && gBean.getDealer_id().equals("")
						&& gBean.getOutlet_id().equals("")) {
					sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published'  and outlet_id in (select sk_outlet_id from mst_dealer_outlet where region_id='"
							+ gBean.getRegion_id() + "')";

				}

				else if (gBean.getRegion_id().equals("")) {
					if (!gBean.getDealer_id().equals("")) {
						System.out.println("here");
						if (gBean.getRegion_id().equals("") && !gBean.getDealer_id().equals("")
								&& gBean.getOutlet_id().equals("")) {
							sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published' and outlet_id in (select sk_outlet_id from mst_dealer_outlet where dealer_id='"
									+ gBean.getDealer_id() + "')";

						} else if (!gBean.getOutlet_id().equals("")) {
							if (gBean.getRegion_id().equals("") && !gBean.getDealer_id().equals("")
									&& !gBean.getOutlet_id().equals("")) {
								sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published' and outlet_id='"
										+ gBean.getOutlet_id() + "'";

							}
						}
					}
				}

				else if (!gBean.getDealer_id().equals("")) {
					System.out.println("here");
					if (!gBean.getRegion_id().equals("") && !gBean.getDealer_id().equals("")
							&& gBean.getOutlet_id().equals("")) {
						sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published' and outlet_id in (select sk_outlet_id from mst_dealer_outlet where dealer_id='"
								+ gBean.getDealer_id() + "')";

					} else if (!gBean.getOutlet_id().equals("")) {
				/*
				 * if (!gBean.getRegion_id().equals("") && !gBean.getDealer_id().equals("") &&
				 * !gBean.getOutlet_id().equals("")) { sql =
				 * "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published' and outlet_id='"
				 * + gBean.getOutlet_id() + "'";
				 * 
				 * }
				 */
						if (gBean.getRegion_id().equals("") && gBean.getDealer_id().equals("")
								&& !gBean.getOutlet_id().equals("")) {
							sql = "select GROUP_CONCAT(distinct outlet_id) as outlets from mst_shopper_details where visit_status='published' and outlet_id='"
									+ gBean.getOutlet_id() + "'";

						}
					}
				}

				System.out.println(sql);
				return template.queryForObject(sql, new RowMapper<GraphBean>() {
					public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
						GraphBean gBean = new GraphBean();
						gBean.setOutlet_id(rs.getString("outlets"));
						return gBean;
					}
				});
	}
	
	public List<GraphBean> getoutletsforcompitation(GraphBean gBean, String did, String rid) {
		if(did=="" || did==null){
			did="all";
		}
		if(rid=="" || rid==null){
			rid="all";
		}
		
		String sql = "SELECT * FROM `mst_dealer_outlet` WHERE `outlet_status`='active'  and (`dealer_id`='"+did+"' or '"+did+"'='All') and (region_id='"+rid+"' or '"+rid+"' ='All') ";
		System.out.println(sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setOutlet_id(rs.getString("sk_outlet_id"));
				gBean.setOutlet_name(rs.getString("outlet_name"));
				return gBean;
			}
		});
	}
	
	
	
	public List<GraphBean> getpromotorDetractorData(GraphBean gBean, String did, String bid,
			String rid, String oid, String month) {
		
		 if(did==null || did=="") {
			 did="all";
		 }
		 if(rid==null || rid=="") {
			 rid="all";
		 }
		 if(oid==null || oid=="") {
			 oid="all";
		 }
		 if(month==null || month=="") {
			 month="all";
		 }
		 int  year;
			String question_id="";
		    if(gBean.getYear()==null || gBean.getYear()=="")
		    {
		      year=Calendar.getInstance().get(Calendar.YEAR);
		      question_id="353";
	          
		    }
		    else if(gBean.getYear().equals("2019"))
		    {
		      year=Integer.parseInt(gBean.getYear());
		      question_id="";
		      
		    }
		    else
		    {
		    	year=Integer.parseInt(gBean.getYear());
		    	question_id="353";
		    }
		 
		String sql = "SELECT totalcount,PromotorCount,PassiveCount,DetractorCount,round(((PromotorCount/totalcount)*100),2)as PromotorCountpercentage,round(((PassiveCount/totalcount)*100),2)as PassiveCountpercentage,round(((DetractorCount/totalcount)*100),2)as DetractorCountpercentage FROM(SELECT COUNT(mys_txn_answers.select_option_text)as totalcount,count(CASE  WHEN mys_txn_answers.select_option_text in (9,10)  and select_option_text is not null THEN 1 ELSE null  END) AS PromotorCount,count(CASE  WHEN mys_txn_answers.select_option_text in (7,8) and select_option_text is not null THEN 1 ELSE null  END ) AS PassiveCount,count(CASE  WHEN mys_txn_answers.select_option_text in (1,2,3,4,5,6)  and select_option_text is not null THEN 1 ELSE null  END) AS DetractorCount from  `mys_txn_answers` left join mst_shopper_details on  mst_shopper_details.sk_shopper_id=mys_txn_answers.shopper_id left join mst_dealer_outlet on mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id WHERE question_id='"+question_id+"' and mst_shopper_details.visit_status='published'  and    (mst_shopper_details.brand_id='"+bid+"' or '"+bid+"'='all') and mst_shopper_details.year='"+year+"' and (month(mst_shopper_details.visit_date)='"+month+"' or '"+month+"'='all') and (mst_shopper_details.dealer_id='"+did+"' or '"+did+"'='all') and (mst_shopper_details.outlet_id='"+oid+"' or '"+oid+"'='all')  and (mst_dealer_outlet.region_id='"+rid+"' or '"+rid+"'='all')  and mys_txn_answers.status='active')res1";
		System.out.println("promotor"+sql);
		return template.query(sql, new RowMapper<GraphBean>() {
			public GraphBean mapRow(ResultSet rs, int row) throws SQLException {
				GraphBean gBean = new GraphBean();
				gBean.setPromotorCountpercentage(rs.getString("PromotorCountpercentage"));
				gBean.setPassiveCountpercentage(rs.getString("PassiveCountpercentage"));
				gBean.setDetractorCountpercentage(rs.getString("DetractorCountpercentage"));
				gBean.setYear(String.valueOf(year));
				return gBean;
			}
		});
	}
} 
	     
