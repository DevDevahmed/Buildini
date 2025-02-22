package com.example.qualitycontrol.Services;

        import com.example.qualitycontrol.Models.Defect;
        import com.example.qualitycontrol.Repositories.DefectRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class DefectService {

    @Autowired
    private DefectRepository defectRepository;

    public Defect saveDefect(Defect defect) {
        return defectRepository.save(defect);
    }

    public Defect getDefectById(Long id) {
        return defectRepository.findById(id).orElseThrow(() -> new RuntimeException("Defect not found"));
    }

    public List<Defect> getAllDefects() {
        return defectRepository.findAll();
    }

    public Defect updateDefect(Long id, Defect defect) {
        defect.setId(id);
        return defectRepository.save(defect);
    }

    public void deleteDefect(Long id) {
        defectRepository.deleteById(id);
    }
}