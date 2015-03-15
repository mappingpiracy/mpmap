import java.util.*;

import dao.MybatisMapper;
import models.Incident;
import models.IncidentFilter;
import org.joda.time.DateTime;
import org.junit.*;

import static org.fest.assertions.Assertions.*;


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
    Map<Integer, Integer> incidentsPerVesselStatus;
    MybatisMapper mybatisMapper = MybatisMapper.getInstance("testing");

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

    @Test
    /*
    Test the closest country filter by checking incrementally against a predefined set of values.
     */
    public void closestCountryFilterTest() {
        initializeIncidentsPerClosestCountry();
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

    }

    @Test
    public void vesselCountryFilterTest() {

    }

    @Test
    public void vesselStatusFilterTest() {

    }

    @Test
    public void conflictActionFilterTest() {

    }

    @Test
    public void conflictTypeFilterTest() {
        IncidentFilter incidentFilter = new IncidentFilter(DATE_MINIMUM, DATE_MAXIMUM);
        List<String> conflictType = new ArrayList<>();
        conflictType.add("Actual");
        incidentFilter.setConflictType(conflictType);
        List<Incident> incidents = Incident.getIncidents(incidentFilter);
        System.out.println(incidents.size());
        assertThat(incidents.size()).isEqualTo(4374);
    }

    protected void initializeIncidentsPerYear() {
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

    protected void initializeIncidentsPerClosestCountry() {
        incidentsPerClosestCountry = new HashMap<>();
        incidentsPerClosestCountry.put(850, 1199);  //Indonesia
        incidentsPerClosestCountry.put(679, 304);   //Malaysia
        incidentsPerClosestCountry.put(820, 341);   //Bangladesh
    }

}
