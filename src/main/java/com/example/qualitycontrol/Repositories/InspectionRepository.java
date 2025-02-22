package com.example.qualitycontrol.Repositories;

 import com.example.qualitycontrol.Models.Inspection;
 import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {
}