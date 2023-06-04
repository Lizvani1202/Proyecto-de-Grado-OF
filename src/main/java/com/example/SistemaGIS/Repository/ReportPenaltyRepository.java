package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.ReportPenalty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportPenaltyRepository extends CrudRepository<ReportPenalty, Long> {
    Optional<ReportPenalty> findTop1ByCarFeaturesNumberPlateOrderByDateDesc(@Param("number_plate") String numberPlate);
    List<ReportPenalty> findAllByCarFeaturesNumberPlateAndCarFeaturesRuatAndStatusOrderByDateDesc(@Param("number_plate") String numberPlate, @Param("ruat") String ruat, @Param("status") Integer status);
    List<ReportPenalty> findAllByDateGreaterThanEqualAndStatusOrderByDateDesc(@Param("date") LocalDateTime date, @Param("status") Integer status);
}
