package com.example.qualitycontrol.Services;



        import com.example.qualitycontrol.Models.Inspection;
        import com.example.qualitycontrol.Repositories.InspectionRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    public Inspection saveInspection(Inspection inspection) {
        return inspectionRepository.save(inspection);
    }

    public Inspection getInspectionById(Long id) {
        return inspectionRepository.findById(id).orElseThrow(() -> new RuntimeException("Inspection not found"));
    }

    public List<Inspection> getAllInspections() {
        return inspectionRepository.findAll();
    }

    public Inspection updateInspection(Long id, Inspection inspection) {
        inspection.setId(id);
        return inspectionRepository.save(inspection);
    }

    public void deleteInspection(Long id) {
        inspectionRepository.deleteById(id);
    }
}