package esprit.microservice1.services;

import esprit.microservice1.entities.Incidents;
import esprit.microservice1.entities.Inspections;
import esprit.microservice1.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Slf4j
@AllArgsConstructor
public class Service implements IService{
    private final UserRepo userRepo;
    private final IncidentsRepository incidentsRepository;
    private final InspectionsRepository inspectionsRepository;
    private final EmailService emailService;

    // Gestion des incidents
    @Override
    public List<Incidents> findAllIN() {
        return this.incidentsRepository.findAll();
    }
    @Override
    public List<Inspections> findAllByIncidentId(int incidentId) {
        return inspectionsRepository.findByIncidentId(incidentId);
    }

    @Override
    public Incidents findByIdIN(int id) {
        return this.incidentsRepository.findById(id).orElse(null);
    }

    @Override
    public Incidents addin(Incidents incident) {
        Incidents savedIncident = this.incidentsRepository.save(incident);

        // ✉️ Envoi de l'e-mail au client
        if (savedIncident.getClientEmail() != null && !savedIncident.getClientEmail().isEmpty()) {
            String subject = "Nouvel Incident Créé";
            String body = "Bonjour,\n\nUn nouvel incident a été enregistré avec les informations suivantes :\n" +
                    "Description: " + savedIncident.getDescription() + "\n" +
                    "Gravité: " + savedIncident.getGravite() + "\n" +
                    "État: " + savedIncident.getEtat() + "\n" +
                    "Responsable: " + savedIncident.getResponsable() + "\n\n" +
                    "Merci de votre attention.";

            emailService.sendSimpleEmail(savedIncident.getClientEmail(), subject, body);
        }

        return savedIncident;
    }
    @Override
    public void deleteIN(int id) {
        this.incidentsRepository.deleteById(id);
    }

    @Override
    public Incidents edit(int id, Incidents incident) {
        return incidentsRepository.findById(id).map(existingIncident -> {
            existingIncident.setDescription(incident.getDescription());
            existingIncident.setGravite(incident.getGravite());
            existingIncident.setEtat(incident.getEtat());
            existingIncident.setResponsable(incident.getResponsable());
            existingIncident.setClientEmail(incident.getClientEmail());
            return incidentsRepository.save(existingIncident);
        }).orElseThrow(() -> new RuntimeException("Incident non trouvé avec ID : " + id));
    }
    // Gestion des inspections
    @Override
    public List<Inspections> findAllI() {
        return this.inspectionsRepository.findAll();
    }

    @Override
    public Inspections findById(int id) {
        return this.inspectionsRepository.findById(id).orElse(null);
    }

    @Override
    public Inspections addi(Inspections inspection, int incidentId) {
        Optional<Incidents> incident = incidentsRepository.findById(incidentId);
        if (incident.isPresent()) {
            inspection.setIncidents(incident.get());
            return inspectionsRepository.save(inspection);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        this.inspectionsRepository.deleteById(id);
    }

    @Override
    public Inspections editi(int id, Inspections updatedInspection) {
        Inspections existingInspection = this.inspectionsRepository.findById(id).orElse(null);

        if (existingInspection != null) {
            existingInspection.setResponsable(updatedInspection.getResponsable());
            existingInspection.setObjet(updatedInspection.getObjet());
            existingInspection.setDateInspection(updatedInspection.getDateInspection());
            existingInspection.setResultat(updatedInspection.getResultat());

            // ✅ Add this to update the signature if provided
            if (updatedInspection.getSignature() != null) {
                existingInspection.setSignature(updatedInspection.getSignature());
            }

            return this.inspectionsRepository.save(existingInspection);
        } else {
            return null;
        }
    }


}
