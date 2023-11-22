package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.TypeIncidentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeIncidentRepository extends JpaRepository<TypeIncidentReport, Long> {
    Optional<TypeIncidentReport> findByName(@Param("name") String name);
}
