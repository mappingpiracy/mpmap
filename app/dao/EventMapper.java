package dao;

import models.Event;

import java.util.List;

/**
 * Created by alex on 12/23/14.
 */
public interface EventMapper {
    public List<Event> getEvents();
}
