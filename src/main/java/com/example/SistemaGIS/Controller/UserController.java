package com.example.SistemaGIS.Controller;

import com.example.SistemaGIS.Model.*;
import com.example.SistemaGIS.Service.PersonService;
import com.example.SistemaGIS.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {


    private final UserService userService;

    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('ROOT', 'SIS_ADMIN_ABC', 'SIS_POLICE')")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDTO userData, HttpServletRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<String> roles = new HashSet<>();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                roles.add(authority.getAuthority());
            }
            User user = userService.instanceUser(userData);
            if (roles.contains("SIS_ADMIN_ABC")){
                userService.addRoleToUser(user, "ADMIN_ABC");
            } else if (roles.contains("SIS_POLICE")){
                userService.addRoleToUser(user, "POLICE");
            } else {
                userService.addRoleToUser(user, "DRIVER");
            }
            User savedUser = userService.saveUser(user).orElseThrow(()-> new Exception("Error al guardar usuario"));
            UserRegisterResponseDTO response = new UserRegisterResponseDTO(savedUser);
            return ResponseEntity.ok("Usuario registrado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
