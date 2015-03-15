import java.util.*;

import dao.MybatisEnvironment;
import dao.MybatisMapper;
import models.Incident;
import models.IncidentFilter;
import org.joda.time.DateTime;
import org.junit.*;

import static org.fest.assertions.Assertions.*;
import static play.libs.Json.toJson;


/**
 * JUnit Test Suite for the MapData controller
 *
 */
public class IncidentTest {

    final Integer NUMBER_OF_INCIDENTS = 6341;
    final String DATE_MINIMUM = "1993-01-01";
    final String DATE_MAXIMUM = "2015-01-30";
    final String DATE_UPPER_BOUND = "2009-12-31";
    Map<Integer, Integer> incidentsPerYear;
    Map<Integer, Integer> incidentsPerClosestCountry;      //date <= DATE_UPPER_BOUND
    Map<Integer, Integer> incidentsPerWaterCountry;        //date <= DATE_UPPER_BOUND
    Map<Integer, Integer> incidentsPerVesselCountry;
    Map<String, Integer> incidentsPerVesselStatus;
    Map<String, Integer> incidentsPerType;
    Map<String, Integer> incidentsPerAction;

    static {
        MybatisMapper mybatisMapper = MybatisMapper.getInstance(MybatisEnvironment.TESTING);
    }

    @Test
    public void getIncidentsTest() {
        System.out.println("getIncidentsTest");
        List<Incident> incidents;
        incidents = Incident.getIncidents();
        assertThat(incidents.size()).isEqualTo(NUMBER_OF_INCIDENTS);
    }

    @Test
    public void getIncidentsWithFilterTest() {
        System.out.println("getIncidentsWithFilterTest");
        IncidentFilter incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_MAXIMUM);
        List<Incident> incidents = Incident.getIncidents(incidentFilter);
        assertThat(incidents.size()).isEqualTo(NUMBER_OF_INCIDENTS);
    }

    /*
    Test the date filter by checking the return count for every year against a predefined list.
     */
    @Test
    public void dateFilterTest() {
        System.out.println("dateFilterTest");
        setIncidentsPerYear();
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

    @Test
    /*
    Test the closest country filter by checking incrementally against a predefined set of values.
     */
    public void closestCountryFilterTest() {
        System.out.println("closestCountryFilterTest");
        setIncidentsPerClosestCountry();
        IncidentFilter incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_UPPER_BOUND);
        List<Incident> incidents;
        Integer count, correctCount;
        List<Integer> countries = new ArrayList<>();
        correctCount = 0;
        for (Integer country : incidentsPerClosestCountry.keySet()) {
            incidentFilter.setBeginDate(DATE_MINIMUM);
            incidentFilter.setEndDate(DATE_UPPER_BOUND);

            countries.add(country);
            incidentFilter.setClosestCountry(countries);

            count = incidentsPerClosestCountry.get(country);
            correctCount += count;

            incidents = Incident.getIncidents(incidentFilter);
            assertThat(incidents.size()).isEqualTo(correctCount);
        }
    }

    @Test
    public void waterCountryFilterTest() {
        System.out.println("waterCountryFilterTest");
        setIncidentsPerWaterCountry();
        IncidentFilter incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_UPPER_BOUND);
        List<Incident> incidents;
        List<Integer> waterCountries;
        Integer count;
        for(Integer waterCountry : incidentsPerWaterCountry.keySet()) {
            waterCountries = new ArrayList<>();
            waterCountries.add(waterCountry);
            incidentFilter.setWaterCountry(waterCountries);
            incidents = Incident.getIncidents(incidentFilter);
            count = incidentsPerWaterCountry.get(waterCountry);
            assertThat(incidents.size()).isEqualTo(count);
        }

    }

    @Test
    public void vesselCountryFilterTest() {
        System.out.println("vesselCountryFilterTest");
        setIncidentsPerVesselCountry();
        IncidentFilter incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_UPPER_BOUND);
        List<Incident> incidents;
        List<Integer> vesselCountries;
        Integer count;
        for(Integer vesselCountry : incidentsPerVesselCountry.keySet()) {
            vesselCountries = new ArrayList<>();
            vesselCountries.add(vesselCountry);
            incidentFilter.setVesselCountry(vesselCountries);
            incidents = Incident.getIncidents(incidentFilter);
            count = incidentsPerVesselCountry.get(vesselCountry);
            assertThat(incidents.size()).isEqualTo(count);
        }
    }

    @Test
    public void vesselStatusFilterTest() {
        System.out.println("vesselStatusFilterTes");
        setIncidentsPerVesselStatus();
        IncidentFilter incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_UPPER_BOUND);
        List<Incident> incidents;
        List<String> vesselStatuses;
        Integer count;
        for(String vesselStatus : incidentsPerVesselStatus.keySet()) {
            vesselStatuses = new ArrayList<>();
            vesselStatuses.add(vesselStatus);
            incidentFilter.setVesselStatus(vesselStatuses);
            incidents = Incident.getIncidents(incidentFilter);
            count = incidentsPerVesselStatus.get(vesselStatus);
            assertThat(incidents.size()).isEqualTo(count);
        }
    }

    @Test
    public void typeFilterTest() {
        System.out.println("typeFilterTest");
        setIncidentsPerType();
        IncidentFilter incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_UPPER_BOUND);
        List<Incident> incidents;
        List<String> types;
        Integer count;
        for (String type : incidentsPerType.keySet()) {
            types = new ArrayList<>();
            types.add(type);
            incidentFilter.setType(types);
            incidents = Incident.getIncidents(incidentFilter);
            count = incidentsPerType.get(type);
            assertThat(incidents.size()).isEqualTo(count);
        }
    }

    @Test
    public void actionFilterTest() {
        System.out.println("actionFilterTest");
        setIncidentsPerAction();
        IncidentFilter  incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_UPPER_BOUND);
        List<Incident> incidents;
        List<String> types = new ArrayList<>();
        Integer count;
        for (String type : incidentsPerAction.keySet()) {
            types.clear();
            types.add(type);
            incidentFilter.setAction(types);
            incidents = Incident.getIncidents(incidentFilter);
            count = incidentsPerAction.get(type);
            assertThat(incidents.size()).isEqualTo(count);
        }
    }

    protected void setIncidentsPerYear() {
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

    protected void setIncidentsPerClosestCountry() {
        incidentsPerClosestCountry = new HashMap<>();
        incidentsPerClosestCountry.put(850, 1199);  //Indonesia
        incidentsPerClosestCountry.put(679, 304);   //Malaysia
        incidentsPerClosestCountry.put(820, 341);   //Bangladesh
    }

    protected void setIncidentsPerType() {
        incidentsPerType = new HashMap<>();
        incidentsPerType.put("Actual", 3267);
        incidentsPerType.put("Attempted", 1253);
    }

    protected void setIncidentsPerAction() {
        incidentsPerAction = new HashMap<>();
        incidentsPerAction.put("Boarded", 2939);
        incidentsPerAction.put("Detaining", 1);
        incidentsPerAction.put("Missing", 12);
        incidentsPerAction.put("Fired Upon", 104);
        incidentsPerAction.put("Hijacked", 309);
    }

    protected void setIncidentsPerVesselStatus() {
        incidentsPerVesselStatus = new HashMap<>();
        incidentsPerVesselStatus.put("Anchored", 1984);
        incidentsPerVesselStatus.put("Berthed", 452);
        incidentsPerVesselStatus.put("Stationary", 1);
        incidentsPerVesselStatus.put("Steaming", 1765);
    }

    public void setIncidentsPerVesselCountry() {
        incidentsPerVesselCountry = new HashMap<>();
        incidentsPerVesselCountry.put(95, 686);
        incidentsPerVesselCountry.put(0, 439);
        incidentsPerVesselCountry.put(830, 378);
        incidentsPerVesselCountry.put(450, 315);
        incidentsPerVesselCountry.put(352, 240);
    }

    public void setIncidentsPerWaterCountry() {
        incidentsPerWaterCountry = new HashMap<>();
        incidentsPerWaterCountry.put(850, 1199);
        incidentsPerWaterCountry.put(771, 349);
        incidentsPerWaterCountry.put(820, 342);
        incidentsPerWaterCountry.put(679, 304);
        incidentsPerWaterCountry.put(475, 268);
    }



}
