package com.zl.service;

import java.util.List;

import com.zl.base.BaseServiceI;
import com.zl.model.datasource1.TCountry;

/**
 * @author liuzh_3nofxnp
 * @since 2015-09-19 17:17
 */
public interface CountryService extends BaseServiceI<TCountry> {

    /**
     * 根据条件分页查询
     *
     * @param country
     * @param page
     * @param rows
     * @return
     */
    List<TCountry> selectByCountry(TCountry country, int page, int rows);

}
