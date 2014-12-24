package models;

import com.fasterxml.jackson.databind.JsonNode;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 12/14/14.
 */
public class EventFilter {
    public String beginDate;
    public String endDate;
    public List<Integer> territorialWaterStatus;
    public List<Integer> closestCoastalState;
    public List<Integer> vesselCountry;

    //TODO: create enums for filter names, figure out how to use them in the scala views
    public EventFilter(JsonNode jsonNode){
        this.beginDate = jsonNode.get("beginDate").asText();
        this.endDate = jsonNode.get("endDate").asText();
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getTerritorialWaterStatus() {
        return territorialWaterStatus;
    }

    public void setTerritorialWaterStatus(List<Integer> territorialWaterStatus) {
        this.territorialWaterStatus = territorialWaterStatus;
    }

    public List<Integer> getClosestCoastalState() {
        return closestCoastalState;
    }

    public void setClosestCoastalState(List<Integer> closestCoastalState) {
        this.closestCoastalState = closestCoastalState;
    }

    public List<Integer> getVesselCountry() {
        return vesselCountry;
    }

    public void setVesselCountry(List<Integer> vesselCountry) {
        this.vesselCountry = vesselCountry;
    }

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    /*
        Check the passed json to make sure begindate and enddate are present
     */
    public static boolean validate(JsonNode jsonEventFilter){
        if(jsonEventFilter.hasNonNull("beginDate") &&
                jsonEventFilter.hasNonNull("endDate")){
            return true;
        } else {
            return false;
        }
    }

    /*
        Convert to map for use in mybatis mapping.
     */
    public Map<String, Object> toMap() {
        Map<String, Object> eventFilterMap = new HashMap<>();

        eventFilterMap.put("beginDate", "2012-01-01");
        eventFilterMap.put("endDate", "2014-01-01");

        return eventFilterMap;
    }
}
