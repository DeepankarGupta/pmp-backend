package com.nagarro.yourmart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.yourmart.service.AdminService;

@Controller
@RequestMapping(value="admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String redirectToHomePage() {
		return "redirect:/admin/home";
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String getLoginPage(ModelMap model, HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String authenticate(@RequestParam("username") String userId, @RequestParam("password") String password, ModelMap model, HttpServletRequest request) {
		String adminUserId = adminService.authenticate(userId, password);
		request.getSession().setAttribute("admin",adminUserId);
		return "redirect:/admin/home"; 
	}
	
	@RequestMapping(value="home", method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("admin")!=null) {
			return "home";
		}
		return "redirect:/admin/login";
	}
	
	@RequestMapping(value="logout",method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("admin")!=null) {
			session.setAttribute("admin", null);
		}
		return "redirect:/admin/login";
	}
}
