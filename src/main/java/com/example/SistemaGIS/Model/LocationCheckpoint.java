package com.example.SistemaGIS.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name="location_checkpoint")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationCheckpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @JoinColumn(name = "name")
    private String name;

    @Column(name = "latitud")
    private BigDecimal latitud;

    @Column(name = "longitud")
    private BigDecimal longitud;

    @OneToMany(mappedBy = "checkpointArrival")
    private Collection<Toll> tollsArrival;

    @OneToMany(mappedBy = "checkpointExit")
    private Collection<Toll> tollsExit;
}
