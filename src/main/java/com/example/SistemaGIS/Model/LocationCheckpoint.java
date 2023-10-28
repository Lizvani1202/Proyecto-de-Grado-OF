package com.example.SistemaGIS.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name="location_checkpoint")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"tollsArrival", "tollsExit"})
@EqualsAndHashCode(exclude = {"tollsArrival", "tollsExit"})
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
    @JsonIgnore
    private Collection<Toll> tollsArrival;

    @OneToMany(mappedBy = "checkpointExit")
    @JsonIgnore
    private Collection<Toll> tollsExit;
}
