package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.IncidentReport;
import com.example.SistemaGIS.Model.IncidentReportResponseDTO;
import com.example.SistemaGIS.Repository.IncidentReportRepository;
import com.example.SistemaGIS.Service.IncidenReportService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class IncidentReportController {
    private final IncidentReportRepository reportPenaltyRepository;
    private final IncidenReportService reportPenaltyService;

    @GetMapping("/get-all-incident-reports")
    @PreAuthorize("hasAnyAuthority('ROOT', 'SIS_ADMIN_ABC', 'ADMIN_ABC')")
    public ResponseEntity<?> getAllIncidentReports(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam("status") Integer status){
        try {
            List<IncidentReport> reportPenalties = reportPenaltyRepository.getIncidentReportsByDateAnAndStatus(date, status);
            List<IncidentReportResponseDTO> response = reportPenaltyService.getReportPenaltyResponseDTOList(reportPenalties);
            URI location = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customers/get-all-incident-reports").toUriString());
            return ResponseEntity.created(location).body(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
