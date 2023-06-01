package com.example.SistemaGIS.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface ReportPenaltyResponse {
    Long getReportId();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date getDate();

    Integer getDebtAmount();

    Integer getStatus();

    Integer getMileage();

    String getCheckpointArrival();

    String getCheckpointExit();
}
