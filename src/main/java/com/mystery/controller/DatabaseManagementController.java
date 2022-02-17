package com.mystery.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mystery.beans.CityBean;
import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.DealerBean;
import com.mystery.beans.RegionBean;
import com.mystery.beans.StateBean;
import com.mystery.dao.DatabaseManagementDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DatabaseManagementController {

	@Autowired
	DatabaseManagementDao dbDao;

	@RequestMapping("state")
	public ModelAndView state(StateBean sBean) {
		log.info("/state api"+new Gson().toJson(sBean));
		ModelAndView mv = new ModelAndView("state");
		List<StateBean> activestateList = dbDao.getStateList(sBean);
		mv.addObject("stateList", activestateList);
		List<StateBean> inactivestateList = dbDao.getInactiveStateList(sBean);
		mv.addObject("inactivestateList", inactivestateList);
		return mv;
	}

	@RequestMapping(value = "/createStatePost", method = RequestMethod.POST)
	public ModelAndView addState(HttpServletRequest request, HttpServletResponse response, StateBean sBean)
			throws IOException {
		log.info("/createStatePost api"+new Gson().toJson(sBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/state");
		dbDao.addState(sBean);

		return model;
	}

	@RequestMapping("editState/{sid}")
	public ModelAndView userEdit(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			StateBean sBean) throws IOException {
		log.info("/editState/{sid} api"+new Gson().toJson(sBean));
		log.info("/editState/{sid} api"+sid);
		ModelAndView mv = new ModelAndView("stateedit");
		dbDao.getStateDetailsById(sBean, sid);
		request.setAttribute("sid", sBean.getSk_state_id());
		request.setAttribute("state_name", sBean.getState_name());
		return mv;

	}

	@RequestMapping(value = "/updateStatePost/{sid}", method = RequestMethod.POST)
	public ModelAndView editstatePost(@PathVariable String sid, HttpServletRequest request,
			HttpServletResponse response, StateBean sBean) throws IOException {
		log.info("/updateStatePost/{sid} api"+new Gson().toJson(sBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/state");
		dbDao.updatStateById(sBean, sid);
		return model;
	}

	@RequestMapping("deleteState/{sid}")
	public ModelAndView deleteUser(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			StateBean sBean) throws IOException {
		log.info("/updateStatePost/{sid} api"+new Gson().toJson(sBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/state");
		dbDao.deleteStateById(sBean, sid);
		return model;
	}

	@RequestMapping("restoreState/{sid}")
	public ModelAndView restoreState(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			StateBean sBean) throws IOException {
		log.info("/restoreState/{sid} api"+new Gson().toJson(sBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/state");
		dbDao.restoreStateStateById(sBean, sid);
		return model;
	}

	@RequestMapping(value = "/getStateExistance")
	public @ResponseBody Object getStateExistance(HttpServletRequest request, HttpServletResponse response,
			StateBean sBean) {
		log.info("/getStateExistance api"+new Gson().toJson(sBean));
		String name = request.getParameter("name");
		StateBean stateList = dbDao.getstateexist(sBean, name);
		return stateList;
	}

	@RequestMapping(value = "/getStateExistanceByid")
	public @ResponseBody Object getStateExistanceByid(HttpServletRequest request, HttpServletResponse response,
			StateBean sBean) {
		log.info("/getStateExistanceByid api"+new Gson().toJson(sBean));
		String name = request.getParameter("name");
		String stateid = request.getParameter("stateid");
		StateBean stateList = dbDao.getstateexistbyid(sBean, name, stateid);
		return stateList;
	}

	/**********************************************************************************************************************/
	@RequestMapping("city")
	public ModelAndView city(CityBean cBean, StateBean sBean) {
		log.info("/city api"+new Gson().toJson(sBean));
		ModelAndView mv = new ModelAndView("city");
		List<StateBean> activestateList = dbDao.getStateList(sBean);
		mv.addObject("stateList", activestateList);
		List<CityBean> activeCityList = dbDao.getCityList(cBean);
		mv.addObject("activeCityList", activeCityList);
		List<CityBean> inactiveCityList = dbDao.getInactiveCityList(cBean);
		mv.addObject("inactiveCityList", inactiveCityList);
		return mv;

	}

	@RequestMapping(value = "/createCityPost", method = RequestMethod.POST)
	public ModelAndView createCityPost(HttpServletRequest request, HttpServletResponse response, CityBean cBean)
			throws IOException {
		log.info("/createCityPost api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/city");
		dbDao.addCity(cBean);
		return model;
	}

	@RequestMapping("editCity/{cid}")
	public ModelAndView userCity(@PathVariable String cid, HttpServletRequest request, HttpServletResponse response,
			CityBean cBean, StateBean sBean) throws IOException {
		log.info("/editCity/{cid} api"+ cid);
		ModelAndView mv = new ModelAndView("cityedit");
		List<StateBean> activestateList = dbDao.getStateList(sBean);
		mv.addObject("stateList", activestateList);
		dbDao.getCityDetailsById(cBean, cid);

		request.setAttribute("sid", cBean.getSk_state_id());
		request.setAttribute("state_name", cBean.getState_name());
		request.setAttribute("cid", cBean.getSk_city_id());
		request.setAttribute("city_name", cBean.getCity_name());
		request.setAttribute("tier", cBean.getTier());
		request.setAttribute("metro", cBean.getMetro());
		return mv;

	}

	@RequestMapping(value = "/updatecity/{cid}", method = RequestMethod.POST)
	public ModelAndView editcityPost(@PathVariable String cid, HttpServletRequest request, HttpServletResponse response,
			CityBean cBean) throws IOException {
		log.info("/updatecity/{cid} api"+ cid);
		ModelAndView model = null;
		model = new ModelAndView("redirect:/city");
		dbDao.updatCityById(cBean, cid);
		return model;
	}

	@RequestMapping("cityDelete/{cid}")
	public ModelAndView deleteCity(@PathVariable String cid, HttpServletRequest request, HttpServletResponse response,
			CityBean cBean) throws IOException {
		log.info("/cityDelete/{cid} api"+ cid);
		ModelAndView model = null;
		model = new ModelAndView("redirect:/city");
		dbDao.deleteCityById(cBean, cid);
		return model;
	}

	@RequestMapping("restoreCity/{cid}")
	public ModelAndView restoreCity(@PathVariable String cid, HttpServletRequest request, HttpServletResponse response,
			CityBean cBean) throws IOException {
		log.info("/restoreCity/{cid} api"+ cid);
		ModelAndView model = null;
		model = new ModelAndView("redirect:/city");
		dbDao.restoreCityById(cBean, cid);
		return model;
	}

	@RequestMapping(value = "/getCityExistance")
	public @ResponseBody Object getCityExistance(HttpServletRequest request, HttpServletResponse response,
			CityBean cBean) {
		log.info("/getCityExistance api");
		String state_id = request.getParameter("state_id");
		String name = request.getParameter("name");
		StateBean cityList = dbDao.getCityExistance(cBean, state_id, name);
		return cityList;
	}

	@RequestMapping(value = "/getCityExistanceByid")
	public @ResponseBody Object getCityExistanceByid(HttpServletRequest request, HttpServletResponse response,
			CityBean cBean) {
		log.info("/getCityExistanceByid api");
		String name = request.getParameter("name");
		String stateid = request.getParameter("stateid");
		String cityid = request.getParameter("cityid");
		StateBean cityList = dbDao.getcityexistbyid(cBean, name, cityid, stateid);
		return cityList;
	}

	/************************************************************************************************************************/
	@RequestMapping("region")
	public ModelAndView region(RegionBean rBean, StateBean sBean, CityBean cBean) {
		log.info("/region api");
		ModelAndView mv = new ModelAndView("region");
		List<CityBean> activeCityList = dbDao.getCityList(cBean);
		mv.addObject("cityList", activeCityList);
		List<RegionBean> activeRegionList = dbDao.getRegionList1(rBean);
		mv.addObject("activeRegionList", activeRegionList);
		List<RegionBean> inactiveRegionList = dbDao.getInactiveRegionList(rBean);
		mv.addObject("inactiveRegionList", inactiveRegionList);
		return mv;
	}

	@RequestMapping(value = "/createRegionPost", method = RequestMethod.POST)
	public ModelAndView createRegionPost(HttpServletRequest request, HttpServletResponse response, RegionBean rBean)
			throws IOException {
		log.info("/createRegionPost api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/region");
		dbDao.addRegion(rBean);
		return model;
	}

	@RequestMapping("editRegion/{rid}")
	public ModelAndView userRegion(@PathVariable String rid, HttpServletRequest request, HttpServletResponse response,
			CityBean cBean, RegionBean rBean) {
		log.info("/editRegion/{rid} api");
		ModelAndView mv = new ModelAndView("editregion");
		List<CityBean> activeCityList = dbDao.getCityList(cBean);
		mv.addObject("cityList", activeCityList);
		dbDao.getRegionDetailsById(rBean, rid);
		request.setAttribute("cid", rBean.getSk_city_id());
		request.setAttribute("city_name", rBean.getCity_name());
		request.setAttribute("rid", rBean.getSk_region_id());
		request.setAttribute("region_name", rBean.getRegion_name());
		return mv;
	}

	@RequestMapping(value = "/updateregion/{rid}", method = RequestMethod.POST)
	public ModelAndView editregionPost(@PathVariable String rid, HttpServletRequest request,
			HttpServletResponse response, RegionBean rBean) throws IOException {
		log.info("/updateregion/{rid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/region");
		dbDao.updatRegionById(rBean, rid);
		return model;
	}

	@RequestMapping("deleteRegion/{rid}")
	public ModelAndView deleteRegion(@PathVariable String rid, RegionBean rBean) {
		log.info("/deleteRegion/{rid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/region");
		dbDao.deleteRegionById(rBean, rid);
		return model;

	}

	@RequestMapping("restoreRegion/{rid}")
	public ModelAndView restoreRegion(@PathVariable String rid, RegionBean rBean) {
		log.info("/restoreRegion/{rid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/region");
		dbDao.restoreRegionById(rBean, rid);
		return model;

	}

	@RequestMapping(value = "/getResgionExistance")
	public @ResponseBody Object getResgionExistance(HttpServletRequest request, HttpServletResponse response,
			RegionBean rBean) {
		log.info("/getResgionExistance api");
		String name = request.getParameter("name");
		StateBean regionList = dbDao.getResgionExistance(rBean, name);
		return regionList;
	}

	@RequestMapping(value = "/getRegionExistanceByid")
	public @ResponseBody Object getRegionExistanceByid(HttpServletRequest request, HttpServletResponse response,
			RegionBean rBean) {
		log.info("/getRegionExistanceByid api");
		String name = request.getParameter("name");
		String regionid = request.getParameter("regionid");
		RegionBean regionList = dbDao.getRegionExistanceByid(rBean, name, regionid);
		return regionList;
	}

	/*************** Region End ******************/

	/* brand start */
	@RequestMapping("brand")
	public ModelAndView brand(DatabaseManagementBean dbBean) {
		log.info("/brand api");
		ModelAndView mv = new ModelAndView("brand");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		List<DatabaseManagementBean> inactivebrandList = dbDao.getInactiveBrandList(dbBean);
		mv.addObject("inactivebrandList", inactivebrandList);
		return mv;

	}

	@RequestMapping(value = "/createBrandPost", method = RequestMethod.POST)
	public ModelAndView addBrand(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/createBrandPost api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/brand");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.addBrand(dbBean, user_id);

		return model;
	}

	/*****************************/
	@RequestMapping("editBrand/{sid}")
	public ModelAndView brandEdit(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/editBrand/{sid} api");
		ModelAndView mv = new ModelAndView("brandedit");
		dbDao.getBrandDetailsById(dbBean, sid);
		request.setAttribute("sid", dbBean.getSk_brand_id());
		request.setAttribute("brand_name", dbBean.getBrand_name());
		request.setAttribute("brand_description", dbBean.getBrand_description());
		return mv;

	}

	@RequestMapping(value = "/updateBrandPost/{sid}", method = RequestMethod.POST)
	public ModelAndView editbrandPost(@PathVariable String sid, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/updateBrandPost/{sid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/brand");
		dbDao.updatBrandById(dbBean, sid);
		return model;
	}

	@RequestMapping("deleteBrand/{sid}")
	public ModelAndView deleteBrand(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/deleteBrand/{sid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/brand");
		dbDao.deleteBrandById(dbBean, sid);
		return model;
	}

	@RequestMapping("restoreBrand/{sid}")
	public ModelAndView restoreBrand(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/restoreBrand/{sid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/brand");
		dbDao.restoreBrandBrandById(dbBean, sid);
		return model;
	}

	@RequestMapping(value = "/getBrandExistance")
	public @ResponseBody Object getBrandExistance(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getBrandExistance api");
		log.info("exisit or no");
		String name = request.getParameter("name");
		DatabaseManagementBean brandExisit = dbDao.getBrandExistance(dbBean, name);
		log.info("brandExisit"+brandExisit);
		// model.addAttribute("roleData", roleList);
		return brandExisit;
	}

	@RequestMapping(value = "/getBrandExistanceByid")
	public @ResponseBody Object getBrandExistanceByid(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getBrandExistanceByid api");
		log.info("exisit or no");
		String name = request.getParameter("name");
		String brand_id = request.getParameter("brand_id");
		DatabaseManagementBean brandExisit = dbDao.getBrandExistanceByid(dbBean, name, brand_id);
		log.info("brandExisit"+brandExisit);
		// model.addAttribute("roleData", roleList);
		return brandExisit;
	}

	/*********************************/
	/* brand end */
	/* model start */
	@RequestMapping("model")
	public ModelAndView model(DatabaseManagementBean dbBean) {
		log.info("/model api");
		ModelAndView mv = new ModelAndView("model");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("brandList", activebrandList);

		List<DatabaseManagementBean> activemodelList = dbDao.getModelList(dbBean);
		mv.addObject("modelList", activemodelList);

		List<DatabaseManagementBean> inactivemodelList = dbDao.getInactiveModelList(dbBean);
		mv.addObject("inactivemodelList", inactivemodelList);
		return mv;

	}

	@RequestMapping(value = "/createModelPost", method = RequestMethod.POST)
	public ModelAndView addModel(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/createModelPost api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/model");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.addModel(dbBean, user_id);

		return model;
	}

	@RequestMapping("editModel/{sid}")
	public ModelAndView modelEdit(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/editModel/{sid} api");
		ModelAndView mv = new ModelAndView("modeledit");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("brandList", activebrandList);
		dbDao.getModelDetailsById(dbBean, sid);
		request.setAttribute("brand_id", dbBean.getBrand_id());
//request.setAttribute("sid", dbBean.getSk_model_id());
		request.setAttribute("brand_name", dbBean.getBrand_name());
		request.setAttribute("model_name", dbBean.getModel_name());

		return mv;
	}

	@RequestMapping("deleteModel/{sid}")
	public ModelAndView deleteModel(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/deleteModel/{sid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/model");
		dbDao.deleteModelById(dbBean, sid);
		return model;
	}

	@RequestMapping("restoreModel/{sid}")
	public ModelAndView restoreModel(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/restoreModel/{sid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/model");
		dbDao.restoreModelModelById(dbBean, sid);
		return model;
	}

	@RequestMapping(value = "/updateModelPost/{sid}", method = RequestMethod.POST)
	public ModelAndView editmodelPost(@PathVariable String sid, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/updateModelPost/{sid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/model");
		dbDao.updatModelById(dbBean, sid);
		return model;
	}

	@RequestMapping(value = "/getModelExistance")
	public @ResponseBody Object getModelExistance(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getModelExistance api");
		log.info("exisit or no");
		String name = request.getParameter("name");
		String brand_id = request.getParameter("brand_id");
		DatabaseManagementBean roleList = dbDao.getmodelexist(dbBean, name, brand_id);
		// model.addAttribute("roleData", roleList);
		return roleList;
	}

	@RequestMapping(value = "/getModelExistanceById")
	public @ResponseBody Object getModelExistanceById(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getModelExistanceById api");

		log.info("exisit or no");
		String name = request.getParameter("name");
		String brand_id = request.getParameter("brand_id");
		String model_id = request.getParameter("model_id");
		DatabaseManagementBean roleList = dbDao.getmodelexistById(dbBean, name, brand_id, model_id);
		// model.addAttribute("roleData", roleList);
		return roleList;
	}

	/* model end */
	/******* varient start ********/
	@RequestMapping("varient")
	public ModelAndView varient(DatabaseManagementBean dbBean) {
		log.info("/varient api");
		ModelAndView mv = new ModelAndView("varient");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("brandList", activebrandList);
		List<DatabaseManagementBean> activemodelList = dbDao.getModelList(dbBean);
		mv.addObject("modelList", activemodelList);

		List<DatabaseManagementBean> activevarientlList = dbDao.getVarientList(dbBean);
		mv.addObject("varientlList", activevarientlList);
		List<DatabaseManagementBean> inactivevarientlList = dbDao.getInactiveVarientList(dbBean);
		mv.addObject("inactivevarientList", inactivevarientlList);

		return mv;

	}

	@RequestMapping(value = "/createVarientPost", method = RequestMethod.POST)
	public ModelAndView addVarient(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/createVarientPost api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/varient");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.addVarient(dbBean, user_id);

		return model;
	}

	@RequestMapping("deleteVarient/{sid}")
	public ModelAndView deleteVarient(@PathVariable String sid, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/deleteVarient/{sid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/varient");
		dbDao.deleteVarientById(dbBean, sid);
		return model;
	}

	@RequestMapping("restoreVarient/{sid}")
	public ModelAndView restoreVarient(@PathVariable String sid, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/restoreVarient/{sid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/varient");
		dbDao.restoreVarientVarientById(dbBean, sid);
		return model;
	}

	@RequestMapping("editVariant/{sid}")
	public ModelAndView variantEdit(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/editVariant/{sid} api");
		ModelAndView mv = new ModelAndView("variantedit");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("brandList", activebrandList);
		List<DatabaseManagementBean> activemodelList = dbDao.getModelList(dbBean);
		mv.addObject("modelList", activemodelList);
		dbDao.getVariantDetailsById(dbBean, sid);
		request.setAttribute("brand_id", dbBean.getBrand_id());
		request.setAttribute("model_id", dbBean.getModel_id());
		request.setAttribute("brand_name", dbBean.getBrand_name());
		request.setAttribute("model_name", dbBean.getModel_name());
		request.setAttribute("variant_name", dbBean.getVarient_name());
		String brand_id = String.valueOf(dbBean.getBrand_id());
		List<DatabaseManagementBean> modelListByBrandId = dbDao.getModelsByBrandId(dbBean, brand_id);
		mv.addObject("modelListByBrandId", modelListByBrandId);
		return mv;
	}

	@RequestMapping(value = "/updateVariantPost/{sid}", method = RequestMethod.POST)
	public ModelAndView editvariantPost(@PathVariable String sid, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/updateVariantPost/{sid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/varient");
		dbDao.updateVariantById(dbBean, sid);
		return model;
	}

	@RequestMapping(value = "/getVariantExistance")
	public @ResponseBody Object getVariantExistance(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getVariantExistance api");
		log.info("exisit or no");
		String name = request.getParameter("name");
		String model_id = request.getParameter("model_id");
		DatabaseManagementBean roleList = dbDao.getvariantexist(dbBean, name, model_id);
		// model.addAttribute("roleData", roleList);
		return roleList;
	}

	@RequestMapping(value = "/getVariantExistanceByid")
	public @ResponseBody Object getVariantExistanceByid(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getVariantExistanceByid api");
		log.info("exisit or no");
		String name = request.getParameter("name");
		String model_id = request.getParameter("model_id");
		String variant_id = request.getParameter("variant_id");
		DatabaseManagementBean roleList = dbDao.getVariantExistanceByid(dbBean, name, model_id, variant_id);
		// model.addAttribute("roleData", roleList);
		return roleList;
	}

	/******* varient end ********/

	/**
	 * 
	 * dealers
	 */

	@RequestMapping("dealer")
	public ModelAndView dealer(DealerBean dBean, RegionBean rBean, DatabaseManagementBean dbBean) {
		log.info("/dealer api");
		ModelAndView mv = new ModelAndView("dealership");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		List<RegionBean> activeRegionList = dbDao.getRegionList1(rBean);
		mv.addObject("activeRegionList", activeRegionList);
		List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
		mv.addObject("activeDealerList", activeDealerList);
		List<DealerBean> inactiveDealerList = dbDao.getInactiveDealerList(dBean);
		mv.addObject("inactiveDealerList", inactiveDealerList);
		return mv;

	}

	@RequestMapping(value = "/createDealerPost", method = RequestMethod.POST)
	public ModelAndView createDealershipPost(HttpServletRequest request, HttpServletResponse response, DealerBean dBean)
			throws IOException {
		log.info("/createDealerPost api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/dealer");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.addDealership(dBean, user_id);
		return model;
	}

	@RequestMapping(value = "/getDealersByBrandId")
	public @ResponseBody Object getDealersByBrandId(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getDealersByBrandId api");
		String brand_id = request.getParameter("sk_brand_id");
		List<DatabaseManagementBean> modelList = dbDao.getDealersByBrandId(dbBean, brand_id);
		// model.addAttribute("roleData", roleList);
		return modelList;
	}

	@RequestMapping(value = "/getRegionsByDealerId")
	public @ResponseBody Object getRegionsByDealerId(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getRegionsByDealerId api");
		String dealer_id = request.getParameter("sk_dealer_id");
		List<DatabaseManagementBean> dealerList = dbDao.getRegionsByDealerId(dbBean, dealer_id);
		// model.addAttribute("roleData", roleList);
		return dealerList;
	}

	@RequestMapping("editDealer/{did}")
	public ModelAndView editDealer(@PathVariable String did, HttpServletRequest request, HttpServletResponse response,
			DealerBean dBean, RegionBean rBean, DatabaseManagementBean dbBean) {
		ModelAndView mv = new ModelAndView("editdealership");
		log.info("/editDealer/{did} api");
		List<RegionBean> activeRegionList = dbDao.getRegionList1(rBean);
		mv.addObject("activeRegionList", activeRegionList);
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		dbDao.getDealerDetailsById(dBean, did);

		request.setAttribute("did", dBean.getSk_dealer_id());
		request.setAttribute("dealer_name", dBean.getDealer_name());
		request.setAttribute("rid", dBean.getRegion_id());
		request.setAttribute("region_name", dBean.getRegion_name());
		request.setAttribute("brand_id", dBean.getBrand_id());
		request.setAttribute("brand_name", dBean.getBrand_name());
		request.setAttribute("email", dBean.getEmail());
		request.setAttribute("contact_persion", dBean.getContact_persion());
		request.setAttribute("lan", dBean.getLan());
		request.setAttribute("lat", dBean.getLat());
		request.setAttribute("mobile", dBean.getMobile());
		request.setAttribute("address", dBean.getAddress());
		return mv;

	}

	@RequestMapping(value = "/updateDealerPost/{did}", method = RequestMethod.POST)
	public ModelAndView updateDealerPost(@PathVariable String did, HttpServletRequest request,
			HttpServletResponse response, DealerBean dBean) throws IOException {
		log.info("/updateDealerPost/{did} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/dealer");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.updateDealerPost(dBean, did, user_id);
		return model;
	}

	@RequestMapping("deleteDealer/{did}")
	public ModelAndView deleteDealer(@PathVariable String did, DealerBean dBean) {
		log.info("/deleteDealer/{did} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/dealer");
		dbDao.deleteDealerById(dBean, did);
		return model;

	}

	@RequestMapping("restoreDealer/{did}")
	public ModelAndView restoreDealer(@PathVariable String did, DealerBean dBean) {
		log.info("/restoreDealer/{did} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/dealer");
		dbDao.restoreDealerById(dBean, did);
		return model;
	}

	@RequestMapping(value = "/getDealerExistance")
	public @ResponseBody Object getDealerExistance(HttpServletRequest request, HttpServletResponse response,
			RegionBean rBean) {
		log.info("/getDealerExistance api");
		String name = request.getParameter("name");
		String brand_id = request.getParameter("brand_id");
		StateBean dealerexistList = dbDao.getDealerExistance(rBean, name, brand_id);
		return dealerexistList;
	}

	@RequestMapping(value = "/getDealerExistanceByid")
	public @ResponseBody Object getDealerExistanceByid(HttpServletRequest request, HttpServletResponse response,
			RegionBean rBean) {
		log.info("/getDealerExistanceByid api");
		String did = request.getParameter("did");
		String name = request.getParameter("name");
		String brand_id = request.getParameter("brand_id");
		log.info("brand_id" + brand_id);
		StateBean dealerexistList = dbDao.getDealerExistanceById(rBean, name, did, brand_id);
		return dealerexistList;
	}

	@RequestMapping(value = "/getModelsByBrandId")
	public @ResponseBody Object getModelsByBrandId(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getModelsByBrandId api");
		String brand_id = request.getParameter("brand_id");
		List<DatabaseManagementBean> modelList = dbDao.getModelsByBrandId(dbBean, brand_id);
		// model.addAttribute("roleData", roleList);
		return modelList;
	}

	/***********************************************************************************************************/
	@RequestMapping("addSection")
	public ModelAndView addSection(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/addSection api");
		ModelAndView mv = new ModelAndView("addSection");
		List<DatabaseManagementBean> sectionList = dbDao.getSectionList(dbBean);
		mv.addObject("sectionList", sectionList);
		List<DatabaseManagementBean> inactiveSectionList = dbDao.getInactiveSectionList(dbBean);
		mv.addObject("inactiveSectionList", inactiveSectionList);
		List<DatabaseManagementBean> subSectionList = dbDao.getSubsectionList(dbBean);
		mv.addObject("subSectionList", subSectionList);
		List<DatabaseManagementBean> inactiveSubsectionList = dbDao.getInactiveSubSectionList(dbBean);
		mv.addObject("inactiveSubsectionList", inactiveSubsectionList);

		return mv;
	}

	@RequestMapping(value = "/getsectionExistance")
	public @ResponseBody Object getsectionExistance(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getsectionExistance api");
		String section_name = request.getParameter("section_name");
		DatabaseManagementBean section_nameList = dbDao.getsectionExistance(dbBean, section_name);
		return section_nameList;
	}

	@RequestMapping(value = "/addSection", method = RequestMethod.POST)
	public ModelAndView SaveSection(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/addSection api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbBean = dbDao.addSection(dbBean, user_id);

		return model;
	}

	@RequestMapping("editSection/{sectionId}")
	public ModelAndView editSection(@PathVariable String sectionId, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/editSection/{sectionId} api");
		ModelAndView mv = new ModelAndView("sectionedit");
		dbDao.getSetionById(dbBean, sectionId);
		request.setAttribute("sectionId", dbBean.getSk_section_id());
		request.setAttribute("sectionName", dbBean.getSection_name());
		return mv;
	}

	@RequestMapping(value = "/getsectionExistanceById")
	public @ResponseBody Object getsectionExistanceById(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getsectionExistanceById api");
		String section_name = request.getParameter("section_name");
		String sk_section_id = request.getParameter("sk_section_id");
		DatabaseManagementBean sectionList = dbDao.getsectionExistanceById(dbBean, section_name, sk_section_id);
		return sectionList;
	}

	@RequestMapping(value = "/updateSection", method = RequestMethod.POST)
	public ModelAndView updateSection(DatabaseManagementBean dbBean, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("/updateSection api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.updateSection(dbBean, user_id);

		return model;
	}

	@RequestMapping("deleteSection/{sectionId}")
	public ModelAndView deleteSection(@PathVariable String sectionId, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/deleteSection/{sectionId} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.deleteSectionById(dbBean, sectionId, user_id);
		return model;
	}

	@RequestMapping("restoreSection/{sectionId}")
	public ModelAndView restoreSection(@PathVariable String sectionId, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/restoreSection/{sectionId} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.restoreSectionById(dbBean, sectionId, user_id);
		return model;
	}

	/*** ADD SUB SECTION ***/

	@RequestMapping("addsubSection")
	public ModelAndView addSubSection(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/addsubSection api");
		ModelAndView mv = new ModelAndView("addsubsection");
		List<DatabaseManagementBean> sectionList = dbDao.getSectionList(dbBean);
		mv.addObject("sectionList", sectionList);
		List<DatabaseManagementBean> subSectionList = dbDao.getSubsectionList(dbBean);
		mv.addObject("subSectionList", subSectionList);
		List<DatabaseManagementBean> inactiveSubsectionList = dbDao.getInactiveSubSectionList(dbBean);
		mv.addObject("inactiveSubsectionList", inactiveSubsectionList);
		return mv;
	}

	@RequestMapping(value = "/addsubSection", method = RequestMethod.POST)
	public ModelAndView saveSubSection(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/addsubSection api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addsubSection");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.addSubSection(dbBean, user_id);
		return model;
	}

	@RequestMapping(value = "/getSubsectionExistance")
	public @ResponseBody Object getSubsectionExistance(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getSubsectionExistance api");
		String subsection_name = request.getParameter("subsection_name");
		String sk_section_id = request.getParameter("sk_section_id");
		DatabaseManagementBean subsectionList = dbDao.getSubsectionExistance(dbBean, sk_section_id, subsection_name);
		return subsectionList;
	}

	@RequestMapping("editsubSection/{sk_subsection_id}")
	public ModelAndView editsubSection(@PathVariable String sk_subsection_id, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/editsubSection/{sk_subsection_id} api");
		ModelAndView mv = new ModelAndView("subsectionedit");
		List<DatabaseManagementBean> sectionList = dbDao.getSectionList(dbBean);
		mv.addObject("sectionList", sectionList);
		dbDao.getsubSetionById(dbBean, sk_subsection_id);
		request.setAttribute("sectionId", dbBean.getSk_section_id());
		request.setAttribute("sectionName", dbBean.getSection_name());
		request.setAttribute("subsectionId", dbBean.getSk_subsection_id());
		request.setAttribute("subsectionName", dbBean.getSubsection_name());
		return mv;
	}

	@RequestMapping(value = "/getSubSectionExistanceById")
	public @ResponseBody Object getSubSectionExistanceById(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getSubSectionExistanceById api");
		String subSectionName = request.getParameter("subSectionName");
		String sectionId = request.getParameter("sectionId");
		String subsectionId = request.getParameter("subsectionId");
		DatabaseManagementBean subSectionList = dbDao.getSubSectionExistanceById(dbBean, subSectionName, sectionId,
				subsectionId);
		return subSectionList;
	}

	@RequestMapping(value = "/updatesubSection", method = RequestMethod.POST)
	public ModelAndView updatesubSection(DatabaseManagementBean dbBean, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("/updatesubSection api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addsubSection");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.updatesubSection(dbBean, user_id);

		return model;
	}

	@RequestMapping("deleteSubSection/{sk_subsection_id}")
	public ModelAndView deleteSubSection(@PathVariable String sk_subsection_id, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/deleteSubSection/{sk_subsection_id} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addsubSection");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.deleteSubSectionById(dbBean, sk_subsection_id, user_id);
		return model;
	}

	@RequestMapping("restoreSubSection/{sk_subsection_id}")
	public ModelAndView restoreSubSection(@PathVariable String sk_subsection_id, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/restoreSubSection/{sk_subsection_id} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addsubSection");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.restoreSubSectionById(dbBean, sk_subsection_id, user_id);
		return model;
	}

	/**** Outlet start *****/
	@RequestMapping("Createoutlet")
	public ModelAndView outlet(DatabaseManagementBean dbBean, StateBean sBean, DealerBean dBean, CityBean cBean,
			RegionBean rBean) {
		ModelAndView mv = new ModelAndView("outlet");
		log.info("/Createoutlet api"+new Gson().toJson(dBean));
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("brandList", activebrandList);
		List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
		mv.addObject("activeDealerList", activeDealerList);
		List<StateBean> activestateList = dbDao.getStateList(sBean);
		mv.addObject("stateList", activestateList);
		List<CityBean> activeCityList = dbDao.getCityList(cBean);
		mv.addObject("cityList", activeCityList);
		List<RegionBean> activeRegionList = dbDao.getRegionList1(rBean);
		mv.addObject("activeRegionList", activeRegionList);

		return mv;

	}

	@RequestMapping("Viewoutlet")
	public ModelAndView outletview(DatabaseManagementBean dbBean) {
		log.info("/Viewoutlet api");
		ModelAndView mv = new ModelAndView("outletview");
		List<DatabaseManagementBean> activeoutletList = dbDao.getOutletList(dbBean);
		mv.addObject("outletList", activeoutletList);
		List<DatabaseManagementBean> inactiveoutletList = dbDao.getinactiveOutletList(dbBean);
		mv.addObject("inactiveoutletList", inactiveoutletList);

		return mv;

	}

	@RequestMapping(value = "/getStatesByRegionId")
	public @ResponseBody Object getStatesByRegionId(HttpServletRequest request, HttpServletResponse response,
			StateBean sBean) {
		log.info("/getStatesByRegionId api");
		String region_id = request.getParameter("sk_region_id");
		List<StateBean> stateList = dbDao.getStatesByRegionId(sBean, region_id);
		// model.addAttribute("roleData", roleList);
		return stateList;
	}

	@RequestMapping(value = "/getCitiesByStateId")
	public @ResponseBody Object getCitiesByStateId(HttpServletRequest request, HttpServletResponse response,
			StateBean sBean, CityBean cBean) {
		log.info("/getCitiesByStateId api");
		String state_id = request.getParameter("sk_state_id");
		List<CityBean> cityList = dbDao.getCitiesByStateId(cBean, state_id);
		// model.addAttribute("roleData", roleList);
		return cityList;
	}

	@RequestMapping(value = "/createOutletPost", method = RequestMethod.POST)
	public ModelAndView addOutlet(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/createOutletPost api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/Createoutlet");
		model = new ModelAndView("redirect:/Viewoutlet");
		// log.info(dbBean.getBrand_id());
		log.info("dealer" + dbBean.getSk_dealer_id());
		log.info("brand" + dbBean.getSk_brand_id());
		log.info("region" + dbBean.getSk_region_id());
		log.info("state" + dbBean.getSk_state_id());
		log.info("city" + dbBean.getSk_city_id());
		log.info("address" + dbBean.getAddress());
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.addOutlet(dbBean, user_id);

		return model;
	}

	@RequestMapping("deleteOutlet/{oid}")
	public ModelAndView deleteOutlet(@PathVariable String oid, DatabaseManagementBean dbBean) {
		ModelAndView model = null;
		log.info("/deleteOutlet/{oid} api");
		model = new ModelAndView("redirect:/Viewoutlet");
		log.info("outlet_id" + oid);
		dbDao.deleteOutletById(dbBean, oid);
		return model;

	}

	@RequestMapping("restoreOutlet/{oid}")
	public ModelAndView restoreOutlet(@PathVariable String oid, DatabaseManagementBean dbBean) {
		log.info("/restoreOutlet/{oid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/Viewoutlet");
		dbDao.restoreOutletById(dbBean, oid);
		return model;
	}

	@RequestMapping("editOutlet/{oid}")
	public ModelAndView editOutlet(@PathVariable String oid, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean, StateBean sBean, DealerBean dBean, CityBean cBean, RegionBean rBean) {
		log.info("/editOutlet/{oid} api");
		ModelAndView mv = new ModelAndView("editoutlet");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("brandList", activebrandList);
		List<DealerBean> activeDealerList = dbDao.getDealerList(dBean);
		mv.addObject("activeDealerList", activeDealerList);
		List<StateBean> activestateList = dbDao.getStateList(sBean);
		mv.addObject("stateList", activestateList);
		List<CityBean> activeCityList = dbDao.getCityList(cBean);
		mv.addObject("cityList", activeCityList);
		List<RegionBean> activeRegionList = dbDao.getRegionList1(rBean);
		mv.addObject("activeRegionList", activeRegionList);

		dbDao.getOutletDetailsById(dbBean, oid);

//request.setAttribute("oid", dbBean.getSk_outlet_id());
		request.setAttribute("outlet_id", dbBean.getSk_outlet_id());
		request.setAttribute("sk_dealer_id", dbBean.getSk_dealer_id());
		request.setAttribute("outlet_name", dbBean.getOutlet_name());
		request.setAttribute("dealer_name", dbBean.getDealer_name());
		request.setAttribute("outlet_type", dbBean.getOutlet_type());
		request.setAttribute("outlet_id", dbBean.getOutlet_id());
		request.setAttribute("sk_region_id", dbBean.getSk_region_id());
		request.setAttribute("sk_state_id", dbBean.getSk_state_id());
		request.setAttribute("sk_city_id", dbBean.getSk_city_id());
		request.setAttribute("region_name", dbBean.getRegion_name());
		request.setAttribute("state_name", dbBean.getState_name());
		request.setAttribute("city_name", dbBean.getCity());
		request.setAttribute("sk_brand_id", dbBean.getSk_brand_id());
		request.setAttribute("brand_name", dbBean.getBrand_name());
		request.setAttribute("email", dbBean.getEmail());
		request.setAttribute("contact_person", dbBean.getContact_person());
		request.setAttribute("lang", dbBean.getLang());
		request.setAttribute("lat", dbBean.getLat());
		request.setAttribute("mobile", dbBean.getMobile());
		request.setAttribute("Pincode", dbBean.getPincode());
		request.setAttribute("address", dbBean.getAddress());

		return mv;

	}

	@RequestMapping(value = "/updateOutletPost/{oid}", method = RequestMethod.POST)
	public ModelAndView updateOutletPost(@PathVariable String oid, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/updateOutletPost/{oid} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/Viewoutlet");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.updateOutletPost(dbBean, oid, user_id);
		return model;
	}

	@RequestMapping(value = "/getOutletExistance")
	public @ResponseBody Object getOutletExistance(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getOutletExistance api");
		log.info("exisit or no");
		String name = request.getParameter("name");
		String dealer_id = request.getParameter("dealer_id");
		log.info(dealer_id + "dealer_id");
		log.info(name + "name");
		DatabaseManagementBean outletList = dbDao.getoutletexist(dbBean, name, dealer_id);
		return outletList;
	}

	@RequestMapping(value = "/getOutletIdExistance")
	public @ResponseBody Object getOutletIdExistance(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getOutletIdExistance api");
		log.info("exisit or no");
		String name = request.getParameter("name");
		log.info(name + "name");
		DatabaseManagementBean outletList = dbDao.getoutletIdexist(dbBean, name);
		return outletList;
	}

	@RequestMapping("weightage")
	public ModelAndView weightageScore(DatabaseManagementBean dbBean) {
		log.info("/weightage api");
		ModelAndView mv = new ModelAndView("addweightage");
		List<DatabaseManagementBean> sectionList = dbDao.getSectionList(dbBean);
		mv.addObject("sectionList", sectionList);
		List<DatabaseManagementBean> weightageList = dbDao.getActiveWeightageList(dbBean);
		mv.addObject("weightageList", weightageList);
		List<DatabaseManagementBean> inactiveWeightageList = dbDao.getInactiveWeightageList(dbBean);
		mv.addObject("inactiveWeightageList", inactiveWeightageList);
		return mv;
	}

	@RequestMapping(value = "/getWeightageExistance")
	public @ResponseBody Object getWeightageExistance(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getWeightageExistance api");
		String year = request.getParameter("year");
		String section_id = request.getParameter("section_id");
		log.info("year" + year);
		log.info("section_id" + section_id);
		DatabaseManagementBean sectionList = dbDao.getWeighageExistance(dbBean, year, section_id);
		return sectionList;

	}

	@RequestMapping(value = "/addWeightage", method = RequestMethod.POST)
	public ModelAndView addWeightage(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/addWeightage api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/weightage");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.addWeightage(dbBean, user_id);
		return model;
	}

	@RequestMapping("editWeightage/{sk_id}")
	public ModelAndView editWeightage(@PathVariable String sk_id, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) {
		log.info("/editWeightage/{sk_id} api");
		ModelAndView mv = new ModelAndView("editweightage");
		List<DatabaseManagementBean> sectionList = dbDao.getSectionList(dbBean);
		mv.addObject("sectionList", sectionList);
		log.info("weightage in id" + sk_id);
		dbDao.getWeightageDetailsById(dbBean, sk_id);
		request.setAttribute("sk_id", dbBean.getSk_id());
		request.setAttribute("year", dbBean.getYear());
		request.setAttribute("sk_section_id", dbBean.getSk_section_id());
		request.setAttribute("section_name", dbBean.getSection_name());
		request.setAttribute("weightage", dbBean.getWeightage());
		return mv;
	}

	@RequestMapping(value = "updateWeightage", method = RequestMethod.POST)
	public ModelAndView updateWeightage(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) throws IOException {
		log.info("/updateWeightage api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/weightage");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.updateWeightage(dbBean, user_id);
		return model;
	}

	@RequestMapping("deleteWeightage/{sk_id}")
	public ModelAndView deleteWeightage(@PathVariable String sk_id, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/deleteWeightage/{sk_id} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/weightage");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.deleteWeightage(dbBean, sk_id, user_id);
		return model;
	}

	@RequestMapping("restoreWeightage/{sk_id}")
	public ModelAndView restoreWeightage(@PathVariable String sk_id, HttpServletRequest request,
			HttpServletResponse response, DatabaseManagementBean dbBean) throws IOException {
		log.info("/restoreWeightage/{sk_id} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/weightage");
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("userId");
		dbDao.restoreWeightage(dbBean, sk_id, user_id);
		return model;
	}

}
