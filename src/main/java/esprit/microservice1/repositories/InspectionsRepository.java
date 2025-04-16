package esprit.microservice1.repositories;

import esprit.microservice1.entities.Inspections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionsRepository extends JpaRepository<Inspections,Integer> {

    @Query("SELECT i FROM Inspections i WHERE i.incidents.id = :incidentId")
    List<Inspections> findByIncidentId(@Param("incidentId") int incidentId);
}
