package models;

/**
 * Created by alex on 12/24/14.
 */

import java.util.List;
import dao.EventMapper;
import dao.MybatisMapper;
import org.apache.ibatis.session.SqlSession;

public class Country {
    private Integer id;
    private Integer cowId;
    private String name;
    private String abbreviation;

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
        MybatisMapper mapper = new MybatisMapper();
        SqlSession session = mapper.getSession();
        EventMapper eventMapper = session.getMapper(EventMapper.class);

        List<Country> countries;

        try {
            countries = eventMapper.getCountries();
        } finally {
            session.close();
        }

        return countries;
    }
}
