package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import helpers.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 3/13/15.
 */
public class IncidentFilter {
    protected String beginDate;
    protected String endDate;
    protected List<Integer> closestCountry = new ArrayList<>();
    protected List<Integer> waterCountry = new ArrayList<>();
    protected List<Integer> vesselCountry = new ArrayList<>();
    protected List<String> vesselStatus = new ArrayList<>();
    protected List<String> conflictType = new ArrayList<>();
    protected List<String> conflictAction = new ArrayList<>();

    public IncidentFilter(String beginDate, String endDate, List<Integer> closestCountry, List<Integer> waterCountry, List<Integer> vesselCountry, List<String> vesselStatus, List<String> conflictType, List<String> conflictAction) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.closestCountry = closestCountry;
        this.waterCountry = waterCountry;
        this.vesselCountry = vesselCountry;
        this.vesselStatus = vesselStatus;
        this.conflictType = conflictType;
        this.conflictAction = conflictAction;
    }

    public IncidentFilter(String beginDate, String endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
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

    public List<String> getConflictType() {
        if(conflictType.isEmpty()) {
            return null;
        } else {
            return conflictType;
        }
    }

    public void setConflictType(List<String> conflictType) {
        this.conflictType = conflictType;
    }

    public List<String> getConflictAction() {
        if(conflictAction.isEmpty()) {
            return null;
        } else {
            return conflictAction;
        }
    }

    public void setConflictAction(List<String> conflictAction) {
        this.conflictAction = conflictAction;
    }
}
