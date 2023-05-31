package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.Person;
import com.example.SistemaGIS.Model.UserLogin;
import com.example.SistemaGIS.Model.User;
import com.example.SistemaGIS.Repository.PersonRepository;
import com.example.SistemaGIS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    @Autowired
    public UserController(UserRepository userRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin loginData){
        try {
            System.out.println(loginData);
            User user = userRepository.findUsersByEmail(loginData.getEmail());
            if (user.getPassword().equals(loginData.getPassword()))
                return ResponseEntity.ok(user);
            return ResponseEntity.status(401).body("Datos de inicio de sesion invalidos");
        } catch (Exception ignored) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User userData){
        try{
        System.out.println(userData);
        Person person = userData.getPerson();

        Person savedPerson = personRepository.save(person);
        userData.setPerson(savedPerson);

        User savedUser = userRepository.save(userData);
        return ResponseEntity.ok(savedUser);
        }catch (Exception ignored) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
