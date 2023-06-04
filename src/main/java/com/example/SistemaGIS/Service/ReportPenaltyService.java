package com.example.SistemaGIS.Service;

import com.example.SistemaGIS.Model.*;
import com.example.SistemaGIS.Repository.CarFeaturesRepository;
import com.example.SistemaGIS.Repository.ReportPenaltyRepository;
import com.example.SistemaGIS.Repository.TollRepository;
import com.example.SistemaGIS.Utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class ReportPenaltyService {
    private final CarFeaturesRepository carFeaturesRepository;
    private final ReportPenaltyRepository reportPenaltyRepository;
    private final TollRepository tollRepository;

    public ReportPenalty instanceReportPenalty(ReportPenaltyPostRequestDTO reportPenaltyData) {
        CarFeatures existingCarFeatures = carFeaturesRepository.findCarFeaturesByNumberPlate(reportPenaltyData.getCarNumberPlate())
                .orElseThrow(() -> new RuntimeException("Características del vehículo no encontradas"));

        Toll existingToll = tollRepository.findTollByCheckpointArrivalNameAndCheckpointExitName(reportPenaltyData.checkpointArrivalName, reportPenaltyData.checkpointExitName)
                .orElseThrow(() -> new RuntimeException("Relacion entre puntos de control no encontrada"));

        ReportPenalty reportPenalty = new ReportPenalty();
        reportPenalty.setCarFeatures(existingCarFeatures);
        reportPenalty.setOwner(existingCarFeatures.getOwner());
        reportPenalty.setDebtAmount(0);
        reportPenalty.setDate(LocalDateTime.now());
        reportPenalty.setToll(existingToll);
        reportPenalty.setStatus(0);
        return reportPenalty;
    }

    public ReportPenalty calcDebtAmount(ReportPenalty reportPenalty) {
        reportPenaltyRepository.findTop1ByCarFeaturesNumberPlateOrderByDateDesc(reportPenalty.getCarFeatures().getNumberPlate())
                .ifPresent(lastReportPenalty -> {
                        Long lastReportExitCheckpointId = lastReportPenalty.getToll().getCheckpointExit().getLocationId();
                        Long currentReportArrivalCheckpointId = reportPenalty.getToll().getCheckpointArrival().getLocationId();
                        if (lastReportExitCheckpointId.equals(currentReportArrivalCheckpointId)) {
                            Integer differenceInMinutes = Utils.differenceInMinutes(lastReportPenalty.getDate(), reportPenalty.getDate());
                            Integer maxTimeMin = reportPenalty.getToll().getMaxTimeMin();
                            if (differenceInMinutes < maxTimeMin) {
                                reportPenalty.setDebtAmount(200);
                                reportPenalty.setStatus(1);
                            }
                        }
                });
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
}
