package esprit.microservice1.repositories;

import esprit.microservice1.entities.Incidents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentsRepository extends JpaRepository<Incidents, Integer> {
}
