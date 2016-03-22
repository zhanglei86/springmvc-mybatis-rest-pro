package com.zl.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "auth")
public class TestLoginController {

	private Logger logger = LoggerFactory.getLogger(TestLoginController.class);

	@RequestMapping(value = { "", "login" }, method = RequestMethod.GET)
	public String testIndex(Model model, HttpServletRequest request) throws Exception {
		logger.debug("…………………………………………………开始登录…………………………………………………………………");
		Subject subject = SecurityUtils.getSubject();
		logger.debug("………………………………………………subject…………………………………………………………………" + subject);
		Object object = subject.getPrincipal();
		// Session session = subject.getSession();
		if (object != null) {
			return "redirect:/auth/success";
		}
		return "auth/login";

	}

	@RequestMapping(value = { "success" })
	public String success(Model model, HttpServletRequest request) throws Exception {
		Object object = SecurityUtils.getSubject().getPrincipal();
		if (object == null) {
			logger.debug("…………………………………………………未登陆…………………………………………………………………");
			model.addAttribute("msg", "未登陆，请先登陆!");
			return "auth/login";
		} else {
			logger.debug("…………………………………………………登录OK…………………………………………………………………");
			model.addAttribute("msg", "登录OK!");
			return "auth/success";

		}
	}

	@RequestMapping(value = { "logout" })
	public String logout(Model model, HttpServletRequest request) throws Exception {
		logger.debug("…………………………………………………注销中…………………………………………………………………");
		SecurityUtils.getSubject().logout();
		model.addAttribute("msg", "注销完成OK!");
		return "auth/login";
	}

}
