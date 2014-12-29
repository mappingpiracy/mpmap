package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.EventMapper;
import dao.MybatisMapper;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by alex on 11/27/14.
 */
public class Event {

    private Integer id;
    private Date occurredOnDate;
    private Time occurredOnTime;
    private double latitude;
    private double longitude;
    private String closestCountry;
    private String territorialWaterStatus;
    private String vesselFlagCountry;

    public Event() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOccurredOnDate() {
        return occurredOnDate;
    }

    public void setOccurredOnDate(Date occurredOnDate) {
        this.occurredOnDate = occurredOnDate;
    }

    public Time getOccurredOnTime() {
        return occurredOnTime;
    }

    public void setOccurredOnTime(Time occurredOnTime) {
        this.occurredOnTime = occurredOnTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getClosestCountry() {
        return closestCountry;
    }

    public void setClosestCountry(String closestCountry) {
        this.closestCountry = closestCountry;
    }

    public String getTerritorialWaterStatus() {
        return territorialWaterStatus;
    }

    public void setTerritorialWaterStatus(String territorialWaterStatus) {
        this.territorialWaterStatus = territorialWaterStatus;
    }

    public String getVesselFlagCountry() {
        return vesselFlagCountry;
    }

    public void setVesselFlagCountry(String vesselFlagCountry) {
        this.vesselFlagCountry = vesselFlagCountry;
    }

     /*
        Data Access Methods
     */

    public static List<Event> getEvents() {
        MybatisMapper mapper = new MybatisMapper();
        SqlSession session = mapper.getSession();
        EventMapper eventMapper = session.getMapper(EventMapper.class);

        List<Event> events;

        try {
            events = eventMapper.getEvents();
        } finally {
            session.close();
        }

        return events;
    }

    public static List<Event> getEvents(EventFilter eventFilter) {
        MybatisMapper mapper = new MybatisMapper();
        SqlSession session = mapper.getSession();
        EventMapper eventMapper = session.getMapper(EventMapper.class);

        List<Event> events;


        try {
            events = eventMapper.getEventsWithFilter(eventFilter, eventFilter.getClosestCountry());
        } finally {
            session.close();
        }

        return events;
    }

}
