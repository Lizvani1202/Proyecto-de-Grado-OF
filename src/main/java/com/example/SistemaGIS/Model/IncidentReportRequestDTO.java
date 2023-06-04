package com.example.SistemaGIS.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncidentReportRequestDTO {

    private String ubicacion;

    private String type;

    private BigDecimal latitud;
    private BigDecimal longitud;
}
