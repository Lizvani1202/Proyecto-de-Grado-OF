package com.example.SistemaGIS.Service;

import com.example.SistemaGIS.Model.*;
import com.example.SistemaGIS.Repository.CarFeaturesRepository;
import com.example.SistemaGIS.Repository.IncidentReportRepository;
import com.example.SistemaGIS.Repository.OwnerRepository;
import com.example.SistemaGIS.Repository.ReportPenaltyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class IncidenReportService {
    private final IncidentReportRepository reportPenaltyRepository;

    public List<IncidentReportResponseDTO> getReportPenaltyResponseDTOList(List<IncidentReport> incidentReports) {
        List<IncidentReportResponseDTO> response = new ArrayList<>();
        for (IncidentReport incidentReport : incidentReports) {
            IncidentReportResponseDTO incidentReportResponseDTO = new IncidentReportResponseDTO(incidentReport);
            response.add(incidentReportResponseDTO);
        }
        return response;
    }
}
