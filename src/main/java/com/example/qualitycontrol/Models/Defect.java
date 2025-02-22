package com.example.qualitycontrol.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La description du défaut est obligatoire")
    private String description;

    @NotBlank(message = "Le statut du défaut est obligatoire")
    private String status;

    @ManyToOne
    @JoinColumn(name = "inspection_id", nullable = false)
    @NotNull(message = "L'inspection associée au défaut est obligatoire")
    private Inspection inspection;
    // Constructors
    public Defect() {
    }

    public Defect(String description, String status, Inspection inspection) {
        this.description = description;
        this.status = status;
        this.inspection = inspection;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }
}