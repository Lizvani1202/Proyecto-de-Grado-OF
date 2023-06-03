package com.example.SistemaGIS.Service;

import com.example.SistemaGIS.Model.Person;
import com.example.SistemaGIS.Model.Role;
import com.example.SistemaGIS.Model.User;
import com.example.SistemaGIS.Repository.PersonRepository;
import com.example.SistemaGIS.Repository.RoleRepository;
import com.example.SistemaGIS.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    public Optional<Person> savePerson(Person person){
        return Optional.of(personRepository.save(person));
    }
}
