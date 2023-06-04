package com.example.SistemaGIS.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneNumberPlateReportPenaltyResponseDTO {
    public Long reportPenaltyId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime date;

    public Integer debtAmount;

    public Integer status;

    public Integer mileage;

    public String checkpointArrival;

    public String checkpointExit;

    public OneNumberPlateReportPenaltyResponseDTO(ReportPenalty reportPenalty){
        this.reportPenaltyId = reportPenalty.getReportPenaltyId();
        this.date = reportPenalty.getDate();
        this.debtAmount = reportPenalty.getDebtAmount();
        this.status = reportPenalty.getStatus();
        this.mileage = reportPenalty.getMileage();
        this.checkpointArrival = reportPenalty.getCheckpointArrival();
        this.checkpointExit = reportPenalty.getCheckpointExit();
    }
}
