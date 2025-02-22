package com.example.qualitycontrol.Controllers;
import com.example.qualitycontrol.Models.Inspection;
import com.example.qualitycontrol.Services.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspections")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @PostMapping
    public ResponseEntity<Inspection> createInspection(@RequestBody Inspection inspection) {
        Inspection savedInspection = inspectionService.saveInspection(inspection);
        return new ResponseEntity<>(savedInspection, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inspection> getInspectionById(@PathVariable Long id) {
        Inspection inspection = inspectionService.getInspectionById(id);
        return new ResponseEntity<>(inspection, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Inspection>> getAllInspections() {
        List<Inspection> inspections = inspectionService.getAllInspections();
        return new ResponseEntity<>(inspections, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inspection> updateInspection(@PathVariable Long id, @RequestBody Inspection inspection) {
        Inspection updatedInspection = inspectionService.updateInspection(id, inspection);
        return new ResponseEntity<>(updatedInspection, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable Long id) {
        inspectionService.deleteInspection(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


