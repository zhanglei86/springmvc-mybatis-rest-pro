package win.leizhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "test")
public class HomeController {

	@RequestMapping("index")
	public ModelAndView home() {
		return new ModelAndView("index");
	}

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
