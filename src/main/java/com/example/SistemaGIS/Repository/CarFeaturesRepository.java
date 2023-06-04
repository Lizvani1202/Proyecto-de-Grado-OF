package com.example.SistemaGIS.Repository;

import com.example.SistemaGIS.Model.CarFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarFeaturesRepository extends JpaRepository<CarFeatures, Long>{
    Optional<CarFeatures> findCarFeaturesByNumberPlate(@Param("number_plate") String numberPlate);
}
