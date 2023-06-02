package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.AllIncidentReportDTO;
import com.example.SistemaGIS.Repository.IncidentReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
public class IncidentReportController {
    private final IncidentReportRepository reportPenaltyRepository;

    @GetMapping("/get-all-incident-reports")
    public ResponseEntity<?> getAllIncidentReports(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam("status") Integer status){
        try {
            List<AllIncidentReportDTO> reportPenalties = reportPenaltyRepository.getIncidentReportsByDateAnAndStatus(date, status);
            return ResponseEntity.ok(reportPenalties);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
