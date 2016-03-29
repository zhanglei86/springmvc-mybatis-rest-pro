package win.leizhang.service.datasource1;

import java.util.List;

import win.leizhang.base.BaseServiceI;
import win.leizhang.model.datasource1.TCountry;

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

