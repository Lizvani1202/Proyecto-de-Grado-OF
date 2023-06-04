package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.ReportPenalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportPenaltyRepository extends CrudRepository<ReportPenalty, Long> {
    Optional<ReportPenalty> findTop1ByCarFeaturesNumberPlateOrderByDateDesc(@Param("number_plate") String numberPlate);
    List<ReportPenalty> findAllByCarFeaturesNumberPlateAndCarFeaturesRuatAndStatusOrderByDateDesc(@Param("number_plate") String numberPlate, @Param("ruat") String ruat, @Param("status") Integer status);

    @Query("SELECT rp " +
            "FROM ReportPenalty rp " +
            "INNER JOIN rp.carFeatures cf " +
            "INNER JOIN cf.owner o " +
            "INNER JOIN o.person p " +
            "WHERE rp.date BETWEEN :date AND NOW() " +
            "AND rp.status = :status " +
            "ORDER BY rp.date DESC")
    List<ReportPenalty> findAllByDateBetweenAndStatusOrderByDateDesc(@Param("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime date, @Param("status") Integer status);
}
