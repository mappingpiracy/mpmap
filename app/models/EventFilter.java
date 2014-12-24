package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import helpers.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.Logger;

import java.util.List;

/**
 * Created by alex on 12/14/14.
 */
public class EventFilter {
    private String beginDate;
    private String endDate;
    private List<Integer> closestCountry;
    private List<Integer> territorialWaterStatus;
    private List<Integer> vesselCountry;

    public EventFilter() { }

    //TODO: create enums for filter names, figure out how to use them in the scala views
    public EventFilter(JsonNode jsonNode){
        this.beginDate = jsonNode.get("beginDate").asText();
        this.endDate = jsonNode.get("endDate").asText();
        this.closestCountry = JsonHelper.arrayNodeToIntegerList((ArrayNode) jsonNode.get("closestCountry"));
        this.territorialWaterStatus = JsonHelper.arrayNodeToIntegerList((ArrayNode) jsonNode.get("territorialWaterStatus"));
        this.vesselCountry = JsonHelper.arrayNodeToIntegerList((ArrayNode) jsonNode.get("vesselCountry"));

        Logger.debug("New EventFilter created.");
        Logger.debug("beginDate: " + this.beginDate);
        Logger.debug("endDate: " + this.endDate);
        Logger.debug("closestCountry: " + this.closestCountry.toString());
        Logger.debug("territorialWaterStatus: " + this.territorialWaterStatus.toString());
        Logger.debug("vesselCountry: " + this.vesselCountry.toString());

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
        if(this.territorialWaterStatus.size() > 0) {
            return territorialWaterStatus;
        } else {
            return null;
        }
    }

    public void setTerritorialWaterStatus(List<Integer> territorialWaterStatus) {
        this.territorialWaterStatus = territorialWaterStatus;
    }

    public List<Integer> getClosestCountry() {
        if(this.closestCountry.size() > 0) {
            return closestCountry;
        } else {
            return null;
        }
    }

    public void setClosestCountry(List<Integer> closestCountry) {
        this.closestCountry = closestCountry;
    }

    public List<Integer> getVesselCountry() {
        if(this.vesselCountry.size() > 0) {
            return vesselCountry;
        } else {
            return null;
        }
    }

    public void setVesselCountry(List<Integer> vesselCountry) {
        this.vesselCountry = vesselCountry;
    }

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
}
