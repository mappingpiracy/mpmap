package models;

public enum EventFilterType {

    BEGIN_DATE("beginDate"),
    END_DATE("endDate"),
    CLOSEST_COUNTRY("closestCountry"),
    TERRITORIAL_WATER_STATUS("territorialWaterStatus"),
    VESSEL_COUNTRY("vesselCountry"),
    VESSEL_STATUS("vesselStatus");


    EventFilterType(String jsName) {
        this.jsName = jsName;
    }

    private String jsName;    //Name of the filter on the javascript front-end

    public String getJsName() {
        return this.jsName;
    }
}