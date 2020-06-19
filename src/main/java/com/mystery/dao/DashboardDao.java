package com.mystery.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mystery.beans.DashboardBean;
import com.mystery.beans.MenuBean;

public class DashboardDao {
	
	@Autowired
    JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public DashboardBean getFashcardPercentage(DashboardBean dBean, String currentmonth, int year1, int year3, String currentmonth1, String roleId, String dealers, String region) throws ParseException {
		Date date = new SimpleDateFormat("MMMM").parse(currentmonth);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int mon=cal.get(Calendar.MONTH)+1;
		
		String sql ="";
		if(roleId.contentEquals("2") || roleId.contentEquals("4") || roleId.contentEquals("5")) {
			dealers="all";
			
			sql= "select tobeconducted,conducted,ifnull(national_avg,0) as national_avg ,ifnull(PE,0) as PE,ifnull(CT,0) as CT,ifnull(osc ,0)as osc from (SELECT count(CASE  WHEN mst_shopper_details.visit_status='scheduled'  and visit_status is not null THEN 1 ELSE null  END) AS tobeconducted,count(CASE  WHEN mst_shopper_details.visit_status!='scheduled'  and visit_status is not null THEN 1 ELSE null  END) AS conducted FROM `mst_shopper_details`,mst_dealer_outlet WHERE mst_shopper_details.year='"+year3+"' and monthname(mst_shopper_details.visit_date)='"+currentmonth1+"' and mst_shopper_details.brand_id='"+dBean.getBrand_id()+"' and mst_shopper_details.status='active' and mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id and  (mst_dealer_outlet.dealer_id='"+dealers+"' or '"+dealers+"'='all') )res1 join (SELECT round(((sum(scored_point_721)/sum(maximum_point_721))*100),2)as national_avg FROM mys_txn_dealer_score WHERE brand_id='"+dBean.getBrand_id()+"' and year='"+year1+"' and month<="+mon+")as res2 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as PE FROM `mys_score`,mst_shopper_details  WHERE brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='0' and mys_score.`year`='"+year1+"' and `section_id`='2' and month(mst_shopper_details.visit_date)<='"+mon+"')res3 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as CT FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.sk_shopper_id= mys_score.shopper_id and  brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='0' and mys_score.`year`='"+year1+"' and `section_id`='3' and month(mst_shopper_details.visit_date)<='"+mon+"')res5 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as osc FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.sk_shopper_id= mys_score.shopper_id and  brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='1' and mys_score.`year`='"+year1+"'  and month(mst_shopper_details.visit_date)<='"+mon+"')res4";
		}else if(roleId.contentEquals("6") ){
			sql= "select tobeconducted,conducted,ifnull(national_avg,0) as national_avg ,ifnull(PE,0) as PE,ifnull(CT,0) as CT,ifnull(osc ,0)as osc from (SELECT count(CASE  WHEN mst_shopper_details.visit_status='scheduled'  and visit_status is not null THEN 1 ELSE null  END) AS tobeconducted,count(CASE  WHEN mst_shopper_details.visit_status!='scheduled'  and visit_status is not null THEN 1 ELSE null  END) AS conducted FROM `mst_shopper_details`,mst_dealer_outlet WHERE mst_shopper_details.year='"+year3+"' and monthname(mst_shopper_details.visit_date)='"+currentmonth1+"' and mst_shopper_details.brand_id='"+dBean.getBrand_id()+"' and mst_shopper_details.status='active' and mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id and (mst_dealer_outlet.region_id='"+region+"' or '"+region+"'='all' ) )res1 join (SELECT round(((sum(scored_point_721)/sum(maximum_point_721))*100),2)as national_avg FROM mys_txn_dealer_score WHERE brand_id='"+dBean.getBrand_id()+"' and year='"+year1+"' and month<="+mon+")as res2 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as PE FROM `mys_score`,mst_shopper_details  WHERE brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='0' and mys_score.`year`='"+year1+"' and `section_id`='2' and month(mst_shopper_details.visit_date)<='"+mon+"')res3 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as CT FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.sk_shopper_id= mys_score.shopper_id and  brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='0' and mys_score.`year`='"+year1+"' and `section_id`='3' and month(mst_shopper_details.visit_date)<='"+mon+"')res5 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as osc FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.sk_shopper_id= mys_score.shopper_id and  brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='1' and mys_score.`year`='"+year1+"'  and month(mst_shopper_details.visit_date)<='"+mon+"')res4";
		} else {
			sql= "select tobeconducted,conducted,ifnull(national_avg,0) as national_avg ,ifnull(PE,0) as PE,ifnull(CT,0) as CT,ifnull(osc ,0)as osc from (SELECT count(CASE  WHEN mst_shopper_details.visit_status='scheduled'  and visit_status is not null THEN 1 ELSE null  END) AS tobeconducted,count(CASE  WHEN mst_shopper_details.visit_status!='scheduled'  and visit_status is not null THEN 1 ELSE null  END) AS conducted FROM `mst_shopper_details`,mst_dealer_outlet WHERE mst_shopper_details.year='"+year3+"' and monthname(mst_shopper_details.visit_date)='"+currentmonth1+"' and mst_shopper_details.brand_id='"+dBean.getBrand_id()+"' and mst_shopper_details.status='active' and mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id and  (mst_dealer_outlet.dealer_id='"+dealers+"' or '"+dealers+"'='all') )res1 join (SELECT round(((sum(scored_point_721)/sum(maximum_point_721))*100),2)as national_avg FROM mys_txn_dealer_score WHERE brand_id='"+dBean.getBrand_id()+"' and year='"+year1+"' and month<="+mon+")as res2 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as PE FROM `mys_score`,mst_shopper_details  WHERE brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='0' and mys_score.`year`='"+year1+"' and `section_id`='2' and month(mst_shopper_details.visit_date)<='"+mon+"')res3 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as CT FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.sk_shopper_id= mys_score.shopper_id and  brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='0' and mys_score.`year`='"+year1+"' and `section_id`='3' and month(mst_shopper_details.visit_date)<='"+mon+"')res5 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as osc FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.sk_shopper_id= mys_score.shopper_id and  brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='1' and mys_score.`year`='"+year1+"'  and month(mst_shopper_details.visit_date)<='"+mon+"')res4";
		}
		
		
		System.out.println(sql);
		

		//System.out.println("select tobeconducted,conducted,ifnull(national_avg,0) as national_avg ,ifnull(PE,0) as PE,ifnull(CT,0) as CT,ifnull(osc ,0)as osc from (SELECT count(CASE  WHEN mst_shopper_details.visit_status='scheduled'  and visit_status is not null THEN 1 ELSE null  END) AS tobeconducted,count(CASE  WHEN mst_shopper_details.visit_status!='scheduled'  and visit_status is not null THEN 1 ELSE null  END) AS conducted FROM `mst_shopper_details` WHERE mst_shopper_details.year='"+year3+"' and monthname(mst_shopper_details.visit_date)='"+currentmonth1+"' and brand_id='"+dBean.getBrand_id()+"' and mst_shopper_details.status='active' and )res1 join (SELECT round(((sum(scored_point_721)/sum(maximum_point_721))*100),2)as national_avg FROM mys_txn_dealer_score WHERE brand_id='"+dBean.getBrand_id()+"' and year='"+year1+"' and month<="+mon+")as res2 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as PE FROM `mys_score`,mst_shopper_details  WHERE brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='0' and mys_score.`year`='"+year1+"' and `section_id`='2' and month(mst_shopper_details.visit_date)<='"+mon+"')res3 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as CT FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.sk_shopper_id= mys_score.shopper_id and  brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='0' and mys_score.`year`='"+year1+"' and `section_id`='3' and month(mst_shopper_details.visit_date)<='"+mon+"')res5 join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as osc FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.sk_shopper_id= mys_score.shopper_id and  brand_id='"+dBean.getBrand_id()+"' and `osc_flag`='1' and mys_score.`year`='"+year1+"'  and month(mst_shopper_details.visit_date)<='"+mon+"')res4;");
		return template.queryForObject(sql,
				new RowMapper<DashboardBean>() {
					public DashboardBean mapRow(ResultSet rs, int row) throws SQLException {
						dBean.setPE_avg(rs.getString("PE"));
						dBean.setCT_avg(rs.getString("CT"));
						dBean.setMys_conducted(rs.getString("conducted"));
						dBean.setMys_tobe_conducted(rs.getString("tobeconducted"));
						dBean.setOsc_avg(rs.getString("osc"));
						dBean.setNational_avg(rs.getString("national_avg"));
						return dBean;
					}
				});
	}

	public DashboardBean checkCurrentMonthClosedVisits(DashboardBean dBean, String currentmonth, String brand_id, int year1) {

		String sql = "select count(*) as count from mst_shopper_details where visit_status='published' and monthName(visit_date)='"
				+ currentmonth + "' and brand_id='" + brand_id + "' and year='"+year1+"' ;";
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<DashboardBean>() {
			public DashboardBean mapRow(ResultSet rs, int row) throws SQLException {
				dBean.setCount(rs.getString("count"));
				return dBean;
			}
		});
	}

	public List<DashboardBean> getScheduledloc(DashboardBean dBean, int year1, String bid, String currentmonth, String roleId, String dealers, String region) {
		String sql ="";
		if(roleId.contentEquals("2") || roleId.contentEquals("4") || roleId.contentEquals("5")) {
			dealers="all";
			
			sql= "SELECT * FROM mst_dealer_outlet WHERE sk_outlet_id in (select outlet_id from mst_shopper_details where visit_status='SCHEDULED' and brand_id='"
					+ bid + "' and year='"+year1+"' and monthName(visit_date)='"+currentmonth+"') and (mst_dealer_outlet.dealer_id='"+dealers+"' or '"+dealers+"'='all')";
		}else if(roleId.contentEquals("6") ){
			sql= "SELECT * FROM mst_dealer_outlet WHERE sk_outlet_id in (select outlet_id from mst_shopper_details where visit_status='SCHEDULED' and brand_id='"
					+ bid + "' and year='"+year1+"' and monthName(visit_date)='"+currentmonth+"') and (mst_dealer_outlet.region_id='"+region+"' or '"+region+"'='all')";
		} else {
			sql= "SELECT * FROM mst_dealer_outlet WHERE sk_outlet_id in (select outlet_id from mst_shopper_details where visit_status='SCHEDULED' and brand_id='"
					+ bid + "' and year='"+year1+"' and monthName(visit_date)='"+currentmonth+"') and (mst_dealer_outlet.dealer_id='"+dealers+"' or '"+dealers+"'='all')";
		}

		

		System.out.println(sql);
		return template.query(sql, new RowMapper<DashboardBean>() {
			public DashboardBean mapRow(ResultSet rs, int row) throws SQLException {
				DashboardBean dBean = new DashboardBean();
				dBean.setLongitute(rs.getString("lang"));
				dBean.setLatitude(rs.getString("lat"));
				return dBean;
			}
		});
	}

	public List<DashboardBean> getConductedloc(DashboardBean dBean, int year1, String bid, String currentmonth, String roleId, String dealers, String region) {
		String sql ="";
		if(roleId.contentEquals("2") || roleId.contentEquals("4") || roleId.contentEquals("5")) {
			dealers="all";
			
			sql= "SELECT * FROM mst_dealer_outlet WHERE sk_outlet_id in (select outlet_id from mst_shopper_details where visit_status!='SCHEDULED' and brand_id='"
					+ bid + "' and year='"+year1+"' and monthName(visit_date)='"+currentmonth+"') and (mst_dealer_outlet.dealer_id='"+dealers+"' or '"+dealers+"'='all')";
		}else if(roleId.contentEquals("6") ){
			sql= "SELECT * FROM mst_dealer_outlet WHERE sk_outlet_id in (select outlet_id from mst_shopper_details where visit_status!='SCHEDULED' and brand_id='"
					+ bid + "' and year='"+year1+"' and monthName(visit_date)='"+currentmonth+"') and (mst_dealer_outlet.region_id='"+region+"' or '"+region+"'='all')";
		} else {
			sql= "SELECT * FROM mst_dealer_outlet WHERE sk_outlet_id in (select outlet_id from mst_shopper_details where visit_status!='SCHEDULED' and brand_id='"
					+ bid + "' and year='"+year1+"' and monthName(visit_date)='"+currentmonth+"') and (mst_dealer_outlet.dealer_id='"+dealers+"' or '"+dealers+"'='all')";
		}

		System.out.println(sql);
		return template.query(sql, new RowMapper<DashboardBean>() {
			public DashboardBean mapRow(ResultSet rs, int row) throws SQLException {
				DashboardBean dBean = new DashboardBean();
				dBean.setLongitute(rs.getString("lang"));
				dBean.setLatitude(rs.getString("lat"));
				return dBean;
			}
		});
	}

	public List<DashboardBean> getdashboardTabledata(DashboardBean dBean, int year, String bid,
			String currentmonth, String roleId, String dealers, String region) throws ParseException {
		Date date = new SimpleDateFormat("MMMM").parse(currentmonth);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month=cal.get(Calendar.MONTH)+1;
		String sql ="";
		if(roleId.contentEquals("2") || roleId.contentEquals("4") || roleId.contentEquals("5")) {
			dealers="all";
			
			sql= "SELECT * from (SELECT movement,dealer_id,monthly_score_percentage,mys_txn_dealer_score.year,mys_txn_dealer_score.month,mys_txn_dealer_score.ytd_score_percentage,mst_dealer.dealer_name, IF(monthly_score_percentage=@_last_score,@curRank:=@curRank,@curRank:=@_sequence) AS rank, @_sequence:=@_sequence+1 as sequence,@_last_score:=monthly_score_percentage AS last_score FROM mys_txn_dealer_score,mst_dealer,(SELECT @curRank := 1, @_sequence:=1, @_last_score:=0) r WHERE mys_txn_dealer_score.brand_id='"+bid+"' and year='"+year+"' and month<="+month+" and mys_txn_dealer_score.dealer_id=mst_dealer.sk_dealer_id and (mst_dealer.region_id='"+dealers+"' or '"+dealers+"'='all')  ORDER BY  monthly_score_percentage DESC)res1 left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as PE,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='2' and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mst_shopper_details.dealer_id='"+dealers+"' or '"+dealers+"'='all') GROUP by mys_score.dealer_id)as res2 on res1.dealer_id=res2.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as CT,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='3' and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mst_shopper_details.dealer_id='"+dealers+"' or '"+dealers+"'='all') GROUP by mys_score.dealer_id)as res3 on res1.dealer_id=res3.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as OSC,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='1' and mys_score.`year`='"+year+"'  and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mst_shopper_details.dealer_id='"+dealers+"' or '"+dealers+"'='all') GROUP by mys_score.dealer_id)as res4 on res1.dealer_id=res4.dealer_id  GROUP by res1.dealer_id";
		}else if(roleId.contentEquals("6") ){
			sql= "SELECT * from (SELECT movement,mys_txn_dealer_score.dealer_id,monthly_score_percentage,mys_txn_dealer_score.year,mys_txn_dealer_score.month,mys_txn_dealer_score.ytd_score_percentage,mst_dealer.dealer_name, IF(monthly_score_percentage=@_last_score,@curRank:=@curRank,@curRank:=@_sequence) AS rank, @_sequence:=@_sequence+1 as sequence,@_last_score:=monthly_score_percentage AS last_score FROM mys_txn_dealer_score,mst_dealer,mst_dealer_outlet,(SELECT @curRank := 1, @_sequence:=1, @_last_score:=0) r WHERE mys_txn_dealer_score.brand_id='"+bid+"' and year='"+year+"' and month<="+month+" and mys_txn_dealer_score.dealer_id=mst_dealer.sk_dealer_id and mst_dealer_outlet.dealer_id=mst_dealer.sk_dealer_id and (mst_dealer_outlet.region_id='"+region+"' or '"+region+"'='all')  ORDER BY  monthly_score_percentage DESC)res1 left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as PE,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='2' and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mys_score.region_id='"+region+"' or '"+region+"'='all') GROUP by mys_score.dealer_id)as res2 on res1.dealer_id=res2.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as CT,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='3' and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mys_score.region_id='"+region+"' or '"+region+"'='all') GROUP by mys_score.dealer_id)as res3 on res1.dealer_id=res3.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as OSC,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='1' and mys_score.`year`='"+year+"'  and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mys_score.region_id='"+region+"' or '"+region+"'='all') GROUP by mys_score.dealer_id)as res4 on res1.dealer_id=res4.dealer_id  GROUP by res1.dealer_id";
		} else {
			sql= "SELECT * from (SELECT movement,dealer_id,monthly_score_percentage,mys_txn_dealer_score.year,mys_txn_dealer_score.month,mys_txn_dealer_score.ytd_score_percentage,mst_dealer.dealer_name, IF(monthly_score_percentage=@_last_score,@curRank:=@curRank,@curRank:=@_sequence) AS rank, @_sequence:=@_sequence+1 as sequence,@_last_score:=monthly_score_percentage AS last_score FROM mys_txn_dealer_score,mst_dealer,(SELECT @curRank := 1, @_sequence:=1, @_last_score:=0) r WHERE mys_txn_dealer_score.brand_id='"+bid+"' and year='"+year+"' and month<="+month+" and mys_txn_dealer_score.dealer_id=mst_dealer.sk_dealer_id and (mst_dealer.region_id='"+dealers+"' or '"+dealers+"'='all')  ORDER BY  monthly_score_percentage DESC)res1 left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as PE,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='2' and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mst_shopper_details.dealer_id='"+dealers+"' or '"+dealers+"'='all') GROUP by mys_score.dealer_id)as res2 on res1.dealer_id=res2.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as CT,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='3' and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mst_shopper_details.dealer_id='"+dealers+"' or '"+dealers+"'='all') GROUP by mys_score.dealer_id)as res3 on res1.dealer_id=res3.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as OSC,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='1' and mys_score.`year`='"+year+"'  and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mst_shopper_details.dealer_id='"+dealers+"' or '"+dealers+"'='all') GROUP by mys_score.dealer_id)as res4 on res1.dealer_id=res4.dealer_id  GROUP by res1.dealer_id";
		}

		
		
	

		//int year=2019;
		
		//currentmonth="September";
		//month=9;
		//String sql = " SELECT * from (SELECT movement,dealer_id,monthly_score_percentage,mys_txn_dealer_score.year,mys_txn_dealer_score.month,mys_txn_dealer_score.ytd_score_percentage,mst_dealer.dealer_name, IF(monthly_score_percentage=@_last_score,@curRank:=@curRank,@curRank:=@_sequence) AS rank, @_sequence:=@_sequence+1 as sequence,@_last_score:=monthly_score_percentage AS last_score FROM mys_txn_dealer_score,mst_dealer,(SELECT @curRank := 1, @_sequence:=1, @_last_score:=0) r WHERE mys_txn_dealer_score.brand_id='"+bid+"' and year='"+year+"' and month='"+month+"' and mys_txn_dealer_score.dealer_id=mst_dealer.sk_dealer_id ORDER BY  monthly_score_percentage DESC)res1 left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as PE,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='2' and monthname(mst_shopper_details.visit_date)='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' GROUP by mys_score.dealer_id)as res2 on res1.dealer_id=res2.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as CT,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='3' and monthname(mst_shopper_details.visit_date)='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' GROUP by mys_score.dealer_id)as res3 on res1.dealer_id=res3.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as OSC,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='1' and mys_score.`year`='"+year+"'  and monthname(mst_shopper_details.visit_date)='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' GROUP by mys_score.dealer_id)as res4 on res1.dealer_id=res4.dealer_id  GROUP by res1.dealer_id";
		//String sql="SELECT * from (SELECT movement,dealer_id,monthly_score_percentage,mys_txn_dealer_score.year,mys_txn_dealer_score.month,mys_txn_dealer_score.ytd_score_percentage,mst_dealer.dealer_name, IF(monthly_score_percentage=@_last_score,@curRank:=@curRank,@curRank:=@_sequence) AS rank, @_sequence:=@_sequence+1 as sequence,@_last_score:=monthly_score_percentage AS last_score FROM mys_txn_dealer_score,mst_dealer,(SELECT @curRank := 1, @_sequence:=1, @_last_score:=0) r WHERE mys_txn_dealer_score.brand_id='"+bid+"' and year='"+year+"' and month<="+month+" and mys_txn_dealer_score.dealer_id=mst_dealer.sk_dealer_id and (mst_dealer.sk_dealer_id='"+dealers+"' or '"+dealers+"'='all')  ORDER BY  monthly_score_percentage DESC)res1 left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as PE,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='2' and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mst_shopper_details.dealer_id='"+dealers+"' or '"+dealers+"'='all') GROUP by mys_score.dealer_id)as res2 on res1.dealer_id=res2.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as CT,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='0' and mys_score.`year`='"+year+"' and `section_id`='3' and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mst_shopper_details.dealer_id='"+dealers+"' or '"+dealers+"'='all') GROUP by mys_score.dealer_id)as res3 on res1.dealer_id=res3.dealer_id left  join (SELECT round(((sum(`scored_points`)/sum(`max_points`))*100),2) as OSC,mys_score.dealer_id FROM `mys_score`,mst_shopper_details  WHERE mst_shopper_details.brand_id='"+bid+"' and `osc_flag`='1' and mys_score.`year`='"+year+"'  and monthname(mst_shopper_details.visit_date)<='"+currentmonth+"' and mst_shopper_details.sk_shopper_id=mys_score.shopper_id and mst_shopper_details.visit_status='published' and (mst_shopper_details.dealer_id='"+dealers+"' or '"+dealers+"'='all') GROUP by mys_score.dealer_id)as res4 on res1.dealer_id=res4.dealer_id  GROUP by res1.dealer_id";
		System.out.println(sql);
		return template.query(sql, new RowMapper<DashboardBean>() {
			public DashboardBean mapRow(ResultSet rs, int row) throws SQLException {
				DashboardBean dBean = new DashboardBean();
				
				try {
					//String currentmonth="September";
					dBean=getYtdpercentage(dBean,rs.getString("dealer_id"),currentmonth,bid,year);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dBean.setYtd_dealer_avg1(dBean.getYtd_dealer_avg1());
				
				dBean.setMovement(dBean.getMovement());
				
				dBean.setPE_avg(rs.getString("PE"));
				dBean.setCT_avg(rs.getString("CT"));
				dBean.setOsc_avg(rs.getString("OSC"));
				if(rs.getString("OSC")==null)
				{
					dBean.setOsc_avg("0.0");
				}
				dBean.setDealer_rank(rs.getString("rank").replace(".0", ""));
				dBean.setDealer_name(rs.getString("dealer_name"));
				
				System.out.println(rs.getString("dealer_name"));
				
				
				return dBean;
			}
		});
	}

	protected DashboardBean getYtdpercentage(DashboardBean dBean, String dealer_id, String currentmonth, String bid,int year) throws ParseException {
		Date date = new SimpleDateFormat("MMMM").parse(currentmonth);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month=cal.get(Calendar.MONTH)+1;

		int premonth=month-1;
		String sql = "select ifnull(curr_ytd_score_percentage,0)as cytd_score_percentage ,ifnull(preytd_score_percentage,0)as pytd_score_percentage ,(ifnull(curr_ytd_score_percentage,0)-ifnull(preytd_score_percentage,0))as dmovement from (SELECT round((((sum(`scored_point_721`))/(sum(`maximum_point_721`)))*100),2)as curr_ytd_score_percentage FROM `mys_txn_dealer_score` WHERE mys_txn_dealer_score.brand_id='"+bid+"' and year='"+year+"' and month<='"+month+"' and `dealer_id`='"+dealer_id+"')res1 join (SELECT round((((sum(`scored_point_721`))/(sum(`maximum_point_721`)))*100),2)as preytd_score_percentage FROM `mys_txn_dealer_score` WHERE mys_txn_dealer_score.brand_id='"+bid+"' and year='"+year+"' and month<='"+premonth+"' and `dealer_id`='"+dealer_id+"')res2";
		//String sql = "select ifnull(curr_ytd_score_percentage,0)as cytd_score_percentage ,ifnull(preytd_score_percentage,0)as pytd_score_percentage ,(ifnull(curr_ytd_score_percentage,0)-ifnull(preytd_score_percentage,0))as dmovement from (SELECT round((((sum(`scores_earned`)+sum(scored_point_721))/(sum(`maximum_scores`)+sum(maximum_point_721)))*100),2)as curr_ytd_score_percentage FROM `mys_txn_dealer_score` WHERE mys_txn_dealer_score.brand_id='"+bid+"' and year='"+year+"' and month<="+month+" and `dealer_id`='"+dealer_id+"')res1 join (SELECT round((((sum(`scores_earned`)+sum(scored_point_721))/(sum(`maximum_scores`)+sum(maximum_point_721)))*100),2)as preytd_score_percentage FROM `mys_txn_dealer_score` WHERE mys_txn_dealer_score.brand_id='"+bid+"' and year='"+year+"' and month<="+premonth+" and `dealer_id`='"+dealer_id+"')res2";
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<DashboardBean>() {
			public DashboardBean mapRow(ResultSet rs, int row) throws SQLException {
				dBean.setYtd_dealer_avg1(rs.getDouble("cytd_score_percentage"));
				System.out.println(rs.getString("cytd_score_percentage"));
				dBean.setMovement(rs.getString("dmovement"));
				return dBean;
			}
		});
	}

	public DashboardBean getbrandName(DashboardBean dBean, String brand_id) {
		String sql = "SELECT * FROM `mst_brand` WHERE `sk_brand_id`='"+brand_id+"' and `brand_status`='active'";

		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<DashboardBean>() {
			public DashboardBean mapRow(ResultSet rs, int row) throws SQLException {
				dBean.setBrand_name(rs.getString("brand_name"));
				return dBean;
			}
		});
	}

	public MenuBean getroleMenuById(final MenuBean mBean, String roleId) {
		System.out.println("SELECT * FROM mst_users_roles WHERE sk_role_id='" + roleId + "';");
		return template.queryForObject("SELECT * FROM mst_users_roles WHERE sk_role_id='" + roleId + "';",
				new RowMapper<MenuBean>() {
					public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
						mBean.setMenu(rs.getString("menu_list"));
						return mBean;
					}
				});
	}
	
	
	public List<MenuBean> getMainMenu(MenuBean mBean) {
		System.out.println("main menu step1");
		System.out.println("SELECT * FROM mst_settings_menu WHERE menu_id IN (0);");
		return template.query("SELECT * FROM mst_settings_menu WHERE menu_id IN (0);", new RowMapper<MenuBean>() {
			public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
				MenuBean mBean = new MenuBean();
				mBean.setMenu_name(rs.getString("menu_name"));
				mBean.setMenu_id(rs.getString("sk_menu_id"));
				mBean.setMenu_link(rs.getString("menu_link"));
				mBean.setMenu_icon(rs.getString("menu_icon"));
				return mBean;
			}
		});
	}
	
	
	public List<MenuBean> getMainMenu(MenuBean mBean, final String menu_list) {
		System.out.println("main menu menulist step2");
		System.out
				.println("SELECT * FROM mst_settings_menu WHERE sk_menu_id IN (" + menu_list + ") AND menu_level='1';");
		return template.query(
				"SELECT * FROM mst_settings_menu WHERE sk_menu_id IN (" + menu_list + ") AND menu_level='1';",
				new RowMapper<MenuBean>() {
					public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
						MenuBean mBean = new MenuBean();
						mBean.setMenu_name(rs.getString("menu_name"));
						mBean.setMenu_id(rs.getString("sk_menu_id"));
						mBean.setMenu_link(rs.getString("menu_link"));
						mBean.setMenu_icon(rs.getString("menu_icon"));
						int menu_level = Integer.parseInt(rs.getString("menu_level")) + 1;
						mBean.setMenu_type(String.valueOf(menu_level));
						List<MenuBean> getsubMenu = getSubMenu(mBean, menu_list);
						mBean.setSubMenu(getsubMenu);
						return mBean;
					}
				});
	}
	
	public List<MenuBean> getSubMenu(MenuBean mBean, final String menu_list) {
		System.out.println("sub menu step3");
		System.out.println("SELECT * FROM mst_settings_menu WHERE menu_id='" + mBean.getMenu_id() + "' and menu_level='"
				+ mBean.getMenu_type() + "' and sk_menu_id IN (" + menu_list + ");");
		return template.query("SELECT * FROM mst_settings_menu WHERE menu_id='" + mBean.getMenu_id()
				+ "' and menu_level='" + mBean.getMenu_type() + "' and sk_menu_id IN (" + menu_list + ");",
				new RowMapper<MenuBean>() {
					public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
						MenuBean mBean = new MenuBean();
						mBean.setMenu_name(rs.getString("menu_name"));
						mBean.setMenu_id(rs.getString("sk_menu_id"));
						mBean.setMenu_link(rs.getString("menu_link"));
						mBean.setMenu_icon(rs.getString("menu_icon"));
						int menu_level = Integer.parseInt(rs.getString("menu_level")) + 1;
						mBean.setMenu_type(String.valueOf(menu_level));
						List<MenuBean> getsubMenu = getSubMenu(mBean, menu_list);
						mBean.setSubMenu(getsubMenu);
						return mBean;
					}
				});
	}
	
	
	
	public List<MenuBean> getSubMenuById(MenuBean mBean, String menuid, String menu_list) {
         System.out.println("sub menu by id  step 4");
		String sql = "";
		if (menu_list == "") {
			sql = "SELECT * FROM mst_settings_menu WHERE menu_id IN(" + menuid + ");";
		} else {

			sql = "SELECT * FROM mst_settings_menu WHERE menu_id=" + menuid + " and sk_menu_id IN(" + menu_list + ");";
		}
		System.out.println(sql);
		return template.query(sql, new RowMapper<MenuBean>() {
			public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
				MenuBean mBean = new MenuBean();
				mBean.setMenu_name(rs.getString("menu_name"));
				mBean.setMenu_id(rs.getString("sk_menu_id"));
				mBean.setMenu_link(rs.getString("menu_link"));
				return mBean;
			}
		});
	}

	public DashboardBean getUserType(DashboardBean dbBean) {
		System.out.println("SELECT * FROM mst_user_type where sk_user_id='" + dbBean.getUser_type_id() + "';");
		return template.queryForObject(
				"SELECT * FROM mst_user_type where sk_user_id='" + dbBean.getUser_type_id() + "';",
				new RowMapper<DashboardBean>() {
					public DashboardBean mapRow(ResultSet rs, int row) throws SQLException {
						dbBean.setUser_type(rs.getString("user_type"));
						dbBean.setUser_type_id(rs.getString("sk_user_id"));
						return dbBean;
					}
				});
	}

	public DashboardBean getMenuByRoleid(DashboardBean dbBean, String menu_list, String getMethod) {
		System.out.println("SELECT count(menu_link) as count FROM `mst_settings_menu` WHERE sk_menu_id in (" + menu_list
				+ ") and menu_link='" + getMethod + "';");
		return template.queryForObject("SELECT count(menu_link) as count FROM `mst_settings_menu` WHERE sk_menu_id in ("
				+ menu_list + ") and menu_link='" + getMethod + "';", new RowMapper<DashboardBean>() {
					public DashboardBean mapRow(ResultSet rs, int row) throws SQLException {
						dbBean.setCount(rs.getString("count"));
						return dbBean;
					}
				});
	}
}
