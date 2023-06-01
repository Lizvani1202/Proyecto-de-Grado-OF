package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.ReportPenalty;
import com.example.SistemaGIS.Model.ReportPenaltyCreateRequest;
import com.example.SistemaGIS.Repository.ReportPenaltyRepository;
import com.example.SistemaGIS.Service.ReportPenaltyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/drivers")
public class DriverController {
    private final ReportPenaltyRepository  reportPenaltyRepository;
    private final ReportPenaltyService reportPenaltyService;

    @PostMapping("/add-report-penalty")
    public ResponseEntity<?> addReportPenalty(@RequestBody ReportPenaltyCreateRequest reportPenaltyData){
        try {
            ReportPenalty reportPenalty = reportPenaltyService.instanceReportPenalty(reportPenaltyData);
            ReportPenalty reportPenaltyWithDebt = reportPenaltyService.calcDebtAmount(reportPenalty);
            ReportPenalty savedReport =  reportPenaltyRepository.save(reportPenaltyWithDebt);
            return ResponseEntity.ok(savedReport);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
