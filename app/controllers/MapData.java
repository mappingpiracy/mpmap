package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import helpers.GeoJsonHelper;
import models.Event;
import models.EventFilter;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

//import javax.inject.Inject;
import java.util.*;

import static play.data.Form.form;
import static play.libs.Json.toJson;

/**
 * Created by alex on 11/27/14.
 */
public class MapData extends Controller {


    public static Result index(){
        return ok("this controller method doesn't really do anything :*(");
    }


    /*
        Checks for posted event filters.
        Returns either filtered events, or all events if there are no filters.
     */
    @BodyParser.Of(BodyParser.Json.class)
    public static Result events() {
        JsonNode jsonEventFilter = request().body().asJson();
        List<Event> events;

        if(jsonEventFilter != null && EventFilter.validate(jsonEventFilter)) {
            events = Event.getEvents(new EventFilter(jsonEventFilter));
        } else {
            events = Event.getEvents();
        }
        return ok(toJson(GeoJsonHelper.eventsToFeatureCollection(events)));
    }

    /*
        Checks for posted event filters.
        Returns either filtered events, or all events if there are no filters.
     */
    @BodyParser.Of(BodyParser.Json.class)
    public static Result export() {
        JsonNode jsonEvents = request().body().asJson();

        if(jsonEvents == null) {
            return ok("nothing to see here...");
        }

        return ok(toJson(jsonEvents));
    }

}
