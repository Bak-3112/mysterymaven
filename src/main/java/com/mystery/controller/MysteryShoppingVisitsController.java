package com.mystery.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.mystery.api.AESCrypt;
import com.mystery.api.Encryption;
import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.MultipleFileUploadForm;
import com.mystery.beans.MysteryShoppingVisitsBean;
import com.mystery.beans.QuestionnaireBean;
import com.mystery.beans.ReportsBean;
import com.mystery.beans.UserBean;
import com.mystery.dao.DatabaseManagementDao;
import com.mystery.dao.HelperDao;
import com.mystery.dao.MysteryShoppingVisitsDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MysteryShoppingVisitsController {

	@Autowired
	DatabaseManagementDao dbDao;
	@Autowired
	MysteryShoppingVisitsDao mvDao;

	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	final String dashboardURL = resource.getString("dashboardURL");
	String emailfrom = resource.getString("emailfrom");
	String emailusername = resource.getString("emailusername");
	String emailpassword = resource.getString("emailpassword");
	String local_filepath = resource.getString("local_filepath");
	String uploaded_file_location = resource.getString("uploaded_file_location");

	@RequestMapping("scheduleMysteryShopper")
	public ModelAndView scheduleMysteryShopper(HttpServletRequest request, HttpServletResponse response, UserBean uBean,
			DatabaseManagementBean dbBean) {
		log.info("/scheduleMysteryShopper api" + new Gson().toJson(uBean));
		ModelAndView mv = new ModelAndView("schedulemysshopper");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		return mv;
	}

	@RequestMapping(value = "/getOutletsByBrandId")
	public @ResponseBody Object getOutletsByBrandId(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) {
		log.info("/getOutletsByBrandId api" + new Gson().toJson(mvBean));
		String brand_id = request.getParameter("brand_id");
		List<MysteryShoppingVisitsBean> outletList = mvDao.getOutletsByBrandId(mvBean, brand_id);
		return outletList;
	}

	@RequestMapping(value = "/getModelVariantById")
	public @ResponseBody Object getModelVariantById(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) {
		log.info("/getModelVariantById api" + new Gson().toJson(mvBean));
		String brand_id = request.getParameter("brand_id");
		List<MysteryShoppingVisitsBean> ModelVariantList = mvDao.getModelVariantById(mvBean, brand_id);
		return ModelVariantList;
	}

	@RequestMapping(value = "/scheduleMysteryShoppePost", method = RequestMethod.POST)
	public ModelAndView addscheduleMysteryShopper(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) throws IOException {
		log.info("/scheduleMysteryShoppePost api" + new Gson().toJson(mvBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewScheduledMysteryShoppers");
		HttpSession session = request.getSession(true);

		String user_id = (String) session.getAttribute("userId");
		String outlet_id = mvBean.getOutlet_id();
		MysteryShoppingVisitsBean mvBean2 = mvDao.getDealerByoutletId(outlet_id, mvBean);
		String dealer_id = mvBean2.getDealer_id();
		MysteryShoppingVisitsBean mvBean1 = mvDao.addscheduleMysteryShopper(mvBean, user_id, dealer_id);
		String shopper_id = mvBean1.getSk_shopper_id();
		String encrypted_shopper_id = Encryption.encrypt(shopper_id);
		/*
		 * try { encrypted_shopper_id = AESCrypt.encrypt(shopper_id);
		 * log.info("encrypted"+encrypted_shopper_id); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		mvDao.getscheduleMysteryShopperById(encrypted_shopper_id, mvBean);
		return model;

	}

	@RequestMapping("viewScheduledMysteryShoppers")
	public ModelAndView viewMysteryShoppers(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) {
		log.info("/viewScheduledMysteryShoppers api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("viewMysScheduled");
		List<MysteryShoppingVisitsBean> mysScheduleList = mvDao.getMysScheduledList(mvBean);
		mv.addObject("mysScheduleList", mysScheduleList);
		return mv;
	}

	@RequestMapping("editMysteryShopper/{sk_shopper_id}")
	public ModelAndView editMysteryShopper(@PathVariable String sk_shopper_id, HttpServletRequest request,
			HttpServletResponse response, MysteryShoppingVisitsBean mvBean, DatabaseManagementBean dbBean) {
		log.info("/editMysteryShopper/{sk_shopper_id} api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("editschedulemysshopper");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		mvDao.getShopperDetailsById(mvBean, sk_shopper_id);
		request.setAttribute("sk_shopper_id", mvBean.getSk_shopper_id());
		request.setAttribute("name", mvBean.getName());
		request.setAttribute("shoppers_car_owned_brand", mvBean.getShoppers_car_owned_brand());
		request.setAttribute("shoppers_car_owned", mvBean.getShoppers_car_owned());
		request.setAttribute("shoppers_car_yop", mvBean.getShoppers_car_yop());
		request.setAttribute("email", mvBean.getEmail());
		request.setAttribute("contact_number", mvBean.getContact_number());
		request.setAttribute("age", mvBean.getAge());
		request.setAttribute("gender", mvBean.getGender());
		request.setAttribute("quarter", mvBean.getQuarter());
		request.setAttribute("year", mvBean.getYear());
		request.setAttribute("mode_of_contact", mvBean.getMode_of_contact());
		request.setAttribute("visit_date", mvBean.getVisit_date());
		request.setAttribute("brand_id", mvBean.getBrand_id());
		request.setAttribute("brand_model_id", mvBean.getBrand_model_id());
		request.setAttribute("outlet_id", mvBean.getOutlet_id());
		request.setAttribute("variant_id", mvBean.getVariant_id());
		request.setAttribute("brand_name", mvBean.getBrand_name());
		request.setAttribute("model_name", mvBean.getModel_name());
		request.setAttribute("outlet_name", mvBean.getOutlet_name());
		request.setAttribute("dealer_name", mvBean.getDealer_name());
		request.setAttribute("variant_name", mvBean.getVariant_name());
		request.setAttribute("type", mvBean.getType());
		request.setAttribute("status", mvBean.getStatus());
		List<MysteryShoppingVisitsBean> outletList = mvDao.getOutletsByBrandId(mvBean, mvBean.getBrand_id());
		mv.addObject("outletList", outletList);
		List<MysteryShoppingVisitsBean> ModelVariantListByid = mvDao.getModelVariantById(mvBean, mvBean.getBrand_id());
		mv.addObject("ModelVariantListByid", ModelVariantListByid);
		return mv;

	}

	@RequestMapping(value = "/updateScheduleMysteryShopper", method = RequestMethod.POST)
	public ModelAndView updateScheduleMysteryShopper(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) throws IOException {
		log.info("/updateScheduleMysteryShopper api" + new Gson().toJson(mvBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewScheduledMysteryShoppers");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		String outlet_id = mvBean.getOutlet_id();
		MysteryShoppingVisitsBean mvBean2 = mvDao.getDealerByoutletId(outlet_id, mvBean);
		String dealer_id = mvBean2.getDealer_id();
		mvDao.updatescheduleMysteryShopper(mvBean, user_id, dealer_id);
		return model;

	}

	@RequestMapping("resendShopperEmail/{shopperId}")
	public ModelAndView resendShopperEmail(@PathVariable String shopperId, HttpServletRequest request,
			HttpServletResponse response, MysteryShoppingVisitsBean mvBean, DatabaseManagementBean dbBean) {
		log.info("/resendShopperEmail/{shopperId} api" + new Gson().toJson(mvBean));

		ModelAndView mv = new ModelAndView("redirect:/viewScheduledMysteryShoppers");
		String encrypted_shopper_id = Encryption.encrypt(shopperId);
		/*
		 * try { encrypted_shopper_id = AESCrypt.encrypt(shopperId);
		 * log.info("encrypted"+encrypted_shopper_id); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		mvDao.getscheduleMysteryShopperById(encrypted_shopper_id, mvBean);
		return mv;
	}

	@RequestMapping("deleteMysteryShopper/{shopperId}")
	public ModelAndView deleteMysteryShopper(@PathVariable String shopperId, HttpServletRequest request,
			HttpServletResponse response, MysteryShoppingVisitsBean mvBean) {
		log.info("/deleteMysteryShopper/{shopperId} api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("redirect:/viewScheduledMysteryShoppers");
		mvDao.deleteMysteryShopper(shopperId, mvBean);
		return mv;
	}

	/*
	 * Schedule MYS SHOPPERS Ends
	 */

	/*
	 * View MYS Visted Starts
	 */

	@RequestMapping("viewMysVisited")
	public ModelAndView viewMysVisited(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) {
		log.info("/viewMysVisited api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("viewMysVisited");
		List<MysteryShoppingVisitsBean> mysVisitedList = mvDao.getMysVisitedList(mvBean);
		mv.addObject("mysVisitedList", mysVisitedList);
		return mv;
	}

	@RequestMapping("/updatetoVisit/{sk_shopper_id}")
	public ModelAndView updatetoVisit(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String sk_shopper_id, MysteryShoppingVisitsBean mvBean) throws IOException {
		log.info("/updatetoVisit/{sk_shopper_id} api" + new Gson().toJson(mvBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewMysVisited");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		String visit_status = "visited";
		mvDao.updateToVisit(mvBean, visit_status, sk_shopper_id, user_id);
		return model;

	}

	@RequestMapping("/updatetoSchedule/{sk_shopper_id}")
	public ModelAndView updatetoSchedule(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String sk_shopper_id, MysteryShoppingVisitsBean mvBean) throws IOException {
		log.info("/updatetoSchedule/{sk_shopper_id} api" + new Gson().toJson(mvBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewScheduledMysteryShoppers");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		String visit_status = "scheduled";
		mvDao.updatetoSchedule(mvBean, visit_status, sk_shopper_id, user_id);
		return model;

	}

	@RequestMapping("/updatetoComplete/{sk_shopper_id}")
	public ModelAndView updatetoComplete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String sk_shopper_id, MysteryShoppingVisitsBean mvBean) throws IOException {
		log.info("/updatetoComplete/{sk_shopper_id} api" + new Gson().toJson(mvBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewMysCompleted");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		String visit_status = "completed";
		mvDao.updatetoComplete(mvBean, visit_status, sk_shopper_id, user_id);
		return model;

	}

	/*
	 * View MYS Visted Ends
	 */

	/*
	 * View MYS Completed Starts
	 */
	@RequestMapping("viewMysCompleted")
	public ModelAndView viewMysCompleted(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) {
		log.info("/viewMysCompleted api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("viewMysCompleted");
		List<MysteryShoppingVisitsBean> mysCompletedList = mvDao.getMysCompletedList(mvBean);
		mv.addObject("mysCompletedList", mysCompletedList);
		return mv;
	}

	@RequestMapping("viewDocuments/{sk_shopper_id}")
	public ModelAndView viewDocuments(@PathVariable String sk_shopper_id, MysteryShoppingVisitsBean mvBean,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("/viewDocuments/{sk_shopper_id} api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("getMysDocuments");
		List<MysteryShoppingVisitsBean> shopperDocumentList = mvDao.getShopperDocumentDetails(mvBean, sk_shopper_id);
		mv.addObject("shopperDocumentList", shopperDocumentList);
		request.setAttribute("sk_shopper_id", sk_shopper_id);
		request.setAttribute("uploaded_file_location", uploaded_file_location);
		return mv;
	}

	@RequestMapping("getMysQuestionnaire/{sk_shopper_id}")
	public ModelAndView getMysQuestionnaire(@PathVariable String sk_shopper_id, MysteryShoppingVisitsBean mvBean,
			HttpServletRequest request, HttpServletResponse response) throws ParseException {
		log.info("/getMysQuestionnaire/{sk_shopper_id} api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("getMysQuestionnaire");
		mvDao.getMysShopperLinkDetailsById(mvBean, sk_shopper_id);
		String datee = mvBean.getVisit_date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = sdf.parse(datee);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		String dayNames[] = new DateFormatSymbols().getWeekdays();
		String visit_day = dayNames[day];
		// String visit_day =
		// LocalDate.parse(datee).getDayOfWeek().getDisplayName(TextStyle.FULL,
		// Locale.US);
		request.setAttribute("sk_shopper_id", mvBean.getSk_shopper_id());
		request.setAttribute("name", mvBean.getName());
		request.setAttribute("shoppers_car_owned", mvBean.getShoppers_car_owned());
		request.setAttribute("shoppers_car_owned_brand", mvBean.getShoppers_car_owned_brand());
		request.setAttribute("shoppers_car_yop", mvBean.getShoppers_car_yop());
		request.setAttribute("email", mvBean.getEmail());
		request.setAttribute("contact_number", mvBean.getContact_number());
		request.setAttribute("age", mvBean.getAge());
		request.setAttribute("gender", mvBean.getGender());
		request.setAttribute("quarter", mvBean.getQuarter());
		request.setAttribute("year", mvBean.getYear());
		request.setAttribute("mode_of_contact", mvBean.getMode_of_contact());
		request.setAttribute("visit_date", mvBean.getVisit_date());
		request.setAttribute("visit_day", visit_day);
		request.setAttribute("calenderweek", week);
		request.setAttribute("brand_id", mvBean.getBrand_id());
		request.setAttribute("outlet_id", mvBean.getOutlet_id());
		request.setAttribute("dealer_id", mvBean.getDealer_id());
		request.setAttribute("brand_model_variant_id", mvBean.getVariant_id());
		request.setAttribute("brand_name", mvBean.getBrand_name());
		request.setAttribute("model_name", mvBean.getModel_name());
		request.setAttribute("dealer_name", mvBean.getDealer_name());
		request.setAttribute("variant_name", mvBean.getVariant_name());
		request.setAttribute("outlet_name", mvBean.getOutlet_name());
		request.setAttribute("outlet_location_id", mvBean.getOutlet_location_id());
		request.setAttribute("pincode", mvBean.getPincode());
		request.setAttribute("state_name", mvBean.getState_name());
		request.setAttribute("city_name", mvBean.getCity_name());
		request.setAttribute("start_time", mvBean.getStart_time());
		request.setAttribute("end_time", mvBean.getEnd_time());
		request.setAttribute("met_sc", mvBean.getMet_sc());
		try {
			if (mvBean.getMet_sc().equals("yes")) {
				request.setAttribute("sc_name", mvBean.getSc_name());
				request.setAttribute("sc_designation", mvBean.getSc_designation());
				request.setAttribute("sc_description", mvBean.getSc_description());
			} else {
				request.setAttribute("sc_name", "");
				request.setAttribute("sc_designation", "");
				request.setAttribute("sc_description", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception /getMysQuestionnaire/{sk_shopper_id} api" + e);
			// TODO: handle exception
		}
		request.setAttribute("shopper_link_name", mvBean.getShopper_link_name());
		request.setAttribute("shopper_link_age", mvBean.getShopper_link_age());
		request.setAttribute("shopper_link_email", mvBean.getShopper_link_email());
		request.setAttribute("shopper_link_contact", mvBean.getShopper_link_contact());
		request.setAttribute("shopper_link_gender", mvBean.getShopper_link_gender());
		request.setAttribute("shoppers_link_shoppersCarBrand", mvBean.getShoppers_link_shoppersCarBrand());
		request.setAttribute("shoppers_link_shoppersCarModel", mvBean.getShoppers_link_shoppersCarModel());
		request.setAttribute("shoppers_link_shoppersCarYop", mvBean.getShoppers_car_yop());
		request.setAttribute("Shopper_link_productMet", mvBean.getShopper_link_productMet());
		request.setAttribute("Shopper_link_productNameProvided", mvBean.getShopper_link_productNameProvided());
		request.setAttribute("Shopper_link_productName", mvBean.getShopper_link_productName());
		request.setAttribute("Shopper_link_productDesc", mvBean.getShopper_link_productDesc());
		request.setAttribute("shopper_link_visit_date", mvBean.getShopper_link_visit_date());
		request.setAttribute("type", mvBean.getType());
		request.setAttribute("visit_status", mvBean.getShopper_link_visit_date());
		List<MysteryShoppingVisitsBean> mysQuestionstatus = mvDao.getmysQuestionstatus(mvBean, sk_shopper_id);
		mv.addObject("mysQuestionstatus", mysQuestionstatus);

		return mv;
	}

	@RequestMapping("editMysteryShopperLinkDetails/{sk_shopper_id}")
	public ModelAndView editMysteryShopperDetails(@PathVariable String sk_shopper_id, MysteryShoppingVisitsBean mvBean,
			DatabaseManagementBean dbBean, HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		ModelAndView mv = new ModelAndView("editShopperLinkVisitsDetails");
		log.info("/editMysteryShopperLinkDetails/{sk_shopper_id} api" + new Gson().toJson(mvBean));
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		mvDao.getMysShopperLinkDetailsById(mvBean, sk_shopper_id);
		String datee = mvBean.getVisit_date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = sdf.parse(datee);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		String dayNames[] = new DateFormatSymbols().getWeekdays();
		String visit_day = dayNames[day];
		// String visit_day =
		// LocalDate.parse(datee).getDayOfWeek().getDisplayName(TextStyle.FULL,
		// Locale.US);

		request.setAttribute("sk_shopper_id", mvBean.getSk_shopper_id());
		request.setAttribute("name", mvBean.getName());
		request.setAttribute("shoppers_car_owned", mvBean.getShoppers_car_owned());
		request.setAttribute("shoppers_car_owned_brand", mvBean.getShoppers_car_owned_brand());
		request.setAttribute("shoppers_car_yop", mvBean.getShoppers_car_yop());
		request.setAttribute("email", mvBean.getEmail());
		request.setAttribute("contact_number", mvBean.getContact_number());
		request.setAttribute("age", mvBean.getAge());
		request.setAttribute("gender", mvBean.getGender());
		request.setAttribute("quarter", mvBean.getQuarter());
		request.setAttribute("year", mvBean.getYear());
		request.setAttribute("mode_of_contact", mvBean.getMode_of_contact());
		request.setAttribute("visit_date", mvBean.getVisit_date());
		request.setAttribute("visit_day", visit_day);
		request.setAttribute("calenderweek", week);
		request.setAttribute("brand_id", mvBean.getBrand_id());
		request.setAttribute("outlet_id", mvBean.getOutlet_id());
		request.setAttribute("dealer_id", mvBean.getDealer_id());
		request.setAttribute("brand_model_variant_id", mvBean.getVariant_id());
		// log.info("brandmodelvariant"+mvBean.getVariant_id());
		request.setAttribute("brand_name", mvBean.getBrand_name());
		request.setAttribute("model_name", mvBean.getModel_name());
		request.setAttribute("dealer_name", mvBean.getDealer_name());
		request.setAttribute("variant_name", mvBean.getVariant_name());
		request.setAttribute("outlet_name", mvBean.getOutlet_name());
		request.setAttribute("outlet_location_id", mvBean.getOutlet_location_id());
		request.setAttribute("pincode", mvBean.getPincode());
		request.setAttribute("state_name", mvBean.getState_name());
		request.setAttribute("city_name", mvBean.getCity_name());
		request.setAttribute("start_time", mvBean.getStart_time());
		request.setAttribute("end_time", mvBean.getEnd_time());
		request.setAttribute("met_sc", mvBean.getMet_sc());
		try {
			if (mvBean.getMet_sc().equals("yes")) {
				request.setAttribute("sc_name", mvBean.getSc_name());
				request.setAttribute("sc_designation", mvBean.getSc_designation());
				request.setAttribute("sc_description", mvBean.getSc_description());
			} else {
				request.setAttribute("sc_name", "");
				request.setAttribute("sc_designation", "");
				request.setAttribute("sc_description", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception /editMysteryShopperLinkDetails/{sk_shopper_id} api" + e);
		}

		request.setAttribute("shopper_link_name", mvBean.getShopper_link_name());
		request.setAttribute("shopper_link_age", mvBean.getShopper_link_age());
		request.setAttribute("shopper_link_email", mvBean.getShopper_link_email());
		request.setAttribute("shopper_link_contact", mvBean.getShopper_link_contact());
		request.setAttribute("shopper_link_gender", mvBean.getShopper_link_gender());
		request.setAttribute("shoppers_link_shoppersCarBrand", mvBean.getShoppers_link_shoppersCarBrand());
		request.setAttribute("shoppers_link_shoppersCarModel", mvBean.getShoppers_link_shoppersCarModel());
		request.setAttribute("shoppers_link_shoppersCarYop", mvBean.getShoppers_car_yop());
		request.setAttribute("Shopper_link_productMet", mvBean.getShopper_link_productMet());
		request.setAttribute("Shopper_link_productNameProvided", mvBean.getShopper_link_productNameProvided());
		request.setAttribute("Shopper_link_productName", mvBean.getShopper_link_productName());
		request.setAttribute("Shopper_link_productDesc", mvBean.getShopper_link_productDesc());
		request.setAttribute("shopper_link_visit_date", mvBean.getShopper_link_visit_date());
		request.setAttribute("type", mvBean.getType());
		request.setAttribute("visit_status", mvBean.getVisit_status());
		List<MysteryShoppingVisitsBean> outletList = mvDao.getOutletsByBrandId(mvBean, mvBean.getBrand_id());
		mv.addObject("outletList", outletList);
		List<MysteryShoppingVisitsBean> ModelVariantListByid = mvDao.getModelVariantById(mvBean, mvBean.getBrand_id());
		mv.addObject("ModelVariantListByid", ModelVariantListByid);
		return mv;
	}

	@RequestMapping(value = "/updateMysteryShopperLinkDetails", method = RequestMethod.POST)
	public ModelAndView updateMysteryShopperLinkDetails(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) throws IOException {
		log.info("/updateMysteryShopperLinkDetails api" + new Gson().toJson(mvBean));
		ModelAndView model = null;
		String sk_shopper_id = mvBean.getSk_shopper_id();
		model = new ModelAndView("redirect:/getMysQuestionnaire/" + sk_shopper_id + "");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		String outlet_id = mvBean.getOutlet_id();
		MysteryShoppingVisitsBean mvBean2 = mvDao.getDealerByoutletId(outlet_id, mvBean);
		String dealer_id = mvBean2.getDealer_id();
		mvDao.updateMysteryShopperLinkDetails(mvBean, user_id, dealer_id);
		return model;

	}

	@RequestMapping(value = "/submitReview", method = RequestMethod.POST)
	public ModelAndView submitReview(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mBean, RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView model = null;
		log.info("/submitReview api" + new Gson().toJson(mBean));
		String sk_shopper_id = mBean.getSk_shopper_id();
		// model = new ModelAndView("redirect:/getMYSsecondphase/" + sk_shopper_id +
		// "");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		String visit_status = "reviewed";
		mvDao.checkShopperAnswerStatus(mBean, sk_shopper_id);
		String not_answered_status_count = mBean.getNot_answered_status_count();
		if (!not_answered_status_count.equals("0")) {
			mBean.setStatus("Please answer all questions");
			model = new ModelAndView("redirect:/getMysQuestionnaire/" + sk_shopper_id + "");

			redirectAttributes.addFlashAttribute("statusMessage", mBean.getStatus());
			redirectAttributes.addFlashAttribute("statusCode", not_answered_status_count);

		}

		else if (not_answered_status_count.equals("0")) {
			mBean.setStatus("you have answere all the questions");
			model = new ModelAndView("redirect:/getMYSsecondphase/" + sk_shopper_id + "");

			redirectAttributes.addFlashAttribute("statusMessage", mBean.getStatus());
			redirectAttributes.addFlashAttribute("statusCode", not_answered_status_count);
			mvDao.submitReview(mBean, visit_status, sk_shopper_id, user_id);
			mvDao.getMaxScorePointsForReview(mBean, sk_shopper_id);
		}

		// mvDao.submitReview(mBean,visit_status,sk_shopper_id,user_id);
		// mvDao.getMaxScorePointsForReview(mBean,sk_shopper_id);

		return model;

	}

	@RequestMapping(value = "/submitReview2", method = RequestMethod.POST)
	public ModelAndView submitReview2(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mBean) throws IOException {
		log.info("/submitReview2 api" + new Gson().toJson(mBean));
		ModelAndView model = null;
		String sk_shopper_id = mBean.getSk_shopper_id();
		model = new ModelAndView("redirect:/getMysQuestionnaire/" + sk_shopper_id + "");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		String visit_status = "completed";
		mvDao.submitReview2(mBean, visit_status, sk_shopper_id, user_id);

		return model;

	}

	@RequestMapping("getMYSsecondphase//{sk_shopper_id}")
	public ModelAndView getMYSsecondphase(@PathVariable String sk_shopper_id, HttpServletRequest request,
			HttpServletResponse response, MysteryShoppingVisitsBean mvBean) throws ParseException {
		log.info("/getMYSsecondphase//{sk_shopper_id} api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("getMysSecondphase");
		// request.setAttribute("sk_shopper_id", sk_shopper_id);
		mvDao.getMysShopperLinkDetailsById(mvBean, sk_shopper_id);
		String datee = mvBean.getVisit_date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = sdf.parse(datee);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		String dayNames[] = new DateFormatSymbols().getWeekdays();
		String visit_day = dayNames[day];
		// String visit_day =
		// LocalDate.parse(datee).getDayOfWeek().getDisplayName(TextStyle.FULL,
		// Locale.US);

		request.setAttribute("sk_shopper_id", mvBean.getSk_shopper_id());
		request.setAttribute("name", mvBean.getName());
		request.setAttribute("shoppers_car_owned", mvBean.getShoppers_car_owned());
		request.setAttribute("shoppers_car_owned_brand", mvBean.getShoppers_car_owned_brand());
		request.setAttribute("shoppers_car_yop", mvBean.getShoppers_car_yop());
		request.setAttribute("email", mvBean.getEmail());
		request.setAttribute("contact_number", mvBean.getContact_number());
		request.setAttribute("age", mvBean.getAge());
		request.setAttribute("gender", mvBean.getGender());
		request.setAttribute("quarter", mvBean.getQuarter());
		request.setAttribute("year", mvBean.getYear());
		request.setAttribute("mode_of_contact", mvBean.getMode_of_contact());
		request.setAttribute("visit_date", mvBean.getVisit_date());
		request.setAttribute("visit_day", visit_day);
		request.setAttribute("calenderweek", week);
		request.setAttribute("brand_id", mvBean.getBrand_id());
		request.setAttribute("outlet_id", mvBean.getOutlet_id());
		request.setAttribute("dealer_id", mvBean.getDealer_id());
		request.setAttribute("brand_model_variant_id", mvBean.getVariant_id());
		request.setAttribute("brand_name", mvBean.getBrand_name());
		request.setAttribute("model_name", mvBean.getModel_name());
		request.setAttribute("dealer_name", mvBean.getDealer_name());
		request.setAttribute("variant_name", mvBean.getVariant_name());
		request.setAttribute("outlet_name", mvBean.getOutlet_name());
		request.setAttribute("outlet_location_id", mvBean.getOutlet_location_id());
		request.setAttribute("pincode", mvBean.getPincode());
		request.setAttribute("state_name", mvBean.getState_name());
		request.setAttribute("city_name", mvBean.getCity_name());
		request.setAttribute("start_time", mvBean.getStart_time());
		request.setAttribute("end_time", mvBean.getEnd_time());
		request.setAttribute("met_sc", mvBean.getMet_sc());
		try {
			if (mvBean.getMet_sc().equals("yes")) {
				request.setAttribute("sc_name", mvBean.getSc_name());
				request.setAttribute("sc_designation", mvBean.getSc_designation());
				request.setAttribute("sc_description", mvBean.getSc_description());
			} else {
				request.setAttribute("sc_name", "");
				request.setAttribute("sc_designation", "");
				request.setAttribute("sc_description", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception /getMYSsecondphase//{sk_shopper_id} api" + e);
		}
		request.setAttribute("shopper_link_name", mvBean.getShopper_link_name());
		request.setAttribute("shopper_link_age", mvBean.getShopper_link_age());
		request.setAttribute("shopper_link_email", mvBean.getShopper_link_email());
		request.setAttribute("shopper_link_contact", mvBean.getShopper_link_contact());
		request.setAttribute("shopper_link_gender", mvBean.getShopper_link_gender());
		request.setAttribute("shoppers_link_shoppersCarBrand", mvBean.getShoppers_link_shoppersCarBrand());
		request.setAttribute("shoppers_link_shoppersCarModel", mvBean.getShoppers_link_shoppersCarModel());
		request.setAttribute("shoppers_link_shoppersCarYop", mvBean.getShoppers_car_yop());
		request.setAttribute("Shopper_link_productMet", mvBean.getShopper_link_productMet());
		request.setAttribute("Shopper_link_productNameProvided", mvBean.getShopper_link_productNameProvided());
		request.setAttribute("Shopper_link_productName", mvBean.getShopper_link_productName());
		request.setAttribute("Shopper_link_productDesc", mvBean.getShopper_link_productDesc());
		request.setAttribute("shopper_link_visit_date", mvBean.getShopper_link_visit_date());
		request.setAttribute("type", mvBean.getType());
		request.setAttribute("visit_status", mvBean.getShopper_link_visit_date());
		List<MysteryShoppingVisitsBean> QualityAssuranceQuestionstatus = mvDao.getmysQuestionstatus(mvBean,
				sk_shopper_id);
		mv.addObject("QualityAssuranceQuestionstatus", QualityAssuranceQuestionstatus);
		return mv;
	}

	@RequestMapping("getMysScore/{sk_shopper_id}")
	public ModelAndView getMysScore(@PathVariable String sk_shopper_id, HttpServletRequest request,
			HttpServletResponse response, MysteryShoppingVisitsBean mvBean) throws ParseException {
		ModelAndView mv = new ModelAndView("MysScore");
		request.setAttribute("sk_shopper_id", sk_shopper_id);
		log.info("/getMysScore/{sk_shopper_id} api" + new Gson().toJson(mvBean));
		mvDao.getMysShopperLinkDetailsById(mvBean, sk_shopper_id);
		String datee = mvBean.getVisit_date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = sdf.parse(datee);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		String dayNames[] = new DateFormatSymbols().getWeekdays();
		String visit_day = dayNames[day];
		// String visit_day =
		// LocalDate.parse(datee).getDayOfWeek().getDisplayName(TextStyle.FULL,
		// Locale.US);

		// request.setAttribute("sk_shopper_id", mvBean.getSk_shopper_id());
		request.setAttribute("name", mvBean.getName());
		request.setAttribute("shoppers_car_owned", mvBean.getShoppers_car_owned());
		request.setAttribute("shoppers_car_owned_brand", mvBean.getShoppers_car_owned_brand());
		request.setAttribute("shoppers_car_yop", mvBean.getShoppers_car_yop());
		request.setAttribute("email", mvBean.getEmail());
		request.setAttribute("contact_number", mvBean.getContact_number());
		request.setAttribute("age", mvBean.getAge());
		request.setAttribute("gender", mvBean.getGender());
		request.setAttribute("quarter", mvBean.getQuarter());
		request.setAttribute("year", mvBean.getYear());
		request.setAttribute("mode_of_contact", mvBean.getMode_of_contact());
		request.setAttribute("visit_date", mvBean.getVisit_date());
		request.setAttribute("visit_day", visit_day);
		request.setAttribute("calenderweek", week);
		request.setAttribute("brand_id", mvBean.getBrand_id());
		request.setAttribute("outlet_id", mvBean.getOutlet_id());
		request.setAttribute("dealer_id", mvBean.getDealer_id());
		request.setAttribute("brand_model_variant_id", mvBean.getVariant_id());
		request.setAttribute("brand_name", mvBean.getBrand_name());
		request.setAttribute("model_name", mvBean.getModel_name());
		request.setAttribute("dealer_name", mvBean.getDealer_name());
		request.setAttribute("variant_name", mvBean.getVariant_name());
		request.setAttribute("outlet_name", mvBean.getOutlet_name());
		request.setAttribute("outlet_location_id", mvBean.getOutlet_location_id());
		request.setAttribute("pincode", mvBean.getPincode());
		request.setAttribute("state_name", mvBean.getState_name());
		request.setAttribute("city_name", mvBean.getCity_name());
		request.setAttribute("start_time", mvBean.getStart_time());
		request.setAttribute("end_time", mvBean.getEnd_time());
		request.setAttribute("met_sc", mvBean.getMet_sc());
		try {
			if (mvBean.getMet_sc().equals("yes")) {
				request.setAttribute("sc_name", mvBean.getSc_name());
				request.setAttribute("sc_designation", mvBean.getSc_designation());
				request.setAttribute("sc_description", mvBean.getSc_description());
			} else {
				request.setAttribute("sc_name", "");
				request.setAttribute("sc_designation", "");
				request.setAttribute("sc_description", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception /getMysScore/{sk_shopper_id} api" + e);
		}
		request.setAttribute("shopper_link_name", mvBean.getShopper_link_name());
		request.setAttribute("shopper_link_age", mvBean.getShopper_link_age());
		request.setAttribute("shopper_link_email", mvBean.getShopper_link_email());
		request.setAttribute("shopper_link_contact", mvBean.getShopper_link_contact());
		request.setAttribute("shopper_link_gender", mvBean.getShopper_link_gender());
		request.setAttribute("shoppers_link_shoppersCarBrand", mvBean.getShoppers_link_shoppersCarBrand());
		request.setAttribute("shoppers_link_shoppersCarModel", mvBean.getShoppers_link_shoppersCarModel());
		request.setAttribute("shoppers_link_shoppersCarYop", mvBean.getShoppers_car_yop());
		request.setAttribute("Shopper_link_productMet", mvBean.getShopper_link_productMet());
		request.setAttribute("Shopper_link_productNameProvided", mvBean.getShopper_link_productNameProvided());
		request.setAttribute("Shopper_link_productName", mvBean.getShopper_link_productName());
		request.setAttribute("Shopper_link_productDesc", mvBean.getShopper_link_productDesc());
		request.setAttribute("shopper_link_visit_date", mvBean.getShopper_link_visit_date());
		request.setAttribute("type", mvBean.getType());
		request.setAttribute("visit_status", mvBean.getShopper_link_visit_date());
		List<MysteryShoppingVisitsBean> scoreTableData = mvDao.getScoreTabTableData(mvBean, sk_shopper_id);
		request.setAttribute("scoreTableData", scoreTableData);

		String overAllScore = scoreTableData.get(1).getOverAllScore();
		request.setAttribute("overAllScore", overAllScore);
		return mv;
	}

	@RequestMapping("viewquestions")
	public ModelAndView viewquestions(HttpServletRequest request, HttpServletResponse response, UserBean uBean) {
		log.info("/viewquestions api" + new Gson().toJson(uBean));
		ModelAndView mv = new ModelAndView("reviewQuestions");
		return mv;
	}

	/*
	 * View MYS Completed Ends
	 */

	/*
	 * View MYS Closed Starts
	 */

	@RequestMapping("viewMysClosed")
	public ModelAndView viewMysClosed(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean, DatabaseManagementBean dbBean) {
		log.info("/viewMysClosed api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("viewMysClosed");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		return mv;
	}

	@RequestMapping(value = "/getYear")
	public @ResponseBody Object getYear(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) {
		log.info("/getYear api" + new Gson().toJson(mvBean));
		String brand_id = request.getParameter("brand_id");
		List<MysteryShoppingVisitsBean> YearList = mvDao.getYear(mvBean, brand_id);
		return YearList;
	}

	@RequestMapping(value = "/viewMysPublished", method = RequestMethod.POST)
	public ModelAndView viewMysPublished(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean, DatabaseManagementBean dbBean) throws IOException {
		log.info("/viewMysPublished api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("viewMysClosed");
		List<MysteryShoppingVisitsBean> mysPublishedList = mvDao.getMysPublishedDetails(mvBean);
		mv.addObject("mysPublishedList", mysPublishedList);
		// mv = new ModelAndView("redirect:/viewMysClosed");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		request.setAttribute("mode_of_contact", mvBean.getMode_of_contact());
		request.setAttribute("month", mvBean.getMonth());
		request.setAttribute("year", mvBean.getYear());
		log.info("year in publish" + mvBean.getYear());
		request.setAttribute("brand_id", mvBean.getBrand_id());

		return mv;

	}

	/*
	 * View MYS Closed End
	 */

	/*
	 * display shopper Details
	 */

	@RequestMapping("viewMysteryUnique")
	public ModelAndView viewMysteryUnique(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) {
		log.info("/viewMysteryUnique api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("viewMysScheduledUnique");
		List<MysteryShoppingVisitsBean> mysScheduleList = mvDao.getMysteryShoppersUniqueList(mvBean, "Scheduled");
		mv.addObject("mysScheduleList", mysScheduleList);
		return mv;
	}

	@RequestMapping("viewMysteryOutlets/{mobile}")
	public ModelAndView viewMysteryOutlets(@PathVariable String mobile, HttpServletRequest request,
			HttpServletResponse response, MysteryShoppingVisitsBean mvBean) {
		log.info("/viewMysteryOutlets/{mobile} api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("viewMysScheduledOutlets");
		List<MysteryShoppingVisitsBean> getShopperList = mvDao.getShopperVisitedDetailsById(mvBean, mobile);
		mv.addObject("getShopperList", getShopperList);
		return mv;
	}

	@RequestMapping("upload/{sid}")
	public ModelAndView upload(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) throws Exception {
		log.info("/upload/{sid} api" + new Gson().toJson(mvBean));

		ModelAndView mv = null;
		String encrypted_shooper_id = sid;
		String decrypted_shooper_id = Encryption.decrypt(encrypted_shooper_id);
		request.setAttribute("shopper_id", encrypted_shooper_id);
		try {
			mvDao.checkshopperlinkstatus(mvBean, decrypted_shooper_id);
			mv = new ModelAndView("expiredpage");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception /upload/{sid} api" + e);
			mv = new ModelAndView("upload");
		}
		return mv;
	}

	@RequestMapping("expiredpage")
	public ModelAndView expirepage(@PathVariable String sid, MysteryShoppingVisitsBean mBean) {
		log.info("/expiredpage api" + new Gson().toJson(mBean));
		ModelAndView mv = new ModelAndView("expiredpage");
		String decrypted_shooper_id = Encryption.decrypt(sid);
		mBean.setSk_shopper_id(decrypted_shooper_id);
		return mv;
	}

	@RequestMapping("document/{sid}")
	public ModelAndView document(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) throws Exception {
		log.info("/document/{sid} api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("uploaddocuments");
		String decrypted_shooper_id = Encryption.decrypt(sid);
		request.setAttribute("sk_shopper_id", decrypted_shooper_id);
		request.setAttribute("sid", sid);
		mvDao.getShopperDetailsById(mvBean, decrypted_shooper_id);
		request.setAttribute("name", mvBean.getName());
		// mvDao.getShopperDocumentCount(mvBean,decrypted_shooper_id);
		// request.setAttribute("shopper_count", mvBean.getShopper_document_count());
		return mv;
	}

	@RequestMapping(value = "/saveDocument", method = RequestMethod.POST)
	public ModelAndView addDocument(
			@ModelAttribute("multipleFileUploadForm") MultipleFileUploadForm multipleFileUploadForm,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			MysteryShoppingVisitsBean mvBean, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException {
		log.info("/saveDocument api" + new Gson().toJson(mvBean));
		ModelAndView model = null;
		String sk_shopper_id = mvBean.getSk_shopper_id();
		String encrypted_shopper_id = mvBean.getSid();
		model = new ModelAndView("redirect:/document/" + encrypted_shopper_id + "");
		mvDao.getShopperDetailsById(mvBean, sk_shopper_id);
		String quarter = mvBean.getQuarter();
		String year = mvBean.getYear();
		String brand = mvBean.getBrand_name();
		String shopperFolderName = "mys" + "_" + quarter + "_" + year + "_" + brand + "_" + sk_shopper_id;
		List<MultipartFile> files = multipleFileUploadForm.getFiles();
		List<MysteryShoppingVisitsBean> documentType = mvBean.getDocumentType();

		try {
			String file = "";
			List<String> fileNames = new ArrayList<String>();
			for (int i = 0; i < files.size(); i++) {
				try {
					file = files.get(i).getOriginalFilename();
					if (!files.get(i).getOriginalFilename().isEmpty()) {
						log.info("file name" + file);
						fileNames.add(file);

						File directory = new File(local_filepath + "documents/" + shopperFolderName);
						if (!directory.exists()) {
							directory.mkdir();
						}
						String filePath = local_filepath + "documents/" + shopperFolderName + "/";
						files.get(i).transferTo(new File(filePath + file));
					} else {
						file = "";
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.info("upload first exception" + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("upload second exception" + e);
		}

		mvDao.addMysDocuments(mvBean, documentType, files, sk_shopper_id, shopperFolderName);
		mvDao.getShopperDocumentCount(mvBean, sk_shopper_id);
		redirectAttributes.addFlashAttribute("shopper_count", mvBean.getShopper_document_count());

		return model;

	}

	@RequestMapping("shopperDetails/{sid}")
	public ModelAndView shopperDetails(@PathVariable String sid, MysteryShoppingVisitsBean mBean,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("/shopperDetails/{sid} api" + new Gson().toJson(mBean));
		ModelAndView mv = new ModelAndView("mys_shopper_overview");
		String encrypted_shooper_id = sid;

		/*
		 * String decrypt_shooper_id = encrypted_shooper_id.replace("symbol", "/");
		 * String decrypted_shooper_id = AESCrypt.decrypt(decrypt_shooper_id);
		 */
		String decrypted_shooper_id = Encryption.decrypt(encrypted_shooper_id);
		mBean.setSk_shopper_id(decrypted_shooper_id);
		List<MysteryShoppingVisitsBean> shoppingList = mvDao.getMysShopperDetails(mBean);
		mv.addObject("shoppingList", shoppingList);
		request.setAttribute("shopper_id", encrypted_shooper_id);
		return mv;
	}

	public static String decrypt(String encodedString) {

		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);

		return decodedString;

	}

	@RequestMapping("finalpage/{sid}")
	public ModelAndView finall(@PathVariable String sid, MysteryShoppingVisitsBean mBean) {
		log.info("/finalpage/{sid} api" + new Gson().toJson(mBean));
		ModelAndView mv = new ModelAndView("final");
		String decrypted_shooper_id = Encryption.decrypt(sid);
		mBean.setSk_shopper_id(decrypted_shooper_id);
		mvDao.updatevisitstatus(mBean, decrypted_shooper_id);
		return mv;
	}

	@RequestMapping(value = "/viewquestion/{sid}", method = RequestMethod.POST)
	public ModelAndView viewquestion(@PathVariable String sid, MysteryShoppingVisitsBean mBean,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("/viewquestion/{sid} api" + new Gson().toJson(mBean));
		ModelAndView mv = new ModelAndView("redirect:/questionare/" + sid + "/0");
		String encrypted_shooper_id = sid;
		log.info(mBean.getShopper_link_productNameProvided() + "sdjkfhskjah");
		/*
		 * String decrypt_shooper_id = encrypted_shooper_id.replace("symbol", "/");
		 * String decrypted_shooper_id = AESCrypt.decrypt(decrypt_shooper_id);
		 */
		String decrypted_shooper_id = Encryption.decrypt(encrypted_shooper_id);
		mBean.setSk_shopper_id(decrypted_shooper_id);
		HttpSession session = request.getSession(true);

		String user_id = (String) session.getAttribute("userId");

		// mvDao.saveMst_anwerData(mBean);

		mvDao.addShopperDetails(mBean, user_id);

		return mv;
	}

	/*
	 * display shopper Details
	 */

	@RequestMapping("questionare/{sid}/{limit}")
	public ModelAndView questionare(@PathVariable String sid, @PathVariable String limit,
			MysteryShoppingVisitsBean mBean, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("/questionare/{sid}/{limit} api" + new Gson().toJson(mBean));
		ModelAndView mv = new ModelAndView("questionare");
		String encrypted_shooper_id = sid;
		String decrypted_shooper_id = Encryption.decrypt(encrypted_shooper_id);
		mBean.setSk_shopper_id(decrypted_shooper_id);
		mvDao.getShoopersDetails(mBean);

		request.setAttribute("shoopername", mBean.getName());
		request.setAttribute("year_applied", mBean.getYear());
		List<MysteryShoppingVisitsBean> allquestionsList = mvDao.allquestionsList(mBean, mBean.getBrand_id(),
				mBean.getMode_of_contact(), limit, decrypted_shooper_id, mBean.getYear());
		mv.addObject("allquestionsList", allquestionsList);

		List<List<MysteryShoppingVisitsBean>> subquestionList = new ArrayList<List<MysteryShoppingVisitsBean>>();
		Iterator<MysteryShoppingVisitsBean> iterator = allquestionsList.iterator();
		while (iterator.hasNext()) {
			String qid = iterator.next().getQuestion_id();
			subquestionList.add(mvDao.getsubquestions(mBean, qid, decrypted_shooper_id));
		}
		request.setAttribute("sid", sid);
		request.setAttribute("bid", mBean.getBrand_id());
		request.setAttribute("modeofcontact", mBean.getMode_of_contact());
		request.setAttribute("pagecount", limit);
		mv.addObject("subquestionList", subquestionList);

		return mv;
	}

	@RequestMapping(value = "/answer/{sid}/{limit}", method = RequestMethod.POST)
	public ModelAndView answer(@PathVariable String sid, @PathVariable String limit, MysteryShoppingVisitsBean mBean,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("/answer/{sid}/{limit} api" + new Gson().toJson(mBean));
		ModelAndView mv = null;
		mv = new ModelAndView("redirect:/questionare/" + sid + "/" + limit);

		String encrypted_shooper_id = sid;
		String decrypted_shooper_id = Encryption.decrypt(encrypted_shooper_id);
		mBean.setSk_shopper_id(decrypted_shooper_id);
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		log.info(mBean.getAnswer());
		List<MysteryShoppingVisitsBean> answerdata = mBean.getAnswerdata();

		if (mBean.getQuestion_type().equals("Main Question") || mBean.getQuestion_type().equals("Dependent Question")) {

			try {
				// mvDao.checkMainquestionexist(mBean);
				log.info("update here");
				mvDao.updateMainQuestion(mBean);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception of /answer/{sid}/{limit} api insert here if" + e);
				// mvDao.addMainQuestionAnswer(mBean);
			}

			if (mBean.getFinalpage().contentEquals("finalpage")) {
				mv = new ModelAndView("redirect:/finalpage/" + sid);
			} else {
				mv = new ModelAndView("redirect:/questionare/" + sid + "/" + limit);
			}

		} else if (mBean.getQuestion_type().equals("Main Question With Set Of SubQuestions")
				|| mBean.getQuestion_type().equals("Dependent Question With Set Of SubQuestions")) {

			try {
				// mvDao.checkMainSubquestionexist(mBean,answerdata);

				mvDao.updateMainSubQuestion(mBean, answerdata);
				if (mBean.getFormula_flag().contentEquals("1")) {
					mvDao.getformulacount(mBean, mBean.getSk_shopper_id());
					mvDao.addFormularesult(mBean, mBean.getResult(), mBean.getSk_formula_map_id());
				}

			} catch (Exception e) {
				log.info("Exception /answer/{sid}/{limit} api else if" + e);
				e.printStackTrace();

			}
			if (mBean.getFinalpage().contentEquals("finalpage")) {
				mv = new ModelAndView("redirect:/finalpage/" + sid);
			} else {
				mv = new ModelAndView("redirect:/questionare/" + sid + "/" + limit);
			}

		}
		return mv;

	}

	@RequestMapping(value = "/getIds")
	public @ResponseBody Object getIds(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mBean) {
		log.info("/getIds api" + new Gson().toJson(mBean));
		String sid = request.getParameter("sid");
		String bid = request.getParameter("bid");
		String modeOfContact = request.getParameter("mode");
		String year_applied = request.getParameter("year_applied");
		MysteryShoppingVisitsBean questionscount = mvDao.questionscount(mBean, sid, bid, modeOfContact, year_applied);
		return questionscount;
	}

	@RequestMapping("viewquestions/{sk_shopper_id}/{question_id}")
	public ModelAndView viewquestions(@PathVariable String sk_shopper_id, @PathVariable String question_id,
			HttpServletRequest request, HttpServletResponse response, MysteryShoppingVisitsBean mvBean)
			throws Exception {
		log.info("/viewquestions/{sk_shopper_id}/{question_id} api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("reviewQuestions");
		mvDao.getQuestionsById(mvBean, sk_shopper_id, question_id);
		request.setAttribute("question_id", mvBean.getQuestion_id());
		request.setAttribute("question_text", mvBean.getQuestion_text());
		request.setAttribute("standard_number", mvBean.getStandard_number());
		request.setAttribute("question_type", mvBean.getQuestion_type());
		request.setAttribute("shopper_id", mvBean.getSk_shopper_id());
		List<MysteryShoppingVisitsBean> questionAnswerlist = mvDao.reviewQuestionDetailsByQuestionId(mvBean,
				sk_shopper_id, question_id);
		mv.addObject("questionAnswerlist", questionAnswerlist);

		// List<MysteryShoppingVisitsBean>optionlistByquestionId=mvDao.getOptionlistsByQuestionId(mvBean,question_id);
		// mv.addObject("optionlistByquestionId", optionlistByquestionId);

		return mv;
	}

	@RequestMapping(value = "updateAnswer", method = RequestMethod.POST)
	public ModelAndView updateAnswer(HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mBean) {
		log.info("/updateAnswer api" + new Gson().toJson(mBean));
		ModelAndView mv = null;
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		log.info("user id in update answer" + user_id);
		String shopper_id = mBean.getSk_shopper_id();
		String question_id = mBean.getQuestion_id();
		String sk_answer_id = mBean.getSk_answer_id();
		mv = new ModelAndView("redirect:/getMysQuestionnaire/" + shopper_id);
		List<MysteryShoppingVisitsBean> answerdata = mBean.getAnswerdata();
		if (mBean.getQuestion_type().equals("Main Question") || mBean.getQuestion_type().equals("Dependent Question")) {
			mvDao.updateAnswerById(mBean, user_id);
		} else {
			log.info("subquestions in else");
			try {
				log.info("hi formula ");
				mvDao.updateAnswerById(mBean, answerdata, user_id);
				log.info("formula flag" + mBean.getFormula_flag());
				String ff[] = mBean.getFormula_flag().split(",");
				if (ff[0].contentEquals("1")) {
					log.info("formula in if");
					mvDao.getformulacount(mBean, shopper_id);
					mvDao.addFormularesult(mBean, mBean.getResult(), mBean.getSk_formula_map_id());
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception /updateAnswer api" + e);

			}
		}
		return mv;
	}

	@RequestMapping(value = "/updatetoClose/{shopper_id}")
	public ModelAndView publishtoClose(@PathVariable String shopper_id, HttpServletRequest request,
			HttpServletResponse response, MysteryShoppingVisitsBean mBean) {
		log.info("/updatetoClose/{shopper_id} api" + new Gson().toJson(mBean));
		ModelAndView mv = null;
		log.info("shopper id=" + shopper_id);
		mvDao.getMaxScorePoints(mBean, shopper_id);
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		String visit_status = "published";
		String sk_shopper_id = shopper_id;
		mvDao.updateToPublish(mBean, visit_status, sk_shopper_id, user_id);
		mvDao.updateToPublishMsm(mBean, visit_status, sk_shopper_id, user_id);
		// mvDao.addOutltScore(mBean,shopper_id);
		mv = new ModelAndView("redirect:/viewMysClosed");
		return mv;
	}

	@RequestMapping("viewQualityAssuranceQuestions/{sk_shopper_id}/{question_id}")
	public ModelAndView viewQualityAssurancequestions(@PathVariable String sk_shopper_id,
			@PathVariable String question_id, HttpServletRequest request, HttpServletResponse response,
			MysteryShoppingVisitsBean mvBean) throws Exception {
		log.info("/viewQualityAssuranceQuestions/{sk_shopper_id}/{question_id} api" + new Gson().toJson(mvBean));
		ModelAndView mv = new ModelAndView("qualityassurancequestions");
		mvDao.getQuestionsById(mvBean, sk_shopper_id, question_id);
		request.setAttribute("question_id", mvBean.getQuestion_id());
		request.setAttribute("question_text", mvBean.getQuestion_text());
		request.setAttribute("standard_number", mvBean.getStandard_number());
		request.setAttribute("question_type", mvBean.getQuestion_type());
		request.setAttribute("shopper_id", mvBean.getSk_shopper_id());
		List<MysteryShoppingVisitsBean> questionAnswerlist = mvDao.reviewQuestionDetailsByQuestionId(mvBean,
				sk_shopper_id, question_id);
		mv.addObject("questionAnswerlist", questionAnswerlist);
		return mv;
	}

}
