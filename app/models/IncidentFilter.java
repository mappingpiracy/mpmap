package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import helpers.JsonHelper;

import java.util.List;

/**
 * Created by alex on 3/13/15.
 */
public class IncidentFilter {
    protected EventFilterType eventFilterType;
    protected String beginDate;
    protected String endDate;
    protected List<Integer> closestCountry;
    protected List<Integer> waterCountry;
    protected List<Integer> vesselCountry;
    protected List<String> vesselStatus;

    public IncidentFilter(JsonNode jsonNode){
        this.beginDate = jsonNode.get(eventFilterType.BEGIN_DATE.getJsName()).asText();
        this.endDate = jsonNode.get(eventFilterType.END_DATE.getJsName()).asText();
        this.closestCountry = JsonHelper.arrayNodeToIntegerList((ArrayNode) jsonNode.get(eventFilterType.CLOSEST_COUNTRY.getJsName()));
        this.waterCountry = JsonHelper.arrayNodeToIntegerList((ArrayNode) jsonNode.get(eventFilterType.TERRITORIAL_WATER_STATUS.getJsName()));
        this.vesselCountry = JsonHelper.arrayNodeToIntegerList((ArrayNode) jsonNode.get(eventFilterType.VESSEL_COUNTRY.getJsName()));
        this.vesselStatus = JsonHelper.arrayNodeToStringList((ArrayNode) jsonNode.get(eventFilterType.VESSEL_STATUS.getJsName()));
    }

    public IncidentFilter(String beginDate, String endDate, List<Integer> closestCountry, List<Integer> waterCountry, List<Integer> vesselCountry, List<String> vesselStatus) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.closestCountry = closestCountry;
        this.waterCountry = waterCountry;
        this.vesselCountry = vesselCountry;
        this.vesselStatus = vesselStatus;
    };

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

    public List<Integer> getWaterCountry() {
        if(waterCountry.isEmpty()) {
            return null;
        } else {
            return waterCountry;
        }
    }

    public void setWaterCountry(List<Integer> waterCountry) {
        this.waterCountry = waterCountry;
    }

    public List<Integer> getClosestCountry() {
        if(closestCountry.isEmpty()) {
            return null;
        } else {
            return closestCountry;
        }
    }

    public void setClosestCountry(List<Integer> closestCountry) {
        this.closestCountry = closestCountry;
    }

    public List<Integer> getVesselCountry() {
        if(vesselCountry.isEmpty()) {
            return null;
        } else {
            return vesselCountry;
        }
    }

    public void setVesselCountry(List<Integer> vesselCountry) {
        this.vesselCountry = vesselCountry;
    }

    public List<String> getVesselStatus() {
        if(vesselStatus.isEmpty()){
            return null;
        } else {
            return vesselStatus;
        }
    }

    public void setVesselStatus(List<String> vesselStatus) {
        this.vesselStatus = vesselStatus;
    }

}
