 package com.mystery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.mystery.beans.CityBean;
import com.mystery.beans.CountryBean;
import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.MysteryShoppingVisitsBean;
import com.mystery.beans.RegionBean;
import com.mystery.beans.StateBean;
import com.mystery.beans.TestBean;
import com.mystery.beans.UserBean;
import com.mystery.dao.DatabaseManagementDao;
import com.mystery.dao.UserManagementDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserManagementController {

	
	@Autowired
	UserManagementDao userDao;
	@Autowired
	DatabaseManagementDao dbDao;
	
	@RequestMapping("userCreate")
	public ModelAndView createUser(HttpServletRequest request, HttpServletResponse response, UserBean uBean,DatabaseManagementBean dbBean){
	log.info("/userCreate api = "+ new Gson().toJson(uBean));
	ModelAndView mv=new ModelAndView("createuser");
	List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
	mv.addObject("activebrandList", activebrandList);
	List<UserBean>userTypeList = userDao.getUserType(uBean);
	mv.addObject("userTypeList", userTypeList);
	List<UserBean> roleList = userDao.getRoles(uBean);
	mv.addObject("roleList", roleList);
	List<UserBean>regionList = userDao.getRegionList(uBean);
	mv.addObject("regionList", regionList);

	return mv;
	}

	@RequestMapping(value = "/getUserMailExisitance")
	public @ResponseBody Object getDealerExistance(HttpServletRequest request, HttpServletResponse response, UserBean uBean) 
	{
		log.info("/getUserMailExisitance api = "+ new Gson().toJson(uBean));
		String email = request.getParameter("email");
		
	    UserBean  userExisit = userDao.getUserMailExistance(uBean, email);
		return userExisit;
	}

	@RequestMapping(value = "/getUserMailExisitanceByuserid")
	public @ResponseBody Object getUserMailExisitanceByuserid(HttpServletRequest request, HttpServletResponse response, UserBean uBean) 
	{
		log.info("/getUserMailExisitanceByuserid api = "+ new Gson().toJson(uBean));
		String email = request.getParameter("email");
		String userid = request.getParameter("userid");
		
	    UserBean  userExisit = userDao.getUserMailExistanceByuserid(uBean, email,userid);
		return userExisit;
	}





	@RequestMapping(value = "/userCreate", method = RequestMethod.POST)
	public ModelAndView SaveUsers(UserBean uBean, HttpServletRequest request, HttpServletResponse response) {
		log.info("/userCreate api = "+ new Gson().toJson(uBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/usersView");
		 userDao.saveUser(uBean);
		 return model;
	}



	@RequestMapping("usersView")
	public ModelAndView viewUser(HttpServletRequest request,HttpServletResponse response, UserBean uBean){
		log.info("/usersView api"+new Gson().toJson(uBean));
		ModelAndView mv=new ModelAndView("usersview");
	List<UserBean> usersList = userDao.getUsers(uBean);
	mv.addObject("usersList", usersList);
	List<UserBean> inactiveUsersList = userDao.getInactiveUsers(uBean);
	mv.addObject("inactiveUsersList", inactiveUsersList);
	return mv;
	}

	@RequestMapping("editUser/{userId}")
	public ModelAndView userEdit(UserBean uBean, @PathVariable String userId,HttpServletRequest request, HttpServletResponse response,DatabaseManagementBean dbBean){
		log.info("/editUser/{userId} api"+new Gson().toJson(uBean));
		ModelAndView mv=new ModelAndView("usersedit");
	List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
	mv.addObject("activebrandList", activebrandList);
	List<UserBean>userTypeList = userDao.getUserType(uBean);
	mv.addObject("userTypeList", userTypeList);
	List<UserBean> roleList = userDao.getRoles(uBean);
	mv.addObject("roleList", roleList);
	List<UserBean>regionList = userDao.getRegionList(uBean);
	mv.addObject("regionList", regionList);
	UserBean uBean1=userDao.getUsersById(uBean,userId);
	request.setAttribute("sk_user_id", uBean1.getSk_user_id());
	request.setAttribute("user_type_id", uBean1.getUser_type_id());
	request.setAttribute("user_type", uBean1.getUser_type());
	request.setAttribute("brand_id", uBean1.getBrand_id());
	request.setAttribute("brand_name", uBean1.getBrand_name());
	request.setAttribute("regionId", uBean1.getRegion_id());
	request.setAttribute("region_name", uBean1.getRegion_name());
	request.setAttribute("dealerId", uBean1.getDealer_id());
	request.setAttribute("dealer_name", uBean1.getDealer_name());
	request.setAttribute("sk_role_id", uBean1.getSk_role_id());
	request.setAttribute("role_name", uBean1.getRole_name());
	request.setAttribute("first_name", uBean1.getFirst_name());
	request.setAttribute("last_name", uBean1.getLast_name());
	request.setAttribute("email", uBean1.getEmail());
	request.setAttribute("password", uBean1.getPassword());
	request.setAttribute("mobile", uBean1.getMobile());
	request.setAttribute("user_status", uBean1.getUser_status());
	log.info("dealer id"+uBean1.getDealer_id());
	String brand_id=uBean1.getBrand_id();
	String region_id=uBean1.getRegion_id();
	List<UserBean>dealerDetails = userDao.GetDealerByRegionId(uBean, brand_id,region_id);
	List<UserBean>dealerList  = new ArrayList<UserBean>();
	 for(UserBean dealer:dealerDetails)
	{
		String dealer_id = dealer.getDealer_id();
		log.info("dealerId" + dealer_id);
		dealerList.add(userDao.getDealerName(uBean, dealer_id));
	}
	 mv.addObject("dealerList", dealerList);

	return mv;

	};

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(UserBean uBean,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		log.info("/updateUser api"+new Gson().toJson(uBean));
		model = new ModelAndView("redirect:/usersView");
		 userDao.updateUser(uBean);
		
		return model;
	}

	@RequestMapping("userDelete/{userId}")
	public ModelAndView userDelete(UserBean uBean,@PathVariable String userId, HttpServletRequest request, HttpServletResponse response){
	ModelAndView mv=new ModelAndView("redirect:/usersView");
	log.info("/userDelete/{userId} api"+new Gson().toJson(uBean));
	userDao.deleteUser(uBean,userId);

	return mv;

	}

	@RequestMapping("restoreUser/{userId}")
	public ModelAndView restoreUser(UserBean uBean,@PathVariable String userId, HttpServletRequest request, HttpServletResponse response){
		log.info("/restoreUser/{userId} api"+new Gson().toJson(uBean));
		ModelAndView mv=new ModelAndView("redirect:/usersView");
	userDao.restoreUser(uBean,userId);

	return mv;
	}

	@RequestMapping("roles")
	public ModelAndView CreateRoles(HttpServletRequest request, HttpServletResponse response, UserBean uBean){
		log.info("/roles api"+new Gson().toJson(uBean));
		ModelAndView mv=new ModelAndView("addroles");
	return mv;
	}

	@RequestMapping(value = "/GetDealerByRegionId")
	public @ResponseBody Object GetDealerByRegionId(HttpServletRequest request, HttpServletResponse response, UserBean uBean) 
	{
		log.info("/GetDealerByRegionId api"+new Gson().toJson(uBean));
		String brand_id = request.getParameter("brand_id");
		String region_id = request.getParameter("region_id");
	    List<UserBean>dealerDetails = userDao.GetDealerByRegionId(uBean, brand_id,region_id);
	    List<UserBean>dealerList  = new ArrayList<UserBean>();
		 for(UserBean dealer:dealerDetails)
		{
	    	String dealer_id = dealer.getDealer_id();
			log.info("dealerId" + dealer_id);
			dealerList.add(userDao.getDealerName(uBean, dealer_id));
		}
	    return dealerList;

	}


	@RequestMapping(value = "/user_type", method = RequestMethod.POST)
	public ModelAndView saveUserType(UserBean uBean, HttpServletRequest request, HttpServletResponse response) {
		log.info("/user_type api"+new Gson().toJson(uBean));
		ModelAndView model = null;
		model = new ModelAndView("redirect:/userCreate");
		 userDao.saveUserType(uBean);
		 return model;
	}



	@RequestMapping(value = "/checkUserTypeExist")
	public @ResponseBody Object checkUserTypeExist(HttpServletRequest request, HttpServletResponse response, UserBean uBean) 
	{		
		log.info("/checkUserTypeExist api"+new Gson().toJson(uBean));
		String user_type = request.getParameter("user_type");
		
	    UserBean  userTypeExisit = userDao.checkUserTypeExist(uBean, user_type);
		return userTypeExisit;
	}
	
	@RequestMapping("/getStateName")
	public @ResponseBody Object getStateName(HttpServletRequest request, HttpServletResponse response,StateBean sBean)
			throws IOException {
		log.info("/getStateName"+new Gson().toJson(sBean));
		String country_id= request.getParameter("aid");
		System.out.println(country_id);
		List<StateBean> stateList = userDao.getState(sBean, country_id);
		log.info("stateList"+stateList);
		return stateList;
	}
	
	@RequestMapping("/getCityName")
	public @ResponseBody Object getCityName(HttpServletRequest request, HttpServletResponse response,CityBean cBean)
			throws IOException {
		String state_id= request.getParameter("cid");
		log.info("state_id"+state_id);
		List<CityBean> cityList = userDao.getCity(cBean, state_id);
		log.info("cityList"+cityList);
		return cityList;
	}
	
	@RequestMapping("/crud")
	public ModelAndView crud(CountryBean coBean,TestBean tBean) {
		log.info("/crud api");
		ModelAndView mv = new ModelAndView("crud");
		List<CountryBean> countrylist= userDao.getCountryList(coBean);
		mv.addObject("countryList", countrylist);
		List<TestBean> tList=userDao.gettList(tBean);
		mv.addObject("tbeanList", tList);
		return mv;
	}
	
	@RequestMapping(value = "/createcrud", method = RequestMethod.POST)
	public ModelAndView createCityPost(TestBean tBean, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.info("/createcrud api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/crud");
		userDao.addtest(tBean);
		return model;
	}
	
	
	@RequestMapping(value= "/editCrud/{id}")
	public ModelAndView editCrud(@PathVariable String id, HttpServletRequest request, HttpServletResponse response,TestBean tBean)
	{
		log.info("/editCrud/{id} api");
		ModelAndView mv = new ModelAndView("crudedit");
		userDao.getCrudDetails(tBean, id);
		request.setAttribute("id", tBean.getId());
		request.setAttribute("name", tBean.getName());
		request.setAttribute("email", tBean.getEmail());
		request.setAttribute("address", tBean.getAddress());
		return mv;
		
	}
	
	@RequestMapping(value = "/updatecrud/{id}", method = RequestMethod.POST)
	public ModelAndView updatecrud(@PathVariable String id, HttpServletRequest request,
			HttpServletResponse response,@RequestBody TestBean tBean) throws IOException {
		log.info("/updatecrud/{id} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/crud");
		userDao.updatecrudbyid(tBean, id);
		return model;
	}

	@RequestMapping("deletecrud/{id}")
	public ModelAndView deleteBrand(@PathVariable String id, HttpServletRequest request, HttpServletResponse response,
			TestBean tBean) throws IOException {
		log.info("/deletecrud/{id} api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/crud");
		userDao.deletecrudbyid(tBean, id);
		return model;
	}
}
