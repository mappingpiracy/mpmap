package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;
import play.db.DB;
import play.libs.Json;
import play.libs.Json.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/27/14.
 */
public class Event {

    private int id;
    private DateTime occurredOn;
    private double latitude;
    private double longitude;

    public Event(int id, DateTime occurredOn, Double latitude, Double longitude) {
        this.id = id;
        this.occurredOn = occurredOn;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public DateTime getOccurredOn(){
        return this.occurredOn;
    }

    public void setOccurredOn(DateTime occurredOn) {
        this.occurredOn = occurredOn;
    }

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
        properties.put("occurredOn", this.occurredOn.toString());

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
        String query = "SELECT id, occurred_on, ST_X(location) AS longitude, ST_Y(location) AS latitude FROM event where id > 6000;";
        Event event;
        try{
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {
                event = new Event(Integer.parseInt(rs.getString("id")),
                        new DateTime(),
                        Double.parseDouble(rs.getString("latitude")),
                        Double.parseDouble(rs.getString("longitude"))
                );
                events.add(event);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return events;
    }


}