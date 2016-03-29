package win.leizhang.service.datasource1.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import win.leizhang.base.BaseServiceImpl;
import win.leizhang.model.datasource1.TCountry;
import win.leizhang.service.datasource1.CountryService;

/**
 * @author liuzh_3nofxnp
 * @since 2015-09-19 17:17
 */
@Service("countryService")
public class CountryServiceImpl extends BaseServiceImpl<TCountry> implements CountryService {

	@Override
	public List<TCountry> selectByCountry(TCountry country, int page, int rows) {
		Example example = new Example(TCountry.class);
		Example.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotEmpty(country.getCountryname())) {
			criteria.andLike("countryname", "%" + country.getCountryname() + "%");
		}
		if (StringUtil.isNotEmpty(country.getCountrycode())) {
			criteria.andLike("countrycode", "%" + country.getCountrycode() + "%");
		}
		if (country.getId() != null) {
			criteria.andEqualTo("id", country.getId());
		}
		// 分页查询
		PageHelper.startPage(page, rows);
		return selectByExample(example);
	}

	@Override
	public boolean isExist(String countryName) {
		TCountry currentTCountry = new TCountry();
		currentTCountry.setCountryname(countryName);
		TCountry tCountry = selectByOther(currentTCountry);
		if (tCountry != null) {
			return true;
		} else {
			return false;
		}
	}

}
