package win.leizhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;

@Controller
@RequestMapping(value = "test")
@Api(hidden = true, value = "hiddenREST", position = 10000)
public class HomeController {

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("index");
	}

	/**
	 * 测试页面
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "", "indexTest" }, method = RequestMethod.GET)
	public String testIndex(String param) {

		return "../../indexTest";
	}

}
