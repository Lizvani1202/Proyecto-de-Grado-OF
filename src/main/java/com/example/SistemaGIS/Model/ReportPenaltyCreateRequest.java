package com.example.SistemaGIS.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Date;

@Data
public class ReportPenaltyCreateRequest {

    private Long carFeaturesId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private Long ownerId;

    private Integer mileage;

    private String checkpointArrival;

    private String checkpointExit;
}
