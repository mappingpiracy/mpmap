package models;

/**
 * Created by alex on 12/24/14.
 */

import java.util.List;

import dao.CountryMapper;
import dao.MybatisMapper;
import org.apache.ibatis.session.SqlSession;

public class Country {
    protected Integer id;
    protected Integer cowId;
    protected String name;
    protected String abbreviation;
    protected static MybatisMapper mybatisMapper;

    public Country() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCowId() {
        return cowId;
    }

    public void setCowId(Integer cowId) {
        this.cowId = cowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static List<Country> getCountries() {
        Country.mybatisMapper = MybatisMapper.getInstance();
        SqlSession session = mybatisMapper.getSqlSession();
        CountryMapper countryMapper = session.getMapper(CountryMapper.class);

        List<Country> countries;

        try {
            countries = countryMapper.getCountries();
        } finally {
            session.close();
        }

        return countries;
    }
}
