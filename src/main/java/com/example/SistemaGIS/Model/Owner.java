package com.example.SistemaGIS.Model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="owner")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"user", "person"})
@EqualsAndHashCode(exclude = {"user", "person"})
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long ownerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "status")
    private Integer status;
}
