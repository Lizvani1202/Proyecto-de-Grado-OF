package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.Toll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TollRepository extends JpaRepository<Toll, Long> {
    Optional<Toll> findTollByCheckpointArrivalNameAndCheckpointExitName(@Param("checkpoint_arrival_name") String checkpointArrivalName, @Param("checkpoint_exit_name") String checkpointExitName);
}
