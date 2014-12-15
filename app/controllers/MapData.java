package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.Event;
import models.EventFilter;
import play.data.DynamicForm;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSONArray$;

import java.util.ArrayList;
import java.util.List;

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
        EventFilter eventFilter;
        List<Event> events;

        if(jsonEventFilter != null && EventFilter.validate(jsonEventFilter)) {
            eventFilter = new EventFilter(jsonEventFilter);
            events = Event.getByFilter(eventFilter);
        } else {
            events = Event.getAll();
        }

        List<JsonNode> jsonEvents = new ArrayList<>();

        for(int i = 0; i < events.size(); i++) {
            jsonEvents.add(events.get(i).toGeoJsonFeature());
        }

        return ok(toJson(Event.toGeoJsonFeatureCollection(jsonEvents)));
    }

}
