package com.example.SistemaGIS.Service;

import com.example.SistemaGIS.Model.Person;
import com.example.SistemaGIS.Model.Role;
import com.example.SistemaGIS.Model.User;
import com.example.SistemaGIS.Model.UserRegisterRequestDTO;
import com.example.SistemaGIS.Repository.RoleRepository;
import com.example.SistemaGIS.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public Optional<User> getUser(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(userRepository.save(user));
    }
    public void addRoleToUser(User user, String rolename){
        Role role = roleRepository.findRoleByRoleName(rolename).orElseThrow(()-> new RuntimeException("Error: Rol no encontrado"));
        user.getUserRoles().add(role);
    }

    public User instanceUser(UserRegisterRequestDTO userData){
        User user = new User();
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setPhoneNumber(userData.getPhoneNumber());
        user.setStatus(userData.getStatus());
        Person person = new Person();
        person.setFirstName(userData.getPerson().getFirstName());
        person.setFirstSurname(userData.getPerson().getFirstSurname());
        person.setSecondSurname(userData.getPerson().getSecondSurname());
        person.setBirthDate(userData.getPerson().getBirthDate());
        person.setAddress(userData.getPerson().getAddress());
        person.setCity(userData.getPerson().getCity());
        user.setPerson(person);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
