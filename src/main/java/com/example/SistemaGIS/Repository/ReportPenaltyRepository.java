package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.ReportPenalty;
import com.example.SistemaGIS.Model.ReportPenaltyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReportPenaltyRepository extends JpaRepository<ReportPenalty, Long> {
    @Query("SELECT rp FROM ReportPenalty rp " +
            "INNER JOIN rp.carFeatures cf " +
            "WHERE cf.numberPlate = :number_plate " +
            "ORDER BY rp.date DESC")
    ReportPenalty getLastPenaltyReportByNumberPlate(@Param("number_plate") String numberPlate);

    @Query("SELECT rp.reportId AS reportId, rp.date AS date, rp.debtAmount AS debtAmount, rp.status AS status, rp.mileage AS mileage, rp.checkpointArrival AS checkpointArrival, rp.checkpointExit AS checkpointExit " +
            "FROM ReportPenalty rp " +
            "INNER JOIN rp.carFeatures cf " +
            "WHERE cf.numberPlate = :number_plate " +
            "ORDER BY rp.date DESC")
    List<ReportPenaltyResponse> getReportPenaltiesByNumberPlate(@Param("number_plate") String numberPlate);
}
