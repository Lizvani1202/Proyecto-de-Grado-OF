package com.example.SistemaGIS.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="car_features")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_features_id")
    private Long carFeaturesId;

    @Column(name = "license_detector_id")
    private Integer licenseDetectorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "number_plate")
    private String numberPlate;

    @Column(name = "ruat")
    private String ruat;

    @Column(name = "brand")
    private String brand;

    @Column(name = "policy")
    private Integer policy;

    @Column(name = "country")
    private Integer country;

    @Column(name = "traction")
    private String traction;

    @Column(name = "color")
    private String color;

    @Column(name = "type")
    private String type;

    @Column(name = "model")
    private String model;

    @Column(name = "service")
    private String service;

    @Column(name = "filling")
    private String filling;

    @Column(name = "status")
    private Integer status;
}
