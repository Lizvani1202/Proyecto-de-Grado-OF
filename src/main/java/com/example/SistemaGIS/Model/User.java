package com.example.SistemaGIS.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import javax.persistence.CascadeType;

@Entity
@Table(name="user")
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private Integer status;

    public User() {}
}
