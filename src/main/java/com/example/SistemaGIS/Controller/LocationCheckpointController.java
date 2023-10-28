package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.LocationCheckpoint;
import com.example.SistemaGIS.Repository.LocationCheckpointRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/location-checkpoints")
public class LocationCheckpointController {
    private final LocationCheckpointRepository locationCheckpointRepository;

    @GetMapping("/get-all-location-checkpoints")
    public ResponseEntity<?> getAllLocationCheckpoints(){
        try {
            List<LocationCheckpoint> locationCheckpoints = locationCheckpointRepository.findAll();
            URI location = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/location-checkpoints/get-all-location-checkpoints").toUriString());
            return ResponseEntity.created(location).body(locationCheckpoints);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
