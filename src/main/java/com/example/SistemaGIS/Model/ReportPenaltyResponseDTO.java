package com.example.SistemaGIS.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPenaltyResponseDTO {

    public Long reportPenaltyId;

    public CarFeaturesDTO carFeatures;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime date;

    public Integer debtAmount;
    public OwnerDTO owner;

    public Integer status;

    public Integer mileage;

    public String checkpointArrival;

    public String checkpointExit;

    public ReportPenaltyResponseDTO(ReportPenalty reportPenalty){
        this.reportPenaltyId = reportPenalty.getReportPenaltyId();
        this.carFeatures = new CarFeaturesDTO();
        this.carFeatures.CarFeaturesId = reportPenalty.getCarFeatures().getCarFeaturesId();
        this.carFeatures.NumberPlate = reportPenalty.getCarFeatures().getNumberPlate();
        this.carFeatures.Brand = reportPenalty.getCarFeatures().getBrand();
        this.carFeatures.Policy = reportPenalty.getCarFeatures().getPolicy();
        this.carFeatures.Country = reportPenalty.getCarFeatures().getCountry();
        this.carFeatures.Traction = reportPenalty.getCarFeatures().getTraction();
        this.carFeatures.Color = reportPenalty.getCarFeatures().getColor();
        this.carFeatures.Type = reportPenalty.getCarFeatures().getType();
        this.carFeatures.Model = reportPenalty.getCarFeatures().getModel();
        this.carFeatures.Service = reportPenalty.getCarFeatures().getService();
        this.carFeatures.Filling = reportPenalty.getCarFeatures().getFilling();
        this.carFeatures.Status = reportPenalty.getCarFeatures().getStatus();
        this.date = reportPenalty.getDate();
        this.debtAmount = reportPenalty.getDebtAmount();
        this.owner = new OwnerDTO();
        this.owner.OwnerId = reportPenalty.getOwner().getOwnerId();
        this.owner.Status = reportPenalty.getOwner().getStatus();
        this.owner.Person = new personDTO();
        this.owner.Person.PersonId = reportPenalty.getOwner().getPerson().getPersonId();
        this.owner.Person.FirstName = reportPenalty.getOwner().getPerson().getFirstName();
        this.owner.Person.FirstSurname = reportPenalty.getOwner().getPerson().getFirstSurname();
        this.owner.Person.SecondSurname = reportPenalty.getOwner().getPerson().getSecondSurname();
        this.owner.Person.BirthDate = reportPenalty.getOwner().getPerson().getBirthDate();
        this.owner.Person.Address = reportPenalty.getOwner().getPerson().getAddress();
        this.owner.Person.City = reportPenalty.getOwner().getPerson().getCity();
        this.status = reportPenalty.getStatus();
        this.mileage = reportPenalty.getMileage();
        this.checkpointArrival = reportPenalty.getCheckpointArrival();
        this.checkpointExit = reportPenalty.getCheckpointExit();
    }


    class CarFeaturesDTO {
        public Long CarFeaturesId;
        public String NumberPlate;
        public String Brand;
        public Integer Policy;
        public Integer Country;
        public String Traction;
        public String Color;
        public String Type;
        public String Model;
        public String Service;
        public String Filling;
        public Integer Status;
    }

    class personDTO {
        public Long PersonId;
        public String FirstName;
        public String FirstSurname;
        public String SecondSurname;

        @JsonFormat(pattern = "yyyy-MM-dd")
        public LocalDate BirthDate;

        public String Address;
        public String City;
    }
    class OwnerDTO {
        public Long OwnerId;
        public Integer Status;
        public personDTO Person;
    }
}
