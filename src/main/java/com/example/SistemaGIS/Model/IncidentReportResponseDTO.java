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
public class IncidentReportResponseDTO {
    public Long incidentReportId;

    public OwnerDTO owner;

    public GISDTO gis;

    public String ubicacion;

    public Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime date;

    public String type;

    public IncidentReportResponseDTO(IncidentReport incidentReport){
        this.incidentReportId = incidentReport.getIncidentReportId();
        this.owner = new OwnerDTO();
        this.owner.ownerId = incidentReport.getOwner().getOwnerId();
        this.owner.status = incidentReport.getOwner().getStatus();
        this.owner.person = new personDTO();
        this.owner.person.personId = incidentReport.getOwner().getPerson().getPersonId();
        this.owner.person.firstName = incidentReport.getOwner().getPerson().getFirstName();
        this.owner.person.firstSurname = incidentReport.getOwner().getPerson().getFirstSurname();
        this.owner.person.secondSurname = incidentReport.getOwner().getPerson().getSecondSurname();
        this.owner.person.birthDate = incidentReport.getOwner().getPerson().getBirthDate();
        this.owner.person.address = incidentReport.getOwner().getPerson().getAddress();
        this.owner.person.city = incidentReport.getOwner().getPerson().getCity();
        this.gis = new GISDTO();
        this.gis.gisId = incidentReport.getGis().getGisId();
        this.gis.latitud = incidentReport.getGis().getLatitud();
        this.gis.longitud = incidentReport.getGis().getLongitud();
        this.gis.status = incidentReport.getGis().getStatus();
        this.ubicacion = incidentReport.getUbicacion();
        this.status = incidentReport.getStatus();
        this.date = incidentReport.getDate();
        this.type = incidentReport.getTypeIncidentReport().getName();
    }

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
    class OwnerDTO {
        public Long ownerId;
        public Integer status;

        public personDTO person;
    }

    class GISDTO {
        public Long gisId;
        public BigDecimal latitud;
        public BigDecimal longitud;
        public Integer status;
    }
}
