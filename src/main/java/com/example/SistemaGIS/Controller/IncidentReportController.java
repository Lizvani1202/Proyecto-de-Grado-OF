package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.IncidentReport;
import com.example.SistemaGIS.Model.IncidentReportRequestDTO;
import com.example.SistemaGIS.Model.IncidentReportResponseDTO;
import com.example.SistemaGIS.Repository.IncidentReportRepository;
import com.example.SistemaGIS.Service.IncidenReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.TimeZone.getTimeZone;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class IncidentReportController {
    private final IncidentReportRepository reportPenaltyRepository;
    private final IncidenReportService reportPenaltyService;

    @GetMapping("/get-all-incident-reports")
    @PreAuthorize("hasAnyAuthority('ROOT', 'SIS_ADMIN_ABC', 'ADMIN_ABC')")
    public ResponseEntity<?> getAllIncidentReports(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam("status") Integer status){
        try {
            LocalDateTime localDateTime = date.atStartOfDay();
            List<IncidentReport> reportPenalties = reportPenaltyRepository.findAllByDateGreaterThanEqualAndStatusEqualsOrderByDateDesc(localDateTime, status);
            List<IncidentReportResponseDTO> response = reportPenaltyService.getReportPenaltyResponseDTOList(reportPenalties);
            URI location = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customers/get-all-incident-reports").toUriString());
            return ResponseEntity.created(location).body(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add-incident-report")
    public ResponseEntity<?> createIncidentReport(@RequestBody IncidentReportRequestDTO incidentReportData){
        try {
            IncidentReport incidentReport = reportPenaltyService.instanceIncidentReport(incidentReportData);
            IncidentReport savedReport =  reportPenaltyRepository.save(incidentReport);
            log.info("Incident report saved: " + savedReport.getDate());
            IncidentReportResponseDTO response = new IncidentReportResponseDTO(savedReport);
            URI location = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customers/create-incident-report").toUriString());
            log.info("Incident report datetime: " + response.getDate());
            return ResponseEntity.created(location).body(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
