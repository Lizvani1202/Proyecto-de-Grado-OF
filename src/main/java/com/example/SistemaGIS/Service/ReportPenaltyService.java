package com.example.SistemaGIS.Service;

import com.example.SistemaGIS.Model.CarFeatures;
import com.example.SistemaGIS.Model.Owner;
import com.example.SistemaGIS.Model.ReportPenalty;
import com.example.SistemaGIS.Model.ReportPenaltyCreateRequest;
import com.example.SistemaGIS.Repository.CarFeaturesRepository;
import com.example.SistemaGIS.Repository.OwnerRepository;
import com.example.SistemaGIS.Repository.ReportPenaltyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportPenaltyService {
//    private final ReportPenaltyRepository reportPenaltyRepository;
    private final CarFeaturesRepository carFeaturesRepository;
    private final OwnerRepository ownerRepository;
    private final ReportPenaltyRepository reportPenaltyRepository;

    public ReportPenalty instanceReportPenalty(ReportPenaltyCreateRequest reportPenaltyData) {
        CarFeatures existingCarFeatures = carFeaturesRepository.findById(reportPenaltyData.getCarFeaturesId())
                .orElseThrow(() -> new RuntimeException("Características del vehículo no encontradas"));
        Owner existingOwner = ownerRepository.findById(reportPenaltyData.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Dueño no encontrado"));

        ReportPenalty reportPenalty = new ReportPenalty();
        reportPenalty.setCarFeatures(existingCarFeatures);
        reportPenalty.setOwner(existingOwner);
        reportPenalty.setDate(reportPenaltyData.getDate());
        reportPenalty.setMileage(reportPenaltyData.getMileage());
        reportPenalty.setCheckpointArrival(reportPenaltyData.getCheckpointArrival());
        reportPenalty.setCheckpointExit(reportPenaltyData.getCheckpointExit());
        return reportPenalty;
    }

    public ReportPenalty calcDebtAmount(ReportPenalty reportPenalty) {
        ReportPenalty lastReportPenalty = reportPenaltyRepository.getLastPenaltyReportByNumberPlate(reportPenalty.getCarFeatures().getNumberPlate());
        //TODO: use lastReportPenalty to calc the debtAmount and set status
        reportPenalty.setDebtAmount(500);
        reportPenalty.setStatus(1);
        return reportPenalty;
    }
}
