package gis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import models.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/29/14.
 */
public class GeoJsonHelper {

    /*
        Converts an event object to a GeoJson formatted feature.
        TODO: create enums for the property names to make it typesafe.
     */
    public static JsonNode eventToFeature(Event event) {
        JsonNodeFactory nodeFactory;
        nodeFactory = new ObjectMapper().getNodeFactory();
        ObjectNode feature = nodeFactory.objectNode();
        ObjectNode geometry = nodeFactory.objectNode();
        ArrayNode coordinates = nodeFactory.arrayNode();
        ObjectNode properties = nodeFactory.objectNode();

        feature.put("type", "Feature");

        geometry.put("type", "Point");
        coordinates.add(event.getLongitude());
        coordinates.add(event.getLatitude());
        geometry.put("coordinates", coordinates);

        properties.put("id", event.getId());
        properties.put("occurredOnDate", event.getOccurredOnDate().toString());
        properties.put("occurredOnTime", event.getOccurredOnTime().toString());
        properties.put("closestCoastalState", event.getClosestCountry());
        properties.put("territorialWaterStatus", event.getTerritorialWaterStatus());
        properties.put("vesselFlagCountry", event.getVesselFlagCountry());

        feature.put("geometry", geometry);
        feature.put("properties", properties);

        return feature;
    }

    /*
        Converts a list of events to a GeoJson formatted feature collection.
        TODO: figure out a clean way to keep this from calling eventToFeature repeatedly
     */
    public static JsonNode eventsToFeatureCollection(List<Event> events) {
        JsonNodeFactory nodeFactory;
        nodeFactory = new ObjectMapper().getNodeFactory();
        ObjectNode featureCollection = nodeFactory.objectNode();
        ArrayNode featureArray = nodeFactory.arrayNode();
        List<JsonNode> jsonEvents = new ArrayList<>();

        for(int i = 0; i < events.size(); i++){
            jsonEvents.add(GeoJsonHelper.eventToFeature(events.get(i)));
        }

        featureCollection.put("type", "FeatureCollection");

        for(int i = 0; i < events.size(); i++) {
            featureArray.add(jsonEvents.get(i));
        }

        featureCollection.put("features", featureArray);

        return featureCollection;
    }

}
