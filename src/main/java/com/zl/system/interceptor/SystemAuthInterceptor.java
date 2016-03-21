package com.zl.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author monee 拦截器
 *
 */
public class SystemAuthInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(SystemAuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.debug(request.getRequestURI());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && modelAndView.getViewName() != null)
			logger.debug("view-----------" + modelAndView.getViewName());
		super.postHandle(request, response, handler, modelAndView);
	}
}
