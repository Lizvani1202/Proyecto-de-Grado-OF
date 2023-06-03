package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.*;
import com.example.SistemaGIS.Service.PersonService;
import com.example.SistemaGIS.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {


    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDTO userData){
        try{
            User user = userService.instanceUser(userData);
            userService.addRoleToUser(user, "USER"); //TODO: Change role based on current user role
            User savedUser = userService.saveUser(user).orElseThrow(()-> new Exception("Error al guardar usuario"));
            UserRegisterResponseDTO response = new UserRegisterResponseDTO(savedUser);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
