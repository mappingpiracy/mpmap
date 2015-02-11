package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import helpers.GeoJsonHelper;
import helpers.ListHelper;
import models.Event;
import models.EventFilter;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import models.Country;

//import javax.inject.Inject;
import java.util.*;

import static play.data.Form.form;
import static play.libs.Json.toJson;

/**
 * Created by alex on 11/27/14.
 */
public class MapData extends Controller {


    public static Result index(){
        return ok();
    }

    /*
        Returns GeoJSON list of all events
     */
    public static Result events() {
        List<Event> events = Event.getEvents();
        return ok(toJson(GeoJsonHelper.eventsToFeatureCollection(events)));
    }

    /*
        Returns GeoJSON list of events given the passed filters
     */
    public static Result events(String beginDate,
                                String endDate,
                                String territorialWaterStatus,
                                String closestCountry,
                                String vesselCountry,
                                String vesselStatus) {
        List<Event> events;
        EventFilter eventFilter = new EventFilter(beginDate, endDate,
                ListHelper.commaStringToIntegerList(territorialWaterStatus),    //  String -> List<Integer>
                ListHelper.commaStringToIntegerList(closestCountry),            //  String -> List<Integer>
                ListHelper.commaStringToIntegerList(vesselCountry),             //  String -> List<Integer>
                ListHelper.commaStringToStringList((vesselStatus)));            //  String -> List<String>
        events = Event.getEvents(eventFilter);
        return ok(toJson(GeoJsonHelper.eventsToFeatureCollection(events)));
    }

    /*
        Return JSON list of countries
     */
    public static Result countries() {
        List<Country> countries = Country.getCountries();
        return ok(toJson(countries));
    }


}
