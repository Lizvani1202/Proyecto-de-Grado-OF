package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.IncidentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncidentReportRepository extends JpaRepository<IncidentReport, Long> {
    List<IncidentReport> findAllByDateGreaterThanEqualAndStatusEqualsOrderByDateDesc(@Param("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime date, @Param("status") Integer status);
}
