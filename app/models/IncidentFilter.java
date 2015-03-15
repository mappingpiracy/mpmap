package models;

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
    protected List<String> type = new ArrayList<>();
    protected List<String> action = new ArrayList<>();

    public IncidentFilter(String beginDate, String endDate, List<Integer> closestCountry, List<Integer> waterCountry, List<Integer> vesselCountry, List<String> vesselStatus, List<String> conflictType, List<String> conflictAction) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.closestCountry = closestCountry;
        this.waterCountry = waterCountry;
        this.vesselCountry = vesselCountry;
        this.vesselStatus = vesselStatus;
        this.type = conflictType;
        this.action = conflictAction;
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
        return (waterCountry.isEmpty()) ? null : waterCountry;
    }

    public void setWaterCountry(List<Integer> waterCountry) {
        this.waterCountry = waterCountry;
    }

    public List<Integer> getClosestCountry() {
        return (closestCountry.isEmpty()) ? null : closestCountry;
    }

    public void setClosestCountry(List<Integer> closestCountry) {
        this.closestCountry = closestCountry;
    }

    public List<Integer> getVesselCountry() {
        return (vesselCountry.isEmpty()) ? null : vesselCountry;
    }

    public void setVesselCountry(List<Integer> vesselCountry) {
        this.vesselCountry = vesselCountry;
    }

    public List<String> getVesselStatus() {
        return (vesselStatus.isEmpty()) ? null : vesselStatus;
    }

    public void setVesselStatus(List<String> vesselStatus) {
        this.vesselStatus = vesselStatus;
    }

    public List<String> getType() {
        return (type.isEmpty()) ? null : type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getAction() {
        return (action.isEmpty()) ? null : action;
    }

    public void setAction(List<String> action) {
        this.action = action;
    }
}
