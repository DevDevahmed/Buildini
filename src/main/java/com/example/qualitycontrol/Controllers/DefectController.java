package com.example.qualitycontrol.Controllers;
   import com.example.qualitycontrol.Models.Defect;
        import com.example.qualitycontrol.Services.DefectService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/defects")
public class DefectController {

    @Autowired
    private DefectService defectService;

    @PostMapping
    public ResponseEntity<Defect> createDefect(@RequestBody Defect defect) {
        Defect savedDefect = defectService.saveDefect(defect);
        return new ResponseEntity<>(savedDefect, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Defect> getDefectById(@PathVariable Long id) {
        Defect defect = defectService.getDefectById(id);
        return new ResponseEntity<>(defect, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Defect>> getAllDefects() {
        List<Defect> defects = defectService.getAllDefects();
        return new ResponseEntity<>(defects, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Defect> updateDefect(@PathVariable Long id, @RequestBody Defect defect) {
        Defect updatedDefect = defectService.updateDefect(id, defect);
        return new ResponseEntity<>(updatedDefect, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefect(@PathVariable Long id) {
        defectService.deleteDefect(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}