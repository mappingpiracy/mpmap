package dao;

import models.Event;
import models.EventFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 12/23/14.
 */
public interface EventMapper {
    public List<Event> getEvents();
    //public List<Event> getEvents(@Param("filterMap") Map<String, Object> filterMap);
    public List<Event> getEvents(@Param("eventFilter") EventFilter eventFilter);
}
