package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.IncidentMapper;
import dao.MybatisMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by alex on 3/12/15.
 */
public class Incident {

    protected Integer id;
    protected Integer referenceId;
    protected Date date;
    protected String timeOfDay;
    protected String type;
    protected String action;
    protected Double latitude;
    protected Double longitude;
    protected String closestCountry;
    protected String waterCountry;
    protected String locationDescription;
    protected String vesselName;
    protected String vesselType;
    protected String vesselCountry;
    protected String vesselStatus;
    protected Boolean violenceDummy;

    public Incident() {}

    public Incident(Integer id, Integer referenceId, Date date, String timeOfDay, String type,
                    String incidentType, String action, Double latitude, Double longitude,
                    String closestCountry, String waterCountry, String locationDescription,
                    String vesselName, String vesselType, String vesselCountry,
                    String vesselStatus, Boolean violenceDummy) {
        this.id = id;
        this.referenceId = referenceId;
        this.date = date;
        this.timeOfDay = timeOfDay;
        this.type = type;
        this.action = action;
        this.latitude = latitude;
        this.longitude = longitude;
        this.closestCountry = closestCountry;
        this.waterCountry = waterCountry;
        this.locationDescription = locationDescription;
        this.vesselName = vesselName;
        this.vesselType = vesselType;
        this.vesselCountry = vesselCountry;
        this.vesselStatus = vesselStatus;
        this.vesselType = vesselType;
        this.violenceDummy = violenceDummy;
    }

    public static List<Incident> getIncidents() {
        List<Incident> incidents;

        MybatisMapper mapper = new MybatisMapper();
        SqlSession session = mapper.getSession();
        IncidentMapper incidentMapper = session.getMapper(IncidentMapper.class);

        incidents = incidentMapper.getIncidents();

        return incidents;
    }

    public static List<Incident> getIncidents(IncidentFilter incidentFilter) {
        MybatisMapper mapper = new MybatisMapper();
        SqlSession session = mapper.getSession();
        IncidentMapper incidentMapper = session.getMapper(IncidentMapper.class);

        List<Incident> incidents = new ArrayList<>();

        try {
            incidents = incidentMapper.getIncidentsWithFilter(incidentFilter);
        } finally {
            session.close();
        }

        return incidents;
    }

    /*
        Convert this object to a GeoJson Feature
     */
    public JsonNode toGeoJsonFeature() {
        JsonNodeFactory nodeFactory = new ObjectMapper().getNodeFactory();
        ObjectNode feature = nodeFactory.objectNode();
        ObjectNode geometry = nodeFactory.objectNode();
        ArrayNode coordinates = nodeFactory.arrayNode();
        ObjectNode properties = nodeFactory.objectNode();

        feature.put("type", "Feature");
        geometry.put("type", "Point");
        coordinates.add(this.longitude);
        coordinates.add(this.latitude);
        geometry.put("coordinates", coordinates);
        feature.put("geometry", geometry);

        feature.put("properties", toJson(this));

        return feature;
    }

    public static JsonNode toFeatureCollection(List<Incident> incidents) {
        JsonNodeFactory nodeFactory;
        nodeFactory = new ObjectMapper().getNodeFactory();
        ObjectNode featureCollection = nodeFactory.objectNode();
        ArrayNode features = nodeFactory.arrayNode();

        featureCollection.put("type", "FeatureCollection");

        for(Incident i : incidents) {
            features.add(i.toGeoJsonFeature());
        }

        featureCollection.put("features", features);

        return featureCollection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getClosestCountry() {
        return closestCountry;
    }

    public void setClosestCountry(String closestCountry) {
        this.closestCountry = closestCountry;
    }

    public String getWaterCountry() {
        return waterCountry;
    }

    public void setWaterCountry(String waterCountry) {
        this.waterCountry = waterCountry;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getVesselType() {
        return vesselType;
    }

    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }

    public String getVesselCountry() {
        return vesselCountry;
    }

    public void setVesselCountry(String vesselCountry) {
        this.vesselCountry = vesselCountry;
    }

    public String getVesselStatus() {
        return vesselStatus;
    }

    public void setVesselStatus(String vesselStatus) {
        this.vesselStatus = vesselStatus;
    }

    public Boolean getViolenceDummy() {
        return violenceDummy;
    }

    public void setViolenceDummy(Boolean violenceDummy) {
        this.violenceDummy = violenceDummy;
    }
}
