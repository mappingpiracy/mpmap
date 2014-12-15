package models;

import com.fasterxml.jackson.databind.JsonNode;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

/**
 * Created by alex on 12/14/14.
 */
public class EventFilter {
    public String beginDate;
    public String endDate;
    public List<Integer> territorialWaterStatus;
    public List<Integer> closestCoastalState;
    public List<Integer> vesselCountry;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    //TODO: create enums for filter names, figure out how to use them in the scala views
    public EventFilter(JsonNode jsonNode){
        this.beginDate = jsonNode.get("beginDate").asText();
        this.endDate = jsonNode.get("endDate").asText();
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
