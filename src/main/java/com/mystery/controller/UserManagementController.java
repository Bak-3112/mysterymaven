package com.mystery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.MysteryShoppingVisitsBean;
import com.mystery.beans.RegionBean;
import com.mystery.beans.UserBean;
import com.mystery.dao.DatabaseManagementDao;
import com.mystery.dao.UserManagementDao;


@Controller
public class UserManagementController {

	@Autowired
	UserManagementDao userDao;
	@Autowired
	DatabaseManagementDao dbDao;
	
	@RequestMapping("userCreate")
	public ModelAndView createUser(HttpServletRequest request, HttpServletResponse response, UserBean uBean,DatabaseManagementBean dbBean){
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

		String email = request.getParameter("email");
		
	    UserBean  userExisit = userDao.getUserMailExistance(uBean, email);
		return userExisit;
	}

	@RequestMapping(value = "/getUserMailExisitanceByuserid")
	public @ResponseBody Object getUserMailExisitanceByuserid(HttpServletRequest request, HttpServletResponse response, UserBean uBean) 
	{

		String email = request.getParameter("email");
		String userid = request.getParameter("userid");
		
	    UserBean  userExisit = userDao.getUserMailExistanceByuserid(uBean, email,userid);
		return userExisit;
	}





	@RequestMapping(value = "/userCreate", method = RequestMethod.POST)
	public ModelAndView SaveUsers(UserBean uBean, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/usersView");
		 userDao.saveUser(uBean);
		 return model;
	}



	@RequestMapping("usersView")
	public ModelAndView viewUser(HttpServletRequest request,HttpServletResponse response, UserBean uBean){
	ModelAndView mv=new ModelAndView("usersview");
	List<UserBean> usersList = userDao.getUsers(uBean);
	mv.addObject("usersList", usersList);
	List<UserBean> inactiveUsersList = userDao.getInactiveUsers(uBean);
	mv.addObject("inactiveUsersList", inactiveUsersList);
	return mv;
	}

	@RequestMapping("editUser/{userId}")
	public ModelAndView userEdit(UserBean uBean, @PathVariable String userId,HttpServletRequest request, HttpServletResponse response,DatabaseManagementBean dbBean){
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
	System.out.println("dealer id"+uBean1.getDealer_id());
	String brand_id=uBean1.getBrand_id();
	String region_id=uBean1.getRegion_id();
	List<UserBean>dealerDetails = userDao.GetDealerByRegionId(uBean, brand_id,region_id);
	List<UserBean>dealerList  = new ArrayList<UserBean>();
	 for(UserBean dealer:dealerDetails)
	{
		String dealer_id = dealer.getDealer_id();
		System.out.println("dealerId" + dealer_id);
		dealerList.add(userDao.getDealerName(uBean, dealer_id));
	}
	 mv.addObject("dealerList", dealerList);

	return mv;

	};

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(UserBean uBean,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		
		model = new ModelAndView("redirect:/usersView");
		 userDao.updateUser(uBean);
		
		return model;
	}

	@RequestMapping("userDelete/{userId}")
	public ModelAndView userDelete(UserBean uBean,@PathVariable String userId, HttpServletRequest request, HttpServletResponse response){
	ModelAndView mv=new ModelAndView("redirect:/usersView");

	userDao.deleteUser(uBean,userId);

	return mv;

	}

	@RequestMapping("restoreUser/{userId}")
	public ModelAndView restoreUser(UserBean uBean,@PathVariable String userId, HttpServletRequest request, HttpServletResponse response){
	ModelAndView mv=new ModelAndView("redirect:/usersView");
	userDao.restoreUser(uBean,userId);

	return mv;
	}

	@RequestMapping("roles")
	public ModelAndView CreateRoles(HttpServletRequest request, HttpServletResponse response, UserBean uBean){
	ModelAndView mv=new ModelAndView("addroles");
	return mv;
	}

	@RequestMapping(value = "/GetDealerByRegionId")
	public @ResponseBody Object GetDealerByRegionId(HttpServletRequest request, HttpServletResponse response, UserBean uBean) 
	{

		String brand_id = request.getParameter("brand_id");
		String region_id = request.getParameter("region_id");
	    List<UserBean>dealerDetails = userDao.GetDealerByRegionId(uBean, brand_id,region_id);
	    List<UserBean>dealerList  = new ArrayList<UserBean>();
		 for(UserBean dealer:dealerDetails)
		{
	    	String dealer_id = dealer.getDealer_id();
			System.out.println("dealerId" + dealer_id);
			dealerList.add(userDao.getDealerName(uBean, dealer_id));
		}
	    return dealerList;

	}


	@RequestMapping(value = "/user_type", method = RequestMethod.POST)
	public ModelAndView saveUserType(UserBean uBean, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/userCreate");
		 userDao.saveUserType(uBean);
		 return model;
	}



	@RequestMapping(value = "/checkUserTypeExist")
	public @ResponseBody Object checkUserTypeExist(HttpServletRequest request, HttpServletResponse response, UserBean uBean) 
	{

		String user_type = request.getParameter("user_type");
		
	    UserBean  userTypeExisit = userDao.checkUserTypeExist(uBean, user_type);
		return userTypeExisit;
	}
}
