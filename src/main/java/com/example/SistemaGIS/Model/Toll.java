package com.example.SistemaGIS.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="toll")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Toll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toll_id")
    private Long tollId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checkpoint_arrival_id")
    private LocationCheckpoint checkpointArrival;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checkpoint_exit_id")
    private LocationCheckpoint checkpointExit;

    @Column(name = "mileage_km")
    private Integer mileageKm;

    @Column(name = "private_car_max_speed_km_h")
    private Integer privateCarMaxSpeedKmH;

    @Column(name = "public_serv_car_max_speed_km_h")
    private Integer publicServCarMaxSpeedKmH;

    @OneToMany(mappedBy = "toll")
    private Collection<ReportPenalty> reportPenalties;
}
