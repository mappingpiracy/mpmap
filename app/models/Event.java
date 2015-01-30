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

    protected Integer id;
    protected Date occurredOnDate;
    protected Time occurredOnTime;
    protected double latitude;
    protected double longitude;
    protected String closestCountry;
    protected String territorialWaterStatus;
    protected String vesselFlagCountry;

    protected String vesselStatus;

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

    public String getVesselStatus() {
        return vesselStatus;
    }

    public void setVesselStatus(String vesselStatus) {
        this.vesselStatus = vesselStatus;
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
