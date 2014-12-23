package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.EventMapper;
import dao.MybatisMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.db.DB;
import play.libs.Json;
import play.libs.Json.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    private String closestCoastalState;
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

    public String getClosestCoastalState() {
        return closestCoastalState;
    }

    public void setClosestCoastalState(String closestCoastalState) {
        this.closestCoastalState = closestCoastalState;
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
            Converts the event object to a geoJson feature compatible with leaflet mapping.
         */
    public JsonNode toGeoJsonFeature(){
        JsonNodeFactory nodeFactory;
        nodeFactory = new ObjectMapper().getNodeFactory();
        ObjectNode feature = nodeFactory.objectNode();
        ObjectNode geometry = nodeFactory.objectNode();
        ArrayNode coordinates = nodeFactory.arrayNode();
        ObjectNode properties = nodeFactory.objectNode();

        feature.put("type", "Feature");

        geometry.put("type", "Point");
        coordinates.add(this.longitude);
        coordinates.add(this.latitude);
        geometry.put("coordinates", coordinates);

        properties.put("id", this.id);
        properties.put("occurredOnDate", this.occurredOnDate.toString());
        properties.put("occurredOnTime", this.occurredOnTime.toString());
        properties.put("closestCoastalState", this.closestCoastalState);
        properties.put("territorialWaterStatus", this.territorialWaterStatus);
        properties.put("vesselFlagCountry", this.vesselFlagCountry);

        feature.put("geometry", geometry);
        feature.put("properties", properties);

        return feature;
    }

    /*
        Takes a list of GeoJson features and returns a featureCollection GeoJson object.
     */
    public static JsonNode toGeoJsonFeatureCollection(List<JsonNode> events) {
        JsonNodeFactory nodeFactory;
        nodeFactory = new ObjectMapper().getNodeFactory();
        ObjectNode featureCollection = nodeFactory.objectNode();
        ArrayNode featureArray = nodeFactory.arrayNode();

        featureCollection.put("type", "FeatureCollection");

        for(int i = 0; i < events.size(); i++) {
            featureArray.add(events.get(i));
        }

        featureCollection.put("features", featureArray);

        return featureCollection;
    }

    /*
        Data Access Methods
     */

    public static List<Event> getAll() {
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

    public static List<Event> getByFilter(EventFilter eventFilter) {
        MybatisMapper mapper = new MybatisMapper();
        SqlSession session = mapper.getSession();
        List<Event> events;

        try {
            events = session.selectList("app.dao.EventMapper.selectEvents");
        } finally {
            session.close();
        }

        return events;

    }


}
