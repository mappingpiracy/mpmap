package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import helpers.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.Logger;
import models.EventFilterType;

import java.lang.String;
import java.util.List;

/**
 * Created by alex on 12/14/14.
 */
public class EventFilter {
    protected EventFilterType eventFilterType;
    protected String beginDate;
    protected String endDate;
    protected List<Integer> closestCountry;
    protected List<Integer> territorialWaterStatus;
    protected List<Integer> vesselCountry;
    protected List<String> vesselStatus;

    public EventFilter() { }

    //TODO: create enums for filter names, figure out how to use them in the scala views
    public EventFilter(JsonNode jsonNode){
        this.beginDate = jsonNode.get(eventFilterType.BEGIN_DATE.getJsName()).asText();
        this.endDate = jsonNode.get(eventFilterType.END_DATE.getJsName()).asText();
        this.closestCountry = JsonHelper.arrayNodeToIntegerList((ArrayNode) jsonNode.get(eventFilterType.CLOSEST_COUNTRY.getJsName()));
        this.territorialWaterStatus = JsonHelper.arrayNodeToIntegerList((ArrayNode) jsonNode.get(eventFilterType.TERRITORIAL_WATER_STATUS.getJsName()));
        this.vesselCountry = JsonHelper.arrayNodeToIntegerList((ArrayNode) jsonNode.get(eventFilterType.VESSEL_COUNTRY.getJsName()));
        //this.vesselStatus = JsonHelper.arrayNodeToStringList((ArrayNode) jsonNode.get(eventFilterType.VESSEL_STATUS.getJsName()));
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

    public List<String> getVesselStatus() {
        return vesselStatus;
    }

    public void setVesselStatus(List<String> vesselStatus) {
        this.vesselStatus = vesselStatus;
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
