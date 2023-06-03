package com.example.SistemaGIS.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPenaltyPostRequestDTO {

    public String carNumberPlate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date date;

    public Integer mileage;

    public String checkpointArrival;

    public String checkpointExit;
}