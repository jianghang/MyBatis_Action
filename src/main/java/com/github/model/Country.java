package com.github.model;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table country
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Country {
    /**
     * id
     */
    private Integer id;

    /**
     * 国家名称
     * countryname
     */
    private String countryname;

    /**
     * 国家代码
     * countrycode
     */
    private String countrycode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname == null ? null : countryname.trim();
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode == null ? null : countrycode.trim();
    }
}