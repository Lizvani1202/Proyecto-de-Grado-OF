package com.example.SistemaGIS.Repository;

import com.example.SistemaGIS.Model.CarFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFeaturesRepository extends JpaRepository<CarFeatures, Long>{

}
