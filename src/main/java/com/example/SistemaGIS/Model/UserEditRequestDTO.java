package com.example.SistemaGIS.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEditRequestDTO {
    private Long userId;

    private PersonDTO person;

    private String phoneNumber;

    private Long roleId;

    @Data
    public class PersonDTO {
        private String firstName;

        private String firstSurname;

        private String secondSurname;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthDate;

        private String address;

        private String city;
    }
}
