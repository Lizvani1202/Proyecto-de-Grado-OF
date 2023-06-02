package com.example.SistemaGIS.Service;

import com.example.SistemaGIS.Model.*;
import com.example.SistemaGIS.Repository.CarFeaturesRepository;
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
public class ReportPenaltyService {
//    private final ReportPenaltyRepository reportPenaltyRepository;
    private final CarFeaturesRepository carFeaturesRepository;
    private final OwnerRepository ownerRepository;
    private final ReportPenaltyRepository reportPenaltyRepository;

    public ReportPenalty instanceReportPenalty(ReportPenaltyPostRequestDTO reportPenaltyData) {
        CarFeatures existingCarFeatures = carFeaturesRepository.getCarFeaturesByNumberPlate(reportPenaltyData.getCarNumberPlate())
                .orElseThrow(() -> new RuntimeException("Características del vehículo no encontradas"));

        ReportPenalty reportPenalty = new ReportPenalty();
        reportPenalty.setCarFeatures(existingCarFeatures);
        reportPenalty.setOwner(existingCarFeatures.getOwner());
        reportPenalty.setDate(reportPenaltyData.getDate());
        reportPenalty.setMileage(reportPenaltyData.getMileage());
        reportPenalty.setCheckpointArrival(reportPenaltyData.getCheckpointArrival());
        reportPenalty.setCheckpointExit(reportPenaltyData.getCheckpointExit());
        return reportPenalty;
    }

    public ReportPenalty calcDebtAmount(ReportPenalty reportPenalty) {
        ReportPenalty lastReportPenalty = reportPenaltyRepository.findTop1ByCarFeaturesNumberPlateOrderByDateDesc(reportPenalty.getCarFeatures().getNumberPlate())
                .orElse(null);
        //TODO: use lastReportPenalty to calc the debtAmount and set status
        reportPenalty.setDebtAmount(500);
        reportPenalty.setStatus(1);
        return reportPenalty;
    }

    public List<ReportPenaltyResponseDTO> getReportPenaltyResponseDTOList(List<ReportPenalty> reportPenalties) {
        List<ReportPenaltyResponseDTO> response = new ArrayList<>();
        for (ReportPenalty reportPenalty : reportPenalties) {
            ReportPenaltyResponseDTO reportPenaltyResponseDTO = new ReportPenaltyResponseDTO(reportPenalty);
            response.add(reportPenaltyResponseDTO);
        }
        return response;
    }

    public List<OneNumberPlateReportPenaltyResponseDTO> getOneNumberPlateReportPenaltyResponseDTOList(List<ReportPenalty> reportPenalties) {
        List<OneNumberPlateReportPenaltyResponseDTO> response = new ArrayList<>();
        for (ReportPenalty reportPenalty : reportPenalties) {
            OneNumberPlateReportPenaltyResponseDTO reportPenaltyResponseDTO = new OneNumberPlateReportPenaltyResponseDTO(reportPenalty);
            response.add(reportPenaltyResponseDTO);
        }
        return response;
    }
}
