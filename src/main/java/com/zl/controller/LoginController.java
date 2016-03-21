package com.zl.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = { "", "login" }, method = RequestMethod.GET)
	public String testIndex(String param, Model model) throws Exception {
		logger.debug("…………………………………………………开始登录…………………………………………………………………");
		Subject subject = SecurityUtils.getSubject();
		logger.debug("………………………………………………subject…………………………………………………………………" + subject);
		Object object = subject.getPrincipal();
		if (object != null) {
			return "redirect:success";
		}
		return "login";

	}

	@RequestMapping(value={"success"})
	public String success(String username,String password,Model model,HttpServletRequest request) throws Exception{
		logger.debug("…………………………………………………登录OK…………………………………………………………………");
		model.addAttribute("msg", "登录OK!");
		return "success";
	}

	@RequestMapping(value = { "logout" })
	public String logout(Model model, HttpServletRequest request) throws Exception {
		logger.debug("…………………………………………………注销中…………………………………………………………………");
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
			model.addAttribute("msg", "注销完成OK!");
		}
		return "login";
	}

}
