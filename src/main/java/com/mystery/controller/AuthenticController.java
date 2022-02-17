package com.mystery.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.mystery.api.Encryption;
import com.mystery.beans.UserBean;
import com.mystery.dao.AuthenticDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AuthenticController {

	@Autowired
	AuthenticDao aDao;

	@RequestMapping("/signin")
	public ModelAndView login() {
		log.info("/signin api");
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public ModelAndView signIn(HttpServletRequest request, HttpServletResponse response, UserBean uBean,
			RedirectAttributes redirectAttributes) {
		ModelAndView model = null;
		log.info("/signin api"+new Gson().toJson(uBean));
		aDao.signin(uBean);
		if (uBean.getStatusCode().equals("1")) {
			HttpSession session = request.getSession();
			if (request.getParameter("JSESSIONID") != null) {
				log.info("log in ifif");
				Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
				response.addCookie(userCookie);
			} else {
				log.info("login in else");
				String sessionId = session.getId();
				Cookie userCookie = new Cookie("JSESSIONID", sessionId);
				response.addCookie(userCookie);
			}
			model = new ModelAndView("redirect:/home");
			request.getSession().setAttribute("userId", uBean.getSk_user_id());
			request.getSession().setAttribute("first_name", uBean.getFirst_name());
			request.getSession().setAttribute("last_name", uBean.getLast_name());
			request.getSession().setAttribute("email", uBean.getEmail());
			request.getSession().setAttribute("password", uBean.getPassword());
			request.getSession().setAttribute("mobile", uBean.getMobile());
			request.getSession().setAttribute("region", uBean.getRegion());
			request.getSession().setAttribute("outlets", uBean.getOutlets());
			request.getSession().setAttribute("dealers", uBean.getDealers());
			request.getSession().setAttribute("role_id", uBean.getSk_role_id());
			request.getSession().setAttribute("user_type", uBean.getUser_type());
			request.getSession().setAttribute("role_for", uBean.getRole_for());
			request.getSession().setAttribute("dealers", uBean.getDealers());
			request.getSession().setAttribute("region", uBean.getRegion());

			request.getSession().setAttribute("role_name", uBean.getRole_name());

			redirectAttributes.addFlashAttribute("statusMessage", uBean.getStatusMessage());
			redirectAttributes.addFlashAttribute("statusCode", uBean.getStatusCode());
		} else {
			model = new ModelAndView("redirect:/signin");
			redirectAttributes.addFlashAttribute("statusMessage", uBean.getStatusMessage());
			redirectAttributes.addFlashAttribute("statusCode", uBean.getStatusCode());
		}

		return model;
	}

	@RequestMapping("/forgotPassword")
	public ModelAndView forgotPassword() {
		log.info("/forgotPassword api");

		ModelAndView mv = new ModelAndView("forgotpassword");
		return mv;

	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ModelAndView forgotPassword(UserBean uBean, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		log.info("/forgotPassword api"+new Gson().toJson(uBean));
		ModelAndView model = null;
		HttpSession session = request.getSession();

		aDao.forgotPassword(uBean);
		if (uBean.getStatusCode().equals("1")) {
			model = new ModelAndView("redirect:/forgotPassword");
			redirectAttributes.addFlashAttribute("statusMessage", uBean.getStatusMessage());
			redirectAttributes.addFlashAttribute("statusCode", uBean.getStatusCode());
		} else {

			model = new ModelAndView("redirect:/forgotPassword");
			redirectAttributes.addFlashAttribute("statusMessage", uBean.getStatusMessage());
			redirectAttributes.addFlashAttribute("statusCode", uBean.getStatusCode());

		}

		return model;
	}

	@RequestMapping(value = "resetPassword/{userId}", method = RequestMethod.GET)
	public ModelAndView resetPassword(@PathVariable String userId, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("/resetPassword/{userId} api"+ userId);
		ModelAndView mv = new ModelAndView("resetpassword");
		/*
		 * String decrypted_userId=""; try { String encrypted_country_id = userId;
		 * String decrypt_userId = encrypted_country_id.replace("symbol", "/");
		 * log.info("encrypted country_id="+decrypt_userId); decrypted_userId
		 * = AESCrypt.decrypt(decrypt_userId); String userId1=decrypted_userId;
		 * log.info("decrypted country id="+decrypt_userId);
		 * request.setAttribute("userId1", userId1); } catch(Exception e) {
		 * log.info("bug"+e.getMessage()); }
		 */
		String encrypted_user_id = userId;
		String decrypted_user_id = Encryption.decrypt(encrypted_user_id);
		request.setAttribute("userId1", decrypted_user_id);
		return mv;

	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPassword(UserBean uBean, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		log.info("/resetPassword api"+ new Gson().toJson(uBean));
		ModelAndView model = null;
		UserBean uBean1 = new UserBean();
		model = new ModelAndView("redirect:/signin");
		uBean1 = aDao.resetPassword(uBean);

		return model;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		log.info("/logout api");
		ModelAndView model = null;
		model = new ModelAndView("redirect:/signin");
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession(false);
		return model;
	}
}
