package com.zl.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zl.model.datasource1.TCountry;
import com.zl.service.datasource1.CountryService;

/**
 * @author liuzh_3nofxnp
 * @since 2015-09-19 17:15
 */
@Controller
public class CountryController {
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	private String page_list = "index";

	private String redirect_list = "redirect:list";

	@RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String hello() {
		return "你好！hello";
	}

	@RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
	public @ResponseBody String say(@PathVariable(value = "msg") String msg) {
		return "{\"msg\":\"you say:'" + msg + "'\"}";
	}

	@RequestMapping(value = "/country/{id:\\d+}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getCountry(@PathVariable("id") int id) {
		logger.info("获取国家id=" + id);
		TCountry tCountry = new TCountry();
		tCountry = countryService.selectByKey(id);
		return tCountry.getCountryname();
	}

	@ResponseBody
	@RequestMapping("/countrys")
	public List<TCountry> selectAll() {
		return countryService.selectAll();
	}

	@RequestMapping(value = { "list", "index", "index.html", "" })
	public ModelAndView getList(TCountry country, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows) {
		ModelAndView result = new ModelAndView(page_list);
		List<TCountry> countryList = countryService.selectByCountry(country, page, rows);
		result.addObject("pageInfo", new PageInfo<TCountry>(countryList));
		result.addObject("queryParam", country);
		result.addObject("page", page);
		result.addObject("rows", rows);
		return result;
	}

	@RequestMapping(value = "view", method = RequestMethod.GET)
	public ModelAndView view(TCountry country) {
		ModelAndView result = new ModelAndView();
		if (country.getId() != null) {
			country = countryService.selectByKey(country.getId());
		}
		result.addObject("country", country);
		return result;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(TCountry country) {
		ModelAndView result = new ModelAndView(redirect_list);
		if (country.getId() != null) {
			countryService.updateAll(country);
		} else {
			countryService.save(country);
		}
		return result;
	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		countryService.delete(id);
		return redirect_list;
	}

}
