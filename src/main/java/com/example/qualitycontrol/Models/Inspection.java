package com.example.qualitycontrol.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
@Entity
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La description de l'inspection est obligatoire")
    private String description;

    @NotNull(message = "La date de l'inspection est obligatoire")
    private LocalDate inspectionDate;

    // One-to-Many relationship with Defect
    @OneToMany(mappedBy = "inspection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Defect> defects;

    // Constructors
    public Inspection() {
    }

    public Inspection(String description, LocalDate inspectionDate) {
        this.description = description;
        this.inspectionDate = inspectionDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public void setDefects(List<Defect> defects) {
        this.defects = defects;
    }

    // Helper method to add a defect
    public void addDefect(Defect defect) {
        defect.setInspection(this);
        this.defects.add(defect);
    }

    // Helper method to remove a defect
    public void removeDefect(Defect defect) {
        defect.setInspection(null);
        this.defects.remove(defect);
    }
}