package com.example.SistemaGIS.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="report_penalty")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportPenalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_features_id")
    private CarFeatures carFeatures;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @Column(name = "debt_amount")
    private Integer debtAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "status")
    private Integer status;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "checkpoint_arrival")
    private String checkpointArrival;

    @Column(name = "checkpoint_exit")
    private String checkpointExit;
}
