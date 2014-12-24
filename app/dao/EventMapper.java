package dao;

import models.Event;
import models.EventFilter;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 12/23/14.
 */
public interface EventMapper {

    //TODO: Figure out if there is a way to combine or overload these two methods

    public List<Event> getEvents();
    public List<Event> getEventsWithFilter(@Param("eventFilter") EventFilter eventFilter);
    public List<Map<Integer, String>> getCountries();
}
