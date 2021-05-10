package com.mystery.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mystery.api.Encryption;
import com.mystery.beans.DashboardBean;
import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.DealerBean;
import com.mystery.beans.GraphBean;
import com.mystery.beans.MenuBean;
import com.mystery.beans.MysteryShoppingVisitsBean;
import com.mystery.beans.QuestionnaireBean;
import com.mystery.beans.RegionBean;
import com.mystery.beans.ReportsBean;
import com.mystery.dao.DashboardDao;
import com.mystery.dao.DatabaseManagementDao;
import com.mystery.dao.MysteryShoppingVisitsDao;
import com.mystery.dao.QuestionnaireDao;
import com.mystery.dao.ReportsDao;

@Controller
public class ReportsController {
	@Autowired
	ReportsDao rDao;
	
	@Autowired
	DatabaseManagementDao dbDao;
	
	@Autowired
	MysteryShoppingVisitsDao mvDao;
	
	@Autowired
	QuestionnaireDao qDao;
	
	@Autowired
	DashboardDao dDao;
	
	
	/******Graphs*******/
	
	@RequestMapping("OverallPerformance")
	public ModelAndView OverallPerformance(HttpServletRequest request, HttpServletResponse response,GraphBean gBean,RegionBean rgBean,QuestionnaireBean qBean ){
	ModelAndView mv=new ModelAndView("OverallPerformance");
	System.out.println("jsdkjsdfjsd="+gBean.getMonth());

	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);


	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	
	gBean.setMonth(gBean.getMonth());
	gBean.setBrand_id(gBean.getBrand_id());
	System.out.println("brand "+gBean.getBrand_id());
	gBean.setRegion_id(gBean.getRegion_id());;
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("brand_id", gBean.getBrand_id());
	request.setAttribute("month", gBean.getMonth());
	request.setAttribute("dealer_id", gBean.getDealer_id());
	request.setAttribute("outlet_id", gBean.getOutlet_id());
	request.setAttribute("region_id", gBean.getRegion_id());
	request.setAttribute("role_id", gBean.getRole_id());
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonths(gBean);
	mv.addObject("getMonths", getMonths);
	  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList); 
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid); 
	 
	  System.out.println("dealer id=="+gBean.getDealer_id());
	  System.out.println("brand id=="+gBean.getBrand_id());
	  System.out.println("region id=="+gBean.getRegion_id());
	  System.out.println("outlet id=="+gBean.getOutlet_id());
	  System.out.println("month=="+gBean.getMonth());
	  String did = gBean.getDealer_id();
		String bid = gBean.getBrand_id();
		String rid = gBean.getRegion_id();
		String oid = gBean.getOutlet_id();
		String mid = gBean.getMonth();
		
		gBean.setDealer_id(gBean.getDealer_id());
		gBean.setRegion_id(gBean.getRegion_id());
		gBean.setOutlet_id(gBean.getOutlet_id());
		gBean.setMonth(gBean.getMonth());
		gBean.setBrand_id(gBean.getBrand_id());
		//GraphBean getoutlets = rDao.getOutletsoverallperFilterForYtdMtd(gBean);
		//QuestionnaireBean getoutlets = qDao.getOutletsoverallperFilter1(qBean,did,bid,rid);
		if(roleId.contentEquals("7")) {
		GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtdForRole(gBean);
		gBean.setOutlet_id(outlets.getOutlet_id());
		}
		else {
			GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtd(gBean);
			gBean.setOutlet_id(outlets.getOutlet_id());
		}
		//gBean.setOutlet_id(outlets.getOutlet_id());
		//System.out.println("outlet in overallperformance==="+outlets.getOutlet_id());
		String monthData = "";
		if (gBean.getMonth() == null || gBean.getMonth().equals("") || gBean.getMonth().equals("all")) {

		} else {
			monthData = " and month(visit_date)= '" + gBean.getMonth() + "'";
		}
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		int year = Calendar.getInstance().get(Calendar.YEAR);
		//int year=2019;
	    Date date = DateUtils.addDays(new Date(), -1);
		sd.setTimeZone(TimeZone.getTimeZone("IST"));
		List<GraphBean> getMonthsData = rDao.getMonthsForYtdMtd(gBean, monthData);
		// System.out.println(gson.toJson(getMonthsData)+":"+getMonthsData.size());
		List<GraphBean> ytdmtd = new ArrayList<GraphBean>();
		for (int i = 0; i < getMonthsData.size(); i++) {
			System.out.println(getMonthsData.get(i).getMonth());
			int month = Integer.parseInt(getMonthsData.get(i).getMonth());
			YearMonth yearMonthObject = YearMonth.of(year, month);
			int daysInMonth = yearMonthObject.lengthOfMonth();
			String StartData = "";
			String EndData = "";
			StartData = year + "-" + String.format("%02d", month) + "-01";
			EndData = year + "-" + String.format("%02d", month) + "-" + daysInMonth + "";
			if (sd.format(date).compareTo(EndData) < 0 && i == getMonthsData.size()) {
				EndData = sd.format(date);
			}
			System.out.println("start date for ytdmtd===="+StartData);
			System.out.println("start date for ytdmtd===="+EndData);
			ytdmtd.addAll(rDao.getytdmtd(qBean,gBean,oid, StartData, EndData, getMonthsData.get(i).getMonth_name(),did));
		}
		mv.addObject("ytdmtd", ytdmtd);
		
		
		String section = "2";
		List<GraphBean> getProcessPercentage = rDao.getOverallPerformanceSectionWiseScore(qBean,gBean, section, mid, oid, bid, did, rid);
		Gson gson = new Gson(); 
	    String jsonArray = gson.toJson(getProcessPercentage);
	    request.setAttribute("jsonArray", jsonArray);

	    section = "3";
		List<GraphBean> getCustomerPercentage = rDao.getOverallPerformanceSectionWiseScore(qBean,gBean, section, mid, oid, bid, did, rid);
		 String jsonArray1 = gson.toJson(getCustomerPercentage);
		 request.setAttribute("jsonArray1", jsonArray1);
		 System.out.println("Customer percentage ====="+getCustomerPercentage);

		 section = "1";
		List<GraphBean> getOnlineSalesChannel = rDao.getOverallPerformanceSectionWiseScoreData(qBean,gBean, section, mid, oid, bid, did, rid);
		mv.addObject("getProcessPercentage", getProcessPercentage);
		mv.addObject("getCustomerPercentage", getCustomerPercentage);
		mv.addObject("getOnlineSalesChannel", getOnlineSalesChannel);
		String jsonArray2 = gson.toJson(getOnlineSalesChannel);
		 request.setAttribute("jsonArray2", jsonArray2);
		 
		 
			 //QuestionnaireBean getshopperIds = qDao.getshoppersByOutletMode(qBean,gBean,mid);
				//qBean.setShopperIds(getshopperIds.getShopperIds());
				DecimalFormat f = new DecimalFormat("###.##");
		 /***success factors**/
				// 1. Personal Response within 2 working days
				rDao.getOverallperformanceFormula1(qBean, "1.3", "Not at all",bid,oid,did,rid,mid);
				request.setAttribute("personal_avg", f.format(Double.parseDouble(qBean.getAvg())));
				
				// 2. Vehicle Presentation
				rDao.getOverallperformanceFormula3(qBean, "2.4",bid,oid,did,rid,mid);
				request.setAttribute("vehicle_presentation", f.format(Double.parseDouble(qBean.getAvg())));
				
				// 3. Test Drive Offer
				rDao.getOverallperformanceFormula2(qBean, "3.1", "Yes, actively offered",bid,oid,did,rid,mid);
				request.setAttribute("Test_Drive_Offer", f.format(Double.parseDouble(qBean.getAvg())));
				
				// 4. New Car Offer Given
				rDao.getOverallperformanceFormula2(qBean, "4.3",
						"Yes, on official paper sent per e-mail during/right after the consultation",bid,oid,did,rid,mid);
				request.setAttribute("car_offer", f.format(Double.parseDouble(qBean.getAvg())));
				
				// 5. Follow-up on Offer
				rDao.getOverallperformanceFormula4(qBean, "5.2", "yes",bid,oid,did,rid,mid);
				request.setAttribute("offer_followup", f.format(Double.parseDouble(qBean.getAvg())));
				
				// 6. Consider Buying a Vehicle
				System.out.println("hello here we are");
				rDao.getOverallperformanceFormula4(qBean, "6.2", "Yes",bid,oid,did,rid,mid);
				request.setAttribute("Buy_vehicle", f.format(Double.parseDouble(qBean.getAvg())));
				
				/********success factors*********/
				
				/****
				 * Working Standards
				 */
				// 1. Auto Response
				rDao.getOverallperformanceFormula2(qBean, "1.2", "Yes",bid,oid,did,rid,mid);
				request.setAttribute("auto_response", f.format(Double.parseDouble(qBean.getAvg())));

				// 2. Personal Response within 4 hrs
				rDao.getOverallperformanceFormula2(qBean, "1.3", "Within 4 hours after sending the request",bid,oid,did,rid,mid);
				request.setAttribute("response_4_hours", f.format(Double.parseDouble(qBean.getAvg())));

				// 3. Quality of Response: Standards
				rDao.getOverallperformanceFormula4(qBean, "1.5", "Yes",bid,oid,did,rid,mid);
				request.setAttribute("quality_of_response", f.format(Double.parseDouble(qBean.getAvg())));

				// 4. Attempt to make an Appointment
				rDao.getOverallperformanceFormula2(qBean, "1.6", "Yes",bid,oid,did,rid,mid);
				request.setAttribute("appointment", f.format(Double.parseDouble(qBean.getAvg())));

				// 5. Retail Lead Documentation
				rDao.getOverallperformanceFormula4(qBean, "4.5.1", "Yes",bid,oid,did,rid,mid);
				request.setAttribute("retail_lead", f.format(Double.parseDouble(qBean.getAvg())));
				
				// 6. Test Drive Offer
				// same as success factors No.3

				// 7. New Car Offer
				// same as success factors No.4

				// 8. Financing Product Offer given
				rDao.getOverallperformanceFormula2(qBean, "4.2", "Yes",bid,oid,did,rid,mid);
				request.setAttribute("financial_offer", f.format(Double.parseDouble(qBean.getAvg())));

				// 9. Follow-up on Offer
				// same as success factors No.5
				/****
				 * Working Standards
				 */
				
				/****
				 * Retail Standards
				 */
				// 1. Contacted personally by the dealership
				// same as success factors No.1

				// 2. Actively welcomed and guided
				rDao.getOverallperformanceFormula3(qBean, "2.1",bid,oid,did,rid,mid);
				request.setAttribute("welcome_guided", f.format(Double.parseDouble(qBean.getAvg())));

				// 3. Systematic and customer oriented product presentation
				rDao.getOverallperformanceFormula3(qBean, "2.4",bid,oid,did,rid,mid);
				request.setAttribute("product_presentation", f.format(Double.parseDouble(qBean.getAvg())));

				// 4. Detailed product information upon request
				rDao.getOverallperformanceFormula2(qBean, "2.6", "Yes",bid,oid,did,rid,mid);
				request.setAttribute("product_information", f.format(Double.parseDouble(qBean.getAvg())));

				// 5. Test drive actively offered
				// same as success factors No.3

				// 6. Test drive procedure requirements fulfilled
				rDao.getOverallperformanceFormula4(qBean, "3.2",bid,oid,did,rid,mid);
				request.setAttribute("drive_requirements", f.format(Double.parseDouble(qBean.getAvg())));

				// 8. Contacted in the corresponding time frame
				// same as Working factors No.9

				// 7. Test drive vehicle condition
				rDao.getOverallperformanceFormula2(qBean, "3.2", "Yes",bid,oid,did,rid,mid);
				request.setAttribute("vehicle_condition", f.format(Double.parseDouble(qBean.getAvg())));
				/*
				 * Retail Standards
				 * 
				 */
				System.out.println("month for mode=============="+(request.getParameter("month")));
				if (request.getParameter("month") != null) {
					if (!request.getParameter("month").equals("")) {
						GraphBean getMode = rDao.getModeByMonth(gBean);
						if (!getMode.getCount().equals("0")) {
							request.setAttribute("mode", "yes");
						} else {
							request.setAttribute("mode", "no");
						}
					} else {
						request.setAttribute("mode", "yes");
					}
				} else {
					request.setAttribute("mode", "yes");
				}
		
	return mv;

	}
	@RequestMapping("CrmCompilance")
	public ModelAndView CrmCompilance(HttpServletRequest request, HttpServletResponse response,GraphBean gBean,ReportsBean rBean,RegionBean rgBean,QuestionnaireBean qBean){
	ModelAndView mv=new ModelAndView("CRM-compilance");
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);


	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("role_id", gBean.getRole_id());
	
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonths(gBean);
	mv.addObject("getMonths", getMonths);
	List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList);
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	  System.out.println("dealer id=="+gBean.getDealer_id());
	  System.out.println("brand id=="+gBean.getBrand_id());
	  System.out.println("region id=="+gBean.getRegion_id());
	  System.out.println("outlet id=="+gBean.getOutlet_id());
	  System.out.println("month=="+gBean.getMonth());
	  String did = gBean.getDealer_id();
		String bid = gBean.getBrand_id();
		String rid = gBean.getRegion_id();
		String oid = gBean.getOutlet_id();
		String mid = gBean.getMonth();
		// QuestionnaireBean getoutlets = qDao.getOutletsoverallperFilter1(qBean,did,bid,rid);
			//qBean.setOutlets(getoutlets.getOutlets());
		if(roleId.contentEquals("7")) {
			GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtdForRole(gBean);
			gBean.setOutlet_id(outlets.getOutlet_id());
			}
			else {
				GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtd(gBean);
				gBean.setOutlet_id(outlets.getOutlet_id());
			}
		//gBean.setOutlet_id(outlets.getOutlet_id());
			 QuestionnaireBean getshopperIds = qDao.getshoppersByOutletMode(qBean,gBean,mid);
				qBean.setShopperIds(getshopperIds.getShopperIds());
				QuestionnaireBean data = qDao.crm(qBean,bid,oid,did,rid,mid);
				String nodelay = data.getNodelay();
				String upto = data.getUpto2days();
				String more = data.getMore2days();
				String name = data.getName();
				System.out.println(more);
				String email = data.getEmail();
				String total = data.getTotalquestions();
				String count = data.getCount();
				String norecords = data.getNorecords();
				String accurate = data.getAccurate();
				DecimalFormat f = new DecimalFormat(".##");
				String delaycompliance = f
						.format(((Double.parseDouble(upto) + Double.parseDouble(more)) / Double.parseDouble(count)) * 100);
				String nodelay_per = f.format((Double.parseDouble(nodelay) / Double.parseDouble(total)) * 100);
				String upto_percentage = f.format((Double.parseDouble(upto) / Double.parseDouble(total)) * 100);
				System.out.println("upto===="+upto_percentage);
				String more_percentage = f.format((Double.parseDouble(more) / Double.parseDouble(total)) * 100);
				System.out.println(Double.parseDouble(more)+"cthgjvkbnrxfchgvjb"+Double.parseDouble(total));
				String email_percentage = f.format((Double.parseDouble(email) / Double.parseDouble(count)) * 100);
				String name_percentage = f.format((Double.parseDouble(name) / Double.parseDouble(count)) * 100);
				String noncom_percentage = f.format((Double.parseDouble(norecords) / Double.parseDouble(count)) * 100);
				String timely_percentage = f.format((Double.parseDouble(accurate) / Double.parseDouble(count)) * 100);
				DecimalFormat f1 = new DecimalFormat("#");
				if (noncom_percentage.equals(".0")) {
					noncom_percentage = f1.format(0);
				}
				if (delaycompliance.equals(".0")) {
					delaycompliance = f1.format(0);
				}
				if (name_percentage.equals(".0")) {
					name_percentage = f1.format(0);
				}
				if (email_percentage.equals(".0")) {
					email_percentage = f1.format(0);
				}
				if (nodelay.equals(".0")) {
					nodelay = f1.format(0);
				}
				if (timely_percentage.equals(".0")) {
					timely_percentage = f1.format(0);
				}

				request.setAttribute("nodelay", nodelay_per);
				request.setAttribute("delaycompliance", delaycompliance);
				request.setAttribute("upto_percentage", upto_percentage);
				request.setAttribute("more_percentage", more_percentage);
				request.setAttribute("email_percentage", email_percentage);
				request.setAttribute("name_percentage", name_percentage);
				request.setAttribute("noncom_percentage", noncom_percentage);
				request.setAttribute("timely_percentage", timely_percentage);
				List<QuestionnaireBean> list = qDao.getcrmbargraph(qBean,bid,oid,did,rid,mid);
				System.out.println(list.size());
				// System.out.println(list.iterator().next().getMonth());
				String yes = "";
				String no = "";
				String month = "";
				for (int i = 0; i < list.size(); i++) {
					month = month + "," + list.get(i).getMonth();
					yes = yes + "," + list.get(i).getYes();
					no = no + "," + list.get(i).getNo();
				}
				month = month.startsWith(",") ? month.substring(1) : month;

				// month="'"+month.replaceAll(",", "', '")+"'";
				yes = yes.startsWith(",") ? yes.substring(1) : yes;
				no = no.startsWith(",") ? no.substring(1) : no;
				System.out.println(month);
				request.setAttribute("yes", yes);
				request.setAttribute("no", no);
				request.setAttribute("months", month);
				
				request.setAttribute("brand_id", gBean.getBrand_id());
				request.setAttribute("month", gBean.getMonth());
				request.setAttribute("dealer_id", gBean.getDealer_id());
				request.setAttribute("outlet_id", gBean.getOutlet_id());
				request.setAttribute("region_id", gBean.getRegion_id());
	return mv;
	}
	@RequestMapping("conquestContactdynamic")
	public ModelAndView conquestContactdynamic(HttpServletRequest request,GraphBean gBean,RegionBean rgBean){
	ModelAndView mv=new ModelAndView();
	try {
		  mv=new ModelAndView("ConquestAndLoyaltyContact");
	System.out.println("jsdkjsdfjsd="+gBean.getMonth());
	gBean.setMonth(gBean.getMonth());
	gBean.setBrand_id(gBean.getBrand_id());
	System.out.println("get brand"+gBean.getBrand_id());
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);
	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	
	request.setAttribute("brand_id", gBean.getBrand_id());
	request.setAttribute("month", gBean.getMonth());
	request.setAttribute("dealer_id", gBean.getDealer_id());
	request.setAttribute("outlet_id", gBean.getOutlet_id());
	request.setAttribute("region_id", gBean.getRegion_id());
	request.setAttribute("year", gBean.getYear());
	request.setAttribute("role_id", gBean.getRole_id());
	

	gBean.setMode_of_contact("Email/Website");
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonthsByMode(gBean);
	mv.addObject("getMonths", getMonths);
	  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList); 
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid); 
	  List<GraphBean> autoresponseemailgraph =rDao.getautoresponseemailgraph(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("autoresponseemailgraph", autoresponseemailgraph); 
	 // String year=autoresponseemailgraph.get(0).getYear();
	  //request.setAttribute("selected_year", year);
	  if(autoresponseemailgraph.isEmpty()) { 
		   int year1 = Calendar.getInstance().get(Calendar.YEAR);
		   String year=String.valueOf(year1);
		   request.setAttribute("selected_year", year);
	   }
	   else {
		   String year=autoresponseemailgraph.get(0).getYear();
		   request.setAttribute("selected_year", year);
	   }
	  Gson gson = new Gson(); 
	  String autoresponseemailgraphjson = gson.toJson(autoresponseemailgraph);
	  request.setAttribute("autoresponseemailgraphjson", autoresponseemailgraphjson);
	  
	  List<GraphBean> dealershipgraph =rDao.getdealershipgraphresponse(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("dealershipgraph", dealershipgraph); 
	  String dealershipgraphresponse = gson.toJson(dealershipgraph);
	  request.setAttribute("dealershipgraphresponse", dealershipgraphresponse);
	  
	  List<GraphBean> appointmentchart =rDao.getappointmentchart(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("appointmentchart", appointmentchart); 
	  String appointmentchartjson = gson.toJson(appointmentchart);
	  request.setAttribute("appointmentchartjson", appointmentchartjson);
	
	  List<GraphBean> meetthestandards =rDao.getmeetthestandards(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("meetthestandards", meetthestandards); 
	  String meetthestandardsjson = gson.toJson(meetthestandards);
	  request.setAttribute("meetthestandardsjson", meetthestandardsjson);
	  
	}catch (Exception e) {
		  mv=new ModelAndView("redirect:/");
	}
	  
	return mv;
	}
	
	@RequestMapping(value = "/getmonths")
	public @ResponseBody Object getmonths(HttpServletRequest request, HttpServletResponse response,
			GraphBean gBean) {
		gBean.setMode_of_contact("Email/Website");
		String bid = request.getParameter("bid");
		List<GraphBean> getMonths = rDao.getMonthsByMode(gBean, bid);
		return getMonths;
	}
	@RequestMapping(value = "/getmonthsforOvper")
	public @ResponseBody Object getmonthsOvper(HttpServletRequest request, HttpServletResponse response,
			GraphBean gBean) {
		
		String bid = request.getParameter("bid");
		List<GraphBean> getMonths = rDao.getMonthsNames(gBean, bid);
		return getMonths;
	}
	
	@RequestMapping(value = "/getDealers")
	public @ResponseBody Object getDealers(HttpServletRequest request, HttpServletResponse response,
			GraphBean gBean) {
		String bid = request.getParameter("bid");
		String rid = request.getParameter("region_id");
		String did = request.getParameter("dealer_id");
		List<GraphBean> getDealers = rDao.getDealers1(gBean, bid,rid,did);
		 Gson gson = new Gson(); 
		System.out.println(gson.toJson(getDealers));
		return getDealers;
	}
	@RequestMapping(value = "/getDealersbyid")
	public @ResponseBody Object getDealersbyid(HttpServletRequest request, HttpServletResponse response,
			GraphBean gBean) {
		String rid = request.getParameter("region_id");
		List<GraphBean> getDealers = rDao.getDealersbyid(gBean,rid);
		return getDealers;
	}
	@RequestMapping(value = "/getDealersforOvper")
	public @ResponseBody Object getDealersforOvper(HttpServletRequest request, HttpServletResponse response,
			GraphBean gBean) {
		String bid = request.getParameter("bid");
		String rid = request.getParameter("region_id");
		List<GraphBean> getDealers = rDao.getDealersforOverp(gBean, bid,rid);
		return getDealers;
	}
	@RequestMapping(value = "/getoutlets")
	public @ResponseBody Object getoutlets(HttpServletRequest request, HttpServletResponse response,
			GraphBean gBean) {
		String did = request.getParameter("did");
		String bid = request.getParameter("bid");
		String rid = request.getParameter("region_id");
		List<GraphBean> getMonths = rDao.getoutlets(gBean, did,bid,rid);
		return getMonths;
	}
	@RequestMapping(value = "/getoutletsforOvper")
	public @ResponseBody Object getoutletsforOvper(HttpServletRequest request, HttpServletResponse response,
			GraphBean gBean) {
		String did = request.getParameter("did");
		String bid = request.getParameter("bid");
		List<GraphBean> getMonths = rDao.getoutletsforOverp(gBean, did,bid);
		return getMonths;
	}
	
	
	
	@RequestMapping("conquestContactTele")
	public ModelAndView conquestContactTele(ReportsBean rBean,GraphBean gBean,HttpServletRequest request,RegionBean rgBean){
		ModelAndView mv=new ModelAndView();
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);
	try {
		  mv=new ModelAndView("ConqAndLoyViaTele");
		

	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	
	gBean.setMonth(gBean.getMonth());
	gBean.setBrand_id(gBean.getBrand_id());
	gBean.setRegion_id(gBean.getRegion_id());;
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("brand_id", gBean.getBrand_id());
	request.setAttribute("month", gBean.getMonth());
	request.setAttribute("dealer_id", gBean.getDealer_id());
	request.setAttribute("outlet_id", gBean.getOutlet_id());
	request.setAttribute("region_id", gBean.getRegion_id());
	request.setAttribute("year", gBean.getYear());
	request.setAttribute("role_id", gBean.getRole_id());
    System.out.println("gbean year"+gBean.getYear());
    
	gBean.setMode_of_contact("Telephone");
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonthsByMode(gBean);
	mv.addObject("getMonths", getMonths);
	  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList); 
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	  
	  List<GraphBean> stepstotakegraph =rDao.getstepstotakegraph(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("stepstotakegraph", stepstotakegraph); 
	  Gson gson = new Gson(); 
	   if(stepstotakegraph.isEmpty()) { 
		   System.out.println("list is empty");
		   int year1 = Calendar.getInstance().get(Calendar.YEAR);
		   String year=String.valueOf(year1);
		   request.setAttribute("selected_year", year);
	   }
	   else {
		   String year=stepstotakegraph.get(0).getYear();
		   request.setAttribute("selected_year", year);
	   }
	  String stepstotakegraphjsonArray = gson.toJson(stepstotakegraph);
	  request.setAttribute("stepstotakegraphjsonArray", stepstotakegraphjsonArray);
	  
	  List<GraphBean> meetthestandardsviatelephone =rDao.getmeetthestandardtelephonesjson(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("meetthestandardsviatelephone", meetthestandardsviatelephone); 
	  String meetthestandardtelephonesjson = gson.toJson(meetthestandardsviatelephone);
	  request.setAttribute("meetthestandardtelephonesjson", meetthestandardtelephonesjson);
	  
	  List<GraphBean> anyoneatdealership =rDao.getanyoneatdealershipjson(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("anyoneatdealership", anyoneatdealership); 
	  String anyoneatdealershipjson = gson.toJson(anyoneatdealership);
	  request.setAttribute("anyoneatdealershipjson", anyoneatdealershipjson);
	}catch (Exception e) {
		  mv=new ModelAndView("redirect:/");
	}
	return mv;
	}
	@RequestMapping("conquestContactFocus")
	public ModelAndView conquestContactFocus(ReportsBean rBean,GraphBean gBean,HttpServletRequest request,RegionBean rgBean){
		ModelAndView mv=new ModelAndView();
		HttpSession session = request.getSession(true);
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		
		String role_for = (String) session.getAttribute("role_for");
		System.out.println("role_for==="+roleId);
	  
		
try {
	mv=new ModelAndView("ConqAndLoyFocusArea");
	
	gBean.setRole_id(roleId);


	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	
	gBean.setMonth(gBean.getMonth());
	gBean.setBrand_id(gBean.getBrand_id());
	gBean.setRegion_id(gBean.getRegion_id());;
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("brand_id", gBean.getBrand_id());
	request.setAttribute("month", gBean.getMonth());
	request.setAttribute("dealer_id", gBean.getDealer_id());
	request.setAttribute("outlet_id", gBean.getOutlet_id());
	request.setAttribute("region_id", gBean.getRegion_id());
	request.setAttribute("year", gBean.getYear());
	request.setAttribute("role_id", gBean.getRole_id());
   System.out.println("gbean year"+ gBean.getYear());
	//gBean.setMode_of_contact("Telephone");
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonthsforconquestcontactFocus(gBean);
	mv.addObject("getMonths", getMonths);
	  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList); 
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	  Gson gson = new Gson(); 
	  List<GraphBean> contactdetailsspeltcorrectly =rDao.getcontactdetailsspeltcorrectly(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("contactdetailsspeltcorrectly", contactdetailsspeltcorrectly); 
	  if(contactdetailsspeltcorrectly.isEmpty()) { 
		   System.out.println("list is empty");
		   int year1 = Calendar.getInstance().get(Calendar.YEAR);
		   String year=String.valueOf(year1);
		   request.setAttribute("selected_year", year);
	   }
	   else {
		   String year=contactdetailsspeltcorrectly.get(0).getYear();
		   request.setAttribute("selected_year", year);
	   }
	  String contactdetailsspeltcorrectlyjson = gson.toJson(contactdetailsspeltcorrectly);
	  request.setAttribute("contactdetailsspeltcorrectlyjson", contactdetailsspeltcorrectlyjson);
	  
	  
	  List<GraphBean> Financingorleasingoptions =rDao.getFinancingorleasingoptions(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("Financingorleasingoptions", Financingorleasingoptions); 
	  String Financingorleasingoptionsgson = gson.toJson(Financingorleasingoptions);
	  request.setAttribute("Financingorleasingoptionsgson", Financingorleasingoptionsgson);
	  
	  List<GraphBean> CourtesyGraph =rDao.getCourtesyGraphoptions(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("CourtesyGraph", CourtesyGraph); 
	  String CourtesyGraphgson = gson.toJson(CourtesyGraph);
	  request.setAttribute("CourtesyGraphgson", CourtesyGraphgson);
	  
	  List<GraphBean> testdriveactivelyoffered =rDao.gettestdriveactivelyoffered(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("testdriveactivelyoffered", testdriveactivelyoffered); 
	  String testdriveactivelyofferedgson = gson.toJson(testdriveactivelyoffered);
	  request.setAttribute("testdriveactivelyofferedgson", testdriveactivelyofferedgson);
	  
	  List<GraphBean> individualizedOffer =rDao.getindividualizedOffergson(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("individualizedOffer", individualizedOffer); 
	  String individualizedOffergson = gson.toJson(individualizedOffer);
	  request.setAttribute("individualizedOffergson", individualizedOffergson);
	  
	  List<GraphBean> correspondingtimeframe =rDao.getcorrespondingtimeframe(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("correspondingtimeframe", correspondingtimeframe); 
	  String correspondingtimeframegson = gson.toJson(correspondingtimeframe);
	  request.setAttribute("correspondingtimeframegson", correspondingtimeframegson);
		}catch (Exception e) {
			  mv=new ModelAndView("redirect:/");
		}
	  
	return mv;
		
	}
	
	
	@RequestMapping("DiscountAnalysis")
	public ModelAndView DiscountAnalysis(ReportsBean rBean,HttpServletRequest request, HttpServletResponse response,GraphBean gBean,RegionBean rgBean,QuestionnaireBean qBean ){
	ModelAndView mv=new ModelAndView();
//	HttpSession session = request.getSession(true);
//	String role_for = (String) session.getAttribute("role_for");
//	String dealers = (String) session.getAttribute("dealers");
//	String roleId = (String) session.getAttribute("role_id");
//	String region = (String) session.getAttribute("region");
//	if(roleId.contentEquals("2") || roleId.contentEquals("4") || roleId.contentEquals("5")) {
//		gBean.setRegion_id(gBean.getRegion_id());
//		gBean.setDealer_id(gBean.getDealer_id());
//		gBean.setOutlet_id(gBean.getOutlet_id());
//		
//	}else if(roleId.contentEquals("6") ){
//		gBean.setRegion_id(region);
//		gBean.setDealer_id(gBean.getDealer_id());
//		gBean.setOutlet_id(gBean.getOutlet_id());
//		
//	}else {
//		gBean.setDealer_id(dealers);
//		gBean.setOutlet_id(gBean.getOutlet_id());
//		gBean.setRegion_id(gBean.getRegion_id());
//		
//	}
//	rgBean.setRegion_id(gBean.getRegion_id());
	try {
		  mv=new ModelAndView("discountanalysis");
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);


	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("role_id", gBean.getRole_id());
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonths(gBean);
	mv.addObject("getMonths", getMonths);
	List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList);
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	  
	
	  System.out.println("dealer id=="+gBean.getDealer_id());
	  System.out.println("brand id=="+gBean.getBrand_id());
	  System.out.println("region id=="+gBean.getRegion_id());
	  System.out.println("outlet id=="+gBean.getOutlet_id());
	  System.out.println("month=="+gBean.getMonth());
	  String did = gBean.getDealer_id();
		String bid = gBean.getBrand_id();
		String rid = gBean.getRegion_id();
		String oid = gBean.getOutlet_id();
		String mid = gBean.getMonth();
		 //QuestionnaireBean getoutlets = qDao.getOutletsoverallperFilter1(qBean,did,bid,rid);
			//qBean.setOutlets(getoutlets.getOutlets());
			//GraphBean getoutlets = rDao.getOutletsoverallperFilterForYtdMtd(gBean);
				//gBean.setOutlet_id(getoutlets.getOutlet_id());
		if(roleId.contentEquals("7")) {
			GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtdForRole(gBean);
			gBean.setOutlet_id(outlets.getOutlet_id());
			}
			else {
				GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtd(gBean);
				gBean.setOutlet_id(outlets.getOutlet_id());
			}
			List<GraphBean> getDiscountPrices = rDao.getDiscountPrices(gBean,bid,mid,did,oid,rid);
			List<GraphBean> list = rDao.getdiscountbrands1(gBean,bid,mid,did,oid,rid);
			System.out.println("list data"+list);
			Gson gson = new Gson(); 
		    String data = gson.toJson(list);
		    System.out.println("list data manoj d discount analysis"+data);
		    request.setAttribute("data", list);
		    GraphBean piechart = rDao.getBranddiscountpiechart(gBean,mid);
		    request.setAttribute("yess", piechart.getYes());
			request.setAttribute("nooo", piechart.getNo());
			request.setAttribute("amount", piechart.getStatus());
			System.out.println("pie-chart amount===="+piechart.getStatus());
			System.out.println("pie-chart no===="+piechart.getNo());
			System.out.println("pie-chart yes===="+piechart.getYes());
			mv.addObject("getDiscountPrices", getDiscountPrices);
			request.setAttribute("brand_id", gBean.getBrand_id());
			request.setAttribute("month", gBean.getMonth());
			request.setAttribute("dealer_id", gBean.getDealer_id());
			request.setAttribute("outlet_id", gBean.getOutlet_id());
			request.setAttribute("region_id", gBean.getRegion_id());
	}catch (Exception e) {
		  mv=new ModelAndView("redirect:/");
	}
	return mv;
	}
	@RequestMapping("CompetitionOverview")
	public ModelAndView CompetitionOverview(ReportsBean rBean,GraphBean gBean,HttpServletRequest request,RegionBean rgBean){
	ModelAndView mv=new ModelAndView();
	try {
		  mv=new ModelAndView("competitionOverview");
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);

	String did = gBean.getDealer_id();
	String bid = gBean.getBrand_id();
	String rid = gBean.getRegion_id();
	String oid = gBean.getOutlet_id();
	System.out.println("dealer_id in competition"+did);
	System.out.println("dealers brand"+gBean.getDealer_id());
	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	
	gBean.setMonth(gBean.getMonth());
	gBean.setBrand_id(gBean.getBrand_id());
	gBean.setRegion_id(gBean.getRegion_id());;
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("brand_id", gBean.getBrand_id());
	request.setAttribute("month", gBean.getMonth());
	request.setAttribute("dealer_id", gBean.getDealer_id());
	request.setAttribute("outlet_id", gBean.getOutlet_id());
	request.setAttribute("region_id", gBean.getRegion_id());
	request.setAttribute("role_id", gBean.getRole_id());
    
	//gBean.setMode_of_contact("Telephone");
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonthsforconquestcontactFocus(gBean);
	mv.addObject("getMonths", getMonths);
	  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList); 
	  List<GraphBean> activedealersbyid = rDao.getDealersByRegionid(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	  
	  GraphBean outlets = rDao.getOutletsoverallperFilterCompetition(gBean,oid,rid,bid,did);

	  gBean.setOutlets(outlets.getOutlets());
	  List<GraphBean> getBrandData = rDao.getcompetionBrandScore(gBean);
		mv.addObject("getBrandData", getBrandData);
		
		List<GraphBean> getRegionData = rDao.getregionScore(gBean);
		mv.addObject("getRegionData", getRegionData);

		List<GraphBean> getShoppingScore = rDao.getShoppingScore(gBean);
		mv.addObject("getShoppingScore", getShoppingScore);
		Gson gson = new Gson();
		System.out.println(gson.toJson(getShoppingScore));
		request.setAttribute("dataarray", gson.toJson(getShoppingScore));
		
		request.setAttribute("dealer", gBean.getDealer_id());
		request.setAttribute("outlet", gBean.getOutlet_id());
		request.setAttribute("region", gBean.getRegion_id());

	}catch (Exception e) {
		  mv=new ModelAndView("redirect:/");
	}
	return mv;
	}
	@RequestMapping("lifeStyleAnalysis")
	public ModelAndView LifeStyleAnalysis(ReportsBean rBean,GraphBean gBean,HttpServletRequest request,RegionBean rgBean){
	ModelAndView mv=new ModelAndView();
	try {
		  mv=new ModelAndView("LifeStyleAnalysis");
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);


	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	
	request.setAttribute("brand_id", gBean.getBrand_id());
	request.setAttribute("month", gBean.getMonth());
	request.setAttribute("dealer_id", gBean.getDealer_id());
	request.setAttribute("outlet_id", gBean.getOutlet_id());
	request.setAttribute("region_id", gBean.getRegion_id());
	request.setAttribute("year", gBean.getYear());
	request.setAttribute("role_id", gBean.getRole_id());

	//gBean.setMode_of_contact("Telephone");
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonthsforconquestcontactFocus(gBean);
	mv.addObject("getMonths", getMonths);
	  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList); 
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	  Gson gson = new Gson(); 
	  //in3
	  List<GraphBean> configurationprocess =rDao.getconfigurationprocess(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("configurationprocess", configurationprocess); 
	  if(configurationprocess.isEmpty()) { 
		   int year1 = Calendar.getInstance().get(Calendar.YEAR);
		   String year=String.valueOf(year1);
		   request.setAttribute("selected_year", year);
	   }
	   else {
		   String year=configurationprocess.get(0).getYear();
		   request.setAttribute("selected_year", year);
	   }
	  String configurationprocessgson = gson.toJson(configurationprocess);
	  request.setAttribute("configurationprocessgson", configurationprocessgson);
	  
	  List<GraphBean> Whattakehomematerial =rDao.getWhattakehomematerial(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("Whattakehomematerial", Whattakehomematerial); 
	  String Whattakehomematerialgson = gson.toJson(Whattakehomematerial);
	  request.setAttribute("Whattakehomematerialgson", Whattakehomematerialgson);
	}catch (Exception e) {
		  mv=new ModelAndView("redirect:/");
	}
	return mv;
	}
	@RequestMapping("TrainingNeedAnalysis")
	public ModelAndView TrainingNeedAnalysis(ReportsBean rBean,HttpServletRequest request, HttpServletResponse response,GraphBean gBean,RegionBean rgBean,QuestionnaireBean qBean ){
	ModelAndView mv=new ModelAndView();
//	HttpSession session = request.getSession(true);
//	String role_for = (String) session.getAttribute("role_for");
//	String dealers = (String) session.getAttribute("dealers");
//	String roleId = (String) session.getAttribute("role_id");
//	String region = (String) session.getAttribute("region");
//	if(roleId.contentEquals("2") || roleId.contentEquals("4") || roleId.contentEquals("5")) {
//		gBean.setRegion_id(gBean.getRegion_id());
//		gBean.setDealer_id(gBean.getDealer_id());
//		gBean.setOutlet_id(gBean.getOutlet_id());
//		
//	}else if(roleId.contentEquals("6") ){
//		gBean.setRegion_id(region);
//		gBean.setDealer_id(gBean.getDealer_id());
//		gBean.setOutlet_id(gBean.getOutlet_id());
//		
//	}else {
//		gBean.setDealer_id(dealers);
//		gBean.setOutlet_id(gBean.getOutlet_id());
//		gBean.setRegion_id(gBean.getRegion_id());
//		
//	}
//	rgBean.setRegion_id(gBean.getRegion_id());
	try {
		  mv=new ModelAndView("trainingNeedAnalysis");
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);


	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("role_id", gBean.getRole_id());
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonths(gBean);
	mv.addObject("getMonths", getMonths);
	List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList);
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	
	  System.out.println("dealer id=="+gBean.getDealer_id());
	  System.out.println("brand id=="+gBean.getBrand_id());
	  System.out.println("region id=="+gBean.getRegion_id());
	  System.out.println("outlet id=="+gBean.getOutlet_id());
	  System.out.println("month=="+gBean.getMonth());
	  String did = gBean.getDealer_id();
		String bid = gBean.getBrand_id();
		String rid = gBean.getRegion_id();
		String oid = gBean.getOutlet_id();
		String mid = gBean.getMonth();
		
		// QuestionnaireBean getoutlets = qDao.getOutletsoverallperFilter1(qBean,did,bid,rid);
			//qBean.setOutlets(getoutlets.getOutlets());
		//GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtd(gBean);
		//gBean.setOutlet_id(outlets.getOutlet_id());
		if(roleId.contentEquals("7")) {
			GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtdForRole(gBean);
			gBean.setOutlet_id(outlets.getOutlet_id());
			}
			else {
				GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtd(gBean);
				gBean.setOutlet_id(outlets.getOutlet_id());
			}
			 QuestionnaireBean getshopperIds = qDao.getshoppersByOutlet(qBean,gBean,mid);
				qBean.setShopperIds(getshopperIds.getShopperIds());
				
				if (getshopperIds.getShopperIds() != null) {
					List<QuestionnaireBean> getdayCount = rDao.getdayCount(qBean, "5.3",bid,oid,did,rid,mid);
					int length = getdayCount.size();
					request.setAttribute("days", getdayCount.get(length - 1).getDate());
				} else {
					request.setAttribute("days", 0);
				}
				
		qBean.setStandard_number("2.2");
		List<QuestionnaireBean> needAnalysisScore = rDao.trainingneedanalysis(qBean,gBean,rid,did,oid,bid,mid);
		mv.addObject("needAnalysisScore", needAnalysisScore);
		
		qBean.setStandard_number("IN-2");
		List<QuestionnaireBean> featurePresentationScore = rDao.trainingneedanalysis(qBean,gBean,rid,did,oid,bid,mid);
		mv.addObject("featurePresentationScore", featurePresentationScore);
		
		qBean.setStandard_number("2.4");
		List<QuestionnaireBean> fivePointScore = rDao.trainingneedanalysis(qBean,gBean,rid,did,oid,bid,mid);
		mv.addObject("fivePointScore", fivePointScore);
		
		qBean.setStandard_number("IN-9");
		List<QuestionnaireBean> ratingstandard = rDao.trainingneedanalysis(qBean,gBean,rid,did,oid,bid,mid);
		mv.addObject("ratingstandard", ratingstandard);
		
		qBean.setStandard_number("2.4");
		QuestionnaireBean productpercentage = rDao.getProductAndDigitalPercentages(qBean,bid,oid,did,rid,mid);
		request.setAttribute("productpercentage", productpercentage.getOne());
		request.setAttribute("digital", productpercentage.getTwo());
		request.setAttribute("brand_id", gBean.getBrand_id());
		request.setAttribute("month", gBean.getMonth());
		request.setAttribute("dealer_id", gBean.getDealer_id());
		request.setAttribute("outlet_id", gBean.getOutlet_id());
		request.setAttribute("region_id", gBean.getRegion_id());
	}catch (Exception e) {
		  mv=new ModelAndView("redirect:/");
	}
	return mv;
	}
	@RequestMapping("financialServiceAnalysis")
	public ModelAndView FinancialServiceAnalysis(ReportsBean rBean,GraphBean gBean,HttpServletRequest request,RegionBean rgBean){
	ModelAndView mv=new ModelAndView();
	try {
		  mv=new ModelAndView("financialServiceAnalysis");
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);


	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	
	gBean.setMonth(gBean.getMonth());
	gBean.setBrand_id(gBean.getBrand_id());
	gBean.setRegion_id(gBean.getRegion_id());;
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("brand_id", gBean.getBrand_id());
	request.setAttribute("month", gBean.getMonth());
	request.setAttribute("dealer_id", gBean.getDealer_id());
	request.setAttribute("outlet_id", gBean.getOutlet_id());
	request.setAttribute("region_id", gBean.getRegion_id());
	request.setAttribute("year", gBean.getYear());	
	request.setAttribute("role_id", gBean.getRole_id());
	//gBean.setMode_of_contact("Telephone");
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonthsforconquestcontactFocus(gBean);
	mv.addObject("getMonths", getMonths);
	  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList); 
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	  Gson gson = new Gson(); 
	  
	  List<GraphBean> bmwfinancialservice =rDao.getbmwfinancialservice(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("bmwfinancialservice", bmwfinancialservice); 
	 // String year=bmwfinancialservice.get(0).getYear();
	  //request.setAttribute("selected_year", year);
	  if(bmwfinancialservice.isEmpty()) { 
		   System.out.println("list is empty");
		   int year1 = Calendar.getInstance().get(Calendar.YEAR);
		   String year=String.valueOf(year1);
		   request.setAttribute("selected_year", year);
	   }
	   else {
		   String year=bmwfinancialservice.get(0).getYear();
		   request.setAttribute("selected_year", year);
	   }
	  String bmwfinancialservicegson = gson.toJson(bmwfinancialservice);
	  request.setAttribute("bmwfinancialservicegson", bmwfinancialservicegson);
	  
	  List<GraphBean> buybackprogram =rDao.getbuybackprogram(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("buybackprogram", buybackprogram); 
	  String buybackprogramgson = gson.toJson(buybackprogram);
	  request.setAttribute("buybackprogramgson", buybackprogramgson);

	  List<GraphBean> offeredbreakdown =rDao.getofferedbreakdown(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("offeredbreakdown", offeredbreakdown); 
	  String offeredbreakdowngson = gson.toJson(offeredbreakdown);
	  request.setAttribute("offeredbreakdowngson", offeredbreakdowngson);
	  
	  List<GraphBean>handoverfinanceoffer =rDao.gethandoverfinanceoffer(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("handoverfinanceoffer", handoverfinanceoffer); 
	  String handoverfinanceoffergson = gson.toJson(handoverfinanceoffer);
	  request.setAttribute("handoverfinanceoffergson", handoverfinanceoffergson);
	  
	}catch (Exception e) {
		  mv=new ModelAndView("redirect:/");
	}

	return mv;
	}
	


	@RequestMapping("bpsAnalysis")
	public ModelAndView BpsAnalysis(ReportsBean rBean,GraphBean gBean,HttpServletRequest request,RegionBean rgBean){
	ModelAndView mv=new ModelAndView();
	try {
		  mv=new ModelAndView("bpsAnalysis");
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);


	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	
	gBean.setMonth(gBean.getMonth());
	gBean.setBrand_id(gBean.getBrand_id());
	gBean.setRegion_id(gBean.getRegion_id());;
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("brand_id", gBean.getBrand_id());
	request.setAttribute("month", gBean.getMonth());
	request.setAttribute("dealer_id", gBean.getDealer_id());
	request.setAttribute("outlet_id", gBean.getOutlet_id());
	request.setAttribute("region_id", gBean.getRegion_id());
	request.setAttribute("year", gBean.getYear());
	request.setAttribute("role_id", gBean.getRole_id());

	//gBean.setMode_of_contact("Telephone");
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonthsforconquestcontactFocus(gBean);
	mv.addObject("getMonths", getMonths);
	  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList); 
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	  Gson gson = new Gson(); 
	  
	  List<GraphBean> currentcustomervehicle =rDao.getcurrentcustomervehiclegson(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("currentcustomervehicle", currentcustomervehicle); 
	  if(currentcustomervehicle.isEmpty()) { 
		   int year1 = Calendar.getInstance().get(Calendar.YEAR);
		   String year=String.valueOf(year1);
		   request.setAttribute("selected_year", year);
	   }
	   else {
		   String year=currentcustomervehicle.get(0).getYear();
		   request.setAttribute("selected_year", year);
	   }
	  
	  String currentcustomervehiclegson = gson.toJson(currentcustomervehicle);
	  request.setAttribute("currentcustomervehiclegson", currentcustomervehiclegson);
	}catch (Exception e) {
		  mv=new ModelAndView("redirect:/");
	}
	return mv;
	}
	
	


	/******Graphs*******/
	
	/******Reports*******/
	@RequestMapping("Reports")
	  public ModelAndView Reports(ReportsBean rBean,RegionBean rgBean,DatabaseManagementBean dbBean,DealerBean dBean,MysteryShoppingVisitsBean mvBean){
	    
	  ModelAndView mv=new ModelAndView("reports_landing");
	  List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
	  mv.addObject("activebrandList", activebrandList);
	  List<MysteryShoppingVisitsBean> activeyearsList = rDao.getYearsList(mvBean);
	  mv.addObject("activeyearsList", activeyearsList);
	  List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
	  mv.addObject("activeDealerList",activeDealerList);
	  List<RegionBean> activeRegionList = dbDao.getRegionList1(rgBean);
	  mv.addObject("activeRegionList", activeRegionList); 
	  return mv;
	  }
	
	@RequestMapping(value = "/reportspost", method = RequestMethod.POST)
	public ModelAndView viewquestion( DatabaseManagementBean dbBean,DealerBean dBean,MysteryShoppingVisitsBean mvBean,RegionBean rBean,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv =null;
		if(mvBean.getReport_type().contentEquals("MSMResult")) {
			 mv= new ModelAndView("redirect:/msmresult/"+mvBean.getReport_type()+"/"+mvBean.getYear()+"/"+mvBean.getMonth()+"/"+mvBean.getRegion_id()+"/"+mvBean.getDealer_id()+"/"+mvBean.getOutlet_id()+"/"+mvBean.getBrand_id()+"/0");
		}else if (mvBean.getReport_type().equals("LowScoringOutlets")) {
			 mv= new ModelAndView("redirect:/LowScoringOutlet/"+mvBean.getReport_type()+"/"+mvBean.getYear()+"/"+mvBean.getMonth()+"/"+mvBean.getRegion_id()+"/"+mvBean.getDealer_id()+"/"+mvBean.getOutlet_id()+"/"+mvBean.getBrand_id()+"/0");
		}else if(mvBean.getReport_type().contentEquals("SummarySheet")) {
			 mv= new ModelAndView("redirect:/summary/"+mvBean.getReport_type()+"/"+mvBean.getYear()+"/"+mvBean.getMonth()+"/"+mvBean.getRegion_id()+"/"+mvBean.getDealer_id()+"/"+mvBean.getOutlet_id()+"/"+mvBean.getBrand_id()+"/0");
		}
		else if(mvBean.getReport_type().contentEquals("ManagementLevel")) {
			 mv= new ModelAndView("redirect:/allgraphs");
		}
		 
if (mvBean.getReport_type().equals("output_level_report")) {
			
	
	System.out.println("outletId iss"+dbBean.getOutlet_id());
	String encrypt_oid= Encryption.encrypt(dbBean.getOutlet_id());
	System.out.println("dealer Id iss"+dbBean.getDealer_id());
	String encrypt_did= Encryption.encrypt(dbBean.getDealer_id());
	System.out.println("Month iss"+dbBean.getMonth());
	String encrypt_mid= Encryption.encrypt(dbBean.getMonth());
	System.out.println("Year iss"+dbBean.getYear());
	String encrypt_year= Encryption.encrypt(dbBean.getYear());
	System.out.println("Brand iss"+dbBean.getBrand_id());
	int brand=dbBean.getBrand_id();
	//int encrypt_brand= Encryption.encrypt(brand);
	request.setAttribute("dealer_id",dbBean.getDealer_id());
	
	mv = new ModelAndView("redirect:/performance/"+ encrypt_oid + "/"+encrypt_did+"/" + encrypt_mid + "/"
			+ encrypt_year +"/"+dbBean.getBrand_id()+ "/1");
			
		}

		return mv;
	}
	
	
	@RequestMapping("LowScoringOutlet/{report_type}/{year}/{month}/{region}/{dealer}/{outlet}/{brand}/{limit}")
	  public ModelAndView lowscoringoutlet(@PathVariable String report_type,@PathVariable String year,@PathVariable String month,@PathVariable String region,@PathVariable String dealer,@PathVariable String outlet,@PathVariable String brand,@PathVariable String limit, DatabaseManagementBean dbBean,DealerBean dBean,MysteryShoppingVisitsBean mvBean,RegionBean rBean,
				HttpServletRequest request, HttpServletResponse response) throws ParseException{

		ModelAndView mv=new ModelAndView("LowScoringOutlet");
		int total = 5;
		System.out.println("LowScoringOutlet");
		 String mon="";
		if(mvBean.getMonth().contentEquals("all")) {
			 mon=mvBean.getMonth();
		}else {
			 mon=String.valueOf((Integer.parseInt(mvBean.getMonth())+100));
			 mon= mon.substring(1);
		}
		  
		 List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		  mv.addObject("activebrandList", activebrandList);
		  List<MysteryShoppingVisitsBean> activeyearsList = rDao.getYearsList(mvBean);
		  mv.addObject("activeyearsList", activeyearsList);
		  List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
		  mv.addObject("activeDealerList",activeDealerList);
		  List<RegionBean> activeregionsList = dbDao.getRegionList1(rBean);
		  mv.addObject("activeregionsList",activeregionsList);
		  mvBean.setReport_type(report_type);
		  mvBean.setYear(year);
		  mvBean.setMonth(mon);
		  mvBean.setRegion_id(region);
		  mvBean.setDealer_id(dealer);
		  mvBean.setOutlet_id(outlet);
		  mvBean.setBrand_id(brand);
		  mvBean.setLimit(limit);
		  request.setAttribute("report_type", mvBean.getReport_type());
		  request.setAttribute("year", mvBean.getYear());
		 
		  request.setAttribute("month",mon );
		  
		  request.setAttribute("region_id", mvBean.getRegion_id());
		  request.setAttribute("dealer_id", mvBean.getDealer_id());
		  request.setAttribute("outlet_id", mvBean.getOutlet_id());
		  request.setAttribute("brand_id", mvBean.getBrand_id());
		  request.setAttribute("limit", mvBean.getLimit());
		  
		  System.out.println("month"+mon);
		  
		  
		  List<DatabaseManagementBean> dealerListByRegionId =rDao. getDealersbySubregion(dbBean, mvBean.getRegion_id(),mvBean.getBrand_id());
		  mv.addObject("dealerListByRegionId",dealerListByRegionId);
		  
		  List<DatabaseManagementBean> outletList = rDao.getOutletsbyDealers(dbBean, mvBean.getDealer_id(),mvBean.getBrand_id());
		  mv.addObject("outletList",outletList);
		 
		  
		  
		List<MysteryShoppingVisitsBean> lowerScore = rDao.getLoweringScore(mvBean,mvBean.getYear(),mvBean.getMonth(),mvBean.getRegion_id(),mvBean.getOutlet_id(),mvBean.getBrand_id(),mvBean.getDealer_id(),limit,total);
		request.setAttribute("dealershipId",dealer);
		request.setAttribute("outletId", outlet);
		mv.addObject("lowerScore", lowerScore);
		
		  return mv;
	}
	
	
	
	
	
	@RequestMapping("LowScoringOutletdownload/{year}/{month}/{region}/{dealer}/{outlet}/{brand}")
	  public ModelAndView LowScoringOutletdownload(@PathVariable String year,@PathVariable String month,@PathVariable String region,@PathVariable String dealer,@PathVariable String outlet,@PathVariable String brand, DatabaseManagementBean dbBean,DealerBean dBean,MysteryShoppingVisitsBean mvBean,RegionBean rBean,
				HttpServletRequest request, HttpServletResponse response) throws ParseException{

		ModelAndView mv=new ModelAndView("lowScoringOutletdownload");
		int total = 5;
		 String mon="";
			if(mvBean.getMonth().contentEquals("all")) {
				 mon=mvBean.getMonth();
			}else {
				 mon=String.valueOf((Integer.parseInt(mvBean.getMonth())+100));
				 mon= mon.substring(1);
			}
			  
			 List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
			  mv.addObject("activebrandList", activebrandList);
			  List<MysteryShoppingVisitsBean> activeyearsList = rDao.getYearsList(mvBean);
			  mv.addObject("activeyearsList", activeyearsList);
			  List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
			  mv.addObject("activeDealerList",activeDealerList);
			  List<RegionBean> activeregionsList = dbDao.getRegionList1(rBean);
			  mv.addObject("activeregionsList",activeregionsList);
			  mvBean.setYear(year);
			  mvBean.setMonth(mon);
			  mvBean.setRegion_id(region);
			  mvBean.setDealer_id(dealer);
			  mvBean.setOutlet_id(outlet);
			  mvBean.setBrand_id(brand);
			  request.setAttribute("report_type", mvBean.getReport_type());
			  request.setAttribute("year", mvBean.getYear());
			 
			  request.setAttribute("month",mon );
			  
			  request.setAttribute("region_id", mvBean.getRegion_id());
			  request.setAttribute("dealer_id", mvBean.getDealer_id());
			  request.setAttribute("outlet_id", mvBean.getOutlet_id());
			  request.setAttribute("brand_id", mvBean.getBrand_id());
			  request.setAttribute("limit", mvBean.getLimit());
			  
			  System.out.println("month"+mon);
		  
		  
		  List<DatabaseManagementBean> dealerListByRegionId =rDao. getDealersbySubregion(dbBean, mvBean.getRegion_id(),mvBean.getBrand_id());
		  mv.addObject("dealerListByRegionId",dealerListByRegionId);
		  
		  List<DatabaseManagementBean> outletList = rDao.getOutletsbyDealers(dbBean, mvBean.getDealer_id(),mvBean.getBrand_id());
		  mv.addObject("outletList",outletList);
		 
		  
		  
		List<MysteryShoppingVisitsBean> lowerScore = rDao.getLoweringScore1(mvBean,mvBean.getYear(),mvBean.getMonth(),mvBean.getRegion_id(),mvBean.getOutlet_id(),mvBean.getBrand_id(),mvBean.getDealer_id());
		request.setAttribute("dealershipId",dealer);
		request.setAttribute("outletId", outlet);
		mv.addObject("lowerScore", lowerScore);
		  return mv;
	}
	
	
	
	@RequestMapping(value = "/getmsmresultcountIds")
	public @ResponseBody Object getIds(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mBean) {
		String report_type = request.getParameter("report_type");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String dealer_id = request.getParameter("sk_dealer_id");
		String outlet_id = request.getParameter("sk_outlet_id");
		String region_id = request.getParameter("region_id");
		String brand_id = request.getParameter("brand_id");
		MysteryShoppingVisitsBean msmresultcountcount = rDao.getmsmresultcountIds(mBean, report_type, month, dealer_id,outlet_id,brand_id,year,region_id);
		return msmresultcountcount;
	}
	
	
	
	@RequestMapping(value = "/getlowscoringcountIds")
	public @ResponseBody Object getlowscoringcountIds(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mBean) {
		String report_type = request.getParameter("report_type");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String dealer_id = request.getParameter("sk_dealer_id");
		String outlet_id = request.getParameter("sk_outlet_id");
		String region_id = request.getParameter("region_id");
		String brand_id = request.getParameter("brand_id");
		MysteryShoppingVisitsBean msmresultcountcount = rDao.getlowscoringcountIds(mBean, report_type, month, dealer_id,outlet_id,brand_id,year,region_id);
		return msmresultcountcount;
	}
	
	@RequestMapping(value = "/getsummarycountIds")
	public @ResponseBody Object getsummarycountIds(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mBean) {
		String report_type = request.getParameter("report_type");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String dealer_id = request.getParameter("sk_dealer_id");
		String outlet_id = request.getParameter("sk_outlet_id");
		String region_id = request.getParameter("region_id");
		String brand_id = request.getParameter("brand_id");
		MysteryShoppingVisitsBean msmresultcountcount = rDao.getsummarycountIds(mBean, report_type, month, dealer_id,outlet_id,brand_id,year,region_id);
		return msmresultcountcount;
	}
	
	
	
	
	/*************manoj d MLR Report***************/

	@RequestMapping("/allgraphs")
	public ModelAndView allgraphs(DatabaseManagementBean dbBean,DealerBean dDBean,MysteryShoppingVisitsBean mvBean,DashboardBean dBean,QuestionnaireBean qBean,GraphBean gBean,RegionBean rgBean,
			MenuBean mBean,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		ModelAndView mv= null;
		try {
			
		mv = new ModelAndView("mlrReport");
		
		System.out.println("hello in all graphs +++++++");
		HttpSession session = request.getSession(true);
		request.setAttribute("role_name", (String) session.getAttribute("role_name"));
		String callingname = request.getRequestURL().toString();
		String getName = callingname.substring(callingname.lastIndexOf("/") + 1);
		System.out.println("getName"+getName);
		try {
		//checkaccessEY(getName, request, response);
		}catch (Exception e) {
			// TODO: handle exception
		}

		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMMMMMMMM");
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY");
		String currentmonth = sdf.format(now.getTime());
		String year = sdf1.format(now.getTime());
		int year3 = Integer.parseInt(year);
		String currentmonth1 = sdf.format(now.getTime());
		int year1 = Integer.parseInt(year);
		
		String role_for = (String) session.getAttribute("role_for");
		String dealers = (String) session.getAttribute("dealers");
		String roleId = (String) session.getAttribute("role_id");
		String region = (String) session.getAttribute("region");
		 String did = gBean.getDealer_id();
			String bid = gBean.getBrand_id();
			String rid = gBean.getRegion_id();
			String oid = gBean.getOutlet_id();
			String mid = gBean.getMonth();
		List<DashboardBean> scheduledloc = dDao.getScheduledloc(dBean, year1, dBean.getBrand_id(), currentmonth,roleId,dealers,region);
		
		Gson gson = new Gson();
		String scheduledlocgson = gson.toJson(scheduledloc);
		request.setAttribute("scheduledlocgson", scheduledlocgson);
		mv.addObject("scheduleloc", scheduledloc);
		
		List<DashboardBean> conductedloc = dDao.getConductedloc(dBean, year1, dBean.getBrand_id(), currentmonth,roleId,dealers,region);
		mv.addObject("conductedloc", conductedloc);
		String conductedlocgson = gson.toJson(conductedloc);
		request.setAttribute("conductedlocgson", conductedlocgson);



		DashboardBean checkClosedVisits1 = dDao.checkCurrentMonthClosedVisits(dBean, currentmonth, dBean.getBrand_id(),year1);
		int i = 0;
		 year1 = Integer.parseInt(year);
		 
		/*
		 * if(checkClosedVisits1.getCount().equals("0")) { if
		 * (currentmonth.contentEquals("January") ||
		 * currentmonth.contentEquals("February") || currentmonth.contentEquals("March")
		 * ) { year1 = Integer.parseInt(year) - 1; }else { year1 =
		 * Integer.parseInt(year); } }else { year1 = Integer.parseInt(year); }
		 */
		

		 int year2 = Integer.parseInt(year);
			DashboardBean checkClosedVisits = dDao.checkCurrentMonthClosedVisits(dBean, currentmonth, dBean.getBrand_id(),year2);

		while (checkClosedVisits.getCount().equals("0")) {
			i = i + 1;
			System.out.println("month" + currentmonth);
			currentmonth = sdf.format(now.getTime());
			now.add(Calendar.MONTH, -1);
			/*
			 * if (currentmonth.contentEquals("January") ||
			 * currentmonth.contentEquals("February") || currentmonth.contentEquals("March")
			 * ) { year1 = Integer.parseInt(year); }else { year1 = Integer.parseInt(year)-1;
			 * }
			 */
			checkClosedVisits = dDao.checkCurrentMonthClosedVisits(dBean, currentmonth, dBean.getBrand_id(),year1);
			if (i == 4) {
				break;
			}
		}
		
		request.setAttribute("currentmonth", currentmonth);
		request.setAttribute("year2", year1);
		
		request.setAttribute("brand_id", dBean.getBrand_id());
		
		dDao.getbrandName(dBean,dBean.getBrand_id());
		request.setAttribute("brand_name", dBean.getBrand_name());
		
		dDao.getFashcardPercentage(dBean, currentmonth, year1,year3,currentmonth1,roleId,dealers,region);
		request.setAttribute("pe_avg", dBean.getPE_avg());
		request.setAttribute("ct_avg", dBean.getCT_avg());
		request.setAttribute("osc_avg", dBean.getOsc_avg());
		request.setAttribute("national_avg", dBean.getNational_avg());
		request.setAttribute("mys_condcuted", dBean.getMys_conducted());
		request.setAttribute("mys_tobe_condcuted", dBean.getMys_tobe_conducted());

		

		List<DashboardBean> getdashboardTabledata = dDao.getdashboardTabledata(dBean, year1, dBean.getBrand_id(),
				currentmonth,roleId,dealers,region);
		
		
		  List<DashboardBean> productsList = new ArrayList<DashboardBean>();  
	        //Adding Products  
	        productsList.addAll(getdashboardTabledata);  
	        
	        double[] score = {Double.MIN_VALUE};
	        int[] no = {0};
	        int[] rank = {0};
	        List<DashboardBean> productPriceList =   
	                productsList.stream().sorted(Comparator.comparingDouble(DashboardBean::getYtd_dealer_avg1).reversed())


	               // productsList.stream()  
	               // .sorted((a, b) -> (int)b.getYtd_dealer_avg1() - (int)a.getYtd_dealer_avg1())
	                            .map(p -> {
	                                ++no[0];
	                                if (score[0] != p.getYtd_dealer_avg1()) rank[0] = no[0];
	                                return new DashboardBean(rank[0], p.getDealer_name(), p.getYtd_dealer_avg1(), p.getPE_avg(), p.getCT_avg(), p.getOsc_avg(),p.getMovement()) ;
	                            })
	                            
	                            
	                            .collect(Collectors.toList());  // collecting as list  
	         
		    String jsonArray = gson.toJson(productPriceList);
		    System.out.println(jsonArray);
		
		
		
		mv.addObject("getdashboardTabledata", productPriceList);
		
		
		System.out.println("role id"+roleId);
		try {
		dDao.getroleMenuById(mBean, roleId);
		}catch (Exception e) {
			// TODO: handle exception
		}
		String menu_list = mBean.getMenu();
		List<MenuBean> mainMenu = dDao.getMainMenu(mBean);
		mv.addObject("mainMenuList", mainMenu);
		List<MenuBean> mainMenu1 = dDao.getMainMenu(mBean, menu_list);
		 mv.addObject("mainMenu1", mainMenu1);
		 request.getSession().setAttribute("mainMenuList", mainMenu);
		 request.getSession().setAttribute("mainMenu1", mainMenu1);
		 List<List<MenuBean>> subMenu = new ArrayList<List<MenuBean>>();
			Iterator<MenuBean> iterator = mainMenu.iterator();
			while (iterator.hasNext()) {
				String menu_id = iterator.next().getMenu_id();
				System.out.println("Hi menu ids" + menu_id);
				subMenu.add(dDao.getSubMenuById(mBean, menu_id, ""));
			}
			mv.addObject("subMenuList", subMenu);
			request.getSession().setAttribute("subMenuList", subMenu);
		/*************dashboard end**************/
		
		
		/************start overAll performance**********/
			List<GraphBean> BrandList = rDao.getBrands(gBean);
			mv.addObject("BrandList", BrandList);
			List<GraphBean> getMonths = rDao.getMonths(gBean);
			mv.addObject("getMonths", getMonths);
			  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
			  mv.addObject("activeRegionList", activeRegionList); 
			  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
			  mv.addObject("activedealersbyid", activedealersbyid); 
			  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
			  mv.addObject("activeoutletsbyid", activeoutletsbyid); 
			  System.out.println("dealer id=="+gBean.getDealer_id());
			  System.out.println("brand id=="+gBean.getBrand_id());
			  System.out.println("region id=="+gBean.getRegion_id());
			  System.out.println("outlet id=="+gBean.getOutlet_id());
			  System.out.println("month=="+gBean.getMonth());
			
				gBean.setDealer_id(gBean.getDealer_id());
				gBean.setRegion_id(gBean.getRegion_id());
				gBean.setOutlet_id(gBean.getOutlet_id());
				gBean.setMonth(gBean.getMonth());
				gBean.setBrand_id(gBean.getBrand_id());
				//request.setAttribute("year", gBean.getYear());
				   //System.out.println("gbean year"+ gBean.getYear());
			  GraphBean outlets = rDao.getOutletsoverallperFilterForYtdMtd(gBean);
				gBean.setOutlet_id(outlets.getOutlet_id());
				System.out.println("outlet in overallperformance==="+outlets.getOutlet_id());
				String monthData = "";
				if (gBean.getMonth() == null || gBean.getMonth().equals("") || gBean.getMonth().equals("all")) {

				} else {
					monthData = " and month(visit_date)= '" + gBean.getMonth() + "'";
				}
				
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				int yearr = Calendar.getInstance().get(Calendar.YEAR);
				//int yearr=2019;
			    Date date = DateUtils.addDays(new Date(), -1);
				sd.setTimeZone(TimeZone.getTimeZone("IST"));
				List<GraphBean> getMonthsData = rDao.getMonthsForYtdMtd(gBean, monthData);
				// System.out.println(gson.toJson(getMonthsData)+":"+getMonthsData.size());
				List<GraphBean> ytdmtd = new ArrayList<GraphBean>();
				for (int j = 0; j < getMonthsData.size(); j++) {
					System.out.println(getMonthsData.get(j).getMonth());
					int month = Integer.parseInt(getMonthsData.get(j).getMonth());
					YearMonth yearMonthObject = YearMonth.of(yearr, month);
					int daysInMonth = yearMonthObject.lengthOfMonth();
					String StartData = "";
					String EndData = "";
					StartData = yearr + "-" + String.format("%02d", month) + "-01";
					EndData = yearr + "-" + String.format("%02d", month) + "-" + daysInMonth + "";
					if (sd.format(date).compareTo(EndData) < 0 && j == getMonthsData.size()) {
						EndData = sd.format(date);
					}
					System.out.println("start date for ytdmtd===="+StartData);
					System.out.println("start date for ytdmtd===="+EndData);
					ytdmtd.addAll(rDao.getytdmtd(qBean,gBean,oid, StartData, EndData, getMonthsData.get(j).getMonth_name(),did));
				}
				mv.addObject("ytdmtd", ytdmtd);
				
				
				String section = "2";
				List<GraphBean> getProcessPercentage = rDao.getOverallPerformanceSectionWiseScore(qBean,gBean, section, mid, oid, bid, did, rid);
				//Gson gson = new Gson(); 
			    String jsonArray11 = gson.toJson(getProcessPercentage);
			    request.setAttribute("jsonArray", jsonArray11);

			    section = "3";
				List<GraphBean> getCustomerPercentage = rDao.getOverallPerformanceSectionWiseScore(qBean,gBean, section, mid, oid, bid, did, rid);
				 String jsonArray1 = gson.toJson(getCustomerPercentage);
				 request.setAttribute("jsonArray1", jsonArray1);
				 System.out.println("Customer percentage ====="+getCustomerPercentage);

				 section = "1";
				List<GraphBean> getOnlineSalesChannel = rDao.getOverallPerformanceSectionWiseScoreData(qBean,gBean, section, mid, oid, bid, did, rid);
				mv.addObject("getProcessPercentage", getProcessPercentage);
				mv.addObject("getCustomerPercentage", getCustomerPercentage);
				mv.addObject("getOnlineSalesChannel", getOnlineSalesChannel);
				String jsonArray2 = gson.toJson(getOnlineSalesChannel);
				 request.setAttribute("jsonArray2", jsonArray2);
				 
				 
					 //QuestionnaireBean getshopperIds = qDao.getshoppersByOutletMode(qBean,gBean,mid);
						//qBean.setShopperIds(getshopperIds.getShopperIds());
						DecimalFormat f = new DecimalFormat("###.##");
				 /***success factors**/
						// 1. Personal Response within 2 working days
						rDao.getOverallperformanceFormula1(qBean, "1.3", "Not at all",bid,oid,did,rid,mid);
						request.setAttribute("personal_avg", f.format(Double.parseDouble(qBean.getAvg())));
						
						// 2. Vehicle Presentation
						rDao.getOverallperformanceFormula3(qBean, "2.4",bid,oid,did,rid,mid);
						request.setAttribute("vehicle_presentation", f.format(Double.parseDouble(qBean.getAvg())));
						
						// 3. Test Drive Offer
						rDao.getOverallperformanceFormula2(qBean, "3.1", "Yes, actively offered",bid,oid,did,rid,mid);
						request.setAttribute("Test_Drive_Offer", f.format(Double.parseDouble(qBean.getAvg())));
						
						// 4. New Car Offer Given
						rDao.getOverallperformanceFormula2(qBean, "4.3",
								"Yes, on official paper sent per e-mail during/right after the consultation",bid,oid,did,rid,mid);
						request.setAttribute("car_offer", f.format(Double.parseDouble(qBean.getAvg())));
						
						// 5. Follow-up on Offer
						rDao.getOverallperformanceFormula4(qBean, "5.2", "yes",bid,oid,did,rid,mid);
						request.setAttribute("offer_followup", f.format(Double.parseDouble(qBean.getAvg())));
						
						// 6. Consider Buying a Vehicle
						System.out.println("hello here we are");
						rDao.getOverallperformanceFormula4(qBean, "6.2", "Yes",bid,oid,did,rid,mid);
						request.setAttribute("Buy_vehicle", f.format(Double.parseDouble(qBean.getAvg())));
						
						/********success factors*********/
						
						/****
						 * Working Standards
						 */
						// 1. Auto Response
						rDao.getOverallperformanceFormula2(qBean, "1.2", "Yes",bid,oid,did,rid,mid);
						request.setAttribute("auto_response", f.format(Double.parseDouble(qBean.getAvg())));

						// 2. Personal Response within 4 hrs
						rDao.getOverallperformanceFormula2(qBean, "1.3", "Within 4 hours after sending the request",bid,oid,did,rid,mid);
						request.setAttribute("response_4_hours", f.format(Double.parseDouble(qBean.getAvg())));

						// 3. Quality of Response: Standards
						rDao.getOverallperformanceFormula4(qBean, "1.5", "Yes",bid,oid,did,rid,mid);
						request.setAttribute("quality_of_response", f.format(Double.parseDouble(qBean.getAvg())));

						// 4. Attempt to make an Appointment
						rDao.getOverallperformanceFormula2(qBean, "1.6", "Yes",bid,oid,did,rid,mid);
						request.setAttribute("appointment", f.format(Double.parseDouble(qBean.getAvg())));

						// 5. Retail Lead Documentation
						rDao.getOverallperformanceFormula4(qBean, "4.5.1", "Yes",bid,oid,did,rid,mid);
						request.setAttribute("retail_lead", f.format(Double.parseDouble(qBean.getAvg())));
						
						// 6. Test Drive Offer
						// same as success factors No.3

						// 7. New Car Offer
						// same as success factors No.4

						// 8. Financing Product Offer given
						rDao.getOverallperformanceFormula2(qBean, "4.2", "Yes",bid,oid,did,rid,mid);
						request.setAttribute("financial_offer", f.format(Double.parseDouble(qBean.getAvg())));

						// 9. Follow-up on Offer
						// same as success factors No.5
						/****
						 * Working Standards
						 */
						
						/****
						 * Retail Standards
						 */
						// 1. Contacted personally by the dealership
						// same as success factors No.1

						// 2. Actively welcomed and guided
						rDao.getOverallperformanceFormula3(qBean, "2.1",bid,oid,did,rid,mid);
						request.setAttribute("welcome_guided", f.format(Double.parseDouble(qBean.getAvg())));

						// 3. Systematic and customer oriented product presentation
						rDao.getOverallperformanceFormula3(qBean, "2.4",bid,oid,did,rid,mid);
						request.setAttribute("product_presentation", f.format(Double.parseDouble(qBean.getAvg())));

						// 4. Detailed product information upon request
						rDao.getOverallperformanceFormula2(qBean, "2.6", "Yes",bid,oid,did,rid,mid);
						request.setAttribute("product_information", f.format(Double.parseDouble(qBean.getAvg())));

						// 5. Test drive actively offered
						// same as success factors No.3

						// 6. Test drive procedure requirements fulfilled
						rDao.getOverallperformanceFormula4(qBean, "3.2",bid,oid,did,rid,mid);
						request.setAttribute("drive_requirements", f.format(Double.parseDouble(qBean.getAvg())));

						// 8. Contacted in the corresponding time frame
						// same as Working factors No.9

						// 7. Test drive vehicle condition
						rDao.getOverallperformanceFormula2(qBean, "3.2", "Yes",bid,oid,did,rid,mid);
						request.setAttribute("vehicle_condition", f.format(Double.parseDouble(qBean.getAvg())));
						/*
						 * Retail Standards
						 * 
						 */
						System.out.println("month for mode=============="+(request.getParameter("month")));
						if (request.getParameter("month") != null) {
							if (!request.getParameter("month").equals("")) {
								GraphBean getMode = rDao.getModeByMonth(gBean);
								if (!getMode.getCount().equals("0")) {
									request.setAttribute("mode", "yes");
								} else {
									request.setAttribute("mode", "no");
								}
							} else {
								request.setAttribute("mode", "yes");
							}
						} else {
							request.setAttribute("mode", "yes");
						}
						request.setAttribute("brand_id", gBean.getBrand_id());
						request.setAttribute("month", gBean.getMonth());
						request.setAttribute("dealer_id", gBean.getDealer_id());
						request.setAttribute("outlet_id", gBean.getOutlet_id());
						request.setAttribute("region_id", gBean.getRegion_id());
						 
		/* END overall performance*/
				
		/*start crm compilance*/
						 /* QuestionnaireBean getshopperIds = qDao.getshoppersByOutletMode(qBean,gBean,mid);
							qBean.setShopperIds(getshopperIds.getShopperIds()); 
							QuestionnaireBean data = qDao.crm(qBean,bid,oid,did,rid,mid);
							String nodelay = data.getNodelay();
							String upto = data.getUpto2days();
							String more = data.getMore2days();
							String name = data.getName();
							System.out.println(more);
							String email = data.getEmail();
							String total = data.getTotalquestions();
							String count = data.getCount();
							String norecords = data.getNorecords();
							String accurate = data.getAccurate();
							DecimalFormat f2 = new DecimalFormat(".##");
							String delaycompliance = f2
									.format(((Double.parseDouble(upto) + Double.parseDouble(more)) / Double.parseDouble(count)) * 100);
							String nodelay_per = f2.format((Double.parseDouble(nodelay) / Double.parseDouble(total)) * 100);
							String upto_percentage = f2.format((Double.parseDouble(upto) / Double.parseDouble(total)) * 100);
							System.out.println();
							String more_percentage = f2.format((Double.parseDouble(more) / Double.parseDouble(total)) * 100);
							System.out.println(Double.parseDouble(more)+"cthgjvkbnrxfchgvjb"+Double.parseDouble(total));
							String email_percentage = f2.format((Double.parseDouble(email) / Double.parseDouble(count)) * 100);
							String name_percentage = f2.format((Double.parseDouble(name) / Double.parseDouble(count)) * 100);
							String noncom_percentage = f2.format((Double.parseDouble(norecords) / Double.parseDouble(count)) * 100);
							String timely_percentage = f2.format((Double.parseDouble(accurate) / Double.parseDouble(count)) * 100);
							DecimalFormat f1 = new DecimalFormat("#");
							if (noncom_percentage.equals(".0")) {
								noncom_percentage = f1.format(0);
							}
							if (delaycompliance.equals(".0")) {
								delaycompliance = f1.format(0);
							}
							if (name_percentage.equals(".0")) {
								name_percentage = f1.format(0);
							}
							if (email_percentage.equals(".0")) {
								email_percentage = f1.format(0);
							}
							if (nodelay.equals(".0")) {
								nodelay = f1.format(0);
							}
							if (timely_percentage.equals(".0")) {
								timely_percentage = f1.format(0);
							}

							request.setAttribute("nodelay", nodelay_per);
							request.setAttribute("delaycompliance", delaycompliance);
							request.setAttribute("upto_percentage", upto_percentage);
							request.setAttribute("more_percentage", more_percentage);
							request.setAttribute("email_percentage", email_percentage);
							request.setAttribute("name_percentage", name_percentage);
							request.setAttribute("noncom_percentage", noncom_percentage);
							request.setAttribute("timely_percentage", timely_percentage);
							List<QuestionnaireBean> list = qDao.getcrmbargraph(qBean,bid,oid,did,rid,mid);
							System.out.println(list.size());
							// System.out.println(list.iterator().next().getMonth());
							String yes = "";
							String noo = "";
							String month = "";
							for (int k = 0; k < list.size(); k++) {
								month = month + "," + list.get(k).getMonth();
								yes = yes + "," + list.get(k).getYes();
								noo = noo + "," + list.get(k).getNo();
							}
							month = month.startsWith(",") ? month.substring(1) : month;

							// month="'"+month.replaceAll(",", "', '")+"'";
							yes = yes.startsWith(",") ? yes.substring(1) : yes;
							noo = noo.startsWith(",") ? noo.substring(1) : noo;
							System.out.println(month);
							request.setAttribute("yes", yes);
							request.setAttribute("no", noo);
							request.setAttribute("months", month); */
						QuestionnaireBean data = qDao.crm(qBean,bid,oid,did,rid,mid);
						String nodelay = data.getNodelay();
						String upto = data.getUpto2days();
						String more = data.getMore2days();
						String name = data.getName();
						System.out.println(more);
						String email = data.getEmail();
						String total = data.getTotalquestions();
						String count = data.getCount();
						String norecords = data.getNorecords();
						String accurate = data.getAccurate();
						DecimalFormat f2 = new DecimalFormat(".##");
						String delaycompliance = f2
								.format(((Double.parseDouble(upto) + Double.parseDouble(more)) / Double.parseDouble(count)) * 100);
						String nodelay_per = f2.format((Double.parseDouble(nodelay) / Double.parseDouble(total)) * 100);
						String upto_percentage = f2.format((Double.parseDouble(upto) / Double.parseDouble(total)) * 100);
						System.out.println("upto===="+upto_percentage);
						String more_percentage = f2.format((Double.parseDouble(more) / Double.parseDouble(total)) * 100);
						System.out.println(Double.parseDouble(more)+"cthgjvkbnrxfchgvjb"+Double.parseDouble(total));
						String email_percentage = f2.format((Double.parseDouble(email) / Double.parseDouble(count)) * 100);
						String name_percentage = f2.format((Double.parseDouble(name) / Double.parseDouble(count)) * 100);
						String noncom_percentage = f2.format((Double.parseDouble(norecords) / Double.parseDouble(count)) * 100);
						String timely_percentage = f2.format((Double.parseDouble(accurate) / Double.parseDouble(count)) * 100);
						DecimalFormat f1 = new DecimalFormat("#");
						if (noncom_percentage.equals(".0")) {
							noncom_percentage = f1.format(0);
						}
						if (delaycompliance.equals(".0")) {
							delaycompliance = f1.format(0);
						}
						if (name_percentage.equals(".0")) {
							name_percentage = f1.format(0);
						}
						if (email_percentage.equals(".0")) {
							email_percentage = f1.format(0);
						}
						if (nodelay.equals(".0")) {
							nodelay = f1.format(0);
						}
						if (timely_percentage.equals(".0")) {
							timely_percentage = f1.format(0);
						}

						request.setAttribute("nodelay", nodelay_per);
						request.setAttribute("delaycompliance", delaycompliance);
						request.setAttribute("upto_percentage", upto_percentage);
						request.setAttribute("more_percentage", more_percentage);
						request.setAttribute("email_percentage", email_percentage);
						request.setAttribute("name_percentage", name_percentage);
						request.setAttribute("noncom_percentage", noncom_percentage);
						request.setAttribute("timely_percentage", timely_percentage);
						List<QuestionnaireBean> list = qDao.getcrmbargraph(qBean,bid,oid,did,rid,mid);
						System.out.println(list.size());
						// System.out.println(list.iterator().next().getMonth());
						String yes = "";
						String noo = "";
						String month = "";
						for (int k = 0; k < list.size(); k++) {
							month = month + "," + list.get(k).getMonth();
							yes = yes + "," + list.get(k).getYes();
							noo = noo + "," + list.get(k).getNo();
						}
						month = month.startsWith(",") ? month.substring(1) : month;

						// month="'"+month.replaceAll(",", "', '")+"'";
						yes = yes.startsWith(",") ? yes.substring(1) : yes;
						noo = noo.startsWith(",") ? noo.substring(1) : noo;
						System.out.println(month);
						request.setAttribute("yes", yes);
						request.setAttribute("no", noo);
						request.setAttribute("months", month);
						
						request.setAttribute("brand_id", gBean.getBrand_id());
						request.setAttribute("month", gBean.getMonth());
						request.setAttribute("dealer_id", gBean.getDealer_id());
						request.setAttribute("outlet_id", gBean.getOutlet_id());
						request.setAttribute("region_id", gBean.getRegion_id()); 

				/******end crm compilance******/
		
					
				/***start Conquest And Loyalty & Contact*******/
					gBean.setMode_of_contact("Email/Website");
					List<GraphBean> autoresponseemailgraph =rDao.getautoresponseemailgraph(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
	                mv.addObject("autoresponseemailgraph", autoresponseemailgraph); 
	                Gson gson3 = new Gson(); 
	                String autoresponseemailgraphjson = gson3.toJson(autoresponseemailgraph);
	                request.setAttribute("autoresponseemailgraphjson", autoresponseemailgraphjson);
	  
	                List<GraphBean> dealershipgraph =rDao.getdealershipgraphresponse(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
	                 mv.addObject("dealershipgraph", dealershipgraph); 
	                String dealershipgraphresponse = gson3.toJson(dealershipgraph);
	                 request.setAttribute("dealershipgraphresponse", dealershipgraphresponse);
	  
	  
	  
	                List<GraphBean> appointmentchart =rDao.getappointmentchart(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
	                mv.addObject("appointmentchart", appointmentchart); 
	                String appointmentchartjson = gson3.toJson(appointmentchart);
	                request.setAttribute("appointmentchartjson", appointmentchartjson);
	
	                List<GraphBean> meetthestandards =rDao.getmeetthestandards(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
	                 mv.addObject("meetthestandards", meetthestandards); 
	                 String meetthestandardsjson = gson3.toJson(meetthestandards);
	                request.setAttribute("meetthestandardsjson", meetthestandardsjson);  
			/****end CL1********/
	                
		 /********* Start Conquest And Loyalty Contact Via Telophone**********/
					   gBean.setMode_of_contact("Telephone");
					   List<GraphBean> stepstotakegraph =rDao.getstepstotakegraph(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
						  mv.addObject("stepstotakegraph", stepstotakegraph); 
						  Gson gson4 = new Gson(); 
						  String stepstotakegraphjsonArray = gson4.toJson(stepstotakegraph);
						  request.setAttribute("stepstotakegraphjsonArray", stepstotakegraphjsonArray);
						  
						  List<GraphBean> meetthestandardsviatelephone =rDao.getmeetthestandardtelephonesjson(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
						  mv.addObject("meetthestandardsviatelephone", meetthestandardsviatelephone); 
						  String meetthestandardtelephonesjson = gson4.toJson(meetthestandardsviatelephone);
						  request.setAttribute("meetthestandardtelephonesjson", meetthestandardtelephonesjson);
						  
						  List<GraphBean> anyoneatdealership =rDao.getanyoneatdealershipjson(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
						  mv.addObject("anyoneatdealership", anyoneatdealership); 
						  String anyoneatdealershipjson = gson4.toJson(anyoneatdealership);
						  request.setAttribute("anyoneatdealershipjson", anyoneatdealershipjson); 
			/********* End Conquest And Loyalty Contact Via Telophone**********/
						  
						  
		   /***************Start Conquest And loyalty Focus Area Performance*************/
						  
						/*  List<GraphBean> contactdetailsspeltcorrectly =rDao.getcontactdetailsspeltcorrectly(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
						  mv.addObject("contactdetailsspeltcorrectly", contactdetailsspeltcorrectly); 
						  String contactdetailsspeltcorrectlyjson = gson.toJson(contactdetailsspeltcorrectly);
						  request.setAttribute("contactdetailsspeltcorrectlyjson", contactdetailsspeltcorrectlyjson);
						  
						  
						  List<GraphBean> Financingorleasingoptions =rDao.getFinancingorleasingoptions(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
						  mv.addObject("Financingorleasingoptions", Financingorleasingoptions); 
						  String Financingorleasingoptionsgson = gson.toJson(Financingorleasingoptions);
						  request.setAttribute("Financingorleasingoptionsgson", Financingorleasingoptionsgson);
						  
						  List<GraphBean> testdriveactivelyoffered =rDao.gettestdriveactivelyoffered(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
						  mv.addObject("testdriveactivelyoffered", testdriveactivelyoffered); 
						  String testdriveactivelyofferedgson = gson.toJson(testdriveactivelyoffered);
						  request.setAttribute("testdriveactivelyofferedgson", testdriveactivelyofferedgson);
						  
						  List<GraphBean> individualizedOffer =rDao.getindividualizedOffergson(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
						  mv.addObject("individualizedOffer", individualizedOffer); 
						  String individualizedOffergson = gson.toJson(individualizedOffer);
						  request.setAttribute("individualizedOffergson", individualizedOffergson);
						  
						  List<GraphBean> correspondingtimeframe =rDao.getcorrespondingtimeframe(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
						  mv.addObject("correspondingtimeframe", correspondingtimeframe); 
						  String correspondingtimeframegson = gson.toJson(correspondingtimeframe);
						  request.setAttribute("correspondingtimeframegson", correspondingtimeframegson); */
						  
						  List<GraphBean> contactdetailsspeltcorrectly =rDao.getcontactdetailsspeltcorrectly(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
						  mv.addObject("contactdetailsspeltcorrectly", contactdetailsspeltcorrectly); 
						  if(contactdetailsspeltcorrectly.isEmpty()) { 
							   System.out.println("list is empty");
							   int year11 = Calendar.getInstance().get(Calendar.YEAR);
							   String year22=String.valueOf(year11);
							   request.setAttribute("selected_year", year22);
						   }
						   else {
							   String year22=contactdetailsspeltcorrectly.get(0).getYear();
							   request.setAttribute("selected_year", year22);
						   }
						  String contactdetailsspeltcorrectlyjson = gson.toJson(contactdetailsspeltcorrectly);
						  request.setAttribute("contactdetailsspeltcorrectlyjson", contactdetailsspeltcorrectlyjson);
						  
						  
						  List<GraphBean> Financingorleasingoptions =rDao.getFinancingorleasingoptions(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
						  mv.addObject("Financingorleasingoptions", Financingorleasingoptions); 
						  String Financingorleasingoptionsgson = gson.toJson(Financingorleasingoptions);
						  request.setAttribute("Financingorleasingoptionsgson", Financingorleasingoptionsgson);
						  
						  List<GraphBean> CourtesyGraph =rDao.getCourtesyGraphoptions(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
						  mv.addObject("CourtesyGraph", CourtesyGraph); 
						  String CourtesyGraphgson = gson.toJson(CourtesyGraph);
						  request.setAttribute("CourtesyGraphgson", CourtesyGraphgson);
						  
						  List<GraphBean> testdriveactivelyoffered =rDao.gettestdriveactivelyoffered(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
						  mv.addObject("testdriveactivelyoffered", testdriveactivelyoffered); 
						  String testdriveactivelyofferedgson = gson.toJson(testdriveactivelyoffered);
						  request.setAttribute("testdriveactivelyofferedgson", testdriveactivelyofferedgson);
						  
						  List<GraphBean> individualizedOffer =rDao.getindividualizedOffergson(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
						  mv.addObject("individualizedOffer", individualizedOffer); 
						  String individualizedOffergson = gson.toJson(individualizedOffer);
						  request.setAttribute("individualizedOffergson", individualizedOffergson);
						  
						  List<GraphBean> correspondingtimeframe =rDao.getcorrespondingtimeframe(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
						  mv.addObject("correspondingtimeframe", correspondingtimeframe); 
						  String correspondingtimeframegson = gson.toJson(correspondingtimeframe);
						  request.setAttribute("correspondingtimeframegson", correspondingtimeframegson);
			/***************Start Conquest And loyalty Focus Area Performance*************/
						  
						  
			/************Srart Discount analysis*************/
						 List<GraphBean> getDiscountPrices = rDao.getDiscountPrices(gBean,bid,mid,did,oid,rid);
						List<GraphBean> list1 = rDao.getdiscountbrands1(gBean,bid,mid,did,oid,rid);
						System.out.println("list data"+list1);
						//Gson gson = new Gson(); 
					    String data1 = gson.toJson(list1);
					    System.out.println("list data manoj d discount analysis"+data1);
					    request.setAttribute("data", list1);
					    GraphBean piechart = rDao.getBranddiscountpiechart(gBean,mid);
					    request.setAttribute("yess", piechart.getYes());
						request.setAttribute("nooo", piechart.getNo());
						request.setAttribute("amount", piechart.getStatus());
						System.out.println("pie-chart amount===="+piechart.getStatus());
						System.out.println("pie-chart no===="+piechart.getNo());
						System.out.println("pie-chart yes===="+piechart.getYes());
						mv.addObject("getDiscountPrices", getDiscountPrices); 
			 /************End  Discount analysis*************/
							
			 /********** Start competition Overview******/
						GraphBean outlets1 = rDao.getOutletsoverallperFilterCompetition(gBean,oid,rid,bid,did);

						  gBean.setOutlets(outlets1.getOutlets());
						List<GraphBean> getBrandData = rDao.getcompetionBrandScore(gBean);
						mv.addObject("getBrandData", getBrandData);
						
						List<GraphBean> getRegionData = rDao.getregionScore(gBean);
						mv.addObject("getRegionData", getRegionData);

						List<GraphBean> getShoppingScore = rDao.getShoppingScore(gBean);
						mv.addObject("getShoppingScore", getShoppingScore);
						
						System.out.println(gson.toJson(getShoppingScore));
						request.setAttribute("dataarray", gson.toJson(getShoppingScore)); 
			/********** End competition Overview******/
								
			/***********Start Life Style Analysis*************/
							
						 List<GraphBean> configurationprocess =rDao.getconfigurationprocess(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
						  mv.addObject("configurationprocess", configurationprocess); 
						  String configurationprocessgson = gson.toJson(configurationprocess);
						  request.setAttribute("configurationprocessgson", configurationprocessgson);
						  
						  List<GraphBean> Whattakehomematerial =rDao.getWhattakehomematerial(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
						  mv.addObject("Whattakehomematerial", Whattakehomematerial); 
						  String Whattakehomematerialgson = gson.toJson(Whattakehomematerial);
						  request.setAttribute("Whattakehomematerialgson", Whattakehomematerialgson);  
			/***********End Life Style Analysis*************/
								  
			 /******** Start Training need Analysis***************/
								 QuestionnaireBean getshopperId = qDao.getshoppersByOutlet(qBean,gBean,mid);
									qBean.setShopperIds(getshopperId.getShopperIds());
								
								if (getshopperId.getShopperIds() != null) {
									List<QuestionnaireBean> getdayCount = rDao.getdayCount(qBean, "5.3",bid,oid,did,rid,mid);
									int length = getdayCount.size();
									request.setAttribute("days", getdayCount.get(length - 1).getDate());
								} else {
									request.setAttribute("days", 0);
								}
								
						qBean.setStandard_number("2.2");
						List<QuestionnaireBean> needAnalysisScore = rDao.trainingneedanalysis(qBean,gBean,rid,did,oid,bid,mid);
						mv.addObject("needAnalysisScore", needAnalysisScore);
						
						qBean.setStandard_number("IN-2");
						List<QuestionnaireBean> featurePresentationScore = rDao.trainingneedanalysis(qBean,gBean,rid,did,oid,bid,mid);
						mv.addObject("featurePresentationScore", featurePresentationScore);
						
						qBean.setStandard_number("2.4");
						List<QuestionnaireBean> fivePointScore = rDao.trainingneedanalysis(qBean,gBean,rid,did,oid,bid,mid);
						mv.addObject("fivePointScore", fivePointScore);
						
						qBean.setStandard_number("IN-9");
						List<QuestionnaireBean> ratingstandard = rDao.trainingneedanalysis(qBean,gBean,rid,did,oid,bid,mid);
						mv.addObject("ratingstandard", ratingstandard);
						
						qBean.setStandard_number("2.4");
						QuestionnaireBean productpercentage = rDao.getProductAndDigitalPercentages(qBean,bid,oid,did,rid,mid);
						request.setAttribute("productpercentage", productpercentage.getOne());
						request.setAttribute("digital", productpercentage.getTwo());  
								
			/****************End Ttraining Need Analysis**********/
								
			/********Start Financial Service Analysis**********/
							
							List<GraphBean> bmwfinancialservice =rDao.getbmwfinancialservice(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
	                        mv.addObject("bmwfinancialservice", bmwfinancialservice); 
	                        String bmwfinancialservicegson = gson.toJson(bmwfinancialservice);
	                         request.setAttribute("bmwfinancialservicegson", bmwfinancialservicegson);
	  
	                        List<GraphBean> buybackprogram =rDao.getbuybackprogram(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
	                         mv.addObject("buybackprogram", buybackprogram); 
	                         String buybackprogramgson = gson.toJson(buybackprogram);
	                         request.setAttribute("buybackprogramgson", buybackprogramgson);

	                         List<GraphBean> offeredbreakdown =rDao.getofferedbreakdown(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
	                         mv.addObject("offeredbreakdown", offeredbreakdown); 
	                         String offeredbreakdowngson = gson.toJson(offeredbreakdown);
	                         request.setAttribute("offeredbreakdowngson", offeredbreakdowngson); 
	                         
	                         List<GraphBean>handoverfinanceoffer =rDao.gethandoverfinanceoffer(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),oid,gBean.getMonth());
	                   	  mv.addObject("handoverfinanceoffer", handoverfinanceoffer); 
	                   	  String handoverfinanceoffergson = gson.toJson(handoverfinanceoffer);
	                   	  request.setAttribute("handoverfinanceoffergson", handoverfinanceoffergson);
			/********End Financial Service Analysis*************/
								  
			/************Start Bps Analysis**********/
							 
	                        List<GraphBean> currentcustomervehicle =rDao.getcurrentcustomervehiclegson(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	                   	  mv.addObject("currentcustomervehicle", currentcustomervehicle); 
	                   	  String currentcustomervehiclegson = gson.toJson(currentcustomervehicle);
	                   	  request.setAttribute("currentcustomervehiclegson", currentcustomervehiclegson);  
			/**********End Bps Analysis*********/
		}catch (Exception e) {
			  mv = new ModelAndView("redirect:/");
		}			
		 return mv;
	}
	/***************manoj d MLR Report************/
	
	/******Reports*******/
	@RequestMapping("msmresult/{report_type}/{year}/{month}/{region}/{dealer}/{outlet}/{brand}/{limit}")
	  public ModelAndView msmresult(@PathVariable String report_type,@PathVariable String year,@PathVariable String month,@PathVariable String region,@PathVariable String dealer,@PathVariable String outlet,@PathVariable String brand,@PathVariable String limit, DatabaseManagementBean dbBean,DealerBean dBean,MysteryShoppingVisitsBean mvBean,RegionBean rBean,
				HttpServletRequest request, HttpServletResponse response){

		ModelAndView mv=new ModelAndView("msmresult");
		System.out.println("jsadhhsdkj");
		int total = 5;
		 List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		  mv.addObject("activebrandList", activebrandList);
		  List<MysteryShoppingVisitsBean> activeyearsList = rDao.getYearsList(mvBean);
		  mv.addObject("activeyearsList", activeyearsList);
		  List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
		  mv.addObject("activeDealerList",activeDealerList);
		  List<RegionBean> activeregionsList = dbDao.getRegionList1(rBean);
		  mv.addObject("activeregionsList",activeregionsList);
		  
		  mvBean.setReport_type(report_type);
		  mvBean.setYear(year);
		  mvBean.setMonth(month);
		  mvBean.setRegion_id(region);
		  mvBean.setDealer_id(dealer);
		  mvBean.setOutlet_id(outlet);
		  mvBean.setBrand_id(brand);
		  mvBean.setLimit(limit);
		  request.setAttribute("report_type", mvBean.getReport_type());
		  request.setAttribute("year", mvBean.getYear());
		  request.setAttribute("month", mvBean.getMonth());
		  request.setAttribute("region_id", mvBean.getRegion_id());
		  request.setAttribute("dealer_id", mvBean.getDealer_id());
		  request.setAttribute("outlet_id", mvBean.getOutlet_id());
		  request.setAttribute("brand_id", mvBean.getBrand_id());
		  request.setAttribute("limit", mvBean.getLimit());
		  
		  
		  
		  List<DatabaseManagementBean> dealerListByRegionId =rDao. getDealersbySubregion(dbBean, mvBean.getRegion_id(),mvBean.getBrand_id());
		  mv.addObject("dealerListByRegionId",dealerListByRegionId);
		  
		  List<DatabaseManagementBean> outletList = rDao.getOutletsbyDealers(dbBean, mvBean.getDealer_id(),mvBean.getBrand_id());
		  mv.addObject("outletList",outletList);
		 
		  
		  
		 
		  
		  System.out.println("report_type="+mvBean.getReport_type());
		  System.out.println("year="+mvBean.getYear());
		  System.out.println("month="+mvBean.getMonth());
		  System.out.println("region="+mvBean.getRegion_id());
		  System.out.println("dealer="+mvBean.getDealer_id());
		  System.out.println("location="+mvBean.getOutlet_id());
		  System.out.println("brand_id="+mvBean.getBrand_id());
		  System.out.println("limit="+mvBean.getLimit());
		  
		//  List<MysteryShoppingVisitsBean> allMSResultAns = rDao.getMSNResultAns1(mvBean,mvBean.getYear(),mvBean.getMonth(),mvBean.getRegion_id(),mvBean.getOutlet_id(),mvBean.getBrand_id(),mvBean.getDealer_id());
		//	mv.addObject("allMSResultAns", allMSResultAns);
			
			
		/*
		 * List<List<MysteryShoppingVisitsBean>> subList1 = new
		 * ArrayList<List<MysteryShoppingVisitsBean>>();
		 * Iterator<MysteryShoppingVisitsBean> iterator1 = allMSResultAns.iterator();
		 * while (iterator1.hasNext()) { String id =
		 * iterator1.next().getSk_shopper_id(); subList1.add(rDao.MSNResultAns(mvBean,
		 * id)); }
		 * 
		 * mv.addObject("subQnList1", subList1);
		 */  
			
			
			

		  List<MysteryShoppingVisitsBean> MSNResult = rDao.getMSNResultHeadders(mvBean,mvBean.getBrand_id(),mvBean.getYear());
		  mv.addObject("MSNResult", MSNResult);
		  
			List<MysteryShoppingVisitsBean> MSResultAns = rDao.getMSNResultAns(mvBean,mvBean.getYear(),mvBean.getMonth(),mvBean.getRegion_id(),mvBean.getOutlet_id(),mvBean.getBrand_id(),mvBean.getDealer_id(),total);
			mv.addObject("MSResultAns", MSResultAns);

		/*	List<List<MysteryShoppingVisitsBean>> subList = new ArrayList<List<MysteryShoppingVisitsBean>>();
			Iterator<MysteryShoppingVisitsBean> iterator = MSResultAns.iterator();
			while (iterator.hasNext()) {
				String id = iterator.next().getSk_shopper_id();
				subList.add(rDao.MSNResultAns(mvBean, id));
			}

			mv.addObject("subQnList", subList);
		  */

	  return mv;
	  }
	
	
	
	@RequestMapping("msmdownload/{year}/{month}/{region}/{dealer}/{outlet}/{brand}")
	  public ModelAndView msmdownload(@PathVariable String year,@PathVariable String month,@PathVariable String region,@PathVariable String dealer,@PathVariable String outlet,@PathVariable String brand, DatabaseManagementBean dbBean,DealerBean dBean,MysteryShoppingVisitsBean mvBean,RegionBean rBean,
				HttpServletRequest request, HttpServletResponse response){

		ModelAndView mv=new ModelAndView("msmdownload");
		 List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		  mv.addObject("activebrandList", activebrandList);
		  List<MysteryShoppingVisitsBean> activeyearsList = rDao.getYearsList(mvBean);
		  mv.addObject("activeyearsList", activeyearsList);
		  List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
		  mv.addObject("activeDealerList",activeDealerList);
		  List<RegionBean> activeregionsList = dbDao.getRegionList1(rBean);
		  mv.addObject("activeregionsList",activeregionsList);
		  
		  mvBean.setYear(year);
		  mvBean.setMonth(month);
		  mvBean.setRegion_id(region);
		  mvBean.setDealer_id(dealer);
		  mvBean.setOutlet_id(outlet);
		  mvBean.setBrand_id(brand);
		  request.setAttribute("year", mvBean.getYear());
		  request.setAttribute("month", mvBean.getMonth());
		  request.setAttribute("region", mvBean.getRegion_id());
		  request.setAttribute("dealer", mvBean.getDealer_id());
		  request.setAttribute("location", mvBean.getOutlet_id());
		  request.setAttribute("brand_id", mvBean.getBrand_id());
		  
		  
		  
		  List<DatabaseManagementBean> dealerListByRegionId =rDao. getDealersbySubregion(dbBean, mvBean.getRegion_id(),mvBean.getBrand_id());
		  mv.addObject("dealerListByRegionId",dealerListByRegionId);
		  
		  List<DatabaseManagementBean> outletList = rDao.getOutletsbyDealers(dbBean, mvBean.getDealer_id(),mvBean.getBrand_id());
		  mv.addObject("outletList",outletList);
		 
		  
		  
		 
		  
		  System.out.println("report_type="+mvBean.getReport_type());
		  System.out.println("year="+mvBean.getYear());
		  System.out.println("month="+mvBean.getMonth());
		  System.out.println("region="+mvBean.getRegion_id());
		  System.out.println("dealer="+mvBean.getDealer_id());
		  System.out.println("location="+mvBean.getOutlet_id());
		  System.out.println("brand_id="+mvBean.getBrand_id());
		  System.out.println("limit="+mvBean.getLimit());
		  
		  List<MysteryShoppingVisitsBean> MSNResult = rDao.getMSNResultHeadders(mvBean,mvBean.getBrand_id(),mvBean.getYear());
		  mv.addObject("MSNResult", MSNResult);
		 
		  
		  List<MysteryShoppingVisitsBean> allMSResultAns = rDao.getMSNResultAns1(mvBean,mvBean.getYear(),mvBean.getMonth(),mvBean.getRegion_id(),mvBean.getOutlet_id(),mvBean.getBrand_id(),mvBean.getDealer_id());
			mv.addObject("allMSResultAns", allMSResultAns);
			System.out.println("msm result final");
		   
	  return mv;
	  }
	
	
	
	
	
	
	@RequestMapping("summary/{report_type}/{year}/{month}/{region}/{dealer}/{outlet}/{brand}/{limit}")
	  public ModelAndView summary(@PathVariable String report_type,@PathVariable String year,@PathVariable String month,@PathVariable String region,@PathVariable String dealer,@PathVariable String outlet,@PathVariable String brand,@PathVariable String limit, DatabaseManagementBean dbBean,DealerBean dBean,MysteryShoppingVisitsBean mvBean,RegionBean rBean,
				HttpServletRequest request, HttpServletResponse response){

		ModelAndView mv=new ModelAndView("summary");
		System.out.println("jsadhhsdkj");
		int total = 5;
		 List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		  mv.addObject("activebrandList", activebrandList);
		  List<MysteryShoppingVisitsBean> activeyearsList = rDao.getYearsList(mvBean);
		  mv.addObject("activeyearsList", activeyearsList);
		  List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
		  mv.addObject("activeDealerList",activeDealerList);
		  List<RegionBean> activeregionsList = dbDao.getRegionList1(rBean);
		  mv.addObject("activeregionsList",activeregionsList);
		  
		  mvBean.setReport_type(report_type);
		  mvBean.setYear(year);
		  mvBean.setMonth(month);
		  mvBean.setRegion_id(region);
		  mvBean.setDealer_id(dealer);
		  mvBean.setOutlet_id(outlet);
		  mvBean.setBrand_id(brand);
		  mvBean.setLimit(limit);
		  request.setAttribute("report_type", mvBean.getReport_type());
		  request.setAttribute("year", mvBean.getYear());
		  request.setAttribute("month", mvBean.getMonth());
		  request.setAttribute("region_id", mvBean.getRegion_id());
		  request.setAttribute("dealer_id", mvBean.getDealer_id());
		  request.setAttribute("outlet_id", mvBean.getOutlet_id());
		  request.setAttribute("brand_id", mvBean.getBrand_id());
		  request.setAttribute("limit", mvBean.getLimit());
		  
		  
		  
		  List<DatabaseManagementBean> dealerListByRegionId =rDao. getDealersbySubregion(dbBean, mvBean.getRegion_id(),mvBean.getBrand_id());
		  mv.addObject("dealerListByRegionId",dealerListByRegionId);
		  
		  List<DatabaseManagementBean> outletList = rDao.getOutletsbyDealers(dbBean, mvBean.getDealer_id(),mvBean.getBrand_id());
		  mv.addObject("outletList",outletList);
		 
		  Gson gson = new Gson();
		  
		 
		  List<MysteryShoppingVisitsBean> summaryScore = rDao.getdealer_performance(mvBean,mvBean.getYear(),mvBean.getMonth(),mvBean.getRegion_id(),mvBean.getOutlet_id(),mvBean.getBrand_id(),mvBean.getDealer_id(),mvBean.getLimit(),total);
		 
//		  double[] score = {Double.MIN_VALUE};
//		     int[] no = {0};
//		     Integer[] rank = {0};
//		     List<MysteryShoppingVisitsBean> ranking = 
//		    		 summaryScore.stream().sorted(Comparator.comparingDouble(MysteryShoppingVisitsBean::getYtd_dealer_avg1).reversed())
//		         .map(p -> {
//		             ++no[0];
//		             if (score[0] != p.getYtd_dealer_avg1()) rank[0] = no[0];
//		             p.setYtd_dealer_rank(rank[0]);
//		             return new MysteryShoppingVisitsBean(rank[0], score[0] =  p.getYtd_dealer_avg1());
//		         })
//		         // .distinct() // if you want to remove duplicate rankings.
//		         .collect(Collectors.toList());
//		     System.out.println(ranking);
//		     
//			    String jsonArray = gson.toJson(ranking);
//			    System.out.println(jsonArray);
//		  
		  mv.addObject("summaryScore", summaryScore);
		  

		  List<MysteryShoppingVisitsBean> summaryheader =rDao.getsectionSubsection(mvBean);
		  mv.addObject("summaryheader",summaryheader);
		  
		  List<MysteryShoppingVisitsBean> summaryheader1 =rDao.getsectionSubsection1(mvBean);
		  mv.addObject("summaryheader1",summaryheader1);
		 

	  return mv;
	  }
	
	
	
	
	@RequestMapping("summarydownload/{year}/{month}/{region}/{dealer}/{outlet}/{brand}")
	  public ModelAndView summarydownload(@PathVariable String year,@PathVariable String month,@PathVariable String region,@PathVariable String dealer,@PathVariable String outlet,@PathVariable String brand, DatabaseManagementBean dbBean,DealerBean dBean,MysteryShoppingVisitsBean mvBean,RegionBean rBean,
				HttpServletRequest request, HttpServletResponse response){

		ModelAndView mv=new ModelAndView("summarysheetdownload");
		 List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		  mv.addObject("activebrandList", activebrandList);
		  List<MysteryShoppingVisitsBean> activeyearsList = rDao.getYearsList(mvBean);
		  mv.addObject("activeyearsList", activeyearsList);
		  List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
		  mv.addObject("activeDealerList",activeDealerList);
		  List<RegionBean> activeregionsList = dbDao.getRegionList1(rBean);
		  mv.addObject("activeregionsList",activeregionsList);
		  
		  mvBean.setYear(year);
		  mvBean.setMonth(month);
		  mvBean.setRegion_id(region);
		  mvBean.setDealer_id(dealer);
		  mvBean.setOutlet_id(outlet);
		  mvBean.setBrand_id(brand);
		  request.setAttribute("report_type", mvBean.getReport_type());
		  request.setAttribute("year", mvBean.getYear());
		  request.setAttribute("month", mvBean.getMonth());
		  request.setAttribute("region_id", mvBean.getRegion_id());
		  request.setAttribute("dealer_id", mvBean.getDealer_id());
		  request.setAttribute("outlet_id", mvBean.getOutlet_id());
		  request.setAttribute("brand_id", mvBean.getBrand_id());
		//  request.setAttribute("limit", mvBean.getLimit());
		  
		  
		  
		  List<DatabaseManagementBean> dealerListByRegionId =rDao. getDealersbySubregion(dbBean, mvBean.getRegion_id(),mvBean.getBrand_id());
		  mv.addObject("dealerListByRegionId",dealerListByRegionId);
		  
		  List<DatabaseManagementBean> outletList = rDao.getOutletsbyDealers(dbBean, mvBean.getDealer_id(),mvBean.getBrand_id());
		  mv.addObject("outletList",outletList);
		 
		  Gson gson = new Gson();
		  
		 
		  List<MysteryShoppingVisitsBean> summaryScore = rDao.getdealer_performance1(mvBean,mvBean.getYear(),mvBean.getMonth(),mvBean.getRegion_id(),mvBean.getOutlet_id(),mvBean.getBrand_id(),mvBean.getDealer_id());
//		  double[] score = {Double.MIN_VALUE};
//		     int[] no = {0};
//		     
//		     Integer[] rank = {0};
//		     List<MysteryShoppingVisitsBean> ranking = 
//		    		 summaryScore.stream().sorted(Comparator.comparingDouble(MysteryShoppingVisitsBean::getYtd_dealer_avg1).reversed())
//		         .map(p -> {
//		             ++no[0];
//		             if (score[0] != p.getYtd_dealer_avg1() )
//		            	 rank[0] = no[0];
//		             p.setYtd_dealer_rank(rank[0]);
//		             return new MysteryShoppingVisitsBean(rank[0], score[0] =  p.getYtd_dealer_avg1());
//		         })
//		         // .distinct() // if you want to remove duplicate rankings.
//		         .collect(Collectors.toList());
//		     System.out.println(ranking);
//		     
//			    String jsonArray = gson.toJson(ranking);
//			    System.out.println(jsonArray);
		  mv.addObject("summaryScore", summaryScore);


		  List<MysteryShoppingVisitsBean> summaryheader =rDao.getsectionSubsection(mvBean);
		  mv.addObject("summaryheader",summaryheader);
		 
		  List<MysteryShoppingVisitsBean> summaryheader1 =rDao.getsectionSubsection1(mvBean);
		  mv.addObject("summaryheader1",summaryheader1);
	  return mv;
	  }
	
	
	
	
	
	
	@RequestMapping(value = "/getDealersbySubregion")
	public @ResponseBody Object getRegionsByDealerId(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		String region_id = request.getParameter("regionId");
		String brand_id = request.getParameter("brand");
		List<DatabaseManagementBean> dealerList = rDao.getDealersbySubregion(dbBean, region_id,brand_id);
		// model.addAttribute("roleData", roleList);
		return dealerList;
	}
	@RequestMapping(value = "/getOutletsbyDealers")
	public @ResponseBody Object getOutletsbyDealers(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		String dealer_id = request.getParameter("sk_dealer_id");
		String brand_id = request.getParameter("brand_id");
		List<DatabaseManagementBean> outletList = rDao.getOutletsbyDealers(dbBean, dealer_id,brand_id);
		// model.addAttribute("roleData", roleList);
		return outletList;
	}
	
	@RequestMapping("performance/{encrypt_oid}/{encrypt_did}/{encrypt_month}/{encrypt_year}/{brand}/{page}")
	public ModelAndView performance(@PathVariable String encrypt_oid,@PathVariable String encrypt_did, @PathVariable String encrypt_year, @PathVariable String page,
			@PathVariable String brand,@PathVariable String encrypt_month, ReportsBean rBean,MysteryShoppingVisitsBean mvBean,HttpServletRequest request) throws java.text.ParseException{
	//ModelAndView mv=new ModelAndView("performance1");
		  ModelAndView model = null;
		  try {
			
		  if (page.equals("1")) {
			  model = new ModelAndView("performance1"); 
			  } else { 
				  model = new ModelAndView("performance2");
				  }
		MysteryShoppingVisitsBean mvBean1= new MysteryShoppingVisitsBean();
		System.out.println("in performance contrlr===="+brand);
		String oid=Encryption.decrypt(encrypt_oid);
		String did=Encryption.decrypt(encrypt_did);
		String month=Encryption.decrypt(encrypt_month);
		System.out.println("month============"+month);
		String year=Encryption.decrypt(encrypt_year);
		mvBean.setOutlet_id(oid);
		mvBean.setYear(year);
		mvBean.setMonth(month);
		mvBean.setBrand_id(brand);
		mvBean.setDealer_id(did);
		System.out.println("dealer id is==="+did);
		request.setAttribute("outlet", encrypt_oid);
		request.setAttribute("dealer", encrypt_did);
		request.setAttribute("month", encrypt_month);
		request.setAttribute("yearr", encrypt_year);
	
		mvBean1=   mvDao.getShopperIdByOid(mvBean,oid,brand);
		String NonOscShopperId = mvBean1.getSk_shopper_id();
		String encrypted_shopper_id = Encryption.encrypt(NonOscShopperId);
		  request.setAttribute("NonOsc_shopper_id", encrypted_shopper_id); 
			
		  
			//MysteryShoppingVisitsBean getShopperid2 =   mvDao.getShopperIdByOid1(mvBean,did,brand);
			mvBean1 =   mvDao.getShopperIdByOid1(mvBean,did,brand);
			String shopper_Id= mvBean1.getOsc_shopper_id();
			String encrypted_OSCshopper_id = Encryption.encrypt(shopper_Id);
			System.out.println("This is Ecrypted SId"+encrypted_OSCshopper_id);
		  request.setAttribute("osc_shopper_id", encrypted_OSCshopper_id);
		  System.out.println("kashdjasjdasd"+mvBean1.getSk_shopper_id());
		  
		 
		  rDao.getNonOscScore(rBean,mvBean1.getSk_shopper_id(),oid,year,month,brand);
		  request.setAttribute("ytd", rBean.getNonosc_ytd_score());
		  request.setAttribute("outlet_score", rBean.getOutlet_score());
		  request.setAttribute("year", year);
		  rDao.getNonOscScorepoints(rBean,mvBean1.getSk_shopper_id());
		  request.setAttribute("max_points", rBean.getMax_points());
		  request.setAttribute("scored_points", rBean.getScored_points());
		
		  rDao.getnonshopperDetails(rBean,mvBean1.getSk_shopper_id());
		  request.setAttribute("brand_name", rBean.getBrand_name());
		  request.setAttribute("dealer_name", rBean.getDealer_name());
		  request.setAttribute("address", rBean.getOutlet_address());
		  request.setAttribute("outlet_id", rBean.getOutlet_id());
		  request.setAttribute("location", rBean.getLocation());
		  request.setAttribute("mode_of_contact", rBean.getMode_of_contact());
		  request.setAttribute("monthname", rBean.getMonth_name());
		  request.setAttribute("year", year);
		  
		  
		  rDao.getOscScore(rBean,mvBean1.getOsc_shopper_id(),did,year,month,brand);
		  request.setAttribute("osc_max_points", rBean.getOsc_max_points());
		  request.setAttribute("osc_scored_points", rBean.getOsc_scored_points());
		  request.setAttribute("osc_ytd", rBean.getOsc_ytd_score());
		  request.setAttribute("osc_outlet_score", rBean.getOsc_outlet_score());
		  request.setAttribute("year", year);
		  
		
		  
		 
		  System.out.println("before month111=======");
		  int mon=Integer.parseInt(month);
			int premonth=mon-1;
			System.out.println("before month222=======");
		  int monthNumber = premonth;
		  int year3 = Integer.parseInt(year);
		  int prevYear = year3;
		  if(monthNumber == 0) {
			  
			  monthNumber = 12;
			  int Currentyear = Calendar.getInstance().get(Calendar.YEAR);
				System.out.println("current year==="+Currentyear);
				 String year1=Encryption.decrypt(encrypt_year);
					int year2 = Integer.parseInt(year1);
					 prevYear = year2 - 1;
				System.out.println("prev Year==="+prevYear);
		  }
		  request.setAttribute("prevYear", prevYear);
		  String prevmon= Month.of(monthNumber).name().toLowerCase().substring(0, 1).toUpperCase() + Month.of(monthNumber).name().toLowerCase().substring(1);
		  request.setAttribute("pre_monthname",  prevmon);
		  
		  System.out.println("month222======="+prevmon);
		  	List<ReportsBean> getNonOscSection = rDao.getNonOscSection(rBean,mvBean1.getSk_shopper_id(),month,year,brand,oid);
		  
		  model.addObject("getNonOscSection", getNonOscSection);
		 
		  
		  
		  
		  List<ReportsBean> getOscSection = rDao.getOscSectionPercentage(rBean,mvBean1.getOsc_shopper_id(),month,year,brand,did);
		  
		  model.addObject("getOscSection", getOscSection);
		  
		  Date date = new SimpleDateFormat("MM/dd/yyyy").parse("02/04/2020");

		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);

		  int century = (calendar.get(Calendar.YEAR) / 100) +1;
		  
		  System.out.println("century==="+century);
		  System.out.println("hi============");
		  List<MysteryShoppingVisitsBean> getDealerPerformananceDetails = mvDao.getDealerPerformananceDetails(mvBean);
		  
		  /*********manoj d*****************/
		     /*
		      * Start working standards
		      */
		  
		/*  List<ReportsBean> getperformance1 = rDao.getDealerPerformance(rBean,mvBean, "1.2", "yes");
			model.addObject("workingstandard1", getperformance1);
			
			List<ReportsBean> getperformance2 = rDao.getDealerPerformance(rBean,mvBean, "1.3",
					"Within 4 hours after sending the request");
			model.addObject("workingstandard2", getperformance2);

			List<ReportsBean> getperformance3 = rDao.getDealerPerformance(rBean,mvBean, "1.5", "yes");
			model.addObject("workingstandard3", getperformance3);

			List<ReportsBean> getperformance4 = rDao.getDealerPerformance(rBean,mvBean, "1.6", "yes");
			model.addObject("workingstandard4", getperformance4);

			List<ReportsBean> getperformance5 = rDao.getDealerPerformance(rBean,mvBean, "4.5.1", "Yes");
			model.addObject("workingstandard5", getperformance5);

			List<ReportsBean> getperformance6 = rDao.getDealerPerformance(rBean,mvBean, "3.1", "Yes, actively offered");
			model.addObject("workingstandard6", getperformance6);

			List<ReportsBean> getperformance7 = rDao.getDealerPerformance(rBean,mvBean, "4.3",
					"Yes, on official paper sent per e-mail during/right after the consultation");
			model.addObject("workingstandard7", getperformance7);

			List<ReportsBean> getperformance8 = rDao.getDealerPerformance(rBean,mvBean, "4.2", "Yes");
			model.addObject("workingstandard8", getperformance8);

			List<ReportsBean> getperformance9 = rDao.getDealerPerformance(rBean,mvBean, "5.2", "Yes");
			model.addObject("workingstandard9", getperformance9); */
			/*
			 * End working standards
			 */
			
			/***
			 *Start success Factors
			 */

		/*	List<ReportsBean> successfactors1 = rDao.getDealerPerformance(rBean,mvBean, "1.3",
					"Not within the predefined time frame");
			model.addObject("successfactors1", successfactors1);

			List<ReportsBean> successfactors2 = rDao.getDealerPerformance(rBean,mvBean, "2.4", "");
			model.addObject("successfactors2", successfactors2);

			List<ReportsBean> successfactors3 = rDao.getDealerPerformance(rBean,mvBean, "3.1", "Yes, actively offered");
			model.addObject("successfactors3", successfactors3);

			List<ReportsBean> successfactors4 = rDao.getDealerPerformance(rBean,mvBean, "4.3",
					"Yes, on official paper sent per e-mail during/right after the consultation");
			model.addObject("successfactors4", successfactors4);

			List<ReportsBean> successfactors5 = rDao.getDealerPerformance(rBean,mvBean, "5.2", "Yes");
			model.addObject("successfactors5", successfactors5);

			List<ReportsBean> successfactors6 = rDao.getDealerPerformance(rBean,mvBean, "6.2", "Yes");
			model.addObject("successfactors6", successfactors6); */

			/***
			 *End success Factors
			 */
			
			/***
			 *  Start Retail Standards
			 */

		/*	List<ReportsBean> retailstandards1 = rDao.getDealerPerformance(rBean,mvBean, "2.1", "");
			model.addObject("retailstandards1", retailstandards1);

			List<ReportsBean> retailstandards2 = rDao.getDealerPerformance(rBean,mvBean, "2.4", "");
			model.addObject("retailstandards2", retailstandards2);

			List<ReportsBean> retailstandards3 = rDao.getDealerPerformance(rBean,mvBean, "2.6", "Yes, actively offered");
			model.addObject("retailstandards3", retailstandards3);

			List<ReportsBean> retailstandards4 = rDao.getDealerPerformance(rBean,mvBean, "3.1", "Yes, actively offered");
			model.addObject("retailstandards4", retailstandards4);

			List<ReportsBean> retailstandards5 = rDao.getDealerPerformance(rBean,mvBean, "3.2", "Yes");
			model.addObject("retailstandards5", retailstandards5);

			List<ReportsBean> retailstandards6 = rDao.getDealerPerformance(rBean,mvBean, "3.2a", "Yes, actively offered");
			model.addObject("retailstandards6", retailstandards6); */

			/***
			 *  End Retail Standards
			 */
		  /*********manoj d*****************/
			System.out.println("This is Ecrypted OSCSId"+encrypted_OSCshopper_id);
			System.out.println("This is Ecrypted SId"+encrypted_shopper_id);
			System.out.println("This is derypted Oid"+oid);
			System.out.println("This is derypted Did"+did);
		  model.addObject("getDealerPerformananceDetails", getDealerPerformananceDetails);
		  } catch (Exception e) {
				// TODO: handle exception
			  System.out.println("exception block"+e);
			  model = new ModelAndView("redirect:/500");
			}
		 return model;
	}
	
	@RequestMapping(value = "/performance3/{NonOsc_shopper_id}/{osc_shopper_id}/{dealer}/{pageid}")
	public @ResponseBody Object performance3(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean,QuestionnaireBean qBean,MysteryShoppingVisitsBean mvBean,@PathVariable String osc_shopper_id,@PathVariable String dealer,@PathVariable String NonOsc_shopper_id,@PathVariable int pageid) {
		 ModelAndView model = null;
		 
		 try {
			
		
			 
		 model = new ModelAndView("performance3");
		 
		 
		 int total = 5;
			if (pageid == 1) {
			} else {
				pageid = (pageid - 1) * total + 1;
			}
			request.setAttribute("pid", pageid);
			request.setAttribute("NoscShopper_id", NonOsc_shopper_id);
			request.setAttribute("shopper_id", osc_shopper_id);
			request.setAttribute("dealer", dealer);
			System.out.println("osc id in p3=="+NonOsc_shopper_id);
			System.out.println("non osc id in p3=="+osc_shopper_id);
		 List<QuestionnaireBean> questionsandoptions = qDao.questionsandoptions(qBean, pageid, total, osc_shopper_id,dealer,NonOsc_shopper_id);
		 model.addObject("questionsandoptions", questionsandoptions);
		 request.setAttribute("pageNumber", questionsandoptions.size());
			request.setAttribute("pageid", pageid);
		 } catch (Exception e) {
				// TODO: handle exception
			 model = new ModelAndView("redirect:/500");
			}
		return model;
	}
	@RequestMapping("/getIdss")
	public @ResponseBody Object questionCount(HttpServletRequest request, HttpServletResponse response, MysteryShoppingVisitsBean mvBean,QuestionnaireBean qBean) {
		System.out.println("dsfsdf======IN getIds");
		
		String encrypt_nonoscid = request.getParameter("nonOscId");
		String encrypt_oscId = request.getParameter("oscId");
		String nonoscid=Encryption.decrypt(encrypt_nonoscid);
		String oscId=Encryption.decrypt(encrypt_oscId);
		System.out.println("non oscid in getidss=="+nonoscid);
		System.out.println("oscid in getidss=="+oscId);
		List<QuestionnaireBean> questionsList = qDao.getIds(qBean, nonoscid,oscId);
		System.out.println("count data"+questionsList);
		return questionsList;
	}
	
	/*
	 * olr download start
	 * 
	 */
	@RequestMapping("/performance/{encrypt_oid}/{encrypt_month}/{encrypt_year}/{brand}/{encrypt_did}/download")
	public ModelAndView performancedownload(@PathVariable String encrypt_oid, @PathVariable String encrypt_year,
			@PathVariable String encrypt_month,@PathVariable String brand,@PathVariable String encrypt_did, ReportsBean rBean,MysteryShoppingVisitsBean mvBean, HttpServletRequest request, HttpServletResponse response, QuestionnaireBean qBean)
			throws ParseException {

		ModelAndView model = null;
		try {
			
		
		model = new ModelAndView("olrdownload");
		MysteryShoppingVisitsBean mvBean1= new MysteryShoppingVisitsBean();
		System.out.println("in performance contrlr===="+brand);
			/*
			 * mvBean.setOutlet_id(oid); mvBean.setYear(year); mvBean.setMonth(month);
			 * mvBean.setBrand_id(brand); mvBean.setDealer_id(did);
			 * System.out.println("dealer id is==="+did); request.setAttribute("outlet",
			 * oid); request.setAttribute("dealer", did); request.setAttribute("year",
			 * year); request.setAttribute("month", month); request.setAttribute("brand",
			 * brand);
			 */
		String oid=Encryption.decrypt(encrypt_oid);
		String did=Encryption.decrypt(encrypt_did);
		String month=Encryption.decrypt(encrypt_month);
		String year=Encryption.decrypt(encrypt_year);
		mvBean.setOutlet_id(oid);
		mvBean.setYear(year);
		mvBean.setMonth(month);
		mvBean.setBrand_id(brand);
		mvBean.setDealer_id(did);
		System.out.println("dealer id is==="+did);
		request.setAttribute("outlet", encrypt_oid);
		request.setAttribute("dealer", encrypt_did);
		request.setAttribute("month", encrypt_month);
		request.setAttribute("yearr", encrypt_year);
		  mvBean1=   mvDao.getShopperIdByOid(mvBean,oid,brand);
		  String NonOscShopperId = mvBean1.getSk_shopper_id();
			String encrypted_shopper_id = Encryption.encrypt(NonOscShopperId);
		  request.setAttribute("Nosc_shopper_id", encrypted_shopper_id);  
		  mvBean1=   mvDao.getShopperIdByOid1(mvBean,did,brand);
		  String shopper_Id= mvBean1.getOsc_shopper_id();
			String encrypted_OSCshopper_id = Encryption.encrypt(shopper_Id);
			System.out.println("This is Ecrypted SId"+encrypted_OSCshopper_id);
		  request.setAttribute("osc_shopper_id",encrypted_OSCshopper_id);
		  System.out.println("kashdjasjdasd"+mvBean1.getSk_shopper_id());
		  
		  rDao.getNonOscScore(rBean,mvBean1.getSk_shopper_id(),oid,year,month,brand);
		  request.setAttribute("ytd", rBean.getNonosc_ytd_score());
		  request.setAttribute("outlet_score", rBean.getOutlet_score());
		  request.setAttribute("year", year);
		  rDao.getNonOscScorepoints(rBean,mvBean1.getSk_shopper_id());
		  request.setAttribute("max_points", rBean.getMax_points());
		  request.setAttribute("scored_points", rBean.getScored_points());
		
		
		  rDao.getnonshopperDetails(rBean,mvBean1.getSk_shopper_id());
		  request.setAttribute("brand_name", rBean.getBrand_name());
		  request.setAttribute("dealer_name", rBean.getDealer_name());
		  request.setAttribute("address", rBean.getOutlet_address());
		  request.setAttribute("outlet_id", rBean.getOutlet_id());
		  request.setAttribute("location", rBean.getLocation());
		  request.setAttribute("mode_of_contact", rBean.getMode_of_contact());
		  request.setAttribute("monthname", rBean.getMonth_name());
		  request.setAttribute("year", year);
		  
		  
		  rDao.getOscScore(rBean,mvBean1.getOsc_shopper_id(),did,year,month,brand);
		  request.setAttribute("osc_max_points", rBean.getOsc_max_points());
		  request.setAttribute("osc_scored_points", rBean.getOsc_scored_points());
		  request.setAttribute("osc_ytd", rBean.getOsc_ytd_score());
		  request.setAttribute("osc_outlet_score", rBean.getOsc_outlet_score());
		  request.setAttribute("year", year);
		  
		
		  
		 
		  
		  //int mon=Integer.parseInt(month);
			//int premonth=mon-1;
		  //int monthNumber = premonth;
		  System.out.println("before month111=======");
		  int mon=Integer.parseInt(month);
			int premonth=mon-1;
			System.out.println("before month222=======");
		  int monthNumber = premonth;
		  int year3 = Integer.parseInt(year);
		  int prevYear = year3;
		  if(monthNumber == 0) {
			  
			  monthNumber = 12;
			  int Currentyear = Calendar.getInstance().get(Calendar.YEAR);
				System.out.println("current year==="+Currentyear);
				String year1=Encryption.decrypt(encrypt_year);
				int year2 = Integer.parseInt(year1);
				 prevYear = year2 - 1;
				System.out.println("prev Year==="+prevYear);
		  }
		  request.setAttribute("prevYear", prevYear);
		  String prevmon= Month.of(monthNumber).name().toLowerCase().substring(0, 1).toUpperCase() + Month.of(monthNumber).name().toLowerCase().substring(1);
		  request.setAttribute("pre_monthname",  prevmon);
		  
		  
		  	List<ReportsBean> getNonOscSection = rDao.getNonOscSection(rBean,mvBean1.getSk_shopper_id(),month,year,brand,oid);
		  
		  model.addObject("getNonOscSection", getNonOscSection);
		 
		  
		  
		  
		  List<ReportsBean> getOscSection = rDao.getOscSectionPercentage(rBean,mvBean1.getOsc_shopper_id(),month,year,brand,did);
		  
		  model.addObject("getOscSection", getOscSection);
		  
		  Date date = new SimpleDateFormat("MM/dd/yyyy").parse("02/04/2020");

		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);

		  int century = (calendar.get(Calendar.YEAR) / 100) +1;
		  
		  System.out.println("century==="+century);
		  List<MysteryShoppingVisitsBean> getDealerPerformananceDetails = mvDao.getDealerPerformananceDetails(mvBean);
		  
		  /*********manoj d*****************/
		     /*
		      * Start working standards
		      */
		  
		  List<ReportsBean> getperformance1 = rDao.getDealerPerformance(rBean,mvBean, "1.2", "yes");
			model.addObject("workingstandard1", getperformance1);
			
			List<ReportsBean> getperformance2 = rDao.getDealerPerformance(rBean,mvBean, "1.3",
					"Within 4 hours after sending the request");
			model.addObject("workingstandard2", getperformance2);

			List<ReportsBean> getperformance3 = rDao.getDealerPerformance(rBean,mvBean, "1.5", "yes");
			model.addObject("workingstandard3", getperformance3);

			List<ReportsBean> getperformance4 = rDao.getDealerPerformance(rBean,mvBean, "1.6", "yes");
			model.addObject("workingstandard4", getperformance4);

			List<ReportsBean> getperformance5 = rDao.getDealerPerformance(rBean,mvBean, "4.5.1", "Yes");
			model.addObject("workingstandard5", getperformance5);

			List<ReportsBean> getperformance6 = rDao.getDealerPerformance(rBean,mvBean, "3.1", "Yes, actively offered");
			model.addObject("workingstandard6", getperformance6);

			List<ReportsBean> getperformance7 = rDao.getDealerPerformance(rBean,mvBean, "4.3",
					"Yes, on official paper sent per e-mail during/right after the consultation");
			model.addObject("workingstandard7", getperformance7);

			List<ReportsBean> getperformance8 = rDao.getDealerPerformance(rBean,mvBean, "4.2", "Yes");
			model.addObject("workingstandard8", getperformance8);

			List<ReportsBean> getperformance9 = rDao.getDealerPerformance(rBean,mvBean, "5.2", "Yes");
			model.addObject("workingstandard9", getperformance9);
			/*
			 * End working standards
			 */
			
			/***
			 *Start success Factors
			 */

			List<ReportsBean> successfactors1 = rDao.getDealerPerformance(rBean,mvBean, "1.3",
					"Not within the predefined time frame");
			model.addObject("successfactors1", successfactors1);

			List<ReportsBean> successfactors2 = rDao.getDealerPerformance(rBean,mvBean, "2.4", "");
			model.addObject("successfactors2", successfactors2);

			List<ReportsBean> successfactors3 = rDao.getDealerPerformance(rBean,mvBean, "3.1", "Yes, actively offered");
			model.addObject("successfactors3", successfactors3);

			List<ReportsBean> successfactors4 = rDao.getDealerPerformance(rBean,mvBean, "4.3",
					"Yes, on official paper sent per e-mail during/right after the consultation");
			model.addObject("successfactors4", successfactors4);

			List<ReportsBean> successfactors5 = rDao.getDealerPerformance(rBean,mvBean, "5.2", "Yes");
			model.addObject("successfactors5", successfactors5);

			List<ReportsBean> successfactors6 = rDao.getDealerPerformance(rBean,mvBean, "6.2", "Yes");
			model.addObject("successfactors6", successfactors6);

			/***
			 *End success Factors
			 */
			
			/***
			 *  Start Retail Standards
			 */

			List<ReportsBean> retailstandards1 = rDao.getDealerPerformance(rBean,mvBean, "2.1", "");
			model.addObject("retailstandards1", retailstandards1);

			List<ReportsBean> retailstandards2 = rDao.getDealerPerformance(rBean,mvBean, "2.4", "");
			model.addObject("retailstandards2", retailstandards2);

			List<ReportsBean> retailstandards3 = rDao.getDealerPerformance(rBean,mvBean, "2.6", "Yes, actively offered");
			model.addObject("retailstandards3", retailstandards3);

			List<ReportsBean> retailstandards4 = rDao.getDealerPerformance(rBean,mvBean, "3.1", "Yes, actively offered");
			model.addObject("retailstandards4", retailstandards4);

			List<ReportsBean> retailstandards5 = rDao.getDealerPerformance(rBean,mvBean, "3.2", "Yes");
			model.addObject("retailstandards5", retailstandards5);

			List<ReportsBean> retailstandards6 = rDao.getDealerPerformance(rBean,mvBean, "3.2a", "Yes, actively offered");
			model.addObject("retailstandards6", retailstandards6);

			/***
			 *  End Retail Standards
			 */
		  /*********manoj d*****************/
		
		  model.addObject("getDealerPerformananceDetails", getDealerPerformananceDetails);
		  
		 List<QuestionnaireBean> questionsandoptions = qDao.questionsandoptions(qBean, mvBean1.getOsc_shopper_id(),did,mvBean1.getSk_shopper_id());
		 model.addObject("questionsandoptions", questionsandoptions);
		 request.setAttribute("pageNumber", questionsandoptions.size());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception in olr download"+e);
			model = new ModelAndView("redirect:/500");
		}
		return model;
	}
	/*
	 * olr download end
	 */
	/******Reports*******/
	
	
	
	
	
	@RequestMapping(value = "/getlowscoringdata")
	  public @ResponseBody Object getlowscoringdata(HttpServletRequest request, HttpServletResponse response,
	      MysteryShoppingVisitsBean mvBean) {
	    String year = request.getParameter("year");
	    String month = request.getParameter("month");
	    String dealer_id = request.getParameter("sk_dealer_id");
	    String outlet_id = request.getParameter("sk_outlet_id");
	    String region_id = request.getParameter("region_id");
	    String brand_id = request.getParameter("brand_id");
	    
	    List<MysteryShoppingVisitsBean> lowerScore = rDao.getLoweringScore1(mvBean,year,month,region_id,outlet_id,brand_id,dealer_id);
	    return lowerScore;
	  }
	/******Reports*******/
	@RequestMapping(value = "/getpaginglowscoringdata")
    public @ResponseBody Object getpaginglowscoringdata(HttpServletRequest request, HttpServletResponse response,
        MysteryShoppingVisitsBean mvBean) {
      String year = request.getParameter("year");
      String month = request.getParameter("month");
      String dealer_id = request.getParameter("sk_dealer_id");
      String outlet_id = request.getParameter("sk_outlet_id");
      String region_id = request.getParameter("region_id");
      String brand_id = request.getParameter("brand_id");
      String limit = request.getParameter("limit");
      String offset = request.getParameter("offset");
     
      List<MysteryShoppingVisitsBean> lowerScore = rDao.getLoweringScore1(mvBean,year,month,region_id,outlet_id,brand_id,dealer_id,limit,offset);
      return lowerScore;
    }
	
	/**
	 * exceptions controller
	 */
	@RequestMapping("/500")
	public ModelAndView model() {
		ModelAndView model = null;
		model = new ModelAndView("500");
		return model;

	}
	/*
	 * exceptions controller
	 */
	
	@RequestMapping(value = "/getoutletsforcompitation")
	public @ResponseBody Object getoutletsforcompitation(HttpServletRequest request, HttpServletResponse response,
			GraphBean gBean) {
		String did = request.getParameter("did");
		String rid = request.getParameter("region_id");
		List<GraphBean> getMonths = rDao.getoutletsforcompitation(gBean, did,rid);
		return getMonths;
	}
	
	@RequestMapping("ctanalysis")
	public ModelAndView ctanalysis(ReportsBean rBean,GraphBean gBean,HttpServletRequest request,RegionBean rgBean){
	ModelAndView mv=new ModelAndView();
	try {
		  mv=new ModelAndView("ctanalysis");
	HttpSession session = request.getSession(true);
	String role_for = (String) session.getAttribute("role_for");
	String dealers = (String) session.getAttribute("dealers");
	String roleId = (String) session.getAttribute("role_id");
	String region = (String) session.getAttribute("region");
	gBean.setRole_id(roleId);
	rgBean.setRegion_id(gBean.getRegion_id());
	gBean.setRegion_id(gBean.getRegion_id());
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	gBean.setMonth(gBean.getMonth());
	gBean.setBrand_id(gBean.getBrand_id());
	gBean.setRegion_id(gBean.getRegion_id());;
	gBean.setDealer_id(gBean.getDealer_id());
	gBean.setOutlet_id(gBean.getOutlet_id());
	request.setAttribute("brand_id", gBean.getBrand_id());
	request.setAttribute("month", gBean.getMonth());
	request.setAttribute("dealer_id", gBean.getDealer_id());
	request.setAttribute("outlet_id", gBean.getOutlet_id());
	request.setAttribute("region_id", gBean.getRegion_id());
	request.setAttribute("year", gBean.getYear());	
	List<GraphBean> BrandList = rDao.getBrands(gBean);
	mv.addObject("BrandList", BrandList);
	List<GraphBean> getMonths = rDao.getMonthsforconquestcontactFocus(gBean);
	mv.addObject("getMonths", getMonths);
	  List<RegionBean> activeRegionList = dbDao.getRegionList(rgBean,region,roleId);
	  mv.addObject("activeRegionList", activeRegionList); 
	  List<GraphBean> activedealersbyid = rDao.getDealers(gBean,gBean.getBrand_id(),region,dealers);
	  mv.addObject("activedealersbyid", activedealersbyid); 
	  List<GraphBean> activeoutletsbyid =rDao.getoutlets(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id());
	  mv.addObject("activeoutletsbyid", activeoutletsbyid);
	  Gson gson = new Gson(); 
	  List<GraphBean> promotorDectractorData =rDao.getpromotorDetractorData(gBean, gBean.getDealer_id(),gBean.getBrand_id(),gBean.getRegion_id(),gBean.getOutlet_id(),gBean.getMonth());
	  mv.addObject("promotorDectractorData", promotorDectractorData); 
	  String promotordata=promotorDectractorData.get(0).getPromotorCountpercentage();
	  String detractordata=promotorDectractorData.get(0).getDetractorCountpercentage();
	  String passivedata=promotorDectractorData.get(0).getPassiveCountpercentage();
	  String year=promotorDectractorData.get(0).getYear();
	  System.out.println("year in ct analysis"+year);
	  request.setAttribute("selected_year", year);
	  String promotorDectractorDatagson = gson.toJson(promotorDectractorData);
	  request.setAttribute("promotorDectractorDatagson", promotorDectractorDatagson);
	  
	}catch (Exception e) {
		  mv=new ModelAndView("redirect:/");
	}

	return mv;
	}
	
	
}