package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.Event;
import play.data.DynamicForm;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

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
        Should return events based on passed filtering variables.
     */
    @BodyParser.Of(BodyParser.Json.class)
    public static Result events() {
        JsonNode json = request().body().asJson();
        String beginDate, endDate;

        beginDate = json.findPath("beginDate").toString();
        endDate = json.findPath("endDate").toString();
        //territorialWaterStatus = json.findPath("territorialWaterStatus").to

        List<Event> events = Event.getAll();
        List<JsonNode> jsonEvents = new ArrayList<>();

        for(int i = 0; i < events.size(); i++) {
            jsonEvents.add(events.get(i).toGeoJsonFeature());
        }

        return ok(toJson(Event.toGeoJsonFeatureCollection(jsonEvents)));
    }

}
