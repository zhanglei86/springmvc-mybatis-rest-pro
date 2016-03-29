package win.leizhang.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "auth")
public class TestLoginController {

	private Logger logger = LoggerFactory.getLogger(TestLoginController.class);

	// 登陆前置判断
	@RequestMapping(value = { "", "login" })
	public String loginTest(Model model, HttpServletRequest request) throws Exception {
		// ensureUserIsLoggedOut();

		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if (exceptionClassName != null && !"".equals(exceptionClassName)) {
			String error = null;
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				error = "账号不存在!";
			} else if (DisabledAccountException.class.getName().equals(exceptionClassName)) {
				error = "帐号被禁用!";
			} else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
				error = "帐号被锁定!";
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				error = "用户名/密码错误";
			} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
				error = "登录失败次数过多";
			} else if (ExpiredCredentialsException.class.getName().equals(exceptionClassName)) {
				error = "凭证过期";
			} else {
				error = "其他错误：" + exceptionClassName;
			}
			model.addAttribute("msg", error);
			return "auth/login";
		}

		logger.debug("…………………………………………………开始登录…………………………………………………………………");
		Subject currentUser = ThreadContext.getSubject();
		if (currentUser == null) {
			currentUser = SecurityUtils.getSubject();
		}
		
		logger.debug("………………………………………………subject…………………………………………………………………" + currentUser);
		if (currentUser.getPrincipal() != null) {
			logger.debug("…………………………………………………/auth/login check OK!…………………………………………………………………");
			return "redirect:/auth/success";
		} else {
			logger.debug("…………………………………………………/auth/login check fail!…………………………………………………………………");
			return "auth/login";
		}
	}

	@RequestMapping(value = { "success" })
	public String success(Model model, HttpServletRequest request) throws Exception {
		if (SecurityUtils.getSubject().getPrincipal() == null) {
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
		ensureUserIsLoggedOut();
		model.addAttribute("msg", "注销完成OK!");
		return "/auth/login";
	}

	// Logout the user fully before continuing.
	private void ensureUserIsLoggedOut() {
		try {
			// Get the user if one is logged in.
			Subject currentUser = ThreadContext.getSubject();
			if (currentUser == null) {
				currentUser = SecurityUtils.getSubject();
				return;
			}
			// Log the user out and kill their session if possible.
			currentUser.logout();
			Session session = currentUser.getSession(false);
			if (session == null)
				return;
			session.stop();
		} catch (Exception e) {
			e.printStackTrace();
			// Ignore all errors, as we're trying to silently
			// log the user out.
		}
	}

}
