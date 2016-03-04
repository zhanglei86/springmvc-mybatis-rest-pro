package com.hdy.model.external;

import javax.persistence.*;

@Table(name = "t_country")
public class TCountry {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "t_countryname")
    private String tCountryname;

    /**
     * 代码
     */
    @Column(name = "t_countrycode")
    private String tCountrycode;

    /**
     * 获取主键
     *
     * @return Id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return t_countryname - 名称
     */
    public String gettCountryname() {
        return tCountryname;
    }

    /**
     * 设置名称
     *
     * @param tCountryname 名称
     */
    public void settCountryname(String tCountryname) {
        this.tCountryname = tCountryname;
    }

    /**
     * 获取代码
     *
     * @return t_countrycode - 代码
     */
    public String gettCountrycode() {
        return tCountrycode;
    }

    /**
     * 设置代码
     *
     * @param tCountrycode 代码
     */
    public void settCountrycode(String tCountrycode) {
        this.tCountrycode = tCountrycode;
    }
}