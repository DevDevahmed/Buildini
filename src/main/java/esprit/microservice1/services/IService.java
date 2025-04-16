package esprit.microservice1.services;

import esprit.microservice1.entities.Incidents;
import esprit.microservice1.entities.Inspections;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IService {
    List<Incidents> findAllIN();

    Incidents findByIdIN(int id);

    Incidents addin(Incidents Incidents);

    void deleteIN(int id);

    Incidents edit(int id,Incidents Incidents);

    List<Inspections> findAllI();

    Inspections findById(int id);
    List<Inspections> findAllByIncidentId(int incidentId);
    Inspections addi(Inspections inspection, int incidentId);

    void delete(int id);

    Inspections editi(int id,Inspections Inspections);
}
