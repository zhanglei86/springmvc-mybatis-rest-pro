package com.hdy.service;

import java.util.List;

import com.hdy.base.BaseServiceI;
import com.hdy.model.internal.TCountry;

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
