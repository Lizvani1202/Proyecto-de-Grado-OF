package com.example.SistemaGIS.Repository;

import com.example.SistemaGIS.Model.CarFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarFeaturesRepository extends JpaRepository<CarFeatures, Long>{

    @Query("SELECT cf FROM CarFeatures cf WHERE cf.numberPlate = :number_plate")
    Optional<CarFeatures> getCarFeaturesByNumberPlate(@Param("number_plate") String numberPlate);
}
