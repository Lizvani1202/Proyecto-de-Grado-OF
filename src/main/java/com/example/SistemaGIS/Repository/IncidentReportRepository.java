package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.IncidentReport;
import com.example.SistemaGIS.Model.AllIncidentReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IncidentReportRepository extends JpaRepository<IncidentReport, Long> {

    @Query("SELECT " +
            "ir.incidentReportId AS incidentReportId, " +
            "o AS owner, " +
            "g AS gis," +
            "ir.ubicacion AS ubicacion, " +
            "ir.status AS status, " +
            "ir.date AS date, " +
            "ir.type AS type " +
            "FROM IncidentReport ir " +
            "INNER JOIN ir.gis g " +
            "INNER JOIN ir.owner o " +
            "INNER JOIN o.person p " +
            "WHERE ir.date BETWEEN :date AND NOW() " +
            "AND ir.status = :status " +
            "ORDER BY ir.date DESC")
    List<AllIncidentReportDTO> getIncidentReportsByDateAnAndStatus(@Param("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @Param("status") Integer status);
}
