package controllers;


import helpers.ListHelper;
import models.*;
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
        return ok();
    }

    public static Result countries() {
        List<Country> countries = Country.getCountries();
        return ok(toJson(countries));
    }

    public static Result incidents(String format,      // json || geojson
                                String beginDate,
                                String endDate,
                                String waterCountry,
                                String closestCountry,
                                String vesselCountry,
                                String vesselType,
                                String vesselStatus,
                                String type,
                                String action) {
        List<Incident> incidents;
        IncidentFilter incidentFilter = new IncidentFilter(beginDate, endDate,
                ListHelper.commaStringToIntegerList(waterCountry),    //  String -> List<Integer>
                ListHelper.commaStringToIntegerList(closestCountry),  //  String -> List<Integer>
                ListHelper.commaStringToIntegerList(vesselCountry),   //  String -> List<Integer>
                ListHelper.commaStringToStringList(vesselType),       //  String -> List<String>
                ListHelper.commaStringToStringList(vesselStatus),     //  String -> List<String>
                ListHelper.commaStringToStringList(type),
                ListHelper.commaStringToStringList(action));
        incidents = Incident.getIncidents(incidentFilter);

        if(format.equals("geojson")) {
            return ok(toJson(Incident.toFeatureCollection(incidents)));
        } else {
            return ok(toJson(incidents));
        }

    }
}
