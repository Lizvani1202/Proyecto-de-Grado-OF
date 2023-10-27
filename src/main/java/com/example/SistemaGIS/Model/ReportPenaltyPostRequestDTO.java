package com.example.SistemaGIS.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPenaltyPostRequestDTO {

    public String carNumberPlate;

    public String checkpointExitName;
}
