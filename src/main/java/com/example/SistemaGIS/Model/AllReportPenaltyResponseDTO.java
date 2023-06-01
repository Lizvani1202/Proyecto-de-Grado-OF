package com.example.SistemaGIS.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface AllReportPenaltyResponseDTO {
    Long getReportId();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date getDate();

    OwnerDTO getOwner();
    Integer getDebtAmount();

    Integer getStatus();

    Integer getMileage();

    String getCheckpointArrival();

    String getCheckpointExit();

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
}
