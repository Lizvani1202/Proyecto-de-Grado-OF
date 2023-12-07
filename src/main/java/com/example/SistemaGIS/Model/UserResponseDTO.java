package com.example.SistemaGIS.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long userId;

    private Person person;

    private String email;

    private String phoneNumber;

    private Integer status;

    private Collection<Role> userRoles = new HashSet<>();

    public UserResponseDTO(User user) {
        this.setUserId(user.getUserId());
        Person person = new Person(user.getPerson());
        this.setPerson(person);
        this.setEmail(user.getEmail());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setStatus(user.getStatus());
        this.setUserRoles(user.getUserRoles());
    }

    @Data
    private class Person {
        private Long personId;
        private String firstName;
        private String firstSurname;
        private String secondSurname;
        private LocalDate birthDate;
        private String address;
        private String city;

        public Person(com.example.SistemaGIS.Model.Person person) {
            this.setPersonId(person.getPersonId());
            this.setFirstName(person.getFirstName());
            this.setFirstSurname(person.getFirstSurname());
            this.setSecondSurname(person.getSecondSurname());
            this.setBirthDate(person.getBirthDate());
            this.setAddress(person.getAddress());
            this.setCity(person.getCity());
        }
    }

}
