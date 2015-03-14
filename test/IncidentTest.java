import java.time.Month;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

import com.fasterxml.jackson.databind.JsonNode;
import models.Incident;
import models.IncidentFilter;
import org.joda.time.DateTime;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;
import static play.libs.Json.toJson;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
 * JUnit Test Suite for the MapData controller
 *
 */
public class IncidentTest {

    final Integer NUMBER_OF_INCIDENTS = 6341;
    final String DATE_MINIMUM = "1993-01-01";
    final String DATE_MAXIMUM = "2015-01-30";

    Map<Integer, Integer> incidentsPerYear;;

    @Test
    public void getIncidentsTest() {
        List<Incident> incidents;
        incidents = Incident.getIncidents();
        System.out.println(incidents.size());
        assertThat(incidents.size()).isEqualTo(NUMBER_OF_INCIDENTS);
    }

    @Test
    public void getIncidentsWithFilterTest() {
        IncidentFilter incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_MAXIMUM);
        List<Incident> incidents = Incident.getIncidents(incidentFilter);
        System.out.println(incidents.size());
        assertThat(incidents.size()).isEqualTo(NUMBER_OF_INCIDENTS);
    }

    /*
    Test the date filter by checking the return count for every year against a predefined list.
     */
    @Test
    public void dateFilterTest() {
        initializeIncidentsPerYear();
        IncidentFilter incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_MAXIMUM);
        List<Incident> incidents;
        Integer count;
        for (Integer year : incidentsPerYear.keySet()) {
            incidentFilter.setBeginDate(new DateTime(year, 1, 1, 0, 0, 0, 0).toString());
            incidentFilter.setEndDate(new DateTime(year, 12, 31, 0, 0, 0, 0).toString());
            incidents = Incident.getIncidents(incidentFilter);
            count = incidentsPerYear.get(year);
            assertThat(incidents.size()).isEqualTo(count);
        }

    }

    public void initializeIncidentsPerYear() {
        incidentsPerYear = new HashMap<>();
        incidentsPerYear.put(1993, 100);
        incidentsPerYear.put(1994, 90);
        incidentsPerYear.put(1995, 165);
        incidentsPerYear.put(1996, 178);
        incidentsPerYear.put(1997, 224);
        incidentsPerYear.put(1998, 176);
        incidentsPerYear.put(1999, 291);
        incidentsPerYear.put(2000, 464);
        incidentsPerYear.put(2001, 339);
        incidentsPerYear.put(2002, 375);
        incidentsPerYear.put(2003, 453);
        incidentsPerYear.put(2004, 302);
        incidentsPerYear.put(2005, 283);
        incidentsPerYear.put(2006, 239);
        incidentsPerYear.put(2007, 270);
        incidentsPerYear.put(2008, 283);
        incidentsPerYear.put(2009, 396);
        incidentsPerYear.put(2010, 436);
        incidentsPerYear.put(2011, 456);
        incidentsPerYear.put(2012, 297);
        incidentsPerYear.put(2013, 262);
        incidentsPerYear.put(2014, 248);
        incidentsPerYear.put(2015, 14);
    }

}
