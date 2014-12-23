package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.db.DB;
import play.libs.Json;
import play.libs.Json.*;

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

    public Event() { }

    public Event(Integer id, DateTime occurredOn, Double latitude, Double longitude) {
        this.id = id;
        //this.occurredOn = occurredOn;
        this.latitude = latitude;
        this.longitude = longitude;
        //this.closestCoastalState = closestCoastalState;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Date getOccurredOnDate() { return occurredOnDate; }

    public void setOccurredOnDate(Date occurredOnDate) { this.occurredOnDate = occurredOnDate; }

    public Time getOccurredOnTime() { return occurredOnTime; }

    public void setOccurredOnTime(Time occurredOnTime) { this.occurredOnTime = occurredOnTime; }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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
        //properties.put("occurredOnDate", this.occurredOn.getMonthOfYear() + "/" + this.occurredOn.getDayOfMonth() + "/" + this.occurredOn.getYear());
        //properties.put("occurredOnTime", this.occurredOn.getHourOfDay() + ":" + this.occurredOn.getMinuteOfHour());
        properties.put("closestCoastalState", this.closestCoastalState);

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

    public static List<Event> getAll(){
        List<Event> events = new ArrayList<>();
        Connection conn = DB.getConnection();
        ResultSet rs;
        Statement statement;
        String query = "select e.id, e.occurred_on, ST_X(e.location) AS longitude, ST_Y(e.location) AS latitude, c.name as closest_coastal_state, c.abbreviation as closest_coastal_state_abbreviation from event as e join country as c on e.closest_coastal_state = c.cow_id;";
        Event event;
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        try{
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

//            while(rs.next()) {
//                event = new Event(Integer.parseInt(rs.getString("id")),
//                        dateTimeFormatter.parseDateTime(rs.getString("occurred_on")),
//                        Double.parseDouble(rs.getString("latitude")),
//                        Double.parseDouble(rs.getString("longitude"))
//                        //rs.getString("closest_coastal_state")
//                );
//                events.add(event);
//            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public static List<Event> getByFilter(EventFilter eventFilter) {
        List<Event> events = new ArrayList<>();
        Connection conn = DB.getConnection();
        ResultSet rs;
        Statement statement;
        String query = "SELECT e.id, e.occurred_on, e.ST_X(location) AS longitude, e.ST_Y(location) AS latitude, c.name as closest_coastal_state, c.abbreviation as closest_coastal_state_abbreviation FROM event as e JOIN country AS c on e.closest_coastal_state = c.cow_id WHERE e.occurred_on > to_timestamp('" + eventFilter.beginDate + "', 'YYYY-MM-dd') AND e.occurred_on <= to_timestamp('" + eventFilter.endDate + "', 'YYYY-MM-dd');";
        Event event;
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        try{
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

//            while(rs.next()) {
//                event = new Event(Integer.parseInt(rs.getString("id")),
//                        dateTimeFormatter.parseDateTime(rs.getString("occurred_on")),
//                        Double.parseDouble(rs.getString("latitude")),
//                        Double.parseDouble(rs.getString("longitude"))
//                        //rs.getString("closest_coastal_state")
//                );
//                events.add(event);
//            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return events;

    }


}
