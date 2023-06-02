package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.AllReportPenaltyResponseDTO;
import com.example.SistemaGIS.Model.ReportPenalty;
import com.example.SistemaGIS.Model.ReportPenaltyCreateRequest;
import com.example.SistemaGIS.Model.ReportPenaltyResponse;
import com.example.SistemaGIS.Repository.ReportPenaltyRepository;
import com.example.SistemaGIS.Service.ReportPenaltyService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/drivers")
public class ReportPenaltiesController {
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

    @GetMapping("/get-report-penalties")
    public ResponseEntity<?> getReportPenalties(@RequestParam("number_plate") String numberPlate, @RequestParam("status") Integer status){
        try {
            List<ReportPenaltyResponse> reportPenalties = reportPenaltyRepository.getReportPenaltiesByNumberPlateAndStatus(numberPlate, status);
            return ResponseEntity.ok(reportPenalties);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping("/get-all-report-penalties")
    public ResponseEntity<?> getAllReportPenalties(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam("status") Integer status){
        try {
            List<AllReportPenaltyResponseDTO> reportPenalties = reportPenaltyRepository.getReportPenaltiesByDateAnAndStatus(date, status);
            return ResponseEntity.ok(reportPenalties);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
