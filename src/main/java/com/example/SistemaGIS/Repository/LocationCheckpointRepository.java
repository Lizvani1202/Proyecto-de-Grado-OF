package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.LocationCheckpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationCheckpointRepository  extends JpaRepository<LocationCheckpoint, Long> {
    Optional<LocationCheckpoint> getLocationCheckpointByName(@Param("name") String name);
}
