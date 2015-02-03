package dao;

import models.Country;
import models.Event;
import models.EventFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alex on 12/23/14.
 */
public interface EventMapper {

    //TODO: Figure out if there is a way to combine or overload the two event methods

    public List<Event> getEvents();
    public List<Event> getEventsWithFilter(@Param("eventFilter") EventFilter eventFilter,
                                           @Param("closestCountry") List<Integer> closestCountry);
}
