package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.ReportPenalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportPenaltyRepository extends JpaRepository<ReportPenalty, Long>{
    @Query("SELECT rp FROM ReportPenalty rp " +
            "INNER JOIN rp.carFeatures cf " +
            "WHERE cf.numberPlate = :number_plate " +
            "ORDER BY rp.date DESC")
    ReportPenalty getLastPenaltyReportByNumberPlate(@Param("number_plate") String numberPlate);
}
