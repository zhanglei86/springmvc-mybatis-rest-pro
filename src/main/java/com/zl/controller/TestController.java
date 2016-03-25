package com.zl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "test")
public class TestController {

	/**
	 * 测试页面
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "", "indexTest" })
	public String testIndex(String param) {

		return "../../indexTest";
	}
}
