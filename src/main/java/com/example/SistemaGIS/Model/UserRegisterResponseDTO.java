package com.example.SistemaGIS.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


@Data
public class UserRegisterResponseDTO {
    private Long userId;
    private PersonDTO person;

    private String email;

    private String phoneNumber;

    private Integer status;

    private Collection<RoleDTO> userRoles = new HashSet<>();

    public UserRegisterResponseDTO(User user){
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.status = user.getStatus();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonId(user.getPerson().getPersonId());
        personDTO.setFirstName(user.getPerson().getFirstName());
        personDTO.setFirstSurname(user.getPerson().getFirstSurname());
        personDTO.setSecondSurname(user.getPerson().getSecondSurname());
        personDTO.setBirthDate(user.getPerson().getBirthDate());
        personDTO.setAddress(user.getPerson().getAddress());
        personDTO.setCity(user.getPerson().getCity());
        this.person = personDTO;
        for(Role role : user.getUserRoles()){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId(role.getRoleId());
            roleDTO.setRoleName(role.getRoleName());
            roleDTO.setStatus(role.getStatus());
            this.userRoles.add(roleDTO);
        }
    }

    @Data
    public class PersonDTO {
        private Long personId;

        private String firstName;

        private String firstSurname;

        private String secondSurname;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date birthDate;

        private String address;

        private String city;
    }

    @Data
    public class RoleDTO {
        private Long roleId;
        private String roleName;
        private Integer status;
    }
}
