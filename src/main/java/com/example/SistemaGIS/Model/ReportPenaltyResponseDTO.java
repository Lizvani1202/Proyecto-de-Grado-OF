package com.example.SistemaGIS.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPenaltyResponseDTO {
    public Long reportPenaltyId;
    public CarFeaturesDTO carFeatures;
    public OwnerDTO owner;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime date;
    public Integer debtAmount;
    public TollDTO toll;
    public Integer status;


    public ReportPenaltyResponseDTO(ReportPenalty reportPenalty){
        this.setReportPenaltyId(reportPenalty.getReportPenaltyId());
        CarFeaturesDTO carFeaturesDTO = new CarFeaturesDTO();
        carFeaturesDTO.setCarFeaturesId(reportPenalty.getCarFeatures().getCarFeaturesId());
        carFeaturesDTO.setNumberPlate(reportPenalty.getCarFeatures().getNumberPlate());
        carFeaturesDTO.setBrand(reportPenalty.getCarFeatures().getBrand());
        carFeaturesDTO.setRuat(reportPenalty.getCarFeatures().getRuat());
        carFeaturesDTO.setPolicy(reportPenalty.getCarFeatures().getPolicy());
        carFeaturesDTO.setCountry(reportPenalty.getCarFeatures().getCountry());
        carFeaturesDTO.setTraction(reportPenalty.getCarFeatures().getTraction());
        carFeaturesDTO.setColor(reportPenalty.getCarFeatures().getColor());
        carFeaturesDTO.setType(reportPenalty.getCarFeatures().getType());
        carFeaturesDTO.setModel(reportPenalty.getCarFeatures().getModel());
        carFeaturesDTO.setService(reportPenalty.getCarFeatures().getService());
        carFeaturesDTO.setFilling(reportPenalty.getCarFeatures().getFilling());
        carFeaturesDTO.setStatus(reportPenalty.getCarFeatures().getStatus());
        this.setCarFeatures(carFeaturesDTO);
        this.setDate(reportPenalty.getDate());
        this.setDebtAmount(reportPenalty.getDebtAmount());
        this.setStatus(reportPenalty.getStatus());
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setOwnerId(reportPenalty.getOwner().getOwnerId());
        ownerDTO.setStatus(reportPenalty.getOwner().getStatus());
        personDTO personDTO = new personDTO();
        personDTO.setPersonId(reportPenalty.getOwner().getPerson().getPersonId());
        personDTO.setFirstName(reportPenalty.getOwner().getPerson().getFirstName());
        personDTO.setFirstSurname(reportPenalty.getOwner().getPerson().getFirstSurname());
        personDTO.setSecondSurname(reportPenalty.getOwner().getPerson().getSecondSurname());
        personDTO.setBirthDate(reportPenalty.getOwner().getPerson().getBirthDate());
        personDTO.setAddress(reportPenalty.getOwner().getPerson().getAddress());
        personDTO.setCity(reportPenalty.getOwner().getPerson().getCity());
        ownerDTO.setPerson(personDTO);
        this.setOwner(ownerDTO);
        TollDTO tollDTO = new TollDTO();
        tollDTO.tollId = reportPenalty.getToll().getTollId();
        LocationCheckpointDTO checkpointArrivalDTO = new LocationCheckpointDTO();
        checkpointArrivalDTO.locationId = reportPenalty.getToll().getCheckpointArrival().getLocationId();
        checkpointArrivalDTO.name = reportPenalty.getToll().getCheckpointArrival().getName();
        checkpointArrivalDTO.latitud = reportPenalty.getToll().getCheckpointArrival().getLatitud();
        checkpointArrivalDTO.longitud = reportPenalty.getToll().getCheckpointArrival().getLongitud();
        tollDTO.setCheckpointArrival(checkpointArrivalDTO);
        LocationCheckpointDTO checkpointExitDTO = new LocationCheckpointDTO();
        checkpointExitDTO.locationId = reportPenalty.getToll().getCheckpointExit().getLocationId();
        checkpointExitDTO.name = reportPenalty.getToll().getCheckpointExit().getName();
        checkpointExitDTO.latitud = reportPenalty.getToll().getCheckpointExit().getLatitud();
        checkpointExitDTO.longitud = reportPenalty.getToll().getCheckpointExit().getLongitud();
        tollDTO.setCheckpointExit(checkpointExitDTO);
        tollDTO.setMileageKm(reportPenalty.getToll().getMileageKm());
        tollDTO.setMaxTimeMin(reportPenalty.getToll().getMaxTimeMin());
        this.setToll(tollDTO);
    }

    @Data
    public class TollDTO {
        public Long tollId;
        public LocationCheckpointDTO checkpointArrival;
        public LocationCheckpointDTO checkpointExit;
        public Integer mileageKm;
        public Integer maxTimeMin;
    }

    @Data
    public class LocationCheckpointDTO {
        public Long locationId;
        public String name;
        public BigDecimal latitud;
        public BigDecimal longitud;
    }

    @Data
    class CarFeaturesDTO {
        public Long carFeaturesId;
        public String numberPlate;
        public String ruat;
        public String brand;
        public Integer policy;
        public Integer country;
        public String traction;
        public String color;
        public String type;
        public String model;
        public String service;
        public String filling;
        public Integer status;
    }

    @Data
    class personDTO {
        public Long personId;
        public String firstName;
        public String firstSurname;
        public String secondSurname;

        @JsonFormat(pattern = "yyyy-MM-dd")
        public LocalDate birthDate;

        public String address;
        public String city;
    }
    @Data
    class OwnerDTO {
        public Long ownerId;
        public Integer status;
        public personDTO person;
    }
}
