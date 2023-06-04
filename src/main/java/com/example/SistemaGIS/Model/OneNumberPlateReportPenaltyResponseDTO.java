package com.example.SistemaGIS.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneNumberPlateReportPenaltyResponseDTO {
    public CarFeaturesDTO carFeatures;
    public OwnerDTO owner;
    public Collection<ReportPenaltiesDTO> reportPenalties;


    public OneNumberPlateReportPenaltyResponseDTO(Owner owner, CarFeatures carFeatures, Collection<ReportPenalty> reportPenaltyCollection){
        CarFeaturesDTO carFeaturesDTO = new CarFeaturesDTO();
        carFeaturesDTO.setCarFeaturesId(carFeatures.getCarFeaturesId());
        carFeaturesDTO.setNumberPlate(carFeatures.getNumberPlate());
        carFeaturesDTO.setBrand(carFeatures.getBrand());
        carFeaturesDTO.setPolicy(carFeatures.getPolicy());
        carFeaturesDTO.setCountry(carFeatures.getCountry());
        carFeaturesDTO.setTraction(carFeatures.getTraction());
        carFeaturesDTO.setColor(carFeatures.getColor());
        carFeaturesDTO.setType(carFeatures.getType());
        carFeaturesDTO.setModel(carFeatures.getModel());
        carFeaturesDTO.setService(carFeatures.getService());
        carFeaturesDTO.setFilling(carFeatures.getFilling());
        carFeaturesDTO.setStatus(carFeatures.getStatus());
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setOwnerId(owner.getOwnerId());
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonId(owner.getPerson().getPersonId());
        personDTO.setFirstName(owner.getPerson().getFirstName());
        personDTO.setFirstSurname(owner.getPerson().getFirstSurname());
        personDTO.setSecondSurname(owner.getPerson().getSecondSurname());
        personDTO.setBirthDate(owner.getPerson().getBirthDate());
        personDTO.setAddress(owner.getPerson().getAddress());
        personDTO.setCity(owner.getPerson().getCity());
        ownerDTO.setPerson(personDTO);
        ownerDTO.setStatus(owner.getStatus());
        this.carFeatures = carFeaturesDTO;
        this.owner = ownerDTO;
        Collection<ReportPenaltiesDTO> reportPenaltiesDTOCollection = new ArrayList<>();
        for (ReportPenalty reportPenalty: reportPenaltyCollection) {
            ReportPenaltiesDTO reportPenaltiesDTO = new ReportPenaltiesDTO();
            reportPenaltiesDTO.setReportPenaltyId(reportPenalty.getReportPenaltyId());
            reportPenaltiesDTO.setDate(reportPenalty.getDate());
            reportPenaltiesDTO.setDebtAmount(reportPenalty.getDebtAmount());
            reportPenaltiesDTO.setStatus(reportPenalty.getStatus());
            reportPenaltiesDTO.setMileage(reportPenalty.getMileage());
            reportPenaltiesDTO.setCheckpointArrival(reportPenalty.getCheckpointArrival());
            reportPenaltiesDTO.setCheckpointExit(reportPenalty.getCheckpointExit());
            reportPenaltiesDTOCollection.add(reportPenaltiesDTO);
        }
        this.reportPenalties = reportPenaltiesDTOCollection;
    }
    @Data
    public class CarFeaturesDTO {
        public Long carFeaturesId;
        public String numberPlate;
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
    public class PersonDTO {
        public Long personId;
        public String firstName;
        public String firstSurname;
        public String secondSurname;
        public LocalDate birthDate;
        public String address;
        public String city;
    }
    @Data
    public class OwnerDTO {
        public Long ownerId;
        public PersonDTO person;
        public Integer status;
    }
    @Data

    public class ReportPenaltiesDTO {
        public Long reportPenaltyId;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        public LocalDateTime date;

        public Integer debtAmount;

        public Integer status;

        public Integer mileage;

        public String checkpointArrival;

        public String checkpointExit;
    }
}