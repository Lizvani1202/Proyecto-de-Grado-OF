package com.example.SistemaGIS.Service;

import com.example.SistemaGIS.Model.*;
import com.example.SistemaGIS.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class IncidenReportService {
    private final IncidentReportRepository reportPenaltyRepository;
    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;
    private final TypeIncidentRepository typeIncidentRepository;

    public List<IncidentReportResponseDTO> getReportPenaltyResponseDTOList(List<IncidentReport> incidentReports) {
        List<IncidentReportResponseDTO> response = new ArrayList<>();
        for (IncidentReport incidentReport : incidentReports) {
            IncidentReportResponseDTO incidentReportResponseDTO = new IncidentReportResponseDTO(incidentReport);
            response.add(incidentReportResponseDTO);
        }
        return response;
    }

    public IncidentReport instanceIncidentReport(IncidentReportRequestDTO incidentReportRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Owner owner = ownerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));

        IncidentReport incidentReport = new IncidentReport();
        incidentReport.setUbicacion(incidentReportRequestDTO.getUbicacion());
        incidentReport.setStatus(1);
        incidentReport.setDate(LocalDateTime.now());
        TypeIncidentReport typeIncidentReport = typeIncidentRepository.findByName(incidentReportRequestDTO.getType())
                .orElseThrow(() -> new RuntimeException("No se encontro el tipo de incidente"));
        incidentReport.setTypeIncidentReport(typeIncidentReport);
        GIS gis = new GIS();
        gis.setLatitud(incidentReportRequestDTO.getLatitud());
        gis.setLongitud(incidentReportRequestDTO.getLongitud());
        gis.setStatus(1);
        incidentReport.setGis(gis);
        incidentReport.setOwner(owner);
        log.info("incidentReport: " + incidentReport);
        return incidentReport;
    }
}
