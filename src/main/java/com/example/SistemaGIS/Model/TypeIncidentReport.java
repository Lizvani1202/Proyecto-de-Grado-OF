package com.example.SistemaGIS.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;


@Entity
@Table(name="type_incident_report")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeIncidentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_incident_report_id")
    private Long typeIncidentReportId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;
}
