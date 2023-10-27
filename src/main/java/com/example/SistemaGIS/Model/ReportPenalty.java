package com.example.SistemaGIS.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="report_penalty")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportPenalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_penalty_id")
    private Long reportPenaltyId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_features_id")
    private CarFeatures carFeatures;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Column(name = "debt_amount")
    private Integer debtAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_checkpoint_id")
    private LocationCheckpoint locationCheckpoint;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "toll_id")
    private Toll toll;

    @Column(name = "status")
    private Integer status;
}
