package com.example.SistemaGIS.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterRequestDTO {
    private PersonDTO person;

    private String email;

    private String password;

    private String phoneNumber;

    private Integer status;

    @Data
    public class PersonDTO {
        private String firstName;

        private String firstSurname;

        private String secondSurname;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date birthDate;

        private String address;

        private String city;
    }
}
