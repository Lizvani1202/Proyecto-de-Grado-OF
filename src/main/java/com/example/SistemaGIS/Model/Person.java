package com.example.SistemaGIS.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "first_surname")
    private String firstSurname;

    @Column(name = "second_surname")
    private String secondSurname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;
}
