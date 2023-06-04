package com.example.SistemaGIS.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="gis")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GIS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gis_id")
    private Long gisId;

    @Column(name = "latitud")
    private BigDecimal latitud;

    @Column(name = "longitud")
    private BigDecimal longitud;

    @Column(name = "status")
    private Integer status;

}
