package esprit.microservice1;

import esprit.microservice1.entities.Incidents;
import esprit.microservice1.entities.Inspections;
import esprit.microservice1.services.IService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/candidat")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")  // ✅ Ajoutez cette ligne si nécessaire
public class MicroserviceRestApi {

    @Autowired
    IService service;

    ////////////////*********CRUD POST*********////////////
    @PostMapping("/ajouterincident")
    Incidents add(@RequestBody Incidents Incidents) {
        return  service.addin(Incidents);
    }

    @GetMapping("/incident_list")
    public List<Incidents> findAllPost() {
        return service.findAllIN();
    }

    @GetMapping("/incident/{id}")
    public Incidents findIncidentById(@PathVariable Integer id) {
        return service.findByIdIN(id);
    }

    @DeleteMapping("/deleincident/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteIN(id);
        return "Post deleted successfully";
    }

    @PutMapping ("/updateincident/{id}")
    Incidents update(@PathVariable Integer id, @RequestBody Incidents Incidents) {
        return  service.edit(id, Incidents);
    }

    /////////////////////////////////////////
    @PostMapping("/ajouterinspection/{id}")
    Inspections addinspection(@PathVariable int id,@RequestBody Inspections Inspections) {
        return  service.addi(Inspections,id);
    }

    @GetMapping("/incidents/{id}/inspections")
    public List<Inspections> getInspectionsByIncidentId(@PathVariable int id) {
        return service.findAllByIncidentId(id);
    }

    @GetMapping("/Inspections_list")
    public List<Inspections> findAlInspections() {
        return service.findAllI();
    }

    @GetMapping("/inspection/{id}")
    public Inspections findInspectionById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/deleInspections/{id}")
    public String deleteInspections(@PathVariable Integer id) {
        service.delete(id);
        return "Post deleted successfully";
    }

    @PutMapping ("/updateInspections/{id}")
    Inspections updateInspections(@PathVariable Integer id, @RequestBody Inspections Inspections) {
        return  service.editi(id, Inspections);
    }

    @PostMapping("/inspection/{id}/sign")
    public ResponseEntity<?> saveInspectionSignature(
            @PathVariable Integer id,
            @RequestBody Map<String, String> requestBody
    ) {
        try {
            String base64Image = requestBody.get("signatureBase64");

            if (base64Image == null || base64Image.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Signature is missing"));
            }

            String cleanBase64 = base64Image.replace("data:image/png;base64,", "");
            byte[] imageBytes = java.util.Base64.getDecoder().decode(cleanBase64);

            String filename = "signature_inspection_" + id + ".png";
            java.nio.file.Path filePath = java.nio.file.Paths.get("uploads/signatures/", filename);
            java.nio.file.Files.createDirectories(filePath.getParent());
            java.nio.file.Files.write(filePath, imageBytes);

            Inspections inspection = service.findById(id);
            inspection.setSignature(filePath.toString());
            service.editi(id, inspection);

            // ✅ Return a proper JSON response
            return ResponseEntity.ok(Map.of("message", "Signature saved successfully"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Erreur de traitement de signature"));
        }
    }


}
