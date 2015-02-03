package dao;

import models.Country;
import models.Event;
import models.EventFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alex on 12/23/14.
 */
public interface CountryMapper {

    public List<Country> getCountries();
}
