package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Exceptions.DoubleReportException;
import com.example.SistemaGIS.Model.*;
import com.example.SistemaGIS.Repository.OwnerRepository;
import com.example.SistemaGIS.Repository.ReportPenaltyRepository;
import com.example.SistemaGIS.Service.ReportPenaltyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/drivers")
public class ReportPenaltiesController {
    private final ReportPenaltyRepository  reportPenaltyRepository;
    private final ReportPenaltyService reportPenaltyService;
    private final OwnerRepository ownerRepository;

    @PostMapping("/add-report-penalty")
    @PreAuthorize("hasAnyAuthority('ROOT', 'SIS_POLICIA', 'POLICIA')")
    public ResponseEntity<?> addReportPenalty(@RequestBody ReportPenaltyPostRequestDTO reportPenaltyData){
        try {
            ReportPenalty reportPenalty = reportPenaltyService.instanceReportPenalty(reportPenaltyData);
            ReportPenalty reportPenaltyWithDebt = reportPenaltyService.calcDebtAmount(reportPenalty);
            ReportPenalty savedReport =  reportPenaltyRepository.save(reportPenaltyWithDebt);
            ReportPenaltyResponseDTO response = new ReportPenaltyResponseDTO(savedReport);
            URI location = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/drivers/get-report-penalty").toUriString());
            return ResponseEntity.created(location).body(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/cancel-report-penalty")
    @PreAuthorize("hasAnyAuthority('ROOT', 'SIS_POLICIA', 'POLICIA')")
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
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @GetMapping("/get-report-penalties")
    public ResponseEntity<?> getReportPenalties(@RequestParam("number_plate") String numberPlate, @RequestParam("ruat") String ruat, @RequestParam("status") Integer status){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<String> roles = new HashSet<>();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                roles.add(authority.getAuthority());
            }

            if (roles.contains("SIS_POLICIA") || roles.contains("POLICIA")){
                if (numberPlate != null && ruat != null && !numberPlate.isEmpty() && !ruat.isEmpty()){
                    List<ReportPenalty> reportPenalties = reportPenaltyRepository.findAllByCarFeaturesNumberPlateAndCarFeaturesRuatAndStatusOrderByDateDesc(numberPlate, ruat, status);
                    Owner owner = ownerRepository.findAllByOwnerCarNumberPlateAndOwnerCarRuat(numberPlate, ruat).orElseThrow(() -> new RuntimeException("Dueño no encontrado"));
                    Collection<CarFeatures> carFeatures = owner.getOwnerCar();
                    CarFeatures ownerCar = carFeatures.stream().filter(car -> car.getNumberPlate().equals(numberPlate) && car.getRuat().equals(ruat)).findFirst().orElseThrow(() -> new RuntimeException("Características del auto no encontradas"));
                    OneNumberPlateReportPenaltyResponseDTO response = new OneNumberPlateReportPenaltyResponseDTO(owner, ownerCar, reportPenalties);
                    return ResponseEntity.ok(response);
                } else if(numberPlate != null && !numberPlate.isEmpty()) {
                    List<ReportPenalty> reportPenalties = reportPenaltyRepository.findAllByCarFeaturesNumberPlateContainingAndStatusOrderByDateDesc(numberPlate, status).orElseThrow(() -> new RuntimeException("Reportes de infracción no encontrados"));
                    Owner owner = ownerRepository.findAllByOwnerCarNumberPlate(numberPlate).orElseThrow(() -> new RuntimeException("Dueño no encontrado"));
                    Collection<CarFeatures> carFeatures = owner.getOwnerCar();
                    CarFeatures ownerCar = carFeatures.stream().filter(car -> car.getNumberPlate().equals(numberPlate)).findFirst().orElseThrow(() -> new RuntimeException("Características del auto no encontradas"));
                    OneNumberPlateReportPenaltyResponseDTO response = new OneNumberPlateReportPenaltyResponseDTO(owner, ownerCar, reportPenalties);
                    return ResponseEntity.ok(response);
                } else if (ruat != null && !ruat.isEmpty()){
                    List<ReportPenalty> reportPenalties = reportPenaltyRepository.findAllByCarFeaturesRuatContainingAndStatusOrderByDateDesc(ruat, status).orElseThrow(() -> new RuntimeException("Reportes de infracción no encontrados"));
                    Owner owner = ownerRepository.findAllByOwnerCarRuat(ruat).orElseThrow(() -> new RuntimeException("Dueño no encontrado"));
                    Collection<CarFeatures> carFeatures = owner.getOwnerCar();
                    CarFeatures ownerCar = carFeatures.stream().filter(car -> car.getRuat().equals(ruat)).findFirst().orElseThrow(() -> new RuntimeException("Características del auto no encontradas"));
                    OneNumberPlateReportPenaltyResponseDTO response = new OneNumberPlateReportPenaltyResponseDTO(owner, ownerCar, reportPenalties);
                    return ResponseEntity.ok(response);
                } else {
                    throw new RuntimeException("Número de placa y/o ruat no pueden estar vacíos");
                }
            } else {
                if ( numberPlate == null || ruat == null || numberPlate.isEmpty() || ruat.isEmpty()){
                    throw new RuntimeException("Número de placa y/o ruat no pueden estar vacíos");
                }
                List<ReportPenalty> reportPenalties = reportPenaltyRepository.findAllByCarFeaturesNumberPlateAndCarFeaturesRuatAndStatusOrderByDateDesc(numberPlate, ruat, status);
                Owner owner = ownerRepository.findAllByOwnerCarNumberPlateAndOwnerCarRuat(numberPlate, ruat).orElseThrow(() -> new RuntimeException("Dueño no encontrado"));
                Collection<CarFeatures> carFeatures = owner.getOwnerCar();
                CarFeatures ownerCar = carFeatures.stream().filter(car -> car.getNumberPlate().equals(numberPlate) && car.getRuat().equals(ruat)).findFirst().orElseThrow(() -> new RuntimeException("Características del auto no encontradas"));
                OneNumberPlateReportPenaltyResponseDTO response = new OneNumberPlateReportPenaltyResponseDTO(owner, ownerCar, reportPenalties);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-report-penalties")
    @PreAuthorize("hasAnyAuthority('ROOT', 'SIS_POLICIA', 'POLICIA')")
    public ResponseEntity<?> getAllReportPenalties(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam("status") Integer status){
        try {
            LocalDateTime localDateTime = date.atStartOfDay();
            List<ReportPenalty> reportPenalties = reportPenaltyRepository.findAllByDateGreaterThanEqualAndStatusOrderByDateDesc(localDateTime, status);
            List<ReportPenaltyResponseDTO> response = reportPenaltyService.getReportPenaltyResponseDTOList(reportPenalties);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
