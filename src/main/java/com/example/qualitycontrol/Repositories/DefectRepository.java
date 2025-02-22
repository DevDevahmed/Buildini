package com.example.qualitycontrol.Repositories;

   import com.example.qualitycontrol.Models.Defect;
   import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectRepository extends JpaRepository<Defect, Long> {
}