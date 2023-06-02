package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.*;
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
    public ResponseEntity<?> addReportPenalty(@RequestBody ReportPenaltyPostRequestDTO reportPenaltyData){
        try {
            ReportPenalty reportPenalty = reportPenaltyService.instanceReportPenalty(reportPenaltyData);
            ReportPenalty reportPenaltyWithDebt = reportPenaltyService.calcDebtAmount(reportPenalty);
            ReportPenalty savedReport =  reportPenaltyRepository.save(reportPenaltyWithDebt);
            ReportPenaltyResponseDTO response = new ReportPenaltyResponseDTO(savedReport);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

//    /cancel-report-penalty")
    @PutMapping("/cancel-report-penalty")
    public ResponseEntity<?> cancelReportPenalty(@RequestBody ReportPenaltyCancelDTO reportPenaltyCancelDTO){
        try {
            ReportPenalty reportPenalty = reportPenaltyRepository.findById(reportPenaltyCancelDTO.getReportPenaltyId())
                    .orElseThrow(() -> new RuntimeException("Reporte de infracción no encontrado"));
            reportPenalty.setStatus(reportPenaltyCancelDTO.getStatus());
            ReportPenalty savedReport =  reportPenaltyRepository.save(reportPenalty);
            ReportPenaltyResponseDTO response = new ReportPenaltyResponseDTO(savedReport);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }


    @GetMapping("/get-report-penalties")
    public ResponseEntity<?> getReportPenalties(@RequestParam("number_plate") String numberPlate, @RequestParam("status") Integer status){
        try {
            List<ReportPenalty> reportPenalties = reportPenaltyRepository.findAllByCarFeaturesNumberPlateAndStatusOrderByDateDesc(numberPlate, status);
            List<OneNumberPlateReportPenaltyResponseDTO> response = reportPenaltyService.getOneNumberPlateReportPenaltyResponseDTOList(reportPenalties);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping("/get-all-report-penalties")
    public ResponseEntity<?> getAllReportPenalties(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam("status") Integer status){
        try {
            List<ReportPenalty> reportPenalties = reportPenaltyRepository.findAllByDateBetweenAndStatusOrderByDateDesc(date, status);
            List<ReportPenaltyResponseDTO> response = reportPenaltyService.getReportPenaltyResponseDTOList(reportPenalties);
            return ResponseEntity.ok(reportPenalties);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
