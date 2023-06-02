package com.example.SistemaGIS.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public interface AllIncidentReportDTO {
    Long getIncidentReportId();

    OwnerDTO getOwner();

    GISDTO getGis();

    String getUbicacion();

    Integer getStatus();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date getDate();

    String getType();

    interface personDTO {
        Long getPersonId();
        String getFirstName();
        String getFirstSurname();
        String getSecondSurname();

        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getBirthDate();

        String getAddress();
        String getCity();
    }
    interface OwnerDTO {
        Long getOwnerId();
        Integer getStatus();

        personDTO getPerson();
    }

    interface GISDTO {
        Long getGisId();
        BigDecimal getLatitud();
        BigDecimal getLongitud();
        Integer getStatus();
    }
}
