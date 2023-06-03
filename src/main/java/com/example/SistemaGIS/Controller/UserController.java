package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.*;
import com.example.SistemaGIS.Service.PersonService;
import com.example.SistemaGIS.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {


    private final UserService userService;
    private final PersonService personService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin loginData){
        try {
            User user = userService.getUser(loginData.getEmail()).orElseThrow(()-> new Exception("Usuario no encontrado"));
            if (user.getPassword().equals(loginData.getPassword()))
                return ResponseEntity.ok(user);
            return ResponseEntity.status(401).body("Datos de inicio de sesion invalidos");
        } catch (Exception ignored) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDTO userData){
        try{
            User user = userService.instanceUser(userData);
            userService.addRoleToUser(user, "USER"); //TODO: Change role based on current user role
            log.info("User: "+user.toString());
            User savedUser = userService.saveUser(user).orElseThrow(()-> new Exception("Error al guardar usuario"));
            UserRegisterResponseDTO response = new UserRegisterResponseDTO(savedUser);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
