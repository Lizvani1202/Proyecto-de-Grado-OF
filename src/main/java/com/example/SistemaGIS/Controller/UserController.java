package com.example.SistemaGIS.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.SistemaGIS.Model.*;
import com.example.SistemaGIS.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {


    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDTO userData) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<String> roles = new HashSet<>();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                roles.add(authority.getAuthority());
            }
            User user = userService.instanceUser(userData);
            if (roles.contains("SIS_ADMIN_ABC")){
                userService.addRoleToUser(user, "ADMIN_ABC");
            } else if (roles.contains("SIS_POLICIA")){
                userService.addRoleToUser(user, "POLICIA");
            } else {
                userService.addRoleToUser(user, "USER");
            }
            User savedUser = userService.saveUser(user).orElseThrow(()-> new Exception("Error al guardar usuario"));
            UserRegisterResponseDTO response = new UserRegisterResponseDTO(savedUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error al registrar usuario: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        try {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username).orElseThrow(() -> new Exception("Usuario no encontrado"));
                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, Object> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                tokens.put("userId", user.getUserId());
                tokens.put("person", user.getPerson());
                tokens.put("email", user.getEmail());
                tokens.put("phoneNumber", user.getPhoneNumber());
                tokens.put("status", user.getStatus());
                tokens.put("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
                return ResponseEntity.ok(tokens);
            } else {
                return ResponseEntity.status(401).body("Refresh token es requerido");
            }
        } catch (Exception e) {
            log.error("Error al refrescar token: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get-users")
    @PreAuthorize("hasAnyAuthority('ROOT')")
    public ResponseEntity<?> getUsers(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<String> roles = new HashSet<>();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                roles.add(authority.getAuthority());
            }
            if (roles.contains("ROOT")){
                List<User> users = userService.getAllUsers().orElseThrow(() -> new Exception("No se encontraron usuarios"));
                List<UserResponseDTO> response = users.stream().map(UserResponseDTO::new).collect(Collectors.toList());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("No autorizado");
            }
        } catch (Exception e){
            log.error("Error al obtener usuarios: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/status-user")
    @PreAuthorize("hasAnyAuthority('ROOT')")
    public ResponseEntity<?> statusUser(@RequestParam("userId") Long userId, @RequestParam("status") Integer status){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<String> roles = new HashSet<>();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                roles.add(authority.getAuthority());
            }
            if (roles.contains("ROOT")){
                if(status != 0 && status != 1){
                    return ResponseEntity.status(400).body("Estado no válido");
                }
                User user = userService.getUserById(userId).orElseThrow(() -> new Exception("Usuario no encontrado"));
                user.setStatus(status);
                User savedUser = userService.saveUser(user).orElseThrow(() -> new Exception("Error al guardar usuario"));
                UserResponseDTO response = new UserResponseDTO(savedUser);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("No autorizado");
            }
        } catch (Exception e){
            log.error("Error al cambiar estado de usuario: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get-roles")
    @PreAuthorize("hasAnyAuthority('ROOT')")
    public ResponseEntity<?> getRoles(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<String> roles = new HashSet<>();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                roles.add(authority.getAuthority());
            }
            if (roles.contains("ROOT")){
                List<Role> rolesList = userService.getAllRoles().orElseThrow(() -> new Exception("No se encontraron roles"));
                List<Role> rolesResponse = rolesList.stream().filter(role -> !role.getRoleName().equals("ROOT")).collect(Collectors.toList());
                return ResponseEntity.ok(rolesResponse);
            } else {
                return ResponseEntity.status(401).body("No autorizado");
            }
        } catch (Exception e){
            log.error("Error al obtener roles: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
