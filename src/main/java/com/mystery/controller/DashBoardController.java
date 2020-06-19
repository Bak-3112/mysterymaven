package com.mystery.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mystery.beans.DashboardBean;

import com.mystery.dao.DashboardDao;
import com.mystery.beans.MenuBean;

@Controller
public class DashBoardController {
	@Autowired
	DashboardDao dDao;
	
	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response,  DashboardBean dBean,MenuBean mBean) throws ParseException, IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(true);
		request.setAttribute("role_name", (String) session.getAttribute("role_name"));
		try {
			  mv = new ModelAndView("/dashboard");
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
//		 
//		 if(checkClosedVisits1.getCount().equals("0")) {
//			 if (currentmonth.contentEquals("January") || currentmonth.contentEquals("February") || currentmonth.contentEquals("March") ) {
//					year1 = Integer.parseInt(year) - 1;
//				}else {
//					year1 = Integer.parseInt(year);
//					System.out.println("year1=="+year1);
//				}
//		 }else {
//			 year1 = Integer.parseInt(year);
//		 }
//		

		 int year2 = Integer.parseInt(year);
			DashboardBean checkClosedVisits = dDao.checkCurrentMonthClosedVisits(dBean, currentmonth, dBean.getBrand_id(),year2);
            System.out.println("closed visits count in controller"+checkClosedVisits.getCount());
		while (checkClosedVisits.getCount().equals("0")) {
			i = i + 1;
			System.out.println("month" + currentmonth);
			currentmonth = sdf.format(now.getTime());
			now.add(Calendar.MONTH, -1);
			System.out.println("currentmonth=="+currentmonth);
			
//			 if (currentmonth.contentEquals("January") || currentmonth.contentEquals("February") || currentmonth.contentEquals("March") ) {
//					year1 = Integer.parseInt(year);
//				}else {
//					year1 = Integer.parseInt(year)-1;
//				}
			checkClosedVisits = dDao.checkCurrentMonthClosedVisits(dBean, currentmonth, dBean.getBrand_id(),year2);
			if (i == 4) {
				break;
			}
		}
		request.setAttribute("currentmonth", currentmonth);
		request.setAttribute("year2", year2);
		
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
		
		}catch (Exception e) {
			  mv = new ModelAndView("redirect:/");
		}
		return mv;
	}
	String dashboardURL="localhost:8080/";
	public String checkaccessEY(String access, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		DashboardBean dbBean = new DashboardBean();
		HttpSession session = request.getSession(true);
		String user_id = (String) session.getAttribute("user_type");
		dbBean.setUser_type_id(user_id);
		DashboardBean gettype = null ;
		try {
			gettype= dDao.getUserType(dbBean);
		}catch (Exception e) {
			// TODO: handle exception
		}
		String data = "";
		if (gettype.getUser_type().equals("BMW")) {
			System.out.println(gettype.getUser_type());
			response.sendRedirect(dashboardURL + access);
		} else {
			System.out.println(session.getAttribute("role_id"));
			String role_id = (String) session.getAttribute("role_id");
			MenuBean mBean = new MenuBean();
			dDao.getroleMenuById(mBean, role_id);
			String menu_list = mBean.getMenu();
			DashboardBean getMenus = dDao.getMenuByRoleid(dbBean, menu_list, access);
			if (getMenus.getCount().equals("0")) {
				System.out.println("hello");
				response.sendRedirect(dashboardURL + "logout");
			}
		}
		
		return data;
	}
}
