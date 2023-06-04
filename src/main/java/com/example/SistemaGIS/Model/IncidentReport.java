package com.example.SistemaGIS.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="incident_report")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncidentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incident_report_id")
    private Long incidentReportId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gis_id")
    private GIS gis;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "status")
    private Integer status;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_incident_report_id")
    private TypeIncidentReport typeIncidentReport;

}
