package dao;

import models.Incident;
import models.IncidentFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncidentMapper {
    public List<Incident> getIncidents();
    public List<Incident> getIncidentsWithFilter(@Param("filter") IncidentFilter incidentFilter);
}
