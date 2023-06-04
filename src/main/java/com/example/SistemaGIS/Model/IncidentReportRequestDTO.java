package com.example.SistemaGIS.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncidentReportRequestDTO {

    private String ubicacion;

    private String type;

    private BigDecimal latitud;
    private BigDecimal longitud;
}
